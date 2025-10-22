package com.example.chikidesk.handles;

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


public class HandleMoldeForm extends HandleFragment<FragmentMoldeForm, FragmentMoldeFormBinding, Integer> {
    private MoldeDao dao;
    private CheckMoldeForm check;

    public HandleMoldeForm(AppCacheViewModel appCache, FragmentMoldeForm fragment) {
        super(appCache, fragment);
    }

    private void insert() {
        Molde newMolde = check.checkData();
        if(newMolde == null) return; //aqui los los TextImputLayout avisaran.
        List<Molde> newList = dao.exeCrudAction(newMolde, MoldeDao.ACTION_INSERT);
        if(newList != null) {
            appCache.moldeList = newList.stream()
                    .sorted(Comparator.comparing(Molde::getNombre, String.CASE_INSENSITIVE_ORDER))
                    .collect(Collectors.toList());
            Navigation.findNavController(getView()).navigate(R.id.action_moldeForm_to_moldeList);
            Toast.makeText(fragment.requireContext(), R.string.tot_new_molde, Toast.LENGTH_SHORT).show();
        } else new AlertDialog
                .Builder(fragment.requireContext())
                .setTitle("ERROR")
                .setMessage(R.string.alert_new_molde)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public FragmentMoldeFormBinding setBinding(FragmentMoldeFormBinding binding) {
        super.binding = binding;
        check = new CheckMoldeForm(appCache, binding);
        dao = new MoldeDao(getContext());
        return binding;
    }

    @Override
    public void setupListener() {
        binding.btnMoldeFormNew.setOnClickListener(v -> insert());
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

    @Override
    public void destroyHandle() {
        super.onDestroyHandle();
        check = null;
        dao = null;
    }

    @Override
    protected void setKeysByBundle() {}
}
