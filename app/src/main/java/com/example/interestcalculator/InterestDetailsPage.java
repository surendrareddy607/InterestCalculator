package com.example.interestcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.ListView;
import android.os.Bundle;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class InterestDetailsPage extends AppCompatActivity {

    ArrayList<DataModel> obj = new ArrayList<DataModel>();
    private TextView lblGivenValue;
    int months = 0;
    private ListView list;
    NumberFormat nf = NumberFormat.getInstance(new Locale("en", "IN"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_details_page);

       lblGivenValue = (TextView)findViewById(R.id.lblGivenValue);
        if(MainActivity.givenValue != null && !MainActivity.givenValue.trim().isEmpty()) {
            lblGivenValue.setText(nf.format(Double.valueOf(MainActivity.givenValue)));
        }
        else
        {
            lblGivenValue.setText("0");
        }

        int i = 1;
        for (DataModel items : MainActivity.financeobj)
        {

            if(items.Months != 0)
            {
                months = items.Months;
                items.setTotalInterestPerYear(String.format("TOTAL INTEREST FOR \n%d MONTH(S):-", items.Months));
                items.setTotalAmountPerYear(String.format("TOTAL AMOUNT FOR \n%d YEAR(S)- %d MONTH(S) WITH INTEREST:-", i - 1, items.Months));
            }
            else if (items.Days != 0)
            {
                items.setTotalInterestPerYear(String.format("TOTAL INTEREST FOR \n%d DAY(S):-", items.Days));
                items.setTotalAmountPerYear(String.format("TOTAL AMOUNT FOR \n%d YEAR(S)- %d MONTH(S)\n- %d DAY(S) WITH INTEREST:-", i-2, months, items.Days));
            }
            else if (i == 1)
            {
                items.setTotalInterestPerYear(String.format("TOTAL INTEREST FOR \n1 YEAR:-"));
                items.setTotalAmountPerYear(String.format("TOTAL AMOUNT FOR \n%d YEAR WITH INTEREST:-", i));
            }
            else
            {
                items.setTotalInterestPerYear(String.format("TOTAL INTEREST FOR \nNEXT YEAR:-"));
                items.setTotalAmountPerYear(String.format("TOTAL AMOUNT FOR \n%d YEARS WITH INTEREST:-", i));
            }
            obj.add(items);
            i++;
        }

        MyListAdapter adapter=new MyListAdapter( obj,this);
        list=(ListView)findViewById(R.id.listview);
        list.setAdapter(adapter);
    }
}
