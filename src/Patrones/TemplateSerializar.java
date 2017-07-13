/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import java.io.IOException;

/**
 Programacion 3
 Realizado por: Juan Cobos, Adrian Flores, Edgar Mora
 * 
 */
public abstract class TemplateSerializar {
    public final void Deserializar() throws IOException, ClassNotFoundException{
        cargarGestorArchivo();
        cargarGestorRegistro();
        
    }
    //metodos template
    protected abstract void cargarGestorArchivo() throws IOException, ClassNotFoundException ;
    protected abstract void cargarGestorRegistro() throws IOException, ClassNotFoundException ;
    
}
