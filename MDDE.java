package mdde;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
     * Crea el jFrame donde se despliegan todos los datos
     * @author MaraDDE
*/
public class MDDE extends javax.swing.JFrame {

    /**
     * Creates new form VistaEstadisticos
     */
    public Grafica datos;
    public Dispersion disp;
    public Burbuja burb;
    public Secuencia secu;
    public Barras barr;
    public Boxplot boxp;
    public JLabel label = new JLabel("");
    
    public MDDE() {
        JFileChooser selector = new JFileChooser();
        datos = new Grafica(3); //tres columnas
        File archivo;
        String ejes[] =  new String[3]; //para nombrar columnas
        List<Integer> nums1 = new ArrayList<>(); //Arreglo de cantidad desconocida
        List<Integer> nums2 = new ArrayList<>();
        List<Integer> nums3 = new ArrayList<>();
        int estado = selector.showOpenDialog(null);
        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                //File archivo = new File("datos.txt");
                archivo = selector.getSelectedFile();
                Scanner escaner = new Scanner(archivo);
                ejes[0] = escaner.next(); //nombres ejes
                ejes[1] = escaner.next();
                ejes[2] = escaner.next();
                while(escaner.hasNextInt()){ //Hace linea por linea
                    nums1.add(escaner.nextInt());
                    nums2.add(escaner.nextInt());
                    nums3.add(escaner.nextInt());
                }
            }catch (FileNotFoundException e){
                System.out.println("Error en lectura");
            }
        } else 
            System.out.println("Se cancelo abrir archivo"); 
        
        datos.setColumna(ejes[0],new Estadisticos(nums1.stream().mapToInt(i->i).toArray(), nums1.size(),false), 0); // https://stackoverflow.com/questions/960431/how-to-convert-listinteger-to-int-in-java
        datos.setColumna(ejes[1],new Estadisticos(nums2.stream().mapToInt(i->i).toArray(), nums2.size(),false), 1); //(int [], int tamaño, boolean)
        datos.setColumna(ejes[2],new Estadisticos(nums3.stream().mapToInt(i->i).toArray(), nums3.size(),false), 2); // pasar de arraylist a []
        disp = new Dispersion(0,1,3);
        disp.setColumna(ejes[0],new Estadisticos(nums1.stream().mapToInt(i->i).toArray(), nums1.size(),false), 0); // https://stackoverflow.com/questions/960431/how-to-convert-listinteger-to-int-in-java
        disp.setColumna(ejes[1],new Estadisticos(nums2.stream().mapToInt(i->i).toArray(), nums2.size(),false), 1); 
        disp.setColumna(ejes[2],new Estadisticos(nums3.stream().mapToInt(i->i).toArray(), nums3.size(),false), 2);
        burb = new Burbuja(0,1,2,3);
        burb.setColumna(ejes[0],new Estadisticos(nums1.stream().mapToInt(i->i).toArray(), nums1.size(),false), 0); // https://stackoverflow.com/questions/960431/how-to-convert-listinteger-to-int-in-java
        burb.setColumna(ejes[1],new Estadisticos(nums2.stream().mapToInt(i->i).toArray(), nums2.size(),false), 1);
        burb.setColumna(ejes[2],new Estadisticos(nums3.stream().mapToInt(i->i).toArray(), nums3.size(),false), 2);
        secu = new Secuencia(0,3);
        secu.setColumna(ejes[0],new Estadisticos(nums1.stream().mapToInt(i->i).toArray(), nums1.size(),false), 0); // https://stackoverflow.com/questions/960431/how-to-convert-listinteger-to-int-in-java
        secu.setColumna(ejes[1],new Estadisticos(nums2.stream().mapToInt(i->i).toArray(), nums2.size(),false), 1);
        secu.setColumna(ejes[2],new Estadisticos(nums3.stream().mapToInt(i->i).toArray(), nums3.size(),false), 2);
        barr = new Barras(0,3);
        barr.setColumna(ejes[0],new Estadisticos(nums1.stream().mapToInt(i->i).toArray(), nums1.size(),false), 0); // https://stackoverflow.com/questions/960431/how-to-convert-listinteger-to-int-in-java
        barr.setColumna(ejes[1],new Estadisticos(nums2.stream().mapToInt(i->i).toArray(), nums2.size(),false), 1);
        barr.setColumna(ejes[2],new Estadisticos(nums3.stream().mapToInt(i->i).toArray(), nums3.size(),false), 2);
        boxp = new Boxplot(0,null,null,3);
        boxp.setColumna(ejes[0],new Estadisticos(nums1.stream().mapToInt(i->i).toArray(), nums1.size(),false), 0); // https://stackoverflow.com/questions/960431/how-to-convert-listinteger-to-int-in-java
        boxp.setColumna(ejes[1],new Estadisticos(nums2.stream().mapToInt(i->i).toArray(), nums2.size(),false), 1);
        boxp.setColumna(ejes[2],new Estadisticos(nums3.stream().mapToInt(i->i).toArray(), nums3.size(),false), 2); // Hice uno para cada uno porque cada uno represneta su propia grafica
       
        initComponents();
        jCX1.setModel(new DefaultComboBoxModel(ejes)); //Llenar combobox con nombre columnas
        jCX2.setModel(new DefaultComboBoxModel(ejes));
        jCX3.setModel(new DefaultComboBoxModel(ejes));
        jCX4.setModel(new DefaultComboBoxModel(ejes));
        jCB1.setText(ejes[0]); 
        jCB2.setText(ejes[1]);
        jCB3.setText(ejes[2]);
        this.buttonGroup1 = new ButtonGroup();
        this.buttonGroup1.add(jRB1_1); //1_burbuja o dispersion
        this.buttonGroup1.add(jRB1_2);
        this.buttonGroup2 = new ButtonGroup();
        this.buttonGroup2.add(jRB2_1); //"2_barra o frecuencia
        this.buttonGroup2.add(jRB2_2);
        jRB1_1.setSelected(true);//pongo opciones por default
        jRB2_1.setSelected(true);
        jCB1.setSelected(true);
        jCX2.setSelectedIndex(1);
        this.disp.setIndexX(jCX1.getSelectedIndex()); //valores de columnas
        this.disp.setIndexY(jCX2.getSelectedIndex());
        this.burb.setIndexX(jCX1.getSelectedIndex());
        this.burb.setIndexY(jCX2.getSelectedIndex());
        this.burb.setIndexRadio(3 - (jCX1.getSelectedIndex() + jCX2.getSelectedIndex())); //Para que me de el faltante indice z
        generaPanel1(); 
        generaPanel2();
        generaPanel3();
        generaPanel4();      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        p1 = new javax.swing.JPanel();
        p2 = new javax.swing.JPanel();
        p3 = new javax.swing.JPanel();
        p4 = new javax.swing.JPanel();
        jRB1_1 = new javax.swing.JRadioButton();
        jRB1_2 = new javax.swing.JRadioButton();
        jCX1 = new javax.swing.JComboBox<>();
        jCX2 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jRB2_1 = new javax.swing.JRadioButton();
        jRB2_2 = new javax.swing.JRadioButton();
        jCB1 = new javax.swing.JCheckBox();
        jCB3 = new javax.swing.JCheckBox();
        jCB2 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jCX3 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jCX4 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setMaximumSize(new java.awt.Dimension(1000, 700));
        setSize(new java.awt.Dimension(1000, 700));

        p1.setForeground(new java.awt.Color(255, 255, 255));
        p1.setMaximumSize(new java.awt.Dimension(350, 250));
        p1.setMinimumSize(new java.awt.Dimension(350, 250));
        p1.setPreferredSize(new java.awt.Dimension(350, 250));

        p2.setForeground(new java.awt.Color(255, 255, 255));
        p2.setMaximumSize(new java.awt.Dimension(350, 250));
        p2.setMinimumSize(new java.awt.Dimension(350, 250));

        javax.swing.GroupLayout p2Layout = new javax.swing.GroupLayout(p2);
        p2.setLayout(p2Layout);
        p2Layout.setHorizontalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        p2Layout.setVerticalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        p3.setForeground(new java.awt.Color(255, 255, 255));
        p3.setMaximumSize(new java.awt.Dimension(350, 250));
        p3.setMinimumSize(new java.awt.Dimension(350, 250));

        p4.setForeground(new java.awt.Color(255, 255, 255));
        p4.setMaximumSize(new java.awt.Dimension(350, 250));
        p4.setMinimumSize(new java.awt.Dimension(350, 250));

        jRB1_1.setText("Dispersión");
        jRB1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB1_1ActionPerformed(evt);
            }
        });

        jRB1_2.setText("Burbuja");
        jRB1_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB1_2ActionPerformed(evt);
            }
        });

        jCX1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCX1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCX1ActionPerformed(evt);
            }
        });

        jCX2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCX2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCX2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Eje X:");

        jLabel2.setText("Eje Y:");

        jRB2_1.setText("Secuencia Temporal");
        jRB2_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB2_1ActionPerformed(evt);
            }
        });

        jRB2_2.setText("Barras de Frecuencias");
        jRB2_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB2_2ActionPerformed(evt);
            }
        });

        jCB1.setText("jCheckBox1");
        jCB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB1ActionPerformed(evt);
            }
        });

        jCB3.setText("jCheckBox1");
        jCB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB3ActionPerformed(evt);
            }
        });

        jCB2.setText("jCheckBox1");
        jCB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Eje X:");

        jCX3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCX3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCX3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Estadisticos de la serie:");

        jCX4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCX4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCX4ActionPerformed(evt);
            }
        });

        jButton1.setText("Autor");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jRB1_1)
                                .addComponent(jRB1_2))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jCX2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jCX1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(p3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCB1)
                        .addGap(18, 18, 18)
                        .addComponent(jCB2)
                        .addGap(18, 18, 18)
                        .addComponent(jCB3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCX4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(p4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jRB2_1)
                                .addComponent(jRB2_2))
                            .addGap(30, 30, 30)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jCX3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(87, 87, 87))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRB1_1)
                            .addComponent(jLabel1)
                            .addComponent(jCX1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRB2_1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRB1_2)
                            .addComponent(jLabel2)
                            .addComponent(jCX2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRB2_2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jCX3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(p3, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(p4, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCB3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCB1)
                        .addComponent(jCB2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jCX4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRB1_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB1_1ActionPerformed
        generaPanel1();
    }//GEN-LAST:event_jRB1_1ActionPerformed

    private void jRB2_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB2_1ActionPerformed
        this.barr.setIndexX(this.jCX3.getSelectedIndex());
        this.secu.setIndexX(this.jCX3.getSelectedIndex());
        generaPanel2(); 
    }//GEN-LAST:event_jRB2_1ActionPerformed

    private void jCX4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCX4ActionPerformed
        generaPanel4();
    }//GEN-LAST:event_jCX4ActionPerformed

    private void jCX1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCX1ActionPerformed
        if(jCX1.getSelectedIndex() == jCX2.getSelectedIndex()){
            switch (jCX1.getSelectedIndex()) {
                case 0:
                    jCX2.setSelectedIndex(1);
                    break;
                case 1:
                    jCX2.setSelectedIndex(2);
                    break;
                case 2:
                    jCX2.setSelectedIndex(0);
                    break;
                default:
                    break;
            }
        }
        this.disp.setIndexX(jCX1.getSelectedIndex());
        this.disp.setIndexY(jCX2.getSelectedIndex());
        this.burb.setIndexX(jCX1.getSelectedIndex());
        this.burb.setIndexY(jCX2.getSelectedIndex());
        this.burb.setIndexRadio(3 - (jCX1.getSelectedIndex() + jCX2.getSelectedIndex()));
        generaPanel1();
    }//GEN-LAST:event_jCX1ActionPerformed

    private void jCB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB1ActionPerformed
        if(!jCB1.isSelected() && !jCB2.isSelected() && !jCB3.isSelected())
            jCB1.setSelected(true);                               
        if(jCB1.isSelected())
            this.boxp.setGraf1(0);
        else
            this.boxp.setGraf1(null);                               
        if(jCB2.isSelected())
            this.boxp.setGraf2(1);
        else
            this.boxp.setGraf2(null);                               
        if(jCB3.isSelected())
            this.boxp.setGraf3(2);
        else
            this.boxp.setGraf3(null);
        generaPanel3();
    }//GEN-LAST:event_jCB1ActionPerformed

    private void jCX2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCX2ActionPerformed
        if(jCX2.getSelectedIndex() == jCX1.getSelectedIndex()){
            switch (jCX2.getSelectedIndex()) {
                case 0:
                    jCX1.setSelectedIndex(1);
                    break;
                case 1:
                    jCX1.setSelectedIndex(2);
                    break;
                case 2:
                    jCX1.setSelectedIndex(0);
                    break;
                default:
                    break;
            }
        }
        this.disp.setIndexX(jCX1.getSelectedIndex());
        this.disp.setIndexY(jCX2.getSelectedIndex());
        this.burb.setIndexX(jCX1.getSelectedIndex());
        this.burb.setIndexY(jCX2.getSelectedIndex());
        this.burb.setIndexRadio(3 - (jCX1.getSelectedIndex() + jCX2.getSelectedIndex()));
        generaPanel1();
    }//GEN-LAST:event_jCX2ActionPerformed

    private void jCB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB2ActionPerformed
        if(!jCB1.isSelected() && !jCB2.isSelected() && !jCB3.isSelected())
            jCB1.setSelected(true);                               
        if(jCB1.isSelected())
            this.boxp.setGraf1(0);
        else
            this.boxp.setGraf1(null);                               
        if(jCB2.isSelected())
            this.boxp.setGraf2(1);
        else
            this.boxp.setGraf2(null);                               
        if(jCB3.isSelected())
            this.boxp.setGraf3(2);
        else
            this.boxp.setGraf3(null);
        generaPanel3();
    }//GEN-LAST:event_jCB2ActionPerformed

    private void jCB3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB3ActionPerformed
        if(!jCB1.isSelected() && !jCB2.isSelected() && !jCB3.isSelected())
            jCB1.setSelected(true);                               
        if(jCB1.isSelected())
            this.boxp.setGraf1(0);
        else
            this.boxp.setGraf1(null);                               
        if(jCB2.isSelected())
            this.boxp.setGraf2(1);
        else
            this.boxp.setGraf2(null);                               
        if(jCB3.isSelected())
            this.boxp.setGraf3(2);
        else
            this.boxp.setGraf3(null);
        generaPanel3();
    }//GEN-LAST:event_jCB3ActionPerformed

    private void jRB1_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB1_2ActionPerformed
        generaPanel1();
    }//GEN-LAST:event_jRB1_2ActionPerformed

    private void jRB2_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB2_2ActionPerformed
        this.barr.setIndexX(this.jCX3.getSelectedIndex());
        this.secu.setIndexX(this.jCX3.getSelectedIndex());
        generaPanel2();
    }//GEN-LAST:event_jRB2_2ActionPerformed

    private void jCX3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCX3ActionPerformed
        this.barr.setIndexX(this.jCX3.getSelectedIndex());
        this.secu.setIndexX(this.jCX3.getSelectedIndex());
        generaPanel2();
    }//GEN-LAST:event_jCX3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Graphics g = p1.getGraphics();
        p1.setBackground(Color.white);
        g.setColor(Color.white);
        g.fillRect(0, 0, p1.getWidth(), p1.getHeight());
        Graphics g2 = p2.getGraphics();
        p2.setBackground(Color.white);
        g2.setColor(Color.white);
        g2.fillRect(0, 0, p2.getWidth(), p2.getHeight());
        Graphics g3 = p3.getGraphics();
        p3.setBackground(Color.white);
        g3.setColor(Color.white);
        g3.fillRect(0, 0, p3.getWidth(), p3.getHeight());
        Graphics g4 = p4.getGraphics();
        p4.setBackground(Color.white);
        g4.setColor(Color.white);
        g4.fillRect(0, 0, p4.getWidth(), p4.getHeight());
        g.setColor(Color.green);
        g2.setColor(Color.green);
        g3.setColor(Color.green);
        g4.setColor(Color.green);
        g.setFont(new Font("Courier", Font.PLAIN,40));
        g2.setFont(new Font("Courier", Font.PLAIN,40));
        g3.setFont(new Font("Courier", Font.PLAIN,40));
        g4.setFont(new Font("Courier", Font.PLAIN,40));
        g.drawString("Mara", 175, 125);
        g2.drawString("De", 175, 125);
        g3.drawString("Diego", 175, 125);
        g4.drawString("Esteva", 175, 125);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void generaPanel4(){
        p4.removeAll(); 
        p4.setBackground(Color.white);
        String cad = "";
        DecimalFormat df = new DecimalFormat("###.###");
        cad = "<html><b>Estadisticos Descriptivos</b><br/>" + datos.recuperaEje(jCX4.getSelectedIndex()) + "<br/>Minimo: " + datos.valores[jCX4.getSelectedIndex()].minimo() + "<br/>";  //https://stackoverflow.com/questions/1090098/newline-in-jlabel
        cad = cad + "Maximo: " + datos.valores[jCX4.getSelectedIndex()].maximo() + "<br/>";
        cad = cad + "Rango: " + datos.valores[jCX4.getSelectedIndex()].rango() + "<br/>";
        cad = cad + "Cuartil 1: " + datos.valores[jCX4.getSelectedIndex()].cuartil_1()+ "<br/>";
        cad = cad + "Cuartil 2: " + datos.valores[jCX4.getSelectedIndex()].cuartil_2()+ "<br/>";
        cad = cad + "Cuartil 3: " + datos.valores[jCX4.getSelectedIndex()].cuartil_3()+ "<br/>";
        cad = cad + "Media: " + datos.valores[jCX4.getSelectedIndex()].media() + "<br/>";
        cad = cad + "Varianza: " + df.format(datos.valores[jCX4.getSelectedIndex()].varianza()) + "<br/>";
        cad = cad + "Coeficiente de Variacion: " + df.format(datos.valores[jCX4.getSelectedIndex()].coefVar()) + "<br/>";
        cad = cad + "Desviacion Estandar: " + df.format(datos.valores[jCX4.getSelectedIndex()].desvStd()) + "</html>";
        label.setText(cad);
        this.p4.add(label);
        this.p4.repaint(); 
    }
    
    private void generaPanel1(){
        Graphics g = p1.getGraphics();
        p1.setBackground(Color.white);
        g.setColor(Color.white);
        g.fillRect(0, 0, p1.getWidth(), p1.getHeight());
        g.setColor(Color.black);
        g.drawRect(25, 25, 300, 200);
        int xMax = 1, yMax = 1, y = 0, x = 0; //Inicializo valores
        
        g.setFont(new Font("Courier", Font.PLAIN,10));
        if(this.jRB1_1.isSelected()){ // Dispersion
            g.drawString(disp.titulo, 175, 12);
            g.drawString(disp.recuperaEje(this.disp.getIndexX()), 175, 245); 
            g.drawString(disp.recuperaEje(this.disp.getIndexY()), 12, 135);
            xMax = Math.round((disp.recuperaColumna(this.disp.getIndexX()).maximo() + 10)/10);// para que se reparta en diez espacios
            yMax = Math.round((disp.recuperaColumna(this.disp.getIndexY()).maximo() + 10)/10);// se suma 10 para darle espacio al margen
        } else {
            g.drawString(burb.titulo, 175, 12);
            g.drawString(burb.recuperaEje(this.burb.getIndexX()), 175, 245);
            g.drawString(burb.recuperaEje(this.burb.getIndexY()), 12, 135);
            xMax = Math.round((burb.recuperaColumna(this.burb.getIndexX()).maximo() + 10)/10);// para que se reparta en diez espacios
            yMax = Math.round((burb.recuperaColumna(this.burb.getIndexY()).maximo() + 10)/10);// se suma 10 para darle espacio al margen
        }
        g.setFont(new Font("Courier", Font.PLAIN,7));
        for (int i = 0; i <= 300; i = i + 30){  //Llena rayitas eje x
            g.drawLine(25+i, 225, 25+i, 230); //- a +
            g.drawString(x+"", 25+i, 240);
            x = x + xMax;
        }
        y = yMax * 10;
        for (int i = 0; i <= 200; i = i + 20){ //Llena rallitas eje y
            g.drawLine(20,25+i,25,25+i); //+ a -
            g.drawString(y+"", 5, 25+i);
            y = y - yMax;
        }
        int xvals [], yvals [],  zvals [] ;
        if(this.jRB1_1.isSelected()){ //Llena valores dispersion
            xvals =  disp.recuperaColumna(this.disp.getIndexX()).getDatos();
            yvals =  disp.recuperaColumna(this.disp.getIndexY()).getDatos();
        }else{                       ////Llena valores dispersion
            xvals =  burb.recuperaColumna(this.burb.getIndexX()).getDatos();
            yvals =  burb.recuperaColumna(this.burb.getIndexY()).getDatos();
        }
        zvals =  burb.recuperaColumna(this.burb.getIndexRadio()).getDatos();
           
        for(int i = 0; i < disp.recuperaColumna(this.disp.getIndexX()).getDatos().length; i++){
            if(this.jRB1_1.isSelected()){ 
                Double xPos = Double.parseDouble(xvals[i]+"")*300/(xMax*10)+25; //Regla de 3: multiplica por maximo y divide entre valor maximo columna
                Double yPos = 200 - (Double.parseDouble(yvals[i]+"")*200/(yMax*10)-25); //Para hacer valor aprox. a la escala 
                g.fillOval(xPos.intValue(),yPos.intValue(), 7, 7);
            }else{
                Double xPos = Double.parseDouble(xvals[i]+"")*300/(xMax*10)+25;
                Double yPos = 200 - (Double.parseDouble(yvals[i]+"")*200/(yMax*10)-25);
                switch (i%4) {
                    case 0:
                        g.setColor(Color.red);
                        break;
                    case 1:
                        g.setColor(Color.blue);
                        break;
                    case 2:
                        g.setColor(Color.green);
                        break;
                    case 3:
                        g.setColor(Color.orange);
                        break;
                    default:
                        g.setColor(Color.black);
                        break;
                }
                g.fillOval(xPos.intValue(),yPos.intValue(), zvals[i], zvals[i]);
            }
        }
        
    }
    
    private void generaPanel2(){
        Graphics g = p2.getGraphics();
        p2.setBackground(Color.white);
        g.setColor(Color.white);
        g.fillRect(0, 0, p2.getWidth(), p2.getHeight());
        g.setColor(Color.black);
        g.drawRect(25, 25, 300, 200);
        int xMax = 1, yMax = 1, y = 0, x = 0;
        
        g.setFont(new Font("Courier", Font.PLAIN,10));
        if(this.jRB2_1.isSelected()){ //Secuencia
            g.drawString(secu.titulo, 175, 12);
            g.drawString("Valor", 12, 135);
            xMax = secu.recuperaColumna(this.secu.getIndexX()).getNumDatos(); //rayitas eje x
            yMax = Math.round((secu.recuperaColumna(this.secu.getIndexX()).maximo() + 10)/10); //rayitas eje y
            g.setFont(new Font("Courier", Font.PLAIN,7));
            int cont = 1;
            for (int i = 0; i <= 300; i = i + (300/xMax)){ 
                g.drawLine(25+i, 225, 25+i, 230);
                g.drawString(cont+"", 25+i, 240);
                cont++;
            }
            y = yMax * 10;
            for (int i = 0; i <= 200; i = i + 20){
                g.drawLine(20,25+i,25,25+i);
                g.drawString(y+"", 5, 25+i);
                y = y - yMax;
            }
        } else { //barra
            g.drawString(barr.titulo, 175, 12);
            g.drawString(barr.recuperaEje(this.barr.getIndexX()), 255, 35);
            int [] arrTemp = this.barr.recuperaColumna(this.barr.getIndexX()).valorSinRepertir();
            int maxVal = 0;
            xMax = this.barr.recuperaColumna(this.barr.getIndexX()).valorSinRepertir().length; 
            for (int i = 0; i < arrTemp.length;i++){ //ir checando valor maximo
                if(maxVal < this.barr.recuperaColumna(this.barr.getIndexX()).repeticiones(arrTemp[i]))
                    maxVal = this.barr.recuperaColumna(this.barr.getIndexX()).repeticiones(arrTemp[i]);
            }
            
            yMax = maxVal;
            g.setFont(new Font("Courier", Font.PLAIN,7));
            int cont=-1;
            for (int i = 0; i <= 300; i = i + (300/xMax)){ //sacar frecuencia
                if(cont != -1){
                    g.drawLine(25+i, 225, 25+i, 230);
                    g.drawString(arrTemp[cont]+"", 10+i, 240);
                }
                cont++;
            }
            cont=yMax;
            for (int i = 0; i <= 200; i = i + (200/yMax)){ //sacar frecuencia
                g.drawLine(20,25+i,25,25+i);
                g.drawString(cont+"", 5, 25+i);
                cont--;
            }
        }
        int xAnt=0,yAnt=0;
           
        for(int i = 0; i < barr.recuperaColumna(this.barr.getIndexX()).getDatos().length; i++){
            g.setColor(Color.black);
            if(this.jRB2_1.isSelected()){
                
                Double xPos = Double.parseDouble(i+"")*300/(xMax)+25;
                Double yPos = 200 - (Double.parseDouble(barr.recuperaColumna(this.barr.getIndexX()).getDatos()[i]+"")*200/(yMax*10)-25);
                if(xAnt == 0 && yAnt == 0){ //Guarda posicion anterior para hacer lineas
                    g.fillOval(xPos.intValue(),yPos.intValue(), 7, 7);
                    xAnt = xPos.intValue();
                    yAnt = yPos.intValue();
                } else {
                    g.drawLine(xAnt, yAnt, xPos.intValue(), yPos.intValue());//para no conectar la primera linea
                    g.fillOval(xPos.intValue(),yPos.intValue(), 7, 7);
                    xAnt = xPos.intValue();
                    yAnt = yPos.intValue();
                }
            }else{ 
                int cont=-1; //para que se llena la primera barra
                xAnt=0;
                yAnt=0;
                int [] arrTemp = this.barr.recuperaColumna(this.barr.getIndexX()).valorSinRepertir();
                for (int j = 0; j <= 300; j = j + (300/xMax)){
                    switch (cont%4) { 
                        case 0:
                            g.setColor(Color.red);
                            break;
                        case 1:
                            g.setColor(Color.blue);
                            break;
                        case 2:
                            g.setColor(Color.green);
                            break;
                        case 3:
                            g.setColor(Color.orange);
                            break;
                        default:
                            g.setColor(Color.black);
                            break;
                    }
                    if(cont != -1){
                        Double yPos = 200 - (Double.parseDouble(barr.recuperaColumna(this.barr.getIndexX()).repeticiones(arrTemp[cont])+"")*200/(yMax)-25);
                        g.fill3DRect(xAnt, yPos.intValue(), 300/xMax, 225-yPos.intValue(),true);
                    }
                    xAnt = 25+j;
                    cont++;
                }
                break;
            }
            if(this.jRB2_1.isSelected()){ //dibuja lineas media y d.e.
                Double media = 200 - (barr.recuperaColumna(this.barr.getIndexX()).media()*200/(yMax*10)-25);
                Double deP = 200 - ((barr.recuperaColumna(this.barr.getIndexX()).media()+barr.recuperaColumna(this.barr.getIndexX()).desvStd())*200/(yMax*10)-25);
                Double deN = 200 - ((barr.recuperaColumna(this.barr.getIndexX()).media()-barr.recuperaColumna(this.barr.getIndexX()).desvStd())*200/(yMax*10)-25);
                g.setColor(Color.ORANGE);
                g.drawLine(25, media.intValue(), 300, media.intValue());
                g.setColor(Color.CYAN);
                g.drawLine(25, deP.intValue(), 300, deP.intValue());
                g.drawLine(25, deN.intValue(), 300, deN.intValue());
            }
        }
        
        
    }
    
	private void generaPanel3(){
        Graphics g = p3.getGraphics();
        p3.setBackground(Color.white);
        g.setColor(Color.white);
        g.fillRect(0, 0, p3.getWidth(), p3.getHeight());
        g.setColor(Color.black);
        g.drawRect(25, 25, 300, 200);
        int divs = 0;
        if(this.jCB1.isSelected())
            divs++;
        if(this.jCB2.isSelected())
            divs++;
        if(this.jCB3.isSelected())
            divs++;
        int xMax = 1, yMax = 1, y = 0, x = 0;
        g.drawString(boxp.titulo, 175, 12);
        xMax = divs;
        yMax = 8;
        g.setFont(new Font("Courier", Font.PLAIN,7));
        List<String> ejeUsados = new ArrayList<>();
        for (int i = (300/xMax); i <= 300; i = i + (300/xMax)){
            g.drawLine(25+i-((300/xMax)/2), 225, 25+i-((300/xMax)/2), 230);
            if(boxp.getGraf1()!= null && !ejeUsados.contains(boxp.recuperaEje(0))){
                g.drawString(boxp.recuperaEje(0), 25+i-((300/xMax)/2), 240);
                ejeUsados.add(boxp.recuperaEje(0));
                Double c1 = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf1()).cuartil_1()+"")*200/(yMax*10)-25);
                Double c2 = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf1()).cuartil_2()+"")*200/(yMax*10)-25);
                Double c3 = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf1()).cuartil_3()+"")*200/(yMax*10)-25);
                Double max = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf1()).maximo()+"")*200/(yMax*10)-25);
                Double min = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf1()).minimo()+"")*200/(yMax*10)-25);
                Double media = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf1()).media()+"")*200/(yMax*10)-25);
                g.setColor(Color.black);
                g.drawLine( 25+i-((300/xMax)/2), max.intValue(), 25+i-((300/xMax)/2), min.intValue());
                g.setColor(Color.green);
                g.fillRect(25+i-((300/xMax)/2) - 45, c3.intValue(), 90, 225-c1.intValue());
                g.setColor(Color.black);
                g.drawLine( 25+i-((300/xMax)/2) - 45, max.intValue(), 25+i-((300/xMax)/2) + 45, max.intValue());
                g.drawLine( 25+i-((300/xMax)/2) - 45, min.intValue(), 25+i-((300/xMax)/2) + 45, min.intValue());
                g.drawLine( 25+i-((300/xMax)/2) - 45, media.intValue(), 25+i-((300/xMax)/2) + 45, media.intValue());
                g.fillRect(25+i-((300/xMax)/2) - 5, c2.intValue()-5, 5, 5);
                g.drawLine( 25+i-((300/xMax)/2) - 45, media.intValue(), 25+i-((300/xMax)/2) + 45, media.intValue());
            } else if (boxp.getGraf2()!= null && !ejeUsados.contains(boxp.recuperaEje(1))){
                g.drawString(boxp.recuperaEje(1), 25+i-((300/xMax)/2), 240);
                ejeUsados.add(boxp.recuperaEje(1));
                Double c1 = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf2()).cuartil_1()+"")*200/(yMax*10)-25);
                Double c2 = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf2()).cuartil_2()+"")*200/(yMax*10)-25);
                Double c3 = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf2()).cuartil_3()+"")*200/(yMax*10)-25);
                Double max = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf2()).maximo()+"")*200/(yMax*10)-25);
                Double min = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf2()).minimo()+"")*200/(yMax*10)-25);
                Double media = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf2()).media()+"")*200/(yMax*10)-25);
                g.setColor(Color.black);
                g.drawLine( 25+i-((300/xMax)/2), max.intValue(), 25+i-((300/xMax)/2), min.intValue());
                g.setColor(Color.blue);
                g.fillRect(25+i-((300/xMax)/2) - 45, c3.intValue(), 90, 225-c1.intValue());
                g.setColor(Color.black);
                g.drawLine( 25+i-((300/xMax)/2) - 45, max.intValue(), 25+i-((300/xMax)/2) + 45, max.intValue());
                g.drawLine( 25+i-((300/xMax)/2) - 45, min.intValue(), 25+i-((300/xMax)/2) + 45, min.intValue());
                g.drawLine( 25+i-((300/xMax)/2) - 45, media.intValue(), 25+i-((300/xMax)/2) + 45, media.intValue());
                g.fillRect(25+i-((300/xMax)/2) - 5, c2.intValue()-5, 5, 5);
                g.drawLine( 25+i-((300/xMax)/2) - 45, media.intValue(), 25+i-((300/xMax)/2) + 45, media.intValue());
            } else if(boxp.getGraf3()!= null && !ejeUsados.contains(boxp.recuperaEje(2))){
                g.drawString(boxp.recuperaEje(2), 25+i-((300/xMax)/2), 240);
                ejeUsados.add(boxp.recuperaEje(2));
                Double c1 = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf3()).cuartil_1()+"")*200/(yMax*10)-25);
                Double c2 = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf3()).cuartil_2()+"")*200/(yMax*10)-25);
                Double c3 = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf3()).cuartil_3()+"")*200/(yMax*10)-25);
                Double max = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf3()).maximo()+"")*200/(yMax*10)-25);
                Double min = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf3()).minimo()+"")*200/(yMax*10)-25);
                Double media = 200 - (Double.parseDouble(boxp.recuperaColumna(this.boxp.getGraf3()).media()+"")*200/(yMax*10)-25);
                g.setColor(Color.black);
                g.drawLine( 25+i-((300/xMax)/2), max.intValue(), 25+i-((300/xMax)/2), min.intValue());
                g.setColor(Color.orange);
                g.fillRect(25+i-((300/xMax)/2) - 45, c3.intValue(), 90, 225-c1.intValue());
                g.setColor(Color.black);
                g.drawLine( 25+i-((300/xMax)/2) - 45, max.intValue(), 25+i-((300/xMax)/2) + 45, max.intValue());
                g.drawLine( 25+i-((300/xMax)/2) - 45, min.intValue(), 25+i-((300/xMax)/2) + 45, min.intValue());
                g.drawLine( 25+i-((300/xMax)/2) - 45, media.intValue(), 25+i-((300/xMax)/2) + 45, media.intValue());
                g.fillRect(25+i-((300/xMax)/2) - 5, c2.intValue()-5, 5, 5);
                g.drawLine( 25+i-((300/xMax)/2) - 45, media.intValue(), 25+i-((300/xMax)/2) + 45, media.intValue());
            }
                
        }
        y = yMax * 10;
        for (int i = 0; i <= 200; i = i + 20){
            g.drawLine(20,25+i,25,25+i);
            g.drawString(y+"", 5, 25+i);
            y = y - yMax;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MDDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MDDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MDDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MDDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MDDE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCB1;
    private javax.swing.JCheckBox jCB2;
    private javax.swing.JCheckBox jCB3;
    private javax.swing.JComboBox<String> jCX1;
    private javax.swing.JComboBox<String> jCX2;
    private javax.swing.JComboBox<String> jCX3;
    private javax.swing.JComboBox<String> jCX4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButton jRB1_1;
    private javax.swing.JRadioButton jRB1_2;
    private javax.swing.JRadioButton jRB2_1;
    private javax.swing.JRadioButton jRB2_2;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel p2;
    private javax.swing.JPanel p3;
    private javax.swing.JPanel p4;
    // End of variables declaration//GEN-END:variables
}
