package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
private EditText ed_name;
private Button bt_add;
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_name=findViewById(R.id.ed_name);
        bt_add=findViewById(R.id.bt_add);

        recyclerView=findViewById(R.id.rv_list);
        noteAdapter=new NoteAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteAdapter);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_name.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                }else{
                    String note_id= UUID.randomUUID().toString();
                    Notes notes=new Notes(note_id,ed_name.getText().toString());
                    NoteDatabase noteDatabase=NoteDatabase.getRoomInstance(getApplicationContext());
                    NotesDao notesDao=noteDatabase.noteDao();
                    new NotesAsyncTask(notesDao).execute(notes);
                }
            }
        });
    }
    private class NotesAsyncTask extends AsyncTask<Notes,Void,Void>{
        private NotesDao notesDao;

        public NotesAsyncTask(NotesDao notesDao){
            this.notesDao=notesDao;
        }
        @Override
        protected Void doInBackground(Notes... notes) {
            notesDao.insertNotes(notes[0]);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, "Data inserted successfull", Toast.LENGTH_SHORT).show();
            new DisplayDataAsyncTask(notesDao).execute();


        }
    }
    private class DisplayDataAsyncTask extends AsyncTask<Void,Void,Void>{
        private NotesDao notesDao;
        private Notes[] notesList;

        public DisplayDataAsyncTask(NotesDao notesDao){
            this.notesDao=notesDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            notesList=notesDao.getAllNotes();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            noteAdapter.setUpdatedDataList(notesList);
        }
    }
}