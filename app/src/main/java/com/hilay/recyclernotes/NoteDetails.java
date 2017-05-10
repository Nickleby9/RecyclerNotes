package com.hilay.recyclernotes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NoteDetails extends AppCompatActivity {

    EditText etNoteTitle, etNoteContent;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etNoteTitle = (EditText) findViewById(R.id.etNoteTitle);
        etNoteContent = (EditText) findViewById(R.id.etNoteContent);

        final Intent intent = getIntent();
        if (intent.getExtras() != null) {
            etNoteTitle.setText(intent.getStringExtra("title"));
            etNoteContent.setText(intent.getStringExtra("content"));
            position = intent.getIntExtra("position", -1);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(NoteDetails.this,MainActivity.class);
                intent1.putExtra("position",position);
                intent1.putExtra("title",etNoteTitle.getText().toString());
                intent1.putExtra("content",etNoteContent.getText().toString());
                startActivity(intent1);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
