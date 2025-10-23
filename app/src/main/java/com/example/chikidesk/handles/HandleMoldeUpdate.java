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
import java.util.stream.Collectors;

public class HandleMoldeUpdate extends HandleFragment<FragmentMoldeUpdate, FragmentMoldeUpdateBinding, Integer> {
    private Molde oldMolde;

    public HandleMoldeUpdate(@NonNull AppCacheViewModel appCache, @NonNull FragmentMoldeUpdate fragment) {
        super(appCache, fragment);
    }

    @Override
    public FragmentMoldeUpdateBinding setBinding(FragmentMoldeUpdateBinding binding) {
        super.binding = binding;
        oldMolde = appCache.moldeList.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElse(null);
        return binding;
    }

    private void update() {
        CheckMoldeUpdate check = new CheckMoldeUpdate(appCache, binding, oldMolde);
        MoldeDao dao = new MoldeDao(getContext());
        if(check.isAreEqualsToUpdate()) {
            Toast.makeText(getContext(),"Sin cambios. Nada que actualizar",
                    Toast.LENGTH_SHORT).show();
            Navigation.findNavController(getView()).popBackStack();
            return;
        }

        if(check.isNotSuccess()) return;

        appCache.moldeList = dao.exeCrudAction(check.getEntity(), MoldeDao.ACTION_UPDATE)
                .stream()
                .sorted(Comparator.comparing(Molde::getNombre, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());

        if(appCache.getStatus()) {
            Navigation.findNavController(getView()).navigate(R.id.action_moldeUpdate_to_moldeList);
            Toast.makeText(getContext(), R.string.tot_upd_molde, Toast.LENGTH_SHORT).show();
        } else assert false;
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
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    @Override
    public void destroyHandle() {
        super.onDestroyHandle();
        this.oldMolde = null;
    }

    @Override
    protected void setKeysByBundle() {
        super.id = getBundle() != null ? getBundle().getInt("id") : 0;
    }
}