/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_test;

import java.util.ArrayList;

/**
 *
 * @author xbendikj
 */
public class language {

/**
 * Function just add elements to the array list 
 * If once function runs and sets variable "inicializovane" true it never runs again
 */    
public static void constructor(){
              /*SK*/                /*CZ*/              /*EN*/   
 /*0*/   language.SK.add("MT3 software, SAG Elektrovod, autori Jozef Bendík & Matej Cenký 2016");  
         language.CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016"); 
         language.EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016");                       //language String value  at position 
        
 /*1*/   language.SK.add("Súbor");              language.CZ.add("Soubor");              language.EN.add("File");            //language String value  at position 
 /*2*/   language.SK.add("Nový");               language.CZ.add("Nový");                language.EN.add("New");             //language String value  at position 
 /*3*/   language.SK.add("Načítať");            language.CZ.add("Načítat");             language.EN.add("Load");            //language String value  at position 
 /*4*/   language.SK.add("Verzia 1.0");         language.CZ.add("Verze 1.0");           language.EN.add("Version 1.0");     //language String value  at position 
 /*5*/   language.SK.add("Nastavenia exportu"); language.CZ.add("Nastavení exportu");   language.EN.add("Export settings"); 
 /*6*/   language.SK.add("Koniec");             language.CZ.add("Konec");               language.EN.add("Exit"); 
 /*7*/   language.SK.add("Knižnica");           language.CZ.add("Knihovna");            language.EN.add("Library");
 /*8*/   language.SK.add("Vodiče");             language.CZ.add("Vodiče");              language.EN.add("Conductors");
 /*9*/   language.SK.add("Help");               language.CZ.add("Help");                language.EN.add("Help");
 /*10*/  language.SK.add("MT3 Help");           language.CZ.add("MT3 Help");            language.EN.add("MT3 Help");
 /*11*/  language.SK.add("Normy");              language.CZ.add("Normy");               language.EN.add("Standards");
 /*12*/  language.SK.add("Matematika");         language.CZ.add("Matematika");          language.EN.add("Math");
 /*12*/  language.SK.add("O programe");         language.CZ.add("O programe");          language.EN.add("About");
   inicializovane = true;
}
 
/**
 * Function returns on string label in set language
 * @param X  defines the language 1 Slovak, 2 Czech, 3 English
 * @param Y  defines the label position according the drawing, is starts from 0 
 * @return 
 */
public static String language_label(int X,int Y){
    
    if ( language.inicializovane == false){constructor();}
    
    String SlovoDaloSlovo = "empty";
    switch (X) {
        case 1:  
            SlovoDaloSlovo=language.SK.get(Y);
            break;
        case 2:
            SlovoDaloSlovo=language.CZ.get(Y);
            break;
        case 3:
            SlovoDaloSlovo=language.EN.get(Y);
            break;
    }
    return SlovoDaloSlovo;
}    

public static String language_label2(){
    
   
    
    String SlovoDaloSlovo = "empty";
    
    return SlovoDaloSlovo;
    } 
 private static  final ArrayList<String> SK= new ArrayList<>();
 private static final ArrayList<String> CZ= new ArrayList<>();
 private static final ArrayList<String> EN= new ArrayList<>();
 private static boolean inicializovane = false;
 
 
    
    
    
    
}
