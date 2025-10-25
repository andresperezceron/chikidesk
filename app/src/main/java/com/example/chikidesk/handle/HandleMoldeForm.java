package com.example.chikidesk.handle;

import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.check.CheckMoldeForm;
import com.example.chikidesk.databinding.FragmentMoldeFormBinding;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.fragment.FragmentMoldeForm;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.util.Comparator;
import java.util.stream.Collectors;


public class HandleMoldeForm extends BaseHandle<FragmentMoldeForm, FragmentMoldeFormBinding, Integer> {

    public HandleMoldeForm(AppCacheViewModel appCache, FragmentMoldeForm fragment) {
        super(appCache, fragment);
    }

    @Override
    public void drive() {

    }

    @Override
    protected void driveActionDao() {
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
    protected void setAdapters() {

    }

    @Override
    public void setupListeners() {
        binding.btnMoldeFormNew.setOnClickListener(v -> driveActionDao());
    }

    @Override
    public void initProperties() {}


    @Override
    public void populateForm() {}

    @Override
    public void setupNavigationButtons() {
        binding.fabMoldeFormBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeFormHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    public void destroyHandle() {
        super.onDestroyDriver();
    }

    @Override
    protected void setKeysByBundle() {}
}
