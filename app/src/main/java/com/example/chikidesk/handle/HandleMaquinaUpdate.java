package com.example.chikidesk.handle;

import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.check.update.CheckUpdateMaquina;
import com.example.chikidesk.databinding.MaquinaUpdateBinding;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.ui.fragment.MainFragment;

import java.util.Comparator;
import java.util.stream.Collectors;

public class HandleMaquinaUpdate extends Handle<MainFragment, Integer> {
    private MaquinaUpdateBinding binding;
    private Maquina oldMaquina;

    public HandleMaquinaUpdate(MainFragment fragment) {
        super(fragment);
        this.binding = (MaquinaUpdateBinding) super.binding;
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
        super.id = getBundle() != null ? getBundle().getInt("id") : 0;
        assert id != 0;
    }

    @Override
    protected void initProperties() {
        oldMaquina = appCache.maquinaList.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    protected void driveActionDao() {
        CheckUpdateMaquina check = new CheckUpdateMaquina(appCache, binding, oldMaquina);
        MaquinaDao dao = new MaquinaDao(getContext());
        if(check.isAreEqualsToUpdate()) {
            Toast.makeText(getContext(),"Sin cambios. Nada que actualizar",
                    Toast.LENGTH_SHORT).show();
            Navigation.findNavController(getView()).popBackStack();
            return;
        }

        if(!check.isSuccess()) return;

        appCache.maquinaList = dao.exeCrudAction(check.getEntity(), MaquinaDao.ACTION_UPDATE)
                .stream()
                .sorted(Comparator.comparing(Maquina::getNombre, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());

        if(appCache.getStatus()) {
            Navigation.findNavController(getView()).navigate(R.id.action_maquinaUpdate_to_maquinaList);
            Toast.makeText(getContext(), R.string.tot_upd_maquina, Toast.LENGTH_SHORT).show();
        } else assert false;
    }

    @Override
    protected void populateForm() {
        binding.edtMaquinaUpdateNombre.setText(oldMaquina.getNombre());
        binding.edtMaquinaUpdateRef.setText(oldMaquina.getReferencia());
        binding.edtMaquinaUpdateDesc.setText(oldMaquina.getDescripcion());
    }

    @Override
    protected void setupListeners() {
        binding.btnMaquinaUpdateUpdate.setOnClickListener(v -> driveActionDao());
    }

    @Override
    protected void setupNavigationButtons() {
        binding.fabMaquinaUpdateBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMaquinaUpdateHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    @Override
    public void destroyDriver() {
        oldMaquina = null;
        this.binding = null;
    }

    @Override
    protected void setAdapters() {}
}