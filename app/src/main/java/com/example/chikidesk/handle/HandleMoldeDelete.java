package com.example.chikidesk.handle;

import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.MoldeDeleteBinding;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.driver.DriverDelete;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.fragment.BaseFragment;
import com.example.chikidesk.util.ImageManager;

import java.util.Comparator;
import java.util.stream.Collectors;


public class HandleMoldeDelete extends BaseHandle<BaseFragment, Integer> implements DriverDelete {
    private final MoldeDeleteBinding binding;
    private Molde molde;
    private ImageManager imageManager;

    public HandleMoldeDelete(BaseFragment fragment) {
        super(fragment);
        binding = (MoldeDeleteBinding) super.binding;
    }

    @Override
    public void drive() {
        initProperties();
        populateForm();
        setupListeners();
        setupNavigationButtons();
    }

    @Override
    public void setKeysByBundle() {
        id = getBundle() != null ? getBundle().getInt("id") : 0;
    }

    @Override
    public void initProperties() {
        imageManager = new ImageManager(getContext(), "molde_", "jpg");
        molde = appCache.moldeList.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElse(null);
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
    protected void driveActionDao() {
        MoldeDao dao = new MoldeDao(getContext());
        appCache.moldeList = dao.exeCrudAction(molde, MoldeDao.ACTION_DELETE).stream()
                .sorted(Comparator.comparing(Molde::getNombre, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());

        if(appCache.getStatus()) {
            imageManager.deleteImage(id);
            Navigation.findNavController(getView()).navigate(R.id.action_moldeDelete_to_moldeList);
            Toast.makeText(getContext(), "Molde Eliminado", Toast.LENGTH_SHORT).show();
        } else assert false;
    }

    @Override
    public void setupListeners() {
        binding.btnMoldeDeleteDelete.setOnClickListener(v ->
                new AlertDialog.Builder(getContext())
                        .setMessage("Se eliminará el molde de forma permanente.")
                        .setCancelable(false)
                        .setPositiveButton("Si estoy de acuerdo",
                                (dialogInterface, i) -> driveActionDao())
                        .setNegativeButton("No", null)
                        .show()
        );
    }

    @Override
    public void setupNavigationButtons() {
        binding.fabMoldeDeleteBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeDeleteHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    @Override
    public void destroyDriver() {
        molde = null;
        imageManager = null;
    }

    @Override
    protected void setAdapters() {}

    private int totalConfigByMolde() {
        return (int) appCache.configList.stream()
                .filter(c -> c.getId_molde() == id)
                .count();
    }
}