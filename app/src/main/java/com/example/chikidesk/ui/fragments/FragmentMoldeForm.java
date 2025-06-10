package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.validateforms.CheckMoldeForm;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentMoldeForm extends Fragment {
    public FragmentMoldeForm() {
        super(R.layout.fragment_molde_form);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        EditText editNombre = view.findViewById(R.id.edtNombreMolde);
        EditText editReferencia = view.findViewById(R.id.edtReferenciaMolde);
        EditText editDescripcion = view.findViewById(R.id.edtDescripcionMolde);
        Button btnGuardar = view.findViewById(R.id.btnGuardarMolde);

        TextInputLayout tilNombre = view.findViewById(R.id.tilNombreMolde);
        TextInputLayout tilReferencia = view.findViewById(R.id.tilReferenciaMolde);
        TextInputLayout tilDescripcion = view.findViewById(R.id.tilDescripcionMolde);

        btnGuardar.setOnClickListener(v -> {
            CheckMoldeForm check = new CheckMoldeForm(getContext(),
                    tilNombre, tilReferencia, tilDescripcion,
                    editNombre.getText().toString().trim(),
                    editReferencia.getText().toString().trim(),
                    editDescripcion.getText().toString().trim());

            if(check.getCheckStatus()) {
                MoldeDao dao = new MoldeDao(requireContext());
                dao.insertar(new Molde(0, check.getNombre(), check.getReferencia(),
                        check.getDescripcion()));
                dao.close();
                Toast.makeText(getContext(), "Molde guardado", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).popBackStack(); // Volver a la lista
            }

        });

        FloatingActionButton fabBack = view.findViewById(R.id.fabBack);
        fabBack.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });

        FloatingActionButton fabHome = view.findViewById(R.id.fabHome);
        fabHome.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_moldeForm_to_home);
        });
    }
}