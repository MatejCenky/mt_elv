/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_main;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
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
 * @author Mattto
 */
public class mainframe extends javax.swing.JFrame {

    /**
     * Creates new form mainframe
     */
    public mainframe() {
        
        
        initComponents();
        setLocationRelativeTo(null);
        seticon();
        this.modelTable = (DefaultTableModel) Table_kotevne_useky.getModel();
        this.modeltable_rozpatia = (DefaultTableModel) Table_rozpatia.getModel();
        this.modeltable_rozpatia_nadm_vysky = (DefaultTableModel) Table_rozpatia_nadm_vysky.getModel();
        
        Table_rozpatia.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        nacitatDatabazuLan(); 
        mainframeLodaed=true;// fisrt load oc conductr databaze
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // if ju exit window app will not close
        
        //vloz prvu hodnotu do RTS nulty index v tabulke
        Object[] Conductor = new Object[7];
            Conductor = Databaza.get(0);
            Variable_RTS = Double.parseDouble(String.valueOf(Conductor[6]))/Double.parseDouble(String.valueOf(Conductor[2]));
           
            DecimalFormat df = new DecimalFormat("###.###");  // definovany počet desatinnych miest
            TextField_RTS.setText(df.format(Variable_RTS));                    
            Variable_Ir50=123456789.987654321;
        
        // vloz defaultne hodnoty do zakladny-mech napatie lana a maximalne zataženie podiel z RTS    
           Variable_zakladne_mech_napatie_lana_pre_minus5=Double.parseDouble(TextField_zakladne_mech_lana_minus5.getText());
           Variable_maximalne_zataz_lana_podiel_z_RTS=Variable_RTS*(Double.parseDouble(TextField_zakladne_mech_lana_minus5.getText())/100);
        
        // setiing importt Variables manually in code
        Variable_Kh =  1;
        Variable_Klc = 1;
        // inicializacia_ vyber druhu namrazy
        
        jComboBox_druh_namrazy.removeAllItems();  // ymaz povodne itemy
        jComboBox_druh_namrazy.addItem(language.language_label(languageOption, 77));
        jComboBox_druh_namrazy.addItem(language.language_label(languageOption, 78));
        jComboBox_druh_namrazy.addItem(language.language_label(languageOption, 79));
        jComboBox_druh_namrazy.addItem(language.language_label(languageOption, 80));
        jComboBox_druh_namrazy.addItem(language.language_label(languageOption, 81));
        jComboBox_druh_namrazy.addItem(language.language_label(languageOption, 82));
          
          
        // nulovanie   
        hodnoty_namrazove_oblasti = new Object[]{(double) 0.0,(double) 0.0, (double) 0.0,(double) 0.0};  
        
        //testing Jtable
        
        modeltable_rozpatia.addRow(new Object[]{(String) "-----------------------------------------"});    // nulty riedok
        Table_rozpatia.setRowHeight(0,8);
        
        
        modeltable_rozpatia.addRow(new Object[0]);
        modeltable_rozpatia_nadm_vysky.addRow(new Object[1]);
        modeltable_rozpatia_nadm_vysky.addRow(new Object[1]);
        Table_rozpatia.setRowHeight(1,16);
        
        // inicializacia_ ARRAZLISTOV?NULOVANIE
        Variable_Ai_dlzka_rozpatia.removeAll(Variable_Ai_dlzka_rozpatia);
        Variable_hi2_nadmorska_vyska_stoziarov.removeAll(Variable_hi2_nadmorska_vyska_stoziarov);
        Variable_hi_vyska_stoziarov.removeAll(Variable_hi_vyska_stoziarov);
        
        Variable_Ai_dlzka_rozpatia.add(0.0);
        Variable_hi_vyska_stoziarov.add(0.0);
        Variable_hi2_nadmorska_vyska_stoziarov.add(0.0);
        Variable_hi_vyska_stoziarov.add(0.0);
        Variable_hi2_nadmorska_vyska_stoziarov.add(0.0);
        
//        for(int i = 0; i<23 ; i++){
//        modeltable_rozpatia.addRow(new Object[0]);
//        modeltable_rozpatia_nadm_vysky.addRow(new Object[1]);
//        Table_rozpatia.setRowHeight(i+2,16);
//        }
       
         Table_rozpatia.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                    int rowNumber =  Table_rozpatia.getSelectedRow(); //- (e.getFirstIndex()-e.getLastIndex());

                    if(rowNumber != 0){
                      
                      Table_rozpatia_nadm_vysky.removeRowSelectionInterval(0, Table_rozpatia_nadm_vysky.getRowCount()-1);
                      Table_rozpatia_nadm_vysky.addColumnSelectionInterval(0, 1);
                      Table_rozpatia_nadm_vysky.addRowSelectionInterval(rowNumber-1, rowNumber);
                    }
                    

                    
                    

                
            }

        });  //selection listener fot text area to show data

         Table_rozpatia.getModel().addTableModelListener(new TableModelListener()  {

            @Override
            public void tableChanged(TableModelEvent e) {
                Table_rozpatia.getModel().removeTableModelListener(this);
                if(tablemodellistener_rozpatia == true){
                tablemodellistener_rozpatia=false;
                tablemodellistener_nad_vysky=false;
                
                int row = e.getFirstRow();
                int column = e.getColumn();
               
                if(row != 0){   // pre nulu nie lebo t je nulty riadok a tam je medzera
                    //prepisuje row -1 lebo tam sa nachadza hodnota  kedže nulty riadok je prazdny vždy
                Variable_Ai_dlzka_rozpatia.set(row-1, doubleChecker_tableinput(String.valueOf(Table_rozpatia.getValueAt(row, column))) );
                
                if(row == Table_rozpatia.getRowCount()-1){ // ak klinuty posledny riadok tak
                Variable_Ai_dlzka_rozpatia.add(0.0);
                Variable_hi_vyska_stoziarov.add(0.0);
                Variable_hi2_nadmorska_vyska_stoziarov.add(0.0);//pridar row vsšade do každe arraylist               
                modeltable_rozpatia_nadm_vysky.addRow(new Object[1]);
                modeltable_rozpatia.addRow(new Object[0]);
                }
                
                }
                }
                tablemodellistener_rozpatia=true;
                tablemodellistener_nad_vysky=true;
                Table_rozpatia.getModel().addTableModelListener(this);
                
                
                System.out.println(Variable_Ai_dlzka_rozpatia);
                System.out.println(Variable_hi_vyska_stoziarov);
                System.out.println(Variable_hi2_nadmorska_vyska_stoziarov);
                
                int odcitacac_rozpati=0;
                for(int i = 0; i< Variable_Ai_dlzka_rozpatia.size();i++){           //odstranuje chybne zapisi alebo prazdne hodnoty pre rozpatia
                    
                    if(Variable_Ai_dlzka_rozpatia.get(i) == 0.0 || Variable_Ai_dlzka_rozpatia.get(i) == 123456789.987654321 ){
                    odcitacac_rozpati=odcitacac_rozpati+1;
                }
                }
                
                Variable_n_pocet_rozpati = (Variable_Ai_dlzka_rozpatia.size()-odcitacac_rozpati);
                
                double Sumar_scitavac=0;
                for(int i = 0; i< Variable_n_pocet_rozpati+1;i++){           //pocita len tam kde je zadana dlka zorpatia ine stožiare bdue ignotrovat plus jedna preto lebo pocet stožiarov je vždy rozpatia plus 1
                    
                    Sumar_scitavac=Sumar_scitavac + Variable_hi_vyska_stoziarov.get(i);
                    
                }

               Variable_Hc_mean_medzikrok= Sumar_scitavac/Variable_n_pocet_rozpati;    // vypocitaj Hcmena
               TextField_hcmean_vpocitana.setText(String.valueOf(Variable_Hc_mean_medzikrok)); // vloz do text field pri radio buttne
               // System.out.println(Variable_n_pocet_rozpati);
            }   
            

            
        }
        );
          Table_rozpatia_nadm_vysky.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Table_rozpatia_nadm_vysky.getModel().removeTableModelListener(this);
                 if(tablemodellistener_nad_vysky == true){
                tablemodellistener_rozpatia=false;
                tablemodellistener_nad_vysky=false;
                
                int row = e.getFirstRow();
                int column = e.getColumn();
                  
                    //prepisuje row -1 lebo tam sa nachadza hodnota  kedže nulty riadok je prazdny vždy
                Variable_hi_vyska_stoziarov.set(row, doubleChecker_tableinput(String.valueOf(Table_rozpatia_nadm_vysky.getValueAt(row, 1))) );
                Variable_hi2_nadmorska_vyska_stoziarov.set(row, doubleChecker_tableinput(String.valueOf(Table_rozpatia_nadm_vysky.getValueAt(row, 0))) );
                
                if(row == Table_rozpatia_nadm_vysky.getRowCount()-1){ // ak klinuty posledny riadok tak
                Variable_Ai_dlzka_rozpatia.add(0.0);
                Variable_hi_vyska_stoziarov.add(0.0);
                Variable_hi2_nadmorska_vyska_stoziarov.add(0.0);//pridar row vsšade do každe arraylist
                modeltable_rozpatia.addRow(new Object[0]);
                modeltable_rozpatia_nadm_vysky.addRow(new Object[1]);
                }
                
                
                
            }
                 tablemodellistener_rozpatia=true;
                tablemodellistener_nad_vysky=true;
                Table_rozpatia_nadm_vysky.getModel().addTableModelListener(this);
                
                 double Sumar_scitavac=0;
                for(int i = 0; i< Variable_n_pocet_rozpati+1;i++){           //pocita len tam kde je zadana dlka zorpatia ine stožiare bdue ignotrovat plus jedna preto lebo pocet stožiarov je vždy rozpatia plus 1
                    
                    Sumar_scitavac=Sumar_scitavac + Variable_hi_vyska_stoziarov.get(i);
                    
                }

               Variable_Hc_mean_medzikrok= Sumar_scitavac/Variable_n_pocet_rozpati;    // vypocitaj Hcmena
               TextField_hcmean_vpocitana.setText(String.valueOf(Variable_Hc_mean_medzikrok)); // vloz do text field pri radio buttne
               
            }
            
            
        }
        );
         
         
        
       // modeltable2.isCellSpanOn();
       /// modeltable2.getCellSpanAt(0, 1);
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_kotevne_useky = new javax.swing.JTable();
        Label_kotevne_useky = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        Button_Icon_save = new javax.swing.JButton();
        Button_Icon_save_as = new javax.swing.JButton();
        Button_Icon_save_results = new javax.swing.JButton();
        Button_Icon_delete_row_table_kotevny_usek = new javax.swing.JButton();
        Button_Icon_arr_row_table_kotevny_usek = new javax.swing.JButton();
        Button_Icon_calculate = new javax.swing.JButton();
        Button_Icon_export_PDF = new javax.swing.JButton();
        Button_Icon_select_all_kotevny_usek = new javax.swing.JButton();
        Button_Icon_deselect_all_kotevny_usek = new javax.swing.JButton();
        Label_kotevne_useky1 = new javax.swing.JLabel();
        Label_RTS = new javax.swing.JLabel();
        TextField_RTS = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        Button_namrazova_oblast = new javax.swing.JButton();
        Label_RTS_velicina = new javax.swing.JLabel();
        Label_vybrana_namrazova_oblast = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        Label_typ_namrazy = new javax.swing.JLabel();
        Label__typ_namrazy_Ccl = new javax.swing.JLabel();
        TextField_Ccl = new javax.swing.JTextField();
        Label_hustota_namrazy = new javax.swing.JLabel();
        TextField_hustota_namrazy = new javax.swing.JTextField();
        Label_RTS_velicina1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        Label_zakladne_mech_napatie_minis5 = new javax.swing.JLabel();
        Label_max_zataz_lana = new javax.swing.JLabel();
        TextField_zakladne_mech_lana_minus5 = new javax.swing.JTextField();
        TextField_max_mech_podiel_z_RTS = new javax.swing.JTextField();
        Label_RTS_velicina2 = new javax.swing.JLabel();
        Label_RTS_velicina3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_rozpatia = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_rozpatia_nadm_vysky = new javax.swing.JTable();
        Label_stredna_vyska_vodicov_nad_terenom = new javax.swing.JLabel();
        jRadioButton_with_label_vypoctana = new javax.swing.JRadioButton();
        jRadioButton_with_label_vlastna = new javax.swing.JRadioButton();
        TextField_hcmean_vpocitana = new javax.swing.JTextField();
        TextField_hcmean_vlastna = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        Table_kotevne_useky.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table_kotevne_useky.setRowSelectionAllowed(false);
        Table_kotevne_useky.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(Table_kotevne_useky);
        if (Table_kotevne_useky.getColumnModel().getColumnCount() > 0) {
            Table_kotevne_useky.getColumnModel().getColumn(0).setResizable(false);
            Table_kotevne_useky.getColumnModel().getColumn(0).setPreferredWidth(5);
            Table_kotevne_useky.getColumnModel().getColumn(1).setResizable(false);
            Table_kotevne_useky.getColumnModel().getColumn(1).setPreferredWidth(200);
            Table_kotevne_useky.getColumnModel().getColumn(1).setHeaderValue(language.language_label(languageOption,51));
        }

        Label_kotevne_useky.setText(language.language_label(languageOption, 51));

        jToolBar1.setRollover(true);

        Button_Icon_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/Save-24.png"))); // NOI18N
        Button_Icon_save.setFocusable(false);
        Button_Icon_save.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Icon_save.setPreferredSize(new java.awt.Dimension(48, 48));
        Button_Icon_save.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_Icon_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Icon_saveActionPerformed(evt);
            }
        });
        Button_Icon_save.setToolTipText(language.language_label(languageOption, 53));

        Button_Icon_save_as.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/Save-as-24.png"))); // NOI18N
        Button_Icon_save_as.setFocusable(false);
        Button_Icon_save_as.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Icon_save_as.setPreferredSize(new java.awt.Dimension(48, 48));
        Button_Icon_save_as.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_Icon_save_as.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Icon_save_asActionPerformed(evt);
            }
        });
        Button_Icon_save_as.setToolTipText(language.language_label(languageOption, 54));

        Button_Icon_save_results.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/Save-results-24.png"))); // NOI18N
        Button_Icon_save_results.setFocusable(false);
        Button_Icon_save_results.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Icon_save_results.setPreferredSize(new java.awt.Dimension(48, 48));
        Button_Icon_save_results.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_Icon_save_results.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Icon_save_resultsActionPerformed(evt);
            }
        });
        Button_Icon_save_results.setToolTipText(language.language_label(languageOption, 55));

        Button_Icon_delete_row_table_kotevny_usek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/Delete Row-24.png"))); // NOI18N
        Button_Icon_delete_row_table_kotevny_usek.setFocusable(false);
        Button_Icon_delete_row_table_kotevny_usek.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Icon_delete_row_table_kotevny_usek.setPreferredSize(new java.awt.Dimension(48, 48));
        Button_Icon_delete_row_table_kotevny_usek.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_Icon_delete_row_table_kotevny_usek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Icon_delete_row_table_kotevny_usekActionPerformed(evt);
            }
        });
        Button_Icon_delete_row_table_kotevny_usek.setToolTipText(language.language_label(languageOption, 57));

        Button_Icon_arr_row_table_kotevny_usek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/Add Row-24.png"))); // NOI18N
        Button_Icon_arr_row_table_kotevny_usek.setFocusable(false);
        Button_Icon_arr_row_table_kotevny_usek.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Icon_arr_row_table_kotevny_usek.setPreferredSize(new java.awt.Dimension(48, 48));
        Button_Icon_arr_row_table_kotevny_usek.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_Icon_arr_row_table_kotevny_usek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Icon_arr_row_table_kotevny_usekActionPerformed(evt);
            }
        });
        Button_Icon_arr_row_table_kotevny_usek.setToolTipText(language.language_label(languageOption, 56));

        Button_Icon_calculate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/Calculator-24.png"))); // NOI18N
        Button_Icon_calculate.setFocusable(false);
        Button_Icon_calculate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Icon_calculate.setPreferredSize(new java.awt.Dimension(48, 48));
        Button_Icon_calculate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_Icon_calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Icon_calculateActionPerformed(evt);
            }
        });
        Button_Icon_calculate.setToolTipText(language.language_label(languageOption, 58));

        Button_Icon_export_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/PDF-24.png"))); // NOI18N
        Button_Icon_export_PDF.setFocusable(false);
        Button_Icon_export_PDF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Icon_export_PDF.setPreferredSize(new java.awt.Dimension(48, 48));
        Button_Icon_export_PDF.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_Icon_export_PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Icon_export_PDFActionPerformed(evt);
            }
        });
        Button_Icon_export_PDF.setToolTipText(language.language_label(languageOption, 59));

        Button_Icon_select_all_kotevny_usek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/Checked Checkbox-24.png"))); // NOI18N
        Button_Icon_select_all_kotevny_usek.setFocusable(false);
        Button_Icon_select_all_kotevny_usek.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Icon_select_all_kotevny_usek.setPreferredSize(new java.awt.Dimension(48, 48));
        Button_Icon_select_all_kotevny_usek.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_Icon_select_all_kotevny_usek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Icon_select_all_kotevny_usekActionPerformed(evt);
            }
        });
        Button_Icon_select_all_kotevny_usek.setToolTipText(language.language_label(languageOption, 63));

        Button_Icon_deselect_all_kotevny_usek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/Close Window-24.png"))); // NOI18N
        Button_Icon_deselect_all_kotevny_usek.setFocusable(false);
        Button_Icon_deselect_all_kotevny_usek.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Icon_deselect_all_kotevny_usek.setPreferredSize(new java.awt.Dimension(48, 48));
        Button_Icon_deselect_all_kotevny_usek.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_Icon_deselect_all_kotevny_usek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Icon_deselect_all_kotevny_usekActionPerformed(evt);
            }
        });
        Button_Icon_deselect_all_kotevny_usek.setToolTipText(language.language_label(languageOption, 64));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Button_Icon_save, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Icon_save_as, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Icon_save_results, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(Button_Icon_arr_row_table_kotevny_usek, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Icon_delete_row_table_kotevny_usek, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Icon_select_all_kotevny_usek, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Icon_deselect_all_kotevny_usek, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(Button_Icon_calculate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Icon_export_PDF, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1018, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Button_Icon_save, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_Icon_save_as, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_Icon_save_results, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_Icon_delete_row_table_kotevny_usek, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_Icon_arr_row_table_kotevny_usek, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_Icon_calculate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_Icon_export_PDF, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Button_Icon_select_all_kotevny_usek, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_Icon_deselect_all_kotevny_usek, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar1.add(jPanel1);

        Label_kotevne_useky1.setText(language.language_label(languageOption, 65));

        jComboBox_conductor_chooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_conductor_chooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_conductor_chooserActionPerformed(evt);
            }
        });

        Label_RTS.setText(language.language_label(languageOption, 66));
        Label_RTS.setToolTipText(language.language_label(languageOption, 90));

        TextField_RTS.setEnabled(false);
        TextField_RTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_RTSActionPerformed(evt);
            }
        });

        Button_namrazova_oblast.setText(language.language_label(languageOption, 67));
        Button_namrazova_oblast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_namrazova_oblastActionPerformed(evt);
            }
        });

        Label_RTS_velicina.setText("MPa");

        Label_vybrana_namrazova_oblast.setText(namrazove_oblasti_názov_oblasti);

        Label_typ_namrazy.setText(language.language_label(languageOption, 76));

        jComboBox_druh_namrazy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_druh_namrazy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_druh_namrazyActionPerformed(evt);
            }
        });

        Label__typ_namrazy_Ccl.setText(language.language_label(languageOption, 83));
        Label__typ_namrazy_Ccl.setToolTipText(language.language_label(languageOption, 88));

        TextField_Ccl.setEnabled(false);
        TextField_Ccl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_CclActionPerformed(evt);
            }
        });
        TextField_Ccl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_CclKeyReleased(evt);
            }
        });

        Label_hustota_namrazy.setText(language.language_label(languageOption, 84));
        Label_hustota_namrazy.setToolTipText(language.language_label(languageOption, 89));

        TextField_hustota_namrazy.setEnabled(false);
        TextField_hustota_namrazy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_hustota_namrazyActionPerformed(evt);
            }
        });
        TextField_hustota_namrazy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_hustota_namrazyKeyReleased(evt);
            }
        });

        Label_RTS_velicina1.setText("kg/m3");

        Label_zakladne_mech_napatie_minis5.setText(language.language_label(languageOption, 86));

        Label_max_zataz_lana.setText(language.language_label(languageOption, 87));

        TextField_zakladne_mech_lana_minus5.setText("50.0");
        TextField_zakladne_mech_lana_minus5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_zakladne_mech_lana_minus5ActionPerformed(evt);
            }
        });
        TextField_zakladne_mech_lana_minus5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_zakladne_mech_lana_minus5KeyReleased(evt);
            }
        });

        TextField_max_mech_podiel_z_RTS.setText("50.0");
        TextField_max_mech_podiel_z_RTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_max_mech_podiel_z_RTSActionPerformed(evt);
            }
        });
        TextField_max_mech_podiel_z_RTS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_max_mech_podiel_z_RTSKeyReleased(evt);
            }
        });

        Label_RTS_velicina2.setText("MPa");

        Label_RTS_velicina3.setText("% RTS");

        jPanel2.setAlignmentX(0.0F);
        jPanel2.setAlignmentY(0.0F);
        jPanel2.setMinimumSize(new java.awt.Dimension(0, 0));

        Table_rozpatia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table_rozpatia.setRowMargin(2);
        Table_rozpatia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Table_rozpatiaKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(Table_rozpatia);
        Table_rozpatia.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (Table_rozpatia.getColumnModel().getColumnCount() > 0) {
            Table_rozpatia.getColumnModel().getColumn(0).setResizable(false);
            Table_rozpatia.getColumnModel().getColumn(0).setHeaderValue(language.language_label(languageOption, 91)
            );
        }

        jScrollPane2.setPreferredSize(new java.awt.Dimension(4593, 403));

        Table_rozpatia_nadm_vysky.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table_rozpatia_nadm_vysky.setRowMargin(2);
        Table_rozpatia_nadm_vysky.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Table_rozpatia_nadm_vyskyKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(Table_rozpatia_nadm_vysky);
        if (Table_rozpatia_nadm_vysky.getColumnModel().getColumnCount() > 0) {
            Table_rozpatia_nadm_vysky.getColumnModel().getColumn(0).setResizable(false);
            Table_rozpatia_nadm_vysky.getColumnModel().getColumn(0).setHeaderValue(language.language_label(languageOption, 92)
            );
            Table_rozpatia_nadm_vysky.getColumnModel().getColumn(1).setResizable(false);
            Table_rozpatia_nadm_vysky.getColumnModel().getColumn(1).setHeaderValue(language.language_label(languageOption, 93)
            );
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Label_stredna_vyska_vodicov_nad_terenom.setText(language.language_label(languageOption, 94));

        buttonGroup1.add(jRadioButton_with_label_vypoctana);
        jRadioButton_with_label_vypoctana.setText(language.language_label(languageOption, 95));

        buttonGroup1.add(jRadioButton_with_label_vlastna);
        jRadioButton_with_label_vlastna.setText(language.language_label(languageOption, 96));
        jRadioButton_with_label_vlastna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_with_label_vlastnaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton_with_label_vlastna)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(TextField_hcmean_vlastna, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jRadioButton_with_label_vypoctana)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextField_hcmean_vpocitana, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                        .addComponent(Label_kotevne_useky, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Label_stredna_vyska_vodicov_nad_terenom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Label_zakladne_mech_napatie_minis5, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                .addComponent(Label_max_zataz_lana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TextField_zakladne_mech_lana_minus5)
                                .addComponent(TextField_max_mech_podiel_z_RTS))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Label_RTS_velicina3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Label_RTS_velicina2, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(Label_RTS)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(TextField_RTS)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Label_RTS_velicina))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Label_kotevne_useky1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jComboBox_conductor_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator2)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Label_vybrana_namrazova_oblast)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator1)
                                .addComponent(Button_namrazova_oblast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Label_typ_namrazy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(Label__typ_namrazy_Ccl, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(3, 3, 3)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(TextField_Ccl, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(Label_hustota_namrazy, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(3, 3, 3)
                                    .addComponent(TextField_hustota_namrazy, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Label_RTS_velicina1))
                                .addComponent(jComboBox_druh_namrazy, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Label_kotevne_useky)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Label_stredna_vyska_vodicov_nad_terenom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton_with_label_vypoctana)
                            .addComponent(TextField_hcmean_vpocitana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton_with_label_vlastna)
                            .addComponent(TextField_hcmean_vlastna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_conductor_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_kotevne_useky1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_RTS)
                            .addComponent(TextField_RTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_RTS_velicina))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_vybrana_namrazova_oblast)
                            .addComponent(Button_namrazova_oblast))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_druh_namrazy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_typ_namrazy))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label__typ_namrazy_Ccl)
                            .addComponent(TextField_Ccl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_hustota_namrazy)
                            .addComponent(TextField_hustota_namrazy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_RTS_velicina1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_zakladne_mech_napatie_minis5)
                            .addComponent(TextField_zakladne_mech_lana_minus5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_RTS_velicina2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_max_zataz_lana)
                            .addComponent(TextField_max_mech_podiel_z_RTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_RTS_velicina3)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(137, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_Icon_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_saveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_Icon_saveActionPerformed

    private void Button_Icon_save_asActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_save_asActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_Icon_save_asActionPerformed

    private void Button_Icon_save_resultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_save_resultsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_Icon_save_resultsActionPerformed

    private void Button_Icon_arr_row_table_kotevny_usekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_arr_row_table_kotevny_usekActionPerformed
     mainframe_new_kotevny_usek mainframe_new_kotevny_usek_JDialog_window = new mainframe_new_kotevny_usek(this, rootPaneCheckingEnabled);
        mainframe_new_kotevny_usek_JDialog_window.setVisible(true);

       if (existnewkotevnyusek == true){  // ak pride že vytvorit od Jdialog tak vytvor ak uzivatel zavie Jdilog križiok tam nie
       modelTable.addRow(new Object[]{(Boolean) false,(String) new_kotevny_usek_name});
       existnewkotevnyusek = false;
       }
        
    }//GEN-LAST:event_Button_Icon_arr_row_table_kotevny_usekActionPerformed

    private void Button_Icon_delete_row_table_kotevny_usekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_delete_row_table_kotevny_usekActionPerformed
       
     int selectedRow = Table_kotevne_useky.getSelectedRow();
     
     if ( selectedRow != -1){
        modelTable.removeRow(selectedRow); 
     }
        
    }//GEN-LAST:event_Button_Icon_delete_row_table_kotevny_usekActionPerformed

    private void Button_Icon_calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_calculateActionPerformed
        if (mainframeLodaed == true) {
            
            //nacitajvodič z jombo box
            int selected_conductor_index_from_JComboBox = jComboBox_conductor_chooser.getSelectedIndex();
            Object[] Conductor = new Object[7];
            Conductor = Databaza.get(selected_conductor_index_from_JComboBox);
            //vlož 4 premene do state equation 
            state_equation.set_variables_from_conductor(Conductor);
            
            
            
            
            
            
            
            
            
        }   
    }//GEN-LAST:event_Button_Icon_calculateActionPerformed

    private void Button_Icon_export_PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_export_PDFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_Icon_export_PDFActionPerformed

    private void Button_Icon_select_all_kotevny_usekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_select_all_kotevny_usekActionPerformed
        int rowCount = Table_kotevne_useky.getRowCount();
            //Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                Table_kotevne_useky.setValueAt(true, i, 0);
            }
    }//GEN-LAST:event_Button_Icon_select_all_kotevny_usekActionPerformed

    private void Button_Icon_deselect_all_kotevny_usekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_deselect_all_kotevny_usekActionPerformed
         int rowCount = Table_kotevne_useky.getRowCount();
            //Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                Table_kotevne_useky.setValueAt(false, i, 0);
            }
    }//GEN-LAST:event_Button_Icon_deselect_all_kotevny_usekActionPerformed

    //listening for conductor option
    private void jComboBox_conductor_chooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_conductor_chooserActionPerformed
       
        if (mainframeLodaed == true) {
            
            //nacitajvodič z jombo box
            int selected_conductor_index_from_JComboBox = jComboBox_conductor_chooser.getSelectedIndex();
            Object[] Conductor = new Object[7];
            Conductor = Databaza.get(selected_conductor_index_from_JComboBox);
            Variable_RTS = Double.parseDouble(String.valueOf(Conductor[6]))/Double.parseDouble(String.valueOf(Conductor[2]));
           
            DecimalFormat df = new DecimalFormat("###.###");  // definovany počet desatinnych miest
            TextField_RTS.setText(df.format(Variable_RTS));
            
            if (is_namrazove_oblasti_setted == true){   //  ak už je zvolena namrazova oblast tk spusti vypocet             
                Variable_Ir50=vypocet_IR50_namrazove_oblasti();
            }
            
        }  
        
        
    }//GEN-LAST:event_jComboBox_conductor_chooserActionPerformed

    private void TextField_RTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_RTSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_RTSActionPerformed

    private void Button_namrazova_oblastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_namrazova_oblastActionPerformed
      
        mainframe_namrazova_oblast_jDialog mainframe_namrazova_oblast_jDialog_window = new mainframe_namrazova_oblast_jDialog(this, rootPaneCheckingEnabled);
        mainframe_namrazova_oblast_jDialog_window.setVisible(true);

        if(is_namrazove_oblasti_setted== true){
        
        Label_vybrana_namrazova_oblast.setText(namrazove_oblasti_názov_oblasti);
        Variable_Ir50=vypocet_IR50_namrazove_oblasti();
        }
        
    }//GEN-LAST:event_Button_namrazova_oblastActionPerformed

    private void jComboBox_druh_namrazyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_druh_namrazyActionPerformed
        if (mainframeLodaed == true) {
            
          int selected_index_from_JComboBox = jComboBox_druh_namrazy.getSelectedIndex();
          
          switch (selected_index_from_JComboBox) {
            case 0:  TextField_Ccl.setEnabled(false);
                     TextField_hustota_namrazy.setEnabled(false);
                     Variable_Ccl = 1.1;
                     Variable_hustota_namrazy= 500.0;
                     
                     break;
            case 1:  TextField_Ccl.setEnabled(false);
                     TextField_hustota_namrazy.setEnabled(false);
                     Variable_Ccl = 1.1;
                     Variable_hustota_namrazy= 900.0;
                     break;
            case 2:  TextField_Ccl.setEnabled(false);
                     TextField_hustota_namrazy.setEnabled(false);
                     Variable_Ccl = 1.1;
                     Variable_hustota_namrazy= 300.0;
                     break;
            case 3:  TextField_Ccl.setEnabled(false);
                     TextField_hustota_namrazy.setEnabled(false);
                     Variable_Ccl = 1.1;
                     Variable_hustota_namrazy= 700.0;
                     break;
            case 4:  TextField_Ccl.setEnabled(false);
                     TextField_hustota_namrazy.setEnabled(false);
                     Variable_Ccl = 1.1;
                     Variable_hustota_namrazy= 500.0;
                     break;
            case 5:  TextField_Ccl.setEnabled(true);
                     TextField_hustota_namrazy.setEnabled(true);
                     Variable_Ccl = 1.1;
                     Variable_hustota_namrazy= 500.0; 
                     TextField_Ccl.setForeground(Color.black);
                     TextField_hustota_namrazy.setForeground(Color.black);
                     break;                    
        }
            TextField_Ccl.setText(String.valueOf(Variable_Ccl));
            TextField_hustota_namrazy.setText(String.valueOf(Variable_hustota_namrazy));
            
            
            
        }
    }//GEN-LAST:event_jComboBox_druh_namrazyActionPerformed

    private void TextField_CclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_CclActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_CclActionPerformed

    private void TextField_hustota_namrazyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_hustota_namrazyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_hustota_namrazyActionPerformed

    private void TextField_CclKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_CclKeyReleased
          
        if (TextField_Ccl.isEditable() == true){
          
         Variable_Ccl=doubleChecker_short_answer(TextField_Ccl);
            
        }
        
        
    }//GEN-LAST:event_TextField_CclKeyReleased

    private void TextField_hustota_namrazyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_hustota_namrazyKeyReleased
       if (TextField_hustota_namrazy.isEditable() == true){
          
         Variable_hustota_namrazy=doubleChecker_short_answer(TextField_hustota_namrazy);
            
        }
    }//GEN-LAST:event_TextField_hustota_namrazyKeyReleased

    private void TextField_zakladne_mech_lana_minus5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_zakladne_mech_lana_minus5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_zakladne_mech_lana_minus5ActionPerformed

    private void TextField_zakladne_mech_lana_minus5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_zakladne_mech_lana_minus5KeyReleased
        Variable_zakladne_mech_napatie_lana_pre_minus5=doubleChecker_short_answer(TextField_zakladne_mech_lana_minus5);
    }//GEN-LAST:event_TextField_zakladne_mech_lana_minus5KeyReleased

    private void TextField_max_mech_podiel_z_RTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_max_mech_podiel_z_RTSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_max_mech_podiel_z_RTSActionPerformed

    private void TextField_max_mech_podiel_z_RTSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_max_mech_podiel_z_RTSKeyReleased
          Variable_maximalne_zataz_lana_podiel_z_RTS=Variable_RTS*(doubleChecker_short_answer(TextField_zakladne_mech_lana_minus5)/100);
    }//GEN-LAST:event_TextField_max_mech_podiel_z_RTSKeyReleased

    private void Table_rozpatia_nadm_vyskyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table_rozpatia_nadm_vyskyKeyReleased
        
        
        
    }//GEN-LAST:event_Table_rozpatia_nadm_vyskyKeyReleased

    private void Table_rozpatiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table_rozpatiaKeyReleased
        
    }//GEN-LAST:event_Table_rozpatiaKeyReleased

    private void jRadioButton_with_label_vlastnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_label_vlastnaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_with_label_vlastnaActionPerformed

 
  
  public static void lanochangeinDatabaze() {
  nacitatDatabazuLan();      
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
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
            java.util.logging.Logger.getLogger(mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Icon_arr_row_table_kotevny_usek;
    private javax.swing.JButton Button_Icon_calculate;
    private javax.swing.JButton Button_Icon_delete_row_table_kotevny_usek;
    private javax.swing.JButton Button_Icon_deselect_all_kotevny_usek;
    private javax.swing.JButton Button_Icon_export_PDF;
    private javax.swing.JButton Button_Icon_save;
    private javax.swing.JButton Button_Icon_save_as;
    private javax.swing.JButton Button_Icon_save_results;
    private javax.swing.JButton Button_Icon_select_all_kotevny_usek;
    private javax.swing.JButton Button_namrazova_oblast;
    private javax.swing.JLabel Label_RTS;
    private javax.swing.JLabel Label_RTS_velicina;
    private javax.swing.JLabel Label_RTS_velicina1;
    private javax.swing.JLabel Label_RTS_velicina2;
    private javax.swing.JLabel Label_RTS_velicina3;
    private javax.swing.JLabel Label__typ_namrazy_Ccl;
    private javax.swing.JLabel Label_hustota_namrazy;
    private javax.swing.JLabel Label_kotevne_useky;
    private static javax.swing.JLabel Label_kotevne_useky1;
    private javax.swing.JLabel Label_max_zataz_lana;
    private static javax.swing.JLabel Label_stredna_vyska_vodicov_nad_terenom;
    private javax.swing.JLabel Label_typ_namrazy;
    private javax.swing.JLabel Label_vybrana_namrazova_oblast;
    private javax.swing.JLabel Label_zakladne_mech_napatie_minis5;
    private javax.swing.JTable Table_kotevne_useky;
    private javax.swing.JTable Table_rozpatia;
    private javax.swing.JTable Table_rozpatia_nadm_vysky;
    private javax.swing.JTextField TextField_Ccl;
    private javax.swing.JTextField TextField_RTS;
    private javax.swing.JTextField TextField_hcmean_vlastna;
    private javax.swing.JTextField TextField_hcmean_vpocitana;
    private javax.swing.JTextField TextField_hustota_namrazy;
    private javax.swing.JTextField TextField_max_mech_podiel_z_RTS;
    private javax.swing.JTextField TextField_zakladne_mech_lana_minus5;
    private javax.swing.ButtonGroup buttonGroup1;
    private static final javax.swing.JComboBox<String> jComboBox_conductor_chooser = new javax.swing.JComboBox<>();
    private static final javax.swing.JComboBox<String> jComboBox_druh_namrazy = new javax.swing.JComboBox<>();
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton_with_label_vlastna;
    private javax.swing.JRadioButton jRadioButton_with_label_vypoctana;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
DefaultTableModel modelTable;
DefaultTableModel modeltable_rozpatia;
DefaultTableModel modeltable_rozpatia_nadm_vysky;
public static String new_kotevny_usek_name;
public static boolean existnewkotevnyusek = false;
private static boolean mainframeLodaed = false;

//namrazove oblasti premene
public static Object[] hodnoty_namrazove_oblasti = new Object[3];
public static String namrazove_oblasti_názov_oblasti = "----";
public static boolean is_namrazove_oblasti_setted = false;
private static boolean tablemodellistener_rozpatia = true;
private static boolean tablemodellistener_nad_vysky = true;
// general variables
private static String filename = "new_file";
private static String filenamePath;
private static String filenamePath_plus_filename;
private static String memory_path_plus_filename_here;
private static boolean memory_path_plus_filename_existence = false;

// importnt variables
private static double  Variable_RTS;
private static double  Variable_Ir50;
private static double  Variable_Klc;
private static double  Variable_Kh;
private static double  Variable_Ccl;
private static double  Variable_hustota_namrazy;
private static double  Variable_zakladne_mech_napatie_lana_pre_minus5;
private static double  Variable_maximalne_zataz_lana_podiel_z_RTS;
private static ArrayList<Double>  Variable_Ai_dlzka_rozpatia = new ArrayList<>();
private static ArrayList<Double>  Variable_hi_vyska_stoziarov = new ArrayList<>();
private static ArrayList<Double>  Variable_hi2_nadmorska_vyska_stoziarov = new ArrayList<>();
private static double  Variable_Hc_mean;
private static double  Variable_Hc_mean_medzikrok;
private static double  Variable_n_pocet_rozpati;
// conductor variables
private static final ArrayList<Object[]> Databaza = new ArrayList<>();
public static javax.swing.JLabel Lano_listener_JLabel_Maska;

// nastav rohovu ikonu
private void seticon() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/mt_graphic/" + "icon.png")));
    }
// zober meno noveho kotevneho useku   
    public static void  new_kotevny_usek_jdialog (String X){
        new_kotevny_usek_name = X;
        
    }
    
    public static boolean newkotevnyusekstatus(boolean X) {
        existnewkotevnyusek = X;
        return existnewkotevnyusek;
    }
    
    private static void nacitatDatabazuLan(){
    mainframeLodaed = false;    
    String  memory_path_plus_filename  = startPanel.set_memory_path_conductor();
        
        if(memory_path_plus_filename.equals("none")){}
        else{
         memory_path_plus_filename_here  = startPanel.set_memory_path_conductor();
         
         File test = new File(memory_path_plus_filename_here); // test či existuje subor ak nie tak potom požiadaj uživatela o cestu 
         boolean file_teste =test.exists();
         if ( file_teste == true) {
           memory_path_plus_filename_existence=true;  
         }else{
           memory_path_plus_filename_existence=false;  
         }
         
         
        }
        
    if ( memory_path_plus_filename_existence == false){
        
        
        
        String userhome = System.getProperty("user.dir");          //userhome is home folder of program
        JFileChooser chooser = new JFileChooser(userhome + "\\resources");  //key files are stored in resources
        FileNameExtensionFilter txtfilter = new FileNameExtensionFilter(
                language.language_label(languageOption, 32), "txt");                                // whitch type of files are we looking for
        chooser.setDialogTitle(language.language_label(languageOption, 31));   // title for Jfile chooser window
        chooser.setFileFilter(txtfilter);                                   // Txt filter for choosing file

        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        filenamePath = f.getParent();                                      //get full path of selected file

        filename = f.getName();                                            // get full name of selected file

                          //set nname of loaded file intext field

        filenamePath_plus_filename= filenamePath + "\\" + filename;        // load entire file into table and memory of  proram
        startPanel.conductor_memory_path_plus_filename=filenamePath_plus_filename;  
        File subor = new File(userhome + "\\resources\\" + "memory.txt");  // ak kanita tak si zapametaj cestu kde to bolo resources, memory
        try {
            PrintWriter fw = new PrintWriter(subor);
            fw.println("Memory file do not edit");
            fw.println(filenamePath_plus_filename);
            fw.close();

        } catch (FileNotFoundException ex) {

        }
        
        
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
           
                      jComboBox_conductor_chooser.removeAllItems();
                             Databaza.removeAll(Databaza);
            
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
                Databaza.add(Conductor);

                if (EmptyLine.equals("EMD_OF_FILE")) {
                    break;
                }

            }
            

            int numberofElements = Databaza.size();             //pregeneruj tabulku a nakresli ju
            for (int i = 0; i <= numberofElements - 1; i++) {
                Object[] Conductor = new Object[7];
                Conductor = Databaza.get(i);
                jComboBox_conductor_chooser.addItem((String) Conductor[0]); 
            }

        } catch (FileNotFoundException ex) {

        }
    
    }
    
    private static double vypocet_IR50_namrazove_oblasti(){
        double Ir50 = 0.0;
        int selected_conductor_index_from_JComboBox = jComboBox_conductor_chooser.getSelectedIndex();
        Object[] Conductor = new Object[7];
        Conductor = Databaza.get(selected_conductor_index_from_JComboBox);
        
        double diameter = Double.parseDouble(String.valueOf(Conductor[1]));
        
        if(diameter <= 30){
           
            double hodnota1 = Double.parseDouble(String.valueOf(hodnoty_namrazove_oblasti[0]));
            double hodnota2 = Double.parseDouble(String.valueOf(hodnoty_namrazove_oblasti[1]));
            
            Ir50 = hodnota1 + hodnota2*diameter;
        }else{
            
            double hodnota1 = Double.parseDouble(String.valueOf(hodnoty_namrazove_oblasti[2]));
            double hodnota2 = Double.parseDouble(String.valueOf(hodnoty_namrazove_oblasti[3]));
            
            Ir50 = hodnota1 + hodnota2*diameter;
        }
            
        //System.out.println(Ir50);
        return Ir50;
    }
    
     private double doubleChecker_short_answer (javax.swing.JTextField Y){
       Double value ;
        try{
        value = Double.parseDouble(Y.getText());
        Y.setForeground(Color.black);
        return value;
        }catch(NumberFormatException | NullPointerException e){
         Y.setForeground(Color.red);
         Y.setText(language.language_label(languageOption, 85));
        return value = 123456789.987654321;            
        }
     }
     
     private double doubleChecker (javax.swing.JTextField Y){
       Double value ;
        try{
        value = Double.parseDouble(Y.getText());
        Y.setForeground(Color.black);
        return value;
        }catch(NumberFormatException | NullPointerException e){
         Y.setForeground(Color.red);
         Y.setText(language.language_label(languageOption, 47));
        return value = 123456789.987654321;            
        }
     }
     
      private double doubleChecker_tableinput (String Y){
       Double value ;
        try{
        value = Double.parseDouble(Y);      
        return value;
        }catch(NumberFormatException | NullPointerException e){ 
       return value = 123456789.987654321;            
        }
     }
}
