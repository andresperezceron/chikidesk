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
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.adapters.AdapterMoldeLista;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FragmentMoldeList extends Fragment {
    public FragmentMoldeList() {
        // Constructor público vacío requerido
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_molde_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMolde);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MoldeDao moldeDao = new MoldeDao(requireContext());
        List<Molde> listaMoldes = moldeDao.obtenerTodos();
        moldeDao.close();

        AdapterMoldeLista.OnItemClickListener listener = molde -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("molde", molde);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_moldeLista_to_moldeDetalle, bundle);
        };
        recyclerView.setAdapter(new AdapterMoldeLista(listaMoldes, listener));

        FloatingActionButton fabHome = view.findViewById(R.id.fabBack);
        fabHome.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });

        FloatingActionButton fabAgregar = view.findViewById(R.id.fabNewMolde);
        fabAgregar.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_moldeLista_to_moldeFormulario);
        });

        return view;
    }
}