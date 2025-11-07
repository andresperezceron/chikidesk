package com.example.chikidesk.check;

import com.example.chikidesk.databinding.ConfigFormBinding;
import com.example.chikidesk.model.Temperatura;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class CheckNewTemperatura extends Check<Temperatura, ConfigFormBinding> {
    public CheckNewTemperatura(AppCacheViewModel appCache, ConfigFormBinding binding) {
        super(appCache, binding);
    }

    @Override
    protected Temperatura newEntityByBinding() {
        return new Temperatura(0,
                getTextFrom(binding.edtConfigFormTemp1),
                getTextFrom(binding.edtConfigFormTemp2),
                getTextFrom(binding.edtConfigFormTemp3),
                getTextFrom(binding.edtConfigFormTemp4));
    }

    @Override
    protected Temperatura chekingNewEntity() {
        empty = false;
        binding.tilConfigFormTemp1.setError(null);
        if(newEntity.getTemp1().isEmpty()) {
            binding.tilConfigFormTemp1.setError("T1 = 0");
            newEntity.setTemp1("0");
            empty = true;
        }
        binding.tilConfigFormTemp2.setError(null);
        if(newEntity.getTemp2().isEmpty()) {
            binding.tilConfigFormTemp2.setError("T2 = 0");
            newEntity.setTemp2("0");
            empty = true;
        }
        binding.tilConfigFormTemp3.setError(null);
        if(newEntity.getTemp3().isEmpty()) {
            binding.tilConfigFormTemp3.setError("T3 = 0");
            newEntity.setTemp3("0");
            empty = true;
        }
        binding.tilConfigFormTemp4.setError(null);
        if(newEntity.getTemp4().isEmpty()) {
            binding.tilConfigFormTemp4.setError("T4 = 0");
            newEntity.setTemp4("0");
            empty = true;
        }
        return newEntity;
    }

    @Override
    protected boolean areEquals() {
        return false;
    }
}
