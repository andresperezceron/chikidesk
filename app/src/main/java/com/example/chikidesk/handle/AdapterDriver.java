package com.example.chikidesk.handle;

public interface AdapterDriver<A, L> {

    void getAdapter(A adapter);
    L getAdapterListener();
}
