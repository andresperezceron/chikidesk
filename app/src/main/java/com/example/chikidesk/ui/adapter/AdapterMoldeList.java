package com.example.chikidesk.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chikidesk.R;
import com.example.chikidesk.model.Molde;

import java.util.List;

public class AdapterMoldeList extends RecyclerView.Adapter<AdapterMoldeList.MoldeViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Molde molde);
    }

    private final OnItemClickListener listener;
    private final List<Molde> moldeList;

    public AdapterMoldeList(List<Molde> moldeList, OnItemClickListener listener) {
        this.moldeList = moldeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MoldeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_generic_list, parent, false);
        return new MoldeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoldeViewHolder holder, int position) {
        holder.bind(moldeList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return moldeList.size();
    }

    public static class MoldeViewHolder extends RecyclerView.ViewHolder {
        private final TextView textNombre;

        public MoldeViewHolder(@NonNull View itemView) {
            super(itemView);
            textNombre = itemView.findViewById(R.id.txvItemList);
        }

        public void bind(Molde molde, OnItemClickListener listener) {
            textNombre.setText(molde.getNombre());
            itemView.setOnClickListener(v  -> {
                if(listener != null)
                    listener.onItemClick(molde);
            });
        }
    }
}