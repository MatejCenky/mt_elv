/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by the author Matej Cenky.
 */
package mt_math;

/**
 *
 * @author Mattto
 */
public class Math_Extended {
    
    public static double asinh(double x) 
    { 
    return Math.log(x + Math.sqrt(x*x + 1.0)); 
    } 

    public static double acosh(double x) 
    { 
    return Math.log(x + Math.sqrt(x*x - 1.0)); 
    } 

    public static double atanh(double x) 
    { 
    return 0.5*Math.log( (x + 1.0) / (x - 1.0) ); 
    } 
}
