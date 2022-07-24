package com.example.relevelproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "note")
public class Note {
    @ColumnInfo(name = "Title")
    public String title;

    @ColumnInfo(name = "Description")
    public String description;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
