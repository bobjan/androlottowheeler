package com.logotet.androlottowheeler;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.logotet.androlottowheeler.model.AllStatic;
import com.logotet.androlottowheeler.model.MyNumberCell;

/**
 * Created by boban on 8/2/15.
 */
public class TicketAdapter extends BaseAdapter {
    private static final String TAG = "TiketAdapter";
    TextView pickedNumbers;
    TextView countText;
    TextView warningText;
    Button btnGenerate;
    private Context mContext;

    public TicketAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return AllStatic.tiket.size();
    }

    @Override
    public Object getItem(int position) {
        return AllStatic.tiket.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final MyNumberCell myButton = (MyNumberCell) getItem(position);
        Resources res = mContext.getResources();
        Button btn = new Button(mContext);
        int crno = res.getColor(R.color.black);
        int belo = res.getColor(R.color.white);
        int backclr = res.getColor(R.color.red);

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            btn = new Button(mContext);
            btn.setPadding(0, 0, 0, 0);
            btn.setBackgroundResource(R.drawable.lottobutton);
            btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) 10.0);
            btn.setTextColor(crno);
            btn.setEnabled(true);
            btn.setClickable(true);


            btn.setLayoutParams(new GridView.LayoutParams(AllStatic.pixelWidth / 11, AllStatic.pixelWidth / 11));
        } else {

            btn = (Button) convertView;
        }

        if (myButton.isPicked()) {
            btn.setBackgroundColor(backclr);
            btn.setTextColor(belo);
        } else {
            btn.setBackgroundResource(R.drawable.lottobutton);
            btn.setTextColor(crno);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myButton.switchState();
                AllStatic.recalculate();
                countText.setText("You selected " + AllStatic.pickedCounter + " number" + ((AllStatic.pickedCounter == 1) ? "" : "s"));
                pickedNumbers.setText(AllStatic.pickedText);
                changeDisplayAtributes();
                notifyDataSetChanged();
            }
        });

        btn.setText(myButton.getValue() + "");
        return btn;
    }

    private void changeDisplayAtributes() {
        if (AllStatic.pickedCounter == AllStatic.selectedWheel.getSelectionSize()) {
            btnGenerate.setVisibility(View.VISIBLE);
        } else {
            btnGenerate.setVisibility(View.GONE);
        }
        if (AllStatic.pickedCounter > AllStatic.selectedWheel.getSelectionSize()) {
            warningText.setVisibility(View.VISIBLE);
        } else {
            warningText.setVisibility(View.INVISIBLE);
        }

    }


    /**
     * setters for field from parent activity
     */
    public void setBtnGenerate(Button btnGenerate) {
        this.btnGenerate = btnGenerate;
    }

    public void setCountText(TextView countText) {
        this.countText = countText;
    }

    public void setPickedNumbers(TextView pickedNumbers) {
        this.pickedNumbers = pickedNumbers;
    }

    public void setWarningText(TextView warningText) {
        this.warningText = warningText;
    }
}
