package com.example.chikidesk.check;

import com.example.chikidesk.databinding.ConfigFormBinding;
import com.example.chikidesk.model.Expulsor;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class CheckNewExpulsor extends Check<Expulsor, ConfigFormBinding> {
    public CheckNewExpulsor(AppCacheViewModel appCache, ConfigFormBinding binding) {
        super(appCache, binding);
    }

    @Override
    protected Expulsor newEntityByBinding() {
        return new Expulsor(0,
                getTextFrom(binding.edtConfigFormExpulsorVel1),
                getTextFrom(binding.edtConfigFormExpulsorPre1),
                getTextFrom(binding.edtConfigFormExpulsorPos1),
                getTextFrom(binding.edtConfigFormExpulsorVel2),
                getTextFrom(binding.edtConfigFormExpulsorPre2),
                getTextFrom(binding.edtConfigFormExpulsorPos2));
    }

    @Override
    protected Expulsor chekingNewEntity() {
        empty = false;
        binding.tilConfigFormExpulsorVel1.setError(null);
        if(newEntity.getVelocidad1().isEmpty()) {
            empty = true;
            binding.tilConfigFormExpulsorVel1.setError("Vel 1 = 0");
            newEntity.setVelocidad1("0");
        }
        if(newEntity.getPresion1().isEmpty()) {
            empty = true;
            binding.tilConfigFormExpulsorPre1.setError("Pre 1 = 0");
            newEntity.setPresion1("0");
        }
        if(newEntity.getPosicion1().isEmpty()) {
            empty = true;
            binding.tilConfigFormExpulsorPos1.setError("Pos 1 = 0");
            newEntity.setPosicion1("0");
        }

        if(newEntity.getVelocidad2().isEmpty()) {
            empty = true;
            binding.tilConfigFormExpulsorVel2.setError("Vel 2 = 0");
            newEntity.setVelocidad2("0");
        }
        if(newEntity.getPresion2().isEmpty()) {
            empty = true;
            binding.tilConfigFormExpulsorPre2.setError("Pre 1 = 0");
            newEntity.setPresion2("0");
        }
        if(newEntity.getPosicion2().isEmpty()) {
            empty = true;
            binding.tilConfigFormExpulsorPos2.setError("Pos 2 = 0");
            newEntity.setPosicion2("0");
        }
        return newEntity;
    }

    @Override
    protected boolean areEquals() {
        return false;
    }
}
