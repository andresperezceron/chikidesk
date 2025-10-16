package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentStartAppBinding;
import com.example.chikidesk.db.ConfigDao;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.util.List;


public class FragmentStartApp extends Fragment {
    private FragmentStartAppBinding binding;
    private AppCacheViewModel appCache;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MoldeDao moldeDao = new MoldeDao(requireContext());
        MaquinaDao maquinaDao = new MaquinaDao(requireContext());
        ConfigDao configDao = new ConfigDao(requireContext());
        List<Molde> moldeList = moldeDao.getAllOderBy("nombre");
        List<Maquina> maquinaList = maquinaDao.getAllOderBy("nombre");
        List<Configuracion> configList = configDao.getAll();

        appCache = new ViewModelProvider(requireActivity()).get(AppCacheViewModel.class);
        appCache.Construct(maquinaList, moldeList, configList);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentStartAppBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(getContext(), String.valueOf(appCache.getListaMoldes().get(0).getNombre()), Toast.LENGTH_SHORT).show();
        binding.btnStarAppMoldes.setOnClickListener(v ->
            Navigation.findNavController(v).navigate(R.id.action_startApp_to_moldeList));

        binding.btnStarAppMaquinas.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_startApp_to_maquinaList));

        binding.btnStarAppConfig.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_startApp_to_configList));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}