/* * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_math;

import mt_main.MyException;
import mt_variables.Conductor_creeping_variables;
import mt_variables.Conductor_variables;

/**
 *
 * @author Mattto
 */
public class conductor_creeping {


/* Defining variables */
    
// conductor
    private static double S;           // cross-section of conductor [mm^2]
    private static double g_c;         // specific weight of conductor [N/m]
    private static double RTS;         // Rated Tensile Parameter [N]
    private static double alpha;       // linear expansion coefficient [1/degreeC]
    private static double w_Fe;        // proportional weight of steel in the conductor [-]
    
// temperatures and times
    private static double T_EDT;       // average year temperature [degreeC]
    private static double t_0;         // time to final state of conductor creeping [h] (usually 30y = 262800h)
    private static double t_p;         // time from the construction of the line - check various time stages on the line [h]
    
// constants
    private static final double fi = 28.2;          // specific creeping [mm/km*h] (= 28.2)
    private static final double n = 0.263;           // load function steepness [-] (= 0.263)
    
// computed values
    private static double k_w;         // conductor composition coefficient [-]
    private static double k_EDS;       // average year load influence coefficient [-]
    private static double k_EDT;       // average year temperature influence coefficient [-]
    
// results
    /**
     * initial thermal shift [d_degreeC]
     */
    public static double dT_0;
    
    /**
     * transient thermal shift [d_degreeC]
     */
    public static double dT_p;
    
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
    
    public static void set_all_variables(Conductor_creeping_variables var, double sigma_HT){
        set_variables(var);
        set_sigma_HT(sigma_HT);
    }
    
//    /**
//     * null the variables /inputs/ from mainframe and partial results 
//     * - final results remain untouched
//     */
//    public static void null_variables(){
//        conductor_creeping.w_Fe = -1111.0000;
//        conductor_creeping.k_w = -1111.0000;
//        conductor_creeping.k_EDS = -1111.0000;
//        conductor_creeping.k_EDT = -1111.0000;
//        conductor_creeping.S = -1111.0000;
//        conductor_creeping.g_c = -1111.0000;
//        conductor_creeping.RTS = -1111.0000;
//        conductor_creeping.T_EDT = -1111.0000;
//        conductor_creeping.alpha = -1111.0000;
//        conductor_creeping.t_0 = -1111.0000;
//        conductor_creeping.t_p = -1111.0000;
//    }
    
    /**
     * checks if all variables /inputs/ are set correctly from mainframe
     */
    public static void check_variables(){
        try {
            if (conductor_creeping.S == -1111.0000){
                System.out.println(conductor_creeping.S + " conductor_creeping not set");
                throw new MyException("Variable set error in conductor creeping class");
            } else if (conductor_creeping.g_c == -1111.0000){
                System.out.println(conductor_creeping.g_c + " conductor_creeping not set");
                throw new MyException("Variable set error in conductor creeping class");
            } else if (conductor_creeping.RTS == -1111.0000){
                System.out.println(conductor_creeping.RTS + " conductor_creeping not set");
                throw new MyException("Variable set error in conductor creeping class");
            } else if (conductor_creeping.T_EDT == -1111.0000){
                System.out.println(conductor_creeping.T_EDT + " conductor_creepingnot set");
                throw new MyException("Variable set error in conductor creeping class");
            } else if (conductor_creeping.alpha == -1111.0000){
                System.out.println(conductor_creeping.alpha + " conductor_creepingnot set");
                throw new MyException("Variable set error in conductor creeping class");
            } else if (conductor_creeping.t_0 == -1111.0000){
                System.out.println(conductor_creeping.t_0 + " conductor_creepingnot set");
                throw new MyException("Variable set error in conductor creeping class");
            } else if (conductor_creeping.t_p == -1111.0000){
                System.out.println(conductor_creeping.t_p + " conductor_creeping not set");
                throw new MyException("Variable set error in conductor creeping class");
            } 
        } catch (MyException e) {
        }
    }
    
    /**
     * Computes the thermal shift; 
     * Variables need to be set BEFORE computation;
     * @param load overload z_1 on the conductor
     * @param mid_span /average/ span of suspension section [m] 
     */
    public static void compute_thermal_shifts(double load, double mid_span){
        // preparations
        check_variables();
        // #1 layer
        //proportional_steel_weight();
        conductor_composition_coefficient();
        // k_EDS, k_EDT - #2 layer
        avg_temperature_coefficient();
        conductor_creeping.sigma_HT = state_equation.compute_sigma_H(load, mid_span, conductor_creeping.T_EDT);
        avg_load_coefficient();
        // results
        thermal_shift_intitial();
        thermal_shift_transient();

    }
    
    /**
     * Computes the initial thermal shift with double variable return
     * - NEED TO BE COMPUTED AFTER "COMPUTE_THERMAL_SHIFTS()"
     * @return initial thermal shift [double]
     */
    public static double compute_initial_thermal_shift_value(){
        return thermal_shift_initial_value();
    }
    
    /**
     * Computes the initial thermal shift with double variable return
     * - NEED TO BE COMPUTED AFTER "COMPUTE_THERMAL_SHIFTS()"
     * @return initial thermal shift [double]
     */
    public static double compute_transient_thermal_shift_value(){
        return thermal_shift_transient_value();
    }

    
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
        System.out.println("deltaETA = " + deltaEta);
        return ((-1*deltaEta)/(Conductor.get_alpha()*1e6)); 

    }
    
    /**
     * Computes the fictive temperature for computing the state equation in 0[h] time - in time of construction
     * @param T_x base temperature WITHOUT thermal shift -> dT_0 must be calculated first
     */
    public static void set_Tx0(double T_x){
        conductor_creeping.T_x0 = T_x + conductor_creeping.dT_0;
    }
    
    /**
     * Computes the fictive temperature for computing the state equation in "p"[h] time - "p" hours after construction
     * @param T_x base temperature WITHOUT thermal shift -> dT_p must be calculated first
     */
    public static void set_Txp(double T_x){
        conductor_creeping.T_xp = T_x + conductor_creeping.dT_p;
    }
    
// **************** PRIVATE METHODS **************** //    
    
        // **************** SETTERS **************** //
    private static void set_variables(Conductor_creeping_variables var){
        conductor_creeping.S = var.get_S();
        conductor_creeping.RTS = var.get_RTS();
        conductor_creeping.alpha = var.get_alpha();
        conductor_creeping.w_Fe = var.get_w_Fe();
        conductor_creeping.g_c = var.get_g_c();
        conductor_creeping.T_EDT = var.get_T_EDT();
        conductor_creeping.t_0 = var.get_t_0();
        conductor_creeping.t_p = var.get_t_p();
    }
    
    private static void set_sigma_HT(double sigma_HT){
        conductor_creeping.sigma_HT = sigma_HT;
    }
    
    
//    /**
//     * Computes the weight of the steel in the conductor "g_Fe" (if not known)
//     */
//    private static void steel_weight(){
//        conductor_creeping.g_Fe = conductor_creeping.S_Fe* conductor_creeping.ro_Fe* conductor_creeping.g;
//    }
    
//    /**
//     * Computes the proportional weight of steel in the conductor "w_Fe"
//     */
//    private static void proportional_steel_weight(){
//        conductor_creeping.w_Fe = conductor_creeping.g_Fe/conductor_creeping.g_c;
//    }
    
    /**
     * Computes the conductor composition coefficient "k_w"
     */
    private static void conductor_composition_coefficient(){
        conductor_creeping.k_w = 1.212 - 1.06* conductor_creeping.w_Fe;
    }
    
    /**
     * Computes the average year load influence coefficient on the conductor "k_EDS"
     */
     private static void avg_load_coefficient(){
        conductor_creeping.k_EDS = 0.0319 * Math.pow(100*conductor_creeping.sigma_HT* conductor_creeping.S / conductor_creeping.RTS, 1.15);
    }
    
    /**
     * Computes the average year temperature influence coefficient of the conductor "k_EDT"
     */
     private static void avg_temperature_coefficient(){
        conductor_creeping.k_EDT = 0.842 + 0.0079* conductor_creeping.T_EDT;
    }
    
    /**
     * Computes the initial thermal shift "dT_0"
     */
     private static void thermal_shift_intitial(){
        conductor_creeping.dT_0 = (-1/(conductor_creeping.alpha*1e6))* conductor_creeping.k_EDS* conductor_creeping.k_EDT* conductor_creeping.k_w* conductor_creeping.fi* Math.pow(conductor_creeping.t_0, n);
    }
    
    /**
     * Computes the transient thermal shift "dT_p"
     */
     private static void thermal_shift_transient(){
        conductor_creeping.dT_p = (-1/(conductor_creeping.alpha*1e6))* conductor_creeping.k_EDS* conductor_creeping.k_EDT* conductor_creeping.k_w* conductor_creeping.fi* (Math.pow(conductor_creeping.t_0, n) - Math.pow(conductor_creeping.t_p, n));
    }
     
    /**
     * Computes the initial thermal shift "dT_0"
     */
     private static double thermal_shift_initial_value(){
        return (-1/(conductor_creeping.alpha*1e6))* conductor_creeping.k_EDS* conductor_creeping.k_EDT* conductor_creeping.k_w* conductor_creeping.fi* Math.pow(conductor_creeping.t_0, n);
    }
     
     /**
     * Computes the initial thermal shift "dT_0"
     */
     private static double thermal_shift_transient_value(){
        return (-1/(conductor_creeping.alpha*1e6))* conductor_creeping.k_EDS* conductor_creeping.k_EDT* conductor_creeping.k_w* conductor_creeping.fi* (Math.pow(conductor_creeping.t_0, n)-Math.pow(t_p, n));
    }
    
    
     /**
     * Computes the conductor composition coefficient "k_w"
     */
    private static double k_w_value(double w_Fe){
        System.out.println(1.212 - 1.06*w_Fe);
        return 1.212 - 1.06*w_Fe;
    }
    
    /**
     * Computes the average year load influence coefficient on the conductor "k_EDS"
     */
     private static double k_EDS_value(double sigma_HT, Conductor_variables Conductor){
        System.out.println(0.0319 * Math.pow((100*sigma_HT*Conductor.get_S()) / Conductor.get_RTS(), 1.15));
        return 0.0319 * Math.pow((100*sigma_HT*Conductor.get_S()) / Conductor.get_RTS(), 1.15);
    }
    
    /**
     * Computes the average year temperature influence coefficient of the conductor "k_EDT"
     */
     private static double k_EDT_value(double T_EDT){
        System.out.println(0.842 + 0.0079* T_EDT);
        return 0.842 + 0.0079* T_EDT;
    }
    
    
}
