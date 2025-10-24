package com.example.chikidesk.ui.fragment;

import androidx.annotation.NonNull;

public interface HandleDriver<H> {
    void startDriving(@NonNull H handleBound);
    void onDestroyView();
}
