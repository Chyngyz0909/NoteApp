package com.example.noteapp.ui.onboard;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.noteapp.Prefs;
import com.example.noteapp.R;


public class BoardFragment extends Fragment {
    private ViewPager2 viewPager;
    BoardAdapter adapter;
    Button btnskip;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnskip = view.findViewById(R.id.btnskip);

        viewPager = view.findViewById(R.id.viewPager);
        adapter = new BoardAdapter();
        viewPager.setAdapter(adapter);

        btnskip.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                         close();
                                       }
                                   }
        );
        adapter.setOnStartClickListener(new BoardAdapter.OnStartClickListener() {
            @Override
            public void onClick() {
                close();
            }
        });
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }

    private void close() {
        Prefs prefs = new Prefs(requireContext());
        prefs.saveIsShown();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigateUp();
    }
}
