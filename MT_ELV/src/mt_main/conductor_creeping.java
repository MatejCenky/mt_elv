/* * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_main;

/**
 *
 * @author Mattto
 */
public class conductor_creeping {
    
    /**
     * - gravitational acceleration, n and fi is set
     */
    public conductor_creeping(){
        conductor_creeping.g = 9.80655;
        conductor_creeping.fi = 28.2;
        conductor_creeping.n = 0.263;
    }
    
    /**
     * - gravitational acceleration, n and fi is set
     * @param steel_weight weight of the steel is set
     */
    public conductor_creeping(double steel_weight){
        conductor_creeping.g = 9.80655;
        conductor_creeping.g_Fe = steel_weight;
        conductor_creeping.fi = 28.2;
        conductor_creeping.n = 0.263;
    }
    
    /**
     * - gravitational acceleration, n and fi is set
     * @param steel_weight weight of the steel is set "g_Fe"
     * @param temperature imaginary temperature WITHOUT thermal shift is set "T_x"
     */
    public conductor_creeping(double steel_weight, double temperature){
        conductor_creeping.g = 9.80655;
        conductor_creeping.g_Fe = steel_weight;
        conductor_creeping.T_EDT = temperature;
        conductor_creeping.fi = 28.2;
        conductor_creeping.n = 0.263;
    }
    
/* Defining variables */
    
// local
    private static double g_Fe;        // weight of the steel in the conductor [N/m]
    private static double S_Fe;        // cross-section of steel in conductor [mm^2]
    private static double S;           // cross-section of conductor [mm^2]
    private static double ro_Fe;       // specific weight of steel [kg/m^3] (usually 7840-7870)
    private static double g;           // gravitational acceleration [m/s^2] (= 9.80655)
    private static double g_c;         // specific weight of conductor [N/m]
    private static double RTS;         // Rated Tensile Parameter [N]
    private static double T_EDT;       // average year temperature [degreeC]
    private static double alpha;       // linear expansion coefficient [1/degreeC]
    private static double fi;          // specific creeping [mm/km*h] (= 28.2)
    private static double t_0;         // time to final state of conductor creeping [h] (usually 30y = 262800h)
    private static double t_p;         // time from the construction of the line - check various time stages on the line [h]
    private static double n;           // load function steepness [-] (= 0.263)
    
// computed values
    private static double w_Fe;        // proportional weight of steel in the conductor [-]
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
    public static void set_variables(){
        // local - results
        conductor_creeping.w_Fe = -1;
        conductor_creeping.k_w = -1;
        conductor_creeping.k_EDS = -1;
        conductor_creeping.k_EDT = -1;
        
        // public - results
        conductor_creeping.T_x0 = -1;
        conductor_creeping.T_xp = -1;
        conductor_creeping.dT_0 = -1;
        conductor_creeping.dT_p = -1;
        conductor_creeping.sigma_HT = -1;
        
        // mainframe - need to be done based on mainframe
    }
    
    public static void get_variables(){
        // need to be done based on mainframe
    }
    
    public static void compute_thermal_shifts(){
        // preparations
        if (conductor_creeping.w_Fe == -1) {
            steel_weight();
        }
        proportional_steel_weight();
        conductor_composition_coefficient();
        // k_EDS, k_EDT
        avg_temperature_coefficient();
        state_equation.compute_sigma_HT(conductor_creeping.T_EDT);
        avg_load_coefficient();
        // results
        thermal_shift_intitial();
        thermal_shift_transient();
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
    /**
     * Computes the weight of the steel in the conductor "g_Fe" (if not known)
     */
    private static void steel_weight(){
        conductor_creeping.g_Fe = conductor_creeping.S_Fe* conductor_creeping.ro_Fe* conductor_creeping.g;
    }
    
    /**
     * Computes the proportional weight of steel in the conductor "w_Fe"
     */
    private static void proportional_steel_weight(){
        conductor_creeping.w_Fe = conductor_creeping.g_Fe/conductor_creeping.g_c;
    }
    
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
     * Computes the transient thermal shift "dT_0"
     */
     private static void thermal_shift_transient(){
        conductor_creeping.dT_p = (-1/(conductor_creeping.alpha*1e6))* conductor_creeping.k_EDS* conductor_creeping.k_EDT* conductor_creeping.k_w* conductor_creeping.fi* (Math.pow(conductor_creeping.t_0, n) - Math.pow(conductor_creeping.t_p, n));
    }
    
    
    
    
}
