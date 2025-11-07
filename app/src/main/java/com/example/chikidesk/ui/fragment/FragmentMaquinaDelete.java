package com.example.chikidesk.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMaquinaDeleteBinding;
import com.example.chikidesk.db.ConfigDao;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.model.Maquina;

import java.io.File;

public class FragmentMaquinaDelete extends Fragment {
    private FragmentMaquinaDeleteBinding binding;

    public FragmentMaquinaDelete() {}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMaquinaDeleteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Maquina maquina = getArguments() != null ? getArguments().getParcelable("maquina") : null;
        assert maquina != null;

        ConfigDao daoConfig = new ConfigDao(getContext());
        int totalConfig = daoConfig.getTotalConfigsByMaquina(maquina);

        File savedImage = new File(requireContext().getFilesDir(), "molde_" +
                maquina.getId() + ".jpg");
        if(savedImage.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(savedImage.getAbsolutePath());
            binding.imgMaquinaDelete.setImageBitmap(bitmap);
        }

        String msn = getString(R.string.msn_delete1) + " " + totalConfig + " " +
                getString(R.string.msn_delete2) + getString(R.string.msn_delete3);
        binding.txvMaquinaDeleteAlert.setText(msn);
        binding.edtMaquinaDeleteNombre.setText(maquina.getNombre());
        binding.edtMaquinaDeleteRef.setText(maquina.getReferencia());

        binding.btnMaquinaDeleteDelete.setOnClickListener(v ->
                new AlertDialog.Builder(requireContext())
                        .setMessage(R.string.tot_del_maquina)
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.alert_confirm), (dialogInterface, i) -> {
                            MaquinaDao dao = new MaquinaDao(getContext());
                            /*if(dao.exeCrudAction(maquina, MaquinaDao.ACTION_DELETE)) {
                                Toast.makeText(getContext(),getString(R.string.tot_del_maquina),
                                        Toast.LENGTH_SHORT).show();
                            }else Log.e(getString(R.string.tag_dao_error), getString(R.string.log_del_maquina));*/

                            if(savedImage.exists()) {
                                binding.imgMaquinaDelete.setImageBitmap(null);
                                if(savedImage.delete())
                                    Log.d("",getString(R.string.tot_del_img));
                            }
                            Navigation.findNavController(v).
                                    navigate(R.id.action_maquinaDelete_to_maquinaList);
                        })
                        .setNegativeButton("No", null)
                        .show()
        );

        binding.fabMaquinaDeleteBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMaquinaDeleteHome.setOnClickListener(v->
                Navigation.findNavController(v).navigate(R.id.action_maquinaDelete_to_home));
    }
}