package com.example.chikidesk.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class Maquina implements Parcelable {
    private int id;
    private String nombre;
    private String referencia;
    private String descripcion;

    public Maquina(int id, String nombre, String referencia, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.referencia = referencia;
        this.descripcion = descripcion;
    }

    protected Maquina(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        referencia = in.readString();
        descripcion = in.readString();
    }

    public static final Creator<Maquina> CREATOR = new Creator<Maquina>() {
        @Override
        public Maquina createFromParcel(Parcel in) {
            return new Maquina(in);
        }

        @Override
        public Maquina[] newArray(int size) {
            return new Maquina[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
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