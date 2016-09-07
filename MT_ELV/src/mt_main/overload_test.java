/*
 * Copyright (c) 2016, Mattto
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
public class overload_test {
    
    /**
     * @param spans array containing all spans in the suspension section [n-1 dim]
     * @param heights array containing all conductor catching points on towers [n dim]
     */
    public overload_test(double spans[], double heights[]){
        overload_test.a = spans;
        overload_test.h = heights;
        overload_test.ro = 1.25;
        overload_test.RR = 0;
        overload_test.k_p = 3;
        overload_test.C_cl = 1.1;
    }
    
    /**
     * default constructor with "-1" values for a[0] and dh[0]
     */
    public overload_test(){
        //overload_test.a[0] = -1;
        //overload_test.dh[0] = -1;
        overload_test.ro = 1.25;
        overload_test.RR = 0;
        overload_test.k_p = 3;
        overload_test.C_cl = 1.1;
    }
   
 /* Defining variables */
  
// basic variables
    
    /**
     * array of the spans on the suspension section [m]
     */
    private static double a[] = new double[]{300}; 
    
    /**
     * array of height differences of the suspension points of towers [m]
     */
    private static double h[] = new double[]{10}; 
    
// towers and conductors
    private static double d = 21.8;           // conductor diameter [m]
    private static double g_c = 9.611;         // specific weight of conductor [N/m]
    
// type of ice
    private static double ro_I = 500;        // density of the ice (= 500 kg/m3)
    private static double C_cl = 1.1; // aerodynamic resistance for conductor with ice coefficient (= 1,1) [-]
    
// icing area
    private static double K_lc = 1;        // local conditions coefficient (čl.4.5.1/SK.3) [-]
    private static double K_h = 1;         // the height coefficient čl.4.5.1/SK.3) [-]
    private static double I_R50 = 3.854;       // reference ice load -> chosen from local icing area (čl.4.5.1/SK.3)
    
// terrain and wind area
    private static double k_r = 0.189;         // terrain coefficient (čl. 4.3.2) [-]
    private static double z_0 = 0.05;         // length of the roughness (čl. 4.3.2) [-]
    private static double V_b0 = 24;        // base wind speed (čl. 4.3.1/SK.1) [m/s]
    private static double c_dir = 1;       // wind direction coefficient [-]
    private static double c_0 = 1;         // orography coefficient [-]
    private static double C_c = 1;         // aerodynamic resistance of the conductor coefficient (čl. 4.4.1.3/SK.1) [-]
    
// directive constants set in constructor
    private static double k_p = 3;   // tip coefficient (= 3) (čl. 4.4.1.2)
    private static double RR = 0;    // resonance response coefficient (RR = R^2 = 0) (čl. 4.4.12)
    private static double ro = 1.25;    // density of the wind (= 1.25)

// reliability coefficient
    private static double gama_w = 1;      // partial wind load coefficient [-]
    private static double gama_I = 1;      // partial ice load coefficient [-]
    private static double Psi_w = 0.25;       // combination coefficient (čl. 4.6.6/SK/CZ) [-]
    private static double Psi_I = 0.35;       // combination ice load coefficient [-]
    private static double B_I = 0.656;         // combination coefficient (čl. 4.6.6/SK/CZ) [-]
    
// computed values
    private static double h_c_mean;    // mean height of the conductor above the ground [m]     
    private static double V_h;         // the mean wind speed [m/s]
    private static double I_v;         // turbulence intensity
    private static double L_m;         // length for response origin coefficient [m]
    private static double L;           // specific length of turbulence [m]
    private static double BB;          // response origin coefficient [m] (= B^2)
    private static double G_c;         // construction coefficient [-]
    
    private static double q_wc;        // characteristic wind load
    private static double q_p;         // peak wind pressure
    private static double q_h;         // mean wind pressure
    private static double I_50;        // characteristic ice load
    
    private static double I_T;         // extreme ice load
    private static double q_wT;        // extreme wind load
    private static double I_3;         // mild ice load
    private static double q_wI3;       // combined load for mild wind with extreme ice
    private static double q_wIT;       // combined load for extreme wind with mild ice
    
    private static double D_I;         // equivalent diameter for conductor with extreme ice [m]
    private static double D_i;         // equivalent diameter for conductor with mild ice [m]
    
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
    public static void set_variables(){
        // local - results
        overload_test.h_c_mean = -1;
        overload_test.V_h = -1;
        overload_test.I_v = -1;
        overload_test.L_m = -1;
        overload_test.L = -1;
        overload_test.BB = -1;
        overload_test.G_c = -1;
        
        overload_test.q_wc = -1;
        overload_test.q_p = -1;
        overload_test.q_h = -1;
        overload_test.I_50 = -1;
        
        overload_test.I_T = -1;
        overload_test.q_wT = -1;
        overload_test.I_3 = -1;
        overload_test.q_wI3 = -1;
        overload_test.q_wIT = -1;
        
        overload_test.D_I = -1;
        overload_test.D_i = -1;
        
        // public - results
        overload_test.z_1 = -1;
        overload_test.z_Iw = -1;
        overload_test.z_w = -1;
        overload_test.z_wI = -1;
        
        // mainframe - need to be done based on mainframe
             
    }
    
    public static void get_variables(){
        // need to be done based on mainframe
    }
    
    public static void compute(){
        //basic check if variables are set
        if (overload_test.k_r == -1) {
            set_variables();
            System.out.println("Variables were set!");
        } 
        // #1 layer
        mean_height();
        mean_wind_speed();
        height_coefficient();
        turbulence_intensity();
        response_coefficient_length();
        specific_turbulence_length();
        // #2 layer
        response_coefficient();
        construcion_coefficient();
        mean_wind_pressure();
        peak_wind_pressure();
        characteristic_wind_load();
        characteristic_ice_load();
        // #3 layer
        extreme_ice_load();
        extreme_wind_load();
        mild_ice_load();
        equivalent_diameter_with_ice();
        combined_load();
        // results
        overload_extreme_ice();
        overload_extreme_wind();
        overload_combined();
    }
    
// **************** PRIVATE METHODS **************** //
    /**
     * computes the height coefficient "K_h" (čl.4.5.1/SK.3) [-]
     */
    private static void height_coefficient(){
        if (overload_test.K_h != 1){
            overload_test.K_h = Math.pow(overload_test.h_c_mean/10,0.13);
        }
    }
    
    /**
     * Computes the mean height of the conductor "h_c_mean" above the ground [m]
     */
    private static void mean_height(){
        double aux = 0;
        int i;
        
        // sum of the double array
        for (i=0; i<overload_test.h.length; i++){
            aux += overload_test.h[i];
        }
        overload_test.h_c_mean = aux/overload_test.h.length;
        
        System.out.println("hcmean = " + overload_test.h_c_mean);
    }
    
    /**
     * Computes the mean wind speed "V_h" using the values chosen from the user (from mainframe)
     */
    private static void mean_wind_speed() {
        overload_test.V_h = overload_test.V_b0*overload_test.c_dir*overload_test.c_0*overload_test.k_r*Math.log(overload_test.h_c_mean/overload_test.z_0);
        
        System.out.println("V_h = " + overload_test.V_h);
    }
    
    /**
     * Computes the turbulence intensity "I_v"
     */
    private static void turbulence_intensity(){
        overload_test.I_v = 1 / (overload_test.c_0*Math.log(overload_test.h_c_mean/overload_test.z_0));
        
        System.out.println("I_v = " + overload_test.I_v);
    }
    
    /**
     * Computes the length for response origin coefficient "L_m" [m]
     */
    private static void response_coefficient_length(){
        double aux = 0;
        for (int i=0; i<overload_test.a.length; i++){
            aux += overload_test.a[i];
        }
        // max 3km = 3000m
        if (aux > 3000){
            aux = 3000;
        }
        overload_test.L_m = aux;
        
        System.out.println("L_m = " + overload_test.L_m);
    }
    
    /**
     * Computes the specific length for turbulence "L" [m]
     */
    private static void specific_turbulence_length(){
        overload_test.L = 300 * Math.pow(overload_test.h_c_mean/200, 0.67 + 0.05*Math.log(overload_test.z_0));
        
        System.out.println("L = " + overload_test.L);
    }
    
    /**
     * Computes the response origin coefficient "BB = B^2" [m]
     */
    private static void response_coefficient (){
        overload_test.BB = 1 / (1 + 1.5*(overload_test.L_m/overload_test.L));
        
        System.out.println("BB = " + overload_test.BB);
    }
    
    /**
     * Computes the construction coefficient "G_c"
     */
    private static void construcion_coefficient(){
        overload_test.G_c = (1 + 2*overload_test.k_p* overload_test.I_v* Math.sqrt(overload_test.BB + overload_test.RR))/(1 + 7*overload_test.I_v);
        
        System.out.println("G_c = " + overload_test.G_c);
    }
    
    /**
     * Computes characteristic wind load "q_wc"
     */
    private static void characteristic_wind_load(){
        overload_test.q_wc = overload_test.q_p* (overload_test.d/1000)* overload_test.C_c* overload_test.G_c;
        
        System.out.println("q_wc = " + overload_test.q_wc);
    }
    
    /**
     * Computes the peak wind pressure "q_p"
     */
    private static void peak_wind_pressure(){
        overload_test.q_p = (1 + 7* overload_test.I_v)* overload_test.q_h;
        
        System.out.println("q_p = " + overload_test.q_p);
    }
    
    /**
     * Computes the mean wind pressure "q_h"
     */
    private static void mean_wind_pressure(){
        overload_test.q_h = 0.5* overload_test.ro* Math.pow(overload_test.V_h, 2);
        
        System.out.println("q_h = " + overload_test.q_h);
    }
    
    /**
     * Computes the characteristic ice load "I_50"
     */
    private static void characteristic_ice_load(){
        overload_test.I_50 = overload_test.I_R50* overload_test.K_h* overload_test.K_lc;
        
        System.out.println("I_50 = " + overload_test.I_50);
    }
    
    /**
     * Computes the extreme ice load "I_T"
     */
    private static void extreme_ice_load(){
        overload_test.I_T = overload_test.I_50* overload_test.gama_I;
        
        System.out.println("I_T = " + overload_test.I_T);
    }
    
    /**
     * Computes the extreme wind load "q_wT"
     */
    private static void extreme_wind_load(){
        overload_test.q_wT = overload_test.q_wc* overload_test.gama_w;
        
        System.out.println("q_wT = " + overload_test.q_wT);
    }
    
    /**
     * Computes the mild ice load "I_3"
     */
    private static void mild_ice_load(){
        overload_test.I_3 = overload_test.I_50* overload_test.Psi_I;
        
        System.out.println("I_3 = " + overload_test.I_3);
    }
    
    /**
     * Computes equivalent diameter for conductor with: 
     * - extreme ice "D_I" 
     * - mild ice "D_i"
     */
    private static void equivalent_diameter_with_ice(){
        overload_test.D_I = Math.sqrt((overload_test.d/1000)* (overload_test.d/1000) + (4*overload_test.I_T)/(9.80665*Math.PI* overload_test.ro_I));   // extreme
        overload_test.D_i = Math.sqrt((overload_test.d/1000)* (overload_test.d/1000) + (4*overload_test.I_3)/(9.80665*Math.PI* overload_test.ro_I));   // mild
        
        System.out.println("D_I / D_i = " + overload_test.D_I + " / " + overload_test.D_i);
    }
    
    /**
     * Computes combined load for:
     * - mild wind with extreme ice "q_wI3"
     * - extreme wind with mild ice "q_wIT"
     */
    private static void combined_load(){
        overload_test.q_wI3 = overload_test.q_p* overload_test.D_I* overload_test.C_cl* overload_test.G_c* overload_test.Psi_w;
        overload_test.q_wIT = overload_test.q_p* overload_test.D_i* overload_test.C_cl* overload_test.G_c* overload_test.B_I* overload_test.B_I;
        
        System.out.println("q_wI3 / q_wIT = " + overload_test.q_wI3 + " / " + overload_test.q_wIT);
    }
    
    /**
     * Computes extreme ice overload on the conductor "z_1"
     */
    private static void overload_extreme_ice(){
        overload_test.z_1 = (overload_test.I_T + overload_test.g_c) / overload_test.g_c;
        
        System.out.println("z_1 = " + overload_test.z_1);
    }
    
    /**
     * Computes combined overload on the conductor with:
     * - mild wind with extreme ice "z_Iw"
     * - extreme wind with mild ice "z_wI"
     */
    private static void overload_combined(){
        overload_test.z_Iw = Math.sqrt((Math.pow(overload_test.I_T + overload_test.g_c,2) + overload_test.q_wI3*overload_test.q_wI3)) / overload_test.g_c;
        overload_test.z_wI = Math.sqrt((Math.pow(overload_test.I_3 + overload_test.g_c,2) + overload_test.q_wIT*overload_test.q_wIT)) / overload_test.g_c;
        
        System.out.println("z_Iw = " + overload_test.z_Iw);
        System.out.println("z_wI = " + overload_test.z_wI);
    }
    
    /**
     * Computes extreme wind overload in conductor "z_W"
     */
    private static void overload_extreme_wind(){
        overload_test.z_w = Math.sqrt((overload_test.q_wT* overload_test.q_wT + overload_test.g_c* overload_test.g_c)) / overload_test.g_c;
        
        System.out.println("z_w = " + overload_test.z_w);
    }
}
