/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Campos;
import Datos.Registros;
import GUI.RecuperarDatos;
import static Logica.GestorArchivo.LongitudesCampos;
import static Logica.GestorRegistro.LongitudesRegistros;
import Patrones.TemplateSerializar;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 Programacion 3
 Realizado por: Juan Cobos, Adrian Flores, Edgar Mora
 * 
 */
public class Prueba {
    public static ArrayList<Campos> l;
    public static ArrayList<Registros> la;
     private static String f = "archivos/META_BD.csv";
 public String histo=null;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here
         RecuperarDatos v = new RecuperarDatos();
         TemplateSerializar cargar = new GestorArchivo();
         cargar.Deserializar();
         GestorRegistro s = new GestorRegistro();
         int g=GestorRegistro.LongitudesRegistros.size();
         //System.out.println(g);
        GestorArchivo.desSerializarGestorArchivo();
        GestorRegistro.deserializar_GestorRegistro();
        la=LongitudesRegistros;
        l=LongitudesCampos;
        //System.out.println("Imprime"+l.size());
        //System.out.println("Imprime"+la.size());
        Iterator it = l.iterator();
       /*for(int i=0; i< l.size(); i++) {
            System.out.println(l.get(i).getCamp());
            System.out.println(l.get(i).getClaveEsp());
             System.out.println(l.get(i).getClaveGen());
             System.out.println(l.get(i).getKey());
             System.out.println(l.get(i));
              System.out.println(l.get(i).getNombre());
               System.out.println(l.get(i).getTotalCam());
                System.out.println(l.get(i).getValor());
             
           // l.get(i).getCamp();*/
       
       
       
       for(int i=0; i< la.size(); i++) {
          System.out.println("REGISTRO:>>>>>>>>>>>");
          System.out.println("NOmbre tabla"+la.get(i).getNombreTabla());
          for (String nombre :la.get(i).getNombreCampo()) {

                System.out.println("CAMPOS:"+nombre);
 
}
           System.out.println("LONGITUD:"+la.get(i).getLongitud());
           
           System.out.println("Nombres"+" "+la.get(i).getNombres());
           System.out.println("REGISTRO:<<<<<<<>>>>>>>>>>>");
       
       }
       
        }
                //CreadorTabla.DesSerializar_CreadorTabla();
                
        
    
        
        
    }
    
    
    

