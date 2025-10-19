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
import com.example.chikidesk.databinding.FragmentConfigListBinding;
import com.example.chikidesk.ui.adapters.AdapterConfigList;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class FragmentConfigList extends Fragment {
    private FragmentConfigListBinding binding;
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
        binding = FragmentConfigListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rcvConfigList.setLayoutManager(new LinearLayoutManager(getContext()));

        AdapterConfigList.OnItemClickListener listener = maquina -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_configList_to_selectConfig, bundle);
        };
        binding.rcvConfigList.setAdapter(new AdapterConfigList(appCache.getMapToConfigList(), listener));

        binding.fabConfigListNew.setOnClickListener(v ->
            Navigation.findNavController(v).navigate(R.id.action_configList_to_selectMaquina));
        binding.fabConfigListHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_configList_to_home));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}