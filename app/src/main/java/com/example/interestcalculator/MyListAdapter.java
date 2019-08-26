package com.example.interestcalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<DataModel> {

   NumberFormat nf = NumberFormat.getInstance(new Locale("en", "IN"));
    public MyListAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.activity_listview, data);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel list = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_listview, parent, false);
        }
        // Lookup view for data population
        TextView lblTotalInterest = (TextView) convertView.findViewById(R.id.lblTotalInterest);
        TextView lblTotalInterestValue = (TextView) convertView.findViewById(R.id.lblTotalInterestValue);
        TextView lblTotalAmount = (TextView) convertView.findViewById(R.id.lblTotalAmount);
        TextView lblTotalAmountValue = (TextView) convertView.findViewById(R.id.lblTotalAmountValue);
        // Populate the data into the template view using the data object
        lblTotalInterest.setText(list.getTotalInterestPerYear());
        lblTotalInterestValue.setText(nf.format(Double.valueOf(list.getTotalInterest())));
        lblTotalAmount.setText(list.getTotalAmountPerYear());
        lblTotalAmountValue.setText(nf.format(Double.valueOf(list.getAmount())));
        // Return the completed view to render on screen
        return convertView;
    }
}
