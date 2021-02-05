package com.example.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={Notes.class},version = 1,exportSchema = false)
abstract class NoteDatabase extends RoomDatabase {
    public abstract NotesDao noteDao();

    public static volatile NoteDatabase noteDatabase;

    public static NoteDatabase getRoomInstance(Context context){
        if (noteDatabase==null){
            synchronized (NoteDatabase.class){
                if (noteDatabase==null){
                    noteDatabase= Room.databaseBuilder(context,NoteDatabase.class,"notes.db").build();
                }
            }
        }
        return noteDatabase;
    }
}
