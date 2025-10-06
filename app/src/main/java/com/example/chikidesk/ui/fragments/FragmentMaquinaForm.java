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
import com.example.chikidesk.ui.validateforms.CheckMaquina;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentMaquinaForm extends Fragment {
    public FragmentMaquinaForm() {
        super(R.layout.fragment_maquina_form);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EditText edtNombre = view.findViewById(R.id.edtMaquinaFormNombre);
        EditText edtRef = view.findViewById(R.id.edtMaquinaFormRef);
        EditText edtDesc = view.findViewById(R.id.edtMaquinaFormDesc);
        Button btnNew = view.findViewById(R.id.btnMaquinaFormNew);

        TextInputLayout tilNombre = view.findViewById(R.id.tilMaquinaFormNombre);
        TextInputLayout tilRef = view.findViewById(R.id.tilMaquinaFormRef);

        FloatingActionButton fabBack = view.findViewById(R.id.fabMaquinaFormBack);
        FloatingActionButton fabHome = view.findViewById(R.id.fabMaquinaFormHome);

        btnNew.setOnClickListener(v -> {
            MaquinaDao dao = new MaquinaDao(getContext());
            CheckMaquina check = new CheckMaquina(dao, new TextInputLayout[]{tilNombre, tilRef});
            check.checkInsert(new Maquina(0,
                    edtNombre.getText().toString().trim(),
                    edtRef.getText().toString().trim(),
                    edtDesc.getText().toString().trim()));

            if(check.getCheckStatus()) {
                dao.insert(check.getCheckedEntity());
                Toast.makeText(getContext(), "Maquina guardada", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).popBackStack(); // Volver a la lista
            }
            dao.close();
        });

        fabBack.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());
        fabHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_maquinaForm_to_home));
    }
}