package com.example.interestcalculator;

public class DataModel {

    String TotalInterest = "0";
    String Amount = "0";
    String TotalAmountPerYear = "0";
    String TotalInterestPerYear = "0";
    int Days = 0;
    int Months = 0;


    public String getTotalInterest() {
        return TotalInterest;
    }

    public String getAmount() {
        return Amount;
    }

    public String getTotalAmountPerYear() {
        return TotalAmountPerYear;
    }

    public String getTotalInterestPerYear() {
        return TotalInterestPerYear;
    }

    public int getDays() {
        return Days;
    }

    public int getMonths() {
        return Months;
    }





    public void setTotalInterest(String value) {
        TotalInterest = value;
    }

    public void setAmount(String value) {
        Amount = value;
    }

    public void setTotalAmountPerYear(String value) {
        TotalAmountPerYear = value;
    }

    public void setTotalInterestPerYear(String value) {
        TotalInterestPerYear = value;
    }

    public void setDays(int value) {
        Days = value;
    }

    public void setMonths(int value) {
        Months = value;
    }
}
