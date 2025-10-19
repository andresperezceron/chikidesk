package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMoldeListBinding;
import com.example.chikidesk.ui.adapters.AdapterMoldeList;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class FragmentMoldeList extends Fragment {
    private FragmentMoldeListBinding binding;
    private AppCacheViewModel appCache;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appCache = new ViewModelProvider(requireActivity()).get(AppCacheViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMoldeListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        populateForm();
        setupNavigationButtons();

        AdapterMoldeList.OnItemClickListener listener = molde -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", molde.getId());
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_moldeList_to_moldeShow, bundle);
        };
        binding.rcvMoldeList.setAdapter(new AdapterMoldeList(appCache.getMoldeList(), listener));
    }

    private void populateForm() {
        binding.rcvMoldeList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvMoldeList.setHasFixedSize(true);
    }

    private void setupNavigationButtons() {
        binding.fabMoldeListHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeListNew.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_moldeList_to_moldeForm));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}