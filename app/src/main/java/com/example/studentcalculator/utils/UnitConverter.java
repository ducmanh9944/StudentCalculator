package com.example.studentcalculator.utils;

public class UnitConverter {
    public static final String CATEGORY_LENGTH = "Độ dài";
    public static final String CATEGORY_WEIGHT = "Khối lượng";
    public static final String CATEGORY_TEMPERATURE = "Nhiệt độ";

    public static double convert(String category, String from, String to, double value) {
        switch (category) {
            case CATEGORY_LENGTH:
                return convertLength(from, to, value);
            case CATEGORY_WEIGHT:
                return convertWeight(from, to, value);
            case CATEGORY_TEMPERATURE:
                return convertTemperature(from, to, value);
            default:
                return value;
        }
    }

    private static double convertLength(String from, String to, double value) {
        // Chuyển hết về mét (m) làm trung gian
        double inMeters = value;
        switch (from) {
            case "mm": inMeters = value / 1000.0; break;
            case "cm": inMeters = value / 100.0; break;
            case "dm": inMeters = value / 10.0; break;
            case "m": inMeters = value; break;
            case "km": inMeters = value * 1000.0; break;
        }

        // Từ mét chuyển sang đơn vị đích
        switch (to) {
            case "mm": return inMeters * 1000.0;
            case "cm": return inMeters * 100.0;
            case "dm": return inMeters * 10.0;
            case "m": return inMeters;
            case "km": return inMeters / 1000.0;
            default: return inMeters;
        }
    }

    private static double convertWeight(String from, String to, double value) {
        // Chuyển hết về gram (g) làm trung gian
        double inGrams = value;
        switch (from) {
            case "mg": inGrams = value / 1000.0; break;
            case "g": inGrams = value; break;
            case "kg": inGrams = value * 1000.0; break;
            case "tấn": inGrams = value * 1000000.0; break;
        }

        switch (to) {
            case "mg": return inGrams * 1000.0;
            case "g": return inGrams;
            case "kg": return inGrams / 1000.0;
            case "tấn": return inGrams / 1000000.0;
            default: return inGrams;
        }
    }

    private static double convertTemperature(String from, String to, double value) {
        if (from.equals(to)) return value;

        // Chuyển về Celsius
        double celsius = value;
        if (from.equals("Fahrenheit (°F)")) {
            celsius = (value - 32) * 5 / 9;
        } else if (from.equals("Kelvin (K)")) {
            celsius = value - 273.15;
        }

        // Từ Celsius chuyển sang đích
        if (to.equals("Celsius (°C)")) {
            return celsius;
        } else if (to.equals("Fahrenheit (°F)")) {
            return celsius * 9 / 5 + 32;
        } else if (to.equals("Kelvin (K)")) {
            return celsius + 273.15;
        }
        return celsius;
    }
}