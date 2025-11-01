package com.example.chikidesk.handle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chikidesk.databinding.FragmentSelectConfigBinding;
import com.example.chikidesk.ui.fragment.BaseFragment;
import com.example.chikidesk.ui.fragment.FragmentSelectConfig;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class HandleSelectConfig extends BaseHandle<BaseFragment, Integer> {
    public HandleSelectConfig(@NonNull AppCacheViewModel appCache, @NonNull BaseFragment fragment) {
        super(fragment);
    }

    @Override
    public void drive() {

    }

    @Override
    protected void driveActionDao() {

    }

    @Override
    protected void setAdapters() {

    }

    @Override
    public void initProperties() {

    }

    @Override
    public void setupListeners() {

        /*

        ConfigDao dao = new ConfigDao(getContext());
        Map<Configuracion, String> mapList = dao.getListToNewConfig(maquina);

        FloatingActionButton fabHome = view.findViewById(R.id.fabSelectConfigHome);
        FloatingActionButton fabBack = view.findViewById(R.id.fabSelectConfigBack);

        AdapterConfigSelect.OnItemClickListener listener = configuracion -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("configuracion", configuracion);
            NavHostFragment.findNavController(this)
            .navigate(R.id.action_selectConfig_to_configShow, bundle);
        };

        recyclerView.setAdapter(new AdapterConfigSelect(mapList, listener));

        fabHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_selectConfig_to_home));
        fabBack.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());
         */
    }

    @Override
    public void populateForm() {
        //binding.rcvSelectConfig.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        //binding.rcvSelectConfig.setHasFixedSize(true);

    }

    @Override
    public void setupNavigationButtons() {

    }

    @Override
    protected void destroyDriver() {

    }

    public void destroyHandle() {
        //super.onDestroyDriver();

    }

    @Override
    protected void setKeysByBundle() {
        super.id = getBundle() != null ? getBundle().getInt("id") : 0;
    }
}
