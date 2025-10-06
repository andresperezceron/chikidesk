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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMoldeDeleteBinding;
import com.example.chikidesk.db.ConfigDao;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;

import java.io.File;

public class FragmentMoldeDelete extends Fragment {
    private FragmentMoldeDeleteBinding binding;

    public FragmentMoldeDelete() {
        super(R.layout.fragment_molde_delete);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMoldeDeleteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Molde molde = getArguments() != null ? getArguments().getParcelable("molde") : null;
        assert molde != null;

        ConfigDao daoConfig = new ConfigDao(getContext());
        int totalConfig = daoConfig.getTotalConfigsByMolde(molde);

        File savedImage = new File(requireContext().getFilesDir(), "molde_" + molde.getId() + ".jpg");
        if(savedImage.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(savedImage.getAbsolutePath());
            binding.imgMoldeDelete.setImageBitmap(bitmap);
        }

        String msn = getString(R.string.msn_delete1) + " " + totalConfig + " " +
                getString(R.string.msn_delete2) + getString(R.string.msn_delete3);
        binding.txvMoldeDeleteAlert.setText(msn);
        binding.edtMoldeDeleteNombre.setText(molde.getNombre());
        binding.edtMoldeDeleteRef.setText(molde.getReferencia());

        binding.btnMoldeDeleteDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setMessage(R.string.dialog_del_molde)
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.dialog_confirm),
                            (dialogInterface, i) -> {
                                MoldeDao dao = new MoldeDao(getContext());
                                if(dao.delete(molde)) {
                                    Toast.makeText(getContext(),
                                            getString(R.string.msn_del_molde_ok),
                                            Toast.LENGTH_SHORT).show();
                                } else Log.e(getString(R.string.log_tag_delete),
                                        getString(R.string.log_del_molde));
                                if(savedImage.exists()) {
                                    binding.imgMoldeDelete.setImageBitmap(null);
                                    if(savedImage.delete())
                                        Log.d("", getString(R.string.msn_img_delete));
                                }
                                Navigation.findNavController(v).
                                        navigate(R.id.action_moldeDelete_to_moldeList);
                            })
                    .setNegativeButton("No", null)
                    .show();
        });

        binding.fabMoldeDeleteBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeDeleteHome.setOnClickListener(v->
                Navigation.findNavController(v).navigate(R.id.action_moldeDelete_to_home));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}