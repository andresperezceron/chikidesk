package com.example.chikidesk.handle;

import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.check.CheckNewMaquina;
import com.example.chikidesk.databinding.MaquinaFormBinding;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.ui.fragment.MainFragment;

import java.util.Comparator;
import java.util.stream.Collectors;

public class HandleMaquinaForm extends Handle<MainFragment, Integer> {
    private MaquinaFormBinding binding;

    public HandleMaquinaForm(MainFragment fragment) {
        super(fragment);
        this.binding = (MaquinaFormBinding) super.binding;
    }

    @Override
    public void drive() {
        setupListeners();
        setupNavigationButtons();
    }

    @Override
    protected void driveActionDao() {
        CheckNewMaquina check = new CheckNewMaquina(appCache, binding);
        if(check.isNotSuccess()) return;

        MaquinaDao dao = new MaquinaDao(getContext());
        appCache.maquinaList = dao.exeCrudAction(check.getEntity(), MaquinaDao.ACTION_INSERT).stream()
                .sorted(Comparator.comparing(Maquina::getNombre, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());

        if(appCache.getStatus()) {
            Navigation.findNavController(getView()).navigate(R.id.action_moldeForm_to_moldeList);
            Toast.makeText(fragment.requireContext(), R.string.tot_new_molde, Toast.LENGTH_SHORT).show();
        } else assert false;
    }

    @Override
    protected void setupListeners() {
        binding.btnMaquinaFormNew.setOnClickListener(v -> driveActionDao());
    }

    @Override
    protected void setupNavigationButtons() {
        binding.fabMaquinaFormBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMaquinaFormHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    @Override
    public void destroyDriver() {
        this.binding = null;
    }

    @Override
    protected void setKeysByBundle() {}
    @Override
    protected void initProperties() {}
    @Override
    protected void populateForm() {}
    @Override
    protected void setAdapters() {}
}