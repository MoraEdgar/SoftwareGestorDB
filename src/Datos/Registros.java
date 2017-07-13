/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.io.Serializable;

/**
 Programacion 3
 Realizado por: Juan Cobos, Adrian Flores, Edgar Mora
 * 
 */
public class Registros implements Serializable,Comparable<Registros>{
    private String nombreTabla;
    private String nombreCampo[];
    private int longitud;
    private String nombres;

    public Registros(String nombreTabla, String[] nombreCampo, int longitud, String nombres) {
        this.nombreTabla = nombreTabla;
        this.nombreCampo = nombreCampo;
        this.longitud = longitud;
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    

    public String[] getNombreCampo() {
        return nombreCampo;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setNombreCampo(String[] nombreCampo) {
        this.nombreCampo = nombreCampo;
    }
    


    
    public String getNombreTabla() {
        return nombreTabla;
    }

    
    

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }
    
    public  String cogerRegistro(int clave)
    {
        System.out.println("nonmbre del campo en la clave "+nombreCampo[clave] );
        return this.nombreCampo[clave];
    }

    @Override
    public int compareTo(Registros o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
