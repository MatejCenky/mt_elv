/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_main;

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
        
 /*1*/   language.SK.add("Súbor");                       language.CZ.add("Soubor");              language.EN.add("File");            //language String value  at position 
 /*2*/   language.SK.add("Nový");                        language.CZ.add("Nový");                language.EN.add("New");             //language String value  at position 
 /*3*/   language.SK.add("Načítať");                     language.CZ.add("Načítat");             language.EN.add("Load");            //language String value  at position 
 /*4*/   language.SK.add("Verzia 1.0");                  language.CZ.add("Verze 1.0");           language.EN.add("Version 1.0");     //language String value  at position 
 /*5*/   language.SK.add("Nastavenia exportu");          language.CZ.add("Nastavení exportu");   language.EN.add("Export settings"); 
 /*6*/   language.SK.add("Koniec");                      language.CZ.add("Konec");               language.EN.add("Exit"); 
 /*7*/   language.SK.add("Knižnica");                    language.CZ.add("Knihovna");            language.EN.add("Library");
 /*8*/   language.SK.add("Vodiče");                      language.CZ.add("Vodiče");              language.EN.add("Conductors");
 /*9*/   language.SK.add("Help");                        language.CZ.add("Help");                language.EN.add("Help");
 /*10*/  language.SK.add("MT3 Help");                    language.CZ.add("MT3 Help");            language.EN.add("MT3 Help");
 /*11*/  language.SK.add("Normy");                       language.CZ.add("Normy");               language.EN.add("Standards");
 /*12*/  language.SK.add("Matematika");                  language.CZ.add("Matematika");          language.EN.add("Math");
 /*13*/  language.SK.add("O programe");                  language.CZ.add("O programe");          language.EN.add("About");
//Help_Jdialog_Math
 /*14*/  language.SK.add("Matematický výpočet");         language.CZ.add("Matematickej výpočet");language.EN.add("Math calculation");
 /*15*/  language.SK.add("Stavová rovnica");             language.CZ.add("Stavová rovnice");     language.EN.add("State Equation");
 /*16*/  language.SK.add("Zaťaženia");                   language.CZ.add("Zatížení");            language.EN.add("Overloads");
 /*17*/  language.SK.add("Tečenie vodiča");              language.CZ.add("Tečení vodiče");       language.EN.add("Conductor creeping");
 /*18*/  language.SK.add("Vibračná ochrana");            language.CZ.add("Vibrační ochrana");    language.EN.add("Vibration protection");
//Help_Jdialog_About
 /*19*/  language.SK.add("O programe");                  language.CZ.add("O programe");          language.EN.add("About");
 /*20*/  language.SK.add("Zavri");                       language.CZ.add("Zavři");               language.EN.add("Close");
  
 /*21*/  language.SK.add("Lorem ipsum bla bla bla teple slova zuzu po slovensky ahoj ideme si zašukat ? ok podme ju ju ju hu hu hu kert na dftr vdvvfvdf fgd er dsds cxcs erzzu dkdk dfsdf d d ddddd dfsdf sdf sdf sdfsdf sdfsdf sdfetrbtrb ddsfdg ");  
         language.CZ.add("Lorem ipsum bla bla bla teple slova zuzu po cesky ahoj  vole to takle si zapichat ju ju ju hu hu hu kert na dftr vdvvfvdf fgd er dsds cxcs erzzu dkdk dfsdf d d ddddd dfsdf sdf sdf sdfsdf sdfsdf sdfetrbtrb ddsfdg "); 
         language.EN.add("Lorem ipsum bla bla bla teple slova zuzu po anglcky hello mother fucker wana fuck today? ju ju ju hu hu hu kert na dftr vdvvfvdf fgd er dsds cxcs erzzu dkdk dfsdf d d ddddd dfsdf sdf sdf sdfsdf sdfsdf sdfetrbtrb ddsfdg ");
//Conductors_Jdialog_main
 /*22*/  language.SK.add("Knižnica vodičov");            language.CZ.add("O programe");          language.EN.add("About");
 /*23*/  language.SK.add("Vodiče");                      language.CZ.add("Zavři");               language.EN.add("Close");
 /*24*/  language.SK.add("Nový");                        language.CZ.add("O programe");          language.EN.add("About");
 /*25*/  language.SK.add("Upraviť");                     language.CZ.add("Zavři");               language.EN.add("Close");
 /*26*/  language.SK.add("Odstrániť");                   language.CZ.add("O programe");          language.EN.add("About");
 /*27*/  language.SK.add("Odomknúť úpravy");             language.CZ.add("Zavři");               language.EN.add("Close");
 /*28*/  language.SK.add("Potvrdiť");                    language.CZ.add("Zavři");               language.EN.add("Close");
 /*29*/  language.SK.add("Databáza");                    language.CZ.add("O programe");          language.EN.add("About");
 /*30*/  language.SK.add("Načítať");                     language.CZ.add("Zavři");               language.EN.add("Close");
 /*31*/  language.SK.add("Vyberte súbor s databázou");   language.CZ.add("Zavři");               language.EN.add("Close");
 /*32*/  language.SK.add("txt subor (*.txt)");           language.CZ.add("txt soubor (*.txt)");               language.EN.add("txt files (*.txt)");
 
 //Conductors_Jdialog_main_JtextArea ( conductor info )
 
 /*33*/  language.SK.add("Priemer = ");                                                 language.CZ.add("O programe");          language.EN.add("About");
 /*34*/  language.SK.add("Prierez = ");                                                 language.CZ.add("Zavři");               language.EN.add("Close");
 /*35*/  language.SK.add("Jednotková hmotnosť = ");                                     language.CZ.add("O programe");          language.EN.add("About");
 /*36*/  language.SK.add("Modul pružnosti = ");                                         language.CZ.add("Zavři");               language.EN.add("Close");
 /*37*/  language.SK.add("Koeficient tepelnej rozťažnosti = ");                         language.CZ.add("O programe");          language.EN.add("About");
 /*38*/  language.SK.add("Matematická únosnosť = ");                                    language.CZ.add("Zavři");               language.EN.add("Close");
 /*39*/  language.SK.add("Pomer hmotnosti oceľovej duše k celkovej hmotnosti lana = "); language.CZ.add("Zavři");               language.EN.add("Close");
 
//Conductors_Jdialog_main_PASSWORD FOR EDITATION SAME EVERY LANGUAGE

/*40*/  language.SK.add("elektrovodMT3");               language.CZ.add("elektrovodMT3");               language.EN.add("elektrovodMT3");
/*41*/  language.SK.add("Správne heslo");                     language.CZ.add("Spravne");                     language.EN.add("elektrovodMT3");
/*42*/  language.SK.add("Nesprávne heslo");                     language.CZ.add("Spravne");                     language.EN.add("elektrovodMT3");
/*43*/  language.SK.add("Zadajte heslo");                     language.CZ.add("Spravne");                     language.EN.add("elektrovodMT3");

//Conductors_Jdialog_new conductor

/*44*/  language.SK.add("Nový vodič");               language.CZ.add("elektrovodMT3");               language.EN.add("elektrovodMT3");
/*45*/  language.SK.add("Vytvoriť");                     language.CZ.add("Spravne");                     language.EN.add("elektrovodMT3");
/*46*/  language.SK.add("Názov vodiča :");                     language.CZ.add("Spravne");                     language.EN.add("elektrovodMT3");
/*47*/  language.SK.add("Zadajte heslo");                     language.CZ.add("Spravne");                     language.EN.add("elektrovodMT3");


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
