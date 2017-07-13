/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 Programacion 3
 Realizado por: Juan Cobos, Adrian Flores, Edgar Mora
 * 
 */
public class EliminadorTabla {

    public static boolean eliminarTablaArchivo(String nombreTabla) throws IOException {
        
        String nombreArchivoTabla = "archivos/" + nombreTabla + ".csv";
        File archivo = new File(nombreArchivoTabla);
        
        if ( archivo.delete() ) {
            String line = null;
            RandomAccessFile file = new RandomAccessFile("archivos/META_BD.csv", "rw");
            long pointerAnt = file.getFilePointer();
            long pointerAct;
            
            while((line = file.readLine()) != null){
                pointerAct = file.getFilePointer();
                if(line.contains(nombreTabla) && line.charAt(0)=='0'){
                    file.seek(pointerAnt);
                    file.write("1".getBytes());
                    CreadorTabla.getInstancia().removeNombreTabla(nombreTabla);
                    file.close();
                    return true;
                }
                pointerAnt = pointerAct;
            }
            file.close();         
        }
        return false;
        
    }
    
}
