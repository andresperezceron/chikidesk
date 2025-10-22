package com.example.chikidesk.handles;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMoldeShowBinding;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.fragments.FragmentMoldeShow;
import com.example.chikidesk.utils.ImageManager;
import com.example.chikidesk.utils.ImgPickerHelper;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.io.File;
import java.util.Objects;

public class HandleMoldeShow extends HandleFragment<FragmentMoldeShow,FragmentMoldeShowBinding, Integer> {
    private ImageManager imageManager;
    private ImgPickerHelper piker;
    private Bundle wellKnownNextBundle;

    private Molde molde;

    public HandleMoldeShow(AppCacheViewModel appCache, FragmentMoldeShow fragment) {
        super(appCache, fragment);
    }

    @Override
    public FragmentMoldeShowBinding setBinding(@NonNull FragmentMoldeShowBinding binding) {
        super.binding = binding;
        imageManager = new ImageManager(getContext(), "molde_", "jpg");
        File destinationFile = imageManager.getImageFile(id);
        piker = new ImgPickerHelper(fragment, destinationFile, binding.imgMoldeShow);
        wellKnownNextBundle = new Bundle();
        wellKnownNextBundle.putInt("id", id);
        molde = appCache.moldeList.stream()
                .filter(m -> Objects.equals(m.getId(), id))
                .findFirst().orElse(null);
        return binding;
    }

    @Override
    public void populateForm() {
        binding.edtMoldeShowNombre.setText(molde.getNombre());
        binding.edtMoldeShowRef.setText(molde.getReferencia());
        binding.edtMoldeShowDesc.setText(molde.getDescripcion());
        imageManager.loadImageInto(id, binding.imgMoldeShow);
    }

    @Override
    public void setupListener() {
        binding.fabMoldeShowSelectImg.setOnClickListener(v -> piker.launch());

        binding.fabMoldeShowDeleteImg.setOnClickListener(v -> {
            if(imageManager.deleteImage(id)) {
                binding.imgMoldeShow.setImageBitmap(null); // Limpia la vista
                Toast.makeText(fragment.getContext(), R.string.log_del_img, Toast.LENGTH_SHORT).show();
            } else {
                // Opcional: mostrar un toast de que no había imagen que borrar
                Toast.makeText(fragment.getContext(), "No había imagen para borrar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setupNavigationButtons() {
        binding.fabMoldeShowBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());

        binding.fabMoldeShowHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));

        binding.fabMoldeShowUpdate.setOnClickListener(v -> {
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_moldeShow_to_moldeUpdate, wellKnownNextBundle);
        });

        binding.fabMoldeShowDelete.setOnClickListener(v -> {
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_moldeShow_to_moldeDelete, wellKnownNextBundle);
        });
    }

    @Override
    public void destroyHandle() {
        super.onDestroyHandle();
        this.imageManager = null;
        this.piker = null;
        this.wellKnownNextBundle = null;
        this.molde = null;
    }

    @Override
    protected void setKeysByBundle() {
        id = getBundle() != null ? getBundle().getInt("id") : 0;
    }
}