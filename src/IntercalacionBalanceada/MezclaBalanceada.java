package IntercalacionBalanceada;


import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class MezclaBalanceada {
    public static void particionInicial(String rutaOriginal,String rutaF1,String rutaF2, int campo) throws FileNotFoundException, IOException, ParseException{
        CsvReader F = new CsvReader(rutaOriginal,',');
        CsvWriter F1 = new CsvWriter(new FileWriter(rutaF1),',');
        CsvWriter F2 = new CsvWriter(new FileWriter(rutaF2),',');
        String R,Aux;
        boolean band=true;
        if (F.readRecord())
            band = false;
        R = F.get(0);
        F1.write(R);
        R = F.get(1);
        F1.write(R);
        R = F.get(2);
        F1.write(R);
        R = F.get(3);
        F1.write(R);
        R = F.get(campo);
        F1.endRecord();
        Aux=R;
        band=true;
        while(F.readRecord()){
            R=F.get(campo);
            if(orderIt(Aux, R, campo)){ //Integer.parseInt(R)>=Integer.parseInt(Aux)
                Aux=R;
                
                if(band==true){
                    
                    R = F.get(0);
                    F1.write(R);
                    R = F.get(1);
                    F1.write(R);
                    R = F.get(2);
                    F1.write(R);
                    R = F.get(3);
                    F1.write(R);
                    F1.endRecord();
                }
                else{
                    R = F.get(0);
                    F2.write(R);
                    R = F.get(1);
                    F2.write(R);
                    R = F.get(2);
                    F2.write(R);
                    R = F.get(3);
                    F2.write(R);
                    F2.endRecord(); 
                }
            }
            else{
                Aux=R;
                if(band==true){
                    R = F.get(0);
                    F2.write(R);
                    R = F.get(1);
                    F2.write(R);
                    R = F.get(2);
                    F2.write(R);
                    R = F.get(3);
                    F2.write(R);
                    F2.endRecord(); 
                    band=false;
                }
                else{
                    R = F.get(0);
                    F1.write(R);
                    R = F.get(1);
                    F1.write(R);
                    R = F.get(2);
                    F1.write(R);
                    R = F.get(3);
                    F1.write(R);
                    F1.endRecord();
                    band=true;
                }
            }
        }
        F.close();
        F1.close();
        F2.close();   
    }
  
    public static void copiarTodo(String RutaCopiar,String RutaVaciar) throws IOException{
        CsvReader FB = new CsvReader(RutaVaciar,',');
        CsvWriter FC = new CsvWriter(new FileWriter(RutaCopiar),',');
        String R2="";
        while(FB.readRecord()){
            R2= FB.get(0);
            FC.write(R2);
            R2= FB.get(1);
            FC.write(R2);
            R2= FB.get(2);
            FC.write(R2);
            R2= FB.get(3);
            FC.write(R2);
            FC.endRecord();
        }
        FC.close();
        FB.close();
    }
    public static void eliminarAux(String rutaF1,String rutaF2,String rutaF3){
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
        existe= new File(rutaF3).exists();
        if (existe)
        {
            File ficheroAux = new File(rutaF3);
            ficheroAux.delete();
        }
    }
    
    
    public static void particionFusion(String rutaF1,String rutaF3,String rutaOriginal,String rutaF2, int campo) throws FileNotFoundException, IOException, ParseException{
        String R1="",R2="",R="";
        String Aux= "-300000";
        CsvReader FA = new CsvReader(rutaF1,',');
        CsvReader FB = new CsvReader(rutaF3,',');
        CsvWriter FC = new CsvWriter(new FileWriter(rutaOriginal),',');
        CsvWriter FD = new CsvWriter(new FileWriter(rutaF2),',');
        boolean band,dele1,dele2;
        band=true;
        FA.readRecord();
        FB.readRecord();
        R1=FA.get(campo);
        R2=FB.get(campo);
        if(R1.equals("")||R2.equals("")){
            return;
        }
        //return Integer.parseInt(R1)<=Integer.parseInt(R2);
        if(orderIt(R1,R2,campo)){//Integer.parseInt(R1)<=Integer.parseInt(R2)
            Aux=R1;
            
        }
        else{
            Aux=R2;
        }
        dele1=dele2=false;
        while((0!=OrdBalanceada.contar(rutaF1)||dele1!=true)&&(0!=OrdBalanceada.contar(rutaF3)||dele2!=true)){
            if(dele1){
                FA.readRecord();
                R1=FA.get(campo);
                dele1=false;
                if(R1.equals("")){
                    dele1=true;
                    break;
                }
            }
            if(dele2){
                FB.readRecord();
                R2=FB.get(campo);
                dele2=false;
                if(R2.equals("")){
                    dele2=true;
                    break;
                }
            }
            if(orderIt(R1,R2,campo)){//Integer.parseInt(R1)<Integer.parseInt(R2)
                if(orderIt(Aux,R1,campo)){//Integer.parseInt(R1)>=Integer.parseInt(Aux)
                    Aux=R1;
                    if(band){
                        R1= FA.get(0);
                        FC.write(R1);
                        R1= FA.get(1);
                        FC.write(R1);
                        R1= FA.get(2);
                        FC.write(R1);
                        R1= FA.get(3);
                        FC.write(R1);
                        FC.endRecord();                       
                    }
                    else{
                        R1= FA.get(0);
                        FD.write(R1);
                        R1= FA.get(1);
                        FD.write(R1);
                        R1= FA.get(2);
                        FD.write(R1);
                        R1= FA.get(3);
                        FD.write(R1);
                        FD.endRecord(); 
                    }
                    dele1=true;
                }
                else if(orderIt(Aux,R2,campo)){//Integer.parseInt(R2)>=Integer.parseInt(Aux)
                    Aux=R2;
                    if(band){
                        R2= FB.get(0);
                        FC.write(R2);
                        R2= FB.get(1);
                        FC.write(R2);
                        R2= FB.get(2);
                        FC.write(R2);
                        R2= FB.get(3);
                        FC.write(R2);
                        FC.endRecord(); 
                    }
                    else{
                        R2= FB.get(0);
                        FD.write(R2);
                        R2= FB.get(1);
                        FD.write(R2);
                        R2= FB.get(2);
                        FD.write(R2);
                        R2= FB.get(3);
                        FD.write(R2);
                        FD.endRecord(); 
                    } 
                    dele2=true;
                }
                else{
                    Aux=R1;
                    if(band){
                        R1= FA.get(0);
                        FD.write(R1);
                        R1= FA.get(1);
                        FD.write(R1);
                        R1= FA.get(2);
                        FD.write(R1);
                        R1= FA.get(3);
                        FD.write(R1);
                        FD.endRecord();
                        band=false;
                    }
                    else{
                        R1= FA.get(0);
                        FC.write(R1);
                        R1= FA.get(1);
                        FC.write(R1);
                        R1= FA.get(2);
                        FC.write(R1);
                        R1= FA.get(3);
                        FC.write(R1);
                        FC.endRecord(); 
                        band=true;
                    }
                    dele1=true;
                }
            }
            else{
                if(orderIt(Aux,R2,campo)){//Integer.parseInt(R2)>=Integer.parseInt(Aux)
                    Aux=R2;
                    if(band){
                        R2= FB.get(0);
                        FC.write(R2);
                        R2= FB.get(1);
                        FC.write(R2);
                        R2= FB.get(2);
                        FC.write(R2);
                        R2= FB.get(3);
                        FC.write(R2);
                        FC.endRecord();                       
                    }
                    else{
                        R2= FB.get(0);
                        FD.write(R2);
                        R2= FB.get(1);
                        FD.write(R2);
                        R2= FB.get(2);
                        FD.write(R2);
                        R2= FB.get(3);
                        FD.write(R2);
                        FD.endRecord(); 
                    }                  
                    dele2=true;
                }
                else if(orderIt(Aux,R1,campo)){//Integer.parseInt(R1)>=Integer.parseInt(Aux)
                    Aux=R1;                  
                    if(band){
                        R1= FA.get(0);
                        FC.write(R1);
                        R1= FA.get(1);
                        FC.write(R1);
                        R1= FA.get(2);
                        FC.write(R1);
                        R1= FA.get(3);
                        FC.write(R1);
                        FC.endRecord();                       
                    }
                    else{
                        R1= FA.get(0);
                        FD.write(R1);
                        R1= FA.get(1);
                        FD.write(R1);
                        R1= FA.get(2);
                        FD.write(R1);
                        R1= FA.get(3);
                        FD.write(R1);
                        FD.endRecord(); 
                    }
                    dele1=true;
                }
                else{ 
                    Aux=R2;
                    if(band){
                        R2= FB.get(0);
                        FD.write(R2);
                        R2= FB.get(1);
                        FD.write(R2);
                        R2= FB.get(2);
                        FD.write(R2);
                        R2= FB.get(3);
                        FD.write(R2);
                        FD.endRecord();    
                        band=false;
                    }
                    else{
                        R2= FB.get(0);
                        FC.write(R2);
                        R2= FB.get(1);
                        FC.write(R2);
                        R2= FB.get(2);
                        FC.write(R2);
                        R2= FB.get(3);
                        FC.write(R2);
                        FC.endRecord();  
                        band=true;
                    }
                    dele2=true;
                }
            }
        }
        if(dele1){
            if(orderIt(Aux,R2,campo)){//Integer.parseInt(R2)>=Integer.parseInt(Aux)
                Aux=R2;
                if(band){
                    R2=FB.get(0);
                    FC.write(R2);
                    R2=FB.get(1);
                    FC.write(R2);
                    R2=FB.get(2);
                    FC.write(R2);
                    R2=FB.get(3);
                    FC.write(R2);
                    FC.endRecord();
                }
                else{
                    R2=FB.get(0);
                    FD.write(R2);
                    R2=FB.get(1);
                    FD.write(R2);
                    R2=FB.get(2);
                    FD.write(R2);
                    R2=FB.get(3);
                    FD.write(R2);
                    FC.endRecord();
                }
            }
            else{
                
                Aux=R2;
                if(band){
                    R2=FB.get(0);
                    FD.write(R2);
                    R2=FB.get(1);
                    FD.write(R2);
                    R2=FB.get(2);
                    FD.write(R2);
                    R2=FB.get(3);
                    FD.write(R2);
                    FC.endRecord();
                    band=false;
                }
                else{
                    R2=FB.get(0);
                    FC.write(R2);
                    R2=FB.get(1);
                    FC.write(R2);
                    R2=FB.get(2);
                    FC.write(R2);
                    R2=FB.get(3);
                    FC.write(R2);
                    FC.endRecord();
                    band=true;
                }
            }
            while(FB.readRecord()){
                R2=FB.get(campo);
                if(orderIt(Aux,R2,campo)){//Integer.parseInt(R2)>=Integer.parseInt(Aux)
                //if(orderIt(R2,R2,campo)){//Integer.parseInt(R2)>=Integer.parseInt(R2)
                    Aux=R2;
                    if(band){
                        R2=FB.get(0);
                        FC.write(R2);
                        R2=FB.get(1);
                        FC.write(R2);
                        R2=FB.get(2);
                        FC.write(R2);
                        R2=FB.get(3);
                        FC.write(R2);
                        FC.endRecord();
                    }
                    else{
                        R2=FB.get(0);
                        FD.write(R2);
                        R2=FB.get(1);
                        FD.write(R2);
                        R2=FB.get(2);
                        FD.write(R2);
                        R2=FB.get(3);
                        FD.write(R2);
                        FC.endRecord();
                    }
                }
                else{
                    Aux=R2;                  
                    if(band){
                        R2=FB.get(0);
                        FD.write(R2);
                        R2=FB.get(1);
                        FD.write(R2);
                        R2=FB.get(2);
                        FD.write(R2);
                        R2=FB.get(3);
                        FD.write(R2);
                        FC.endRecord();
                        band=false;
                    }
                    else{
                        R2=FB.get(0);
                        FC.write(R2);
                        R2=FB.get(1);
                        FC.write(R2);
                        R2=FB.get(2);
                        FC.write(R2);
                        R2=FB.get(3);
                        FC.write(R2);
                        FC.endRecord();
                        band=true;
                    } 
                }
            }


        }
        if(dele2){
            if(orderIt(Aux,R1,campo)){//Integer.parseInt(R1)>=Integer.parseInt(Aux)
                Aux=R1;
                if(band){
                    R1=FA.get(0);
                    FC.write(R1);
                    R1=FA.get(1);
                    FC.write(R1);
                    R1=FA.get(2);
                    FC.write(R1);
                    R1=FA.get(3);
                    FC.write(R1);
                    FC.endRecord();
                }
                else{
                    R1=FA.get(0);
                    FD.write(R1);
                    R1=FA.get(1);
                    FD.write(R1);
                    R1=FA.get(2);
                    FD.write(R1);
                    R1=FA.get(3);
                    FD.write(R1);
                    FD.endRecord();
                }
            }
        
            else{
                Aux=R1;
                if(band){
                    R1=FA.get(0);
                    FD.write(R1);
                    R1=FA.get(1);
                    FD.write(R1);
                    R1=FA.get(2);
                    FD.write(R1);
                    R1=FA.get(3);
                    FD.write(R1);
                    FD.endRecord();
                    band=false;
                }
                else{
                    R1=FA.get(0);
                    FC.write(R1);
                    R1=FA.get(1);
                    FC.write(R1);
                    R1=FA.get(2);
                    FC.write(R1);
                    R1=FA.get(3);
                    FC.write(R1);
                    FC.endRecord();
                    band=true;
                }
            }
            while(FA.readRecord()){
                R1=FA.get(campo);
                
                
                if(orderIt(Aux,R1,campo)){//Integer.parseInt(R1)>=Integer.parseInt(Aux)
                    Aux = R1;
                    if(band){
                        R1=FA.get(0);  
                        FC.write(R1); 
                        R1=FA.get(1);
                        FC.write(R1);
                        R1=FA.get(2);
                        FC.write(R1);
                        R1=FA.get(3);
                        FC.write(R1);
                        FC.endRecord();
                       
                    }else{
                        R1=FA.get(0);  
                        FD.write(R1); 
                        R1=FA.get(1);
                        FD.write(R1);
                        R1=FA.get(2);
                        FD.write(R1);
                        R1=FA.get(3);
                        FD.write(R1);
                        FD.endRecord();
                    }
                }
                else{
                    Aux=R1;
                    if(band){
                        R1=FA.get(0);   
                        FD.write(R1); 
                        R1=FA.get(1);
                        FD.write(R1);
                        R1=FA.get(2);
                        FD.write(R1);
                        R1=FA.get(3);
                        FD.write(R1);
                        FD.endRecord();
                        band=false;
                    }
                    else{
                        R1=FA.get(0);    
                        FC.write(R1); 
                        R1=FA.get(1);
                        FC.write(R1);
                        R1=FA.get(2);
                        FC.write(R1);
                        R1=FA.get(3);
                        FC.write(R1);
                        FC.endRecord();
                        band=true;
                    }
                }
            }
        }
        FA.close();
        FB.close();
        FC.close(); 
        FD.close();
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

         }
         return false;
     }
}