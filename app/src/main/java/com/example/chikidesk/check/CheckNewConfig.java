package com.example.chikidesk.check;

import com.example.chikidesk.databinding.ConfigFormBinding;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class CheckNewConfig extends Check<Configuracion, ConfigFormBinding> {
    private final Maquina maquina;
    private final Molde molde;

    public CheckNewConfig(AppCacheViewModel appCache, ConfigFormBinding binding,
                          Maquina maquina, Molde molde) {
        super(appCache, binding);
        this.maquina = maquina;
        this.molde = molde;
    }

    @Override
    protected Configuracion newEntityByBinding() {
        return new Configuracion(0, maquina.getId(), molde.getId(),
                getTextFrom(binding.edtConfigFormPlastico),
                getTextFrom(binding.edtConfigFormCiclo),
                getTextFrom(binding.edtConfigFormCicloReal),
                getTextFrom(binding.edtConfigFormEnfriar),
                getTextFrom(binding.edtConfigFormTimeOut),
                getTextFrom(binding.edtConfigFormMaterial),
                getTextFrom(binding.edtConfigFormObservaciones));
    }

    @Override
    protected Configuracion chekingNewEntity() {
        empty = false;

        binding.tilConfigFormPlastifico.setError(null);
        if(newEntity.getPlastificacion().isEmpty()) {
            empty = true;
            binding.tilConfigFormPlastifico.setError("Sin plastificacion");
            newEntity.setPlastificacion("Sin plastificacion");
        }

        binding.tilConfigFormCiclo.setError(null);
        if(newEntity.getTiempoCiclo().isEmpty()) {
            empty = true;
            binding.tilConfigFormCiclo.setError("Ciclo = 0");
            newEntity.setTiempoCiclo("0");
        }

        binding.tilConfigFormCicloReal.setError(null);
        if(newEntity.getTiempoCicloReal().isEmpty()) {
            empty = true;
            binding.tilConfigFormCicloReal.setError("Real = 0");
            newEntity.setTiempoCicloReal("0");
        }

        binding.tilConfigFormEnfriar.setError(null);
        if(newEntity.getTiempoEnfriar().isEmpty()) {
            empty = true;
            binding.tilConfigFormEnfriar.setError("Enfriar = 0");
            newEntity.setTiempoEnfriar("0");
        }
        binding.tilConfigFormTimeOut.setError(null);
        if(newEntity.getTimeOut().isEmpty()) {
            empty = true;
            binding.tilConfigFormTimeOut.setError("Time Out = 0");
            newEntity.setTimeOut("0");
        }

        binding.tilConfigFormMaterial.setError(null);
        if(newEntity.getMaterial().isEmpty()) {
            empty = true;
            binding.tilConfigFormMaterial.setError("Sin material");
            newEntity.setMaterial("Sin material");
        }

        if(newEntity.getObservaciones().isEmpty()) {
            newEntity.setObservaciones("Sin observaciones");
        }

        return newEntity;
    }

    @Override
    protected boolean areEquals() {
        return false;
    }
}
