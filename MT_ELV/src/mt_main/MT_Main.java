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

        startPanel.main(args);

        while (true) { //wait for information that user has choosen a language
            try {
                Thread.sleep(100);                 //1000 milliseconds is one second. wait to not get overcycled
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            Boolean status = startPanel.getStatus_for_new_project(); // vytvor nove okno
            if (status.equals(true)) {             //has user choosen the language in language Chooser frame ?             
                startPanel.setStatus_for_new_project(false);    // nastavi status že bolo vytvorene blokne od zacyklenia
                mainframe.main(args);
            }

        }

    }

}
