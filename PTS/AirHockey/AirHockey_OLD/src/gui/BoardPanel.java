//<editor-fold defaultstate="collapsed" desc="Jibberish">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import airhockey.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.DoubleBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import net.java.games.input.Controller;
import objects.JInputJoystick;
//</editor-fold>

/**
 * BoardPanel bevat alle code tussen de GUI en de CodeBehind.
 *
 * @author jeroen
 */
public class BoardPanel extends JComponent implements Runnable, MouseListener {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //<editor-fold defaultstate="collapsed" desc="Virtuele Veld">
    double veldWidth = 1000;
    double veldHeight = Math.sqrt((veldWidth * veldWidth) - ((veldWidth / 2) * (veldWidth / 2)));
    //</editor-fold>
    private Spel spel;
    private String besturing;
    private int direction = 0;
    private int mouseLocation;
    private JInputJoystick joystick;
    private SpelForm spelForm;
    private double multiplier;
    private double xTranslation;
    private double yTranslation;
    Thread spelThread;

    public void setMouseLocation(int mouseLocation) {
	this.mouseLocation = mouseLocation;
    }

    public void setDirection(int direction) {
	this.direction = direction;
    }

    public String getBesturing() {
	return this.besturing;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Deze constructor wordt gebruikt om als Bean aangemaakt te worden.
     */
    public BoardPanel() {
	initComponents();
	addMouseListener(this);
    }

    /**
     * Start het spel met alle Threads die daarvoor nodig zijn. Ook wordt Double
     * Buffering aan gezet.
     */
    public void Initialize(Spel spel, String besturing, JInputJoystick joystick,SpelForm spelform) {
	this.spel = spel;
	this.besturing = besturing;
	this.joystick = joystick;
        this.spelForm = spelform;
	spelThread = new Thread(this.spel);
	spelThread.start();
	Thread painThread = new Thread(this);
	painThread.start();
	setDoubleBuffered(true);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Set Player Label">
    /**
     * Hierin word de scoren van een speler aangepast.
     *
     * @param text     de text die weergegeven moet worden.
     * @param playerNr het nummer van de speler waar de score bij hoot.
     */
    public void setPlayerLabel(String text, int playerNr) {
	switch (playerNr) {
	    case 1:
		jLabelPlayer1.setText(text);
		break;
	    case 2:
		jLabelPlayer2.setText(text);
		break;
	    case 3:
		jLabelPlayer3.setText(text);
		break;
	}
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Paint Component">
    /**
     * Hierin wordt het virtueel opgebouwde veld, met alle componenten
     * uitgeschreven naar het scherm.
     *
     * @param g is het Graphics component waarin gepaint wordt.
     */
    @Override
    public void paintComponent(Graphics g) {
	if (spel.isVisible()) {
	    //<editor-fold defaultstate="collapsed" desc="Initialize">
	    jButtonRestart.setVisible(false);
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g;
	    if (getWidth() > ((Math.tan(Math.toRadians(30)) * getHeight()) * 2)) {
		double A = ((Math.tan(Math.toRadians(30)) * getHeight()) * 2);
		double B = getHeight();
		multiplier = A / veldWidth;
		multiplier = B / veldHeight;
	    } else {
		double A = getWidth() / 2;
		double C = getWidth();
		double B = Math.sqrt((C * C) - (A * A));
		multiplier = B / veldWidth;
		multiplier = C / veldHeight;
	    }
	    xTranslation = (int) (getWidth() - (veldWidth * multiplier)) / 2;
	    yTranslation = (int) (getHeight() - (veldHeight * multiplier)) / 2;
        //</editor-fold>

	    //<editor-fold defaultstate="collapsed" desc="Draw">
	    spel.puck.draw(g, multiplier, xTranslation, yTranslation);
	    for (Lijn l : spel.getLijnen()) {
		l.draw(g, multiplier, xTranslation, yTranslation);
	    }
	    for (Lijn l : spel.getGoals()) {
		l.draw(g, multiplier, xTranslation, yTranslation);
	    }
	    for (Speler s : spel.getSpelers()) {
		s.batje.draw(g, multiplier, xTranslation, yTranslation);
	    }
	    jLabelPlayer1Score.setText(String.valueOf("(" + String.format("%.2f", spel.getSpeler(1).getRating()) + ")" + spel.getSpeler(1).getScore()) + " Punten");
	    jLabelPlayer2Score.setText(String.valueOf(spel.getSpeler(2).getScore()) + " Punten");
	    jLabelPlayer3Score.setText(String.valueOf(spel.getSpeler(3).getScore()) + " Punten");
            if (spel.getRonde()!=11)
	    jLabelRonde.setText("Ronde : " + String.valueOf(spel.getRonde()));
	    //</editor-fold>
	} else {
	    jButtonRestart.setVisible(true);
	}
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

        jLabelPlayer1Score = new javax.swing.JLabel();
        jLabelPlayer2 = new javax.swing.JLabel();
        jLabelPlayer3 = new javax.swing.JLabel();
        jLabelPlayer1 = new javax.swing.JLabel();
        jLabelPlayer3Score = new javax.swing.JLabel();
        jLabelPlayer2Score = new javax.swing.JLabel();
        jLabelRonde = new javax.swing.JLabel();
        jButtonRestart = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(400, 400));

        jLabelPlayer1Score.setText(": 20 Punten");

        jLabelPlayer2.setText("Groen");

        jLabelPlayer3.setText("Blauw");

        jLabelPlayer1.setText("Rood");

        jLabelPlayer3Score.setText(": 20 Punten");

        jLabelPlayer2Score.setText(": 20 Punten");

        jLabelRonde.setText("Ronde :");

        jButtonRestart.setText("Restart");
        jButtonRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRestartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelRonde)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                        .addComponent(jLabelPlayer1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPlayer2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelPlayer3, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPlayer1Score, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelPlayer2Score, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelPlayer3Score, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jButtonRestart)
                .addGap(150, 150, 150))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPlayer1Score)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelPlayer2Score)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelPlayer3Score))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPlayer1)
                            .addComponent(jLabelRonde))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelPlayer2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelPlayer3)))
                .addGap(75, 75, 75)
                .addComponent(jButtonRestart)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRestartActionPerformed
	spel.restart();
        if (this.besturing == "Toetsenbord")
        spelForm.requestFocusInWindow();
    }//GEN-LAST:event_jButtonRestartActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRestart;
    private javax.swing.JLabel jLabelPlayer1;
    private javax.swing.JLabel jLabelPlayer1Score;
    private javax.swing.JLabel jLabelPlayer2;
    private javax.swing.JLabel jLabelPlayer2Score;
    private javax.swing.JLabel jLabelPlayer3;
    private javax.swing.JLabel jLabelPlayer3Score;
    private javax.swing.JLabel jLabelRonde;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Run">
    /**
     * Hierin wordt Repaint aangeroepen, en wordt gekeken naar de input van de
     * Joystick, en de Muis.
     */
    @Override
    public void run() {
	while (true) {
	    try {
		Thread.sleep(10);
		//<editor-fold defaultstate="collapsed" desc="Joystick">
		int locatie = spel.getSpeler(1).batje.getLocation().x - 296;
		if (joystick.isControllerConnected() && besturing.equals("Controller")) {
		    joystick.pollController();
		    int direction = (int) joystick.getXAxisValue();
		    int locatieVerplaatsing = 90;
		    if (direction == 1) {
			locatieVerplaatsing = 0;
		    }
		    if (direction == -1) {
			locatieVerplaatsing = 180;
		    }
		    if (locatieVerplaatsing != 90) {
			spel.getSpeler(1).batje.verplaatsBatje(locatieVerplaatsing);
		    }
		}

		if (besturing.equals("Toetsenbord")) {
		    int locatieVerplaatsing = 90;
		    if (direction == 1) {
			locatieVerplaatsing = 0;
		    }
		    if (direction == -1) {
			locatieVerplaatsing = 180;
		    }
		    if (locatieVerplaatsing != 90) {
			spel.getSpeler(1).batje.verplaatsBatje(locatieVerplaatsing);
		    }
		}
                //</editor-fold>

		//<editor-fold defaultstate="collapsed" desc="Mouse">
		if (mouseLocation != 0 && besturing.equals("Muis")) {
		    try {
			mouseLocation = getMousePosition().x;
		    } catch (Exception e) {
			mouseLocation = 0;
		    }
		    if ((spel.getSpeler(1).batje.getLocation().getX() * multiplier) + xTranslation > mouseLocation + 20) {
			spel.getSpeler(1).batje.verplaatsBatje(180);
		    } else if ((spel.getSpeler(1).batje.getLocation().getX() * multiplier) + xTranslation < mouseLocation - 20) {
			spel.getSpeler(1).batje.verplaatsBatje(0);
		    }
		    //spel.getSpeler(1).batje.setX(mouseLocation + 300);
		}
		//</editor-fold>
		repaint();
	    } catch (InterruptedException ex) {
		Logger.getLogger(BoardPanel.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mouse">
    @Override
    public void mouseClicked(MouseEvent e) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	try {
	    setMouseLocation(getMousePosition().x);
	} catch (NullPointerException ex) {
	    setMouseLocation(0);
	}
    }

    @Override
    public void mouseExited(MouseEvent e) {
	setMouseLocation(0);
    }
    //</editor-fold>
}
