/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Programacion 3
 Realizado por: Juan Cobos, Adrian Flores, Edgar Mora
 * 
 */
public class CreadorTabla implements Serializable{

    public static List<String> nombresTablas;
    private static CreadorTabla instancia = null;
    
    
    private CreadorTabla() { 
        nombresTablas = new ArrayList<String>();      
    }
    
    public static CreadorTabla getInstancia() {
        if (instancia == null)
            instancia = new CreadorTabla();       
        return instancia;   
    }
    
    public boolean addNombreTabla(String nombre, String[] campos) throws IOException {
        
        
        if (nombresTablas.contains(nombre))
            return true;
        else {
            nombresTablas.add(nombre);
            
            crearArchivoTabla(nombre,campos);
            return false;
        }
    }
    
    public void removeNombreTabla(String nombre) throws IOException {
        for (int i = 0; i < nombresTablas.size(); i++) {
            if(nombresTablas.get(i).equals(nombre))
                nombresTablas.remove(i);
        }
        
        serializar_CreadorTabla();
    }
    
    public void setNombresTablas(List<String> nombresTablas) {
        this.nombresTablas = nombresTablas;
    }
    
    private void crearArchivoTabla(String nombre, String[] campos) throws IOException {
        String archivo = "archivos/" + nombre + ".csv";
        File ficheroTabla = new File(archivo);
        String a= Arrays.toString(campos);
        
        if (!ficheroTabla.getParentFile().exists())
            ficheroTabla.getParentFile().mkdirs();
        ficheroTabla.createNewFile();
                        
        FileWriter archivoWriter = new FileWriter(ficheroTabla);
        CsvWriter csvOutput = new CsvWriter(archivoWriter, ',');
        
        for (int i = 0; i<campos.length; i++) {
            //separarLongitud(campos[i]);
            csvOutput.write(campos[i]);
        }
        
        csvOutput.endRecord();
        csvOutput.flush();
        csvOutput.close();
    }
    
    
    public static void serializar_CreadorTabla() throws IOException{
        try (ObjectOutputStream output_nombreTablas = new ObjectOutputStream(new FileOutputStream("serializar/CreadorTabla_nombreTablas.dat"))) {
            output_nombreTablas.writeObject(nombresTablas);
            output_nombreTablas.close();
            System.out.println("Serializado CreadorTabla");
        }
        
    }
    
    public static void DesSerializar_CreadorTabla() throws FileNotFoundException, IOException, ClassNotFoundException{
        try (ObjectInputStream input_nombreTabla = new ObjectInputStream(new FileInputStream("serializar/CreadorTabla_nombreTablas.dat"))) {
            nombresTablas = (List<String>) input_nombreTabla.readObject();
            input_nombreTabla.close();
            System.out.println("Desserializado nombTablas");
        }
        
    }
    
}
