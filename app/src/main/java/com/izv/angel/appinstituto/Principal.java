package com.izv.angel.appinstituto;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;


public class Principal extends Activity {

    public static ArrayList<Actividad> actividades = new ArrayList<>();
    public static ArrayList<Profesor> profesores = new ArrayList<>();
    public static ArrayList<Grupo> grupos = new ArrayList<>();
    private final static String URLBASE="http://ieszv.x10.bz/restful/api/";
    private ListView lv;
    private Adaptador ad;
    private String formattedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        lv= (ListView)findViewById(R.id.lvActividades);
        ad = new Adaptador(this,R.layout.detalle,actividades);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Principal.this,VerActividades.class);
                intent.putExtra("pos",position);
                Log.v("posicion",position+"");
                startActivity(intent);
            }
        });
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        formattedDate = df.format(c.getTime());
        Log.v("fecha",formattedDate);
        cargarActividades();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarActividades();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_anadir) {
            Intent i = new Intent(Principal.this,Aniadir.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void cargarActividades(){
        String [] peticiones = new String[3];
        peticiones[0] = "actividad/angel/"+formattedDate;
        peticiones[1] = "profesor";
        peticiones[2] = "grupo";
        GetRestful get = new GetRestful();
        get.execute(peticiones);
    }

    private class GetRestful extends AsyncTask< String, Void, String[] > {

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
            dialog= new ProgressDialog(Principal.this);
            dialog.setMessage("Cargando Datos");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(String[] r) {
            super.onPostExecute(r);
            JSONTokener tokenact = new JSONTokener(r[0]);
            JSONTokener tokenpro = new JSONTokener(r[1]);
            JSONTokener tokengru = new JSONTokener(r[2]);
            try {
                JSONArray array = new JSONArray(tokenact);
                actividades.clear();
                for (int i = 0; i < array.length(); i++){
                    JSONObject objeto = array.getJSONObject(i);
                    Actividad a = new Actividad(objeto);
                        actividades.add(a);
                }
                //ad.notifyDataSetChanged();
            } catch (JSONException e) {

            }
            try {
                JSONArray array = new JSONArray(tokenpro);
                for (int i = 0; i < array.length(); i++){
                    JSONObject objeto = array.getJSONObject(i);
                    Profesor p = new Profesor(objeto);
                    if(!profesores.contains(p)){
                        profesores.add(p);
                    }
                }
                //ad.notifyDataSetChanged();
            } catch (JSONException e) {

            }
            try {
                JSONArray array = new JSONArray(tokengru);
                for (int i = 0; i < array.length(); i++){
                    JSONObject objeto = array.getJSONObject(i);
                    Grupo g = new Grupo(objeto);
                    if(!grupos.contains(g)){
                        grupos.add(g);
                    }
                }
                ad.notifyDataSetChanged();
            } catch (JSONException e) {

            }
            Collections.sort(actividades);
            dialog.dismiss();
        }
    }
}
