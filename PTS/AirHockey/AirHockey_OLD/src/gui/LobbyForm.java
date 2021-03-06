//<editor-fold defaultstate="collapsed" desc="Jibberish">
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import airhockey.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import objects.JInputJoystick;
import org.jdesktop.xswingx.PromptSupport;
import net.java.games.input.Controller;
import objects.JInputJoystick;
//</editor-fold>

/**
 * Dit is een containerclasse van de Lobby.
 *
 * @author jeroen
 */
public class LobbyForm extends javax.swing.JFrame {

    private JInputJoystick joystick;
    //<editor-fold defaultstate="collapsed" desc="Constructor">

    /**
     * Creates new form LobbyForm
     */
    public LobbyForm() {
        initComponents();
        joystick = new JInputJoystick(Controller.Type.STICK, Controller.Type.GAMEPAD);
        if (!joystick.isControllerConnected()) {
            jComboBoxStuurSelectie.removeItemAt(2);
        }
        jTextFieldName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonLoginActionPerformed(e);
            }
        });
        jTextFieldPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonLoginActionPerformed(e);
            }
        });
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Generated Code">
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonLogin = new javax.swing.JButton();
        jTextFieldName = new javax.swing.JTextField();
        PromptSupport.setPrompt("Username", jTextFieldName);
        jCheckBoxCompetitie = new javax.swing.JCheckBox();
        jComboBoxStuurSelectie = new javax.swing.JComboBox();
        jLabelBesturing = new javax.swing.JLabel();
        jTextFieldPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonLogin.setText("Login");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jCheckBoxCompetitie.setText("Competitie");

        jComboBoxStuurSelectie.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Muis", "Toetsenbord", "Controller" }));

        jLabelBesturing.setText("Besturing:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldPassword)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jCheckBoxCompetitie)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonLogin))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jLabelBesturing)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jComboBoxStuurSelectie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextFieldName))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxCompetitie)
                    .addComponent(jButtonLogin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxStuurSelectie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBesturing))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //</editor-fold>

    //<editor-fold desc="Methodes">
    //<editor-fold defaultstate="collapsed" desc="Login Button">
    /**
     * Dit is een event gekoppeld aan de login button. Hierin wordt gekeken of
     * de de gebruiker al bestaat, zo niet ofdat die een wachtwoord heeft (dan
     * wordt het een nieuwe gebruiker), en anders word er gekeken of het
     * wachtwoord klopt, en wordt die ingeladen vanuit een bestand.
     *
     * @param evt een parameter die standaard megegeven wordt.
     */
    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        //<editor-fold defaultstate="collapsed" desc="Declarations">
        Persoon persoon = null;
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Initialization">
        StringBuilder sb = new StringBuilder();
        sb.append("saves/");
        sb.append(jTextFieldName.getText());
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Read">
        if (!jTextFieldPassword.getText().isEmpty()) {
            Path path = Paths.get(sb.toString());

            if (Files.exists(path)) {
                // file exist
                persoon = Serialization.GetSpeler(jTextFieldName.getText());
                if (persoon.CheckWachtWoord(jTextFieldPassword.getText())) {
                    createGame(persoon);
                } else {
                    JOptionPane.showMessageDialog(this, "Onjuist Wachtwoord");
                }

            } else {
                // file is not exist
                Serialization.nieuweSpeler(jTextFieldName.getText(), jTextFieldPassword.getText());
                persoon = new Persoon(jTextFieldName.getText(), jTextFieldPassword.getText());
                createGame(persoon);
            }

        } else {
            persoon = new Persoon(jTextFieldName.getText());
            createGame(persoon);
        }
        //</editor-fold>
    }//GEN-LAST:event_jButtonLoginActionPerformed
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Create Game">
    /**
     * Hierin wordt een nieuw spel aangemaakt, met een hostspeler, etc. Als het
     * spel is aangemaakt wordt het spelbord weergegeven in een nieuw Form.
     *
     * @param persoon de persoon die hostspeler wordt.
     */
    private void createGame(Persoon persoon) {
        Spel spel = null;
        if (jCheckBoxCompetitie.isSelected()) {
            spel = new Spel("Single_Competitie", persoon);
        } else {
            spel = new Spel("Single_Fun", persoon);
        }
        SpelForm spelForm = new SpelForm(spel, jComboBoxStuurSelectie.getSelectedItem().toString(), joystick);
        spelForm.setPlayerLabel(jTextFieldName.getText(), 1);
        spelForm.setVisible(true);
        this.setVisible(false);
    }
    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Static Main">

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LobbyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LobbyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LobbyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LobbyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LobbyForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JCheckBox jCheckBoxCompetitie;
    private javax.swing.JComboBox jComboBoxStuurSelectie;
    private javax.swing.JLabel jLabelBesturing;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JPasswordField jTextFieldPassword;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
