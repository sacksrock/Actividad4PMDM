package com.example.juanmajr.flclase;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.juanmajr.flclase.Adapters.ListaCochesAdapter;
import com.example.juanmajr.flclase.FBObjects.FBCoche;
import com.example.milib.AsyncTasks.HTTPAsyncTask;
import com.example.milib.AsyncTasks.HttpJSONAsyncsTask;
import com.example.milib.ListaFragment;
import com.example.milib.DetallesFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

import GPSAdmin.GPSTracker;

public class SecondActivity extends AppCompatActivity {

    ListaFragment ListaFragmentCoches;
    SupportMapFragment mapFragment;
    ListaCochesAdapter listaCochesAdapter;
    DetallesFragment mapDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SecondActivityEvents events = new SecondActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);


        ListaFragmentCoches = (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentListCoches);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMapa);
        mapFragment.getMapAsync(events);
        mapDetailFragment = (DetallesFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMapaDetalles);



        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.hide(ListaFragmentCoches);
        transition.hide(mapDetailFragment);
        transition.show(mapFragment);
        transition.commit();

        GPSTracker gpsTracker=new GPSTracker(this );
        if (gpsTracker.canGetLocation()){
            Log.v("SecondActivity",gpsTracker.getLatitude()+"  "+gpsTracker.getLongitude());
            FBCoche fbCoche=new FBCoche("El Madrid Al Pozo","El Madrid Al Pozo 2.0",gpsTracker.getLatitude(),gpsTracker.getLongitude(),"");
            DataHolder.instance.fireBaseAdmin.insertarEnRama("/Noticias/3",fbCoche.toMap());
        }
        else{
            gpsTracker.showSettingsAlert();
        }

        HttpJSONAsyncsTask httpJSONAsyncsTask=new HttpJSONAsyncsTask();
        String url= String.format("http://api.openweathermap.org/data/2.5/weather?id=%s&appid=%s",
                "3117732",DataHolder.instance.API_KEY);
        httpJSONAsyncsTask.execute(url);

    }
}

class SecondActivityEvents implements FireBaseAdminListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    SecondActivity secondActivity;
    ListaCochesAdapter listaCochesAdapter;
    ArrayList<FBCoche> noticias;
    GoogleMap mMap;

    public SecondActivityEvents(SecondActivity secondActivity){
        this.secondActivity = secondActivity;
    }

    @Override
    public void FireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot) {
        if(rama.equals("Noticias")){
            GenericTypeIndicator<ArrayList<FBCoche>> indicator = new GenericTypeIndicator<ArrayList<FBCoche>>(){};
            noticias = dataSnapshot.getValue(indicator);

            secondActivity.listaCochesAdapter = new ListaCochesAdapter(noticias,secondActivity);
            secondActivity.ListaFragmentCoches.recyclerView.setAdapter(secondActivity.listaCochesAdapter);
            //secondActivity.listaCochesAdapter.setListener(this);
            agregarPines();
        }
    }
    public void quitarViejosPines(){
        if (noticias != null) {
            for (int i = 0; i < noticias.size(); i++) {
                FBCoche cocheTemp = noticias.get(i);
                if (cocheTemp.getMarker() != null) {
                    cocheTemp.getMarker().remove();
                }
            }
        }
    }
    public void agregarPines(){
        for (int i=0; i<noticias.size();i++){
            FBCoche cocheTemp=noticias.get(i);
            LatLng cochePos = new LatLng(cocheTemp.lat, cocheTemp.lon);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(cochePos);
            markerOptions.title(cocheTemp.titulo);

            if(mMap!=null) {
                Marker marker=mMap.addMarker(markerOptions);
                cocheTemp.setMarker(marker);
                marker.setTag(cocheTemp);
                if(i==0)mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cochePos,7));
            }
        }
    }



    @Override
    public void FireBaseAdmin_RegisterOk(Boolean ok) {

    }

    @Override
    public void FireBaseAdmin_LoginOk(Boolean ok) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener (this);

        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng(-34, 151);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Noticias");
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        FBCoche noticias=(FBCoche)marker.getTag();
        Log.v("SecondActivity","Presionando pin:"+noticias.titulo);

        secondActivity.mapDetailFragment.tvtitulo.setText(noticias.titulo);
        secondActivity.mapDetailFragment.tvcuerpo.setText(noticias.cuerpo);

        FragmentTransaction transition = secondActivity.getSupportFragmentManager().beginTransaction();
        transition.show(secondActivity.mapDetailFragment);
        transition.commit();

        return false;
    }
}
