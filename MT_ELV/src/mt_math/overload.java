/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_math;

import mt_main.MyException;
import mt_variables.Overload_variables;

/**
 *
 * @author Mattto
 */
public class overload {
    
 /* Defining variables */
    
// towers and conductors [#1]
    private static double d     ;           // conductor diameter !!!!!! [m] !!!!!!
    private static double g_c   ;         // specific weight of conductor [N/m]
    private static double a[];              // array of the spans on the suspension section [m]
    
// type of ice [#2]
    private static double ro_I   ;        // density of the ice (= 500 kg/m3)
    
// icing area [#2]
    private static double K_lc   ;        // local conditions coefficient (čl.4.5.1/SK.3) [-]
    private static double K_h    ;         // the height coefficient čl.4.5.1/SK.3) [-]
    private static double I_R50  ;       // reference ice load -> chosen from local icing area (čl.4.5.1/SK.3)
    
// terrain and wind area [#3]
    private static double h_c_mean;    // mean height of the conductor above the ground [m] 
    private static double k_r    ;         // terrain coefficient (čl. 4.3.2) [-]
    private static double z_0    ;         // length of the roughness (čl. 4.3.2) [-]
    private static double V_b0   ;        // base wind speed (čl. 4.3.1/SK.1) [m/s]
    private static double c_dir  ;       // wind direction coefficient [-]
    private static double c_0    ;         // orography coefficient [-]
    private static double C_c   ;         // aerodynamic resistance of the conductor coefficient (čl. 4.4.1.3/SK.1) [-]
    
// directive constants [set by user !!!!! - or manually in code !!!!!!]
    private static double k_p;          // tip coefficient (= 3) (čl. 4.4.1.2)
    private static double RR;           // resonance response coefficient (RR = R^2 = 0) (čl. 4.4.12)
    private static double ro;           // density of the wind (= 1.25)
    private static double C_cl;        // aerodynamic resistance for conductor with ice coefficient (= 1,1) [-]


// reliability coefficient [#3]
    private static double gama_w     ;      // partial wind load coefficient [-]
    private static double gama_I     ;      // partial ice load coefficient [-]
    private static double Psi_w      ;       // combination coefficient (čl. 4.6.6/SK/CZ) [-]
    private static double Psi_I      ;       // combination ice load coefficient [-]
    private static double B_I        ;         // combination coefficient (čl. 4.6.6/SK/CZ) [-]
    
// computed values [computed - not set]
        
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
    
    private static double D_I;         // equivalent diameter for conductor with extreme ice [m]
    private static double D_i;         // equivalent diameter for conductor with mild ice [m]
    
// results
    /* LOADS */
    /**
     * extreme ice load
     */
    public static double I_T;         
    
    /**
     * extreme wind load
     */
    public static double q_wT;        
    
    /**
     * mild ice load
     */
    public static double I_3;        
    
    /**
     * combined load for mild wind with extreme ice
     */
    public static double q_wI3;      
    
    /**
     * combined load for extreme wind with mild ice
     */
    public static double q_wIT;      
    
    /* OVERLOADS */
    /**
     * extreme ice overload
     */
    public static double z_I;
    
    /**
     * combined overload on the conductor with mild wind and extreme ice
     */
    public static double z_Iw;  
    
    /**
     * combined overload on the conductor with mild ice and extreme wind
     */
    public static double z_iW;  
    
    /**
     * extreme wind overload
     */
    public static double z_W;          
    
// **************** PUBLIC METHODS **************** //

    public static void set_all_variables(Overload_variables Variables, double[] Spans){
        set_variables_spans(Spans);
        set_variables(Variables);
    }
      
    /**
     * checks if all variables /inputs/ are set correctly from mainframe
     */
    public static void check_variables(){
        try {
            if (overload.d == -1111.0000){
                System.out.println(overload.d + "not set");
                throw new MyException("Variable set error");
            } else if (overload.g_c == -1111.0000){
                System.out.println(overload.g_c + "not set");
                throw new MyException("Variable set error");
            } else if (overload.ro_I == -1111.0000){
                System.out.println(overload.ro_I + "not set");
                throw new MyException("Variable set error");
            } else if (overload.K_lc == -1111.0000){
                System.out.println(overload.K_lc + "not set");
                throw new MyException("Variable set error");
            } else if (overload.K_h == -1111.0000){
                System.out.println(overload.K_h + "not set");
                throw new MyException("Variable set error");
            } else if (overload.I_R50 == -1111.0000){
                System.out.println(overload.I_R50 + "not set");
                throw new MyException("Variable set error");
            } else if (overload.k_r == -1111.0000){
                System.out.println(overload.k_r + "not set");
                throw new MyException("Variable set error");
            } else if (overload.z_0 == -1111.0000){
                System.out.println(overload.z_0 + "not set");
                throw new MyException("Variable set error");
            } else if (overload.V_b0 == -1111.0000){
                System.out.println(overload.V_b0 + "not set");
                throw new MyException("Variable set error");
            } else if (overload.c_dir == -1111.0000){
                System.out.println(overload.c_dir + "not set");
                throw new MyException("Variable set error");
            } else if (overload.c_0 == -1111.0000){
                System.out.println(overload.c_0 + "not set");
                throw new MyException("Variable set error");
            } else if (overload.C_c == -1111.0000){
                System.out.println(overload.C_c + "not set");
                throw new MyException("Variable set error");
            } else if (overload.gama_w == -1111.0000){
                System.out.println(overload.gama_w + "not set");
                throw new MyException("Variable set error");
            } else if (overload.gama_I == -1111.0000){
                System.out.println(overload.gama_I + "not set");
                throw new MyException("Variable set error");
            } else if (overload.Psi_I == -1111.0000){
                System.out.println(overload.Psi_I + "not set");
                throw new MyException("Variable set error");
            } else if (overload.Psi_w == -1111.0000){
                System.out.println(overload.Psi_w + "not set");
                throw new MyException("Variable set error");
            } else if (overload.B_I == -1111.0000){
                System.out.println(overload.B_I + "not set");
                throw new MyException("Variable set error");
            }
        } catch (MyException e){}
    }
    
    /**
     * null the variables /inputs/ from mainframe - results remain untouched
     */
    public static void null_variables(){
        overload.d = -1111.0000;
        overload.g_c = -1111.0000;
        overload.ro_I = -1111.0000;
        overload.K_lc = -1111.0000;
        overload.K_h = -1111.0000;
        overload.I_R50 = -1111.0000;
        overload.k_r = -1111.0000;
        overload.z_0 = -1111.0000;
        overload.V_b0 = -1111.0000;
        overload.c_dir = -1111.0000;
        overload.c_0 = -1111.0000;
        overload.C_c = -1111.0000;
        overload.gama_w = -1111.0000;
        overload.gama_I = -1111.0000;
        overload.Psi_I = -1111.0000;
        overload.Psi_w = -1111.0000;
        overload.B_I = -1111.0000;
    }
    
    public static void compute(){
        // #1 layer
//        mean_height();
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
        // null inputs
        null_variables();
    }
    
// **************** PRIVATE METHODS **************** //
    
        // **************** SETTERS **************** //

    /**
     * set the spans [#1]
     * @param X - double[] array of spans
     */
    private static void set_variables_spans(double[] X){                         
        // assign a[] from mainframe
         overload.a = X;
    }
    
    /**
     * set all other variables connected with overload class
     * @param var overload_variables class object
     */
    private static void set_variables(Overload_variables var){
        overload.d = var.get_d();               // conductor diameter !!!!!! [m] !!!!!!
        overload.g_c = var.get_g_c();           // specific weight of conductor [N/m]
        overload.h_c_mean = var.get_h_c_mean();
        
        overload.ro_I = var.get_ro_I();         // density of the ice (= 500 kg/m3)
        overload.K_lc = var.get_K_lc();         // local conditions coefficient (čl.4.5.1/SK.3) [-]
        overload.K_h = var.get_K_h();          // the height coefficient čl.4.5.1/SK.3) [-]
        overload.I_R50 = var.get_I_R50();        // reference ice load -> chosen from local icing area (čl.4.5.1/SK.3)
        
        overload.k_r = var.get_k_r();         // terrain coefficient (čl. 4.3.2) [-]
        overload.z_0 = var.get_z_0();         // length of the roughness (čl. 4.3.2) [-]
        overload.V_b0 = var.get_V_b0();        // base wind speed (čl. 4.3.1/SK.1) [m/s]
        overload.c_dir = var.get_c_dir();       // wind direction coefficient [-]
        overload.c_0 = var.get_c_0();         // orography coefficient [-]
        overload.C_c = var.get_C_c();        // aerodynamic resistance of the conductor coefficient (čl. 4.4.1.3/SK.1) [-] 
        
        overload.gama_w = var.get_gama_w();     // partial wind load coefficient [-]
        overload.gama_I = var.get_gama_I();     // partial ice load coefficient [-]
        overload.Psi_I = var.get_Psi_I();      // combination ice load coefficient [-]
        overload.Psi_w = var.get_Psi_w();      // combination wind load coefficient (čl. 4.6.6/SK/CZ) [-]
        overload.B_I = var.get_B_I();        // combination coefficient (čl. 4.6.6/SK/CZ) [-]
        
        overload.k_p = var.get_k_p();           // tip coefficient (= 3) (čl. 4.4.1.2)
        overload.RR = var.get_RR();             // resonance response coefficient (RR = R^2 = 0) (čl. 4.4.12)
        overload.ro = var.get_ro();             // density of the wind (= 1.25)
        overload.C_cl = var.get_C_cl();         // aerodynamic resistance for conductor with ice coefficient (= 1,1) [-]
       
    } 
    
    
        // **************** OTHER METHODS **************** //

    
    /**
     * computes the height coefficient "K_h" (čl.4.5.1/SK.3) [-]
     */
    private static void height_coefficient(){
        overload.K_h = Math.pow(overload.h_c_mean/10,0.13);
    }
    
    /**
     * Computes the mean wind speed "V_h" using the values chosen from the user (from mainframe)
     */
    private static void mean_wind_speed() {
        overload.V_h = overload.V_b0*overload.c_dir*overload.c_0*overload.k_r*Math.log(overload.h_c_mean/overload.z_0);
    }
    
    /**
     * Computes the turbulence intensity "I_v"
     */
    private static void turbulence_intensity(){
        overload.I_v = 1 / (overload.c_0*Math.log(overload.h_c_mean/overload.z_0));
    }
    
    /**
     * Computes the length for response origin coefficient "L_m" [m]
     */
    private static void response_coefficient_length(){
        double aux = 0;
        for (int i=0; i<overload.a.length; i++){
            aux += overload.a[i];
        }
        // max 3km = 3000m
        if (aux > 3000){
            aux = 3000;
        }
        overload.L_m = aux;
    }
    
    /**
     * Computes the specific length for turbulence "L" [m]
     */
    private static void specific_turbulence_length(){
        overload.L = 300 * Math.pow(overload.h_c_mean/200, 0.67 + 0.05*Math.log(overload.z_0));
    }
    
    /**
     * Computes the response origin coefficient "BB = B^2" [m]
     */
    private static void response_coefficient (){
        overload.BB = 1 / (1 + 1.5*(overload.L_m/overload.L));
    }
    
    /**
     * Computes the construction coefficient "G_c"
     */
    private static void construcion_coefficient(){
        overload.G_c = (1 + 2*overload.k_p*overload.I_v*Math.sqrt(overload.BB + overload.RR))/(1 + 7*overload.I_v);
    }
    
    /**
     * Computes characteristic wind load "q_wc"
     */
    private static void characteristic_wind_load(){
        overload.q_wc = overload.q_p* overload.d* overload.C_c* overload.G_c;
    }
    
    /**
     * Computes the peak wind pressure "q_p"
     */
    private static void peak_wind_pressure(){
        overload.q_p = (1 + 7* overload.I_v)* overload.q_h;
    }
    
    /**
     * Computes the mean wind pressure "q_h"
     */
    private static void mean_wind_pressure(){
        double tmp = overload.ro;
        double tmp2 = Math.pow(overload.V_h, 2);
        overload.q_h = (tmp* tmp2)/2;
        //overload.q_h = ((1/2)* overload.ro* Math.pow(overload.V_h, 2));
    }
    
    /**
     * Computes the characteristic ice load "I_50"
     */
    private static void characteristic_ice_load(){
        overload.I_50 = overload.I_R50* overload.K_h* overload.K_lc;
    }
    
    /**
     * Computes the extreme ice load "I_T"
     */
    private static void extreme_ice_load(){
        overload.I_T = overload.I_50* overload.gama_I;
    }
    
    /**
     * Computes the extreme wind load "q_wT"
     */
    private static void extreme_wind_load(){
        overload.q_wT = overload.q_wc* overload.gama_w;
    }
    
    /**
     * Computes the mild ice load "I_3"
     */
    private static void mild_ice_load(){
        overload.I_3 = overload.I_50* overload.Psi_I;
    }
    
    /**
     * Computes equivalent diameter for conductor with: 
     * - extreme ice "D_I" 
     * - mild ice "D_i"
     */
    private static void equivalent_diameter_with_ice(){
        overload.D_I = Math.sqrt(overload.d* overload.d + (4*overload.I_T)/(9.80665*Math.PI* overload.ro_I));   // extreme
        overload.D_i = Math.sqrt(overload.d* overload.d + (4*overload.I_3)/(9.80665*Math.PI* overload.ro_I));   // mild
    }
    
    /**
     * Computes combined load for:
     * - mild wind with extreme ice "q_wI3"
     * - extreme wind with mild ice "q_wIT"
     */
    private static void combined_load(){
        overload.q_wI3 = overload.q_p* overload.D_I* overload.C_cl* overload.G_c* overload.Psi_w;
        overload.q_wIT = overload.q_p* overload.D_i* overload.C_cl* overload.G_c* overload.B_I* overload.B_I;
    }
    
    /**
     * Computes extreme ice overload on the conductor "z_I"
     */
    private static void overload_extreme_ice(){
        overload.z_I = (overload.I_T + overload.g_c) / overload.g_c;
    }
    
    /**
     * Computes combined overload on the conductor with:
     * - mild wind with extreme ice "z_Iw"
     * - extreme wind with mild ice "z_iW"
     */
    private static void overload_combined(){
        overload.z_Iw = Math.sqrt((Math.pow(overload.I_T + overload.g_c,2) + overload.q_wI3*overload.q_wI3)) / overload.g_c;
        overload.z_iW = Math.sqrt((Math.pow(overload.I_3 + overload.g_c,2) + overload.q_wIT*overload.q_wIT)) / overload.g_c;
    }
    
    /**
     * Computes extreme wind overload in conductor "z_W"
     */
    private static void overload_extreme_wind(){
        overload.z_W = Math.sqrt((overload.q_wT* overload.q_wT + overload.g_c* overload.g_c)) / overload.g_c;
    }
}
