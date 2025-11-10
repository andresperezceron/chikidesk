package com.example.chikidesk.handle;

import android.os.Bundle;
import android.widget.Toast;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.MaquinaShowBinding;
import com.example.chikidesk.driver.DriverShow;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.ui.fragment.MainFragment;
import com.example.chikidesk.util.ImageManager;
import com.example.chikidesk.util.ImgPickerHelper;

import java.io.File;
import java.util.Objects;

public class HandleMaquinaShow extends Handle<MainFragment, Integer> implements DriverShow {
    private MaquinaShowBinding binding;
    private ImageManager imageManager;
    private Maquina maquina;
    private Bundle wellKnownNextBundle;

    public HandleMaquinaShow(MainFragment fragment) {
        super(fragment);
        this.binding = (MaquinaShowBinding) super.binding;
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
        assert id != 0;
    }

    @Override
    public void initProperties() {
        imageManager = new ImageManager(getContext(), "maquina_", "jpg");
        maquina = appCache.maquinaList.stream()
                .filter(m -> Objects.equals(m.getId(), id))
                .findFirst().orElse(null);
        wellKnownNextBundle = new Bundle();
        wellKnownNextBundle.putInt("id", id);
    }

    @Override
    public void populateForm() {
        binding.edtMaquinaShowNombre.setText(maquina.getNombre());
        binding.edtMaquinaShowRef.setText(maquina.getReferencia());
        binding.edtMaquinaShowDesc.setText(maquina.getDescripcion());
        imageManager.loadImageInto(id, binding.imgMaquinaShow);
    }

    @Override
    public void setupListeners() {
        File destinationFile = imageManager.getImageFile(id);
        ImgPickerHelper piker = new ImgPickerHelper(fragment, destinationFile, binding.imgMaquinaShow);
        binding.fabMaquinaShowSelectImg.setOnClickListener(v -> piker.launch());

        binding.fabMaquinaShowDeleteImg.setOnClickListener(v -> {
            if(imageManager.deleteImage(id)) {
                binding.imgMaquinaShow.setImageBitmap(null);
                Toast.makeText(fragment.getContext(), R.string.tot_del_img, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setupNavigationButtons() {
        binding.fabMaquinaShowBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());

        binding.fabMaquinaShowHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));

        binding.fabMaquinaShowUpdate.setOnClickListener(v ->
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_maquinaShow_to_maquinaUpdate, wellKnownNextBundle));

        binding.fabMaquinaShowDelete.setOnClickListener(v ->
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_maquinaShow_to_maquinaDelete, wellKnownNextBundle));
    }

    @Override
    public void destroyDriver() {
        this.imageManager = null;
        this.maquina = null;
        this.wellKnownNextBundle = null;
        this.binding = null;
    }

    @Override
    protected void driveActionDao() {}
    @Override
    protected void setAdapters() {}
}