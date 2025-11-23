package com.example.chikidesk.handle;

import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.MoldeDeleteBinding;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.repository.MoldeRepository;
import com.example.chikidesk.ui.fragment.MainFragment;
import com.example.chikidesk.util.ImageManager;


public class HandleMoldeDelete extends Handle<MainFragment, Integer> {
    private MoldeDeleteBinding binding;
    private Molde molde;
    private ImageManager imageManager;
    private MoldeRepository repo;

    public HandleMoldeDelete(MainFragment fragment) {
        super(fragment);
        binding = (MoldeDeleteBinding) super.binding;
    }

    @Override
    public void drive() {
        setKeysByBundle();
        initProperties();
        populateForm();
        setupListeners();
        setupNavigationButtons();
    }

    @Override
    protected void setKeysByBundle() {
        id = getBundle() != null ? getBundle().getInt("id") : 0;
        assert id != 0;
    }

    @Override
    protected void initProperties() {
        imageManager = new ImageManager(getContext(), "molde_", "jpg");
        molde = appCache.moldeList.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElse(null);
        assert molde != null;
        repo = new MoldeRepository(getContext(), appCache);
    }

    @Override
    protected void populateForm() {
        String msn = fragment.getString(R.string.msn_delete1) + " " + totalConfigByMolde() + " " +
                fragment.getString(R.string.msn_delete2) + fragment.getString(R.string.msn_delete3);
        binding.txvMoldeDeleteAlert.setText(msn);
        binding.edtMoldeDeleteNombre.setText(molde.getNombre());
        binding.edtMoldeDeleteRef.setText(molde.getReferencia());
        imageManager.loadImageInto(id, binding.imgMoldeDelete);
    }

    @Override
    protected void driveActionDao() {
        boolean success = repo.deleteMolde(molde);

        if(success) {
            imageManager.deleteImage(id);
            Navigation.findNavController(getView()).navigate(R.id.action_moldeDelete_to_moldeList);
            Toast.makeText(getContext(), R.string.tot_del_molde, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error al eliminar el molde y sus configuraciones", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void setupListeners() {
        binding.btnMoldeDeleteDelete.setOnClickListener(v ->
                new AlertDialog.Builder(getContext())
                        .setTitle(R.string.alert_title_aviso)
                        .setMessage(R.string.alert_del_molde)
                        .setCancelable(false)
                        .setPositiveButton(R.string.alert_confirm,
                                (dialogInterface, i) -> driveActionDao())
                        .setNegativeButton(R.string.alert_no, null)
                        .show()
        );
    }

    @Override
    protected void setupNavigationButtons() {
        binding.fabMoldeDeleteBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeDeleteHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    @Override
    public void destroyDriver() {
        molde = null;
        imageManager = null;
        repo = null;
        this.binding = null;
    }

    @Override
    protected void setAdapters() {}

    private int totalConfigByMolde() {
        return (int) appCache.configList.stream()
                .filter(c -> c.getId_molde() == id)
                .count();
    }
}