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
import com.example.chikidesk.model.Molde;

public class FragmentMoldeShow extends Fragment {
    public FragmentMoldeShow() {
        super(R.layout.fragment_molde_show);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView txvMoldeShowHeader = view.findViewById(R.id.txvMoldeShowHeader);
        //TextView txvReferenciaMolde = view.findViewById(R.id.txvReferenciaMolde);
        //TextView txvDescripcionMolde = view.findViewById(R.id.txvDescripcionMolde);
        //Button btnVisto = view.findViewById(R.id.btnVisto);

        Molde molde = getArguments() != null ? getArguments().getParcelable("molde") : null;

        assert molde != null;
        txvMoldeShowHeader.setText(molde.getNombre());
        //txvReferenciaMolde.setText(molde.getReferencia());
        //txvDescripcionMolde.setText(molde.getDescripcion());

        /*btnVisto.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });*/
    }
}
