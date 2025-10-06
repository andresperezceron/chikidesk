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

import com.example.chikidesk.R;
import com.example.chikidesk.db.ConfigDao;
import com.example.chikidesk.db.ExpulsorDao;
import com.example.chikidesk.db.InyeccionDao;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.db.MoldeDao;
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


public class FragmentConfigUpdate extends Fragment {
    private EditText edtTemp1, edtTemp2, edtTemp3, edtTemp4;
    private EditText edtInyVel1, edtInyVel2, edtInyVel3, edtInyVel4, edtInyVel5;
    private EditText edtInyPre1, edtInyPre2, edtInyPre3, edtInyPre4, edtInyPre5;
    private EditText edtRetVel, edtRetPre, edtRetTmp;
    private EditText edtExpVel1, edtExpVel2, edtExpPre1, edtExpPre2, edtExpPos1, edtExpPos2;
    private EditText edtPlastificacion, edtCiclo, edtReal;
    private EditText edtEnfriar, edtTimeOut, edtMaterial, edtObservaciones;
    private Temperatura oldTemp;
    private Inyeccion oldIny;
    private RetenPresion oldReten;

    public FragmentConfigUpdate() { super(R.layout.fragment_config_update); }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Configuracion oldConfig = getArguments() != null ?
                getArguments().getParcelable("configuracion") : null;
        assert oldConfig != null;

        MoldeDao moldeDao = new MoldeDao(getContext());
        Molde molde = moldeDao.getById(oldConfig.getId_molde());
        moldeDao.close();

        MaquinaDao maquinaDao = new MaquinaDao(getContext());
        Maquina maquina = maquinaDao.getById(oldConfig.getId_maquina());
        maquinaDao.close();

        EditText edtMaquina = view.findViewById(R.id.edtConfigUpdateMaquina);
        EditText edtMolde = view.findViewById(R.id.edtConfigUpdateMolde);
        edtMaquina.setText(maquina.getNombre());
        edtMolde.setText(molde.getNombre());

        TemperaturaDao temperaturaDao = new TemperaturaDao(getContext());
        oldTemp = temperaturaDao.getByConfig(oldConfig);
        temperaturaDao.close();
        edtTemp1 = view.findViewById(R.id.edtConfigUpdateTemp1);
        edtTemp2 = view.findViewById(R.id.edtConfigUpdateTemp2);
        edtTemp3 = view.findViewById(R.id.edtConfigUpdateTemp3);
        edtTemp4 = view.findViewById(R.id.edtConfigUpdateTemp4);
        edtTemp1.setText(oldTemp.getTemp1());
        edtTemp2.setText(oldTemp.getTemp2());
        edtTemp3.setText(oldTemp.getTemp3());
        edtTemp4.setText(oldTemp.getTemp4());

        InyeccionDao inyeccionDao = new InyeccionDao(getContext());
        oldIny = inyeccionDao.getByConfig(oldConfig);
        inyeccionDao.close();
        edtInyVel1 = view.findViewById(R.id.edtConfigUpdateInyVel1);
        edtInyVel2 = view.findViewById(R.id.edtConfigUpdateInyVel2);
        edtInyVel3 = view.findViewById(R.id.edtConfigUpdateInyVel3);
        edtInyVel4 = view.findViewById(R.id.edtConfigUpdateInyVel4);
        edtInyVel5 = view.findViewById(R.id.edtConfigUpdateInyVel5);
        edtInyVel1.setText(oldIny.getVelocidad1());
        edtInyVel2.setText(oldIny.getVelocidad2());
        edtInyVel3.setText(oldIny.getVelocidad3());
        edtInyVel4.setText(oldIny.getVelocidad4());
        edtInyVel5.setText(oldIny.getVelocidad5());
        edtInyPre1 = view.findViewById(R.id.edtConfigUpdateInyPre1);
        edtInyPre2 = view.findViewById(R.id.edtConfigUpdateInyPre2);
        edtInyPre3 = view.findViewById(R.id.edtConfigUpdateInyPre3);
        edtInyPre4 = view.findViewById(R.id.edtConfigUpdateInyPre4);
        edtInyPre5 = view.findViewById(R.id.edtConfigUpdateInyPre5);
        edtInyPre1.setText(oldIny.getPresion1());
        edtInyPre2.setText(oldIny.getPresion2());
        edtInyPre3.setText(oldIny.getPresion3());
        edtInyPre4.setText(oldIny.getPresion4());
        edtInyPre5.setText(oldIny.getPresion5());

        RetenPresionDao retenPresionDao = new RetenPresionDao(getContext());
        oldReten = retenPresionDao.getByConfig(oldConfig);
        retenPresionDao.close();
        edtRetVel = view.findViewById(R.id.edtConfigUpdateRetenVel);
        edtRetPre = view.findViewById(R.id.edtConfigUpdateRetenPre);
        edtRetTmp = view.findViewById(R.id.edtConfigUpdateRetenTmp);
        edtRetVel.setText(oldReten.getVelocidad());
        edtRetPre.setText(oldReten.getPresion());
        edtRetTmp.setText(oldReten.getTiempo());

        ExpulsorDao expulsorDao = new ExpulsorDao(getContext());
        Expulsor oldExp = expulsorDao.getByConfig(oldConfig);
        expulsorDao.close();
        edtExpVel1 = view.findViewById(R.id.edtConfigUpdateExpulsorVel1);
        edtExpVel2 = view.findViewById(R.id.edtConfigUpdateExpulsorVel2);
        edtExpPre1 = view.findViewById(R.id.edtConfigUpdateExpulsorPre1);
        edtExpPre2 = view.findViewById(R.id.edtConfigUpdateExpulsorPre2);
        edtExpPos1 = view.findViewById(R.id.edtConfigUpdateExpulsorPos1);
        edtExpPos2 = view.findViewById(R.id.edtConfigUpdateExpulsorPos2);
        edtExpVel1.setText(oldExp.getVelocidad1());
        edtExpVel2.setText(oldExp.getVelocidad2());
        edtExpPre1.setText(oldExp.getPresion1());
        edtExpPre2.setText(oldExp.getPresion2());
        edtExpPos1.setText(oldExp.getPosicion1());
        edtExpPos2.setText(oldExp.getPosicion2());

        edtPlastificacion = view.findViewById(R.id.edtConfigUpdatePlastico);
        edtCiclo = view.findViewById(R.id.edtConfigUpdateCiclo);
        edtReal = view.findViewById(R.id.edtConfigUpdateCicloReal);
        edtTimeOut = view.findViewById(R.id.edtConfigUpdateTimeOut);
        edtEnfriar = view.findViewById(R.id.edtConfigUpdateEnfriar);
        edtMaterial = view.findViewById(R.id.edtConfigUpdateMaterial);
        edtObservaciones = view.findViewById(R.id.edtConfigUpdateObservaciones);
        edtPlastificacion.setText(oldConfig.getPlastificacion());
        edtCiclo.setText(oldConfig.getTiempoCiclo());
        edtReal.setText(oldConfig.getTiempoCicloReal());
        edtTimeOut.setText(oldConfig.getTimeOut());
        edtEnfriar.setText(oldConfig.getTiempoEnfriar());
        edtMaterial.setText(oldConfig.getMaterial());
        edtObservaciones.setText(oldConfig.getObservaciones());

        TextInputLayout tilTemp1 = view.findViewById(R.id.tilConfigUpdateTemp1);
        TextInputLayout tilTemp2 = view.findViewById(R.id.tilConfigUpdateTemp2);
        TextInputLayout tilTemp3 = view.findViewById(R.id.tilConfigUpdateTemp3);
        TextInputLayout tilTemp4 = view.findViewById(R.id.tilConfigUpdateTemp4);

        TextInputLayout tilInyVel1 = view.findViewById(R.id.tilConfigUpdateInyVel1);
        TextInputLayout tilInyVel2 = view.findViewById(R.id.tilConfigUpdateInyVel2);
        TextInputLayout tilInyVel3 = view.findViewById(R.id.tilConfigUpdateInyVel3);
        TextInputLayout tilInyVel4 = view.findViewById(R.id.tilConfigUpdateInyVel4);
        TextInputLayout tilInyVel5 = view.findViewById(R.id.tilConfigUpdateInyVel5);
        TextInputLayout tilInyPre1 = view.findViewById(R.id.tilConfigUpdateInyPre1);
        TextInputLayout tilInyPre2 = view.findViewById(R.id.tilConfigUpdateInyPre2);
        TextInputLayout tilInyPre3 = view.findViewById(R.id.tilConfigUpdateInyPre3);
        TextInputLayout tilInyPre4 = view.findViewById(R.id.tilConfigUpdateInyPre4);
        TextInputLayout tilInyPre5 = view.findViewById(R.id.tilConfigUpdateInyPre5);

        TextInputLayout tilRetenVel = view.findViewById(R.id.tilConfigUpdateRetenVel);
        TextInputLayout tilRetenPre = view.findViewById(R.id.tilConfigUpdateRetenPre);
        TextInputLayout tilRetenTmp = view.findViewById(R.id.tilConfigUpdateRetenTmp);

        TextInputLayout tilExpVel1 = view.findViewById(R.id.tilConfigUpdateExpulsorVel1);
        TextInputLayout tilExpVel2 = view.findViewById(R.id.tilConfigUpdateExpulsorVel2);
        TextInputLayout tilExpPre1 = view.findViewById(R.id.tilConfigUpdateExpulsorPre1);
        TextInputLayout tilExpPre2 = view.findViewById(R.id.tilConfigUpdateExpulsorPre2);
        TextInputLayout tilExpPos1 = view.findViewById(R.id.tilConfigUpdateExpulsorPos1);
        TextInputLayout tilExpPos2 = view.findViewById(R.id.tilConfigUpdateExpulsorPos2);

        TextInputLayout tilPlastificacion = view.findViewById(R.id.tilConfigUpdatePlastifico);
        TextInputLayout tilCiclo = view.findViewById(R.id.tilConfigUpdateCiclo);
        TextInputLayout tilCicloReal = view.findViewById(R.id.tilConfigUpdateCicloReal);
        TextInputLayout tilEnfriar = view.findViewById(R.id.tilConfigUpdateEnfriar);
        TextInputLayout tilTimeOut = view.findViewById(R.id.tilConfigUpdateTimeOut);
        TextInputLayout tilMaterial = view.findViewById(R.id.tilConfigUpdateMaterial);
        TextInputLayout tilObservaciones = view.findViewById(R.id.tilConfigUpdateObservaciones);


        Button btnUpdate = view.findViewById(R.id.btnConfigUpdateUpdate);
        FloatingActionButton fabHome = view.findViewById(R.id.fabConfigUpdateHome);

        btnUpdate.setOnClickListener(v -> {
            TemperaturaDao tempDao = new TemperaturaDao(getContext());
            CheckTemp checkTemp = new CheckTemp(new TextInputLayout[] {
                    tilTemp1, tilTemp2, tilTemp3, tilTemp4 });
            checkTemp.checkUpdate(oldTemp, new Temperatura(0,
                    edtTemp1.getText().toString().trim(),
                    edtTemp2.getText().toString().trim(),
                    edtTemp3.getText().toString().trim(),
                    edtTemp4.getText().toString().trim()));

            InyeccionDao inyDao = new InyeccionDao(getContext());
            CheckInyeccion checkIny = new CheckInyeccion(new TextInputLayout[] {
                    tilInyVel1, tilInyVel2, tilInyVel3, tilInyVel4, tilInyVel5,
                    tilInyPre1, tilInyPre2, tilInyPre3, tilInyPre4, tilInyPre5 });
            checkIny.checkUpdate(oldIny, new Inyeccion(0,
                    edtInyVel1.getText().toString().trim(), edtInyPre1.getText().toString().trim(),
                    edtInyVel2.getText().toString().trim(), edtInyPre2.getText().toString().trim(),
                    edtInyVel3.getText().toString().trim(), edtInyPre3.getText().toString().trim(),
                    edtInyVel4.getText().toString().trim(), edtInyPre4.getText().toString().trim(),
                    edtInyVel5.getText().toString().trim(), edtInyPre5.getText().toString().trim()));

            RetenPresionDao retenDao = new RetenPresionDao(getContext());
            CheckReten checkReten = new CheckReten(new TextInputLayout[] {
                    tilRetenVel, tilRetenPre, tilRetenTmp });
            checkReten.checkUpdate(oldReten, new RetenPresion(0,
                    edtRetVel.getText().toString().trim(),
                    edtRetPre.getText().toString().trim(),
                    edtRetTmp.getText().toString().trim()));

            ExpulsorDao expDao = new ExpulsorDao(getContext());
            CheckExpulsor checkExp = new CheckExpulsor(new TextInputLayout[] {
                    tilExpVel1, tilExpVel2, tilExpPre1, tilExpPre2, tilExpPos1, tilExpPos2});
            checkExp.checkUpdate(oldExp, new Expulsor(0,
                    edtExpVel1.getText().toString().trim(),
                    edtExpPre1.getText().toString().trim(),
                    edtExpPos1.getText().toString().trim(),
                    edtExpVel2.getText().toString().trim(),
                    edtExpPre2.getText().toString().trim(),
                    edtExpPos2.getText().toString().trim()));

            ConfigDao configDao = new ConfigDao(getContext());
            CheckConfig checkConfig = new CheckConfig(new TextInputLayout[] {
                    tilPlastificacion, tilCiclo, tilCicloReal, tilEnfriar, tilTimeOut,
                    tilMaterial, tilObservaciones });
            checkConfig.checkUpdate(oldConfig, new Configuracion(0,
                    maquina.getId(), molde.getId(),
                    edtPlastificacion.getText().toString().trim(),
                    edtCiclo.getText().toString().trim(),
                    edtReal.getText().toString().trim(),
                    edtEnfriar.getText().toString().trim(),
                    edtTimeOut.getText().toString().trim(),
                    edtMaterial.getText().toString().trim(),
                    edtObservaciones.getText().toString().trim()));

            if(checkTemp.isEmpty() || checkIny.isEmpty() || checkReten.isEmpty() ||
               checkExp.isEmpty() || checkConfig.isEmpty()) {
                new AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.dialog_empty_fields))
                        .setMessage(R.string.msn_update_empty)
                        .setPositiveButton(getString(R.string.msn_ok), null)
                        .show();
            } else {
                if(checkConfig.getCheckStatus())
                    configDao.update(checkConfig.getCheckedEntity());
                if(checkTemp.getCheckStatus())
                    tempDao.update(checkTemp.getCheckedEntity());
                if(checkIny.getCheckStatus())
                    inyDao.update(checkIny.getCheckedEntity());
                if(checkReten.getCheckStatus())
                    retenDao.update(checkReten.getCheckedEntity());
                if(checkExp.getCheckStatus())
                    expDao.update(checkExp.getCheckedEntity());
            }

            tempDao.close();
            inyDao.close();
            retenDao.close();
            expDao.close();
            configDao.close();
        });

        fabHome.setOnClickListener(v ->
            Navigation.findNavController(v).navigate(R.id.action_configUpdate_to_home)
        );
    }
}