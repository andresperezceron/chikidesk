package com.example.chikidesk.check.update;

import com.example.chikidesk.check.Check;
import com.example.chikidesk.databinding.ConfigUpdateBinding;
import com.example.chikidesk.model.Temperatura;

public class CheckUpdateTemperatura extends Check<Temperatura, ConfigUpdateBinding> {

    public CheckUpdateTemperatura(ConfigUpdateBinding binding, Temperatura oldEntity) {
        super(binding, oldEntity);
        super.newEntity = newEntityByBinding();
        super.areEqualsToUpdate = areEquals();
        super.entityChecked = checkingNewEntity();
    }

    @Override
    protected Temperatura newEntityByBinding() {
        return new Temperatura(oldEntity.getId(),
                getTextFrom(binding.edtConfigUpdateTemp1),
                getTextFrom(binding.edtConfigUpdateTemp2),
                getTextFrom(binding.edtConfigUpdateTemp3),
                getTextFrom(binding.edtConfigUpdateTemp4));
    }

    @Override
    protected Temperatura checkingNewEntity() {
        if(areEqualsToUpdate) return null;

        binding.tilConfigUpdateTemp1.setError(null);
        if(newEntity.getTemp1().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateTemp1.setError("");
        }

        binding.tilConfigUpdateTemp2.setError(null);
        if(newEntity.getTemp2().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateTemp2.setError("");
        }

        binding.tilConfigUpdateTemp3.setError(null);
        if(newEntity.getTemp3().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateTemp3.setError("");
        }

        binding.tilConfigUpdateTemp4.setError(null);
        if(newEntity.getTemp4().isEmpty()) {
            empty = true;
            success = false;
            binding.tilConfigUpdateTemp4.setError("");
        }

        return success ? newEntity : null;
    }

    @Override
    protected boolean areEquals() {
        return oldEntity.getTemp1().equals(newEntity.getTemp1()) &&
                oldEntity.getTemp2().equals(newEntity.getTemp2()) &&
                oldEntity.getTemp3().equals(newEntity.getTemp3()) &&
                oldEntity.getTemp4().equals(newEntity.getTemp4());
    }
}