package com.example.chikidesk.cofigurator;

import androidx.annotation.NonNull;

import com.example.chikidesk.driver.BaseDriver;
import com.example.chikidesk.handle.HandleConfigList;
import com.example.chikidesk.handle.HandleMoldeDelete;
import com.example.chikidesk.handle.HandleMoldeForm;
import com.example.chikidesk.handle.HandleMoldeList;
import com.example.chikidesk.handle.HandleMoldeShow;
import com.example.chikidesk.handle.HandleMoldeUpdate;
import com.example.chikidesk.ui.fragment.BaseFragment;

import java.util.stream.Stream;

public class HandleStream {
    public Stream<ItemHandle> stream(@NonNull BaseFragment fragment) {
        String idFragment = fragment.getClass().getSimpleName();
        return Stream.of(
            new ItemHandle() {
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMoldeList.name()); }
                @Override
                public BaseDriver createDriver() { return new HandleMoldeList(fragment); }
            },
            new ItemHandle() {
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMoldeShow.name()); }
                @Override
                public BaseDriver createDriver() { return new HandleMoldeShow(fragment); }
            },
            new ItemHandle() {
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMoldeForm.name()); }
                @Override
                public BaseDriver createDriver() { return new HandleMoldeForm(fragment); }
            },
            new ItemHandle() {
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentMoldeUpdate.name());
                }
                @Override
                public BaseDriver createDriver() {
                    return new HandleMoldeUpdate(fragment);
                }
            },
            new ItemHandle() { //MoldeDelete
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentMoldeDelete.name());
                }
                @Override
                public BaseDriver createDriver() {
                    return new HandleMoldeDelete(fragment);
                }
            },
            new ItemHandle() {
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentConfigList.name());
                }
                @Override
                public BaseDriver createDriver() {
                    return new HandleConfigList(fragment);
                }
            }
        );
    }
}
