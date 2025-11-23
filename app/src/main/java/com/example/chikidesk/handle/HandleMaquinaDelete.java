package com.example.chikidesk.handle;

import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.MaquinaDeleteBinding;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.repository.MaquinaRepository;
import com.example.chikidesk.ui.fragment.MainFragment;
import com.example.chikidesk.util.ImageManager;

public class HandleMaquinaDelete extends Handle<MainFragment, Integer> {
    private MaquinaDeleteBinding binding;
    private Maquina maquina;
    private ImageManager imageManager;
    private MaquinaRepository repo;

    public HandleMaquinaDelete(MainFragment fragment) {
        super(fragment);
        this.binding = (MaquinaDeleteBinding) super.binding;
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
        imageManager = new ImageManager(getContext(), "maquina_", "jpg");
        maquina = appCache.maquinaList.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElse(null);
        assert maquina != null;
        repo = new MaquinaRepository(getContext(), appCache);
    }

    @Override
    protected void populateForm() {
        String msn = fragment.getString(R.string.msn_delete1) + " " + totalConfigByMaquina() + " " +
                fragment.getString(R.string.msn_delete2) + fragment.getString(R.string.msn_delete3);
        binding.txvMaquinaDeleteAlert.setText(msn);
        binding.edtMaquinaDeleteNombre.setText(maquina.getNombre());
        binding.edtMaquinaDeleteRef.setText(maquina.getReferencia());
        imageManager.loadImageInto(id, binding.imgMaquinaDelete);
    }

    @Override
    protected void setupListeners() {
        binding.btnMaquinaDeleteDelete.setOnClickListener(v ->
                new AlertDialog.Builder(getContext())
                        .setTitle(R.string.alert_title_aviso)
                        .setMessage(R.string.alert_del_maquina)
                        .setCancelable(false)
                        .setPositiveButton(R.string.alert_confirm,
                                (dialogInterface, i) -> driveActionDao())
                        .setNegativeButton(R.string.alert_no, null)
                        .show()
        );
    }

    @Override
    protected void setupNavigationButtons() {
        binding.fabMaquinaDeleteBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMaquinaDeleteHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    @Override
    public void destroyDriver() {
        maquina = null;
        imageManager = null;
        repo = null;
        this.binding = null;
    }

    @Override
    protected void driveActionDao() {
        boolean success = repo.deleteMaquina(maquina);

        if(success) {
            imageManager.deleteImage(id);
            Navigation.findNavController(getView()).navigate(R.id.action_maquinaDelete_to_maquinaList);
            Toast.makeText(getContext(), "Máquina eliminada correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error al eliminar la máquina y sus configuraciones", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void setAdapters() {}

    private int totalConfigByMaquina() {
        return (int) appCache.configList.stream()
                .filter(c -> c.getId_maquina() == id)
                .count();
    }
}