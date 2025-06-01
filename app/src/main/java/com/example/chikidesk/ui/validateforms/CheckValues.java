package com.example.chikidesk.ui.validateforms;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class CheckValues {
    private final List<String> listaVacios;

    public CheckValues() {
        listaVacios = new ArrayList<>();
    }

    public String checkEdt(EditText edt, String nombreCampo) {
        String value = edt.getText().toString().trim();
        switch(nombreCampo) {
            case "temp1" :
                if(value.isEmpty()) {
                    listaVacios.add("Temperatura 1");
                    return "0";
                }break;
            case "temp2" :
                if(value.isEmpty()) {
                    listaVacios.add("Temperatura 2");
                    return "0";
                }break;
            case "temp3" :
                if(value.isEmpty()) {
                    listaVacios.add("Temperatura 3");
                    return "0";
                }break;
            case "temp4" :
                if(value.isEmpty()) {
                    listaVacios.add("Temperatura 4");
                    return "0";
                }break;
            /* --------------------------------- Fin Temperaturas*/
            case "iny_velocidad1" :
                if(value.isEmpty()) {
                    listaVacios.add("Iyeccion: Velocidad 1");
                    return "0";
                }break;
            case "iny_velocidad2" :
                if(value.isEmpty()) {
                    listaVacios.add("Iyeccion: Velocidad 2");
                    return "0";
                }break;
            case "iny_velocidad3" :
                if(value.isEmpty()) {
                    listaVacios.add("Iyeccion: Velocidad 3");
                    return "0";
                }break;
            case "iny_velocidad4" :
                if(value.isEmpty()) {
                    listaVacios.add("Iyeccion: Velocidad 4");
                    return "0";
                }break;
            case "iny_velocidad5" :
                if(value.isEmpty()) {
                    listaVacios.add("Iyeccion: Velocidad 5");
                    return "0";
                }break;
            case "iny_presion1" :
                if(value.isEmpty()) {
                    listaVacios.add("Iyeccion: Presion 1");
                    return "0";
                }break;
            case "iny_presion2" :
                if(value.isEmpty()) {
                    listaVacios.add("Iyeccion: Presion 2");
                    return "0";
                }break;
            case "iny_presion3" :
                if(value.isEmpty()) {
                    listaVacios.add("Iyeccion: Presion 3");
                    return "0";
                }break;
            case "iny_presion4" :
                if(value.isEmpty()) {
                    listaVacios.add("Iyeccion: Presion 4");
                    return "0";
                }break;
            case "iny_presion5" :
                if(value.isEmpty()) {
                    listaVacios.add("Iyeccion: Presion 5");
                    return "0";
                }break;
            /* --------------------------------- Fin Inyeccion */
            case "reten_velocidad" :
                if(value.isEmpty()) {
                    listaVacios.add("Reten Presion: Velocidad");
                    return "0";
                }break;
            case "reten_presion" :
                if(value.isEmpty()) {
                    listaVacios.add("Reten Presion: Presion");
                    return "0";
                }break;
            case "reten_tiempo" :
                if(value.isEmpty()) {
                    listaVacios.add("Reten Presion: Tiempo");
                    return "0";
                }break;
            case "expulsor_velocidad1" :
                if(value.isEmpty()) {
                    listaVacios.add("Expulsor: Velocidad 1");
                    return "0";
                }break;
            case "expulsor_velocidad2" :
                if(value.isEmpty()) {
                    listaVacios.add("Expulsor: Velocidad 2");
                    return "0";
                }break;
            case "expulsor_presion1" :
                if(value.isEmpty()) {
                    listaVacios.add("Expulsor: Presion 1");
                    return "0";
                }break;
            case "expulsor_presion2" :
                if(value.isEmpty()) {
                    listaVacios.add("Expulsor: Presion 2");
                    return "0";
                }break;
            case "expulsor_posicion1" :
                if(value.isEmpty()) {
                    listaVacios.add("Expulsor: Posicion 1");
                    return "0";
                }break;
            case "expulsor_posicion2" :
                if(value.isEmpty()) {
                    listaVacios.add("Expulsor: Posicion 2");
                    return "0";
                }break;
            case "plastificacion" :
                if(value.isEmpty()) {
                    listaVacios.add("Plastificacion");
                    return "Sin plastificacion";
                }break;
            case "tiempo_ciclo" :
                if(value.isEmpty()) {
                    listaVacios.add("Tiempo Ciclo");
                    return "0";
                }break;
            case "tiempo_ciclo_real" :
                if(value.isEmpty()) {
                    listaVacios.add("Tiempo Ciclo Real");
                    return "0";
                }break;
            case "tiempo_enfriar" :
                if(value.isEmpty()) {
                    listaVacios.add("Tiempo Enfriar");
                    return "0";
                }break;
            case "time_out" :
                if(value.isEmpty()) {
                    listaVacios.add("Time Out");
                    return "0";
                }break;
            case "material" :
                if(value.isEmpty()) {
                    listaVacios.add("Material");
                    return "Sin Material";
                }break;
            case "observaciones" :
                if(value.isEmpty()) {
                    listaVacios.add("Observaciones");
                    return "Sin Observaciones";
                }break;
            default: return value;
        }
        return value;
    }

    public boolean hayCamposVacios() {
        return !listaVacios.isEmpty();
    }

    public List<String> listaCamposVacios() {
        return listaVacios;
    }
}
