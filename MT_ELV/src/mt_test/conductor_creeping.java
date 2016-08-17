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
public class conductor_creeping {
    
    /**
     * Computes the weight of the steel in the conductor "g_Fe" (if not known)
     * @param S_Fe cross-section of the steel in the conductor [mm^2]
     * @param ro_Fe specific weight of steel [kg/m^3] (usually 7840-7870)
     * @param g gravitational acceleration [m/s^2] (= 9.80655)
     * @return weight of the steel in the conductor [N/m]
     */
    public double steel_weight(double S_Fe, double ro_Fe, double g){
        return S_Fe*ro_Fe*g;
    }
    
    /**
     * Computes the proportional weight of steel in the conductor "w_Fe"
     * @param g_Fe specific weight of steel part of conductor [N/m]
     * @param g_c specific weight of conductor [N/m]
     * @return proportional weight of steel in the conductor [-]
     */
    public double proportional_steel_weight(double g_Fe, double g_c){
        return g_Fe/g_c;
    }
    
    /**
     * Computes the conductor composition coefficient "k_w"
     * @param w_Fe proportional weight of steel in the conductor [-]
     * @return conductor composition coefficient [-]
     */
    public double conductor_composition_coefficient(double w_Fe){
        return 1.212 - 1.06*w_Fe;
    }
    
    /**
     * Computes the average year load influence coefficient on the conductor "k_EDS"
     * @param sigma_HT horizontal part of load in mean year temperature [MPa]
     *                  -> computes through state equation using average year temperature
     * @param S cross-section of a conductor [mm^2]
     * @param RTS Rated Tensile Parameter [N]
     * @return average year load influence coefficient [-]
     */
    public double avg_load_coefficient(double sigma_HT, double S, double RTS){
        return 0.0319 * Math.pow(100*sigma_HT*S / RTS, 1.15);
    }
    
    /**
     * Computes the average year temperature influence coefficient of the conductor "k_EDT"
     * @param T_EDT average year temperature [degreeC]
     * @return average year temperature influence coefficient [-]
     */
    public double avg_temperature_coefficient(double T_EDT){
        return 0.842 + 0.0079*T_EDT;
    }
    
    /**
     * Computes the initial thermal shift "dT_0"
     * @param alpha linear expansion coefficient [1/degreeC]
     * @param k_EDS average year load influence coefficient [-]
     * @param k_EDT average year temperature influence coefficient [-]
     * @param k_w conductor composition coefficient [-]
     * @param fi specific creeping [mm/km*h] (= 28.2)
     * @param t_0 time to final state of conductor creeping [h] (usually 30y = 262800h)
     * @return initial thermal shift [d_degreeC]
     */
    public double thermal_shift_intitial(double alpha, double k_EDS, double k_EDT, double k_w, double fi, double t_0){
        return (-1/(alpha*1e6)) * k_EDS * k_EDT * k_w * fi * t_0;
    }
    
    /**
     * Computes the transient thermal shift "dT_0"
     * @param alpha linear expansion coefficient [1/degreeC]
     * @param k_EDS average year load influence coefficient [-]
     * @param k_EDT average year temperature influence coefficient [-]
     * @param k_w conductor composition coefficient [-]
     * @param fi specific creeping [mm/km*h] (= 28.2)
     * @param t_0 time to final state of conductor creeping [h] (usually 30y = 262800h)
     * @param t_p time from the construction of the line - check various time stages on the line [h]
     * @return 
     */
    public double thermal_shift_transient(double alpha, double k_EDS, double k_EDT, double k_w, double fi, double t_0, double t_p){
        return (-1/(alpha*1e6)) * k_EDS * k_EDT * k_w * fi * (t_0-t_p);
    }
    
    
}
