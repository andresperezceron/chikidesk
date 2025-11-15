package com.example.chikidesk.handle;

import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.ConfigDeleteBinding;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.FullConfig;
import com.example.chikidesk.repository.ConfigRepository;
import com.example.chikidesk.ui.fragment.MainFragment;

public class HandleConfigDelete extends Handle<MainFragment, Integer> {
    private ConfigDeleteBinding binding;
    private Configuracion configToDelete;
    private ConfigRepository repo;

    public HandleConfigDelete(MainFragment fragment) {
        super(fragment);
        this.binding = (ConfigDeleteBinding) super.binding;
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
    public void destroyDriver() {
        binding = null;
        configToDelete = null;
        repo = null;
    }

    @Override
    protected void setKeysByBundle() {
        id = getBundle() != null ? getBundle().getInt("id") : 0;
        assert id != 0;
    }

    @Override
    protected void initProperties() {
        configToDelete = appCache.configList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        assert configToDelete != null;

        repo = new ConfigRepository(getContext(), appCache);
    }

    @Override
    protected void populateForm() {
        FullConfig fullConfig = appCache.createFullConfigByConfig(configToDelete);
        assert fullConfig != null;

        String message = String.format(
                "¿Estás seguro de que quieres eliminar la configuración para la máquina '%s' y el molde '%s'?",
                fullConfig.getMaquina().getNombre(),
                fullConfig.getMolde().getNombre()
        );
        binding.tvConfigDeleteMessage.setText(message);
    }

    @Override
    protected void driveActionDao() {
        boolean success = repo.deleteFullConfig(configToDelete);

        if (success) {
            Toast.makeText(getContext(), "Configuración eliminada correctamente", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(getView()).navigate(R.id.action_configDelete_to_configList);
        } else {
            Toast.makeText(getContext(), "Error al eliminar la configuración", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void setupListeners() {
        binding.btnConfigDeleteConfirm.setOnClickListener(v -> driveActionDao());
    }

    @Override
    protected void setupNavigationButtons() {
        binding.btnConfigDeleteCancel.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabConfigDeleteBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabConfigDeleteHome.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack(R.id.fragmentStartApp, false));
    }

    @Override
    protected void setAdapters() {}
}
