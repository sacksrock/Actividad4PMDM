package com.example.milib;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetallesFragment extends Fragment {
    public TextView tvcuerpo;
    public TextView tvtitulo;

    public DetallesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detalles, container, false);

        tvcuerpo= v.findViewById(R.id.tvcuerpo);
        tvtitulo= v.findViewById(R.id.tvtitulo);

        return v;
    }


}
