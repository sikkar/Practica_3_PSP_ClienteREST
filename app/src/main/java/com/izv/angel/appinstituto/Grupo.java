package com.izv.angel.appinstituto;


import org.json.JSONException;
import org.json.JSONObject;

public class Grupo {

    private String id, grupo;

    public Grupo(String grupo, String id) {
        this.grupo = grupo;
        this.id = id;
    }

    public Grupo(JSONObject object) throws JSONException {
        this.id = object.getString("id");
        this.grupo = object.getString("grupo");
    }

    public JSONObject getJSON(){
        JSONObject objetoJSON = new JSONObject();
        try {
            objetoJSON.put("id",this.id);
            objetoJSON.put("grupo", this.grupo);
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

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return  " "+this.grupo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grupo grupo1 = (Grupo) o;

        if (grupo != null ? !grupo.equals(grupo1.grupo) : grupo1.grupo != null) return false;
        if (id != null ? !id.equals(grupo1.id) : grupo1.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (grupo != null ? grupo.hashCode() : 0);
        return result;
    }
}
