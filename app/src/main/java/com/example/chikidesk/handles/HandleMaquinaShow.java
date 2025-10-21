package com.example.chikidesk.handles;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMaquinaShowBinding;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.utils.ImageManager;
import com.example.chikidesk.utils.ImgPickerHelper;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.io.File;

public class HandleMaquinaShow {
    private FragmentMaquinaShowBinding binding;
    private final Maquina maquina;
    private final ImageManager imageManager;
    private ImgPickerHelper piker;
    private final Fragment fragment;
    private final Bundle bundle;

    public HandleMaquinaShow(AppCacheViewModel appCache, int id, ImageManager imageManager,
                           Fragment fragment) {
        assert id != 0;
        this.imageManager = imageManager;
        this.fragment = fragment;
        maquina = appCache.maquinaList.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElse(null);
        bundle = createBundle();
    }

    public void setBinding(FragmentMaquinaShowBinding binding) {
        this.binding = binding;
    }
    public void populateForm() {
        binding.edtMaquinaShowNombre.setText(maquina.getNombre());
        binding.edtMaquinaShowRef.setText(maquina.getReferencia());
        binding.edtMaquinaShowDesc.setText(maquina.getDescripcion());
        imageManager.loadImageInto(maquina.getId(), binding.imgMaquinaShow);
        setupImageButtons();
    }

    public void setupNavigationButtons() {
        binding.fabMaquinaShowBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());

        binding.fabMaquinaShowHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));

        binding.fabMaquinaShowUpdate.setOnClickListener(v -> {
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_moldeShow_to_moldeUpdate, bundle);
        });

        binding.fabMaquinaShowDelete.setOnClickListener(v -> {
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_moldeShow_to_moldeDelete, bundle);
        });
    }

    private void setupImageButtons() {
        File destinationFile = imageManager.getImageFile(maquina.getId());
        piker = new ImgPickerHelper(fragment, destinationFile, binding.imgMaquinaShow);
        binding.fabMaquinaShowSelectImg.setOnClickListener(v -> piker.launch());

        binding.fabMaquinaShowDeleteImg.setOnClickListener(v -> {
            if(imageManager.deleteImage(maquina.getId())) {
                binding.imgMaquinaShow.setImageBitmap(null); // Limpia la vista
                Toast.makeText(fragment.getContext(), R.string.log_del_img, Toast.LENGTH_SHORT).show();
            } else {
                // Opcional: mostrar un toast de que no había imagen que borrar
                Toast.makeText(fragment.getContext(), "No había imagen para borrar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Bundle createBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", maquina.getId());
        return bundle;
    }
}
