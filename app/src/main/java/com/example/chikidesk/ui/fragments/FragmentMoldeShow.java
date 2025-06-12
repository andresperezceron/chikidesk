package com.example.chikidesk.ui.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.model.Molde;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FragmentMoldeShow extends Fragment {
    private ActivityResultLauncher<String> imagePickerLauncher;
    private ConstraintLayout rootLayout;

    public FragmentMoldeShow() {
        super(R.layout.fragment_molde_show);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Molde molde = getArguments() != null ? getArguments().getParcelable("molde") : null;
        assert molde != null;

        rootLayout = view.findViewById(R.id.layoutMoldeShow);
        FloatingActionButton fabSelectImg = view.findViewById(R.id.fabSelectImage);
        FloatingActionButton fabNavHome = view.findViewById(R.id.fabNavHome);
        TextView txvNameMolde = view.findViewById(R.id.txvNameMolde);
        TextView txvRefMolde = view.findViewById(R.id.txvRefMolde);
        TextView txvDescMolde = view.findViewById(R.id.txvDescMolde);

        txvNameMolde.setText(molde.getNombre());
        txvRefMolde.setText(molde.getReferencia());
        txvDescMolde.setText(molde.getDescripcion());

        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if(uri != null) {
                        try {
                            File file = saveImageToInternalStorage(uri, "molde_" + molde.getId() + ".jpg");
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            rootLayout.setBackground(new BitmapDrawable(getResources(), bitmap));
                        }catch(IOException e) {e.getMessage();}
                    }
                });

        fabSelectImg.setOnClickListener(v -> {
            imagePickerLauncher.launch("image/*"); // Abre la galería
        });

        fabNavHome.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });

        File savedImage = new File(requireContext().getFilesDir(), "molde_" + molde.getId() + ".jpg");
        if(savedImage.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(savedImage.getAbsolutePath());
            rootLayout.setBackground(new BitmapDrawable(getResources(), bitmap));
        }
    }

    private File saveImageToInternalStorage(Uri uri, String filename) throws IOException {
        File file = new File(requireContext().getFilesDir(), filename);
        try(InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
            FileOutputStream outputStream = new FileOutputStream(file)) {
                if(inputStream != null) {
                byte[] buffer = new byte[4096];
                int len;
                while((len = inputStream.read(buffer)) > 0)
                    outputStream.write(buffer, 0, len);
            }
        }
        return file;
    }
}