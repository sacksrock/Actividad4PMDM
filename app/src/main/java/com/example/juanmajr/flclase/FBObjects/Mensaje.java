package com.example.juanmajr.flclase.FBObjects;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by JuanmaJR on 10/12/2017.
 */
@IgnoreExtraProperties
public class Mensaje {
    public String original;

    public Mensaje(){

    }

    public Mensaje(String original){
        this.original = original;
    }

}
