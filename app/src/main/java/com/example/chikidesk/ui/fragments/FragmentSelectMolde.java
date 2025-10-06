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
import com.example.chikidesk.databinding.FragmentSelectMoldeBinding;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.adapters.AdapterMoldeList;

import java.util.List;

public class FragmentSelectMolde extends Fragment {
    private FragmentSelectMoldeBinding binding;

    public FragmentSelectMolde() {}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSelectMoldeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rcvSelectMolde.setLayoutManager(new LinearLayoutManager(getContext()));

        Maquina maquina = getArguments() != null ? getArguments().getParcelable("maquina") : null;
        assert maquina != null;

        MoldeDao dao = new MoldeDao(getContext());
        List<Molde> moldes = dao.getMoldesNotConfig(maquina.getId());

        AdapterMoldeList.OnItemClickListener listener = molde -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            bundle.putParcelable("molde", molde);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_selectMolde_to_configForm, bundle);
        };

        binding.rcvSelectMolde.setAdapter(new AdapterMoldeList(moldes, listener));

        binding.fabSelectMoldeHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_selectMolde_to_home));
        binding.fabSelectMoldeBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}