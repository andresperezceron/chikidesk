package com.example.chikidesk.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.chikidesk.driver.DriverList;
import com.example.chikidesk.handle.HandleMaquinaList;
import com.example.chikidesk.databinding.FragmentMaquinaListBinding;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class FragmentMaquinaList extends Fragment {
    private DriverList driver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //driver = new HandleMaquinaList(
                //new ViewModelProvider(requireActivity()).get(AppCacheViewModel.class),
                //this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                                  @Nullable ViewGroup container,
                                  @Nullable Bundle savedInstanceState) {
        //return driver.setBinding(FragmentMaquinaListBinding
          //              .inflate(inflater, container, false)).getRoot();
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        driver.drive();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        driver.destroyDriver();
    }
}