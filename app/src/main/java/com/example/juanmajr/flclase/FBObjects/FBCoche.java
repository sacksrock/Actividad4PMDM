package com.example.juanmajr.flclase.FBObjects;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

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

    public Marker getMarker(){
        return marker;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("cuerpo", cuerpo);
        result.put("titulo", titulo);
        result.put("lat", lat);
        result.put("lon", lon);
        result.put("imgurl", imgurl);

        return result;
    }
    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

}
