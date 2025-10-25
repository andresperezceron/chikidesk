package com.example.chikidesk.handle;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMaquinaListBinding;
import com.example.chikidesk.driver.DriverList;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.ui.adapter.AdapterMaquinaList;
import com.example.chikidesk.ui.fragment.FragmentMaquinaList;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.util.List;

public class HandleMaquinaList
        extends BaseHandle<FragmentMaquinaList, FragmentMaquinaListBinding, Integer>
        implements DriverList<FragmentMaquinaListBinding> {


    public HandleMaquinaList(@NonNull AppCacheViewModel appCache, @NonNull FragmentMaquinaList fragment) {
        super(appCache, fragment);
    }

    @Override
    public void drive() {
        setAdapters();
        populateForm();
        setupNavigationButtons();
    }

    @Override
    public void setAdapters() {
        binding.rcvMaquinaList.setAdapter(new AdapterMaquinaList(appCache.maquinaList, maquina -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", maquina.getId());
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_maquinaList_to_maquinaShow, bundle);
        }));
    }

    @Override
    public void populateForm() {

    }

    @Override
    public void setupNavigationButtons() {

    }

    @Override
    public void destroyDriver() {
        super.onDestroyDriver();
    }

    @Override
    protected void setKeysByBundle() {

    }

    @Override
    protected void initProperties() {

    }

    @Override
    protected void driveActionDao() {

    }

    @Override
    protected void setupListeners() {

    }
}