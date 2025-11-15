package com.example.chikidesk.cofigurator;

import androidx.annotation.NonNull;

import com.example.chikidesk.handle.Driver;
import com.example.chikidesk.handle.HandleConfigDelete;
import com.example.chikidesk.handle.HandleConfigForm;
import com.example.chikidesk.handle.HandleConfigList;
import com.example.chikidesk.handle.HandleConfigShow;
import com.example.chikidesk.handle.HandleConfigUpdate;
import com.example.chikidesk.handle.HandleMaquinaDelete;
import com.example.chikidesk.handle.HandleMaquinaForm;
import com.example.chikidesk.handle.HandleMaquinaList;
import com.example.chikidesk.handle.HandleMaquinaShow;
import com.example.chikidesk.handle.HandleMaquinaUpdate;
import com.example.chikidesk.handle.HandleMoldeDelete;
import com.example.chikidesk.handle.HandleMoldeForm;
import com.example.chikidesk.handle.HandleMoldeList;
import com.example.chikidesk.handle.HandleMoldeShow;
import com.example.chikidesk.handle.HandleMoldeUpdate;
import com.example.chikidesk.handle.HandleSelectConfig;
import com.example.chikidesk.handle.HandleSelectMaquina;
import com.example.chikidesk.handle.HandleSelectMolde;
import com.example.chikidesk.ui.fragment.MainFragment;

import java.util.stream.Stream;

public class HandleStream {
    public Stream<ItemHandle> stream(@NonNull MainFragment fragment) {
        String idFragment = fragment.getClass().getSimpleName();
        return Stream.of(
            new ItemHandle() { //MOLDE LIST
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMoldeList.name()); }
                @Override
                public Driver createDriver() { return new HandleMoldeList(fragment); }
            },
            new ItemHandle() { //MOLDE SHOW
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMoldeShow.name()); }
                @Override
                public Driver createDriver() { return new HandleMoldeShow(fragment); }
            },
            new ItemHandle() { //MOLDE FORM
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMoldeForm.name()); }
                @Override
                public Driver createDriver() { return new HandleMoldeForm(fragment); }
            },
            new ItemHandle() { //MOLDE UPDATE
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentMoldeUpdate.name());
                }
                @Override
                public Driver createDriver() {
                    return new HandleMoldeUpdate(fragment);
                }
            },
            new ItemHandle() { //MOLDE DELETE
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentMoldeDelete.name());
                }
                @Override
                public Driver createDriver() {
                    return new HandleMoldeDelete(fragment);
                }
            },
            new ItemHandle() { /* MAQUINA LIST */
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMaquinaList.name()); }
                @Override
                public Driver createDriver() { return new HandleMaquinaList(fragment); }
            },
            new ItemHandle() { /* MAQUINA SHOW */
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMaquinaShow.name()); }
                @Override
                public Driver createDriver() { return new HandleMaquinaShow(fragment); }
            },
            new ItemHandle() { /* MAQUINA FORM */
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMaquinaForm.name()); }
                @Override
                public Driver createDriver() { return new HandleMaquinaForm(fragment); }
            },
            new ItemHandle() { /* MAQUINA UPDATE */
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMaquinaUpdate.name()); }
                @Override
                public Driver createDriver() { return new HandleMaquinaUpdate(fragment); }
            },
            new ItemHandle() { /* MAQUINA DELETE */
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMaquinaDelete.name()); }
                @Override
                public Driver createDriver() { return new HandleMaquinaDelete(fragment); }
            },
            new ItemHandle() { //CONFIG LIST
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentConfigList.name()); }
                @Override
                public Driver createDriver() { return new HandleConfigList(fragment); }
            },
            new ItemHandle() { //CONFIG FORM
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentConfigForm.name()); }
                @Override
                public Driver createDriver() { return new HandleConfigForm(fragment); }
            },
            new ItemHandle() { //CONFIG SHOW
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentConfigShow.name()); }
                @Override
                public Driver createDriver() { return new HandleConfigShow(fragment); }
            },
            new ItemHandle() { //CONFIG UPDATE
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentConfigUpdate.name()); }
                @Override
                public Driver createDriver() { return new HandleConfigUpdate(fragment); }
            },
            new ItemHandle() { //CONFIG DELETE
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentConfigDelete.name()); }
                @Override
                public Driver createDriver() { return new HandleConfigDelete(fragment); }
            },
            new ItemHandle() { //SELECT MAQUINA
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentSelectMaquina.name()); }
                @Override
                public Driver createDriver() { return new HandleSelectMaquina(fragment); }
            },
            new ItemHandle() { //SELECT MOLDE
                @Override
                public boolean getId() {return idFragment.equals(Ids.FragmentSelectMolde.name()); }
                @Override
                public Driver createDriver() { return new HandleSelectMolde(fragment); }
            },
            new ItemHandle() { //SELECT CONFIG
                @Override
                public boolean getId() {return idFragment.equals(Ids.FragmentSelectConfig.name()); }
                @Override
                public Driver createDriver() { return new HandleSelectConfig(fragment); }
            }
        );
    }
}