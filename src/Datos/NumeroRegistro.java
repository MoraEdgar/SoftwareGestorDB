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
public class NumeroRegistro implements Serializable{
    private int numReg;
    private String nombre;

    public NumeroRegistro(int numReg, String nombre) {
        this.numReg = numReg;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumReg() {
        return numReg;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumReg(int numReg) {
        this.numReg = numReg;
    }
    
}
