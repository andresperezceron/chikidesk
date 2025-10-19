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
import com.example.chikidesk.databinding.FragmentMaquinaShowBinding;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.utils.ImageManager;
import com.example.chikidesk.utils.ImgPickerHelper;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.io.File;


public class FragmentMaquinaShow extends Fragment {
    private FragmentMaquinaShowBinding binding;
    private ImgPickerHelper piker;
    private ImageManager imageManager;
    private Maquina maquina;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCacheViewModel appCache;
        appCache = new ViewModelProvider(requireActivity()).get(AppCacheViewModel.class);
        maquina = appCache.getMaquinaById(getArguments() != null ?
                getArguments().getInt("id") : 0);
        assert maquina != null;
        imageManager = new ImageManager(requireContext(), "molde_", "jpg");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMaquinaShowBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.edtMaquinaShowNombre.setText(maquina.getNombre());
        binding.edtMaquinaShowRef.setText(maquina.getReferencia());
        binding.edtMaquinaShowDesc.setText(maquina.getDescripcion());
        imageManager.loadImageInto(maquina.getId(), binding.imgMaquinaShow);
        setupButtons();
    }

    private void setupButtons() {
        // --- Lógica de Imagen ---
        // El Piker se crea aquí porque depende del ImageView y del Fragment (para el launcher)
        File destinationFile = imageManager.getImageFile(maquina.getId());
        piker = new ImgPickerHelper(this, destinationFile, binding.imgMaquinaShow);

        binding.fabMaquinaShowSelectImg.setOnClickListener(v -> piker.launch());

        binding.fabMaquinaShowDeleteImg.setOnClickListener(v -> {
            if(imageManager.deleteImage(maquina.getId())) {
                binding.imgMaquinaShow.setImageBitmap(null); // Limpia la vista
                Toast.makeText(requireContext(), R.string.log_del_img, Toast.LENGTH_SHORT).show();
            } else {
                // Opcional: mostrar un toast de que no había imagen que borrar
                Toast.makeText(requireContext(), "No había imagen para borrar", Toast.LENGTH_SHORT).show();
            }
        });

        // --- Lógica de Navegación ---
        binding.fabMaquinaShowBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());

        binding.fabMaquinaShowHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));

        binding.fabMaquinaShowUpdate.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", maquina.getId());
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_moldeShow_to_moldeUpdate, bundle);
        });

        binding.fabMaquinaShowDelete.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_moldeShow_to_moldeDelete, bundle);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}