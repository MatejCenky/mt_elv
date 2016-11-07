/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_main;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Jozef
 */
public class startPanel extends javax.swing.JFrame {

    /**
     * Creates new form startPanel
     */
    public startPanel() {
        languageOption= languageChooserFrame.getOption();
        initComponents();
        setLocationRelativeTo(null); //center of window position
        seticon();
        
     String userhome = System.getProperty("user.dir");
     File subor = new File(userhome + "\\resources\\" + "memory.txt");
     try {
         Scanner input = new Scanner(subor);
         String EmptyLine;
         
         EmptyLine= input.nextLine(); // first empty line info line
         conductor_memory_path_plus_filename=input.nextLine();
         
         
          } catch (FileNotFoundException ex) {

        }
     
      
     setDefaultCloseOperation(startPanel.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            end_of_programme=false;
           
            
            warning_sign2but("Si kokot?");
            
            
            if(end_of_programme == true  ){
              
                
                System.exit(0);
            } 
            
            if(end_of_programme == false ){
              
                
              
            } 
            
             
                
                
            }
        });
     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        textfieldBottomBasicAbout = new javax.swing.JLabel();
        textLabelVerzia = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        JmenuFileMenu = new javax.swing.JMenu();
        JmenuFIleNewBUtton = new javax.swing.JMenuItem();
        jMenuFlneILoadbutton = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuIFileExitButton = new javax.swing.JMenuItem();
        jMenuLibratyButton = new javax.swing.JMenu();
        jMenuILibratyConductorsButton = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuIHelpHelpMT3BUtton = new javax.swing.JMenuItem();
        jMenuIHelpStandardsButton = new javax.swing.JMenuItem();
        jMenuIHelpMathButton = new javax.swing.JMenuItem();
        jMenuIHelpAboutButton = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        textfieldBottomBasicAbout.setText(language.language_label(languageOption,0));

        textLabelVerzia.setText(language.language_label(languageOption,4));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/logo.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mt_graphic/picbackStarframe2.png"))); // NOI18N
        jLabel3.setText("jLabel3");

        jMenuBar1.add(jMenu1);

        JmenuFileMenu.setText(language.language_label(languageOption,1));

        JmenuFIleNewBUtton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        JmenuFIleNewBUtton.setText(language.language_label(languageOption,2));
        JmenuFIleNewBUtton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmenuFIleNewBUttonActionPerformed(evt);
            }
        });
        JmenuFileMenu.add(JmenuFIleNewBUtton);

        jMenuFlneILoadbutton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuFlneILoadbutton.setText(language.language_label(languageOption,3));
        jMenuFlneILoadbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFlneILoadbuttonActionPerformed(evt);
            }
        });
        JmenuFileMenu.add(jMenuFlneILoadbutton);
        JmenuFileMenu.add(jSeparator2);

        jMenuIFileExitButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuIFileExitButton.setText(language.language_label(languageOption,6));
        jMenuIFileExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIFileExitButtonActionPerformed(evt);
            }
        });
        JmenuFileMenu.add(jMenuIFileExitButton);

        jMenuBar1.add(JmenuFileMenu);

        jMenuLibratyButton.setText(language.language_label(languageOption,7));

        jMenuILibratyConductorsButton.setText(language.language_label(languageOption,8));
        jMenuILibratyConductorsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuILibratyConductorsButtonActionPerformed(evt);
            }
        });
        jMenuLibratyButton.add(jMenuILibratyConductorsButton);

        jMenuBar1.add(jMenuLibratyButton);

        jMenuHelp.setText(language.language_label(languageOption,9));

        jMenuIHelpHelpMT3BUtton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuIHelpHelpMT3BUtton.setText(language.language_label(languageOption,10));
        jMenuIHelpHelpMT3BUtton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIHelpHelpMT3BUttonActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuIHelpHelpMT3BUtton);

        jMenuIHelpStandardsButton.setText(language.language_label(languageOption,11));
        jMenuIHelpStandardsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIHelpStandardsButtonActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuIHelpStandardsButton);

        jMenuIHelpMathButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuIHelpMathButton.setText(language.language_label(languageOption,12));
        jMenuIHelpMathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIHelpMathButtonActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuIHelpMathButton);

        jMenuIHelpAboutButton.setText(language.language_label(languageOption,13));
        jMenuIHelpAboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIHelpAboutButtonActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuIHelpAboutButton);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textfieldBottomBasicAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textLabelVerzia, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textfieldBottomBasicAbout)
                    .addComponent(textLabelVerzia))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JmenuFIleNewBUttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmenuFIleNewBUttonActionPerformed
      Clicked_NEW = 1;
    }//GEN-LAST:event_JmenuFIleNewBUttonActionPerformed

     public  void warning_sign2but (String X){
        
    mainframe_warningEND mainframe_warningEND = new mainframe_warningEND(this, rootPaneCheckingEnabled);
        mainframe_warningEND.setVisible(true);  
    }
    
    
    private void jMenuFlneILoadbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFlneILoadbuttonActionPerformed
      Clicked_NEW = 2;
    }//GEN-LAST:event_jMenuFlneILoadbuttonActionPerformed

    private void jMenuIFileExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIFileExitButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuIFileExitButtonActionPerformed

    private void jMenuILibratyConductorsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuILibratyConductorsButtonActionPerformed
       
        Conductors_main_JDialog Conductors_main_JDialog_window = new Conductors_main_JDialog(this, rootPaneCheckingEnabled);
       Conductors_main_JDialog_window.setVisible(true);   // opens dialog window 
        
    }//GEN-LAST:event_jMenuILibratyConductorsButtonActionPerformed

    private void jMenuIHelpHelpMT3BUttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIHelpHelpMT3BUttonActionPerformed
      
        if (Desktop.isDesktopSupported()) {
    try {
        String userhome = System.getProperty("user.dir");   // find home directory
        File myFile = new File(userhome + "\\resources\\HelpMT3.pdf"); //access if and open
        Desktop.getDesktop().open(myFile);
    } catch (IOException ex) {
        // no application registered for PDFs
    }
}
        
        
        
    }//GEN-LAST:event_jMenuIHelpHelpMT3BUttonActionPerformed

    private void jMenuIHelpStandardsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIHelpStandardsButtonActionPerformed
       
        if (Desktop.isDesktopSupported()) {
    try {
        String userhome = System.getProperty("user.dir");   // find home directory
        File myFile = new File(userhome + "\\resources\\NormXyz.pdf"); //access if and open
        Desktop.getDesktop().open(myFile);
    } catch (IOException ex) {
        // no application registered for PDFs
    }
}
        
        
    }//GEN-LAST:event_jMenuIHelpStandardsButtonActionPerformed

    private void jMenuIHelpMathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIHelpMathButtonActionPerformed
        
       Help_Math_JDialog help_math_dialog_window = new Help_Math_JDialog(this, rootPaneCheckingEnabled);
       help_math_dialog_window.setVisible(true);   // opens dialog window 
        
    }//GEN-LAST:event_jMenuIHelpMathButtonActionPerformed

    private void jMenuIHelpAboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIHelpAboutButtonActionPerformed
        Help_About_JDialog help_about_dialog_window = new Help_About_JDialog(this, rootPaneCheckingEnabled);
       help_about_dialog_window.setVisible(true);   // opens dialog window 
    }//GEN-LAST:event_jMenuIHelpAboutButtonActionPerformed

    //posiela status mainu aby odblokovalo vytvorenie noveho okna
    public static int getStatus_for_new_project() {
        return Clicked_NEW;
        
    }
    
    public static boolean getStatus_conductor_password() {
        return conductor_password_set;
        
    }
    
    //prijma status z Mainu aby nastavilo status na vytvaranie novych projektových oknien oknie na False
    public static void setStatus_for_new_project(int X) {
         Clicked_NEW =X;
        
    }
    
    public static void setStatus_conductor_password(boolean X) {
         conductor_password_set =X;
        
    }
    //posiela prikaz či existuje subor s lanovou databazou tam kde ma podla memory file
    public static String set_memory_path_conductor() {
         return conductor_memory_path_plus_filename;
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(startPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(startPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(startPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(startPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new startPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem JmenuFIleNewBUtton;
    private javax.swing.JMenu JmenuFileMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuFlneILoadbutton;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuIFileExitButton;
    private javax.swing.JMenuItem jMenuIHelpAboutButton;
    private javax.swing.JMenuItem jMenuIHelpHelpMT3BUtton;
    private javax.swing.JMenuItem jMenuIHelpMathButton;
    private javax.swing.JMenuItem jMenuIHelpStandardsButton;
    private javax.swing.JMenuItem jMenuILibratyConductorsButton;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu jMenuLibratyButton;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel textLabelVerzia;
    private javax.swing.JLabel textfieldBottomBasicAbout;
    // End of variables declaration//GEN-END:variables
public static Integer languageOption  ;
 public static int Clicked_NEW = 0;
 public static boolean conductor_password_set = false;
 public static String conductor_memory_path_plus_filename="none";
 public static boolean end_of_programme = false;
 
    private void seticon() {
     setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/mt_graphic/" + "icon.png")));
    }
}
