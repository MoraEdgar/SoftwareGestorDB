/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 Programacion 3
 Realizado por: Juan Cobos, Adrian Flores, Edgar Mora
 * 
 */
public class Tools {
     public static int Longitud(CsvReader file) throws IOException
    {
        int count=0;
        while (file.readRecord())
        {
            count++;
        }
        return count;
    }
     
     public static void DeleteFile(String rutaF1, String rutaF2)
    {
        boolean existe = new File(rutaF1).exists();
        if (existe)
        {
            File ficheroAux = new File(rutaF1);
            ficheroAux.delete();
        }
        existe= new File(rutaF2).exists();
        if (existe)
        {
            File ficheroAux = new File(rutaF2);
            ficheroAux.delete();
        }
    }
     
     public static boolean orderIt(String R1, String R2, int campo) throws ParseException
     {
         switch(campo)
         {
             case 0:
                 return Integer.parseInt(R1)<=Integer.parseInt(R2);
             case 1:
                 return R1.compareTo(R2)<=0;
             case 2:
                 if(R1.equals("TRUE")){
                     R1="0";
                 }
                 else{
                     R1="1";
                 }
                 if(R2.equals("TRUE")){
                     R2="0";
                 }
                 else{
                     R2="1";
                 }
                 return Integer.parseInt(R1)<=Integer.parseInt(R2);
             case 3:
                 SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
                try
                {
                   Date fecha1 = sd.parse(R1);
                   Date fecha2 = sd.parse(R2);
                   return fecha1.compareTo(fecha2)<=0;
                }
                catch (ParseException e)
                {
                   // Error, la cadena de texto no se puede convertir en fecha.
                }
                 /*Date date1 = sd.parse(R1);                 
                 Date date2 = sd.parse(R2);
                 return date1.compareTo(date2)<=0;*/
         }
         return false;
     }
   
}
      