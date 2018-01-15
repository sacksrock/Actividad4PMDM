package com.example.juanmajr.flclase.FBObjects;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by JuanmaJR on 11/12/2017.
 */

@IgnoreExtraProperties
public class FBCoche {
    public String cuerpo;
    public String titulo;
    public double lat;
    public double lon;
    public String imgurl;
    
    //Fuera de firebase
    private Marker marker;



    public FBCoche(){

    }

    public FBCoche( String titulo, String cuerpo, double lat, double lon,String imgurl) {
        this.cuerpo = cuerpo;
        this.titulo = titulo;
        this.lat = lat;
        this.lon = lon;
        this.imgurl = imgurl;
    }

    public void setMarker(Marker marker){
        this.marker=marker;
    }

    public void getMarker(Marker marker){
        return marker;
    }
}
