package com.example.studentcalculator.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.studentcalculator.database.HistoryEntity;
import com.example.studentcalculator.repository.HistoryRepository;
import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private final HistoryRepository repository;
    private final LiveData<List<HistoryEntity>> allHistory;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        repository = new HistoryRepository(application);
        allHistory = repository.getAllHistory();
    }

    public LiveData<List<HistoryEntity>> getAllHistory() {
        return allHistory;
    }

    public void delete(HistoryEntity history) {
        repository.delete(history);
    }

    public void clearAll() {
        repository.clearAll();
    }
}