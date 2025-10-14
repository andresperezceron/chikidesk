package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMaquinaFormBinding;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.ui.validateforms.CheckMaquina;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentMaquinaForm extends Fragment {
    private FragmentMaquinaFormBinding binding;

    public FragmentMaquinaForm() {}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMaquinaFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnMaquinaFormNew.setOnClickListener(v -> {
            MaquinaDao dao = new MaquinaDao(getContext());
            CheckMaquina check = new CheckMaquina(dao, new TextInputLayout[] {
                   binding.tilMaquinaFormNombre, binding.tilMaquinaFormRef });
            check.checkInsert(new Maquina(0,
                    getTextFrom(binding.edtMaquinaFormNombre),
                    getTextFrom(binding.edtMaquinaFormRef),
                    getTextFrom(binding.edtMaquinaFormDesc)));

            if(check.getCheckStatus()) {
                if(dao.insert(check.getCheckedEntity()) > 0) {
                    Toast.makeText(getContext(), R.string.tot_new_maquina,
                            Toast.LENGTH_SHORT).show();
                } else Log.d(getString(R.string.tag_dao_error), getString(R.string.log_new_maquina));
                Navigation.findNavController(v).popBackStack();
            }
        });

        binding.fabMaquinaFormBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMaquinaFormHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_maquinaForm_to_home));
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