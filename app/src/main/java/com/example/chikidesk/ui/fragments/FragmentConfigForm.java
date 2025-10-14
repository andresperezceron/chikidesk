package com.example.chikidesk.ui.fragments;

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
import com.example.chikidesk.databinding.FragmentConfigFormBinding;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Expulsor;
import com.example.chikidesk.model.Inyeccion;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.model.Molde;
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

public class FragmentConfigForm extends Fragment {
    private FragmentConfigFormBinding binding;
    private CheckFullConfig checkFullConfig;
    private ConfigRepository configRepository;

    public FragmentConfigForm() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Es un buen lugar para inicializar objetos que no dependen de la vista
        configRepository = new ConfigRepository(requireContext());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentConfigFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Maquina maquina = getArguments() != null ? getArguments().getParcelable("maquina") : null;
        Molde molde = getArguments() != null ? getArguments().getParcelable("molde") : null;
        assert maquina != null;
        assert molde != null;

        binding.edtConfigFormMaquina.setText(maquina.getNombre());
        binding.edtConfigFormMolde.setText(molde.getNombre());
        binding.btnConfigFormContinue.setEnabled(false);
        binding.btnConfigFormContinue.setText("");

        binding.btnConfigFormNew.setOnClickListener(v -> {
            checkFullConfig = createCheckFullConfig(maquina, molde);

            if(checkFullConfig.isEmpty()) {
                new AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.dialog_empty_fields))
                        .setMessage(R.string.msn_default_value)
                        .setPositiveButton(getString(R.string.msn_ok), null)
                        .show();
                binding.btnConfigFormContinue.setEnabled(true);
                binding.btnConfigFormContinue.setText(R.string.btn_text_continue);
            } else saveConfiguration(v);

        });

        binding.btnConfigFormContinue.setOnClickListener(this::saveConfiguration);

        binding.btnConfigFormReset.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            bundle.putParcelable("molde", molde);
            NavHostFragment.findNavController(this).navigate(R.id.action_resetForm, bundle);
        });

        binding.fabConfigFormBack.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_configForm_to_selectMolde, bundle);
        });

        binding.fabConfigFormHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_configForm_to_home));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Llama al repositorio para guardar la configuración completa en una transacción segura.
     * Navega a la siguiente pantalla si la operación tiene éxito.
     */
    private void saveConfiguration(View view) {
        // ¡La magia está aquí! Una sola llamada al repositorio.
        boolean success = configRepository.insertFullConfig(
                checkFullConfig.getCheckConfig().getCheckedEntity(),
                checkFullConfig.getCheckTemp().getCheckedEntity(),
                checkFullConfig.getCheckInyeccion().getCheckedEntity(),
                checkFullConfig.getCheckReten().getCheckedEntity(),
                checkFullConfig.getCheckExpulsor().getCheckedEntity());

        if(success) {
            Toast.makeText(getContext(), R.string.tot_new_config, Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigate(R.id.action_configForm_to_configList);
        } else {
            // Si algo falla, el repositorio ya ha hecho rollback. Solo informamos al usuario.
            new AlertDialog.Builder(requireContext())
                    .setTitle("Error")
                    .setMessage("No se pudo guardar la configuración. Por favor, inténtelo de nuevo.")
                    .setPositiveButton(getString(R.string.msn_ok), null)
                    .show();
        }
    }

    private CheckFullConfig createCheckFullConfig(Maquina maquina, Molde molde) {
        CheckConfig checkConfig = new CheckConfig(new TextInputLayout[] {
                binding.tilConfigFormPlastifico, binding.tilConfigFormCiclo,
                binding.tilConfigFormCicloReal, binding.tilConfigFormEnfriar,
                binding.tilConfigFormTimeOut, binding.tilConfigFormMaterial,
                binding.tilConfigFormObservaciones});
        checkConfig.checkInsert(new Configuracion(0, maquina.getId(), molde.getId(),
                getTextFrom(binding.edtConfigFormPlastico),
                getTextFrom(binding.edtConfigFormCiclo),
                getTextFrom(binding.edtConfigFormCicloReal),
                getTextFrom(binding.edtConfigFormEnfriar),
                getTextFrom(binding.edtConfigFormTimeOut),
                getTextFrom(binding.edtConfigFormMaterial),
                getTextFrom(binding.edtConfigFormObservaciones)));

        CheckTemp checkTemp = new CheckTemp(new TextInputLayout[] {
                binding.tilConfigFormTemp1, binding.tilConfigFormTemp2,
                binding.tilConfigFormTemp3, binding.tilConfigFormTemp4 });
        checkTemp.checkInsert(new Temperatura(0,
                getTextFrom(binding.edtConfigFormTemp1),
                getTextFrom(binding.edtConfigFormTemp2),
                getTextFrom(binding.edtConfigFormTemp3),
                getTextFrom(binding.edtConfigFormTemp4)));

        CheckInyeccion checkIny = new CheckInyeccion(new TextInputLayout[] {
                binding.tilConfigFormInyVel1, binding.tilConfigFormInyVel2,
                binding.tilConfigFormInyVel3, binding.tilConfigFormInyVel4,
                binding.tilConfigFormInyVel5, binding.tilConfigFormInyPre1,
                binding.tilConfigFormInyPre2, binding.tilConfigFormInyPre3,
                binding.tilConfigFormInyPre4, binding.tilConfigFormInyPre5 });
        checkIny.checkInsert(new Inyeccion(0,
                getTextFrom(binding.edtConfigFormInyVel1),
                getTextFrom(binding.edtConfigFormInyPre1),
                getTextFrom(binding.edtConfigFormInyVel2),
                getTextFrom(binding.edtConfigFormInyPre2),
                getTextFrom(binding.edtConfigFormInyVel3),
                getTextFrom(binding.edtConfigFormInyPre3),
                getTextFrom(binding.edtConfigFormInyVel4),
                getTextFrom(binding.edtConfigFormInyPre4),
                getTextFrom(binding.edtConfigFormInyVel5),
                getTextFrom(binding.edtConfigFormInyPre5)));

        CheckReten checkReten = new CheckReten(new TextInputLayout[] {
                binding.tilConfigFormRetenVel,
                binding.tilConfigFormRetenPre,
                binding.tilConfigFormRetenTmp });
        checkReten.checkInsert(new RetenPresion(0,
                getTextFrom(binding.edtConfigFormRetenVel),
                getTextFrom(binding.edtConfigFormRetenPre),
                getTextFrom(binding.edtConfigFormRetenTmp)));

        CheckExpulsor checkExp = new CheckExpulsor(new TextInputLayout[] {
                binding.tilConfigFormExpulsorVel1, binding.tilConfigFormExpulsorVel2,
                binding.tilConfigFormExpulsorPre1, binding.tilConfigFormExpulsorPre2,
                binding.tilConfigFormExpulsorPos1, binding.tilConfigFormExpulsorPos2 });
        checkExp.checkInsert(new Expulsor(0,
                getTextFrom(binding.edtConfigFormExpulsorVel1),
                getTextFrom(binding.edtConfigFormExpulsorPre1),
                getTextFrom(binding.edtConfigFormExpulsorPos1),
                getTextFrom(binding.edtConfigFormExpulsorVel2),
                getTextFrom(binding.edtConfigFormExpulsorPre2),
                getTextFrom(binding.edtConfigFormExpulsorPos2)));

        return new CheckFullConfig(checkConfig, checkIny, checkTemp, checkExp, checkReten);
    }

    private String getTextFrom(EditText editText) {
        return (editText == null || editText.getText() == null) ? "" :
                editText.getText().toString().trim();
    }
}