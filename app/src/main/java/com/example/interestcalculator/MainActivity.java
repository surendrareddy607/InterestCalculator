package com.example.interestcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {

    private EditText GivenAmount;
    private EditText GivenInterest;
    private EditText GivenMonths;
    private EditText GivenDays;
    private Button btnInterest;
    private TextView lblPAmountValue;
    private TextView lblTInterestValue;
    private TextView lblTAmountValue;
    private  RelativeLayout  ResultRelative;
    private Button btnCInterest;
    private TextView lblTInterest;
    private Button btnClear;
    private TextView lblTAmount;
    private Button btnViewDetails;
    private TextView lblInfo;

    double Amount = 0;
    double Interest = 0;
    double months = 0;
    double InterestPerMonth = 0;
    double TotalInterest = 0;
    int Days = 0;
    double amount = 0;
    double InterestPerMonthInYear = 0;
    public static ArrayList<DataModel> financeobj = new ArrayList<DataModel>();
    public static String givenValue;
    NumberFormat nf = NumberFormat.getInstance(new Locale("en", "IN"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_app);

        GivenAmount = (EditText)findViewById(R.id.etAmount);
        GivenInterest = (EditText)findViewById(R.id.etInterest);
        GivenMonths = (EditText)findViewById(R.id.etMonths);
        GivenDays = (EditText)findViewById(R.id.etDays);
        btnInterest = (Button) findViewById(R.id.btnInterest);
        lblPAmountValue = (TextView)findViewById(R.id.lblPAmountValue);
        lblTInterestValue = (TextView)findViewById(R.id.lblTInterestValue);
        lblTAmountValue = (TextView)findViewById(R.id.lblTAmountValue);
        ResultRelative = (RelativeLayout)findViewById(R.id.ResultRelative);
        btnCInterest = (Button) findViewById(R.id.btnCInterest);
        lblTInterest = (TextView)findViewById(R.id.lblTInterest);
        btnClear = (Button) findViewById(R.id.btnClear);
        lblTAmount = (TextView)findViewById(R.id.lblTAmount);
        btnViewDetails = (Button) findViewById(R.id.btnViewDetails);
        lblInfo = (TextView)findViewById(R.id.lblInfo);
        givenValue = GivenAmount.getText().toString();




        btnInterest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ResultRelative.setVisibility(View.VISIBLE);
                lblTAmountValue.setVisibility(View.VISIBLE);
                lblTAmount.setVisibility(View.VISIBLE);
                lblInfo.setVisibility(View.INVISIBLE);
                btnViewDetails.setVisibility(View.INVISIBLE);
                Amount = 0;
                Interest = 0;
                months = 0;
                Days = 0;
                GetInputs();
                InterestPerMonth = (Amount * Interest) / 100;
                TotalInterest = InterestPerMonth * months;
                Amount = Amount + TotalInterest;
                if (Days != 0)
                {
                    double InterestPerDay = InterestPerMonth / 30;
                    double DaysInterest = InterestPerDay * Days;
                    TotalInterest = TotalInterest + DaysInterest;
                    Amount = Amount + DaysInterest;
                }

                if(GivenAmount.getText().toString() != null && !GivenAmount.getText().toString().trim().isEmpty()) {
                    lblPAmountValue.setText(nf.format(Double.valueOf(GivenAmount.getText().toString())));
                    lblTInterestValue.setText(nf.format(TotalInterest));
                    lblTAmountValue.setText(nf.format(Amount));
                }
                else {
                    lblPAmountValue.setText("0");
                }

            }
        });






        btnCInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amount = 0;
                Interest = 0;
                months = 0;
                Days = 0;
                givenValue = GivenAmount.getText().toString();
                ResultRelative.setVisibility(View.VISIBLE);
                lblInfo.setVisibility(View.VISIBLE);
                btnViewDetails.setVisibility(View.VISIBLE);
                lblTAmountValue.setVisibility(View.INVISIBLE);
                lblTAmount.setVisibility(View.INVISIBLE);
                GetInputs();
                DoubleInterest();

                if(GivenAmount.getText().toString() != null && !GivenAmount.getText().toString().trim().isEmpty()) {
                    lblPAmountValue.setText(nf.format(Double.valueOf(GivenAmount.getText().toString())));
                    lblTInterestValue.setText(nf.format(Amount));

                }
                else {
                    lblPAmountValue.setText("0");
                }
                lblTInterest.setText("Total Amount");
            }
        });




        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amount = 0;
                Interest = 0;
                months = 0;
                Days = 0;
                ResultRelative.setVisibility(View.INVISIBLE);
                lblInfo.setVisibility(View.INVISIBLE);
                btnViewDetails.setVisibility(View.INVISIBLE);
                GivenAmount.setText("");
                GivenInterest.setText("");
                GivenMonths.setText("");
                GivenDays.setText("");

            }
        });




        btnViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InterestDetailsPage.class);
                startActivity(intent);

            }
        });

    }


    public void DoubleInterest()
    {
        int i;
        int years;
        financeobj = new ArrayList<DataModel>();
        //var years = Convert.ToInt32(months / 12);
        int index = Double.toString((months / 12)).indexOf(".");
        if (index > 0)
        {
            years = Integer.parseInt(Double.toString(months / 12).substring(0, index));
        }
        else
        {
            years = (int)(months / 12);
        }
        String value = Double.toString(months / 12);
        if (years >= 1)
        {
            for (i = 0; i < years; i++)
            {
                DataModel obj = new DataModel();
                InterestPerMonth = (Amount * Interest) / 100;
                TotalInterest = InterestPerMonth * 12;
                Amount = Amount + TotalInterest;
                amount = Amount;
                InterestPerMonthInYear = (Amount * Interest) / 100;
                obj.setAmount(Double.toString(Amount));
                obj.setTotalInterest(Double.toString(TotalInterest));
                financeobj.add(obj);
            }
        }

        if(value.contains(".") && !value.contains(".0"))
        {
            DataModel obj = new DataModel();
            int YearsConvertToMonths = years * 12;
            InterestPerMonth = (Amount * Interest) / 100;
            amount = Amount;
            //  if (years >= 1)
            // {
            double RemainingMonths = months - YearsConvertToMonths;
            TotalInterest = InterestPerMonth * RemainingMonths;
            //}
            //else
            //{
            //    TotalInterest = InterestPerMonth * c;
            //}
            Amount = Amount + TotalInterest;
            obj.setAmount(Double.toString(Amount));
            obj.setMonths((int)RemainingMonths);
            obj.setTotalInterest(Double.toString(TotalInterest));
            financeobj.add(obj);
        }

        if (Days != 0)
        {
            DataModel obj = new DataModel();
            obj.setDays(Days);
            InterestPerMonth = (amount * Interest) / 100;
            double InterestPerDay = InterestPerMonth / 30;
            double DaysInterest = InterestPerDay * Days;
            //TotalInterest = TotalInterest + DaysInterest;
            Amount = Amount + DaysInterest;
            obj.setAmount(Double.toString(Amount));
            obj.setTotalInterest(Double.toString(DaysInterest));
            financeobj.add(obj);
        }
    }




    public void GetInputs()
    {
        if(GivenAmount.getText().toString() != null && !GivenAmount.getText().toString().trim().isEmpty()) {
            Amount = Double.parseDouble(GivenAmount.getText().toString());
        }
        else
        {
            Amount = 0;
        }
        //  int val = Integer.parseInt( num.getText().toString() );
        if(GivenInterest.getText().toString() != null && !GivenInterest.getText().toString().trim().isEmpty()) {
            Interest = Double.parseDouble(GivenInterest.getText().toString());
        }
        else
        {
            Interest = 0;
        }

        if(GivenMonths.getText().toString() != null && !GivenMonths.getText().toString().trim().isEmpty()) {
            months = Double.parseDouble(GivenMonths.getText().toString());
        }
        else
        {
            months = 0;
        }

        if(GivenDays.getText().toString() != null && !GivenDays.getText().toString().trim().isEmpty()) {
            Days = Integer.parseInt(GivenDays.getText().toString());
        }
        else
        {
            Days = 0;
        }
    }
}
