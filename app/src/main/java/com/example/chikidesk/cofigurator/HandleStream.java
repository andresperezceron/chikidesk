package com.example.chikidesk.cofigurator;

import androidx.annotation.NonNull;

import com.example.chikidesk.driver.Driver;
import com.example.chikidesk.handle.HandleConfigForm;
import com.example.chikidesk.handle.HandleConfigList;
import com.example.chikidesk.handle.HandleMoldeDelete;
import com.example.chikidesk.handle.HandleMoldeForm;
import com.example.chikidesk.handle.HandleMoldeList;
import com.example.chikidesk.handle.HandleMoldeShow;
import com.example.chikidesk.handle.HandleMoldeUpdate;
import com.example.chikidesk.handle.HandleSelectMaquina;
import com.example.chikidesk.handle.HandleSelectMolde;
import com.example.chikidesk.ui.fragment.MainFragment;

import java.util.stream.Stream;

public class HandleStream {
    public Stream<ItemHandle> stream(@NonNull MainFragment fragment) {
        String idFragment = fragment.getClass().getSimpleName();
        return Stream.of(
            new ItemHandle() {
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMoldeList.name()); }
                @Override
                public Driver createDriver() { return new HandleMoldeList(fragment); }
            },
            new ItemHandle() {
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMoldeShow.name()); }
                @Override
                public Driver createDriver() { return new HandleMoldeShow(fragment); }
            },
            new ItemHandle() {
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMoldeForm.name()); }
                @Override
                public Driver createDriver() { return new HandleMoldeForm(fragment); }
            },
            new ItemHandle() {
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentMoldeUpdate.name());
                }
                @Override
                public Driver createDriver() {
                    return new HandleMoldeUpdate(fragment);
                }
            },
            new ItemHandle() { //MoldeDelete
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentMoldeDelete.name());
                }
                @Override
                public Driver createDriver() {
                    return new HandleMoldeDelete(fragment);
                }
            },
            new ItemHandle() {
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentConfigList.name());
                }
                @Override
                public Driver createDriver() {
                    return new HandleConfigList(fragment);
                }
            },
            new ItemHandle() {
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentConfigForm.name());
                }
                @Override
                public Driver createDriver() {
                    return new HandleConfigForm(fragment);
                }
            },
            new ItemHandle() {
                @Override
                public boolean getId() {return idFragment.equals(Ids.FragmentSelectMaquina.name()); }
                @Override
                public Driver createDriver() {
                    return new HandleSelectMaquina(fragment);
                }
            },
            new ItemHandle() {
                @Override
                public boolean getId() {return idFragment.equals(Ids.FragmentSelectMolde.name()); }
                @Override
                public Driver createDriver() {
                    return new HandleSelectMolde(fragment);
                }
            }
        );
    }
}
