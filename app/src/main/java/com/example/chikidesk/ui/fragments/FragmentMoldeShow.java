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
import com.example.chikidesk.databinding.FragmentMoldeShowBinding;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.utils.ImgPickerHelper;

import java.io.File;

public class FragmentMoldeShow extends Fragment {
    private FragmentMoldeShowBinding binding;
    private ImgPickerHelper piker;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(binding != null) return binding.getRoot();
        binding = FragmentMoldeShowBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Molde molde = getArguments() != null ? getArguments().getParcelable("molde") : null;
        assert molde != null;
        File savedImage = populateForm(molde);

        piker = new ImgPickerHelper(this, molde.getId(), "molde_", "jpg",
                binding.imgMoldeShow);
        binding.fabMoldeShowSelectImg.setOnClickListener(v -> piker.launch());

        binding.fabMoldeShowBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeShowHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));

        binding.fabMoldeShowUpdate.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("molde", molde);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_moldeShow_to_moldeUpdate, bundle);
        } );

        binding.fabMoldeShowDelete.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("molde", molde);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_moldeShow_to_moldeDelete, bundle);
        });

        binding.fabMoldeShowDeleteImg.setOnClickListener(v -> {
            if(savedImage.exists()) {
                binding.imgMoldeShow.setImageBitmap(null);
                if(savedImage.delete()) {
                    Toast.makeText(requireContext(), R.string.log_del_img,
                            Toast.LENGTH_SHORT).show();
                } else Log.d(getString(R.string.tag_img_error),
                        getString(R.string.log_del_img_molde));
            }
        });
    }

    private File populateForm(Molde molde) {
        binding.edtMoldeShowNombre.setText(molde.getNombre());
        binding.edtMoldeShowRef.setText(molde.getReferencia());
        binding.edtMoldeShowDesc.setText(molde.getDescripcion());

        File savedImage = new File(requireContext().getFilesDir(), "molde_" +
                molde.getId() + ".jpg");
        if(savedImage.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(savedImage.getAbsolutePath());
            binding.imgMoldeShow.setImageBitmap(bitmap);
        }
        return savedImage;
    }
}