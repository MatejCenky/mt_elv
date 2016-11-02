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
 *
 * @author Matus
 */
public class forces_check {
 
    /**
     * 
     * @param x_AB
     * @param c
     * @param Conductor
     * @param load
     * @return 
     */
    public static double F_ABi(double x_AB, double c, Conductor_variables Conductor, double load){
        double gama = (Conductor.get_m()*9.80655)/Conductor.get_S();
        return c*Math.cosh(x_AB/c)*gama*load;
    }

    /**
     * 
     * @param dH difference of absolute heights
     * @param c parameter
     * @param Ai section
     * @return x_Ai -> result[0], x_Bi -> result[1]
     */
    public static double[] x_ABi(double dH, double c, double Ai){
        
        double x_Ai;
        
        if(dH<0){
            x_Ai = c* Math_Extended.asinh(Math.abs(dH)/(2*c*Math.sinh(Ai/(2*c)))) + Ai/2;
        } else {
            x_Ai = -c* Math_Extended.asinh(Math.abs(dH)/(2*c*Math.sinh(Ai/(2*c)))) + Ai/2;
        }
        
        double x_Bi = Ai - x_Ai;
        double[] result = new double[2];
        
        result[0] = x_Ai;
        result[1] = x_Bi;
        
        return result;
    }
    
}
