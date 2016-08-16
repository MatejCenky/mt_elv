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
     * computes the height coefficient "K_h" (čl.4.5.1/SK.3) [-]
     * @param h_c_mean mean height of the conductor above the ground [m]
     * @return the height coefficient K_h 
     */
    public double height_coefficient(double h_c_mean){
        return Math.pow(h_c_mean/10,0.13);
    }
    
    /**
     * Computes the mean height of the conductor "h_c_mean" above the ground [m]
     * @param h array of the heights of the conductor placing on each tower across the suspension section
     * @return mean height of the conductor above the ground [m]
     */
    public double mean_height(double h[]){
        double aux = 0;
        int i;
        
        // sum of the double array
        for (i=0; i<h.length; i++){
            aux += h[i];
        }
        
        return aux/h.length;
    }
    
    /**
     * Computes the mean wind speed "V_h" using the values chosen from the user (from mainframe)
     * @param V_b_0 base wind speed (čl. 4.3.1/SK.1) [m/s]
     * @param c_dir wind direction coefficient [-]
     * @param c_0 orography coefficient [-]
     * @param k_r terrain coefficient (čl. 4.3.2) [-]
     * @param h_c_mean mean height of the conductor above the ground [m]
     * @param z_0 length of the roughness (čl. 4.3.2) [-]
     * @return the mean wind speed [m/s]
     */
    public double mean_wind_speed(double V_b_0, double c_dir, double c_0, double k_r, double h_c_mean, double z_0) {
        return V_b_0*c_dir*c_0*k_r*Math.log(h_c_mean/z_0);
    }
    
    /**
     * Computes the turbulence intensity "I_v"
     * @param c_0 orography coefficient [-]
     * @param h_c_mean mean height of the conductor above the ground [m]
     * @param z_0 length of the roughness (čl. 4.3.2) [-]
     * @return the turbulence intensity
     */
    public double turbulence_intensity(double c_0, double h_c_mean, double z_0){
        return 1 / (c_0*Math.log(h_c_mean/z_0));
    }
    
    // !!!!!!!!!!! need to be checked
    
    /**
     * Computes the length for response origin coefficient "L_m" [m]
     * @param a array of spans of the suspension section [m]
     * @return the length for response origin coefficient [m]
     */
    public double response_coefficient_length(double a[]){
        double aux = 0;
        for (int i=0; i<a.length; i++){
            aux += a[i];
        }
        
        // max 3km = 3000m
        if (aux > 3000){
            aux = 3000;
        }
        return aux;
    }
    
    
    // need to be checked !!!!!!!!!!!! L = ?????
    
    /**
     * Computes the response origin coefficient "BB = B^2" [m]
     * @param L_m length for response origin coefficient [m]
     * @param L ???????
     * @return response origin coefficient [m]
     */
    public double response_coefficient (double L_m, double L){
        return 1 / (1 + 3/2 * L_m/L);
    }
    
    /**
     * Computes the construction coefficient "G_c"
     * @param k_p tip coefficient (= 3) (čl. 4.4.1.2)
     * @param I_v turbulence intensity
     * @param BB response origin coefficient (BB = B^2)
     * @param RR resonance response coefficient (RR = R^2 = 0) (čl. 4.4.12)
     * @return construction coefficient
     */
    public double construcion_coefficient(double k_p, double I_v, double BB, double RR){
        return (1 + 2*k_p*I_v*Math.sqrt(BB + RR))/(1 + 7*I_v);
    }
    
    /**
     * Computes characteristic wind load "q_wc"
     * @param q_p peak wind pressure
     * @param d conductor diameter [m]
     * @param C_c aerodynamic resistance of the conductor coefficient (čl. 4.4.1.3/SK.1) [-]
     * @param G_c construction coefficient
     * @return characteristic wind load
     */
    public double characteristic_wind_load(double q_p, double d, double C_c, double G_c){
        return q_p*d*C_c*G_c;
    }
    
    /**
     * Computes the peak wind pressure "q_p"
     * @param I_v turbulence intensity
     * @param q_h mean wind pressure
     * @return peak wind pressure
     */
    public double peak_wind_pressure(double I_v, double q_h){
        return (1 + 7*I_v)*q_h;
    }
    
    /**
     * Computes the mean wind pressure "q_h"
     * @param ro density of the wind (= 1.25)
     * @param V_h mean wind speed
     * @return mean wind pressure
     */
    public double mean_wind_pressure(double ro, double V_h){
        return 1/2 * ro * Math.pow(V_h, 2);
    }
    
    /**
     * Computes the characteristic ice load "I_50"
     * @param I_R50 reference ice load -> chosen from local icing area (čl.4.5.1/SK.3)
     * @param K_lc local conditions coefficient (čl.4.5.1/SK.3) [-]
     * @param K_h height coefficient (čl.4.5.1/SK.3) [-]
     * @return characteristic ice load
     */
    public double characteristic_ice_load(double I_R50, double K_lc, double K_h){
        return I_R50*K_h*K_lc;
    }
    
    /**
     * Computes the extreme ice load "I_T"
     * @param I_50 characteristic ice load
     * @param gama_I partial ice load coefficient
     * @return extreme ice load
     */
    public double extreme_ice_load(double I_50, double gama_I){
        return I_50*gama_I;
    }
    
    /**
     * Computes the extreme wind load "q_wT"
     * @param q_wc characteristic wind load
     * @param gama_w partial wind load coefficient [-]
     * @return extreme wind load
     */
    public double extreme_wind_load(double q_wc, double gama_w){
        return q_wc*gama_w;
    }
    
    /**
     * Computes the mild ice load "I_3"
     * @param I_50 characteristic ice load
     * @param psi_I combination ice load coefficient
     * @return mild ice load
     */
    public double mild_ice_load(double I_50, double psi_I){
        return I_50*psi_I;
    }
    
    /**
     * Computes equivalent diameter for conductor with a)extreme [D_I] / b)mild [D_i] ice
     * @param d diameter of the conductor [m]
     * @param I a)extreme [I_T] / b)mild [I_3] ice load
     * @param ro_I density of the ice (= 500 kg/m3)
     * @return equivalent diameter for conductor with ice
     */
    public double equivalent_diameter_with_ice(double d, double I, double ro_I){
        return Math.sqrt(d*d + (4*I)/(9.80665*Math.PI*ro_I));
    }
    
    /**
     * Computes combined load for:
     * a) mild wind with extreme ice "q_wI3"
     * b) extreme wind with mild ice "q_wIT"
     * @param q_p peak wind pressure
     * @param D equivalent diameter a)[D_I] / b)[D_i]
     * @param C_cl aerodynamic resistance for conductor with ice coefficient (= 1,1) [-]
     * @param G_c aerodynamic resistance of the conductor coefficient (čl. 4.4.1.3/SK.1) [-]
     * @param x combination coefficient a)[psi_w] / b)[(B_I)^2] (čl. 4.6.6/SK/CZ)
     * @return combined load
     */
    public double combined_load(double q_p, double D, double C_cl, double G_c, double x){
        return q_p*D*C_cl*G_c*x;
    }
    
    /**
     * Computes extreme ice overload on the conductor "z_1"
     * @param I_T extreme ice load
     * @param g_c specific gravity of conductor [N/m]
     * @return extreme ice overload
     */
    public double overload_extreme_ice(double I_T, double g_c){
        return (I_T + g_c) / g_c;
    }
    
    /**
     * Computes combined overload on the conductor for:
     * a) mild wind with extreme ice "z_Iw"
     * b) extreme wind with mild ice "z_Wi"
     * @param I a)extreme [I_T] / b)mild [I_3] ice load
     * @param g_c specific gravity of conductor [N/m]
     * @param q a)[q_wI3] / b)[q_wIT]
     * @return combined overload
     */
    public double overload_combined(double I, double g_c, double q){
        return Math.sqrt((Math.pow(I + g_c,2) + q*q)) / g_c;
    }
    
    /**
     * Computes extreme wind overload in conductor "z_W"
     * @param q_wT extreme wind load
     * @param g_c specific gravity of conductor [N/m]
     * @return extreme wind overload
     */
    public double overload_extreme_wind(double q_wT, double g_c){
        return Math.sqrt((q_wT*q_wT + g_c*g_c)) / g_c;
    }
}
