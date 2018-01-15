package com.example.juanmajr.flclase;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.juanmajr.flclase.Adapters.ListaCochesAdapter;
import com.example.juanmajr.flclase.Adapters.ListaMensajesAdapter;
import com.example.juanmajr.flclase.FBObjects.FBCoche;
import com.example.juanmajr.flclase.FBObjects.Mensaje;
import com.example.milib.ListaFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListaFragment ListaFragmentCoches;
    SupportMapFragment mapFragment;
    ArrayList<FBCoche> noticias;
    ListaCochesAdapter listaCochesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SecondActivityEvents events = new SecondActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);


        ListaFragmentCoches = (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentListCoches);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMapa);
        mapFragment.getMapAsync(events);



        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.hide(ListaFragmentCoches);
        transition.show(mapFragment);
        transition.commit();


    }
}

class SecondActivityEvents implements FireBaseAdminListener, OnMapReadyCallback{

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
    public void agregarPines(){
        for (int i=0; i<noticias.size();i++){
            FBCoche cocheTemp=noticias.get(i);
            LatLng cochePos = new LatLng(cocheTemp.lat, cocheTemp.lon);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(cochePos);
            markerOptions.title(cocheTemp.titulo);

            if(mMap!=null) {
                mMap.addMarker(markerOptions);
                if(i==0)mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cochePos,10));
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

        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng(-34, 151);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Noticias");
    }
}
