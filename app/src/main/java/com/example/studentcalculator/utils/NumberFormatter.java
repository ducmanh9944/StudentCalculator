package com.example.studentcalculator.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumberFormatter {

    /**
     * Định dạng giá trị kiểu double theo SRS:
     * - Tối đa 4 chữ số thập phân.
     * - Loại bỏ các số 0 ở cuối.
     * - Sử dụng dấu chấm (.) làm dấu phân cách thập phân.
     */
    public static String format(double value) {
        // Handle whole numbers to avoid ".0"
        if (value == (long) value) {
            return String.format(Locale.US, "%d", (long) value);
        }

        // DecimalFormat for max 4 decimal places and removing trailing zeros
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        
        DecimalFormat df = new DecimalFormat("0.####", symbols);
        return df.format(value);
    }
}