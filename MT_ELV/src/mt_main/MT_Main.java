/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mainframe_1.mainframe_1;
import mainframe_2.mainframe_2;
import mainframe_3.mainframe_3;

/**
 *
 * @author Mattto
 */
public class MT_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
 
    


// TODO code application logic here
        languageChooserFrame.main(args);

        while (true) { //wait for information that user has choosen a language
            try {
                Thread.sleep(100);                 //1000 milliseconds is one second. wait to not get overcycled
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            Boolean status = languageChooserFrame.getStatus();
            if (status.equals(true)) {             //has user choosen the language in language Chooser frame ?
                break;
            }

        }
        
        //System.out.println("Overload class testing ...");
        //overload_test.compute();
        
        //System.out.println("State_equation class testing ...");
        //state_equation_test.compute_sigma_H1();
        
        startPanel.main(args);

        
        int pocet_otvoreni = 0;
        while (true) { //wait for information that user has choosen a language
            try {
                Thread.sleep(100);                 //1000 milliseconds is one second. wait to not get overcycled
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            
            int status = startPanel.getStatus_for_new_project(); // vytvor nove okno
            
            if (status == 1) {                               // Status 1 mena new project           
                startPanel.setStatus_for_new_project(0);    // nastavi status že bolo vytvorene blokne od zacyklenia                
               
            if(pocet_otvoreni == 0){mainframe_1.main(args,false);}
            if(pocet_otvoreni == 1){mainframe_2.main(args,false);}    
            if(pocet_otvoreni == 2){mainframe_3.main(args,false);}   
            pocet_otvoreni=pocet_otvoreni+1;          
            }
            
            if (status == 2) {                               // Status 1 mena new project           
                startPanel.setStatus_for_new_project(0);    // nastavi status že bolo vytvorene blokne od zacyklenia                
                
                
              if(pocet_otvoreni == 0){ mainframe_1.main(args,true);}
              if(pocet_otvoreni == 1){ mainframe_2.main(args,true);   } 
              if(pocet_otvoreni == 2){ mainframe_3.main(args,true);   } 
            pocet_otvoreni=pocet_otvoreni+1;  
            }
            
            

        }

    }

}
