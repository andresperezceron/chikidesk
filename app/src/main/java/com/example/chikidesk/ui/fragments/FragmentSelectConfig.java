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
import com.example.chikidesk.db.ConfigDao;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.ui.adapters.AdapterConfigSelect;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class FragmentSelectConfig extends Fragment {
    public FragmentSelectConfig() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_config, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rcvSelectConfig);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Maquina maquina = getArguments() != null ? getArguments().getParcelable("maquina") : null;
        assert maquina != null;

        ConfigDao dao = new ConfigDao(getContext());
        Map<Configuracion, String> mapList = dao.getListToNewConfig(maquina);

        FloatingActionButton fabHome = view.findViewById(R.id.fabSelectConfigHome);
        FloatingActionButton fabBack = view.findViewById(R.id.fabSelectConfigBack);

        AdapterConfigSelect.OnItemClickListener listener = configuracion -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("configuracion", configuracion);
            NavHostFragment.findNavController(this)
            .navigate(R.id.action_selectConfig_to_configShow, bundle);
        };

        recyclerView.setAdapter(new AdapterConfigSelect(mapList, listener));

        fabHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_selectConfig_to_home));
        fabBack.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());

        return view;
    }
}
