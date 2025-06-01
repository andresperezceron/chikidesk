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
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;

public class FragmentMoldeDetalle extends Fragment {
    public FragmentMoldeDetalle() {
        super(R.layout.fragment_molde_detalle);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView txvNombreMolde = view.findViewById(R.id.txvNombreMolde);
        TextView txvReferenciaMolde = view.findViewById(R.id.txvReferenciaMolde);
        TextView txvDescripcionMolde = view.findViewById(R.id.txvDescripcionMolde);
        Button btnVisto = view.findViewById(R.id.btnVisto);

        int idMolde = getArguments() != null ? getArguments().getInt("id_molde") : 0;
        MoldeDao moldeDao = new MoldeDao(requireContext());
        Molde molde = moldeDao.obtenerPorId(idMolde);
        moldeDao.close();

        txvNombreMolde.setText(molde.getNombre());
        txvReferenciaMolde.setText(molde.getReferencia());
        txvDescripcionMolde.setText(molde.getDescripcion());

        btnVisto.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });
    }
}
