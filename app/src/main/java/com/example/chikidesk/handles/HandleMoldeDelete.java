package com.example.chikidesk.handles;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMoldeDeleteBinding;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.fragments.FragmentMoldeDelete;
import com.example.chikidesk.utils.ImageManager;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class HandleMoldeDelete implements HandleFragment{
    private FragmentMoldeDeleteBinding binding;
    private final Molde molde;
    private final ImageManager imageManager;
    private final MoldeDao dao;
    private final AppCacheViewModel appCache;
    private final FragmentMoldeDelete fragment;
    private final View v;

    public HandleMoldeDelete(@NonNull AppCacheViewModel appCache, MoldeDao dao, int id,
                             ImageManager imageManager, @NonNull FragmentMoldeDelete fragment) {
        this.appCache = appCache;
        this.dao = dao;
        this.imageManager = imageManager;
        this.fragment = fragment;
        v = fragment.getView();
        molde = appCache.moldeList.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElse(null);
    }

    public void delete() {
        new AlertDialog.Builder(fragment.requireContext())
                .setMessage(R.string.alert_new_molde)
                .setCancelable(false)
                .setPositiveButton("Si, estoy de acuerdo",
                        (dialogInterface, i) -> acceptDelete())
                .setNegativeButton("No", null)
                .show();
    }

    private void acceptDelete() {
        List<Molde> list = dao.exeCrudAction(molde, MoldeDao.ACTION_DELETE);
        if(list != null) {
            appCache.moldeList = list.stream()
                    .sorted(Comparator.comparing(Molde::getNombre, String.CASE_INSENSITIVE_ORDER))
                    .collect(Collectors.toList());
            imageManager.deleteImage(molde.getId());
            Navigation.findNavController(v).navigate(R.id.action_moldeDelete_to_moldeList);
            Toast.makeText(v.getContext(), "Molde Eliminado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void populateForm() {
        String msn = "Se ELIMINARAN " + totalConfigByMolde() + "\n\nCuando eliminamos un molde o " +
            "una maquina, también se eliminan las configuraciones donde aparezcan. Los campos en " +
            "rojo tomarán valores por defecto.";
        binding.txvMoldeDeleteAlert.setText(msn);
        binding.edtMoldeDeleteNombre.setText(molde.getNombre());
        binding.edtMoldeDeleteRef.setText(molde.getReferencia());
        imageManager.loadImageInto(molde.getId(), binding.imgMoldeDelete);
    }

    @Override
    public void setupNavigationButtons() {
        binding.fabMoldeDeleteBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeDeleteHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_moldeDelete_to_home));
    }

    @Override
    public void setBinding() {
        this.binding = (FragmentMoldeDeleteBinding) fragment.getBinding();
    }

    private int totalConfigByMolde() {
        return (int) appCache.configList.stream()
                .filter(c -> c.getId_molde() == molde.getId())
                .count();
    }
}