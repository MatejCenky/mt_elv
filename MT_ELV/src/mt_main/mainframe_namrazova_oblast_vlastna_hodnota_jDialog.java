/*
 * Copyright (c) 2016, Mattto
 * All rights reserved.
 * 
 * Any usage of the source code, program or any parts
 * of it must be consulted and the permission granted 
 * by authors Ing. Matej Cenky and Ing. Jozef Bendik.
 */
package mt_main;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JDialog;
import static mt_main.startPanel.languageOption;

/**
 *
 * @author Jozef
 */
public class mainframe_namrazova_oblast_vlastna_hodnota_jDialog extends javax.swing.JDialog {

    /**
     * Creates new form mainframe_namrazova_oblast_vlastna_hodnota_jDialog
     */
    public mainframe_namrazova_oblast_vlastna_hodnota_jDialog(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setIcon();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textfield_smaller = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textfield_larger = new javax.swing.JTextField();
        button_set = new javax.swing.JButton();
        button_stormo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        textfield_smaller.setText("0.000");
        textfield_smaller.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfield_smallerKeyReleased(evt);
            }
        });

        jLabel1.setText(language.language_label(languageOption,72));

        jLabel2.setText("d£ 30 mm ");

        jLabel3.setText("d> 30 mm ");

        textfield_larger.setText("0.000");
        textfield_larger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_largerActionPerformed(evt);
            }
        });
        textfield_larger.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfield_largerKeyReleased(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(textfield_smaller, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textfield_larger, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_set, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_stormo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(button_set)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textfield_smaller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfield_larger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_stormo))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textfield_largerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_largerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfield_largerActionPerformed

    private void button_setActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_setActionPerformed
        
       Double hodnota1=doubleChecker( textfield_smaller);
       Double hodnota2=doubleChecker( textfield_larger);
       
       if( hodnota1.equals(123456789.987654321) || hodnota2.equals(123456789.987654321)){
           
       }else{
   
        Object[] hodnoty = new Object[4];
        hodnoty = new Object[]{(double) hodnota1,(double) 0.0, (double) hodnota2,(double) 0.0};
        mainframe_namrazova_oblast_jDialog.getvlastnehodnoty(hodnoty);
       disinit();
       }
        
        
    }//GEN-LAST:event_button_setActionPerformed

    private void button_stormoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_stormoActionPerformed
        
     Object[] hodnoty = new Object[4];
        hodnoty = new Object[]{(double) 0.0,(double) 0.0, (double) 0.0,(double) 0.0};
        mainframe_namrazova_oblast_jDialog.getvlastnehodnoty(hodnoty);
       disinit();
        
    }//GEN-LAST:event_button_stormoActionPerformed

    private void textfield_smallerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfield_smallerKeyReleased
        int key = evt.getKeyCode();

        if (key == 10) {
            button_set.doClick();
        } else {

        }        // TODO add your handling code here:
    }//GEN-LAST:event_textfield_smallerKeyReleased

    private void textfield_largerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfield_largerKeyReleased
        int key = evt.getKeyCode();

        if (key == 10) {
            button_set.doClick();
        } else {

        }         // TODO add your handling code here:
    }//GEN-LAST:event_textfield_largerKeyReleased

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
            java.util.logging.Logger.getLogger(mainframe_namrazova_oblast_vlastna_hodnota_jDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainframe_namrazova_oblast_vlastna_hodnota_jDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainframe_namrazova_oblast_vlastna_hodnota_jDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainframe_namrazova_oblast_vlastna_hodnota_jDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainframe_namrazova_oblast_vlastna_hodnota_jDialog dialog = new mainframe_namrazova_oblast_vlastna_hodnota_jDialog(new javax.swing.JDialog(), true);
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
       Double value ;
        try{
        value = Double.parseDouble(Y.getText());
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
     
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_set;
    private javax.swing.JButton button_stormo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField textfield_larger;
    private javax.swing.JTextField textfield_smaller;
    // End of variables declaration//GEN-END:variables
}
