package com.example.chikidesk.handle;

import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMoldeDeleteBinding;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.fragment.BaseFragment;
import com.example.chikidesk.ui.fragment.FragmentMoldeDelete;
import com.example.chikidesk.util.ImageManager;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class HandleMoldeDelete extends BaseHandle<BaseFragment, Integer> {
    private Molde molde;
    private ImageManager imageManager;
    private MoldeDao dao;

    public HandleMoldeDelete(AppCacheViewModel appCache, BaseFragment fragment) {
        super(appCache, fragment);
    }

    @Override
    public void drive() {

    }

    @Override
    protected void driveActionDao() {
        List<Molde> list = dao.exeCrudAction(molde, MoldeDao.ACTION_DELETE);
        if(list != null) {
            appCache.moldeList = list.stream()
                    .sorted(Comparator.comparing(Molde::getNombre, String.CASE_INSENSITIVE_ORDER))
                    .collect(Collectors.toList());
            //imageManager.deleteImage(id);
            Navigation.findNavController(getView()).navigate(R.id.action_moldeDelete_to_moldeList);
            Toast.makeText(getContext(), "Molde Eliminado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void setAdapters() {

    }

    @Override
    public void initProperties() {
        /*dao = new MoldeDao(getContext());
        imageManager = new ImageManager(getContext(), "molde_", "jpg");
        molde = appCache.moldeList.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElse(null);*/
    }

    @Override
    public void setupListeners() {
       /* binding.btnMoldeDeleteDelete.setOnClickListener(v ->
                new AlertDialog.Builder(getContext())
                        .setMessage("Se eliminará el molde de forma permanente.")
                        .setCancelable(false)
                        .setPositiveButton("Si estoy de acuerdo",
                                (dialogInterface, i) -> driveActionDao())
                        .setNegativeButton("No", null)
                        .show()
        );*/
    }

    @Override
    public void populateForm() {
       /* String msn = "Se ELIMINARAN " + totalConfigByMolde() + "\n\nCuando eliminamos un molde o " +
            "una maquina, también se eliminan las configuraciones donde aparezcan. Los campos en " +
            "rojo tomarán valores por defecto.";
        binding.txvMoldeDeleteAlert.setText(msn);
        binding.edtMoldeDeleteNombre.setText(molde.getNombre());
        binding.edtMoldeDeleteRef.setText(molde.getReferencia());
        imageManager.loadImageInto(id, binding.imgMoldeDelete);*/
    }

    @Override
    public void setupNavigationButtons() {
        /*binding.fabMoldeDeleteBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeDeleteHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));*/
    }

    @Override
    protected void destroyDriver() {

    }

    public void destroyHandle() {
        //super.onDestroyDriver();
        dao = null;
        molde = null;
        imageManager = null;
    }

    @Override
    protected void setKeysByBundle() {
        //id = getBundle() != null ? getBundle().getInt("id") : 0;
    }


    /*private int totalConfigByMolde() {
        return (int) appCache.configList.stream()
                .filter(c -> c.getId_molde() == id)
                .count();
    }*/
}