package com.example.chikidesk.factory;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.example.chikidesk.databinding.MoldeListBinding;

public class FactoryBinding extends BaseFactory {

    public static ViewBinding getInflatedBinding(Class<?> callOwner,
                                                 @NonNull LayoutInflater inflater,
                                                 @Nullable ViewGroup container) {
        switch(callOwner.getSimpleName()) {
            case MOLDE_LIST:
                return MoldeListBinding.inflate(inflater, container, false);
            default: return null;
        }
    }
}
