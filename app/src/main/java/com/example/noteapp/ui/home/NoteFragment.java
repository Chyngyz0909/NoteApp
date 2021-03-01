package com.example.noteapp.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.noteapp.App;
import com.example.noteapp.R;
import com.example.noteapp.models.Note;

public class NoteFragment extends Fragment {

    private Note note;
    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null)
            note = (Note) getArguments().getSerializable("note");
        editText = view.findViewById(R.id.editText);
        if (note != null) editText.setText(note.getTitle());

        // получение bundle из home fragment
        String text = requireArguments().getString("key");
        Button btnSave = view.findViewById(R.id.btnSave);

        editText.setText(text);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void save() {

        String text = editText.getText().toString();
        if (note == null) {
            note = new Note(text);
            note.setCreatedAt(System.currentTimeMillis());
            App.getDatabase().noteDao().insert(note);
        } else {
            note.setTitle(text);
            App.getDatabase().noteDao().update(note);
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("note", note);

        // bundle передает инфу между фрагментами,при помощи ключа "text"
        //       bundle.putString("text", text);
        getParentFragmentManager().setFragmentResult("rk_note", bundle);  //  отправляет bundle
        close();
    }


    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigateUp();
    }
}


