package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
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

public class FragmentConfigDetalle extends Fragment {
    public FragmentConfigDetalle() {
        super(R.layout.fragment_config_detalle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Configuracion config = getArguments() != null
                ? getArguments().getParcelable("configuracion") : null;

        MaquinaDao maquinaDao = new MaquinaDao(getContext());
        Maquina maquina = maquinaDao.obtenerPorId(config.getId_maquina());
        maquinaDao.close();

        MoldeDao moldeDao = new MoldeDao(getContext());
        Molde molde = moldeDao.obtenerPorId(config.getId_molde());
        moldeDao.close();

        TemperaturaDao temperaturaDao = new TemperaturaDao(getContext());
        Temperatura temperatura = temperaturaDao.obtenerPorIdConfig(config.getId());
        temperaturaDao.close();

        InyeccionDao inyeccionDao = new InyeccionDao(getContext());
        Inyeccion inyeccion = inyeccionDao.obtenerPorIdConfig(config.getId());
        inyeccionDao.close();

        RetenPresionDao retenDao = new RetenPresionDao(getContext());
        RetenPresion reten = retenDao.obtenerPorIdConfig(config.getId());
        retenDao.close();

        ExpulsorDao expulsorDao = new ExpulsorDao(getContext());
        Expulsor expulsor = expulsorDao.obtenerPorIdConfig(config.getId());
        expulsorDao.close();

        TextView txvNombreMaquina = view.findViewById(R.id.txvNombreMaquina);
        TextView txvNombreMolde = view.findViewById(R.id.txvNombreMolde);
        txvNombreMaquina.setText(maquina.getNombre());
        txvNombreMolde.setText(molde.getNombre());

        TextView txvTemp1 = view.findViewById(R.id.txvTemp1);
        TextView txvTemp2 = view.findViewById(R.id.txvTemp2);
        TextView txvTemp3 = view.findViewById(R.id.txvTemp3);
        TextView txvTemp4 = view.findViewById(R.id.txvTemp4);
        txvTemp1.setText(temperatura.getTemp1());
        txvTemp2.setText(temperatura.getTemp2());
        txvTemp3.setText(temperatura.getTemp3());
        txvTemp4.setText(temperatura.getTemp4());

        TextView txvInyVel1 = view.findViewById(R.id.txvInyVel1);
        TextView txvInyVel2 = view.findViewById(R.id.txvInyVel2);
        TextView txvInyVel3 = view.findViewById(R.id.txvInyVel3);
        TextView txvInyVel4 = view.findViewById(R.id.txvInyVel4);
        TextView txvInyVel5 = view.findViewById(R.id.txvInyVel5);
        txvInyVel1.setText(inyeccion.getVelocidad1());
        txvInyVel2.setText(inyeccion.getVelocidad2());
        txvInyVel3.setText(inyeccion.getVelocidad3());
        txvInyVel4.setText(inyeccion.getVelocidad4());
        txvInyVel5.setText(inyeccion.getVelocidad5());

        TextView txvInyPre1 = view.findViewById(R.id.txvInyPre1);
        TextView txvInyPre2 = view.findViewById(R.id.txvInyPre2);
        TextView txvInyPre3 = view.findViewById(R.id.txvInyPre3);
        TextView txvInyPre4 = view.findViewById(R.id.txvInyPre4);
        TextView txvInyPre5 = view.findViewById(R.id.txvInyPre5);
        txvInyPre1.setText(inyeccion.getPresion1());
        txvInyPre2.setText(inyeccion.getPresion2());
        txvInyPre3.setText(inyeccion.getPresion3());
        txvInyPre4.setText(inyeccion.getPresion4());
        txvInyPre5.setText(inyeccion.getPresion5());

        TextView txvRetenVelocidad = view.findViewById(R.id.txvRetenVelocidad);
        TextView txvRetenPresion = view.findViewById(R.id.txvRetenPresion);
        TextView txvRetenTiempo = view.findViewById(R.id.txvRetenTiempo);
        txvRetenVelocidad.setText(reten.getVelocidad());
        txvRetenPresion.setText(reten.getPresion());
        txvRetenTiempo.setText(reten.getTiempo());

        TextView txvPlastificacion = view.findViewById(R.id.txvPlastificacion);
        txvPlastificacion.setText(config.getPlastificacion());

        TextView txvExpulsorVel1 = view.findViewById(R.id.txvExpulsorVel1);
        TextView txvExpulsorVel2 = view.findViewById(R.id.txvExpulsorVel2);
        TextView txvExpulsorPre1 = view.findViewById(R.id.txvExpulsorPre1);
        TextView txvExpulsorPre2 = view.findViewById(R.id.txvExpulsorPre2);
        TextView txvExpulsorPos1 = view.findViewById(R.id.txvExpulsorPos1);
        TextView txvExpulsorPos2 = view.findViewById(R.id.txvExpulsorPos2);
        txvExpulsorVel1.setText(expulsor.getVelocidad1());
        txvExpulsorVel2.setText(expulsor.getVelocidad2());
        txvExpulsorPre1.setText(expulsor.getPresion1());
        txvExpulsorPre2.setText(expulsor.getPresion2());
        txvExpulsorPos1.setText(expulsor.getPosicion1());
        txvExpulsorPos2.setText(expulsor.getPosicion2());

        TextView txvTiempoCiclo = view.findViewById(R.id.txvTiempoCiclo);
        TextView txvTiempoCicloReal = view.findViewById(R.id.txvTiempoCicloReal);
        txvTiempoCiclo.setText(config.getTiempoCiclo());
        txvTiempoCicloReal.setText(config.getTiempoCicloReal());

        TextView txvTiempoEnfriar = view.findViewById(R.id.txvTiempoEnfriar);
        TextView txvTimeOut = view.findViewById(R.id.txvTimeOut);
        txvTiempoEnfriar.setText(config.getTiempoEnfriar());
        txvTimeOut.setText(config.getTimeOut());

        TextView txvMaterial = view.findViewById(R.id.txvMaterial);
        txvMaterial.setText(config.getMaterial());

        TextView txvObservaciones = view.findViewById(R.id.txvObservaciones);
        txvObservaciones.setText(config.getObservaciones());

        Button btnVisto = view.findViewById(R.id.btnVisto);
        btnVisto.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });
    }
}