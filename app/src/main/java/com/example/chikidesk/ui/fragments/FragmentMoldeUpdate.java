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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMoldeUpdateBinding;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.validateforms.CheckMolde;
import com.example.chikidesk.viewmodel.AppCacheViewModel;
import com.google.android.material.textfield.TextInputLayout;


public class FragmentMoldeUpdate extends Fragment {
    private FragmentMoldeUpdateBinding binding;
    private AppCacheViewModel appCache;
    private int id;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments() != null ? getArguments().getInt("id") : 0; assert id == 0;
        appCache = new ViewModelProvider(requireActivity()).get(AppCacheViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMoldeUpdateBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Molde oldMolde = appCache.getMoldeById(id);

        binding.edtMoldeUpdateNombre.setText(oldMolde.getNombre());
        binding.edtMoldeUpdateRef.setText(oldMolde.getReferencia());
        binding.edtMoldeUpdateDesc.setText(oldMolde.getDescripcion());

        binding.btnMoldeUpdateUpdate.setOnClickListener(v -> {
            MoldeDao dao = new MoldeDao(getContext());
            CheckMolde check = new CheckMolde(dao, new TextInputLayout[]{
                    binding.tilMoldeUpdateNombre, binding.tilMoldeUpdateRef});
            check.checkUpdate(oldMolde, new Molde(oldMolde.getId(),
                    getTextFrom(binding.edtMoldeUpdateNombre),
                    getTextFrom(binding.edtMoldeUpdateRef),
                    getTextFrom(binding.edtMoldeUpdateDesc)));

            if(check.isEqualToUpdate()) {
                Toast.makeText(getContext(),"Sin cambios. Nada que actualizar",
                        Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).popBackStack();
                return;
            }

            if(check.getCheckStatus()) {
                if(appCache.setMoldeList(dao.exeCrudAction(check.getCheckedEntity(), MoldeDao.ACTION_UPDATE))) {
                    Navigation.findNavController(v).navigate(R.id.action_moldeUpdate_to_moldeList);
                    Toast.makeText(getContext(), R.string.tot_upd_molde,
                            Toast.LENGTH_SHORT).show();
                } else Log.d(getString(R.string.tag_dao_error),
                        getString(R.string.log_del_molde));
            }
        });

        binding.fabMoldeUpdateBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeUpdateHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_moldeUpdate_to_home));
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