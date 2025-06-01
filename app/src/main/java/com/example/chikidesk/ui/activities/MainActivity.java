package com.example.chikidesk.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chikidesk.R;
import com.example.chikidesk.db.ConfigDao;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.model.Molde;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MoldeDao moldeDao = new MoldeDao(this);
        if (moldeDao.obtenerTodos().isEmpty()) {
            moldeDao.insertar(new Molde(0, "Molde A", "RefA", "Descripcion Molde A"));
            moldeDao.insertar(new Molde(0, "Molde B", "RefB", "Descripcion Molde B"));
            moldeDao.insertar(new Molde(0, "Molde C", "RefC", "Descripcion Molde C"));
        }
        moldeDao.close();

        MaquinaDao maquinaDao = new MaquinaDao(this);
        if (maquinaDao.obtenerTodos().isEmpty()) {
            maquinaDao.insertar(new Maquina(0, "Maquina01",
                    "RefM01", "Sin descripcion"));
            maquinaDao.insertar(new Maquina(0, "Maquina02", "RefM02",
                    "Sin descripcioin"));
        }
        maquinaDao.close();

    }
}