package com.example.chikidesk.handle;

import android.widget.Toast;

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
        if(!check.isSuccess()) {
            Toast.makeText(getContext(), "Datos inválidos", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean success = repo.insertMaquina(check.getEntity());

        if(success) {
            Navigation.findNavController(getView()).navigate(R.id.action_maquinaForm_to_maquinaList);
            Toast.makeText(fragment.requireContext(), "Nueva máquina guardada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error al guardar la máquina", Toast.LENGTH_LONG).show();
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

        // Logic for the new shortcut button
        binding.fabMaquinaFormList.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentMaquinaList, false));
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