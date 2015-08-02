package com.logotet.androlottowheeler;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;


public class GridActivity extends ActionBarActivity {
    private static final String TAG = "ArhivaActivity";
    TicketAdapter adapter;
    TextView tekst;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        tekst = (TextView) findViewById(R.id.gridText);

        adapter = new TicketAdapter(this);
        adapter.setTekst(tekst);
        GridView gridview = (GridView) findViewById(R.id.ticketGrid);
        gridview.setNumColumns(4);
        gridview.setAdapter(adapter);

    }

}
