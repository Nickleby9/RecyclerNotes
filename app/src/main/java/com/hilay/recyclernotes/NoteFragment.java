package com.hilay.recyclernotes;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends DialogFragment {

    SharedPreferences prefs;
    int pageCounter = 1;
    ArrayList<NoteItem> notes = new ArrayList<NoteItem>();


    public NoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        prefs = getSharedPreferences("Notes", MODE_PRIVATE);

        final EditText etTitle = (EditText) view.findViewById(R.id.tvTitle);
        final EditText etNote = (EditText) view.findViewById(R.id.etNote);
        Button btnOK = (Button) view.findViewById(R.id.btnOk);
        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                save(etTitle.getText().toString(), etNote.getText().toString());
                pageCounter++;
                listener.onNoteCreated(etTitle.getText().toString(), etNote.getText().toString());

                dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

    }

    OnNoteCreatedListener listener;

    public interface OnNoteCreatedListener{
        void onNoteCreated(String title, String note);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNoteCreatedListener)
            listener = (OnNoteCreatedListener) context;
        else
            throw new RuntimeException(context.toString() + " Must implement " + OnNoteCreatedListener.class.getSimpleName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
