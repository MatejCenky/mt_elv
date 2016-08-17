/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by the author Matej Cenky.
 */
package mt_test;

import mt_math.Math_Extended;

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
    
    /**
     * Computes the cubic equation in the form: A*x^3 + B*x^2 + C*x + D = 0
     * @param A [double]
     * @param B [double]
     * @param C [double]
     * @param D [double]
     * @return the result of the cubic equation [double]
     */
    public double cubic_equation_solve(double A, double B, double C, double D){
        double part1;
        double part2;
        double part3;
        double condition;
        double result;
        
        condition = Math.pow(-1*Math.pow(B, 2)/9, 3) + Math.pow(-1*(27*D+2*Math.pow(B, 3)/54),2);
        
        if (0 < condition) {
            part1 = Math.cbrt((-1*(27*D+2*Math.pow(B, 3)/54)) - Math.sqrt(condition));
            part2 = Math.cbrt((-1*(27*D+2*Math.pow(B, 3)/54)) + Math.sqrt(condition)) - B/3;
            result = part1 + part2;
        } else {
            part1 = Math.cbrt(-1*Math.pow(-1*Math.pow(B, 2)/9, 3));
            part2 = Math.acos(-1*(27*D+2*Math.pow(B, 3)/54)/part1);
            part3 = Math.cos(part2/3) - B/3;
            result = 2 * part1 * part3;
        }
        return result;
    }
    
    /**
     * Computes the A coefficient which is input to the cubic equation
     * @return coefficient A [double]
     */
    public double cubic_equation_coef_A(){
        return 1;
    }
    
    /**
     * Computes the B coefficient which is input to the cubic equation
     * @param gama specific gravity of the conductor [N/m*mm^2]
     * @param E Young model of conductor [MPa]
     * @param a span of the suspension section [m]
     * @param z_0 conductor overload in state "0" [-]
     * @param sigma_h0 horizontal stress in conductor in state "0" [MPa]
     * @param alfa linear expansion coefficient [1/degree_C]
     * @param teta_1 conductor temperature in state "1"
     * @param teta_0 conductor temperature in state "0"
     * @return coefficient B [double]
     */
    public double cubic_equation_coef_B(double gama, double E, double a, double z_0, double sigma_h0, double alfa, double teta_1, double teta_0){
        return Math.pow(gama,2)*E/24 * Math.pow(a*z_0/sigma_h0, 2) + alfa*E*(teta_1 - teta_0) - sigma_h0;
    }
    
    /**
     * Computes the C coefficient which is input to the cubic equation
     * @return coefficient C [double]
     */
    public double cubic_equation_coef_C(){
        return 0;
    }
    
    /**
     * Computes the D coefficient which is input to the cubic equation
     * @param gama specific gravity of the conductor [N/m*mm^2]
     * @param E Young model of conductor [MPa]
     * @param a span of the suspension section [m]
     * @param z_1 conductor overload in state "1" [-]
     * @return coefficient D [double]
     */
    public double cubic_equation_coef_D(double gama, double E, double a, double z_1){
        return Math.pow(gama,2)*E/24 * Math.pow(a*z_1, 2);
    }
    
    /**
     * Computes the pulling force based on the horizontal stress of the conductor
     * @param sigma_h1 horizontal stress in conductor in state "1" [MPa]
     * @param S cross-section area of the conductor [mm^2]
     * @return the pulling force of the conductor in state "1" [N]
     */
    public double pulling_force(double sigma_h1, double S){
        return sigma_h1 * S;
    }
    
    /**
     * Computes the "c" parameter of the conductor shape /sag/
     * @param sigma_h1 horizontal stress in conductor in state "1" [MPa]
     * @param gama specific gravity of the conductor [N/m*mm^2]
     * @param z_1 conductor overload in state "1" [-]
     * @return the "c" parameter [m]
     */
    public double c_parameter(double sigma_h1, double gama, double z_1){
        return sigma_h1 / (gama * z_1);
    }
    
    /**
     * Computes the maximum sag for ideal conditions
     * @param c the "c" parameter [m]
     * @param a span of the suspension section [m]
     * @return the maximum ideal sag [m]
     */
    public double sag_max(double c, double a){
        return c * (Math.cosh(a/(2*c)) - 1);
    }
    
    /**
     * Computes the visible sag of one span in one suspension section 
     * - need to be calculated for every span!
     * @param c the "c" parameter [m]
     * @param a span of the suspension section [m]
     * @param h height differences of the suspension points of towers [m]
     * @return the visible sag of one span in one suspension section [m]
     */
    public double sag_visible(double c, double a, double h){
        double part1;
        double part2;
        double part3;
        double part4;
        
        part1 = a/(2*c);
        part2 = part1 + Math_Extended.asinh(h/(2*c*Math.sinh(part1)));
        part3 = Math_Extended.asinh(h/a);
        part4 = Math.cosh(part2) - Math.cosh(part3) - (h/a)*(part2 - part3);
        
        return c*part4;
    }
}
