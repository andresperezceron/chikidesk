package com.example.chikidesk.handle;

import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.check.CheckUpdateMolde;
import com.example.chikidesk.databinding.MoldeUpdateBinding;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.driver.DriverUpdate;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.ui.fragment.MainFragment;

import java.util.Comparator;
import java.util.stream.Collectors;

public class HandleMoldeUpdate extends Handle<MainFragment, Integer> implements DriverUpdate {
    private MoldeUpdateBinding binding;
    private Molde oldMolde;

    public HandleMoldeUpdate(MainFragment fragment) {
        super(fragment);
        binding = (MoldeUpdateBinding) super.binding;
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
        super.id = getBundle() != null ? getBundle().getInt("id") : 0;
    }

    @Override
    public void initProperties() {
        oldMolde = appCache.moldeList.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public void driveActionDao() {
        CheckUpdateMolde check = new CheckUpdateMolde(appCache, binding, oldMolde);
        MoldeDao dao = new MoldeDao(getContext());
        if(check.isAreEqualsToUpdate()) {
            Toast.makeText(getContext(),"Sin cambios. Nada que actualizar",
                    Toast.LENGTH_SHORT).show();
            Navigation.findNavController(getView()).popBackStack();
            return;
        }

        if(check.isNotSuccess()) return;

        appCache.moldeList = dao.exeCrudAction(check.getEntity(), MoldeDao.ACTION_UPDATE)
                .stream()
                .sorted(Comparator.comparing(Molde::getNombre, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());

        if(appCache.getStatus()) {
            Navigation.findNavController(getView()).navigate(R.id.action_moldeUpdate_to_moldeList);
            Toast.makeText(getContext(), R.string.tot_upd_molde, Toast.LENGTH_SHORT).show();
        } else assert false;
    }

    @Override
    public void populateForm() {
        binding.edtMoldeUpdateNombre.setText(oldMolde.getNombre());
        binding.edtMoldeUpdateRef.setText(oldMolde.getReferencia());
        binding.edtMoldeUpdateDesc.setText(oldMolde.getDescripcion());
    }

    @Override
    public void setupListeners() {
        binding.btnMoldeUpdateUpdate.setOnClickListener(v -> driveActionDao());
    }

    @Override
    public void setupNavigationButtons() {
        binding.fabMoldeUpdateBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeUpdateHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    @Override
    public void destroyDriver() {
        oldMolde = null;
        this.binding = null;
    }

    @Override
    protected void setAdapters() {}
}