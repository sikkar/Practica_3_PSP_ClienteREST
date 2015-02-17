package com.izv.angel.appinstituto;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoVista extends Fragment {

    private int id;
    private Actividad act;
    private final static String URLBASE="http://ieszv.x10.bz/restful/api/";
    private String idGrupo, nombreGrupo;
    private TextView tvTipo, tvFechaI, tvFechaF, tvLugarI, tvLugarF, tvDescripcion,tvGrupo;
    private ImageView ivTipo;
    private View v;


    public FragmentoVista() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_fragmento_vista, container, false);
        tvTipo = (TextView) v.findViewById(R.id.tvTipoActividad);
        tvFechaI = (TextView) v.findViewById(R.id.tvFechaInicio);
        tvFechaF = (TextView) v.findViewById(R.id.tvFechaFin);
        tvLugarI = (TextView) v.findViewById(R.id.tvLugarInicio);
        tvLugarF = (TextView) v.findViewById(R.id.tvLugarFin);
        tvDescripcion = (TextView) v.findViewById(R.id.tvDescripcion);
        tvGrupo = (TextView) v.findViewById(R.id.tvGrupo);
        ivTipo = (ImageView) v.findViewById(R.id.ivTipo);
        return v;
    }

    public void cargarListView (int id){
        this.id = id;
        act = Principal.actividades.get(id);
        cargarActividades();
    }

    private void cargarActividades(){
        String [] peticiones = new String[1];
        peticiones[0] ="actividadgrupo/"+act.getId();
        GetRestful get = new GetRestful();
        get.execute(peticiones);
    }

    private class GetRestful extends AsyncTask< String, Void, String[]> {



        @Override
        protected String[] doInBackground(String... params) {
            String [] r = new String [params.length];
            int contador =0;
            for(String s:params){
                r[contador] = ClienteRestFul.get(URLBASE + s);
                contador++;
            }
            return r;
        }



        @Override
        protected void onPostExecute(String r[]) {
            super.onPostExecute(r);
            JSONTokener token = new JSONTokener(r[0]);
            try {
                JSONArray array = new JSONArray(token);
                for (int i = 0; i < array.length(); i++){
                    JSONObject objeto = array.getJSONObject(i);
                    idGrupo = objeto.getString("idgrupo");
                }
            } catch (JSONException e) {

            }
            for (int i = 0; i < Principal.grupos.size() ; i++) {
                if(idGrupo.equals(Principal.grupos.get(i).getId())){
                    nombreGrupo= Principal.grupos.get(i).getGrupo();
                }
            }
            tvTipo.setText(act.getTipo());
            if(act.getTipo().equals("extraescolar")){
                ivTipo.setImageResource(R.mipmap.extra);
            }else{
                ivTipo.setImageResource(R.mipmap.comp);
            }
            tvFechaI.setText(act.getFechai());
            tvFechaF.setText(act.getFechaf());
            tvLugarI.setText(act.getLugari());
            tvLugarF.setText(act.getLugarf());
            tvGrupo.setText(nombreGrupo);
            tvDescripcion.setText(act.getDescripcion());
        }
    }


}
