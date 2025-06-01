package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chikidesk.R;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.ui.adapters.AdapterConfigLista;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

public class FragmentConfigLista extends Fragment {
    public FragmentConfigLista() {}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config_lista, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rcwConfiguracionLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MaquinaDao dao = new MaquinaDao(getContext());
        Map<Maquina, Integer> mapConfiguraciones = dao.obtenerTotalesConfiguraciones();
        dao.close();

        AdapterConfigLista.OnItemClickListener listener = maquina -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id_maquina", maquina.getId());
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_configLista_to_configSelect, bundle);
        };

        recyclerView.setAdapter(new AdapterConfigLista(mapConfiguraciones, listener));

        FloatingActionButton fabAgregarConfiguracion = view.findViewById(R.id.fabAgregarConfiguracion);
        fabAgregarConfiguracion.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_configLista_to_maquinaSelect);
        });

        return view;
    }
}