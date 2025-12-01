package com.example.chikidesk.check.update;

import com.example.chikidesk.check.Check;
import com.example.chikidesk.databinding.ConfigUpdateBinding;
import com.example.chikidesk.model.Inyeccion;

public class CheckUpdateInyeccion extends Check<Inyeccion, ConfigUpdateBinding> {

    public CheckUpdateInyeccion(ConfigUpdateBinding binding, Inyeccion oldEntity) {
        super(binding, oldEntity);
        super.newEntity = newEntityByBinding();
        super.areEqualsToUpdate = areEquals();
        super.entityChecked = checkingNewEntity();
    }

    @Override
    protected Inyeccion newEntityByBinding() {
        return new Inyeccion(oldEntity.getId(),
                getTextFrom(binding.edtConfigUpdateInyVel1),
                getTextFrom(binding.edtConfigUpdateInyPre1),
                getTextFrom(binding.edtConfigUpdateInyVel2),
                getTextFrom(binding.edtConfigUpdateInyPre2),
                getTextFrom(binding.edtConfigUpdateInyVel3),
                getTextFrom(binding.edtConfigUpdateInyPre3),
                getTextFrom(binding.edtConfigUpdateInyVel4),
                getTextFrom(binding.edtConfigUpdateInyPre4),
                getTextFrom(binding.edtConfigUpdateInyVel5),
                getTextFrom(binding.edtConfigUpdateInyPre5));
    }

    @Override
    protected Inyeccion checkingNewEntity() {
        if (areEqualsToUpdate) return null;

        binding.tilConfigUpdateInyVel1.setError(null);
        if (newEntity.getVelocidad1().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateInyVel1.setError("");
        }

        binding.tilConfigUpdateInyPre1.setError(null);
        if (newEntity.getPresion1().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateInyPre1.setError("");
        }

        binding.tilConfigUpdateInyVel2.setError(null);
        if (newEntity.getVelocidad2().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateInyVel2.setError("");
        }

        binding.tilConfigUpdateInyPre2.setError(null);
        if(newEntity.getPresion2().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateInyPre2.setError("");
        }

        binding.tilConfigUpdateInyVel3.setError(null);
        if(newEntity.getVelocidad3().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateInyVel3.setError("");
        }

        binding.tilConfigUpdateInyPre3.setError(null);
        if(newEntity.getPresion3().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateInyPre3.setError("");
        }

        binding.tilConfigUpdateInyVel4.setError(null);
        if(newEntity.getVelocidad4().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateInyVel4.setError("");
        }

        binding.tilConfigUpdateInyPre4.setError(null);
        if(newEntity.getPresion4().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateInyPre4.setError("");
        }

        binding.tilConfigUpdateInyVel5.setError(null);
        if(newEntity.getVelocidad5().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateInyVel5.setError("");
        }

        binding.tilConfigUpdateInyPre5.setError(null);
        if(newEntity.getPresion5().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateInyPre5.setError("");
        }

        return success ? newEntity : null;
    }

    @Override
    protected boolean areEquals() {
        return oldEntity.getVelocidad1().equals(newEntity.getVelocidad1()) &&
                oldEntity.getPresion1().equals(newEntity.getPresion1()) &&
                oldEntity.getVelocidad2().equals(newEntity.getVelocidad2()) &&
                oldEntity.getPresion2().equals(newEntity.getPresion2()) &&
                oldEntity.getVelocidad3().equals(newEntity.getVelocidad3()) &&
                oldEntity.getPresion3().equals(newEntity.getPresion3()) &&
                oldEntity.getVelocidad4().equals(newEntity.getVelocidad4()) &&
                oldEntity.getPresion4().equals(newEntity.getPresion4()) &&
                oldEntity.getVelocidad5().equals(newEntity.getVelocidad5()) &&
                oldEntity.getPresion5().equals(newEntity.getPresion5());
    }
}