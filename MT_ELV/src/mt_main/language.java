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

/*86*/  language.SK.add("Zákl. mech. napätie lana pri -5°C");                 language.CZ.add("Ulozit cestu");          language.EN.add("Save path");
/*87*/  language.SK.add("Maximálne zaťaženie pre vodič");                     language.CZ.add("-");                     language.EN.add("-");

// main_frame amrazy tool tips

/*88*/  language.SK.add("Súčiniteľ aerodynamického odporu vodiča s námrazou");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*89*/  language.SK.add("Hustota námrazy");                     language.CZ.add("-");                     language.EN.add("-");
/*90*/  language.SK.add("Rated Tensile Strength (Matematická únosnost)");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");

// main_frame BigTable for rozpatia and so on

/*91*/  language.SK.add("Rozpätia [m]");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*92*/  language.SK.add("Výška m.n.m. [m]");                     language.CZ.add("-");                     language.EN.add("-");
/*93*/  language.SK.add("Výška stožiara [m]");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");

// main_frame BigTable for rozpatia and so on

/*94*/  language.SK.add("Stredná výška vodičov nad terénom");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*95*/  language.SK.add("Vypočítana");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*96*/  language.SK.add("Vlastná");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");

// main_frame tabulky cas

/*97*/  language.SK.add("Tabuľky");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*98*/  language.SK.add("Počiatočné");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*99*/  language.SK.add("Prechodné");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*100*/  language.SK.add("Konečné");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*101*/  language.SK.add("Roky");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");

// main_frame Klc, Kh, BI

/*102*/  language.SK.add("Klc =");                     language.CZ.add("Klc =");                     language.EN.add("Klc =");
/*103*/  language.SK.add("súčiniteľ miestnych podmienok (čl.4.5.1/SK.3)");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*104*/  language.SK.add("Kh =");                     language.CZ.add("Kh =");                     language.EN.add("Kh =");
/*105*/  language.SK.add("súčiniteľ výšky (čl.4.5.1/SK.3)");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*106*/  language.SK.add("Bl =");                     language.CZ.add("Bl =");                     language.EN.add("Bl =");
/*107*/  language.SK.add("súčiniteľ pre spolupôsobenie vetra a námrazy (čl. 4.6.6/SK/CZ)");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");

// main_frame vetrova oblast

/*108*/  language.SK.add("Vetrová oblasť");                     language.CZ.add("Klc =");                     language.EN.add("Klc =");
/*109*/  language.SK.add("Základná rýchlosť vetra");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*110*/  language.SK.add("Cdir =");                     language.CZ.add("Kh =");                     language.EN.add("Kh =");
/*111*/  language.SK.add("Súčiniteľ smerovosti vetra");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*112*/  language.SK.add("Co =");                     language.CZ.add("Bl =");                     language.EN.add("Bl =");
/*113*/  language.SK.add("Súčiniteľ orografie");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");

/*114*/  language.SK.add("I-SK");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*115*/  language.SK.add("II-SK");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*116*/  language.SK.add("III-SK");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*117*/  language.SK.add("IV-SK");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*118*/  language.SK.add("I-CZ");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*119*/  language.SK.add("II-CZ");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*120*/  language.SK.add("III-CZ");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*121*/  language.SK.add("IV-CZ");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*122*/  language.SK.add("V-CZ");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*123*/  language.SK.add("Volitelné");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");

/*124*/  language.SK.add("Vmean_O =");                     language.CZ.add("Vmean_O =");                     language.EN.add("Vmean_O =");

// main_frame Charakt_terenu
/*125*/  language.SK.add("Charakteristika terénu");                     language.CZ.add("Klc =");                     language.EN.add("Klc =");
/*126*/  language.SK.add("kr =");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*127*/  language.SK.add("z0 =");                     language.CZ.add("Kh =");                     language.EN.add("Kh =");
/*128*/  language.SK.add("Súčiniteľ terénu");                     language.CZ.add("Ulozit cestu");                     language.EN.add("Save path");
/*129*/  language.SK.add("Dĺžka drsnosti");                     language.CZ.add("Bl =");                     language.EN.add("Bl =");

/*130*/  language.SK.add("Kategória I");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*131*/  language.SK.add("Kategória II");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*132*/  language.SK.add("Kategória III");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*133*/  language.SK.add("Kategória IV");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*134*/  language.SK.add("Kategória V");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");

/*135*/  language.SK.add("Kategória I\n\rBúrlivé otvorené more, jazerá do vzdialenosti najmenej 5 km proti smeru vetra a rovná krajina bez prekážok.");                    
         language.CZ.add("Kategória I_blablablablablabl");                    
         language.EN.add("Kategória I_blablablablablabl");
         
/*136*/  language.SK.add("Kategória II\n\rPoľnohospodárska pôda s deliacimi živými plotmi, rozptýlené malé poľnohospodárske sídla, domy a stromy");                    
         language.CZ.add("Kategória II_blablablablablabl");                    
         language.EN.add("Kategória II_blablablablablabl");
         
/*137*/  language.SK.add("Kategória III\n\rPredmestské alebo priemyselné oblasti a trvale zalesnené územia.");                    
         language.CZ.add("Kategória III_blablablablablabl");                    
         language.EN.add("Kategória III_blablablablablabl");
         
/*138*/  language.SK.add("Kategória IV\n\rMestské oblasti, v ktorých je aspoň 15% povrchu pokrytého pozemnými stavbami s priemernou výškou > 15 m.");                    
         language.CZ.add("Kategória IV_blablablablablabl");                    
         language.EN.add("Kategória IV_blablablablablabl");
         
/*139*/  language.SK.add("Kategória V\n\rHornatý a viac členitý terén, kde sa vietor môže lokálne zosilňovať alebo zoslabovať.");                    
         language.CZ.add("Kategória V_blablablablablabl");                    
         language.EN.add("Kategória V_blablablablablabl");

         // main_frame uroven spolahlivosti
         
/*140*/  language.SK.add("Úroveň spoľahlivosti");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*141*/  language.SK.add("1 (50 rokov)");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*142*/  language.SK.add("2 (150 rokov)");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*143*/  language.SK.add("3 (500 rokov)");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*144*/  language.SK.add("Dočasná stavba do 3 dní");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*145*/  language.SK.add("Dočasná stavba do 3 mesiacov");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*146*/  language.SK.add("Dočasná stavba do 1 roka");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*147*/  language.SK.add("Vlastné hodnoty");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");

/*148*/  language.SK.add("Úroveň spoľahlivosti vlastné hodnoty");     language.CZ.add("I-SK");                     language.EN.add("I-SK");

/*149*/  language.SK.add("Čas návratu klimatickej udalosti  [rok] t =");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*150*/  language.SK.add("Parc. faktor. pre extrémny vietor [-]  Yw =");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*151*/  language.SK.add("Parc. faktor. pre extrémnu námrazu[-]  Yi =");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*152*/  language.SK.add("Parc. faktor. pre komb. vietor    [-]  Ww =");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*153*/  language.SK.add("Parc. faktor. pre. komb. námrazu  [-]  Wi =");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");

// main_frame str rozpatie

/*154*/  language.SK.add("Stredné rozpätie");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*155*/  language.SK.add("Klasicky bez prevíšení");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*156*/  language.SK.add("S prevíšeniami");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
         
// main_frame _status: teploty
/*157*/  language.SK.add("Teploty [°C]");                     language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*158*/  language.SK.add("Preťaženia");                       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*159*/  language.SK.add("Vypočítane");                       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*160*/  language.SK.add("Vlastné");                          language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*161*/  language.SK.add("Stredná ročná teplota");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*162*/  language.SK.add("Stav");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
  
 // kontrola pracovného bodu
/*163*/  language.SK.add("Kontrola pracovného bodu");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*164*/  language.SK.add("Typ terénu");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
  
/*165*/  language.SK.add("Typ I");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*166*/  language.SK.add("Typ II");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*167*/  language.SK.add("Typ III");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*168*/  language.SK.add("Typ IV");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
        
/*169*/  language.SK.add("Otvorený plochý terén bez stromov, bez prekážok, so snehovou prikrývkou, blízko vodných plôch alebo cez ne");                    
         language.CZ.add("Kategória II_blablablablablabl");                    
         language.EN.add("Kategória II_blablablablablabl");
         
/*170*/  language.SK.add("Otvorený plochý terén bez prekážok, bez snehu, napr. poľnohospodárska pôda bez akýchkoľvek prekážok");                    
         language.CZ.add("Kategória III_blablablablablabl");                    
         language.EN.add("Kategória III_blablablablablabl");
         
/*171*/  language.SK.add("Otvorený plochý alebo zvlnený terén s ojedinelými prekážkami, napr. otvorené pastviny alebo poľnohospodárska pôda s málo stromami, živými plotmi a inými bariérami");                    
         language.CZ.add("Kategória IV_blablablablablabl");                    
         language.EN.add("Kategória IV_blablablablablabl");
         
/*172*/  language.SK.add("Zastavaný terén so streomami a stavbami, napr. predmestia, malé mestá, zalesnené oblasti s krovinami, malé polia s krovinami, stromami a živými plotmi");                    
         language.CZ.add("Kategória V_blablablablablabl");                    
         language.EN.add("Kategória V_blablablablablabl");

/*173*/  language.SK.add("V čase rok po montáži");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*174*/  language.SK.add("V rovnakom čase ako prechodné tabuľky");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
             
// main_frame texty do pdf 

/*175*/  language.SK.add("Hlavička dokumentu");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*176*/  language.SK.add("Výpočet podľa normy");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*177*/  language.SK.add("Nadpis pre prechodné");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*178*/  language.SK.add("Stavba");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*179*/  language.SK.add("SO-PS");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*180*/  language.SK.add("Archívne číslo");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*181*/  language.SK.add("Číslovanie strán od");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*182*/  language.SK.add("Vypracoval");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*183*/  language.SK.add("Dátum");       language.CZ.add("I-SK");                     language.EN.add("I-SK");

// main_frame_pdf
/*184*/  language.SK.add("Počiatočné tabuľky");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*185*/  language.SK.add("Prechodné tabuľky");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*186*/  language.SK.add("Konečné tabuľky");       language.CZ.add("I-SK");                     language.EN.add("I-SK");

/*187*/  language.SK.add("Výpočet podľa normy");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*188*/  language.SK.add("Stavba");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*189*/  language.SK.add("Dátum");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*190*/  language.SK.add("Archívne číslo");       language.CZ.add("I-SK");                     language.EN.add("I-SK");

/*191*/  language.SK.add("PS-SO");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*192*/  language.SK.add("Vypracoval");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*193*/  language.SK.add("Strana");       language.CZ.add("I-SK");                     language.EN.add("I-SK");

/*194*/  language.SK.add("Typ lana");       language.CZ.add("I-SK");                     language.EN.add("I-SK");
/*195*/  language.SK.add("Parametre Lana");       language.CZ.add("I-SK");                     language.EN.add("I-SK");


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
