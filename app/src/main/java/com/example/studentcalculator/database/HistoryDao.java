package com.example.studentcalculator.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert
    void insert(HistoryEntity history);

    @Update
    void update(HistoryEntity history);

    @Delete
    void delete(HistoryEntity history);

    @Query("DELETE FROM history")
    void clearAll();

    @Query("SELECT * FROM history ORDER BY createdAt DESC")
    LiveData<List<HistoryEntity>> getAllHistory();

    @Query("SELECT * FROM history WHERE type = :type ORDER BY createdAt DESC")
    LiveData<List<HistoryEntity>> getHistoryByType(String type);

    @Query("SELECT * FROM history WHERE id = :id LIMIT 1")
    HistoryEntity getHistoryById(int id);
}