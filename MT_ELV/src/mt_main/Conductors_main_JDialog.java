/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_main;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import static mt_main.startPanel.languageOption;

/**
 *
 * @author Jozef
 */
public class Conductors_main_JDialog extends javax.swing.JDialog {

    /**
     * Creates new form Help_Math_JDialog
     */
    public Conductors_main_JDialog(java.awt.Frame parent, boolean modal) {
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

                    //  int rowNumber2 = e.getLastIndex();
                    //  if(rowNumber > rowNumber2) {rowNumber=rowNumber2;}
                    Object[] Conductor = new Object[7];
                     Conductor = Databaza.get(rowNumber);

                    textAreaConductorInfo.setText(
                            language.language_label(languageOption, 33) + String.valueOf(Conductor[1]) + " mm" + "\r\n"
                            + language.language_label(languageOption, 34) + String.valueOf(Conductor[2]) + " mm2" + "\r\n"
                            + language.language_label(languageOption, 35) + String.valueOf(Conductor[3]) + " kg/m" + "\r\n"
                            + language.language_label(languageOption, 36) + String.valueOf(Conductor[4]) + " MPa" + "\r\n"
                            + language.language_label(languageOption, 37) + String.valueOf(Conductor[5]) + " 1/°C" + "\r\n"
                            + language.language_label(languageOption, 38) + String.valueOf(Conductor[6]) + " N" + "\r\n"
                            + language.language_label(languageOption, 39) + String.valueOf(Conductor[7]) + " " + "\r\n"
                    );

                }
            }

        });  //selection listener fot text area to show data

        
        //if pass ok bas been set remember to unloct features
        if (startPanel.getStatus_conductor_password().equals(true)) {
            Button_new_conductor.setEnabled(true);
            BUtton_change_conductor.setEnabled(true);
            BUtton_delete_conductor.setEnabled(true);

            textfiled_password_status_nonEditable.setText(language.language_label(languageOption, 41));
        } else {
            textfiled_password_status_nonEditable.setText(language.language_label(languageOption, 43));
        }

        // is  name of file  loading automaticly?
          String  memory_path_plus_filename  = startPanel.set_memory_path_conductor();
        
        if(memory_path_plus_filename.equals("none")){}
        else{
         memory_path_plus_filename_here  = startPanel.set_memory_path_conductor();
         memory_path_plus_filename_existence=true;
         BUtton_load_database.doClick();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Button_set_password = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        textfiled_password_typeing = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        Textfiled_nameofLoadedfile = new javax.swing.JTextField();
        BUtton_load_database = new javax.swing.JButton();
        textfiled_password_status_nonEditable = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        BUtton_delete_conductor = new javax.swing.JButton();
        Button_new_conductor = new javax.swing.JButton();
        BUtton_change_conductor = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaConductorInfo = new javax.swing.JTextArea();
        BUtton_load_database1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        Button_set_password.setText(language.language_label(languageOption,28));
        Button_set_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_set_passwordActionPerformed(evt);
            }
        });

        textfiled_password_typeing.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textfiled_password_typeingKeyPressed(evt);
            }
        });

        jLabel2.setText(language.language_label(languageOption,27));

        jLabel3.setText(language.language_label(languageOption,29));

        Textfiled_nameofLoadedfile.setEnabled(false);
        Textfiled_nameofLoadedfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Textfiled_nameofLoadedfileActionPerformed(evt);
            }
        });

        BUtton_load_database.setText(language.language_label(languageOption,30));
        BUtton_load_database.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUtton_load_databaseActionPerformed(evt);
            }
        });

        textfiled_password_status_nonEditable.setEnabled(false);
        textfiled_password_status_nonEditable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfiled_password_status_nonEditableActionPerformed(evt);
            }
        });

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
            jTable.getColumnModel().getColumn(0).setHeaderValue(language.language_label(languageOption,23));
        }

        jLabel1.setText(language.language_label(languageOption,22));

        BUtton_delete_conductor.setText(language.language_label(languageOption,26));
        BUtton_delete_conductor.setEnabled(false);
        BUtton_delete_conductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUtton_delete_conductorActionPerformed(evt);
            }
        });

        Button_new_conductor.setText(language.language_label(languageOption,24));
        Button_new_conductor.setEnabled(false);
        Button_new_conductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_new_conductorActionPerformed(evt);
            }
        });

        BUtton_change_conductor.setText(language.language_label(languageOption,25));
        BUtton_change_conductor.setEnabled(false);
        BUtton_change_conductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUtton_change_conductorActionPerformed(evt);
            }
        });

        textAreaConductorInfo.setColumns(20);
        textAreaConductorInfo.setLineWrap(true);
        textAreaConductorInfo.setRows(5);
        jScrollPane2.setViewportView(textAreaConductorInfo);

        BUtton_load_database1.setText(language.language_label(languageOption,50));
        BUtton_load_database1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUtton_load_database1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textfiled_password_status_nonEditable)
                            .addComponent(textfiled_password_typeing))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Button_set_password, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(BUtton_load_database1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(Textfiled_nameofLoadedfile, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(BUtton_load_database, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BUtton_change_conductor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Button_new_conductor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BUtton_delete_conductor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Button_new_conductor)
                        .addGap(18, 18, 18)
                        .addComponent(BUtton_change_conductor)
                        .addGap(18, 18, 18)
                        .addComponent(BUtton_delete_conductor)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textfiled_password_typeing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_set_password))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textfiled_password_status_nonEditable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Textfiled_nameofLoadedfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BUtton_load_database))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BUtton_load_database1)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_new_conductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_new_conductorActionPerformed

        tableListenerswitch = false;

        Conductors_main_new_conductor_JDialog Conductors_main_new_conducor_JDialog_window = new Conductors_main_new_conductor_JDialog(this, rootPaneCheckingEnabled);
        Conductors_main_new_conducor_JDialog_window.setVisible(true);

        if (existnewConductor == true) {

            Databaza.add(newConductor);
            arralist_sort(Databaza);

            int rowCount = jTable.getRowCount();
            //Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                modelTable.removeRow(i);
            }

            int numberofElements = Databaza.size();             //pregeneruj tabulku a nakresli ju
            for (int i = 0; i <= numberofElements - 1; i++) {
                Object[] Conductor = new Object[7];
                Conductor = Databaza.get(i);
                modelTable.addRow(new Object[]{(String) Conductor[0]});

            }
            existnewConductor = false;
        }
        SaveDatabase(Databaza);
        tableListenerswitch = true;
    }//GEN-LAST:event_Button_new_conductorActionPerformed

    private void BUtton_delete_conductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUtton_delete_conductorActionPerformed
        tableListenerswitch = false;

        Databaza.remove(jTable.getSelectedRow());       //    odstran z databazy

        int rowCount = jTable.getRowCount();            //odstran tabulku
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            modelTable.removeRow(i);
        }

        arralist_sort(Databaza);                        // sortuj databazu

        int numberofElements = Databaza.size();             //pregeneruj tabulku a nakresli ju
        for (int i = 0; i <= numberofElements - 1; i++) {
            Object[] Conductor = new Object[7];
            Conductor = Databaza.get(i);
            modelTable.addRow(new Object[]{(String) Conductor[0]});
        }
        SaveDatabase(Databaza);
        tableListenerswitch = true;
    }//GEN-LAST:event_BUtton_delete_conductorActionPerformed

    private void BUtton_load_databaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUtton_load_databaseActionPerformed

       if ( memory_path_plus_filename_existence == false){
        
        
        
        String userhome = System.getProperty("user.dir");          //userhome is home folder of program
        JFileChooser chooser = new JFileChooser(userhome + "\\resources");  //key files are stored in resources
        FileNameExtensionFilter txtfilter = new FileNameExtensionFilter(
                language.language_label(languageOption, 32), "txt");                                // whitch type of files are we looking for
        chooser.setDialogTitle(language.language_label(languageOption, 31));   // title for Jfile chooser window
        chooser.setFileFilter(txtfilter);                                   // Txt filter for choosing file

        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        this.filenamePath = f.getParent();                                      //get full path of selected file

        this.filename = f.getName();                                            // get full name of selected file

        Textfiled_nameofLoadedfile.setText(this.filename);                      //set nname of loaded file intext field

        filenamePath_plus_filename= this.filenamePath + "\\" + this.filename;        // load entire file into table and memory of  proram
       }else{
        filenamePath_plus_filename = memory_path_plus_filename_here;
        memory_path_plus_filename_existence = false;
       }
        File subor = new File(filenamePath_plus_filename);
        try {
            Scanner input = new Scanner(subor);                                 // reading the file

            String nameOfConductor = "empty";                                             //define readind variables
            Double Diameter = 0.;
            Double CrossSection = 0.;
            Double unitWeight = 0.;
            Double modulofFlexibility = 0.;
            Double koeficientOfTermalExpension = 0.;
            Double MathematicalCarringCapacity = 0.;
            Double WeightRationCoreAndConductor = 0.;
            String EmptyLine;

            EmptyLine = input.nextLine();
            EmptyLine = input.nextLine(); // read first line and unlock the input.hasnext
            Integer counter = 0;
            // ochrana pred dualnym nacitavanim
                        int rowCount = jTable.getRowCount();            //odstran tabulku
                         //Remove rows one by one from the end of the table
                         for (int i = rowCount - 1; i >= 0; i--) {
                             modelTable.removeRow(i);
                                   }
                             this.Databaza.removeAll(Databaza);
            
             while (input.hasNext()) {
                Object[] Conductor = new Object[7];                                //create object ONE conductor with above parameters

                nameOfConductor = input.nextLine();

                Diameter = Double.valueOf(input.next());
                EmptyLine = input.nextLine();
                CrossSection = Double.valueOf(input.next());
                EmptyLine = input.nextLine();
                unitWeight = Double.valueOf(input.next());
                EmptyLine = input.nextLine();
                modulofFlexibility = Double.valueOf(input.next());
                EmptyLine = input.nextLine();
                koeficientOfTermalExpension = Double.valueOf(input.next());
                EmptyLine = input.nextLine();
                MathematicalCarringCapacity = Double.valueOf(input.next());
                EmptyLine = input.nextLine();
                WeightRationCoreAndConductor = Double.valueOf(input.next());
                EmptyLine = input.nextLine();
                EmptyLine = input.nextLine();

                //vytvorim objekt Conductor a do neho vložim data    
                Conductor = new Object[]{(String) nameOfConductor, (double) Diameter, (double) CrossSection, (double) unitWeight, (double) modulofFlexibility, (double) koeficientOfTermalExpension, (double) MathematicalCarringCapacity, (double) WeightRationCoreAndConductor};
                //vložim od konečnej array           
                this.Databaza.add(Conductor);

                if (EmptyLine.equals("EMD_OF_FILE")) {
                    break;
                }

            }
            arralist_sort(Databaza);

            int numberofElements = Databaza.size();             //pregeneruj tabulku a nakresli ju
            for (int i = 0; i <= numberofElements - 1; i++) {
                Object[] Conductor = new Object[7];
                Conductor = Databaza.get(i);
                modelTable.addRow(new Object[]{(String) Conductor[0]});
            }

        } catch (FileNotFoundException ex) {

        }

    }//GEN-LAST:event_BUtton_load_databaseActionPerformed

    private ArrayList<Object[]> arralist_sort(ArrayList<Object[]> X) {

        int numberofElements = X.size();
        String S1;
        String S2;
        Object[] Conductor1 = new Object[7];
        Object[] Conductor2 = new Object[7];

        while (true) {
            int breakcounter = 0;
            for (int i = 0; i < numberofElements - 1; i++) {

                Conductor1 = Databaza.get(i);
                S1 = String.valueOf(Conductor1[0]);

                Conductor2 = Databaza.get(i + 1);
                S2 = String.valueOf(Conductor2[0]);

                int compareValue = S1.compareTo(S2);

                if (compareValue < 0 || compareValue == 0) {

                } else {
                    breakcounter = breakcounter + 1;
                    Databaza.set(i, Conductor2);
                    Databaza.set(i + 1, Conductor1);
                }

            }
            if (breakcounter == 0) {
                break;
            }
        }

        return Databaza;
    }

    private void SaveDatabase(ArrayList<Object[]> X) {

        try {
            File ulozenieSubor = new File(this.filenamePath + "\\" + this.filename);
            PrintWriter fw = new PrintWriter(ulozenieSubor);
            fw.println("....next conductor....");
            fw.println("Database file do not modify or I will hunt you and eat you alive. JB");

            int numberofElements = Databaza.size();             //pregeneruj tabulku a nakresli ju
            for (int i = 0; i <= numberofElements - 1; i++) {
                Object[] Conductor = new Object[7];
                Conductor = Databaza.get(i);

                fw.println(String.valueOf(Conductor[0]) + "");
                fw.println(String.valueOf(Conductor[1]) + " mm");
                fw.println(String.valueOf(Conductor[2]) + " mm2");
                fw.println(String.valueOf(Conductor[3]) + " kg/m");
                fw.println(String.valueOf(Conductor[4]) + " MPa");
                fw.println(String.valueOf(Conductor[5]) + " 1/°C");
                fw.println(String.valueOf(Conductor[6]) + " N");
                fw.println(String.valueOf(Conductor[7]));

                if (i == ((numberofElements - 1))) {
                    fw.println("END_OF_FILE");
                } else {
                    fw.println("....next conductor....");
                }

            }

            fw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conductors_main_JDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void Textfiled_nameofLoadedfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Textfiled_nameofLoadedfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Textfiled_nameofLoadedfileActionPerformed

    private void textfiled_password_status_nonEditableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfiled_password_status_nonEditableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfiled_password_status_nonEditableActionPerformed

    public static void newConductorsetter(Object[] X) {
        newConductor = X;

    }

    public static boolean newConductorstatus(boolean X) {
        existnewConductor = X;
        return existnewConductor;
    }


    private void Button_set_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_set_passwordActionPerformed
        String passwordTaken = textfiled_password_typeing.getText();

        if (passwordTaken.equals(language.language_label(languageOption, 40))) {
            Button_new_conductor.setEnabled(true);
            BUtton_change_conductor.setEnabled(true);
            BUtton_delete_conductor.setEnabled(true);

            textfiled_password_status_nonEditable.setText(language.language_label(languageOption, 41));
            startPanel.setStatus_conductor_password(true);
        } else {
            textfiled_password_status_nonEditable.setText(language.language_label(languageOption, 42)); 
            
        }

    }//GEN-LAST:event_Button_set_passwordActionPerformed

    private void BUtton_change_conductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUtton_change_conductorActionPerformed
        //jTable.getSelectionModel().removeListSelectionListener(jTable);
        tableListenerswitch = false;
        try {
            Conductors_main_change_conductor_JDialog.newConductorsetter(Databaza.get(jTable.getSelectedRow())); // musi byt redtym lebo inač ked sa spusti Jdialog toto okno sa blokne na dalšiu pracu kym neskoni J Dialog

            Conductors_main_change_conductor_JDialog Conductors_main_change_conducor_JDialog_window = new Conductors_main_change_conductor_JDialog(this, rootPaneCheckingEnabled);
            Conductors_main_change_conducor_JDialog_window.setVisible(true);

            if (existnewConductor == true) {

                Databaza.set(jTable.getSelectedRow(), newConductor);  // vymen prvok v databaze
                arralist_sort(Databaza);                                // sortuj databazu

                int rowCount = jTable.getRowCount();                         // premaž
                //Remove rows one by one from the end of the table
                for (int i = rowCount - 1; i >= 0; i--) {
                    modelTable.removeRow(i);
                }

                int numberofElements = Databaza.size();             //pregeneruj tabulku a nakresli ju
                for (int i = 0; i <= numberofElements - 1; i++) {
                    Object[] Conductor = new Object[7];
                    Conductor = Databaza.get(i);
                    modelTable.addRow(new Object[]{(String) Conductor[0]});

                }
                existnewConductor = false;
            }
        } catch (NullPointerException e) {
        }   // catch ak nie je selectute nič v tabulke

        SaveDatabase(Databaza);
        tableListenerswitch = true;
    }//GEN-LAST:event_BUtton_change_conductorActionPerformed

    private void textfiled_password_typeingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfiled_password_typeingKeyPressed
        int key = evt.getKeyCode();
        
        if(key == 10) 
        {Button_set_password.doClick();}
        else{
        
        }
    }//GEN-LAST:event_textfiled_password_typeingKeyPressed

    private void BUtton_load_database1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUtton_load_database1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BUtton_load_database1ActionPerformed

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
            java.util.logging.Logger.getLogger(Conductors_main_JDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Conductors_main_JDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Conductors_main_JDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Conductors_main_JDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Conductors_main_JDialog dialog = new Conductors_main_JDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BUtton_change_conductor;
    private javax.swing.JButton BUtton_delete_conductor;
    private javax.swing.JButton BUtton_load_database;
    private javax.swing.JButton BUtton_load_database1;
    private javax.swing.JButton Button_new_conductor;
    private javax.swing.JButton Button_set_password;
    private javax.swing.JTextField Textfiled_nameofLoadedfile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable;
    private javax.swing.JTextArea textAreaConductorInfo;
    private javax.swing.JTextField textfiled_password_status_nonEditable;
    private javax.swing.JTextField textfiled_password_typeing;
    // End of variables declaration//GEN-END:variables
    private String filename = "new_file";
    private String filenamePath;
    private String filenamePath_plus_filename;
    private String memory_path_plus_filename_here;
    public static boolean memory_path_plus_filename_existence = false;
    
    private final ArrayList<Object[]> Databaza = new ArrayList<>();
    private final ArrayList<Object[]> Databaza_help_sort = new ArrayList<>();
    private boolean tableListenerswitch = true;
    public static Object[] newConductor = new Object[7];
    public static boolean existnewConductor = false;
    DefaultTableModel modelTable;
}
