package com.example.chikidesk.check.insert;

import com.example.chikidesk.check.Check;
import com.example.chikidesk.databinding.MoldeFormBinding;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class CheckInsertMolde extends Check<Molde, MoldeFormBinding> {
    private final AppCacheViewModel appCache;

    public CheckInsertMolde(AppCacheViewModel appCache, MoldeFormBinding binding) {
        super(binding);
        this.appCache = appCache;
        newEntity = newEntityByBinding();
        entityChecked = checkingNewEntity();
    }

    @Override
    protected Molde checkingNewEntity() {
        binding.tilMoldeFormNombre.setError(null);
        if(newEntity.getNombre().isEmpty()) {
            binding.tilMoldeFormNombre.setError("El campo Nombre es obligatorio");
            success = false;
        } else if(appCache.moldeList.stream().anyMatch(molde ->
                molde.getNombre().equals(newEntity.getNombre()))) {
            binding.tilMoldeFormNombre.setError("Nombre utilizado por otro molde");
            success = false;
        }

        binding.tilMoldeFormRef.setError(null);
        if(newEntity.getReferencia().isEmpty()) {
            binding.tilMoldeFormRef.setError("El campo Referencia es obligatorio");
            success = false;
        } else if(appCache.moldeList.stream().anyMatch(molde ->
                molde.getReferencia().equals(newEntity.getReferencia()))) {
            binding.tilMoldeFormRef.setError("Referencia utilizada por otro molde");
            success = false;
        }

        if(newEntity.getDescripcion().isEmpty()) {
            newEntity.setDescripcion("Sin descripción");
        }

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