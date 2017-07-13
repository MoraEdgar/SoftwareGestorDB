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
public class Campos implements Serializable{
    private String camp;
    private int valor;
    private String nombre;
    private int totalCam;
    private int claveEsp;
    private int claveGen;
    private String key;

    public Campos(String camp, int valor, String nombre, int totalCam, int claveEsp, int claveGen, String key) {
        this.camp = camp;
        this.valor = valor;
        this.nombre = nombre;
        this.totalCam = totalCam;
        this.claveEsp = claveEsp;
        this.claveGen = claveGen;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    

    public void setClaveEsp(int claveEsp) {
        this.claveEsp = claveEsp;
    }

    public void setClaveGen(int claveGen) {
        this.claveGen = claveGen;
    }

    public int getClaveEsp() {
        return claveEsp;
    }

    public int getClaveGen() {
        return claveGen;
    }
  

    public int getTotalCam() {
        return totalCam;
    }

    public void setTotalCam(int totalCam) {
        this.totalCam = totalCam;
    }
    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
       
    public void setCamp(String camp) {
        this.camp = camp;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public int getValor() {
        return valor;
    }

    public String getCamp() {
        return camp;
    }
    public void mostrar(){
    System.out.println(getCamp()+getNombre());
    }

}