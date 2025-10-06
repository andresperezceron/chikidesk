package com.example.chikidesk.ui.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.chikidesk.R;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.utils.ImgPickerHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;


public class FragmentMaquinaShow extends Fragment {
    private ImgPickerHelper piker;

    public FragmentMaquinaShow() {
        super(R.layout.fragment_maquina_show);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Maquina maquina = getArguments() != null ? getArguments().getParcelable("maquina") : null;
        assert maquina != null;

        FloatingActionButton fabSelectImg = view.findViewById(R.id.fabMaquinaShowSelectImg);
        FloatingActionButton fabBack = view.findViewById(R.id.fabMaquinaShowBack);
        FloatingActionButton fabHome = view.findViewById(R.id.fabMaquinaShowHome);
        FloatingActionButton fabUpdate = view.findViewById(R.id.fabMaquinaShowUpdate);
        FloatingActionButton fabDelete = view.findViewById(R.id.fabMaquinaShowDelete);
        FloatingActionButton fabDelImg = view.findViewById(R.id.fabMaquinaShowDeleteImg);

        EditText edtNombre = view.findViewById(R.id.edtMaquinaShowNombre);
        EditText edtRef = view.findViewById(R.id.edtMaquinaShowRef);
        EditText edtDesc = view.findViewById(R.id.edtMaquinaShowDesc);
        ImageView imageView = view.findViewById(R.id.imgMaquinaShow);

        edtNombre.setText(maquina.getNombre());
        edtRef.setText(maquina.getReferencia());
        edtDesc.setText(maquina.getDescripcion());

        File savedImage = new File(requireContext().getFilesDir(),
                "maquina_" + maquina.getId() + ".jpg");
        if(savedImage.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(savedImage.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        }

        piker = new ImgPickerHelper(this, maquina.getId(), "maquina_", "jpg", imageView);
        fabSelectImg.setOnClickListener(v -> piker.launch());

        fabBack.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_maquinaShow_to_maquinaList));
        fabHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_maquinaShow_to_home));

        fabUpdate.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_maquinaShow_to_maquinaUpdate, bundle);
        } );

        fabDelete.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("maquina", maquina);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_maquinaShow_to_maquinaDelete, bundle);
        });

        fabDelImg.setOnClickListener(v -> {
            if(savedImage.exists()) {
                imageView.setImageBitmap(null);
                if(savedImage.delete()) {
                    new AlertDialog.Builder(requireContext())
                            .setMessage(R.string.msn_img_delete)
                            .setPositiveButton(getString(R.string.msn_ok), null)
                            .show();
                }
            }
        });
    }
}