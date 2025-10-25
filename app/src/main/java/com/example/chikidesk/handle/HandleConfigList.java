package com.example.chikidesk.handle;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentConfigListBinding;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.ui.adapter.AdapterConfigList;
import com.example.chikidesk.ui.fragment.FragmentConfigList;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HandleConfigList extends BaseHandle<FragmentConfigList, FragmentConfigListBinding, Integer> {
    public HandleConfigList(@NonNull AppCacheViewModel appCache, @NonNull FragmentConfigList fragment) {
        super(appCache, fragment);
    }

    @Override
    public void drive() {

    }

    @Override
    protected void driveActionDao() {}

    @Override
    protected void setAdapters() {

    }

    @Override
    public void initProperties() {}

    @Override
    public void setupListeners() {
        AdapterConfigList.OnItemClickListener listener = maquina -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_configList_to_selectConfig, bundle);
        };
        binding.rcvConfigList.setAdapter(new AdapterConfigList(getListMapped(), listener));

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

    public void destroyHandle() {
        super.onDestroyDriver();
    }

    @Override
    protected void setKeysByBundle() {}

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