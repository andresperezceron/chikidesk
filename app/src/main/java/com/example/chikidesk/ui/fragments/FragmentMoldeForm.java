package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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

    public FragmentMoldeForm() {}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(binding != null) return binding.getRoot();
        binding = FragmentMoldeFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnMoldeFormNew.setOnClickListener(v -> {
            MoldeDao dao = new MoldeDao(getContext());
            CheckMolde check = createCheck(dao);

            if(check.getCheckStatus()) {
                if(dao.insert(check.getCheckedEntity()) > 0) {
                    Navigation.findNavController(v).navigate(R.id.action_moldeForm_to_moldeList);
                    Toast.makeText(getContext(), R.string.tot_new_molde,
                            Toast.LENGTH_SHORT).show();
                } else new AlertDialog.Builder(requireContext())
                        .setTitle(R.string.alert_title_error)
                        .setMessage(R.string.alert_new_molde)
                        .setPositiveButton(getString(R.string.alert_ok), null)
                        .show();
            }
        });

        binding.fabMoldeFormBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeFormHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    private CheckMolde createCheck(MoldeDao dao) {
        CheckMolde check = new CheckMolde(dao, new TextInputLayout[]{
                binding.tilMoldeFormNombre, binding.tilMoldeFormRef});
        check.checkInsert(new Molde(0,
                getTextFrom(binding.edtMoldeFormNombre),
                getTextFrom(binding.edtMoldeFormRef),
                getTextFrom(binding.edtMoldeFormDesc)));
        return check;
    }

    private String getTextFrom(EditText editText) {
        return (editText == null || editText.getText() == null) ? "" :
                editText.getText().toString().trim();
    }
}