package com.example.chikidesk.check;

import com.example.chikidesk.databinding.MoldeUpdateBinding;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class CheckUpdateMolde extends Check<Molde, MoldeUpdateBinding> {

    public CheckUpdateMolde(AppCacheViewModel appCache, MoldeUpdateBinding binding, Molde oldMolde) {
        super(appCache, binding, oldMolde);
    }

    @Override
    protected Molde chekingNewEntity() {
        success = true;
        binding.tilMoldeUpdateNombre.setError(null);
        if(!oldEntity.getNombre().equals(newEntity.getNombre())) {
            if(newEntity.getNombre().isEmpty()) {
                success = false;
                binding.tilMoldeUpdateNombre.setError("El campo Nombre es obligatorio");
            }else if((appCache.moldeList.stream().anyMatch(molde ->
                    molde.getNombre().equals(newEntity.getNombre())))) {
                success = false;
                binding.tilMoldeUpdateNombre.setError("Ya hay un molde con este nombre");
            }
        }
        binding.tilMoldeUpdateRef.setError(null);
        if(!oldEntity.getReferencia().equals(newEntity.getReferencia())) {
            if(newEntity.getReferencia().isEmpty()) {
                success = false;
                binding.tilMoldeUpdateRef.setError("El campo Referencia es obligatorio");
            }else if((appCache.moldeList.stream().anyMatch(molde ->
                    molde.getReferencia().equals(newEntity.getReferencia())))) {
                success = false;
                binding.tilMoldeUpdateRef.setError("Ya hay un molde con esta referencia");
            }
        }
        return success ? newEntity : null;
    }


    @Override
    protected Molde newEntityByBinding() {
        return new Molde(oldEntity.getId(),
                getTextFrom(binding.edtMoldeUpdateNombre),
                getTextFrom(binding.edtMoldeUpdateRef),
                getTextFrom(binding.edtMoldeUpdateDesc));
    }

    @Override
    protected boolean areEquals() {
        return oldEntity.getNombre().equals(newEntity.getNombre()) &&
                oldEntity.getReferencia().equals(newEntity.getReferencia()) &&
                oldEntity.getDescripcion().equals(newEntity.getDescripcion());
    }
}