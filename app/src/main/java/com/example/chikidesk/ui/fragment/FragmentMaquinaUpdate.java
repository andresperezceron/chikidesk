package com.example.chikidesk.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        binding.edtMaquinaUpdateNombre.setText(oldMaquina.getNombre());
        binding.edtMaquinaUpdateRef.setText(oldMaquina.getReferencia());
        binding.edtMaquinaUpdateDesc.setText(oldMaquina.getDescripcion());

        binding.btnMaquinaUpdateUpdate.setOnClickListener(v -> {
            MaquinaDao dao = new MaquinaDao(getContext());
            CheckMaquina check = new CheckMaquina(dao, new TextInputLayout[] {
                    binding.tilMaquinaUpdateNombre,
                    binding.tilMaquinaUpdateRef });
            check.checkUpdate(oldMaquina, new Maquina(oldMaquina.getId(),
                    getTextFrom(binding.edtMaquinaUpdateNombre),
                    getTextFrom(binding.edtMaquinaUpdateRef),
                    getTextFrom(binding.edtMaquinaUpdateDesc))
            );
            if(check.getCheckStatus()) {
                /*if(dao.update(check.getCheckedEntity()) > 0) {
                    Toast.makeText(getContext(), R.string.tot_upd_maquina,
                            Toast.LENGTH_SHORT).show();
                } else Log.d(getString(R.string.tag_dao_error),
                        getString(R.string.log_upd_maquina));*/
                Bundle bundle = new Bundle();
                bundle.putParcelable("maquina", check.getCheckedEntity());
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_maquinaUpdate_to_maquinaShow, bundle);
            }
        });

        binding.fabMaquinaUpdateBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMaquinaUpdateHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_maquinaUpdate_to_home));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private String getTextFrom(EditText editText) {
        return (editText == null || editText.getText() == null) ? "" :
                editText.getText().toString().trim();
    }
}