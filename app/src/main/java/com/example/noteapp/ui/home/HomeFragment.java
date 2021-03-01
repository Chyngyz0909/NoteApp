package com.example.noteapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.R;
import com.example.noteapp.models.Note;
import com.example.noteapp.onItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private Boolean isUpdating;
    private int changePosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter= new NoteAdapter(getViewLifecycleOwner());
    }

    public View onCreate(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNote(null);
                isUpdating = false;
            }
        });
        getParentFragmentManager().setFragmentResultListener("rk_note", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Note note = (Note) result.getSerializable("note");
                //                      String text = result.getString("text");
 //               String desc = result.getString("text");
                //                       if (isUpdating) {
                //                           adapter.setItem(changePosition, text);
//                        } else {
                //                           adapter.addItem(text);
                adapter.addItem(note);
            }
            //
        });
        initList();
    }

    private void initList() {
        adapter = new NoteAdapter(getViewLifecycleOwner());
        recyclerView.setAdapter(adapter);
        Log.e("TAG", "initList возврат листа : ");
        adapter.setOnItemClickListener(new onItemClickListener() {
            @Override
            public void onClick(int position) {
                changePosition = position;    //  При нажатии на элемент  сохраняет нго позицию
                isUpdating = true;            //  При нажатии на элеиент меняет на тру
                Note note = adapter.getItem(position);
                openNote(note);
            }

            @Override
            public void longClick(int position) {                                      // 2 dz

                LayoutInflater inflater = LayoutInflater.from(getContext());
                View view = inflater.inflate(R.layout.dialog_layout, null);

                Button delete = view.findViewById(R.id.delete);
                Button cancel = view.findViewById(R.id.cancel);

                AlertDialog.Builder alert = new AlertDialog.Builder(getContext())
                        .setView(view);

                final AlertDialog dialog = alert.create();

                delete.setOnClickListener(v -> {
                    adapter.remove(position);
                    dialog.dismiss();
                });
                cancel.setOnClickListener(v -> dialog.dismiss());
                dialog.show();
            }
        });
    }

    private void openNote(Note note) {
       Bundle bundle = new Bundle();
//        bundle.putString("key", text);
        bundle.putSerializable("note",note);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.noteFragment);
    }

    private void openNote() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        Bundle bundle = null;
        navController.navigate(R.id.noteFragment,bundle);
    }
}