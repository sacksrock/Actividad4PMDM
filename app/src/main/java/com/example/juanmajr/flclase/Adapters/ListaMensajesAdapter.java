package com.example.juanmajr.flclase.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juanmajr.flclase.R;

import java.util.ArrayList;

/**
 * Created by JuanmaJR on 10/12/2017.
 */

public class ListaMensajesAdapter extends RecyclerView.Adapter<MensajeViewHolder> {

    private ArrayList<String> mensajes;

    public ListaMensajesAdapter(ArrayList<String> mensajes){
        this.mensajes = mensajes;
    }

    @Override
    public MensajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_mensaje_layout,null);
        MensajeViewHolder mensajeViewHolder = new MensajeViewHolder(view);
        return mensajeViewHolder;
    }

    @Override
    public void onBindViewHolder(MensajeViewHolder holder, int position) {

        holder.textmensaje.setText(mensajes.get(position));
    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }
}


class MensajeViewHolder extends RecyclerView.ViewHolder{

    public TextView textmensaje;

    public MensajeViewHolder(View itemView) {
        super(itemView);
        textmensaje=itemView.findViewById(R.id.textomensaje);
    }
}