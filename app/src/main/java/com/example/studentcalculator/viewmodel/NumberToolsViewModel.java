package com.example.studentcalculator.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.studentcalculator.database.HistoryEntity;
import com.example.studentcalculator.repository.HistoryRepository;
import com.example.studentcalculator.utils.MathUtils;

public class NumberToolsViewModel extends AndroidViewModel {
    private final HistoryRepository repository;

    public NumberToolsViewModel(@NonNull Application application) {
        super(application);
        repository = new HistoryRepository(application);
    }

    public String calculateGcdLcm(long n1, long n2) {
        long gcd = MathUtils.gcd(n1, n2);
        long lcm = MathUtils.lcm(n1, n2);
        
        String result = "ƯCLN: " + gcd + "\nBCNN: " + lcm;
        saveToHistory("Số học", "n1 = " + n1 + ", n2 = " + n2, result);
        return result;
    }

    public String getPrimeFactorization(long n1, long n2) {
        String p1 = MathUtils.primeFactorization(n1);
        String p2 = MathUtils.primeFactorization(n2);
        
        String result = "n1 = " + p1 + "\nn2 = " + p2;
        return result;
    }

    private void saveToHistory(String type, String expr, String res) {
        HistoryEntity history = new HistoryEntity(type, expr, res, System.currentTimeMillis());
        repository.insert(history);
    }
}