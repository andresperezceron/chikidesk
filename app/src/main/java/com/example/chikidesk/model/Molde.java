package com.example.chikidesk.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.chikidesk.db.IEntity;

public class Molde implements IEntity<Integer>, Parcelable {
    private int id;
    private String nombre;
    private String referencia;
    private String descripcion;

    public Molde(int id, String nombre, String referencia, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.referencia = referencia;
        this.descripcion = descripcion;
    }

    protected Molde(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        referencia = in.readString();
        descripcion = in.readString();
    }

    public static final Creator<Molde> CREATOR = new Creator<>() {
        @Override
        public Molde createFromParcel(Parcel in) {
            return new Molde(in);
        }

        @Override
        public Molde[] newArray(int size) {
            return new Molde[size];
        }
    };

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nombre);
        parcel.writeString(referencia);
        parcel.writeString(descripcion);
    }
}