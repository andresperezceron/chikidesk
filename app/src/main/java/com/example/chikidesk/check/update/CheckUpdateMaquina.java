package com.example.chikidesk.check.update;

import com.example.chikidesk.check.Check;
import com.example.chikidesk.databinding.MaquinaUpdateBinding;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class CheckUpdateMaquina extends Check<Maquina, MaquinaUpdateBinding> {
    private final AppCacheViewModel appCache;

    public CheckUpdateMaquina(AppCacheViewModel appCache, MaquinaUpdateBinding binding, Maquina oldEntity) {
        super(binding, oldEntity);
        this.appCache = appCache;
        super.newEntity = newEntityByBinding();
        super.areEqualsToUpdate = areEquals();
        super.entityChecked = checkingNewEntity();
    }

    @Override
    protected Maquina newEntityByBinding() {
        if(areEqualsToUpdate) return null;

        binding.tilMaquinaUpdateNombre.setError(null);
        if(!oldEntity.getNombre().equals(newEntity.getNombre())) {
            if(newEntity.getNombre().isEmpty()) {
                success = false;
                binding.tilMaquinaUpdateNombre.setError("El campo Nombre es obligatorio");
            }else if((appCache.maquinaList.stream().anyMatch(m ->
                    m.getNombre().equals(newEntity.getNombre())))) {
                success = false;
                binding.tilMaquinaUpdateNombre.setError("Ya hay una maquina con este nombre");
            }
        }
        binding.tilMaquinaUpdateRef.setError(null);
        if(!oldEntity.getReferencia().equals(newEntity.getReferencia())) {
            if(newEntity.getReferencia().isEmpty()) {
                success = false;
                binding.tilMaquinaUpdateRef.setError("El campo Referencia es obligatorio");
            }else if((appCache.maquinaList.stream().anyMatch(m ->
                    m.getReferencia().equals(newEntity.getReferencia())))) {
                success = false;
                binding.tilMaquinaUpdateRef.setError("Ya hay un maquina con esta referencia");
            }
        }
        return success ? newEntity : null;
    }

    @Override
    protected Maquina checkingNewEntity() {
        return new Maquina(oldEntity.getId(),
                getTextFrom(binding.edtMaquinaUpdateNombre),
                getTextFrom(binding.edtMaquinaUpdateRef),
                getTextFrom(binding.edtMaquinaUpdateDesc));
    }

    @Override
    protected boolean areEquals() {
        return oldEntity.getNombre().equals(newEntity.getNombre()) &&
                oldEntity.getReferencia().equals(newEntity.getReferencia()) &&
                oldEntity.getDescripcion().equals(newEntity.getDescripcion());
    }
}