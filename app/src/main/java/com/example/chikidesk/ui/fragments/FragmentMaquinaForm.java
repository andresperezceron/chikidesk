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
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.model.Maquina;

public class FragmentMaquinaForm extends Fragment {
    public FragmentMaquinaForm() {
        super(R.layout.fragment_maquina_form);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EditText edtNombreMaquina = view.findViewById(R.id.edtNombreMaquina);
        EditText edtReferencia = view.findViewById(R.id.edtReferenciaMaquina);
        EditText edtDescripcion = view.findViewById(R.id.edtDescripcionMaquina);
        Button btnGuardar = view.findViewById(R.id.btnGuardarMaquina);

        btnGuardar.setOnClickListener(v -> {
            String nombre = edtNombreMaquina.getText().toString().trim();
            String referencia = edtReferencia.getText().toString().trim();
            String descripcion = edtDescripcion.getText().toString().trim();

            if (nombre.isEmpty() || referencia.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(getContext(), "Todos los campos son obligatorios",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            MaquinaDao dao = new MaquinaDao(requireContext());
            dao.insert(new Maquina(0, nombre, referencia, descripcion));
            dao.close();

            Toast.makeText(getContext(), "Maquina guardada", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).popBackStack(); // Volver a la lista
        });
    }
}