package com.example.chikidesk.handles;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.checks.CheckMoldeUpdate;
import com.example.chikidesk.databinding.FragmentMoldeUpdateBinding;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.fragments.FragmentMoldeUpdate;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HandleMoldeUpdate extends HandleFragment<FragmentMoldeUpdate, FragmentMoldeUpdateBinding, Integer> {
    private Molde oldMolde;
    private CheckMoldeUpdate check;
    private MoldeDao dao;

    public HandleMoldeUpdate(@NonNull AppCacheViewModel appCache, @NonNull FragmentMoldeUpdate fragment) {
        super(appCache, fragment);
    }

    @Override
    public FragmentMoldeUpdateBinding setBinding(FragmentMoldeUpdateBinding binding) {
        super.binding = binding;
        check = new CheckMoldeUpdate(appCache, binding);
        dao = new MoldeDao(getContext());
        oldMolde = appCache.moldeList.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElse(null);
        return binding;
    }

    private void update() {
        Molde newMolde = check.checkData(oldMolde);
        if(newMolde == null) return;
        if(check.isAreEqualsToUpdate()) {
            Toast.makeText(getContext(),"Sin cambios. Nada que actualizar",
                    Toast.LENGTH_SHORT).show();
            Navigation.findNavController(getView()).popBackStack();
            return;
        }
        List<Molde> newList = dao.exeCrudAction(newMolde, MoldeDao.ACTION_UPDATE);
        if(newList != null) {
            appCache.moldeList = newList.stream()
                    .sorted(Comparator.comparing(Molde::getNombre, String.CASE_INSENSITIVE_ORDER))
                    .collect(Collectors.toList());
            Navigation.findNavController(getView()).navigate(R.id.action_moldeUpdate_to_moldeList);
            Toast.makeText(getContext(), R.string.tot_upd_molde,
                    Toast.LENGTH_SHORT).show();
        } else Toast.makeText(getContext(),"Error en update", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupListener() {
        binding.btnMoldeUpdateUpdate.setOnClickListener(v -> update());
    }

    @Override
    public void populateForm() {
        binding.edtMoldeUpdateNombre.setText(oldMolde.getNombre());
        binding.edtMoldeUpdateRef.setText(oldMolde.getReferencia());
        binding.edtMoldeUpdateDesc.setText(oldMolde.getDescripcion());
    }

    @Override
    public void setupNavigationButtons() {
        binding.fabMoldeUpdateBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeUpdateHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_moldeUpdate_to_home));
    }

    @Override
    public void destroyHandle() {
        super.onDestroyHandle();
        this.oldMolde = null;
        this.check = null;
        this.dao = null;
    }

    @Override
    protected void setKeysByBundle() {
        super.id = getBundle() != null ? getBundle().getInt("id_molde") : 0;
        super.idAux1 = getBundle() != null ? getBundle().getInt("id_maquina") : 0;
    }
}