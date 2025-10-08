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
import com.example.chikidesk.databinding.FragmentMoldeFormBinding;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.validateforms.CheckMolde;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentMoldeForm extends Fragment {
    private FragmentMoldeFormBinding binding;

    public FragmentMoldeForm() {
        super();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMoldeFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnMoldeFormNew.setOnClickListener(v -> {
            MoldeDao dao = new MoldeDao(getContext());
            CheckMolde check = new CheckMolde(dao, new TextInputLayout[]{
                    binding.tilMoldeFormNombre, binding.tilMoldeFormRef});
            check.checkInsert(new Molde(0,
                    getTextFrom(binding.edtMoldeFormNombre),
                    getTextFrom(binding.edtMoldeFormRef),
                    getTextFrom(binding.edtMoldeFormDesc)));

            if(check.getCheckStatus()) {
                if(dao.insert(check.getCheckedEntity()) > 0) {
                    Toast.makeText(getContext(), R.string.tot_new_molde,
                            Toast.LENGTH_SHORT).show();
                } else Log.d(getString(R.string.tag_dao_error), getString(R.string.log_new_molde));
                Navigation.findNavController(v).popBackStack();
            }
        });

        binding.fabMoldeFormBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeFormHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_moldeForm_to_home));
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