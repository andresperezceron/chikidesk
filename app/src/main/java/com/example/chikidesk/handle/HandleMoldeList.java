package com.example.chikidesk.handle;

import android.os.Bundle;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.MoldeListBinding;
import com.example.chikidesk.ui.adapter.AdapterMoldeList;
import com.example.chikidesk.ui.fragment.MainFragment;


public class HandleMoldeList extends Handle<MainFragment, Integer> {
    private MoldeListBinding binding;

    public HandleMoldeList(MainFragment fragment) {
        super(fragment);
        this.binding = (MoldeListBinding) super.binding;
    }

    @Override
    public void drive() {
        setAdapters();
        populateForm();
        setupNavigationButtons();
    }

    @Override
    protected void setAdapters() {
        binding.rcvMoldeList.setAdapter(new AdapterMoldeList(appCache.moldeList, molde -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", molde.getId());
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_moldeList_to_moldeShow, bundle);
        }));
    }

    @Override
    protected void populateForm() {
        binding.rcvMoldeList.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        binding.rcvMoldeList.setHasFixedSize(true);
    }

    @Override
    protected void setupNavigationButtons() {
        binding.fabMoldeListHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeListNew.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_moldeList_to_moldeForm));
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