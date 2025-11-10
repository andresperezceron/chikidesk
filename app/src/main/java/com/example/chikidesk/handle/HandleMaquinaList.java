package com.example.chikidesk.handle;

import android.os.Bundle;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.MaquinaListBinding;
import com.example.chikidesk.driver.DriverList;
import com.example.chikidesk.ui.adapter.AdapterMaquinaList;
import com.example.chikidesk.ui.fragment.MainFragment;

public class HandleMaquinaList extends Handle<MainFragment, Integer> implements DriverList {
    private MaquinaListBinding binding;

    public HandleMaquinaList(MainFragment fragment) {
        super(fragment);
        this.binding = (MaquinaListBinding) super.binding;
    }

    @Override
    public void drive() {
        setAdapters();
        populateForm();
        setupNavigationButtons();
    }

    @Override
    public void setAdapters() {
        binding.rcvMaquinaList.setAdapter(new AdapterMaquinaList(appCache.maquinaList, m -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", m.getId());
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_maquinaList_to_maquinaShow, bundle);
        }));
    }

    @Override
    public void populateForm() {
        binding.rcvMaquinaList.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        binding.rcvMaquinaList.setHasFixedSize(true);
    }

    @Override
    public void setupNavigationButtons() {
        binding.fabMaquinaListHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMaquinaListNew.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_maquinaList_to_maquinaForm));
    }

    @Override
    public void destroyDriver() {
        this.binding = null;
    }

    @Override
    protected void setKeysByBundle() {}
    @Override
    protected void initProperties() {}
    @Override
    protected void driveActionDao() {}
    @Override
    protected void setupListeners() {}
}