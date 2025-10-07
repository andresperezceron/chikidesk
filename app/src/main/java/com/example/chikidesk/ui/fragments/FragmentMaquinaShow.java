package com.example.chikidesk.ui.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMaquinaShowBinding;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.utils.ImgPickerHelper;

import java.io.File;


public class FragmentMaquinaShow extends Fragment {
    private FragmentMaquinaShowBinding binding;
    private ImgPickerHelper piker;

    public FragmentMaquinaShow() {}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMaquinaShowBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Maquina maquina = getArguments() != null ? getArguments().getParcelable("maquina") : null;
        assert maquina != null;

        binding.edtMaquinaShowNombre.setText(maquina.getNombre());
        binding.edtMaquinaShowRef.setText(maquina.getReferencia());
        binding.edtMaquinaShowDesc.setText(maquina.getDescripcion());

        File savedImage = new File(requireContext().getFilesDir(),
                "maquina_" + maquina.getId() + ".jpg");
        if(savedImage.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(savedImage.getAbsolutePath());
            binding.imgMaquinaShow.setImageBitmap(bitmap);
        }

        piker = new ImgPickerHelper(this, maquina.getId(), "maquina_",
                "jpg", binding.imgMaquinaShow);
        binding.fabMaquinaShowSelectImg.setOnClickListener(v -> piker.launch());

        binding.fabMaquinaShowUpdate.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_maquinaShow_to_maquinaUpdate, bundle);
        } );

        binding.fabMaquinaShowDelete.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_maquinaShow_to_maquinaDelete, bundle);
        });

        binding.fabMaquinaShowBack.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_maquinaShow_to_maquinaList));
        binding.fabMaquinaShowHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_maquinaShow_to_home));

        binding.fabMaquinaShowDeleteImg.setOnClickListener(v -> {
            if(savedImage.exists()) {
                binding.imgMaquinaShow.setImageBitmap(null);
                if(savedImage.delete()) {
                    Toast.makeText(requireContext(), R.string.tot_del_img,
                            Toast.LENGTH_SHORT).show();
                } else Log.d(getString(R.string.tag_img_error),
                        getString(R.string.log_del_img_maquina));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}