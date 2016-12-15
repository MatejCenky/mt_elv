/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mainframe_11;

import mainframe_1.*;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JDialog;
import mt_main.language;
import static mt_main.startPanel.languageOption;

/**
 *
 * @author Jozef
 */
public class mainframe_uroven_spolahlivosti_vlastna_hodnota_1 extends javax.swing.JDialog {

    /**
     * Creates new form mainframe_namrazova_oblast_vlastna_hodnota_jDialog
     */
    public mainframe_uroven_spolahlivosti_vlastna_hodnota_1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setIcon();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        textfield_hodnota1.setText(String.valueOf(Uroven_splah_var_1));
        textfield_hodnota2.setText(String.valueOf(Uroven_splah_var_2));
        textfield_hodnota3.setText(String.valueOf(Uroven_splah_var_3));
        textfield_hodnota4.setText(String.valueOf(Uroven_splah_var_4));
        textfield_hodnota5.setText(String.valueOf(Uroven_splah_var_5));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textfield_hodnota1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        textfield_hodnota2 = new javax.swing.JTextField();
        button_set = new javax.swing.JButton();
        button_stormo = new javax.swing.JButton();
        jLabel_hodnota1 = new javax.swing.JLabel();
        jLabel_hodnota2 = new javax.swing.JLabel();
        jLabel_hodnota3 = new javax.swing.JLabel();
        textfield_hodnota3 = new javax.swing.JTextField();
        jLabel_hodnota4 = new javax.swing.JLabel();
        textfield_hodnota4 = new javax.swing.JTextField();
        jLabel_hodnota5 = new javax.swing.JLabel();
        textfield_hodnota5 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        textfield_hodnota1.setText("0.000");
        textfield_hodnota1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfield_hodnota1KeyReleased(evt);
            }
        });

        jLabel1.setText(language.language_label(languageOption,148));

        textfield_hodnota2.setText("0.000");
        textfield_hodnota2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_hodnota2ActionPerformed(evt);
            }
        });
        textfield_hodnota2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfield_hodnota2KeyReleased(evt);
            }
        });

        button_set.setText(language.language_label(languageOption,73));
        button_set.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_setActionPerformed(evt);
            }
        });

        button_stormo.setText(language.language_label(languageOption,74));
        button_stormo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_stormoActionPerformed(evt);
            }
        });

        jLabel_hodnota1.setText(language.language_label(languageOption,149));

        jLabel_hodnota2.setText(language.language_label(languageOption,150));

        jLabel_hodnota3.setText(language.language_label(languageOption,151));

        textfield_hodnota3.setText("0.000");
        textfield_hodnota3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_hodnota3ActionPerformed(evt);
            }
        });
        textfield_hodnota3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfield_hodnota3KeyReleased(evt);
            }
        });

        jLabel_hodnota4.setText(language.language_label(languageOption,152));

        textfield_hodnota4.setText("0.000");
        textfield_hodnota4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_hodnota4ActionPerformed(evt);
            }
        });
        textfield_hodnota4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfield_hodnota4KeyReleased(evt);
            }
        });

        jLabel_hodnota5.setText(language.language_label(languageOption,153));

        textfield_hodnota5.setText("0.000");
        textfield_hodnota5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_hodnota5ActionPerformed(evt);
            }
        });
        textfield_hodnota5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfield_hodnota5KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel_hodnota2, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                    .addComponent(jLabel_hodnota1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textfield_hodnota1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textfield_hodnota2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(136, 136, 136))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel_hodnota4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                    .addComponent(jLabel_hodnota3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel_hodnota5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textfield_hodnota3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textfield_hodnota4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textfield_hodnota5, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(button_set, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button_stormo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(17, 17, 17))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(button_set)
                        .addGap(14, 14, 14)
                        .addComponent(button_stormo))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textfield_hodnota1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_hodnota1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textfield_hodnota2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_hodnota2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textfield_hodnota3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_hodnota3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textfield_hodnota4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_hodnota4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textfield_hodnota5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_hodnota5))))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textfield_hodnota2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_hodnota2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfield_hodnota2ActionPerformed

    private void button_setActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_setActionPerformed
        
       Double hodnota1=doubleChecker(textfield_hodnota1);
       Double hodnota2=doubleChecker(textfield_hodnota2);
       Double hodnota3=doubleChecker(textfield_hodnota3);
       Double hodnota4=doubleChecker(textfield_hodnota4);
       Double hodnota5=doubleChecker(textfield_hodnota5);
       
       
       if( hodnota1.equals(123456789.987654321) || hodnota2.equals(123456789.987654321)|| hodnota3.equals(123456789.987654321) || hodnota4.equals(123456789.987654321) || hodnota5.equals(123456789.987654321)){
           
       }else{
   
        Object[] hodnoty = new Object[4];
        hodnoty = new Object[]{(double) hodnota1,(double) hodnota2, (double) hodnota3,(double) hodnota4, (double) hodnota5};
        mainframe_11.getvlastnehodnoty_uroven_splahlivosti(hodnoty);
       disinit();
       }
        
        
    }//GEN-LAST:event_button_setActionPerformed

    private void button_stormoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_stormoActionPerformed
        
     Object[] hodnoty = new Object[4];
        hodnoty = new Object[]{(double) 0.0,(double) 0.0, (double) 0.0,(double) 0.0,(double) 0.0};
        mainframe_11.getvlastnehodnoty_uroven_splahlivosti(hodnoty);
       disinit();
        
    }//GEN-LAST:event_button_stormoActionPerformed

    private void textfield_hodnota1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfield_hodnota1KeyReleased
           Double hodnota1 = doubleChecker(textfield_hodnota1);
    }//GEN-LAST:event_textfield_hodnota1KeyReleased

    private void textfield_hodnota2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfield_hodnota2KeyReleased
              Double hodnota1 = doubleChecker(textfield_hodnota2);
    }//GEN-LAST:event_textfield_hodnota2KeyReleased

    private void textfield_hodnota3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_hodnota3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfield_hodnota3ActionPerformed

    private void textfield_hodnota3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfield_hodnota3KeyReleased
        Double hodnota1 = doubleChecker(textfield_hodnota3);
    }//GEN-LAST:event_textfield_hodnota3KeyReleased

    private void textfield_hodnota4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_hodnota4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfield_hodnota4ActionPerformed

    private void textfield_hodnota4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfield_hodnota4KeyReleased
        Double hodnota1 = doubleChecker(textfield_hodnota4);
    }//GEN-LAST:event_textfield_hodnota4KeyReleased

    private void textfield_hodnota5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_hodnota5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfield_hodnota5ActionPerformed

    private void textfield_hodnota5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfield_hodnota5KeyReleased
        Double hodnota1 = doubleChecker(textfield_hodnota5);
    }//GEN-LAST:event_textfield_hodnota5KeyReleased

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
            java.util.logging.Logger.getLogger(mainframe_uroven_spolahlivosti_vlastna_hodnota_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainframe_uroven_spolahlivosti_vlastna_hodnota_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainframe_uroven_spolahlivosti_vlastna_hodnota_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainframe_uroven_spolahlivosti_vlastna_hodnota_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainframe_uroven_spolahlivosti_vlastna_hodnota_1 dialog = new mainframe_uroven_spolahlivosti_vlastna_hodnota_1(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

     public void disinit(){    
        setVisible(false);
       setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
     private double doubleChecker (javax.swing.JTextField Y){
       String hodnota1 =Y.getText();
       String hodnota2=hodnota1.replace(",", ".");
       if(hodnota1.equals(hodnota2)){}else{ Y.setText(hodnota2);}
       
  
       Double value ;
        try{
        value = Double.parseDouble(hodnota2);
        Y.setForeground(Color.black);
        return value;
        }catch(NumberFormatException | NullPointerException e){
         Y.setForeground(Color.red);
         Y.setText(language.language_label(languageOption, 47));
        return value = 123456789.987654321;            
        }
        
        
        
    }
    
    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/mt_graphic/" + "icon.png")));
    }
     
     public static void setValues(double a, double b,double c,double d,double e) {
        Uroven_splah_var_1 =a;
        Uroven_splah_var_2=b;
        Uroven_splah_var_3=c;
        Uroven_splah_var_4=d;
        Uroven_splah_var_5=e;
    }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_set;
    private javax.swing.JButton button_stormo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_hodnota1;
    private javax.swing.JLabel jLabel_hodnota2;
    private javax.swing.JLabel jLabel_hodnota3;
    private javax.swing.JLabel jLabel_hodnota4;
    private javax.swing.JLabel jLabel_hodnota5;
    private javax.swing.JSeparator jSeparator1;
    private static javax.swing.JTextField textfield_hodnota1;
    private static javax.swing.JTextField textfield_hodnota2;
    private static javax.swing.JTextField textfield_hodnota3;
    private static javax.swing.JTextField textfield_hodnota4;
    private static javax.swing.JTextField textfield_hodnota5;
    // End of variables declaration//GEN-END:variables
        public static double Uroven_splah_var_1 ;
        public static double Uroven_splah_var_2 ;
        public static double Uroven_splah_var_3 ;
        public static double Uroven_splah_var_4 ;
        public static double Uroven_splah_var_5 ;

}
