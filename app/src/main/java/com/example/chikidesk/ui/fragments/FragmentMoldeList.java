package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.chikidesk.R;
import com.example.chikidesk.handles.HandleBinding;
import com.example.chikidesk.handles.HandleMoldeList;
import com.example.chikidesk.databinding.FragmentMoldeListBinding;
import com.example.chikidesk.ui.adapters.AdapterMoldeList;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class FragmentMoldeList extends Fragment implements HandleBinding {
    private FragmentMoldeListBinding binding;
    private HandleMoldeList handle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handle = new HandleMoldeList(
                new ViewModelProvider(requireActivity()).get(AppCacheViewModel.class), this);
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

        handle.setBinding();
        handle.populateForm();
        handle.setupNavigationButtons();

        AdapterMoldeList.OnItemClickListener listener = molde -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", molde.getId());
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_moldeList_to_moldeShow, bundle);
        };
        binding.rcvMoldeList.setAdapter(new AdapterMoldeList(handle.getList(), listener));
    }

    @Override
    public Object getBinding() {
        return this.binding;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}