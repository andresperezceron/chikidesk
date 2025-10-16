package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentConfigShowBinding;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.FullConfig;
import com.example.chikidesk.repository.ConfigRepository;

public class FragmentConfigShow extends Fragment {
    private FragmentConfigShowBinding binding;
    private ConfigRepository configRepository;

    public FragmentConfigShow() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configRepository = new ConfigRepository(requireContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentConfigShowBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Configuracion config = getArguments() != null
                ? getArguments().getParcelable("configuracion") : null;
        assert config != null;

        populateForm(configRepository.createFullConfigByConfig(config));

        binding.fabConfigShowUpdate.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("configuracion", config);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_configShow_to_configUpdate, bundle);
        });

        binding.fabConfigShowHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_configShow_to_home));
        binding.fabConfigShowBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void populateForm(FullConfig fullConfig) {
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

        binding.edtConfigShowRetenVel.setText(fullConfig.getRentenPresion().getVelocidad());
        binding.edtConfigShowRetenPre.setText(fullConfig.getRentenPresion().getPresion());
        binding.edtConfigShowRetenTmp.setText(fullConfig.getRentenPresion().getTiempo());

        binding.edtConfigShowExpulsorVel1.setText(fullConfig.getExpulsor().getVelocidad1());
        binding.edtConfigShowExpulsorVel2.setText(fullConfig.getExpulsor().getVelocidad2());
        binding.edtConfigShowExpulsorPre1.setText(fullConfig.getExpulsor().getPresion1());
        binding.edtConfigShowExpulsorPre2.setText(fullConfig.getExpulsor().getPresion2());
        binding.edtConfigShowExpulsorPos1.setText(fullConfig.getExpulsor().getPosicion1());
        binding.edtConfigShowExpulsorPos2.setText(fullConfig.getExpulsor().getPosicion2());
    }
}