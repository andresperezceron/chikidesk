package com.example.chikidesk.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentConfigUpdateBinding;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Expulsor;
import com.example.chikidesk.model.FullConfig;
import com.example.chikidesk.model.Inyeccion;
import com.example.chikidesk.model.RetenPresion;
import com.example.chikidesk.model.Temperatura;
import com.example.chikidesk.repository.ConfigRepository;
import com.example.chikidesk.ui.validateforms.CheckConfig;
import com.example.chikidesk.ui.validateforms.CheckExpulsor;
import com.example.chikidesk.ui.validateforms.CheckFullConfig;
import com.example.chikidesk.ui.validateforms.CheckInyeccion;
import com.example.chikidesk.ui.validateforms.CheckReten;
import com.example.chikidesk.ui.validateforms.CheckTemp;
import com.google.android.material.textfield.TextInputLayout;


public class FragmentConfigUpdate extends Fragment {
    private FragmentConfigUpdateBinding binding;
    private ConfigRepository configRepository;
    private FullConfig oldFullConfig;

    public FragmentConfigUpdate() {}

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configRepository = new ConfigRepository(requireContext());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentConfigUpdateBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Configuracion oldConfig = getArguments() != null ?
                getArguments().getParcelable("configuracion") : null;
        assert oldConfig != null;

        this.oldFullConfig = configRepository.createFullConfigByConfig(oldConfig);
        populateForm();

        binding.btnConfigUpdateUpdate.setOnClickListener(v -> {
            CheckFullConfig checkFullConfig = createCheckFullConfig();
            if(checkFullConfig.areEqualToUpdate())
                return;

            if(checkFullConfig.isEmpty()) {
                new AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.dialog_empty_fields))
                        .setMessage(R.string.msn_update_empty)
                        .setPositiveButton(getString(R.string.msn_ok), null)
                        .show();
            } else if(configRepository.updateFullConfig(
                    checkFullConfig.getCheckConfig().getCheckedEntity(),
                    checkFullConfig.getCheckTemp().getCheckedEntity(),
                    checkFullConfig.getCheckInyeccion().getCheckedEntity(),
                    checkFullConfig.getCheckReten().getCheckedEntity(),
                    checkFullConfig.getCheckExpulsor().getCheckedEntity())) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("configuracion",
                        checkFullConfig.getCheckConfig().getConfigForBundle());
                Toast.makeText(getContext(), R.string.tot_upd_config, Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_configUpdate_to_configShow, bundle);
            }
        });

        binding.fabConfigUpdateBack.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("configuracion", oldConfig);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_configUpdate_to_configShow, bundle);
        });

        binding.fabConfigUpdateHome.setOnClickListener(v ->
            Navigation.findNavController(v).navigate(R.id.action_configUpdate_to_home)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private CheckFullConfig createCheckFullConfig() {
        CheckConfig checkConfig = new CheckConfig(new TextInputLayout[] {
                binding.tilConfigUpdatePlastifico, binding.tilConfigUpdateCiclo,
                binding.tilConfigUpdateCicloReal, binding.tilConfigUpdateEnfriar,
                binding.tilConfigUpdateTimeOut, binding.tilConfigUpdateMaterial,
                binding.tilConfigUpdateObservaciones});
        checkConfig.checkUpdate(oldFullConfig.getConfiguracion(), new Configuracion(
                oldFullConfig.getConfiguracion().getId(),
                oldFullConfig.getMaquina().getId(),
                oldFullConfig.getMolde().getId(),
                getTextFrom(binding.edtConfigUpdatePlastico),
                getTextFrom(binding.edtConfigUpdateCiclo),
                getTextFrom(binding.edtConfigUpdateCicloReal),
                getTextFrom(binding.edtConfigUpdateEnfriar),
                getTextFrom(binding.edtConfigUpdateTimeOut),
                getTextFrom(binding.edtConfigUpdateMaterial),
                getTextFrom(binding.edtConfigUpdateObservaciones)));

        CheckTemp checkTemp = new CheckTemp(new TextInputLayout[] {
                binding.tilConfigUpdateTemp1, binding.tilConfigUpdateTemp2,
                binding.tilConfigUpdateTemp3, binding.tilConfigUpdateTemp4 });
        checkTemp.checkUpdate(oldFullConfig.getTemperatura(), new Temperatura(
                oldFullConfig.getConfiguracion().getId(),
                getTextFrom(binding.edtConfigUpdateTemp1),
                getTextFrom(binding.edtConfigUpdateTemp2),
                getTextFrom(binding.edtConfigUpdateTemp3),
                getTextFrom(binding.edtConfigUpdateTemp4)));

        CheckInyeccion checkInyeccion = new CheckInyeccion(new TextInputLayout[] {
                binding.tilConfigUpdateInyVel1, binding.tilConfigUpdateInyVel2,
                binding.tilConfigUpdateInyVel3, binding.tilConfigUpdateInyVel4,
                binding.tilConfigUpdateInyVel5, binding.tilConfigUpdateInyPre1,
                binding.tilConfigUpdateInyPre2, binding.tilConfigUpdateInyPre3,
                binding.tilConfigUpdateInyPre4, binding.tilConfigUpdateInyPre5 });
        checkInyeccion.checkUpdate(oldFullConfig.getInyeccion(), new Inyeccion(
                oldFullConfig.getConfiguracion().getId(),
                getTextFrom(binding.edtConfigUpdateInyVel1),
                getTextFrom(binding.edtConfigUpdateInyPre1),
                getTextFrom(binding.edtConfigUpdateInyVel2),
                getTextFrom(binding.edtConfigUpdateInyPre2),
                getTextFrom(binding.edtConfigUpdateInyVel3),
                getTextFrom(binding.edtConfigUpdateInyPre3),
                getTextFrom(binding.edtConfigUpdateInyVel4),
                getTextFrom(binding.edtConfigUpdateInyPre4),
                getTextFrom(binding.edtConfigUpdateInyVel5),
                getTextFrom(binding.edtConfigUpdateInyPre5)));

        CheckReten checkReten = new CheckReten(new TextInputLayout[] {
                binding.tilConfigUpdateRetenVel, binding.tilConfigUpdateRetenPre,
                binding.tilConfigUpdateRetenTmp });
        checkReten.checkUpdate(oldFullConfig.getRentenPresion(), new RetenPresion(
                oldFullConfig.getConfiguracion().getId(),
                getTextFrom(binding.edtConfigUpdateRetenVel),
                getTextFrom(binding.edtConfigUpdateRetenPre),
                getTextFrom(binding.edtConfigUpdateRetenTmp)));

        CheckExpulsor checkExpulsor = new CheckExpulsor(new TextInputLayout[] {
                binding.tilConfigUpdateExpulsorVel1, binding.tilConfigUpdateExpulsorVel2,
                binding.tilConfigUpdateExpulsorPre1, binding.tilConfigUpdateExpulsorPre2,
                binding.tilConfigUpdateExpulsorPos1,  binding.tilConfigUpdateExpulsorPos2 });
        checkExpulsor.checkUpdate(oldFullConfig.getExpulsor(), new Expulsor(
                oldFullConfig.getConfiguracion().getId(),
                getTextFrom(binding.edtConfigUpdateExpulsorVel1),
                getTextFrom(binding.edtConfigUpdateExpulsorPre1),
                getTextFrom(binding.edtConfigUpdateExpulsorPos1),
                getTextFrom(binding.edtConfigUpdateExpulsorVel2),
                getTextFrom(binding.edtConfigUpdateExpulsorPre2),
                getTextFrom(binding.edtConfigUpdateExpulsorPos2)));

        return new CheckFullConfig(checkConfig, checkInyeccion, checkTemp, checkExpulsor, checkReten);
    }
    private void populateForm() {
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

        binding.edtConfigUpdateRetenVel.setText(oldFullConfig.getRentenPresion().getVelocidad());
        binding.edtConfigUpdateRetenPre.setText(oldFullConfig.getRentenPresion().getPresion());
        binding.edtConfigUpdateRetenTmp.setText(oldFullConfig.getRentenPresion().getTiempo());

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

    private String getTextFrom(EditText editText) {
        return (editText == null || editText.getText() == null) ? "" :
                editText.getText().toString().trim();
    }
}