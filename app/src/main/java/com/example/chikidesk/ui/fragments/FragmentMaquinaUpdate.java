package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMaquinaUpdateBinding;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.ui.validateforms.CheckMaquina;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentMaquinaUpdate extends Fragment {
    private FragmentMaquinaUpdateBinding binding;

    public FragmentMaquinaUpdate() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMaquinaUpdateBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Maquina oldMaquina = getArguments() != null ? getArguments().getParcelable("maquina") : null;
        assert oldMaquina != null;

        EditText edtNombre = view.findViewById(R.id.edtMaquinaUpdateNombre);
        EditText edtRef = view.findViewById(R.id.edtMaquinaUpdateRef);
        EditText edtDesc = view.findViewById(R.id.edtMaquinaUpdateDesc);
        Button btnUpdate = view.findViewById(R.id.btnMaquinaUpdateUpdate);
        TextInputLayout tilNombre = view.findViewById(R.id.tilMaquinaUpdateNombre);
        TextInputLayout tilRef = view.findViewById(R.id.tilMaquinaUpdateRef);
        FloatingActionButton fabBack = view.findViewById(R.id.fabMaquinaUpdateBack);
        FloatingActionButton fabHome = view.findViewById(R.id.fabMaquinaUpdateHome);

        binding.edtMaquinaUpdateNombre.setText(oldMaquina.getNombre());
        edtRef.setText(oldMaquina.getReferencia());
        edtDesc.setText(oldMaquina.getDescripcion());

        btnUpdate.setOnClickListener(v -> {
            MaquinaDao dao = new MaquinaDao(getContext());
            CheckMaquina check = new CheckMaquina(dao, new TextInputLayout[]{tilNombre, tilRef});
            check.checkUpdate(oldMaquina, new Maquina(oldMaquina.getId(),
                    edtNombre.getText().toString().trim(),
                    edtRef.getText().toString().trim(),
                    edtDesc.getText().toString().trim()));
            if(check.getCheckStatus()) {
                dao.update(check.getCheckedEntity());
                dao.close();
                Bundle bundle = new Bundle();
                bundle.putParcelable("maquina", check.getCheckedEntity());
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_maquinaUpdate_to_maquinaShow, bundle);
            }
            dao.close();
        });

        fabBack.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());
        fabHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_maquinaUpdate_to_home));
    }
}