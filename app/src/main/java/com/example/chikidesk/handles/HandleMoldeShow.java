package com.example.chikidesk.handles;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMoldeShowBinding;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.utils.ImageManager;
import com.example.chikidesk.utils.ImgPickerHelper;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.io.File;
import java.util.Objects;

public class HandleMoldeShow {
    private FragmentMoldeShowBinding binding;
    private final Molde molde;
    private final ImageManager imageManager;
    private ImgPickerHelper piker;
    private final Fragment fragment;
    private final Bundle bundle;

    public HandleMoldeShow(AppCacheViewModel appCache, int id, ImageManager imageManager,
                           Fragment fragment) {
        molde = appCache.moldeList.stream()
                .filter(m -> Objects.equals(m.getId(), id))
                .findFirst().orElse(null);
        this.imageManager = imageManager;
        this.fragment = fragment;
        bundle = createNextBundle();
    }

    public void setBinding(FragmentMoldeShowBinding binding) {
        this.binding = binding;
    }

    public void populateForm() {
        binding.edtMoldeShowNombre.setText(molde.getNombre());
        binding.edtMoldeShowRef.setText(molde.getReferencia());
        binding.edtMoldeShowDesc.setText(molde.getDescripcion());
        imageManager.loadImageInto(molde.getId(), binding.imgMoldeShow);
        setupImageButtons();
    }

    public void setupNavigationButtons() {
        binding.fabMoldeShowBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());

        binding.fabMoldeShowHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));

        binding.fabMoldeShowUpdate.setOnClickListener(v -> {
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_moldeShow_to_moldeUpdate, bundle);
        });

        binding.fabMoldeShowDelete.setOnClickListener(v -> {
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_moldeShow_to_moldeDelete, bundle);
        });
    }

    private void setupImageButtons() {
        File destinationFile = imageManager.getImageFile(molde.getId());
        piker = new ImgPickerHelper(fragment, destinationFile, binding.imgMoldeShow);
        binding.fabMoldeShowSelectImg.setOnClickListener(v -> piker.launch());

        binding.fabMoldeShowDeleteImg.setOnClickListener(v -> {
            if(imageManager.deleteImage(molde.getId())) {
                binding.imgMoldeShow.setImageBitmap(null); // Limpia la vista
                Toast.makeText(fragment.getContext(), R.string.log_del_img, Toast.LENGTH_SHORT).show();
            } else {
                // Opcional: mostrar un toast de que no había imagen que borrar
                Toast.makeText(fragment.getContext(), "No había imagen para borrar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Bundle createNextBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", molde.getId());
        return bundle;
    }
}
