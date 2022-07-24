package com.example.relevelproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    TextView emptyTextView;
    RecyclerView notesRecyclerView;
    RecyclerViewAdapter adapter;
    FloatingActionButton addNoteButton;
    AppDatabase db;
    DaoInterface dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        emptyTextView = findViewById(R.id.noNotesTextView);
        addNoteButton = findViewById(R.id.addNoteFab);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mydatabase").allowMainThreadQueries().build();
        dao = db.daoInterface();

        notesRecyclerView = findViewById(R.id.notesRecylerView);
        notesRecyclerView.setHasFixedSize(true);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, InfoScreen.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Note> noteList = dao.getAllNotes();
        if(noteList.size() == 0) {
            emptyTextView.setVisibility(View.VISIBLE);
        } else {
            adapter = new RecyclerViewAdapter(noteList);
            notesRecyclerView.setAdapter(adapter);
        }
    }
}