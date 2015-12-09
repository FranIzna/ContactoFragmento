package com.example.chrno.contactofragmento.util;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chrno on 02/12/2015.
 */
public class Contacto implements Serializable,Comparable<Contacto>{
    private long id;
    private String nombre,rutaFoto;
    private List<String> telefonos;

    public Contacto(){
        rutaFoto="";
    }

    public Contacto(long id,String nombre, List<String> telefonos,String rutaFoto) {
        this.id=id;
        this.nombre = nombre;
        this.telefonos = telefonos;
        this.rutaFoto=rutaFoto;
    }    public Contacto(long id,String nombre, List<String> telefonos) {
        this.id=id;
        this.nombre = nombre;
        this.telefonos = telefonos;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", telefonos=" + telefonos +
                '}';
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getTelefonos() {
        return telefonos;
    }
    public String getStringTelefonos(){
        String s="";
        for(String a:telefonos){
            s+=a;
        }
        return s;
    }

    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getNum() {
        return telefonos.get(0);
    }
    public String getNum(int pos) {
        return telefonos.get(pos);
    }
    public List<String> getArrayNum(){
        return this.telefonos;
    }
    public int getSize(){
        return telefonos.size();
    }
    public String getNumeros() {
        String s="";
        for(String a:telefonos)
            s+=a+"\n";
        return s;
    }
    @Override
    public int compareTo(Contacto contacto) {
        int r=this.nombre.compareToIgnoreCase(contacto.nombre);
        if(r==0)
            r=(int) (this.id-contacto.id);
        return r;
    }
}
