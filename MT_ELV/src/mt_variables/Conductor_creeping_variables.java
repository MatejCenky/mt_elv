/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_variables;

/**
 *
 * @author Mattto
 */
public class Conductor_creeping_variables {
    
    // conductor creeping variables
    private static double w_Fe_creep;
    private static double S_creep;
    private static double g_c_creep;
    private static double RTS_creep;
    private static double T_EDT_creep;
    private static double alpha_creep;
    private static double t_0_creep;
    private static double t_p_creep;
    
    /**
     * constructor for conductor creeping variables
     * @param Conductor - Variables_conductor class object
     * @param g_c specific weight of conductor [N/m]
     * @param T_EDT average year temperature [degreeC]
     * @param t_0 time to final state of conductor creeping [h] (usually 30y = 262800h)
     * @param t_p time from the construction of the line - check various time stages on the line [h]
     */
    public Conductor_creeping_variables(Conductor_variables Conductor,
                                        double g_c,
                                        double T_EDT,
                                        double t_0,
                                        double t_p){
        
        Conductor_creeping_variables.S_creep = Conductor.get_S();
        Conductor_creeping_variables.RTS_creep = Conductor.get_RTS();
        Conductor_creeping_variables.alpha_creep = Conductor.get_alpha();
        Conductor_creeping_variables.w_Fe_creep = Conductor.get_w_Fe();
        Conductor_creeping_variables.g_c_creep = g_c;
        Conductor_creeping_variables.T_EDT_creep = T_EDT;
        Conductor_creeping_variables.t_0_creep = t_0;
        Conductor_creeping_variables.t_p_creep = t_p;
    }
    
    /**
     * setting conductor creeping variables
     * @param Conductor - Variables_conductor class object
     * @param g_c specific weight of conductor [N/m]
     * @param T_EDT average year temperature [degreeC]
     * @param t_0 time to final state of conductor creeping [h] (usually 30y = 262800h)
     * @param t_p time from the construction of the line - check various time stages on the line [h]
     */
    public static void set_variables(   Conductor_variables Conductor,
                                        double g_c,
                                        double T_EDT,
                                        double t_0,
                                        double t_p){
        
        Conductor_creeping_variables.S_creep = Conductor.get_S();
        Conductor_creeping_variables.RTS_creep = Conductor.get_RTS();
        Conductor_creeping_variables.alpha_creep = Conductor.get_alpha();
        Conductor_creeping_variables.w_Fe_creep = Conductor.get_w_Fe();
        Conductor_creeping_variables.g_c_creep = g_c;
        Conductor_creeping_variables.T_EDT_creep = T_EDT;
        Conductor_creeping_variables.t_0_creep = t_0;
        Conductor_creeping_variables.t_p_creep = t_p;
    }
    
    public double get_S(){
        return Conductor_creeping_variables.S_creep;
    }
    
    public double get_RTS(){
        return Conductor_creeping_variables.RTS_creep;
    }
    
    public double get_alpha(){
        return Conductor_creeping_variables.alpha_creep;
    }
    
    public double get_w_Fe(){
        return Conductor_creeping_variables.w_Fe_creep;
    }
    
    public double get_g_c(){
        return Conductor_creeping_variables.g_c_creep;
    }
    
    public double get_T_EDT(){
        return Conductor_creeping_variables.T_EDT_creep;
    }
    
    public double get_t_0(){
        return Conductor_creeping_variables.t_0_creep;
    }
    
    public double get_t_p(){
        return Conductor_creeping_variables.t_p_creep;
    }
}
