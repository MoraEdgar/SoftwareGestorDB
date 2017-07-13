/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Campos;
import Datos.NumeroRegistro;
import Datos.Registros;
import static Logica.GestorRegistro.ContadoresUnir;
import static Logica.GestorRegistro.LongitudesRegistros;
import static Logica.GestorRegistro.Sel;
import static Logica.GestorRegistro.Unir;
import static Logica.GestorRegistro.crearTablaDeserializar;
import Patrones.TemplateSerializar;
import com.csvreader.CsvReader;
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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 Programacion 3
 Realizado por: Juan Cobos, Adrian Flores, Edgar Mora
 * 
 */
    
public class GestorArchivo extends TemplateSerializar implements Serializable{
    
    private ArrayList<NumeroRegistro> tab= new ArrayList(); 
    public static String nombres[];
    private static final String PATH_ARCHIVO = "archivos/META_BD.csv";
    public static ArrayList<Campos> LongitudesCampos = new ArrayList<Campos>();
    
    
    public static void EliminarArchivo(String nomTabla){
        for (int i = (LongitudesCampos.size())-1; i >=0; i--) {
            if(LongitudesCampos.get(i).getNombre().equals(nomTabla)){
                System.out.println("long camp: "+LongitudesCampos.get(i).getNombre());
                LongitudesCampos.remove(i);
            }
        }
    }
    
  public static boolean crearTabla(String text) {
        System.out.println("CREAR TABLA: " + text);
        try {

            File ficheroTabla = new File(PATH_ARCHIVO);

            FileWriter archivo = new FileWriter(ficheroTabla, true);
            CsvWriter csvOutput = new CsvWriter(archivo, ',');

            String nombreTabla;

            String longitud = text.substring(text.indexOf(" LONGITUD ") + 10, text.length());

            String clave = text.substring(text.indexOf(" CLAVE ") + 7, text.indexOf(" LONGITUD "));

            System.out.println("la clave es: " + clave);
            boolean campos = text.contains(" CAMPOS ");
            if (campos) {
                csvOutput.setDelimiter(',');
                int posTabla = 0;
                nombreTabla = text.substring(0, text.indexOf(" CAMPOS "));
                String nuevoText = text.substring(text.indexOf(" CAMPOS ") + 8, text.indexOf(" CLAVE "));
                System.out.println("nuevo texto" + nuevoText);

                String tablas[] = nuevoText.substring(0, nuevoText.length()).split(",");
                String longitudes[] = longitud.substring(0, longitud.length()).split(",");
             
                for (int i = 0; i < tablas.length; i++) {
                    if (tablas[i].equals(clave)) {
                        posTabla = i;
                    }
                }
                nombres = new String[tablas.length];
                for (int i = 0; i < tablas.length; i++) {
                    separarLongitud(tablas[i],longitudes[i], i, nombreTabla, tablas.length, i, posTabla, clave);

                }

                if (CreadorTabla.getInstancia().addNombreTabla(nombreTabla, nombres)) {
                    return true;
                } else {
                    csvOutput.write("0");
                    csvOutput.write(nombreTabla);
                    String cant = String.valueOf(GestorRegistro.contarRegistros(nombreTabla));
                    csvOutput.write(cant);
                    csvOutput.write(nombres[0]);
                    csvOutput.setDelimiter(';');

                    for (int i = 1; i < nombres.length; i++) {

                        csvOutput.write(nombres[i]);
                    }
                    csvOutput.setDelimiter(' ');
                    csvOutput.write("\n");
                    csvOutput.endRecord();
                    serializarGestorArchivo();
                }
            }

            csvOutput.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Object[]> crearSalida() throws FileNotFoundException, IOException {
        boolean alreadyExists = new File(PATH_ARCHIVO).exists();
        if (alreadyExists) {

            CsvReader tablas_import = new CsvReader("archivos/META_BD.csv");
            List<Object[]> tabla = new ArrayList<Object[]>();

            while (tablas_import.readRecord()) {
                Object tablas[] = tablas_import.getValues();
                if (tablas[0].equals("0")) {
                    tablas[3] = tablas[3].toString().replace(";", ",");
                    tabla.add(Arrays.copyOfRange(tablas, 1, tablas.length));
                }

                for (int i = 0; i < tablas.length; i++) {
                    System.out.println(tablas[i]);
                }
            }
            tablas_import.close();
            return tabla;
        } else {
            System.out.println("no hay");
        }
        return null;
    }

    
    public static void separarLongitud(String palabras, String num, int val, String nombre, int cantidad, int claveEsp, int claveGen, String key) throws IOException {

        int valor = Integer.parseInt(num);

        Campos agregar = new Campos(palabras, valor, nombre, cantidad, claveEsp, claveGen, key);

        LongitudesCampos.add(agregar);
        nombres[val] = palabras;
        serializarGestorArchivo();

    }

    public static void remover(String nombreTabla){
        LongitudesCampos.remove(nombreTabla);
    }
    
    public static void serializarGestorArchivo() throws IOException{
        try (ObjectOutputStream output_LongitudesCampos = new ObjectOutputStream(new FileOutputStream("serializar/GestorArchivo_LongitudesCampos.dat"))) {
            output_LongitudesCampos.writeObject(LongitudesCampos);
            output_LongitudesCampos.close();
            System.out.println("GestorArchivo Serializado");
        }catch(IOException x){
            
        }
    }
    
    public static void desSerializarGestorArchivo() throws FileNotFoundException, IOException, ClassNotFoundException{
        
        try (ObjectInputStream input_LongCamp = new ObjectInputStream(new FileInputStream("serializar/GestorArchivo_LongitudesCampos.dat"))) {
            LongitudesCampos=(ArrayList<Campos>)input_LongCamp.readObject();
            input_LongCamp.close();
            System.out.println("DESserializado long");
        }catch(IOException x){
            
        }
        /*
        try (ObjectInputStream input_nombres = new ObjectInputStream(new FileInputStream("serializar/GestorArchivo_nombres.dat"))) {
            nombres = (String[]) input_nombres.readObject();
            input_nombres.close();
            
            System.out.println("DESserializado nomb");
        }catch(IOException x){
            
        }*/
        
    }

    @Override
    protected void cargarGestorRegistro() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input_LongCamp = new ObjectInputStream(new FileInputStream("serializar/GestorRegistro_LongitudRegistros.dat"))) {
            LongitudesRegistros=(ArrayList<Registros>)input_LongCamp.readObject();
        }catch (IOException X) {

        }
        crearTablaDeserializar();
        
    
    }

    @Override
    protected void cargarGestorArchivo() throws IOException, ClassNotFoundException {
        
        try (ObjectInputStream input_LongCamp = new ObjectInputStream(new FileInputStream("serializar/GestorArchivo_LongitudesCampos.dat"))) {
            LongitudesCampos=(ArrayList<Campos>)input_LongCamp.readObject();
            input_LongCamp.close();
            System.out.println("DESserializado long");
        }catch(IOException x){
            
        }
     /*   
        try (ObjectInputStream input_nombres = new ObjectInputStream(new FileInputStream("serializar/GestorArchivo_nombres.dat"))) {
            nombres = (String[]) input_nombres.readObject();
            input_nombres.close();
            
            System.out.println("DESserializado nomb");
        }catch(IOException x){
            
        }*/
        
        
    }

    
    
}
