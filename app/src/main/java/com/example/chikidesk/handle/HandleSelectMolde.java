package com.example.chikidesk.handle;

import android.os.Bundle;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.SelectMoldeBinding;
import com.example.chikidesk.driver.DriverList;
import com.example.chikidesk.driver.DriverListSelect;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.adapter.AdapterMoldeList;
import com.example.chikidesk.ui.fragment.MainFragment;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HandleSelectMolde extends Handle<MainFragment, Integer> implements DriverListSelect {
    private SelectMoldeBinding binding;

    public HandleSelectMolde(MainFragment fragment) {
        super(fragment);
        this.binding = (SelectMoldeBinding) super.binding;
    }

    @Override
    public void drive() {
        setKeysByBundle();
        setAdapters();
        populateForm();
        setupNavigationButtons();
    }

    @Override
    public void setKeysByBundle() {
        id = getBundle() != null ? getBundle().getInt("id") : 0;
    }

    @Override
    public void setAdapters() {
        binding.rcvSelectMolde.setAdapter(new AdapterMoldeList(getMoldesNotConfig(), molde -> {
            Bundle bundle = new Bundle();
            bundle.putInt("maquina", id);
            bundle.putInt("molde", molde.getId());
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_selectMolde_to_configForm, bundle);
        }));
    }

    @Override
    public void populateForm() {
        binding.rcvSelectMolde.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        binding.rcvSelectMolde.setHasFixedSize(true);
    }

    @Override
    public void setupNavigationButtons() {
        binding.fabSelectMoldeHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
        binding.fabSelectMoldeBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
    }

    @Override
    public void destroyDriver() {
        this.binding = null;
    }

    @Override
    protected void initProperties() {}
    @Override
    protected void driveActionDao() {}
    @Override
    protected void setupListeners() {}

    private List<Molde> getMoldesNotConfig() {
        Set<Integer> idsMoldesConfig = appCache.configList.stream()
                .filter(c -> c.getId_maquina() == id)
                .map(Configuracion::getId_molde)
                .collect(Collectors.toSet());

        return appCache.moldeList.stream()
                .filter(molde -> !idsMoldesConfig.contains(molde.getId()))
                .collect(Collectors.toList());
    }
}
