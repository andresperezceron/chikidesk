package com.example.chikidesk.handle;

import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.check.insert.CheckInsertMaquina;
import com.example.chikidesk.databinding.MaquinaFormBinding;
import com.example.chikidesk.repository.MaquinaRepository;
import com.example.chikidesk.ui.fragment.MainFragment;

public class HandleMaquinaForm extends Handle<MainFragment, Integer> {
    private MaquinaFormBinding binding;
    private MaquinaRepository repo;

    public HandleMaquinaForm(MainFragment fragment) {
        super(fragment);
        this.binding = (MaquinaFormBinding) super.binding;
    }

    @Override
    public void drive() {
        initProperties();
        setupListeners();
        setupNavigationButtons();
    }

    @Override
    protected void initProperties() {
        repo = new MaquinaRepository(getContext(), appCache);
    }

    @Override
    protected void driveActionDao() {
        CheckInsertMaquina check = new CheckInsertMaquina(appCache, binding);
        if(!check.isSuccess()) { return; }

        boolean success = repo.insertMaquina(check.getEntity());

        if(success) {
            Navigation.findNavController(getView()).navigate(R.id.action_maquinaForm_to_maquinaList);
            Toast.makeText(fragment.requireContext(), R.string.tot_new_maquina, Toast.LENGTH_SHORT).show();
        } else {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.alert_title_error)
                    .setMessage(R.string.alert_new_maquina)
                    .setCancelable(false)
                    .setPositiveButton(R.string.alert_ok, null)
                    .show();
        }
    }

    @Override
    protected void setupListeners() {
        binding.btnMaquinaFormNew.setOnClickListener(v -> driveActionDao());
    }

    @Override
    protected void setupNavigationButtons() {
        binding.fabMaquinaFormBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());

        binding.fabMaquinaFormHome.setOnClickListener(v ->
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