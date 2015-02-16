package com.izv.angel.appinstituto;


import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class Adaptador extends ArrayAdapter<Actividad> {

   private Context contexto;
   private ArrayList<Actividad> lista;
   private int recurso;
   private static LayoutInflater i;

   static class ViewHolder{
       public TextView tv1,tv2,tv3,tv4;
       public ImageView iv1;
       public int posicion;
   }

   public Adaptador(Context context, int resource, ArrayList<Actividad> objects) {
       super(context, resource, objects);
       this.contexto = context;
       this.lista = objects;
       this.recurso = resource;
       this.i = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
   }

   public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder vh = null;
       if(convertView == null){
           convertView = i.inflate(recurso, null);
           vh = new ViewHolder();
           vh.tv4 = (TextView) convertView.findViewById(R.id.tvProfesor);
           vh.tv1 = (TextView) convertView.findViewById(R.id.tvTipo);
           vh.tv2 = (TextView) convertView.findViewById(R.id.tvLugar);
           vh.tv3 = (TextView) convertView.findViewById(R.id.tvFecha);
           vh.iv1 = (ImageView) convertView.findViewById(R.id.ivIcono);
           convertView.setTag(vh);
       }
       else{
           vh=(ViewHolder) convertView.getTag();
       }
       vh.posicion=position;
       String idprof = lista.get(position).getIdprofesor();
       String profesor = null;
       for (int j = 0; j < Principal.profesores.size(); j++) {
           if(idprof.equals(Principal.profesores.get(j).getId())){
               profesor= Principal.profesores.get(j).getNombre() +" "+ Principal.profesores.get(j).getApellido();
           }
       }
       if(lista.get(position).getTipo().equals("extraescolar")){
           vh.iv1.setImageResource(R.mipmap.extra);
       }else{
           vh.iv1.setImageResource(R.mipmap.comp);
       }
       vh.tv4.setText(profesor);
       vh.tv1.setText(lista.get(position).getTipo());
       vh.tv2.setText(lista.get(position).getLugari());
       vh.tv3.setText(lista.get(position).getFechai());
       return convertView;
   }

}

