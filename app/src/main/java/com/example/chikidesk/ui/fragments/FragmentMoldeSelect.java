package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chikidesk.R;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.adapters.AdapterMoldeLista;

import java.util.List;

public class FragmentMoldeSelect extends Fragment {
    public FragmentMoldeSelect() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_molde_select, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rcvMoldeSelect);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        int idMaquina = getArguments() != null ? getArguments().getInt("id_maquina") : 0;
        MoldeDao dao = new MoldeDao(getContext());
        List<Molde> moldes = dao.obtenerMoldesNoConfigurados(idMaquina);
        dao.close();

        AdapterMoldeLista.OnItemClickListener listener = molde -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id_maquina", idMaquina);
            bundle.putInt("id_molde", molde.getId());
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_moldeSelect_to_configForm, bundle);
        };

        recyclerView.setAdapter(new AdapterMoldeLista(moldes, listener));

        return view;
    }
}
