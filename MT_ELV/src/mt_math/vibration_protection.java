/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_math;

import mt_variables.Conductor_variables;

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

// **************** PUBLIC METHODS **************** //    
    
    /**
     * Computes the vibration protection area
     * @param x axis value
     * @param y axis value
     * @param c_vib 
     * @param EQ_vib
     * @return the safety category 1-3
     */
    public static double evaluate_protection_area(double x, double y, double c_vib, double EQ_vib){
        
        if (x <= c_vib){
            return 1; 
            // basic safe area
        } else {
            if (y <= 1.5 && y <= EQ_vib){
                return 2;
                // safe area
            } else {
                return 3;
                // specific requirements needed
            }
        }
    }
    
    /**
     * Class is used for computation of the vibration protection
     * - it is checking the safety for the spans
     * @param terrain [1-4] - define the terrain
     *      1 - open flat terrain, without trees, with snow, near / over water areas
     *      2 - open flat terrain, without trees, without snow
     *      3 - open flat / rough terrain, few trees, occasional breaks / barriers
     *      4 - built-up terrain with trees and buildings, forests, fields with bushes
     * @return c vibration [arameter
     */
    public static double c_vib_value(int terrain){
        switch(terrain){
            case 1:
                return 1000;
            case 2:
                return 1125;
            case 3:
                return 1225;
            case 4:
                return 1425;
            default:
                return 0;
        }
    }
   
    /**
     * Computes the y axis
     * @param L span
     * @param Conductor conductor variables
     * @return axis y
     */
    public static double axis_y_value(double L, Conductor_variables Conductor){
        return L* Conductor.get_d() / (Conductor.get_m()*9.80665);
    }
    
    /**
     * Computes the x axis
     * @param T0 horizontal stress
     * @param Conductor conductor variables
     * @return x axis
     */
    public static double axis_x_value(double T0, Conductor_variables Conductor){
        return T0* Conductor.get_S() / (Conductor.get_m()*9.80665);
    }
    
    public static double EQvib_value(int terrain, double T0, Conductor_variables Conductor){
        switch(terrain){
            case 1:
                return (1.3* Math.pow(10, 27)) / Math.pow(T0* Conductor.get_S() / (Conductor.get_m()*9.80665), 8.2);
            case 2:
                return (5.4* Math.pow(10, 27)) / Math.pow(T0* Conductor.get_S() / (Conductor.get_m()*9.80665), 8.3);
            case 3:
                return (1.3* Math.pow(10, 28)) / Math.pow(T0* Conductor.get_S() / (Conductor.get_m()*9.80665), 8.4);
            case 4:
                return (1.1* Math.pow(10, 29)) / Math.pow(T0* Conductor.get_S() / (Conductor.get_m()*9.80665), 8.6);
            default:
                return 0;
        }
    }
}
