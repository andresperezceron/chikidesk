package com.example.chikidesk.handle;

import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.check.CheckUpdateConfig;
import com.example.chikidesk.check.CheckUpdateExpulsor;
import com.example.chikidesk.check.CheckUpdateFullConfig;
import com.example.chikidesk.check.CheckUpdateInyeccion;
import com.example.chikidesk.check.CheckUpdateRetenPresion;
import com.example.chikidesk.check.CheckUpdateTemperatura;
import com.example.chikidesk.databinding.ConfigUpdateBinding;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.FullConfig;
import com.example.chikidesk.repository.ConfigRepository;
import com.example.chikidesk.ui.fragment.MainFragment;

public class HandleConfigUpdate extends Handle<MainFragment, Integer> {
    private ConfigUpdateBinding binding;
    private FullConfig oldFullConfig;
    private ConfigRepository repo;

    public HandleConfigUpdate(MainFragment fragment) {
        super(fragment);
        this.binding = (ConfigUpdateBinding) super.binding;
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
    public void destroyDriver() {
        this.binding = null;
        this.oldFullConfig = null;
        this.repo = null;
    }

    @Override
    protected void setKeysByBundle() {
        id = getBundle() != null ? getBundle().getInt("id") : 0;
        assert id != 0;
    }

    @Override
    protected void initProperties() {
        Configuracion oldConfig = appCache.configList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        assert oldConfig != null;
        oldFullConfig = appCache.createFullConfigByConfig(oldConfig);
        assert oldFullConfig != null;

        repo = new ConfigRepository(getContext(), appCache);
    }

    @Override
    protected void populateForm() {
        binding.edtConfigUpdateMaquina.setText(oldFullConfig.getMaquina().getNombre());
        binding.edtConfigUpdateMolde.setText(oldFullConfig.getMolde().getNombre());

        binding.edtConfigUpdateTemp1.setText(oldFullConfig.getTemperatura().getTemp1());
        binding.edtConfigUpdateTemp2.setText(oldFullConfig.getTemperatura().getTemp2());
        binding.edtConfigUpdateTemp3.setText(oldFullConfig.getTemperatura().getTemp3());
        binding.edtConfigUpdateTemp4.setText(oldFullConfig.getTemperatura().getTemp4());

        binding.edtConfigUpdateInyVel1.setText(oldFullConfig.getInyeccion().getVelocidad1());
        binding.edtConfigUpdateInyVel2.setText(oldFullConfig.getInyeccion().getVelocidad2());
        binding.edtConfigUpdateInyVel3.setText(oldFullConfig.getInyeccion().getVelocidad3());
        binding.edtConfigUpdateInyVel4.setText(oldFullConfig.getInyeccion().getVelocidad4());
        binding.edtConfigUpdateInyVel5.setText(oldFullConfig.getInyeccion().getVelocidad5());
        binding.edtConfigUpdateInyPre1.setText(oldFullConfig.getInyeccion().getPresion1());
        binding.edtConfigUpdateInyPre2.setText(oldFullConfig.getInyeccion().getPresion2());
        binding.edtConfigUpdateInyPre3.setText(oldFullConfig.getInyeccion().getPresion3());
        binding.edtConfigUpdateInyPre4.setText(oldFullConfig.getInyeccion().getPresion4());
        binding.edtConfigUpdateInyPre5.setText(oldFullConfig.getInyeccion().getPresion5());

        binding.edtConfigUpdateRetenVel.setText(oldFullConfig.getRetenPresion().getVelocidad());
        binding.edtConfigUpdateRetenPre.setText(oldFullConfig.getRetenPresion().getPresion());
        binding.edtConfigUpdateRetenTmp.setText(oldFullConfig.getRetenPresion().getTiempo());

        binding.edtConfigUpdateExpulsorVel1.setText(oldFullConfig.getExpulsor().getVelocidad1());
        binding.edtConfigUpdateExpulsorPre1.setText(oldFullConfig.getExpulsor().getPresion1());
        binding.edtConfigUpdateExpulsorPos1.setText(oldFullConfig.getExpulsor().getPosicion1());
        binding.edtConfigUpdateExpulsorVel2.setText(oldFullConfig.getExpulsor().getVelocidad2());
        binding.edtConfigUpdateExpulsorPre2.setText(oldFullConfig.getExpulsor().getPresion2());
        binding.edtConfigUpdateExpulsorPos2.setText(oldFullConfig.getExpulsor().getPosicion2());

        binding.edtConfigUpdatePlastico.setText(oldFullConfig.getConfiguracion().getPlastificacion());
        binding.edtConfigUpdateCiclo.setText(oldFullConfig.getConfiguracion().getTiempoCiclo());
        binding.edtConfigUpdateCicloReal.setText(oldFullConfig.getConfiguracion().getTiempoCicloReal());
        binding.edtConfigUpdateTimeOut.setText(oldFullConfig.getConfiguracion().getTimeOut());
        binding.edtConfigUpdateEnfriar.setText(oldFullConfig.getConfiguracion().getTiempoEnfriar());
        binding.edtConfigUpdateMaterial.setText(oldFullConfig.getConfiguracion().getMaterial());
        binding.edtConfigUpdateObservaciones.setText(oldFullConfig.getConfiguracion().getObservaciones());
    }

    @Override
    protected void driveActionDao() {
        CheckUpdateFullConfig check = new CheckUpdateFullConfig(
                new CheckUpdateConfig(oldFullConfig.getMaquina(), oldFullConfig.getMolde(), binding, oldFullConfig.getConfiguracion()),
                new CheckUpdateTemperatura(binding, oldFullConfig.getTemperatura()),
                new CheckUpdateInyeccion(binding, oldFullConfig.getInyeccion()),
                new CheckUpdateRetenPresion(binding, oldFullConfig.getRetenPresion()),
                new CheckUpdateExpulsor(binding, oldFullConfig.getExpulsor()));

        if(check.areEqualToUpdate()) {
            Toast.makeText(getContext(), "No se han realizado cambios", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!check.isSuccess()) {
            Toast.makeText(getContext(), "Hay campos vacíos o inválidos", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean success = repo.updateFullConfig(
                check.getCheckConfig().getEntity(),
                check.getCheckTemp().getEntity(),
                check.getCheckInyeccion().getEntity(),
                check.getCheckReten().getEntity(),
                check.getCheckExpulsor().getEntity()
        );

        if (success) {
            Toast.makeText(getContext(), "Configuración actualizada correctamente", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(getView()).navigate(R.id.action_configUpdate_to_configShow, getBundle());
        } else {
            Toast.makeText(getContext(), "Error al actualizar la configuración", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void setupListeners() {
        binding.btnConfigUpdateUpdate.setOnClickListener(v -> driveActionDao());
    }

    @Override
    protected void setupNavigationButtons() {
        binding.fabConfigUpdateBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabConfigUpdateHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    @Override
    protected void setAdapters() {}
}