package com.example.chikidesk.handle;

import android.os.Bundle;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.SelectConfigBinding;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.adapter.AdapterMoldeList;
import com.example.chikidesk.ui.fragment.MainFragment;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HandleSelectConfig extends Handle<MainFragment, Integer> {
    private SelectConfigBinding binding;
    private List<Molde> moldesWithConfig;
    private List<Configuracion> configsForMachine;

    public HandleSelectConfig(MainFragment fragment) {
        super(fragment);
        this.binding = (SelectConfigBinding) super.binding;
    }

    @Override
    public void drive() {
        setKeysByBundle();
        initProperties();
        setAdapters();
        populateForm();
        setupNavigationButtons();
    }

    @Override
    protected void setKeysByBundle() {
        // 'id' from Handle class will store the id_maquina
        id = getBundle() != null ? getBundle().getInt("id") : 0;
        assert id != 0;
    }

    @Override
    protected void initProperties() {
        // Filter configurations for the selected machine
        configsForMachine = appCache.configList.stream()
                .filter(config -> config.getId_maquina() == id)
                .collect(Collectors.toList());

        // Get molde IDs from the filtered configurations
        List<Integer> moldeIds = configsForMachine.stream()
                .map(Configuracion::getId_molde)
                .collect(Collectors.toList());

        // Filter moldes that have a configuration with the selected machine
        moldesWithConfig = appCache.moldeList.stream()
                .filter(molde -> moldeIds.contains(molde.getId()))
                .sorted(Comparator.comparing(Molde::getNombre, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    @Override
    protected void setAdapters() {
        AdapterMoldeList adapter = new AdapterMoldeList(moldesWithConfig, molde -> {
            // Find the configuration that corresponds to the selected machine and molde
            Configuracion selectedConfig = configsForMachine.stream()
                    .filter(config -> config.getId_molde() == molde.getId())
                    .findFirst()
                    .orElse(null);

            if(selectedConfig != null) {
                // Navigate to ConfigShow, passing the configuration ID
                Bundle bundle = new Bundle();
                bundle.putInt("id", selectedConfig.getId());
                Navigation.findNavController(getView()).navigate(R.id.action_selectConfig_to_configShow, bundle);
            }
        });
        binding.rcvSelectConfig.setAdapter(adapter);
    }

    @Override
    protected void populateForm() {
        binding.rcvSelectConfig.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        binding.rcvSelectConfig.setHasFixedSize(true);
    }

    @Override
    protected void setupNavigationButtons() {
        binding.fabSelectConfigBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabSelectConfigHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    @Override
    public void destroyDriver() {
        this.binding = null;
        moldesWithConfig = null;
        configsForMachine = null;
    }

    @Override
    protected void driveActionDao() {}
    @Override
    protected void setupListeners() {}
}