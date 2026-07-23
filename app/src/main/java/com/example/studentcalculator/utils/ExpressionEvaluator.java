package com.example.studentcalculator.utils;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class ExpressionEvaluator {

    public static double evaluate(String expression) throws Exception {
        if (expression == null || expression.trim().isEmpty()) {
            return 0;
        }

        String processed = expression
                .replace('×', '*')
                .replace('÷', '/')
                .replace(',', '.')
                .replace("π", "pi"); // Thêm hỗ trợ số Pi

        // (A + B% -> A + (A * B / 100))
        processed = handlePercentage(processed);

        // Xử lý nhân ngầm định (Ví dụ: 2√9 -> 2*√9, 2π -> 2*pi, (2)(3) -> (2)*(3))
        // Bổ sung π và các hàm lượng giác vào regex nhân ngầm định
        processed = processed
                .replaceAll("(\\d|\\))(?=[\\u221A\\(πsct])", "$1*")
                .replaceAll("(\\))(?=\\d)", "$1*");

        // Thay thế dấu căn √ (\u221A) bằng hàm sqrt của exp4j
        processed = processed.replace("\u221A", "sqrt");

        try {
            Expression e = new ExpressionBuilder(processed).build();
            double result = e.evaluate();

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                throw new ArithmeticException("Lỗi phép tính");
            }

            return result;
        } catch (Exception e) {
            throw new Exception("Biểu thức không hợp lệ");
        }
    }

    private static String handlePercentage(String input) {
        StringBuilder sb = new StringBuilder(input);
        int index;
        while ((index = sb.indexOf("%")) != -1) {
            int numEnd = index;
            int numStart = numEnd - 1;
            while (numStart >= 0 && (Character.isDigit(sb.charAt(numStart)) || sb.charAt(numStart) == '.')) {
                numStart--;
            }
            String bStr = sb.substring(numStart + 1, numEnd);
            
            if (numStart >= 0 && (sb.charAt(numStart) == '+' || sb.charAt(numStart) == '-')) {
                char op = sb.charAt(numStart);
                String aStr = sb.substring(0, numStart);
                if (!aStr.isEmpty()) {
                    String replacement = "(" + aStr + ")" + op + "((" + aStr + ")*(" + bStr + "/100))";
                    sb.replace(0, index + 1, replacement);
                    continue; 
                }
            }
            sb.replace(numStart + 1, index + 1, "(" + bStr + "/100)");
        }
        return sb.toString();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(';
    }
}
