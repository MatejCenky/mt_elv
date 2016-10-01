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
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.ToDoubleFunction;
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
        setResizable(true);
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
        
        jRadioButton_Klc_1.doClick();
        jRadioButton_Kh_1.doClick();
        jRadioButton_Bi_1.doClick();

        // inicializacia_ vyber druhu namrazy
        
        jComboBox_druh_namrazy.removeAllItems();  // ymaz povodne itemy
        jComboBox_druh_namrazy.addItem(language.language_label(languageOption, 77));
        jComboBox_druh_namrazy.addItem(language.language_label(languageOption, 78));
        jComboBox_druh_namrazy.addItem(language.language_label(languageOption, 79));
        jComboBox_druh_namrazy.addItem(language.language_label(languageOption, 80));
        jComboBox_druh_namrazy.addItem(language.language_label(languageOption, 81));
        jComboBox_druh_namrazy.addItem(language.language_label(languageOption, 82));
        jComboBox_druh_namrazy.setSelectedIndex(4); 
        
        // inicializacia KPB
        jComboBox_KPB_typ_terenu.removeAllItems();;
        jComboBox_KPB_typ_terenu.addItem(language.language_label(languageOption, 165));
        jComboBox_KPB_typ_terenu.addItem(language.language_label(languageOption, 166));
        jComboBox_KPB_typ_terenu.addItem(language.language_label(languageOption, 167));
        jComboBox_KPB_typ_terenu.addItem(language.language_label(languageOption, 168));
        jComboBox_druh_namrazy.setSelectedIndex(1);
        jRadioButton_KPB_cas_vypoctu_1_rok.doClick();
        // inicializacia Hcmean
        jRadioButton_with_label_vypoctana.doClick();
        Variable_Hc_mean_medzikrok= 0.0;
        TextField_hcmean_vpocitana.setText("0.0");
        // inicializacia tabulky a casy
        jRadioButton_with_label_konecne.doClick();
        Variable_T0_zivotnost= Double.valueOf(TextField_tabulky_konecna.getText())*24*365;
        Variable_Tp_prechodna_doba=Double.valueOf(TextField_tabulky_konecna.getText())*24*365;
        jRadioButton_with_pretazenia_vypocitana.doClick();;
        // vetrova oblast  a dalsie s nou spojene premenne 
        jComboBox_vetrova_oblast.removeAllItems();      
        for (int i = 0; i < 10; i++) { // nacitanie prvkov do JCombo
            jComboBox_vetrova_oblast.addItem(language.language_label(languageOption, 114+i));
        }      
        jComboBox_vetrova_oblast.setSelectedIndex(0);
        jRadioButton_vetrova_oblast_Cdir_1.doClick();
        jRadioButton_vetrova_oblast_C0_1.doClick();
        // KPB
       
        //charterenu
        jComboBox_char_terenu.removeAllItems();
        for (int i = 0; i < 5; i++) { // nacitanie prvkov do JCombo
            jComboBox_char_terenu.addItem(language.language_label(languageOption, 130+i));
        }
        jComboBox_char_terenu.setSelectedIndex(0);
        
        //uroven spolahlivosti
        jComboBox_uroven_splahlivosti.removeAllItems();
        for (int i = 0; i < 7; i++) { // nacitanie prvkov do JCombo
            jComboBox_uroven_splahlivosti.addItem(language.language_label(languageOption, 141+i));
        }
        jComboBox_uroven_splahlivosti.setSelectedIndex(0);
        
        // str rozpaia
        Variable_mid_span_docasna=0.0;
        Variable_mid_span_terrain_docasna=0.0;
        jRadioButton_with_label_rozpatie_klasicky.doClick();
        // nulovanie   
        hodnoty_namrazove_oblasti = new Object[]{(double) 0.0,(double) 0.0, (double) 0.0,(double) 0.0};  
        // str. rocna teplota
        TextField_srt_roc_teplota.setEditable(true);
        TextField_srt_roc_teplota.setEnabled(true);
        Variable_streda_roc_teplota =  Double.parseDouble(TextField_srt_roc_teplota.getText());
        
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
                
//                System.out.println(Variable_Ai_dlzka_rozpatia);
//                System.out.println(Variable_hi_vyska_stoziarov);
//                System.out.println(Variable_hi2_nadmorska_vyska_stoziarov);
                
                int odcitacac_rozpati=0;
                for(int i = 0; i< Variable_Ai_dlzka_rozpatia.size();i++){           //odstranuje chybne zapisi alebo prazdne hodnoty pre rozpatia
                    
                    if(Variable_Ai_dlzka_rozpatia.get(i) == 0.0 || Variable_Ai_dlzka_rozpatia.get(i) == 123456789.987654321 ){
                    odcitacac_rozpati=odcitacac_rozpati+1;
                }
                }
                
                Variable_n_pocet_rozpati = (Variable_Ai_dlzka_rozpatia.size()-odcitacac_rozpati);
                
                // naplnenie AI array rozpatia na zaklade postu rozpati a deltaHi ktore ma rovnaky rozmer ako Ai
                Variable_Ai_array = new double[(int) Variable_n_pocet_rozpati];  
                Variable_DeltaHi_array = new double[(int) Variable_n_pocet_rozpati];
                Variable_Hi_array = new double[(int) Variable_n_pocet_rozpati+1];
                for (int i = 0; i < Variable_Ai_array.length; i++) {
                   
                Variable_Ai_array[i] = Variable_Ai_dlzka_rozpatia.get(i);
                Variable_Hi_array[i] = Variable_hi_vyska_stoziarov.get(i);
                
                double prvavyska_stoziar_plus_zem = Variable_hi2_nadmorska_vyska_stoziarov.get(i) + Variable_hi_vyska_stoziarov.get(i);
                double druhavyska_stoziar_plus_zem= Variable_hi2_nadmorska_vyska_stoziarov.get(i+1) + Variable_hi_vyska_stoziarov.get(i+1);
                Variable_DeltaHi_array[i]= druhavyska_stoziar_plus_zem - prvavyska_stoziar_plus_zem;
                
                }
                Variable_Hi_array[Variable_Ai_array.length] = Variable_hi_vyska_stoziarov.get(Variable_Ai_array.length);
                
                // Hcmean pocitac
                double Sumar_scitavac=0;
                for(int i = 0; i< Variable_n_pocet_rozpati+1;i++){           //pocita len tam kde je zadana dlka zorpatia ine stožiare bdue ignotrovat plus jedna preto lebo pocet stožiarov je vždy rozpatia plus 1
                    
                    Sumar_scitavac=Sumar_scitavac + Variable_hi_vyska_stoziarov.get(i);
                    
                }

               Variable_Hc_mean_medzikrok= Sumar_scitavac/Variable_n_pocet_rozpati;
               if(jRadioButton_with_label_vypoctana.isSelected() == true){
               Variable_Hc_mean=Variable_Hc_mean_medzikrok;
               }
               DecimalFormat df = new DecimalFormat("###.###");   
               TextField_hcmean_vpocitana.setText(String.valueOf(df.format(Variable_Hc_mean_medzikrok))); // vloz do text field pri radio buttne
               
               if(Variable_Ai_array.length != 0 || Variable_DeltaHi_array.length != 0){
                  mid_span_flat();
                  mid_span_terrain();
                  
                  TextField_STRrozpatie_klasicky.setText(String.valueOf(df.format(Variable_mid_span_docasna)));
                  TextField_STRrozpatie_sPrevisenim.setText(String.valueOf(df.format(Variable_mid_span_terrain_docasna)));
                  if(jRadioButton_with_label_rozpatie_klasicky.isSelected() == true){Variable_mid_span=Variable_mid_span_docasna;}
                  if(jRadioButton_with_label_rozpate_previsenia.isSelected() == true){Variable_mid_span=Variable_mid_span_terrain_docasna;}
                }
               
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
                
                 // Hcmean pocitac
                 double Sumar_scitavac=0;
                for(int i = 0; i< Variable_n_pocet_rozpati+1;i++){           //pocita len tam kde je zadana dlka zorpatia ine stožiare bdue ignotrovat plus jedna preto lebo pocet stožiarov je vždy rozpatia plus 1
                    
                    Sumar_scitavac=Sumar_scitavac + Variable_hi_vyska_stoziarov.get(i);
                    
                }
               DecimalFormat df = new DecimalFormat("###.###");
               Variable_Hc_mean_medzikrok= Sumar_scitavac/Variable_n_pocet_rozpati;    // vypocitaj Hcmena
               TextField_hcmean_vpocitana.setText(String.valueOf(df.format(Variable_Hc_mean_medzikrok))); // vloz do text field pri radio buttne
               if(jRadioButton_with_label_vypoctana.isSelected() == true){
               Variable_Hc_mean=Variable_Hc_mean_medzikrok;
               }
                // naplnenie AI array rozpatia na zaklade postu rozpati a deltaHi ktore ma rovnaky rozmer ako Ai
                Variable_Ai_array = new double[(int) Variable_n_pocet_rozpati];  
                Variable_DeltaHi_array = new double[(int) Variable_n_pocet_rozpati];
                Variable_Hi_array = new double[(int) Variable_n_pocet_rozpati+1];
                
                for (int i = 0; i < Variable_Ai_array.length; i++) {
                   
                Variable_Ai_array[i] = Variable_Ai_dlzka_rozpatia.get(i);
                Variable_Hi_array[i] = Variable_hi_vyska_stoziarov.get(i);
                
                double prvavyska_stoziar_plus_zem = Variable_hi2_nadmorska_vyska_stoziarov.get(i) + Variable_hi_vyska_stoziarov.get(i);
                double druhavyska_stoziar_plus_zem= Variable_hi2_nadmorska_vyska_stoziarov.get(i+1) + Variable_hi_vyska_stoziarov.get(i+1);
                Variable_DeltaHi_array[i]= druhavyska_stoziar_plus_zem - prvavyska_stoziar_plus_zem;
                }
                Variable_Hi_array[Variable_Ai_array.length] = Variable_hi_vyska_stoziarov.get(Variable_Ai_array.length);
                
                //ak niesu array nulove vypočitaj vyšlu
                if(Variable_Ai_array.length != 0 || Variable_DeltaHi_array.length != 0){
                  mid_span_flat();
                  mid_span_terrain();
                 
                  TextField_STRrozpatie_klasicky.setText(String.valueOf(df.format(Variable_mid_span_docasna)));
                  TextField_STRrozpatie_sPrevisenim.setText(String.valueOf(df.format(Variable_mid_span_terrain_docasna)));
                  if(jRadioButton_with_label_rozpatie_klasicky.isSelected() == true){Variable_mid_span=Variable_mid_span_docasna;}
                  if(jRadioButton_with_label_rozpate_previsenia.isSelected() == true){Variable_mid_span=Variable_mid_span_terrain_docasna;}
                }
//                System.out.println("-------------");
//                System.out.println(Arrays.toString(Variable_Ai_array));
//                System.out.println(Arrays.toString(Variable_DeltaHi_array));
//                System.out.println(Arrays.toString(Variable_Hi_array));
//                System.out.println(Variable_Hc_mean);
               
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
        buttonGroup_tabulka = new javax.swing.ButtonGroup();
        buttonGroup_klc = new javax.swing.ButtonGroup();
        buttonGroup_kh = new javax.swing.ButtonGroup();
        buttonGroup_Bi = new javax.swing.ButtonGroup();
        buttonGroup_Cdir = new javax.swing.ButtonGroup();
        buttonGroup_C0 = new javax.swing.ButtonGroup();
        buttonGroup_stredne_rozpatia = new javax.swing.ButtonGroup();
        buttonGroup_pretazenia_vlystne_vypocitane = new javax.swing.ButtonGroup();
        buttonGroup_KPB_cas_vypoctu = new javax.swing.ButtonGroup();
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
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        TextField_teploha_stav1 = new javax.swing.JTextField();
        Label_teploty = new javax.swing.JLabel();
        TextField_teploha_stav2 = new javax.swing.JTextField();
        TextField_teploha_stav3 = new javax.swing.JTextField();
        TextField_teploha_stav4 = new javax.swing.JTextField();
        TextField_teploha_stav5 = new javax.swing.JTextField();
        TextField_teploha_stav6 = new javax.swing.JTextField();
        TextField_teploha_stav7 = new javax.swing.JTextField();
        TextField_teploha_stav8 = new javax.swing.JTextField();
        TextField_teploha_stav9 = new javax.swing.JTextField();
        TextField_teploha_stav10 = new javax.swing.JTextField();
        TextField_teploha_stav11 = new javax.swing.JTextField();
        TextField_teploha_stav12 = new javax.swing.JTextField();
        TextField_teploha_stav13 = new javax.swing.JTextField();
        TextField_teploha_stav14 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        TextField_pretazenia_stav1 = new javax.swing.JTextField();
        Label_pretazenia = new javax.swing.JLabel();
        TextField_pretazenia_stav2 = new javax.swing.JTextField();
        TextField_pretazenia_stav3 = new javax.swing.JTextField();
        TextField_pretazenia_stav4 = new javax.swing.JTextField();
        TextField_pretazenia_stav5 = new javax.swing.JTextField();
        TextField_pretazenia_stav6 = new javax.swing.JTextField();
        TextField_pretazenia_stav7 = new javax.swing.JTextField();
        TextField_pretazenia_stav8 = new javax.swing.JTextField();
        TextField_pretazenia_stav9 = new javax.swing.JTextField();
        TextField_pretazenia_stav10 = new javax.swing.JTextField();
        TextField_pretazenia_stav11 = new javax.swing.JTextField();
        TextField_pretazenia_stav12 = new javax.swing.JTextField();
        TextField_pretazenia_stav13 = new javax.swing.JTextField();
        TextField_pretazenia_stav14 = new javax.swing.JTextField();
        jRadioButton_with_pretazenia_vypocitana = new javax.swing.JRadioButton();
        jRadioButton_with_pretazenia_vlastna = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        Label_tabulky = new javax.swing.JLabel();
        Label_tabulky1 = new javax.swing.JLabel();
        jRadioButton_with_label_pociatocne = new javax.swing.JRadioButton();
        jRadioButton_with_label_prechodne = new javax.swing.JRadioButton();
        jRadioButton_with_label_konecne = new javax.swing.JRadioButton();
        TextField_tabulky_prechodna = new javax.swing.JTextField();
        TextField_tabulky_konecna = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        Label_vybrana_namrazova_oblast = new javax.swing.JLabel();
        Button_namrazova_oblast = new javax.swing.JButton();
        Label__Klc = new javax.swing.JLabel();
        jRadioButton_Klc_1 = new javax.swing.JRadioButton();
        TextField_Kcl = new javax.swing.JTextField();
        jRadioButton_Klc_vlastna = new javax.swing.JRadioButton();
        Label__Kh = new javax.swing.JLabel();
        jRadioButton_Kh_1 = new javax.swing.JRadioButton();
        jRadioButton_Kh_vlastna = new javax.swing.JRadioButton();
        TextField_Kh = new javax.swing.JTextField();
        Label__Bi = new javax.swing.JLabel();
        jRadioButton_Bi_1 = new javax.swing.JRadioButton();
        TextField_Bi_1 = new javax.swing.JTextField();
        jRadioButton_Bi_2 = new javax.swing.JRadioButton();
        TextField_Bi2 = new javax.swing.JTextField();
        Label_typ_namrazy = new javax.swing.JLabel();
        jComboBox_druh_namrazy = new javax.swing.JComboBox<>();
        Label__typ_namrazy_Ccl = new javax.swing.JLabel();
        TextField_Ccl = new javax.swing.JTextField();
        Label_hustota_namrazy = new javax.swing.JLabel();
        TextField_hustota_namrazy = new javax.swing.JTextField();
        Label_RTS_velicina1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        Label_stredne_rozpatie = new javax.swing.JLabel();
        jRadioButton_with_label_rozpatie_klasicky = new javax.swing.JRadioButton();
        jRadioButton_with_label_rozpate_previsenia = new javax.swing.JRadioButton();
        TextField_STRrozpatie_klasicky = new javax.swing.JTextField();
        TextField_STRrozpatie_sPrevisenim = new javax.swing.JTextField();
        Label_RTS_velicina5 = new javax.swing.JLabel();
        Label_RTS_velicina6 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_rozpatia = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_rozpatia_nadm_vysky = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_kotevne_useky = new javax.swing.JTable();
        Label_kotevne_useky = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jRadioButton_with_label_vlastna = new javax.swing.JRadioButton();
        TextField_hcmean_vlastna = new javax.swing.JTextField();
        TextField_hcmean_vpocitana = new javax.swing.JTextField();
        jRadioButton_with_label_vypoctana = new javax.swing.JRadioButton();
        Label_stredna_vyska_vodicov_nad_terenom = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        Label_kotevne_useky1 = new javax.swing.JLabel();
        Label_RTS = new javax.swing.JLabel();
        TextField_RTS = new javax.swing.JTextField();
        Label_RTS_velicina = new javax.swing.JLabel();
        Label_zakladne_mech_napatie_minis5 = new javax.swing.JLabel();
        Label_max_zataz_lana = new javax.swing.JLabel();
        TextField_zakladne_mech_lana_minus5 = new javax.swing.JTextField();
        TextField_max_mech_podiel_z_RTS = new javax.swing.JTextField();
        Label_RTS_velicina2 = new javax.swing.JLabel();
        Label_RTS_velicina3 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        Label_uroven_spolahlivosti = new javax.swing.JLabel();
        jComboBox_uroven_splahlivosti = new javax.swing.JComboBox<>();
        Label__stredna_rocna_teplota = new javax.swing.JLabel();
        TextField_srt_roc_teplota = new javax.swing.JTextField();
        Label_RTS_velicina7 = new javax.swing.JLabel();
        Label__stav_KPB = new javax.swing.JLabel();
        jComboBox_stav_KPB = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        Label_KPB = new javax.swing.JLabel();
        Label_KPB_typ_terenu = new javax.swing.JLabel();
        jComboBox_KPB_typ_terenu = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPane_KPB_typ_terenu = new javax.swing.JTextArea();
        jRadioButton_KPB_cas_vypoctu_1_rok = new javax.swing.JRadioButton();
        jRadioButton_KPB_cas_vypoctu_prechodne = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        Label_vetrova_oblast = new javax.swing.JLabel();
        Label_základna_rychlost_vetra = new javax.swing.JLabel();
        TextField_Vmean_0 = new javax.swing.JTextField();
        Label_RTS_velicina4 = new javax.swing.JLabel();
        Label_vetrova_oblast_Cdir = new javax.swing.JLabel();
        jRadioButton_vetrova_oblast_Cdir_1 = new javax.swing.JRadioButton();
        jRadioButton_vetrova_oblast_Cdir_vlastna = new javax.swing.JRadioButton();
        TextField_vetrova_oblast_Cdir = new javax.swing.JTextField();
        Label_vetrova_oblast_C0 = new javax.swing.JLabel();
        jRadioButton_vetrova_oblast_C0_1 = new javax.swing.JRadioButton();
        jRadioButton_vetrova_oblast_C0_vlastna = new javax.swing.JRadioButton();
        TextField_vetrova_oblast_C0 = new javax.swing.JTextField();
        Label_char_terenu = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane_char_terenu = new javax.swing.JTextArea();
        Label__char_terenu_kr = new javax.swing.JLabel();
        TextField_Kr = new javax.swing.JTextField();
        Label_char_terenu_zo = new javax.swing.JLabel();
        TextField_dlzka_drsnjosti_zo = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        Label_hlavicka_nazov = new javax.swing.JLabel();
        Label_hlavicka_vypocet_podla_normi = new javax.swing.JLabel();
        jTextField_nazov_normi = new javax.swing.JTextField();
        jTextField_nadpis_pre_prechodna = new javax.swing.JTextField();
        Label_hlavicka_nadpis_pre_prechodne = new javax.swing.JLabel();
        Label_hlavicka_stavba = new javax.swing.JLabel();
        jTextField_nazov_nazov_stavby = new javax.swing.JTextField();
        Label_hlavicka_SOPS = new javax.swing.JLabel();
        jTextField_nazov_SOPS = new javax.swing.JTextField();
        jTextField_nazov_arch_cislo = new javax.swing.JTextField();
        Label_hlavicka_arch_cislo = new javax.swing.JLabel();
        Label_hlavicka_cislo_strany = new javax.swing.JLabel();
        jTextField_nazov_cislo_strany = new javax.swing.JTextField();
        Label_hlavicka_vypracoval = new javax.swing.JLabel();
        jTextField_vypracoval = new javax.swing.JTextField();
        jTextField_datum = new javax.swing.JTextField();
        Label_hlavicka_datum = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

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
                .addGap(800, 800, 800))
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

        TextField_teploha_stav1.setText("0");
        TextField_teploha_stav1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_teploha_stav1ActionPerformed(evt);
            }
        });
        TextField_teploha_stav1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_teploha_stav1KeyReleased(evt);
            }
        });

        Label_teploty.setText(language.language_label(languageOption, 157));

        TextField_teploha_stav2.setText("0");
        TextField_teploha_stav2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_teploha_stav2ActionPerformed(evt);
            }
        });
        TextField_teploha_stav2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_teploha_stav2KeyReleased(evt);
            }
        });

        TextField_teploha_stav3.setText("0");
        TextField_teploha_stav3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_teploha_stav3ActionPerformed(evt);
            }
        });
        TextField_teploha_stav3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_teploha_stav3KeyReleased(evt);
            }
        });

        TextField_teploha_stav4.setText("0");
        TextField_teploha_stav4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_teploha_stav4ActionPerformed(evt);
            }
        });
        TextField_teploha_stav4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_teploha_stav4KeyReleased(evt);
            }
        });

        TextField_teploha_stav5.setText("0");
        TextField_teploha_stav5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_teploha_stav5ActionPerformed(evt);
            }
        });
        TextField_teploha_stav5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_teploha_stav5KeyReleased(evt);
            }
        });

        TextField_teploha_stav6.setText("0");
        TextField_teploha_stav6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_teploha_stav6ActionPerformed(evt);
            }
        });
        TextField_teploha_stav6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_teploha_stav6KeyReleased(evt);
            }
        });

        TextField_teploha_stav7.setText("0");
        TextField_teploha_stav7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_teploha_stav7ActionPerformed(evt);
            }
        });
        TextField_teploha_stav7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_teploha_stav7KeyReleased(evt);
            }
        });

        TextField_teploha_stav8.setText("0");
        TextField_teploha_stav8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_teploha_stav8ActionPerformed(evt);
            }
        });
        TextField_teploha_stav8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_teploha_stav8KeyReleased(evt);
            }
        });

        TextField_teploha_stav9.setText("0");
        TextField_teploha_stav9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_teploha_stav9ActionPerformed(evt);
            }
        });
        TextField_teploha_stav9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_teploha_stav9KeyReleased(evt);
            }
        });

        TextField_teploha_stav10.setText("0");
        TextField_teploha_stav10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_teploha_stav10ActionPerformed(evt);
            }
        });
        TextField_teploha_stav10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_teploha_stav10KeyReleased(evt);
            }
        });

        TextField_teploha_stav11.setText("0");
        TextField_teploha_stav11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_teploha_stav11ActionPerformed(evt);
            }
        });
        TextField_teploha_stav11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_teploha_stav11KeyReleased(evt);
            }
        });

        TextField_teploha_stav12.setText("0");
        TextField_teploha_stav12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_teploha_stav12ActionPerformed(evt);
            }
        });
        TextField_teploha_stav12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_teploha_stav12KeyReleased(evt);
            }
        });

        TextField_teploha_stav13.setText("0");
        TextField_teploha_stav13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_teploha_stav13ActionPerformed(evt);
            }
        });
        TextField_teploha_stav13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_teploha_stav13KeyReleased(evt);
            }
        });

        TextField_teploha_stav14.setText("0");
        TextField_teploha_stav14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_teploha_stav14ActionPerformed(evt);
            }
        });
        TextField_teploha_stav14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_teploha_stav14KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(TextField_teploha_stav1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_teploha_stav2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_teploha_stav3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_teploha_stav4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_teploha_stav5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_teploha_stav6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_teploha_stav7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_teploha_stav8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_teploha_stav9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_teploha_stav10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_teploha_stav11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_teploha_stav12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_teploha_stav13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_teploha_stav14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Label_teploty))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(Label_teploty)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextField_teploha_stav1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_teploha_stav2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_teploha_stav3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_teploha_stav4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_teploha_stav5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_teploha_stav6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_teploha_stav7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_teploha_stav8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_teploha_stav9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_teploha_stav10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_teploha_stav11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_teploha_stav12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_teploha_stav13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_teploha_stav14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        TextField_pretazenia_stav1.setText("1.0");
        TextField_pretazenia_stav1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_pretazenia_stav1ActionPerformed(evt);
            }
        });
        TextField_pretazenia_stav1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_pretazenia_stav1KeyReleased(evt);
            }
        });

        Label_pretazenia.setText(language.language_label(languageOption, 158));

        TextField_pretazenia_stav2.setText("1.0");
        TextField_pretazenia_stav2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_pretazenia_stav2ActionPerformed(evt);
            }
        });
        TextField_pretazenia_stav2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_pretazenia_stav2KeyReleased(evt);
            }
        });

        TextField_pretazenia_stav3.setText("1.0");
        TextField_pretazenia_stav3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_pretazenia_stav3ActionPerformed(evt);
            }
        });
        TextField_pretazenia_stav3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_pretazenia_stav3KeyReleased(evt);
            }
        });

        TextField_pretazenia_stav4.setText("1.0");
        TextField_pretazenia_stav4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_pretazenia_stav4ActionPerformed(evt);
            }
        });
        TextField_pretazenia_stav4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_pretazenia_stav4KeyReleased(evt);
            }
        });

        TextField_pretazenia_stav5.setText("1.0");
        TextField_pretazenia_stav5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_pretazenia_stav5ActionPerformed(evt);
            }
        });
        TextField_pretazenia_stav5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_pretazenia_stav5KeyReleased(evt);
            }
        });

        TextField_pretazenia_stav6.setText("1.0");
        TextField_pretazenia_stav6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_pretazenia_stav6ActionPerformed(evt);
            }
        });
        TextField_pretazenia_stav6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_pretazenia_stav6KeyReleased(evt);
            }
        });

        TextField_pretazenia_stav7.setText("1.0");
        TextField_pretazenia_stav7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_pretazenia_stav7ActionPerformed(evt);
            }
        });
        TextField_pretazenia_stav7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_pretazenia_stav7KeyReleased(evt);
            }
        });

        TextField_pretazenia_stav8.setText("1.0");
        TextField_pretazenia_stav8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_pretazenia_stav8ActionPerformed(evt);
            }
        });
        TextField_pretazenia_stav8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_pretazenia_stav8KeyReleased(evt);
            }
        });

        TextField_pretazenia_stav9.setText("1.0");
        TextField_pretazenia_stav9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_pretazenia_stav9ActionPerformed(evt);
            }
        });
        TextField_pretazenia_stav9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_pretazenia_stav9KeyReleased(evt);
            }
        });

        TextField_pretazenia_stav10.setText("1.0");
        TextField_pretazenia_stav10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_pretazenia_stav10ActionPerformed(evt);
            }
        });
        TextField_pretazenia_stav10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_pretazenia_stav10KeyReleased(evt);
            }
        });

        TextField_pretazenia_stav11.setText("1.0");
        TextField_pretazenia_stav11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_pretazenia_stav11ActionPerformed(evt);
            }
        });
        TextField_pretazenia_stav11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_pretazenia_stav11KeyReleased(evt);
            }
        });

        TextField_pretazenia_stav12.setText("1.0");
        TextField_pretazenia_stav12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_pretazenia_stav12ActionPerformed(evt);
            }
        });
        TextField_pretazenia_stav12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_pretazenia_stav12KeyReleased(evt);
            }
        });

        TextField_pretazenia_stav13.setText("1.0");
        TextField_pretazenia_stav13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_pretazenia_stav13ActionPerformed(evt);
            }
        });
        TextField_pretazenia_stav13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_pretazenia_stav13KeyReleased(evt);
            }
        });

        TextField_pretazenia_stav14.setText("1.0");
        TextField_pretazenia_stav14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_pretazenia_stav14ActionPerformed(evt);
            }
        });
        TextField_pretazenia_stav14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_pretazenia_stav14KeyReleased(evt);
            }
        });

        buttonGroup_pretazenia_vlystne_vypocitane.add(jRadioButton_with_pretazenia_vypocitana);
        jRadioButton_with_pretazenia_vypocitana.setText(language.language_label(languageOption, 159));
        jRadioButton_with_pretazenia_vypocitana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_with_pretazenia_vypocitanaActionPerformed(evt);
            }
        });

        buttonGroup_pretazenia_vlystne_vypocitane.add(jRadioButton_with_pretazenia_vlastna);
        jRadioButton_with_pretazenia_vlastna.setText(language.language_label(languageOption, 160));
        jRadioButton_with_pretazenia_vlastna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_with_pretazenia_vlastnaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_pretazenia)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(TextField_pretazenia_stav1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_pretazenia_stav2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jRadioButton_with_pretazenia_vypocitana)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton_with_pretazenia_vlastna)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(TextField_pretazenia_stav3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_pretazenia_stav4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_pretazenia_stav5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_pretazenia_stav6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_pretazenia_stav7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_pretazenia_stav8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_pretazenia_stav9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_pretazenia_stav10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_pretazenia_stav11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_pretazenia_stav12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_pretazenia_stav13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_pretazenia_stav14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(Label_pretazenia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextField_pretazenia_stav1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_pretazenia_stav2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_pretazenia_stav3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_pretazenia_stav4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_pretazenia_stav5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_pretazenia_stav6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_pretazenia_stav7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_pretazenia_stav8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_pretazenia_stav9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_pretazenia_stav10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_pretazenia_stav11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_pretazenia_stav12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_pretazenia_stav13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_pretazenia_stav14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_with_pretazenia_vypocitana)
                    .addComponent(jRadioButton_with_pretazenia_vlastna))
                .addGap(224, 224, 224))
        );

        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        Label_tabulky.setText(language.language_label(languageOption, 97));

        Label_tabulky1.setText(language.language_label(languageOption, 101));

        buttonGroup_tabulka.add(jRadioButton_with_label_pociatocne);
        jRadioButton_with_label_pociatocne.setText(language.language_label(languageOption, 98));
        jRadioButton_with_label_pociatocne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_with_label_pociatocneActionPerformed(evt);
            }
        });

        buttonGroup_tabulka.add(jRadioButton_with_label_prechodne);
        jRadioButton_with_label_prechodne.setText(language.language_label(languageOption, 99));
        jRadioButton_with_label_prechodne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_with_label_prechodneActionPerformed(evt);
            }
        });

        buttonGroup_tabulka.add(jRadioButton_with_label_konecne);
        jRadioButton_with_label_konecne.setText(language.language_label(languageOption, 100));
        jRadioButton_with_label_konecne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_with_label_konecneActionPerformed(evt);
            }
        });

        TextField_tabulky_prechodna.setText("1.0");
        TextField_tabulky_prechodna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_tabulky_prechodnaActionPerformed(evt);
            }
        });

        TextField_tabulky_konecna.setText("50.0");
        TextField_tabulky_konecna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_tabulky_konecnaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jRadioButton_with_label_pociatocne)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jRadioButton_with_label_prechodne)
                                    .addComponent(jRadioButton_with_label_konecne))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(TextField_tabulky_prechodna, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TextField_tabulky_konecna, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(Label_tabulky, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Label_tabulky1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_tabulky)
                    .addComponent(Label_tabulky1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton_with_label_pociatocne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_with_label_prechodne)
                    .addComponent(TextField_tabulky_prechodna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_with_label_konecne)
                    .addComponent(TextField_tabulky_konecna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        Label_vybrana_namrazova_oblast.setText(namrazove_oblasti_názov_oblasti);

        Button_namrazova_oblast.setText(language.language_label(languageOption, 67));
        Button_namrazova_oblast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_namrazova_oblastActionPerformed(evt);
            }
        });

        Label__Klc.setText(language.language_label(languageOption, 102));
        Label__Klc.setToolTipText(language.language_label(languageOption, 103));

        buttonGroup_klc.add(jRadioButton_Klc_1);
        jRadioButton_Klc_1.setText("1");
        jRadioButton_Klc_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Klc_1ActionPerformed(evt);
            }
        });

        TextField_Kcl.setText("1.0");
        TextField_Kcl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_KclKeyReleased(evt);
            }
        });

        buttonGroup_klc.add(jRadioButton_Klc_vlastna);
        jRadioButton_Klc_vlastna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Klc_vlastnaActionPerformed(evt);
            }
        });

        Label__Kh.setText(language.language_label(languageOption, 104));
        Label__Kh.setToolTipText(language.language_label(languageOption, 105));

        buttonGroup_kh.add(jRadioButton_Kh_1);
        jRadioButton_Kh_1.setText("1");
        jRadioButton_Kh_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Kh_1ActionPerformed(evt);
            }
        });

        buttonGroup_kh.add(jRadioButton_Kh_vlastna);
        jRadioButton_Kh_vlastna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Kh_vlastnaActionPerformed(evt);
            }
        });

        TextField_Kh.setText("1.0");
        TextField_Kh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_KhKeyReleased(evt);
            }
        });

        Label__Bi.setText(language.language_label(languageOption, 106));
        Label__Bi.setToolTipText(language.language_label(languageOption, 107));

        buttonGroup_Bi.add(jRadioButton_Bi_1);
        jRadioButton_Bi_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Bi_1ActionPerformed(evt);
            }
        });

        TextField_Bi_1.setEditable(false);
        TextField_Bi_1.setText("0.656");
        TextField_Bi_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_Bi_1ActionPerformed(evt);
            }
        });
        TextField_Bi_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_Bi_1KeyReleased(evt);
            }
        });

        buttonGroup_Bi.add(jRadioButton_Bi_2);
        jRadioButton_Bi_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Bi_2ActionPerformed(evt);
            }
        });

        TextField_Bi2.setEditable(false);
        TextField_Bi2.setText("0.707");
        TextField_Bi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_Bi2KeyReleased(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_vybrana_namrazova_oblast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Label__Klc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Label__Kh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Label__Bi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Label_typ_namrazy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(Label__typ_namrazy_Ccl)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Button_namrazova_oblast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox_druh_namrazy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(TextField_Ccl, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Label_hustota_namrazy, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TextField_hustota_namrazy, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Label_RTS_velicina1))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jRadioButton_Klc_1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton_Klc_vlastna)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_Kcl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jRadioButton_Kh_1)
                                        .addGap(4, 4, 4)
                                        .addComponent(jRadioButton_Kh_vlastna))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jRadioButton_Bi_1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TextField_Bi_1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextField_Kh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jRadioButton_Bi_2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TextField_Bi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_vybrana_namrazova_oblast)
                    .addComponent(Button_namrazova_oblast))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextField_Kcl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_Klc_vlastna)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton_Klc_1)
                        .addComponent(Label__Klc)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton_Kh_1)
                        .addComponent(Label__Kh))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(TextField_Kh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton_Kh_vlastna)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(TextField_Bi_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Label__Bi)
                                .addComponent(jRadioButton_Bi_1, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jRadioButton_Bi_2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_Bi2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_druh_namrazy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_typ_namrazy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label__typ_namrazy_Ccl)
                    .addComponent(TextField_Ccl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_hustota_namrazy)
                    .addComponent(TextField_hustota_namrazy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_RTS_velicina1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        Label_stredne_rozpatie.setText(language.language_label(languageOption, 154));

        buttonGroup_stredne_rozpatia.add(jRadioButton_with_label_rozpatie_klasicky);
        jRadioButton_with_label_rozpatie_klasicky.setText(language.language_label(languageOption, 155));
        jRadioButton_with_label_rozpatie_klasicky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_with_label_rozpatie_klasickyActionPerformed(evt);
            }
        });

        buttonGroup_stredne_rozpatia.add(jRadioButton_with_label_rozpate_previsenia);
        jRadioButton_with_label_rozpate_previsenia.setText(language.language_label(languageOption, 156));
        jRadioButton_with_label_rozpate_previsenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_with_label_rozpate_previseniaActionPerformed(evt);
            }
        });

        TextField_STRrozpatie_klasicky.setEditable(false);
        TextField_STRrozpatie_klasicky.setText("0.0");

        TextField_STRrozpatie_sPrevisenim.setEditable(false);
        TextField_STRrozpatie_sPrevisenim.setText("0.0");
        TextField_STRrozpatie_sPrevisenim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_STRrozpatie_sPrevisenimActionPerformed(evt);
            }
        });
        TextField_STRrozpatie_sPrevisenim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_STRrozpatie_sPrevisenimKeyReleased(evt);
            }
        });

        Label_RTS_velicina5.setText("m");

        Label_RTS_velicina6.setText("m");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jRadioButton_with_label_rozpatie_klasicky, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TextField_STRrozpatie_klasicky, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextField_STRrozpatie_sPrevisenim, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_RTS_velicina6)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(Label_RTS_velicina5)
                                .addContainerGap())))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(Label_stredne_rozpatie)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jRadioButton_with_label_rozpate_previsenia, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(Label_stredne_rozpatie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextField_STRrozpatie_klasicky, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_RTS_velicina5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextField_STRrozpatie_sPrevisenim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_RTS_velicina6))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jRadioButton_with_label_rozpatie_klasicky)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton_with_label_rozpate_previsenia)
                        .addContainerGap())))
        );

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

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                    .addComponent(Label_kotevne_useky))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Label_kotevne_useky)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        buttonGroup1.add(jRadioButton_with_label_vlastna);
        jRadioButton_with_label_vlastna.setText(language.language_label(languageOption, 96));
        jRadioButton_with_label_vlastna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_with_label_vlastnaActionPerformed(evt);
            }
        });

        TextField_hcmean_vlastna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_hcmean_vlastnaActionPerformed(evt);
            }
        });
        TextField_hcmean_vlastna.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_hcmean_vlastnaKeyReleased(evt);
            }
        });

        TextField_hcmean_vpocitana.setEditable(false);
        TextField_hcmean_vpocitana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_hcmean_vpocitanaActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton_with_label_vypoctana);
        jRadioButton_with_label_vypoctana.setText(language.language_label(languageOption, 95));
        jRadioButton_with_label_vypoctana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_with_label_vypoctanaActionPerformed(evt);
            }
        });

        Label_stredna_vyska_vodicov_nad_terenom.setText(language.language_label(languageOption, 94));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(Label_stredna_vyska_vodicov_nad_terenom, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jRadioButton_with_label_vypoctana, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TextField_hcmean_vpocitana, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton_with_label_vlastna, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_hcmean_vlastna, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 116, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_stredna_vyska_vodicov_nad_terenom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_with_label_vypoctana)
                    .addComponent(TextField_hcmean_vpocitana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_with_label_vlastna)
                    .addComponent(TextField_hcmean_vlastna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

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

        Label_RTS_velicina.setText("MPa");

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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox_conductor_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(Label_kotevne_useky1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Label_RTS_velicina3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Label_RTS, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(Label_zakladne_mech_napatie_minis5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Label_max_zataz_lana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TextField_max_mech_podiel_z_RTS, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                    .addComponent(TextField_zakladne_mech_lana_minus5))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(TextField_RTS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Label_RTS_velicina)
                            .addComponent(Label_RTS_velicina2))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(Label_kotevne_useky1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_conductor_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextField_RTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_RTS_velicina)
                    .addComponent(Label_RTS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_zakladne_mech_napatie_minis5)
                    .addComponent(TextField_zakladne_mech_lana_minus5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_RTS_velicina2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_max_zataz_lana)
                    .addComponent(TextField_max_mech_podiel_z_RTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_RTS_velicina3))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        Label_uroven_spolahlivosti.setText(language.language_label(languageOption, 140));

        jComboBox_uroven_splahlivosti.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_uroven_splahlivosti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_uroven_splahlivostiActionPerformed(evt);
            }
        });

        Label__stredna_rocna_teplota.setText(language.language_label(languageOption, 161));

        TextField_srt_roc_teplota.setText("10.0");
        TextField_srt_roc_teplota.setEnabled(false);
        TextField_srt_roc_teplota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_srt_roc_teplotaActionPerformed(evt);
            }
        });
        TextField_srt_roc_teplota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_srt_roc_teplotaKeyReleased(evt);
            }
        });

        Label_RTS_velicina7.setText("°C");

        Label__stav_KPB.setText(language.language_label(languageOption, 162));

        jComboBox_stav_KPB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_stav_KPB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_stav_KPBItemStateChanged(evt);
            }
        });
        jComboBox_stav_KPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_stav_KPBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label__stredna_rocna_teplota, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label__stav_KPB, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(TextField_srt_roc_teplota, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Label_RTS_velicina7))
                            .addComponent(jComboBox_stav_KPB, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Label_uroven_spolahlivosti, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_uroven_splahlivosti, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(Label_uroven_spolahlivosti)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_uroven_splahlivosti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label__stredna_rocna_teplota)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextField_srt_roc_teplota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Label_RTS_velicina7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label__stav_KPB)
                    .addComponent(jComboBox_stav_KPB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        Label_KPB.setText(language.language_label(languageOption, 163));

        Label_KPB_typ_terenu.setText(language.language_label(languageOption, 164));

        jComboBox_KPB_typ_terenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_KPB_typ_terenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_KPB_typ_terenuActionPerformed(evt);
            }
        });

        jTextPane_KPB_typ_terenu.setColumns(20);
        jTextPane_KPB_typ_terenu.setLineWrap(true);
        jTextPane_KPB_typ_terenu.setRows(5);
        jTextPane_KPB_typ_terenu.setWrapStyleWord(true);
        jScrollPane6.setViewportView(jTextPane_KPB_typ_terenu);

        buttonGroup_KPB_cas_vypoctu.add(jRadioButton_KPB_cas_vypoctu_1_rok);
        jRadioButton_KPB_cas_vypoctu_1_rok.setText(language.language_label(languageOption, 173));
        jRadioButton_KPB_cas_vypoctu_1_rok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_KPB_cas_vypoctu_1_rokActionPerformed(evt);
            }
        });

        buttonGroup_KPB_cas_vypoctu.add(jRadioButton_KPB_cas_vypoctu_prechodne);
        jRadioButton_KPB_cas_vypoctu_prechodne.setText(language.language_label(languageOption, 174));
        jRadioButton_KPB_cas_vypoctu_prechodne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_KPB_cas_vypoctu_prechodneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(Label_KPB_typ_terenu, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_KPB_typ_terenu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Label_KPB)
                    .addComponent(jRadioButton_KPB_cas_vypoctu_1_rok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton_KPB_cas_vypoctu_prechodne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Label_KPB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_KPB_typ_terenu)
                            .addComponent(jComboBox_KPB_typ_terenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton_KPB_cas_vypoctu_1_rok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton_KPB_cas_vypoctu_prechodne)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        Label_vetrova_oblast.setText(language.language_label(languageOption, 108));

        jComboBox_vetrova_oblast.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_vetrova_oblast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_vetrova_oblastActionPerformed(evt);
            }
        });

        Label_základna_rychlost_vetra.setText(language.language_label(languageOption, 124));
        Label_základna_rychlost_vetra.setToolTipText(language.language_label(languageOption, 109));

        TextField_Vmean_0.setEnabled(false);
        TextField_Vmean_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_Vmean_0ActionPerformed(evt);
            }
        });
        TextField_Vmean_0.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_Vmean_0KeyReleased(evt);
            }
        });

        Label_RTS_velicina4.setText("m/s");

        Label_vetrova_oblast_Cdir.setText(language.language_label(languageOption, 110));
        Label_vetrova_oblast_Cdir.setToolTipText(language.language_label(languageOption, 111));

        buttonGroup_Cdir.add(jRadioButton_vetrova_oblast_Cdir_1);
        jRadioButton_vetrova_oblast_Cdir_1.setText("1");
        jRadioButton_vetrova_oblast_Cdir_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_vetrova_oblast_Cdir_1ActionPerformed(evt);
            }
        });

        buttonGroup_Cdir.add(jRadioButton_vetrova_oblast_Cdir_vlastna);
        jRadioButton_vetrova_oblast_Cdir_vlastna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_vetrova_oblast_Cdir_vlastnaActionPerformed(evt);
            }
        });

        TextField_vetrova_oblast_Cdir.setText("1.0");
        TextField_vetrova_oblast_Cdir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_vetrova_oblast_CdirActionPerformed(evt);
            }
        });
        TextField_vetrova_oblast_Cdir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_vetrova_oblast_CdirKeyReleased(evt);
            }
        });

        Label_vetrova_oblast_C0.setText(language.language_label(languageOption, 112));
        Label_vetrova_oblast_C0.setToolTipText(language.language_label(languageOption, 113));

        buttonGroup_C0.add(jRadioButton_vetrova_oblast_C0_1);
        jRadioButton_vetrova_oblast_C0_1.setText("1");
        jRadioButton_vetrova_oblast_C0_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_vetrova_oblast_C0_1ActionPerformed(evt);
            }
        });

        buttonGroup_C0.add(jRadioButton_vetrova_oblast_C0_vlastna);
        jRadioButton_vetrova_oblast_C0_vlastna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_vetrova_oblast_C0_vlastnaActionPerformed(evt);
            }
        });

        TextField_vetrova_oblast_C0.setText("1.0");
        TextField_vetrova_oblast_C0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_vetrova_oblast_C0ActionPerformed(evt);
            }
        });
        TextField_vetrova_oblast_C0.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_vetrova_oblast_C0KeyReleased(evt);
            }
        });

        Label_char_terenu.setText(language.language_label(languageOption, 125));

        jComboBox_char_terenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_char_terenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_char_terenuActionPerformed(evt);
            }
        });

        jTextPane_char_terenu.setColumns(20);
        jTextPane_char_terenu.setLineWrap(true);
        jTextPane_char_terenu.setRows(5);
        jScrollPane5.setViewportView(jTextPane_char_terenu);

        Label__char_terenu_kr.setText(language.language_label(languageOption, 126));
        Label__char_terenu_kr.setToolTipText(language.language_label(languageOption, 128));

        TextField_Kr.setEnabled(false);
        TextField_Kr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_KrActionPerformed(evt);
            }
        });
        TextField_Kr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_KrKeyReleased(evt);
            }
        });

        Label_char_terenu_zo.setText(language.language_label(languageOption, 127));
        Label_char_terenu_zo.setToolTipText(language.language_label(languageOption, 129));

        TextField_dlzka_drsnjosti_zo.setEnabled(false);
        TextField_dlzka_drsnjosti_zo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_dlzka_drsnjosti_zoActionPerformed(evt);
            }
        });
        TextField_dlzka_drsnjosti_zo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_dlzka_drsnjosti_zoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane5)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(Label_vetrova_oblast)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_vetrova_oblast, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextField_Vmean_0, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jRadioButton_vetrova_oblast_Cdir_1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton_vetrova_oblast_Cdir_vlastna))
                            .addComponent(Label_char_terenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_char_terenu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Label_základna_rychlost_vetra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Label_vetrova_oblast_Cdir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(90, 90, 90)
                                        .addComponent(Label_RTS_velicina4))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addComponent(TextField_vetrova_oblast_Cdir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Label_vetrova_oblast_C0, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRadioButton_vetrova_oblast_C0_1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jRadioButton_vetrova_oblast_C0_vlastna)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TextField_vetrova_oblast_C0, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(Label__char_terenu_kr)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_Kr, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Label_char_terenu_zo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(TextField_dlzka_drsnjosti_zo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_vetrova_oblast)
                    .addComponent(jComboBox_vetrova_oblast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_základna_rychlost_vetra)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextField_Vmean_0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Label_RTS_velicina4)))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton_vetrova_oblast_Cdir_1)
                        .addComponent(Label_vetrova_oblast_Cdir))
                    .addComponent(jRadioButton_vetrova_oblast_Cdir_vlastna)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextField_vetrova_oblast_Cdir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton_vetrova_oblast_C0_1)
                        .addComponent(Label_vetrova_oblast_C0, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButton_vetrova_oblast_C0_vlastna)
                    .addComponent(TextField_vetrova_oblast_C0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_char_terenu)
                    .addComponent(jComboBox_char_terenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label__char_terenu_kr)
                    .addComponent(TextField_Kr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_char_terenu_zo)
                    .addComponent(TextField_dlzka_drsnjosti_zo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        Label_hlavicka_nazov.setText(language.language_label(languageOption, 175));

        Label_hlavicka_vypocet_podla_normi.setText(language.language_label(languageOption, 176));

        jTextField_nazov_normi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nazov_normiActionPerformed(evt);
            }
        });

        jTextField_nadpis_pre_prechodna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nadpis_pre_prechodnaActionPerformed(evt);
            }
        });

        Label_hlavicka_nadpis_pre_prechodne.setText(language.language_label(languageOption, 177));

        Label_hlavicka_stavba.setText(language.language_label(languageOption, 178));

        jTextField_nazov_nazov_stavby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nazov_nazov_stavbyActionPerformed(evt);
            }
        });

        Label_hlavicka_SOPS.setText(language.language_label(languageOption, 179));

        jTextField_nazov_SOPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nazov_SOPSActionPerformed(evt);
            }
        });

        jTextField_nazov_arch_cislo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nazov_arch_cisloActionPerformed(evt);
            }
        });

        Label_hlavicka_arch_cislo.setText(language.language_label(languageOption, 180));

        Label_hlavicka_cislo_strany.setText(language.language_label(languageOption, 181));

        jTextField_nazov_cislo_strany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nazov_cislo_stranyActionPerformed(evt);
            }
        });

        Label_hlavicka_vypracoval.setText(language.language_label(languageOption, 182));

        jTextField_vypracoval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_vypracovalActionPerformed(evt);
            }
        });

        jTextField_datum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_datumActionPerformed(evt);
            }
        });

        Label_hlavicka_datum.setText(language.language_label(languageOption, 183));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Label_hlavicka_nazov, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(Label_hlavicka_vypocet_podla_normi, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_nazov_normi, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Label_hlavicka_nadpis_pre_prechodne, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_nadpis_pre_prechodna, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(Label_hlavicka_stavba, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_nazov_nazov_stavby))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(Label_hlavicka_SOPS, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_nazov_SOPS))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_hlavicka_arch_cislo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_hlavicka_vypracoval))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jTextField_nazov_arch_cislo, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Label_hlavicka_cislo_strany, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_nazov_cislo_strany, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jTextField_vypracoval, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(Label_hlavicka_datum)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_datum)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_hlavicka_nazov)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_hlavicka_vypocet_podla_normi)
                    .addComponent(jTextField_nazov_normi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_hlavicka_nadpis_pre_prechodne)
                    .addComponent(jTextField_nadpis_pre_prechodna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_hlavicka_stavba)
                    .addComponent(jTextField_nazov_nazov_stavby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_hlavicka_SOPS)
                    .addComponent(jTextField_nazov_SOPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_hlavicka_arch_cislo)
                    .addComponent(jTextField_nazov_arch_cislo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_hlavicka_cislo_strany)
                    .addComponent(jTextField_nazov_cislo_strany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_hlavicka_vypracoval)
                    .addComponent(jTextField_vypracoval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Label_hlavicka_datum)
                        .addComponent(jTextField_datum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Table_rozpatia_nadm_vyskyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table_rozpatia_nadm_vyskyKeyReleased
        
    }//GEN-LAST:event_Table_rozpatia_nadm_vyskyKeyReleased
    private void Table_rozpatiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table_rozpatiaKeyReleased
        
    }//GEN-LAST:event_Table_rozpatiaKeyReleased

    private void jRadioButton_with_label_vlastnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_label_vlastnaActionPerformed
        TextField_hcmean_vlastna.setEnabled(true);
        Variable_Hc_mean=doubleChecker_short_answer(TextField_hcmean_vlastna);
        
    }//GEN-LAST:event_jRadioButton_with_label_vlastnaActionPerformed

    private void jRadioButton_with_label_vypoctanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_label_vypoctanaActionPerformed
        TextField_hcmean_vlastna.setEnabled(false);
        Variable_Hc_mean=Variable_Hc_mean_medzikrok;
    }//GEN-LAST:event_jRadioButton_with_label_vypoctanaActionPerformed

    private void TextField_hcmean_vlastnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_hcmean_vlastnaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_hcmean_vlastnaActionPerformed
    private void TextField_hcmean_vlastnaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_hcmean_vlastnaKeyReleased
        
        if(TextField_hcmean_vlastna.isEditable() == true){
          if(TextField_hcmean_vlastna.getText().equals("-"))TextField_hcmean_vlastna.setText("");
            
            Variable_Hc_mean=doubleChecker_short_answer(TextField_hcmean_vlastna);           
        }
        
    }//GEN-LAST:event_TextField_hcmean_vlastnaKeyReleased

    private void jRadioButton_with_label_pociatocneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_label_pociatocneActionPerformed
       Variable_T0_zivotnost=doubleChecker_short_answer(TextField_tabulky_konecna)*24*365;
       Variable_Tp_prechodna_doba=0.0;
       
        TextField_teploha_stav1.setText("-10");TextField_teploha_stav1.setEnabled(true);
         TextField_teploha_stav2.setText("-5");TextField_teploha_stav2.setEnabled(false);
         TextField_teploha_stav3.setText("0");TextField_teploha_stav3.setEnabled(false);
         TextField_teploha_stav4.setText("5");TextField_teploha_stav4.setEnabled(false);
         TextField_teploha_stav5.setText("10");TextField_teploha_stav5.setEnabled(false);
         TextField_teploha_stav6.setText("15");TextField_teploha_stav6.setEnabled(false);
         TextField_teploha_stav7.setText("17");TextField_teploha_stav7.setEnabled(true);
         TextField_teploha_stav8.setText("20");TextField_teploha_stav8.setEnabled(false);
         TextField_teploha_stav9.setText("22");TextField_teploha_stav9.setEnabled(true);
         TextField_teploha_stav10.setText("25");TextField_teploha_stav10.setEnabled(true);
         TextField_teploha_stav11.setText("27");TextField_teploha_stav11.setEnabled(true);
         TextField_teploha_stav12.setText("30");TextField_teploha_stav12.setEnabled(true);
         TextField_teploha_stav13.setText("35");TextField_teploha_stav13.setEnabled(true);
         TextField_teploha_stav14.setText("40");TextField_teploha_stav14.setEnabled(true);
       
         array_teploty_stav_rovnica_pociatocne_loader_setter();
         Jcombo_stav_KPB_setter();
         teplotyser=true;
    }//GEN-LAST:event_jRadioButton_with_label_pociatocneActionPerformed

    private void jRadioButton_with_label_prechodneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_label_prechodneActionPerformed
         Variable_T0_zivotnost=doubleChecker_short_answer(TextField_tabulky_konecna)*24*365;
       Variable_Tp_prechodna_doba=doubleChecker_short_answer(TextField_tabulky_prechodna)*24*365;
       
         TextField_teploha_stav1.setText("-30");TextField_teploha_stav1.setEnabled(false);
         TextField_teploha_stav2.setText("-20");TextField_teploha_stav2.setEnabled(true);
         TextField_teploha_stav3.setText("-10");TextField_teploha_stav3.setEnabled(true);
         TextField_teploha_stav4.setText("-5");TextField_teploha_stav4.setEnabled(false);
         TextField_teploha_stav5.setText("-5+N");TextField_teploha_stav5.setEnabled(false);
         TextField_teploha_stav6.setText("-5+V");TextField_teploha_stav6.setEnabled(false);
         TextField_teploha_stav7.setText("-5+Nv");TextField_teploha_stav7.setEnabled(false);
         TextField_teploha_stav8.setText("-5+vn");TextField_teploha_stav8.setEnabled(false);
         TextField_teploha_stav9.setText("0");TextField_teploha_stav9.setEnabled(false);
         TextField_teploha_stav10.setText("10");TextField_teploha_stav10.setEnabled(true);
         TextField_teploha_stav11.setText("20");TextField_teploha_stav11.setEnabled(true);
         TextField_teploha_stav12.setText("40");TextField_teploha_stav12.setEnabled(true);
         TextField_teploha_stav13.setText("60");TextField_teploha_stav13.setEnabled(true);
         TextField_teploha_stav14.setText("80");TextField_teploha_stav14.setEnabled(false);
         
         array_teploty_stav_rovnica_konecne_loader_setter();
         
         Jcombo_stav_KPB_setter();
         teplotyser=true;
    }//GEN-LAST:event_jRadioButton_with_label_prechodneActionPerformed

    private void jRadioButton_with_label_konecneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_label_konecneActionPerformed
         Variable_T0_zivotnost=doubleChecker_short_answer(TextField_tabulky_konecna)*24*365;
         Variable_Tp_prechodna_doba=doubleChecker_short_answer(TextField_tabulky_konecna)*24*365;
        
         TextField_teploha_stav1.setText("-30");TextField_teploha_stav1.setEnabled(false);
         TextField_teploha_stav2.setText("-20");TextField_teploha_stav2.setEnabled(true);
         TextField_teploha_stav3.setText("-10");TextField_teploha_stav3.setEnabled(true);
         TextField_teploha_stav4.setText("-5");TextField_teploha_stav4.setEnabled(false);
         TextField_teploha_stav5.setText("-5+N");TextField_teploha_stav5.setEnabled(false);
         TextField_teploha_stav6.setText("-5+V");TextField_teploha_stav6.setEnabled(false);
         TextField_teploha_stav7.setText("-5+Nv");TextField_teploha_stav7.setEnabled(false);
         TextField_teploha_stav8.setText("-5+vn");TextField_teploha_stav8.setEnabled(false);
         TextField_teploha_stav9.setText("0");TextField_teploha_stav9.setEnabled(false);
         TextField_teploha_stav10.setText("10");TextField_teploha_stav10.setEnabled(true);
         TextField_teploha_stav11.setText("20");TextField_teploha_stav11.setEnabled(true);
         TextField_teploha_stav12.setText("40");TextField_teploha_stav12.setEnabled(true);
         TextField_teploha_stav13.setText("60");TextField_teploha_stav13.setEnabled(true);
         TextField_teploha_stav14.setText("80");TextField_teploha_stav14.setEnabled(false);
         
         array_teploty_stav_rovnica_konecne_loader_setter();
         
        Jcombo_stav_KPB_setter();

        if (teplotyser == false){ // vloz prvu hodnotu
        Variable_vybrany_stav_pre_KPB=Variable_teploty_stav_rovnica[0];
        }
        teplotyser=true;
    }//GEN-LAST:event_jRadioButton_with_label_konecneActionPerformed
    private void TextField_tabulky_konecnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_tabulky_konecnaActionPerformed
       
    }//GEN-LAST:event_TextField_tabulky_konecnaActionPerformed

    private void TextField_tabulky_prechodnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_tabulky_prechodnaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_tabulky_prechodnaActionPerformed

    private void jComboBox_vetrova_oblastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_vetrova_oblastActionPerformed
        if (mainframeLodaed == true) {

            int selected_index_from_JComboBox = jComboBox_vetrova_oblast.getSelectedIndex();

            switch (selected_index_from_JComboBox) {
                case 0:
                    TextField_Vmean_0.setEnabled(false);
                    Variable_V_mean_0 = 24;
                    break;
                case 1:
                    TextField_Vmean_0.setEnabled(false);
                    Variable_V_mean_0 = 26;
                    break;
                case 2:
                    TextField_Vmean_0.setEnabled(false);
                    Variable_V_mean_0 = 30;
                    break;
                case 3:
                    TextField_Vmean_0.setEnabled(false);
                    Variable_V_mean_0 = 33;
                    break;
                case 4:
                    TextField_Vmean_0.setEnabled(false);
                    Variable_V_mean_0 = 22.5;
                    break;
                case 5:
                    TextField_Vmean_0.setEnabled(false);
                    Variable_V_mean_0 = 25;
                    break;
                case 6:
                    TextField_Vmean_0.setEnabled(false);
                    Variable_V_mean_0 = 27.5;
                    break;
                case 7:
                    TextField_Vmean_0.setEnabled(false);
                    Variable_V_mean_0 = 30;
                    break;
                case 8:
                    TextField_Vmean_0.setEnabled(false);
                    Variable_V_mean_0 = 36;
                    break;

                case 9:
                    TextField_Vmean_0.setEnabled(true);                  
                    Variable_V_mean_0 = 24;             
                    TextField_Vmean_0.setForeground(Color.black);
                    break;
            }
            TextField_Vmean_0.setText(String.valueOf(Variable_V_mean_0));

        }
    }//GEN-LAST:event_jComboBox_vetrova_oblastActionPerformed

    private void TextField_Vmean_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_Vmean_0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_Vmean_0ActionPerformed

    private void TextField_Vmean_0KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_Vmean_0KeyReleased
        Variable_V_mean_0=doubleChecker_short_answer(TextField_Vmean_0);
    }//GEN-LAST:event_TextField_Vmean_0KeyReleased

    private void jRadioButton_vetrova_oblast_Cdir_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_vetrova_oblast_Cdir_1ActionPerformed
        Variable_Cdir=1.0;
        TextField_vetrova_oblast_Cdir.setEnabled(false);        
    }//GEN-LAST:event_jRadioButton_vetrova_oblast_Cdir_1ActionPerformed

    private void jRadioButton_vetrova_oblast_Cdir_vlastnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_vetrova_oblast_Cdir_vlastnaActionPerformed
        TextField_vetrova_oblast_Cdir.setEnabled(true);
        Variable_Cdir=doubleChecker_short_answer(TextField_vetrova_oblast_Cdir);
    }//GEN-LAST:event_jRadioButton_vetrova_oblast_Cdir_vlastnaActionPerformed

    private void TextField_vetrova_oblast_CdirKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_vetrova_oblast_CdirKeyReleased
        Variable_Cdir=doubleChecker_short_answer(TextField_vetrova_oblast_Cdir);
    }//GEN-LAST:event_TextField_vetrova_oblast_CdirKeyReleased

    private void TextField_vetrova_oblast_CdirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_vetrova_oblast_CdirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_vetrova_oblast_CdirActionPerformed

    private void TextField_vetrova_oblast_C0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_vetrova_oblast_C0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_vetrova_oblast_C0ActionPerformed

    private void TextField_vetrova_oblast_C0KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_vetrova_oblast_C0KeyReleased
        Variable_Co=doubleChecker_short_answer(TextField_vetrova_oblast_C0);
    }//GEN-LAST:event_TextField_vetrova_oblast_C0KeyReleased

    private void jRadioButton_vetrova_oblast_C0_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_vetrova_oblast_C0_1ActionPerformed
         Variable_Co=1.0;
        TextField_vetrova_oblast_C0.setEnabled(false); 
    }//GEN-LAST:event_jRadioButton_vetrova_oblast_C0_1ActionPerformed

    private void jRadioButton_vetrova_oblast_C0_vlastnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_vetrova_oblast_C0_vlastnaActionPerformed
        TextField_vetrova_oblast_C0.setEnabled(true);
        Variable_Co=doubleChecker_short_answer(TextField_vetrova_oblast_C0);
    }//GEN-LAST:event_jRadioButton_vetrova_oblast_C0_vlastnaActionPerformed

    private void TextField_dlzka_drsnjosti_zoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_dlzka_drsnjosti_zoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_dlzka_drsnjosti_zoActionPerformed

    private void TextField_dlzka_drsnjosti_zoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_dlzka_drsnjosti_zoKeyReleased
       Variable_char_terenu_Kr=doubleChecker_short_answer(TextField_dlzka_drsnjosti_zo);
    }//GEN-LAST:event_TextField_dlzka_drsnjosti_zoKeyReleased

    private void TextField_KrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_KrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_KrActionPerformed

    private void TextField_KrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_KrKeyReleased
        Variable_char_terenu_Kr=doubleChecker_short_answer(TextField_Kr);
    }//GEN-LAST:event_TextField_KrKeyReleased
    private void jComboBox_char_terenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_char_terenuActionPerformed
     
         if (mainframeLodaed == true) {
            
          int selected_index_from_JComboBox = jComboBox_char_terenu.getSelectedIndex();
          
          switch (selected_index_from_JComboBox) {
            case 0:  TextField_Kr.setEnabled(false);
                     TextField_dlzka_drsnjosti_zo.setEnabled(false);
                     Variable_char_terenu_Kr = 0.169;
                     Variable_char_terenu_Zo= 0.01;
                     jTextPane_char_terenu.setText(language.language_label(languageOption, 135));
                     break;
            case 1:  TextField_Kr.setEnabled(false);
                     TextField_dlzka_drsnjosti_zo.setEnabled(false);
                     Variable_char_terenu_Kr = 0.189;
                     Variable_char_terenu_Zo= 0.05;
                     jTextPane_char_terenu.setText(language.language_label(languageOption, 136));
                     break;
            case 2:  TextField_Kr.setEnabled(false);
                     TextField_dlzka_drsnjosti_zo.setEnabled(false);
                     Variable_char_terenu_Kr = 0.214;
                     Variable_char_terenu_Zo= 0.3;
                     jTextPane_char_terenu.setText(language.language_label(languageOption, 137));
                     break;
            case 3:  TextField_Kr.setEnabled(false);
                     TextField_dlzka_drsnjosti_zo.setEnabled(false);
                     Variable_char_terenu_Kr = 0.233;
                     Variable_char_terenu_Zo= 1;
                     jTextPane_char_terenu.setText(language.language_label(languageOption, 139));
                     break;
            case 4:  TextField_Kr.setEnabled(true);
                     TextField_dlzka_drsnjosti_zo.setEnabled(true);
                     Variable_char_terenu_Kr = 0.169;
                     Variable_char_terenu_Zo= 0.01;
                     TextField_Kr.setForeground(Color.black);
                     TextField_dlzka_drsnjosti_zo.setForeground(Color.black);
                     jTextPane_char_terenu.setText(language.language_label(languageOption, 139));
                     break;                    
        }
            TextField_Kr.setText(String.valueOf(Variable_char_terenu_Kr));
            TextField_dlzka_drsnjosti_zo.setText(String.valueOf(Variable_char_terenu_Zo));
            
        }
        
    }//GEN-LAST:event_jComboBox_char_terenuActionPerformed

    private void jRadioButton_with_label_rozpatie_klasickyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_label_rozpatie_klasickyActionPerformed
       Variable_mid_span=Variable_mid_span_docasna;
    }//GEN-LAST:event_jRadioButton_with_label_rozpatie_klasickyActionPerformed

    private void jRadioButton_with_label_rozpate_previseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_label_rozpate_previseniaActionPerformed
       Variable_mid_span=Variable_mid_span_terrain_docasna;
    }//GEN-LAST:event_jRadioButton_with_label_rozpate_previseniaActionPerformed

    private void TextField_STRrozpatie_sPrevisenimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_STRrozpatie_sPrevisenimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_STRrozpatie_sPrevisenimActionPerformed

    private void TextField_STRrozpatie_sPrevisenimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_STRrozpatie_sPrevisenimKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_STRrozpatie_sPrevisenimKeyReleased

    private void TextField_hcmean_vpocitanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_hcmean_vpocitanaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_hcmean_vpocitanaActionPerformed

    private void Button_Icon_deselect_all_kotevny_usekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_deselect_all_kotevny_usekActionPerformed
        int rowCount = Table_kotevne_useky.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            Table_kotevne_useky.setValueAt(false, i, 0);
        }
    }//GEN-LAST:event_Button_Icon_deselect_all_kotevny_usekActionPerformed

    private void Button_Icon_select_all_kotevny_usekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_select_all_kotevny_usekActionPerformed
        int rowCount = Table_kotevne_useky.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            Table_kotevne_useky.setValueAt(true, i, 0);
        }
    }//GEN-LAST:event_Button_Icon_select_all_kotevny_usekActionPerformed

    private void Button_Icon_export_PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_export_PDFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_Icon_export_PDFActionPerformed

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

    private void Button_Icon_save_resultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_save_resultsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_Icon_save_resultsActionPerformed

    private void Button_Icon_save_asActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_save_asActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_Icon_save_asActionPerformed

    private void Button_Icon_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_saveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_Icon_saveActionPerformed

    private void TextField_teploha_stav1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav1ActionPerformed

    private void TextField_teploha_stav2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav2ActionPerformed

    private void TextField_teploha_stav3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav3ActionPerformed

    private void TextField_teploha_stav4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav4ActionPerformed

    private void TextField_teploha_stav5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav5ActionPerformed

    private void TextField_teploha_stav6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav6ActionPerformed

    private void TextField_teploha_stav7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav7ActionPerformed

    private void TextField_teploha_stav8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav8ActionPerformed

    private void TextField_teploha_stav9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav9ActionPerformed

    private void TextField_teploha_stav10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav10ActionPerformed

    private void TextField_teploha_stav11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav11ActionPerformed

    private void TextField_teploha_stav12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav12ActionPerformed

    private void TextField_teploha_stav13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav13ActionPerformed

    private void TextField_teploha_stav14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav14ActionPerformed

    private void TextField_pretazenia_stav1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav1ActionPerformed

    private void TextField_pretazenia_stav2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav2ActionPerformed

    private void TextField_pretazenia_stav3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav3ActionPerformed

    private void TextField_pretazenia_stav4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav4ActionPerformed

    private void TextField_pretazenia_stav5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav5ActionPerformed

    private void TextField_pretazenia_stav6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav6ActionPerformed

    private void TextField_pretazenia_stav7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav7ActionPerformed

    private void TextField_pretazenia_stav8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav8ActionPerformed

    private void TextField_pretazenia_stav9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav9ActionPerformed

    private void TextField_pretazenia_stav10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav10ActionPerformed

    private void TextField_pretazenia_stav11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav11ActionPerformed

    private void TextField_pretazenia_stav12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav12ActionPerformed

    private void TextField_pretazenia_stav13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav13ActionPerformed

    private void TextField_pretazenia_stav14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav14ActionPerformed
    private void jRadioButton_with_pretazenia_vypocitanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_pretazenia_vypocitanaActionPerformed
        
        variable_pretazenia_vlastne=false;
        TextField_pretazenia_stav1.setEnabled(false);
        TextField_pretazenia_stav2.setEnabled(false);
        TextField_pretazenia_stav3.setEnabled(false);
        TextField_pretazenia_stav4.setEnabled(false);
        TextField_pretazenia_stav5.setEnabled(false);
        TextField_pretazenia_stav6.setEnabled(false);
        TextField_pretazenia_stav7.setEnabled(false);
        TextField_pretazenia_stav8.setEnabled(false);
        TextField_pretazenia_stav9.setEnabled(false);
        TextField_pretazenia_stav10.setEnabled(false);
        TextField_pretazenia_stav11.setEnabled(false);
        TextField_pretazenia_stav12.setEnabled(false);
        TextField_pretazenia_stav13.setEnabled(false);
        TextField_pretazenia_stav14.setEnabled(false);
        array_pretaezenia_stav_rovnica_loader_setter(); // toto len aby nebol aprazdna premenna
       
    }//GEN-LAST:event_jRadioButton_with_pretazenia_vypocitanaActionPerformed

    private void jRadioButton_with_pretazenia_vlastnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_pretazenia_vlastnaActionPerformed
        variable_pretazenia_vlastne=true;
        TextField_pretazenia_stav1.setEnabled(true);
        TextField_pretazenia_stav2.setEnabled(true);
        TextField_pretazenia_stav3.setEnabled(true);
        TextField_pretazenia_stav4.setEnabled(true);
        TextField_pretazenia_stav5.setEnabled(true);
        TextField_pretazenia_stav6.setEnabled(true);
        TextField_pretazenia_stav7.setEnabled(true);
        TextField_pretazenia_stav8.setEnabled(true);
        TextField_pretazenia_stav9.setEnabled(true);
        TextField_pretazenia_stav10.setEnabled(true);
        TextField_pretazenia_stav11.setEnabled(true);
        TextField_pretazenia_stav12.setEnabled(true);
        TextField_pretazenia_stav13.setEnabled(true);
        TextField_pretazenia_stav14.setEnabled(true);
        array_pretaezenia_stav_rovnica_loader_setter(); // toto len aby nebol aprazdna premenna
        
    }//GEN-LAST:event_jRadioButton_with_pretazenia_vlastnaActionPerformed

    private void TextField_teploha_stav1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav1KeyReleased
         if(TextField_teploha_stav1.isEnabled()== true){
                 Variable_teploty_stav_rovnica[0] = doubleChecker_short_answer(TextField_teploha_stav1);}
                 Jcombo_stav_KPB_setter();
    }//GEN-LAST:event_TextField_teploha_stav1KeyReleased

    private void TextField_pretazenia_stav1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav1KeyReleased
        Variable_pretazenia_stav_rovnica[0] = doubleChecker_short_answer(TextField_pretazenia_stav1);
    }//GEN-LAST:event_TextField_pretazenia_stav1KeyReleased

    private void TextField_pretazenia_stav2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav2KeyReleased
         Variable_pretazenia_stav_rovnica[1] = doubleChecker_short_answer(TextField_pretazenia_stav2);
    }//GEN-LAST:event_TextField_pretazenia_stav2KeyReleased

    private void TextField_pretazenia_stav3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav3KeyReleased
         Variable_pretazenia_stav_rovnica[2] = doubleChecker_short_answer(TextField_pretazenia_stav3);
    }//GEN-LAST:event_TextField_pretazenia_stav3KeyReleased

    private void TextField_pretazenia_stav4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav4KeyReleased
        Variable_pretazenia_stav_rovnica[3] = doubleChecker_short_answer(TextField_pretazenia_stav4);
    }//GEN-LAST:event_TextField_pretazenia_stav4KeyReleased

    private void TextField_pretazenia_stav5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav5KeyReleased
         Variable_pretazenia_stav_rovnica[4] = doubleChecker_short_answer(TextField_pretazenia_stav5);
    }//GEN-LAST:event_TextField_pretazenia_stav5KeyReleased

    private void TextField_pretazenia_stav6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav6KeyReleased
        Variable_pretazenia_stav_rovnica[5] = doubleChecker_short_answer(TextField_pretazenia_stav6);
    }//GEN-LAST:event_TextField_pretazenia_stav6KeyReleased

    private void TextField_pretazenia_stav7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav7KeyReleased
        Variable_pretazenia_stav_rovnica[6] = doubleChecker_short_answer(TextField_pretazenia_stav7);
    }//GEN-LAST:event_TextField_pretazenia_stav7KeyReleased

    private void TextField_pretazenia_stav8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav8KeyReleased
         Variable_pretazenia_stav_rovnica[7] = doubleChecker_short_answer(TextField_pretazenia_stav8);
    }//GEN-LAST:event_TextField_pretazenia_stav8KeyReleased

    private void TextField_pretazenia_stav9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav9KeyReleased
        Variable_pretazenia_stav_rovnica[8] = doubleChecker_short_answer(TextField_pretazenia_stav9);
    }//GEN-LAST:event_TextField_pretazenia_stav9KeyReleased

    private void TextField_pretazenia_stav10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav10KeyReleased
        Variable_pretazenia_stav_rovnica[9] = doubleChecker_short_answer(TextField_pretazenia_stav10);
    }//GEN-LAST:event_TextField_pretazenia_stav10KeyReleased

    private void TextField_pretazenia_stav11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav11KeyReleased
         Variable_pretazenia_stav_rovnica[10] = doubleChecker_short_answer(TextField_pretazenia_stav11);
    }//GEN-LAST:event_TextField_pretazenia_stav11KeyReleased

    private void TextField_pretazenia_stav12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav12KeyReleased
        Variable_pretazenia_stav_rovnica[11] = doubleChecker_short_answer(TextField_pretazenia_stav12);
    }//GEN-LAST:event_TextField_pretazenia_stav12KeyReleased

    private void TextField_pretazenia_stav13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav13KeyReleased
         Variable_pretazenia_stav_rovnica[12] = doubleChecker_short_answer(TextField_pretazenia_stav13);
    }//GEN-LAST:event_TextField_pretazenia_stav13KeyReleased

    private void TextField_pretazenia_stav14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav14KeyReleased
        Variable_pretazenia_stav_rovnica[13] = doubleChecker_short_answer(TextField_pretazenia_stav14);
    }//GEN-LAST:event_TextField_pretazenia_stav14KeyReleased

    private void TextField_teploha_stav2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav2KeyReleased
        if (TextField_teploha_stav2.isEnabled() == true) {
            Variable_teploty_stav_rovnica[1] = doubleChecker_short_answer(TextField_teploha_stav2);
        Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav2KeyReleased

    private void TextField_teploha_stav3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav3KeyReleased
        if (TextField_teploha_stav3.isEnabled() == true) {
            Variable_teploty_stav_rovnica[2] = doubleChecker_short_answer(TextField_teploha_stav3);
        Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav3KeyReleased

    private void TextField_teploha_stav4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav4KeyReleased
        if (TextField_teploha_stav4.isEnabled() == true) {
            Variable_teploty_stav_rovnica[3] = doubleChecker_short_answer(TextField_teploha_stav4);
        Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav4KeyReleased

    private void TextField_teploha_stav5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav5KeyReleased
        if (TextField_teploha_stav5.isEnabled() == true) {
       
            Variable_teploty_stav_rovnica[4] = doubleChecker_short_answer(TextField_teploha_stav5);
        Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav5KeyReleased

    private void TextField_teploha_stav6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav6KeyReleased
        if (TextField_teploha_stav6.isEnabled() == true) {
            Variable_teploty_stav_rovnica[5] = doubleChecker_short_answer(TextField_teploha_stav6);
        Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav6KeyReleased

    private void TextField_teploha_stav7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav7KeyReleased
        if (TextField_teploha_stav7.isEnabled() == true) {
            Variable_teploty_stav_rovnica[6] = doubleChecker_short_answer(TextField_teploha_stav7);
        Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav7KeyReleased

    private void TextField_teploha_stav8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav8KeyReleased
        if (TextField_teploha_stav8.isEnabled() == true) {
            Variable_teploty_stav_rovnica[7] = doubleChecker_short_answer(TextField_teploha_stav8);
        Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav8KeyReleased

    private void TextField_teploha_stav9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav9KeyReleased
        if (TextField_teploha_stav9.isEnabled() == true) {
            Variable_teploty_stav_rovnica[8] = doubleChecker_short_answer(TextField_teploha_stav9);
        Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav9KeyReleased

    private void TextField_teploha_stav10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav10KeyReleased
        if (TextField_teploha_stav10.isEnabled() == true) {
        
            Variable_teploty_stav_rovnica[9] = doubleChecker_short_answer(TextField_teploha_stav10);
        Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav10KeyReleased

    private void TextField_teploha_stav11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav11KeyReleased
        if (TextField_teploha_stav11.isEnabled() == true) {
            Variable_teploty_stav_rovnica[10] = doubleChecker_short_answer(TextField_teploha_stav11);
        Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav11KeyReleased

    private void TextField_teploha_stav12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav12KeyReleased
        if (TextField_teploha_stav12.isEnabled() == true) {
            Variable_teploty_stav_rovnica[11] = doubleChecker_short_answer(TextField_teploha_stav12);
        Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav12KeyReleased

    private void TextField_teploha_stav13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav13KeyReleased
        if (TextField_teploha_stav13.isEnabled() == true) {
            Variable_teploty_stav_rovnica[12] = doubleChecker_short_answer(TextField_teploha_stav13);
        Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav13KeyReleased

    private void TextField_teploha_stav14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav14KeyReleased
        if (TextField_teploha_stav14.isEnabled() == true) {
            Variable_teploty_stav_rovnica[13] = doubleChecker_short_answer(TextField_teploha_stav14);
        Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav14KeyReleased

    private void jTextField_nazov_normiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nazov_normiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nazov_normiActionPerformed

    private void jTextField_nadpis_pre_prechodnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nadpis_pre_prechodnaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nadpis_pre_prechodnaActionPerformed

    private void jTextField_nazov_nazov_stavbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nazov_nazov_stavbyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nazov_nazov_stavbyActionPerformed

    private void jTextField_nazov_SOPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nazov_SOPSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nazov_SOPSActionPerformed

    private void jTextField_nazov_arch_cisloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nazov_arch_cisloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nazov_arch_cisloActionPerformed

    private void jTextField_nazov_cislo_stranyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nazov_cislo_stranyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nazov_cislo_stranyActionPerformed

    private void jTextField_vypracovalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_vypracovalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_vypracovalActionPerformed

    private void jTextField_datumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_datumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_datumActionPerformed

    private void TextField_hustota_namrazyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_hustota_namrazyKeyReleased
        if (TextField_hustota_namrazy.isEditable() == true){

            Variable_hustota_namrazy=doubleChecker_short_answer(TextField_hustota_namrazy);

        }
    }//GEN-LAST:event_TextField_hustota_namrazyKeyReleased

    private void TextField_hustota_namrazyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_hustota_namrazyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_hustota_namrazyActionPerformed

    private void TextField_CclKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_CclKeyReleased

        if (TextField_Ccl.isEditable() == true){

            Variable_Ccl=doubleChecker_short_answer(TextField_Ccl);

        }
    }//GEN-LAST:event_TextField_CclKeyReleased

    private void TextField_CclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_CclActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_CclActionPerformed

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

    private void TextField_Bi2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_Bi2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_Bi2KeyReleased

    private void jRadioButton_Bi_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Bi_2ActionPerformed
        TextField_Bi2.setEnabled(true);
        TextField_Bi_1.setEnabled(false);
        Variable_Bi=doubleChecker_short_answer(TextField_Bi2);
    }//GEN-LAST:event_jRadioButton_Bi_2ActionPerformed

    private void TextField_Bi_1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_Bi_1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_Bi_1KeyReleased

    private void TextField_Bi_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_Bi_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_Bi_1ActionPerformed

    private void jRadioButton_Bi_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Bi_1ActionPerformed
        TextField_Bi_1.setEnabled(true);
        TextField_Bi2.setEnabled(false);
        Variable_Bi=doubleChecker_short_answer(TextField_Bi_1);
    }//GEN-LAST:event_jRadioButton_Bi_1ActionPerformed

    private void TextField_KhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_KhKeyReleased
        Variable_Kh =doubleChecker_short_answer(TextField_Kh);
    }//GEN-LAST:event_TextField_KhKeyReleased

    private void jRadioButton_Kh_vlastnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Kh_vlastnaActionPerformed
        TextField_Kh.setEnabled(true);
        Variable_Kh=doubleChecker_short_answer(TextField_Kcl);
    }//GEN-LAST:event_jRadioButton_Kh_vlastnaActionPerformed

    private void jRadioButton_Kh_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Kh_1ActionPerformed
        Variable_Kh=1.0;
        TextField_Kh.setEnabled(false);
    }//GEN-LAST:event_jRadioButton_Kh_1ActionPerformed

    private void jRadioButton_Klc_vlastnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Klc_vlastnaActionPerformed
        TextField_Kcl.setEnabled(true);
        Variable_Klc=doubleChecker_short_answer(TextField_Kcl);
    }//GEN-LAST:event_jRadioButton_Klc_vlastnaActionPerformed

    private void TextField_KclKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_KclKeyReleased
        Variable_Klc=doubleChecker_short_answer(TextField_Kcl);
    }//GEN-LAST:event_TextField_KclKeyReleased

    private void jRadioButton_Klc_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Klc_1ActionPerformed
        Variable_Klc=1.0;
        TextField_Kcl.setEnabled(false);
    }//GEN-LAST:event_jRadioButton_Klc_1ActionPerformed

    private void Button_namrazova_oblastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_namrazova_oblastActionPerformed

        mainframe_namrazova_oblast_jDialog mainframe_namrazova_oblast_jDialog_window = new mainframe_namrazova_oblast_jDialog(this, rootPaneCheckingEnabled);
        mainframe_namrazova_oblast_jDialog_window.setVisible(true);

        if(is_namrazove_oblasti_setted== true){

            Label_vybrana_namrazova_oblast.setText(namrazove_oblasti_názov_oblasti);
            Variable_Ir50=vypocet_IR50_namrazove_oblasti();
        }
    }//GEN-LAST:event_Button_namrazova_oblastActionPerformed

    private void TextField_max_mech_podiel_z_RTSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_max_mech_podiel_z_RTSKeyReleased
        Variable_maximalne_zataz_lana_podiel_z_RTS=Variable_RTS*(doubleChecker_short_answer(TextField_zakladne_mech_lana_minus5)/100);
    }//GEN-LAST:event_TextField_max_mech_podiel_z_RTSKeyReleased

    private void TextField_max_mech_podiel_z_RTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_max_mech_podiel_z_RTSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_max_mech_podiel_z_RTSActionPerformed

    private void TextField_zakladne_mech_lana_minus5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_zakladne_mech_lana_minus5KeyReleased
        Variable_zakladne_mech_napatie_lana_pre_minus5=doubleChecker_short_answer(TextField_zakladne_mech_lana_minus5);
    }//GEN-LAST:event_TextField_zakladne_mech_lana_minus5KeyReleased

    private void TextField_zakladne_mech_lana_minus5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_zakladne_mech_lana_minus5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_zakladne_mech_lana_minus5ActionPerformed

    private void TextField_RTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_RTSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_RTSActionPerformed

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

    private void jComboBox_stav_KPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_stav_KPBActionPerformed
        if( teplotyser==true){

            int selected_index = jComboBox_stav_KPB.getSelectedIndex();
            if (selected_index == -1){
                Variable_vybrany_stav_pre_KPB=Variable_teploty_stav_rovnica[0];
            }else{
                Variable_vybrany_stav_pre_KPB=Variable_teploty_stav_rovnica[selected_index];
            }
        }

    }//GEN-LAST:event_jComboBox_stav_KPBActionPerformed

    private void jComboBox_stav_KPBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_stav_KPBItemStateChanged
        if( teplotyser==true){

            int selected_index = jComboBox_stav_KPB.getSelectedIndex();
            if (selected_index == -1){
                Variable_vybrany_stav_pre_KPB=Variable_teploty_stav_rovnica[0];
            }else{
                Variable_vybrany_stav_pre_KPB=Variable_teploty_stav_rovnica[selected_index];
            }
        }

    }//GEN-LAST:event_jComboBox_stav_KPBItemStateChanged

    private void TextField_srt_roc_teplotaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_srt_roc_teplotaKeyReleased
        Variable_streda_roc_teplota=doubleChecker(TextField_srt_roc_teplota);
    }//GEN-LAST:event_TextField_srt_roc_teplotaKeyReleased

    private void TextField_srt_roc_teplotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_srt_roc_teplotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_srt_roc_teplotaActionPerformed

    private void jComboBox_uroven_splahlivostiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_uroven_splahlivostiActionPerformed

        if (mainframeLodaed == true) {

            int selected_index_from_JComboBox = jComboBox_uroven_splahlivosti.getSelectedIndex();

            switch (selected_index_from_JComboBox) {
                case 0:  //50 rokov
                Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti = 50;
                Variable_uroven_spolahlivosti_Yw=1;
                Variable_uroven_spolahlivosti_Yi=1;
                Variable_uroven_spolahlivosti_Ww=0.25;
                Variable_uroven_spolahlivosti_Wi=0.35;
                break;
                case 1:  //150 rokov
                Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti = 150;
                Variable_uroven_spolahlivosti_Yw=1.2;
                Variable_uroven_spolahlivosti_Yi=1.25;
                Variable_uroven_spolahlivosti_Ww=0.25;
                Variable_uroven_spolahlivosti_Wi=0.35;
                break;
                case 2:  //500 rokov
                Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti = 500;
                Variable_uroven_spolahlivosti_Yw=1.4;
                Variable_uroven_spolahlivosti_Yi=1.5;
                Variable_uroven_spolahlivosti_Ww=0.25;
                Variable_uroven_spolahlivosti_Wi=0.35;
                break;
                case 3:  //3 dni
                Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti = 2;
                Variable_uroven_spolahlivosti_Yw=0.52;
                Variable_uroven_spolahlivosti_Yi=0.26;
                Variable_uroven_spolahlivosti_Ww=0.22;
                Variable_uroven_spolahlivosti_Wi=0.35;
                break;
                case 4:  //3 mesiace
                Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti = 5;
                Variable_uroven_spolahlivosti_Yw=0.66;
                Variable_uroven_spolahlivosti_Yi=0.5;
                Variable_uroven_spolahlivosti_Ww=0.25;
                Variable_uroven_spolahlivosti_Wi=0.35;
                break;
                case 5:  //1 rok
                Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti = 10;
                Variable_uroven_spolahlivosti_Yw=0.75;
                Variable_uroven_spolahlivosti_Yi=0.65;
                Variable_uroven_spolahlivosti_Ww=0.25;
                Variable_uroven_spolahlivosti_Wi=0.35;
                break;
                case 6:  //50 rokov
                mainframe_uroven_spolahlivosti_vlastna_hodnota.setValues(Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti, Variable_uroven_spolahlivosti_Yw, Variable_uroven_spolahlivosti_Yi, Variable_uroven_spolahlivosti_Ww, Variable_uroven_spolahlivosti_Wi);
                mainframe_uroven_spolahlivosti_vlastna_hodnota mainframe_uroven_spolahlivosti_vlastna_hodnota_jDialog_window = new  mainframe_uroven_spolahlivosti_vlastna_hodnota(this, rootPaneCheckingEnabled);
                mainframe_uroven_spolahlivosti_vlastna_hodnota_jDialog_window.setVisible(true);

                Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti = Double.valueOf( vlastnehodnoty_uroven_splahlivosti[0].toString());
                Variable_uroven_spolahlivosti_Yw=Double.valueOf( vlastnehodnoty_uroven_splahlivosti[1].toString());
                Variable_uroven_spolahlivosti_Yi=Double.valueOf( vlastnehodnoty_uroven_splahlivosti[2].toString());
                Variable_uroven_spolahlivosti_Ww=Double.valueOf( vlastnehodnoty_uroven_splahlivosti[3].toString());
                Variable_uroven_spolahlivosti_Wi=Double.valueOf( vlastnehodnoty_uroven_splahlivosti[4].toString());

                break;
            }

        }

    }//GEN-LAST:event_jComboBox_uroven_splahlivostiActionPerformed

    private void jRadioButton_KPB_cas_vypoctu_prechodneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_KPB_cas_vypoctu_prechodneActionPerformed
        Variable_KPB_cas_vypoctu= Variable_Tp_prechodna_doba;
    }//GEN-LAST:event_jRadioButton_KPB_cas_vypoctu_prechodneActionPerformed

    private void jRadioButton_KPB_cas_vypoctu_1_rokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_KPB_cas_vypoctu_1_rokActionPerformed
        Variable_KPB_cas_vypoctu = 365*24;
    }//GEN-LAST:event_jRadioButton_KPB_cas_vypoctu_1_rokActionPerformed

    private void jComboBox_KPB_typ_terenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_KPB_typ_terenuActionPerformed

        if (mainframeLodaed == true) {

            int selected_index_from_JComboBox = jComboBox_KPB_typ_terenu.getSelectedIndex();

            switch (selected_index_from_JComboBox) {
                case 0:
                Variable_vybrany_stav_pre_KPB= 1.0;
                jTextPane_KPB_typ_terenu.setText(language.language_label(languageOption, 169));
                break;
                case 1:  Variable_vybrany_stav_pre_KPB= 2.0;
                jTextPane_KPB_typ_terenu.setText(language.language_label(languageOption, 170));
                break;
                case 2:  Variable_vybrany_stav_pre_KPB= 3.0;
                jTextPane_KPB_typ_terenu.setText(language.language_label(languageOption, 171));
                break;
                case 3:  Variable_vybrany_stav_pre_KPB= 4.0;
                jTextPane_KPB_typ_terenu.setText(language.language_label(languageOption, 172));
                break;

            }

        }else{ Variable_vybrany_stav_pre_KPB= 1.0;}

    }//GEN-LAST:event_jComboBox_KPB_typ_terenuActionPerformed

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
    private javax.swing.JLabel Label_KPB;
    private javax.swing.JLabel Label_KPB_typ_terenu;
    private javax.swing.JLabel Label_RTS;
    private javax.swing.JLabel Label_RTS_velicina;
    private javax.swing.JLabel Label_RTS_velicina1;
    private javax.swing.JLabel Label_RTS_velicina2;
    private javax.swing.JLabel Label_RTS_velicina3;
    private javax.swing.JLabel Label_RTS_velicina4;
    private javax.swing.JLabel Label_RTS_velicina5;
    private javax.swing.JLabel Label_RTS_velicina6;
    private javax.swing.JLabel Label_RTS_velicina7;
    private javax.swing.JLabel Label__Bi;
    private javax.swing.JLabel Label__Kh;
    private javax.swing.JLabel Label__Klc;
    private javax.swing.JLabel Label__char_terenu_kr;
    private javax.swing.JLabel Label__stav_KPB;
    private javax.swing.JLabel Label__stredna_rocna_teplota;
    private javax.swing.JLabel Label__typ_namrazy_Ccl;
    private javax.swing.JLabel Label_char_terenu;
    private javax.swing.JLabel Label_char_terenu_zo;
    private static javax.swing.JLabel Label_hlavicka_SOPS;
    private static javax.swing.JLabel Label_hlavicka_arch_cislo;
    private static javax.swing.JLabel Label_hlavicka_cislo_strany;
    private static javax.swing.JLabel Label_hlavicka_datum;
    private static javax.swing.JLabel Label_hlavicka_nadpis_pre_prechodne;
    private static javax.swing.JLabel Label_hlavicka_nazov;
    private static javax.swing.JLabel Label_hlavicka_stavba;
    private static javax.swing.JLabel Label_hlavicka_vypocet_podla_normi;
    private static javax.swing.JLabel Label_hlavicka_vypracoval;
    private javax.swing.JLabel Label_hustota_namrazy;
    private javax.swing.JLabel Label_kotevne_useky;
    private javax.swing.JLabel Label_kotevne_useky1;
    private javax.swing.JLabel Label_max_zataz_lana;
    private static javax.swing.JLabel Label_pretazenia;
    private static javax.swing.JLabel Label_stredna_vyska_vodicov_nad_terenom;
    private static javax.swing.JLabel Label_stredne_rozpatie;
    private static javax.swing.JLabel Label_tabulky;
    private static javax.swing.JLabel Label_tabulky1;
    private static javax.swing.JLabel Label_teploty;
    private javax.swing.JLabel Label_typ_namrazy;
    private javax.swing.JLabel Label_uroven_spolahlivosti;
    private javax.swing.JLabel Label_vetrova_oblast;
    private javax.swing.JLabel Label_vetrova_oblast_C0;
    private javax.swing.JLabel Label_vetrova_oblast_Cdir;
    private javax.swing.JLabel Label_vybrana_namrazova_oblast;
    private javax.swing.JLabel Label_zakladne_mech_napatie_minis5;
    private javax.swing.JLabel Label_základna_rychlost_vetra;
    private javax.swing.JTable Table_kotevne_useky;
    private javax.swing.JTable Table_rozpatia;
    private javax.swing.JTable Table_rozpatia_nadm_vysky;
    private javax.swing.JTextField TextField_Bi2;
    private javax.swing.JTextField TextField_Bi_1;
    private javax.swing.JTextField TextField_Ccl;
    private javax.swing.JTextField TextField_Kcl;
    private javax.swing.JTextField TextField_Kh;
    private javax.swing.JTextField TextField_Kr;
    private javax.swing.JTextField TextField_RTS;
    private javax.swing.JTextField TextField_STRrozpatie_klasicky;
    private javax.swing.JTextField TextField_STRrozpatie_sPrevisenim;
    private javax.swing.JTextField TextField_Vmean_0;
    private javax.swing.JTextField TextField_dlzka_drsnjosti_zo;
    private javax.swing.JTextField TextField_hcmean_vlastna;
    private javax.swing.JTextField TextField_hcmean_vpocitana;
    private javax.swing.JTextField TextField_hustota_namrazy;
    private javax.swing.JTextField TextField_max_mech_podiel_z_RTS;
    private javax.swing.JTextField TextField_pretazenia_stav1;
    private javax.swing.JTextField TextField_pretazenia_stav10;
    private javax.swing.JTextField TextField_pretazenia_stav11;
    private javax.swing.JTextField TextField_pretazenia_stav12;
    private javax.swing.JTextField TextField_pretazenia_stav13;
    private javax.swing.JTextField TextField_pretazenia_stav14;
    private javax.swing.JTextField TextField_pretazenia_stav2;
    private javax.swing.JTextField TextField_pretazenia_stav3;
    private javax.swing.JTextField TextField_pretazenia_stav4;
    private javax.swing.JTextField TextField_pretazenia_stav5;
    private javax.swing.JTextField TextField_pretazenia_stav6;
    private javax.swing.JTextField TextField_pretazenia_stav7;
    private javax.swing.JTextField TextField_pretazenia_stav8;
    private javax.swing.JTextField TextField_pretazenia_stav9;
    private javax.swing.JTextField TextField_srt_roc_teplota;
    private javax.swing.JTextField TextField_tabulky_konecna;
    private javax.swing.JTextField TextField_tabulky_prechodna;
    private javax.swing.JTextField TextField_teploha_stav1;
    private javax.swing.JTextField TextField_teploha_stav10;
    private javax.swing.JTextField TextField_teploha_stav11;
    private javax.swing.JTextField TextField_teploha_stav12;
    private javax.swing.JTextField TextField_teploha_stav13;
    private javax.swing.JTextField TextField_teploha_stav14;
    private javax.swing.JTextField TextField_teploha_stav2;
    private javax.swing.JTextField TextField_teploha_stav3;
    private javax.swing.JTextField TextField_teploha_stav4;
    private javax.swing.JTextField TextField_teploha_stav5;
    private javax.swing.JTextField TextField_teploha_stav6;
    private javax.swing.JTextField TextField_teploha_stav7;
    private javax.swing.JTextField TextField_teploha_stav8;
    private javax.swing.JTextField TextField_teploha_stav9;
    private javax.swing.JTextField TextField_vetrova_oblast_C0;
    private javax.swing.JTextField TextField_vetrova_oblast_Cdir;
    private javax.swing.JTextField TextField_zakladne_mech_lana_minus5;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup_Bi;
    private javax.swing.ButtonGroup buttonGroup_C0;
    private javax.swing.ButtonGroup buttonGroup_Cdir;
    private javax.swing.ButtonGroup buttonGroup_KPB_cas_vypoctu;
    private javax.swing.ButtonGroup buttonGroup_kh;
    private javax.swing.ButtonGroup buttonGroup_klc;
    private javax.swing.ButtonGroup buttonGroup_pretazenia_vlystne_vypocitane;
    private javax.swing.ButtonGroup buttonGroup_stredne_rozpatia;
    private javax.swing.ButtonGroup buttonGroup_tabulka;
    private javax.swing.JComboBox<String> jComboBox_KPB_typ_terenu;
    private static final javax.swing.JComboBox<String> jComboBox_char_terenu = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> jComboBox_conductor_chooser = new javax.swing.JComboBox<>();
    private javax.swing.JComboBox<String> jComboBox_druh_namrazy;
    private javax.swing.JComboBox<String> jComboBox_stav_KPB;
    private javax.swing.JComboBox<String> jComboBox_uroven_splahlivosti;
    private static final javax.swing.JComboBox<String> jComboBox_vetrova_oblast = new javax.swing.JComboBox<>();
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton_Bi_1;
    private javax.swing.JRadioButton jRadioButton_Bi_2;
    private javax.swing.JRadioButton jRadioButton_KPB_cas_vypoctu_1_rok;
    private javax.swing.JRadioButton jRadioButton_KPB_cas_vypoctu_prechodne;
    private javax.swing.JRadioButton jRadioButton_Kh_1;
    private javax.swing.JRadioButton jRadioButton_Kh_vlastna;
    private javax.swing.JRadioButton jRadioButton_Klc_1;
    private javax.swing.JRadioButton jRadioButton_Klc_vlastna;
    private javax.swing.JRadioButton jRadioButton_vetrova_oblast_C0_1;
    private javax.swing.JRadioButton jRadioButton_vetrova_oblast_C0_vlastna;
    private javax.swing.JRadioButton jRadioButton_vetrova_oblast_Cdir_1;
    private javax.swing.JRadioButton jRadioButton_vetrova_oblast_Cdir_vlastna;
    private javax.swing.JRadioButton jRadioButton_with_label_konecne;
    private javax.swing.JRadioButton jRadioButton_with_label_pociatocne;
    private javax.swing.JRadioButton jRadioButton_with_label_prechodne;
    private javax.swing.JRadioButton jRadioButton_with_label_rozpate_previsenia;
    private javax.swing.JRadioButton jRadioButton_with_label_rozpatie_klasicky;
    private javax.swing.JRadioButton jRadioButton_with_label_vlastna;
    private javax.swing.JRadioButton jRadioButton_with_label_vypoctana;
    private javax.swing.JRadioButton jRadioButton_with_pretazenia_vlastna;
    private javax.swing.JRadioButton jRadioButton_with_pretazenia_vypocitana;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField_datum;
    private javax.swing.JTextField jTextField_nadpis_pre_prechodna;
    private javax.swing.JTextField jTextField_nazov_SOPS;
    private javax.swing.JTextField jTextField_nazov_arch_cislo;
    private javax.swing.JTextField jTextField_nazov_cislo_strany;
    private javax.swing.JTextField jTextField_nazov_nazov_stavby;
    private javax.swing.JTextField jTextField_nazov_normi;
    private javax.swing.JTextField jTextField_vypracoval;
    private javax.swing.JTextArea jTextPane_KPB_typ_terenu;
    private javax.swing.JTextArea jTextPane_char_terenu;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
DefaultTableModel modelTable;
DefaultTableModel modeltable_rozpatia;
DefaultTableModel modeltable_rozpatia_nadm_vysky;
public static String new_kotevny_usek_name;
public static boolean existnewkotevnyusek = false;
private static boolean mainframeLodaed = false;
private static boolean teplotyser = false;

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
public static Object[] vlastnehodnoty_uroven_splahlivosti = new Object[4];

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
private static double[] Variable_Ai_array;
private static double[] Variable_DeltaHi_array;
private static double[] Variable_Hi_array;
private static double Variable_T0_zivotnost;
private static double Variable_Tp_prechodna_doba;
private static double Variable_Bi;
private static double Variable_V_mean_0;
private static double Variable_Cdir;
private static double Variable_Co;
private static double Variable_char_terenu_Kr;
private static double Variable_char_terenu_Zo;
private static double Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti;
private static double Variable_uroven_spolahlivosti_Yw;
private static double Variable_uroven_spolahlivosti_Yi;
private static double Variable_uroven_spolahlivosti_Ww;
private static double Variable_uroven_spolahlivosti_Wi;
private static double Variable_mid_span_docasna;
private static double Variable_mid_span_terrain_docasna;
private static double Variable_mid_span;
private static double[] Variable_teploty_stav_rovnica = new double[14];
private static double[] Variable_pretazenia_stav_rovnica = new double[14];
private static boolean variable_pretazenia_vlastne= false;
private static double Variable_streda_roc_teplota;
private static double Variable_vybrany_stav_pre_KPB;
private static double Variable_KPB_typ_terenu;
private static double Variable_KPB_cas_vypoctu;
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
      
      public static void getvlastnehodnoty_uroven_splahlivosti(Object[] X) {
        vlastnehodnoty_uroven_splahlivosti = X;

    }
      
       /**
     * Method computes middle span for flat terrain "MSF".
     */
    private static void mid_span_flat(){
        double cube = 0;
        double sum = 0;
        for (int i=0; i<Variable_Ai_array.length; i++){
            cube = cube + Math.pow(Variable_Ai_array[i],3);
            sum = sum + Variable_Ai_array[i];
        }
        Variable_mid_span_docasna = Math.sqrt(cube/sum);
    }
    
    /**
     * Method computes middle span for non-flat terrain "MST".
     */
    private static void mid_span_terrain(){
        double upper = 0;
        double lower = 0;
        double temp;
        for (int i=0; i<Variable_Ai_array.length; i++){
            temp = Math.sqrt(Math.pow(Variable_Ai_array[i],2)+ Math.pow(Variable_DeltaHi_array[i], 2));
            upper = upper + Math.pow(Variable_Ai_array[i], 4)/temp;
            lower = lower + temp;
        }
       Variable_mid_span_terrain_docasna = Math.sqrt(upper/lower);
    }
    
    private  void array_teploty_stav_rovnica_konecne_loader_setter(){
    Variable_teploty_stav_rovnica[0]=doubleChecker_short_answer(TextField_teploha_stav1);
    Variable_teploty_stav_rovnica[1]=doubleChecker_short_answer(TextField_teploha_stav2);
    Variable_teploty_stav_rovnica[2]=doubleChecker_short_answer(TextField_teploha_stav3);
    Variable_teploty_stav_rovnica[3]=doubleChecker_short_answer(TextField_teploha_stav4);
    
    Variable_teploty_stav_rovnica[4]=-5;
    Variable_teploty_stav_rovnica[5]=-5;
    Variable_teploty_stav_rovnica[6]=-5;
    Variable_teploty_stav_rovnica[7]=-5;
    
    Variable_teploty_stav_rovnica[8]=doubleChecker_short_answer(TextField_teploha_stav9);
    Variable_teploty_stav_rovnica[9]=doubleChecker_short_answer(TextField_teploha_stav10);
    Variable_teploty_stav_rovnica[10]=doubleChecker_short_answer(TextField_teploha_stav11);
    Variable_teploty_stav_rovnica[11]=doubleChecker_short_answer(TextField_teploha_stav12);
    Variable_teploty_stav_rovnica[12]=doubleChecker_short_answer(TextField_teploha_stav13);
    Variable_teploty_stav_rovnica[13]=doubleChecker_short_answer(TextField_teploha_stav14); 
    }
    
    private  void array_teploty_stav_rovnica_pociatocne_loader_setter(){
    Variable_teploty_stav_rovnica[0]=doubleChecker_short_answer(TextField_teploha_stav1);
    Variable_teploty_stav_rovnica[1]=doubleChecker_short_answer(TextField_teploha_stav2);
    Variable_teploty_stav_rovnica[2]=doubleChecker_short_answer(TextField_teploha_stav3);
    Variable_teploty_stav_rovnica[3]=doubleChecker_short_answer(TextField_teploha_stav4);
    
    Variable_teploty_stav_rovnica[4]=doubleChecker_short_answer(TextField_teploha_stav5);
    Variable_teploty_stav_rovnica[5]=doubleChecker_short_answer(TextField_teploha_stav6);
    Variable_teploty_stav_rovnica[6]=doubleChecker_short_answer(TextField_teploha_stav7);
    Variable_teploty_stav_rovnica[7]=doubleChecker_short_answer(TextField_teploha_stav8);
    
    Variable_teploty_stav_rovnica[8]=doubleChecker_short_answer(TextField_teploha_stav9);
    Variable_teploty_stav_rovnica[9]=doubleChecker_short_answer(TextField_teploha_stav10);
    Variable_teploty_stav_rovnica[10]=doubleChecker_short_answer(TextField_teploha_stav11);
    Variable_teploty_stav_rovnica[11]=doubleChecker_short_answer(TextField_teploha_stav12);
    Variable_teploty_stav_rovnica[12]=doubleChecker_short_answer(TextField_teploha_stav13);
    Variable_teploty_stav_rovnica[13]=doubleChecker_short_answer(TextField_teploha_stav14); 
    }
    
     private  void array_pretaezenia_stav_rovnica_loader_setter(){
    Variable_pretazenia_stav_rovnica[0]=doubleChecker_short_answer(TextField_pretazenia_stav1);
    Variable_pretazenia_stav_rovnica[1]=doubleChecker_short_answer(TextField_pretazenia_stav2);
    Variable_pretazenia_stav_rovnica[2]=doubleChecker_short_answer(TextField_pretazenia_stav3);
    Variable_pretazenia_stav_rovnica[3]=doubleChecker_short_answer(TextField_pretazenia_stav4);
    Variable_pretazenia_stav_rovnica[4]=doubleChecker_short_answer(TextField_pretazenia_stav5);
    Variable_pretazenia_stav_rovnica[5]=doubleChecker_short_answer(TextField_pretazenia_stav6);
    Variable_pretazenia_stav_rovnica[6]=doubleChecker_short_answer(TextField_pretazenia_stav7);
    Variable_pretazenia_stav_rovnica[7]=doubleChecker_short_answer(TextField_pretazenia_stav8);
    Variable_pretazenia_stav_rovnica[8]=doubleChecker_short_answer(TextField_pretazenia_stav9);
    Variable_pretazenia_stav_rovnica[9]=doubleChecker_short_answer(TextField_pretazenia_stav10);
    Variable_pretazenia_stav_rovnica[10]=doubleChecker_short_answer(TextField_pretazenia_stav11);
    Variable_pretazenia_stav_rovnica[11]=doubleChecker_short_answer(TextField_pretazenia_stav12);
    Variable_pretazenia_stav_rovnica[12]=doubleChecker_short_answer(TextField_pretazenia_stav13);
    Variable_pretazenia_stav_rovnica[13]=doubleChecker_short_answer(TextField_pretazenia_stav14); 
    }
     
      private  void Jcombo_stav_KPB_setter(){
          jComboBox_stav_KPB.removeAllItems();
          jComboBox_stav_KPB.addItem((String) TextField_teploha_stav1.getText());
          jComboBox_stav_KPB.addItem((String) TextField_teploha_stav2.getText());
          jComboBox_stav_KPB.addItem((String) TextField_teploha_stav3.getText());
          jComboBox_stav_KPB.addItem((String) TextField_teploha_stav4.getText());
          jComboBox_stav_KPB.addItem((String) TextField_teploha_stav5.getText());
          jComboBox_stav_KPB.addItem((String) TextField_teploha_stav6.getText());
          jComboBox_stav_KPB.addItem((String) TextField_teploha_stav7.getText());
          jComboBox_stav_KPB.addItem((String) TextField_teploha_stav8.getText());
          jComboBox_stav_KPB.addItem((String) TextField_teploha_stav9.getText());
          jComboBox_stav_KPB.addItem((String) TextField_teploha_stav10.getText());
          jComboBox_stav_KPB.addItem((String) TextField_teploha_stav11.getText());
          jComboBox_stav_KPB.addItem((String) TextField_teploha_stav12.getText());
          jComboBox_stav_KPB.addItem((String) TextField_teploha_stav13.getText());
          jComboBox_stav_KPB.addItem((String) TextField_teploha_stav14.getText());   
      }
}
