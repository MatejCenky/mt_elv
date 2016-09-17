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
public class overload {
    
//    /**
//     * @param spans array containing all spans in the suspension section [n-1 dim]
//     * @param heights array containing all conductor catching points on towers [n dim]
//     */
//    public overload(double spans[], double heights[]){
//        overload.a = spans;
//        overload.dh = heights;
//        overload.ro = 1.25;
//        overload.RR = 0;
//        overload.k_p = 3;
//        overload.C_cl = 1.1;
//    }
//    
//    /**
//     * default constructor with "-1" values for a[0] and dh[0]
//     */
//    public overload(){
//        overload.a[0] = -1;
//        overload.dh[0] = -1;
//        overload.ro = 1.25;
//        overload.RR = 0;
//        overload.k_p = 3;
//        overload.C_cl = 1.1;
//    }
   
 /* Defining variables */
  
// basic variables
    
    /**
     * array of the spans on the suspension section [m]
     */
    private static double a[]; 
    
    /**
     * array of height differences of the suspension points of towers [m]
     */
    private static double dh[]; 
    
// towers and conductors
    private static double d     ;           // conductor diameter !!!!!! [m] !!!!!!
    private static double g_c   ;         // specific weight of conductor [N/m]
    
// type of ice
    private static double ro_I   ;        // density of the ice (= 500 kg/m3)
    
// icing area
    private static double K_lc   ;        // local conditions coefficient (čl.4.5.1/SK.3) [-]
    private static double K_h    ;         // the height coefficient čl.4.5.1/SK.3) [-]
    private static double I_R50  ;       // reference ice load -> chosen from local icing area (čl.4.5.1/SK.3)
    
// terrain and wind area
    private static double k_r    ;         // terrain coefficient (čl. 4.3.2) [-]
    private static double z_0    ;         // length of the roughness (čl. 4.3.2) [-]
    private static double V_b0   ;        // base wind speed (čl. 4.3.1/SK.1) [m/s]
    private static double c_dir  ;       // wind direction coefficient [-]
    private static double c_0    ;         // orography coefficient [-]
    private static double C_c   ;         // aerodynamic resistance of the conductor coefficient (čl. 4.4.1.3/SK.1) [-]
    
// directive constants 
    private static final double k_p = 3;   // tip coefficient (= 3) (čl. 4.4.1.2)
    private static final double RR = 0;    // resonance response coefficient (RR = R^2 = 0) (čl. 4.4.12)
    private static final double ro = 1.25;    // density of the wind (= 1.25)
    private static final double C_cl  = 1.1;        // aerodynamic resistance for conductor with ice coefficient (= 1,1) [-]


// reliability coefficient
    private static double gama_w     ;      // partial wind load coefficient [-]
    private static double gama_I     ;      // partial ice load coefficient [-]
    private static double Psi_w      ;       // combination coefficient (čl. 4.6.6/SK/CZ) [-]
    private static double Psi_I      ;       // combination ice load coefficient [-]
    private static double B_I        ;         // combination coefficient (čl. 4.6.6/SK/CZ) [-]
    
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
    
    /**
     * set variables of conductor from main frame
     * @param X conductor object
     */
    public static void set_variables_from_conductor(Object[] X){
       overload.d=Double.valueOf(String.valueOf(X[1])); // diameter of conductor
       //=Double.valueOf(String.valueOf(X[2])); //cross-section area of the conductor [mm^2]      
       overload.g_c=Double.valueOf(String.valueOf(X[3]))*9.80655; //weight of the conductor per unit [kg/m] -> converted into g_c [N/m]
       //=Double.valueOf(String.valueOf(X[4])); //Young model of elasticity of conductor [MPa]
       //=Double.valueOf(String.valueOf(X[5])); //linear expansion coefficient [1/degree_C]
       //=Double.valueOf(String.valueOf(X[6])); // RTS
       //=Double.valueOf(String.valueOf(X[7])); // Fe/AlFe
    }
    
    /**
     * checks if all variables /inputs/ are set correctly from mainframe
     */
    public void check_variables(){
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
    public void null_variables(){
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
    
    public void compute(){
        // check if variables are set
        check_variables();
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
        // null inputs
        null_variables();
    }
    
// **************** PRIVATE METHODS **************** //
    /**
     * computes the height coefficient "K_h" (čl.4.5.1/SK.3) [-]
     */
    private void height_coefficient(){
        overload.K_h = Math.pow(overload.h_c_mean/10,0.13);
    }
    
    /**
     * Computes the mean height of the conductor "h_c_mean" above the ground [m]
     */
    private void mean_height(){
        double aux = 0;
        int i;
        
        // sum of the double array
        for (i=0; i<overload.dh.length; i++){ //[n+1]
            aux += overload.dh[i];
        }
        overload.h_c_mean = aux/overload.a.length; // [n]
    }
    
    /**
     * Computes the mean wind speed "V_h" using the values chosen from the user (from mainframe)
     */
    private void mean_wind_speed() {
        overload.V_h = overload.V_b0*overload.c_dir*overload.c_0*overload.k_r*Math.log(overload.h_c_mean/overload.z_0);
    }
    
    /**
     * Computes the turbulence intensity "I_v"
     */
    private void turbulence_intensity(){
        overload.I_v = 1 / (overload.c_0*Math.log(overload.h_c_mean/overload.z_0));
    }
    
    /**
     * Computes the length for response origin coefficient "L_m" [m]
     */
    private void response_coefficient_length(){
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
    private void specific_turbulence_length(){
        overload.L = 300 * Math.pow(overload.h_c_mean/200, 0.67 + 0.05*Math.log(overload.z_0));
    }
    
    /**
     * Computes the response origin coefficient "BB = B^2" [m]
     */
    private void response_coefficient (){
        overload.BB = 1 / (1 + 1.5*(overload.L_m/overload.L));
    }
    
    /**
     * Computes the construction coefficient "G_c"
     */
    private void construcion_coefficient(){
        overload.G_c = (1 + 2*overload.k_p*overload.I_v*Math.sqrt(overload.BB + overload.RR))/(1 + 7*overload.I_v);
    }
    
    /**
     * Computes characteristic wind load "q_wc"
     */
    private void characteristic_wind_load(){
        overload.q_wc = overload.q_p* overload.d* overload.C_c* overload.G_c;
    }
    
    /**
     * Computes the peak wind pressure "q_p"
     */
    private void peak_wind_pressure(){
        overload.q_p = (1 + 7* overload.I_v)* overload.q_h;
    }
    
    /**
     * Computes the mean wind pressure "q_h"
     */
    private void mean_wind_pressure(){
        overload.q_h = 1/2 * overload.ro * Math.pow(overload.V_h, 2);
    }
    
    /**
     * Computes the characteristic ice load "I_50"
     */
    private void characteristic_ice_load(){
        overload.I_50 = overload.I_R50* overload.K_h* overload.K_lc;
    }
    
    /**
     * Computes the extreme ice load "I_T"
     */
    private void extreme_ice_load(){
        overload.I_T = overload.I_50* overload.gama_I;
    }
    
    /**
     * Computes the extreme wind load "q_wT"
     */
    private void extreme_wind_load(){
        overload.q_wT = overload.q_wc* overload.gama_w;
    }
    
    /**
     * Computes the mild ice load "I_3"
     */
    private void mild_ice_load(){
        overload.I_3 = overload.I_50* overload.Psi_I;
    }
    
    /**
     * Computes equivalent diameter for conductor with: 
     * - extreme ice "D_I" 
     * - mild ice "D_i"
     */
    private void equivalent_diameter_with_ice(){
        overload.D_I = Math.sqrt(overload.d* overload.d + (4*overload.I_T)/(9.80665*Math.PI* overload.ro_I));   // extreme
        overload.D_i = Math.sqrt(overload.d* overload.d + (4*overload.I_3)/(9.80665*Math.PI* overload.ro_I));   // mild
    }
    
    /**
     * Computes combined load for:
     * - mild wind with extreme ice "q_wI3"
     * - extreme wind with mild ice "q_wIT"
     */
    private void combined_load(){
        overload.q_wI3 = overload.q_p* overload.D_I* overload.C_cl* overload.G_c* overload.Psi_w;
        overload.q_wIT = overload.q_p* overload.D_i* overload.C_cl* overload.G_c* overload.B_I* overload.B_I;
    }
    
    /**
     * Computes extreme ice overload on the conductor "z_I"
     */
    private void overload_extreme_ice(){
        overload.z_I = (overload.I_T + overload.g_c) / overload.g_c;
    }
    
    /**
     * Computes combined overload on the conductor with:
     * - mild wind with extreme ice "z_Iw"
     * - extreme wind with mild ice "z_iW"
     */
    private void overload_combined(){
        overload.z_Iw = Math.sqrt((Math.pow(overload.I_T + overload.g_c,2) + overload.q_wI3*overload.q_wI3)) / overload.g_c;
        overload.z_iW = Math.sqrt((Math.pow(overload.I_3 + overload.g_c,2) + overload.q_wIT*overload.q_wIT)) / overload.g_c;
    }
    
    /**
     * Computes extreme wind overload in conductor "z_W"
     */
    private void overload_extreme_wind(){
        overload.z_W = Math.sqrt((overload.q_wT* overload.q_wT + overload.g_c* overload.g_c)) / overload.g_c;
    }
}
