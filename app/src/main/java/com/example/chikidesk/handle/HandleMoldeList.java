package com.example.chikidesk.handle;

import android.os.Bundle;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMoldeListBinding;
import com.example.chikidesk.ui.adapter.AdapterMoldeList;
import com.example.chikidesk.ui.fragment.FragmentMoldeList;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class HandleMoldeList extends BaseHandle<FragmentMoldeList, FragmentMoldeListBinding, Integer>
        implements AdapterDriver<AdapterMoldeList, AdapterMoldeList.OnItemClickListener> {
    public HandleMoldeList(AppCacheViewModel appCache, FragmentMoldeList fragment) {
        super(appCache, fragment);
    }

    @Override
    public void drive() {
        populateForm();
        driveActionDao();
        setupNavigationButtons();
    }

    @Override
    protected void populateForm() {
        binding.rcvMoldeList.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        binding.rcvMoldeList.setHasFixedSize(true);
    }

    @Override
    protected void driveActionDao() {
        binding.rcvMoldeList.setAdapter(new AdapterMoldeList(appCache.moldeList, molde -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", molde.getId());
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_moldeList_to_moldeShow, bundle);
        }));
    }

    @Override
    protected void setupNavigationButtons() {
        binding.fabMoldeListHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeListNew.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_moldeList_to_moldeForm));
    }

    @Override
    public void destroyHandle() {
        super.onDestroyHandle();
    }

    @Override
    protected void setKeysByBundle() {}
    @Override
    protected void initProperties() {}
    @Override
    protected void setupListeners() {}

    @Override
    public void getAdapter(AdapterMoldeList adapter) {
        
    }

    @Override
    public AdapterMoldeList.OnItemClickListener getAdapterListener() {
        return null;
    }
}