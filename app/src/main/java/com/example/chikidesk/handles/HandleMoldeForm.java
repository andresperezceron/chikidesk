package com.example.chikidesk.handles;

import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.checks.CheckMoldeForm;
import com.example.chikidesk.databinding.FragmentMoldeFormBinding;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.fragments.FragmentMoldeForm;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class HandleMoldeForm implements HandleFragment {
    private final AppCacheViewModel appCache;
    private final MoldeDao dao;
    private final FragmentMoldeForm fragment;
    private FragmentMoldeFormBinding binding;
    private final View v;

    public HandleMoldeForm(AppCacheViewModel appCache, MoldeDao dao, FragmentMoldeForm fragment) {
        this.appCache = appCache;
        this.dao = dao;
        this.fragment = fragment;
        v = fragment.getView();
    }

    @Override
    public void setBinding() {
        this.binding = (FragmentMoldeFormBinding) fragment.getBinding();
    }

    public void insert() {
        CheckMoldeForm check = new CheckMoldeForm(appCache, binding);
        Molde newMolde = check.checkData();
        if(newMolde == null) return;
        List<Molde> newList = dao.exeCrudAction(newMolde, MoldeDao.ACTION_INSERT);
        if(newList != null) {
            appCache.moldeList = newList.stream()
                    .sorted(Comparator.comparing(Molde::getNombre, String.CASE_INSENSITIVE_ORDER))
                    .collect(Collectors.toList());
            Navigation.findNavController(v).navigate(R.id.action_moldeForm_to_moldeList);
            Toast.makeText(fragment.requireContext(), R.string.tot_new_molde, Toast.LENGTH_SHORT).show();
        } else new AlertDialog
                .Builder(fragment.requireContext())
                .setTitle("ERROR")
                .setMessage(R.string.alert_new_molde)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public void populateForm() {}

    @Override
    public void setupNavigationButtons() {
        binding.fabMoldeFormBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeFormHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }
}
