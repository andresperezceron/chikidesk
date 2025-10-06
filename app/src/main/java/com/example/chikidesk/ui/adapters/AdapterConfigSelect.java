package com.example.chikidesk.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chikidesk.R;
import com.example.chikidesk.model.Configuracion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdapterConfigSelect extends RecyclerView.Adapter<AdapterConfigSelect.ConfiguracionViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Configuracion configuracion);
    }

    private final List<Configuracion> configList;
    private final Map<Configuracion, String> mapList;
    private final OnItemClickListener listener;

    public AdapterConfigSelect(Map<Configuracion, String> mapList, OnItemClickListener listener) {
        this.configList = new ArrayList<>(mapList.keySet());
        this.mapList = mapList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ConfiguracionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_generic_list, parent, false);
        return new ConfiguracionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfiguracionViewHolder holder, int position) {
        Configuracion configuracion = configList.get(position);
        String nombreMolde = mapList.get(configuracion);
        holder.bind(configuracion, nombreMolde, listener);
    }

    @Override
    public int getItemCount() {
        return mapList.size();
    }

    public static class ConfiguracionViewHolder extends RecyclerView.ViewHolder {
        private final TextView txvNombreMolde;

        public ConfiguracionViewHolder(@NonNull View itemView) {
            super(itemView);
            txvNombreMolde = itemView.findViewById(R.id.txvItemList);
        }

        public void bind(Configuracion configuracion, String nombreMolde, OnItemClickListener listener) {
            txvNombreMolde.setText(nombreMolde);
            itemView.setOnClickListener(v -> {
                if (listener != null)
                    listener.onItemClick(configuracion);
            });
        }
    }
}
