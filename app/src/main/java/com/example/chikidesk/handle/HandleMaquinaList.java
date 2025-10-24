package com.example.chikidesk.handle;

import android.content.Context;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMaquinaListBinding;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.util.List;

public class HandleMaquinaList {
    private FragmentMaquinaListBinding binding;
    private final AppCacheViewModel appCache;
    private final Context context;

    public HandleMaquinaList(AppCacheViewModel appCache, Context context) {
        this.appCache = appCache;
        this.context = context;
    }

    public void setBinding(FragmentMaquinaListBinding binding) {
        this.binding = binding;
    }

    public void populateForm() {
        binding.rcvMaquinaList.setLayoutManager(new LinearLayoutManager(context));
        binding.rcvMaquinaList.setHasFixedSize(true);
    }

    public void setupNavigationButtons() {
        binding.fabMaquinaListHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMaquinaListNew.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_moldeList_to_moldeForm));
    }

    public List<Maquina> getList() {
        return appCache.maquinaList;
    }
}
