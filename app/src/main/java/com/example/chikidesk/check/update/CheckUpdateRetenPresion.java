package com.example.chikidesk.check.update;

import com.example.chikidesk.check.Check;
import com.example.chikidesk.databinding.ConfigUpdateBinding;
import com.example.chikidesk.model.RetenPresion;

public class CheckUpdateRetenPresion extends Check<RetenPresion, ConfigUpdateBinding> {

    public CheckUpdateRetenPresion(ConfigUpdateBinding binding, RetenPresion oldEntity) {
        super(binding, oldEntity);
        super.newEntity = newEntityByBinding();
        super.areEqualsToUpdate = areEquals();
        super.entityChecked = checkingNewEntity();
    }

    @Override
    protected RetenPresion newEntityByBinding() {
        return new RetenPresion(oldEntity.getId(),
                getTextFrom(binding.edtConfigUpdateRetenVel),
                getTextFrom(binding.edtConfigUpdateRetenPre),
                getTextFrom(binding.edtConfigUpdateRetenTmp));
    }

    @Override
    protected RetenPresion checkingNewEntity() {
        if(areEqualsToUpdate) return null;
        success = true;
        empty = false;

        binding.tilConfigUpdateRetenVel.setError(null);
        if (newEntity.getVelocidad().isEmpty()) { empty = true; success = false; binding.tilConfigUpdateRetenVel.setError(""); }
        
        binding.tilConfigUpdateRetenPre.setError(null);
        if (newEntity.getPresion().isEmpty()) { empty = true; success = false; binding.tilConfigUpdateRetenPre.setError(""); }
        
        binding.tilConfigUpdateRetenTmp.setError(null);
        if (newEntity.getTiempo().isEmpty()) { empty = true; success = false; binding.tilConfigUpdateRetenTmp.setError(""); }

        return success ? newEntity : null;
    }

    @Override
    protected boolean areEquals() {
        return oldEntity.getVelocidad().equals(newEntity.getVelocidad()) &&
                oldEntity.getPresion().equals(newEntity.getPresion()) &&
                oldEntity.getTiempo().equals(newEntity.getTiempo());
    }
}
