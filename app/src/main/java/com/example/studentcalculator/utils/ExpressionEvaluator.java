package com.example.studentcalculator.utils;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class ExpressionEvaluator {
    public static double evaluate(String expression) throws Exception {
        if (expression == null || expression.trim().isEmpty()) {
            return 0;
        }

        // 1. Thay thế các ký hiệu hiển thị cơ bản
        String processed = expression
                .replace('×', '*')
                .replace('÷', '/')
                .replace(',', '.');

        // 2. Xử lý phần trăm (%) thông minh (Calculator Style: A + B% -> A + (A * B / 100))
        processed = handlePercentage(processed);

        // 3. Xử lý nhân ngầm định (Implicit Multiplication)
        // Chèn * giữa (số hoặc ngoặc đóng) và (dấu căn hoặc ngoặc mở)
        // \u221A là √
        processed = processed
                .replaceAll("(\\d|\\))(?=[\\u221A\\(])", "$1*")
                .replaceAll("(\\))(?=\\d)", "$1*");

        // 4. Thay thế dấu căn √ (\u221A) bằng hàm sqrt của exp4j
        processed = processed.replace("\u221A", "sqrt").replace("√", "sqrt");

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

    /**
     * Logic xử lý phần trăm thông minh:
     * - Nếu là A + B% hoặc A - B%, nó sẽ được chuyển thành A + (A * B / 100).
     * - Các trường hợp khác (như A * B% hoặc đứng một mình) vẫn là B / 100.
     */
    private static String handlePercentage(String expression) {
        StringBuilder sb = new StringBuilder(expression);
        int i = 0;
        while ((i = sb.indexOf("%", i)) != -1) {
            int bEnd = i;
            int bStart = bEnd - 1;
            // Tìm số B đứng trước dấu %
            while (bStart >= 0 && (Character.isDigit(sb.charAt(bStart)) || sb.charAt(bStart) == '.')) {
                bStart--;
            }

            if (bStart < bEnd - 1) {
                String bStr = sb.substring(bStart + 1, bEnd);
                
                // Kiểm tra xem có toán tử + hoặc - ngay trước số B không
                if (bStart >= 0 && (sb.charAt(bStart) == '+' || sb.charAt(bStart) == '-')) {
                    char op = sb.charAt(bStart);
                    
                    // Tìm điểm bắt đầu của biểu thức A (trong cùng một cấp độ ngoặc)
                    int aStartCandidate = bStart - 1;
                    int balance = 0;
                    while (aStartCandidate >= 0) {
                        char c = sb.charAt(aStartCandidate);
                        if (c == ')') balance++;
                        else if (c == '(') {
                            if (balance == 0) break;
                            balance--;
                        }
                        aStartCandidate--;
                    }
                    int aStart = aStartCandidate + 1;
                    
                    String aStr = sb.substring(aStart, bStart).trim();
                    
                    // A không được rỗng và không kết thúc bằng một toán tử khác (ví dụ: 5 * -10%)
                    if (!aStr.isEmpty() && !isOperator(aStr.charAt(aStr.length() - 1))) {
                        // Thay thế "A op B%" thành "((A) op ((A)*(B/100)))"
                        String replacement = "((" + aStr + ")" + op + "((" + aStr + ")*(" + bStr + "/100)))";
                        sb.replace(aStart, i + 1, replacement);
                        i = aStart + replacement.length();
                        continue;
                    }
                }
                
                // Trường hợp mặc định cho nhân/chia hoặc đứng một mình: B% -> (B/100)
                String replacement = "(" + bStr + "/100)";
                sb.replace(bStart + 1, i + 1, replacement);
                i = bStart + replacement.length();
            } else {
                i++;
            }
        }
        return sb.toString();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(';
    }
}
