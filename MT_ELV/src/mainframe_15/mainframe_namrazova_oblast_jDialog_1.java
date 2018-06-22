/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mainframe_15;

import mainframe_1.*;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import mt_main.language;
import static mt_main.startPanel.languageOption;

/**
 *
 * @author Jozef
 */
public class mainframe_namrazova_oblast_jDialog_1 extends javax.swing.JDialog {

    /**
     * Creates new form Help_Math_JDialog
     */
    public mainframe_namrazova_oblast_jDialog_1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null); //center of window position
        setIcon();
           
        this.modelTable = (DefaultTableModel) jTable.getModel();    //inicialize table in default model table model (copy from EMFTsim )

        jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tableListenerswitch == true) {
                    int rowNumber =  jTable.getSelectedRow(); //- (e.getFirstIndex()-e.getLastIndex());

                    if ( rowNumber == 8){
                        
                    run_vlastne_hodnoty();
                        
                    
                    }
                    
                    //  int rowNumber2 = e.getLastIndex();
                    //  if(rowNumber > rowNumber2) {rowNumber=rowNumber2;}
                    Object[] oblast = new Object[3];
                     oblast = Databaza_namrazova_oblast.get(rowNumber);

                    textAreaConductorInfo.setText(
                            language.language_label(languageOption, 67) + "  " + String.valueOf(jTable.getValueAt(rowNumber, 0)) + "  - STN 50341-2-23 -  " + "\r\n"
                            +"\r\n"
                            + language.language_label(languageOption, 70) + " d< 30 mm , Ir = " + String.valueOf(oblast[0]) + " + " + String.valueOf(oblast[1]) + "×d" +  "\r\n"
                            + language.language_label(languageOption, 70) + " d> 30 mm , Ir = " + String.valueOf(oblast[2]) + " + " + String.valueOf(oblast[3]) + "×d" +  "\r\n"
                    );

                }
            }

        });  //selection listener fot text area to show data

         modelTable.addRow(new Object[]{(String) "I-0"});
         modelTable.addRow(new Object[]{(String) "I-1"});
         modelTable.addRow(new Object[]{(String) "I-2"});
         modelTable.addRow(new Object[]{(String) "I-3"});
         modelTable.addRow(new Object[]{(String) "I-5"});
         modelTable.addRow(new Object[]{(String) "I-8"});
         modelTable.addRow(new Object[]{(String) "I-12"});
         modelTable.addRow(new Object[]{(String) "I-18"});
         modelTable.addRow(new Object[]{(String) "I-K " + language.language_label(languageOption,71)});
         
               Object[] oblast_hodnoty = new Object[3];this.Databaza_namrazova_oblast.clear();
     /*I-0*/   oblast_hodnoty = new Object[]{(double) 1.064, (double) 0.1280,(double) 3.963, (double) 0.0314};this.Databaza_namrazova_oblast.add(oblast_hodnoty);
     /*I-1*/   oblast_hodnoty = new Object[]{(double) 3.175, (double) 0.2212,(double) 8.515, (double) 0.0432};this.Databaza_namrazova_oblast.add(oblast_hodnoty);
     /*I-2*/   oblast_hodnoty = new Object[]{(double) 8.661, (double) 0.3653,(double) 17.53, (double) 0.070};this.Databaza_namrazova_oblast.add(oblast_hodnoty);
     /*I-3*/   oblast_hodnoty = new Object[]{(double) 15.00, (double) 0.481,(double) 25.46, (double) 0.132};this.Databaza_namrazova_oblast.add(oblast_hodnoty);
     /*I-5*/   oblast_hodnoty = new Object[]{(double) 29.00, (double) 0.668,(double) 43.84, (double) 0.174};this.Databaza_namrazova_oblast.add(oblast_hodnoty);
     /*I-8*/   oblast_hodnoty = new Object[]{(double) 51.70, (double) 0.893,(double) 73.89, (double) 0.153};this.Databaza_namrazova_oblast.add(oblast_hodnoty);
     /*I-12*/  oblast_hodnoty = new Object[]{(double) 83.66, (double) 1.135,(double) 107.8, (double) 0.330};this.Databaza_namrazova_oblast.add(oblast_hodnoty);
     /*I-18*/  oblast_hodnoty = new Object[]{(double) 133.53, (double) 1.435,(double) 176.58, (double) 0.0};this.Databaza_namrazova_oblast.add(oblast_hodnoty);
     /*I-K*/   oblast_hodnoty = new Object[]{(double) 0.0, (double) 0.0,(double) 0.0, (double) 0.0}; this.Databaza_namrazova_oblast.add(oblast_hodnoty);
        //vložim od konečnej array           
        

         
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaConductorInfo = new javax.swing.JTextArea();
        button_set = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable);
        if (jTable.getColumnModel().getColumnCount() > 0) {
            jTable.getColumnModel().getColumn(0).setResizable(false);
            jTable.getColumnModel().getColumn(0).setHeaderValue(language.language_label(languageOption,69));
        }

        jLabel1.setText(language.language_label(languageOption,68));

        textAreaConductorInfo.setColumns(20);
        textAreaConductorInfo.setLineWrap(true);
        textAreaConductorInfo.setRows(5);
        jScrollPane2.setViewportView(textAreaConductorInfo);

        button_set.setText(language.language_label(languageOption, 75));
        button_set.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_setActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button_set, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_set))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_setActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_setActionPerformed
       
        int rowNumber =  jTable.getSelectedRow();
       
        if( rowNumber >= 0 && rowNumber <= 8){
           Object[] hodnoty = new Object[3];
           hodnoty = Databaza_namrazova_oblast.get(rowNumber);
        mainframe_15.hodnoty_namrazove_oblasti = hodnoty;
        
        if (rowNumber == 8){
        mainframe_15.namrazove_oblasti_názov_oblasti = "I-K";    
        }else{
        mainframe_15.namrazove_oblasti_názov_oblasti = String.valueOf(jTable.getValueAt(rowNumber, 0));
        }
        mainframe_15.is_namrazove_oblasti_setted=true;
        disinit();
       }else{
   
        
       }
        
        
    }//GEN-LAST:event_button_setActionPerformed

   
    public static void getvlastnehodnoty(Object[] X) {
        vlastnehodnoty = X;
        Databaza_namrazova_oblast.set(8, X);
        

    }
    public void disinit(){    
        setVisible(false);
       setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    public  void run_vlastne_hodnoty(){
         mainframe_namrazova_oblast_vlastna_hodnota_jDialog_1 mainframe_namrazova_oblast_vlastna_hodnota_jDialog_window = new  mainframe_namrazova_oblast_vlastna_hodnota_jDialog_1(this, rootPaneCheckingEnabled);
                    mainframe_namrazova_oblast_vlastna_hodnota_jDialog_window.setVisible(true);

    }




    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
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
            java.util.logging.Logger.getLogger(mainframe_namrazova_oblast_jDialog_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainframe_namrazova_oblast_jDialog_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainframe_namrazova_oblast_jDialog_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainframe_namrazova_oblast_jDialog_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainframe_namrazova_oblast_jDialog_1 dialog = new mainframe_namrazova_oblast_jDialog_1(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/mt_graphic/" + "icon.png")));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_set;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable;
    private javax.swing.JTextArea textAreaConductorInfo;
    // End of variables declaration//GEN-END:variables
  
    private static final ArrayList<Object[]> Databaza_namrazova_oblast = new ArrayList<>();
    private boolean tableListenerswitch = true;
    public static Object[] vlastnehodnoty = new Object[1];

    DefaultTableModel modelTable;
}
