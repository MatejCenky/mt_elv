/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_main;

import mainframe_1.mainframe_1;
import mainframe_10.mainframe_10;
import mainframe_11.mainframe_11;
import mainframe_12.mainframe_12;
import mainframe_13.mainframe_13;
import mainframe_14.mainframe_14;
import mainframe_15.mainframe_15;
import mainframe_16.mainframe_16;
import mainframe_17.mainframe_17;
import mainframe_18.mainframe_18;
import mainframe_19.mainframe_19;
import mainframe_2.mainframe_2;
import mainframe_20.mainframe_20;
import mainframe_3.mainframe_3;
import mainframe_4.mainframe_4;
import mainframe_5.mainframe_5;
import mainframe_6.mainframe_6;
import mainframe_7.mainframe_7;
import mainframe_8.mainframe_8;
import mainframe_9.mainframe_9;

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

        
        int pocet_otvoreni = 1;
        while (true) { //wait for information that user has choosen a language
            try {
                Thread.sleep(100);                 //1000 milliseconds is one second. wait to not get overcycled
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            
            int status = startPanel.getStatus_for_new_project(); // vytvor nove okno
            
            if (status == 1) {                               // Status 1 mena new project           
                startPanel.setStatus_for_new_project(0);    // nastavi status že bolo vytvorene blokne od zacyklenia                
            
                
            if(pocet_otvoreni ==1){mainframe_1.main(args,false);}           
            if(pocet_otvoreni ==2){mainframe_2.main(args,false);}    
            if(pocet_otvoreni ==3){mainframe_3.main(args,false);} 
            if(pocet_otvoreni ==4){mainframe_4.main(args,false);} 
            if(pocet_otvoreni ==5){mainframe_5.main(args,false);} 
            if(pocet_otvoreni ==6){mainframe_6.main(args,false);} 
            if(pocet_otvoreni ==7){mainframe_7.main(args,false);} 
            if(pocet_otvoreni ==8){mainframe_8.main(args,false);} 
            if(pocet_otvoreni ==9){mainframe_9.main(args,false);} 
            if(pocet_otvoreni ==10){mainframe_10.main(args,false);} 
            if(pocet_otvoreni ==11){mainframe_11.main(args,false);} 
            if(pocet_otvoreni ==12){mainframe_12.main(args,false);}
            if(pocet_otvoreni ==13){mainframe_13.main(args,false);} 
            if(pocet_otvoreni ==14){mainframe_14.main(args,false);} 
            if(pocet_otvoreni ==15){mainframe_15.main(args,false);} 
            if(pocet_otvoreni ==16){mainframe_16.main(args,false);} 
            if(pocet_otvoreni ==17){mainframe_17.main(args,false);} 
            if(pocet_otvoreni ==18){mainframe_18.main(args,false);} 
            if(pocet_otvoreni ==19){mainframe_19.main(args,false);} 
            if(pocet_otvoreni ==20){mainframe_20.main(args,false);} 
            pocet_otvoreni=pocet_otvoreni+1;          
            }
            
            if (status == 2) {                               // Status 1 mena new project           
                startPanel.setStatus_for_new_project(0);    // nastavi status že bolo vytvorene blokne od zacyklenia                
                
                
            if(pocet_otvoreni ==1){mainframe_1.main(args,true);}           
            if(pocet_otvoreni ==2){mainframe_2.main(args,true);}    
            if(pocet_otvoreni ==3){mainframe_3.main(args,true);} 
            if(pocet_otvoreni ==4){mainframe_4.main(args,true);} 
            if(pocet_otvoreni ==5){mainframe_5.main(args,true);} 
            if(pocet_otvoreni ==6){mainframe_6.main(args,true);} 
            if(pocet_otvoreni ==7){mainframe_7.main(args,true);} 
            if(pocet_otvoreni ==8){mainframe_8.main(args,true);} 
            if(pocet_otvoreni ==9){mainframe_9.main(args,true);} 
            if(pocet_otvoreni ==10){mainframe_10.main(args,true);} 
            if(pocet_otvoreni ==11){mainframe_11.main(args,true);} 
            if(pocet_otvoreni ==12){mainframe_12.main(args,true);}
            if(pocet_otvoreni ==13){mainframe_13.main(args,true);} 
            if(pocet_otvoreni ==14){mainframe_14.main(args,true);} 
            if(pocet_otvoreni ==15){mainframe_15.main(args,true);} 
            if(pocet_otvoreni ==16){mainframe_16.main(args,true);} 
            if(pocet_otvoreni ==17){mainframe_17.main(args,true);} 
            if(pocet_otvoreni ==18){mainframe_18.main(args,true);} 
            if(pocet_otvoreni ==19){mainframe_19.main(args,true);} 
            if(pocet_otvoreni ==20){mainframe_20.main(args,true);}  
            pocet_otvoreni=pocet_otvoreni+1;  
            }
            
            

        }

    }

}
