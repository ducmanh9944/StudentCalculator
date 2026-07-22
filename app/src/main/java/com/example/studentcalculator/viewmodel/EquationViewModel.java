package com.example.studentcalculator.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.studentcalculator.database.HistoryEntity;
import com.example.studentcalculator.repository.HistoryRepository;
import com.example.studentcalculator.utils.EquationSolver;
import com.example.studentcalculator.utils.NumberFormatter;

public class EquationViewModel extends AndroidViewModel {
    private final HistoryRepository repository;

    public EquationViewModel(@NonNull Application application) {
        super(application);
        repository = new HistoryRepository(application);
    }

    public String solveLinear(double a, double b) {
        String result = EquationSolver.solveLinear(a, b);
        String expr = NumberFormatter.format(a) + "x + " + NumberFormatter.format(b) + " = 0";
        saveToHistory("PT Bậc 1", expr, result);
        return result;
    }

    public String solveQuadratic(double a, double b, double c) {
        String result = EquationSolver.solveQuadratic(a, b, c);
        String expr = NumberFormatter.format(a) + "x² + " + NumberFormatter.format(b) + "x + " + NumberFormatter.format(c) + " = 0";
        saveToHistory("PT Bậc 2", expr, result);
        return result;
    }

    public String solveCubic(double a, double b, double c, double d) {
        String result = EquationSolver.solveCubic(a, b, c, d);
        String expr = NumberFormatter.format(a) + "x³ + " + NumberFormatter.format(b) + "x² + " + NumberFormatter.format(c) + "x + " + NumberFormatter.format(d) + " = 0";
        saveToHistory("PT Bậc 3", expr, result);
        return result;
    }

    public String solveSystem(double a1, double b1, double c1, double a2, double b2, double c2) {
        String result = EquationSolver.solveSystem(a1, b1, c1, a2, b2, c2);
        String expr = String.format("%sx + %sy = %s\n%sx + %sy = %s",
                NumberFormatter.format(a1), NumberFormatter.format(b1), NumberFormatter.format(c1),
                NumberFormatter.format(a2), NumberFormatter.format(b2), NumberFormatter.format(c2));
        saveToHistory("Hệ PT", expr, result);
        return result;
    }

    private void saveToHistory(String type, String expr, String res) {
        HistoryEntity history = new HistoryEntity(type, expr, res, System.currentTimeMillis());
        repository.insert(history);
    }
}
