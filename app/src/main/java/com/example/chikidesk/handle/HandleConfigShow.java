package com.example.chikidesk.handle;

import android.os.Bundle;
import androidx.navigation.Navigation;
import com.example.chikidesk.R;
import com.example.chikidesk.databinding.ConfigShowBinding;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.FullConfig;
import com.example.chikidesk.ui.fragment.MainFragment;

public class HandleConfigShow extends Handle<MainFragment, Integer> {
    private ConfigShowBinding binding;
    private Configuracion config;
    private FullConfig fullConfig;
    private Bundle wellKnownNextBundle;

    public HandleConfigShow(MainFragment fragment) {
        super(fragment);
        this.binding = (ConfigShowBinding) super.binding;
    }

    @Override
    public void drive() {
        setKeysByBundle();
        initProperties();
        populateForm();
        setupNavigationButtons();
    }

    @Override
    protected void setKeysByBundle() {
        id = getBundle() != null ? getBundle().getInt("id") : 0;
        assert id != 0;
    }

    @Override
    protected void initProperties() {
        config = appCache.configList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        assert config != null;

        this.fullConfig = appCache.createFullConfigByConfig(config);
        assert fullConfig != null;

        // Prepare the bundle for the "update" screen
        wellKnownNextBundle = new Bundle();
        wellKnownNextBundle.putInt("id", id);
    }

    @Override
    protected void populateForm() {
        binding.edtConfigShowMaquina.setText(fullConfig.getMaquina().getNombre());
        binding.edtConfigShowMolde.setText(fullConfig.getMolde().getNombre());

        binding.edtConfigShowPlastico.setText(fullConfig.getConfiguracion().getPlastificacion());
        binding.edtConfigShowCiclo.setText(fullConfig.getConfiguracion().getTiempoCiclo());
        binding.edtConfigShowCicloReal.setText(fullConfig.getConfiguracion().getTiempoCicloReal());
        binding.edtConfigShowTimeOut.setText(fullConfig.getConfiguracion().getTimeOut());
        binding.edtConfigShowEnfriar.setText(fullConfig.getConfiguracion().getTiempoEnfriar());
        binding.edtConfigShowMaterial.setText(fullConfig.getConfiguracion().getMaterial());
        binding.edtConfigShowObservaciones.setText(fullConfig.getConfiguracion().getObservaciones());

        binding.edtConfigShowTemp1.setText(fullConfig.getTemperatura().getTemp1());
        binding.edtConfigShowTemp2.setText(fullConfig.getTemperatura().getTemp2());
        binding.edtConfigShowTemp3.setText(fullConfig.getTemperatura().getTemp3());
        binding.edtConfigShowTemp4.setText(fullConfig.getTemperatura().getTemp4());

        binding.edtConfigShowInyVel1.setText(fullConfig.getInyeccion().getVelocidad1());
        binding.edtConfigShowInyVel2.setText(fullConfig.getInyeccion().getVelocidad2());
        binding.edtConfigShowInyVel3.setText(fullConfig.getInyeccion().getVelocidad3());
        binding.edtConfigShowInyVel4.setText(fullConfig.getInyeccion().getVelocidad4());
        binding.edtConfigShowInyVel5.setText(fullConfig.getInyeccion().getVelocidad5());
        binding.edtConfigShowInyPre1.setText(fullConfig.getInyeccion().getPresion1());
        binding.edtConfigShowInyPre2.setText(fullConfig.getInyeccion().getPresion2());
        binding.edtConfigShowInyPre3.setText(fullConfig.getInyeccion().getPresion3());
        binding.edtConfigShowInyPre4.setText(fullConfig.getInyeccion().getPresion4());
        binding.edtConfigShowInyPre5.setText(fullConfig.getInyeccion().getPresion5());

        binding.edtConfigShowRetenVel.setText(fullConfig.getRetenPresion().getVelocidad());
        binding.edtConfigShowRetenPre.setText(fullConfig.getRetenPresion().getPresion());
        binding.edtConfigShowRetenTmp.setText(fullConfig.getRetenPresion().getTiempo());

        binding.edtConfigShowExpulsorVel1.setText(fullConfig.getExpulsor().getVelocidad1());
        binding.edtConfigShowExpulsorVel2.setText(fullConfig.getExpulsor().getVelocidad2());
        binding.edtConfigShowExpulsorPre1.setText(fullConfig.getExpulsor().getPresion1());
        binding.edtConfigShowExpulsorPre2.setText(fullConfig.getExpulsor().getPresion2());
        binding.edtConfigShowExpulsorPos1.setText(fullConfig.getExpulsor().getPosicion1());
        binding.edtConfigShowExpulsorPos2.setText(fullConfig.getExpulsor().getPosicion2());
    }

    @Override
    protected void setupNavigationButtons() {
        binding.fabConfigShowBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());

        binding.fabConfigShowHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));

        binding.fabConfigShowUpdate.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_configShow_to_configUpdate, wellKnownNextBundle));
        binding.fabConfigShowDelete.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_configShow_to_configDelete, wellKnownNextBundle));
    }

    @Override
    public void destroyDriver() {
        this.binding = null;
        config = null;
        fullConfig = null;
        wellKnownNextBundle = null;
    }

    @Override
    protected void setupListeners() {}
    @Override
    protected void driveActionDao() {}
    @Override
    protected void setAdapters() {}
}