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
 /*0*/   language.SK.add("MT3 software, SAG Elektrovod, autori Jozef Bendík & Matej Cenký 2016 - BETA - MAX 3 inštancie");  
         language.CZ.add("MT3 software, SAG Elektrovod, autoři Jozef Bendík & Matej Cenký 2016- BETA - MAX 3 inštance"); 
         language.EN.add("MT3 software, SAG Elektrovod, created by Jozef Bendík & Matej Cenký 2016 - BETA - MAX 3 instances");                       //language String value  at position 
        
 /*1*/   language.SK.add("Súbor");                       language.CZ.add("Soubor");              language.EN.add("File");            //language String value  at position 
 /*2*/   language.SK.add("Nový");                        language.CZ.add("Nový");                language.EN.add("New");             //language String value  at position 
 /*3*/   language.SK.add("Načítať");                     language.CZ.add("Načítat");             language.EN.add("Load");            //language String value  at position 
 /*4*/   language.SK.add("Verzia 1.0 Beta");             language.CZ.add("Verze 1.0 Beta");      language.EN.add("Version 1.0 Beta");     //language String value  at position 
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
  
 /*21*/  language.SK.add("MT3 software je program na výpočet montážnych tabuliek vonkajších elektrických vedení. Program a všetky je práva s ním spojené sú majetkom SAG Elektrovod a.s. Program je napísaný v programovacom jazyku JAVA. V prípade akýchkoľvek otázok sa prosím obrátťe na na vývojárov tohto programu Mateja Cenkého && Jozef Bendíka.");  
         language.CZ.add("MT3 software je program pro výpočet montážních tabulek venkovních elektrických vedení. Program a všechny je práva s ním spojené jsou majetkem SAG Elektrovod a.s. Program je napsán v programovacím jazyce JAVA. V případě jakýchkoliv dotazů se prosím obraťte na na vývojáře tohoto programu Matěje Čenkov && Jozef Bendíka."); 
         language.EN.add("MT3 is a software for calculation of the mechanical properties of overhead power lines. Program and all the rights associated with it are the property of SAG Elektrovod a.s. The program is written in Java language. If you have any questions, please contact the developer of the program Matej Cenký && Josef Bendík.");
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
 /*32*/  language.SK.add("MT3 subor (*.MT3)");           language.CZ.add("txt soubor (*.txt)");             language.EN.add("txt files (*.txt)");
 
 //Conductors_Jdialog_main_JtextArea ( conductor info )
 
 /*33*/  language.SK.add("Priemer = ");                                                 language.CZ.add("Průměr = ");                         language.EN.add("Diameter = ");
 /*34*/  language.SK.add("Prierez = ");                                                 language.CZ.add("Průřez = ");                         language.EN.add("Cross section = ");
 /*35*/  language.SK.add("Jednotková hmotnosť = ");                                     language.CZ.add("Jednotková hmotnost = ");            language.EN.add("Specific weight = ");
 /*36*/  language.SK.add("Modul pružnosti = ");                                         language.CZ.add("Modul pružnosti = ");                language.EN.add("Young Module = ");
 /*37*/  language.SK.add("Koeficient tepelnej rozťažnosti = ");                         language.CZ.add("Koeficient tepelní roztažnosti = "); language.EN.add("Thermal expansion coeff. = ");
 /*38*/  language.SK.add("Matematická únosnosť = ");                                    language.CZ.add("Matematická únosnosť = ");           language.EN.add("Rated Tensile Strength = ");
 
 /*39*/  language.SK.add("Pomer hmotnosti oceľovej duše k celkovej hmotnosti lana = "); 
         language.CZ.add("Poměr hmotnosti ocelové duše k celkové hmotnosti lana = ");               
         language.EN.add("Conductor steel core / whole conductor weight ratio = ");
 
//Conductors_Jdialog_main_PASSWORD FOR EDITATION SAME EVERY LANGUAGE

/*40*/  language.SK.add("elektrovodMT3");               language.CZ.add("elektrovodMT3");               language.EN.add("elektrovodMT3");
/*41*/  language.SK.add("Správne heslo");               language.CZ.add("Správní heslo");               language.EN.add("Correct password");
/*42*/  language.SK.add("Nesprávne heslo");             language.CZ.add("Nesprávní heslo");             language.EN.add("Incorrect password");
/*43*/  language.SK.add("Zadajte heslo");               language.CZ.add("Zadejte heslo");               language.EN.add("Set the password");

//Conductors_Jdialog_new conductor

/*44*/  language.SK.add("Nový vodič");               
        language.CZ.add("Nový vodič");                
        language.EN.add("New conductor");
        
/*45*/  language.SK.add("Vytvoriť");                 
        language.CZ.add("Vytvořit");                  
        language.EN.add("Create");
        
/*46*/  language.SK.add("Názov vodiča :");           
        language.CZ.add("Název vodiče :");            
        language.EN.add("Name of the conductor :");
        
/*47*/  language.SK.add("Zlá vstupná hodnota");      
        language.CZ.add("Zlá vstupní hodnota");       
        language.EN.add("Incorrect input");

//Conductors_Jdialog_change conductor

/*48*/  language.SK.add("Upraviť vodič");               
        language.CZ.add("Upravit vodič");               
        language.EN.add("Change conductor");
        
/*49*/  language.SK.add("Upraviť");                     
        language.CZ.add("Upravit");                     
        language.EN.add("Edit");

//Conductors_Jdialog_main_save memory button 


/*50*/  language.SK.add("Uložiť cestu");                     
        language.CZ.add("Ulozit cestu");                     
        language.EN.add("Save path");

// Main_frame
                    
/*51*/  language.SK.add("Zoznam kotevných úsekov");          
        language.CZ.add("Seznam kotěvných úseků");           
        language.EN.add("Tensioning section list");
        
/*52*/  language.SK.add("Kotevný úsek");                     
        language.CZ.add("Kotěvný úsek");                     
        language.EN.add("Tensioning section");

// Main_frame_tool_tip

/*53*/  language.SK.add("Uložiť projekt");                     
        language.CZ.add("Uložit projekt");                     
        language.EN.add("Save project");

/*54*/  language.SK.add("Uložiť projekt ako");                     
        language.CZ.add("Uložit projekt jako");                     
        language.EN.add("Save project as");
        
/*55*/  language.SK.add("Uložiť výsledky projektu");                     
        language.CZ.add("Uložit výsledky projektu");                     
        language.EN.add("Save project results");
        
/*56*/  language.SK.add("Nový kotevný úsek");                     
        language.CZ.add("Nový kotěvný úsek");                     
        language.EN.add("New tens. section");
        
/*57*/  language.SK.add("Zmazať kotevný úsek");                     
        language.CZ.add("Smazat kotěvný úsek");                     
        language.EN.add("Delete tens. section");
        
/*58*/  language.SK.add("Spustiť výpočet");                     
        language.CZ.add("Spustit výpočet");                     
        language.EN.add("Run calculation");
        
/*59*/  language.SK.add("Tlač do PDF");                     
        language.CZ.add("Tlač do PDF");                     
        language.EN.add("Print to PDF");


// Main_frame_jdialog_create_kotevny_usek
/*60*/  language.SK.add("Nový kotevný úsek");                     
        language.CZ.add("Nový kotěvný úsek");                     
        language.EN.add("New tens. section");

        
/*61*/  language.SK.add("Vytvoriť");                     
        language.CZ.add("Vytvořit");                     
        language.EN.add("Create");
        
/*62*/  language.SK.add("Názov");                     
        language.CZ.add("Název");                     
        language.EN.add("Name");

// Main_frame_tool_tip2

/*63*/  language.SK.add("Označiť všetky kotevné úseky");                     
        language.CZ.add("Označit všechny kot. úseky");
        language.EN.add("Select all tens. sections");
        
/*64*/  language.SK.add("Odznačiť všetky kotevné úseky");                     
        language.CZ.add("Odznačit všechny kotěvné úseky");                     
        language.EN.add("Deselect all tens. sections");

// main_frame

/*65*/  language.SK.add("Lano");                     
        language.CZ.add("Lano");                     
        language.EN.add("Conductor");
        
/*66*/  language.SK.add("RTS");                     
        language.CZ.add("RTS");                     
        language.EN.add("RTS");
        
/*67*/  language.SK.add("Námrazová oblasť");                     
        language.CZ.add("Námrazová oblast");                     
        language.EN.add("Icing area");

// main_frame namrazova oblast

/*68*/  language.SK.add("Námrazová oblasť");                     
        language.CZ.add("Námrazová oblast");                     
        language.EN.add("Icing area");
        
/*69*/  language.SK.add("Označenie");                     
        language.CZ.add("Označení");                     
        language.EN.add("Name");
        
/*70*/  language.SK.add("Ak");                     
        language.CZ.add("Ak");                     
        language.EN.add("If");
        
/*71*/  language.SK.add("Vlastná hodnota");                     
        language.CZ.add("Vlastní hodnota");                     
        language.EN.add("Specific value");
        
/*72*/  language.SK.add("Vlastné definovanie námrazovej oblasti");                     
        language.CZ.add("Vlastní definice námrazové oblasti");                     
        language.EN.add("Specific definition of icing area");
        
/*73*/  language.SK.add("Použi");                     
        language.CZ.add("Použij");                     
        language.EN.add("Use");
        
/*74*/  language.SK.add("Zrušiť");                     
        language.CZ.add("Zrušit");                     
        language.EN.add("Cancel");

/*75*/  language.SK.add("Použiť");                     
        language.CZ.add("Použiť");                     
        language.EN.add("Use");

// main_frame druh námrazy

/*76*/  language.SK.add("Typ námrazy");                     
        language.CZ.add("Typ námrazy");                     
        language.EN.add("Icing type");
        
/*77*/  language.SK.add("Mokrý sneh");                     
        language.CZ.add("Mokrý sněh");                     
        language.EN.add("Wet snow");
        
/*78*/  language.SK.add("Ľadovica");                     
        language.CZ.add("Ledovice");                     
        language.EN.add("Black ice");
        
/*79*/  language.SK.add("Ľahká inoväť");                     
        language.CZ.add("Lehká inovať");                     
        language.EN.add("Light frost");
        
/*80*/  language.SK.add("Ťažká inoväť");                     
        language.CZ.add("Těžká inovať");                     
        language.EN.add("Heavy frost");
        
/*81*/  language.SK.add("NNA-SK");                     
        language.CZ.add("NNA-SK");                     
        language.EN.add("NNA-SK");
        
/*82*/  language.SK.add("Voliteľná");                     
        language.CZ.add("Volitelná");                     
        language.EN.add("Custom");

/*83*/  language.SK.add("Ccl = ");                     
        language.CZ.add("Ccl = ");                     
        language.EN.add("Ccl = ");
        
/*84*/  language.SK.add("\u03C1 = ");                     
        language.CZ.add("\u03C1 = ");                     
        language.EN.add("\u03C1 = ");
        
/*85*/  language.SK.add("-");                     
        language.CZ.add("-");                     
        language.EN.add("-");

// main_frame mech napatia a podiel z RTS

/*86*/  language.SK.add("Zákl. mech. napätie lana pri -5°C");                 
        language.CZ.add("Zákl. mech. napětí lana při -5°C");          
        language.EN.add("Base cond. mech. stress at -5°C ");
        
/*87*/  language.SK.add("Maximálne zaťaženie vodiča");                     
        language.CZ.add("Maximální zatížení vodiče");                    
        language.EN.add("Maximum conductor load");

// main_frame amrazy tool tips

/*88*/  language.SK.add("Súčiniteľ aerodynamického odporu vodiča s námrazou");                     
        language.CZ.add("Součinitel aerodynamického odporu vodiče s námrazkem");                     
        language.EN.add("Aerodynamic resistance coefficient for conductor with ice");
        
/*89*/  language.SK.add("Hustota námrazy");                     
        language.CZ.add("Hustota námrazy");                     
        language.EN.add("Density of ice");
        
/*90*/  language.SK.add("Rated Tensile Strength (Matematická únosnosť)");                     
        language.CZ.add("Rated Tensile Strength (Matematická únosnost)");                     
        language.EN.add("Rated Tensile Strength");

// main_frame BigTable for rozpatia and so on

/*91*/  language.SK.add("Rozpätia [m]");                     
        language.CZ.add("Rozpětí [m]");                     
        language.EN.add("Spans [m]");
        
/*92*/  language.SK.add("Výška m.n.m. [m]");                     
        language.CZ.add("Výška m.n.m. [m]");                     
        language.EN.add("Height m.a.s.l.");
        
/*93*/  language.SK.add("Výška stožiara [m]");                     
        language.CZ.add("Výška stožáru [m]");                     
        language.EN.add("Tower height [m]");

// main_frame BigTable for rozpatia and so on

/*94*/  language.SK.add("Stredná výška vodičov nad terénom");                     
        language.CZ.add("Střední výška vodiče nad terénem");                     
        language.EN.add("Avg. cond. height above terrain");
        
/*95*/  language.SK.add("Vypočítaná");                    
        language.CZ.add("Spočtená");                     
        language.EN.add("Computed");
        
/*96*/  language.SK.add("Vlastná");                     
        language.CZ.add("Vlastní");                     
        language.EN.add("Own");

// main_frame tabulky cas

/*97*/  language.SK.add("Tabuľky");                     
        language.CZ.add("Tabulky");                     
        language.EN.add("Tables");
        
/*98*/  language.SK.add("Počiatočné a)");                     
        language.CZ.add("Počáteční a)");                     
        language.EN.add("Initial a)");
        
/*99*/  language.SK.add("Prechodné");                     
        language.CZ.add("Prechodní");                     
        language.EN.add("Transient");
        
/*100*/  language.SK.add("Konečné");                     
         language.CZ.add("Koneční");                     
         language.EN.add("Final");
         
/*101*/  language.SK.add("Roky");                     
        language.CZ.add("Roky");                     
        language.EN.add("Years");

// main_frame Klc, Kh, BI

/*102*/  language.SK.add("Klc =");                     
        language.CZ.add("Klc =");                     
        language.EN.add("Klc =");
        
/*103*/  language.SK.add("Súčiniteľ miestnych podmienok (čl.4.5.1/SK.3)");                     
        language.CZ.add("Součinitel místních podmínek (čl.4.5.1/SK.3)");                     
        language.EN.add("Local factors coefficient (čl.4.5.1/SK.3)");
        
/*104*/  language.SK.add("Kh =");                     
        language.CZ.add("Kh =");                     
        language.EN.add("Kh =");
        
/*105*/  language.SK.add("Súčiniteľ výšky (čl.4.5.1/SK.3)");                     
        language.CZ.add("Součinitel výšky (čl.4.5.1/SK.3)");                     
        language.EN.add("Height coeff. (čl.4.5.1/SK.3)");
        
/*106*/  language.SK.add("Bl =");                     
        language.CZ.add("Bl =");                     
        language.EN.add("Bl =");
        
/*107*/  language.SK.add("Súčiniteľ pre spolupôsobenie vetra a námrazy (čl. 4.6.6/SK/CZ)");                     
        language.CZ.add("Součinitel pro společné působení větra a námrazy (čl. 4.6.6/SK/CZ)");                     
        language.EN.add("Cummulative influence of wind and ice coeff. (čl. 4.6.6/SK/CZ)");

// main_frame vetrova oblast

/*108*/  language.SK.add("Vetrová oblasť");                    
        language.CZ.add("Větrová oblast");                     
        language.EN.add("Wind area");
        
/*109*/  language.SK.add("Základná rýchlosť vetra");                     
        language.CZ.add("Základní rychlost větra");                     
        language.EN.add("Base wind speed");
        
/*110*/  language.SK.add("Cdir =");                     
        language.CZ.add("Cdir =");                     
        language.EN.add("Cdir =");
        
/*111*/  language.SK.add("Súčiniteľ smerovosti vetra");                     
        language.CZ.add("Součinitel směrovosti větra");                     
        language.EN.add("Wind orientation coeff.");
        
/*112*/  language.SK.add("Co =");                     
        language.CZ.add("Co =");                     
        language.EN.add("Co =");
        
/*113*/  language.SK.add("Súčiniteľ orografie");                     
        language.CZ.add("Součinitel orografie");                     
        language.EN.add("Orography coeff.");

/*114*/  language.SK.add("I-SK");                     
        language.CZ.add("I-SK");                     
        language.EN.add("I-SK");
        
/*115*/  language.SK.add("II-SK");                     
        language.CZ.add("II-SK");                     
        language.EN.add("II-SK");
        
/*116*/  language.SK.add("III-SK");                     
        language.CZ.add("III-SK");                     
        language.EN.add("III-SK");
        
/*117*/  language.SK.add("IV-SK");                     
        language.CZ.add("IV-SK");                     
        language.EN.add("IV-SK");
        
/*118*/  language.SK.add("I-CZ");                     
        language.CZ.add("I-CZ");                     
        language.EN.add("I-CZ");
        
/*119*/  language.SK.add("II-CZ");                     
        language.CZ.add("II-CZ");                     
        language.EN.add("II-CZ");
        
/*120*/  language.SK.add("III-CZ");                     
        language.CZ.add("III-CZ");                     
        language.EN.add("III-CZ");
        
/*121*/  language.SK.add("IV-CZ");                     
        language.CZ.add("IV-CZ");                     
        language.EN.add("IV-CZ");
        
/*122*/  language.SK.add("V-CZ");                     
        language.CZ.add("V-CZ");                     
        language.EN.add("V-CZ");
        
/*123*/  language.SK.add("Volitelné");                     
        language.CZ.add("Volitelné");                     
        language.EN.add("Custom");

/*124*/  language.SK.add("Vmean_O =");                     
        language.CZ.add("Vmean_O =");                     
        language.EN.add("Vmean_O =");

// main_frame Charakt_terenu
/*125*/  language.SK.add("Charakteristika terénu");                     
        language.CZ.add("Charakteristika terénu");                     
        language.EN.add("Terrain type");
        
/*126*/  language.SK.add("kr =");                     
        language.CZ.add("kr =");                     
        language.EN.add("kr =");
        
/*127*/  language.SK.add("z0 =");                     
        language.CZ.add("z0 =");                    
        language.EN.add("z0 =");
        
/*128*/  language.SK.add("Súčiniteľ terénu");                    
        language.CZ.add("Součinitel terénu");                     
        language.EN.add("Terrain coeff.");
        
/*129*/  language.SK.add("Dĺžka drsnosti");                     
        language.CZ.add("Délka drsnosti");                     
        language.EN.add("Roughness length");

/*130*/  language.SK.add("Kategória I");                     
        language.CZ.add("Kategorie I");                     
        language.EN.add("Category I");
        
/*131*/  language.SK.add("Kategória II");                     
        language.CZ.add("Kategorie II");                     
        language.EN.add("Category II");
        
/*132*/  language.SK.add("Kategória III");                     
        language.CZ.add("Kategorie III");                     
        language.EN.add("Category III");
        
/*133*/  language.SK.add("Kategória IV");                     
        language.CZ.add("Kategorie IV");                     
        language.EN.add("Category IV");
        
/*134*/  language.SK.add("Kategória V");                     
        language.CZ.add("Kategorie V");                     
        language.EN.add("Category V");

/*135*/  language.SK.add("Kategória I\n\rBúrlivé otvorené more, jazerá do vzdialenosti najmenej 5 km proti smeru vetra a rovná krajina bez prekážok.");                    
         language.CZ.add("Kategória I\n\rBouřlivé otevřené moře, jezera do vzdálenosti nejméne 5 km proti směru větra a rovná krajina bez překážek.");                    
         language.EN.add("Category I\n\rStormy open sea, lakes at least 5 km away from the opposite side then wind orientation and landscape without obstacles.");
         
/*136*/  language.SK.add("Kategória II\n\rPoľnohospodárska pôda s deliacimi živými plotmi, rozptýlené malé poľnohospodárske sídla, domy a stromy.");                    
         language.CZ.add("Kategória II\n\rZemědělská půda s dělícími živými plotmi, rozptýlená malá polnohospodářská sídla, domy a stromy.");                    
         language.EN.add("Category II\n\rAgricultural land divided with hedges, distributed small agricultural settlements, houses and trees.");
         
/*137*/  language.SK.add("Kategória III\n\rPredmestské alebo priemyselné oblasti a trvale zalesnené územia.");                    
         language.CZ.add("Kategória III\n\rPředměstské nebo průmyslné oblasti a trvalo zalesněná území.");                    
         language.EN.add("Category III\n\rSuburban or industrial areas and permanently forested areas.");
         
/*138*/  language.SK.add("Kategória IV\n\rMestské oblasti, v ktorých je aspoň 15% povrchu pokrytého pozemnými stavbami s priemernou výškou > 15 m.");                    
         language.CZ.add("Kategória IV\n\rMěstské oblasti, ve kterých je alespoň 15% povrchu pokrytého pozemními stavbami s průmernou výškou > 15 m.");                    
         language.EN.add("Category IV\n\rUrban areas with at least 15% of surface covered with ground structures with height over 15 m.");
         
/*139*/  language.SK.add("Kategória V\n\rHornatý a viac členitý terén, kde sa vietor môže lokálne zosilňovať alebo zoslabovať.");                    
         language.CZ.add("Kategória V\n\rHornatý a více članitý terén, kde se vítr může lokálně zesilovat nebo zoslabovat.");                    
         language.EN.add("Category V\n\rHighland and more indented terrain, where is the possibilty of local increase or decrease of wind speed.");

         // main_frame uroven spolahlivosti
         
/*140*/  language.SK.add("Úroveň spoľahlivosti");                     
        language.CZ.add("Úroveň spolahlivosti");                     
        language.EN.add("Reliabilty level");
        
/*141*/  language.SK.add("1 (50 rokov)");                     
        language.CZ.add("1 (50 roků)");                     
        language.EN.add("1 (50 years)");
        
/*142*/  language.SK.add("2 (150 rokov)");                     
        language.CZ.add("2 (150 roků)");                     
        language.EN.add("2 (150 years)");
        
/*143*/  language.SK.add("3 (500 rokov)");                     
        language.CZ.add("3 (500 roků)");                     
        language.EN.add("3 (500 years)");
        
/*144*/  language.SK.add("Dočasná stavba < 3 dni");                     
        language.CZ.add("Dočasná stavba < 3 dny");                     
        language.EN.add("Temp. construction < 3 days ");
        
/*145*/  language.SK.add("Dočasná stavba < 3 mesiace");                     
        language.CZ.add("Dočasná stavba < 3 měsíce");                     
        language.EN.add("Temp. construction < 3 months");
        
/*146*/  language.SK.add("Dočasná stavba < 1 rok");                     
        language.CZ.add("Dočasná stavba < 1 rok");                     
        language.EN.add("Temp. construction < 1 year");
        
/*147*/  language.SK.add("Vlastné hodnoty");                     
        language.CZ.add("Vlastní hodnoty");                     
        language.EN.add("Custom values");

/*148*/  language.SK.add("Úroveň spoľahlivosti vlastnej hodnoty");     
        language.CZ.add("Úroveň spolehlivosti vlastní hodnoty");                     
        language.EN.add("Custom value reliability coeff.");

/*149*/  language.SK.add("Čas návratu klimatickej udalosti  [rok] t =");                     
         language.CZ.add("Čas návratu klimatické události   [rok] t =");                     
         language.EN.add("Return time of the climate event [year] t =");
        
/*150*/  language.SK.add("Parc. faktor. pre extrémny vietor [-]  Yw =");                     
         language.CZ.add("Parc. faktor. pro extrémní vítr   [-]  Yw =");                     
         language.EN.add("Extreme wind partial coefficient  [-]  Yw =");
        
/*151*/  language.SK.add("Parc. faktor. pre extrémnu námrazu[-]  Yi =");                     
         language.CZ.add("Parc. faktor. pro extrémní námrazu[-]  Yi =");                     
         language.EN.add("Extreme ice partial coefficient   [-]  Yi =");
         
/*152*/  language.SK.add("Parc. faktor. pre komb. vietor    [-]  Ww =");                     
         language.CZ.add("Parc. faktor. pro komb. vietor    [-]  Ww =");                     
         language.EN.add("Comb. wind partial coefficient    [-]  Ww =");
         
/*153*/  language.SK.add("Parc. faktor. pre. komb. námrazu  [-]  Wi =");                     
         language.CZ.add("Parc. faktor. pro. komb. námrazu  [-]  Wi =");                     
         language.EN.add("Comb. ice partial coefficient     [-]  Wi =");

// main_frame str rozpatie

/*154*/  language.SK.add("Stredné rozpätie");                     
        language.CZ.add("Střední rozpětí");                     
        language.EN.add("Mean span length");
        
/*155*/  language.SK.add("Klasicky bez prevýšení");                     
        language.CZ.add("Klasicky bez převýšení");                     
        language.EN.add("Without elevation diff.");
        
/*156*/  language.SK.add("S prevýšeniami");                     
        language.CZ.add("S převýšeními");                     
        language.EN.add("With elevation");
         
// main_frame _status: teploty
/*157*/  language.SK.add("Teploty [°C]");                     
        language.CZ.add("Teploty [°C]");                     
        language.EN.add("Temper. [°C]");
        
/*158*/  language.SK.add("Preťaženia");                       
        language.CZ.add("Přetížení");                     
        language.EN.add("Overloads");
        
/*159*/  language.SK.add("Vypočítane");                       
        language.CZ.add("Spočítáno");                     
        language.EN.add("Calculated");
        
/*160*/  language.SK.add("Vlastné");                          
        language.CZ.add("Vlastní");                     
        language.EN.add("Custom");
        
/*161*/  language.SK.add("Stredná ročná teplota");       
        language.CZ.add("Střední ročná teplota");                     
        language.EN.add("Average year temper.");
        
/*162*/  language.SK.add("Stav");       
        language.CZ.add("Stav");                     
        language.EN.add("State");
  
 // kontrola pracovného bodu
/*163*/  language.SK.add("Kontrola pracovného bodu");       
        language.CZ.add("Kontrola pracovního bodu");                    
        language.EN.add("Working nodes check");
        
/*164*/  language.SK.add("Typ terénu");       
        language.CZ.add("Typ terénu");                     
        language.EN.add("Terr. type");
  
/*165*/  language.SK.add("Typ I");       
        language.CZ.add("Typ I");                     
        language.EN.add("Type I");
        
/*166*/  language.SK.add("Typ II");       
        language.CZ.add("Typ II");                     
        language.EN.add("Type II");
        
/*167*/  language.SK.add("Typ III");       
        language.CZ.add("Typ III");                     
        language.EN.add("Type III");
        
/*168*/  language.SK.add("Typ IV");       
        language.CZ.add("Typ IV");                     
        language.EN.add("Type IV");
        
/*169*/  language.SK.add("Otvorený plochý terén bez stromov, bez prekážok, so snehovou prikrývkou, blízko vodných plôch alebo cez ne.");                    
         language.CZ.add("Otevřený plochý terén bez stromů, bez překážek, se sněhovou přikrývkou, v blízkosti vodních ploch nebo přes tyto plochy.");                    
         language.EN.add("Open flat terrain without trees, without obstacles, covered with snow, nearby water surfaces or over them.");
         
/*170*/  language.SK.add("Otvorený plochý terén bez prekážok, bez snehu, napr. poľnohospodárska pôda bez akýchkoľvek prekážok.");                    
         language.CZ.add("Otevřený plochý terén bez překážek, bez sněhu, např. zemědělská půda bez jakýkoliv překážky.");                    
         language.EN.add("Open flat terrain without obstacles, without snow, for example agricultural land without any obstacles.");
         
/*171*/  language.SK.add("Otvorený plochý alebo zvlnený terén s ojedinelými prekážkami, napr. otvorené pastviny alebo poľnohospodárska pôda s málo stromami, živými plotmi a inými bariérami.");                    
         language.CZ.add("Otevřený plochý nebo zvlnený terén s ojedinelými překážkami, např. otevřené pastviny nebo zemědělská půda s málo stromami, živými plotmi a jinými bariérami.");                    
         language.EN.add("Open flat or rolling landscape with occasional obstacles, for example open pastures or agricultural land with few tress, hedges and other obstacles.");
         
/*172*/  language.SK.add("Zastavaný terén so stromami a stavbami, napr. predmestia, malé mestá, zalesnené oblasti s krovinami, malé polia s krovinami, stromami a živými plotmi");                    
         language.CZ.add("Zastavaný terén se stromami a stavbami, např. pŕedměstí, malá města, zalesnené oblasti s krovinami, malé pole s krovinami, stromami a živými plotmi");                    
         language.EN.add("Built up landscape with trees and buildings, for example suburban areas, small cities, forested areas, small fields with bushes, trees and hedges.");

/*173*/  language.SK.add("V čase rok po montáži");       
         language.CZ.add("V čase rok po montáži");                     
         language.EN.add("In 1yr after constr. ");
        
/*174*/  language.SK.add("V rovnakom čase ako prechodné tabuľky");       
         language.CZ.add("V rovnakém čase jak přechodní tabulky");                     
         language.EN.add("Same time as transient tables");
             
// main_frame texty do pdf 

/*175*/  language.SK.add("Hlavička dokumentu");       
         language.CZ.add("Hlavička dokumentu");                     
         language.EN.add("Head of document");
         
/*176*/  language.SK.add("Výpočet podľa normy");       
         language.CZ.add("Výpočet podle normy");                     
         language.EN.add("Calc. based on norm");
         
/*177*/  language.SK.add("Nadpis pre prechodné");       
         language.CZ.add("Nadpis pro přechodní");                     
         language.EN.add("Name for transient");
         
/*178*/  language.SK.add("Stavba");       
         language.CZ.add("Stavba");                     
         language.EN.add("Const.");
         
/*179*/  language.SK.add("SO-PS");       
         language.CZ.add("SO-PS");                     
         language.EN.add("SO-PS");
         
/*180*/  language.SK.add("Archívne číslo");       
         language.CZ.add("Archivní číslo");                     
         language.EN.add("Archive number");
         
/*181*/  language.SK.add("Číslovanie strán od");       
         language.CZ.add("Číslování stran od");                     
         language.EN.add("Page numbering from");
         
/*182*/  language.SK.add("Vypracoval");       
         language.CZ.add("Vypracoval");                     
         language.EN.add("Created by");
         
/*183*/  language.SK.add("Dátum");       
         language.CZ.add("Datum");                     
         language.EN.add("Date ");

// main_frame_pdf
/*184*/  language.SK.add("Počiatočné tabuľky");       
         language.CZ.add("Počáteční tabulky");                     
         language.EN.add("Initial tables");
         
/*185*/  language.SK.add("Prechodné tabuľky");       
         language.CZ.add("Přechodní tabulky");                     
         language.EN.add("Transient tables");
         
/*186*/  language.SK.add("Konečné tabuľky");       
         language.CZ.add("Koneční tabulky");                    
         language.EN.add("Final tables");

/*187*/  language.SK.add("Výpočet podľa normy");       
         language.CZ.add("Výpočet podle normy");                     
         language.EN.add("Calc. based on norm");
         
/*188*/  language.SK.add("Stavba");       
         language.CZ.add("Stavba");                     
         language.EN.add("Const.");
         
/*189*/  language.SK.add("Dátum");       
         language.CZ.add("Datum");                     
         language.EN.add("Date ");

/*190*/  language.SK.add("Archívne číslo");       
         language.CZ.add("Archivní číslo");                     
         language.EN.add("Archive number");

/*191*/  language.SK.add("SO-PS");       
         language.CZ.add("SO-PS");                     
         language.EN.add("SO-PS");
         
/*192*/  language.SK.add("Vypracoval");       
         language.CZ.add("Vypracoval");                     
         language.EN.add("Created by");
         
/*193*/  language.SK.add("Strana");       
         language.CZ.add("Strana");                    
         language.EN.add("Page");

/*194*/  language.SK.add("Typ lana");       
         language.CZ.add("Typ lana");                     
         language.EN.add("Conductor");
         
/*195*/  language.SK.add("Parametre Lana");       
         language.CZ.add("Parametre Lana");                     
         language.EN.add("Parameters");

/*196*/  language.SK.add("Menovitý priemer");       
         language.CZ.add("Menovitý průměr");                     
         language.EN.add("Diameter");
         
/*197*/  language.SK.add("Menovitý prierez");       
         language.CZ.add("Menovitý průřez");                     
         language.EN.add("Cross section");
         
/*198*/  language.SK.add("Menovitá hmotnosť");       
         language.CZ.add("Menovitá hmotnost");                     
         language.EN.add("Weight");
         
/*199*/  language.SK.add("Modul pružnosti");       
         language.CZ.add("Modul pružnosti");                     
         language.EN.add("Young module");
         
/*200*/  language.SK.add("Merná tiaž lana");       
         language.CZ.add("Merná tíž lana");                     
         language.EN.add("Spec. gravity");
         
/*201*/  language.SK.add("Koef. tep. rozťažnosti");      
         language.CZ.add("Koef. tep. roztažnosti");                     
         language.EN.add("Thermal expans. coeff.");
         
/*202*/  language.SK.add("Mat. únosnosť lana");       
         language.CZ.add("Mat. únosnost lana");                     
         language.EN.add("Rated Tens. Stren.");

/*203*/  language.SK.add("Horizontálna zložka mechanického namáhania pri -5°C");       
         language.CZ.add("Horizonrální složka mechanického namáhání při -5°C");                     
         language.EN.add("Horizontal mechanical stress in -5°C");
         
/*204*/  language.SK.add("Stredná výška vodiča nad terénom v kotevnom úseku");       
         language.CZ.add("Střední výška vodiče nad terénem v kotevním úseku");                     
         language.EN.add("Average cond. height in tensioning section");
         
/*205*/  language.SK.add("Charakteristika terénu");       
         language.CZ.add("Charakteristika terénu");                     
         language.EN.add("Terr. characteristics");
         
/*206*/  language.SK.add("Typ terénu");       
         language.CZ.add("Typ terénu");                     
         language.EN.add("Terr. type");

/*207*/  language.SK.add("Búrlivé otvorené more, jazerá do vzdialenosti najmenej 5 km proti smeru vetra a rovná krajina bez prekážok.");                    
         language.CZ.add("Bouřlivé otevřené moře, jezera do vzdálenosti nejméne 5 km proti směru větra a rovná krajina bez překážek.");                    
         language.EN.add("Stormy open sea, lakes at least 5 km away from the opposite side then wind orientation and landscape without obstacles.");
         
/*208*/  language.SK.add("Poľnohospodárska pôda s deliacimi živými plotmi, rozptýlené malé poľnohospodárske sídla, domy a stromy");                    
         language.CZ.add("Zemědělská půda s dělícími živými plotmi, rozptýlená malá polnohospodářská sídla, domy a stromy.");                    
         language.EN.add("Agricultural land divided with hedges, distributed small agricultural settlements, houses and trees.");
         
/*209*/  language.SK.add("Predmestské alebo priemyselné oblasti a trvale zalesnené územia.");                    
         language.CZ.add("Předměstské nebo průmyslné oblasti a trvalo zalesněná území.");                    
         language.EN.add("Suburban or industrial areas and permanently forested areas.");
         
/*210*/  language.SK.add("Mestské oblasti, v ktorých je aspoň 15% povrchu pokrytého pozemnými stavbami s priemernou výškou > 15 m.");                    
         language.CZ.add("Městské oblasti, ve kterých je alespoň 15% povrchu pokrytého pozemními stavbami s průmernou výškou > 15 m.");                    
         language.EN.add("Urban areas with at least 15% of surface covered with ground structures with height over 15 m.");
         
/*211*/  language.SK.add("Hornatý a viac členitý terén, kde sa vietor môže lokálne zosilňovať alebo zoslabovať.");                    
         language.CZ.add("Hornatý a více članitý terén, kde se vítr může lokálně zesilovat nebo zoslabovat.");                    
         language.EN.add("Highland and more indented terrain, where is the possibilty of local increase or decrease of wind speed.");

/*212*/  language.SK.add("Úroveň spoľahlivosti");                     
         language.CZ.add("Úroveň spolahlivosti");                     
         language.EN.add("Reliabilty level");
        
/*213*/  language.SK.add("Doba návratu klimatického zaťaženia");       
         language.CZ.add("Doba návratu klimatického zatížení");                     
         language.EN.add("Return time of the climate load");
         
/*214*/  language.SK.add("rokov");       
         language.CZ.add("roků");                     
         language.EN.add("years");
         
/*215*/  language.SK.add("Námrazová oblasť");       
         language.CZ.add("Námrazová oblast");                     
         language.EN.add("Ice area");

/*216*/  language.SK.add("I-0");       language.CZ.add("I-0");                     language.EN.add("I-0");
/*217*/  language.SK.add("I-1");       language.CZ.add("I-1");                     language.EN.add("I-1");
/*218*/  language.SK.add("I-2");       language.CZ.add("I-2");                     language.EN.add("I-2");
/*219*/  language.SK.add("I-3");       language.CZ.add("I-3");                     language.EN.add("I-3");
/*220*/  language.SK.add("I-5");       language.CZ.add("I-5");                     language.EN.add("I-5");
/*221*/  language.SK.add("I-8");       language.CZ.add("I-8");                     language.EN.add("I-8");
/*222*/  language.SK.add("I-12");       language.CZ.add("I-12");                     language.EN.add("I-12");
/*223*/  language.SK.add("I-18");       language.CZ.add("I-18");                     language.EN.add("I-18");

/*224*/  language.SK.add("I-K - vlastná hodnota");       
         language.CZ.add("I-K - vlastní hodnota");                     
         language.EN.add("I-K - custom value");
                     
/*225*/  language.SK.add(" - ");       
         language.CZ.add(" - ");                     
         language.EN.add(" - ");
   
/*226*/  language.SK.add("Vetrová oblasť");                    
        language.CZ.add("Větrová oblast");                     
        language.EN.add("Wind area");
        
/*227*/  language.SK.add("Koeficient nadm. výšky");       
         language.CZ.add("Koeficient nadm. výšky");                     
         language.EN.add("Above sea level coeff.");
         
/*228*/  language.SK.add("Zaťaženie extrémnou námrazou");       
         language.CZ.add("Zatížení extrémni námrazou");                     
         language.EN.add("Extreme ice overload");
         
/*229*/  language.SK.add("Zaťaženie miernou námrazou");      
         language.CZ.add("Zatížení mírnou námrazou");                    
         language.EN.add("Mild ice overload");
         
/*230*/  language.SK.add("Zaťaženie extrémnym vetrom");       
         language.CZ.add("Zatížení extrémnim větrem");                     
         language.EN.add("Extreme wind overload");
         
/*231*/  language.SK.add("Zaťaženie extrémnou námrazou a vysoko pravdepodobnostným vetrom");       
         language.CZ.add("Zatížení extrémní námrazou a vysoce pravdepodobnostním větrem");                     
         language.EN.add("Extreme ice and high probability wind overload");
         
/*232*/  language.SK.add("Zaťaženie menovitou námrazou a nízko pravdepodobnostným vetrom");       
         language.CZ.add("Zatížení jmenovitou námrazou a nízce pravdepodobnostním větrem");                     
         language.EN.add("Nominal ice and low probability wind overload");

/*233*/  language.SK.add("Navrhovaná živostnosť vedenia");       
         language.CZ.add("Navrhovaná životnost vedení");                     
         language.EN.add("Projected lifespan");
         
/*234*/  language.SK.add("Čas od montáže vedenia");       
         language.CZ.add("Čas od montáže vedení");                    
         language.EN.add("Time from construction");

/*235*/  language.SK.add("dní");       
         language.CZ.add("dnů");                     
         language.EN.add("days");
         
/*236*/  language.SK.add("rok");       
         language.CZ.add("rok");                     
         language.EN.add("year");
         
/*237*/  language.SK.add("mesiace");       
         language.CZ.add("měsíce");                     
         language.EN.add("months");

/*238*/  language.SK.add("Hodnoty namáhania ");       
         language.CZ.add("Hodnoty namáhání ");                     
         language.EN.add("Stress values ");
         
/*239*/  language.SK.add("a parametra c pre stredné rozpätie = ");       
         language.CZ.add("a parametra c pro střední rozpetí  = ");                     
         language.EN.add("and parameter c for average span   = ");

/*240*/  language.SK.add("teplota [°C]");       
         language.CZ.add("teplota [°C]");                     
         language.EN.add("temp. [°C]");
         
/*241*/  language.SK.add("preťaženia");       
         language.CZ.add("přetížení");                    
         language.EN.add("overloads");
         
/*242*/  language.SK.add("Viditeľné priehyby F");       
         language.CZ.add("Viditeľné priehyby");                     
         language.EN.add("Visible sags");
         
/*243*/  language.SK.add("v jednotlicých rozpätiach kotevného úseku");       
         language.CZ.add("v jednotlivých rozpětích kotěvního úseku");                     
         language.EN.add("in specific spans from tensioning section");

/*244*/  language.SK.add("Nový projekt");       
         language.CZ.add("Nový projekt");                     
         language.EN.add("New project");
         
/*245*/  language.SK.add("Nový projekt - názov ");       
         language.CZ.add("Nový projekt - název");                     
         language.EN.add("New project - name");

/*246*/  language.SK.add("Načítať projekt");       
         language.CZ.add("Načíst projekt");                     
         language.EN.add("load project");
         
/*247*/  language.SK.add("Uložiť projekt ako");       
         language.CZ.add("Uložit projekt jak");                     
         language.EN.add("Save project as");

/*248*/  language.SK.add("Námraza [%]");       
         language.CZ.add("Námraza [%]");                     
         language.EN.add("Ice [%]");

/*249*/  language.SK.add("ťah pre -5+N [MPa]");       
         language.CZ.add("tah pro -5+N [MPa]");                     
         language.EN.add("pull for -5+N [MPa]");
         
/*250*/  language.SK.add("Kontrola ťahov a pracovného bodu");       
         language.CZ.add("Kontrola tahů a pracovních bodů");                     
         language.EN.add("Pull and working nodes check");
         
/*251*/  language.SK.add("Zavri povodný subor");       
         language.CZ.add("Zavři původní soubor");                     
         language.EN.add("Close original file");
         
/*252*/  language.SK.add("Nie je vybraný žiaden kotevný usek");       
         language.CZ.add("Není vybraný žádný kotěvný úsek");                     
         language.EN.add("No tensioning section selected");
         
/*253*/  language.SK.add("Rozpätie kot. úseku");       
         language.CZ.add("Rozpetí kot. úseku");                     
         language.EN.add("Span of tens. sec.");
         
/*254*/  language.SK.add("Oblasť");       
         language.CZ.add("Oblast");                     
         language.EN.add("Area");

/*255*/  language.SK.add("1 - základná bezpečná oblasť");       
         language.CZ.add("1 - základní bezpeční oblast");                     
         language.EN.add("1 - basic safe zone");
         
/*256*/  language.SK.add("2 - bezpečná oblasť");       
         language.CZ.add("2 - bezpeční oblast");                     
         language.EN.add("2 - safe zone");
         
/*257*/  language.SK.add("3 - oblasť osobitných opatrení");       
         language.CZ.add("3 - oblast osobitých opatření");                     
         language.EN.add("3 - specific requirements needed");
         
/*258*/  language.SK.add("0.0");       
         language.CZ.add("0.0");                    
         language.EN.add("0.0");
         
/*259*/  language.SK.add("Dizajn manažér MT3");       
         language.CZ.add("Dizajn manažer MT3");                     
         language.EN.add("Design manager MT3");
         
/*260*/  language.SK.add("Náhľad exportu PDF");       
         language.CZ.add("Náhled exportu PDF");                     
         language.EN.add("Preview export PDF");
         
/*261*/  language.SK.add("Čas vytvorenia PDF náhľadu");       
         language.CZ.add("Čas vytvoření PDF náhladu");                     
         language.EN.add("PDF preview create time");
         
/*262*/  language.SK.add("Počiatočné b)");      
         language.CZ.add("Počáteční b)");                     
         language.EN.add("Initial b)");

/*263*/  language.SK.add("Náhľad PDF a výsledkov v MT3");       
         language.CZ.add("Náhled PDF a výsledků v MT3");                     
         language.EN.add("Preview PDF & results in MT3");
         
/*264*/  language.SK.add("Náhľad PDF a výsledkov v Adobe reader");       
         language.CZ.add("Náhled PDF a výsledků v Adobe reader");                    
         language.EN.add("Preview PDF & results in Adobe reader");
         
/*265*/  language.SK.add("Vytvorenie PDF");       
         language.CZ.add("Vytvoření PDF");                    
         language.EN.add("Create PDF");
         
/*266*/  language.SK.add("Uložiť monážne tabuľky - PDF export");       
         language.CZ.add("Uložit montážní tabulky - PDF export");                     
         language.EN.add("Save assembly tables - PDF export");

/*267*/  language.SK.add("Fa [MPa]");       language.CZ.add("Fa [MPa]");                     language.EN.add("Fa [MPa]");
/*268*/  language.SK.add("Fb [MPa]");       language.CZ.add("Fb [MPa]");                     language.EN.add("Fb [MPa]");
/*269*/  language.SK.add("Fa [MPa]");       language.CZ.add("Fa [MPa]");                     language.EN.add("Fa [MPa]");
/*270*/  language.SK.add("Fb [MPa]");       language.CZ.add("Fb [MPa]");                     language.EN.add("Fb [MPa]");

/*271*/  language.SK.add("Nový projekt vytvorený");       
         language.CZ.add("Nový projekt vytvořen");                     
         language.EN.add("New project created");
         
/*272*/  language.SK.add("Projekt načítaný");       
         language.CZ.add("Projekt načítán");                    
         language.EN.add("Project loaded");
         
/*273*/  language.SK.add("Projekt uložený");       
         language.CZ.add("Projekt uložen");                     
         language.EN.add("Project saved");
         
/*274*/  language.SK.add("Projekt uložený ako");       
         language.CZ.add("Projekt uložen jako");                     
         language.EN.add("Project saved as");
         
/*275*/  language.SK.add("Ťahy a kontrola pracovného bodu vypočítaná");       
         language.CZ.add("Tahy a kontrola pracovního bodu spočtena");                     
         language.EN.add("Pull and working node check computed");
         
/*276*/  language.SK.add("Interný náhlad pripravený");       
         language.CZ.add("Interní náhled připraven");                     
         language.EN.add("Internal preview ready");
         
/*277*/  language.SK.add("Externý náhlad pripravený");       
         language.CZ.add("Externí náhled připraven");                     
         language.EN.add("External preview ready");
         
/*278*/  language.SK.add("Montážne tabuľky - PDF export vytvorený");      
         language.CZ.add("Montážní tabulky - PDF export vytvořen");                     
         language.EN.add("Assembly tables - PDF export created");
         
/*279*/  language.SK.add("Kotevný úsek vytvorený");       
         language.CZ.add("Kotěvný úsek vytvořen");                     
         language.EN.add("Tensioning section created");
         
/*280*/  language.SK.add("Kotevný úsek odstránený");       
         language.CZ.add("Kotěvný úsek odstráněn");                     
         language.EN.add("Tensioning section deleted");
         
/*281*/  language.SK.add("Ťah pre -5+N - percentuálny podiel námrazy");       
         language.CZ.add("Tah pro -5+N - percentuální podíl námrazy");                     
         language.EN.add("Pull for -5+N - ice overload percent");

/*282*/  language.SK.add("Ťah -5+Nv konečné tab.");       
         language.CZ.add("Tah -5+Nv koneční tab.");                     
         language.EN.add("Pull -5+Nv final tab.");
         
/*283*/  language.SK.add("Ťah ");       
         language.CZ.add("Tah ");                    
         language.EN.add("Pull");
         
/*284*/  language.SK.add("Nie je označený žiaden kot. úsek");       
         language.CZ.add("Není označen žádný kotěvný úsek");                     
         language.EN.add("No tensioning section selected");
         
/*285*/  language.SK.add(" volitelné tab.");       
         language.CZ.add(" voltelné tab.");                    
         language.EN.add(" custom tables");
         
/*286*/  language.SK.add("kontrola ťahov pracovného bodu");       
         language.CZ.add("kontrola tahů pracovního bodu");                    
         language.EN.add("working node pull check");

/*287*/  language.SK.add("Uložiť zmeny pred ukončením projektu ?");       
         language.CZ.add("Uložiť zmeny před ukončením projektu ?");                    
         language.EN.add("Save changes before quiting the project?"); 

/*288*/  language.SK.add("Nie");       
         language.CZ.add("Ne");                    
         language.EN.add("No");  
         
/*289*/  language.SK.add("Ano");       
         language.CZ.add("Ano");                    
         language.EN.add("Yes");   
         
/*290*/  language.SK.add("Zrušiť");       
         language.CZ.add("Zrušiť");                    
         language.EN.add("Cancel");           

/*291*/  language.SK.add("Chystáte sa ukončiť MT3 ste si tym istý ?");       
         language.CZ.add("Chystáte se ukončiť MT3 ste si tím jistý ?");                    
         language.EN.add("You are about to exit MT3 are you sure ?"); 
         
/*292*/  language.SK.add("Koeficietn Kh bez vplyvu výšky stožiarov");       
         language.CZ.add("Koeficietn Kh bez vplyvu výšky stožárú");                    
         language.EN.add("Koeficietn Kh without influence of tower height"); 

/*293*/  language.SK.add("Koeficietn Kh s vplyvom výšky stožiarov");       
         language.CZ.add("Koeficietn Kh s plyvem výšky stožárú");                    
         language.EN.add("Koeficietn Kh with influence of tower height"); 

/*294*/  language.SK.add("OK");       
         language.CZ.add("OK");                    
         language.EN.add("OK");           
/*295*/  language.SK.add("Štandartná hodnota");       
         language.CZ.add("Štandartná hodnota");                    
         language.EN.add("Standard value");
/*296*/  language.SK.add("Typická hodnota pre Čeps a.s.");       
         language.CZ.add("Typická hodnota pro Čeps a.s.");                    
         language.EN.add("Typical value for Čeps a.s.");  
/*297*/  language.SK.add("Podiel z námrazy");       
         language.CZ.add("Podíl z námrazy");                    
         language.EN.add("Pecetage from iceload");
         
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
