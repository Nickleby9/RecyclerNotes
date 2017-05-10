package com.hilay.recyclernotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteFragment.OnNoteCreatedListener {

    SharedPreferences prefs;
    TextView tvTitle;
    EditText etNote, etTitle;
    int counter = 1;
    int pageCounter = 1;
    ConstraintLayout layout;
    RecyclerView recycler;
    static ArrayList<NoteItem> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (intent.getExtras() != null){
            int position = intent.getIntExtra("position", -1);
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");
            notes.set(position,new NoteItem(title,content));
        }
        prefs = getSharedPreferences("Notes", MODE_PRIVATE);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        etNote = (EditText) findViewById(R.id.etNote);
        etTitle = (EditText) findViewById(R.id.tvTitle);

        layout = (ConstraintLayout) findViewById(R.id.layout);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        NoteAdapter adapter = new NoteAdapter(this, NoteDataSource.getNotes());
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteFragment n = new NoteFragment();
                n.show(getSupportFragmentManager(),"noteDialog");

                pageCounter = counter;
                counter++;
                pageCounter++;

            }
        });
    }

    @Override
    public void onNoteCreated(String title, String note) {
        notes.add(new NoteItem(title, note));
        NoteAdapter adapter = new NoteAdapter(this, NoteDataSource.getNotes());
        recycler.setAdapter(adapter);
    }

    public static List<NoteItem> getList(){
        return notes;
    }

}
