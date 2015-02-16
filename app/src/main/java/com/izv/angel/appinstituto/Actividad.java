package com.izv.angel.appinstituto;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Actividad implements Comparable<Actividad> {

    private String id,idprofesor,tipo,fechai,fechaf,lugari,lugarf,descripcion,alumno,idgrupo;

    public Actividad(JSONObject object) throws JSONException {
        this.id = object.getString("id");
        this.idprofesor = object.getString("idprofesor");
        this.tipo = object.getString("tipo");
        this.fechai = object.getString("fechai");
        this.fechaf = object.getString("fechaf");
        this.lugari = object.getString("lugari");
        this.lugarf = object.getString("lugarf");
        this.descripcion = object.getString("descripcion");
        this.alumno = object.getString("alumno");

    }

    public JSONObject getJSON(){
        JSONObject objetoJSON = new JSONObject();
        try {
            objetoJSON.put("idprofesor", this.idprofesor);
            objetoJSON.put("tipo", this.tipo);
            objetoJSON.put("fechai",this.fechai);
            objetoJSON.put("fechaf",this.fechaf);
            objetoJSON.put("lugari",this.lugari);
            objetoJSON.put("lugarf",this.lugarf);
            objetoJSON.put("descripcion",this.descripcion);
            objetoJSON.put("alumno",this.alumno);
            return objetoJSON;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Actividad( String idprofesor, String tipo, String fechai, String fechaf, String lugari, String lugarf, String descripcion, String alumno) {
        this.idprofesor = idprofesor;
        this.tipo = tipo;
        this.fechai = fechai;
        this.fechaf = fechaf;
        this.lugari = lugari;
        this.lugarf = lugarf;
        this.descripcion = descripcion;
        this.alumno = alumno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(String idprofesor) {
        this.idprofesor = idprofesor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechai() {
        return fechai;
    }

    public void setFechai(String fechai) {
        this.fechai = fechai;
    }

    public String getFechaf() {
        return fechaf;
    }

    public void setFechaf(String fechaf) {
        this.fechaf = fechaf;
    }

    public String getLugari() {
        return lugari;
    }

    public void setLugari(String lugari) {
        this.lugari = lugari;
    }

    public String getLugarf() {
        return lugarf;
    }

    public void setLugarf(String lugarf) {
        this.lugarf = lugarf;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(String idgrupo) {
        this.idgrupo = idgrupo;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id='" + id + '\'' +
                ", idprofesor='" + idprofesor + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fechai='" + fechai + '\'' +
                ", fechaf='" + fechaf + '\'' +
                ", lugari='" + lugari + '\'' +
                ", lugarf='" + lugarf + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", alumno='" + alumno + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actividad actividad = (Actividad) o;

        if (alumno != null ? !alumno.equals(actividad.alumno) : actividad.alumno != null)
            return false;
        if (descripcion != null ? !descripcion.equals(actividad.descripcion) : actividad.descripcion != null)
            return false;
        if (fechaf != null ? !fechaf.equals(actividad.fechaf) : actividad.fechaf != null)
            return false;
        if (fechai != null ? !fechai.equals(actividad.fechai) : actividad.fechai != null)
            return false;
        if (id != null ? !id.equals(actividad.id) : actividad.id != null) return false;
        if (idprofesor != null ? !idprofesor.equals(actividad.idprofesor) : actividad.idprofesor != null)
            return false;
        if (lugarf != null ? !lugarf.equals(actividad.lugarf) : actividad.lugarf != null)
            return false;
        if (lugari != null ? !lugari.equals(actividad.lugari) : actividad.lugari != null)
            return false;
        if (tipo != null ? !tipo.equals(actividad.tipo) : actividad.tipo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idprofesor != null ? idprofesor.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (fechai != null ? fechai.hashCode() : 0);
        result = 31 * result + (fechaf != null ? fechaf.hashCode() : 0);
        result = 31 * result + (lugari != null ? lugari.hashCode() : 0);
        result = 31 * result + (lugarf != null ? lugarf.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (alumno != null ? alumno.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Actividad another) {
        if (this.fechai.compareTo(another.fechai)!=0){
            return this.fechai.compareTo(another.fechai);
        }else{
            return this.tipo.compareTo(another.tipo);
        }
    }
}
