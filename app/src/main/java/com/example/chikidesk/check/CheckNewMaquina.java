package com.example.chikidesk.check;

import com.example.chikidesk.databinding.MaquinaFormBinding;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class CheckNewMaquina extends Check<Maquina, MaquinaFormBinding> {
    private final AppCacheViewModel appCache;
    public CheckNewMaquina(AppCacheViewModel appCache, MaquinaFormBinding binding) {
        super(binding);
        this.appCache = appCache;
        newEntity = newEntityByBinding();
        entityChecked = checkingNewEntity();
    }

    @Override
    protected Maquina newEntityByBinding() {
        success = true;
        binding.tilMaquinaFormNombre.setError(null);
        if(newEntity.getNombre().isEmpty()) {
            binding.tilMaquinaFormNombre.setError("El campo Nombre es obligatorio");
            success = false;
        }
        if(appCache.maquinaList.stream().anyMatch(m ->
                m.getNombre().equals(newEntity.getNombre()))) {
            binding.tilMaquinaFormNombre.setError("Nombre ulizado por otra maquina");
            success = false;
        }

        binding.tilMaquinaFormRef.setError(null);
        if(newEntity.getReferencia().isEmpty()) {
            success = false;
            binding.tilMaquinaFormRef.setError("El campo Referencia es obligatorio");
        } else if (appCache.maquinaList.stream().anyMatch(m ->
                m.getReferencia().equals(newEntity.getReferencia()))) {
            binding.tilMaquinaFormRef.setError("Referencia ulizada por otra maquina");
            success = false;
        }

        if(newEntity.getDescripcion().isEmpty())
            newEntity.setDescripcion("Sin descripcon");

        return success ? newEntity : null;
    }

    @Override
    protected Maquina checkingNewEntity() {
        return new Maquina(0,
                getTextFrom(binding.edtMaquinaFormNombre),
                getTextFrom(binding.edtMaquinaFormRef),
                getTextFrom(binding.edtMaquinaFormDesc));
    }

    @Override
    protected boolean areEquals() {
        return false;
    }
}