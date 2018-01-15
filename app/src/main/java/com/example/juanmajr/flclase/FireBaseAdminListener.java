package com.example.juanmajr.flclase;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by JuanmaJR on 10/12/2017.
 */

public interface FireBaseAdminListener {

    public void FireBaseAdmin_RegisterOk(Boolean ok);
    public void FireBaseAdmin_LoginOk(Boolean ok);
    public void FireBaseAdmin_RamaDescargada(String rama,DataSnapshot dataSnapshot);
}
