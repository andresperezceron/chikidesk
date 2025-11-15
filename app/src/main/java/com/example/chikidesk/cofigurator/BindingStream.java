package com.example.chikidesk.cofigurator;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.example.chikidesk.databinding.ConfigDeleteBinding;
import com.example.chikidesk.databinding.ConfigFormBinding;
import com.example.chikidesk.databinding.ConfigListBinding;
import com.example.chikidesk.databinding.ConfigShowBinding;
import com.example.chikidesk.databinding.ConfigUpdateBinding;
import com.example.chikidesk.databinding.MaquinaDeleteBinding;
import com.example.chikidesk.databinding.MaquinaFormBinding;
import com.example.chikidesk.databinding.MaquinaListBinding;
import com.example.chikidesk.databinding.MaquinaShowBinding;
import com.example.chikidesk.databinding.MaquinaUpdateBinding;
import com.example.chikidesk.databinding.MoldeDeleteBinding;
import com.example.chikidesk.databinding.MoldeFormBinding;
import com.example.chikidesk.databinding.MoldeListBinding;
import com.example.chikidesk.databinding.MoldeShowBinding;
import com.example.chikidesk.databinding.MoldeUpdateBinding;
import com.example.chikidesk.databinding.SelectConfigBinding;
import com.example.chikidesk.databinding.SelectMaquinaBinding;
import com.example.chikidesk.databinding.SelectMoldeBinding;
import com.example.chikidesk.ui.fragment.MainFragment;

import java.util.stream.Stream;

public class BindingStream {
    public Stream<ItemBinding> stream(@NonNull MainFragment fragment) {
        String idFragment = fragment.getClass().getSimpleName();
        return Stream.of(
            new ItemBinding() { //MOLDE LIST
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentMoldeList.name());
                }
                @Override
                public ViewBinding createBinding() {
                    return MoldeListBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { //MOLDE SHOW
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMoldeShow.name()); }
                @Override
                public ViewBinding createBinding() {
                    return MoldeShowBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { //MOLDE FORM
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentMoldeForm.name());
                }
                @Override
                public ViewBinding createBinding() {
                    return MoldeFormBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { //MOLDE UPDATE
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentMoldeUpdate.name());
                }
                @Override
                public ViewBinding createBinding() {
                    return MoldeUpdateBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { /* MOLDE DELETE */
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMoldeDelete.name()); }
                @Override
                public ViewBinding createBinding() {
                    return MoldeDeleteBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { /* MAQUINA LIST */
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMaquinaList.name()); }
                @Override
                public ViewBinding createBinding() {
                    return MaquinaListBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { //MAQUINA SHOW
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMaquinaShow.name()); }
                @Override
                public ViewBinding createBinding() {
                    return MaquinaShowBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { //MAQUINA FORM
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMaquinaForm.name()); }
                @Override
                public ViewBinding createBinding() {
                    return MaquinaFormBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { //MAQUINA UPDATE
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMaquinaUpdate.name()); }
                @Override
                public ViewBinding createBinding() {
                    return MaquinaUpdateBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { //MAQUINA DELETE
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMaquinaDelete.name()); }
                @Override
                public ViewBinding createBinding() {
                    return MaquinaDeleteBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { /* CONFIG LIST */
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentConfigList.name()); }
                @Override
                public ViewBinding createBinding() {
                    return ConfigListBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { //CONFIG FORM
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentConfigForm.name());
                }
                @Override
                public ViewBinding createBinding() {
                    return ConfigFormBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { //CONFIG SHOW
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentConfigShow.name());
                }
                @Override
                public ViewBinding createBinding() {
                    return ConfigShowBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { //CONFIG UPDATE
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentConfigUpdate.name()); }
                @Override
                public ViewBinding createBinding() {
                    return ConfigUpdateBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { //CONFIG DELETE
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentConfigDelete.name()); }
                @Override
                public ViewBinding createBinding() {
                    return ConfigDeleteBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { //SELECT MAQUINA
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentSelectMaquina.name()); }
                @Override
                public ViewBinding createBinding() {
                    return SelectMaquinaBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { //SELECT MOLDE
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentSelectMolde.name());
                }
                @Override
                public ViewBinding createBinding() {
                    return SelectMoldeBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() { //SELECT CONFIG
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentSelectConfig.name()); }
                @Override
                public ViewBinding createBinding() {
                    return SelectConfigBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            }
        );
    }
}