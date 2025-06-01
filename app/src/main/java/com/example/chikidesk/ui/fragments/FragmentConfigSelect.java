package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chikidesk.R;
import com.example.chikidesk.db.ConfigDao;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.ui.adapters.AdapterConfigSelect;

import java.util.Map;

public class FragmentConfigSelect extends Fragment {
    public FragmentConfigSelect() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config_select, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rcvConfigSelect);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        int idMaquina = getArguments() != null ? getArguments().getInt("id_maquina") : 0;
        ConfigDao dao = new ConfigDao(getContext());
        Map<Configuracion, String> mapList = dao.obtenerListaConfigSelect(idMaquina);
        dao.close();

        AdapterConfigSelect.OnItemClickListener listener = configuracion -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("configuracion", configuracion);

            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_configSelect_to_configDetalle, bundle);
        };

        recyclerView.setAdapter(new AdapterConfigSelect(mapList, listener));

        return view;
    }
}
