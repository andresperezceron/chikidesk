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

import java.util.ArrayList;
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
        RecyclerView rcvMoldeList = view.findViewById(R.id.rcvMoldeList);
        rcvMoldeList.setLayoutManager(new LinearLayoutManager(getContext()));

        FloatingActionButton fabNavHome = view.findViewById(R.id.fabNavHome);
        FloatingActionButton fabNewMolde = view.findViewById(R.id.fabNewMolde);

        MoldeDao moldeDao = new MoldeDao(requireContext());
        List<Molde> listMoldes = moldeDao.getAllOderBy("nombre");
        moldeDao.close();

        AdapterMoldeLista.OnItemClickListener listener = molde -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("molde", molde);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_moldeList_to_moldeShow, bundle);
        };
        rcvMoldeList.setAdapter(new AdapterMoldeLista(listMoldes, listener));

        fabNavHome.setOnClickListener(v -> Navigation.findNavController(view).popBackStack());
        fabNewMolde.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_moldeList_to_moldeForm));

        return view;
    }
}