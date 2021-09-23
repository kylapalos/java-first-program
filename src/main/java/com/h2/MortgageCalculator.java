package com.h2;


import java.text.DecimalFormat;

public class MortgageCalculator {

    private long loanAmount = 0L;
    private int termInYears = 0;
    private float annualRate = 0.0f;
    private double monthlyPayment = 0.00d;

    public MortgageCalculator(long loanAmount, int termInYears, float annualRate) {
        this.loanAmount = loanAmount;
        this.termInYears = termInYears;
        this.annualRate = annualRate;
    }

    private int getNumberOfPayments() {
        return termInYears * 12;
    }

    private float getMonthlyInterestRate() {
        final float interestRate = annualRate / 100;
        return interestRate / 12;
    }

    public void calculateMonthlyPayment() {
        final long principal = loanAmount;
        final float rate = getMonthlyInterestRate();
        final int numOfPayments = getNumberOfPayments();

        final double monthly = principal * (
                ((rate * Math.pow(1 + rate, numOfPayments))) / ((Math.pow((1 + rate), numOfPayments)) - 1)
        );

        this.monthlyPayment = monthly;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("####0.00");

        return "monthlyPayment: " + df.format(monthlyPayment);
    }

    public static void main(String[] args) {
        final long loanAmount = Utilities.getLongValue(args[0]);
        final int termInYears = Utilities.getIntValue(args[1]);
        final float annualRate = Utilities.getFloatValue(args[2]);

        MortgageCalculator calculator = new MortgageCalculator(loanAmount, termInYears, annualRate);
        calculator.calculateMonthlyPayment();
        System.out.println(calculator);
    }
}
