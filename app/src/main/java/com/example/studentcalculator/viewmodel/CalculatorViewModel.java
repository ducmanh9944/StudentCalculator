package com.example.studentcalculator.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.studentcalculator.database.HistoryEntity;
import com.example.studentcalculator.repository.HistoryRepository;
import com.example.studentcalculator.utils.ExpressionEvaluator;
import com.example.studentcalculator.utils.NumberFormatter;

public class CalculatorViewModel extends AndroidViewModel {
    private final MutableLiveData<String> expression = new MutableLiveData<>("");
    private final MutableLiveData<String> result = new MutableLiveData<>("");
    private final HistoryRepository repository;
    private static final int MAX_LENGTH = 50;

    public CalculatorViewModel(@NonNull Application application) {
        super(application);
        repository = new HistoryRepository(application);
    }

    public LiveData<String> getExpression() { return expression; }
    public LiveData<String> getResult() { return result; }

    public void append(String str) {
        String current = expression.getValue();
        if (current == null) current = "";

        if (current.length() >= MAX_LENGTH) return;

        // Xử lý logic tránh lặp dấu toán tử
        if (isOperator(str) && !current.isEmpty()) {
            char lastChar = current.charAt(current.length() - 1);
            if (isOperator(String.valueOf(lastChar))) {
                expression.setValue(current.substring(0, current.length() - 1) + str);
                autoCalculate();
                return;
            }
        }

        expression.setValue(current + str);
        autoCalculate();
    }

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("×") || s.equals("÷") || s.equals("^");
    }

    public void clear() {
        expression.setValue("");
        result.setValue("");
    }

    public void delete() {
        String current = expression.getValue();
        if (current == null || current.isEmpty()) return;

        if (current.endsWith("sin(") || current.endsWith("cos(") || current.endsWith("tan(")) {
            expression.setValue(current.substring(0, current.length() - 4));
        } else if (current.endsWith("√(")) {
            expression.setValue(current.substring(0, current.length() - 2));
        } else {
            expression.setValue(current.substring(0, current.length() - 1));
        }
        autoCalculate();
    }

    private void autoCalculate() {
        String expr = expression.getValue();
        if (expr == null || expr.isEmpty()) {
            result.setValue("");
            return;
        }
        try {
            double val = ExpressionEvaluator.evaluate(expr);
            result.setValue(NumberFormatter.format(val));
        } catch (Exception e) {
            result.setValue("");
        }
    }

    public void onEquals() {
        String expr = expression.getValue();
        if (expr == null || expr.isEmpty()) return;

        try {
            double val = ExpressionEvaluator.evaluate(expr);
            String formattedResult = NumberFormatter.format(val);
            
            HistoryEntity history = new HistoryEntity(
                "Calculator",
                expr,
                formattedResult,
                System.currentTimeMillis()
            );
            repository.insert(history);
            
            expression.setValue(formattedResult);
            result.setValue("");
        } catch (Exception e) {
            result.setValue("Định dạng không hợp lệ");
        }
    }
    
    public void toggleSign() {
        String current = expression.getValue();
        if (current == null || current.isEmpty()) return;
        
        if (current.startsWith("-")) {
            expression.setValue(current.substring(1));
        } else {
            expression.setValue("-" + current);
        }
        autoCalculate();
    }

    public void appendSquare() { append("^2"); }
    public void appendPower() { append("^"); }
    public void appendSqrt() { append("√("); }
    public void appendPi() { append("π"); }
    public void appendSin() { append("sin("); }
    public void appendCos() { append("cos("); }
    public void appendTan() { append("tan("); }
}
