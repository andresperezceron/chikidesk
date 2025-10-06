package com.example.chikidesk.ui.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.db.ConfigDao;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.model.Maquina;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

public class FragmentMaquinaDelete extends Fragment {
    public FragmentMaquinaDelete() { super(R.layout.fragment_maquina_delete); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Maquina maquina = getArguments() != null ? getArguments().getParcelable("maquina") : null;
        assert maquina != null;

        ConfigDao daoConfig = new ConfigDao(getContext());
        int totalConfig = daoConfig.getTotalConfigsByMaquina(maquina);
        daoConfig.close();

        TextView txvTotalConfig = view.findViewById(R.id.txvMaquinaDeleteAlert);
        EditText edtNombre = view.findViewById(R.id.edtMaquinaDeleteNombre);
        EditText edtRef = view.findViewById(R.id.edtMaquinaDeleteRef);
        Button btnDelete = view.findViewById(R.id.btnMaquinaDeleteDelete);
        FloatingActionButton fabBack = view.findViewById(R.id.fabMaquinaDeleteBack);
        FloatingActionButton fabHome = view.findViewById(R.id.fabMaquinaDeleteHome);
        ImageView imageView = view.findViewById(R.id.imgMaquinaDelete);

        File savedImage = new File(requireContext().getFilesDir(), "molde_" + maquina.getId() + ".jpg");
        if(savedImage.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(savedImage.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        }

        String msn = getString(R.string.msn_delete1) + " " + totalConfig + " " +
                getString(R.string.msn_delete2) + getString(R.string.msn_delete3);
        txvTotalConfig.setText(msn);
        edtNombre.setText(maquina.getNombre());
        edtRef.setText(maquina.getReferencia());

        btnDelete.setOnClickListener(v ->
                new AlertDialog.Builder(requireContext())
                        .setMessage(R.string.msn_delete_maquina)
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.dialog_confirm), (dialogInterface, i) -> {
                            MaquinaDao dao = new MaquinaDao(getContext());
                            if(dao.delete(maquina)) {
                                Toast.makeText(getContext(),getString(R.string.msn_delete_maquina),
                                        Toast.LENGTH_SHORT).show();
                            }

                            if(savedImage.exists()) {
                                imageView.setImageBitmap(null);
                                if(savedImage.delete())
                                    Log.d("", "");
                            }
                            Navigation.findNavController(v).navigate(R.id.action_maquinaDelete_to_maquinaList);
                        })
                        .setNegativeButton("No", null)
                        .show()
        );

        fabBack.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());
        fabHome.setOnClickListener(v->
                Navigation.findNavController(v).navigate(R.id.action_maquinaDelete_to_home));
    }
}