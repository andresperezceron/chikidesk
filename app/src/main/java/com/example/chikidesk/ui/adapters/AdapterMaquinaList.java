package com.example.chikidesk.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chikidesk.R;
import com.example.chikidesk.model.Maquina;

import java.util.List;

public class AdapterMaquinaList extends RecyclerView.Adapter<AdapterMaquinaList.MaquinaViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Maquina maquina);
    }

    private final List<Maquina> maquinas; // Lista de nombres únicos
    private final OnItemClickListener listener;

    public AdapterMaquinaList(List<Maquina> maquinas, OnItemClickListener listener) {
        this.maquinas = maquinas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MaquinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_generic_list, parent, false);
        return new MaquinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaquinaViewHolder holder, int position) {
        holder.bind(maquinas.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return maquinas.size();
    }

    public static class MaquinaViewHolder extends RecyclerView.ViewHolder {
        private final TextView textNombre;

        public MaquinaViewHolder(@NonNull View itemView) {
            super(itemView);
            textNombre = itemView.findViewById(R.id.txvItemList);
        }
        public void bind(Maquina maquina, OnItemClickListener listener) {
            textNombre.setText(maquina.getNombre());
            itemView.setOnClickListener(v  -> {
                if(listener != null)
                    listener.onItemClick(maquina);
            });
        }
    }
}