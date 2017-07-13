/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntercalacionNatural;

import Tools.Tools;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
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
public class OrdenacionNatural {
         
     public static void Fusiona(String rutaOriginal, String rutaF1, String rutaF2, int part, int campo) throws FileNotFoundException, IOException, ParseException{         
        int limit=1;
        CsvWriter Original = new CsvWriter(new FileWriter(rutaOriginal),',');
        CsvReader F1 = new CsvReader(rutaF1,',');
        CsvReader F2 = new CsvReader(rutaF2,',');
        String auxR1="", auxR2="";
        boolean end1=true, end2=true;
        
        if (F1.readRecord())
        {    
            auxR1=F1.get(campo);
            end1=false;
        }
        if (F2.readRecord())
        {    
            auxR2=F2.get(campo);
            end2=false;
        }
        
        
        while((!end1 || !end2)&&limit<part) // && limit < part
        {
            
            while(!end1 && !end2 && (Tools.orderIt(auxR1, F1.get(campo), campo) && Tools.orderIt(auxR2, F2.get(campo), campo)) )
            {
               // System.out.printf("\nEste es auxR1: %d. Este es auxR2: %d. --- Estos son F1.get: %s. F2.get: %s\n",
                 //       auxR1, auxR2, F1.get(0), F2.get(0));
                // Tools.orderIt(auxR1, F1.get(campo), campo) && Tools.orderIt(auxR2, F2.get(campo), campo)
                if ( orderIt(F1.get(campo),F2.get(campo), campo) ) // > de "<" orderIt(F1.get(campo),F2.get(campo), campo)
                {
                    //System.out.println("Escribe en ORI: "+F1.get(0)+". Desde F1. Cuando F1<F2");
                    Original.write(F1.get(0));
                    Original.write(F1.get(1));
                    Original.write(F1.get(2));
                    Original.write(F1.get(3));
                    auxR1 = F1.get(campo);
                    Original.endRecord();
                    end1=true;
                    if (F1.readRecord()){
                        if ( Tools.orderIt(auxR1, F1.get(campo), campo)) // Tools.orderIt(auxR1, F1.get(campo), campo)
                            auxR1 = F1.get(campo);
                        end1=false;
                    }                        
                }else
                {
                    //System.out.println("Escribe en ORI: "+F2.get(0)+". Desde F2. Cuando F2<F1");
                    Original.write(F2.get(0));
                    Original.write(F2.get(1));
                    Original.write(F2.get(2));
                    Original.write(F2.get(3));
                    auxR2 = F2.get(campo);
                    Original.endRecord();
                    end2=true;
                    if (F2.readRecord()){
                        end2=false;
                        if ( Tools.orderIt(auxR2, F2.get(campo), campo) ) // Tools.orderIt(auxR2, F2.get(campo), campo)
                            auxR2 = F2.get(campo);
                        
                    }
                       
                }                
            }
            //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Antes de terminar tramoF1");
            while (!end1 && Tools.orderIt(auxR1, F1.get(campo), campo) ) // Tools.orderIt(auxR1, F1.get(campo), campo)
            {
                //System.out.println("Escribe en ORI: "+F1.get(0)+". Desde F1.");
                Original.write(F1.get(0));
                Original.write(F1.get(1));
                Original.write(F1.get(2));
                Original.write(F1.get(3));
                auxR1 = F1.get(campo);
                Original.endRecord();
                end1=true;
                if (F1.readRecord()){
                    end1=false;
                }       
            }
            //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Antes de terminar tramoF2");
            while (!end2 && Tools.orderIt(auxR2, F2.get(campo),campo) ) // Tools.orderIt(auxR2, F2.get(campo),campo)
            {
                //System.out.println("Escribe en ORI: "+F2.get(0)+ ". Desde F2");
                Original.write(F2.get(0));
                Original.write(F2.get(1));
                Original.write(F2.get(2));
                Original.write(F2.get(3));
                auxR2 = F2.get(campo);
                Original.endRecord();
                end2=true;
                if (F2.readRecord()){
                    end2=false;
                }                   
            }
            if (!end1)            // El huevo de oro
                auxR1 = F1.get(campo);
            if (!end2)
                auxR2 = F2.get(campo);
            limit++;
        }  
        Original.close();
        F1.close();
        F2.close();         
     }
     
     
     public static int Particiona(String rutaOriginal, String rutaF1, String rutaF2, int campo) throws FileNotFoundException, IOException, ParseException
     {
         int particiona=1;
         CsvReader Original = new CsvReader(rutaOriginal, ',');
         CsvWriter F1 = new CsvWriter (new FileWriter(rutaF1), ',');
         CsvWriter F2 = new CsvWriter (new FileWriter(rutaF2), ',');
         boolean end=true;
         boolean whatFile=true;         
         String auxReg="";
         if (Original.readRecord())
             end=false;
         //System.out.println("Aqui comienza la particion======================\n");
         while (!end)
         {
             if (whatFile)
             {
                //System.out.println("ESCRIBRI F1: "+Original.get(0));
                F1.write(Original.get(0));
                F1.write(Original.get(1));
                F1.write(Original.get(2));
                F1.write(Original.get(3));
                F1.endRecord();
                end=true;
             }else
             {
                 //System.out.println("ESCRIBRI F2: "+Original.get(0));
                F2.write(Original.get(0));
                F2.write(Original.get(1));
                F2.write(Original.get(2));
                F2.write(Original.get(3));
                F2.endRecord();
                end=true;
             }
             
             auxReg= Original.get(campo);
             
             if (Original.readRecord())
                end=false;
             
            // System.out.printf("\nEste es auxReg: %d. Y este es Original.get: %s\n",
              //       auxReg, Original.get(0));
             if (!end && orderIt(Original.get(campo), auxReg, campo)) // Antes esta <= !!! orderIt(Original.get(campo), auxReg, campo)
             {
                // System.out.println("\nEntre al bucle!!\n\n");
                 whatFile = !whatFile;
                 particiona++; 
             }             
         }
            Original.close();
            F1.close();
            F2.close();
         return particiona;
     }
     
     public static boolean orderIt(String R1, String R2, int campo) throws ParseException
     {
         switch(campo)
         {
             case 0:
                 return Integer.parseInt(R1)<Integer.parseInt(R2);
             case 1:
                 return R1.compareTo(R2)<0;
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
                 return Integer.parseInt(R1)<Integer.parseInt(R2);
             case 3:
                 SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
                try
                {
                   Date fecha1 = sd.parse(R1);
                   Date fecha2 = sd.parse(R2);
                   return fecha1.compareTo(fecha2)<0;
                }
                catch (ParseException e)
                {
                   // Error, la cadena de texto no se puede convertir en fecha.
                }

         }
         return false;
     }    
}