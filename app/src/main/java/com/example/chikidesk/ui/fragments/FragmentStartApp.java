package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;

public class FragmentStartApp extends Fragment {
    public FragmentStartApp() {
        super(R.layout.fragment_start_app);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnMoldes = view.findViewById(R.id.btnMoldes);
        Button btnMaquinas = view.findViewById(R.id.btnMaquinas);
        Button btnConfiguraciones = view.findViewById(R.id.btnConfiguraciones);

        btnMoldes.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_inicio_to_moldes);
        });

        btnMaquinas.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_inicio_to_maquinas);
        });

        btnConfiguraciones.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_inicio_to_configuraciones);
        });
    }
}