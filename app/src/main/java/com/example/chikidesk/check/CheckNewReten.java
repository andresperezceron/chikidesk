package com.example.chikidesk.check;

import com.example.chikidesk.databinding.ConfigFormBinding;
import com.example.chikidesk.model.RetenPresion;

public class CheckNewReten extends Check<RetenPresion, ConfigFormBinding> {
    public CheckNewReten(ConfigFormBinding binding) {
        super(binding);
        newEntity = newEntityByBinding();
        entityChecked = checkingNewEntity();
    }

    @Override
    protected RetenPresion newEntityByBinding() {
        return new RetenPresion(0,
                getTextFrom(binding.edtConfigFormRetenVel),
                getTextFrom(binding.edtConfigFormRetenPre),
                getTextFrom(binding.edtConfigFormRetenTmp));
    }

    @Override
    protected RetenPresion checkingNewEntity() {
        empty = false;
        binding.tilConfigFormRetenVel.setError(null);
        if(newEntity.getVelocidad().isEmpty()) {
            empty = true;
            binding.tilConfigFormRetenVel.setError("Velocidad = 0");
            newEntity.setVelocidad("0");
        }
        binding.tilConfigFormRetenPre.setError(null);
        if(newEntity.getPresion().isEmpty()) {
            empty = true;
            binding.tilConfigFormRetenPre.setError("Presion = 0");
            newEntity.setPresion("0");
        }
        binding.tilConfigFormRetenTmp.setError(null);
        if(newEntity.getTiempo().isEmpty()) {
            empty = true;
            binding.tilConfigFormRetenTmp.setError("Tiempo = 0");
            newEntity.setTiempo("0");
        }
        return newEntity;
    }

    @Override
    protected boolean areEquals() { return false; }
}