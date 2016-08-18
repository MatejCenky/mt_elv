/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by the author Matej Cenky.
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
 * If once fuction runs and sets variable "inicializovane" true it never runs again
 */    
public static void constructor(){
              /*SK*/                /*CZ*/              /*EN*/   
 /*0*/  language.SK.add("aaa");  language.CZ.add("aaa"); language.EN.add("aaa"); //language String value  at position 
 /*1*/  language.SK.add("aaa");  language.CZ.add("aaa"); language.EN.add("aaa"); //language String value  at position 
 /*2*/  language.SK.add("aaa");  language.CZ.add("aaa"); language.EN.add("aaa"); //language String value  at position 
 /*3*/  language.SK.add("aaa");  language.CZ.add("aaa"); language.EN.add("aaa"); //language String value  at position 
 /*4*/  language.SK.add("aaa");  language.CZ.add("aaa"); language.EN.add("aaa"); //language String value  at position 
 /*5*/  language.SK.add("aaa");  language.CZ.add("aaa"); language.EN.add("aaa"); //language String value  at position 
   
 
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
