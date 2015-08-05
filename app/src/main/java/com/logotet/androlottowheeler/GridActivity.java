package com.logotet.androlottowheeler;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.logotet.androlottowheeler.model.AllStatic;


public class GridActivity extends ActionBarActivity {
    private static final String TAG = "ArhivaActivity";
    TicketAdapter adapter;
    TextView tvPickedNumbers;
    TextView countText;
    TextView warningText;
    TextView titleLine;
    Button btnClear;
    Button btnGenerate;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        tvPickedNumbers= (TextView) findViewById(R.id.tvPickedNumbers);
        countText = (TextView) findViewById(R.id.tvCountText);
        warningText = (TextView) findViewById(R.id.tvWarning);
       titleLine = (TextView) findViewById(R.id.tvTitleLine);

        titleLine.setText("Select your " + AllStatic.systemSize + " numbers");

        btnClear = (Button) findViewById(R.id.btnClear);
        btnGenerate = (Button) findViewById(R.id.btnGenerate);



        adapter = new TicketAdapter(this);
        adapter.setPickedNumbers(tvPickedNumbers);
        adapter.setCountText(countText);
        adapter.setWarningText(warningText);
        adapter.setBtnGenerate(btnGenerate);


        GridView gridview = (GridView) findViewById(R.id.ticketGrid);
        gridview.setAdapter(adapter);


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllStatic.clearAll();
                adapter.notifyDataSetChanged();
                countText.setText("");
                tvPickedNumbers.setText("");
            }
        });

    }

}
