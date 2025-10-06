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

        Button btnMoldes = view.findViewById(R.id.btnStarAppMoldes);
        Button btnMaquinas = view.findViewById(R.id.btnStarAppMaquinas);
        Button btnConfig = view.findViewById(R.id.btnStarAppConfig);

        btnMoldes.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_startApp_to_moldeList));

        btnMaquinas.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_startApp_to_maquinaList));

        btnConfig.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_startApp_to_configList));
    }
}