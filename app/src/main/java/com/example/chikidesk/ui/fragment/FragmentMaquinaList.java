package com.example.chikidesk.ui.fragment;

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
import com.example.chikidesk.handle.HandleMaquinaList;
import com.example.chikidesk.databinding.FragmentMaquinaListBinding;
import com.example.chikidesk.ui.adapter.AdapterMaquinaList;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class FragmentMaquinaList extends Fragment {
    private FragmentMaquinaListBinding binding;
    private HandleMaquinaList handle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handle = new HandleMaquinaList(
                new ViewModelProvider(requireActivity()).get(AppCacheViewModel.class),
                requireContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMaquinaListBinding.inflate(inflater, container, false);
        handle.setBinding(binding);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        handle.populateForm();
        handle.setupNavigationButtons();

        AdapterMaquinaList.OnItemClickListener listener = maquina -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", maquina.getId());
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_maquinaList_to_maquinaShow, bundle);
        };
        binding.rcvMaquinaList.setAdapter(new AdapterMaquinaList(handle.getList(), listener));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}