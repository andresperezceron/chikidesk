package com.example.chikidesk.handle;

import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.check.insert.CheckInsertMolde;
import com.example.chikidesk.databinding.MoldeFormBinding;
import com.example.chikidesk.repository.MoldeRepository;
import com.example.chikidesk.ui.fragment.MainFragment;

public class HandleMoldeForm extends Handle<MainFragment, Integer> {
    private MoldeFormBinding binding;
    private MoldeRepository repo;

    public HandleMoldeForm(MainFragment fragment) {
        super(fragment);
        binding = (MoldeFormBinding) super.binding;
    }

    @Override
    public void drive() {
        initProperties();
        setupListeners();
        setupNavigationButtons();
    }

    @Override
    protected void initProperties() {
        repo = new MoldeRepository(getContext(), appCache);
    }

    @Override
    protected void driveActionDao() {
        CheckInsertMolde check = new CheckInsertMolde(appCache, binding);
        if(!check.isSuccess()) { return; }

        boolean success = repo.insertMolde(check.getEntity());

        if(success) {
            Navigation.findNavController(getView()).navigate(R.id.action_moldeForm_to_moldeList);
            Toast.makeText(fragment.requireContext(), R.string.tot_new_molde, Toast.LENGTH_SHORT).show();
        } else {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.alert_title_error)
                    .setMessage(R.string.alert_new_molde)
                    .setCancelable(false)
                    .setPositiveButton(R.string.alert_ok, null)
                    .show();
        }
    }

    @Override
    protected void setupListeners() {
        binding.btnMoldeFormNew.setOnClickListener(v -> driveActionDao());
    }

    @Override
    protected void setupNavigationButtons() {
        binding.fabMoldeFormBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());

        binding.fabMoldeFormHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    @Override
    public void destroyDriver() {
        this.binding = null;
        this.repo = null;
    }

    @Override
    protected void setKeysByBundle() {}

    @Override
    protected void populateForm() {}

    @Override
    protected void setAdapters() {}
}