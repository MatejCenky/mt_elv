/* * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by the author Matej Cenky.
 */
package mt_test;

/**
 *
 * @author Mattto
 */
public class conductor_creeping {
    
    /**
     * - gravitational acceleration and fi is set
     */
    public conductor_creeping(){
        this.g = 9.80655;
        this.fi = 28.2;
    }
    
    /**
     * - gravitational acceleration and fi is set
     * @param steel_weight weight of the steel is set
     */
    public conductor_creeping(double steel_weight){
        this.g = 9.80655;
        this.g_Fe = steel_weight;
        this.fi = 28.2;
    }
    
/* Defining variables */
    
// local
    private double g_Fe;        // weight of the steel in the conductor [N/m]
    private double S_Fe;        // cross-section of steel in conductor [mm^2]
    private double S;           // cross-section of conductor [mm^2]
    private double ro_Fe;       // specific weight of steel [kg/m^3] (usually 7840-7870)
    private final double g;     // gravitational acceleration [m/s^2] (= 9.80655)
    private double g_c;         // specific weight of conductor [N/m]
    private double sigma_HT;    // horizontal part of load in mean year temperature [MPa] -> computes through state equation using average year temperature
    private double RTS;         // Rated Tensile Parameter [N]
    private double T_EDT;       // average year temperature [degreeC]
    private double alpha;       // linear expansion coefficient [1/degreeC]
    private final double fi;    // specific creeping [mm/km*h] (= 28.2)
    private double t_0;         // time to final state of conductor creeping [h] (usually 30y = 262800h)
    private double t_p;         // time from the construction of the line - check various time stages on the line [h]
    
    
// computed values
    private double w_Fe;        // proportional weight of steel in the conductor [-]
    private double k_w;         // conductor composition coefficient [-]
    private double k_EDS;       // average year load influence coefficient [-]
    private double k_EDT;       // average year temperature influence coefficient [-]
    
// results
    /**
     * initial thermal shift [d_degreeC]
     */
    public static double dT_0;
    
    /**
     * transient thermal shift [d_degreeC]
     */
    public static double dT_p;
   
// **************** PUBLIC METHODS **************** //
    public void set_variables(){
        // need to be done
    }
    
    public void get_variables(){
        // need to be done
    }
    
    public void compute(){
        // need to be done
    }
    
// **************** PRIVATE METHODS **************** //    
    /**
     * Computes the weight of the steel in the conductor "g_Fe" (if not known)
     */
    private void steel_weight(){
        this.g_Fe = this.S_Fe* this.ro_Fe* this.g;
    }
    
    /**
     * Computes the proportional weight of steel in the conductor "w_Fe"
     */
    private void proportional_steel_weight(){
        this.w_Fe = this.g_Fe/this.g_c;
    }
    
    /**
     * Computes the conductor composition coefficient "k_w"
     */
    private void conductor_composition_coefficient(){
        this.k_w = 1.212 - 1.06* this.w_Fe;
    }
    
    /**
     * Computes the average year load influence coefficient on the conductor "k_EDS"
     */
     private void avg_load_coefficient(){
        this.k_EDS = 0.0319 * Math.pow(100*this.sigma_HT* this.S / this.RTS, 1.15);
    }
    
    /**
     * Computes the average year temperature influence coefficient of the conductor "k_EDT"
     */
     private void avg_temperature_coefficient(){
        this.k_EDT = 0.842 + 0.0079* this.T_EDT;
    }
    
    /**
     * Computes the initial thermal shift "dT_0"
     */
     private void thermal_shift_intitial(){
        conductor_creeping.dT_0 = (-1/(this.alpha*1e6))* this.k_EDS* this.k_EDT* this.k_w* this.fi* this.t_0;
    }
    
    /**
     * Computes the transient thermal shift "dT_0"
     */
     private void thermal_shift_transient(){
        conductor_creeping.dT_p = (-1/(this.alpha*1e6))* this.k_EDS* this.k_EDT* this.k_w* this.fi* (this.t_0 - this.t_p);
    }
    
    
}
