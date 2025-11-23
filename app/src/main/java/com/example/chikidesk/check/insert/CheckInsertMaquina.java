package com.example.chikidesk.check.insert;

import com.example.chikidesk.check.Check;
import com.example.chikidesk.databinding.MaquinaFormBinding;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class CheckInsertMaquina extends Check<Maquina, MaquinaFormBinding> {
    private final AppCacheViewModel appCache;
    public CheckInsertMaquina(AppCacheViewModel appCache, MaquinaFormBinding binding) {
        super(binding);
        this.appCache = appCache;
        newEntity = newEntityByBinding();
        entityChecked = checkingNewEntity();
    }

    @Override
    protected Maquina newEntityByBinding() {
        return new Maquina(0,
                getTextFrom(binding.edtMaquinaFormNombre),
                getTextFrom(binding.edtMaquinaFormRef),
                getTextFrom(binding.edtMaquinaFormDesc));
    }

    @Override
    protected Maquina checkingNewEntity() {
        binding.tilMaquinaFormNombre.setError(null);
        if(newEntity.getNombre().isEmpty()) {
            binding.tilMaquinaFormNombre.setError("El campo Nombre es obligatorio");
            success = false;
        } else if(appCache.maquinaList.stream().anyMatch(m ->
                m.getNombre().equals(newEntity.getNombre()))) {
            binding.tilMaquinaFormNombre.setError("Nombre utilizado por otra máquina");
            success = false;
        }

        binding.tilMaquinaFormRef.setError(null);
        if(newEntity.getReferencia().isEmpty()) {
            binding.tilMaquinaFormRef.setError("El campo Referencia es obligatorio");
            success = false;
        } else if(appCache.maquinaList.stream().anyMatch(m ->
                m.getReferencia().equals(newEntity.getReferencia()))) {
            binding.tilMaquinaFormRef.setError("Referencia utilizada por otra máquina");
            success = false;
        }

        if(newEntity.getDescripcion().isEmpty()) {
            newEntity.setDescripcion("Sin descripción");
        }

        return success ? newEntity : null;
    }

    @Override
    protected boolean areEquals() {
        return false;
    }
}