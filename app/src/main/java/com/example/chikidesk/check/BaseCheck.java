package com.example.chikidesk.check;

import android.widget.EditText;

import com.example.chikidesk.viewmodel.AppCacheViewModel;

public abstract class BaseCheck<T, B> {
    protected AppCacheViewModel appCache;
    protected final B binding;
    protected final T oldEntity;
    protected final T newEntity;
    protected T entityChecked;
    protected boolean success;
    protected  boolean empty;
    protected final boolean areEqualsToUpdate;

    public BaseCheck(AppCacheViewModel appCache, B binding) {
        this.appCache = appCache;
        this.binding = binding;
        oldEntity = null;
        newEntity = newEntityByBinding();
        entityChecked = chekingNewEntity();
        areEqualsToUpdate = false;
    }

    public BaseCheck(AppCacheViewModel appCache, B binding, T oldEntity) {
        this.appCache = appCache;
        this.binding = binding;
        this.oldEntity = oldEntity;
        this.newEntity = newEntityByBinding();
        entityChecked = chekingNewEntity();
        areEqualsToUpdate = areEquals();
    }

    protected abstract T newEntityByBinding();
    protected abstract T chekingNewEntity();
    protected abstract boolean areEquals();

    public T getEntity() {
        return entityChecked;
    }
    public boolean isNotSuccess() {
        return entityChecked == null;
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
