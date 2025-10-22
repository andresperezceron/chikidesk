package com.example.chikidesk.handles;

import android.widget.Toast;

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


public class HandleMoldeDelete extends HandleFragment<FragmentMoldeDelete, FragmentMoldeDeleteBinding, Integer> {
    private Molde molde;
    private ImageManager imageManager;
    private MoldeDao dao;

    public HandleMoldeDelete(AppCacheViewModel appCache, FragmentMoldeDelete fragment) {
        super(appCache, fragment);
    }


    @Override
    public FragmentMoldeDeleteBinding setBinding(FragmentMoldeDeleteBinding binding) {
        super.binding = binding;
        this.dao = new MoldeDao(getContext());
        imageManager = new ImageManager(getContext(), "molde_", "jpg");
        molde = appCache.moldeList.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElse(null);
        return binding;
    }

    @Override
    public void setupListener() {
        binding.btnMoldeDeleteDelete.setOnClickListener(v ->
                new AlertDialog.Builder(getContext())
                        .setMessage("Se eliminará el molde de forma permanente.")
                        .setCancelable(false)
                        .setPositiveButton("Si estoy de acuerdo",
                                (dialogInterface, i) -> delete())
                        .setNegativeButton("No", null)
                        .show()
        );
    }

    private void delete() {
        List<Molde> list = dao.exeCrudAction(molde, MoldeDao.ACTION_DELETE);
        if(list != null) {
            appCache.moldeList = list.stream()
                    .sorted(Comparator.comparing(Molde::getNombre, String.CASE_INSENSITIVE_ORDER))
                    .collect(Collectors.toList());
            imageManager.deleteImage(id);
            Navigation.findNavController(getView()).navigate(R.id.action_moldeDelete_to_moldeList);
            Toast.makeText(getContext(), "Molde Eliminado", Toast.LENGTH_SHORT).show();
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
        imageManager.loadImageInto(id, binding.imgMoldeDelete);
    }

    @Override
    public void setupNavigationButtons() {
        binding.fabMoldeDeleteBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeDeleteHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_moldeDelete_to_home));
    }

    @Override
    public void destroyHandle() {
        super.onDestroyHandle();
        dao = null;
        molde = null;
        imageManager = null;
    }

    @Override
    protected void setKeysByBundle() {
        id = getBundle() != null ? getBundle().getInt("id") : 0;
    }


    private int totalConfigByMolde() {
        return (int) appCache.configList.stream()
                .filter(c -> c.getId_molde() == id)
                .count();
    }
}