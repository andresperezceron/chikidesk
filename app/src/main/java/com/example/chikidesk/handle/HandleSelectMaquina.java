package com.example.chikidesk.handle;

import android.os.Bundle;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.SelectMaquinaBinding;
import com.example.chikidesk.ui.adapter.AdapterMaquinaList;
import com.example.chikidesk.ui.fragment.MainFragment;

public class HandleSelectMaquina extends Handle<MainFragment, Integer> {
    private SelectMaquinaBinding binding;

    public HandleSelectMaquina(MainFragment fragment) {
        super(fragment);
        this.binding = (SelectMaquinaBinding) super.binding;
    }

    @Override
    public void drive() {
        setAdapters();
        populateForm();
        setupNavigationButtons();
    }

    @Override
    protected void setAdapters() {
        binding.rcvSelectMaquina.setAdapter(new AdapterMaquinaList(appCache.maquinaList, maquina -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", maquina.getId());
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_selectMaquina_to_selectMolde, bundle);
        }));
    }

    @Override
    protected void populateForm() {
        binding.rcvSelectMaquina.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvSelectMaquina.setHasFixedSize(true);
    }

    @Override
    protected void setupNavigationButtons() {
        binding.fabSelectMaquinaHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
        binding.fabSelectMaquinaBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
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