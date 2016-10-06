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
 *
 * @author Matus
 */
public class Variables {
    
}

class Variables_state extends Variables{
    
    // state equation variables
    private static double gama_state; 
    private static double m_state; 
    private static double S_state;  
    private static double E_state;  
    private static double alpha_state;    
    private static double MSF_state;
    private static double MST_state;
    private static double z_1_state;
    private static double theta_1_state;
    private static double theta_0_state;
    private static double sigma_h0_state;
    
    /**
     * constructor for state equation variables
     * @param gama specific gravity of the conductor [N/m*mm^2]
     * @param m weight of the conductor per unit [kg/m]
     * @param S cross-section area of the conductor [mm^2]
     * @param E Young model of elasticity of conductor [MPa]
     * @param alpha linear expansion coefficient [1/degree_C]
     * @param MSF /average/ span of flat suspension section [m]
     * @param MST /average/ span of terrain suspension section [m]
     * @param z_1 conductor overload in state "1" [-]
     * @param theta_1 conductor temperature in state "1"
     * @param theta_0 conductor temperature in state "0"
     * @param sigma_h0 horizontal stress in conductor in state "0" [MPa]
     */
    public Variables_state(double gama, 
                    double m, 
                    double S, 
                    double E, 
                    double alpha, 
                    double MSF, 
                    double MST, 
                    double z_1, 
                    double theta_1, 
                    double theta_0, 
                    double sigma_h0){
        
        Variables_state.gama_state = gama; 
        Variables_state.m_state = m; 
        Variables_state.S_state = S;  
        Variables_state.E_state = E;  
        Variables_state.alpha_state = alpha;    
        Variables_state.MSF_state = MSF;
        Variables_state.MST_state = MST;
        Variables_state.z_1_state = z_1;
        Variables_state.theta_1_state = theta_1;
        Variables_state.theta_0_state = theta_0;
        Variables_state.sigma_h0_state = sigma_h0;
    }
}

class Variables_over extends Variables{
    
    // overload variables
    private static double d_over;
    private static double g_c_over;
    private static double ro_I_over;
    private static double K_lc_over;
    private static double K_h_over;
    private static double I_R50_over;
    private static double k_r_over;
    private static double z_0_over;
    private static double V_b0_over;
    private static double c_dir_over;
    private static double c_0_over;
    private static double C_c_over;
    private static double gama_w_over;
    private static double gama_I_over;
    private static double Psi_I_over;
    private static double Psi_w_over;
    private static double B_I_over;
    
    /**
     * constructor for overload computation variables
     * @param d conductor diameter !!!!!! [m] !!!!!!
     * @param g_c specific weight of conductor [N/m]
     * @param ro_I density of the ice (= 500 kg/m3)
     * @param K_lc local conditions coefficient (čl.4.5.1/SK.3) [-]
     * @param K_h the height coefficient čl.4.5.1/SK.3) [-]
     * @param I_R50 reference ice load -> chosen from local icing area (čl.4.5.1/SK.3)
     * @param k_r terrain coefficient (čl. 4.3.2) [-]
     * @param z_0 length of the roughness (čl. 4.3.2) [-]
     * @param V_b0 base wind speed (čl. 4.3.1/SK.1) [m/s]
     * @param c_dir wind direction coefficient [-]
     * @param c_0 orography coefficient [-]
     * @param C_c aerodynamic resistance of the conductor coefficient (čl. 4.4.1.3/SK.1) [-]
     * @param gama_w partial wind load coefficient [-]
     * @param gama_I partial ice load coefficient [-]
     * @param Psi_I combination coefficient (čl. 4.6.6/SK/CZ) [-]
     * @param Psi_w combination ice load coefficient [-]
     * @param B_I combination coefficient (čl. 4.6.6/SK/CZ) [-]
     */
    public Variables_over(double d, 
                    double g_c, 
                    double ro_I, 
                    double K_lc,
                    double K_h,
                    double I_R50,
                    double k_r,
                    double z_0,
                    double V_b0,
                    double c_dir,
                    double c_0,
                    double C_c,
                    double gama_w,
                    double gama_I,
                    double Psi_I,
                    double Psi_w,
                    double B_I){
        Variables_over.d_over = d;
        Variables_over.g_c_over = g_c;
        Variables_over.ro_I_over = ro_I;
        Variables_over.K_lc_over = K_lc;
        Variables_over.K_h_over = K_h;
        Variables_over.I_R50_over = I_R50;
        Variables_over.k_r_over = k_r;
        Variables_over.z_0_over = z_0;
        Variables_over.V_b0_over = V_b0;
        Variables_over.c_dir_over = c_dir;
        Variables_over.c_0_over = c_0;
        Variables_over.C_c_over = C_c;
        Variables_over.gama_w_over = gama_w;
        Variables_over.gama_I_over = gama_I;
        Variables_over.Psi_I_over = Psi_I;
        Variables_over.Psi_w_over = Psi_w;
        Variables_over.B_I_over = B_I;
    }
}
    
class Variables_creep extends Variables{
    
    // conductor creeping variables
    private static double w_Fe_creep;
    private static double k_w_creep;
    private static double k_EDS_creep;
    private static double k_EDT_creep;
    private static double S_creep;
    private static double g_c_creep;
    private static double RTS_creep;
    private static double T_EDT_creep;
    private static double alpha_creep;
    private static double t_0_creep;
    private static double t_p_creep;
    
    /**
     * constructor for conductor creeping variables
     * @param w_Fe proportional weight of steel in the conductor [-]
     * @param k_w conductor composition coefficient [-]
     * @param k_EDS average year load influence coefficient [-]
     * @param k_EDT average year temperature influence coefficient [-]
     * @param S cross-section of conductor [mm^2]
     * @param g_c specific weight of conductor [N/m]
     * @param RTS Rated Tensile Parameter [N]
     * @param T_EDT average year temperature [degreeC]
     * @param alpha linear expansion coefficient [1/degreeC]
     * @param t_0 time to final state of conductor creeping [h] (usually 30y = 262800h)
     * @param t_p time from the construction of the line - check various time stages on the line [h]
     * @param fill_in_zero 
     */
    public Variables_creep(double w_Fe,
                    double k_w,
                    double k_EDS,
                    double k_EDT,
                    double S,
                    double g_c,
                    double RTS,
                    double T_EDT,
                    double alpha,
                    double t_0,
                    double t_p){
        Variables_creep.w_Fe_creep = w_Fe;
        Variables_creep.k_w_creep = k_w;
        Variables_creep.k_EDS_creep = k_EDS;
        Variables_creep.k_EDT_creep = k_EDT;
        Variables_creep.S_creep = S;
        Variables_creep.g_c_creep = g_c;
        Variables_creep.RTS_creep = RTS;
        Variables_creep.T_EDT_creep = T_EDT;
        Variables_creep.alpha_creep = alpha;
        Variables_creep.t_0_creep = t_0;
        Variables_creep.t_p_creep = t_p;
    }
}
 

 class Variables_vib extends Variables{   
        
    // vibration protection variables
    private static double c_vib_vib;
    private static double EQ_vib_vib;
    private static double d_vib;
    private static double g_c_vib;
    private static double L_vib;
    private static double T_0_vib;
    private static double x_vib;
    private static double y_vib;
      
    /**
     * constructor for overload computation variables
     * @param c_vib catenary parameter for specific terrain
     * @param EQ_vib auxiliary parameter for specific terrain
     * @param d diameter of conductor [mm]
     * @param g_c specific weight of conductor [N/m]
     * @param L L == a[i]; specific span from the suspension section
     * @param T_0 similar as sigma_H1 but with specific initial values
     * @param x x axis
     * @param y y axis
     */
    public Variables_vib(double c_vib,
                        double EQ_vib,
                        double d,
                        double g_c,
                        double L,
                        double T_0,
                        double x,
                        double y){
        Variables_vib.c_vib_vib = c_vib;
        Variables_vib.EQ_vib_vib = EQ_vib;
        Variables_vib.d_vib = d;
        Variables_vib.g_c_vib = c_vib;
        Variables_vib.L_vib = L;
        Variables_vib.T_0_vib = T_0;
        Variables_vib.x_vib = x;
        Variables_vib.y_vib = y;
    }
    
}
