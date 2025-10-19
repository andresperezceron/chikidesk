package com.example.chikidesk.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class Configuracion implements Parcelable {
    private int id;
    private int id_maquina;
    private int id_molde;
    private String plastificacion;
    private String tiempoCiclo;
    private String tiempoCicloReal;
    private String tiempoEnfriar;
    private String timeOut;
    private String material;
    private String observaciones;

    public Configuracion(int id, int id_maquina, int id_molde, String plastificacion,
                         String tiempoCiclo, String tiempoCicloReal, String tiempoEnfriar,
                         String timeOut, String material, String observaciones) {
        this.id = id;
        this.id_maquina = id_maquina;
        this.id_molde = id_molde;
        this.plastificacion = plastificacion;
        this.tiempoCiclo = tiempoCiclo;
        this.tiempoCicloReal = tiempoCicloReal;
        this.tiempoEnfriar = tiempoEnfriar;
        this.timeOut = timeOut;
        this.material = material;
        this.observaciones = observaciones;
    }

    protected Configuracion(Parcel in) {
        id = in.readInt();
        id_maquina = in.readInt();
        id_molde = in.readInt();
        plastificacion = in.readString();
        tiempoCiclo = in.readString();
        tiempoCicloReal = in.readString();
        tiempoEnfriar = in.readString();
        timeOut = in.readString();
        material = in.readString();
        observaciones = in.readString();
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(id_maquina);
        parcel.writeInt(id_molde);
        parcel.writeString(plastificacion);
        parcel.writeString(tiempoCiclo);
        parcel.writeString(tiempoCicloReal);
        parcel.writeString(tiempoEnfriar);
        parcel.writeString(timeOut);
        parcel.writeString(material);
        parcel.writeString(observaciones);
    }

    public static final Creator<Configuracion> CREATOR = new Creator<>() {
        @Override
        public Configuracion createFromParcel(Parcel in) {
            return new Configuracion(in);
        }

        @Override
        public Configuracion[] newArray(int size) {
            return new Configuracion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_molde() {
        return id_molde;
    }

    public void setId_molde(int id_molde) {
        this.id_molde = id_molde;
    }

    public int getId_maquina() {
        return id_maquina;
    }

    public void setId_maquina(int id_maquina) {
        this.id_maquina = id_maquina;
    }

    public String getPlastificacion() {
        return plastificacion;
    }

    public void setPlastificacion(String plastificacion) {
        this.plastificacion = plastificacion;
    }

    public String getTiempoCiclo() {
        return tiempoCiclo;
    }

    public void setTiempoCiclo(String tiempoCiclo) {
        this.tiempoCiclo = tiempoCiclo;
    }

    public String getTiempoCicloReal() {
        return tiempoCicloReal;
    }

    public void setTiempoCicloReal(String tiempoCicloReal) {
        this.tiempoCicloReal = tiempoCicloReal;
    }

    public String getTiempoEnfriar() {
        return tiempoEnfriar;
    }

    public void setTiempoEnfriar(String tiempoEnfriar) {
        this.tiempoEnfriar = tiempoEnfriar;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}