package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentMoldeDeleteBinding;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.utils.ImageManager;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class FragmentMoldeDelete extends Fragment {
    private FragmentMoldeDeleteBinding binding;
    private AppCacheViewModel appCache;
    private ImageManager imageManager;
    private Molde molde;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appCache = new ViewModelProvider(requireActivity()).get(AppCacheViewModel.class);
        molde = appCache.getMoldeById(getArguments() != null ?
                getArguments().getInt("id") : 0);
        assert molde != null;
        imageManager = new ImageManager(requireContext(), "molde_", "jpg");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMoldeDeleteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        populateForm();
        setupNavigationButtons();

        binding.btnMoldeDeleteDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setMessage(R.string.alert_new_molde)
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.alert_confirm),
                            (dialogInterface, i) -> alertDialogPositive(view))
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void alertDialogPositive(View v) {
        MoldeDao dao = new MoldeDao(getContext());
        if(appCache.setMoldeList(dao.exeCrudAction(molde, MoldeDao.ACTION_DELETE))) {
            imageManager.deleteImage(molde.getId());
            Navigation.findNavController(v).navigate(R.id.action_moldeDelete_to_moldeList);
            Toast.makeText(getContext(), getString(R.string.tot_del_molde), Toast.LENGTH_SHORT).show();
        }
    }
    private void populateForm () {
        String msn = getString(R.string.msn_delete1) + " " + appCache.getTotalConfigByMolde(molde.getId()) + " " +
                getString(R.string.msn_delete2) + getString(R.string.msn_delete3);
        binding.txvMoldeDeleteAlert.setText(msn);
        binding.edtMoldeDeleteNombre.setText(molde.getNombre());
        binding.edtMoldeDeleteRef.setText(molde.getReferencia());
        imageManager.loadImageInto(molde.getId(), binding.imgMoldeDelete);
    }

    private void setupNavigationButtons () {
        binding.fabMoldeDeleteBack.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack());
        binding.fabMoldeDeleteHome.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_moldeDelete_to_home));
    }
}