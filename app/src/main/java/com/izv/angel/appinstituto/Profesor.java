package com.izv.angel.appinstituto;


import org.json.JSONException;
import org.json.JSONObject;

public class Profesor {

    private String id, nombre, apellidos, departamento;

    public Profesor(String id, String nombre, String apellido, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellido;
        this.departamento = departamento;
    }

    public Profesor(JSONObject object) throws JSONException {
        this.id = object.getString("id");
        this.nombre = object.getString("nombre");
        this.apellidos = object.getString("apellidos");
        this.departamento = object.getString("departamento");
    }

    public JSONObject getJSON(){
        JSONObject objetoJSON = new JSONObject();
        try {
            objetoJSON.put("id",this.id);
            objetoJSON.put("nombre", this.nombre);
            objetoJSON.put("apellido",this.apellidos);
            objetoJSON.put("departamento",this.departamento);
            return objetoJSON;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellidos;
    }

    public void setApellido(String apellido) {
        this.apellidos = apellido;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return this.nombre +" "+this.apellidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profesor profesor = (Profesor) o;

        if (apellidos != null ? !apellidos.equals(profesor.apellidos) : profesor.apellidos != null)
            return false;
        if (departamento != null ? !departamento.equals(profesor.departamento) : profesor.departamento != null)
            return false;
        if (id != null ? !id.equals(profesor.id) : profesor.id != null) return false;
        if (nombre != null ? !nombre.equals(profesor.nombre) : profesor.nombre != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (departamento != null ? departamento.hashCode() : 0);
        return result;
    }


}
