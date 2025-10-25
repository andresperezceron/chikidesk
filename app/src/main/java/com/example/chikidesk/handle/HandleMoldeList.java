package com.example.chikidesk.handle;

import android.os.Bundle;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.MoldeListBinding;
import com.example.chikidesk.driver.DriverList;
import com.example.chikidesk.ui.adapter.AdapterMoldeList;
import com.example.chikidesk.ui.fragment.FragmentMoldeList;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class HandleMoldeList
        extends BaseHandle<FragmentMoldeList, MoldeListBinding, Integer>
        implements DriverList<MoldeListBinding> {
    public HandleMoldeList(AppCacheViewModel appCache, FragmentMoldeList fragment) {
        super(appCache, fragment);
    }

    @Override
    public void drive() {
        setAdapters();
        populateForm();
        setupNavigationButtons();
    }

    @Override
    public void setAdapters() {
        binding.rcvMoldeList.setAdapter(new AdapterMoldeList(appCache.moldeList, molde -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", molde.getId());
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_moldeList_to_moldeShow, bundle);
        }));

    }

    @Override
    public void populateForm() {
        binding.rcvMoldeList.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        binding.rcvMoldeList.setHasFixedSize(true);
    }

    @Override
    public void setupNavigationButtons() {
        binding.fabMoldeListHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeListNew.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_moldeList_to_moldeForm));
    }

    @Override
    public void destroyDriver() {
        super.onDestroyDriver();
    }

    @Override
    protected void setKeysByBundle() {}
    @Override
    protected void initProperties() {}
    @Override
    protected void setupListeners() {}
    @Override
    protected void driveActionDao() {}
}