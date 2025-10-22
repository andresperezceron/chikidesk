package com.example.chikidesk.checks;

import com.example.chikidesk.databinding.FragmentMoldeFormBinding;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.util.Objects;

public class CheckMoldeForm extends BaseCheck<Molde, FragmentMoldeFormBinding> {

    public CheckMoldeForm(AppCacheViewModel appCache, FragmentMoldeFormBinding binding) {
        super(appCache, binding);
    }

    @Override
    public Molde checkData() {
        Molde newMolde = loadData();
        success = true;
        binding.tilMoldeFormNombre.setError(null);
        if(newMolde.getNombre().isEmpty()) {
            binding.tilMoldeFormNombre.setError("El campo Nombre es obligatorio");
            success = false;
        }
        if(appCache.moldeList.stream().anyMatch(molde ->
                Objects.equals(molde.getNombre(), newMolde.getNombre()))) {
            binding.tilMoldeFormNombre.setError("Nombre ulizado por otro molde");
            success = false;
        }

        binding.tilMoldeFormRef.setError(null);
        if(newMolde.getReferencia().isEmpty()) {
            success = false;
            binding.tilMoldeFormRef.setError("El campo Referencia es obligatorio");
        } else if (appCache.moldeList.stream().anyMatch(molde ->
                molde.getReferencia().equals(newMolde.getReferencia()))) {
            binding.tilMoldeFormRef.setError("Referencia ulizada por otro molde");
            success = false;
        }

        if(newMolde.getDescripcion().isEmpty())
            newMolde.setDescripcion("Sin descripcon");

        return success ? newMolde : null;
    }

    @Override
    protected Molde loadData() {
        return new Molde(0,
                getTextFrom(binding.edtMoldeFormNombre),
                getTextFrom(binding.edtMoldeFormRef),
                getTextFrom(binding.edtMoldeFormDesc));
    }

    @Override
    protected boolean areEquals(Molde oldEntity, Molde newEntity) {
        return false;
    }

    @Override
    public Molde checkData(Molde oldEntity) {
        return null;
    }
}