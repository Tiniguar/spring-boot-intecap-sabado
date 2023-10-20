package com.company.intecap.apibooks.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {
    //responseRest: informaicon para el cliente e como respondio el servicio metadata
    // hashmap:  es una estructura de datos que almacena pares de valores
    //arraylist: es una estructura de datos que almacena una coleccion de objectos


    private ArrayList<HashMap<String,String>> metadata =  new ArrayList<>();

    public ArrayList<HashMap<String, String>> getMetadata() {
        return metadata;
    }

    public void setMetadata(String tipo, String codigo, String dato) {
        HashMap<String,String> mapa = new HashMap<String, String>();
        mapa.put("tipo",tipo);
        mapa.put("codigo",codigo);
        mapa.put("dato",dato);

        this.metadata.add( mapa);
    }
}
