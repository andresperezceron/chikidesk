package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentConfigShow extends Fragment {
    public FragmentConfigShow() {
        super(R.layout.fragment_config_show);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Configuracion config = getArguments() != null
                ? getArguments().getParcelable("configuracion") : null;
        assert config != null;

        MaquinaDao maquinaDao = new MaquinaDao(getContext());
        Maquina maquina = maquinaDao.getById(config.getId_maquina());
        maquinaDao.close();

        MoldeDao moldeDao = new MoldeDao(getContext());
        Molde molde = moldeDao.getById(config.getId_molde());
        moldeDao.close();

        EditText edtMaquina = view.findViewById(R.id.edtConfigShowMaquina);
        EditText edtMolde = view.findViewById(R.id.edtConfigShowMolde);
        edtMaquina.setText(maquina.getNombre());
        edtMolde.setText(molde.getNombre());

        TemperaturaDao temperaturaDao = new TemperaturaDao(getContext());
        Temperatura temperatura = temperaturaDao.getByConfig(config);
        temperaturaDao.close();
        EditText edtTemp1 = view.findViewById(R.id.edtConfigShowTemp1);
        EditText edtTemp2 = view.findViewById(R.id.edtConfigShowTemp2);
        EditText edtTemp3 = view.findViewById(R.id.edtConfigShowTemp3);
        EditText edtTemp4 = view.findViewById(R.id.edtConfigShowTemp4);
        edtTemp1.setText(temperatura.getTemp1());
        edtTemp2.setText(temperatura.getTemp2());
        edtTemp3.setText(temperatura.getTemp3());
        edtTemp4.setText(temperatura.getTemp4());

        InyeccionDao inyeccionDao = new InyeccionDao(getContext());
        Inyeccion inyeccion = inyeccionDao.getByConfig(config);
        inyeccionDao.close();
        EditText edtInyVel1 = view.findViewById(R.id.edtConfigShowInyVel1);
        EditText edtInyVel2 = view.findViewById(R.id.edtConfigShowInyVel2);
        EditText edtInyVel3 = view.findViewById(R.id.edtConfigShowInyVel3);
        EditText edtInyVel4 = view.findViewById(R.id.edtConfigShowInyVel4);
        EditText edtInyVel5 = view.findViewById(R.id.edtConfigShowInyVel5);
        edtInyVel1.setText(inyeccion.getVelocidad1());
        edtInyVel2.setText(inyeccion.getVelocidad2());
        edtInyVel3.setText(inyeccion.getVelocidad3());
        edtInyVel4.setText(inyeccion.getVelocidad4());
        edtInyVel5.setText(inyeccion.getVelocidad5());
        EditText edtInyPre1 = view.findViewById(R.id.edtConfigShowInyPre1);
        EditText edtInyPre2 = view.findViewById(R.id.edtConfigShowInyPre2);
        EditText edtInyPre3 = view.findViewById(R.id.edtConfigShowInyPre3);
        EditText edtInyPre4 = view.findViewById(R.id.edtConfigShowInyPre4);
        EditText edtInyPre5 = view.findViewById(R.id.edtConfigShowInyPre5);
        edtInyPre1.setText(inyeccion.getPresion1());
        edtInyPre2.setText(inyeccion.getPresion2());
        edtInyPre3.setText(inyeccion.getPresion3());
        edtInyPre4.setText(inyeccion.getPresion4());
        edtInyPre5.setText(inyeccion.getPresion5());

        RetenPresionDao retenDao = new RetenPresionDao(getContext());
        RetenPresion reten = retenDao.getByConfig(config);
        retenDao.close();
        EditText edtRetenVel = view.findViewById(R.id.edtConfigShowRetenVel);
        EditText edtRetenPre = view.findViewById(R.id.edtConfigShowRetenPre);
        EditText edtRetenTmp = view.findViewById(R.id.edtConfigShowRetenTmp);
        edtRetenVel.setText(reten.getVelocidad());
        edtRetenPre.setText(reten.getPresion());
        edtRetenTmp.setText(reten.getTiempo());

        ExpulsorDao expulsorDao = new ExpulsorDao(getContext());
        Expulsor expulsor = expulsorDao.getByConfig(config);
        expulsorDao.close();
        EditText edtExpVel1 = view.findViewById(R.id.edtConfigShowExpulsorVel1);
        EditText edtExpVel2 = view.findViewById(R.id.edtConfigShowExpulsorVel2);
        EditText edtExpPre1 = view.findViewById(R.id.edtConfigShowExpulsorPre1);
        EditText edtExpPre2 = view.findViewById(R.id.edtConfigShowExpulsorPre2);
        EditText edtExpPos1 = view.findViewById(R.id.edtConfigShowExpulsorPos1);
        EditText edtExpPos2 = view.findViewById(R.id.edtConfigShowExpulsorPos2);
        edtExpVel1.setText(expulsor.getVelocidad1());
        edtExpVel2.setText(expulsor.getVelocidad2());
        edtExpPre1.setText(expulsor.getPresion1());
        edtExpPre2.setText(expulsor.getPresion2());
        edtExpPos1.setText(expulsor.getPosicion1());
        edtExpPos2.setText(expulsor.getPosicion2());

        EditText edtPlastic = view.findViewById(R.id.edtConfigShowPlastico);
        EditText edtCiclo = view.findViewById(R.id.edtConfigShowCiclo);
        EditText edtReal = view.findViewById(R.id.edtConfigShowCicloReal);
        EditText edtTimeOut = view.findViewById(R.id.edtConfigShowTimeOut);
        EditText edtEnfriar = view.findViewById(R.id.edtConfigShowEnfriar);
        EditText edtMaterial = view.findViewById(R.id.edtConfigShowMaterial);
        EditText edtObservation = view.findViewById(R.id.edtConfigShowObservaciones);
        edtPlastic.setText(config.getPlastificacion());
        edtCiclo.setText(config.getTiempoCiclo());
        edtReal.setText(config.getTiempoCicloReal());
        edtTimeOut.setText(config.getTimeOut());
        edtEnfriar.setText(config.getTiempoEnfriar());
        edtMaterial.setText(config.getMaterial());
        edtObservation.setText(config.getObservaciones());

        FloatingActionButton fabHome = view.findViewById(R.id.fabConfigShowHome);
        FloatingActionButton fabBack = view.findViewById(R.id.fabConfigShowBack);
        FloatingActionButton fabUpdate = view.findViewById(R.id.fabConfigShowUpdate);
        fabHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_configShow_to_home));
        fabBack.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());
        fabUpdate.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("configuracion", config);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_configShow_to_configUpdate, bundle);
        });
    }
}