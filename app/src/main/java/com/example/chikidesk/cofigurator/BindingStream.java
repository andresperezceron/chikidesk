package com.example.chikidesk.cofigurator;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.example.chikidesk.databinding.ConfigListBinding;
import com.example.chikidesk.databinding.MoldeDeleteBinding;
import com.example.chikidesk.databinding.MoldeFormBinding;
import com.example.chikidesk.databinding.MoldeListBinding;
import com.example.chikidesk.databinding.MoldeShowBinding;
import com.example.chikidesk.databinding.MoldeUpdateBinding;
import com.example.chikidesk.ui.fragment.BaseFragment;

import java.util.stream.Stream;

public class BindingStream {
    public Stream<ItemBinding> stream(@NonNull BaseFragment fragment) {
        String idFragment = fragment.getClass().getSimpleName();
        return Stream.of(
            new ItemBinding() {
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
            new ItemBinding() {
                @Override
                public boolean getId() { return idFragment.equals(Ids.FragmentMoldeShow.name()); }
                @Override
                public ViewBinding createBinding() {
                    return MoldeShowBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() {
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
            new ItemBinding() {
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
            new ItemBinding() {
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentMoldeDelete.name());
                }
                @Override
                public ViewBinding createBinding() {
                    return MoldeDeleteBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            },
            new ItemBinding() {
                @Override
                public boolean getId() {
                    return idFragment.equals(Ids.FragmentConfigList.name());
                }
                @Override
                public ViewBinding createBinding() {
                    return ConfigListBinding
                            .inflate(fragment.getInflater(), fragment.getContainer(), false);
                }
            }
        );
    }
}
