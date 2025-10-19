package com.example.chikidesk.ui.validateforms;


import com.example.chikidesk.model.Inyeccion;
import com.google.android.material.textfield.TextInputLayout;

public class CheckInyeccion extends BaseCheck<Inyeccion> {
    private Inyeccion inyChecked;
    private final int V1 = 0;
    private final int V2 = 1;
    private final int V3 = 2;
    private final int V4 = 3;
    private final int V5 = 4;
    private final int P1 = 5;
    private final int P2 = 6;
    private final int P3 = 7;
    private final int P4 = 8;
    private final int P5 = 9;

    public CheckInyeccion(TextInputLayout[] arrayTils) {
        super(arrayTils);
    }

    @Override
    public void checkInsert(Inyeccion newEntity) {
        inyChecked = newEntity;
        isEmpty = false;
        checkStatus = true;

        resetAllTils();
        if(inyChecked.getVelocidad1().isEmpty()) {
            isEmpty = true;
            getTil(V1).setError("V1 = 0");
            inyChecked.setVelocidad1("0");
        }

        if(inyChecked.getVelocidad2().isEmpty()) {
            isEmpty = true;
            getTil(V2).setError("V2 = 0");
            inyChecked.setVelocidad2("0");
        }

        if(inyChecked.getVelocidad3().isEmpty()) {
            isEmpty = true;
            getTil(V3).setError("V3 = 0");
            inyChecked.setVelocidad3("0");
        }

        if(inyChecked.getVelocidad4().isEmpty()) {
            isEmpty = true;
            getTil(V4).setError("V4 = 0");
            inyChecked.setVelocidad4("0");
        }

        if(inyChecked.getVelocidad5().isEmpty()) {
            isEmpty = true;
            getTil(V5).setError("V5 = 0");
            inyChecked.setVelocidad5("0");
        }

        if(inyChecked.getPresion1().isEmpty()) {
            isEmpty = true;
            getTil(P1).setError("P1 = 0");
            inyChecked.setPresion1("0");
        }

        if(inyChecked.getPresion2().isEmpty()) {
            isEmpty = true;
            getTil(P2).setError("P2 = 0");
            inyChecked.setPresion2("0");
        }

        if(inyChecked.getPresion3().isEmpty()) {
            isEmpty = true;
            getTil(P3).setError("P3 = 0");
            inyChecked.setPresion3("0");
        }

        if(inyChecked.getPresion4().isEmpty()) {
            isEmpty = true;
            getTil(P4).setError("P4 = 0");
            inyChecked.setPresion4("0");
        }

        if(inyChecked.getPresion5().isEmpty()) {
            isEmpty = true;
            getTil(P5).setError("P5 = 0");
            inyChecked.setPresion5("0");
        }
    }

    @Override
    public void checkUpdate(Inyeccion oldEntity, Inyeccion newEntity) {
        inyChecked = newEntity;
        equalToUpdate = areEquals(oldEntity, newEntity);
        if(equalToUpdate) {
            checkStatus = false;
            isEmpty = false;
            return;
        }

        checkStatus = true;
        isEmpty = false;
        resetAllTils();

        if(inyChecked.getVelocidad1().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(V1).setError(" ");
        }
        if(inyChecked.getVelocidad2().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(V2).setError(" ");
        }
        if(inyChecked.getVelocidad3().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(V3).setError(" ");
        }
        if(inyChecked.getVelocidad4().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(V4).setError(" ");
        }
        if(inyChecked.getVelocidad5().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(V5).setError(" ");
        }
        if(inyChecked.getPresion1().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(P1).setError(" ");
        }
        if(inyChecked.getPresion2().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(P2).setError(" ");
        }
        if(inyChecked.getPresion3().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(P3).setError(" ");
        }
        if(inyChecked.getPresion4().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(P4).setError(" ");
        }
        if(inyChecked.getPresion5().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(P5).setError(" ");
        }
    }

    @Override
    public Inyeccion getCheckedEntity() {
        return checkStatus ? inyChecked : null;
    }

    @Override
    protected boolean areEquals(Inyeccion oldEntity, Inyeccion newEntity) {
        return oldEntity.getVelocidad1().equals(newEntity.getVelocidad1()) &&
                oldEntity.getVelocidad2().equals(newEntity.getVelocidad2()) &&
                oldEntity.getVelocidad3().equals(newEntity.getVelocidad3()) &&
                oldEntity.getVelocidad4().equals(newEntity.getVelocidad4()) &&
                oldEntity.getVelocidad5().equals(newEntity.getVelocidad5()) &&
                oldEntity.getPresion1().equals(newEntity.getPresion1()) &&
                oldEntity.getPresion2().equals(newEntity.getPresion2()) &&
                oldEntity.getPresion3().equals(newEntity.getPresion3()) &&
                oldEntity.getPresion4().equals(newEntity.getPresion4()) &&
                oldEntity.getPresion5().equals(newEntity.getPresion5());
    }
}