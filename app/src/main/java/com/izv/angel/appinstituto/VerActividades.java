package com.izv.angel.appinstituto;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Collections;


public class VerActividades extends Activity {

    private int id;
    private Actividad act;
    private final static String URLBASE="http://ieszv.x10.bz/restful/api/";
    private String idGrupo, nombreGrupo;
    private TextView tvTipo, tvFechaI, tvFechaF, tvLugarI, tvLugarF, tvDescripcion,tvGrupo;
    private ImageView ivTipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_ver_actividades);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        Intent intent = getIntent();
        id= intent.getIntExtra("pos", 0);
        act = Principal.actividades.get(id);
        tvTipo = (TextView) findViewById(R.id.tvTipoActividad);
        tvFechaI = (TextView) findViewById(R.id.tvFechaInicio);
        tvFechaF = (TextView) findViewById(R.id.tvFechaFin);
        tvLugarI = (TextView) findViewById(R.id.tvLugarInicio);
        tvLugarF = (TextView) findViewById(R.id.tvLugarFin);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);
        tvGrupo = (TextView) findViewById(R.id.tvGrupo);
        ivTipo = (ImageView) findViewById(R.id.ivTipo);
        cargarActividades();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ver_actividades, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_borrar) {
            borrar();
            return true;
        }
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void cargarActividades(){
        String [] peticiones = new String[1];
        peticiones[0] ="actividadgrupo/"+act.getId();
        GetRestful get = new GetRestful();
        get.execute(peticiones);
    }

    private class GetRestful extends AsyncTask < String, Void, String[]> {

        private ProgressDialog dialog;

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
        protected void onPreExecute() {
            super.onPreExecute();
            dialog= new ProgressDialog(VerActividades.this);
            dialog.setMessage("Cargando Datos");
            dialog.setCancelable(false);
            dialog.show();
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
            dialog.dismiss();
        }
    }

    private void borrar(){
        String ruta = URLBASE +"actividad/"+act.getId();
        PostRestful post = new PostRestful();
        post.execute(ruta);
    }

    private class PostRestful extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
            String r = ClienteRestFul.delete(params[0]);
            return r;
        }

        @Override
        protected void onPostExecute(String r) {
            super.onPostExecute(r);
            JSONTokener token = new JSONTokener(r);
            try {
                JSONObject objeto = new JSONObject(token);
                String id = objeto.getString("r");
                if(id.equals(0)){
                    Toast.makeText(getBaseContext(), "No se ha podido borrar la actividad", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Actividad Borrada", Toast.LENGTH_SHORT).show();
                    finish();
                }
            } catch (JSONException e) {

            }
        }

    }
}
