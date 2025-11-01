package com.example.chikidesk.handle;


import android.os.Bundle;
import android.widget.Toast;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.MoldeShowBinding;
import com.example.chikidesk.driver.DriverShow;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.fragment.BaseFragment;
import com.example.chikidesk.ui.fragment.FragmentMoldeShow;
import com.example.chikidesk.util.ImageManager;
import com.example.chikidesk.util.ImgPickerHelper;

import java.io.File;
import java.util.Objects;

public class HandleMoldeShow extends BaseHandle<BaseFragment, Integer> implements DriverShow {
    private final MoldeShowBinding binding;
    private ImageManager imageManager;
    private Molde molde;

    public HandleMoldeShow(BaseFragment fragment) {
        super(fragment);
        binding = (MoldeShowBinding) super.binding;
    }

    @Override
    public void drive() {
        setKeysByBundle();
        initProperties();
        populateForm();
        setupListeners();
        setupNavigationButtons();
    }

    @Override
    public void setKeysByBundle() {
        id = getBundle() != null ? getBundle().getInt("id") : 0;
    }

    @Override
    public void initProperties() {
        imageManager = new ImageManager(getContext(), "molde_", "jpg");
        molde = appCache.moldeList.stream()
                .filter(m -> Objects.equals(m.getId(), id))
                .findFirst().orElse(null);
    }

    @Override
    public void populateForm() {
        binding.edtMoldeShowNombre.setText(molde.getNombre());
        binding.edtMoldeShowRef.setText(molde.getReferencia());
        binding.edtMoldeShowDesc.setText(molde.getDescripcion());
        imageManager.loadImageInto(id, binding.imgMoldeShow);
    }

    @Override
    public void setupListeners() {
        File destinationFile = imageManager.getImageFile(id);
        ImgPickerHelper piker = new ImgPickerHelper(fragment, destinationFile, binding.imgMoldeShow);
        binding.fabMoldeShowSelectImg.setOnClickListener(v -> piker.launch());

        binding.fabMoldeShowDeleteImg.setOnClickListener(v -> {
            if(imageManager.deleteImage(id)) {
                binding.imgMoldeShow.setImageBitmap(null);
                Toast.makeText(fragment.getContext(), R.string.log_del_img, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(fragment.getContext(),
                        "No había imagen para borrar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setupNavigationButtons() {
        Bundle wellKnownNextBundle = new Bundle();
        wellKnownNextBundle.putInt("id", id);

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
    public void destroyDriver() {
        this.imageManager = null;
        this.molde = null;
    }

    @Override
    protected void driveActionDao() {}
    @Override
    protected void setAdapters() {}
}