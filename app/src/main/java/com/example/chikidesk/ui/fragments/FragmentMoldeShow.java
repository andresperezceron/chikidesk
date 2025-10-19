package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMoldeShowBinding;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.utils.ImageManager;
import com.example.chikidesk.utils.ImgPickerHelper;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.io.File;

public class FragmentMoldeShow extends Fragment {
    private FragmentMoldeShowBinding binding;
    private ImgPickerHelper piker;
    private ImageManager imageManager;
    private Molde molde;
    private Bundle miBundle;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCacheViewModel appCache;
        appCache = new ViewModelProvider(requireActivity()).get(AppCacheViewModel.class);
        molde = appCache.getMoldeById(getArguments() != null ?
                getArguments().getInt("id") : 0);
        assert molde != null;
        imageManager = new ImageManager(requireContext(), "molde_", "jpg");
        miBundle = new Bundle();
        miBundle.putInt("id", molde.getId());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMoldeShowBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateForm();
        setupImageButtons();
        setupNavigationButtons();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void populateForm() {
        binding.edtMoldeShowNombre.setText(molde.getNombre());
        binding.edtMoldeShowRef.setText(molde.getReferencia());
        binding.edtMoldeShowDesc.setText(molde.getDescripcion());
        imageManager.loadImageInto(molde.getId(), binding.imgMoldeShow);
    }

    private void setupImageButtons() {
        // --- Lógica de Imagen ---
        // El Piker se crea aquí porque depende del ImageView y del Fragment (para el launcher)
        File destinationFile = imageManager.getImageFile(molde.getId());
        piker = new ImgPickerHelper(this, destinationFile, binding.imgMoldeShow);
        binding.fabMoldeShowSelectImg.setOnClickListener(v -> piker.launch());

        binding.fabMoldeShowDeleteImg.setOnClickListener(v -> {
            if(imageManager.deleteImage(molde.getId())) {
                binding.imgMoldeShow.setImageBitmap(null); // Limpia la vista
                Toast.makeText(requireContext(), R.string.log_del_img, Toast.LENGTH_SHORT).show();
            } else {
                // Opcional: mostrar un toast de que no había imagen que borrar
                Toast.makeText(requireContext(), "No había imagen para borrar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupNavigationButtons() {
        binding.fabMoldeShowBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());

        binding.fabMoldeShowHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));

        binding.fabMoldeShowUpdate.setOnClickListener(v -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_moldeShow_to_moldeUpdate, miBundle);
        });

        binding.fabMoldeShowDelete.setOnClickListener(v -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_moldeShow_to_moldeDelete, miBundle);
        });
    }
}