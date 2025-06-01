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
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.model.Maquina;


public class FragmentMaquinaDetalle extends Fragment {
    public FragmentMaquinaDetalle() {
        super(R.layout.fragment_maquina_detalle);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView txvNombreMaquina = view.findViewById(R.id.txvNombreMaquina);
        TextView txvReferenciaMaquina = view.findViewById(R.id.txvReferenciaMaquina);
        TextView txvDescripcionMaquina = view.findViewById(R.id.txvDescripcionMaquina);
        Button btnVisto = view.findViewById(R.id.btnVisto);

        int idMaquina = getArguments() != null ? getArguments().getInt("id_maquina") : 0;
        MaquinaDao maquinaDao = new MaquinaDao(requireContext());
        Maquina maquina = maquinaDao.obtenerPorId(idMaquina);
        maquinaDao.close();

        txvNombreMaquina.setText(maquina.getNombre());
        txvReferenciaMaquina.setText(maquina.getReferencia());
        txvDescripcionMaquina.setText(maquina.getDescripcion());

        btnVisto.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });
    }
}