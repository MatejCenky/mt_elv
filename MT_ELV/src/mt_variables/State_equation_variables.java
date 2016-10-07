/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_variables;

/**
 *
 * @author Mattto
 */
public class State_equation_variables {
    
    // state equation variables
    private static double m_state; 
    private static double S_state;  
    private static double E_state;  
    private static double alpha_state;    
    private static double theta_1_state;
    private static double theta_0_state;
    private static double sigma_h0_state;
    private static double z_0_state;
    
    /**
     * constructor for state equation variables
     * @param Conductor - Variables_conductor class object
     * @param theta_1 conductor temperature in state "1"
     * @param theta_0 conductor temperature in state "0"
     * @param sigma_h0 horizontal stress in conductor in state "0" [MPa]
     * @param z_0 conductor overload in state "0" [-]
     */
    public State_equation_variables(Conductor_variables Conductor, 
                                    double theta_1, 
                                    double theta_0, 
                                    double sigma_h0,
                                    double z_0){

        State_equation_variables.m_state = Conductor.get_m(); 
        State_equation_variables.S_state = Conductor.get_S();  
        State_equation_variables.E_state = Conductor.get_E();  
        State_equation_variables.alpha_state = Conductor.get_alpha();    
        State_equation_variables.theta_1_state = theta_1;
        State_equation_variables.theta_0_state = theta_0;
        State_equation_variables.sigma_h0_state = sigma_h0;
        State_equation_variables.z_0_state = z_0;
    }
    
    /**
     * setting the state equation variables
     * @param Conductor - Variables_conductor class object
     * @param theta_1 conductor temperature in state "1"
     * @param theta_0 conductor temperature in state "0"
     * @param sigma_h0 horizontal stress in conductor in state "0" [MPa]
     * @param z_0 conductor overload in state "0" [-]
     */
    
    public static void set_variables(   Conductor_variables Conductor, 
                                        double theta_1, 
                                        double theta_0, 
                                        double sigma_h0,
                                        double z_0){
        
        State_equation_variables.m_state = Conductor.get_m(); 
        State_equation_variables.S_state = Conductor.get_S();  
        State_equation_variables.E_state = Conductor.get_E();  
        State_equation_variables.alpha_state = Conductor.get_alpha();    
        State_equation_variables.theta_1_state = theta_1;
        State_equation_variables.theta_0_state = theta_0;
        State_equation_variables.sigma_h0_state = sigma_h0;
        State_equation_variables.z_0_state = z_0;
    }
    
    public double get_m(){
        return State_equation_variables.m_state;
    }
    
    public double get_S(){
        return State_equation_variables.S_state;
    }
    
    public double get_E(){
        return State_equation_variables.E_state;
    }
    
    public double get_alpha(){
        return State_equation_variables.alpha_state;
    }
    
    public double get_theta_1(){
        return State_equation_variables.theta_1_state;
    }
    
    public double get_theta_0(){
        return State_equation_variables.theta_0_state;
    }
    
    public double get_sigma_h0(){
        return State_equation_variables.sigma_h0_state;
    }
    
    public double get_z_0(){
        return State_equation_variables.z_0_state;
    }
}
