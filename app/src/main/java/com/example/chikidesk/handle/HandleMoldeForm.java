package com.example.chikidesk.handle;

import android.widget.Toast;

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
        if(!check.isSuccess()) {
            Toast.makeText(getContext(), "Datos inválidos", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean success = repo.insertMolde(check.getEntity());

        if(success) {
            Navigation.findNavController(getView()).navigate(R.id.action_moldeForm_to_moldeList);
            Toast.makeText(fragment.requireContext(), "Nuevo molde guardado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error al guardar el molde", Toast.LENGTH_LONG).show();
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

        // Logic for the new shortcut button
        binding.fabMoldeFormList.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentMoldeList, false));
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