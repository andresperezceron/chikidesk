package com.example.chikidesk.check;

import com.example.chikidesk.databinding.ConfigUpdateBinding;
import com.example.chikidesk.model.RetenPresion;

public class CheckUpdateRetenPresion extends Check<RetenPresion, ConfigUpdateBinding> {

    public CheckUpdateRetenPresion(ConfigUpdateBinding binding, RetenPresion oldEntity) {
        super(binding, oldEntity);
        super.newEntity = newEntityByBinding();
        super.entityChecked = checkingNewEntity();
        super.areEqualsToUpdate = areEquals();
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
        success = true;

        if (newEntity.getVelocidad().isEmpty()) { empty = true; success = false; binding.tilConfigUpdateRetenVel.setError(""); }
        if (newEntity.getPresion().isEmpty()) { empty = true; success = false; binding.tilConfigUpdateRetenPre.setError(""); }
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
