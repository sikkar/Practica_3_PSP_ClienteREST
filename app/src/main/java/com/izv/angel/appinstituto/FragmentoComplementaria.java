package com.izv.angel.appinstituto;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoComplementaria extends Fragment  {

    private Spinner sp1,sp2,sp3,sp4;
    private View v;
    private EditText etFecha, etHoraD, etHoraH, etDescripcion, etLugar;
    private TextView tvDepartamento;
    private final static String URLBASE="http://ieszv.x10.bz/restful/api/";
    private String idProfesor;
    private String grupo;

    public FragmentoComplementaria() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_fragmento_complementaria, container, false);
        sp1 = (Spinner) v.findViewById(R.id.spProfesor);
        sp2 = (Spinner) v.findViewById(R.id.spDepartamento);
        sp3 = (Spinner) v.findViewById(R.id.spGrupo);
        sp4 = (Spinner) v.findViewById(R.id.spLugar);
        etFecha = (EditText) v.findViewById(R.id.etFecha);
        etHoraD = (EditText) v.findViewById(R.id.etHoraD);
        etHoraH = (EditText) v.findViewById(R.id.etHoraH);
        etLugar = (EditText) v.findViewById(R.id.etLugar);
        etDescripcion = (EditText) v.findViewById(R.id.tvDescripcion);
        tvDepartamento = (TextView) v.findViewById(R.id.tvDepartamento);
        fecha();
        hora();
        profesores();
        departamentos();
        lugares();
        grupos();
        aceptar();
        return v;
    }



    public void profesores(){
        ArrayAdapter<Profesor> adaptador = new ArrayAdapter<Profesor>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, Principal.profesores);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adaptador);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvDepartamento.setText(Principal.profesores.get(position).getDepartamento());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void departamentos(){
        ArrayAdapter <CharSequence> adaptador = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.departamentos,android.R.layout.simple_spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adaptador);
    }

    public void grupos(){
        ArrayAdapter <Grupo> adaptador = new ArrayAdapter<Grupo>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, Principal.grupos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adaptador);
    }

    public void lugares(){
        ArrayAdapter <CharSequence> adaptador = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.lugares,android.R.layout.simple_spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp4.setAdapter(adaptador);
    }

    public void fecha(){
        ImageButton button = (ImageButton)v.findViewById(R.id.ibFecha);
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog= new AlertDialog.Builder(v.getContext());
                dialog.setTitle("Añadir Fecha");
                LayoutInflater inflater= LayoutInflater.from(v.getContext());
                final View vista = inflater.inflate(R.layout.elegir_fecha, null);
                if(getActivity().getResources().getConfiguration().smallestScreenWidthDp>599) {
                    ((DatePicker) vista.findViewById(R.id.datePicker)).setCalendarViewShown(true);
                }
                dialog.setView(vista);
                dialog.setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DatePicker dp = (DatePicker) vista.findViewById(R.id.datePicker);
                                String dia=String.valueOf(dp.getDayOfMonth());
                                String mes=String.valueOf(dp.getMonth()+1);
                                String año = String.valueOf(dp.getYear());
                                etFecha.setText(año+"-"+mes+"-"+dia);
                            }
                        });
                dialog.setNegativeButton("Cancelar",null);
                dialog.show();
            }
        });
    }

        public void hora(){
        ImageButton button = (ImageButton)v.findViewById(R.id.ibHoraD);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog= new AlertDialog.Builder(v.getContext());
                dialog.setTitle("Añadir Hora");
                LayoutInflater inflater= LayoutInflater.from(v.getContext());
                final View vista = inflater.inflate(R.layout.elegir_hora, null);
                dialog.setView(vista);
                dialog.setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                TimePicker tp = (TimePicker) vista.findViewById(R.id.timePicker);
                                String hora=tp.getCurrentHour().toString();
                                String minutos=tp.getCurrentMinute().toString();
                                etHoraD.setText(hora+":"+minutos);
                            }
                        });
                dialog.setNegativeButton("Cancelar",null);
                dialog.show();
            }
        });

        ImageButton button2 = (ImageButton)v.findViewById(R.id.ibHoraH);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog= new AlertDialog.Builder(v.getContext());
                dialog.setTitle("Añadir Hora");
                LayoutInflater inflater= LayoutInflater.from(v.getContext());
                final View vista = inflater.inflate(R.layout.elegir_hora, null);
                dialog.setView(vista);
                dialog.setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                TimePicker tp = (TimePicker) vista.findViewById(R.id.timePicker);
                                String hora=tp.getCurrentHour().toString();
                                String minutos=tp.getCurrentMinute().toString();
                                etHoraH.setText(hora+":"+minutos);
                            }
                        });
                dialog.setNegativeButton("Cancelar",null);
                dialog.show();
            }
        });

    }


    public void aceptar() {
        Button bt = (Button) v.findViewById(R.id.btAceptar);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idProfesor = ((Profesor)sp1.getSelectedItem()).getId();
                grupo = ((Grupo)sp3.getSelectedItem()).getId();
                String grupo = sp3.getSelectedItem().toString();
                String lugar = etLugar.getText().toString();
                String descripcion = etDescripcion.getText().toString();
                String fecha = etFecha.getText().toString();
                String horaini = etHoraD.getText().toString();
                String horafin = etHoraH.getText().toString();
                Actividad act = new Actividad(idProfesor,"complementaria",fecha +" "+horaini,fecha+" "+horafin,lugar,lugar,descripcion,"angel");
                PostRestful post = new PostRestful();
                ParametrosPost pp = new ParametrosPost();
                pp.url=URLBASE+"actividad";
                pp.json=act.getJSON();
                post.execute(pp);
            }
        });

    }

    static class ParametrosPost {
        private String url;
        private JSONObject json;
    }

    private class PostRestful extends AsyncTask<ParametrosPost, Void, String> {

        @Override
        protected String doInBackground(ParametrosPost[] params) {
            String r = ClienteRestFul.post(params[0].url,params[0].json);
            JSONTokener token = new JSONTokener(r);
            JSONObject obProf = new JSONObject();
            JSONObject obGrup = new JSONObject();
            try {
                JSONObject objeto = new JSONObject(token);
                String id = objeto.getString("r");
                obProf.put("idactividad",id);
                obProf.put("idprofesor",idProfesor);
                obGrup.put("idactividad",id);
                obGrup.put("idgrupo",grupo);
            } catch (JSONException e) {

            }
            String s=ClienteRestFul.post(URLBASE+"actividadprofesor",obProf);
            Log.v("exito", s.toString());
            s=ClienteRestFul.post(URLBASE+"actividadgrupo",obGrup);
            Log.v("exito2",s.toString());
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
                    Toast.makeText(getActivity().getBaseContext(), "No se ha podido insertar", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity().getBaseContext(), "Insertado con el id "+id, Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {

            }
            getActivity().finish();
        }
    }

}
