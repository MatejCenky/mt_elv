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
 /*22*/  language.SK.add("Knižnica vodičov");            language.CZ.add("Knižnice vodičú");                language.EN.add("Conductor library");
 /*23*/  language.SK.add("Vodiče");                      language.CZ.add("Vodiče");                         language.EN.add("Conductors");
 /*24*/  language.SK.add("Nový");                        language.CZ.add("Nový");                           language.EN.add("New");
 /*25*/  language.SK.add("Upraviť");                     language.CZ.add("Upravit");                        language.EN.add("Change");
 /*26*/  language.SK.add("Odstrániť");                   language.CZ.add("Vodstránit");                     language.EN.add("Delete");
 /*27*/  language.SK.add("Odomknúť úpravy");             language.CZ.add("Vodomknout úpravy");              language.EN.add("Unlock changes");
 /*28*/  language.SK.add("Potvrdiť");                    language.CZ.add("Potrvdit");                       language.EN.add("Set");
 /*29*/  language.SK.add("Databáza");                    language.CZ.add("Databáze");                       language.EN.add("Database");
 /*30*/  language.SK.add("Načítať");                     language.CZ.add("Načítat");                        language.EN.add("Load");
 /*31*/  language.SK.add("Vyberte súbor s databázou vodičou");   language.CZ.add("Vyběrte soubor s databazí vodičú");      language.EN.add("Choose database file of conductors");
 /*32*/  language.SK.add("txt subor (*.txt)");           language.CZ.add("txt soubor (*.txt)");             language.EN.add("txt files (*.txt)");
 
 //Conductors_Jdialog_main_JtextArea ( conductor info )
 
 /*33*/  language.SK.add("Priemer = ");                                                 language.CZ.add("Prúměr = ");                         language.EN.add("Diameter = ");
 /*34*/  language.SK.add("Prierez = ");                                                 language.CZ.add("Prúrez = ");                         language.EN.add("Cross section = ");
 /*35*/  language.SK.add("Jednotková hmotnosť = ");                                     language.CZ.add("Jednotková hmotnost = ");            language.EN.add("Unit weight = ");
 /*36*/  language.SK.add("Modul pružnosti = ");                                         language.CZ.add("Modul pružnosti = ");                language.EN.add("Modul of flexibility = ");
 /*37*/  language.SK.add("Koeficient tepelnej rozťažnosti = ");                         language.CZ.add("Koeficient tepelní roztažnosti = "); language.EN.add("Koeficient of termal expension = ");
 /*38*/  language.SK.add("Matematická únosnosť = ");                                    language.CZ.add("Matematická únosnosť = ");           language.EN.add("Mathematical carring capacity = ");
 
 /*39*/  language.SK.add("Pomer hmotnosti oceľovej duše k celkovej hmotnosti lana = "); 
         language.CZ.add("Poměr hmotnosti oceľovej duše k celkové hmotnosti lana = ");               
         language.EN.add("Weight ratio of conductor steel core to whole conductor = ");
 
//Conductors_Jdialog_main_PASSWORD FOR EDITATION SAME EVERY LANGUAGE

/*40*/  language.SK.add("elektrovodMT3");               language.CZ.add("elektrovodMT3");               language.EN.add("elektrovodMT3");
/*41*/  language.SK.add("Správne heslo");               language.CZ.add("Správni heslo");               language.EN.add("Correct password");
/*42*/  language.SK.add("Nesprávne heslo");             language.CZ.add("Nesprávni heslo");             language.EN.add("Incorrect password");
/*43*/  language.SK.add("Zadajte heslo");               language.CZ.add("Zadejte heslo");               language.EN.add("Please set the password");

//Conductors_Jdialog_new conductor

/*44*/  language.SK.add("Nový vodič");               language.CZ.add("Novej vodič");               language.EN.add("New conductor");
/*45*/  language.SK.add("Vytvoriť");                 language.CZ.add("Vytvořit");                  language.EN.add("Create");
/*46*/  language.SK.add("Názov vodiča :");           language.CZ.add("Jméno vodiče :");            language.EN.add("Name of the conductor :");
/*47*/  language.SK.add("Zlá vstupná hodnota");      language.CZ.add("Zlá vstupní hodnota");       language.EN.add("Incorrect input");

//Conductors_Jdialog_change conductor

/*48*/  language.SK.add("Upraviť vodič");               language.CZ.add("Upravit vodič");               language.EN.add("Change conductor");
/*49*/  language.SK.add("Upraviť");                     language.CZ.add("Upravit");                     language.EN.add("Change");

//Conductors_Jdialog_main_save memory button 

/*50*/  language.SK.add("Uložiť cestu");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");


// Main_frame

/*51*/  language.SK.add("Zoznam kotevných úsekov");                            language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*52*/  language.SK.add("Kotevný úsek");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");

// Main_frame_tool_tip

/*53*/  language.SK.add("Uložiť projekt");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*54*/  language.SK.add("Uložiť projekt ako");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*55*/  language.SK.add("Uložiť výsledky projektu");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*56*/  language.SK.add("Nový koťevný úsek");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*57*/  language.SK.add("Zmazať koťevný úsek");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*58*/  language.SK.add("Spustiť výpočet");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*59*/  language.SK.add("Tlač do PDF");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");

// Main_frame_jdialog_create_kotevny_usek
/*60*/  language.SK.add("Nový koťevný úsek");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*61*/  language.SK.add("Vytvoriť");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*62*/  language.SK.add("Názov");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");

// Main_frame_tool_tip2

/*63*/  language.SK.add("Označiť všetky kotevné úseky");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*64*/  language.SK.add("Odznačiť všetky kotevné úseky");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");

// main_frame

/*65*/  language.SK.add("Lano");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*66*/  language.SK.add("RTS");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*67*/  language.SK.add("Námrazová oblasť");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");

// main_frame namrazova oblast

/*68*/  language.SK.add("Námrazová oblasť");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*69*/  language.SK.add("Označenie");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*70*/  language.SK.add("Ak");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*71*/  language.SK.add("Vlastná hodnota");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*72*/  language.SK.add("Vlastná definovanie námrazovej oblasti");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*73*/  language.SK.add("Použi");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*74*/  language.SK.add("Zrušiť");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");

/*75*/  language.SK.add("Použiť");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");

// main_frame druh námrazy

/*76*/  language.SK.add("Typ námrazy");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*77*/  language.SK.add("Mokrý sneh");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*78*/  language.SK.add("Ľadovica");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*79*/  language.SK.add("Ľahká inovať");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*80*/  language.SK.add("Ťažká inovať");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*81*/  language.SK.add("NNA-SK");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*82*/  language.SK.add("Voliteľná");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");

/*83*/  language.SK.add("Ccl = ");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*84*/  language.SK.add("\u03C1 = ");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*85*/  language.SK.add("-");                     language.CZ.add("-");                     language.EN.add("-");

// main_frame mech napatia a podiel z RTS

/*86*/  language.SK.add("Záklané mech. napätie lana pri -5°C");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*87*/  language.SK.add("Maximálne zaťaženie pre vodič");                     language.CZ.add("-");                     language.EN.add("-");

// main_frame amrazy tool tips

/*88*/  language.SK.add("Súčiniteľ aerodynamického odporu vodiča s námrazou");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*89*/  language.SK.add("Hustota námrazy");                     language.CZ.add("-");                     language.EN.add("-");
/*90*/  language.SK.add("Rated Tensile Strength (Matematická únosnost)");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");

// main_frame BigTable for rozpatia and so on

/*91*/  language.SK.add("Rozpätia [m]");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*92*/  language.SK.add("Výška m.n.m. [m]");                     language.CZ.add("-");                     language.EN.add("-");
/*93*/  language.SK.add("Výška stožiara [m]");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");


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
