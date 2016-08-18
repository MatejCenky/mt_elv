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
     * @param terrain 1 == flat [MSF] // else == terrain [MST]
     * 
     * Values a[0] and dh[0] set to "-1" as default;
     */
    public state_equation(int terrain){
        this.ter = terrain;
        state_equation.a[0] = -1;
        state_equation.dh[0] = -1;
    }
    
    /** 
     * @param terrain 1 == flat [MSF] // else == terrain [MST]
     * @param spans array containing all spans in the suspension section [n-1 dim]
     * @param heights array containing all conductor catching points on towers [n dim]
     */
    public state_equation(int terrain, double spans[], double heights[]){
        this.ter = terrain;
        state_equation.a = spans;
        state_equation.dh = heights;
    }
    
    // basic global variables
    public static double a[];         // array of the spans on the suspension section [m]
    public static double dh[];        // array of height differences of the suspension points of towers [m]
    public double gama;        // specific gravity of the conductor [N/m*mm^2]
    public double m;           // weight of the conductor per unit [kg/m]
    public double S;           // cross-section area of the conductor [mm^2]
    public double E;           // Young model of elasticity of conductor [MPa]
    public double alpha;       // linear expansion coefficient [1/degree_C]
    // values for state equation
    private final int ter;      // specify flat / non-flat terrain - in constructor !!!;
    private double MSF;         // /average/ span of flat suspension section [m] 
    private double MST;         // /average/ span of terrain suspension section [m] 
    private double Ac;          // coefficient A in cubic equation
    private double Bc;          // coefficient B in cubic equation
    private double Cc;          // coefficient C in cubic equation
    private double Dc;          // coefficient D in cubic equation
    private double z_0;         // conductor overload in state "0" [-]
    private double z_1;         // conductor overload in state "1" [-]
    private double theta_1;     // conductor temperature in state "1"
    private double theta_0;     // conductor temperature in state "0"
    private double sigma_h0;    // horizontal stress in conductor in state "0" [MPa]
    // results
    private double sigma_h1;    // solution of a cubic equation == horizontal stress in conductor in state "1" [MPa]
    private double F_mH1;       // the pulling force based on the horizontal stress of the conductor [N]
    private double c;           // the "c" parameter [m]
    private double sag_max;     // maximum ideal sag [m]
    private double sag_vis[];   // array of all visible sags in one suspension section [m]
    
    /**
     * Set all the variables needed for the computation of the state equation
     * from "mainframe" and also locally;
     * 
     * Not known values set to "-1" as a default value when error checking;
     * 
     */    
    public void set_variables(){
        // local - results
        this.F_mH1 = -1;
        this.sigma_h1 = -1;
        this.c = -1;
        this.sag_max = -1;
        this.sag_vis[0] = -1;
        
        // local - sources
        this.gama = -1;
        this.MSF = -1;
        this.MST = -1;
        
        //mainframe - need to be added
        this.m = -1;
        this.S = -1;
        state_equation.a[0] = -1;
        state_equation.dh[0] = -1;
        this.E = -1;
        this.z_0 = -1;
        this.z_1 = -1;
        this.alpha = -1;
        this.theta_0 = -1;
        this.theta_1 = -1;
        this.sigma_h0 = -1;
        //for example: this.m = mainframe.jText_m.getText();
    }
    
    /**
     * Computes the state equation using set variables; 
     * Variables need to be set BEFORE computation;
     */
    public void compute(){
        //basic check if variables are set
        if (this.m == -1) {
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
     * Set the cubic equation ABCD coefficients values
     */
    private void set_cubic_values(){
        this.Ac = 1;
        cubic_equation_coef_B();
        this.Cc = 0;
        cubic_equation_coef_D();
    }
    
    /**
     * Method computes the specific gravity of the conductor "gama".
     */
    private void gama(){
        this.gama = this.m * this.S;
    }
    
    /**
     * Method computes middle span for flat terrain "MSF".
     */
    private void mid_span_flat(){
        double cube = 0;
        double sum = 0;
        for (int i=0; i<state_equation.a.length; i++){
            cube = cube + Math.pow(state_equation.a[i],3);
            sum = sum + state_equation.a[i];
        }
        this.MSF = Math.sqrt(cube/sum);
    }
    
    /**
     * Method computes middle span for non-flat terrain "MST".
     */
    private void mid_span_terrain(){
        double upper = 0;
        double lower = 0;
        double temp;
        for (int i=0; i<state_equation.a.length; i++){
            temp = Math.sqrt(Math.pow(state_equation.a[i],2)+Math.pow(state_equation.dh[i], 2));
            upper = upper + Math.sqrt(Math.pow(state_equation.a[i], 4)/temp);
            lower = lower + temp;
        }
        this.MST = Math.sqrt(upper/lower);
    }
    
    /**
     * Computes the cubic equation in the form: A*x^3 + B*x^2 + C*x + D = 0
     */
    private void cubic_equation_solve(){
        double part1;
        double part2;
        double part3;
        double condition;
        double result;
        
        condition = Math.pow(-1*Math.pow(this.Bc, 2)/9, 3) + Math.pow(-1*(27*this.Dc+2*Math.pow(this.Bc, 3)/54),2);
        
        if (0 < condition) {
            part1 = Math.cbrt((-1*(27*this.Dc+2*Math.pow(this.Bc, 3)/54)) - Math.sqrt(condition));
            part2 = Math.cbrt((-1*(27*this.Dc+2*Math.pow(this.Bc, 3)/54)) + Math.sqrt(condition)) - this.Bc/3;
            result = part1 + part2;
        } else {
            part1 = Math.cbrt(-1*Math.pow(-1*Math.pow(this.Bc, 2)/9, 3));
            part2 = Math.acos(-1*(27*this.Dc+2*Math.pow(this.Bc, 3)/54)/part1);
            part3 = Math.cos(part2/3) - this.Bc/3;
            result = 2 * part1 * part3;
        }
        this.sigma_h1 = result;
    }

    
    /**
     * Computes the B coefficient which is input to the cubic equation
     */
    private void cubic_equation_coef_B(){
        double var;
        if (this.ter == 1){
            var = this.MSF;
        } else {
            var = this.MST;
        }
        this.Bc =  Math.pow(this.gama,2) * this.E/24 * Math.pow(var*this.z_0/this.sigma_h0, 2) + this.alpha*this.E*(this.theta_1 - this.theta_0) - this.sigma_h0;
    }
    
    /**
     * Computes the D coefficient which is input to the cubic equation
     */
    private void cubic_equation_coef_D(){
        double var;
        if (this.ter == 1){
            var = this.MSF;
        } else {
            var = this.MST;
        }
        this.Dc = Math.pow(this.gama,2)*this.E/24 * Math.pow(var*this.z_1, 2);
    }
    
    /**
     * Computes the pulling force based on the horizontal stress of the conductor "F_mH"
     */
    private void pulling_force(){
        this.F_mH1 = this.sigma_h1 * this.S;
    }
    
    /**
     * Computes the "c" parameter of the conductor shape /sag/
     */
    private void c_parameter(){
        this.c = this.sigma_h1 / (this.gama * this.z_1);
    }
    
    /**
     * Computes the maximum sag for ideal conditions
     */
    private void sag_maximum(){
        double var;
        if (this.ter == 1) {
            var = this.MSF;
        } else {
            var = this.MST;
        }
        this.sag_max = this.c * (Math.cosh(var/(2*this.c)) - 1);
    }
    
    /**
     * Computes the array of all visible sags in one suspension section. 
     */
    private void sag_visible(){
        double part1;
        double part2;
        double part3;
        double part4[] = new double[state_equation.a.length];
        double var;
        
        if (this.ter == 1) {
            var = this.MSF;
        } else {
            var = this.MST;
        } 
            
        for (int i=0; i<state_equation.a.length; i++) {
            part1 = var/(2*this.c);
            part2 = part1 + Math_Extended.asinh(state_equation.dh[i]/(2*this.c*Math.sinh(part1)));
            part3 = Math_Extended.asinh(state_equation.dh[i]/this.a[i]);
            part4[i] = this.c*(Math.cosh(part2) - Math.cosh(part3) - (state_equation.dh[i]/state_equation.a[i])*(part2 - part3));
        }
        
        this.sag_vis = part4;
    }
}
