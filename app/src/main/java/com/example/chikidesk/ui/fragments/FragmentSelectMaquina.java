package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentSelectMaquinaBinding;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.ui.adapters.AdapterMaquinaList;

import java.util.ArrayList;
import java.util.List;

public class FragmentSelectMaquina extends Fragment {
    private FragmentSelectMaquinaBinding binding;

    public FragmentSelectMaquina() {}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSelectMaquinaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rcvSelectMaquina.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvSelectMaquina.setHasFixedSize(true);

        MaquinaDao dao = new MaquinaDao(requireContext());
        List<Maquina> list = new ArrayList<>(dao.getAll());

        AdapterMaquinaList.OnItemClickListener listener = maquina -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_selectMaquina_to_selectMolde, bundle);
        };

        binding.rcvSelectMaquina.setAdapter(new AdapterMaquinaList(list, listener));

        binding.fabSelectMaquinaHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_selectMaquina_to_home));
        binding.fabSelectMaquinaBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}