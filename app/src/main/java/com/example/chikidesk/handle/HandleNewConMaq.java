package com.example.chikidesk.handle;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentNewConMaqBinding;
import com.example.chikidesk.ui.adapter.AdapterMaquinaList;
import com.example.chikidesk.ui.fragment.BaseFragment;
import com.example.chikidesk.ui.fragment.FragmenNewConMaq;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class HandleNewConMaq extends BaseHandle<BaseFragment, Integer> {
    public HandleNewConMaq(@NonNull AppCacheViewModel appCache, @NonNull BaseFragment fragment) {
        super(appCache, fragment);
    }

    @Override
    public void drive() {

    }

    @Override
    protected void driveActionDao() {

    }

    @Override
    protected void setAdapters() {

    }

    @Override
    public void initProperties() {

    }

    @Override
    public void setupListeners() {
        AdapterMaquinaList.OnItemClickListener listener = maquina -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", maquina.getId());
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_listMaquinaConfig_to_listMoldeConfig, bundle);
        };
        //binding.rcvNewConMaq.setAdapter(new AdapterMaquinaList(appCache.maquinaList, listener));

    }

    @Override
    public void populateForm() {
        //binding.rcvNewConMaq.setLayoutManager(new LinearLayoutManager(getContext()));
        //binding.rcvNewConMaq.setHasFixedSize(true);

    }

    @Override
    public void setupNavigationButtons() {
            /*binding.fabNewConMaqHome.setOnClickListener(v ->
                    Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
            binding.fabNewConMaqBack.setOnClickListener(v ->
                    Navigation.findNavController(v).popBackStack());*/

    }

    @Override
    protected void destroyDriver() {

    }

    public void destroyHandle() {
        //super.onDestroyDriver();
    }

    @Override
    protected void setKeysByBundle() {}
}