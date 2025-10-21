package com.example.chikidesk.checks;

import android.widget.EditText;

import com.example.chikidesk.viewmodel.AppCacheViewModel;

public abstract class BaseCheckFragment<T> {
    protected AppCacheViewModel appCache;
    protected boolean success;

    public BaseCheckFragment(AppCacheViewModel appCache) {
        this.appCache = appCache;
    }

    protected abstract T loadData();
    public abstract T checkData();

    protected String getTextFrom(EditText editText) {
        return (editText == null || editText.getText() == null) ? "" :
                editText.getText().toString().trim();
    }
}
