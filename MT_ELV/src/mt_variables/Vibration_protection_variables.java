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
public class Vibration_protection_variables {
    
    // vibration protection variables
    private static double c_vib_vib;
    private static double EQ_vib_vib;
    private static double d_vib;
    private static double g_c_vib;
    private static double L_vib;
    private static double T_0_vib;
    private static double x_vib;
    private static double y_vib;
      
    /**
     * constructor vibration protection variables
     * @param Conductor Conductor_variables class object with containing conductor properties
     * @param c_vib catenary parameter for specific terrain
     * @param EQ_vib auxiliary parameter for specific terrain
     * @param g_c specific weight of conductor [N/m]
     * @param L L == a[i]; specific span from the suspension section
     * @param T_0 similar as sigma_H1 but with specific initial values
     * @param x x axis
     * @param y y axis
     */
    public Vibration_protection_variables(  Conductor_variables Conductor,
                                            double c_vib,
                                            double EQ_vib,
                                            double g_c,
                                            double L,
                                            double T_0,
                                            double x,
                                            double y){
        
        Vibration_protection_variables.c_vib_vib = c_vib;
        Vibration_protection_variables.EQ_vib_vib = EQ_vib;
        Vibration_protection_variables.d_vib = Conductor.get_d();
        Vibration_protection_variables.g_c_vib = c_vib;
        Vibration_protection_variables.L_vib = L;
        Vibration_protection_variables.T_0_vib = T_0;
        Vibration_protection_variables.x_vib = x;
        Vibration_protection_variables.y_vib = y;
    }
    
    /**
     * setting vibration protection variables
     * @param Conductor Conductor_variables class object with containing conductor properties
     * @param c_vib catenary parameter for specific terrain
     * @param EQ_vib auxiliary parameter for specific terrain
     * @param g_c specific weight of conductor [N/m]
     * @param L L == a[i]; specific span from the suspension section
     * @param T_0 similar as sigma_H1 but with specific initial values
     * @param x x axis
     * @param y y axis
     */
    public static void set_variables(   Conductor_variables Conductor,
                                        double c_vib,
                                        double EQ_vib,
                                        double g_c,
                                        double L,
                                        double T_0,
                                        double x,
                                        double y){
        
        Vibration_protection_variables.c_vib_vib = c_vib;
        Vibration_protection_variables.EQ_vib_vib = EQ_vib;
        Vibration_protection_variables.d_vib = Conductor.get_d();
        Vibration_protection_variables.g_c_vib = c_vib;
        Vibration_protection_variables.L_vib = L;
        Vibration_protection_variables.T_0_vib = T_0;
        Vibration_protection_variables.x_vib = x;
        Vibration_protection_variables.y_vib = y;
    }
    
    public double get_c_vib(){
        return Vibration_protection_variables.c_vib_vib;
    }
    
    public double get_EQ_vib(){
        return Vibration_protection_variables.EQ_vib_vib;
    }
    
    public double get_d_vib(){
        return Vibration_protection_variables.d_vib;
    }
    
    public double get_g_c(){
        return Vibration_protection_variables.g_c_vib;
    }
    
    public double get_L(){
        return Vibration_protection_variables.L_vib;
    }
    
    public double get_T_0(){
        return Vibration_protection_variables.T_0_vib;
    }
    
    public double get_x(){
        return Vibration_protection_variables.x_vib;
    }
    
    public double get_y(){
        return Vibration_protection_variables.y_vib;
    }
}
