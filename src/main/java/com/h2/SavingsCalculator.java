package com.h2;

import java.time.LocalDate;
import java.time.YearMonth;


public class SavingsCalculator {

    private float[] credits;
    private float[] debits;

    public SavingsCalculator(float[] credits, float[] debits) {
        this.credits = credits;
        this.debits = debits;
    }

    private float sumOfCredits() {
        var sum = 0.0f;
        for (float credit : credits) {
            sum += credit;
        }
        return sum;
    }

    private float sumOfDebits() {
        var sum = 0.0f;
        for (float debit : debits) {
            sum += debit;
        }

        return sum;
    }

    private static int remainingDaysInMonth(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        YearMonth yearMonth = YearMonth.of(year, month);

        int totalDaysInMonth = yearMonth.lengthOfMonth();

        return totalDaysInMonth - date.getDayOfMonth();
    }

    public float calculate() {
        return sumOfCredits() - sumOfDebits();
    }

    public static void main(String[] args) {
        final String[] creditsAsString = args[0].split(",");
        final String[] debitsAsString = args[1].split(",");

        final float[] credits = new float[creditsAsString.length];
        final float[] debits = new float[debitsAsString.length];

        for (int i = 0; i < creditsAsString.length; i++) {
            credits[i] = Utilities.getFloatValue(creditsAsString[i]);
        }

        for (int i = 0; i < debitsAsString.length; i++) {
            debits[i] = Utilities.getFloatValue(debitsAsString[i]);
        }

        SavingsCalculator calculator = new SavingsCalculator(credits, debits);
        float netSavings = calculator.calculate();
        int remainingDays = remainingDaysInMonth(LocalDate.now());
        System.out.println("Net Savings = " + netSavings + ", remaining days in month = " + remainingDays);
    }

}
