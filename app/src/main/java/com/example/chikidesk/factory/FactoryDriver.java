package com.example.chikidesk.factory;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.chikidesk.databinding.MoldeListBinding;
import com.example.chikidesk.driver.BaseDriver;
import com.example.chikidesk.driver.DriverList;
import com.example.chikidesk.handle.HandleMoldeList;
import com.example.chikidesk.ui.fragment.BaseFragment;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.security.Provider;

public class FactoryDriver extends BaseFactory {

    @Nullable
    public static BaseDriver getDriver(@NonNull Class<?> callOwner, BaseFragment fragment) {
        switch(callOwner.getSimpleName()) {
            case MOLDE_LIST:
                return new HandleMoldeList(fragment.getAppCache(), fragment);
            default: return null;
        }
    }
}
