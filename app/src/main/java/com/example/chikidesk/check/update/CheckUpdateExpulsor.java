package com.example.chikidesk.check.update;

import com.example.chikidesk.check.Check;
import com.example.chikidesk.databinding.ConfigUpdateBinding;
import com.example.chikidesk.model.Expulsor;

public class CheckUpdateExpulsor extends Check<Expulsor, ConfigUpdateBinding> {

    public CheckUpdateExpulsor(ConfigUpdateBinding binding, Expulsor oldEntity) {
        super(binding, oldEntity);
        super.newEntity = newEntityByBinding();
        super.areEqualsToUpdate = areEquals();
        super.entityChecked = checkingNewEntity();
    }

    @Override
    protected Expulsor newEntityByBinding() {
        return new Expulsor(oldEntity.getId(),
                getTextFrom(binding.edtConfigUpdateExpulsorVel1),
                getTextFrom(binding.edtConfigUpdateExpulsorPre1),
                getTextFrom(binding.edtConfigUpdateExpulsorPos1),
                getTextFrom(binding.edtConfigUpdateExpulsorVel2),
                getTextFrom(binding.edtConfigUpdateExpulsorPre2),
                getTextFrom(binding.edtConfigUpdateExpulsorPos2));
    }

    @Override
    protected Expulsor checkingNewEntity() {
        if(areEqualsToUpdate) return null;

        binding.tilConfigUpdateExpulsorVel1.setError(null);
        if (newEntity.getVelocidad1().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateExpulsorVel1.setError("");
        }

        binding.tilConfigUpdateExpulsorPre1.setError(null);
        if(newEntity.getPresion1().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateExpulsorPre1.setError("");
        }

        binding.tilConfigUpdateExpulsorPos1.setError(null);
        if(newEntity.getPosicion1().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateExpulsorPos1.setError("");
        }

        binding.tilConfigUpdateExpulsorVel2.setError(null);
        if(newEntity.getVelocidad2().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateExpulsorVel2.setError("");
        }

        binding.tilConfigUpdateExpulsorPre2.setError(null);
        if(newEntity.getPresion2().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateExpulsorPre2.setError("");
        }

        binding.tilConfigUpdateExpulsorPos2.setError(null);
        if(newEntity.getPosicion2().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateExpulsorPos2.setError("");
        }

        return success ? newEntity : null;
    }

    @Override
    protected boolean areEquals() {
        return oldEntity.getVelocidad1().equals(newEntity.getVelocidad1()) &&
                oldEntity.getPresion1().equals(newEntity.getPresion1()) &&
                oldEntity.getPosicion1().equals(newEntity.getPosicion1()) &&
                oldEntity.getVelocidad2().equals(newEntity.getVelocidad2()) &&
                oldEntity.getPresion2().equals(newEntity.getPresion2()) &&
                oldEntity.getPosicion2().equals(newEntity.getPosicion2());
    }
}