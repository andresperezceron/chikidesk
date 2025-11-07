package com.example.chikidesk.check;

import com.example.chikidesk.databinding.ConfigFormBinding;
import com.example.chikidesk.model.Inyeccion;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class CheckNewInyeccion extends Check<Inyeccion, ConfigFormBinding> {
    public CheckNewInyeccion(AppCacheViewModel appCache, ConfigFormBinding binding) {
        super(appCache, binding);
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
    protected Inyeccion chekingNewEntity() {
        return null;
    }

    @Override
    protected boolean areEquals() {
        return false;
    }
}
