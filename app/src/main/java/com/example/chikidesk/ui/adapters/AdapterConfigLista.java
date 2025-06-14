package com.example.chikidesk.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chikidesk.R;
import com.example.chikidesk.model.Maquina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterConfigLista extends RecyclerView.Adapter<AdapterConfigLista.ConfiguracionViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Maquina maquina);
    }

    private final Map<Maquina, Integer> mapConfiguracion;
    private final ArrayList<Maquina> keyList;
    private final OnItemClickListener listener;

    public AdapterConfigLista(Map<Maquina, Integer> mapConfiguracion, OnItemClickListener listener) {
        this.mapConfiguracion = mapConfiguracion;
        keyList = new ArrayList<>(mapConfiguracion.keySet());
        this.listener = listener;
    }

    @NonNull
    @Override
    public ConfiguracionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_config_lista, parent, false);
        return new AdapterConfigLista.ConfiguracionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfiguracionViewHolder holder, int position) {
        holder.bind(keyList.get(position), mapConfiguracion.get(keyList.get(position)), listener);
    }

    @Override
    public int getItemCount() {
        return mapConfiguracion.size();
    }

    public static class ConfiguracionViewHolder extends RecyclerView.ViewHolder {
        private final TextView txvNombreMaquina;
        private final TextView txvTotalConfiguraciones;

        public ConfiguracionViewHolder(@NonNull View itemView) {
            super(itemView);
            txvNombreMaquina = itemView.findViewById(R.id.txvNombreMolde);
            txvTotalConfiguraciones = itemView.findViewById(R.id.txvTotalConfiguraciones);
        }

        public void bind(Maquina maquina, Integer total, OnItemClickListener listener) {
            String mostrandoTotal = "Configuraciones: " + total;
            txvNombreMaquina.setText(maquina.getNombre());
            txvTotalConfiguraciones.setText(mostrandoTotal);

            itemView.setOnClickListener(v  -> {
                if(listener != null)
                    listener.onItemClick(maquina);
            });
        }
    }
}
