package com.example.chikidesk.check;

import android.widget.EditText;

public abstract class Check<T, B> {
    protected final B binding;
    protected T oldEntity;
    protected T newEntity;
    protected T entityChecked;
    protected boolean areEqualsToUpdate;
    protected boolean success;
    protected boolean empty;

    public Check(B binding) {
        this.binding = binding;
        success = true;
        empty = false;
    }

    public Check(B binding, T oldEntity) {
        this.binding = binding;
        this.oldEntity = oldEntity;
        success = true;
        empty = false;
    }

    protected abstract T newEntityByBinding();
    protected abstract T checkingNewEntity();
    protected abstract boolean areEquals();

    public T getEntity() {
        return entityChecked;
    }
    public boolean isSuccess() {
        return success;
    }
    public boolean isAreEqualsToUpdate() {
        return areEqualsToUpdate;
    }

    public boolean isEmpty() {
        return empty;
    }

    protected String getTextFrom(EditText editText) {
        return (editText == null || editText.getText() == null) ? "" :
                editText.getText().toString().trim();
    }
}