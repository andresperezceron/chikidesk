package com.example.chikidesk.handle;

import android.os.Bundle;
import android.widget.Toast;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMoldeShowBinding;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.fragment.BaseFragment;
import com.example.chikidesk.ui.fragment.FragmentMoldeShow;
import com.example.chikidesk.util.ImageManager;
import com.example.chikidesk.util.ImgPickerHelper;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.io.File;
import java.util.Objects;

public class HandleMoldeShow extends BaseHandle<BaseFragment, Integer> {
    private ImageManager imageManager;
    private Molde molde;
    public HandleMoldeShow(AppCacheViewModel appCache, BaseFragment fragment) {
        super(appCache, fragment);
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
    protected void setKeysByBundle() {
        id = getBundle() != null ? getBundle().getInt("id") : 0;
    }

    @Override
    protected void initProperties() {
        imageManager = new ImageManager(getContext(), "molde_", "jpg");
        molde = appCache.moldeList.stream()
                .filter(m -> Objects.equals(m.getId(), id))
                .findFirst().orElse(null);
    }

    @Override
    protected void populateForm() {
        /*binding.edtMoldeShowNombre.setText(molde.getNombre());
        binding.edtMoldeShowRef.setText(molde.getReferencia());
        binding.edtMoldeShowDesc.setText(molde.getDescripcion());
        imageManager.loadImageInto(id, binding.imgMoldeShow);*/
    }

    @Override
    protected void setupListeners() {
        /*File destinationFile = imageManager.getImageFile(id);
        ImgPickerHelper piker = new ImgPickerHelper(fragment, destinationFile, binding.imgMoldeShow);
        binding.fabMoldeShowSelectImg.setOnClickListener(v -> piker.launch());

        binding.fabMoldeShowDeleteImg.setOnClickListener(v -> {
            if(imageManager.deleteImage(id)) {
                binding.imgMoldeShow.setImageBitmap(null); // Limpia la vista
                Toast.makeText(fragment.getContext(), R.string.log_del_img, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(fragment.getContext(), "No había imagen para borrar", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    protected void setupNavigationButtons() {
        /*Bundle wellKnownNextBundle = new Bundle();
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
        });*/
    }

    @Override
    protected void destroyDriver() {

    }

    public void destroyHandle() {
        //super.onDestroyDriver();
        this.imageManager = null;
        this.molde = null;
    }

    @Override
    protected void driveActionDao() {}

    @Override
    protected void setAdapters() {

    }
}