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
import com.example.chikidesk.databinding.FragmentMaquinaListBinding;
import com.example.chikidesk.ui.adapters.AdapterMaquinaList;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class FragmentMaquinaList extends Fragment {
    private FragmentMaquinaListBinding binding;
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
        binding = FragmentMaquinaListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rcvMaquinaList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvMaquinaList.setHasFixedSize(true);

        AdapterMaquinaList.OnItemClickListener listener = maquina -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", maquina.getId());
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_maquinaList_to_maquinaShow, bundle);
        };
        binding.rcvMaquinaList.setAdapter(new AdapterMaquinaList(appCache.getMaquinaList(), listener));

        binding.fabMaquinaListHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMaquinaListNew.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_maquinaList_to_maquinaForm));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}