/* * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_math;

import mt_variables.Conductor_variables;

/**
 *
 * @author Mattto
 */
public class conductor_creeping {


/* Defining variables */
   
// constants
    private static final double fi = 28.2;          // specific creeping [mm/km*h] (= 28.2)
    private static final double n = 0.263;           // load function steepness [-] (= 0.263)
    
   
    /**
     * fictive temperature for computing the state equation in 0[h] time - in time of construction
     */
    public static double T_x0;
    
    /**
     * fictive temperature for computing the state equation in "p"[h] time - "p" hours after construction
     */
    public static double T_xp;

// public variables
    /**
     * horizontal part of load in mean year temperature [MPa] -> computes through state equation using average year temperature
     */
    public static double sigma_HT;
    
   
// **************** PUBLIC METHODS **************** //

    /**
     * 
     * @param t0 - as theta_0
     * @param tp - as theta_1
     * @param sigma_HT - computed for -5 degrees and z_0 == 1
     * @param Conductor - conductor variables class
     * @param T_EDT - average year temperature
     * @return thermal shift usable for multiple purposes
     */
     public static double thermal_shift_universal_value(   double t0, 
                                                           double tp,
                                                           double sigma_HT,
                                                           Conductor_variables Conductor,
                                                           double T_EDT){
         
        double deltaEta =   k_EDS_value(sigma_HT, Conductor)* 
                            k_EDT_value(T_EDT)*
                            k_w_value(Conductor.get_w_Fe())*
                            conductor_creeping.fi*
                            (Math.pow(t0, n) - Math.pow(tp, n));
//        System.out.println("deltaETA = " + deltaEta);
        return ((-1*deltaEta)/(Conductor.get_alpha()*1e6)); 

    }
    
// **************** PRIVATE METHODS **************** //    

    
     /**
     * Computes the conductor composition coefficient "k_w"
     */
    private static double k_w_value(double w_Fe){
        System.out.println("k_w = " + (1.212 - 1.06*w_Fe));
        return 1.212 - 1.06*w_Fe;
    }
    
    /**
     * Computes the average year load influence coefficient on the conductor "k_EDS"
     */
     private static double k_EDS_value(double sigma_HT, Conductor_variables Conductor){
        System.out.println("k_EDS = " + (0.0319 * Math.pow((100*sigma_HT*Conductor.get_S()) / Conductor.get_RTS(), 1.15)));
        return 0.0319 * Math.pow((100*sigma_HT*Conductor.get_S()) / Conductor.get_RTS(), 1.15);
    }
    
    /**
     * Computes the average year temperature influence coefficient of the conductor "k_EDT"
     */
     private static double k_EDT_value(double T_EDT){
        System.out.println("k_EDT = " + (0.842 + 0.0079* T_EDT));
        return 0.842 + 0.0079* T_EDT;
    }
    
    
}
