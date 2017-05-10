package com.hilay.recyclernotes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Hilay on 10-מאי-2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{
    private LayoutInflater inflater;
    private Context context;
    private List<NoteItem> data;

    public NoteAdapter(Context context, List<NoteItem> data){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.note_item, parent,false);
        Log.d("HILAY#","NoteAdapter");
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        NoteItem note = data.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvNote.setText(note.getNote());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvNote;

        public NoteViewHolder(final View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvNote = (TextView) itemView.findViewById(R.id.tvNote);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    NoteItem note = data.get(position);

                    Intent intenet = new Intent(context, NoteDetails.class);
                    intenet.putExtra("title",note.getTitle());
                    intenet.putExtra("content",note.getNote());
                    intenet.putExtra("position",position);
                    context.startActivity(intenet);
                }
            });
        }

    }


}
