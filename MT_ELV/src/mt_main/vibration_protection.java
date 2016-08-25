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

    private static double c_vib;
    
}
