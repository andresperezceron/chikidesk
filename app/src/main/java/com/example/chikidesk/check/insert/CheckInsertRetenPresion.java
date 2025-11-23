package com.example.chikidesk.check.insert;

import com.example.chikidesk.check.Check;
import com.example.chikidesk.databinding.ConfigFormBinding;
import com.example.chikidesk.model.RetenPresion;

public class CheckInsertRetenPresion extends Check<RetenPresion, ConfigFormBinding> {
    public CheckInsertRetenPresion(ConfigFormBinding binding) {
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
        binding.tilConfigFormRetenVel.setError(null);
        if (newEntity.getVelocidad().isEmpty()) {
            empty = true;
            binding.tilConfigFormRetenVel.setError("V = 0");
            newEntity.setVelocidad("0");
        }

        binding.tilConfigFormRetenPre.setError(null);
        if (newEntity.getPresion().isEmpty()) {
            empty = true;
            binding.tilConfigFormRetenPre.setError("P = 0");
            newEntity.setPresion("0");
        }

        binding.tilConfigFormRetenTmp.setError(null);
        if (newEntity.getTiempo().isEmpty()) {
            empty = true;
            binding.tilConfigFormRetenTmp.setError("T = 0");
            newEntity.setTiempo("0");
        }

        return success ? newEntity : null;
    }

    @Override
    protected boolean areEquals() {
        return false;
    }
}
