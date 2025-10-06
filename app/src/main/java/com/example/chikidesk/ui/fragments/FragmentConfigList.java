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
import com.example.chikidesk.ui.adapters.AdapterConfigList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class FragmentConfigList extends Fragment {
    public FragmentConfigList() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rcvConfigList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FloatingActionButton fabNew = view.findViewById(R.id.fabConfigListNew);
        FloatingActionButton fabHome = view.findViewById(R.id.fabConfigListHome);

        MaquinaDao dao = new MaquinaDao(getContext());
        Map<Maquina, Integer> mapConfig = dao.getConfigList();
        dao.close();

        AdapterConfigList.OnItemClickListener listener = maquina -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_configList_to_selectConfig, bundle);
        };

        recyclerView.setAdapter(new AdapterConfigList(mapConfig, listener));

        fabNew.setOnClickListener(v ->
            Navigation.findNavController(v).navigate(R.id.action_configList_to_selectMaquina));
        fabHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_configList_to_home));

        return view;
    }
}