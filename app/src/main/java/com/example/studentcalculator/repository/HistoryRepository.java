package com.example.studentcalculator.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.studentcalculator.database.AppDatabase;
import com.example.studentcalculator.database.HistoryDao;
import com.example.studentcalculator.database.HistoryEntity;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HistoryRepository {
    private final HistoryDao historyDao;
    private final LiveData<List<HistoryEntity>> allHistory;
    private final ExecutorService executorService;

    public HistoryRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        historyDao = db.historyDao();
        allHistory = historyDao.getAllHistory();
        // Sử dụng SingleThreadExecutor để đảm bảo thứ tự ghi dữ liệu
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(HistoryEntity history) {
        executorService.execute(() -> historyDao.insert(history));
    }

    public void delete(HistoryEntity history) {
        executorService.execute(() -> historyDao.delete(history));
    }

    public void clearAll() {
        executorService.execute(historyDao::clearAll);
    }

    public LiveData<List<HistoryEntity>> getAllHistory() {
        return allHistory;
    }

    public LiveData<List<HistoryEntity>> getHistoryByType(String type) {
        return historyDao.getHistoryByType(type);
    }
}