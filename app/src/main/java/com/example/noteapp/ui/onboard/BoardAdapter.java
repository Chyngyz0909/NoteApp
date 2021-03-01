package com.example.noteapp.ui.onboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.R;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private int[] image = new int[]{R.drawable.ic_fast,R.drawable.ic_free_delivery,R.drawable.ic_empowerment};
    private String[] description = new String[]{"Быстрый", "Свободный", "Сильный"};
    private String[] titles = new String[]{"Fast", "Free", "Powerfull"};



    public interface OnStartClickListener {
        void onClick();
    }

    private OnStartClickListener onStartClickListener;

    public BoardAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pager_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public void setOnStartClickListener(OnStartClickListener onStartClickListener) {
        this.onStartClickListener = onStartClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private Button btnStart;
        private ImageView imageView;
        private  TextView textdesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textdesc = itemView.findViewById(R.id.textDesc);
            textTitle = itemView.findViewById(R.id.textTitle);
            btnStart = itemView.findViewById(R.id.btnStart);
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStartClickListener.onClick();
                }
            });
        }

        public void bind(int position) {
            imageView.setImageResource(image[position]);
            textdesc.setText(description[position]);
            textTitle.setText(titles[position]);
            if (position == 2 ){
                btnStart.setVisibility(View.VISIBLE);
            }
        }
    }
}
