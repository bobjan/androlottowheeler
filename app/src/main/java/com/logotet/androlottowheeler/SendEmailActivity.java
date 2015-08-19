package com.logotet.androlottowheeler;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.logotet.androlottowheeler.model.AllStatic;
import com.logotet.androlottowheeler.threads.AttachmentMaker;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/***
 * proveriti ove skracene sisteme
 * ﹕ 12660408.LS7
 * 13660888.LS7
 * 13670224.LS7
 */

public class SendEmailActivity extends ActionBarActivity {
    private static final String TAG = "SelndEmailActivity";

    private static final String username = "boban.yankovic@gmail.com";
    private static final String password = "$serbia$";

    private File file;
    String subject = "Lotto wheel";
    String messageBody;

    private EditText email;
    private Button btnSendEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendemail);

        AttachmentMaker maker = new AttachmentMaker(this);
        maker.start();

        ;

        email = (EditText) findViewById(R.id.etEmail);
        email.setText(AllStatic.email);

        btnSendEmail = (Button) findViewById(R.id.btnSendEmail);

       messageBody = "Ovo je message body a treba da bude jos vesi bla bla";

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailValid(email.getText().toString())){
                    AllStatic.email = email.getText().toString();
                    SharedPreferences prefs = getSharedPreferences(AllStatic.MY_PREFS_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("email", AllStatic.email);
                    editor.commit();



                    sendMail(email.getText().toString(), subject, messageBody);
                }else{
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Not a valid email address", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    private void sendMail(String email, String subject, String messageBody) {
        Session session = createSessionObject();

        try {
            Message message = createMessage(email, subject, messageBody, session);

            new SendMailTask().execute(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private Message createMessage(String email, String subject, String messageBody, Session session) throws MessagingException, UnsupportedEncodingException {
        if(AllStatic.attachment == null)
            throw new MessagingException("Not a valid attachment");

        File file = AllStatic.attachment;

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("noreplys@lottoweheeler.com", "Android Lotto Wheel"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, email));
        message.setSubject(subject);


        Multipart multipart = new MimeMultipart();
        // creates body part for the message
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(messageBody, "text/html");
        // creates body part for the attachment
        MimeBodyPart attachPart = new MimeBodyPart();
        DataSource source = new FileDataSource(file);
        attachPart.setDataHandler(new DataHandler(source));
        attachPart.setFileName(file.getName());
        // adds parts to the multipart
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachPart);
        message.setContent(multipart);
        return message;
    }

    private Session createSessionObject() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }


    private class SendMailTask extends AsyncTask<Message, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(SendEmailActivity.this, "Please wait", "Sending mail", true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public boolean emailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
