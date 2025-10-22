package com.example.chikidesk.checks;

import com.example.chikidesk.databinding.FragmentMoldeUpdateBinding;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class CheckMoldeUpdate extends BaseCheck<Molde, FragmentMoldeUpdateBinding> {
    private Molde newMolde, oldMolde;

    public CheckMoldeUpdate(AppCacheViewModel appCache, FragmentMoldeUpdateBinding binding) {
        super(appCache, binding);
    }

    @Override
    public Molde checkData(Molde oldEntity) {
        oldMolde = oldEntity;
        newMolde = loadData();
        areEqualsToUpdate = areEquals(oldEntity, newMolde);
        if(areEqualsToUpdate) return null;

        success = true;
        binding.tilMoldeUpdateNombre.setError(null);
        if(!oldEntity.getNombre().equals(newMolde.getNombre())) {
            if(newMolde.getNombre().isEmpty()) {
                success = false;
                binding.tilMoldeUpdateNombre.setError("El campo Nombre es obligatorio");
            }else if((appCache.moldeList.stream().anyMatch(molde ->
                    molde.getNombre().equals(newMolde.getNombre())))) {
                success = false;
                binding.tilMoldeUpdateNombre.setError("Ya hay un molde con este nombre");
            }
        }
        binding.tilMoldeUpdateRef.setError(null);
        if(!oldEntity.getReferencia().equals(newMolde.getReferencia())) {
            if(newMolde.getReferencia().isEmpty()) {
                success = false;
                binding.tilMoldeUpdateRef.setError("El campo Referencia es obligatorio");
            }else if((appCache.moldeList.stream().anyMatch(molde ->
                    molde.getReferencia().equals(newMolde.getReferencia())))) {
                success = false;
                binding.tilMoldeUpdateRef.setError("Ya hay un molde con esta referencia");
            }
        }
        return success ? newMolde : null;
    }

    @Override
    public Molde checkData() {
        return null;
    }

    @Override
    protected Molde loadData() {
        return new Molde(oldMolde.getId(),
                getTextFrom(binding.edtMoldeUpdateNombre),
                getTextFrom(binding.edtMoldeUpdateRef),
                getTextFrom(binding.edtMoldeUpdateDesc));
    }

    @Override
    protected boolean areEquals(Molde oldEntity, Molde newEntity) {
        return oldEntity.getNombre().equals(newEntity.getNombre()) &&
                oldEntity.getReferencia().equals(newEntity.getReferencia()) &&
                oldEntity.getDescripcion().equals(newEntity.getDescripcion());
    }
}