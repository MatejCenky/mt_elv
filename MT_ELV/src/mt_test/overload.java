/*
 * Copyright (c) 2016, Mattto
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
public class overload {
    
    /**
     * @param spans array containing all spans in the suspension section [n-1 dim]
     * @param heights array containing all conductor catching points on towers [n dim]
     */
    public overload(double spans[], double heights[]){
        state_equation.a = spans;
        state_equation.dh = heights;
        this.ro = 1.25;
        this.RR = 0;
        this.k_p = 3;
        this.C_cl = 1.1;
    }
    
    /**
     * default constructor with "-1" values for a[0] and dh[0]
     */
    public overload(){
        state_equation.a[0] = -1;
        state_equation.dh[0] = -1;
        this.ro = 1.25;
        this.RR = 0;
        this.k_p = 3;
        this.C_cl = 1.1;
    }
   
 /* Defining variables */
    
// towers and conductors
    private double d;           // conductor diameter [m]
    private double g_c;         // specific weight of conductor [N/m]
    
// type of ice
    private double ro_I;        // density of the ice (= 500 kg/m3)
    private final double C_cl; // aerodynamic resistance for conductor with ice coefficient (= 1,1) [-]
    
// icing area
    private double K_lc;        // local conditions coefficient (čl.4.5.1/SK.3) [-]
    private double K_h;         // the height coefficient čl.4.5.1/SK.3) [-]
    private double I_R50;       // reference ice load -> chosen from local icing area (čl.4.5.1/SK.3)
    
// terrain and wind area
    private double k_r;         // terrain coefficient (čl. 4.3.2) [-]
    private double z_0;         // length of the roughness (čl. 4.3.2) [-]
    private double V_b0;        // base wind speed (čl. 4.3.1/SK.1) [m/s]
    private double c_dir;       // wind direction coefficient [-]
    private double c_0;         // orography coefficient [-]
    private double C_c;         // aerodynamic resistance of the conductor coefficient (čl. 4.4.1.3/SK.1) [-]
    
// directive constants -> defined as final -> set in constructor
    private final double k_p;   // tip coefficient (= 3) (čl. 4.4.1.2)
    private final double RR;    // resonance response coefficient (RR = R^2 = 0) (čl. 4.4.12)
    private final double ro;    // density of the wind (= 1.25)

// reliability coefficient
    private double gama_w;      // partial wind load coefficient [-]
    private double gama_I;      // partial ice load coefficient [-]
    private double Psi_w;       // combination coefficient (čl. 4.6.6/SK/CZ) [-]
    private double Psi_I;       // combination ice load coefficient [-]
    private double B_I;         // combination coefficient (čl. 4.6.6/SK/CZ) [-]
    
// computed values
    private double h_c_mean;    // mean height of the conductor above the ground [m]     
    private double V_h;         // the mean wind speed [m/s]
    private double I_v;         // turbulence intensity
    private double L_m;         // length for response origin coefficient [m]
    private double L;           // specific length of turbulence [m]
    private double BB;          // response origin coefficient [m] (= B^2)
    private double G_c;         // construction coefficient [-]
    
    private double q_wc;        // characteristic wind load
    private double q_p;         // peak wind pressure
    private double q_h;         // mean wind pressure
    private double I_50;        // characteristic ice load
    
    private double I_T;         // extreme ice load
    private double q_wT;        // extreme wind load
    private double I_3;         // mild ice load
    private double q_wI3;       // combined load for mild wind with extreme ice
    private double q_wIT;       // combined load for extreme wind with mild ice
    
    private double D_I;         // equivalent diameter for conductor with extreme ice [m]
    private double D_i;         // equivalent diameter for conductor with mild ice [m]
    
// results
    /**
     * extreme ice overload
     */
    public static double z_1;
    
    /**
     * combined overload on the conductor with mild wind and extreme ice
     */
    public static double z_Iw;  
    
    /**
     * combined overload on the conductor with mild ice and extreme wind
     */
    public static double z_wI;  
    
    /**
     * extreme wind overload
     */
    public static double z_w;          
    
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
     * computes the height coefficient "K_h" (čl.4.5.1/SK.3) [-]
     */
    private void height_coefficient(){
        this.K_h = Math.pow(this.h_c_mean/10,0.13);
    }
    
    /**
     * Computes the mean height of the conductor "h_c_mean" above the ground [m]
     */
    private void mean_height(){
        double aux = 0;
        int i;
        
        // sum of the double array
        for (i=0; i<state_equation.dh.length; i++){ //[n+1]
            aux += state_equation.dh[i];
        }
        this.h_c_mean = aux/state_equation.a.length; // [n]
    }
    
    /**
     * Computes the mean wind speed "V_h" using the values chosen from the user (from mainframe)
     */
    private void mean_wind_speed() {
        this.V_h = this.V_b0*this.c_dir*this.c_0*this.k_r*Math.log(this.h_c_mean/this.z_0);
    }
    
    /**
     * Computes the turbulence intensity "I_v"
     */
    private void turbulence_intensity(){
        this.I_v = 1 / (this.c_0*Math.log(this.h_c_mean/this.z_0));
    }
    
    /**
     * Computes the length for response origin coefficient "L_m" [m]
     */
    private void response_coefficient_length(){
        double aux = 0;
        for (int i=0; i<state_equation.a.length; i++){
            aux += state_equation.a[i];
        }
        // max 3km = 3000m
        if (aux > 3000){
            aux = 3000;
        }
        this.L_m = aux;
    }
    
    /**
     * Computes the specific length for turbulence "L" [m]
     */
    private void specific_turbulence_length(){
        this.L = 300 * Math.pow(this.h_c_mean/200, 0.67 + 0.05*Math.log(this.z_0));
    }
    
    /**
     * Computes the response origin coefficient "BB = B^2" [m]
     */
    private void response_coefficient (){
        this.BB = 1 / (1 + 3/2 * this.L_m/this.L);
    }
    
    /**
     * Computes the construction coefficient "G_c"
     */
    private void construcion_coefficient(){
        this.G_c = (1 + 2*this.k_p*this.I_v*Math.sqrt(this.BB + this.RR))/(1 + 7*this.I_v);
    }
    
    /**
     * Computes characteristic wind load "q_wc"
     */
    private void characteristic_wind_load(){
        this.q_wc = this.q_p* this.d* this.C_c* this.G_c;
    }
    
    /**
     * Computes the peak wind pressure "q_p"
     */
    private void peak_wind_pressure(){
        this.q_p = (1 + 7* this.I_v)* this.q_h;
    }
    
    /**
     * Computes the mean wind pressure "q_h"
     */
    private void mean_wind_pressure(){
        this.q_h = 1/2 * this.ro * Math.pow(this.V_h, 2);
    }
    
    /**
     * Computes the characteristic ice load "I_50"
     */
    private void characteristic_ice_load(){
        this.I_50 = this.I_R50* this.K_h* this.K_lc;
    }
    
    /**
     * Computes the extreme ice load "I_T"
     */
    private void extreme_ice_load(){
        this.I_T = this.I_50* this.gama_I;
    }
    
    /**
     * Computes the extreme wind load "q_wT"
     */
    private void extreme_wind_load(){
        this.q_wT = this.q_wc* this.gama_w;
    }
    
    /**
     * Computes the mild ice load "I_3"
     */
    private void mild_ice_load(){
        this.I_3 = this.I_50* this.Psi_I;
    }
    
    /**
     * Computes equivalent diameter for conductor with: 
     * - extreme ice "D_I" 
     * - mild ice "D_i"
     */
    private void equivalent_diameter_with_ice(){
        this.D_I = Math.sqrt(this.d* this.d + (4*this.I_T)/(9.80665*Math.PI* this.ro_I));   // extreme
        this.D_i = Math.sqrt(this.d* this.d + (4*this.I_3)/(9.80665*Math.PI* this.ro_I));   // mild
    }
    
    /**
     * Computes combined load for:
     * - mild wind with extreme ice "q_wI3"
     * - extreme wind with mild ice "q_wIT"
     */
    private void combined_load(){
        this.q_wI3 = this.q_p* this.D_I* this.C_cl* this.G_c* this.Psi_w;
        this.q_wIT = this.q_p* this.D_i* this.C_cl* this.G_c* this.B_I* this.B_I;
    }
    
    /**
     * Computes extreme ice overload on the conductor "z_1"
     */
    private void overload_extreme_ice(){
        overload.z_1 = (this.I_T + this.g_c) / this.g_c;
    }
    
    /**
     * Computes combined overload on the conductor with:
     * - mild wind with extreme ice "z_Iw"
     * - extreme wind with mild ice "z_wI"
     */
    private void overload_combined(){
        overload.z_Iw = Math.sqrt((Math.pow(this.I_T + this.g_c,2) + this.q_wI3*this.q_wI3)) / this.g_c;
        overload.z_wI = Math.sqrt((Math.pow(this.I_3 + this.g_c,2) + this.q_wIT*this.q_wIT)) / this.g_c;
    }
    
    /**
     * Computes extreme wind overload in conductor "z_W"
     */
    private void overload_extreme_wind(){
        overload.z_w = Math.sqrt((this.q_wT* this.q_wT + this.g_c* this.g_c)) / this.g_c;
    }
}
