/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_main;

import mt_math.Math_Extended;

/**
 *
 * @author Mattto
 */
public class state_equation_test {

    /** 
     * @param terrain 1 == flat [MSF] // else == terrain [MST]
     * 
     * Values a[0] and dh[0] set to "-1" as default;
     */
    public state_equation_test(int terrain){
        state_equation_test.ter = terrain;
        //state_equation_test.a[0] = -1;
        //state_equation_test.dh[0] = -1;
    }
    
    /** 
     * @param terrain 1 == flat [MSF] // else == terrain [MST]
     * @param spans array containing all spans in the suspension section [n-1 dim]
     * @param heights array containing all conductor catching points on towers [n dim]
     */
    public state_equation_test(int terrain, double spans[], double heights[]){
        state_equation_test.ter = terrain;
        state_equation_test.a = spans;
        state_equation_test.dh = heights;
    }

 /* Defining variables */
    
// basic variables
    
    /**
     * array of the spans on the suspension section [m]
     */
    private static double a[] = new double[]{300}; 
    
    /**
     * array of height differences of the suspension points of towers [m]
     */
    private static double dh[] = new double[]{0}; 
    
    /**
     * specific gravity of the conductor [N/m*mm^2]
     */
    private static double gama;  
    
    /**
     * weight of the conductor per unit [kg/m]
     */
    private static double m = 0.9801; 
    
    /**
     * cross-section area of the conductor [mm^2]
     */
    private static double S = 282.5;  
    
    /**
     * Young model of elasticity of conductor [MPa]
     */
    private static double E = 77000;  
    
    /**
     * linear expansion coefficient [1/degree_C]
     */
    private static double alpha = 18.9e6;    
    
    /**
     * gravitational acceleration [m.s^-2]
     */
    private static double g = 9.80665; 
    
// values for state equation
    private static int ter;            // specify flat / non-flat terrain - in constructor !!!;
    private static double MSF;         // /average/ span of flat suspension section [m] 
    private static double MST;         // /average/ span of terrain suspension section [m] 
    private static double Bc;          // coefficient B in cubic equation
    private static double Dc;          // coefficient D in cubic equation
    private static double z_0;         // conductor overload in state "0" [-]
    private static double z_1;         // conductor overload in state "1" [-]
    private static double theta_1;     // conductor temperature in state "1"
    private static double theta_0;     // conductor temperature in state "0"
    private static double sigma_h0 = 70.796;    // horizontal stress in conductor in state "0" [MPa]

//values for state equation with imaginary temperatures
    private static double Bc_i;        // coefficient B in cubic equation with imaginary temperatures
    
// results
    /**
     * solution of a cubic equation == horizontal stress in conductor in state "1" [MPa]
     */
    public static double sigma_h1;    
    
    /**
     * the pulling force based on the horizontal stress of the conductor [N]
     */
    public static double F_mH1;      
    
    /**
     * the "c" parameter [m]
     */
    public static double c;        
    
    /**
     * maximum ideal sag [m]
     */
    public static double sag_max[];    
    
    /**
     * array of all visible sags in one suspension section [m]
     */
    public static double sag_vis[];  
    
// **************** PUBLIC METHODS **************** //    
    /**
     * Set all the variables needed for the computation of the state equation
     * from "mainframe" and also locally;
     * 
     * Not known values set to "-1" as a default value when error checking;
     * 
     */    
    public static void set_variables(){
        // public - results
        state_equation_test.F_mH1 = -1;
        state_equation_test.sigma_h1 = -1;
        state_equation_test.c = -1;
        state_equation_test.sag_max[0] = -1;
        state_equation_test.sag_vis[0] = -1;
        
        // local - results
        state_equation_test.gama = -1;
        state_equation_test.MSF = -1;
        state_equation_test.MST = -1;
        
        // mainframe - need to be done based on mainframe
        state_equation_test.m = -1;
        state_equation_test.S = -1;
        state_equation_test.a[0] = -1;
        state_equation_test.dh[0] = -1;
        state_equation_test.E = -1;
        state_equation_test.z_0 = -1;
        state_equation_test.z_1 = -1;
        state_equation_test.alpha = -1;
        state_equation_test.theta_0 = -1;
        state_equation_test.theta_1 = -1;
        state_equation_test.sigma_h0 = -1;
        //for example: state_equation.m = mainframe.jText_m.getText();
    }
    
    public static void get_variables(){
        // need to be done based on mainframe
    }
    
    /**
     * Computes the state equation using set variables; 
     * Variables need to be set BEFORE computation;
     */
    public static void compute_sigma_H1(){
        //basic check if variables are set
        if (state_equation_test.m == -1) {
            set_variables();
            System.out.println("Variables were set!");
        } 
        //preparing
        gama();
        mid_span_flat();
        mid_span_terrain();
        //cubic equation
        set_cubic_values();
        cubic_equation_solve();
        // results
        pulling_force();
        c_parameter();
        sag_maximum();
        sag_visible();
    }
    
    /**
     * @param T_EDT average year temperature [degreeC] - act as theta_1
     * @return sigma_HT
     */
    public static double compute_sigma_HT(double T_EDT){
        cubic_equation_coef_B_imaginary(theta_0, T_EDT);
        return cubic_equation_solve_imaginary();
    }
    
    /**
     * @param T_x0 imaginary temperature == theta_0
     * @param T_xp imaginary temperature == theta_1
     * @return T_0 == sigma_H1
     */
    public static double compute_sigma_Hvib(double T_x0, double T_xp){
        cubic_equation_coef_B_imaginary(T_x0, T_xp);
        return cubic_equation_solve_imaginary();
    }
    
    
// **************** PRIVATE METHODS **************** //
    /**
     * Set the cubic equation B,D coefficients values
     */
    private static void set_cubic_values(){
        cubic_equation_coef_B();
        cubic_equation_coef_D();
    }
    
    /**
     * Method computes the specific gravity of the conductor "gama".
     */
    private static void gama(){
        state_equation_test.gama = (state_equation_test.m *state_equation_test.g)/state_equation_test.S;
    }
    
    /**
     * Method computes middle span for flat terrain "MSF".
     */
    private static void mid_span_flat(){
        double cube = 0;
        double sum = 0;
        for (int i=0; i<state_equation_test.a.length; i++){
            cube = cube + Math.pow(state_equation_test.a[i],3);
            sum = sum + state_equation_test.a[i];
        }
        state_equation_test.MSF = Math.sqrt(cube/sum);
    }
    
    /**
     * Method computes middle span for non-flat terrain "MST".
     */
    private static void mid_span_terrain(){
        double upper = 0;
        double lower = 0;
        double temp;
        for (int i=0; i<state_equation_test.a.length; i++){
            temp = Math.sqrt(Math.pow(state_equation_test.a[i],2)+ Math.pow(state_equation_test.dh[i], 2));
            upper = upper + Math.pow(state_equation_test.a[i], 4)/temp;
            lower = lower + temp;
        }
        state_equation_test.MST = Math.sqrt(upper/lower);
    }
    
    /**
     * Computes the cubic equation in the form: A*x^3 + B*x^2 + C*x + D = 0
     * Defined are only B,D coefficient -> sufficient to solve
     */
    private static void cubic_equation_solve(){
        double part1;
        double part2;
        double part3;
        double condition;
        double result;
        
        condition = Math.pow(-1* Math.pow(state_equation_test.Bc, 2)/9, 3) + Math.pow(-1* (27* state_equation_test.Dc + 2* Math.pow(state_equation_test.Bc, 3)) /54, 2);
        
        if (0 < condition) {
            part1 = Math.cbrt((-1*(27*state_equation_test.Dc+2*Math.pow(state_equation_test.Bc, 3))/54) - Math.sqrt(condition));
            part2 = Math.cbrt((-1*(27*state_equation_test.Dc+2*Math.pow(state_equation_test.Bc, 3))/54) + Math.sqrt(condition)) - state_equation_test.Bc/3;
            result = part1 + part2;
        } else {
            part1 = Math.cbrt(-1*Math.pow(-1*Math.pow(state_equation_test.Bc, 2)/9, 3));
            part2 = Math.acos(-1*(27*state_equation_test.Dc+2*Math.pow(state_equation_test.Bc, 3))/54/part1);
            part3 = Math.cos(part2/3) - state_equation_test.Bc/3;
            result = 2 * part1 * part3;
        }
        state_equation_test.sigma_h1 = result;
    }

    
    /**
     * Computes the B coefficient which is input to the cubic equation
     */
    private static void cubic_equation_coef_B(){
        double var;
        if (state_equation_test.ter == 1){
            var = state_equation_test.MSF;
        } else {
            var = state_equation_test.MST;
        }
        state_equation_test.Bc =  Math.pow(state_equation_test.gama,2) * state_equation_test.E/24 * Math.pow(var*state_equation_test.z_0/state_equation_test.sigma_h0, 2) + state_equation_test.alpha*state_equation_test.E*(state_equation_test.theta_1 - state_equation_test.theta_0) - state_equation_test.sigma_h0;
    }
   
    /**
     * Computes the D coefficient which is input to the cubic equation [also with imaginary temperatures (Dc_i == Dc)]
     */
    private static void cubic_equation_coef_D(){
        double var;
        if (state_equation_test.ter == 1){
            var = state_equation_test.MSF;
        } else {
            var = state_equation_test.MST;
        }
        state_equation_test.Dc = Math.pow(state_equation_test.gama,2)*state_equation_test.E/24 * Math.pow(var*state_equation_test.z_1, 2);
    }
    
    /**
     * Computes the pulling force based on the horizontal stress of the conductor "F_mH"
     */
    private static void pulling_force(){
        state_equation_test.F_mH1 = state_equation_test.sigma_h1 * state_equation_test.S;
    }
    
    /**
     * Computes the "c" parameter of the conductor shape /sag/
     */
    private static void c_parameter(){
        state_equation_test.c = state_equation_test.sigma_h1 / (state_equation_test.gama * state_equation_test.z_1);
    }
    
    /**
     * Computes the maximum sag for ideal conditions in one suspension section
     */
    private static void sag_maximum(){
        for (int i=0; i<state_equation_test.a.length; i++) {
        state_equation_test.sag_max[i] = state_equation_test.c* (Math.cosh(state_equation_test.a[i]/(2*state_equation_test.c)) - 1);
        }
    }
    
    /**
     * Computes the array of all visible sags in one suspension section. 
     */
    private static void sag_visible(){
        double part1[] = new double[state_equation_test.a.length];
        double part2;
        double part3;

        for (int i=0; i<state_equation_test.a.length; i++) {
            part1[i] = state_equation_test.a[i]/(2*state_equation_test.c);
            part2 = part1[i] + Math_Extended.asinh(state_equation_test.dh[i]/(2*state_equation_test.c*Math.sinh(part1[i])));
            part3 = Math_Extended.asinh(state_equation_test.dh[i]/state_equation_test.a[i]);
            state_equation_test.sag_vis[i] = state_equation_test.c*(Math.cosh(part2) - Math.cosh(part3) - (state_equation_test.dh[i]/state_equation_test.a[i])*(part2 - part3));
        }
    }
    
     /**
     * Computes the B coefficient which is input to the cubic equation with imaginary temperatures
     */
    private static void cubic_equation_coef_B_imaginary(double t0, double tp){
        double var;
        if (state_equation_test.ter == 1){
            var = state_equation_test.MSF;
        } else {
            var = state_equation_test.MST;
        }
        state_equation_test.Bc_i =  Math.pow(state_equation_test.gama,2) * state_equation_test.E/24 * Math.pow(var*state_equation_test.z_0/state_equation_test.sigma_h0, 2) + state_equation_test.alpha*state_equation_test.E*(tp - t0) - state_equation_test.sigma_h0;
    }
    

    /**
     * Computes the cubic equation in the form: A*x^3 + B*x^2 + C*x + D = 0 with imaginary temperatures!!
     * Defined are only B,D coefficient -> sufficient to solve
     * Storing the result into the conductor_creeping variable!!
     */
    private static double cubic_equation_solve_imaginary(){
        double part1;
        double part2;
        double part3;
        double condition;
        double result;
        
        condition = Math.pow(-1*Math.pow(state_equation_test.Bc_i, 2)/9, 3) + Math.pow(-1*(27*state_equation_test.Dc+2*Math.pow(state_equation_test.Bc_i, 3))/54,2);
        
        if (0 < condition) {
            part1 = Math.cbrt((-1*(27*state_equation_test.Dc+2*Math.pow(state_equation_test.Bc_i, 3))/54) - Math.sqrt(condition));
            part2 = Math.cbrt((-1*(27*state_equation_test.Dc+2*Math.pow(state_equation_test.Bc_i, 3))/54) + Math.sqrt(condition)) - state_equation_test.Bc_i/3;
            result = part1 + part2;
        } else {
            part1 = Math.cbrt(-1*Math.pow(-1*Math.pow(state_equation_test.Bc_i, 2)/9, 3));
            part2 = Math.acos(-1*(27*state_equation_test.Dc+2*Math.pow(state_equation_test.Bc_i, 3))/54/part1);
            part3 = Math.cos(part2/3) - state_equation_test.Bc_i/3;
            result = 2 * part1 * part3;
        }
        return result;
    }
}
