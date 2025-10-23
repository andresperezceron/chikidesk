package com.example.chikidesk.handles;

import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.checks.CheckMoldeForm;
import com.example.chikidesk.databinding.FragmentMoldeFormBinding;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.fragments.FragmentMoldeForm;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.util.Comparator;
import java.util.stream.Collectors;


public class HandleMoldeForm extends HandleFragment<FragmentMoldeForm, FragmentMoldeFormBinding, Integer> {

    public HandleMoldeForm(AppCacheViewModel appCache, FragmentMoldeForm fragment) {
        super(appCache, fragment);
    }

    private void insert() {
        CheckMoldeForm check = new CheckMoldeForm(appCache, binding);
        if(check.isNotSuccess()) return;

        MoldeDao dao = new MoldeDao(getContext());
        appCache.moldeList = dao.exeCrudAction(check.getEntity(), MoldeDao.ACTION_INSERT).stream()
                .sorted(Comparator.comparing(Molde::getNombre, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());

        if(appCache.getStatus()) {
            Navigation.findNavController(getView()).navigate(R.id.action_moldeForm_to_moldeList);
            Toast.makeText(fragment.requireContext(), R.string.tot_new_molde, Toast.LENGTH_SHORT).show();
        } else assert false;
    }

    @Override
    public FragmentMoldeFormBinding setBinding(FragmentMoldeFormBinding binding) {
        super.binding = binding;
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
    }

    @Override
    protected void setKeysByBundle() {}
}
