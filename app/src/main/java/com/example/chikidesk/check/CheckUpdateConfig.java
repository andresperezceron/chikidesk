package com.example.chikidesk.check;

import com.example.chikidesk.databinding.ConfigUpdateBinding;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.model.Molde;

public class CheckUpdateConfig extends Check<Configuracion, ConfigUpdateBinding> {
    private final Maquina maquina;
    private final Molde molde;

    public CheckUpdateConfig(Maquina maquina, Molde molde, ConfigUpdateBinding binding,
                             Configuracion oldEntity) {
        super(binding, oldEntity);
        this.maquina = maquina;
        this.molde = molde;
        super.newEntity = newEntityByBinding();
        super.entityChecked = checkingNewEntity();
        super.areEqualsToUpdate = areEquals();
    }

    @Override
    protected Configuracion newEntityByBinding() {
        return new Configuracion(oldEntity.getId(), maquina.getId(), molde.getId(),
                getTextFrom(binding.edtConfigUpdatePlastico),
                getTextFrom(binding.edtConfigUpdateCiclo),
                getTextFrom(binding.edtConfigUpdateCicloReal),
                getTextFrom(binding.edtConfigUpdateEnfriar),
                getTextFrom(binding.edtConfigUpdateTimeOut),
                getTextFrom(binding.edtConfigUpdateMaterial),
                getTextFrom(binding.edtConfigUpdateObservaciones));
    }

    @Override
    protected Configuracion checkingNewEntity() {
        success = true;
        binding.tilConfigUpdatePlastifico.setError(null);
        if(newEntity.getPlastificacion().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdatePlastifico.setError("");
        }

        binding.tilConfigUpdateCiclo.setError(null);
        if(newEntity.getTiempoCiclo().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateCiclo.setError("");
        }

        binding.tilConfigUpdateCicloReal.setError(null);
        if(newEntity.getTiempoCicloReal().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateCicloReal.setError("");
        }

        binding.tilConfigUpdateEnfriar.setError(null);
        if(newEntity.getTiempoEnfriar().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateEnfriar.setError("");
        }
        binding.tilConfigUpdateTimeOut.setError(null);
        if(newEntity.getTimeOut().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateTimeOut.setError("");
        }

        binding.tilConfigUpdateMaterial.setError(null);
        if(newEntity.getMaterial().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateMaterial.setError("");
        }

        if(newEntity.getObservaciones().isEmpty()) {
            empty = true;
            success = false;
        }

        return success ? newEntity : null;
    }

    @Override
    protected boolean areEquals() {
        return oldEntity.getPlastificacion().equals(newEntity.getPlastificacion()) &&
                oldEntity.getTiempoCiclo().equals(newEntity.getTiempoCiclo()) &&
                oldEntity.getTiempoCicloReal().equals(newEntity.getTiempoCicloReal()) &&
                oldEntity.getTiempoEnfriar().equals(newEntity.getTiempoEnfriar()) &&
                oldEntity.getTimeOut().equals(newEntity.getTimeOut()) &&
                oldEntity.getMaterial().equals(newEntity.getMaterial()) &&
                oldEntity.getObservaciones().equals(newEntity.getObservaciones());
    }
}