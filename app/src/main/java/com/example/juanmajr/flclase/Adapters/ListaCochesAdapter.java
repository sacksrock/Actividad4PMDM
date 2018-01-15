package com.example.juanmajr.flclase.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.juanmajr.flclase.FBObjects.FBCoche;
import com.example.juanmajr.flclase.R;

import java.util.ArrayList;

/**
 * Created by JuanmaJR on 10/12/2017.
 */

public class ListaCochesAdapter extends RecyclerView.Adapter<CochesViewHolder> {

    private ArrayList<FBCoche> coches;
    private Context nContext;

    public ListaCochesAdapter(ArrayList<FBCoche> coches,Context nContext){
        this.coches = coches;
        this.nContext = nContext;
    }

    @Override
    public CochesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_coche_layout,null);
        CochesViewHolder cochesViewHolder = new CochesViewHolder(view);
        return cochesViewHolder;
    }

    @Override
    public void onBindViewHolder(CochesViewHolder holder, int position) {
        holder.tvcuerpo.setText(coches.get(position).cuerpo);
        holder.tvtitulo.setText(coches.get(position).titulo);
        holder.tvlatitud.setText(coches.get(position).lat+"");
        holder.tvlongitud.setText(coches.get(position).lon+"");

        Glide.with(nContext).load(coches.get(position).imgurl).into(holder.imgcoche);

    }


    @Override
    public int getItemCount() {
       return coches.size();
    }
}


class CochesViewHolder extends RecyclerView.ViewHolder{

    public TextView tvcuerpo;
    public TextView tvtitulo;
    public TextView tvlatitud;
    public TextView tvlongitud;
    public ImageView imgcoche;

    public CochesViewHolder(View itemView) {
        super(itemView);
        tvcuerpo=itemView.findViewById(R.id.tvcuerpo);
        tvtitulo=itemView.findViewById(R.id.tvtitulo);
        tvlatitud=itemView.findViewById(R.id.tvlatitude);
        tvlongitud=itemView.findViewById(R.id.tvlongitud);
        imgcoche=itemView.findViewById(R.id.imgcoche);
    }
}