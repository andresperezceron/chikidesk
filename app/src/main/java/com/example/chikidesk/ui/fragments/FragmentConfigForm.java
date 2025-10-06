package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.chikidesk.R;
import com.example.chikidesk.db.ConfigDao;
import com.example.chikidesk.db.ExpulsorDao;
import com.example.chikidesk.db.InyeccionDao;
import com.example.chikidesk.db.RetenPresionDao;
import com.example.chikidesk.db.TemperaturaDao;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Expulsor;
import com.example.chikidesk.model.Inyeccion;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.model.RetenPresion;
import com.example.chikidesk.model.Temperatura;
import com.example.chikidesk.ui.validateforms.CheckConfig;
import com.example.chikidesk.ui.validateforms.CheckExpulsor;
import com.example.chikidesk.ui.validateforms.CheckInyeccion;
import com.example.chikidesk.ui.validateforms.CheckReten;
import com.example.chikidesk.ui.validateforms.CheckTemp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentConfigForm extends Fragment {
    private EditText edtTemp1, edtTemp2, edtTemp3, edtTemp4;
    private EditText edtInyVel1, edtInyVel2, edtInyVel3, edtInyVel4, edtInyVel5;
    private EditText edtInyPre1, edtInyPre2, edtInyPre3, edtInyPre4, edtInyPre5;
    private EditText edtRetVel, edtRetPre, edtRetTmp;
    private EditText edtExpVel1, edtExpVel2, edtExpPre1, edtExpPre2, edtExpPos1, edtExpPos2;
    private EditText edtPlastificacion, edtTiempoCiclo, edtTiempoCicloReal;
    private EditText edtTiempoEnfriar, edtTimeOup, edtMaterial, edtObservaciones;
    private Configuracion newConfig;
    private Temperatura newTemp;
    private Inyeccion newInyeccion;
    private RetenPresion newReten;
    private Expulsor newExp;

    public FragmentConfigForm() {
        super(R.layout.fragment_config_form);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Maquina maquina = getArguments() != null ? getArguments().getParcelable("maquina") : null;
        Molde molde = getArguments() != null ? getArguments().getParcelable("molde") : null;
        assert maquina != null;
        assert molde != null;

        EditText edtMaquina = view.findViewById(R.id.edtConfigFormMaquina);
        edtMaquina.setText(maquina.getNombre());
        EditText edtMolde = view.findViewById(R.id.edtConfigFormMolde);
        edtMolde.setText(molde.getNombre());

        TextInputLayout tilTemp1 = view.findViewById(R.id.tilConfigFormTemp1);
        TextInputLayout tilTemp2 = view.findViewById(R.id.tilConfigFormTemp2);
        TextInputLayout tilTemp3 = view.findViewById(R.id.tilConfigFormTemp3);
        TextInputLayout tilTemp4 = view.findViewById(R.id.tilConfigFormTemp4);

        TextInputLayout tilInyVel1 = view.findViewById(R.id.tilConfigFormInyVel1);
        TextInputLayout tilInyVel2 = view.findViewById(R.id.tilConfigFormInyVel2);
        TextInputLayout tilInyVel3 = view.findViewById(R.id.tilConfigFormInyVel3);
        TextInputLayout tilInyVel4 = view.findViewById(R.id.tilConfigFormInyVel4);
        TextInputLayout tilInyVel5 = view.findViewById(R.id.tilConfigFormInyVel5);
        TextInputLayout tilInyPre1 = view.findViewById(R.id.tilConfigFormInyPre1);
        TextInputLayout tilInyPre2 = view.findViewById(R.id.tilConfigFormInyPre2);
        TextInputLayout tilInyPre3 = view.findViewById(R.id.tilConfigFormInyPre3);
        TextInputLayout tilInyPre4 = view.findViewById(R.id.tilConfigFormInyPre4);
        TextInputLayout tilInyPre5 = view.findViewById(R.id.tilConfigFormInyPre5);

        TextInputLayout tilRetenVel = view.findViewById(R.id.tilConfigFormRetenVel);
        TextInputLayout tilRetenPre = view.findViewById(R.id.tilConfigFormRetenPre);
        TextInputLayout tilRetenTmp = view.findViewById(R.id.tilConfigFormRetenTmp);

        TextInputLayout tilExpVel1 = view.findViewById(R.id.tilConfigFormExpulsorVel1);
        TextInputLayout tilExpVel2 = view.findViewById(R.id.tilConfigFormExpulsorVel2);
        TextInputLayout tilExpPre1 = view.findViewById(R.id.tilConfigFormExpulsorPre1);
        TextInputLayout tilExpPre2 = view.findViewById(R.id.tilConfigFormExpulsorPre2);
        TextInputLayout tilExpPos1 = view.findViewById(R.id.tilConfigFormExpulsorPos1);
        TextInputLayout tilExpPos2 = view.findViewById(R.id.tilConfigFormExpulsorPos2);

        TextInputLayout tilPlastico = view.findViewById(R.id.tilConfigFormPlastifico);
        TextInputLayout tilCiclo = view.findViewById(R.id.tilConfigFormCiclo);
        TextInputLayout tilCicloReal = view.findViewById(R.id.tilConfigFormCicloReal);
        TextInputLayout tilEnfriar = view.findViewById(R.id.tilConfigFormEnfriar);
        TextInputLayout tilTimeOut = view.findViewById(R.id.tilConfigFormTimeOut);
        TextInputLayout tilMaterial = view.findViewById(R.id.tilConfigFormMaterial);
        TextInputLayout tilObservaciones = view.findViewById(R.id.tilConfigFormObservaciones);

        Button btnReset = view.findViewById(R.id.btnConfigFormReset);
        Button btnNew = view.findViewById(R.id.btnConfigFormNew);
        Button btnOk = view.findViewById(R.id.btnConfigFormOk);
        btnOk.setEnabled(false);
        btnOk.setText("");

        FloatingActionButton fabBack = view.findViewById(R.id.fabConfigFormBack);
        FloatingActionButton fabHome = view.findViewById(R.id.fabConfigFormHome);

        edtTemp1 = view.findViewById(R.id.edtConfigFormTemp1);
        edtTemp2 = view.findViewById(R.id.edtConfigFormTemp2);
        edtTemp3 = view.findViewById(R.id.edtConfigFormTemp3);
        edtTemp4 = view.findViewById(R.id.edtConfigFormTemp4);

        edtInyVel1 = view.findViewById(R.id.edtConfigFormInyVel1);
        edtInyVel2 = view.findViewById(R.id.edtConfigFormInyVel2);
        edtInyVel3 = view.findViewById(R.id.edtConfigFormInyVel3);
        edtInyVel4 = view.findViewById(R.id.edtConfigFormInyVel4);
        edtInyVel5 = view.findViewById(R.id.edtConfigFormInyVel5);
        edtInyPre1 = view.findViewById(R.id.edtConfigFormInyPre1);
        edtInyPre2 = view.findViewById(R.id.edtConfigFormInyPre2);
        edtInyPre3 = view.findViewById(R.id.edtConfigFormInyPre3);
        edtInyPre4 = view.findViewById(R.id.edtConfigFormInyPre4);
        edtInyPre5 = view.findViewById(R.id.edtConfigFormInyPre5);

        edtRetVel = view.findViewById(R.id.edtConfigFormRetenVel);
        edtRetPre = view.findViewById(R.id.edtConfigFormRetenPre);
        edtRetTmp = view.findViewById(R.id.edtConfigFormRetenTmp);

        edtExpVel1 = view.findViewById(R.id.edtConfigFormExpulsorVel1);
        edtExpVel2 = view.findViewById(R.id.edtConfigFormExpulsorVel2);
        edtExpPre1 = view.findViewById(R.id.edtConfigFormExpulsorPre1);
        edtExpPre2 = view.findViewById(R.id.edtConfigFormExpulsorPre2);
        edtExpPos1 = view.findViewById(R.id.edtConfigFormExpulsorPos1);
        edtExpPos2 = view.findViewById(R.id.edtConfigFormExpulsorPos2);

        edtPlastificacion = view.findViewById(R.id.edtConfigFormPlastico);
        edtTiempoCiclo = view.findViewById(R.id.edtConfigFormCiclo);
        edtTiempoCicloReal = view.findViewById(R.id.edtConfigFormCicloReal);
        edtTiempoEnfriar = view.findViewById(R.id.edtConfigFormEnfriar);
        edtTimeOup = view.findViewById(R.id.edtConfigFormTimeOut);
        edtMaterial = view.findViewById(R.id.edtConfigFormMaterial);
        edtObservaciones = view.findViewById(R.id.edtConfigFormObservaciones);

        btnNew.setOnClickListener(v -> {
            CheckTemp checkTemp = new CheckTemp(new TextInputLayout[] {
                    tilTemp1, tilTemp2, tilTemp3, tilTemp4 });
            checkTemp.checkInsert(new Temperatura(0,
                    edtTemp1.getText().toString().trim(),
                    edtTemp2.getText().toString().trim(),
                    edtTemp3.getText().toString().trim(),
                    edtTemp4.getText().toString().trim()));
            newTemp = checkTemp.getCheckedEntity();

            CheckInyeccion checkIny = new CheckInyeccion(new TextInputLayout[] {
                    tilInyVel1, tilInyVel2, tilInyVel3, tilInyVel4, tilInyVel5,
                    tilInyPre1, tilInyPre2, tilInyPre3, tilInyPre4, tilInyPre5 });
            checkIny.checkInsert(new Inyeccion(0,
                    edtInyVel1.getText().toString().trim(), edtInyPre1.getText().toString().trim(),
                    edtInyVel2.getText().toString().trim(), edtInyPre2.getText().toString().trim(),
                    edtInyVel3.getText().toString().trim(), edtInyPre3.getText().toString().trim(),
                    edtInyVel4.getText().toString().trim(), edtInyPre4.getText().toString().trim(),
                    edtInyVel5.getText().toString().trim(), edtInyPre5.getText().toString().trim()));
            newInyeccion = checkIny.getCheckedEntity();

            CheckReten checkReten = new CheckReten(new TextInputLayout[] {
                    tilRetenVel, tilRetenPre, tilRetenTmp });
            checkReten.checkInsert(new RetenPresion(0,
                    edtRetVel.getText().toString().trim(),
                    edtRetPre.getText().toString().trim(),
                    edtRetTmp.getText().toString().trim()));
            newReten = checkReten.getCheckedEntity();

            CheckExpulsor checkExp = new CheckExpulsor(new TextInputLayout[] {
                    tilExpVel1, tilExpVel2, tilExpPre1, tilExpPre2, tilExpPos1, tilExpPos2});
            checkExp.checkInsert(new Expulsor(0,
                    edtExpVel1.getText().toString().trim(),
                    edtExpPre1.getText().toString().trim(),
                    edtExpPos1.getText().toString().trim(),
                    edtExpVel2.getText().toString().trim(),
                    edtExpPre2.getText().toString().trim(),
                    edtExpPos2.getText().toString().trim()));
            newExp = checkExp.getCheckedEntity();

            CheckConfig checkConfig = new CheckConfig(new TextInputLayout[] {tilPlastico, tilCiclo,
                    tilCicloReal, tilEnfriar, tilTimeOut, tilMaterial, tilObservaciones});
            checkConfig.checkInsert(new Configuracion(0, maquina.getId(), molde.getId(),
                    edtPlastificacion.getText().toString().trim(),
                    edtTiempoCiclo.getText().toString().trim(),
                    edtTiempoCicloReal.getText().toString().trim(),
                    edtTiempoEnfriar.getText().toString().trim(),
                    edtTimeOup.getText().toString().trim(),
                    edtMaterial.getText().toString().trim(),
                    edtObservaciones.getText().toString().trim()));
            newConfig = checkConfig.getCheckedEntity();

            if(checkConfig.isEmpty() || checkTemp.isEmpty() || checkIny.isEmpty() ||
               checkReten.isEmpty() || checkExp.isEmpty()) {
                new AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.dialog_empty_fields))
                        .setMessage(R.string.msn_default_value)
                        .setPositiveButton(getString(R.string.msn_ok), null)
                        .show();
                btnOk.setEnabled(true);
                btnOk.setText(R.string.msn_ok);
            } else {
                TemperaturaDao tempDao = new TemperaturaDao(getContext());
                InyeccionDao inyDao = new InyeccionDao(getContext());
                RetenPresionDao retenDao = new RetenPresionDao(getContext());
                ExpulsorDao expulsorDao = new ExpulsorDao(getContext());
                ConfigDao configDao = new ConfigDao(getContext());

                int idNewConfig = (int) configDao.insert(newConfig);
                _setIdNewConfig(idNewConfig);
                tempDao.insert(newTemp);
                inyDao.insert(newInyeccion);
                retenDao.insert(newReten);
                expulsorDao.insert(newExp);
                Navigation.findNavController(v).navigate(R.id.action_configForm_to_configList);
            }
        });

        btnOk.setOnClickListener(v -> {
            ConfigDao configDao = new ConfigDao(getContext());
            TemperaturaDao tempDao = new TemperaturaDao(getContext());
            InyeccionDao inyDao = new InyeccionDao(getContext());
            RetenPresionDao retenDao = new RetenPresionDao(getContext());
            ExpulsorDao expDao = new ExpulsorDao(getContext());

            int idNewConfig = (int) configDao.insert(newConfig);
            _setIdNewConfig(idNewConfig);
            tempDao.insert(newTemp);
            inyDao.insert(newInyeccion);
            retenDao.insert(newReten);
            expDao.insert(newExp);

            Navigation.findNavController(v).navigate(R.id.action_configForm_to_configList);
        });

        btnReset.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            bundle.putParcelable("molde", molde);
            NavHostFragment.findNavController(this).navigate(R.id.action_resetForm, bundle);
        });

        fabBack.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_configForm_to_selectMolde, bundle);
        });

        fabHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_configForm_to_home));
    }

    private void _setIdNewConfig(int id) {
        newTemp.setId(id);
        newInyeccion.setId(id);
        newReten.setId(id);
        newExp.setId(id);
    }
}