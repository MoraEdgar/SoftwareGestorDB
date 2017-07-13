/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Registros;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

/**
 Programacion 3
 Realizado por: Juan Cobos, Adrian Flores, Edgar Mora
 * 
 */
public class MainWithoutLibrary {
   public static ArrayList<Registros> l;
   
   public static final String SEPARATOR=";";
   public static final String QUOTE="\"";
   //public static ArrayList<Registros> LongitudesRegistros = new ArrayList<Registros>();
   
   
   public static void main(String[] args) {
      
      BufferedReader br = null;
      
      try {
         
         //br =new BufferedReader(new FileReader("src/Archivos/META_BD.csv"));
         br =new BufferedReader(new FileReader("src/ArchivosPrueba/tabla1.csv"));
         String line = br.readLine();
         while (null!=line) {
            String [] fields = line.split(SEPARATOR);
           
            //System.out.println(Arrays.toString(fields));
            
            fields = removeTrailingQuotes(fields);
            System.out.println(Arrays.toString(fields));
           // Registros s =new Registros()
            
            
            line = br.readLine();
         }
         
      } catch (Exception e) {
         System.err.println("Error! "+e.getMessage());
      } finally {
         if (null!=br){
            try {
               br.close();
            } catch (IOException e) {
               System.err.println("Error closing file !! "+e.getMessage());
            }
         }
      }
      
   }
   
   private static String[] removeTrailingQuotes(String[] fields) {
      String result[] = new String[fields.length];
      for (int i=0;i<result.length;i++){
         result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");
      }
      return result;
   }
   

}