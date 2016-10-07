/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_variables;

/**
 *
 * @author Mattto
 */
public class Conductor_variables {
    
    private static double d_conductor;
       private static double S_conductor;
       private static double m_conductor;
       private static double E_conductor;
       private static double alpha_conductor;
       private static double RTS_conductor;
       private static double Fe_AlFe_conductor;
             
       /**
        * constructor for conductor object from object[] with following parameters:
        * [1] d conductor diameter !!!!!! [m] !!!!!!
        * [2] S cross-section area of the conductor [mm^2]  
        * [3] m weight of the conductor per unit [kg/m]
        * [4] E Young model of elasticity of conductor [MPa]
        * [5] alpha linear expansion coefficient [1/degree_C]
        * [6] RTS Rated Tensile Strength (Matematicka unosnost lana)
        * [7] Fe_Alfe Fe/AlFe ratio (pomer Fe / Alfe vodica)
        * @param Conductor - object[] containing conductor variables
        */
       public Conductor_variables(Object[] Conductor){
           Conductor_variables.d_conductor = Double.valueOf(String.valueOf(Conductor[1]));
           Conductor_variables.S_conductor = Double.valueOf(String.valueOf(Conductor[2]));
           Conductor_variables.m_conductor = Double.valueOf(String.valueOf(Conductor[3]));
           Conductor_variables.E_conductor = Double.valueOf(String.valueOf(Conductor[4]));
           Conductor_variables.alpha_conductor = Double.valueOf(String.valueOf(Conductor[5]));
           Conductor_variables.RTS_conductor = Double.valueOf(String.valueOf(Conductor[6]));
           Conductor_variables.Fe_AlFe_conductor = Double.valueOf(String.valueOf(Conductor[7]));
       }
       
       /**
        * setting conductor variables separately
        * @param d conductor diameter !!!!!! [m] !!!!!!
        * @param S cross-section area of the conductor [mm^2]  
        * @param m weight of the conductor per unit [kg/m]
        * @param E Young model of elasticity of conductor [MPa]
        * @param alpha linear expansion coefficient [1/degree_C]
        * @param RTS Rated Tensile Strength (Matematicka unosnost lana)
        * @param Fe_AlFe Fe/AlFe ratio (pomer Fe / Alfe vodica)
        */
       public static void set_variables(double d,
                                        double S,
                                        double m,
                                        double E,
                                        double alpha,
                                        double RTS,
                                        double Fe_AlFe){
           
           Conductor_variables.d_conductor = d;
           Conductor_variables.S_conductor = S;
           Conductor_variables.m_conductor = m;
           Conductor_variables.E_conductor = E;
           Conductor_variables.alpha_conductor = alpha;
           Conductor_variables.RTS_conductor = RTS;
           Conductor_variables.Fe_AlFe_conductor = Fe_AlFe;
       }
       
       /**
        * setting conductor variables from object[] with following parameters:
        * [1] d conductor diameter !!!!!! [m] !!!!!!
        * [2] S cross-section area of the conductor [mm^2]  
        * [3] m weight of the conductor per unit [kg/m]
        * [4] E Young model of elasticity of conductor [MPa]
        * [5] alpha linear expansion coefficient [1/degree_C]
        * [6] RTS Rated Tensile Strength (Matematicka unosnost lana)
        * [7] Fe_Alfe Fe/AlFe ratio (pomer Fe / Alfe vodica)
        * @param Conductor - object[] containing conductor variables
        */
       public static void set_variables_from_object(Object[] Conductor){
           Conductor_variables.d_conductor = Double.valueOf(String.valueOf(Conductor[1]));
           Conductor_variables.S_conductor = Double.valueOf(String.valueOf(Conductor[2]));
           Conductor_variables.m_conductor = Double.valueOf(String.valueOf(Conductor[3]));
           Conductor_variables.E_conductor = Double.valueOf(String.valueOf(Conductor[4]));
           Conductor_variables.alpha_conductor = Double.valueOf(String.valueOf(Conductor[5]));
           Conductor_variables.RTS_conductor = Double.valueOf(String.valueOf(Conductor[6]));
           Conductor_variables.Fe_AlFe_conductor = Double.valueOf(String.valueOf(Conductor[7]));
       }
        
       /**
        * 
        * @return conductor diameter !!!!!! [m] !!!!!!
        */
       public double get_d(){
           return Conductor_variables.d_conductor;
       }
       
       /**
        * 
        * @return cross-section area of the conductor [mm^2]  
        */
       public double get_S(){
           return Conductor_variables.S_conductor;
       }
       
       /**
        * 
        * @return weight of the conductor per unit [kg/m]
        */
       public double get_m(){
           return Conductor_variables.m_conductor;
       }
       
       /**
        * 
        * @return Young model of elasticity of conductor [MPa]
        */
       public double get_E(){
           return Conductor_variables.E_conductor;
       }
       
       /**
        * 
        * @return linear expansion coefficient [1/degree_C]
        */
       public double get_alpha(){
           return Conductor_variables.alpha_conductor;
       }
       
       /**
        * 
        * @return Rated Tensile Strength (Matematicka unosnost lana)
        */
       public double get_RTS(){
           return Conductor_variables.RTS_conductor;
       }
       
       /**
        * 
        * @return Fe/AlFe ratio (pomer Fe / Alfe vodica)
        */
       public double get_Fe_Alfe(){
           return Conductor_variables.Fe_AlFe_conductor;
       }
}
