package com.example.chikidesk.handle;

import androidx.annotation.NonNull;

import com.example.chikidesk.check.CheckNewConfig;
import com.example.chikidesk.check.CheckFullConfig;
import com.example.chikidesk.check.CheckNewInyeccion;
import com.example.chikidesk.check.CheckNewTemperatura;
import com.example.chikidesk.databinding.ConfigFormBinding;
import com.example.chikidesk.driver.DriverFormConfig;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.fragment.MainFragment;

import java.util.Objects;

public class HandleConfigForm extends Handle<MainFragment, Integer> implements DriverFormConfig {
    private ConfigFormBinding binding;
    private Maquina maquina;
    private Molde molde;

    public HandleConfigForm(MainFragment fragment) {
        super(fragment);
        this.binding = (ConfigFormBinding) super.binding;
    }

    @Override
    public void drive() {
        setKeysByBundle();
        initProperties();
        populateForm();
    }

    @Override
    public void setKeysByBundle() {
        id = getBundle() != null ? getBundle().getInt("maquina") : 0;
        idAux1 = getBundle() != null ? getBundle().getInt("molde") : 0;
    }

    @Override
    public void initProperties() {
        maquina = appCache.maquinaList.stream()
                .filter(m -> Objects.equals(m.getId(), id))
                .findFirst().orElse(null);
        molde = appCache.moldeList.stream()
                .filter(m -> Objects.equals(m.getId(), idAux1))
                .findFirst().orElse(null);
    }

    @Override
    public void populateForm() {
        binding.edtConfigFormMaquina.setText(maquina.getNombre());
        binding.edtConfigFormMolde.setText(molde.getNombre());
        binding.btnConfigFormContinue.setEnabled(false);
        binding.btnConfigFormContinue.setText("");
    }

    @Override
    public void setupListeners() {

    }

    @Override
    public void setupNavigationButtons() {

    }

    @Override
    public void destroyDriver() {
        this.binding = null;
    }

    @Override
    protected void driveActionDao() {

    }

    @Override
    protected void setAdapters() {

    }

    @NonNull
    private CheckFullConfig getCheckFullConfig() {
        return new CheckFullConfig(
                new CheckNewConfig(appCache, binding, maquina, molde),
                new CheckNewTemperatura(appCache, binding), new CheckNewInyeccion(appCache, binding),

    }
}
