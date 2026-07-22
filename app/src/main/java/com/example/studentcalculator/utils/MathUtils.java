package com.example.studentcalculator.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MathUtils {

    public static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b > 0) {
            a %= b;
            long temp = a;
            a = b;
            b = temp;
        }
        return a;
    }

    public static long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return Math.abs(a * b) / gcd(a, b);
    }

    /**
     * Phân tích một số ra thừa số nguyên tố.
     * Ví dụ: 120 -> 2^3 * 3 * 5
     */
    public static String primeFactorization(long n) {
        if (n < 2) return String.valueOf(n);
        
        Map<Long, Integer> factors = new TreeMap<>();
        long temp = n;
        for (long i = 2; i <= temp / i; i++) {
            while (temp % i == 0) {
                factors.put(i, factors.getOrDefault(i, 0) + 1);
                temp /= i;
            }
        }
        if (temp > 1) {
            factors.put(temp, factors.getOrDefault(temp, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Long, Integer> entry : factors.entrySet()) {
            if (sb.length() > 0) sb.append(" × ");
            sb.append(entry.getKey());
            if (entry.getValue() > 1) {
                sb.append(toSuperscript(entry.getValue()));
            }
        }
        return sb.toString();
    }

    private static String toSuperscript(int num) {
        String str = String.valueOf(num);
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            switch (c) {
                case '0': sb.append("⁰"); break;
                case '1': sb.append("¹"); break;
                case '2': sb.append("²"); break;
                case '3': sb.append("³"); break;
                case '4': sb.append("⁴"); break;
                case '5': sb.append("⁵"); break;
                case '6': sb.append("⁶"); break;
                case '7': sb.append("⁷"); break;
                case '8': sb.append("⁸"); break;
                case '9': sb.append("⁹"); break;
            }
        }
        return sb.toString();
    }
}