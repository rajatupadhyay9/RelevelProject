package com.example.relevelproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoScreen extends AppCompatActivity {
    TextView titleText, descriptionText;
    Button saveButton;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);

        titleText = findViewById(R.id.newNoteTitleText);
        descriptionText = findViewById(R.id.newNoteDescText);
        saveButton = findViewById(R.id.newNoteSaveButton);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mydatabase").allowMainThreadQueries().build();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleText.getText().toString();
                String description = descriptionText.getText().toString();

                DaoInterface daoInterface = db.daoInterface();
                daoInterface.insertNote(new Note(title, description));

                finish();
            }
        });
    }
}