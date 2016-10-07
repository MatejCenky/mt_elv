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
public class Overload_variables {
    
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
    private static double k_p_over;
    private static double RR_over;
    private static double ro_over;
    private static double C_cl_over;
    
    /**
     * constructor for overload computation variables
     * @param Conductor - Variables_conductor class object
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
     * @param k_p tip coefficient (= 3) (čl. 4.4.1.2)
     * @param RR resonance response coefficient (RR = R^2 = 0) (čl. 4.4.12)
     * @param ro density of the wind (= 1.25)
     * @param C_cl aerodynamic resistance for conductor with ice coefficient (= 1,1) [-]
     */
    public Overload_variables(  Conductor_variables Conductor, 
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
                                double B_I,
                                double k_p,
                                double RR,
                                double ro,
                                double C_cl){
        
        Overload_variables.d_over = Conductor.get_d();
        Overload_variables.g_c_over = g_c;
        Overload_variables.ro_I_over = ro_I;
        Overload_variables.K_lc_over = K_lc;
        Overload_variables.K_h_over = K_h;
        Overload_variables.I_R50_over = I_R50;
        Overload_variables.k_r_over = k_r;
        Overload_variables.z_0_over = z_0;
        Overload_variables.V_b0_over = V_b0;
        Overload_variables.c_dir_over = c_dir;
        Overload_variables.c_0_over = c_0;
        Overload_variables.C_c_over = C_c;
        Overload_variables.gama_w_over = gama_w;
        Overload_variables.gama_I_over = gama_I;
        Overload_variables.Psi_I_over = Psi_I;
        Overload_variables.Psi_w_over = Psi_w;
        Overload_variables.B_I_over = B_I;
        Overload_variables.k_p_over = k_p;
        Overload_variables.RR_over = RR;
        Overload_variables.ro_over = ro;
        Overload_variables.C_cl_over = C_cl;
    }
    
    /**
     * setting overload computation variables
     * @param Conductor - Variables_conductor class object
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
     * @param k_p tip coefficient (= 3) (čl. 4.4.1.2)
     * @param RR resonance response coefficient (RR = R^2 = 0) (čl. 4.4.12)
     * @param ro density of the wind (= 1.25)
     * @param C_cl aerodynamic resistance for conductor with ice coefficient (= 1,1) [-]
     */
    public static void set_variables(   Conductor_variables Conductor, 
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
                                        double B_I,
                                        double k_p,
                                        double RR,
                                        double ro,
                                        double C_cl){
        
        Overload_variables.d_over = Conductor.get_d();
        Overload_variables.g_c_over = g_c;
        Overload_variables.ro_I_over = ro_I;
        Overload_variables.K_lc_over = K_lc;
        Overload_variables.K_h_over = K_h;
        Overload_variables.I_R50_over = I_R50;
        Overload_variables.k_r_over = k_r;
        Overload_variables.z_0_over = z_0;
        Overload_variables.V_b0_over = V_b0;
        Overload_variables.c_dir_over = c_dir;
        Overload_variables.c_0_over = c_0;
        Overload_variables.C_c_over = C_c;
        Overload_variables.gama_w_over = gama_w;
        Overload_variables.gama_I_over = gama_I;
        Overload_variables.Psi_I_over = Psi_I;
        Overload_variables.Psi_w_over = Psi_w;
        Overload_variables.B_I_over = B_I;
        Overload_variables.k_p_over = k_p;
        Overload_variables.RR_over = RR;
        Overload_variables.ro_over = ro;
        Overload_variables.C_cl_over = C_cl;
    }
    
    public double get_d(){
        return Overload_variables.d_over;
    }
    
    public double get_c_dir(){
        return Overload_variables.c_dir_over;
    }
    
    public double get_g_c(){
        return Overload_variables.g_c_over;
    }
    
    public double get_ro_I(){
        return Overload_variables.ro_I_over;
    }
    
    public double get_K_lc(){
        return Overload_variables.K_lc_over;
    }
    
    public double get_K_h(){
        return Overload_variables.K_h_over;
    }
    
    public double get_I_R50(){
        return Overload_variables.I_R50_over;
    }
    
    public double get_k_r(){
        return Overload_variables.k_r_over;
    }
    
    public double get_z_0(){
        return Overload_variables.z_0_over;
    }
    
    public double get_V_b0(){
        return Overload_variables.V_b0_over;
    }
    
    public double get_c_0(){
        return Overload_variables.c_0_over;
    }
    
    public double get_C_c(){
        return Overload_variables.C_c_over;
    }
    
    public double get_gama_w(){
        return Overload_variables.gama_w_over;
    }
    
    public double get_gama_I(){
        return Overload_variables.gama_I_over;
    }
    
    public double get_Psi_I(){
        return Overload_variables.Psi_I_over;
    }
    
    public double get_Psi_w(){
        return Overload_variables.Psi_w_over;
    }
    
    public double get_B_I(){
        return Overload_variables.B_I_over;
    }
    
    public double get_k_p(){
        return Overload_variables.k_p_over;
    }
    
    public double get_RR(){
        return Overload_variables.RR_over;
    }
    
    public double get_ro(){
        return Overload_variables.ro_over;
    }
    
    public double get_C_cl(){
        return Overload_variables.C_cl_over;
    }
}
