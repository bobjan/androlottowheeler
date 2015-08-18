package com.logotet.androlottowheeler;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.logotet.androlottowheeler.model.AllStatic;
import com.logotet.androlottowheeler.threads.MappingThread;


public class GridActivity extends ActionBarActivity {
    private static final String TAG = "GridActivity";
    TicketAdapter adapter;
    TextView tvPickedNumbers;
    TextView countText;
    TextView warningText;
    TextView titleLine;
    Button btnClear;
    Button btnGenerate;


    Intent genIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        tvPickedNumbers = (TextView) findViewById(R.id.tvPickedNumbers);
        countText = (TextView) findViewById(R.id.tvCountText);
        warningText = (TextView) findViewById(R.id.tvWarning);
        titleLine = (TextView) findViewById(R.id.tvTitleLine);

        titleLine.setText("Select your " + AllStatic.selectedWheel.getSelectionSize() + " numbers");

        btnClear = (Button) findViewById(R.id.btnClear);
        btnGenerate = (Button) findViewById(R.id.btnGenerate);


        adapter = new TicketAdapter(this);
        adapter.setPickedNumbers(tvPickedNumbers);
        adapter.setCountText(countText);
        adapter.setWarningText(warningText);
        adapter.setBtnGenerate(btnGenerate);


        GridView gridview = (GridView) findViewById(R.id.ticketGrid);
        gridview.setAdapter(adapter);

        genIntent = new Intent(this, GeneratedWheelActivity.class);


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllStatic.clearAll();
                btnGenerate.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
                countText.setText("");
                tvPickedNumbers.setText("");
            }
        });

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssetManager assetManager = getAssets();
                String[] files = new String[0];
                MappingThread thread = new MappingThread(assetManager);
                thread.start();

                startActivity(genIntent);

            }
        });

    }

}
