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

public class FragmentMoldeForm extends Fragment {
    public FragmentMoldeForm() {
        super(R.layout.fragment_molde_form);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        EditText editNombre = view.findViewById(R.id.editTextNombre);
        EditText editReferencia = view.findViewById(R.id.editTextReferencia);
        EditText editDescripcion = view.findViewById(R.id.editTextDescripcion);
        Button btnGuardar = view.findViewById(R.id.btnGuardarMolde);

        btnGuardar.setOnClickListener(v -> {
            String nombre = editNombre.getText().toString().trim();
            String referencia = editReferencia.getText().toString().trim();
            String descripcion = editDescripcion.getText().toString().trim();

            if (nombre.isEmpty() || referencia.isEmpty()) {
                Toast.makeText(getContext(), "Todos los campos son obligatorios",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            MoldeDao dao = new MoldeDao(requireContext());
            dao.insertar(new Molde(0, nombre, referencia, descripcion));
            dao.close();

            Toast.makeText(getContext(), "Molde guardado", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).popBackStack(); // Volver a la lista
        });
    }
}