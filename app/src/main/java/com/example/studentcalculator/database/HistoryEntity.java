package com.example.studentcalculator.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history")
public class HistoryEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String type;

    @NonNull
    private String expression;

    @NonNull
    private String result;

    private long createdAt;

    public HistoryEntity() {
    }

    public HistoryEntity(@NonNull String type,
                         @NonNull String expression,
                         @NonNull String result,
                         long createdAt) {
        this.type = type;
        this.expression = expression;
        this.result = result;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @NonNull
    public String getExpression() {
        return expression;
    }

    public void setExpression(@NonNull String expression) {
        this.expression = expression;
    }

    @NonNull
    public String getResult() {
        return result;
    }

    public void setResult(@NonNull String result) {
        this.result = result;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}