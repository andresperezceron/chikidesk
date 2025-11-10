package com.example.chikidesk.check;

import com.example.chikidesk.databinding.ConfigFormBinding;
import com.example.chikidesk.model.Inyeccion;

public class CheckNewInyeccion extends Check<Inyeccion, ConfigFormBinding> {
    public CheckNewInyeccion(ConfigFormBinding binding) {
        super(binding);
        newEntity = newEntityByBinding();
        entityChecked = checkingNewEntity();
    }

    @Override
    protected Inyeccion newEntityByBinding() {
        return new Inyeccion(0,
                getTextFrom(binding.edtConfigFormInyVel1),
                getTextFrom(binding.edtConfigFormInyPre1),
                getTextFrom(binding.edtConfigFormInyVel2),
                getTextFrom(binding.edtConfigFormInyPre2),
                getTextFrom(binding.edtConfigFormInyVel3),
                getTextFrom(binding.edtConfigFormInyPre3),
                getTextFrom(binding.edtConfigFormInyVel4),
                getTextFrom(binding.edtConfigFormInyPre4),
                getTextFrom(binding.edtConfigFormInyVel5),
                getTextFrom(binding.edtConfigFormInyPre5));
    }

    @Override
    protected Inyeccion checkingNewEntity() {
        empty = false;
        binding.tilConfigFormInyVel1.setError(null);
        if(newEntity.getVelocidad1().isEmpty()) {
            empty = true;
            binding.tilConfigFormInyVel1.setError("V1 = 0");
            newEntity.setVelocidad1("0");
        }
        binding.tilConfigFormInyPre1.setError(null);
        if(newEntity.getPresion1().isEmpty()) {
            empty = true;
            binding.tilConfigFormInyPre1.setError("P1 = 0");
            newEntity.setPresion1("0");
        }

        binding.tilConfigFormInyVel2.setError(null);
        if(newEntity.getVelocidad2().isEmpty()) {
            empty = true;
            binding.tilConfigFormInyVel2.setError("V2 = 0");
            newEntity.setVelocidad2("0");
        }
        binding.tilConfigFormInyPre2.setError(null);
        if(newEntity.getPresion2().isEmpty()) {
            empty = true;
            binding.tilConfigFormInyPre2.setError("P2 = 0");
            newEntity.setPresion2("0");
        }

        binding.tilConfigFormInyVel3.setError(null);
        if(newEntity.getVelocidad3().isEmpty()) {
            empty = true;
            binding.tilConfigFormInyVel3.setError("V3 = 0");
            newEntity.setVelocidad3("0");
        }
        binding.tilConfigFormInyPre3.setError(null);
        if(newEntity.getPresion3().isEmpty()) {
            empty = true;
            binding.tilConfigFormInyPre3.setError("P3 = 0");
            newEntity.setPresion3("0");
        }

        binding.tilConfigFormInyVel4.setError(null);
        if(newEntity.getVelocidad4().isEmpty()) {
            empty = true;
            binding.tilConfigFormInyVel4.setError("V4 = 0");
            newEntity.setVelocidad4("0");
        }
        binding.tilConfigFormInyPre4.setError(null);
        if(newEntity.getPresion4().isEmpty()) {
            empty = true;
            binding.tilConfigFormInyPre4.setError("P4 = 0");
            newEntity.setPresion4("0");
        }

        binding.tilConfigFormInyVel5.setError(null);
        if(newEntity.getVelocidad5().isEmpty()) {
            empty = true;
            binding.tilConfigFormInyVel5.setError("V5 = 0");
            newEntity.setVelocidad5("0");
        }
        binding.tilConfigFormInyPre5.setError(null);
        if(newEntity.getPresion5().isEmpty()) {
            empty = true;
            binding.tilConfigFormInyPre5.setError("P5 = 0");
            newEntity.setPresion5("0");
        }
        return newEntity;
    }

    @Override
    protected boolean areEquals() {
        return false;
    }
}