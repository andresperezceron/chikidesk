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
import com.example.chikidesk.databinding.FragmentMoldeListBinding;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.adapters.AdapterMoldeList;

import java.util.List;

public class FragmentMoldeList extends Fragment {
    private FragmentMoldeListBinding binding;
    private List<Molde> list;

    public FragmentMoldeList() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MoldeDao dao = new MoldeDao(requireContext());
        list = dao.getAllOderBy("nombre");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(binding != null) return binding.getRoot();
        binding = FragmentMoldeListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rcvMoldeList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvMoldeList.setHasFixedSize(true);

        AdapterMoldeList.OnItemClickListener listener = molde -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("molde", molde);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_moldeList_to_moldeShow, bundle);
        };
        binding.rcvMoldeList.setAdapter(new AdapterMoldeList(list, listener));

        binding.fabMoldeListHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeListNew.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_moldeList_to_moldeForm));
    }
}