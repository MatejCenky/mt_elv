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
 * Protection of the conductor from the vibration induced by wind (STN EN 50341-1, cl. 9.2.4/SK.2)
 * Need to be calculated for EVERY SPAN in the suspension section. 
 * 
 * RESULT IS FOR ONE SPAN FOR ONE CALCULATION FROM THIS CLASS
 * 
 * Firstly the horizontal load from state equation needs to be calculated with parameters:
 * theta_0 = T_x,0 
 * theta_1 = T_X,p 
 * t_p = 1y = 8760h
 * T_x = -5 degreeC
 * 
 * @author Mattto
 */
public class vibration_protection {
    
    /**
     * Class is used for computation of the vibration protection
     * - it is checking the safety for the spans
     * @param terrain [1-4] - define the terrain
     *      1 - open flat terrain, without trees, with snow, near / over water areas
     *      2 - open flat terrain, without trees, without snow
     *      3 - open flat / rough terrain, few trees, occasional breaks / barriers
     *      4 - built-up terrain with trees and buildings, forests, fields with bushes
     */
    public vibration_protection(int terrain){
        vibration_protection.ter = terrain;
        switch(terrain){
            case 1:
                vibration_protection.c_vib = 1000;
            case 2:
                vibration_protection.c_vib = 1125;
            case 3:
                vibration_protection.c_vib = 1225;
            case 4:
                vibration_protection.c_vib = 1425;
        }
    }

/* Defining variables */

// constants
    private static double c_vib;        // catenary parameter for specific terrain
    private static double EQ_vib;       // auxiliary parameter for specific terrain
    private static double d;            // diameter of conductor [mm]
    private static double g_c;          // specific weight of conductor [N/m]
    private static double L;            // L == a[i]; specific span from the suspension section
    private static int ter;             // terrain
    
// calculation
    private static double T_0;          // similar as sigma_H1 but with specific initial values
    private static double y;            // y axis
    private static double x;            // x axis
    
// results
    /**
     * Value is in range [1-3] based on area 1 - 3.
     */
    public static int protection_area;  
    
// **************** PUBLIC METHODS **************** //    
    
    public static void set_variables(){
        // need to be done
    }
    
    public static void get_variables(){
        // need to be done
    }
    
    public static void compute(){
        imaginary_horizontal_stress();
        axis_x();
        axis_y();
        set_EQvib(vibration_protection.ter);
        
        if (vibration_protection.x <= vibration_protection.c_vib){
            vibration_protection.protection_area = 1; 
            // basic safe area
        } else {
            if (vibration_protection.y <= 1.5 && 
                vibration_protection.y <= vibration_protection.EQ_vib){
                vibration_protection.protection_area = 2;
                // safe area
            } else {
                vibration_protection.protection_area = 3;
                // specific requirements needed
            }
        }
    }
    
// **************** PRIVATE METHODS **************** //  
    
    /**
     * Computes T_0 using theory of conductor creeping and state equation
     */
    private static void imaginary_horizontal_stress(){
        // setting variables - with help of theory #3
        double T_x0 = -5 + conductor_creeping.compute_initial_thermal_shift_value();
        double T_xp = -5 + conductor_creeping.compute_transient_thermal_shift_value(8760);
        
        // computing state equation using specific initial conditions
        vibration_protection.T_0 = state_equation.compute_sigma_Hvib(T_x0, T_xp);
    }
    
    /**
     * Computes the y axis
     */
    private static void axis_y(){
        vibration_protection.y = vibration_protection.L* vibration_protection.d / vibration_protection.g_c;
    }
    
    /**
     * Computes the x axis
     */
    private static void axis_x(){
        vibration_protection.x = vibration_protection.T_0 / vibration_protection.g_c;
    }
    
    private static void set_EQvib(int terrain){
        switch(terrain){
            case 1:
                vibration_protection.EQ_vib = (1.3* Math.pow(10, 27)) / Math.pow(vibration_protection.T_0 / vibration_protection.g_c, 8.2);
            case 2:
                vibration_protection.EQ_vib = (5.4* Math.pow(10, 27)) / Math.pow(vibration_protection.T_0 / vibration_protection.g_c, 8.3);
            case 3:
                vibration_protection.EQ_vib = (1.3* Math.pow(10, 28)) / Math.pow(vibration_protection.T_0 / vibration_protection.g_c, 8.4);
            case 4:
                vibration_protection.EQ_vib = (1.1* Math.pow(10, 29)) / Math.pow(vibration_protection.T_0 / vibration_protection.g_c, 8.6);
        }
    }
    
    



    
    
}
