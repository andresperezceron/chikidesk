package com.example.chikidesk.handle;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.chikidesk.R;
import com.example.chikidesk.check.CheckNewConfig;
import com.example.chikidesk.check.CheckNewFullConfig;
import com.example.chikidesk.check.CheckNewExpulsor;
import com.example.chikidesk.check.CheckNewInyeccion;
import com.example.chikidesk.check.CheckNewReten;
import com.example.chikidesk.check.CheckNewTemperatura;
import com.example.chikidesk.databinding.ConfigFormBinding;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.repository.ConfigRepository;
import com.example.chikidesk.ui.fragment.MainFragment;

import java.util.Objects;

public class HandleConfigForm extends Handle<MainFragment, Integer> {
    private ConfigFormBinding binding;
    private Maquina maquina;
    private Molde molde;
    private CheckNewFullConfig check;

    public HandleConfigForm(MainFragment fragment) {
        super(fragment);
        this.binding = (ConfigFormBinding) super.binding;
    }

    @Override
    public void drive() {
        setKeysByBundle();
        initProperties();
        populateForm();
        setupListeners();
        setupNavigationButtons();
    }

    @Override
    protected void setKeysByBundle() {
        id = getBundle() != null ? getBundle().getInt("maquina") : 0;
        idAux1 = getBundle() != null ? getBundle().getInt("molde") : 0;
        assert id != 0;
        assert idAux1 != 0;
    }

    @Override
    protected void initProperties() {
        maquina = appCache.maquinaList.stream()
                .filter(m -> Objects.equals(m.getId(), id))
                .findFirst().orElse(null);
        molde = appCache.moldeList.stream()
                .filter(m -> Objects.equals(m.getId(), idAux1))
                .findFirst().orElse(null);
    }

    @Override
    protected void populateForm() {
        binding.edtConfigFormMaquina.setText(maquina.getNombre());
        binding.edtConfigFormMolde.setText(molde.getNombre());
        binding.btnConfigFormContinue.setEnabled(false);
        binding.btnConfigFormContinue.setText("");
    }

    @Override
    protected void setupListeners() {
        binding.btnConfigFormNew.setOnClickListener(v -> selectionModeInsert());
        binding.btnConfigFormContinue.setOnClickListener(v -> driveActionDao());
        binding.btnConfigFormReset.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("maquina", maquina.getId());
            bundle.putInt("molde", molde.getId());
            NavHostFragment.findNavController(fragment).navigate(R.id.action_resetForm, bundle);
        });
    }

    @Override
    protected void setupNavigationButtons() {
        binding.fabConfigFormBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabConfigFormHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    @Override
    public void destroyDriver() {
        this.binding = null;
        this.maquina = null;
        this.molde = null;
        this.check = null;
    }

    @Override
    protected void driveActionDao() {
        ConfigRepository repo = new ConfigRepository(getContext(), appCache);
        boolean success = repo.insertFullConfig(
                check.getCheckConfig().getEntity(),
                check.getCheckTemp().getEntity(),
                check.getCheckInyeccion().getEntity(),
                check.getCheckReten().getEntity(),
                check.getCheckExpulsor().getEntity());

        if(success) {
            Toast.makeText(getContext(), R.string.tot_new_config, Toast.LENGTH_SHORT).show();
            Navigation.findNavController(getView()).navigate(R.id.action_configForm_to_configList);
        } else {
            new AlertDialog.Builder(getContext())
                    .setTitle("Error")
                    .setMessage("No se pudo guardar la configuración. Por favor, inténtelo de nuevo.")
                    .setPositiveButton(R.string.msn_ok, null)
                    .show();
        }
    }

    @Override
    protected void setAdapters() {}

    private void selectionModeInsert() {
        check = new CheckNewFullConfig(
                new CheckNewConfig(maquina, molde, binding),
                new CheckNewTemperatura(binding),
                new CheckNewInyeccion(binding),
                new CheckNewExpulsor(binding),
                new CheckNewReten(binding));
        if(check.isEmpty()) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.dialog_empty_fields)
                    .setMessage(R.string.msn_default_value)
                    .setPositiveButton(R.string.msn_ok, null)
                    .show();
            binding.btnConfigFormContinue.setEnabled(true);
            binding.btnConfigFormContinue.setText(R.string.btn_text_continue);
        } else driveActionDao();
    }
}