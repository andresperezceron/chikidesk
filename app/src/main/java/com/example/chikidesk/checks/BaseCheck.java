package com.example.chikidesk.checks;

import android.widget.EditText;

import com.example.chikidesk.viewmodel.AppCacheViewModel;

public abstract class BaseCheck<T, B> {
    protected AppCacheViewModel appCache;
    protected B binding;
    protected boolean success;
    protected boolean areEqualsToUpdate;

    public BaseCheck(AppCacheViewModel appCache, B binding) {
        this.appCache = appCache;
        this.binding = binding;
    }

    public abstract T checkData();
    public abstract T checkData(T oldEntity);
    protected abstract T loadData();
    protected abstract boolean areEquals(T oldEntity, T newEntity);

    public boolean isAreEqualsToUpdate() {
        return areEqualsToUpdate;
    }

    protected String getTextFrom(EditText editText) {
        return (editText == null || editText.getText() == null) ? "" :
                editText.getText().toString().trim();
    }
}
