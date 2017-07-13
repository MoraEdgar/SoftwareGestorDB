/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import IntercalacionBalanceada.MezclaBalanceada;
import IntercalacionBalanceada.OrdBalanceada;
import IntercalacionNatural.OrdNatural;
import Logica.GestorArchivo;
import Logica.GestorRegistro;
import static Logica.GestorRegistro.contarRegistros;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 Programacion 3
 Realizado por: Juan Cobos, Adrian Flores, Edgar Mora
 * 
 */
public class MostrarTabla extends javax.swing.JDialog {

    /**
     * Creates new form NewJDialog
     *
     * @param parent
     * @param modal
     * @param nombreTabla
     */
    private int opc = 0;

    public MostrarTabla(java.awt.Frame parent, boolean modal, String nombreTabla, int op, String nombre1, String nombre2, String nomCamp) {
        super(parent, modal);
        this.opc = op;
        initComponents();
        setLocationRelativeTo(null);
        if (opc == 1) {
            lNombreTabla.setText("SELECCION: " + nombreTabla + ", por el campo: " + nomCamp);
        } else if (opc == 2) {
            lNombreTabla.setText("UNIR: " + nombre1 + " con " + nombre2 + ", por el campo: " + nomCamp);
        } else {
            lNombreTabla.setText("TABLA: " + nombreTabla);
        }
        //fondoPane();
    }

    
    public void fondoPane(){
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Color.BLACK);
        UI.put("Panel.background", Color.BLACK);
        UI.put("OptionPane.messageForeground", Color.white);
    }
    public void generarTabla1(String nombreTabla) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        int cont = 0;
        Object[] tabla = new Object[(GestorRegistro.Sel.get(0).length)];
        
        for (int i = 0; i < GestorRegistro.Sel.get(0).length; i++) {
            if (GestorArchivo.LongitudesCampos.get(i).getNombre().equals(nombreTabla)) {
             
                tabla[cont] = GestorArchivo.LongitudesCampos.get(i).getCamp();
                cont++;
            }
        }

        modelo.setColumnIdentifiers(tabla);

        for (int i = 0; i < GestorRegistro.Sel.size(); i++) {
            modelo.addRow(GestorRegistro.Sel.get(i));
        }
        this.tCampos.setModel(modelo);
    }

    public void generarTabla2(String nombreTabla1, String nombreTabla2) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        int cont = 0;
        int mayor = 0;
        for (int i = 0; i < GestorRegistro.ContadoresUnir.size(); i++) {
            if (GestorRegistro.ContadoresUnir.get(i) >= GestorRegistro.ContadoresUnir.get(i + 1)) {
                mayor = GestorRegistro.ContadoresUnir.get(i);
                break;
            } else {
                mayor = GestorRegistro.ContadoresUnir.get(i + 1);
                break;
            }
        }
        Object[] tabla = new Object[mayor];
        for (int i = 0; i < tabla.length; i++) {
            tabla[cont] = " - ";
            cont++;
        }
        modelo.setColumnIdentifiers(tabla);
        for (int i = 0; i < GestorRegistro.Unir.size(); i++) {
            modelo.addRow(GestorRegistro.Unir.get(i));
        }
        this.tCampos.setModel(modelo);
    }

    public void generarTabla3(String campos, String nombreTabla) throws FileNotFoundException, IOException {
        DefaultTableModel modelo = new DefaultTableModel(null, campos.split(",")) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        

        FileReader fr = new FileReader("archivos/" + nombreTabla + ".csv");
        BufferedReader br = new BufferedReader(fr);
        String line = "";

        String aux = "";
        String newLine;
        line = br.readLine();
        while ((line = br.readLine()) != null) {
            String valores[] = line.split(",");
            modelo.addRow(valores);
        }

        this.tCampos.setModel(modelo);
    }
    public void generarTabla4(String campos, String nombreTabla) throws FileNotFoundException, IOException, ParseException {
        DefaultTableModel modelo = new DefaultTableModel(null, campos.split(",")) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        File f_entrada = new File("archivos/" + nombreTabla + ".csv");
            File f_salida = new File("archivos/" + nombreTabla + "salida"+".csv");
            LinkedList<String> lista = new LinkedList<String>();
            try {
            /* Creamos un FileReader para leer del fichero en forma de
            Bytes */
            FileReader fr = new FileReader(f_entrada);
            /* Creamos un BufferedReader a partir del FileReader para poder
            leer caracteres */
            BufferedReader br = new BufferedReader(fr);

            /* Creamos un FileWriter para escribir en el fichero en forma
            de Bytes */
            FileWriter fw = new FileWriter(f_salida);
            /* Creamos un PrintWriter a partir del FileWriter para poder
            escribir caracteres */
            PrintWriter pw = new PrintWriter(fw);
            /* Vamos leyendo linea a linea hasta llegar al final del
            fichero (null) */
            String linea = null;
            while ((linea = br.readLine()) != null){
            lista.add(linea);
            }
            /* Ordenamos la lista con el método sort de la clase
            Collections */
            Collections.sort(lista);
            /* Escribimos en el fichero de salida */
            Iterator iter = lista.iterator();
            String cadena;
            while (iter.hasNext())
            {
            cadena = (String) iter.next();
            pw.println(cadena);
            System.out.println("imprimiendo ordenado"+cadena);
            String line = "";

            
            }
            /* Cerramos los flujos de los ficheros */
            br.close();
            fr.close();
            pw.close();
            fw.close();
            } catch (FileNotFoundException e) {
          
            System.err.println("No se ha encontrado el fichero");
            } catch (IOException e) {
            e.printStackTrace();
            }
            OrdBalanceada ordena =new OrdBalanceada();
            ordena.sort(2, "archivos/" + nombreTabla);
            FileReader fr = new FileReader("archivos/" + nombreTabla +"salida"+".csv");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
            String aux = "";
            String newLine;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
            String valores[] = line.split(",");
            modelo.addRow(valores);
        }

        

        
        
        

        this.tCampos.setModel(modelo);
    }
    public void generarTabla5(String campos, String nombreTabla) throws FileNotFoundException, IOException, ParseException {
        DefaultTableModel modelo = new DefaultTableModel(null, campos.split(",")) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        OrdNatural n = new OrdNatural();
        OrdBalanceada t = new OrdBalanceada();
        t.sort(2,"archivos/" + nombreTabla);
        
        //n.sort(2, "archivos/" + nombreTabla);
        System.out.println("ORDENADO");
        FileReader fr = new FileReader("archivos/" + nombreTabla + ".csv");
        BufferedReader br = new BufferedReader(fr);
        String line = "";

        String aux = "";
        String newLine;
        line = br.readLine();
        while ((line = br.readLine()) != null) {
            String valores[] = line.split(",");
            modelo.addRow(valores);
        }
        

        

        this.tCampos.setModel(modelo);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lNombreTabla = new javax.swing.JLabel();
        btnEditarCampo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tCampos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(650, 350));

        lNombreTabla.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        lNombreTabla.setForeground(new java.awt.Color(255, 255, 255));
        lNombreTabla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lNombreTabla.setText("UNIR");
        lNombreTabla.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnEditarCampo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditarCampo.setText("Salir");
        btnEditarCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCampoActionPerformed(evt);
            }
        });

        jScrollPane1.getViewport().setBackground(Color.DARK_GRAY);

        tCampos.setBackground(new java.awt.Color(0, 0, 0));
        tCampos.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        tCampos.setForeground(new java.awt.Color(255, 255, 255));
        tCampos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tCampos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lNombreTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(btnEditarCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lNombreTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(219, 219, 219)
                .addComponent(btnEditarCampo))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCampoActionPerformed

        this.setVisible(false);
    }//GEN-LAST:event_btnEditarCampoActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditarCampo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lNombreTabla;
    private javax.swing.JTable tCampos;
    // End of variables declaration//GEN-END:variables
}
