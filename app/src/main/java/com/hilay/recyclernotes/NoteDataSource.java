package com.hilay.recyclernotes;


import android.os.Parcel;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilay on 10-מאי-2017.
 */

public class NoteDataSource {

    int counter;
    String title, note;


    public static List<NoteItem> getNotes() {
        List <NoteItem> notes = MainActivity.getList();
        Log.d("##HILAY##",notes.toString());

        return notes;
    }
}
