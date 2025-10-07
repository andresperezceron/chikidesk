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
import androidx.recyclerview.widget.RecyclerView;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMaquinaListBinding;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.ui.adapters.AdapterMaquinaList;

import java.util.List;

public class FragmentMaquinaList extends Fragment {
    private FragmentMaquinaListBinding binding;

    public FragmentMaquinaList() {}

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

        MaquinaDao dao = new MaquinaDao(requireContext());
        List<Maquina> list = dao.getAllOderBy("nombre");

        AdapterMaquinaList.OnItemClickListener listener = maquina -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_maquinaList_to_maquinaShow, bundle);
        };
        binding.rcvMaquinaList.setAdapter(new AdapterMaquinaList(list, listener));

        binding.fabMaquinaListHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_maquinaList_to_home));
        binding.fabMaquinaListNew.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_maquinaList_to_maquinaForm));

    }
}