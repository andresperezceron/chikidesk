package com.example.chikidesk.handles;

import android.os.Bundle;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMoldeListBinding;
import com.example.chikidesk.ui.adapters.AdapterMoldeList;
import com.example.chikidesk.ui.fragments.FragmentMoldeList;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class HandleMoldeList extends HandleFragment<FragmentMoldeList, FragmentMoldeListBinding, Integer> {
    public HandleMoldeList(AppCacheViewModel appCache, FragmentMoldeList fragment) {
        super(appCache, fragment);
    }

    @Override
    public FragmentMoldeListBinding setBinding(FragmentMoldeListBinding binding) {
        super.binding = binding;
        return binding;
    }

    @Override
    public void setupListener() {
        AdapterMoldeList.OnItemClickListener listener = molde -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", molde.getId());
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_moldeList_to_moldeShow, bundle);
        };
        binding.rcvMoldeList.setAdapter(new AdapterMoldeList(appCache.moldeList, listener));
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
    public void destroyHandle() {
        super.onDestroyHandle();
    }

    @Override
    protected void setKeysByBundle() {}
}