package com.example.chikidesk.handle;

import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.MaquinaDeleteBinding;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.driver.DriverDelete;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.ui.fragment.MainFragment;
import com.example.chikidesk.util.ImageManager;

import java.util.Comparator;
import java.util.stream.Collectors;

public class HandleMaquinaDelete extends Handle<MainFragment, Integer> implements DriverDelete {
    private MaquinaDeleteBinding binding;
    private Maquina maquina;
    private ImageManager imageManager;

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
    public void setKeysByBundle() {
        id = getBundle() != null ? getBundle().getInt("id") : 0;
        assert id != 0;
    }

    @Override
    public void initProperties() {
        imageManager = new ImageManager(getContext(), "maquina_", "jpg");
        maquina = appCache.maquinaList.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public void populateForm() {
        String msn = fragment.getString(R.string.msn_delete1) + " " + totalConfigByMaquina() + " " +
                fragment.getString(R.string.msn_delete2) + fragment.getString(R.string.msn_delete3);
        binding.txvMaquinaDeleteAlert.setText(msn);
        binding.edtMaquinaDeleteNombre.setText(maquina.getNombre());
        binding.edtMaquinaDeleteRef.setText(maquina.getReferencia());
        imageManager.loadImageInto(id, binding.imgMaquinaDelete);
    }

    @Override
    public void setupListeners() {
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
    public void setupNavigationButtons() {
        binding.fabMaquinaDeleteBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMaquinaDeleteHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    @Override
    public void destroyDriver() {
        maquina = null;
        imageManager = null;
        this.binding = null;
    }

    @Override
    protected void driveActionDao() {
        MaquinaDao dao = new MaquinaDao(getContext());
        appCache.maquinaList = dao.exeCrudAction(maquina, MaquinaDao.ACTION_DELETE).stream()
                .sorted(Comparator.comparing(Maquina::getNombre, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());

        if(appCache.getStatus()) {
            imageManager.deleteImage(id);
            Navigation.findNavController(getView()).navigate(R.id.action_maquinaDelete_to_maquinaList);
            Toast.makeText(getContext(), R.string.tot_del_molde, Toast.LENGTH_SHORT).show();
        } else assert false;
    }

    @Override
    protected void setAdapters() {}

    private int totalConfigByMaquina() {
        return (int) appCache.configList.stream()
                .filter(c -> c.getId_maquina() == id)
                .count();
    }
}