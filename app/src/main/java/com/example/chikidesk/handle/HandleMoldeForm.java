package com.example.chikidesk.handle;

import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.check.CheckMoldeForm;
import com.example.chikidesk.databinding.MoldeFormBinding;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.driver.DriverForm;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.fragment.BaseFragment;

import java.util.Comparator;
import java.util.stream.Collectors;


public class HandleMoldeForm extends BaseHandle<BaseFragment, Integer> implements DriverForm {
    private final MoldeFormBinding binding;
    public HandleMoldeForm(BaseFragment fragment) {
        super(fragment);
        binding = (MoldeFormBinding) super.binding;
    }

    @Override
    public void drive() {
        setupListeners();
        setupNavigationButtons();
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
    public void setupListeners() {
        binding.btnMoldeFormNew.setOnClickListener(v -> driveActionDao());
    }

    @Override
    public void setupNavigationButtons() {
        binding.fabMoldeFormBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeFormHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    @Override
    public void destroyDriver() {}

    @Override
    protected void setKeysByBundle() {}
    @Override
    protected void initProperties() {}
    @Override
    protected void populateForm() {}
    @Override
    protected void setAdapters() {}
}