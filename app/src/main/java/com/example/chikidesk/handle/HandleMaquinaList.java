package com.example.chikidesk.handle;

import androidx.annotation.NonNull;

import com.example.chikidesk.driver.DriverList;
import com.example.chikidesk.ui.fragment.MainFragment;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class HandleMaquinaList
        extends Handle<MainFragment, Integer> implements DriverList{


    public HandleMaquinaList(@NonNull AppCacheViewModel appCache, @NonNull MainFragment fragment) {
        super(fragment);
    }

    @Override
    public void drive() {
        setAdapters();
        populateForm();
        setupNavigationButtons();
    }

    @Override
    public void setAdapters() {
        /*binding.rcvMaquinaList.setAdapter(new AdapterMaquinaList(appCache.maquinaList, maquina -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", maquina.getId());
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_maquinaList_to_maquinaShow, bundle);
        }));*/
    }

    @Override
    public void populateForm() {

    }

    @Override
    public void setupNavigationButtons() {

    }

    @Override
    public void destroyDriver() {
        //super.onDestroyDriver();
    }

    @Override
    protected void setKeysByBundle() {

    }

    @Override
    protected void initProperties() {

    }

    @Override
    protected void driveActionDao() {

    }

    @Override
    protected void setupListeners() {

    }
}