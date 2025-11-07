package com.example.chikidesk.cofigurator;

import androidx.annotation.NonNull;

import com.example.chikidesk.ui.fragment.MainFragment;

public class Configurator {
    private final MainFragment fragment;
    public Configurator(@NonNull MainFragment fragment) {
        this.fragment = fragment;
        fragment.setBinding(findBinding().createBinding());
        fragment.setDriver(findHandle().createDriver());
    }

    private ItemBinding findBinding() {
        return new BindingStream().stream(fragment)
                .filter(ItemBinding::getId)
                .findFirst()
                .orElse(null);
    }

    private ItemHandle findHandle() {
        return new HandleStream().stream(fragment)
                .filter(ItemHandle::getId)
                .findFirst()
                .orElse(null);
    }
}