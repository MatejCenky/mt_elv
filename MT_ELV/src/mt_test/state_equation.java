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
public class state_equation {

  
    /**
     * Function computes the specific gravity of the conductor.
     * @param m weight of the conductor per unit [kg/m]
     * @param S cross-section area of the conductor [mm^2]
     * @return specific gravity of the conductor [N/m*mm^2]
     */
    public static double gama (double m, double S){
        return (m*9.80655)/S;
    }
    
    /**
     * Function computes middle span for flat terrain.
     * @param a array of the spans on the suspension section [m]
     * @return middle /average/ span of the suspension section [m]
     */
    public double mid_span_flat(double a[]){
        double cube = 0;
        double sum = 0;
        for (int i=0; i<a.length; i++){
            cube = cube + Math.pow(a[i],3);
            sum = sum + a[i];
        }
        return Math.sqrt(cube/sum);
    }
    
    /**
     * Function computes middle span for non-flat terrain.
     * @param a array of the spans on the suspension section [m]
     * @param dh array of height differences of the suspension points of towers [m]
     * @return middle /average/ span of the suspension section [m]
     */
    public double mid_span_terrain(double a[], double dh[]){
        double upper = 0;
        double lower = 0;
        double temp;
        for (int i=0; i<a.length; i++){
            temp = Math.sqrt(Math.pow(a[i],2)+Math.pow(dh[i], 2));
            upper = upper + Math.sqrt(Math.pow(a[i], 4)/temp);
            lower = lower + temp;
        }
        return Math.sqrt(upper/lower);
    }
    
    
}
