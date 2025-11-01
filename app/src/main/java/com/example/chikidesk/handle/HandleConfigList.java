package com.example.chikidesk.handle;

import android.os.Bundle;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.ConfigListBinding;
import com.example.chikidesk.driver.DriverList;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.ui.adapter.AdapterConfigList;
import com.example.chikidesk.ui.fragment.BaseFragment;
import com.example.chikidesk.ui.fragment.FragmentConfigList;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HandleConfigList extends BaseHandle<BaseFragment, Integer> implements DriverList {
    private ConfigListBinding binding;
    public HandleConfigList(BaseFragment fragment) {
        super(fragment);
        binding = (ConfigListBinding) super.binding;
    }

    @Override
    public void drive() {
        setAdapters();
        populateForm();
        setupNavigationButtons();
    }

    @Override
    public void setAdapters() {
        binding.rcvConfigList.setAdapter(new AdapterConfigList(getListMapped(), maquina -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_configList_to_selectConfig, bundle);
        }));
    }

    @Override
    public void populateForm() {
        binding.rcvConfigList.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        binding.rcvConfigList.setHasFixedSize(true);
    }

    @Override
    public void setupNavigationButtons() {
        binding.fabConfigListNew.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_configList_to_selectMaquina));
        binding.fabConfigListHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_configList_to_home));
    }

    @Override
    public void destroyDriver() {
        binding = null;
    }

    @Override
    protected void setKeysByBundle() {}
    @Override
    protected void initProperties() {}
    @Override
    protected void driveActionDao() {}
    @Override
    protected void setupListeners() {}

    private Map<Maquina, Long> getListMapped() {
        return appCache.maquinaList.stream()
                .sorted(Comparator.comparing(Maquina::getNombre, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors
                        .toMap(Function.identity(),
                                maquina -> appCache.configList.stream()
                                        .filter(config ->
                                                config.getId_maquina() == maquina.getId())
                                        .count(),
                                (oldValue, newValue) -> oldValue,
                                LinkedHashMap::new
                        ));
    }
}