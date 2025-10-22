package com.example.chikidesk.handles;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentSelectMaquinaBinding;
import com.example.chikidesk.ui.adapters.AdapterMaquinaList;
import com.example.chikidesk.ui.fragments.FragmentListConfigMaquina;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class HandleSelectMaquina extends HandleFragment<FragmentListConfigMaquina, FragmentSelectMaquinaBinding, Integer> {
    public HandleSelectMaquina(@NonNull AppCacheViewModel appCache, @NonNull FragmentListConfigMaquina fragment) {
        super(appCache, fragment);
    }

    @Override
    public FragmentSelectMaquinaBinding setBinding(FragmentSelectMaquinaBinding binding) {
        super.binding = binding;
        return binding;
    }

    @Override
    public void setupListener() {
        AdapterMaquinaList.OnItemClickListener listener = maquina -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", maquina.getId());
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_selectMaquina_to_selectMolde, bundle);
        };
        binding.rcvSelectMaquina.setAdapter(new AdapterMaquinaList(appCache.maquinaList, listener));
    }

    @Override
    public void populateForm() {
        binding.rcvSelectMaquina.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvSelectMaquina.setHasFixedSize(true);
    }

    @Override
    public void setupNavigationButtons() {
        binding.fabSelectMaquinaHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_selectMaquina_to_home));
        binding.fabSelectMaquinaBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());

    }

    @Override
    public void destroyHandle() {
        super.onDestroyHandle();
    }

    @Override
    protected void setKeysByBundle() {}
}
