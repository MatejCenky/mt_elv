/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mainframe_17;

import mainframe_1.*;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventObject;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import mt_main.language;
import mt_main.startPanel;
import static mt_main.startPanel.languageOption;
import mt_math.conductor_creeping;
import mt_math.forces_check;
import mt_math.overload;
import mt_math.state_equation;
import mt_math.vibration_protection;
import mt_variables.Conductor_variables;
import mt_variables.Overload_variables;
import mt_variables.State_equation_variables;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import sun.swing.DefaultLookup;

/**
 *
 * @author Mattto
 */
  public   class mainframe_17 extends javax.swing.JFrame {

   

    /**
     * Creates new form mainframe
     */
    public  mainframe_17() {
       
           
        
        
        if(loaded_file== false){
        mainframe_new_project_set_title_1 mainframe_new_project_JDialog_window = new mainframe_new_project_set_title_1(this, rootPaneCheckingEnabled);
        mainframe_new_project_JDialog_window.setVisible(true); 
        project_name=mainframe_new_project_set_title_1.name_of_project;
        }else{
            project_name= "new";           
        }
        
        
//        JScrollPane myJScrollPane = new JScrollPane(jPanel18,
//         JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//         JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        
        initComponents();
        setDefaultCloseOperation(mainframe_17.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        isOpen = true;
        first_Start = true;
        povodna_hodnota_selekcie = 0;
        current_pdf_page = 1;
        pocet_pdf_stran = 0;
        existnewkotevnyusek = false;
        mainframeLodaed = false;
        teplotyser = false;
        urovenspolahlivostiblocker = true;
        first_Start = true;
        selection_kotevny_usek = true;
        povodna_hodnota_selekcie = 0;
        Calculation_done = false;
       // loaded_file = false;
        project_filename = "";
        project_filepath = "";
        is_namrazove_oblasti_setted = true;
        tablemodellistener_rozpatia = true;
        tablemodellistener_nad_vysky = true;
        tablemodellistener_total = true;
        filename = "new_file";
        memory_path_plus_filename_existence = false;
        variable_pretazenia_vlastne = false;
        Variable_globeal_kotevny_usek.removeAll(Variable_globeal_kotevny_usek);
        Variable_Ai_dlzka_rozpatia.removeAll(Variable_Ai_dlzka_rozpatia);
        Variable_hi_vyska_stoziarov.removeAll(Variable_hi_vyska_stoziarov);
        Variable_hi2_nadmorska_vyska_stoziarov.removeAll(Variable_hi2_nadmorska_vyska_stoziarov);

        Variable_Kh=1.0;
        Variable_Ai_array = null;
        Variable_DeltaHi_array = null;
        Variable_Hi_array = null;
        Variable_Hi_array_nmv = null;
        Variable_pretazenia_stav_rovnica = new double[14];
        Variable_teploty_stav_rovnica = new double[14];
        Textfield_cas.setText("00:00:00");
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/empty.png")));
        
        Label_status.setText(language.language_label(languageOption, 271));
        
        seticon();
        
       
        
        this.modelTable = (DefaultTableModel) Table_kotevne_useky.getModel();
        this.modeltable_rozpatia = (DefaultTableModel) Table_rozpatia.getModel();
        this.modeltable_rozpatia_nadm_vysky = (DefaultTableModel) Table_rozpatia_nadm_vysky.getModel();
        this.modeltable_KPB = (DefaultTableModel) Table_KPB.getModel();
        this.modeltable_tahy = (DefaultTableModel) Table_tahy.getModel();
        // pridavame cell editory custom made uplne dole je separatna class nato
        TableColumn col = Table_rozpatia_nadm_vysky.getColumnModel().getColumn(0);
        col.setCellEditor( new MyCellEditor());
        TableColumn col1 = Table_rozpatia_nadm_vysky.getColumnModel().getColumn(1);
        col1.setCellEditor( new MyCellEditor());
        TableColumn colr = Table_rozpatia.getColumnModel().getColumn(0);
        colr.setCellEditor( new MyCellEditor());
        TableColumn colk = Table_kotevne_useky.getColumnModel().getColumn(1);
        colk.setCellEditor( new MyCellEditor());
        
   
        
        // lost focus when not selected auto enter select
        Table_rozpatia.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        Table_rozpatia_nadm_vysky.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        Table_kotevne_useky.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);   
         
        Table_rozpatia.setSurrendersFocusOnKeystroke(true); // for focus on key listener
              
        nacitatDatabazuLan(); 
        mainframeLodaed=true;// fisrt load oc conductr databaze
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // if ju exit window app will not close
        setResizable(true);
               
//vloz prvu hodnotu do RTS nulty index v tabulke
        Object[] Conductor = new Object[7];
            Conductor = Databaza.get(0);
            Variable_RTS = Double.parseDouble(String.valueOf(Conductor[6]))/Double.parseDouble(String.valueOf(Conductor[2]));
           
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
               otherSymbols.setDecimalSeparator('.');
               DecimalFormat df = new DecimalFormat("###.###",otherSymbols);  // definovany počet desatinnych miest
            TextField_RTS.setText(df.format(Variable_RTS));                    
            Variable_Ir50=123456789.987654321;
               
        // vloz defaultne hodnoty do zakladny-mech napatie lana a maximalne zataženie podiel z RTS    
           Variable_zakladne_mech_napatie_lana_pre_minus5=Double.parseDouble(TextField_zakladne_mech_lana_minus5.getText());
           Variable_maximalne_zataz_lana_podiel_z_RTS=Variable_RTS*(Double.parseDouble(TextField_zakladne_mech_lana_minus5.getText())/100);
        
        // setiing importt Variables manually in code
        
        jRadioButton_Klc_1.doClick();
        jRadioButton_Kh_noH.doClick();
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
        Label_vybrana_namrazova_oblast.setText("---");
        // inicializacia KPB
        jComboBox_KPB_typ_terenu.removeAllItems();;
        jComboBox_KPB_typ_terenu.addItem(language.language_label(languageOption, 165));
        jComboBox_KPB_typ_terenu.addItem(language.language_label(languageOption, 166));
        jComboBox_KPB_typ_terenu.addItem(language.language_label(languageOption, 167));
        jComboBox_KPB_typ_terenu.addItem(language.language_label(languageOption, 168));
        jComboBox_KPB_typ_terenu.setSelectedIndex(1);
        jRadioButton_KPB_cas_vypoctu_1_rok.doClick();
        // inicializacia Hcmean
        jRadioButton_with_label_vypoctana.doClick();
        Variable_Hc_mean_medzikrok= 0.0;
        TextField_hcmean_vpocitana.setText("0.0");
        TextField_hcmean_vlastna.setText("0.0");
        // inicializacia Hpercent_pre vypocet_sygmi
        percento_podiel_namrazy_sigma1=30;
        percento_podiel_namrazy_sigma2=40;
        percento_podiel_namrazy_sigma3=50;
        percento_podiel_namrazy_sigma4=70;
         vyp_percento1_sigma.setText( language.language_label(languageOption, 258) );
         vyp_percento2_sigma.setText( language.language_label(languageOption, 258)  );
         vyp_percento3_sigma.setText( language.language_label(languageOption, 258)  );
         vyp_percento4_sigma.setText( language.language_label(languageOption, 258)  );
        
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
        jComboBox_char_terenu.setSelectedIndex(1);
        
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
        tah_konecne1.setText(language.language_label(languageOption, 283) + String.valueOf(jComboBox_stav_KPB.getSelectedItem()) + language.language_label(languageOption, 285));
        //testing Jtable
        
        modeltable_rozpatia.addRow(new Object[]{(String) "-----------------------------------------"});    // nulty riedok
        Table_rozpatia.setRowHeight(0,12);
        
        modeltable_rozpatia.addRow(new Object[0]);
        modeltable_rozpatia_nadm_vysky.addRow(new Object[1]);
        modeltable_rozpatia_nadm_vysky.addRow(new Object[1]);
        Table_rozpatia.setRowHeight(1,24);
        Table_rozpatia_nadm_vysky.setRowHeight(1,24);
        
        // inicializacia_ ARRAZLISTOV?NULOVANIE
        Variable_Ai_dlzka_rozpatia.removeAll(Variable_Ai_dlzka_rozpatia);
        Variable_hi2_nadmorska_vyska_stoziarov.removeAll(Variable_hi2_nadmorska_vyska_stoziarov);
        Variable_hi_vyska_stoziarov.removeAll(Variable_hi_vyska_stoziarov);
        
        Variable_Ai_dlzka_rozpatia.add(0.0);
        Variable_hi_vyska_stoziarov.add(0.0);
        Variable_hi2_nadmorska_vyska_stoziarov.add(0.0);
        Variable_hi_vyska_stoziarov.add(0.0);
        Variable_hi2_nadmorska_vyska_stoziarov.add(0.0);
        
        //Listeneers
 
        Table_kotevne_useky.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (selection_kotevny_usek == false) {

                    int rowNumber = Table_kotevne_useky.getSelectedRow(); //- (e.getFirstIndex()-e.getLastIndex());
                    if (povodna_hodnota_selekcie != rowNumber) {
                        double[] empty = null;
                         double[][] empty2 = null;
                          double[] hodnoty = new double[4];
                         
        try{hodnoty[0] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[0]));}catch(NullPointerException i){hodnoty[0]=0;}
        try{hodnoty[1] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[1]));}catch(NullPointerException i){hodnoty[1]=0;}
        try{hodnoty[2] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[2]));}catch(NullPointerException i){hodnoty[2]=0;}
        try{hodnoty[3] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[3]));}catch(NullPointerException i){hodnoty[3]=0;}
                        kotevnyUsek docasny_kot_usek = new kotevnyUsek(new_kotevny_usek_name, 0,"0", 0, 0, 0, 0, filename, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, teplotyser, first_Start, teplotyser, teplotyser, teplotyser, first_Start, Variable_Ai_array, Variable_Hi_array, Variable_Hi_array, Variable_Hi_array_nmv, 0, 0, 0,hodnoty);
                        mainframe_to_kotevny_usek(docasny_kot_usek,povodna_hodnota_selekcie);
                        Variable_globeal_kotevny_usek.set(povodna_hodnota_selekcie, docasny_kot_usek);
                        mainframe_to_kotevny_usek(docasny_kot_usek,povodna_hodnota_selekcie);

                        kotevn_usek_to_mainframe(Variable_globeal_kotevny_usek.get(rowNumber));

                        
                        set_percento_to_mainframe(Variable_globeal_kotevny_usek.get(rowNumber));
                    }
                    
                    //udate nazvnu pre tabbed panel KPB
                    try {
                        int cislo = Table_kotevne_useky.getSelectedRow();
                        String nazov = Variable_globeal_kotevny_usek.get(cislo).get_name();
                        jTabbedPane1.setTitleAt(1, language.language_label(languageOption, 250) + " - " + nazov);

                    } catch (ArrayIndexOutOfBoundsException p) {
                        jTabbedPane1.setTitleAt(1, language.language_label(languageOption, 250));

                    }

                    povodna_hodnota_selekcie = rowNumber;
                }
//                for( int i=0;i<Variable_globeal_kotevny_usek.size();i++){              
//                    Iwriter(i);
//                    Swriter(Variable_globeal_kotevny_usek.get(i).get_name());
//                    Dwriter(Variable_globeal_kotevny_usek.get(i).get_conductor_number());
//                    Swriter("----------------");
//                }
//                Swriter("---------cycle end---------");
                
            }

        });  //selection listener fot text area to show data

        Table_kotevne_useky.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                
                    try {
                        int cislo = Table_kotevne_useky.getSelectedRow();
                        String nazov = Variable_globeal_kotevny_usek.get(cislo).get_name();
                        jTabbedPane1.setTitleAt(1, language.language_label(languageOption, 250) + " - " + nazov);

                    } catch (ArrayIndexOutOfBoundsException p) {
                        jTabbedPane1.setTitleAt(1, language.language_label(languageOption, 250));

                    }
                

            
        }}
        );
        
        
        
        Table_rozpatia.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tablemodellistener_total == true) {
                    int rowNumber = Table_rozpatia.getSelectedRow(); //- (e.getFirstIndex()-e.getLastIndex());

                    if (rowNumber != 0) {

                        Table_rozpatia_nadm_vysky.removeRowSelectionInterval(0, Table_rozpatia_nadm_vysky.getRowCount() - 1);
                        Table_rozpatia_nadm_vysky.addColumnSelectionInterval(0, 1);
                        Table_rozpatia_nadm_vysky.addRowSelectionInterval(rowNumber - 1, rowNumber);
                    }

                }
            }

        });  //selection listener fot text area to show data

        Table_rozpatia.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                if (tablemodellistener_total == true) {
                    Table_rozpatia.getModel().removeTableModelListener(this);
                    if (tablemodellistener_rozpatia == true) {
                        tablemodellistener_rozpatia = false;
                        tablemodellistener_nad_vysky = false;

                        int row = e.getFirstRow();
                        int column = e.getColumn();

                        if (row != 0) {   // pre nulu nie lebo t je nulty riadok a tam je medzera
                            //prepisuje row -1 lebo tam sa nachadza hodnota  kedže nulty riadok je prazdny vždy
                            Variable_Ai_dlzka_rozpatia.set(row - 1, doubleChecker_tableinput(String.valueOf(Table_rozpatia.getValueAt(row, column))));

                            if (row == Table_rozpatia.getRowCount() - 1) { // ak klinuty posledny riadok tak
                                Variable_Ai_dlzka_rozpatia.add(0.0);
                                Variable_hi_vyska_stoziarov.add(0.0);
                                Variable_hi2_nadmorska_vyska_stoziarov.add(0.0);//pridar row vsšade do každe arraylist               
                                modeltable_rozpatia_nadm_vysky.addRow(new Object[1]);
                                modeltable_rozpatia.addRow(new Object[0]);
                            }

                        }
                    }
                    
                    try {String hodnota1 =String.valueOf(Table_rozpatia.getValueAt(Table_rozpatia.getEditingRow(), 0));
                     hodnota1=hodnota1.replace(",", ".");
                     Table_rozpatia.setValueAt(hodnota1,Table_rozpatia.getEditingRow(), 0);
                     double value;
                       try{
                      value = Double.parseDouble(String.valueOf(hodnota1));      
        
                      }catch(NumberFormatException | NullPointerException p){ 
         
                      if(Table_rozpatia.getSelectedRow() == 0){}
                      else{ 
                      Table_rozpatia.setValueAt("0.0",Table_rozpatia.getEditingRow(), 0);

                          
                      }
                      }
                    }catch(ArrayIndexOutOfBoundsException p){}
                    
                    
                    tablemodellistener_rozpatia = true;
                    tablemodellistener_nad_vysky = true;
                    Table_rozpatia.getModel().addTableModelListener(this);

//                System.out.println(Variable_Ai_dlzka_rozpatia);
//                System.out.println(Variable_hi_vyska_stoziarov);
//                System.out.println(Variable_hi2_nadmorska_vyska_stoziarov);
                    int odcitacac_rozpati = 0;
                    for (int i = 0; i < Variable_Ai_dlzka_rozpatia.size(); i++) {           //odstranuje chybne zapisi alebo prazdne hodnoty pre rozpatia

                        if (Variable_Ai_dlzka_rozpatia.get(i) == 0.0 || Variable_Ai_dlzka_rozpatia.get(i) == 123456789.987654321) {
                            odcitacac_rozpati = odcitacac_rozpati + 1;
                        }
                    }

                    Variable_n_pocet_rozpati = (Variable_Ai_dlzka_rozpatia.size() - odcitacac_rozpati);

                    // naplnenie AI array rozpatia na zaklade postu rozpati a deltaHi ktore ma rovnaky rozmer ako Ai
                    Variable_Ai_array = new double[(int) Variable_n_pocet_rozpati];
                    Variable_DeltaHi_array = new double[(int) Variable_n_pocet_rozpati];
                    Variable_Hi_array = new double[(int) Variable_n_pocet_rozpati + 1];
                    Variable_Hi_array_nmv = new double[(int) Variable_n_pocet_rozpati + 1];
                    for (int i = 0; i < Variable_Ai_array.length; i++) {

                        Variable_Ai_array[i] = Variable_Ai_dlzka_rozpatia.get(i);
                        Variable_Hi_array[i] = Variable_hi_vyska_stoziarov.get(i);
                        Variable_Hi_array_nmv[i] = Variable_hi2_nadmorska_vyska_stoziarov.get(i);

                        double prvavyska_stoziar_plus_zem = Variable_hi2_nadmorska_vyska_stoziarov.get(i) + Variable_hi_vyska_stoziarov.get(i);
                        double druhavyska_stoziar_plus_zem = Variable_hi2_nadmorska_vyska_stoziarov.get(i + 1) + Variable_hi_vyska_stoziarov.get(i + 1);
                        Variable_DeltaHi_array[i] = druhavyska_stoziar_plus_zem - prvavyska_stoziar_plus_zem;
                    }
                    Variable_Hi_array[Variable_Ai_array.length] = Variable_hi_vyska_stoziarov.get(Variable_Ai_array.length);
                    Variable_Hi_array_nmv[Variable_Ai_array.length] = Variable_hi2_nadmorska_vyska_stoziarov.get(Variable_Ai_array.length);
                    Variable_Ai_array=double_setter_array(Variable_Ai_array);
                    pretazenia_intomainframe();
                    
                    // Hcmean pocitac
                    double Sumar_scitavac = 0;
                    for (int i = 0; i < Variable_n_pocet_rozpati ; i++) {           //pocita len tam kde je zadana dlka zorpatia ine stožiare bdue ignotrovat plus jedna preto lebo pocet stožiarov je vždy rozpatia plus 1

                        Sumar_scitavac = Sumar_scitavac + ((Variable_hi_vyska_stoziarov.get(i)+ Variable_hi_vyska_stoziarov.get(i+1))/2);

                    }

                    Variable_Hc_mean_medzikrok = Sumar_scitavac / (Variable_n_pocet_rozpati);
                    if (jRadioButton_with_label_vypoctana.isSelected() == true) {
                        Variable_Hc_mean =double_setter( Variable_Hc_mean_medzikrok);
                        pretazenia_intomainframe();
                    }
                    DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
                    otherSymbols.setDecimalSeparator('.');
                    DecimalFormat df = new DecimalFormat("###.###", otherSymbols);
                    TextField_hcmean_vpocitana.setText(String.valueOf(df.format(Variable_Hc_mean_medzikrok))); // vloz do text field pri radio buttne
                    //Stredne rozpatie
                    if (Variable_Ai_array.length != 0 || Variable_DeltaHi_array.length != 0) {
                        mid_span_flat();
                        mid_span_terrain();

                        TextField_STRrozpatie_klasicky.setText(String.valueOf(df.format(Variable_mid_span_docasna)));
                        TextField_STRrozpatie_sPrevisenim.setText(String.valueOf(df.format(Variable_mid_span_terrain_docasna)));
                        if (jRadioButton_with_label_rozpatie_klasicky.isSelected() == true) {
                            Variable_mid_span = Variable_mid_span_docasna;
                        }
                        if (jRadioButton_with_label_rozpate_previsenia.isSelected() == true) {
                            Variable_mid_span = Variable_mid_span_terrain_docasna;
                        }
                    }

                }

            }
        });
        
        Table_rozpatia_nadm_vysky.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                if (tablemodellistener_total == true) {
                    Table_rozpatia_nadm_vysky.getModel().removeTableModelListener(this);
                    if (tablemodellistener_nad_vysky == true) {
                        tablemodellistener_rozpatia = false;
                        tablemodellistener_nad_vysky = false;

                        int row = e.getFirstRow();
                        int column = e.getColumn();

                        //prepisuje row -1 lebo tam sa nachadza hodnota  kedže nulty riadok je prazdny vždy
                        
                     
                        if (row == Table_rozpatia_nadm_vysky.getRowCount() - 1) { // ak klinuty posledny riadok tak
                            Variable_Ai_dlzka_rozpatia.add(0.0);
                            Variable_hi_vyska_stoziarov.add(0.0);
                            Variable_hi2_nadmorska_vyska_stoziarov.add(0.0);//pridar row vsšade do každe arraylist
                            modeltable_rozpatia.addRow(new Object[0]);
                            modeltable_rozpatia_nadm_vysky.addRow(new Object[1]);
                        }
                        
                          Variable_hi_vyska_stoziarov.set(row, doubleChecker_tableinput(String.valueOf(Table_rozpatia_nadm_vysky.getValueAt(row, 1))));
                        Variable_hi2_nadmorska_vyska_stoziarov.set(row, doubleChecker_tableinput(String.valueOf(Table_rozpatia_nadm_vysky.getValueAt(row, 0))));


                    }
                    
                    //replace , for .
                    
                     try {String hodnota1 =String.valueOf(Table_rozpatia_nadm_vysky.getValueAt(Table_rozpatia_nadm_vysky.getEditingRow(), Table_rozpatia_nadm_vysky.getEditingColumn()));
                     hodnota1=hodnota1.replace(",", ".");
                     Table_rozpatia_nadm_vysky.setValueAt(hodnota1,Table_rozpatia_nadm_vysky.getEditingRow(), Table_rozpatia_nadm_vysky.getEditingColumn());
                     //check.
                     double value;
                       try{
                      value = Double.parseDouble(String.valueOf(hodnota1));      
        
                      }catch(NumberFormatException | NullPointerException p){ 

                      Table_rozpatia_nadm_vysky.setValueAt("0.0",Table_rozpatia_nadm_vysky.getEditingRow(), Table_rozpatia_nadm_vysky.getEditingColumn());
           
                      }
                     }catch(ArrayIndexOutOfBoundsException p){}
                    
                    tablemodellistener_rozpatia = true;
                    tablemodellistener_nad_vysky = true;
                    Table_rozpatia_nadm_vysky.getModel().addTableModelListener(this);

                    
                    
                    
                    // Hcmean pocitac
                    double Sumar_scitavac = 0;
                    for (int i = 0; i < Variable_n_pocet_rozpati ; i++) {           //pocita len tam kde je zadana dlka zorpatia ine stožiare bdue ignotrovat plus jedna preto lebo pocet stožiarov je vždy rozpatia plus 1

                        Sumar_scitavac = Sumar_scitavac + ((Variable_hi_vyska_stoziarov.get(i)+ Variable_hi_vyska_stoziarov.get(i+1))/2);

                    }

                    Variable_Hc_mean_medzikrok = Sumar_scitavac / (Variable_n_pocet_rozpati);
                    DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
                    otherSymbols.setDecimalSeparator('.');
                    DecimalFormat df = new DecimalFormat("###.###", otherSymbols);
                        // vypocitaj Hcmena
                    TextField_hcmean_vpocitana.setText(String.valueOf(df.format(Variable_Hc_mean_medzikrok))); // vloz do text field pri radio buttne

                    if (jRadioButton_with_label_vypoctana.isSelected() == true) {
                        Variable_Hc_mean =double_setter( Variable_Hc_mean_medzikrok);
                                pretazenia_intomainframe();
                    }
                    // naplnenie AI array rozpatia na zaklade postu rozpati a deltaHi ktore ma rovnaky rozmer ako Ai
                    Variable_Ai_array = new double[(int) Variable_n_pocet_rozpati];
                    Variable_DeltaHi_array = new double[(int) Variable_n_pocet_rozpati];
                    Variable_Hi_array = new double[(int) Variable_n_pocet_rozpati + 1];
                    Variable_Hi_array_nmv = new double[(int) Variable_n_pocet_rozpati + 1];
                    for (int i = 0; i < Variable_Ai_array.length; i++) {

                        Variable_Ai_array[i] = Variable_Ai_dlzka_rozpatia.get(i);
                        Variable_Hi_array[i] = Variable_hi_vyska_stoziarov.get(i);
                        Variable_Hi_array_nmv[i] = Variable_hi2_nadmorska_vyska_stoziarov.get(i);

                        double prvavyska_stoziar_plus_zem = Variable_hi2_nadmorska_vyska_stoziarov.get(i) + Variable_hi_vyska_stoziarov.get(i);
                        double druhavyska_stoziar_plus_zem = Variable_hi2_nadmorska_vyska_stoziarov.get(i + 1) + Variable_hi_vyska_stoziarov.get(i + 1);
                        Variable_DeltaHi_array[i] = druhavyska_stoziar_plus_zem - prvavyska_stoziar_plus_zem;
                    }
                    Variable_Hi_array[Variable_Ai_array.length] = Variable_hi_vyska_stoziarov.get(Variable_Ai_array.length);
                    Variable_Hi_array_nmv[Variable_Ai_array.length] = Variable_hi2_nadmorska_vyska_stoziarov.get(Variable_Ai_array.length);

                    Variable_Ai_array=double_setter_array(Variable_Ai_array); pretazenia_intomainframe();
                   
                    
                    //ak niesu array nulove vypočitaj vyšlu
                    if (Variable_Ai_array.length != 0 || Variable_DeltaHi_array.length != 0) {
                        mid_span_flat();
                        mid_span_terrain();

                        TextField_STRrozpatie_klasicky.setText(String.valueOf(df.format(Variable_mid_span_docasna)));
                        TextField_STRrozpatie_sPrevisenim.setText(String.valueOf(df.format(Variable_mid_span_terrain_docasna)));
                        if (jRadioButton_with_label_rozpatie_klasicky.isSelected() == true) {
                            Variable_mid_span = Variable_mid_span_docasna;
                        }
                        if (jRadioButton_with_label_rozpate_previsenia.isSelected() == true) {
                            Variable_mid_span = Variable_mid_span_terrain_docasna;
                        }
                    }


                }

            }
        });

        
        // add first row
        
        Button_Icon_arr_row_table_kotevny_usek.doClick();
        
        
        setDefaultCloseOperation(mainframe_17.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            really_close_mainframe=false;
            really_close_mainframe_save=false;
            
            warning_sign2but("Si kokot?");
            
            
            if(really_close_mainframe == true && really_close_mainframe_save == false ){
              
                isOpen=false;
                e.getWindow().dispose();
            } 
            
            if(really_close_mainframe == true && really_close_mainframe_save == true ){
              
                Button_Icon_save.doClick();
                isOpen=false;
                e.getWindow().dispose();
            } 
            
            if(really_close_mainframe == false && really_close_mainframe_save == false){
             
            }    
                
                
            }
        });
        
        
        // IF LOADding 
        
        if(loaded_file== true){
        
        String no= "";
        load_project(no);   
        setTitle(project_name);
        Label_status.setText(language.language_label(languageOption, 272));   
        
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
        jPanel15 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Button_Icon_save = new javax.swing.JButton();
        Button_Icon_save_as = new javax.swing.JButton();
        Button_Icon_delete_row_table_kotevny_usek = new javax.swing.JButton();
        Button_Icon_arr_row_table_kotevny_usek = new javax.swing.JButton();
        Button_Icon_calculate = new javax.swing.JButton();
        Button_Icon_export_PDF_internal = new javax.swing.JButton();
        Button_Icon_select_all_kotevny_usek = new javax.swing.JButton();
        Button_Icon_deselect_all_kotevny_usek = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Label_status = new javax.swing.JLabel();
        Button_Icon_export_PDF_external = new javax.swing.JButton();
        Button_Icon_export_PDF2_save_as = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel18 = new javax.swing.JPanel();
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
        jTextField_nazov_nazov_stavby1 = new javax.swing.JTextField();
        jTextField_nazov_SOPS1 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        Label_KPB = new javax.swing.JLabel();
        Label_KPB_typ_terenu = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPane_KPB_typ_terenu = new javax.swing.JTextArea();
        jRadioButton_KPB_cas_vypoctu_1_rok = new javax.swing.JRadioButton();
        jRadioButton_KPB_cas_vypoctu_prechodne = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        Label_stredne_rozpatie = new javax.swing.JLabel();
        jRadioButton_with_label_rozpatie_klasicky = new javax.swing.JRadioButton();
        jRadioButton_with_label_rozpate_previsenia = new javax.swing.JRadioButton();
        TextField_STRrozpatie_klasicky = new javax.swing.JTextField();
        TextField_STRrozpatie_sPrevisenim = new javax.swing.JTextField();
        Label_RTS_velicina5 = new javax.swing.JLabel();
        Label_RTS_velicina6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        TextField_pretazenia_stav1 = new javax.swing.JTextField();
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
        Label_pretazenia = new javax.swing.JLabel();
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
        Label_tabulky2 = new javax.swing.JLabel();
        jRadioButton_with_label_pociatocne1 = new javax.swing.JRadioButton();
        jPanel19 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        Label_uroven_spolahlivosti = new javax.swing.JLabel();
        Label__stredna_rocna_teplota = new javax.swing.JLabel();
        TextField_srt_roc_teplota = new javax.swing.JTextField();
        Label_RTS_velicina7 = new javax.swing.JLabel();
        Label__stav_KPB = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        Label_kotevne_useky1 = new javax.swing.JLabel();
        jComboBox_conductor_chooser = new javax.swing.JComboBox<>();
        Label_RTS = new javax.swing.JLabel();
        TextField_RTS = new javax.swing.JTextField();
        Label_RTS_velicina = new javax.swing.JLabel();
        Label_zakladne_mech_napatie_minis5 = new javax.swing.JLabel();
        Label_max_zataz_lana = new javax.swing.JLabel();
        TextField_zakladne_mech_lana_minus5 = new javax.swing.JTextField();
        TextField_max_mech_podiel_z_RTS = new javax.swing.JTextField();
        Label_RTS_velicina2 = new javax.swing.JLabel();
        Label_RTS_velicina3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Label_vetrova_oblast = new javax.swing.JLabel();
        Label_základna_rychlost_vetra = new javax.swing.JLabel();
        TextField_Vmean_0 = new javax.swing.JTextField();
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
        Label_RTS_velicina8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jRadioButton_with_label_vlastna = new javax.swing.JRadioButton();
        TextField_hcmean_vlastna = new javax.swing.JTextField();
        TextField_hcmean_vpocitana = new javax.swing.JTextField();
        jRadioButton_with_label_vypoctana = new javax.swing.JRadioButton();
        Label_stredna_vyska_vodicov_nad_terenom = new javax.swing.JLabel();
        Label_RTS_velicina9 = new javax.swing.JLabel();
        Label_RTS_velicina10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        Label_vybrana_namrazova_oblast = new javax.swing.JLabel();
        Button_namrazova_oblast = new javax.swing.JButton();
        Label_typ_namrazy = new javax.swing.JLabel();
        Label__typ_namrazy_Ccl = new javax.swing.JLabel();
        TextField_Ccl = new javax.swing.JTextField();
        Label_hustota_namrazy = new javax.swing.JLabel();
        TextField_hustota_namrazy = new javax.swing.JTextField();
        Label_RTS_velicina1 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jRadioButton_Klc_1 = new javax.swing.JRadioButton();
        jRadioButton_Klc_vlastna = new javax.swing.JRadioButton();
        jRadioButton_Kh_noH = new javax.swing.JRadioButton();
        Label__Klc = new javax.swing.JLabel();
        Label__Kh = new javax.swing.JLabel();
        TextField_Kcl = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        TextField_Bi2 = new javax.swing.JTextField();
        jRadioButton_Bi_2 = new javax.swing.JRadioButton();
        TextField_Bi_1 = new javax.swing.JTextField();
        jRadioButton_Bi_1 = new javax.swing.JRadioButton();
        Label__Bi = new javax.swing.JLabel();
        TextField_Kh_noH = new javax.swing.JTextField();
        jRadioButton_Kh_H = new javax.swing.JRadioButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        percento1_sigma = new javax.swing.JTextField();
        percento2_sigma = new javax.swing.JTextField();
        percento3_sigma = new javax.swing.JTextField();
        percento4_sigma = new javax.swing.JTextField();
        vyp_percento1_sigma = new javax.swing.JTextField();
        vyp_percento2_sigma = new javax.swing.JTextField();
        vyp_percento3_sigma = new javax.swing.JTextField();
        vyp_percento4_sigma = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_rozpatia = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_rozpatia_nadm_vysky = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_kotevne_useky = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        Table_KPB = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        Table_tahy = new javax.swing.JTable();
        tah_konecne = new javax.swing.JLabel();
        tah_konecne1 = new javax.swing.JLabel();
        podiel_z_RTS1 = new javax.swing.JTextField();
        podiel_z_RTS2 = new javax.swing.JTextField();
        Label_RTS_velicina11 = new javax.swing.JLabel();
        Label_RTS_velicina12 = new javax.swing.JLabel();
        Label_RTS_velicina13 = new javax.swing.JLabel();
        Label_RTS_velicina14 = new javax.swing.JLabel();
        tah_konecne2 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Button_pdf_pageup = new javax.swing.JButton();
        Button_pdf_pagedown = new javax.swing.JButton();
        Textfield_cas = new javax.swing.JTextField();
        label_cas_vytvorenia = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(project_name);
        setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        Button_Icon_export_PDF_internal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/PDF-24.png"))); // NOI18N
        Button_Icon_export_PDF_internal.setFocusable(false);
        Button_Icon_export_PDF_internal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Icon_export_PDF_internal.setPreferredSize(new java.awt.Dimension(48, 48));
        Button_Icon_export_PDF_internal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_Icon_export_PDF_internal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Icon_export_PDF_internalActionPerformed(evt);
            }
        });
        Button_Icon_export_PDF_internal.setToolTipText(language.language_label(languageOption, 263));

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

        jLabel1.setText("Status : ");

        Label_status.setText("jLabel2");

        Button_Icon_export_PDF_external.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/PDFexternal-24.png"))); // NOI18N
        Button_Icon_export_PDF_external.setFocusable(false);
        Button_Icon_export_PDF_external.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Icon_export_PDF_external.setPreferredSize(new java.awt.Dimension(48, 48));
        Button_Icon_export_PDF_external.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_Icon_export_PDF_external.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Icon_export_PDF_externalActionPerformed(evt);
            }
        });
        Button_Icon_export_PDF_external.setToolTipText(language.language_label(languageOption, 264));

        Button_Icon_export_PDF2_save_as.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/PDFas-24.png"))); // NOI18N
        Button_Icon_export_PDF2_save_as.setFocusable(false);
        Button_Icon_export_PDF2_save_as.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Icon_export_PDF2_save_as.setPreferredSize(new java.awt.Dimension(48, 48));
        Button_Icon_export_PDF2_save_as.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_Icon_export_PDF2_save_as.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Icon_export_PDF2_save_asActionPerformed(evt);
            }
        });
        Button_Icon_export_PDF2_save_as.setToolTipText(language.language_label(languageOption, 265));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Button_Icon_save, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Icon_save_as, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
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
                .addComponent(Button_Icon_export_PDF_internal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(Button_Icon_export_PDF_external, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Icon_export_PDF2_save_as, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Label_status, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Button_Icon_save_as, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_Icon_save, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Button_Icon_export_PDF2_save_as, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_Icon_export_PDF_external, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Button_Icon_select_all_kotevny_usek, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Button_Icon_deselect_all_kotevny_usek, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Button_Icon_delete_row_table_kotevny_usek, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Button_Icon_arr_row_table_kotevny_usek, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Button_Icon_calculate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Button_Icon_export_PDF_internal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_status, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        jPanel18.setAutoscrolls(true);

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));
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

        jTextField_nazov_nazov_stavby1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nazov_nazov_stavby1ActionPerformed(evt);
            }
        });

        jTextField_nazov_SOPS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nazov_SOPS1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Label_hlavicka_vypocet_podla_normi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Label_hlavicka_nazov, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_nazov_normi, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Label_hlavicka_nadpis_pre_prechodne, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_nadpis_pre_prechodna, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_hlavicka_vypracoval, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Label_hlavicka_SOPS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(Label_hlavicka_stavba, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(Label_hlavicka_arch_cislo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_nazov_arch_cislo)
                            .addComponent(jTextField_nazov_SOPS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                            .addComponent(jTextField_nazov_nazov_stavby, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField_vypracoval))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(Label_hlavicka_datum, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_datum, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(Label_hlavicka_cislo_strany, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_nazov_cislo_strany, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField_nazov_SOPS1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                                .addComponent(jTextField_nazov_nazov_stavby1)))))
                .addContainerGap())
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
                    .addComponent(jTextField_nazov_nazov_stavby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_nazov_nazov_stavby1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_hlavicka_SOPS)
                    .addComponent(jTextField_nazov_SOPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_nazov_SOPS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(Label_hlavicka_datum)
                    .addComponent(jTextField_datum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(0, 153, 153));
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
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_KPB_cas_vypoctu_prechodne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(Label_KPB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_KPB_typ_terenu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButton_KPB_cas_vypoctu_1_rok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Label_KPB_typ_terenu, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_KPB)
                            .addComponent(jComboBox_KPB_typ_terenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Label_KPB_typ_terenu)
                        .addGap(6, 6, 6)
                        .addComponent(jRadioButton_KPB_cas_vypoctu_1_rok)
                        .addGap(10, 10, 10)
                        .addComponent(jRadioButton_KPB_cas_vypoctu_prechodne)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(0, 153, 153));
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
        TextField_STRrozpatie_klasicky.setEnabled(false);
        TextField_STRrozpatie_klasicky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_STRrozpatie_klasickyActionPerformed(evt);
            }
        });

        TextField_STRrozpatie_sPrevisenim.setEditable(false);
        TextField_STRrozpatie_sPrevisenim.setText("0.0");
        TextField_STRrozpatie_sPrevisenim.setEnabled(false);
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
                .addGap(12, 12, 12)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Label_stredne_rozpatie, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_with_label_rozpatie_klasicky, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextField_STRrozpatie_klasicky, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Label_RTS_velicina5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton_with_label_rozpate_previsenia, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextField_STRrozpatie_sPrevisenim, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Label_RTS_velicina6)
                .addGap(80, 80, 80))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(Label_stredne_rozpatie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextField_STRrozpatie_sPrevisenim, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Label_RTS_velicina6)
                        .addComponent(jRadioButton_with_label_rozpate_previsenia, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton_with_label_rozpatie_klasicky, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TextField_STRrozpatie_klasicky, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Label_RTS_velicina5)))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

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

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
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
                .addGap(0, 1, Short.MAX_VALUE))
        );

        Label_pretazenia.setText(language.language_label(languageOption, 158));

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
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(Label_pretazenia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton_with_pretazenia_vypocitana)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton_with_pretazenia_vlastna))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(TextField_pretazenia_stav1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_pretazenia_stav2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                .addComponent(TextField_pretazenia_stav14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Label_pretazenia)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton_with_pretazenia_vypocitana)
                        .addComponent(jRadioButton_with_pretazenia_vlastna)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));
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
        TextField_tabulky_prechodna.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_tabulky_prechodnaKeyReleased(evt);
            }
        });

        TextField_tabulky_konecna.setText("50.0");
        TextField_tabulky_konecna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_tabulky_konecnaActionPerformed(evt);
            }
        });
        TextField_tabulky_konecna.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_tabulky_konecnaKeyReleased(evt);
            }
        });

        Label_tabulky2.setText(language.language_label(languageOption, 101));

        buttonGroup_tabulka.add(jRadioButton_with_label_pociatocne1);
        jRadioButton_with_label_pociatocne1.setText(language.language_label(languageOption, 262));
        jRadioButton_with_label_pociatocne1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_with_label_pociatocne1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(Label_tabulky, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton_with_label_pociatocne, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButton_with_label_pociatocne1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(TextField_tabulky_prechodna, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Label_tabulky2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButton_with_label_prechodne, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(TextField_tabulky_konecna, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Label_tabulky1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButton_with_label_konecne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_tabulky)
                    .addComponent(jRadioButton_with_label_prechodne, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_with_label_pociatocne, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_with_label_konecne, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextField_tabulky_prechodna, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_tabulky_konecna, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_tabulky1)
                    .addComponent(Label_tabulky2)
                    .addComponent(jRadioButton_with_label_pociatocne1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                    .addComponent(jComboBox_uroven_splahlivosti, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Label_uroven_spolahlivosti, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label__stredna_rocna_teplota, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label__stav_KPB, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(TextField_srt_roc_teplota, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Label_RTS_velicina7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jComboBox_stav_KPB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
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
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Label_zakladne_mech_napatie_minis5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                    .addComponent(Label_RTS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Label_max_zataz_lana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextField_RTS, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(TextField_zakladne_mech_lana_minus5, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                                        .addComponent(TextField_max_mech_podiel_z_RTS)))
                                .addGap(2, 2, 2)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Label_RTS_velicina3)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(Label_RTS_velicina)
                                            .addComponent(Label_RTS_velicina2))))
                                .addGap(0, 5, Short.MAX_VALUE))
                            .addComponent(jComboBox_conductor_chooser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Label_kotevne_useky1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
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
                .addGap(15, 15, 15))
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

        Label_RTS_velicina8.setText("m/s");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(Label_char_terenu, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_char_terenu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(Label_vetrova_oblast)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox_vetrova_oblast, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(57, 57, 57))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_základna_rychlost_vetra, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(Label_vetrova_oblast_Cdir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton_vetrova_oblast_Cdir_1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton_vetrova_oblast_Cdir_vlastna)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(TextField_Vmean_0, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Label_RTS_velicina8))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(TextField_vetrova_oblast_Cdir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Label_vetrova_oblast_C0, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRadioButton_vetrova_oblast_C0_1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRadioButton_vetrova_oblast_C0_vlastna)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TextField_vetrova_oblast_C0, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(Label__char_terenu_kr, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(TextField_Kr, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Label_char_terenu_zo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(TextField_dlzka_drsnjosti_zo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_vetrova_oblast)
                    .addComponent(jComboBox_vetrova_oblast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_základna_rychlost_vetra)
                    .addComponent(TextField_Vmean_0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_RTS_velicina8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton_vetrova_oblast_Cdir_1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Label_vetrova_oblast_Cdir))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextField_vetrova_oblast_Cdir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton_vetrova_oblast_C0_1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Label_vetrova_oblast_C0, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButton_vetrova_oblast_C0_vlastna, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_vetrova_oblast_C0, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_vetrova_oblast_Cdir_vlastna, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_char_terenu)
                    .addComponent(jComboBox_char_terenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label__char_terenu_kr)
                    .addComponent(TextField_Kr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_char_terenu_zo)
                    .addComponent(TextField_dlzka_drsnjosti_zo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        TextField_hcmean_vpocitana.setEnabled(false);
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

        Label_RTS_velicina9.setText("m");

        Label_RTS_velicina10.setText("m");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(Label_stredna_vyska_vodicov_nad_terenom, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton_with_label_vypoctana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton_with_label_vlastna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextField_hcmean_vpocitana, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextField_hcmean_vlastna, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_RTS_velicina9)
                            .addComponent(Label_RTS_velicina10))
                        .addGap(21, 21, 21))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(Label_stredna_vyska_vodicov_nad_terenom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextField_hcmean_vpocitana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_with_label_vypoctana)
                    .addComponent(Label_RTS_velicina9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_with_label_vlastna)
                    .addComponent(TextField_hcmean_vlastna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_RTS_velicina10))
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

        jPanel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        buttonGroup_klc.add(jRadioButton_Klc_1);
        jRadioButton_Klc_1.setText("1");
        jRadioButton_Klc_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Klc_1ActionPerformed(evt);
            }
        });

        buttonGroup_klc.add(jRadioButton_Klc_vlastna);
        jRadioButton_Klc_vlastna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Klc_vlastnaActionPerformed(evt);
            }
        });

        buttonGroup_kh.add(jRadioButton_Kh_noH);
        jRadioButton_Kh_noH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Kh_noHActionPerformed(evt);
            }
        });
        jRadioButton_Kh_noH.setToolTipText(language.language_label(languageOption, 292));

        Label__Klc.setText(language.language_label(languageOption, 102));
        Label__Klc.setToolTipText(language.language_label(languageOption, 103));

        Label__Kh.setText(language.language_label(languageOption, 104));
        Label__Kh.setToolTipText(language.language_label(languageOption, 105));

        TextField_Kcl.setText("1.0");
        TextField_Kcl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_KclActionPerformed(evt);
            }
        });
        TextField_Kcl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_KclKeyReleased(evt);
            }
        });

        TextField_Bi2.setEditable(false);
        TextField_Bi2.setText("0.707");
        TextField_Bi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_Bi2ActionPerformed(evt);
            }
        });
        TextField_Bi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_Bi2KeyReleased(evt);
            }
        });

        buttonGroup_Bi.add(jRadioButton_Bi_2);
        jRadioButton_Bi_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Bi_2ActionPerformed(evt);
            }
        });
        jRadioButton_Bi_2.setToolTipText(language.language_label(languageOption, 296));

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
        TextField_Bi_1.setToolTipText(language.language_label(languageOption, 295));

        buttonGroup_Bi.add(jRadioButton_Bi_1);
        jRadioButton_Bi_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Bi_1ActionPerformed(evt);
            }
        });
        jRadioButton_Bi_1.setToolTipText(language.language_label(languageOption, 295));

        Label__Bi.setText(language.language_label(languageOption, 106));
        Label__Bi.setToolTipText(language.language_label(languageOption, 107));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label__Bi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jRadioButton_Bi_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextField_Bi_1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton_Bi_2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextField_Bi2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label__Bi)
                    .addComponent(jRadioButton_Bi_1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_Bi_2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_Bi_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_Bi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TextField_Kh_noH.setText("1.0");
        TextField_Kh_noH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_Kh_noHActionPerformed(evt);
            }
        });
        TextField_Kh_noH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextField_Kh_noHKeyReleased(evt);
            }
        });

        buttonGroup_kh.add(jRadioButton_Kh_H);
        jRadioButton_Kh_H.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Kh_HActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Label__Klc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jRadioButton_Klc_1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton_Klc_vlastna)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_Kcl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(Label__Kh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton_Kh_noH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_Kh_noH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton_Kh_H)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_Klc_vlastna, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton_Klc_1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Label__Klc))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextField_Kcl, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Label__Kh))
                    .addComponent(jRadioButton_Kh_noH, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_Kh_noH, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_Kh_H, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jRadioButton_Kh_H.setToolTipText(language.language_label(languageOption, 293));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_vybrana_namrazova_oblast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Label_typ_namrazy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(Label__typ_namrazy_Ccl)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(TextField_Ccl, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Label_hustota_namrazy, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TextField_hustota_namrazy, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Label_RTS_velicina1))
                                    .addComponent(jComboBox_druh_namrazy, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(Button_namrazova_oblast, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_vybrana_namrazova_oblast)
                    .addComponent(Button_namrazova_oblast))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(0, 153, 153));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText(language.language_label(languageOption, 248));

        jLabel3.setText(language.language_label(languageOption, 249));

        percento1_sigma.setText("30");
        percento1_sigma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                percento1_sigmaActionPerformed(evt);
            }
        });
        percento1_sigma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                percento1_sigmaKeyReleased(evt);
            }
        });

        percento2_sigma.setText("40");
        percento2_sigma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                percento2_sigmaActionPerformed(evt);
            }
        });
        percento2_sigma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                percento2_sigmaKeyReleased(evt);
            }
        });

        percento3_sigma.setText("50");
        percento3_sigma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                percento3_sigmaActionPerformed(evt);
            }
        });
        percento3_sigma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                percento3_sigmaKeyReleased(evt);
            }
        });

        percento4_sigma.setText("70");
        percento4_sigma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                percento4_sigmaActionPerformed(evt);
            }
        });
        percento4_sigma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                percento4_sigmaKeyReleased(evt);
            }
        });

        vyp_percento1_sigma.setEditable(false);
        vyp_percento1_sigma.setText("0");
        vyp_percento1_sigma.setEnabled(false);
        vyp_percento1_sigma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vyp_percento1_sigmaActionPerformed(evt);
            }
        });
        vyp_percento1_sigma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vyp_percento1_sigmaKeyReleased(evt);
            }
        });

        vyp_percento2_sigma.setEditable(false);
        vyp_percento2_sigma.setText("0");
        vyp_percento2_sigma.setEnabled(false);
        vyp_percento2_sigma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vyp_percento2_sigmaActionPerformed(evt);
            }
        });
        vyp_percento2_sigma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vyp_percento2_sigmaKeyReleased(evt);
            }
        });

        vyp_percento3_sigma.setEditable(false);
        vyp_percento3_sigma.setText("0");
        vyp_percento3_sigma.setEnabled(false);
        vyp_percento3_sigma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vyp_percento3_sigmaActionPerformed(evt);
            }
        });
        vyp_percento3_sigma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vyp_percento3_sigmaKeyReleased(evt);
            }
        });

        vyp_percento4_sigma.setEditable(false);
        vyp_percento4_sigma.setText("0");
        vyp_percento4_sigma.setEnabled(false);
        vyp_percento4_sigma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vyp_percento4_sigmaActionPerformed(evt);
            }
        });
        vyp_percento4_sigma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vyp_percento4_sigmaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(percento1_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(percento2_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(percento3_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(percento4_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(vyp_percento1_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vyp_percento2_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vyp_percento3_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vyp_percento4_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(percento1_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(percento2_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(percento3_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(percento4_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vyp_percento1_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vyp_percento2_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vyp_percento3_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vyp_percento4_sigma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel12.setBackground(new java.awt.Color(0, 153, 153));
        jPanel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

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
        Table_rozpatia.setColumnSelectionAllowed(true);
        Table_rozpatia.setRowHeight(24);
        Table_rozpatia.setRowMargin(2);
        Table_rozpatia.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                Table_rozpatiaInputMethodTextChanged(evt);
            }
        });
        Table_rozpatia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Table_rozpatiaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Table_rozpatiaKeyTyped(evt);
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
        Table_rozpatia_nadm_vysky.setRowHeight(24);
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
        Table_kotevne_useky.setCellSelectionEnabled(true);
        Table_kotevne_useky.setRowHeight(24);
        Table_kotevne_useky.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Table_kotevne_useky.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Table_kotevne_useky);
        Table_kotevne_useky.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (Table_kotevne_useky.getColumnModel().getColumnCount() > 0) {
            Table_kotevne_useky.getColumnModel().getColumn(0).setResizable(false);
            Table_kotevne_useky.getColumnModel().getColumn(0).setPreferredWidth(5);
            Table_kotevne_useky.getColumnModel().getColumn(1).setResizable(false);
            Table_kotevne_useky.getColumnModel().getColumn(1).setPreferredWidth(200);
            Table_kotevne_useky.getColumnModel().getColumn(1).setHeaderValue(language.language_label(languageOption,51));
        }

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab1", jPanel12);

        jPanel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        Table_KPB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table_KPB.setToolTipText("1--kokotbačov \\n\\r  1--kokotbačov \\n\\r 1--kokotbačov \\n\\r 1--kokotbačov ");
        Table_KPB.setRowHeight(24);
        Table_KPB.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Table_KPBMouseMoved(evt);
            }
        });
        Table_KPB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Table_KPBMouseEntered(evt);
            }
        });
        jScrollPane8.setViewportView(Table_KPB);
        if (Table_KPB.getColumnModel().getColumnCount() > 0) {
            Table_KPB.getColumnModel().getColumn(0).setResizable(false);
            Table_KPB.getColumnModel().getColumn(0).setPreferredWidth(85);
            Table_KPB.getColumnModel().getColumn(0).setHeaderValue(language.language_label(languageOption, 253)
            );
            Table_KPB.getColumnModel().getColumn(1).setResizable(false);
            Table_KPB.getColumnModel().getColumn(1).setPreferredWidth(15);
            Table_KPB.getColumnModel().getColumn(1).setHeaderValue(language.language_label(languageOption, 254)
            );
        }

        Table_tahy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table_tahy.setRowHeight(24);
        jScrollPane7.setViewportView(Table_tahy);
        if (Table_tahy.getColumnModel().getColumnCount() > 0) {
            Table_tahy.getColumnModel().getColumn(0).setResizable(false);
            Table_tahy.getColumnModel().getColumn(0).setHeaderValue(language.language_label(languageOption, 267)
            );
            Table_tahy.getColumnModel().getColumn(1).setResizable(false);
            Table_tahy.getColumnModel().getColumn(1).setHeaderValue(language.language_label(languageOption, 268)
            );
            Table_tahy.getColumnModel().getColumn(2).setResizable(false);
            Table_tahy.getColumnModel().getColumn(2).setHeaderValue(language.language_label(languageOption, 269)
            );
            Table_tahy.getColumnModel().getColumn(3).setResizable(false);
            Table_tahy.getColumnModel().getColumn(3).setHeaderValue(language.language_label(languageOption, 270)
            );
        }

        tah_konecne.setText(language.language_label(languageOption, 282));

        tah_konecne1.setText(language.language_label(languageOption, 283) + String.valueOf(jComboBox_stav_KPB.getSelectedItem()) + language.language_label(languageOption, 285));

        podiel_z_RTS1.setEditable(false);
        podiel_z_RTS1.setText("0.0");
        podiel_z_RTS1.setEnabled(false);
        podiel_z_RTS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                podiel_z_RTS1ActionPerformed(evt);
            }
        });

        podiel_z_RTS2.setEditable(false);
        podiel_z_RTS2.setText("0.0");
        podiel_z_RTS2.setEnabled(false);

        Label_RTS_velicina11.setText("% RTS");

        Label_RTS_velicina12.setText("% RTS");

        Label_RTS_velicina13.setText("max");

        Label_RTS_velicina14.setText("max");

        tah_konecne2.setText(language.language_label(languageOption, 286));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(Label_RTS_velicina13)
                                .addGap(11, 11, 11)
                                .addComponent(podiel_z_RTS1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Label_RTS_velicina11))
                            .addComponent(tah_konecne, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                .addComponent(Label_RTS_velicina14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(podiel_z_RTS2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Label_RTS_velicina12)
                                .addGap(6, 6, 6))
                            .addComponent(tah_konecne1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(tah_konecne2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tah_konecne)
                    .addComponent(tah_konecne1)
                    .addComponent(tah_konecne2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(podiel_z_RTS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(podiel_z_RTS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_RTS_velicina11)
                    .addComponent(Label_RTS_velicina12)
                    .addComponent(Label_RTS_velicina13)
                    .addComponent(Label_RTS_velicina14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("tab2", jPanel21);

        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/logo_mainframe.png"))); // NOI18N

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, 0))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.setTitleAt(0, language.language_label(languageOption, 51));

        try{
            int cislo = Table_kotevne_useky.getSelectedRow();
            String nazov = Variable_globeal_kotevny_usek.get(cislo).get_name();
            jTabbedPane1.setTitleAt(1, language.language_label(languageOption, 250) + " - " + nazov );

        }catch(ArrayIndexOutOfBoundsException e){
            jTabbedPane1.setTitleAt(1, language.language_label(languageOption, 250)  );

        }

        jTabbedPane2.addTab("tab3", jPanel18);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/empty.png"))); // NOI18N

        Button_pdf_pageup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/Circled Chevron Up-24.png"))); // NOI18N
        Button_pdf_pageup.setFocusable(false);
        Button_pdf_pageup.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_pdf_pageup.setPreferredSize(new java.awt.Dimension(48, 48));
        Button_pdf_pageup.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_pdf_pageup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_pdf_pageupActionPerformed(evt);
            }
        });
        Button_Icon_save_results.setToolTipText(language.language_label(languageOption, 55));

        Button_pdf_pagedown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/Circled Chevron Down-24.png"))); // NOI18N
        Button_pdf_pagedown.setFocusable(false);
        Button_pdf_pagedown.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_pdf_pagedown.setPreferredSize(new java.awt.Dimension(48, 48));
        Button_pdf_pagedown.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Button_pdf_pagedown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_pdf_pagedownActionPerformed(evt);
            }
        });
        Button_Icon_save_results.setToolTipText(language.language_label(languageOption, 55));

        Textfield_cas.setEditable(false);
        Textfield_cas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Textfield_cas.setText("00:00:00");
        Textfield_cas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Textfield_casActionPerformed(evt);
            }
        });

        label_cas_vytvorenia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_cas_vytvorenia.setText(language.language_label(languageOption, 261));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Button_pdf_pageup, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_pdf_pagedown, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label_cas_vytvorenia, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(Textfield_cas))
                .addGap(41, 41, 41)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(Button_pdf_pageup, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Button_pdf_pagedown, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(label_cas_vytvorenia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Textfield_cas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("tab2", jPanel23);

        jScrollPane4.setViewportView(jTabbedPane2);
        jTabbedPane2.setTitleAt(0, language.language_label(languageOption, 259));
        jTabbedPane2.setTitleAt(1, language.language_label(languageOption, 260));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void Button_Icon_export_PDF_internalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_export_PDF_internalActionPerformed
        
        pdf_internal=true;
        pdf_external=false;
        pdf_as=false;
        pdf_manager();
        Label_status.setText(language.language_label(languageOption, 276));
        
    }//GEN-LAST:event_Button_Icon_export_PDF_internalActionPerformed

    private void pdf_manager(){
    
        String warning_text = "empty";       
        Button_Icon_calculate.doClick();
        String text="No mama dont cry";
        Boolean madatavprvomstlpciOtaznik = false;
        
        
        for(int i =0;i<Variable_globeal_kotevny_usek.size();i++ ){  // cyklus pre všetky existujuce
        if(Table_kotevne_useky.getValueAt(i, 0).equals(true)){      // zisti  je zaskrtnuty prv ysstlpc ak nie vypis warning
            madatavprvomstlpciOtaznik=true;
        }}
        
        if(madatavprvomstlpciOtaznik.equals(true)){
        
           
        try {
        
        String temppdfname = "temp1Pdf.pdf";
        String userhome = System.getProperty("user.dir");
        
         
        File f = new File(userhome + "\\temp\\" + temppdfname);
        if(f.exists() && !f.isDirectory()) { 
         warning_text =language.language_label(languageOption, 251); 
        
         if(pdf_as == false){ // ak DF save nerob nič ak je ootvorene nevadi  
         f.delete();// do something
        }
        }    
            
            
//        // kontrola ci je mozne urobit export PDF aj vje urobeny vypocet a zaroven ak je vypocet urobeny z aktualnych dat    
//        if(Calculation_done== true && Variable_globeal_kotevny_usek_zmena.equals( Variable_globeal_kotevny_usek)){        
//        }else{        
//        warning_text ="Urobym prepočet nehnevaj sa! :)"; warning_sign(warning_text);
//        Button_Icon_calculate.doClick();
//        }
            
            
            // tu sa vlozi chooser kd ktory urči nazov a kde a kokotiny podobne
            
            
            header_pdf hlavicka = new header_pdf(jTextField_nazov_normi.getText(),
                                         jTextField_nadpis_pre_prechodna.getText(),
                                         jTextField_nazov_nazov_stavby.getText(),
                                         jTextField_nazov_nazov_stavby1.getText(),
                                         jTextField_nazov_SOPS.getText(),
                                         jTextField_nazov_SOPS1.getText(),
                                         jTextField_nazov_arch_cislo.getText(),
                                         jTextField_vypracoval.getText(),
                                         jTextField_datum.getText(),
                                         intChecker_short_answer(jTextField_nazov_cislo_strany)
                                         );
            
            Document doc = new Document(PageSize.A4, 56, 28, 28, 28);
             String pdf_file_path_absolute ="null";
            if(pdf_as==true){
            
                
            String user_path = ""   ;  
              
        // ak je zadaná špec lokaciakde ukladať tak tam ak nide default priečion kde existuje
         JFileChooser chooser;
        if(user_path.equals("")){
         chooser = new JFileChooser(userhome + "");}
        else{
          chooser = new JFileChooser(user_path);}
        
          //key files are stored in resources
                                     // whitch type of files are we looking for
        chooser.setDialogTitle(language.language_label(languageOption, 266));   // title for Jfile chooser window
        chooser.showSaveDialog(null);
        File fi = chooser.getSelectedFile(); 
            
           
              pdf_file_path_absolute = fi.getAbsolutePath();  
            PdfWriter writer = PdfWriter.getInstance(doc,new FileOutputStream( pdf_file_path_absolute));    
            }else{
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(userhome + "\\temp\\" +temppdfname));
            }
            BaseFont bf = BaseFont.createFont("/mt_graphic/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); // pridanie našeho kodovanie pre slovensko vranci fontu 
            //BaseFont mojFOnt = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            
            Font fontHeader = new Font(bf, 10,
                      Font.NORMAL | Font.BOLD);
            Font fontText = new Font(bf, 8,
                      Font.NORMAL | Font.BOLD );
            Font fontTable = new Font(bf, 8,
                      Font.NORMAL );
            Font fontScript = new Font(bf, 5,
                      Font.NORMAL );
            Font fontScript2 = new Font(bf, 5,
                      Font.NORMAL );
            
            
            
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
               otherSymbols.setDecimalSeparator('.');
               DecimalFormat decimal_trimiesta = new DecimalFormat("####.###",otherSymbols);  // definovany počet desatinnych miest
               DecimalFormat decimal_dvamiesta = new DecimalFormat("####.##",otherSymbols); 
               DecimalFormat decimal_long = new DecimalFormat("##.########",otherSymbols);  // definovany počet desatinnych miest
               DecimalFormat decimal_none = new DecimalFormat("########",otherSymbols);  // definovany počet desatinnych miest
            
            
            
            
        for(int i =0;i<Variable_globeal_kotevny_usek.size();i++ ){  // cyklus pre všetky existujuce
        if(Table_kotevne_useky.getValueAt(i, 0).equals(true)){     
           
           doc.open(); 
           Image headerLogo = Image.getInstance(getClass().getResource("/mt_graphic/header.png"));          
           headerLogo.scaleAbsolute( 511f,55f);
           headerLogo.setAlignment(Image.RIGHT);
           doc.add(headerLogo);

           LineSeparator line = new LineSeparator();
           doc.add(line);
         
            
           
            
            // riadok 1 typ tabulky
            switch ((int) PDF_VAR_typ_tabulky) {
                case 1:
                    text=language.language_label(languageOption, 184);
                    break;
                case 2:
                    text=language.language_label(languageOption, 185)+hlavicka.nadpis_pre_prechodne();
                    break;
                case 3:
                    text=language.language_label(languageOption, 186);
                    break;
            }            
        
    
            
            float[] columnWidths1 = {511f};
            PdfPTable table1 = new PdfPTable(columnWidths1);

                table1.setTotalWidth(511f); 
                table1.setLockedWidth(true);      
                table1.setHorizontalAlignment(Element.ALIGN_LEFT);
                
                PdfPCell c1 = new PdfPCell(new Phrase(" "+text,fontText));
                c1.setHorizontalAlignment(Element.ALIGN_LEFT); 
                c1.setBorder(Rectangle.NO_BORDER);
                table1.addCell(c1);

                c1= new PdfPCell(new Phrase(" "+language.language_label(languageOption, 187) + " - " + hlavicka.vypocet_podla_normy_getter(),fontText)); 
                c1.setBorder(Rectangle.NO_BORDER);
                table1.addCell(c1);     
                table1.setSpacingBefore(1);
                table1.setSpacingAfter(3);
                doc.add(table1);
                       
            doc.add(line);
            float[] columnWidths = {80f, 270f,20f, 60f,81f};
            PdfPTable table = new PdfPTable(columnWidths);

                table.setTotalWidth(511f); 
                table.setLockedWidth(true);
                
                table.setHorizontalAlignment(Element.ALIGN_LEFT);
         

                c1 = new PdfPCell(new Phrase(" "+language.language_label(languageOption, 188),fontText));
                c1.setHorizontalAlignment(Element.ALIGN_LEFT); 
                c1.setBorder(Rectangle.NO_BORDER);
               
                table.addCell(c1);

                c1= new PdfPCell(new Phrase(hlavicka.Stavba() + "\n\r" + hlavicka.Stavba1() ,fontText)); 
                c1.setBorder(Rectangle.NO_BORDER);
                table.addCell(c1);
                c1= new PdfPCell(new Phrase(""));  // emptycell
                c1.setBorder(Rectangle.NO_BORDER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(language.language_label(languageOption, 189) +"\n\r"+language.language_label(languageOption, 190),fontText));
                c1.setVerticalAlignment(Element.ALIGN_BOTTOM); 
                c1.setBorder(Rectangle.NO_BORDER);
                table.addCell(c1);
                
                c1 = new PdfPCell(new Phrase(hlavicka.dátum() + "\n\r" + hlavicka.Archivne_cislo(),fontText));
                c1.setVerticalAlignment(Element.ALIGN_BOTTOM); 
                c1.setBorder(Rectangle.NO_BORDER);
                table.addCell(c1);

                table.setSpacingBefore(1);
                table.setSpacingAfter(3);
            doc.add(table);
            doc.add(line);
                  
                table = new PdfPTable(columnWidths);
                table.setTotalWidth(511f); 
                table.setLockedWidth(true);               
                table.setHorizontalAlignment(Element.ALIGN_LEFT);
                
                c1 = new PdfPCell(new Phrase(" "+language.language_label(languageOption, 191),fontText));
                c1.setHorizontalAlignment(Element.ALIGN_LEFT); 
                c1.setBorder(Rectangle.NO_BORDER);              
                table.addCell(c1);

                c1= new PdfPCell(new Phrase(hlavicka.SO_PS()+ "\n\r" + hlavicka.SO_PS1(),fontText)); 
                c1.setBorder(Rectangle.NO_BORDER);
                table.addCell(c1);
                c1= new PdfPCell(new Phrase(""));  // emptycell
                c1.setBorder(Rectangle.NO_BORDER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(language.language_label(languageOption, 192) +"\n\r"+language.language_label(languageOption, 193),fontText));
                c1.setVerticalAlignment(Element.ALIGN_BOTTOM); 
                c1.setBorder(Rectangle.NO_BORDER);
                table.addCell(c1);
                
                c1 = new PdfPCell(new Phrase(hlavicka.vypracoval() + "\n\r" + String.valueOf(hlavicka.cislovanie_stran_od()+i),fontText));
                c1.setVerticalAlignment(Element.ALIGN_BOTTOM); 
                c1.setBorder(Rectangle.NO_BORDER);
                table.addCell(c1);

                table.setSpacingBefore(1);
                table.setSpacingAfter(3);
            
            doc.add(table);
            doc.add(line);
            
            doc.add(new Paragraph(" "));
            doc.add(new Phrase(" " + Variable_globeal_kotevny_usek.get(i).get_name(),fontHeader)); // nadpis kotevneho useku
            
            doc.add(new Phrase("\n\r " + language.language_label(languageOption, 194) + " : " + Databaza.get(Variable_globeal_kotevny_usek.get(i).get_conductor_number())[0] +
                               "\n " + language.language_label(languageOption, 195) + " : "  ,fontText)); 
            
            
            //conductoe info table http://tutorials.jenkov.com/java-itext/paragraph.html help
            float[] columnWidths3 = {73f,73f,73f,73f,73f,73f,73f};
             table = new PdfPTable(columnWidths3);
             table.setTotalWidth(511f); 
                table.setLockedWidth(true);
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                
                Chunk superScript = new Chunk("2",fontScript); // superscript
                superScript.setTextRise(2f);
                
                
                c1 = new PdfPCell();
                Chunk p1 = new Chunk(language.language_label(languageOption, 196) +"\n" +"d [mm]" ,fontTable);
                Paragraph p1_total= new Paragraph(10);
                          p1_total.add(p1);
                p1_total.setAlignment(Element.ALIGN_CENTER);
               p1_total.setSpacingAfter(5);
               p1_total.setSpacingBefore(10);
                c1.addElement(p1_total);
                
                PdfPCell c2 = new PdfPCell();
                Chunk p2 = new Chunk(language.language_label(languageOption, 197) +"\n" +"S [mm" ,fontTable);
                Chunk p22 = new Chunk("]" ,fontTable);
                Paragraph p2_total= new Paragraph(10);
                       p2_total.add(p2);
                       p2_total.add(superScript);
                       p2_total.add(p22);
                p2_total.setAlignment(Element.ALIGN_CENTER);       
                c2.addElement(p2_total);
                
                
                
                PdfPCell c3 = new PdfPCell();
                Chunk p3 = new Chunk(language.language_label(languageOption, 198) +"\n" +"m [kg/m]" ,fontTable);
                Paragraph p3_total= new Paragraph(10);
                          p3_total.add(p3);
                p3_total.setAlignment(Element.ALIGN_CENTER); 
                c3.addElement(p3_total);
                
                PdfPCell c4 = new PdfPCell();
                Chunk p4 = new Chunk(language.language_label(languageOption, 199) +"\n" +"E [MPa]" ,fontTable);
                Paragraph p4_total= new Paragraph(10);
                          p4_total.add(p4);
                p4_total.setAlignment(Element.ALIGN_CENTER); 
                c4.addElement(p4_total);
                
                PdfPCell c5 = new PdfPCell();
                Chunk p5 = new Chunk(language.language_label(languageOption, 200) +"\r" +"\u03B3 [N/m.mm" ,fontTable);
                Chunk p55 = new Chunk("]" ,fontTable);
                Paragraph p5_total= new Paragraph(10);
                       p5_total.add(p5);
                       p5_total.add(superScript);
                       p5_total.add(p55);
                p5_total.setAlignment(Element.ALIGN_CENTER);       
                c5.addElement(p5_total);
                
                
                PdfPCell c6 = new PdfPCell();
                Chunk p6 = new Chunk(language.language_label(languageOption, 201) +"\n" +"\u03B1 [1/°C]" ,fontTable);
                Paragraph p6_total= new Paragraph(10);
                          p6_total.add(p6);
                p6_total.setAlignment(Element.ALIGN_CENTER); 
                c6.addElement(p6_total);
                 
                //kokot
                PdfPCell c7 = new PdfPCell();
                Chunk p7 = new Chunk(language.language_label(languageOption, 202) +"\n" +"F [N]" ,fontTable);
                Paragraph p7_total= new Paragraph(10);
                          p7_total.add(p7);
                p7_total.setAlignment(Element.ALIGN_CENTER); 
                c7.addElement(p7_total);
                
                // pdf table conductor values
        
                PdfPCell c8 = new PdfPCell();
                Chunk p8 = new Chunk(  decimal_trimiesta.format(Databaza.get(Variable_globeal_kotevny_usek.get(i).get_conductor_number())[1] ) ,fontTable);
                Paragraph p8_total= new Paragraph(10);
                          p8_total.add(p8);
                p8_total.setAlignment(Element.ALIGN_CENTER);
                 p8_total.setSpacingAfter(5);
               p8_total.setSpacingBefore(5);
                c8.addElement(p8_total);
                
                 PdfPCell c9 = new PdfPCell();
                Chunk p9 = new Chunk(  decimal_trimiesta.format(Databaza.get(Variable_globeal_kotevny_usek.get(i).get_conductor_number())[2] ) ,fontTable);
                Paragraph p9_total= new Paragraph(10);
                          p9_total.add(p9);
                p9_total.setAlignment(Element.ALIGN_CENTER); 
                c9.addElement(p9_total);
                
                 PdfPCell c10 = new PdfPCell();
                Chunk p10 = new Chunk(  decimal_trimiesta.format(Databaza.get(Variable_globeal_kotevny_usek.get(i).get_conductor_number())[3] ) ,fontTable);
                Paragraph p10_total= new Paragraph(10);
                          p10_total.add(p10);
                p10_total.setAlignment(Element.ALIGN_CENTER); 
                c10.addElement(p10_total);
                
                 PdfPCell c11 = new PdfPCell();
                Chunk p11 = new Chunk(  decimal_none.format(Databaza.get(Variable_globeal_kotevny_usek.get(i).get_conductor_number())[4] ) ,fontTable);
                Paragraph p11_total= new Paragraph(10);
                          p11_total.add(p11);
                p11_total.setAlignment(Element.ALIGN_CENTER); 
                c11.addElement(p11_total);
                
                double m = (double) Databaza.get(Variable_globeal_kotevny_usek.get(i).get_conductor_number())[3];
                double S = (double) Databaza.get(Variable_globeal_kotevny_usek.get(i).get_conductor_number())[2];
                
                double gamma =  (m*9.80665 )/S;
                
                 PdfPCell c12 = new PdfPCell();
                Chunk p12 = new Chunk(  decimal_long.format(gamma) ,fontTable);
                Paragraph p12_total= new Paragraph(10);
                          p12_total.add(p12);
                p12_total.setAlignment(Element.ALIGN_CENTER); 
                c12.addElement(p12_total);
                
                 PdfPCell c13 = new PdfPCell();
                Chunk p13 = new Chunk(  decimal_long.format(Databaza.get(Variable_globeal_kotevny_usek.get(i).get_conductor_number())[5] ) ,fontTable);
                Paragraph p13_total= new Paragraph(10);
                          p13_total.add(p13);
                p13_total.setAlignment(Element.ALIGN_CENTER); 
                c13.addElement(p13_total);
                
                PdfPCell c14 = new PdfPCell();
                Chunk p14 = new Chunk(  decimal_none.format(Databaza.get(Variable_globeal_kotevny_usek.get(i).get_conductor_number())[6] ) ,fontTable);
                Paragraph p14_total= new Paragraph(10);
                          p14_total.add(p14);
                p14_total.setAlignment(Element.ALIGN_CENTER); 
                c14.addElement(p14_total);
                
             
                table.addCell(c1);
                table.addCell(c2);
                table.addCell(c3);
                table.addCell(c4);
                table.addCell(c5);
                table.addCell(c6);
                table.addCell(c7);
                
                
                table.addCell(c8);
                table.addCell(c9);
                table.addCell(c10);
                table.addCell(c11);
                table.addCell(c12);
                table.addCell(c13);
                table.addCell(c14);
                doc.add(table);
                
           float[] columnWidths4 = {272f,166f};
             table = new PdfPTable(columnWidths4);
             table.setTotalWidth(511f); 
             table.setLockedWidth(true); 
               
            
   
           c1 = new PdfPCell();
           Paragraph par_left= new Paragraph(10);
           Chunk text_left = new Chunk(  language.language_label(languageOption, 203) + " : " + decimal_trimiesta.format( Variable_globeal_kotevny_usek.get(i).get_zakladne_mech_napatie_lana_pre_minus5_over() ) + " MPa" + "\n" 
                                       +  language.language_label(languageOption, 204) + " : " + decimal_trimiesta.format( Variable_globeal_kotevny_usek.get(i).get_h_c_mean() ) + " m"   
                                       + "\n" ,fontTable); 
           Chunk text_left1 = new Chunk(  language.language_label(languageOption, 205) + " : " + get_char_terenu_number(Variable_globeal_kotevny_usek.get(i))  
                                       + "\n" + get_char_terenu_text(Variable_globeal_kotevny_usek.get(i))  
                                       + "\n" ,fontTable);
           Chunk text_left2 = new Chunk(  language.language_label(languageOption, 206) + " : " + get_typ_terenu_number(jComboBox_KPB_typ_terenu.getSelectedIndex())  
                                       + "\n" + get_typ_terenu_text(jComboBox_KPB_typ_terenu.getSelectedIndex())  + "\n"  ,fontTable);
           Chunk text_left3 = new Chunk(  language.language_label(languageOption, 281)   ,fontTable);
           
           
           // tu vytvoriť
          float[] columnWidths4x = {88f,50f,50f,50f,50f};
          
          PdfPTable testTable = new PdfPTable(columnWidths4x);
          PdfPCell c2x = new PdfPCell();
          Paragraph par_total= new Paragraph(2);
          
           c11 = new PdfPCell();
          Chunk text_ltesttable = new Chunk(  language.language_label(languageOption, 297),fontTable);        
          par_total= new Paragraph(6);
          par_total.add(text_ltesttable);
          par_total.setAlignment(Element.ALIGN_CENTER);
          c11.addElement(par_total);testTable.addCell(c11);
           c11 = new PdfPCell();
          text_ltesttable = new Chunk(  percento1_sigma.getText() + "%",fontTable);        
          par_total= new Paragraph(6);
          par_total.add(text_ltesttable);
          par_total.setAlignment(Element.ALIGN_CENTER);
           c11.addElement(par_total);testTable.addCell(c11);
        
           c11 = new PdfPCell();
          text_ltesttable = new Chunk(  percento2_sigma.getText() + "%" ,fontTable);
          par_total= new Paragraph(6);
          par_total.add(text_ltesttable);par_total.setAlignment(Element.ALIGN_CENTER);
           c11.addElement(par_total);testTable.addCell(c11);
          
           c11 = new PdfPCell();
          text_ltesttable = new Chunk(  percento3_sigma.getText() + "%",fontTable);
          par_total= new Paragraph(6);
          par_total.add(text_ltesttable);par_total.setAlignment(Element.ALIGN_CENTER);
           c11.addElement(par_total);testTable.addCell(c11);
          
          c11 = new PdfPCell();
          text_ltesttable = new Chunk(  percento4_sigma.getText() + "%",fontTable);
          par_total= new Paragraph(6);
          par_total.add(text_ltesttable);par_total.setAlignment(Element.ALIGN_CENTER);
          c11.addElement(par_total);testTable.addCell(c11);
          
          
          
           Chunk susubScript = new Chunk("H",fontScript); // superscript
         susubScript.setTextRise(-2f); 
         
        c11 = new PdfPCell();
        p1 = new Chunk("\u03C3" ,fontTable);        
        Chunk p23 = new Chunk( " [MPa]"  ,fontTable);
        p1_total= new Paragraph(6);
        p1_total.add(p1);p1_total.add(susubScript);p1_total.add(p23);p1_total.setAlignment(Element.ALIGN_CENTER);
        c11.addElement(p1_total);testTable.addCell(c11);
          
          c11 = new PdfPCell();
          text_ltesttable = new Chunk( decimal_dvamiesta.format(Variable_globeal_kotevny_usek.get(i).get_vysledky_per_podiel_sigma()[1][0]) ,fontTable); 
          par_total= new Paragraph(6);
          par_total.add(text_ltesttable);par_total.setAlignment(Element.ALIGN_CENTER);
          c11.addElement(par_total);testTable.addCell(c11);
          
          c11 = new PdfPCell();
          text_ltesttable = new Chunk( decimal_dvamiesta.format(Variable_globeal_kotevny_usek.get(i).get_vysledky_per_podiel_sigma()[1][1]) ,fontTable);
          par_total= new Paragraph(6);
          par_total.add(text_ltesttable);par_total.setAlignment(Element.ALIGN_CENTER);
          c11.addElement(par_total);testTable.addCell(c11);
          
          c11 = new PdfPCell();
          text_ltesttable = new Chunk( decimal_dvamiesta.format(Variable_globeal_kotevny_usek.get(i).get_vysledky_per_podiel_sigma()[1][2]) ,fontTable);  
          par_total= new Paragraph(6);
          par_total.add(text_ltesttable);par_total.setAlignment(Element.ALIGN_CENTER);
         c11.addElement(par_total);testTable.addCell(c11);
          
          c11 = new PdfPCell();
          text_ltesttable = new Chunk( decimal_dvamiesta.format(Variable_globeal_kotevny_usek.get(i).get_vysledky_per_podiel_sigma()[1][3]) ,fontTable);
          par_total= new Paragraph(6);
          par_total.add(text_ltesttable);par_total.setAlignment(Element.ALIGN_CENTER);
          c11.addElement(par_total);testTable.addCell(c11);
          
        c11 = new PdfPCell();
        p1 = new Chunk("F" ,fontTable);        
        p2 = new Chunk( " [kN]"  ,fontTable);
        p1_total= new Paragraph(6);
        p1_total.add(p1);p1_total.add(susubScript);p1_total.add(p2);p1_total.setAlignment(Element.ALIGN_CENTER);
        c11.addElement(p1_total);testTable.addCell(c11);
        
          c11 = new PdfPCell(); 
          text_ltesttable = new Chunk( decimal_dvamiesta.format(Variable_globeal_kotevny_usek.get(i).get_vysledky_per_podiel_sigma()[2][0]) ,fontTable); 
          par_total= new Paragraph(6);
          par_total.add(text_ltesttable);par_total.setAlignment(Element.ALIGN_CENTER);
          c11.addElement(par_total);testTable.addCell(c11);
          
          c11 = new PdfPCell();
          text_ltesttable = new Chunk( decimal_dvamiesta.format(Variable_globeal_kotevny_usek.get(i).get_vysledky_per_podiel_sigma()[2][1]) ,fontTable);
          par_total= new Paragraph(6);
          par_total.add(text_ltesttable);par_total.setAlignment(Element.ALIGN_CENTER);
          c11.addElement(par_total);testTable.addCell(c11);
          
          c11 = new PdfPCell();
          text_ltesttable = new Chunk( decimal_dvamiesta.format(Variable_globeal_kotevny_usek.get(i).get_vysledky_per_podiel_sigma()[2][2]) ,fontTable);  
          par_total= new Paragraph(6);
          par_total.add(text_ltesttable);par_total.setAlignment(Element.ALIGN_CENTER);
          c11.addElement(par_total);testTable.addCell(c11);
          
          c11 = new PdfPCell();
          text_ltesttable = new Chunk( decimal_dvamiesta.format(Variable_globeal_kotevny_usek.get(i).get_vysledky_per_podiel_sigma()[2][3]) ,fontTable);
          par_total= new Paragraph(6);
          par_total.add(text_ltesttable);par_total.setAlignment(Element.ALIGN_CENTER);
          c11.addElement(par_total);testTable.addCell(c11);
          
          
          
           
            par_left.add(text_left);par_left.add(line);par_left.add(text_left1);par_left.add(line);par_left.add(text_left2);par_left.add(line); par_left.add(text_left3);
            par_left.add(testTable);
            c1.addElement(par_left);
            c1.setBorder(Rectangle.NO_BORDER);
            
           c2 = new PdfPCell();
           Paragraph par_right= new Paragraph(10);
           Chunk text_right = new Chunk(  language.language_label(languageOption, 212) + " : " + decimal_trimiesta.format( Variable_globeal_kotevny_usek.get(i).get_uroven_spolahlivosti_porcislo()+1) + "\n" 
                                       +  language.language_label(languageOption, 213) + " : " + get_rok_navratu_cas(jComboBox_uroven_splahlivosti.getSelectedIndex()) + "\n" 
                                       +  language.language_label(languageOption, 215) + " : " + Variable_globeal_kotevny_usek.get(i).get_namrazova_oblast_string() +  language.language_label(languageOption, 225) + hlavicka.vypocet_podla_normy_getter() +"\n" 
                                       +  language.language_label(languageOption, 226) + " : " + get_vetrova_oblast_string(jComboBox_vetrova_oblast.getSelectedIndex()) + ", v = " + decimal_trimiesta.format(Variable_globeal_kotevny_usek.get(i).get_V_mean()) + " m/s" +"\n" 
                                       ,fontTable); 
           Chunk text_right1 = new Chunk(  language.language_label(languageOption, 228) + " : " + decimal_dvamiesta.format(Variable_globeal_kotevny_usek.get(i).get_vysledky_tlaky6()[0]) + " N/m" + "\n" 
                                         + language.language_label(languageOption, 229) + " : " + decimal_dvamiesta.format(Variable_globeal_kotevny_usek.get(i).get_vysledky_tlaky6()[1]) + " N/m" + "\n" 
                                         + language.language_label(languageOption, 230) + " : " + decimal_dvamiesta.format(Variable_globeal_kotevny_usek.get(i).get_vysledky_tlaky6()[2]) + " N/m" + "\n" 
                                         + language.language_label(languageOption, 231) + " : " + decimal_dvamiesta.format(Variable_globeal_kotevny_usek.get(i).get_vysledky_tlaky6()[3]) + " N/m" + "\n" 
                                         + language.language_label(languageOption, 232) + " : " + decimal_dvamiesta.format(Variable_globeal_kotevny_usek.get(i).get_vysledky_tlaky6()[4]) + " N/m" + "\n",fontTable);
           Chunk text_right2 = new Chunk(  language.language_label(languageOption, 233) + " : " + decimal_dvamiesta.format(Variable_T0_zivotnost/(365*24)) +" " +language.language_label(languageOption, 214)+ "\n" 
                                         + language.language_label(languageOption, 234) + " : " + get_vcas_od_montaze() +" " +language.language_label(languageOption, 214)   ,fontTable);
        
           par_right.add(text_right);par_right.add(line);par_right.add(text_right1);par_right.add(line);par_right.add(text_right2);
           c2.addElement(par_right);
           c2.setBorder(Rectangle.NO_BORDER);
            table.addCell(c1);
            table.addCell(c2);
            doc.add(table);
           
          Chunk subScript = new Chunk("H",fontScript2); // superscript
                 subScript.setTextRise(-2f);   
          doc.add(new Phrase(" " + language.language_label(languageOption, 238) + "\u03C3",fontText)); 
          doc.add(subScript);  
          doc.add(new Phrase(" " + language.language_label(languageOption, 239) + decimal_trimiesta.format(Variable_globeal_kotevny_usek.get(i).get_str_rozpatie()  ) + " m" ,fontText)); 
            
          
          make_MT_table(doc,Variable_globeal_kotevny_usek.get(i),fontTable,fontScript,fontScript2,fontText,6,decimal_dvamiesta,decimal_trimiesta,decimal_none);
          
            doc.newPage();
            
        } // if check box enabled
        } // do  pocet kotevnych usekov  
         doc.close();
         if(pdf_internal == true){
         //create PNG from pdf
         PDDocument document = PDDocument.load(new File(userhome + "\\temp\\" + temppdfname));
         PDFRenderer pdfRenderer = new PDFRenderer(document);
         pocet_pdf_stran= document.getNumberOfPages();
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

                // suffix in filename will be used as the file format
                ImageIOUtil.writeImage(bim,userhome + "\\temp\\" + temppdfname + "-" + (page + 1) + ".png", 300);
            }
            document.close();
 // nahod prvu   
     String path = userhome + "\\temp\\" + "temp1Pdf.pdf-" +"1" +".png";
     ImageIcon icon = new ImageIcon(path);
     java.awt.Image img = icon.getImage();
     jLabel5.setIcon(new ImageIcon(img.getScaledInstance(jLabel5.getWidth(), jLabel5.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING)));
         
       
            
       // nahod cas do  textfield cas 
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Textfield_cas.setText(sdf.format(cal.getTime())); ;

         } 
         
         // opeing in  extenaô viewer         
         if(pdf_external ==  true) {
         
         File ff = new File(userhome + "\\temp\\" + temppdfname);
        if(ff.exists() && !ff.isDirectory()) { 
         Desktop.getDesktop().open(ff);// do something
        }
         }
         
         if(pdf_as ==  true) {
         
         File ff = new File(pdf_file_path_absolute);
        if(ff.exists() && !ff.isDirectory()) { 
         Desktop.getDesktop().open(ff);// do something
        }
         }
         
         
         
         
         
        }catch(NullPointerException e){  // catch for cycle and kotevny usek data
           warning_sign(warning_text);     
        } catch (DocumentException | FileNotFoundException ex) {
           warning_sign(warning_text); Logger.getLogger(mainframe_17.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(mainframe_17.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{  // ak niesu data v v prom stlpci
            
            warning_sign(language.language_label(languageOption, 252));
        
        }
        
        
    }
    
    private void Button_Icon_calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_calculateActionPerformed
        String warning_text = "empty";
        if (mainframeLodaed == true) {
        
        // RESAVE actual window
        
         int rowNumber =  Table_kotevne_useky.getSelectedRow(); //- (e.getFirstIndex()-e.getLastIndex()); 
             double[] empty = null;
             double[][] empty2 = null;
              double[] hodnoty = new double[4];
             
        try{hodnoty[0] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[0]));}catch(NullPointerException e){hodnoty[0]=0;}
        try{hodnoty[1] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[1]));}catch(NullPointerException e){hodnoty[1]=0;}
        try{hodnoty[2] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[2]));}catch(NullPointerException e){hodnoty[2]=0;}
        try{hodnoty[3] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[3]));}catch(NullPointerException e){hodnoty[3]=0;}
             kotevnyUsek docasny_kot_usek = new kotevnyUsek(new_kotevny_usek_name, 0,"0", 0, 0, 0, 0, filename, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, teplotyser, first_Start, teplotyser, teplotyser, teplotyser, first_Start, Variable_Ai_array, Variable_Hi_array, Variable_Hi_array,Variable_Hi_array_nmv,0,0,0,hodnoty);                     
             mainframe_to_kotevny_usek(docasny_kot_usek,rowNumber);                        
             Variable_globeal_kotevny_usek.set(rowNumber, docasny_kot_usek);  
                       
        try{
            
        for(int i =0;i<Variable_globeal_kotevny_usek.size();i++ ){  // cyklus pre všetky existujuce
        if(Table_kotevne_useky.getValueAt(i, 0).equals(true)){      // cyklus či je zaskrtnuty kot usek
            
        /// WARNINGOVAC AND TESTER ZONE 
        kotevnyUsek Kot_usek = Variable_globeal_kotevny_usek.get(i);
        warning_text =language.language_label(languageOption, 298);            if (Kot_usek.get_Ai_array().length == 1){}  // warning neni array A1 vyplneni a nebude to fungovať
        warning_text =language.language_label(languageOption, 299);            if (Kot_usek.get_Hi_array().length == 1){}  // warning neni array A1 vyplneni a nebude to fungovať     
        warning_text =language.language_label(languageOption, 300);            if (Kot_usek.get_DeltaHi_array().length == 1){}  // warning neni array A1 vyplneni a nebude to fungovať        
        warning_text =language.language_label(languageOption, 301);  if (Kot_usek.get_I_R50() == 123456789.987654321){throw new NullPointerException();}
        warning_text ="Error Hcmean=0";  if (Kot_usek.get_h_c_mean() == 123456789.987654321 || Kot_usek.get_h_c_mean() == 0){throw new NullPointerException();}
        
        
        ///MAIN CALCULATION
        
            // setting the conductor - prerequisite
            int selected_conductor_index_from_JComboBox = Kot_usek.get_conductor_number();
            Conductor_variables Conductor =  new  Conductor_variables (Databaza.get(selected_conductor_index_from_JComboBox));   
            
            // overload class - first step
            double final_psi_w;
            if(Kot_usek.get_uroven_spolahlivosti_porcislo() == 6){ // custom values set
                final_psi_w = Kot_usek.get_Psi_w();
            } else {
                final_psi_w = overload.set_psi_w(Kot_usek.get_uroven_spolahlivosti_porcislo(), Kot_usek.get_B_I()); // set from table
            }
                                                                
            Overload_variables Overload = new Overload_variables(Conductor,
                                                                Conductor.get_m()*9.80665,
                                                                Kot_usek.get_ro_I(),
                                                                Kot_usek.get_K_lc(),
                                                                
                                                                Kot_usek.get_K_h(), 
                                                                Kot_usek.get_I_R50(),
                                                                Kot_usek.get_k_r(), 
                                                                Kot_usek.get_z_0(), 
                                                                Kot_usek.get_V_mean(), 
                                                                Kot_usek.get_c_dir(),
                                                                Kot_usek.get_c_0(), 
                                                                1.,
                                                                Kot_usek.get_gama_w(),
                                                                Kot_usek.get_gama_I(),
                                                                Kot_usek.get_Psi_I(), 
                                                                final_psi_w, 
                                                                Kot_usek.get_B_I(), 
                                                                3, 
                                                                0, 
                                                                1.25,
                                                                Kot_usek.get_C_cl(),
                                                                Kot_usek.get_h_c_mean());

           // setting variables into overload vlass 
           overload.set_all_variables(Overload,Kot_usek.get_Ai_array());
           // compute overloads
           overload.compute();

          //parsing value to array list zatazenia a prezataniza 
           ArrayList<Double>  vysledky_pretazenia5 = new ArrayList<>();
           vysledky_pretazenia5.removeAll(vysledky_pretazenia5);
           vysledky_pretazenia5.add(overload.z_I);
           vysledky_pretazenia5.add(overload.z_W);
           vysledky_pretazenia5.add(overload.z_Iw);
           vysledky_pretazenia5.add(overload.z_iW);
           double[] target = new double[vysledky_pretazenia5.size()];
           for (int a = 0; a < target.length; a++) {    
           target[a] = vysledky_pretazenia5.get(a);                // java 1.5+ style (outboxing)
           }         
           Kot_usek.set_vysledky_pretazenia6(target);


           ArrayList<Double> vysledky_tlaky = new ArrayList<>();
           vysledky_tlaky.removeAll(vysledky_tlaky);
           vysledky_tlaky.add(overload.I_T);
           vysledky_tlaky.add(overload.I_3);
           vysledky_tlaky.add(overload.q_wT);
           vysledky_tlaky.add(overload.q_wI3);
           vysledky_tlaky.add(overload.q_wIT);  
           double[] target2 = new double[vysledky_tlaky.size()];
          for (int a = 0; a < target2.length; a++) {    
          target2[a] = vysledky_tlaky.get(a);                // java 1.5+ style (outboxing)
           }
          Kot_usek.set_vysledky_tlaky6(target2);

         double[] sigmy = new double[14];
         double[][] sigmy_minu5_podiel = new double[3][4];
         double[] cecka = new double[14];
         double[] pretazenia = new double[14];
         double[] sily = new double[14];
         int[] KPB_vysledky = new int[Kot_usek.get_Ai_array().length];
       
         
         // rozličovač ake pretaženia ma bra vlastne alebo vypocitane hotova vec 
       if(jRadioButton_with_pretazenia_vlastna.isSelected() == true ){
            for(int y=0 ;y<14;y++){
                pretazenia[y]=Variable_pretazenia_stav_rovnica[y];           
            }
       }else{
             pretazenia[0]=1; 
             pretazenia[1]=1;
             pretazenia[2]=1;
             pretazenia[3]=1;
             pretazenia[4]=overload.z_I;
             pretazenia[5]=overload.z_W;
             pretazenia[6]=overload.z_Iw;
             pretazenia[7]=overload.z_iW;
             pretazenia[8]=1;
             pretazenia[9]=1;
             pretazenia[10]=1;
             pretazenia[11]=1;
             pretazenia[12]=1;
             pretazenia[13]=1;
             }  
         
//        State_equation_variables Base = new State_equation_variables(Conductor, -5, -5, Variable_zakladne_mech_napatie_lana_pre_minus5, 1);
//        state_equation.set_all_variables(Base, Kot_usek.get_Ai_array(), Kot_usek.get_DeltaHi_array());
//        double sigma_H0_base = state_equation.compute_sigma_H(1, Kot_usek.get_zakladne_mech_napatie_lana_pre_minus5_over(), -5, -5);
//        System.out.println("SigmaH0_Base = " + sigma_H0_base + " MPa");
//        
       // 14 členny cyklus pre už samotny vypočet jednotlivých stavov
            for(int y=0 ; y<Variable_teploty_stav_rovnica.length; y++){
                double[] temperatures_state_equation = temperatures_for_state_equation( Conductor, 
                                                                                        Kot_usek, 
                                                                                        Variable_teploty_stav_rovnica[y], 
                                                                                        Variable_streda_roc_teplota, 
                                                                                        Variable_T0_zivotnost, 
                                                                                        Variable_Tp_prechodna_doba );
                State_equation_variables State = new State_equation_variables(  Conductor,
                                                                                temperatures_state_equation[1],
                                                                                temperatures_state_equation[0],
                                                                                Kot_usek.get_zakladne_mech_napatie_lana_pre_minus5_over(),
                                                                                1.0);
                state_equation.set_all_variables(State, Kot_usek.get_Ai_array(), Kot_usek.get_DeltaHi_array());
                sigmy[y] = state_equation.compute_sigma_H(  pretazenia[y],                  // load
                                                            Kot_usek.get_str_rozpatie(),    // mid span
                                                            temperatures_state_equation[0], // Tx0
                                                            temperatures_state_equation[1]);// Tx1
                cecka[y] = state_equation.compute_c(sigmy[y], pretazenia[y], Conductor);
                sily[y] = state_equation.compute_Fh(sigmy[y], Conductor)/1000; //kN
             
        // cyklus na podielove zataženia     
            // sa bude prepočitavat zaťaženie na percentulany podiel namrazy
            if(y==4){ // -5+N
                 
                // defining specific loads - local variables
                double over1, over2, over3, over4;
                over1 = (1 + (pretazenia[y]-1)*(percento_podiel_namrazy_sigma1/100));
                over2 = (1 + (pretazenia[y]-1)*(percento_podiel_namrazy_sigma2/100));
                over3 = (1 + (pretazenia[y]-1)*(percento_podiel_namrazy_sigma3/100));
                over4 = (1 + (pretazenia[y]-1)*(percento_podiel_namrazy_sigma4/100));
//                
//                System.out.println("Pretazenie a% = " + over1);
//                System.out.println("Pretazenie b% = " + over2);
//                System.out.println("Pretazenie c% = " + over3);
//                System.out.println("Pretazenie d% = " + over4);
                
                state_equation.set_all_variables(State, Kot_usek.get_Ai_array(), Kot_usek.get_DeltaHi_array());
                double sigma_pomerna_1 = state_equation.compute_sigma_H(over1,                  // load
                                                            Kot_usek.get_str_rozpatie(),    // mid span
                                                            temperatures_state_equation[0], // Tx0
                                                            temperatures_state_equation[1]);// Tx1
                double sila_pomerna_1 = state_equation.compute_Fh(sigma_pomerna_1, Conductor)/1000; //kN
                
                
                state_equation.set_all_variables(State, Kot_usek.get_Ai_array(), Kot_usek.get_DeltaHi_array());
                double sigma_pomerna_2 = state_equation.compute_sigma_H(over2,                  // load
                                                            Kot_usek.get_str_rozpatie(),    // mid span
                                                            temperatures_state_equation[0], // Tx0
                                                            temperatures_state_equation[1]);// Tx1
                double sila_pomerna_2 = state_equation.compute_Fh(sigma_pomerna_2, Conductor)/1000; //kN
                state_equation.set_all_variables(State, Kot_usek.get_Ai_array(), Kot_usek.get_DeltaHi_array());
                double sigma_pomerna_3 = state_equation.compute_sigma_H(over3,                  // load
                                                            Kot_usek.get_str_rozpatie(),    // mid span
                                                            temperatures_state_equation[0], // Tx0
                                                            temperatures_state_equation[1]);// Tx1
                double sila_pomerna_3 = state_equation.compute_Fh(sigma_pomerna_3, Conductor)/1000; //kN
                state_equation.set_all_variables(State, Kot_usek.get_Ai_array(), Kot_usek.get_DeltaHi_array());
                double sigma_pomerna_4 = state_equation.compute_sigma_H(over4,                  // load
                                                            Kot_usek.get_str_rozpatie(),    // mid span
                                                            temperatures_state_equation[0], // Tx0
                                                            temperatures_state_equation[1]);// Tx1
                double sila_pomerna_4 = state_equation.compute_Fh(sigma_pomerna_4, Conductor)/1000; //kN
                sigmy_minu5_podiel[1][0]=sigma_pomerna_1;        
                sigmy_minu5_podiel[1][1]=sigma_pomerna_2;        
                sigmy_minu5_podiel[1][2]=sigma_pomerna_3;       
                sigmy_minu5_podiel[1][3]=sigma_pomerna_4;   
                sigmy_minu5_podiel[2][0] = sila_pomerna_1;
                sigmy_minu5_podiel[2][1] = sila_pomerna_2;
                sigmy_minu5_podiel[2][2] = sila_pomerna_3;
                sigmy_minu5_podiel[2][3] = sila_pomerna_4;
                
                

                }
                if (y == 7) { // -5+Nv

                    
                }
                if (y == 8) { // -5+nV

                   
                }

            }

            ////////////////////////////// vlozenie vysledkov
            Kot_usek.set_vysledky_sigmaH_MT(sigmy);
            Kot_usek.set_vysledky_c_MT(cecka);
            Kot_usek.set_vysledky_pretazenia_MT(pretazenia);
            Kot_usek.set_vysledky_sily_MT(sily);
            Kot_usek.set_per_podiel_sigma(sigmy_minu5_podiel);  
            /////////////////////////////// v
        
        ///////////// cyklus na zaklade poctu rozpati  pre vypocet vid priehybov 
        double[][] vid_priehyby = new double[Kot_usek.get_Ai_array().length][14];
        double[][] tahy = new double[Kot_usek.get_Ai_array().length][4];  
        
            double[] a = Kot_usek.get_Ai_array();
            double[] dh = Kot_usek.get_DeltaHi_array();
            
            for (int y = 0; y < Kot_usek.get_Ai_array().length; y++) {
                for (int z = 0; z<Variable_teploty_stav_rovnica.length; z++) {
                    vid_priehyby[y][z] = state_equation.compute_sag_vis(a[y], cecka[z], dh[y]);                    
                }                
            }
          
        ////////////////////////////////////////////////// vibration protection
            double[] temperatures_vibration = temperatures_for_state_equation( Conductor, 
                                                                                Kot_usek, 
                                                                                -5, 
                                                                                Variable_streda_roc_teplota, 
                                                                                Variable_T0_zivotnost, 
                                                                                Variable_KPB_cas_vypoctu );
            
            State_equation_variables Vib = new State_equation_variables(    Conductor,-5, -5,
                                                                            Kot_usek.get_zakladne_mech_napatie_lana_pre_minus5_over(),
                                                                            1.0);
            state_equation.set_all_variables(Vib, Kot_usek.get_Ai_array(), Kot_usek.get_DeltaHi_array());
            double T0 = state_equation.compute_sigma_H(1, // load
                    Kot_usek.get_str_rozpatie(), // mid span
                    temperatures_vibration[0], // Tx0
                    temperatures_vibration[1]);// Tx1
            for (int y=0; y<Kot_usek.get_Ai_array().length; y++){
                
                double x_axis = vibration_protection.axis_x_value(T0, Conductor);
                double y_axis = vibration_protection.axis_y_value(a[y], Conductor);
                double c_vib = vibration_protection.c_vib_value((int)Variable_KPB_typ_terenu);
                double EQ_vib = vibration_protection.EQvib_value((int)Variable_KPB_typ_terenu, T0, Conductor);
//                System.out.println("x axis = " + x_axis);
//                System.out.println("y axis = " + y_axis);
//                System.out.println("protection area = " + (int)vibration_protection.evaluate_protection_area(x_axis, y_axis, c_vib, EQ_vib));
                KPB_vysledky[y]= (int)vibration_protection.evaluate_protection_area(x_axis, y_axis, c_vib, EQ_vib);
            }
                        
                
                //////////////////////////////////// kontrola tahov v pracovnych bodoch
            int selected = jComboBox_stav_KPB.getSelectedIndex();
            
            double[] temperatures_base = temperatures_for_state_equation(   Conductor, 
                                                                            Kot_usek, 
                                                                            -5, // pre -5 degree
                                                                            Variable_streda_roc_teplota, 
                                                                            Variable_T0_zivotnost, 
                                                                            50*8760 ); // konecny stav
            State_equation_variables Base = new State_equation_variables(   Conductor,
                                                                            temperatures_base[1],
                                                                            temperatures_base[0],
                                                                            Kot_usek.get_zakladne_mech_napatie_lana_pre_minus5_over(),
                                                                            1.0);
            state_equation.set_all_variables(Base, Kot_usek.get_Ai_array(), Kot_usek.get_DeltaHi_array());
            double sigma_H_base = state_equation.compute_sigma_H(   pretazenia[6],                  // load -5+Nv
                                                                    Kot_usek.get_str_rozpatie(),    // mid span
                                                                    temperatures_base[0], // Tx0
                                                                    temperatures_base[1]);// Tx1
            double c_base = state_equation.compute_c(   sigma_H_base, 
                                                        pretazenia[6], // load -5+Nv
                                                        Conductor);
            
                
            for (int y=0; y<Kot_usek.get_Ai_array().length; y++){
                
                double x_AB_base[] = forces_check.x_ABi(Kot_usek.get_DeltaHi_array()[y], c_base, Kot_usek.get_Ai_array()[y]);
                double x_A_base = x_AB_base[0];
                double x_B_base = x_AB_base[1];
                double F_A_base = forces_check.F_ABi(x_A_base, c_base, Conductor, pretazenia[6]);// load -5+Nv
                double F_B_base = forces_check.F_ABi(x_B_base, c_base, Conductor, pretazenia[6]);// load -5+Nv
                
                
                double[] temperatures_check = temperatures_for_state_equation(  Conductor, 
                                                                                Kot_usek, 
                                                                                Variable_vybrany_stav_pre_KPB, 
                                                                                Variable_streda_roc_teplota, 
                                                                                Variable_T0_zivotnost, 
                                                                                Variable_KPB_cas_vypoctu );
                State_equation_variables Check = new State_equation_variables(  Conductor,
                                                                                temperatures_check[1],
                                                                                temperatures_check[0],
                                                                                Kot_usek.get_zakladne_mech_napatie_lana_pre_minus5_over(),
                                                                                1.0);
                state_equation.set_all_variables(Check, Kot_usek.get_Ai_array(), Kot_usek.get_DeltaHi_array());
                double sigma_H_check = state_equation.compute_sigma_H(  pretazenia[selected],                  // load
                                                                        Kot_usek.get_str_rozpatie(),    // mid span
                                                                        temperatures_check[0], // Tx0
                                                                        temperatures_check[1]);// Tx1
                double c_check = state_equation.compute_c(  sigma_H_check, 
                                                            pretazenia[selected], 
                                                            Conductor);
                double F_check_max = (Variable_maximalne_zataz_lana_podiel_z_RTS*1000)/Conductor.get_S();
                double x_AB[] = forces_check.x_ABi(Kot_usek.get_DeltaHi_array()[y], c_check, Kot_usek.get_Ai_array()[y]);
                double x_A = x_AB[0];
                double x_B = x_AB[1];
                double F_A_mod = forces_check.F_ABi(x_A, c_check, Conductor, pretazenia[selected]);
                double F_B_mod = forces_check.F_ABi(x_B, c_check, Conductor, pretazenia[selected]);

                
                
                
                
                // tu sa zadavaju tahy
                
                tahy[y][0] = F_A_base;  // F_A -> base in kN
                tahy[y][1] = F_B_base;  // F_B -> base in kN
                tahy[y][2] = F_A_mod;  // F_A -> modal in kN
                tahy[y][3] = F_B_mod;  // F_B -> modal in kN
                
            }
            
        ////////////////////////////// vlozenie vysledkov    
        Kot_usek.set_vysledky_vid_priehyb_M(vid_priehyby); 
        Kot_usek.set_vysledky_KPB(KPB_vysledky);
        Kot_usek.set_tahy(tahy);    
        //////////////////////////////   
          

        } // if check box enabled
        } // do  pocet kotevnych usekov
        
        //insert value % into text fields
        int cislo = Table_kotevne_useky.getSelectedRow();
        set_percento_to_mainframe(Variable_globeal_kotevny_usek.get(cislo));
        set_tahy_to_mainframe(true,Variable_globeal_kotevny_usek.get(cislo));
        set_KPB_to_mainframe(true,Variable_globeal_kotevny_usek.get(cislo));
        Calculation_done=true;  // bool prebehla kalkulacia
        Variable_globeal_kotevny_usek_zmena = Variable_globeal_kotevny_usek;
        Label_status.setText(language.language_label(languageOption, 284));
        
        for(int i =0;i<Variable_globeal_kotevny_usek.size();i++ ){  // cyklus pre všetky existujuce
        if(Table_kotevne_useky.getValueAt(i, 0).equals(true)){      // zisti  je zaskrtnuty prv ysstlpc ak nie vypis warning
        Label_status.setText(language.language_label(languageOption, 275));  
        }}
        
        
        } catch(NullPointerException e){
           warning_sign(warning_text);
        
        }
        }
        
    }//GEN-LAST:event_Button_Icon_calculateActionPerformed

    /**
     * 
     * @param Conductor selected conductor -> Conductor_variables class
     * @param Kot_usek selected suspension section -> kotevnyUsek class
     * @param Temperature temepreature from the Variable_teplotny _stav_rovnica -> global variable
     * @param stredna_rocna_teplota average year temperature -> global variable
     * @param T0_zivotnost global variable
     * @param Tp_prechodna_doba global variable
     * @return double[] array in which:
     *      [0] - Tx0 - used as theta0 in state equation
     *      [1] - Tx1 - used as theta1 in state equaton
     */
    private static double[] temperatures_for_state_equation(    Conductor_variables Conductor, 
                                                                kotevnyUsek Kot_usek, 
                                                                double Temperature,
                                                                double stredna_rocna_teplota,
                                                                double T0_zivotnost,
                                                                double Tp_prechodna_doba){
         double[] result_T = new double[2];
 
            // set variables to state equation class 
            // - compute sigma_HT for conductor creeping variable - USING AVERAG YEAR TEMPERATURE
            // - compute thermal shift for selected temperature
            State_equation_variables State = new State_equation_variables(Conductor, stredna_rocna_teplota, -5, Kot_usek.get_zakladne_mech_napatie_lana_pre_minus5_over(), 1);
            state_equation.set_all_variables(State, Kot_usek.get_Ai_array(), Kot_usek.get_DeltaHi_array());
            double sigma_H_creeping = state_equation.compute_sigma_H(1, Kot_usek.get_str_rozpatie(),-5d,stredna_rocna_teplota);
         
//             // set variables to state equation class 
//            // - compute sigma_HT for conductor creeping variable - USING 0 degrees AVG YEAR TEMPERATURE
//            // - compute thermal shift for selected temperature
//            State_equation_variables State = new State_equation_variables(Conductor, 0d, -5d, Kot_usek.get_zakladne_mech_napatie_lana_pre_minus5_over(), 1);
//            state_equation.set_all_variables(State, Kot_usek.get_Ai_array(), Kot_usek.get_DeltaHi_array());
//            double sigma_H_creeping = state_equation.compute_sigma_H(1, Kot_usek.get_str_rozpatie(),-5d,0d);

            
            // set variables to conductor creeping class 
            // - compute thermal shift for selected temperature
            double shift = conductor_creeping.thermal_shift_universal_value(T0_zivotnost, Tp_prechodna_doba, sigma_H_creeping, Conductor, stredna_rocna_teplota);
            result_T[1] = Temperature + shift;  // theta_1
            result_T[0] = -5; // + shift;           // theta_0
            
//            System.out.println("Tx0 = " + result_T[0]);
//            System.out.println("Tx1 = " + result_T[1]);
//            System.out.println("shift = " + shift);
            return result_T;
    }
    
    private  void set_KPB_to_mainframe(Boolean perse,kotevnyUsek X){
        
      Boolean kontrola3 = false;  
        
      // vymaž všetky riadky ak su v tabulle KPB  
     try{ 
         
         int rowCount = Table_KPB.getRowCount();
        for (int i = rowCount ; i > 0; i--) {
            modeltable_KPB.removeRow(i-1);      
        }
        
        
         for (int i =0  ; i < X.get_Ai_array().length; i++) {
            modeltable_KPB.addRow(new Object[0]);
            modeltable_KPB.setValueAt(X.get_Ai_array()[i], i, 0);
            modeltable_KPB.setValueAt(X.get_vysledky_KPB()[i], i, 1);
            if(X.get_vysledky_KPB()[i] == 3 ){
                kontrola3=true;
            }
            
           // Table_KPB.getComponentAt(0, 0).SETsetToolTipText(filename);
        }
        if(perse==true){
         warning_text =language.language_label(languageOption, 303) + X.get_name();
     if( kontrola3==true){
         throw new NullPointerException();
         } }
         
        
     }catch(NullPointerException e ){Swriter("no data");
     if(perse==true){warning_sign(warning_text);}}   
    }
    
     private  void set_tahy_to_mainframe(Boolean perse,kotevnyUsek X){
        
            
         
       DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
               otherSymbols.setDecimalSeparator('.');
               DecimalFormat df = new DecimalFormat("####.##",otherSymbols);  // definovany počet desatinnych miest
                 
         
      // vymaž všetky riadky ak su v tabulle tahy
     try{ 
         
         int rowCount = Table_tahy.getRowCount();
        for (int i = rowCount ; i > 0; i--) {
            modeltable_tahy.removeRow(i-1);      
        }
        
        double max_tah1 = 0.0;
        double max_tah2 = 0.0;
         for (int i =0  ; i < X.get_Ai_array().length; i++) {
            modeltable_tahy.addRow(new Object[0]);
            modeltable_tahy.setValueAt(df.format(X.get_tahy()[i][0]), i, 0);
            modeltable_tahy.setValueAt(df.format(X.get_tahy()[i][1]), i, 1);
            modeltable_tahy.setValueAt(df.format(X.get_tahy()[i][2]), i, 2);
            modeltable_tahy.setValueAt(df.format(X.get_tahy()[i][3]), i, 3);
          
            if(X.get_tahy()[i][0] > max_tah1){
                max_tah1=X.get_tahy()[i][0];
            }
            if(X.get_tahy()[i][1] > max_tah1){
                max_tah1=X.get_tahy()[i][1];
            }
            if(X.get_tahy()[i][2] > max_tah2){
                max_tah2=X.get_tahy()[i][2];
            }
            if(X.get_tahy()[i][3] > max_tah2){
                max_tah2=X.get_tahy()[i][3];
            }
            
        }
         
         double podielzRTS1 = (max_tah1 / X.get_RTS_over())*100;
         double podielzRTS2 = (max_tah2 / X.get_RTS_over())*100;
         podiel_z_RTS1.setText(df.format(podielzRTS1));
         podiel_z_RTS2.setText(df.format(podielzRTS2));
         
     if(perse == true){
     warning_text =language.language_label(languageOption, 302) + X.get_name();
     if( podielzRTS1 > X.get_maximalne_zataz_lana_podiel_z_RTS_over()){
         throw new NullPointerException();
         }    
     } 
         
     }catch(NullPointerException e ){Swriter("no data");
      
     if(perse ==true){warning_sign(warning_text);}}  
     
     
     
     
    }
     
     
     
     private  void set_percento_to_mainframe(kotevnyUsek X){
        
       
     try{ 
          vyp_percento1_sigma.setForeground(Color.BLACK);
     vyp_percento2_sigma.setForeground(Color.BLACK);
     vyp_percento3_sigma.setForeground(Color.BLACK);
     vyp_percento4_sigma.setForeground(Color.BLACK);
     DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
               otherSymbols.setDecimalSeparator('.');
               DecimalFormat df = new DecimalFormat("###.##",otherSymbols);
               
         vyp_percento1_sigma.setText( df.format(X.get_vysledky_per_podiel_sigma()[1][0]) );
         vyp_percento2_sigma.setText( df.format(X.get_vysledky_per_podiel_sigma()[1][1]) );
         vyp_percento3_sigma.setText( df.format(X.get_vysledky_per_podiel_sigma()[1][2]) );
         vyp_percento4_sigma.setText( df.format(X.get_vysledky_per_podiel_sigma()[1][3]) );
        
     }catch(NullPointerException e ){Swriter("no data");
        vyp_percento1_sigma.setBackground(Color.ORANGE);
        vyp_percento2_sigma.setForeground(Color.ORANGE);
        vyp_percento3_sigma.setForeground(Color.ORANGE);
        vyp_percento4_sigma.setForeground(Color.ORANGE);
         vyp_percento1_sigma.setText( language.language_label(languageOption, 258) );
         vyp_percento2_sigma.setText( language.language_label(languageOption, 258)  );
         vyp_percento3_sigma.setText( language.language_label(languageOption, 258)  );
         vyp_percento4_sigma.setText( language.language_label(languageOption, 258)  );
    }
     
     
     
     
     }
    
    
    private void Button_Icon_arr_row_table_kotevny_usekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_arr_row_table_kotevny_usekActionPerformed
        selection_kotevny_usek=true;
        if ( first_Start==true){
        
           new_kotevny_usek_name=language.language_label(languageOption, 60);
           
            modelTable.addRow(new Object[]{(Boolean) false,(String) new_kotevny_usek_name});
            double[] empty = null;
            double[][] empty2 = null;
                 double[] hodnoty = new double[4];
             
        try{hodnoty[0] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[0]));}catch(NullPointerException e){hodnoty[0]=0;}
        try{hodnoty[1] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[1]));}catch(NullPointerException e){hodnoty[1]=0;}
        try{hodnoty[2] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[2]));}catch(NullPointerException e){hodnoty[2]=0;}
        try{hodnoty[3] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[3]));}catch(NullPointerException e){hodnoty[3]=0;}
      
        kotevnyUsek novy_usek =  new kotevnyUsek(new_kotevny_usek_name, 0,"0", 0, 0, 0, 0, language.language_label(languageOption, 60), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, teplotyser, teplotyser, teplotyser, teplotyser, teplotyser, teplotyser, Variable_Ai_array, Variable_Hi_array, Variable_Hi_array,Variable_Hi_array_nmv,0,0,0,hodnoty);    
        
        
        Variable_globeal_kotevny_usek.add(novy_usek);
            mainframe_to_kotevny_usek(novy_usek,0);
            Table_kotevne_useky.changeSelection(0, 1, false, false);
           
           
           first_Start=false;
           
               }else{     
        mainframe_new_kotevny_usek_1 mainframe_new_kotevny_usek_JDialog_window = new mainframe_new_kotevny_usek_1(this, rootPaneCheckingEnabled);
        mainframe_new_kotevny_usek_JDialog_window.setVisible(true);
        Label_status.setText(language.language_label(languageOption, 279));
                       }
        
        
        if (existnewkotevnyusek == true){  // ak pride že vytvorit od Jdialog tak vytvor ak uzivatel zavie Jdilog križiok tam nie
            modelTable.addRow(new Object[]{(Boolean) false,(String) new_kotevny_usek_name});
            double[] empty = null;
            double[][] empty2 = null;
                 double[] hodnoty = new double[4];
             
        try{hodnoty[0] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[0]));}catch(NullPointerException e){hodnoty[0]=0;}
        try{hodnoty[1] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[1]));}catch(NullPointerException e){hodnoty[1]=0;}
        try{hodnoty[2] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[2]));}catch(NullPointerException e){hodnoty[2]=0;}
        try{hodnoty[3] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[3]));}catch(NullPointerException e){hodnoty[3]=0;}
           
        
         int rowNumber =  Table_kotevne_useky.getSelectedRow(); //- (e.getFirstIndex()-e.getLastIndex()); 
             kotevnyUsek docasny_kot_usek = new kotevnyUsek(new_kotevny_usek_name, 0,"0", 0, 0, 0, 0, filename, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, teplotyser, first_Start, teplotyser, teplotyser, teplotyser, first_Start, Variable_Ai_array, Variable_Hi_array, Variable_Hi_array,Variable_Hi_array_nmv,0,0,0,hodnoty);                     
             mainframe_to_kotevny_usek(docasny_kot_usek,rowNumber);

        kotevnyUsek novy_usek =  docasny_kot_usek;
        novy_usek.set_name(new_kotevny_usek_name);
        novy_usek.set_Ai_array(empty);
        novy_usek.set_Hi_array(empty);
        novy_usek.set_DeltaHi_array(empty);
        novy_usek.set_Hi_array_nvm(empty);
        
        //new kotevnyUsek(new_kotevny_usek_name, 0, 0, 1, 0, 0, "KOKOT", 4,0.0, 50, 50, 1, 500, 1, 1, 123456789.987654321, 0.189, 0.05, 24.0, 1, 1, 1, 1, 1, 0.35, 0.25, 0.656, 3, 0, 1.25, 1.1, 0.0, true, true, true, true, true, true, empty, empty, empty,empty,0,0,0,hodnoty,empty,empty,empty,empty,empty,empty,empty2);    
        Variable_globeal_kotevny_usek.add(novy_usek);
            
            
            existnewkotevnyusek = false;
        }
        selection_kotevny_usek=false;
    }//GEN-LAST:event_Button_Icon_arr_row_table_kotevny_usekActionPerformed
    private void Button_Icon_delete_row_table_kotevny_usekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_delete_row_table_kotevny_usekActionPerformed

        int selectedRow = Table_kotevne_useky.getSelectedRow();

        if ( selectedRow != -1){
        
        if (selectedRow-1 != -1){    
            Table_kotevne_useky.changeSelection(selectedRow-1, 1, false, false);       
            modelTable.removeRow(selectedRow);
            Variable_globeal_kotevny_usek.remove(selectedRow);
            Label_status.setText(language.language_label(languageOption, 280));
        }}
    }//GEN-LAST:event_Button_Icon_delete_row_table_kotevny_usekActionPerformed

    private void Button_Icon_save_asActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_save_asActionPerformed
       
         //RESAVE ACTUAL SCREEN
        int rowNumber =  Table_kotevne_useky.getSelectedRow(); //- (e.getFirstIndex()-e.getLastIndex()); 
             double[] empty = null;
             double[][] empty2 = null;
             double[] hodnoty = new double[4];
             
        try{hodnoty[0] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[0]));}catch(NullPointerException e){hodnoty[0]=0;}
        try{hodnoty[1] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[1]));}catch(NullPointerException e){hodnoty[1]=0;}
        try{hodnoty[2] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[2]));}catch(NullPointerException e){hodnoty[2]=0;}
        try{hodnoty[3] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[3]));}catch(NullPointerException e){hodnoty[3]=0;}
             kotevnyUsek docasny_kot_usek = new kotevnyUsek(new_kotevny_usek_name, 0,"0", 0, 0, 0, 0, filename, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, teplotyser, first_Start, teplotyser, teplotyser, teplotyser, first_Start, Variable_Ai_array, Variable_Hi_array, Variable_Hi_array,Variable_Hi_array_nmv,0,0,0,hodnoty);                     
             mainframe_to_kotevny_usek(docasny_kot_usek,rowNumber);                        
             Variable_globeal_kotevny_usek.set(rowNumber, docasny_kot_usek);  
          
        project_save_as=true;
        String no= "";
        try {
            save_project(no);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(mainframe_17.class.getName()).log(Level.SEVERE, null, ex);
        }
        Label_status.setText(language.language_label(languageOption, 274));
        
    }//GEN-LAST:event_Button_Icon_save_asActionPerformed

    private void Button_Icon_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_saveActionPerformed
       
        
        //RESAVE ACTUAL SCREEN
        int rowNumber =  Table_kotevne_useky.getSelectedRow(); //- (e.getFirstIndex()-e.getLastIndex()); 
             double[] empty = null;
             double[][] empty2 = null;
              double[] hodnoty = new double[4];
             
        try{hodnoty[0] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[0]));}catch(NullPointerException e){hodnoty[0]=0;}
        try{hodnoty[1] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[1]));}catch(NullPointerException e){hodnoty[1]=0;}
        try{hodnoty[2] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[2]));}catch(NullPointerException e){hodnoty[2]=0;}
        try{hodnoty[3] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[3]));}catch(NullPointerException e){hodnoty[3]=0;}
             kotevnyUsek docasny_kot_usek = new kotevnyUsek(new_kotevny_usek_name, 0,"0", 0, 0, 0, 0, filename, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, teplotyser, first_Start, teplotyser, teplotyser, teplotyser, first_Start, Variable_Ai_array, Variable_Hi_array, Variable_Hi_array,Variable_Hi_array_nmv,0,0,0,hodnoty);                     
             mainframe_to_kotevny_usek(docasny_kot_usek,rowNumber);                        
             Variable_globeal_kotevny_usek.set(rowNumber, docasny_kot_usek);  
          
        project_save_as=false;
        String no= "";
        try {
            save_project(no);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(mainframe_17.class.getName()).log(Level.SEVERE, null, ex);
        }
        Label_status.setText(language.language_label(languageOption, 273));
    }//GEN-LAST:event_Button_Icon_saveActionPerformed

    private void Button_pdf_pageupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_pdf_pageupActionPerformed
      
     if(pocet_pdf_stran == 0){}else{   
     current_pdf_page=current_pdf_page+1;

     if ( current_pdf_page > pocet_pdf_stran){ 
     current_pdf_page=1;   
     }
     String userhome = System.getProperty("user.dir"); 
     String path = userhome + "\\temp\\" + "temp1Pdf.pdf-" +current_pdf_page +".png";
     ImageIcon icon = new ImageIcon(path);
     java.awt.Image img = icon.getImage();
     jLabel5.setIcon(new ImageIcon(img.getScaledInstance(jLabel5.getWidth(), jLabel5.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING)));
     }  
    }//GEN-LAST:event_Button_pdf_pageupActionPerformed

    private void Button_pdf_pagedownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_pdf_pagedownActionPerformed
      if(pocet_pdf_stran == 0){}else{   
     current_pdf_page=current_pdf_page-1;

     if ( current_pdf_page < 1){ 
     current_pdf_page=pocet_pdf_stran;   
     }
     String userhome = System.getProperty("user.dir"); 
     String path = userhome + "\\temp\\" + "temp1Pdf.pdf-" +current_pdf_page +".png";
     ImageIcon icon = new ImageIcon(path);
     java.awt.Image img = icon.getImage();
     jLabel5.setIcon(new ImageIcon(img.getScaledInstance(jLabel5.getWidth(), jLabel5.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING)));
     } 
        
        
        
    }//GEN-LAST:event_Button_pdf_pagedownActionPerformed

    private void Textfield_casActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Textfield_casActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Textfield_casActionPerformed

    private void Button_Icon_export_PDF_externalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_export_PDF_externalActionPerformed
       
        pdf_internal=false;
        pdf_external=true;
        pdf_as=false;
        pdf_manager();
        Label_status.setText(language.language_label(languageOption, 277));
        
    }//GEN-LAST:event_Button_Icon_export_PDF_externalActionPerformed

    private void Button_Icon_export_PDF2_save_asActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Icon_export_PDF2_save_asActionPerformed
        
        pdf_internal=false;
        pdf_external=false;
        pdf_as=true;
        pdf_manager();
        Label_status.setText(language.language_label(languageOption, 278));
        
    }//GEN-LAST:event_Button_Icon_export_PDF2_save_asActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        int cislo = Table_kotevne_useky.getSelectedRow();
        set_KPB_to_mainframe(false,Variable_globeal_kotevny_usek.get(cislo));
        set_tahy_to_mainframe(false,Variable_globeal_kotevny_usek.get(cislo));
        tah_konecne1.setText(language.language_label(languageOption, 283) + String.valueOf(jComboBox_stav_KPB.getSelectedItem()) + language.language_label(languageOption, 285));
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void podiel_z_RTS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_podiel_z_RTS1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_podiel_z_RTS1ActionPerformed

    private void Table_KPBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_KPBMouseEntered

    }//GEN-LAST:event_Table_KPBMouseEntered

    private void Table_KPBMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_KPBMouseMoved

        Point point = evt.getPoint();
        int row;
        row 	= Table_KPB.rowAtPoint(point);
        int hodnota = (int)Table_KPB.getValueAt(row, 1);
        if(hodnota == 1){

            Table_KPB.setToolTipText(language.language_label(languageOption, 255));

        }
        if(hodnota == 2){

            Table_KPB.setToolTipText(language.language_label(languageOption, 256));

        }
        if(hodnota == 3){

            Table_KPB.setToolTipText(language.language_label(languageOption, 257));

        }

    }//GEN-LAST:event_Table_KPBMouseMoved

    private void Table_rozpatia_nadm_vyskyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table_rozpatia_nadm_vyskyKeyReleased

    }//GEN-LAST:event_Table_rozpatia_nadm_vyskyKeyReleased

    private void Table_rozpatiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table_rozpatiaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_Table_rozpatiaKeyTyped

    private void Table_rozpatiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table_rozpatiaKeyReleased

    }//GEN-LAST:event_Table_rozpatiaKeyReleased

    private void Table_rozpatiaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_Table_rozpatiaInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Table_rozpatiaInputMethodTextChanged

    private void vyp_percento4_sigmaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vyp_percento4_sigmaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_vyp_percento4_sigmaKeyReleased

    private void vyp_percento4_sigmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vyp_percento4_sigmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vyp_percento4_sigmaActionPerformed

    private void vyp_percento3_sigmaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vyp_percento3_sigmaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_vyp_percento3_sigmaKeyReleased

    private void vyp_percento3_sigmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vyp_percento3_sigmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vyp_percento3_sigmaActionPerformed

    private void vyp_percento2_sigmaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vyp_percento2_sigmaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_vyp_percento2_sigmaKeyReleased

    private void vyp_percento2_sigmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vyp_percento2_sigmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vyp_percento2_sigmaActionPerformed

    private void vyp_percento1_sigmaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vyp_percento1_sigmaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_vyp_percento1_sigmaKeyReleased

    private void vyp_percento1_sigmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vyp_percento1_sigmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vyp_percento1_sigmaActionPerformed

    private void percento4_sigmaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_percento4_sigmaKeyReleased
        percento_podiel_namrazy_sigma4=doubleChecker_short_answer(percento4_sigma);
    }//GEN-LAST:event_percento4_sigmaKeyReleased

    private void percento4_sigmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_percento4_sigmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_percento4_sigmaActionPerformed

    private void percento3_sigmaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_percento3_sigmaKeyReleased
        percento_podiel_namrazy_sigma3=doubleChecker_short_answer(percento3_sigma);
    }//GEN-LAST:event_percento3_sigmaKeyReleased

    private void percento3_sigmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_percento3_sigmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_percento3_sigmaActionPerformed

    private void percento2_sigmaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_percento2_sigmaKeyReleased
        percento_podiel_namrazy_sigma2=doubleChecker_short_answer(percento2_sigma);
    }//GEN-LAST:event_percento2_sigmaKeyReleased

    private void percento2_sigmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_percento2_sigmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_percento2_sigmaActionPerformed

    private void percento1_sigmaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_percento1_sigmaKeyReleased
        percento_podiel_namrazy_sigma1=doubleChecker_short_answer(percento1_sigma);
    }//GEN-LAST:event_percento1_sigmaKeyReleased

    private void percento1_sigmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_percento1_sigmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_percento1_sigmaActionPerformed

    private void jRadioButton_Bi_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Bi_1ActionPerformed
        TextField_Bi_1.setEnabled(true);
        TextField_Bi2.setEnabled(false);
        TextField_Bi2.setEditable(false);
        Variable_Bi=double_setter(doubleChecker_short_answer(TextField_Bi_1));
        pretazenia_intomainframe();
    }//GEN-LAST:event_jRadioButton_Bi_1ActionPerformed

    private void TextField_Bi_1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_Bi_1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_Bi_1KeyReleased

    private void TextField_Bi_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_Bi_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_Bi_1ActionPerformed

    private void jRadioButton_Bi_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Bi_2ActionPerformed
        TextField_Bi2.setEnabled(true);
        TextField_Bi_1.setEnabled(false);
        TextField_Bi2.setEditable(true);
        Variable_Bi=double_setter(doubleChecker_short_answer(TextField_Bi2));
        pretazenia_intomainframe();
    }//GEN-LAST:event_jRadioButton_Bi_2ActionPerformed

    private void TextField_Bi2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_Bi2KeyReleased
        Variable_Bi=double_setter(doubleChecker_short_answer(TextField_Bi2));
        pretazenia_intomainframe();
    }//GEN-LAST:event_TextField_Bi2KeyReleased

    private void TextField_KclKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_KclKeyReleased
        Variable_Klc=double_setter(doubleChecker_short_answer(TextField_Kcl));
        pretazenia_intomainframe();
    }//GEN-LAST:event_TextField_KclKeyReleased

    private void TextField_KclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_KclActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_KclActionPerformed

    private void jRadioButton_Kh_HActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Kh_HActionPerformed
       // TextField_Kh_H.setEnabled(true);
        TextField_Kh_noH.setEnabled(false);
        Variable_Kh=double_setter(-1);
        pretazenia_intomainframe();
    }//GEN-LAST:event_jRadioButton_Kh_HActionPerformed

    private void jRadioButton_Kh_noHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Kh_noHActionPerformed
        TextField_Kh_noH.setEnabled(true);
        Variable_Kh=double_setter(1.0);
        Variable_Kh=double_setter(doubleChecker_short_answer(TextField_Kh_noH));
        pretazenia_intomainframe();
      //  TextField_Kh_H.setEnabled(false);
    }//GEN-LAST:event_jRadioButton_Kh_noHActionPerformed

    private void jRadioButton_Klc_vlastnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Klc_vlastnaActionPerformed
        TextField_Kcl.setEnabled(true);
        Variable_Klc=double_setter(doubleChecker_short_answer(TextField_Kcl));
        pretazenia_intomainframe();
    }//GEN-LAST:event_jRadioButton_Klc_vlastnaActionPerformed

    private void jRadioButton_Klc_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Klc_1ActionPerformed
        Variable_Klc=double_setter(1.0);
        pretazenia_intomainframe();
        TextField_Kcl.setEnabled(false);
    }//GEN-LAST:event_jRadioButton_Klc_1ActionPerformed

    private void TextField_hustota_namrazyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_hustota_namrazyKeyReleased
        if (TextField_hustota_namrazy.isEditable() == true){

            Variable_hustota_namrazy=double_setter(doubleChecker_short_answer(TextField_hustota_namrazy));
            pretazenia_intomainframe();

        }
    }//GEN-LAST:event_TextField_hustota_namrazyKeyReleased

    private void TextField_hustota_namrazyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_hustota_namrazyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_hustota_namrazyActionPerformed

    private void TextField_CclKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_CclKeyReleased

        if (TextField_Ccl.isEditable() == true){

            Variable_Ccl=double_setter(doubleChecker_short_answer(TextField_Ccl));
            pretazenia_intomainframe();

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
                Variable_Ccl =double_setter( 1.1);
                pretazenia_intomainframe();
                Variable_hustota_namrazy= double_setter(500.0);
                pretazenia_intomainframe();

                break;
                case 1:  TextField_Ccl.setEnabled(false);
                TextField_hustota_namrazy.setEnabled(false);
                Variable_Ccl = double_setter(1.1);
                pretazenia_intomainframe();
                Variable_hustota_namrazy= double_setter(900.0);
                pretazenia_intomainframe();
                break;
                case 2:  TextField_Ccl.setEnabled(false);
                TextField_hustota_namrazy.setEnabled(false);
                Variable_Ccl = double_setter(1.1);
                pretazenia_intomainframe();
                Variable_hustota_namrazy= double_setter(300.0);
                pretazenia_intomainframe();
                break;
                case 3:  TextField_Ccl.setEnabled(false);
                TextField_hustota_namrazy.setEnabled(false);
                Variable_Ccl =double_setter( 1.1);
                pretazenia_intomainframe();
                Variable_hustota_namrazy= double_setter(700.0);
                pretazenia_intomainframe();
                break;
                case 4:  TextField_Ccl.setEnabled(false);
                TextField_hustota_namrazy.setEnabled(false);
                Variable_Ccl =double_setter( 1.1);
                pretazenia_intomainframe();
                Variable_hustota_namrazy= double_setter(500.0);
                pretazenia_intomainframe();
                break;
                case 5:  TextField_Ccl.setEnabled(true);
                TextField_hustota_namrazy.setEnabled(true);
                Variable_Ccl = double_setter(1.1);
                pretazenia_intomainframe();
                Variable_hustota_namrazy= double_setter(500.0);
                pretazenia_intomainframe();
                TextField_Ccl.setForeground(Color.black);
                TextField_hustota_namrazy.setForeground(Color.black);
                break;
            }
            TextField_Ccl.setText(String.valueOf(Variable_Ccl));
            TextField_hustota_namrazy.setText(String.valueOf(Variable_hustota_namrazy));

        }
    }//GEN-LAST:event_jComboBox_druh_namrazyActionPerformed

    private void Button_namrazova_oblastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_namrazova_oblastActionPerformed

        mainframe_namrazova_oblast_jDialog_1 mainframe_namrazova_oblast_jDialog_window = new mainframe_namrazova_oblast_jDialog_1(this, rootPaneCheckingEnabled);
        mainframe_namrazova_oblast_jDialog_window.setVisible(true);

        if(is_namrazove_oblasti_setted== true){

            Label_vybrana_namrazova_oblast.setText(namrazove_oblasti_názov_oblasti);
            Variable_Ir50=double_setter(vypocet_IR50_namrazove_oblasti());
            pretazenia_intomainframe();
            Variable_Ir50=double_setter(vypocet_IR50_namrazove_oblasti());
            pretazenia_intomainframe();
        }
    }//GEN-LAST:event_Button_namrazova_oblastActionPerformed

    private void jRadioButton_with_label_vypoctanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_label_vypoctanaActionPerformed
        TextField_hcmean_vlastna.setEnabled(false);
        Variable_Hc_mean=double_setter(Variable_Hc_mean_medzikrok);
        pretazenia_intomainframe();
    }//GEN-LAST:event_jRadioButton_with_label_vypoctanaActionPerformed

    private void TextField_hcmean_vpocitanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_hcmean_vpocitanaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_hcmean_vpocitanaActionPerformed

    private void TextField_hcmean_vlastnaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_hcmean_vlastnaKeyReleased

        if(TextField_hcmean_vlastna.isEditable() == true){
            if(TextField_hcmean_vlastna.getText().equals("-"))TextField_hcmean_vlastna.setText("");

            Variable_Hc_mean=double_setter(doubleChecker_short_answer(TextField_hcmean_vlastna));
            pretazenia_intomainframe();
        }

    }//GEN-LAST:event_TextField_hcmean_vlastnaKeyReleased

    private void TextField_hcmean_vlastnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_hcmean_vlastnaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_hcmean_vlastnaActionPerformed

    private void jRadioButton_with_label_vlastnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_label_vlastnaActionPerformed
        TextField_hcmean_vlastna.setEnabled(true);
        Variable_Hc_mean=double_setter(doubleChecker_short_answer(TextField_hcmean_vlastna));
        pretazenia_intomainframe();

    }//GEN-LAST:event_jRadioButton_with_label_vlastnaActionPerformed

    private void TextField_dlzka_drsnjosti_zoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_dlzka_drsnjosti_zoKeyReleased
        Variable_char_terenu_Zo=double_setter(doubleChecker_short_answer(TextField_dlzka_drsnjosti_zo));
        pretazenia_intomainframe();
    }//GEN-LAST:event_TextField_dlzka_drsnjosti_zoKeyReleased

    private void TextField_dlzka_drsnjosti_zoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_dlzka_drsnjosti_zoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_dlzka_drsnjosti_zoActionPerformed

    private void TextField_KrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_KrKeyReleased
        Variable_char_terenu_Kr=double_setter(doubleChecker_short_answer(TextField_Kr));
        pretazenia_intomainframe();
    }//GEN-LAST:event_TextField_KrKeyReleased

    private void TextField_KrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_KrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_KrActionPerformed

    private void jComboBox_char_terenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_char_terenuActionPerformed

        if (mainframeLodaed == true) {

            int selected_index_from_JComboBox = jComboBox_char_terenu.getSelectedIndex();

            switch (selected_index_from_JComboBox) {
                case 0:  TextField_Kr.setEnabled(false);
                TextField_dlzka_drsnjosti_zo.setEnabled(false);
                Variable_char_terenu_Kr = double_setter(0.169);
                pretazenia_intomainframe();
                Variable_char_terenu_Zo= double_setter(0.01);
                pretazenia_intomainframe();
                jTextPane_char_terenu.setText(language.language_label(languageOption, 135));
                break;
                case 1:  TextField_Kr.setEnabled(false);
                TextField_dlzka_drsnjosti_zo.setEnabled(false);
                Variable_char_terenu_Kr = double_setter(0.189);
                pretazenia_intomainframe();
                Variable_char_terenu_Zo= double_setter(0.05);
                pretazenia_intomainframe();
                jTextPane_char_terenu.setText(language.language_label(languageOption, 136));
                break;
                case 2:  TextField_Kr.setEnabled(false);
                TextField_dlzka_drsnjosti_zo.setEnabled(false);
                Variable_char_terenu_Kr = double_setter(0.214);
                pretazenia_intomainframe();
                Variable_char_terenu_Zo= double_setter(0.3);
                pretazenia_intomainframe();
                jTextPane_char_terenu.setText(language.language_label(languageOption, 137));
                break;
                case 3:  TextField_Kr.setEnabled(false);
                TextField_dlzka_drsnjosti_zo.setEnabled(false);
                Variable_char_terenu_Kr = double_setter(0.233);
                pretazenia_intomainframe();
                Variable_char_terenu_Zo= double_setter(1);
                pretazenia_intomainframe();
                jTextPane_char_terenu.setText(language.language_label(languageOption, 139));
                break;
                case 4:  TextField_Kr.setEnabled(true);
                TextField_dlzka_drsnjosti_zo.setEnabled(true);
                Variable_char_terenu_Kr = double_setter(0.169);
                pretazenia_intomainframe();
                Variable_char_terenu_Zo= double_setter(0.01);
                pretazenia_intomainframe();
                TextField_Kr.setForeground(Color.black);
                TextField_dlzka_drsnjosti_zo.setForeground(Color.black);
                jTextPane_char_terenu.setText(language.language_label(languageOption, 139));
                break;
            }
            TextField_Kr.setText(String.valueOf(Variable_char_terenu_Kr));
            TextField_dlzka_drsnjosti_zo.setText(String.valueOf(Variable_char_terenu_Zo));

        }

    }//GEN-LAST:event_jComboBox_char_terenuActionPerformed

    private void TextField_vetrova_oblast_C0KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_vetrova_oblast_C0KeyReleased
        Variable_Co=double_setter(doubleChecker_short_answer(TextField_vetrova_oblast_C0));
        pretazenia_intomainframe();
    }//GEN-LAST:event_TextField_vetrova_oblast_C0KeyReleased

    private void TextField_vetrova_oblast_C0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_vetrova_oblast_C0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_vetrova_oblast_C0ActionPerformed

    private void jRadioButton_vetrova_oblast_C0_vlastnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_vetrova_oblast_C0_vlastnaActionPerformed
        TextField_vetrova_oblast_C0.setEnabled(true);
        Variable_Co=double_setter(doubleChecker_short_answer(TextField_vetrova_oblast_C0));
        pretazenia_intomainframe();
    }//GEN-LAST:event_jRadioButton_vetrova_oblast_C0_vlastnaActionPerformed

    private void jRadioButton_vetrova_oblast_C0_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_vetrova_oblast_C0_1ActionPerformed
        Variable_Co=double_setter(1.0);
        pretazenia_intomainframe();
        TextField_vetrova_oblast_C0.setEnabled(false);
    }//GEN-LAST:event_jRadioButton_vetrova_oblast_C0_1ActionPerformed

    private void TextField_vetrova_oblast_CdirKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_vetrova_oblast_CdirKeyReleased
        Variable_Cdir=double_setter(doubleChecker_short_answer(TextField_vetrova_oblast_Cdir));
        pretazenia_intomainframe();
    }//GEN-LAST:event_TextField_vetrova_oblast_CdirKeyReleased

    private void TextField_vetrova_oblast_CdirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_vetrova_oblast_CdirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_vetrova_oblast_CdirActionPerformed

    private void jRadioButton_vetrova_oblast_Cdir_vlastnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_vetrova_oblast_Cdir_vlastnaActionPerformed
        TextField_vetrova_oblast_Cdir.setEnabled(true);
        Variable_Cdir=double_setter(doubleChecker_short_answer(TextField_vetrova_oblast_Cdir));
    }//GEN-LAST:event_jRadioButton_vetrova_oblast_Cdir_vlastnaActionPerformed

    private void jRadioButton_vetrova_oblast_Cdir_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_vetrova_oblast_Cdir_1ActionPerformed
        Variable_Cdir=double_setter(1.0);
        pretazenia_intomainframe();
        TextField_vetrova_oblast_Cdir.setEnabled(false);
    }//GEN-LAST:event_jRadioButton_vetrova_oblast_Cdir_1ActionPerformed

    private void TextField_Vmean_0KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_Vmean_0KeyReleased
        Variable_V_mean_0=double_setter(doubleChecker_short_answer(TextField_Vmean_0));
        pretazenia_intomainframe();
    }//GEN-LAST:event_TextField_Vmean_0KeyReleased

    private void TextField_Vmean_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_Vmean_0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_Vmean_0ActionPerformed

    private void jComboBox_vetrova_oblastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_vetrova_oblastActionPerformed
        if (mainframeLodaed == true) {

            int selected_index_from_JComboBox = jComboBox_vetrova_oblast.getSelectedIndex();

            switch (selected_index_from_JComboBox) {
                case 0:
                TextField_Vmean_0.setEnabled(false);
                Variable_V_mean_0 = double_setter(24);
                pretazenia_intomainframe();
                break;
                case 1:
                TextField_Vmean_0.setEnabled(false);
                Variable_V_mean_0 = double_setter(26);
                pretazenia_intomainframe();
                break;
                case 2:
                TextField_Vmean_0.setEnabled(false);
                Variable_V_mean_0 = double_setter(30);
                pretazenia_intomainframe();
                break;
                case 3:
                TextField_Vmean_0.setEnabled(false);
                Variable_V_mean_0 = double_setter(33);
                pretazenia_intomainframe();
                break;
                case 4:
                TextField_Vmean_0.setEnabled(false);
                Variable_V_mean_0 = double_setter(22.5);
                pretazenia_intomainframe();
                break;
                case 5:
                TextField_Vmean_0.setEnabled(false);
                Variable_V_mean_0 = double_setter(25);
                pretazenia_intomainframe();
                break;
                case 6:
                TextField_Vmean_0.setEnabled(false);
                Variable_V_mean_0 = double_setter(27.5);
                pretazenia_intomainframe();
                break;
                case 7:
                TextField_Vmean_0.setEnabled(false);
                Variable_V_mean_0 = double_setter(30);
                pretazenia_intomainframe();
                break;
                case 8:
                TextField_Vmean_0.setEnabled(false);
                Variable_V_mean_0 = double_setter(36);
                pretazenia_intomainframe();
                break;

                case 9:
                TextField_Vmean_0.setEnabled(true);
                Variable_V_mean_0 = double_setter(24);
                pretazenia_intomainframe();
                TextField_Vmean_0.setForeground(Color.black);
                break;
            }
            TextField_Vmean_0.setText(String.valueOf(Variable_V_mean_0));

        }
    }//GEN-LAST:event_jComboBox_vetrova_oblastActionPerformed

    private void TextField_max_mech_podiel_z_RTSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_max_mech_podiel_z_RTSKeyReleased
        Variable_maximalne_zataz_lana_podiel_z_RTS=Variable_RTS*((doubleChecker_short_answer(TextField_max_mech_podiel_z_RTS))/(100));
    }//GEN-LAST:event_TextField_max_mech_podiel_z_RTSKeyReleased

    private void TextField_max_mech_podiel_z_RTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_max_mech_podiel_z_RTSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_max_mech_podiel_z_RTSActionPerformed

    private void TextField_zakladne_mech_lana_minus5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_zakladne_mech_lana_minus5KeyReleased
        
       
       
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("###.##",otherSymbols); // definovany počet desatinnych miest
       
        
        Variable_zakladne_mech_napatie_lana_pre_minus5=doubleChecker_short_answer(TextField_zakladne_mech_lana_minus5);
        if ( Variable_zakladne_mech_napatie_lana_pre_minus5 < 0){
            Variable_zakladne_mech_napatie_lana_pre_minus5=Variable_zakladne_mech_napatie_lana_pre_minus5 * (-1);
            TextField_zakladne_mech_lana_minus5.setText(df.format(Variable_zakladne_mech_napatie_lana_pre_minus5));
        }
        
        
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

            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
            otherSymbols.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("###.###",otherSymbols); // definovany počet desatinnych miest
            TextField_RTS.setText(df.format(Variable_RTS));

            if (is_namrazove_oblasti_setted == true){   //  ak už je zvolena namrazova oblast tk spusti vypocet
                Variable_Ir50=double_setter(vypocet_IR50_namrazove_oblasti());
                pretazenia_intomainframe();
            }

            double lentak = double_setter(1); /// aby prenbehol vypočet zažťeženi
            pretazenia_intomainframe();
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
            tah_konecne1.setText(language.language_label(languageOption, 283) + String.valueOf(jComboBox_stav_KPB.getSelectedItem()) + language.language_label(languageOption, 285));

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

        if (mainframeLodaed == true && urovenspolahlivostiblocker == true) {

            int selected_index_from_JComboBox = jComboBox_uroven_splahlivosti.getSelectedIndex();

            switch (selected_index_from_JComboBox) {
                case 0:  //50 rokov
                Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti = double_setter(50);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Yw=double_setter(1);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Yi=double_setter(1);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Ww=double_setter(0.25);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Wi=double_setter(0.35);
                pretazenia_intomainframe();
                break;
                case 1:  //150 rokov
                Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti = double_setter(150);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Yw=double_setter(1.2);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Yi=double_setter(1.25);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Ww=double_setter(0.25);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Wi=double_setter(0.35);
                pretazenia_intomainframe();
                break;
                case 2:  //500 rokov
                Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti = double_setter(500);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Yw=double_setter(1.4);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Yi=double_setter(1.5);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Ww=double_setter(0.25);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Wi=double_setter(0.35);
                pretazenia_intomainframe();
                break;
                case 3:  //3 dni
                Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti =double_setter( 2);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Yw=double_setter(0.52);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Yi=double_setter(0.26);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Ww=double_setter(0.22);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Wi=double_setter(0.35);
                pretazenia_intomainframe();
                break;
                case 4:  //3 mesiace
                Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti = double_setter(5);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Yw=double_setter(0.66);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Yi=double_setter(0.5);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Ww=double_setter(0.25);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Wi=double_setter(0.35);
                pretazenia_intomainframe();
                break;
                case 5:  //1 rok
                Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti = double_setter(10);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Yw=double_setter(0.75);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Yi=double_setter(0.65);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Ww=double_setter(0.25);
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Wi=double_setter(0.35);
                pretazenia_intomainframe();
                break;
                case 6:  //50 rokov
                mainframe_uroven_spolahlivosti_vlastna_hodnota_1.setValues(Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti, Variable_uroven_spolahlivosti_Yw, Variable_uroven_spolahlivosti_Yi, Variable_uroven_spolahlivosti_Ww, Variable_uroven_spolahlivosti_Wi);
                mainframe_uroven_spolahlivosti_vlastna_hodnota_1 mainframe_uroven_spolahlivosti_vlastna_hodnota_jDialog_window = new  mainframe_uroven_spolahlivosti_vlastna_hodnota_1(this, rootPaneCheckingEnabled);
                mainframe_uroven_spolahlivosti_vlastna_hodnota_jDialog_window.setVisible(true);

                Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti = double_setter(Double.valueOf( vlastnehodnoty_uroven_splahlivosti[0].toString()));
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Yw=double_setter(Double.valueOf( vlastnehodnoty_uroven_splahlivosti[1].toString()));
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Yi=double_setter(Double.valueOf( vlastnehodnoty_uroven_splahlivosti[2].toString()));
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Ww=double_setter(Double.valueOf( vlastnehodnoty_uroven_splahlivosti[3].toString()));
                pretazenia_intomainframe();
                Variable_uroven_spolahlivosti_Wi=double_setter(Double.valueOf( vlastnehodnoty_uroven_splahlivosti[4].toString()));
pretazenia_intomainframe();
                break;
            }

        }
    }//GEN-LAST:event_jComboBox_uroven_splahlivostiActionPerformed

    private void jRadioButton_with_label_pociatocne1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_label_pociatocne1ActionPerformed

        Variable_T0_zivotnost=doubleChecker_short_answer(TextField_tabulky_konecna)*24*365;
        Variable_Tp_prechodna_doba=0.0;

        TextField_teploha_stav1.setText("-30");TextField_teploha_stav1.setEnabled(false);
        TextField_teploha_stav2.setText("-20");TextField_teploha_stav2.setEnabled(true);
        TextField_teploha_stav3.setText("-10");TextField_teploha_stav3.setEnabled(true);
        TextField_teploha_stav4.setText("-5");TextField_teploha_stav4.setEnabled(false);
        TextField_teploha_stav5.setText("-5+N");TextField_teploha_stav5.setEnabled(false);
        TextField_teploha_stav6.setText("-5+V");TextField_teploha_stav6.setEnabled(false);
        TextField_teploha_stav7.setText("-5+Nv");TextField_teploha_stav7.setEnabled(false);
        TextField_teploha_stav8.setText("-5+Vn");TextField_teploha_stav8.setEnabled(false);
        TextField_teploha_stav9.setText("0");TextField_teploha_stav9.setEnabled(false);
        TextField_teploha_stav10.setText("10");TextField_teploha_stav10.setEnabled(true);
        TextField_teploha_stav11.setText("20");TextField_teploha_stav11.setEnabled(true);
        TextField_teploha_stav12.setText("40");TextField_teploha_stav12.setEnabled(true);
        TextField_teploha_stav13.setText("60");TextField_teploha_stav13.setEnabled(true);
        TextField_teploha_stav14.setText("80");TextField_teploha_stav14.setEnabled(false);

        array_teploty_stav_rovnica_konecne_loader_setter();
        Jcombo_stav_KPB_setter();

        one_pretazenia_setter();
        array_pretaezenia_stav_rovnica_loader_setter();

        
        pretazenia_intomainframe();
        teplotyser=true;
        PDF_VAR_typ_tabulky =1;

    }//GEN-LAST:event_jRadioButton_with_label_pociatocne1ActionPerformed

    private void TextField_tabulky_konecnaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_tabulky_konecnaKeyReleased
        Variable_T0_zivotnost = doubleChecker_short_answer(TextField_tabulky_konecna)*24*365;
    }//GEN-LAST:event_TextField_tabulky_konecnaKeyReleased

    private void TextField_tabulky_konecnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_tabulky_konecnaActionPerformed

    }//GEN-LAST:event_TextField_tabulky_konecnaActionPerformed

    private void TextField_tabulky_prechodnaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_tabulky_prechodnaKeyReleased
        Variable_Tp_prechodna_doba=doubleChecker_short_answer(TextField_tabulky_prechodna)*24*365;
        
        if(jRadioButton_with_label_konecne.isSelected() == true){
             Variable_Tp_prechodna_doba=doubleChecker_short_answer(TextField_tabulky_konecna)*24*365;

        TextField_teploha_stav1.setText("-30");TextField_teploha_stav1.setEnabled(false);
        TextField_teploha_stav2.setText("-20");TextField_teploha_stav2.setEnabled(true);
        TextField_teploha_stav3.setText("-10");TextField_teploha_stav3.setEnabled(true);
        TextField_teploha_stav4.setText("-5");TextField_teploha_stav4.setEnabled(false);
        TextField_teploha_stav5.setText("-5+N");TextField_teploha_stav5.setEnabled(false);
        TextField_teploha_stav6.setText("-5+V");TextField_teploha_stav6.setEnabled(false);
        TextField_teploha_stav7.setText("-5+Nv");TextField_teploha_stav7.setEnabled(false);
        TextField_teploha_stav8.setText("-5+Vn");TextField_teploha_stav8.setEnabled(false);
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

        
        pretazenia_intomainframe();
        teplotyser=true;
        PDF_VAR_typ_tabulky =3;
            
        }
    }//GEN-LAST:event_TextField_tabulky_prechodnaKeyReleased

    private void TextField_tabulky_prechodnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_tabulky_prechodnaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_tabulky_prechodnaActionPerformed

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
        TextField_teploha_stav8.setText("-5+Vn");TextField_teploha_stav8.setEnabled(false);
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

        
        pretazenia_intomainframe();
        teplotyser=true;
        PDF_VAR_typ_tabulky =3;
    }//GEN-LAST:event_jRadioButton_with_label_konecneActionPerformed

    private void jRadioButton_with_label_prechodneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_label_prechodneActionPerformed
        Variable_T0_zivotnost=doubleChecker_short_answer(TextField_tabulky_konecna)*24*365;
        Variable_Tp_prechodna_doba=doubleChecker_short_answer(TextField_tabulky_prechodna)*24*365;

        if (jRadioButton_KPB_cas_vypoctu_prechodne.isSelected() == true){
            Variable_KPB_cas_vypoctu = Variable_Tp_prechodna_doba;
        }

        TextField_teploha_stav1.setText("-30");TextField_teploha_stav1.setEnabled(false);
        TextField_teploha_stav2.setText("-20");TextField_teploha_stav2.setEnabled(true);
        TextField_teploha_stav3.setText("-10");TextField_teploha_stav3.setEnabled(true);
        TextField_teploha_stav4.setText("-5");TextField_teploha_stav4.setEnabled(false);
        TextField_teploha_stav5.setText("-5+N");TextField_teploha_stav5.setEnabled(false);
        TextField_teploha_stav6.setText("-5+V");TextField_teploha_stav6.setEnabled(false);
        TextField_teploha_stav7.setText("-5+Nv");TextField_teploha_stav7.setEnabled(false);
        TextField_teploha_stav8.setText("-5+Vn");TextField_teploha_stav8.setEnabled(false);
        TextField_teploha_stav9.setText("0");TextField_teploha_stav9.setEnabled(false);
        TextField_teploha_stav10.setText("10");TextField_teploha_stav10.setEnabled(true);
        TextField_teploha_stav11.setText("20");TextField_teploha_stav11.setEnabled(true);
        TextField_teploha_stav12.setText("40");TextField_teploha_stav12.setEnabled(true);
        TextField_teploha_stav13.setText("60");TextField_teploha_stav13.setEnabled(true);
        TextField_teploha_stav14.setText("80");TextField_teploha_stav14.setEnabled(false);

        array_teploty_stav_rovnica_konecne_loader_setter();

        Jcombo_stav_KPB_setter();
        
        pretazenia_intomainframe();
        teplotyser=true;
        PDF_VAR_typ_tabulky =2;
    }//GEN-LAST:event_jRadioButton_with_label_prechodneActionPerformed

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
        one_pretazenia_setter();

        array_pretaezenia_stav_rovnica_loader_setter();

        
        pretazenia_intomainframe();
        teplotyser=true;
        PDF_VAR_typ_tabulky =1;
    }//GEN-LAST:event_jRadioButton_with_label_pociatocneActionPerformed

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

    private void TextField_teploha_stav14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav14KeyReleased
        if (TextField_teploha_stav14.isEnabled() == true) {
            Variable_teploty_stav_rovnica[13] = doubleChecker_short_answer(TextField_teploha_stav14);
            Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav14KeyReleased

    private void TextField_teploha_stav14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav14ActionPerformed

    private void TextField_teploha_stav13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav13KeyReleased
        if (TextField_teploha_stav13.isEnabled() == true) {
            Variable_teploty_stav_rovnica[12] = doubleChecker_short_answer(TextField_teploha_stav13);
            Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav13KeyReleased

    private void TextField_teploha_stav13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav13ActionPerformed

    private void TextField_teploha_stav12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav12KeyReleased
        if (TextField_teploha_stav12.isEnabled() == true) {
            Variable_teploty_stav_rovnica[11] = doubleChecker_short_answer(TextField_teploha_stav12);
            Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav12KeyReleased

    private void TextField_teploha_stav12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav12ActionPerformed

    private void TextField_teploha_stav11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav11KeyReleased
        if (TextField_teploha_stav11.isEnabled() == true) {
            Variable_teploty_stav_rovnica[10] = doubleChecker_short_answer(TextField_teploha_stav11);
            Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav11KeyReleased

    private void TextField_teploha_stav11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav11ActionPerformed

    private void TextField_teploha_stav10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav10KeyReleased
        if (TextField_teploha_stav10.isEnabled() == true) {

            Variable_teploty_stav_rovnica[9] = doubleChecker_short_answer(TextField_teploha_stav10);
            Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav10KeyReleased

    private void TextField_teploha_stav10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav10ActionPerformed

    private void TextField_teploha_stav9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav9KeyReleased
        if (TextField_teploha_stav9.isEnabled() == true) {
            Variable_teploty_stav_rovnica[8] = doubleChecker_short_answer(TextField_teploha_stav9);
            Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav9KeyReleased

    private void TextField_teploha_stav9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav9ActionPerformed

    private void TextField_teploha_stav8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav8KeyReleased
        if (TextField_teploha_stav8.isEnabled() == true) {
            Variable_teploty_stav_rovnica[7] = doubleChecker_short_answer(TextField_teploha_stav8);
            Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav8KeyReleased

    private void TextField_teploha_stav8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav8ActionPerformed

    private void TextField_teploha_stav7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav7KeyReleased
        if (TextField_teploha_stav7.isEnabled() == true) {
            Variable_teploty_stav_rovnica[6] = doubleChecker_short_answer(TextField_teploha_stav7);
            Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav7KeyReleased

    private void TextField_teploha_stav7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav7ActionPerformed

    private void TextField_teploha_stav6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav6KeyReleased
        if (TextField_teploha_stav6.isEnabled() == true) {
            Variable_teploty_stav_rovnica[5] = doubleChecker_short_answer(TextField_teploha_stav6);
            Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav6KeyReleased

    private void TextField_teploha_stav6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav6ActionPerformed

    private void TextField_teploha_stav5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav5KeyReleased
        if (TextField_teploha_stav5.isEnabled() == true) {

            Variable_teploty_stav_rovnica[4] = doubleChecker_short_answer(TextField_teploha_stav5);
            Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav5KeyReleased

    private void TextField_teploha_stav5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav5ActionPerformed

    private void TextField_teploha_stav4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav4KeyReleased
        if (TextField_teploha_stav4.isEnabled() == true) {
            Variable_teploty_stav_rovnica[3] = doubleChecker_short_answer(TextField_teploha_stav4);
            Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav4KeyReleased

    private void TextField_teploha_stav4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav4ActionPerformed

    private void TextField_teploha_stav3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav3KeyReleased
        if (TextField_teploha_stav3.isEnabled() == true) {
            Variable_teploty_stav_rovnica[2] = doubleChecker_short_answer(TextField_teploha_stav3);
            Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav3KeyReleased

    private void TextField_teploha_stav3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav3ActionPerformed

    private void TextField_teploha_stav2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav2KeyReleased
        if (TextField_teploha_stav2.isEnabled() == true) {
            Variable_teploty_stav_rovnica[1] = doubleChecker_short_answer(TextField_teploha_stav2);
            Jcombo_stav_KPB_setter();
        }
    }//GEN-LAST:event_TextField_teploha_stav2KeyReleased

    private void TextField_teploha_stav2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav2ActionPerformed

    private void TextField_teploha_stav1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_teploha_stav1KeyReleased
        if(TextField_teploha_stav1.isEnabled()== true){
            Variable_teploty_stav_rovnica[0] = doubleChecker_short_answer(TextField_teploha_stav1);}
        Jcombo_stav_KPB_setter();
    }//GEN-LAST:event_TextField_teploha_stav1KeyReleased

    private void TextField_teploha_stav1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_teploha_stav1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_teploha_stav1ActionPerformed

    private void TextField_pretazenia_stav14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav14KeyReleased
        Variable_pretazenia_stav_rovnica[13] = doubleChecker_short_answer(TextField_pretazenia_stav14);
    }//GEN-LAST:event_TextField_pretazenia_stav14KeyReleased

    private void TextField_pretazenia_stav14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav14ActionPerformed

    private void TextField_pretazenia_stav13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav13KeyReleased
        Variable_pretazenia_stav_rovnica[12] = doubleChecker_short_answer(TextField_pretazenia_stav13);
    }//GEN-LAST:event_TextField_pretazenia_stav13KeyReleased

    private void TextField_pretazenia_stav13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav13ActionPerformed

    private void TextField_pretazenia_stav12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav12KeyReleased
        Variable_pretazenia_stav_rovnica[11] = doubleChecker_short_answer(TextField_pretazenia_stav12);
    }//GEN-LAST:event_TextField_pretazenia_stav12KeyReleased

    private void TextField_pretazenia_stav12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav12ActionPerformed

    private void TextField_pretazenia_stav11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav11KeyReleased
        Variable_pretazenia_stav_rovnica[10] = doubleChecker_short_answer(TextField_pretazenia_stav11);
    }//GEN-LAST:event_TextField_pretazenia_stav11KeyReleased

    private void TextField_pretazenia_stav11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav11ActionPerformed

    private void TextField_pretazenia_stav10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav10KeyReleased
        Variable_pretazenia_stav_rovnica[9] = doubleChecker_short_answer(TextField_pretazenia_stav10);
    }//GEN-LAST:event_TextField_pretazenia_stav10KeyReleased

    private void TextField_pretazenia_stav10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav10ActionPerformed

    private void TextField_pretazenia_stav9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav9KeyReleased
        Variable_pretazenia_stav_rovnica[8] = doubleChecker_short_answer(TextField_pretazenia_stav9);
    }//GEN-LAST:event_TextField_pretazenia_stav9KeyReleased

    private void TextField_pretazenia_stav9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav9ActionPerformed

    private void TextField_pretazenia_stav8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav8KeyReleased
        Variable_pretazenia_stav_rovnica[7] = doubleChecker_short_answer(TextField_pretazenia_stav8);
    }//GEN-LAST:event_TextField_pretazenia_stav8KeyReleased

    private void TextField_pretazenia_stav8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav8ActionPerformed

    private void TextField_pretazenia_stav7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav7KeyReleased
        Variable_pretazenia_stav_rovnica[6] = doubleChecker_short_answer(TextField_pretazenia_stav7);
    }//GEN-LAST:event_TextField_pretazenia_stav7KeyReleased

    private void TextField_pretazenia_stav7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav7ActionPerformed

    private void TextField_pretazenia_stav6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav6KeyReleased
        Variable_pretazenia_stav_rovnica[5] = doubleChecker_short_answer(TextField_pretazenia_stav6);
    }//GEN-LAST:event_TextField_pretazenia_stav6KeyReleased

    private void TextField_pretazenia_stav6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav6ActionPerformed

    private void TextField_pretazenia_stav5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav5KeyReleased
        Variable_pretazenia_stav_rovnica[4] = doubleChecker_short_answer(TextField_pretazenia_stav5);
    }//GEN-LAST:event_TextField_pretazenia_stav5KeyReleased

    private void TextField_pretazenia_stav5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav5ActionPerformed

    private void TextField_pretazenia_stav4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav4KeyReleased
        Variable_pretazenia_stav_rovnica[3] = doubleChecker_short_answer(TextField_pretazenia_stav4);
    }//GEN-LAST:event_TextField_pretazenia_stav4KeyReleased

    private void TextField_pretazenia_stav4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav4ActionPerformed

    private void TextField_pretazenia_stav3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav3KeyReleased
        Variable_pretazenia_stav_rovnica[2] = doubleChecker_short_answer(TextField_pretazenia_stav3);
    }//GEN-LAST:event_TextField_pretazenia_stav3KeyReleased

    private void TextField_pretazenia_stav3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav3ActionPerformed

    private void TextField_pretazenia_stav2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav2KeyReleased
        Variable_pretazenia_stav_rovnica[1] = doubleChecker_short_answer(TextField_pretazenia_stav2);
    }//GEN-LAST:event_TextField_pretazenia_stav2KeyReleased

    private void TextField_pretazenia_stav2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav2ActionPerformed

    private void TextField_pretazenia_stav1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav1KeyReleased
        Variable_pretazenia_stav_rovnica[0] = doubleChecker_short_answer(TextField_pretazenia_stav1);
    }//GEN-LAST:event_TextField_pretazenia_stav1KeyReleased

    private void TextField_pretazenia_stav1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_pretazenia_stav1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_pretazenia_stav1ActionPerformed

    private void TextField_STRrozpatie_sPrevisenimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_STRrozpatie_sPrevisenimKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_STRrozpatie_sPrevisenimKeyReleased

    private void TextField_STRrozpatie_sPrevisenimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_STRrozpatie_sPrevisenimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_STRrozpatie_sPrevisenimActionPerformed

    private void TextField_STRrozpatie_klasickyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_STRrozpatie_klasickyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_STRrozpatie_klasickyActionPerformed

    private void jRadioButton_with_label_rozpate_previseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_label_rozpate_previseniaActionPerformed
        Variable_mid_span=Variable_mid_span_terrain_docasna;
    }//GEN-LAST:event_jRadioButton_with_label_rozpate_previseniaActionPerformed

    private void jRadioButton_with_label_rozpatie_klasickyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_with_label_rozpatie_klasickyActionPerformed
        Variable_mid_span=Variable_mid_span_docasna;
    }//GEN-LAST:event_jRadioButton_with_label_rozpatie_klasickyActionPerformed

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
                Variable_KPB_typ_terenu = 1.0;
                jTextPane_KPB_typ_terenu.setText(language.language_label(languageOption, 169));
                break;
                case 1:  Variable_KPB_typ_terenu = 2.0;
                jTextPane_KPB_typ_terenu.setText(language.language_label(languageOption, 170));
                break;
                case 2:  Variable_KPB_typ_terenu = 3.0;
                jTextPane_KPB_typ_terenu.setText(language.language_label(languageOption, 171));
                break;
                case 3:  Variable_KPB_typ_terenu= 4.0;
                jTextPane_KPB_typ_terenu.setText(language.language_label(languageOption, 172));
                break;

            }

        }else{ Variable_KPB_typ_terenu = 2.0;}
    }//GEN-LAST:event_jComboBox_KPB_typ_terenuActionPerformed

    private void jTextField_nazov_SOPS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nazov_SOPS1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nazov_SOPS1ActionPerformed

    private void jTextField_nazov_nazov_stavby1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nazov_nazov_stavby1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nazov_nazov_stavby1ActionPerformed

    private void jTextField_datumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_datumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_datumActionPerformed

    private void jTextField_vypracovalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_vypracovalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_vypracovalActionPerformed

    private void jTextField_nazov_cislo_stranyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nazov_cislo_stranyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nazov_cislo_stranyActionPerformed

    private void jTextField_nazov_arch_cisloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nazov_arch_cisloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nazov_arch_cisloActionPerformed

    private void jTextField_nazov_SOPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nazov_SOPSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nazov_SOPSActionPerformed

    private void jTextField_nazov_nazov_stavbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nazov_nazov_stavbyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nazov_nazov_stavbyActionPerformed

    private void jTextField_nadpis_pre_prechodnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nadpis_pre_prechodnaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nadpis_pre_prechodnaActionPerformed

    private void jTextField_nazov_normiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nazov_normiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nazov_normiActionPerformed

    private void TextField_Bi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_Bi2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_Bi2ActionPerformed

    private void TextField_Kh_noHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_Kh_noHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_Kh_noHActionPerformed

    private void TextField_Kh_noHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_Kh_noHKeyReleased
        Variable_Kh =double_setter(doubleChecker_short_answer(TextField_Kh_noH));
        pretazenia_intomainframe();
    }//GEN-LAST:event_TextField_Kh_noHKeyReleased

  public static void lanochangeinDatabaze() {
  nacitatDatabazuLan();      
        
    }

    /**
     * 
     * @param args
     * @param load True of file is being loaded and not new
     */
   
    public static void main(String args[],boolean load) {
      
        loaded_file=load;
        
        
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
            java.util.logging.Logger.getLogger(mainframe_17.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainframe_17.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainframe_17.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainframe_17.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainframe_17().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Icon_arr_row_table_kotevny_usek;
    private javax.swing.JButton Button_Icon_calculate;
    private javax.swing.JButton Button_Icon_delete_row_table_kotevny_usek;
    private javax.swing.JButton Button_Icon_deselect_all_kotevny_usek;
    private javax.swing.JButton Button_Icon_export_PDF2_save_as;
    private javax.swing.JButton Button_Icon_export_PDF_external;
    private javax.swing.JButton Button_Icon_export_PDF_internal;
    private javax.swing.JButton Button_Icon_save;
    private javax.swing.JButton Button_Icon_save_as;
    private javax.swing.JButton Button_Icon_select_all_kotevny_usek;
    private javax.swing.JButton Button_namrazova_oblast;
    private javax.swing.JButton Button_pdf_pagedown;
    private javax.swing.JButton Button_pdf_pageup;
    private static javax.swing.JLabel Label_KPB;
    private static javax.swing.JLabel Label_KPB_typ_terenu;
    private javax.swing.JLabel Label_RTS;
    private javax.swing.JLabel Label_RTS_velicina;
    private javax.swing.JLabel Label_RTS_velicina1;
    private javax.swing.JLabel Label_RTS_velicina10;
    private javax.swing.JLabel Label_RTS_velicina11;
    private javax.swing.JLabel Label_RTS_velicina12;
    private javax.swing.JLabel Label_RTS_velicina13;
    private javax.swing.JLabel Label_RTS_velicina14;
    private javax.swing.JLabel Label_RTS_velicina2;
    private javax.swing.JLabel Label_RTS_velicina3;
    private javax.swing.JLabel Label_RTS_velicina5;
    private javax.swing.JLabel Label_RTS_velicina6;
    private javax.swing.JLabel Label_RTS_velicina7;
    private javax.swing.JLabel Label_RTS_velicina8;
    private javax.swing.JLabel Label_RTS_velicina9;
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
    private static javax.swing.JLabel Label_kotevne_useky1;
    private javax.swing.JLabel Label_max_zataz_lana;
    private static javax.swing.JLabel Label_pretazenia;
    private javax.swing.JLabel Label_status;
    private static javax.swing.JLabel Label_stredna_vyska_vodicov_nad_terenom;
    private static javax.swing.JLabel Label_stredne_rozpatie;
    private static javax.swing.JLabel Label_tabulky;
    private static javax.swing.JLabel Label_tabulky1;
    private static javax.swing.JLabel Label_tabulky2;
    private static javax.swing.JLabel Label_teploty;
    private javax.swing.JLabel Label_typ_namrazy;
    private javax.swing.JLabel Label_uroven_spolahlivosti;
    private javax.swing.JLabel Label_vetrova_oblast;
    private javax.swing.JLabel Label_vetrova_oblast_C0;
    private javax.swing.JLabel Label_vetrova_oblast_Cdir;
    private javax.swing.JLabel Label_vybrana_namrazova_oblast;
    private javax.swing.JLabel Label_zakladne_mech_napatie_minis5;
    private javax.swing.JLabel Label_základna_rychlost_vetra;
    private javax.swing.JTable Table_KPB;
    private static javax.swing.JTable Table_kotevne_useky;
    private javax.swing.JTable Table_rozpatia;
    private javax.swing.JTable Table_rozpatia_nadm_vysky;
    private javax.swing.JTable Table_tahy;
    private javax.swing.JTextField TextField_Bi2;
    private javax.swing.JTextField TextField_Bi_1;
    private javax.swing.JTextField TextField_Ccl;
    private javax.swing.JTextField TextField_Kcl;
    private javax.swing.JTextField TextField_Kh_noH;
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
    private javax.swing.JTextField Textfield_cas;
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
    private static final javax.swing.JComboBox<String> jComboBox_KPB_typ_terenu = new javax.swing.JComboBox<>();
    private static final javax.swing.JComboBox<String> jComboBox_char_terenu = new javax.swing.JComboBox<>();
    private static javax.swing.JComboBox<String> jComboBox_conductor_chooser;
    private static final javax.swing.JComboBox<String> jComboBox_druh_namrazy = new javax.swing.JComboBox<>();
    private static final javax.swing.JComboBox<String> jComboBox_stav_KPB = new javax.swing.JComboBox<>();
    private static final javax.swing.JComboBox<String> jComboBox_uroven_splahlivosti = new javax.swing.JComboBox<>();
    private final javax.swing.JComboBox<String> jComboBox_vetrova_oblast = new javax.swing.JComboBox<>();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
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
    private javax.swing.JRadioButton jRadioButton_Kh_H;
    private javax.swing.JRadioButton jRadioButton_Kh_noH;
    private javax.swing.JRadioButton jRadioButton_Klc_1;
    private javax.swing.JRadioButton jRadioButton_Klc_vlastna;
    private javax.swing.JRadioButton jRadioButton_vetrova_oblast_C0_1;
    private javax.swing.JRadioButton jRadioButton_vetrova_oblast_C0_vlastna;
    private javax.swing.JRadioButton jRadioButton_vetrova_oblast_Cdir_1;
    private javax.swing.JRadioButton jRadioButton_vetrova_oblast_Cdir_vlastna;
    private javax.swing.JRadioButton jRadioButton_with_label_konecne;
    private javax.swing.JRadioButton jRadioButton_with_label_pociatocne;
    private javax.swing.JRadioButton jRadioButton_with_label_pociatocne1;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField_datum;
    private javax.swing.JTextField jTextField_nadpis_pre_prechodna;
    private javax.swing.JTextField jTextField_nazov_SOPS;
    private javax.swing.JTextField jTextField_nazov_SOPS1;
    private javax.swing.JTextField jTextField_nazov_arch_cislo;
    private javax.swing.JTextField jTextField_nazov_cislo_strany;
    private javax.swing.JTextField jTextField_nazov_nazov_stavby;
    private javax.swing.JTextField jTextField_nazov_nazov_stavby1;
    private javax.swing.JTextField jTextField_nazov_normi;
    private javax.swing.JTextField jTextField_vypracoval;
    private javax.swing.JTextArea jTextPane_KPB_typ_terenu;
    private javax.swing.JTextArea jTextPane_char_terenu;
    private javax.swing.JLabel label_cas_vytvorenia;
    private javax.swing.JTextField percento1_sigma;
    private javax.swing.JTextField percento2_sigma;
    private javax.swing.JTextField percento3_sigma;
    private javax.swing.JTextField percento4_sigma;
    private javax.swing.JTextField podiel_z_RTS1;
    private javax.swing.JTextField podiel_z_RTS2;
    private javax.swing.JLabel tah_konecne;
    private javax.swing.JLabel tah_konecne1;
    private javax.swing.JLabel tah_konecne2;
    private javax.swing.JTextField vyp_percento1_sigma;
    private javax.swing.JTextField vyp_percento2_sigma;
    private javax.swing.JTextField vyp_percento3_sigma;
    private javax.swing.JTextField vyp_percento4_sigma;
    // End of variables declaration//GEN-END:variables
DefaultTableModel modelTable;
DefaultTableModel modeltable_rozpatia;
DefaultTableModel modeltable_rozpatia_nadm_vysky;
DefaultTableModel modeltable_KPB;
DefaultTableModel modeltable_tahy;
private static String new_kotevny_usek_name;
private static boolean existnewkotevnyusek = false;
private static boolean mainframeLodaed = false;
private static boolean teplotyser = false;
private static boolean urovenspolahlivostiblocker = true;
private static boolean first_Start= true;
private static boolean selection_kotevny_usek= true;
private static int povodna_hodnota_selekcie=0;
private static boolean  Calculation_done=false;
private static boolean loaded_file=false;
private static int pocet_pdf_stran = 0;
private static int current_pdf_page = 1;
private static String project_name;
private static String project_filename="";
private static String project_filepath="";
private static boolean project_save_as;
private static boolean pdf_internal=false;
private static boolean pdf_external=false;
private static boolean pdf_as=false;
public static boolean really_close_mainframe=false;
public static boolean really_close_mainframe_save=false;

//namrazove oblasti premene
public static Object[] hodnoty_namrazove_oblasti = new Object[3];
public static String namrazove_oblasti_názov_oblasti = "----";
public static boolean is_namrazove_oblasti_setted = false;
// listenery blokady bool
private static boolean tablemodellistener_rozpatia = true;
private static boolean tablemodellistener_nad_vysky = true;
private static boolean tablemodellistener_total = true;
// general variables
private static String filename = "new_file";
private static String filenamePath;
private static String filenamePath_plus_filename;
private static String memory_path_plus_filename_here;
private static boolean memory_path_plus_filename_existence = false;
public static Object[] vlastnehodnoty_uroven_splahlivosti = new Object[4];
public static String warning_text = "";
public static Boolean isOpen = false;
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
private static ArrayList<kotevnyUsek>  Variable_globeal_kotevny_usek = new ArrayList<>();
private static ArrayList<kotevnyUsek>  Variable_globeal_kotevny_usek_zmena = new ArrayList<>();
private static double  Variable_Hc_mean;
private static double  Variable_Hc_mean_medzikrok;
private static double  Variable_n_pocet_rozpati;
private static double[] Variable_Ai_array;
private static double[] Variable_DeltaHi_array;
private static double[] Variable_Hi_array;
private static double[] Variable_Hi_array_nmv;
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

private static double percento_podiel_namrazy_sigma1;
private static double percento_podiel_namrazy_sigma2;
private static double percento_podiel_namrazy_sigma3;
private static double percento_podiel_namrazy_sigma4;

// conductor variables
private static final ArrayList<Object[]> Databaza = new ArrayList<>();
public static javax.swing.JLabel Lano_listener_JLabel_Maska;
// variables for PDF
private static double PDF_VAR_typ_tabulky = 3; // 3 konecne , 2prechodne , pociatocne

// nastav rohovu ikonu
private void seticon() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/mt_graphic/" + "icon.png")));
    }

// zober meno noveho kotevneho useku   
    public static void  new_kotevny_usek_jdialog (String X){
        new_kotevny_usek_name = X;
        
    }
    
    public  void warning_sign (String X){
        warning_text=X ;
        mainframe_warning_jDialog_1 mainframe_warning = new mainframe_warning_jDialog_1(this, rootPaneCheckingEnabled);
        mainframe_warning.setVisible(true);      
    }
    
    public  void warning_sign2but (String X){
        warning_text=X ;
    mainframe_warning2but_jDialog_1 mainframe_warning2but = new mainframe_warning2but_jDialog_1(this, rootPaneCheckingEnabled);
        mainframe_warning2but.setVisible(true);  
    }
    public void Swriter (String S){
        System.out.println(S);
    }
    
    public void Iwriter (int S){
        System.out.println(S);
    }
     
    public void Dwriter (double S){
        System.out.println(S);
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
            fw.println(startPanel.load_memory_path_plus_filename);
            fw.println("....");
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

        } catch (FileNotFoundException  | NullPointerException exx) {

        }
    
    }
    
    private  double vypocet_IR50_namrazove_oblasti(){
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
    String hodnota1 =Y.getText();
       String hodnota2=hodnota1.replace(",", ".");
       if(hodnota1.equals(hodnota2)){}else{ Y.setText(hodnota2);}
       
  
       Double value ;
        try{
        value = Double.parseDouble(hodnota2);
        Y.setForeground(Color.black);
        return value;
        }catch(NumberFormatException | NullPointerException e){
         Y.setForeground(Color.red);
         Y.setText(language.language_label(languageOption, 85));
        return value = 123456789.987654321;            
        }
     }
     
      private int intChecker_short_answer (javax.swing.JTextField Y){
       int value ;
        try{
        value = Integer.parseInt(Y.getText());
        Y.setForeground(Color.black);
        return value;
        }catch(NumberFormatException | NullPointerException e){
         Y.setForeground(Color.red);
         Y.setText(language.language_label(languageOption, 85));
        return value = -1;            
        }
     }
     
    
     
      
     private double doubleChecker (javax.swing.JTextField Y){
       String hodnota1 =Y.getText();
       String hodnota2=hodnota1.replace(",", ".");
       if(hodnota1.equals(hodnota2)){}else{ Y.setText(hodnota2);}
       
  
       Double value ;
        try{
        value = Double.parseDouble(hodnota2);
        Y.setForeground(Color.black);
        return value;
        }catch(NumberFormatException | NullPointerException e){
         Y.setForeground(Color.red);
         Y.setText(language.language_label(languageOption, 47));
        return value = 123456789.987654321;            
        }
     }
     
     private double doubleChecker_tableinput (String Y){
       
       String hodnota1 =Y;
       hodnota1=hodnota1.replace(",", ".");
      
       
          Double value ;
        try{
        value = Double.parseDouble(hodnota1);      
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
    
    private void one_pretazenia_setter(){
    
        TextField_pretazenia_stav1.setText("1.0");
        TextField_pretazenia_stav2.setText("1.0");
        TextField_pretazenia_stav3.setText("1.0");
        TextField_pretazenia_stav4.setText("1.0");
        TextField_pretazenia_stav5.setText("1.0");
        TextField_pretazenia_stav6.setText("1.0");
        TextField_pretazenia_stav7.setText("1.0");
        TextField_pretazenia_stav8.setText("1.0");
        TextField_pretazenia_stav9.setText("1.0");
        TextField_pretazenia_stav10.setText("1.0");
        TextField_pretazenia_stav11.setText("1.0");
        TextField_pretazenia_stav12.setText("1.0");
        TextField_pretazenia_stav13.setText("1.0");
        TextField_pretazenia_stav14.setText("1.0");
        
        
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
    /**
     * 
     * @param X true/false = on/of tablemodellistener_rozpatia
     * @param Y true/false = on/of tablemodellistener_nad_vysky
     * @param Z true/false = on/of tablemodellistener_TOTAL
     */
    private void listener_switch (boolean X,boolean Y,boolean Z){
      tablemodellistener_rozpatia =X;  // vypnutie listenerov pri praci s taulkami
      tablemodellistener_nad_vysky=Y;
      tablemodellistener_total=Z;  
        
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
    /**
     * 
     * @param X        input of kotevny usek
     * @param number   number of selected row if no change in table kotevny usek or number of row before change if change in table_kotevny usek_has beenn made
     */
    private void mainframe_to_kotevny_usek(kotevnyUsek X,int number)    {
        //vloz prave označeny kotevny usek
        
        int selekcia_for_name=number;
        try{
//            
        X.set_name(String.valueOf(Table_kotevne_useky.getValueAt(selekcia_for_name, 1)));
        
        X.set_vysledky_KPB(Variable_globeal_kotevny_usek.get(selekcia_for_name).get_vysledky_KPB());
        X.set_vysledky_c_MT(Variable_globeal_kotevny_usek.get(selekcia_for_name).get_vysledky_c_MT());
        X.set_vysledky_pretazenia6(Variable_globeal_kotevny_usek.get(selekcia_for_name).get_vysledky_pretazenia5());
        X.set_vysledky_pretazenia_MT(Variable_globeal_kotevny_usek.get(selekcia_for_name).get_vysledky_pretazenia_MT());
        X.set_vysledky_sigmaH_MT(Variable_globeal_kotevny_usek.get(selekcia_for_name).get_vysledky_sigmaH_MT());
        X.set_vysledky_sily_MT(Variable_globeal_kotevny_usek.get(selekcia_for_name).get_vysledky_sily_MT());
        X.set_vysledky_tlaky6(Variable_globeal_kotevny_usek.get(selekcia_for_name).get_vysledky_tlaky6());
        X.set_vysledky_vid_priehyb_M(Variable_globeal_kotevny_usek.get(selekcia_for_name).get_vysledky_vid_priehyb_MT());
        X.set_per_podiel_sigma(Variable_globeal_kotevny_usek.get(selekcia_for_name).get_vysledky_per_podiel_sigma());
        X.set_tahy(Variable_globeal_kotevny_usek.get(selekcia_for_name).get_tahy());
        
        }catch(Exception s){
        
        }
        X.set_conductor_number(jComboBox_conductor_chooser.getSelectedIndex());
         X.set_conductor_name(String.valueOf(Databaza.get(jComboBox_conductor_chooser.getSelectedIndex())[0]));
         System.out.println(X.get_conductor_name());
        X.set_vetrova_oblast_porcislo(jComboBox_vetrova_oblast.getSelectedIndex());
        X.set_char_terenu_porcislo(jComboBox_char_terenu.getSelectedIndex());
        X.set_uroven_spolahlivosti_porcislo(jComboBox_uroven_splahlivosti.getSelectedIndex());
        X.set_uroven_spolahlivosti_stav_porcislo(jComboBox_stav_KPB.getSelectedIndex());
        X.set_namrazova_oblast_string(Label_vybrana_namrazova_oblast.getText());
        X.set_typ_namrazy_porcislo(jComboBox_druh_namrazy.getSelectedIndex());
        X.set_RTS_over(Variable_RTS);
        X.set_zakladne_mech_napatie_lana_pre_minus5_over(Variable_zakladne_mech_napatie_lana_pre_minus5);
        X.set_maximalne_zataz_lana_podiel_z_RTS_over(Double.parseDouble(TextField_max_mech_podiel_z_RTS.getText()));
        X.set_c_dir(Variable_Cdir);
        X.set_g_c(1.);
        X.set_ro_I(Variable_hustota_namrazy);
        X.set_K_lc(Variable_Klc);
        X.set_K_h(Variable_Kh);
        X.set_I_R50(Variable_Ir50);
        X.set_k_r(Variable_char_terenu_Kr);
        X.set_z_0(Variable_char_terenu_Zo);
        X.set_V_mean(Variable_V_mean_0);
        X.set_c_0(Variable_Co);
        X.set_C_c(1.);
        X.set_gama_w(Variable_uroven_spolahlivosti_Yw);
        X.set_gama_I(Variable_uroven_spolahlivosti_Yi);
        X.set_Psi_w(Variable_uroven_spolahlivosti_Ww);
        X.set_Psi_I(Variable_uroven_spolahlivosti_Wi);
        X.set_B_I(Variable_Bi);
        X.set_k_p(3);
        X.set_RR(0);
        X.set_ro(1.25);
        X.set_C_cl(Variable_Ccl);
        X.set_h_c_mean(Variable_Hc_mean);
        X.set_h_c_mean_window_vypocitana(Double.parseDouble(TextField_hcmean_vpocitana.getText()));
        X.set_h_c_mean_window_vlastna(Double.parseDouble(TextField_hcmean_vlastna.getText()));
        X.set_str_vys_vodicov_radio(jRadioButton_with_label_vypoctana.isSelected());
        X.set_CDIR_radio(jRadioButton_vetrova_oblast_Cdir_1.isSelected());
        X.set_CO_radio(jRadioButton_vetrova_oblast_C0_1.isSelected());
        X.set_Kcl_radio(jRadioButton_Klc_1.isSelected());
        X.set_Kh_radio(jRadioButton_Kh_noH.isSelected());
        X.set_Bi_radio(jRadioButton_Bi_1.isSelected());
        X.set_Ai_array(Variable_Ai_array);
        X.set_DeltaHi_array(Variable_DeltaHi_array);
        X.set_Hi_array(Variable_Hi_array); 
        X.set_Hi_array_nvm(Variable_Hi_array_nmv);
        X.set_str_rozpatie(Variable_mid_span);
        
        double[] hodnoty = new double[4];
        hodnoty[0] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[0]));
        hodnoty[1] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[1]));
        hodnoty[2] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[2]));
        hodnoty[3] =Double.valueOf(String.valueOf(hodnoty_namrazove_oblasti[3]));
        X.set_hodnoty_namrazove_oblasti(hodnoty);
        
        if(is_namrazove_oblasti_setted== true){

            Label_vybrana_namrazova_oblast.setText(namrazove_oblasti_názov_oblasti);
            Variable_Ir50=double_setter(vypocet_IR50_namrazove_oblasti());
            pretazenia_intomainframe();
            Variable_Ir50=double_setter(vypocet_IR50_namrazove_oblasti());
            pretazenia_intomainframe();
        }
    
    }
    
        
   
    private void kotevn_usek_to_mainframe(kotevnyUsek X){
        //vybrany kotevny usek jebne do mainframe
        try{
     
        Table_kotevne_useky.setValueAt( X.get_name(), Table_kotevne_useky.getSelectedRow() ,1 );
        
        }catch(NullPointerException s){       
        }
       DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
               otherSymbols.setDecimalSeparator('.');
               DecimalFormat df = new DecimalFormat("###.###",otherSymbols);
       jComboBox_conductor_chooser.setSelectedIndex(najdiconductor_number(X.get_conductor_name()));
       jComboBox_vetrova_oblast.setSelectedIndex(X.get_vetrova_oblast_porcislo());
       jComboBox_char_terenu.setSelectedIndex(X.get_char_terenu_porcislo()); 
       
       urovenspolahlivostiblocker =false;
       jComboBox_uroven_splahlivosti.setSelectedIndex(X.get_uroven_spolahlivosti_porcislo());
       urovenspolahlivostiblocker =true;
        
       jComboBox_stav_KPB.setSelectedIndex(X.get_uroven_spolahlivosti_stav_porcislo());
       
       Label_vybrana_namrazova_oblast.setText(X.get_namrazova_oblast_string());
       jComboBox_druh_namrazy.setSelectedIndex(X.get_typ_namrazy_porcislo());
       
       Variable_RTS=X.get_RTS_over(); TextField_RTS.setText(df.format(Variable_RTS));
      
       Variable_zakladne_mech_napatie_lana_pre_minus5=X.get_zakladne_mech_napatie_lana_pre_minus5_over();
       TextField_zakladne_mech_lana_minus5.setText(df.format(Variable_zakladne_mech_napatie_lana_pre_minus5));
       
       Variable_maximalne_zataz_lana_podiel_z_RTS=X.get_maximalne_zataz_lana_podiel_z_RTS_over();
       TextField_max_mech_podiel_z_RTS.setText(df.format(Variable_maximalne_zataz_lana_podiel_z_RTS));
       Variable_maximalne_zataz_lana_podiel_z_RTS=Variable_RTS*((doubleChecker_short_answer(TextField_zakladne_mech_lana_minus5))/(100));
       
       if (X.get_CDIR_radio() == true )
       {jRadioButton_vetrova_oblast_Cdir_1.setSelected(true);
       Variable_Cdir=double_setter(X.get_c_dir());
       pretazenia_intomainframe();}
       else{
       jRadioButton_vetrova_oblast_Cdir_vlastna.setSelected(true);
       TextField_vetrova_oblast_Cdir.setText(String.valueOf(X.get_c_dir()));
       Variable_Cdir=double_setter(X.get_c_dir());
       pretazenia_intomainframe();
       }
       
      Variable_hustota_namrazy=double_setter(X.get_ro_I());
      pretazenia_intomainframe();
       TextField_hustota_namrazy.setText(String.valueOf(Variable_hustota_namrazy));
       
       if (X.get_Kcl_radio() == true )
       {jRadioButton_Klc_1.setSelected(true);
        Variable_Klc=double_setter(X.get_K_lc());
       pretazenia_intomainframe();}
       else{
       jRadioButton_Klc_vlastna.setSelected(true);
       TextField_Kcl.setText(String.valueOf(X.get_K_lc()));
       Variable_Klc=double_setter(X.get_K_lc());
       pretazenia_intomainframe();
       }
        
       if (X.get_Kh_radio()== true )
       {jRadioButton_Kh_noH.setSelected(true);
        Variable_Kh=double_setter(X.get_K_h());
       pretazenia_intomainframe();}
       else{
       jRadioButton_Kh_H.setSelected(true);
       TextField_Kh_noH.setText(String.valueOf(X.get_K_h()));
       Variable_Kh=-1;
       pretazenia_intomainframe();
       }
        
       Variable_Ir50=double_setter(X.get_I_R50());
       pretazenia_intomainframe();
       Variable_char_terenu_Kr=double_setter(X.get_k_r());
       pretazenia_intomainframe();
       TextField_Kr.setText(String.valueOf(Variable_char_terenu_Kr));
      
       Variable_char_terenu_Zo=double_setter(X.get_z_0());
       pretazenia_intomainframe();
       TextField_dlzka_drsnjosti_zo.setText(String.valueOf(Variable_char_terenu_Zo));
      
       Variable_V_mean_0=double_setter(X.get_V_mean());
       pretazenia_intomainframe();
       TextField_Vmean_0.setText(String.valueOf(Variable_V_mean_0));
      
       if (X.get_CO_radio()== true )
       {jRadioButton_vetrova_oblast_C0_1.setSelected(true);
        Variable_Co=double_setter(X.get_c_0());
       pretazenia_intomainframe();}
       else{
       jRadioButton_vetrova_oblast_C0_vlastna.setSelected(true);
       TextField_vetrova_oblast_C0.setText(String.valueOf(X.get_c_0()));
       Variable_Co=double_setter(X.get_c_0());
       pretazenia_intomainframe();
       }
       
       if (X.get_Bi_radio()== true )
       {jRadioButton_Bi_1.setSelected(true);
        Variable_Bi=double_setter(X.get_B_I());
       pretazenia_intomainframe();} 
       else{
       jRadioButton_Bi_2.setSelected(true);
       TextField_Bi2.setText(String.valueOf(X.get_B_I()));
       Variable_Bi=double_setter(X.get_B_I());
       }
       
       Variable_Ccl=double_setter(X.get_C_cl());
       pretazenia_intomainframe();
       TextField_Ccl.setText(String.valueOf(Variable_Ccl));
      
       Variable_uroven_spolahlivosti_Yw=double_setter(X.get_gama_w());
       pretazenia_intomainframe();
       Variable_uroven_spolahlivosti_Yi=double_setter(X.get_gama_I());
       pretazenia_intomainframe();
       Variable_uroven_spolahlivosti_Ww=double_setter(X.get_Psi_w());
       pretazenia_intomainframe();
       Variable_uroven_spolahlivosti_Wi=double_setter(X.get_Psi_I());
       pretazenia_intomainframe(); 
       
       //Remove rows one by one from the end of the table
        listener_switch(false, false, false);

        int rowCount = Table_rozpatia.getRowCount();
        for (int i = rowCount - 1; i > 0; i--) {

            modeltable_rozpatia.removeRow(i);
            
           
        }
        Variable_Ai_dlzka_rozpatia.removeAll(Variable_Ai_dlzka_rozpatia);
        
        
        int rowCount2 = Table_rozpatia_nadm_vysky.getRowCount();
        for (int i = rowCount2 - 1; i >= 0; i--) {

            modeltable_rozpatia_nadm_vysky.removeRow(i);
            
        }
        Variable_hi_vyska_stoziarov.removeAll(Variable_hi_vyska_stoziarov);
        Variable_hi2_nadmorska_vyska_stoziarov.removeAll(Variable_hi2_nadmorska_vyska_stoziarov);
        
         //add new data to wows
        if (X.get_Ai_array() == null) {
        } else {

            for (int i = 0; i < X.get_Ai_array().length; i++) {
                modeltable_rozpatia.addRow(new Object[0]);
                Table_rozpatia.setValueAt(X.get_Ai_array()[i], i + 1, 0);
                Variable_Ai_dlzka_rozpatia.add(X.get_Ai_array()[i]);
                
            }
        }
        
        
        
        if (X.get_Hi_array() == null) {
        } else {

            for (int i = 0; i < X.get_Hi_array().length; i++) {
                modeltable_rozpatia_nadm_vysky.addRow(new Object[0]);
                Table_rozpatia_nadm_vysky.setValueAt(X.get_Hi_array()[i], i , 1);
                Table_rozpatia_nadm_vysky.setValueAt(X.get_Hi_array_nvm()[i], i , 0);
                Variable_hi_vyska_stoziarov.add(X.get_Hi_array()[i]);
                Variable_hi2_nadmorska_vyska_stoziarov.add(X.get_Hi_array_nvm()[i]);
            }
        }
       
       //plnenie arrayov !!!!
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
                Variable_Hi_array_nmv = new double[(int) Variable_n_pocet_rozpati+1];
                for (int i = 0; i < Variable_Ai_array.length; i++) {
                   
                Variable_Ai_array[i] = Variable_Ai_dlzka_rozpatia.get(i);
                Variable_Hi_array[i] = Variable_hi_vyska_stoziarov.get(i);
                Variable_Hi_array_nmv[i] = Variable_hi2_nadmorska_vyska_stoziarov.get(i);
                        
                double prvavyska_stoziar_plus_zem = Variable_hi2_nadmorska_vyska_stoziarov.get(i) + Variable_hi_vyska_stoziarov.get(i);
                double druhavyska_stoziar_plus_zem= Variable_hi2_nadmorska_vyska_stoziarov.get(i+1) + Variable_hi_vyska_stoziarov.get(i+1);
                Variable_DeltaHi_array[i]= druhavyska_stoziar_plus_zem - prvavyska_stoziar_plus_zem;
                }
                if(Variable_Ai_array.length==0){}else{
                Variable_Hi_array[Variable_Ai_array.length] = Variable_hi_vyska_stoziarov.get(Variable_Ai_array.length);
                Variable_Hi_array_nmv[Variable_Ai_array.length] = Variable_hi2_nadmorska_vyska_stoziarov.get(Variable_Ai_array.length);
                }
                Variable_Ai_array=double_setter_array(Variable_Ai_array);
                pretazenia_intomainframe();
                
                
       modeltable_rozpatia.addRow(new Object[0]);  
       if (X.get_Hi_array() == null) {modeltable_rozpatia_nadm_vysky.addRow(new Object[1]);}
       modeltable_rozpatia_nadm_vysky.addRow(new Object[1]);
       
       Variable_Ai_dlzka_rozpatia.add(0.0);
       Variable_hi_vyska_stoziarov.add(0.0);
       Variable_hi2_nadmorska_vyska_stoziarov.add(0.0);//pridar row vsšade do každe arraylist               
                               
       listener_switch(true, true, true);                   
       
        if (X.get_str_vys_vodicov_radio()== true ){
       jRadioButton_with_label_vypoctana.setSelected(true);
        
        Variable_Hc_mean=double_setter(X.get_h_c_mean());
        pretazenia_intomainframe();
        TextField_hcmean_vpocitana.setText(df.format(X.get_h_c_mean_window_vypocitana()));
        Variable_Hc_mean_medzikrok=X.get_h_c_mean_window_vypocitana();
        TextField_hcmean_vlastna.setText(df.format(X.get_h_c_mean_window_vlastna()));
       // System.out.println(Variable_Hc_mean);
        }else{
       jRadioButton_with_label_vlastna.setSelected(true);
       Variable_Hc_mean=double_setter(X.get_h_c_mean());
       pretazenia_intomainframe();
       Variable_Hc_mean_medzikrok=X.get_h_c_mean_window_vypocitana();
       
       TextField_hcmean_vpocitana.setText(String.valueOf(X.get_h_c_mean_window_vypocitana()));
        TextField_hcmean_vlastna.setText(String.valueOf(X.get_h_c_mean_window_vlastna()));
       // System.out.println(Variable_Hc_mean);
       }
       
       if(Variable_Ai_array.length != 0 || Variable_DeltaHi_array.length != 0){
                  mid_span_flat();
                  mid_span_terrain();
                  
        TextField_STRrozpatie_klasicky.setText(String.valueOf(df.format(Variable_mid_span_docasna)));
        TextField_STRrozpatie_sPrevisenim.setText(String.valueOf(df.format(Variable_mid_span_terrain_docasna)));
        if(jRadioButton_with_label_rozpatie_klasicky.isSelected() == true){Variable_mid_span=Variable_mid_span_docasna;}
        if(jRadioButton_with_label_rozpate_previsenia.isSelected() == true){Variable_mid_span=Variable_mid_span_terrain_docasna;}
                }
       
        hodnoty_namrazove_oblasti[0]=X.get_hodnotynamrazove_oblast()[0];
        hodnoty_namrazove_oblasti[1]=X.get_hodnotynamrazove_oblast()[1];
        hodnoty_namrazove_oblasti[2]=X.get_hodnotynamrazove_oblast()[2];
        hodnoty_namrazove_oblasti[3]=X.get_hodnotynamrazove_oblast()[3];
        
        
        }
   
    private String get_char_terenu_number(kotevnyUsek X){
        
        int number_of_char = X.get_char_terenu_porcislo();
        String output="none";  
          switch (number_of_char) {
            case 0:  output="I";
                     //jTextPane_char_terenu.setText(language.language_label(languageOption, 135));
                     break;
            case 1:  output="II";
                     //jTextPane_char_terenu.setText(language.language_label(languageOption, 136));
                     break;
            case 2:  output="III";
                     //jTextPane_char_terenu.setText(language.language_label(languageOption, 137));
                     break;
            case 3:  output="IV";
                     //jTextPane_char_terenu.setText(language.language_label(languageOption, 139));
                     break;
            case 4:  output="V";
                     //jTextPane_char_terenu.setText(language.language_label(languageOption, 139));
                     break;                    
        }
        return output;      
    }
    
    private String get_char_terenu_text(kotevnyUsek X){
        
        int number_of_char = X.get_char_terenu_porcislo();
        String output="none";  
          switch (number_of_char) {
            case 0:  
                     output=language.language_label(languageOption, 207);
                     break;
            case 1:  
                     output=language.language_label(languageOption, 208);
                     break;
            case 2:  
                     output=language.language_label(languageOption, 209);
                     break;
            case 3:  
                     output=language.language_label(languageOption, 210);
                     break;
            case 4:  
                     output=language.language_label(languageOption, 211);
                     break;                    
        }
        return output;      
    }
    
    private String get_typ_terenu_number(int X){
        
        int number_of_char = X;
        String output="none";  
          switch (number_of_char) {
                case 0:  output="1";
                //jTextPane_KPB_typ_terenu.setText(language.language_label(languageOption, 169));
                break;
                case 1:  output="2"; 
               // jTextPane_KPB_typ_terenu.setText(language.language_label(languageOption, 170));
                break;
                case 2:  output="3"; 
                //jTextPane_KPB_typ_terenu.setText(language.language_label(languageOption, 171));
                break;
                case 3:  output="4"; 
                //jTextPane_KPB_typ_terenu.setText(language.language_label(languageOption, 172));
                break;

            }
        return output;      
    }
    
    private String get_typ_terenu_text(int X){
        
        int number_of_char = X;
        String output="none";  
          switch (number_of_char) {
                case 0:  output=language.language_label(languageOption, 169);
                break;
                case 1:  output=language.language_label(languageOption, 170);
                break;
                case 2:  output=language.language_label(languageOption, 171);
                break;
                case 3:  output=language.language_label(languageOption, 172);
                break;

            }
        return output;      
    }
    
    private String get_rok_navratu_cas(int X){
        
        int number = X;
        String output="none";
        
        int selected_index_from_JComboBox = jComboBox_uroven_splahlivosti.getSelectedIndex();

            switch (number) {
                case 0:  //50 rokov
                output = "50 " + language.language_label(languageOption, 214);
                break;
                case 1:  //150 rokov
                output = "150 " + language.language_label(languageOption, 214);
                break;
                case 2:  //500 rokov
                output = "500 " + language.language_label(languageOption, 214);
                break;
                case 3:  //3 dni
                output = "3 " + language.language_label(languageOption, 235);
                break;
                case 4:  //3 mesiace
                output = "50"+ language.language_label(languageOption, 237);
                break;
                case 5:  //1 rok
                output = "50"+ language.language_label(languageOption, 236);
                break;
                case 6:  //50 rokov
                output = String.valueOf(Variable_uroven_spolahlivosti_cas_navratu_klim_udalosti) +" " + language.language_label(languageOption, 214);
                break;
            }
        
        
        return output;      
    }
 
    private String get_vetrova_oblast_string(int X){
        
        int number = X;
        String output="none";
        
        

            switch (number) {
                case 0:  //50 rokov
                output = "I-SK";
                break;
                case 1:  //150 rokov
                output = "II-SK";
                break;
                case 2:  //500 rokov
                output = "III-SK";
                break;
                case 3:  //3 dni
                output = "IV-SK";
                break;
                case 4:  //3 mesiace
                output = "I-CZ";
                break;
                case 5:  //1 rok
                output = "II-CZ";
                break;
                case 6:  //1 rok
                output = "III-CZ";
                break;
                case 7:  //1 rok
                output = "IV-CZ";
                break;
                case 8:  //1 rok
                output = "V-CZ";
                break;
                case 9:  //1 rok
                output = "Volitelné";
                break;               
            }
        
        
        return output;      
    }
   
    private String get_vcas_od_montaze(){
        
        
        String output="none";
         
        if(jRadioButton_with_label_pociatocne.isSelected()){
            output= "0";
        }
        if(jRadioButton_with_label_pociatocne1.isSelected()){
            output= "0";
        }
        if(jRadioButton_with_label_prechodne.isSelected()){
            output= TextField_tabulky_prechodna.getText();
        }
         if(jRadioButton_with_label_konecne.isSelected()){
            output= TextField_tabulky_konecna.getText();
        }

        return output;      
    }
   
    /**
     * 
     * @param doc    Itext document
     * @param kot_usek kotevny usek class
     * @param fontTable   font for table
     * @param fontScript   font for script
     * @param fontScript2   font for script outsideš table
     * @param fontText      font fot plain text
     * @param spacing       spacing in table
     * @param decimal_dva   rounding on two decimal places
     * @throws DocumentException  well this is posible
     */
    private void make_MT_table (Document doc,kotevnyUsek kot_usek, Font fontTable,Font fontScript,Font fontScript2,Font fontText,int spacing,DecimalFormat decimal_dva,DecimalFormat decimal_tri,DecimalFormat decimal_none   ) throws DocumentException{
       float[] columnWidths_of_table = {49f,33f,33f,33f,33f,33f,33f,33f,33f,33f,33f,33f,33f,33f,33f};
        PdfPTable table = new PdfPTable(columnWidths_of_table);
        table.setTotalWidth(511f); 
        table.setLockedWidth(true);
        table.setHorizontalAlignment(Element.ALIGN_CENTER); 
        
        PdfPCell c1 = new PdfPCell();
        Chunk p1 = new Chunk(language.language_label(languageOption, 240)  ,fontTable);
        Paragraph p1_total= new Paragraph(spacing);
        p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
        c1.addElement(p1_total);table.addCell(c1);
        
        
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav1.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
        
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav2.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav3.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav4.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav5.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav6.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav7.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav8.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav9.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav10.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav11.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav12.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav13.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav14.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         Chunk subScript = new Chunk("H",fontScript); // superscript
         subScript.setTextRise(-2f); 
         
        c1 = new PdfPCell();
        p1 = new Chunk("\u03C3" ,fontTable);        
        Chunk p2 = new Chunk( " [MPa]"  ,fontTable);
        p1_total= new Paragraph(spacing);
        p1_total.add(p1);p1_total.add(subScript);p1_total.add(p2);p1_total.setAlignment(Element.ALIGN_CENTER);
        c1.addElement(p1_total);table.addCell(c1);
        
        for(int i =0 ; i<14;i++){
         c1 = new PdfPCell();
         p1 = new Chunk(decimal_dva.format(kot_usek.get_vysledky_sigmaH_MT()[i]),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
           
            
        }
        
        c1 = new PdfPCell();    
        p1 = new Chunk( "c [m]"  ,fontTable);
        p1_total= new Paragraph(spacing);
        p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
        c1.addElement(p1_total);table.addCell(c1);
        
        for(int i =0 ; i<14;i++){
         c1 = new PdfPCell();
         p1 = new Chunk(decimal_none.format(kot_usek.get_vysledky_c_MT()[i]),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
           
            
        }
        
        c1 = new PdfPCell();    
        p1 = new Chunk( language.language_label(languageOption, 241)  ,fontTable);
        p1_total= new Paragraph(spacing);
        p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
        c1.addElement(p1_total);table.addCell(c1);
        
        for(int i =0 ; i<14;i++){
         c1 = new PdfPCell();
         p1 = new Chunk(decimal_tri.format(kot_usek.get_vysledky_pretazenia_MT()[i]),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
           
            
        }
        
        c1 = new PdfPCell();
        p1 = new Chunk("F" ,fontTable);        
        p2 = new Chunk( " [kN]"  ,fontTable);
        p1_total= new Paragraph(spacing);
        p1_total.add(p1);p1_total.add(subScript);p1_total.add(p2);p1_total.setAlignment(Element.ALIGN_CENTER);
        c1.addElement(p1_total);table.addCell(c1);
        
        for(int i =0 ; i<14;i++){
         c1 = new PdfPCell();
         p1 = new Chunk(decimal_dva.format(kot_usek.get_vysledky_sily_MT()[i]),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
           
            
        }
        
         
         
         doc.add(table);
         
          subScript = new Chunk("v",fontScript2); // superscript
                 subScript.setTextRise(-2f);   
          doc.add(new Phrase(" " + language.language_label(languageOption, 242) ,fontText)); 
          doc.add(subScript);  
          doc.add(new Phrase("  [m] " + language.language_label(languageOption, 243) ,fontText)); 
            
         
         
         
        table = new PdfPTable(columnWidths_of_table);
        table.setTotalWidth(511f); 
        table.setLockedWidth(true);
        table.setHorizontalAlignment(Element.ALIGN_CENTER); 
        
         c1 = new PdfPCell();
         p1 = new Chunk(language.language_label(languageOption, 240)  ,fontTable);
         p1_total= new Paragraph(spacing);
        p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
        c1.addElement(p1_total);table.addCell(c1);
        
        
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav1.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
        
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav2.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav3.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav4.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav5.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav6.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav7.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav8.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav9.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav10.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav11.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav12.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav13.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         c1 = new PdfPCell();
         p1 = new Chunk(TextField_teploha_stav14.getText(),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         double celkova_dlzka_kot_useku=0;
         
          for (int y = 0; y < kot_usek.get_Ai_array().length; y++) {
                
               c1 = new PdfPCell();    
               p1 = new Chunk( decimal_dva.format(kot_usek.get_Ai_array()[y]) ,fontTable);
               p1_total= new Paragraph(spacing);
               p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
               c1.addElement(p1_total);table.addCell(c1);
               celkova_dlzka_kot_useku=celkova_dlzka_kot_useku+kot_usek.get_Ai_array()[y];
              
                for (int z = 0; z < 14; z++) {
                    
                     c1 = new PdfPCell();
                     p1 = new Chunk(decimal_dva.format(kot_usek.get_vysledky_vid_priehyb_MT()[y][z]),fontTable);
                     p1_total= new Paragraph(spacing);
                     p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
                     c1.addElement(p1_total);table.addCell(c1);
                    
                                       
                }                
            }
          
         
         c1 = new PdfPCell();
         p1 = new Chunk(decimal_dva.format(celkova_dlzka_kot_useku),fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.addElement(p1_total);table.addCell(c1);
         
         for(int i =0 ; i<14;i++){
         c1 = new PdfPCell();
         p1 = new Chunk("",fontTable);
         p1_total= new Paragraph(spacing);
         p1_total.add(p1);p1_total.setAlignment(Element.ALIGN_CENTER);
         c1.setBorder(Rectangle.NO_BORDER);
         c1.addElement(p1_total);table.addCell(c1);
           
            
        }
            
          doc.add(table);
         
    }

    private void global_var_to_main_frame(projekt_global_variables X){
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
               otherSymbols.setDecimalSeparator('.');
               DecimalFormat df = new DecimalFormat("###.##",otherSymbols);
               DecimalFormat df0 = new DecimalFormat("###",otherSymbols);
        
               
        jRadioButton_with_label_rozpatie_klasicky.setSelected(X.get_mid_span_option());
        jRadioButton_KPB_cas_vypoctu_1_rok.setSelected(X.get_KPB_typ_terenu());
        jComboBox_KPB_typ_terenu.setSelectedIndex(X.get_KPB_combobo_number());
        Variable_KPB_typ_terenu =(double) X.get_KPB_combobo_number() +1; 
        TextField_tabulky_prechodna.setText(df.format(X.get_tables_prechodne()));
        TextField_tabulky_konecna.setText(df.format(X.get_tables_konecne()));
        Variable_T0_zivotnost=X.get_tables_konecne()*24*365;
        Variable_Tp_prechodna_doba=X.get_tables_prechodne()*24*365;
        
        
        if(X.get_tables_number123()==2 || X.get_tables_number123()==3){
        if(X.get_tables_number123()==3){
        jRadioButton_with_label_konecne.setSelected(true);
        Variable_Tp_prechodna_doba=Variable_T0_zivotnost;
        PDF_VAR_typ_tabulky = 3;
        
        }else{jRadioButton_with_label_prechodne.setSelected(true); PDF_VAR_typ_tabulky = 2;}
       
        for(int i=0;i<14;i++){
         Variable_teploty_stav_rovnica[i]=X.get_teploty_MT()[i];   
        }
        TextField_teploha_stav2.setText(df0.format(X.get_teploty_MT()[1]));
        TextField_teploha_stav3.setText(df0.format(X.get_teploty_MT()[2]));
        TextField_teploha_stav10.setText(df0.format(X.get_teploty_MT()[9]));
        TextField_teploha_stav11.setText(df0.format(X.get_teploty_MT()[10]));
        TextField_teploha_stav12.setText(df0.format(X.get_teploty_MT()[11]));
        TextField_teploha_stav13.setText(df0.format(X.get_teploty_MT()[12]));
        
        
        }
        
        if(X.get_tables_number123()==1){
        jRadioButton_with_label_pociatocne.setSelected(true);
        Variable_Tp_prechodna_doba = 0.0;
        for(int i=0;i<14;i++){
         Variable_teploty_stav_rovnica[i]=X.get_teploty_MT()[i];   
        }
        TextField_teploha_stav1.setText(df0.format(X.get_teploty_MT()[0]));
        TextField_teploha_stav7.setText(df0.format(X.get_teploty_MT()[6]));
        TextField_teploha_stav9.setText(df0.format(X.get_teploty_MT()[8]));
        TextField_teploha_stav10.setText(df0.format(X.get_teploty_MT()[9]));
        TextField_teploha_stav11.setText(df0.format(X.get_teploty_MT()[10]));
        TextField_teploha_stav12.setText(df0.format(X.get_teploty_MT()[11]));
         TextField_teploha_stav13.setText(df0.format(X.get_teploty_MT()[12]));
          TextField_teploha_stav14.setText(df0.format(X.get_teploty_MT()[13]));
        PDF_VAR_typ_tabulky = 1;
        
        }
        if(X.get_tables_number123()==4){
        jRadioButton_with_label_pociatocne1.setSelected(true);
        Variable_Tp_prechodna_doba = 0.0;
        for(int i=0;i<14;i++){
         Variable_teploty_stav_rovnica[i]=X.get_teploty_MT()[i];   
        }
        TextField_teploha_stav2.setText(df0.format(X.get_teploty_MT()[1]));
        TextField_teploha_stav3.setText(df0.format(X.get_teploty_MT()[2]));
        TextField_teploha_stav10.setText(df0.format(X.get_teploty_MT()[9]));
        TextField_teploha_stav11.setText(df0.format(X.get_teploty_MT()[10]));
        TextField_teploha_stav12.setText(df0.format(X.get_teploty_MT()[11]));
        TextField_teploha_stav13.setText(df0.format(X.get_teploty_MT()[12]));
        PDF_VAR_typ_tabulky = 1;
        
        }
        
        
         jRadioButton_with_pretazenia_vypocitana.setSelected(X.get_pretazenia_vypocitane());
    
         
         if(X.get_pretazenia_vypocitane() == false ){
        
         TextField_pretazenia_stav1.setText(df.format(X.get_pretazenia_MT()[0]));    
         TextField_pretazenia_stav2.setText(df.format(X.get_pretazenia_MT()[1]));
         TextField_pretazenia_stav3.setText(df.format(X.get_pretazenia_MT()[2]));
         TextField_pretazenia_stav4.setText(df.format(X.get_pretazenia_MT()[3]));
         TextField_pretazenia_stav5.setText(df.format(X.get_pretazenia_MT()[4]));
         TextField_pretazenia_stav6.setText(df.format(X.get_pretazenia_MT()[5]));
         TextField_pretazenia_stav7.setText(df.format(X.get_pretazenia_MT()[6]));
         TextField_pretazenia_stav8.setText(df.format(X.get_pretazenia_MT()[7]));
         TextField_pretazenia_stav9.setText(df.format(X.get_pretazenia_MT()[8]));
         TextField_pretazenia_stav10.setText(df.format(X.get_pretazenia_MT()[9]));
         TextField_pretazenia_stav11.setText(df.format(X.get_pretazenia_MT()[10]));
         TextField_pretazenia_stav12.setText(df.format(X.get_pretazenia_MT()[11]));
         TextField_pretazenia_stav13.setText(df.format(X.get_pretazenia_MT()[12]));
         TextField_pretazenia_stav14.setText(df.format(X.get_pretazenia_MT()[13]));
         array_pretaezenia_stav_rovnica_loader_setter();
        
        }
         
     jTextField_datum.setText(X.get_hlavicka().dátum());
     jTextField_nadpis_pre_prechodna.setText(X.get_hlavicka().nadpis_pre_prechodne());
     jTextField_nazov_SOPS.setText(X.get_hlavicka().SO_PS());
     jTextField_nazov_SOPS1.setText(X.get_hlavicka().SO_PS1());
     jTextField_nazov_arch_cislo.setText(X.get_hlavicka().Archivne_cislo());
     jTextField_nazov_cislo_strany.setText(String.valueOf(X.get_hlavicka().cislovanie_stran_od()));
     jTextField_nazov_nazov_stavby.setText(X.get_hlavicka().Stavba());
    jTextField_nazov_nazov_stavby1.setText(X.get_hlavicka().Stavba1());
    jTextField_nazov_normi.setText(X.get_hlavicka().vypocet_podla_normy_getter());
     jTextField_vypracoval.setText(X.get_hlavicka().vypracoval()); 
         
    
    }
    
    private void save_project(String user_path) throws UnsupportedEncodingException{
        
        // create variables filename and filepath
        // if save  with false just resave ( fafalse if loaded of of save as
        // if save with true open chooser ane create new file and so on save simple
        
        //make saving cycle folder for save on the 
        
        // save include global variable ane variables kotevne useky witch is cycles 
        
        //ochrana ak nie jezadani filepath alebo filename
        if(project_filename.equals("") || project_filepath.equals("")){
          project_save_as=true;  
        }
        
        if(project_save_as==true){
        
        String userhome = System.getProperty("user.dir");          //userhome is home folder of program
        
        // ak je zadaná špec lokaciakde ukladať tak tam ak nide default priečion kde existuje
         JFileChooser chooser;
        if(user_path.equals("")){
         chooser = new JFileChooser(userhome + "");}
        else{
          chooser = new JFileChooser(user_path);}
        
          //key files are stored in resources
        FileNameExtensionFilter txtfilter = new FileNameExtensionFilter(
                language.language_label(languageOption, 32), "MT3");                                // whitch type of files are we looking for
        chooser.setDialogTitle(language.language_label(languageOption, 247));   // title for Jfile chooser window
        chooser.setFileFilter(txtfilter);                                   // Txt filter for choosing file

        chooser.showSaveDialog(null);
        File f = chooser.getSelectedFile();   
            
        project_filename=  f.getName()+".MT3"; 
        project_filepath = f.getParent(); 
        
        }else{
          
        //save  no save_as  filename and filepath has data    
            
        }
        
        File subor = new File(project_filepath +"\\" +project_filename);
        
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(subor), Charset.forName("UTF-8"));
            PrintWriter fw = new PrintWriter(outputStreamWriter);
            
            fw.println("Saved project file DO NOT MODIFY or I will hunt you and eat you alive");
            fw.println(language.language_label(languageOption,308));
            fw.println("All rights restricked for SAG Elektrovod a.s. Created by Jozef Bendík & Matej Cenký 2016 ");
            fw.println("General project data");
            fw.println(project_name); // name of project
            
            fw.println(   jRadioButton_with_label_rozpatie_klasicky.isSelected()       );
            fw.println(   jRadioButton_KPB_cas_vypoctu_1_rok.isSelected()       );
            fw.println(   jComboBox_KPB_typ_terenu.getSelectedIndex()       );
            
            int cislo=3;
            if(jRadioButton_with_label_pociatocne.isSelected()== true){cislo=1;}
            if(jRadioButton_with_label_prechodne.isSelected()== true){cislo=2;}
            if(jRadioButton_with_label_konecne.isSelected()== true){cislo=3;}
            if(jRadioButton_with_label_pociatocne1.isSelected()== true){cislo=4;}
            fw.println(   cislo      );
            
            fw.println(   TextField_tabulky_prechodna.getText()      );
            fw.println(   TextField_tabulky_konecna.getText()      );
            
            for(int i =0 ;i<14;i++){
            fw.print(   Variable_teploty_stav_rovnica[i] + " "      );}
            fw.println(" ");
             for(int i =0 ;i<14;i++){
            fw.print(   Variable_pretazenia_stav_rovnica[i] + " "      );}
            fw.println(" ");
            
            fw.println(   jRadioButton_with_pretazenia_vypocitana.isSelected()   );
            
            fw.println(input_header_text(jTextField_nazov_normi));   // hlavicka projektu tak ako sa zadava do class
            fw.println(input_header_text(jTextField_nadpis_pre_prechodna)); 
            fw.println(input_header_text(jTextField_nazov_nazov_stavby)); 
            fw.println(input_header_text(jTextField_nazov_nazov_stavby1)); 
            fw.println(input_header_text(jTextField_nazov_SOPS)); 
            fw.println(input_header_text(jTextField_nazov_SOPS1)); 
            fw.println(input_header_text(jTextField_nazov_arch_cislo)); 
            fw.println(input_header_text(jTextField_vypracoval)); 
            fw.println(input_header_text(jTextField_datum));
            fw.println(intChecker_short_answer(jTextField_nazov_cislo_strany)); 
            // zapis kolko kotevných usekov existuje
            fw.println(Table_kotevne_useky.getRowCount());
            
            for(int i = 0 ; i< Table_kotevne_useky.getRowCount();i++ ){
            fw.println("-----------------------------------");
            fw.println(Variable_globeal_kotevny_usek.get(i).get_name());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_conductor_name());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_vetrova_oblast_porcislo());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_char_terenu_porcislo());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_uroven_spolahlivosti_porcislo());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_uroven_spolahlivosti_stav_porcislo());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_namrazova_oblast_string());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_typ_namrazy_porcislo());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_RTS_over());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_zakladne_mech_napatie_lana_pre_minus5_over());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_maximalne_zataz_lana_podiel_z_RTS_over());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_c_dir());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_g_c());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_ro_I());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_K_lc());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_K_h());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_I_R50());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_k_r());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_z_0());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_V_mean());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_c_0());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_C_c());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_gama_w());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_gama_I());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_Psi_I());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_Psi_w());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_B_I());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_k_p());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_RR());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_ro());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_C_cl());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_h_c_mean());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_h_c_mean_window_vypocitana());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_h_c_mean_window_vlastna());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_str_rozpatie());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_str_vys_vodicov_radio());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_CDIR_radio());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_CO_radio());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_Kcl_radio());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_Kh_radio());
            fw.println(Variable_globeal_kotevny_usek.get(i).get_Bi_radio());
            
            
            try{fw.println(Variable_globeal_kotevny_usek.get(i).get_Ai_array().length);
            for(int a =0 ;a<Variable_globeal_kotevny_usek.get(i).get_Ai_array().length;a++){
            fw.print(   Variable_globeal_kotevny_usek.get(i).get_Ai_array()[a]   + " "   );}
            fw.println(" ");}catch(NullPointerException a ){fw.println(1); fw.println(0.0);}
            
            try{fw.println(Variable_globeal_kotevny_usek.get(i).get_DeltaHi_array().length);
             for(int a =0 ;a<Variable_globeal_kotevny_usek.get(i).get_DeltaHi_array().length;a++){
            fw.print(   Variable_globeal_kotevny_usek.get(i).get_DeltaHi_array()[a]   + " "   );}
            fw.println(" ");}catch(NullPointerException a ){fw.println(1); fw.println(0.0);}
            
            try{ fw.println(Variable_globeal_kotevny_usek.get(i).get_Hi_array().length);
             for(int a =0 ;a<Variable_globeal_kotevny_usek.get(i).get_Hi_array().length;a++){
            fw.print(   Variable_globeal_kotevny_usek.get(i).get_Hi_array()[a]   + " "   );}
            fw.println(" ");   }catch(NullPointerException a ){fw.println("2"); fw.println("0.0  0.0");}  
            
            try{ fw.println(Variable_globeal_kotevny_usek.get(i).get_Hi_array_nvm().length);
             for(int a =0 ;a<Variable_globeal_kotevny_usek.get(i).get_Hi_array_nvm().length;a++){
            fw.print(   Variable_globeal_kotevny_usek.get(i).get_Hi_array_nvm()[a]   + " "   );}
            fw.println(" "); }catch(NullPointerException a ){fw.println("2"); fw.println("0.0  0.0");}
             
            fw.println(Variable_globeal_kotevny_usek.get(i).get_hodnotynamrazove_oblast()[0] );
            fw.println(Variable_globeal_kotevny_usek.get(i).get_hodnotynamrazove_oblast()[1] );
            fw.println(Variable_globeal_kotevny_usek.get(i).get_hodnotynamrazove_oblast()[2] );
            fw.println(Variable_globeal_kotevny_usek.get(i).get_hodnotynamrazove_oblast()[3] );
        
            
            
            }
            fw.println("This is End of file_ bye bye java reader");
            
            
            fw.close();
            
            
            
            
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mainframe_17.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
    
    private String input_header_text(javax.swing.JTextField Y){
        
        String X="---";
        if(Y.getText().equals("")){
             X="---";
        }else{
         X=Y.getText();
        }    
        return X;
    }
    
    private void load_project(String user_path){
        // load sa spusta pred nacitanim prvkov s tym že blokuje vačšinu veci
        // výdy sa pusťa chooser
        String userhome = System.getProperty("user.dir");
        if(startPanel.load_memory_path_plus_filename.equals("none")){
             userhome = System.getProperty("user.dir");
        }else{
            userhome = startPanel.load_memory_path_plus_filename;  
        }
                  //userhome is home folder of program
        
        // ak je zadaná špec lokaciakde ukladať tak tam ak nide default priečion kde existuje
         JFileChooser chooser;
        if(user_path.equals("")){
         chooser = new JFileChooser(userhome + "");}
        else{
          chooser = new JFileChooser(user_path);}
        
          //key files are stored in resources
        FileNameExtensionFilter txtfilter = new FileNameExtensionFilter(
                language.language_label(languageOption, 32), "MT3");                                // whitch type of files are we looking for
        chooser.setDialogTitle(language.language_label(languageOption, 246));   // title for Jfile chooser window
        chooser.setFileFilter(txtfilter);                                   // Txt filter for choosing file

        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();   
            
        project_filename=  f.getName();
        project_filepath = f.getParent(); 
        
        File subor = new File(project_filepath + "\\" + project_filename);
        String pokus;
        
        try {
            Scanner input = new Scanner(subor,"UTF-8");
            
            Locale locale2 = new Locale("sk","SK");
            input.useLocale(locale2);
            
            
            
            pokus = input.nextLine();  // uvod kecy
            pokus = input.nextLine();
            
            if ( pokus.equals(language.language_label(languageOption,308))){
                
            }else{
                JOptionPane.showMessageDialog(Table_kotevne_useky, language.language_label(languageOption,309));
            }
            
            pokus = input.nextLine();
            pokus = input.nextLine();
            
            project_name = input.nextLine();
            
            boolean rozpatie_klasicky = Boolean.valueOf(input.nextLine()) ;
            boolean KPB_cas_vypoctu_1_rok = Boolean.valueOf(input.nextLine()) ;
            int ComboBox_KPB_typ_terenu =  Integer.valueOf(input.nextLine());
            int radio_number_tabulky=  Integer.valueOf(input.nextLine());
            double tabulky_prechodna = Double.valueOf(input.nextLine());
            double tabulky_konecne = Double.valueOf(input.nextLine());
            
            double[] teploty_stav_mt= new double[14];
            double[] pretazenia_stav_mt= new double[14];
            
             for(int i =0 ;i<14;i++){
             
             teploty_stav_mt[i]=Double.parseDouble(input.next());
             }
              pokus = input.nextLine();
             for(int i =0 ;i<14;i++){
             pretazenia_stav_mt[i]=Double.parseDouble(input.next());     
             }
             pokus = input.nextLine();
             boolean pretazena_vypocitane = Boolean.valueOf(input.nextLine()) ;
           
             String nazov_normi = input.nextLine();
             String       nadpis_pre_prechodna = input.nextLine();
             String       nazov_nazov_stavby = input.nextLine();
             String       nazov_nazov_stavby1 = input.nextLine();
             String       nazov_SOPS = input.nextLine();
             String       nazov_SOPS1 = input.nextLine();
             String       nazov_arch_cislo = input.nextLine();
             String       vypracoval = input.nextLine();
             String       datum = input.nextLine();
             int       nazov_cislo_strany = Integer.valueOf(input.nextLine());
             
             
             header_pdf hlavicka = new header_pdf(
                                         nazov_normi ,
                                         nadpis_pre_prechodna,
                                         nazov_nazov_stavby,
                                         nazov_nazov_stavby1,
                                         nazov_SOPS,
                                         nazov_SOPS1,
                                         nazov_arch_cislo,
                                         vypracoval,
                                         datum,
                                         nazov_cislo_strany
             );
             
             //add to global variables
             projekt_global_variables project_global_VAR  = new projekt_global_variables(
             rozpatie_klasicky,
             KPB_cas_vypoctu_1_rok,
             ComboBox_KPB_typ_terenu,
             radio_number_tabulky,
             tabulky_prechodna,
             tabulky_konecne,
             teploty_stav_mt,
             pretazenia_stav_mt,
             pretazena_vypocitane,
             hlavicka
                     );
             
             global_var_to_main_frame(project_global_VAR);
            
             //nacitavanie kotevnych usekov
               int       pocetkot_usekov = Integer.valueOf(input.nextLine());
              for(int i =0 ;i<pocetkot_usekov;i++){
              pokus = input.nextLine();
              
              String name = input.nextLine();
              String conductor_name = input.nextLine();
              int    conductor_number = najdiconductor_number(conductor_name);
              int    vetrova_obl_number = Integer.valueOf(input.nextLine());
              int    chat_ter_number = Integer.valueOf(input.nextLine());
              int    uroven_splo_number = Integer.valueOf(input.nextLine());
              int    uroven_stav_number = Integer.valueOf(input.nextLine());
              String namr_obl = input.nextLine();
              int    typ_namr = Integer.valueOf(input.nextLine());
              double RTS = Double.valueOf(input.nextLine());
              double zakladne_mech_napatie_lana_pre_minus5 = Double.valueOf(input.nextLine());
              double maximalne_zataz_lana_podiel_z_RTS = Double.valueOf(input.nextLine());
              double c_dir = Double.valueOf(input.nextLine());
              double g_c = Double.valueOf(input.nextLine());
              double ro_I = Double.valueOf(input.nextLine());
              double K_lc = Double.valueOf(input.nextLine());
              double K_h = Double.valueOf(input.nextLine());
              double I_R50 = Double.valueOf(input.nextLine());
              double k_r = Double.valueOf(input.nextLine());
              double z_0 = Double.valueOf(input.nextLine());
              double V_mean = Double.valueOf(input.nextLine());
              double c_0 = Double.valueOf(input.nextLine());
              double C_c = Double.valueOf(input.nextLine());
              double gama_w = Double.valueOf(input.nextLine());
              double gama_I = Double.valueOf(input.nextLine());
              double Psi_I = Double.valueOf(input.nextLine());
              double Psi_w = Double.valueOf(input.nextLine());
              double B_I = Double.valueOf(input.nextLine());
              double k_p = Double.valueOf(input.nextLine());
              double RR = Double.valueOf(input.nextLine());
              double ro = Double.valueOf(input.nextLine());
              double C_cl = Double.valueOf(input.nextLine());
              double h_c_mean = Double.valueOf(input.nextLine());
              double h_c_mean_window_vypocitana = Double.valueOf(input.nextLine());
              Variable_Hc_mean_medzikrok=h_c_mean_window_vypocitana;
              double h_c_mean_window_vlastn = Double.valueOf(input.nextLine());
              double str_rozpatie = Double.valueOf(input.nextLine());
              boolean str_vys_vodicov_radio = Boolean.valueOf(input.nextLine());
              boolean CDIR_radio = Boolean.valueOf(input.nextLine());
              boolean CO_radio = Boolean.valueOf(input.nextLine());
              boolean Kcl_radio = Boolean.valueOf(input.nextLine());
              boolean Kh_radio = Boolean.valueOf(input.nextLine());
              boolean Bi_radio = Boolean.valueOf(input.nextLine());
              
             
             
             int    pocetAi = Integer.valueOf(input.nextLine());
             double[] Ai_array= new double[pocetAi];           
             for(int a =0 ;a<pocetAi;a++){             
             Ai_array[a]=Double.parseDouble(input.next());
             }
             pokus = input.nextLine();
              
             int    pocetDeltaHi = Integer.valueOf(input.nextLine());
             double[] DeltaHi_array= new double[pocetDeltaHi];           
             for(int a =0 ;a<pocetDeltaHi;a++){             
             DeltaHi_array[a]=Double.parseDouble(input.next());
             }
             pokus = input.nextLine();
             
             int    pocetHi = Integer.valueOf(input.nextLine());
             double[] Hi_array= new double[pocetHi];           
             for(int a =0 ;a<pocetHi;a++){             
             Hi_array[a]=Double.parseDouble(input.next());
             }
             pokus = input.nextLine();
             
             int    pocetHi_nvm = Integer.valueOf(input.nextLine());
             double[] Hi_nvm_array= new double[pocetHi_nvm];           
             for(int a =0 ;a<pocetHi_nvm;a++){             
             Hi_nvm_array[a]=Double.parseDouble(input.next());
             }
             pokus = input.nextLine();
             
             double namr_hod1 = Double.valueOf(input.nextLine());
             double namr_hod2 = Double.valueOf(input.nextLine());
             double namr_hod3 = Double.valueOf(input.nextLine());
             double namr_hod4 = Double.valueOf(input.nextLine());
             double[] oblast_hodnoty = new double[4];
             oblast_hodnoty = new double[]{(double) namr_hod1, (double) namr_hod2,(double) namr_hod3, (double) namr_hod4};
             
            double[] empty = null;
            double[][] empty2 = null;
             
             kotevnyUsek novy_usek =  new kotevnyUsek(
                     name,
                     conductor_number,
                     conductor_name,
                     vetrova_obl_number,
                     chat_ter_number,
                     uroven_splo_number,
                     uroven_stav_number,
                     namr_obl,
                     typ_namr,
                     RTS,
                     zakladne_mech_napatie_lana_pre_minus5,
                     maximalne_zataz_lana_podiel_z_RTS,
                     g_c,
                     ro_I,
                     K_lc,
                     K_h,
                     I_R50,
                     k_r,
                     z_0,
                     V_mean,
                     c_dir,
                     c_0,
                     C_c,
                     gama_w,
                     gama_I,
                     Psi_I,
                     Psi_w,
                     B_I,
                     k_p,
                     RR,
                     ro,
                     C_cl,
                     h_c_mean,
                     str_vys_vodicov_radio,
                     CDIR_radio,
                     CO_radio,
                     Kcl_radio,
                     Kh_radio,
                     Bi_radio,
                     Ai_array,
                     DeltaHi_array,                    
                     Hi_array,
                     Hi_nvm_array,
                     h_c_mean_window_vypocitana,
                     h_c_mean_window_vlastn,
                     str_rozpatie,
                     oblast_hodnoty);    
                selection_kotevny_usek=true;
               if(i==0){
                   Variable_globeal_kotevny_usek.set(0, novy_usek);
                   kotevn_usek_to_mainframe(novy_usek);
               }else{
                  modelTable.addRow(new Object[]{(Boolean) false,(String) name});
                  Variable_globeal_kotevny_usek.add(novy_usek);
               }
               selection_kotevny_usek=false;
             
              }
              pokus = input.nextLine();
              
             pretazenia_intomainframe();
              //udate nazvnu pre tabbed panel KPB
                    try {
                        int cislo = Table_kotevne_useky.getSelectedRow();
                        String nazov = Variable_globeal_kotevny_usek.get(cislo).get_name();
                        jTabbedPane1.setTitleAt(1, language.language_label(languageOption, 250) + " - " + nazov);

                    } catch (ArrayIndexOutOfBoundsException p) {
                        jTabbedPane1.setTitleAt(1, language.language_label(languageOption, 250));

                    }
              
              
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mainframe_17.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public int najdiconductor_number(String name){
        int index_v_databaze = 0;
        
        for(int cl1 = 0 ; cl1< Databaza.size();cl1 ++){
            
            if ( Databaza.get(cl1)[0].equals(name)){
                index_v_databaze=cl1;
                break;
            }else{
                index_v_databaze=0;
            }
            
        }
        
        
        return index_v_databaze; 
    }
    
    public  double double_setter(double X){
       // pretazenia_intomainframe();
        color_the_percento_field_to_orange();
        return X;
           
           
       }
    
    public double[] double_setter_array(double[] X){
       // pretazenia_intomainframe();
        color_the_percento_field_to_orange();
        return X;
           
       }
  
    public   void pretazenia_intomainframe(){
        
      if(jRadioButton_with_pretazenia_vypocitana.isSelected() == true &&
         Variable_Ir50 != 123456789.987564321 &&
         Variable_Hc_mean != 0.0            
              ){
        
      try{  
      int selected_conductor_index_from_JComboBox = jComboBox_conductor_chooser.getSelectedIndex();
            Conductor_variables Conductor =  new  Conductor_variables (Databaza.get(selected_conductor_index_from_JComboBox));   
         
       double final_psi_w ;
            if(jComboBox_uroven_splahlivosti.getSelectedIndex() == 6){ // custom values set
                final_psi_w = Variable_globeal_kotevny_usek.get(Table_kotevne_useky.getSelectedRow()).get_Psi_w();
            } else {
                final_psi_w = overload.set_psi_w(Variable_globeal_kotevny_usek.get(Table_kotevne_useky.getSelectedRow()).get_uroven_spolahlivosti_porcislo(), Variable_Bi); // set from table
            }      
            
            // overload class - first step
            Overload_variables Overload = new Overload_variables(Conductor,
                                                                Conductor.get_m()*9.80665,
                                                                Variable_hustota_namrazy,
                                                                Variable_Klc,
                                                                
                                                                Variable_Kh, 
                                                                Variable_Ir50,
                                                                Variable_char_terenu_Kr, 
                                                                Variable_char_terenu_Zo, 
                                                                Variable_V_mean_0, 
                                                                Variable_Cdir,
                                                                Variable_Co, 
                                                                1.,
                                                                Variable_uroven_spolahlivosti_Yw,
                                                                Variable_uroven_spolahlivosti_Yi,
                                                                Variable_uroven_spolahlivosti_Wi, 
                                                                final_psi_w,
                                                                Variable_Bi, 
                                                                3, 
                                                                0, 
                                                                1.25,
                                                                Variable_Ccl,
                                                                Variable_Hc_mean);

           // setting variables into overload vlass 
           overload.set_all_variables(Overload,Variable_Ai_array);
           // compute overloads
           overload.compute();
    
          //parsing value to array list zatazenia a prezataniza 

          DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
               otherSymbols.setDecimalSeparator('.');
               DecimalFormat df = new DecimalFormat("###.###",otherSymbols);
          
           TextField_pretazenia_stav5.setText(df.format(overload.z_I));
            TextField_pretazenia_stav6.setText(df.format(overload.z_W));
             TextField_pretazenia_stav7.setText(df.format(overload.z_Iw));
              TextField_pretazenia_stav8.setText(df.format(overload.z_iW));
           
           
        }catch(NullPointerException a){Swriter("nemam AI");};
        
    }}
  
    public void color_the_percento_field_to_orange(){
     vyp_percento1_sigma.setForeground(Color.ORANGE);
     vyp_percento2_sigma.setForeground(Color.ORANGE);
     vyp_percento3_sigma.setForeground(Color.ORANGE);
     vyp_percento4_sigma.setForeground(Color.ORANGE);
    }

    private static class Button_Icon_save_results {

        private static void setToolTipText(String language_label) {
           
        }

        public Button_Icon_save_results() {
        }
    }
  
  }

  

//Classes

class header_pdf extends javax.swing.JFrame{
    private static String PDF_VAR_vypocet_podla_normy = "";
    private static String PDF_VAR_nadpis_pre_prechodne = "";
    private static String PDF_VAR_Stavba = "";
    private static String PDF_VAR_Stavba1 = "";
    private static String PDF_VAR_SO_PS = "";
    private static String PDF_VAR_SO_PS1 = "";
    private static String PDF_VAR_Archivne_cislo = "";
    private static String PDF_VAR_vypracoval = "";
    private static String PDF_VAR_dátum = "";
    private static int PDF_VAR_cislovanie_stran_od = 1;
    //constructor
    /**
     * Create header for PDf file
     * @param a PDF_VAR_vypocet_podla_normy (string)
     * @param b PDF_VAR_nadpis_pre_prechodne (string)
     * @param c PDF_VAR_Stavba (string)
     * @param d PDF_VAR_Stavba (string)
     * @param e PDF_VAR_SO_PS (string)
     * @param f PDF_VAR_SO_PS (string)
     * @param g PDF_VAR_Archivne_cisl (string)
     * @param h PDF_VAR_vypracoval (string)
     * @param i PDF_VAR_dátum (string)
     * @param j PDF_VAR_cislovanie_stran_od (int)
     */
    header_pdf(String a, String b,String c, String d,String e,String f,String g,String h,String i, int j) {
        PDF_VAR_vypocet_podla_normy = a;
        PDF_VAR_nadpis_pre_prechodne = b;
        PDF_VAR_Stavba=c;
        PDF_VAR_Stavba1=d;
        PDF_VAR_SO_PS=e;
        PDF_VAR_SO_PS1=f;
        PDF_VAR_Archivne_cislo=g;
        PDF_VAR_vypracoval=h;
        PDF_VAR_dátum=i;
        PDF_VAR_cislovanie_stran_od=j;
    }
    /**
     * 
     * @return string PDF_VAR_vypocet_podla_normy
     */
     String vypocet_podla_normy_getter(){
        return PDF_VAR_vypocet_podla_normy;
    }   
    /**
     * 
     * @return string PDF_VAR_nadpis_pre_prechodne
     */
     String nadpis_pre_prechodne(){
        return PDF_VAR_nadpis_pre_prechodne;
    }   
    /**
     * 
     * @return string PDF_VAR_Stavba
     */
     String Stavba(){
        return PDF_VAR_Stavba;
    }  
      /**
     * 
     * @return string PDF_VAR_Stavba1
     */
     String Stavba1(){
        return PDF_VAR_Stavba1;
    }  
    /**
     * 
     * @return string PDF_VAR_SO_PS
     */
     String SO_PS(){
        return PDF_VAR_SO_PS;
    }
    /**
     * 
     * @return string PDF_VAR_SO_PS1
     */
     String SO_PS1(){
        return PDF_VAR_SO_PS1;
    }   
    /**
     * 
     * @return string PDF_VAR_Archivne_cislo
     */
     String Archivne_cislo(){
        return PDF_VAR_Archivne_cislo;
    }   
    /**
     * 
     * @return string PDF_VAR_vypracoval
     */
     String vypracoval(){
        return PDF_VAR_vypracoval;
    }   
     /**
     * 
     * @return string PDF_VAR_dátum
     */
     String dátum(){
        return PDF_VAR_dátum;
    }   
     /**
     * 
     * @return int PDF_VAR_cislovanie_stran_od
     */
     int cislovanie_stran_od(){
        return PDF_VAR_cislovanie_stran_od;
    }
}

class kotevnyUsek extends javax.swing.JFrame{
    
    private  String name;  
    private  int conductor_porcislo;
    private  String conductor_name; 
    private  int vetrova_oblast_porcislo;
    private  int char_terenu_porcislo;
    private  int uroven_spolahlivosti_porcislo;
    private  int uroven_spolahlivosti_stav_porcislo;
    private  String namrazova_oblast;
    private  int typ_namrazy_porcislo;   
    private  double RTS_over;
    private  double zakladne_mech_napatie_lana_pre_minus5_over;
    private  double maximalne_zataz_lana_podiel_z_RTS_over;
    private  double g_c_over;
    private  double ro_I_over;
    private  double K_lc_over;
    private  double K_h_over;
    private  double I_R50_over;
    private  double k_r_over;
    private  double z_0_over;
    private  double V_mean_over;
    private  double c_dir_over;
    private  double c_0_over;
    private  double C_c_over;
    private  double gama_w_over;
    private  double gama_I_over;
    private  double Psi_I_over;
    private  double Psi_w_over;
    private  double B_I_over;
    private  double k_p_over;
    private  double RR_over;
    private  double ro_over;
    private  double C_cl_over;
    private  double h_c_mean_over;
    private  double h_c_mean_window_vypocitana_over;
    private  double h_c_mean_window_vlastna_over;
    private  double str_rozpatie_over;
    private  double[] hodnoty_namrazove_oblasti_over;
    
    private  boolean str_vys_vodicov_radio_over;
    private  boolean CDIR_radio_over;
    private  boolean CO_radio_over;
    private  boolean Kcl_radio_over;
    private  boolean Kh_radio_over;
    private  boolean Bi_radio_over;
    
    private  double[] Ai_array_over;
    private  int[] vysledky_KPB_over;
    private  double[] DeltaHi_array_over;
    private  double[] Hi_array_over;
    private  double[] Hi_array_nmv_over;
    
                              private  double[] vysledky_tlaky6_over;
                              private  double[] vysledky_pretazenia5_over;
                              private  double[] vysledky_sigmaH_MT_over;
                              private  double[] vysledky_c_MT_over;
                              private  double[] vysledky_pretazenia_MT_over;
                              private  double[] vysledky_sily_MT_over;
                              private  double[][] vysledky_vid_priehyb_MT_over;
                              private  double[][] vysledky_per_podiel_sigma_over;
                              private  double[][] vysledky_tahy_over;
    
    //constructor
    
    
    
    kotevnyUsek(                String name_kot_useku,
                                int conductor,
                                String conductor_name,
                                int vetrova_oblast_porC,
                                int char_terenu_porC, 
                                int uroven_spolahlivosti_porC,
                                int uroven_spolahlivosti_stav_porC,
                                String namrazova_oblst,
                                int typ_namrazy_porC,
                                double RTS,
                                double zakl_mech_minus_5,
                                double max_zataz_lana_podiel_z_RTS,
                                double g_c, 
                                double ro_I, 
                                double K_lc,
                                double K_h,
                                double I_R50,
                                double k_r,
                                double z_0,
                                double V_mean,
                                double c_dir,
                                double c_0,
                                double C_c,
                                double gama_w,
                                double gama_I,
                                double Psi_I,
                                double Psi_w,
                                double B_I,
                                double k_p,
                                double RR,
                                double ro,
                                double C_cl,
                                double h_c_mean,
                                boolean str_vys_vodicov_radio,
                                boolean CDIR_radio,
                                boolean CO_radio,
                                boolean  Kcl_radio,
                                boolean Kh_radio,
                                boolean Bi_radio,
                                double[] A1_array,
                                double[] Delta_Hi_array,
                                double[] H1_array,
                                double[] H1_array_nmv,
                                double h_c_mean_window_vypocitana,
                                double h_c_mean_window_vlastna,
                                double str_rozpatie,
                                double[] hodnoty_namrazove_oblasti
            
//                                double[] vysledky_tlaky6,
//                                double[] vysledky_zatazenia5,
//                                double[] vysledky_sigmaH_MT,
//                                double[] vysledky_c_MT,
//                                double[] vysledky_pretazenia_MT,
//                                double[] vysledky_sily_MT,
//                                double[][] vysledky_vid_priehyb_MT,
//                                double[][] vysledky_per_podiel_sigma
                              //double[] vysledky_KPB
                                
    
    ){
        
        name=name_kot_useku;
        conductor_porcislo = conductor;
        this.conductor_name = conductor_name;
        vetrova_oblast_porcislo=vetrova_oblast_porC;
        char_terenu_porcislo=char_terenu_porC;
        uroven_spolahlivosti_porcislo=uroven_spolahlivosti_porC;
        uroven_spolahlivosti_stav_porcislo=uroven_spolahlivosti_stav_porC;
        namrazova_oblast=namrazova_oblst;
        typ_namrazy_porcislo=typ_namrazy_porC;
        RTS_over=RTS;
        zakladne_mech_napatie_lana_pre_minus5_over= zakl_mech_minus_5;
        maximalne_zataz_lana_podiel_z_RTS_over = max_zataz_lana_podiel_z_RTS;
        g_c_over = g_c;
        ro_I_over = ro_I;
        K_lc_over = K_lc;
        K_h_over = K_h;
        I_R50_over = I_R50;
        k_r_over = k_r;
        z_0_over = z_0;
        V_mean_over = V_mean;
        c_dir_over = c_dir;
        c_0_over = c_0;
        C_c_over = C_c;
        gama_w_over = gama_w;
        gama_I_over = gama_I;
        Psi_I_over = Psi_I;
        Psi_w_over = Psi_w;
        B_I_over = B_I;
        k_p_over = k_p;
        RR_over = RR;
        ro_over = ro;
        C_cl_over = C_cl;
        h_c_mean_over = h_c_mean;
        str_vys_vodicov_radio_over = str_vys_vodicov_radio;
        CDIR_radio_over=CDIR_radio;
        CO_radio_over=CO_radio;
        Kcl_radio_over=Kcl_radio;
        Kh_radio_over= Kh_radio;
        Bi_radio_over=Bi_radio;
        Ai_array_over=A1_array;
        DeltaHi_array_over= Delta_Hi_array;
        Hi_array_over= H1_array;
        Hi_array_nmv_over= H1_array_nmv;
        h_c_mean_window_vypocitana_over= h_c_mean_window_vypocitana;
        h_c_mean_window_vlastna_over= h_c_mean_window_vlastna;
        str_rozpatie_over=str_rozpatie;
        hodnoty_namrazove_oblasti_over=hodnoty_namrazove_oblasti;
    }
    
 
    
    public String get_name(){
        return name;
    }
    
    public int get_conductor_number(){
        return conductor_porcislo;
    }
     public String get_conductor_name(){
        return this.conductor_name;
    }
    public int get_vetrova_oblast_porcislo(){
        return vetrova_oblast_porcislo;
    }
    public int get_char_terenu_porcislo(){
        return char_terenu_porcislo;
    }
    public int get_uroven_spolahlivosti_porcislo(){
        return uroven_spolahlivosti_porcislo;
    }
    public int get_uroven_spolahlivosti_stav_porcislo(){
        return uroven_spolahlivosti_stav_porcislo;
    }
    public String get_namrazova_oblast_string(){
        return namrazova_oblast;
    }
    public int get_typ_namrazy_porcislo(){
        return typ_namrazy_porcislo;
    }
    public double get_RTS_over(){
        return RTS_over;
    }
    public double get_zakladne_mech_napatie_lana_pre_minus5_over(){
        return zakladne_mech_napatie_lana_pre_minus5_over;
    }
    public double get_maximalne_zataz_lana_podiel_z_RTS_over(){
        return maximalne_zataz_lana_podiel_z_RTS_over;
    }
    public double get_c_dir(){
        return c_dir_over;
    }
    
    public double get_g_c(){
        return g_c_over;
    }
    
    public double get_ro_I(){
        return ro_I_over;
    }
    
    public double get_K_lc(){
        return K_lc_over;
    }
    
    public double get_K_h(){
        return K_h_over;
    }
    
    public double get_I_R50(){
        return I_R50_over;
    }
    
    public double get_k_r(){
        return k_r_over;
    }
    
    public double get_z_0(){
        return z_0_over;
    }
    
    public double get_V_mean(){
        return V_mean_over;
    }
    
    public double get_c_0(){
        return c_0_over;
    }
    
    public double get_C_c(){
        return C_c_over;
    }
    
    public double get_gama_w(){
        return gama_w_over;
    }
    
    public double get_gama_I(){
        return gama_I_over;
    }
    
    public double get_Psi_I(){
        return Psi_I_over;
    }
    
    public double get_Psi_w(){
        return Psi_w_over;
    }
    
    public double get_B_I(){
        return B_I_over;
    }
    
    public double get_k_p(){
        return k_p_over;
    }
    
    public double get_RR(){
        return RR_over;
    }
    
    public double get_ro(){
        return ro_over;
    }
    
    public double get_C_cl(){
        return C_cl_over;
    }
   
    public double get_h_c_mean(){
        return h_c_mean_over;
    }
    public double get_h_c_mean_window_vypocitana(){
        return h_c_mean_window_vypocitana_over;
    }
    public double get_h_c_mean_window_vlastna(){
        return h_c_mean_window_vlastna_over;
    }
    public double get_str_rozpatie(){
        return str_rozpatie_over;
    }
     /**
     * 
     * @return True vypocitana / False Vlastna
     */
    public boolean get_str_vys_vodicov_radio(){
        return str_vys_vodicov_radio_over;
    }
     /**
     * 
     * @return True 1 / False Vlastna
     */
    public boolean get_CDIR_radio(){
        return CDIR_radio_over;
    }
     /**
     * 
     * @return True 1 / False Vlastna
     */
    public boolean get_CO_radio(){
        return CO_radio_over;
    }
     /**
     * 
     * @return True 1 / False Vlastna
     */
    public boolean get_Kcl_radio(){
        return Kcl_radio_over;
    }
     /**
     * 
     * @return True 1 / False Vlastna
     */
    public boolean get_Kh_radio(){
        return Kh_radio_over;
    }
     /**
     * 
     * @return True 0.656 / False Vlastna basic 0.707
     */
    public boolean get_Bi_radio(){
        return Bi_radio_over;
    }
    public double[] get_Ai_array(){
        return Ai_array_over;
    }
    public double[] get_DeltaHi_array(){
        return DeltaHi_array_over;
    }
    public double[] get_Hi_array(){
        return Hi_array_over;
    }
     public int[] get_vysledky_KPB(){
        return vysledky_KPB_over;
    }
    
    public double[] get_Hi_array_nvm(){
        return Hi_array_nmv_over;
    }
     public double[] get_hodnotynamrazove_oblast(){
        return hodnoty_namrazove_oblasti_over;
    }
   
    public double[] get_vysledky_tlaky6(){
        return vysledky_tlaky6_over;
    }
    public double[] get_vysledky_pretazenia5(){
        return vysledky_pretazenia5_over;
    }
    public double[] get_vysledky_sigmaH_MT(){
        return vysledky_sigmaH_MT_over;
    }
    public double[] get_vysledky_c_MT(){
        return vysledky_c_MT_over;
    }
    public double[] get_vysledky_pretazenia_MT(){
        return vysledky_pretazenia_MT_over;
    }
    public double[] get_vysledky_sily_MT(){
        return vysledky_sily_MT_over;
    }
    public double[][] get_vysledky_vid_priehyb_MT(){
        return vysledky_vid_priehyb_MT_over;
    }
    
     public double[][] get_vysledky_per_podiel_sigma(){
        return vysledky_per_podiel_sigma_over;
    }
    
     public double[][] get_tahy(){
        return vysledky_tahy_over;
    }
    
     
    public void set_name(String name_kot_useku){
        name=name_kot_useku;
    }
    
    public void set_conductor_number(int conductor){
        conductor_porcislo=conductor;
    }
    public void set_conductor_name(String conductor_name){
        this.conductor_name=conductor_name;
    }
    
    public void set_vetrova_oblast_porcislo(int vetrova_oblast_porC){
        vetrova_oblast_porcislo=vetrova_oblast_porC;
    }
    public void set_char_terenu_porcislo(int char_terenu_porC){
        char_terenu_porcislo=char_terenu_porC;
    }
    public void set_uroven_spolahlivosti_porcislo(int uroven_spolahlivosti_porC){
        uroven_spolahlivosti_porcislo=uroven_spolahlivosti_porC;
    }
    public void set_uroven_spolahlivosti_stav_porcislo(int uroven_spolahlivosti_stav_porC){
        uroven_spolahlivosti_stav_porcislo=uroven_spolahlivosti_stav_porC;
    }
    public void set_namrazova_oblast_string(String namrazova_oblst){
        namrazova_oblast=namrazova_oblst;
    }
    public void set_typ_namrazy_porcislo(int typ_namrazy_porC){
        typ_namrazy_porcislo=typ_namrazy_porC;
    }
    public void set_RTS_over(double RTS){
        RTS_over=RTS;
    }
    public void set_zakladne_mech_napatie_lana_pre_minus5_over(double zakl_mech_minus_5){
         zakladne_mech_napatie_lana_pre_minus5_over= zakl_mech_minus_5;
    }
    public void set_maximalne_zataz_lana_podiel_z_RTS_over(double max_zataz_lana_podiel_z_RTS){
        maximalne_zataz_lana_podiel_z_RTS_over = max_zataz_lana_podiel_z_RTS;
    }
    public void set_c_dir(double c_dir){
        c_dir_over = c_dir;
    }
    
    public void set_g_c(double g_c){
        g_c_over = g_c;
    }
    
    public void set_ro_I(double ro_I){
        ro_I_over = ro_I;
    }
    
    public void set_K_lc(double K_lc){
        K_lc_over = K_lc;
    }
    
    public void set_K_h(double K_h){
       K_h_over = K_h;
    }
    
    public void set_I_R50(double I_R50){
        I_R50_over = I_R50;
    }
    
    public void set_k_r(double k_r){
        k_r_over = k_r;
    }
    
    public void set_z_0(double z_0){
        z_0_over = z_0;
    }
    
    public void set_V_mean(double V_mean){
        V_mean_over = V_mean;
    }
    
    public void set_c_0(double c_0){
        c_0_over = c_0;
    }
    
    public void set_C_c(double C_c){
        C_c_over = C_c;
    }
    
    public void set_gama_w(double gama_w){
        gama_w_over = gama_w;
    }
    
    public void set_gama_I(double gama_I){
        gama_I_over = gama_I;
    }
    
    public void set_Psi_I(double Psi_I){
        Psi_I_over = Psi_I;
    }
    
    public void set_Psi_w(double Psi_w){
        Psi_w_over = Psi_w;
    }
    
    public void set_B_I(double B_I){
        B_I_over = B_I;
    }
    
    public void set_k_p(double k_p){
        k_p_over = k_p;
    }
    
    public void set_RR(double RR){
        RR_over = RR;
    }
    
    public void set_ro(double ro){
        ro_over = ro;
    }
    
    public void set_C_cl(double C_cl){
        C_cl_over = C_cl;
    }
   
    public void set_h_c_mean(double h_c_mean){
        h_c_mean_over = h_c_mean;
    }
    public void set_h_c_mean_window_vypocitana(double h_c_mean_vypocitana){
        h_c_mean_window_vypocitana_over = h_c_mean_vypocitana;
    }
    public void set_h_c_mean_window_vlastna(double h_c_mean_vlastna){
        h_c_mean_window_vlastna_over = h_c_mean_vlastna;
    }
    public void set_str_rozpatie(double str_rozpatie){
        str_rozpatie_over = str_rozpatie;
    }
     /**
     * 
     * @return True vypocitana / False Vlastna
     */
    public void set_str_vys_vodicov_radio(boolean str_vys_vodicov_radio){
        str_vys_vodicov_radio_over = str_vys_vodicov_radio;
    }
     /**
     * 
     * @return True 1 / False Vlastna
     */
    public void set_CDIR_radio(boolean CDIR_radio){
        CDIR_radio_over=CDIR_radio;
    }
     /**
     * 
     * @return True 1 / False Vlastna
     */
    public void set_CO_radio(boolean CO_radio){
        CO_radio_over=CO_radio;
    }
     /**
     * 
     * @return True 1 / False Vlastna
     */
    public void set_Kcl_radio(boolean  Kcl_radio){
        Kcl_radio_over=Kcl_radio;
    }
     /**
     * 
     * @return True 1 / False Vlastna
     */
    public void set_Kh_radio(boolean Kh_radio){
        Kh_radio_over= Kh_radio;
    }
     /**
     * 
     * @return True 0.656 / False Vlastna basic 0.707
     */
    public void set_Bi_radio(boolean Bi_radio){
        Bi_radio_over=Bi_radio;
    }
    public void set_Ai_array(double[] A1_array){
        Ai_array_over=A1_array;
    }
     public void set_vysledky_KPB(int[] vysledky_KPB){
        vysledky_KPB_over=vysledky_KPB;
    }
    
    public void set_hodnoty_namrazove_oblasti(double[] hodnoty_namrazove_oblasti){
        hodnoty_namrazove_oblasti_over=hodnoty_namrazove_oblasti;
    }
    
    public void set_DeltaHi_array(double[] Delta_Hi_array){
        DeltaHi_array_over= Delta_Hi_array;
    }
    public void set_Hi_array(double[] H1_array){
        Hi_array_over= H1_array;
    }
    public void set_Hi_array_nvm(double[] H1_array_nvm){
        Hi_array_nmv_over= H1_array_nvm;
    }
     public void set_vysledky_tlaky6(double[] vysledky_tlaky6){
        vysledky_tlaky6_over= vysledky_tlaky6;
    }
      public void set_vysledky_pretazenia6(double[] vysledky_pretazenia4){
        vysledky_pretazenia5_over= vysledky_pretazenia4;
    }
        public void set_vysledky_sigmaH_MT(double[] vysledky_sigmaH_MT){
        vysledky_sigmaH_MT_over= vysledky_sigmaH_MT;
    }
        public void set_vysledky_c_MT(double[] vysledky_c_MT){
        vysledky_c_MT_over= vysledky_c_MT;
    }
        public void set_vysledky_pretazenia_MT(double[] vysledky_pretazenia_MT){
        vysledky_pretazenia_MT_over= vysledky_pretazenia_MT;
    }
            public void set_vysledky_sily_MT(double[] vysledky_sily_MT){
       vysledky_sily_MT_over= vysledky_sily_MT;
    }
       public void set_vysledky_vid_priehyb_M(double[][] vysledky_vid_priehyb_MT){
       vysledky_vid_priehyb_MT_over= vysledky_vid_priehyb_MT;
    }        
        public void set_per_podiel_sigma(double[][] vysledky_per_podiel_sigma){
       vysledky_per_podiel_sigma_over= vysledky_per_podiel_sigma;
    }        
  
        public void set_tahy(double[][] vysledky_tahy){
       vysledky_tahy_over= vysledky_tahy;
    }        
  
       
       

}


class projekt_global_variables extends javax.swing.JFrame{
    
      private boolean mid_span_option_over;
      private boolean KPB_typ_terenu_over;
      private int KPB_combobo_number_over;
      private int tables_number123_over;
      private double tables_prechodne_over;
      private double tables_konecne_over;
      private double[] teploty_MT_over;
      private double[] pretazenia_MT_over;
      private boolean pretazenia_vypocitane_over;
      private header_pdf hlavicka_over;
    
    
    
    
projekt_global_variables(
      boolean mid_span_option,
      boolean KPB_typ_terenu,
      int KPB_combobo_number,
      int tables_number123,
      double tables_prechodne,
      double tables_konecne,
      double[] teploty_MT,
      double[] pretazenia_MT,
      boolean pretazenia_vypocitane,
      header_pdf hlavicka
        
){
      mid_span_option_over=mid_span_option;
       KPB_typ_terenu_over=KPB_typ_terenu;
       KPB_combobo_number_over=KPB_combobo_number;
       tables_number123_over=tables_number123;
       tables_prechodne_over=tables_prechodne;
       tables_konecne_over=tables_konecne;
       teploty_MT_over=teploty_MT;
       pretazenia_MT_over=pretazenia_MT;
       pretazenia_vypocitane_over=pretazenia_vypocitane;
       hlavicka_over=hlavicka;   
}    
      public boolean get_mid_span_option(){
        return mid_span_option_over;
    }
    public boolean get_KPB_typ_terenu(){
        return KPB_typ_terenu_over;
    }
    public int get_KPB_combobo_number(){
        return KPB_combobo_number_over;
    }
    public int get_tables_number123(){
        return tables_number123_over;
    }
    public double get_tables_prechodne(){
        return tables_prechodne_over;
    }
    public double get_tables_konecne(){
        return tables_konecne_over;
    }
    public double[] get_teploty_MT(){
        return teploty_MT_over;
    }
    public double[] get_pretazenia_MT(){
        return pretazenia_MT_over;
    }
    public boolean get_pretazenia_vypocitane(){
        return pretazenia_vypocitane_over;
    }
    public header_pdf get_hlavicka(){
        return hlavicka_over;
    }
    
    
    public void set_mid_span_option(boolean mid_span_option){
         mid_span_option_over=mid_span_option;
    }
    public void set_KPB_typ_terenu(boolean KPB_typ_terenu){
         KPB_typ_terenu_over=KPB_typ_terenu;
    }
    public void set_KPB_combobo_number(int KPB_combobo_number){
         KPB_combobo_number_over=KPB_combobo_number;
    }
    public void  set_tables_number123(int tables_number123){
         tables_number123_over=tables_number123;
    }
    public void  set_tables_prechodne(double tables_prechodne){
         tables_prechodne_over=tables_prechodne;
    }
    public void   set_tables_konecne(double tables_konecne ){
         tables_konecne_over=tables_konecne;
    }
    public void  set_teploty_MT(double[] teploty_MT){
         teploty_MT_over=teploty_MT;
    }
    public void  set_pretazenia_MT(double[] pretazenia_MT){
         pretazenia_MT_over=pretazenia_MT;
    }
    public void  set_pretazenia_vypocitane(boolean pretazenia_vypocitane){
         pretazenia_vypocitane_over=pretazenia_vypocitane;
    }
    public void  set_hlavicka(header_pdf hlavicka ){
         hlavicka_over=hlavicka;
    }
    
}

 class TextFieldCellEditor extends JTextField implements TableCellEditor
{
    private CellEditorListener  cellEditorListener  = null;

    private boolean             isInteger           = false;
    private Object              oldValue;

    // Start editing
    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj, boolean isSelected, int row, int column)
    {
        Color color2 = DefaultLookup.getColor(this, ui, "Table.alternateRowColor");
        super.setBackground(color2 != null && (row & 1) == 1? color2 : table.getBackground());
        super.setForeground(table.getForeground());
        super.setBorder(DefaultLookup.getBorder(this, ui, "Table.focusCellHighlightBorder"));

        super.setText(obj.toString());

        if (isSelected) {
    this.selectAll();
}
        
        isInteger = obj instanceof Integer;
        if (isInteger)
        {
            super.setHorizontalAlignment(SwingConstants.RIGHT);
            oldValue = obj;
        }

        // SwingUtilities.invokeLater(new Runnable()
        // {
        // public void run()
        // {
        // TextFieldCellEditor.this.selectAll();
        // }
        // });

        return this;
    }

    // Retrieve e dited value
    @Override
    public Object getCellEditorValue()
    {
        if (isInteger)
        {
            // Try to convert to integer. If input is invalid, revert.
            try
            {
                return new Integer(super.getText());
            }
            catch (NumberFormatException e)
            {
                return oldValue;
            }
        }
        return super.getText();
    }

    @Override
    public boolean isCellEditable(EventObject e)
    {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject e)
    {
        return true;
    }

    @Override
    public boolean stopCellEditing()
    {
        cellEditorListener.editingStopped(new ChangeEvent(this));
        return true;
    }

    @Override
    public void cancelCellEditing()
    {
        cellEditorListener.editingCanceled(new ChangeEvent(this));
    }

    @Override
    public void addCellEditorListener(CellEditorListener celleditorlistener)
    {
        cellEditorListener = celleditorlistener;
    }

    @Override
    public void removeCellEditorListener(CellEditorListener celleditorlistener)
    {
        if (cellEditorListener == cellEditorListener) cellEditorListener = null;
    }
}

 class MyCellEditor extends DefaultCellEditor {

    private boolean keyTriggered;

    public MyCellEditor() {
        super(new JTextField());
        final JTextField textField = (JTextField) getComponent();
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (!keyTriggered) {
                            textField.selectAll();
                        }
                    }
                });
            }
        });
    }

    public void setKeyTriggered(boolean keyTriggered) {
        this.keyTriggered = keyTriggered;
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column) {
        final JTextField textField = (JTextField)
                super.getTableCellEditorComponent(table, value, isSelected, row, column);
        textField.selectAll();
        return textField;
    }
}