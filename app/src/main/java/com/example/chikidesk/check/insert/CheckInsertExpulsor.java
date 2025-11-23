package com.example.chikidesk.check.insert;

import com.example.chikidesk.check.Check;
import com.example.chikidesk.databinding.ConfigFormBinding;
import com.example.chikidesk.model.Expulsor;

public class CheckInsertExpulsor extends Check<Expulsor, ConfigFormBinding> {
    public CheckInsertExpulsor(ConfigFormBinding binding) {
        super(binding);
        newEntity = newEntityByBinding();
        entityChecked = checkingNewEntity();
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
    protected Expulsor checkingNewEntity() {
        binding.tilConfigFormExpulsorVel1.setError(null);
        if(newEntity.getVelocidad1().isEmpty()) {
            empty = true;
            binding.tilConfigFormExpulsorVel1.setError("Vel 1 = 0");
            newEntity.setVelocidad1("0");
        }

        binding.tilConfigFormExpulsorPre1.setError(null);
        if(newEntity.getPresion1().isEmpty()) {
            empty = true;
            binding.tilConfigFormExpulsorPre1.setError("Pre 1 = 0");
            newEntity.setPresion1("0");
        }

        binding.tilConfigFormExpulsorPos1.setError(null);
        if(newEntity.getPosicion1().isEmpty()) {
            empty = true;
            binding.tilConfigFormExpulsorPos1.setError("Pos 1 = 0");
            newEntity.setPosicion1("0");
        }

        binding.tilConfigFormExpulsorVel2.setError(null);
        if(newEntity.getVelocidad2().isEmpty()) {
            empty = true;
            binding.tilConfigFormExpulsorVel2.setError("Vel 2 = 0");
            newEntity.setVelocidad2("0");
        }

        binding.tilConfigFormExpulsorPre2.setError(null);
        if(newEntity.getPresion2().isEmpty()) {
            empty = true;
            binding.tilConfigFormExpulsorPre2.setError("Pre 2 = 0");
            newEntity.setPresion2("0");
        }

        binding.tilConfigFormExpulsorPos2.setError(null);
        if(newEntity.getPosicion2().isEmpty()) {
            empty = true;
            binding.tilConfigFormExpulsorPos2.setError("Pos 2 = 0");
            newEntity.setPosicion2("0");
        }
        
        return success ? newEntity : null;
    }

    @Override
    protected boolean areEquals() {
        return false;
    }
}