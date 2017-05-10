package com.hilay.recyclernotes;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Hilay on 10-מאי-2017.
 */

public class NoteItem implements Parcelable {

    private String title;
    private String note;

    public NoteItem(String title, String note) {
        this.title = title;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.note);
    }

    protected NoteItem(Parcel in) {
        this.title = in.readString();
        this.note = in.readString();
    }

    public static final Parcelable.Creator<NoteItem> CREATOR = new Parcelable.Creator<NoteItem>() {
        @Override
        public NoteItem createFromParcel(Parcel source) {
            return new NoteItem(source);
        }

        @Override
        public NoteItem[] newArray(int size) {
            Log.d("HILAY#","NoteItem");
            return new NoteItem[size];
        }
    };
}
