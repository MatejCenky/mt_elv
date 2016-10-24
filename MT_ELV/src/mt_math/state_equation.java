/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_math;

import mt_main.MyException;
import mt_variables.Conductor_variables;
import mt_variables.State_equation_variables;

/**
 *
 * @author Mattto
 */
public class state_equation {

 /* Defining variables */
    
// basic variables
    
    /**
     * array of the spans on the suspension section [m]
     */
    private static double a[]; 
    
    /**
     * array of height differences of the suspension points of towers [m]
     */
    private static double dh[]; 
    
    /**
     * specific gravity of the conductor [N/m*mm^2]
     */
    private static double gama;  
    
    /**
     * weight of the conductor per unit [kg/m]
     */
    private static double m; 
    
    /**
     * cross-section area of the conductor [mm^2]
     */
    private static double S;  
    
    /**
     * Young model of elasticity of conductor [MPa]
     */
    private static double E;  
    
    /**
     * linear expansion coefficient [1/degree_C]
     */
    private static double alpha;    
    
    /**
     * gravitational acceleration [m.s^-2]
     */
    private static final double g = 9.80665; 
    
// values for state equation
    private static double mid_span;    // /average/ span of suspension section [m] 
    private static double Bc;          // coefficient B in cubic equation
    private static double Dc;          // coefficient D in cubic equation
    private static double z_0;         // conductor overload in state "0" [-]
    private static double z_1;         // conductor overload in state "1" [-]
    private static double theta_1;     // conductor temperature in state "1"
    private static double theta_0;     // conductor temperature in state "0"
    private static double sigma_h0;    // horizontal stress in conductor in state "0" [MPa]

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
    
    public static void set_all_variables(   State_equation_variables Variables,
                                            double[] spans,
                                            double[] heights){
        set_variables(Variables);
        set_variables_spans(spans);
        set_variables_heights(heights);
    }

    /**
     * checks if all variables /inputs/ are set correctly from mainframe
     */
    public static void check_variables(){
        try {
            if (state_equation.m == -1111.0000){
                System.out.println(state_equation.m + " state_equation not set");
                throw new MyException("Variable set error in state equation class");
            } else if (state_equation.S == -1111.0000){
                System.out.println(state_equation.S + " state_equation not set");
                throw new MyException("Variable set error in state equation class");
            } else if (state_equation.E == -1111.0000){
                System.out.println(state_equation.E + " state_equationnot set");
                throw new MyException("Variable set error in state equation class");
            } else if (state_equation.alpha == -1111.0000){
                System.out.println(state_equation.alpha + " state_equation not set");
                throw new MyException("Variable set error in state equation class");
            } else if (state_equation.theta_1 == -1111.0000){
                System.out.println(state_equation.theta_1 + " state_equationnot set");
                throw new MyException("Variable set error in state equation class");
            } else if (state_equation.theta_0 == -1111.0000){
                System.out.println(state_equation.theta_0 + " state_equation not set");
                throw new MyException("Variable set error in state equation class");
            } else if (state_equation.sigma_h0 == -1111.0000){
                System.out.println(state_equation.sigma_h0 + " state_equation not set");
                throw new MyException("Variable set error in state equation class");
            } else if (state_equation.gama == -1111.0000){
                System.out.println(state_equation.gama + " state_equationnot set");
                throw new MyException("Variable set error in state equation class");
            } else if (state_equation.z_1 == -1111.0000){
                System.out.println(state_equation.gama + " state_equation not set");
                throw new MyException("Variable set error in state equation class");
            }
        } catch (MyException e){}
    }
    
    /**
     * null the variables /inputs/ from mainframe and partial results 
     * - final results remain untouched
     */
    public static void null_variables(){
        state_equation.gama = -1111.0000;
        state_equation.m = -1111.0000;
        state_equation.S = -1111.0000;
        state_equation.E = -1111.0000;
        state_equation.alpha = -1111.0000;
        state_equation.mid_span = -1111.0000;
        state_equation.Bc = -1111.0000;
        state_equation.Dc = -1111.0000;
        state_equation.z_1 = -1111.0000;
        state_equation.theta_1 = -1111.0000;
        state_equation.theta_0 = -1111.0000;
        state_equation.sigma_h0 = -1111.0000;
    }
    
    /**
     * Computes the state equation using set variables; 
     * Variables need to be set BEFORE computation;
     * @param load overload z_1 on the conductor
     * @param mid_span /average/ span of suspension section [m] 
     */
    public static void compute_sigma_H( double load, 
                                        double mid_span){
        //preparing
        state_equation.mid_span = mid_span;
        state_equation.z_1 = load;
        gama();
        check_variables();
        //cubic equation
        set_cubic_values();
        cubic_equation_solve();
        // results
        pulling_force();
        c_parameter();
        sag_maximum();
        sag_visible();
        // null variables and partial results
//        null_variables();
    }
    
    /**
     * Computes the state equation using set variables; 
     * Variables need to be set BEFORE computation;
     * @param load overload z_1 on the conductor
     * @param mid_span /average/ span of suspension section [m] 
     * @return  sigma_H as double
     */
    public static double compute_sigma_H_value( double load, 
                                                double mid_span){
        //preparing
        state_equation.mid_span = mid_span;
        state_equation.z_1 = load;
        gama();
        check_variables();
        //cubic equation
        set_cubic_values();
        return cubic_equation_solve_value();
    }
    
    /**
     * @param T_EDT average year temperature [degreeC] - act as theta_1
     * @param load overload z_1 on the conductor
     * @param mid_span /average/ span of suspension section [m] 
     * @return sigma_HT
     */
    public static double compute_sigma_H(   double load, 
                                            double mid_span,
                                            double T_EDT){
        //preparing
        state_equation.mid_span = mid_span;
        state_equation.z_1 = load;
        gama();
        check_variables();
        //cubic equation
        set_cubic_values();
        cubic_equation_coef_B_imaginary(theta_0, T_EDT);
        // results - variables are nulled before return statement
        return cubic_equation_solve_imaginary();
    }
    
    /**
     * @param T_x0 imaginary temperature == theta_0
     * @param T_xp imaginary temperature == theta_1
     * @param load overload z_1 on the conductor
     * @param mid_span /average/ span of suspension section [m] 
     * @return T_0 == sigma_H1
     */
    public static double compute_sigma_H(   double load, 
                                            double mid_span,
                                            double T_x0, 
                                            double T_xp){
        //preparing
        state_equation.mid_span = mid_span;
        state_equation.z_1 = load;
        gama();
        check_variables();
        //cubic equation
        set_cubic_values();
        cubic_equation_coef_B_imaginary(T_x0, T_xp);
        // results - variables are nulled before return statement
        return cubic_equation_solve_imaginary();
    }
    
    public static double compute_c(double sigma_h1, double pretazenie, Conductor_variables Conductor){
        return c_parameter_value(sigma_h1, pretazenie, (Conductor.get_m()*state_equation.g)/Conductor.get_S());
    }
    
    public static double compute_Fh(double sigma_h1, Conductor_variables Conductor){
        return pulling_force_value(sigma_h1, Conductor);
    }
    
    public static double[] compute_sag_vis(double[] spans, double c, double[] dh){
        return sag_visible_value(spans, c, dh);
    }
    
    public static double[] compute_sag_max(double[] spans, double c){
        return sag_maximum_value(spans, c);
    }
    
    /**
     * Computes the maximum and visible SAGs for every span
     * in the suspension section. Spans array a[] need to be defined!
     * Public results in sag_max[] and sag_vis[] in this class
     */
    public static void compute_sags(){
        sag_maximum();
        sag_visible();
    }
    
    
// **************** PRIVATE METHODS **************** //
    
    
    // **************** SETTERS **************** //
    /**
     *
     * @param var
     */
    private static void set_variables(State_equation_variables var){
        state_equation.m = var.get_m();
        state_equation.S = var.get_S();
        state_equation.E = var.get_E();
        state_equation.alpha = var.get_alpha();
        state_equation.theta_1 = var.get_theta_1();
        state_equation.theta_0 = var.get_theta_0();
        state_equation.sigma_h0 = var.get_sigma_h0();
        state_equation.z_0 = var.get_z_0();
    }
    
    /**
     * set the spans
     * @param X - double[] array of spans
     */
    private static void set_variables_spans(double[] X){                         
        // assign a[] from mainframe
            state_equation.a = X;
    }
    
    /**
     * set the difference of heights
     * @param X - double[] array of spans
     */
    private static void set_variables_heights(double[] X){                         
        // assign a[] from mainframe
            state_equation.dh = X;
    }
    
    // **************** OTHER METHODS **************** //
    
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
        state_equation.gama = (state_equation.m *state_equation.g)/state_equation.S;
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
        
        condition = Math.pow(-1* Math.pow(state_equation.Bc, 2)/9, 3) + Math.pow(-1* (27* state_equation.Dc + 2* Math.pow(state_equation.Bc, 3)) /54, 2);
        
        if (0 < condition) {
            part1 = Math.cbrt((-1*(27*state_equation.Dc+2*Math.pow(state_equation.Bc, 3))/54) - Math.sqrt(condition));
            part2 = Math.cbrt((-1*(27*state_equation.Dc+2*Math.pow(state_equation.Bc, 3))/54) + Math.sqrt(condition)) - state_equation.Bc/3;
            result = part1 + part2;
        } else {
            part1 = Math.cbrt(-1*Math.pow(-1*Math.pow(state_equation.Bc, 2)/9, 3));
            part2 = Math.acos(-1*(27*state_equation.Dc+2*Math.pow(state_equation.Bc, 3))/54/part1);
            part3 = Math.cos(part2/3) - state_equation.Bc/3;
            result = 2 * part1 * part3;
        }
        state_equation.sigma_h1 = result;
    }
    
    /**
     * Computes the cubic equation in the form: A*x^3 + B*x^2 + C*x + D = 0
     * Defined are only B,D coefficient -> sufficient to solve
     */
    private static double cubic_equation_solve_value(){
        double part1;
        double part2;
        double part3;
        double condition;
        double result;
        
        condition = Math.pow(-1* Math.pow(state_equation.Bc, 2)/9, 3) + Math.pow(-1* (27* state_equation.Dc + 2* Math.pow(state_equation.Bc, 3)) /54, 2);
        
        if (0 < condition) {
            part1 = Math.cbrt((-1*(27*state_equation.Dc+2*Math.pow(state_equation.Bc, 3))/54) - Math.sqrt(condition));
            part2 = Math.cbrt((-1*(27*state_equation.Dc+2*Math.pow(state_equation.Bc, 3))/54) + Math.sqrt(condition)) - state_equation.Bc/3;
            result = part1 + part2;
        } else {
            part1 = Math.cbrt(-1*Math.pow(-1*Math.pow(state_equation.Bc, 2)/9, 3));
            part2 = Math.acos(-1*(27*state_equation.Dc+2*Math.pow(state_equation.Bc, 3))/54/part1);
            part3 = Math.cos(part2/3) - state_equation.Bc/3;
            result = 2 * part1 * part3;
        }
        return result;
    }

    /**
     * Computes the B coefficient which is input to the cubic equation
     */
    private static void cubic_equation_coef_B(){
        double var = mid_span;
//        if (state_equation.ter == 1){
//            var = state_equation.MSF;
//        } else {
//            var = state_equation.MST;
//        }
        state_equation.Bc =  Math.pow(state_equation.gama,2) * state_equation.E/24 * Math.pow(var*state_equation.z_0/state_equation.sigma_h0, 2) + state_equation.alpha*state_equation.E*(state_equation.theta_1 - state_equation.theta_0) - state_equation.sigma_h0;
    }
   
    /**
     * Computes the D coefficient which is input to the cubic equation [also with imaginary temperatures (Dc_i == Dc)]
     */
    private static void cubic_equation_coef_D(){
        double var = mid_span;
//        if (state_equation.ter == 1){
//            var = state_equation.MSF;
//        } else {
//            var = state_equation.MST;
//        }
        state_equation.Dc = (-1)*Math.pow(state_equation.gama,2)*state_equation.E/24 * Math.pow(var*state_equation.z_1, 2);
    }
    
    /**
     * Computes the pulling force based on the horizontal stress of the conductor "F_mH"
     */
    private static void pulling_force(){
        state_equation.F_mH1 = state_equation.sigma_h1 * state_equation.S;
    }
    
    /**
     * Computes the "c" parameter of the conductor shape /sag/
     */
    private static void c_parameter(){
        state_equation.c = state_equation.sigma_h1 / (state_equation.gama * state_equation.z_1);
    }
    
    /**
     * Computes the maximum sag for ideal conditions in one suspension section
     */
    private static void sag_maximum(){
        for (int i=0; i<state_equation.a.length; i++) {
        state_equation.sag_max[i] = state_equation.c* (Math.cosh(state_equation.a[i]/(2*state_equation.c)) - 1);
        }
    }
    
    /**
     * Computes the array of all visible sags in one suspension section. 
     */
    private static void sag_visible(){
        double part1[] = new double[state_equation.a.length];
        double part2;
        double part3;

        for (int i=0; i<state_equation.a.length; i++) {
            part1[i] = state_equation.a[i]/(2*state_equation.c);
            part2 = part1[i] + Math_Extended.asinh(state_equation.dh[i]/(2*state_equation.c*Math.sinh(part1[i])));
            part3 = Math_Extended.asinh(state_equation.dh[i]/state_equation.a[i]);
            state_equation.sag_vis[i] = state_equation.c*(Math.cosh(part2) - Math.cosh(part3) - (state_equation.dh[i]/state_equation.a[i])*(part2 - part3));
        }
    }
    
     /**
     * Computes the B coefficient which is input to the cubic equation with imaginary temperatures
     */
    private static void cubic_equation_coef_B_imaginary(double t0, double tp){
        double var = mid_span;
//        if (state_equation.ter == 1){
//            var = state_equation.MSF;
//        } else {
//            var = state_equation.MST;
//        }
        state_equation.Bc_i =  Math.pow(state_equation.gama,2) * state_equation.E/24 * Math.pow(var*state_equation.z_0/state_equation.sigma_h0, 2) + state_equation.alpha*state_equation.E*(tp - t0) - state_equation.sigma_h0;
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
        
        condition = Math.pow(-1*Math.pow(state_equation.Bc_i, 2)/9, 3) + Math.pow(-1*(27*state_equation.Dc+2*Math.pow(state_equation.Bc_i, 3))/54,2);
        
        if (0 < condition) {
            part1 = Math.cbrt((-1*(27*state_equation.Dc+2*Math.pow(state_equation.Bc_i, 3))/54) - Math.sqrt(condition));
            part2 = Math.cbrt((-1*(27*state_equation.Dc+2*Math.pow(state_equation.Bc_i, 3))/54) + Math.sqrt(condition)) - state_equation.Bc_i/3;
            result = part1 + part2;
        } else {
            part1 = Math.cbrt(-1*Math.pow(-1*Math.pow(state_equation.Bc_i, 2)/9, 3));
            part2 = Math.acos(-1*(27*state_equation.Dc+2*Math.pow(state_equation.Bc_i, 3))/54/part1);
            part3 = Math.cos(part2/3) - state_equation.Bc_i/3;
            result = 2 * part1 * part3;
        }
//        null_variables();
        return result;
    }
    
    /**
     * Computes the pulling force based on the horizontal stress of the conductor "F_mH"
     */
    private static double pulling_force_value(double sigma_H1, Conductor_variables Conductor){
        return sigma_H1* Conductor.get_S();
    }
    
    /**
     * Computes the "c" parameter of the conductor shape /sag/ - gama and z_1 are get straight from state_equation class!
     */
    private static double c_parameter_value(double sigma_H1, double pretazenie, double gama){
        return sigma_H1 / (gama* pretazenie);
    }
    
    /**
     * Computes the maximum sag for ideal conditions in one suspension section
     */
    private static double[] sag_maximum_value(double[] spans, double c){
        double[] result = new double[spans.length];
        for (int i=0; i<spans.length; i++) {
        result[i] = c* (Math.cosh(spans[i]/(2*c)) - 1);
        }
        return result;
    }
    
    /**
     * Computes the array of all visible sags in one suspension section. 
     */
    private static double[] sag_visible_value(double[] spans, double c, double[] dh){
        double result[] = new double[spans.length];
        double part1[] = new double[spans.length];
        double part2;
        double part3;
        
        for (int i=0; i<spans.length; i++) {
            part1[i] = spans[i]/(2*c);
            part2 = part1[i] + Math_Extended.asinh(dh[i]/(2*c*Math.sinh(part1[i])));
            part3 = Math_Extended.asinh(dh[i]/spans[i]);
            result[i] = c*(Math.cosh(part2) - Math.cosh(part3) - (dh[i]/spans[i])*(part2 - part3));
        }
        
        return result;
    }
}


