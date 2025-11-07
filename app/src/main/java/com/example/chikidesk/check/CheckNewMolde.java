package com.example.chikidesk.check;

import com.example.chikidesk.databinding.MoldeFormBinding;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.util.Objects;

public class CheckNewMolde extends Check<Molde, MoldeFormBinding> {

    public CheckNewMolde(AppCacheViewModel appCache, MoldeFormBinding binding) {
        super(appCache, binding);
    }

    @Override
    protected Molde chekingNewEntity() {
        success = true;
        binding.tilMoldeFormNombre.setError(null);
        if(newEntity.getNombre().isEmpty()) {
            binding.tilMoldeFormNombre.setError("El campo Nombre es obligatorio");
            success = false;
        }
        if(appCache.moldeList.stream().anyMatch(molde ->
                Objects.equals(molde.getNombre(), newEntity.getNombre()))) {
            binding.tilMoldeFormNombre.setError("Nombre ulizado por otro molde");
            success = false;
        }

        binding.tilMoldeFormRef.setError(null);
        if(newEntity.getReferencia().isEmpty()) {
            success = false;
            binding.tilMoldeFormRef.setError("El campo Referencia es obligatorio");
        } else if (appCache.moldeList.stream().anyMatch(molde ->
                molde.getReferencia().equals(newEntity.getReferencia()))) {
            binding.tilMoldeFormRef.setError("Referencia ulizada por otro molde");
            success = false;
        }

        if(newEntity.getDescripcion().isEmpty())
            newEntity.setDescripcion("Sin descripcon");

        return success ? newEntity : null;
    }

    @Override
    protected Molde newEntityByBinding() {
        return new Molde(0,
                getTextFrom(binding.edtMoldeFormNombre),
                getTextFrom(binding.edtMoldeFormRef),
                getTextFrom(binding.edtMoldeFormDesc));
    }

    @Override
    protected boolean areEquals() {
        return false;
    }
}