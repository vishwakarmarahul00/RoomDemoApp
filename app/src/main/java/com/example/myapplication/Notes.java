package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "notes")
public class Notes {
    @PrimaryKey
    @NotNull
    private String id;

    @NotNull
    @ColumnInfo(name="note")
    private String name;

    public Notes(String id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }
}
