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
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.ui.adapters.AdapterMaquinaLista;

import java.util.ArrayList;
import java.util.List;

public class FragmentMaquinaSelect extends Fragment {
    public FragmentMaquinaSelect() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maquina_select, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.revConfigSelectMaquina);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MaquinaDao maquinaDao = new MaquinaDao(requireContext());
        List<Maquina> maquinas = new ArrayList<>(maquinaDao.getAll());
        maquinaDao.close();

        AdapterMaquinaLista.OnItemClickListener listener = maquina -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id_maquina", maquina.getId());
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_maquinaSelect_to_moldeSelect, bundle);
        };

        recyclerView.setAdapter(new AdapterMaquinaLista(maquinas, listener));

        return view;
    }
}
