package com.hilay.recyclernotes;

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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("@@@", "onSaveInstanceState");
        outState.putInt("counter", counter);
        outState.putString("current", tvTitle.getText().toString());
        outState.putInt("pageCounter", pageCounter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("Notes", MODE_PRIVATE);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        etNote = (EditText) findViewById(R.id.etNote);
        etTitle = (EditText) findViewById(R.id.tvTitle);

        layout = (ConstraintLayout) findViewById(R.id.layout);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        NoteAdapter adapter = new NoteAdapter(this, NoteDataSource.getNotes());
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);


        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("counter");
//            tvTitle.setText(savedInstanceState.getString("current"));
//            etNote.setText(load(savedInstanceState.getString("current")));
            pageCounter = savedInstanceState.getInt("pageCounter");
        }

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



    private String loadTitle(String id) {
        return prefs.getString(id + "title", "");
    }

    private String loadNote(String id) {
        return prefs.getString(id = "note", "");
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
