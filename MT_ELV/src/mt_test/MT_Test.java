/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by the author Matej Cenky.
 */

package mt_test;

import javax.swing.JFrame;

/**
 *
 * @author Mattto
 */
public class MT_Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        languageChooserFrame.main(args);
        
        
        while (true) {
            try {
                Thread.sleep(100);                 //1000 milliseconds is one second. cakaj
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();  //zavri ho
            }
            Boolean status = languageChooserFrame.getStatus();
            if ( status.equals(true)) {
                break;
            }

        }
        
        
        
        startPanel.main(args);
        mainframe.main(args);
        
    }
    
}
