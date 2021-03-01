package com.example.noteapp.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.App;
import com.example.noteapp.R;
import com.example.noteapp.models.Note;
import com.example.noteapp.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private ArrayList<Note> list;
//    private ArrayList<String> list;
    private onItemClickListener onItemClickListener;
    private Object Note;

    public NoteAdapter(LifecycleOwner viewLifecycleOwner) {
        list = new ArrayList<>();
        LifecycleOwner owner;
        list.addAll(App.getDatabase().noteDao().getAll());
//                App.getDatabase().noteDao().getAllLive().observe(owner, new Observer<List<Note>>() {
//            @Override
//            public void onChanged(List<Note> notes) {
//                list.clear();
//              list.addAll(notes);
            }

 //       list.addAll(App.getDatabase().noteDao().getAll());
 //       list.add("Нурумбет");
   //     list.add("Айдар");
 //       list.add("Айдай");
 //       list.add(" Алина");
 //       list.add("Исламбек");
    }

    public void setOnItemClickListener(com.example.noteapp.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_note, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

 //   public void addItem(String text) {
//       list.add(0, text);
    public void addItem(Note note){
        list.add (0,note);
        notifyItemInserted(0);
//        list.add(list.size() - 1, text);    В список добавляет с конца.
//        notifyItemInserted(list.size() - 1);

    }

    public Note getItem(int position) {

        return list.get(position);
    }

    public void remove(int position) {
        notifyItemRemoved(position);
        list.remove(position);
    }
    
    public void setItem(int position, String note) {     //меняет элемент по позиции
        Note  = list.set(position,  note);
        notifyItemInserted(position);
    }



public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.textTitle);
            textTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(getAdapterPosition());
                }
            });
            textTitle.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.longClick(getAdapterPosition());
                    return true;
                }
            });
        }

        public  void bind(Note note) {
            textTitle.setText(note.getTitle());
        }
 //       public void bind(String s) {
  //          textTitle.setText(s);
  //      }
    }
}
