package com.logotet.androlottowheeler.threads;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import com.logotet.androlottowheeler.model.AllStatic;
import com.logotet.androlottowheeler.model.Kombinacija;
import com.logotet.androlottowheeler.model.Sistem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

/**
 * Created by boban on 8/19/15.
 */
public class AttachmentMaker extends Thread {
    private static final String TAG = "Attachment Maker";
    private Activity caller;

    public AttachmentMaker(Activity caller) {
        this.caller = caller;
    }

    @Override
    public void run() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {

            AllStatic.attachment = new File(caller.getExternalFilesDir(null), AllStatic.selectedWheel.getFileName() + ".txt");
            try {
                OutputStream os = new FileOutputStream(AllStatic.attachment);
                Iterator iterator =  AllStatic.mappedSistem.getKombinacije().iterator();

                while (iterator.hasNext()){
                    Kombinacija k = (Kombinacija) iterator.next();
                    os.write(k.toString().getBytes());
                    os.write("\n".getBytes());
                }
                os.close();
            } catch (IOException e) {
                Log.w("ExternalStorage", "Error writing " + AllStatic.attachment, e);
            }
        }
    }
}
