package com.example.chikidesk.handles;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMoldeListBinding;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.fragments.FragmentMoldeList;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.util.List;

public class HandleMoldeList implements HandleFragment {
    private final AppCacheViewModel appCache;
    private FragmentMoldeListBinding binding;
    private final FragmentMoldeList fragment;

    public HandleMoldeList(AppCacheViewModel appCache, FragmentMoldeList fragment) {
        this.appCache = appCache;
        this.fragment = fragment;
    }

    public List<Molde> getList() {
        return appCache.moldeList;
    }

    @Override
    public void setBinding() {
        binding = (FragmentMoldeListBinding) fragment.getBinding();
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
}