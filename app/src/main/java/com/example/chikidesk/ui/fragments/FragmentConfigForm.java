package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

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
import com.example.chikidesk.ui.validateforms.CheckConfigForm;

public class FragmentConfigForm extends Fragment {
    private EditText edtTemp1, edtTemp2, edtTemp3, edtTemp4;
    private EditText edtInyVel1, edtInyVel2, edtInyVel3, edtInyVel4, edtInyVel5;
    private EditText edtInyPre1, edtInyPre2, edtInyPre3, edtInyPre4, edtInyPre5;
    private EditText edtRetVel, edtRetPre, edtRetTiempo;
    private EditText edtExpVel1, edtExpVel2, edtExpPre1, edtExpPre2, edtExpPos1, edtExpPos2;
    private EditText edtPlastificacion, edtTiempoCiclo, edtTiempoCicloReal;
    private EditText edtTiempoEnfriar, edtTimeOup, edtMaterial, edtObservaciones;

    public FragmentConfigForm() {
        super(R.layout.fragment_config_form);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int id_maquina = getArguments() != null ? getArguments().getInt("id_maquina") : 0;
        int id_molde = getArguments() != null ? getArguments().getInt("id_molde") : 0;

        TextView txvNombreMaquina = view.findViewById(R.id.txvNombreMaquina);
        TextView txvNombreMolde = view.findViewById(R.id.txvNombreMolde);

        edtTemp1 = view.findViewById(R.id.edtTemp1);
        edtTemp2 = view.findViewById(R.id.edtTemp2);
        edtTemp3 = view.findViewById(R.id.edtTemp3);
        edtTemp4 = view.findViewById(R.id.edtTemp4);

        edtInyVel1 = view.findViewById(R.id.edtInyVelocidad1);
        edtInyVel2 = view.findViewById(R.id.edtInyVelocidad2);
        edtInyVel3 = view.findViewById(R.id.edtInyVelocidad3);
        edtInyVel4 = view.findViewById(R.id.edtInyVelocidad4);
        edtInyVel5 = view.findViewById(R.id.edtInyVelocidad5);
        edtInyPre1 = view.findViewById(R.id.edtInyPresion1);
        edtInyPre2 = view.findViewById(R.id.edtInyPresion2);
        edtInyPre3 = view.findViewById(R.id.edtInyPresion3);
        edtInyPre4 = view.findViewById(R.id.edtInyPresion4);
        edtInyPre5 = view.findViewById(R.id.edtInyPresion5);

        edtRetVel = view.findViewById(R.id.edtRetenVelocidad);
        edtRetPre = view.findViewById(R.id.edtRetenPresion);
        edtRetTiempo = view.findViewById(R.id.edtRetenTiempo);

        edtExpVel1 = view.findViewById(R.id.edtExpVelocidad1);
        edtExpVel2 = view.findViewById(R.id.edtExpVelocidad2);
        edtExpPre1 = view.findViewById(R.id.edtExpPresion1);
        edtExpPre2 = view.findViewById(R.id.edtExpPresion2);
        edtExpPos1 = view.findViewById(R.id.edtExpPosicion1);
        edtExpPos2 = view.findViewById(R.id.edtExpPosicion2);

        edtPlastificacion = view.findViewById(R.id.etPlastificacion);
        edtTiempoCiclo = view.findViewById(R.id.etTiempoCiclo);
        edtTiempoCicloReal = view.findViewById(R.id.etTiempoCicloReal);
        edtTiempoEnfriar = view.findViewById(R.id.etTiempoEnfriamiento);
        edtTimeOup = view.findViewById(R.id.etTimeout);
        edtMaterial = view.findViewById(R.id.etMaterial);
        edtObservaciones = view.findViewById(R.id.etObservaciones);

        Button btnGuardarConfiguracion = view.findViewById(R.id.btnGuardarConfiguracion);

        MaquinaDao maquinaDao = new MaquinaDao(getContext());
        Maquina maquina = maquinaDao.getById(id_maquina);
        MoldeDao moldeDao = new MoldeDao(getContext());
        Molde molde = moldeDao.getById(id_molde);
        maquinaDao.close();
        moldeDao.close();

        txvNombreMaquina.setText(maquina.getNombre());
        txvNombreMolde.setText(molde.getNombre());

        btnGuardarConfiguracion.setOnClickListener(v -> {
            CheckConfigForm cv = new CheckConfigForm();

            Temperatura nuevaTemperatura = new Temperatura(0,
                    cv.checkEdt(edtTemp1, "temp1"),
                    cv.checkEdt(edtTemp2, "temp2"),
                    cv.checkEdt(edtTemp3, "temp3"),
                    cv.checkEdt(edtTemp4, "temp4"));

            Inyeccion nuevaInyeccion = new Inyeccion(0,
                    cv.checkEdt(edtInyVel1, "iny_velocidad1"),
                    cv.checkEdt(edtInyVel2, "iny_velocidad2"),
                    cv.checkEdt(edtInyVel3, "iny_velocidad3"),
                    cv.checkEdt(edtInyVel4, "iny_velocidad4"),
                    cv.checkEdt(edtInyVel5, "iny_velocidad5"),
                    cv.checkEdt(edtInyPre1, "iny_presion1"),
                    cv.checkEdt(edtInyPre2, "iny_presion2"),
                    cv.checkEdt(edtInyPre3, "iny_presion3"),
                    cv.checkEdt(edtInyPre4, "iny_presion4"),
                    cv.checkEdt(edtInyPre5, "iny_presion5"));

            RetenPresion nuevoRetenPresion = new RetenPresion(0,
                    cv.checkEdt(edtRetVel, "reten_velocidad"),
                    cv.checkEdt(edtRetPre, "reten_presion"),
                    cv.checkEdt(edtRetTiempo, "reten_tiempo"));

            Expulsor nuevoExpulsor = new Expulsor(0,
                    cv.checkEdt(edtExpVel1, "expulsor_velocidad1"),
                    cv.checkEdt(edtExpVel2, "expulsor_velocidad2"),
                    cv.checkEdt(edtExpPre1, "expulsor_presion1"),
                    cv.checkEdt(edtExpPre2, "expulsor_presion2"),
                    cv.checkEdt(edtExpPos1, "expulsor_posicion1"),
                    cv.checkEdt(edtExpPos2, "expulsor_posicion2"));

            Configuracion nuevaConfiguracion = new Configuracion(0, id_maquina, id_molde,
                    cv.checkEdt(edtPlastificacion, "plastificacion"),
                    cv.checkEdt(edtTiempoCiclo, "tiempo_ciclo"),
                    cv.checkEdt(edtTiempoCicloReal, "tiempo_ciclo_real"),
                    cv.checkEdt(edtTiempoEnfriar, "tiempo_enfriar"),
                    cv.checkEdt(edtTimeOup, "time_out"),
                    cv.checkEdt(edtMaterial, "material"),
                    cv.checkEdt(edtObservaciones, "observaciones"));

            if(cv.hayCamposVacios()) {
                new AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.campos_vacios_titulo))
                        .setMessage(String.format(getString(R.string.campos_vacios_mensaje),
                                String.join("\n", cv.listaCamposVacios())))
                        .setPositiveButton(getString(R.string.continuar), (dialog, which) -> {
                            ConfigDao configDao = new ConfigDao(getContext());
                            int id_configuracion = (int) configDao.insert(nuevaConfiguracion);
                            configDao.close();
                            _completarInsertConfig(id_configuracion,
                                    nuevaTemperatura, nuevaInyeccion,
                                    nuevoRetenPresion, nuevoExpulsor);
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            } else {
                ConfigDao configDao = new ConfigDao(getContext());
                int id_configuracion = (int) configDao.insert(nuevaConfiguracion);
                configDao.close();
                _completarInsertConfig(id_configuracion, nuevaTemperatura, nuevaInyeccion,
                        nuevoRetenPresion, nuevoExpulsor);
            }
        });
    }

    private void _completarInsertConfig(int id_configuracion, Temperatura temperatura,
                                        Inyeccion inyeccion, RetenPresion retenPresion,
                                        Expulsor expulsor) {
        TemperaturaDao temperaturaDao = new TemperaturaDao(getContext());
        temperatura.setId(id_configuracion);
        temperaturaDao.insert(temperatura);
        temperaturaDao.close();

        InyeccionDao inyeccionDao = new InyeccionDao(getContext());
        inyeccion.setId(id_configuracion);
        inyeccionDao.insert(inyeccion);
        inyeccionDao.close();

        RetenPresionDao retenDao = new RetenPresionDao(getContext());
        retenPresion.setId(id_configuracion);
        retenDao.insert(retenPresion);
        retenDao.close();

        ExpulsorDao expulsorDao = new ExpulsorDao(getContext());
        expulsor.setId(id_configuracion);
        expulsorDao.insert(expulsor);
        expulsorDao.close();

        _limpiarCampos();

        new AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.configuracion_guardada_titulo))
                .setMessage(getString(R.string.configuracion_guardada_mensaje))
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    private void _limpiarCampos() {
        EditText[] campos = {
                edtTemp1, edtTemp2, edtTemp3, edtTemp4,
                edtInyVel1, edtInyVel2, edtInyVel3, edtInyVel4, edtInyVel5,
                edtInyPre1, edtInyPre2, edtInyPre3, edtInyPre4, edtInyPre5,
                edtRetVel, edtRetPre, edtRetTiempo,
                edtExpVel1, edtExpVel2, edtExpPre1, edtExpPre2, edtExpPos1, edtExpPos2,
                edtPlastificacion, edtTiempoCiclo, edtTiempoCicloReal,
                edtTiempoEnfriar, edtTimeOup, edtMaterial, edtObservaciones
        };

        for(EditText campo : campos)
            campo.setText("");
    }
}