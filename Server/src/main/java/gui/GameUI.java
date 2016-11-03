/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import game.Game;
import game.ResourceAmount;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Maarten
 */
public class GameUI extends javax.swing.JFrame
{
    private JPanel buttonPanel;
    private Game game;
    
    public GameUI()
    {
        
        game = new Game(10, 10, ResourceAmount.MEDIUM);
        URL location = GameUI.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
        init();
    }
    
    private void init()
    {
        this.setSize(800, 800);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 10));
        gridLayout(10, 10);
        //this.pack();
        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        ImageIcon imageIconBeta =  new ImageIcon(this.getClass().getResource("/doge.png")); 
        for (Component button : buttonPanel.getComponents())
        {
            Image img = imageIconBeta.getImage();
            img.getScaledInstance(((JButton) button).getWidth(), ((JButton) button).getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(img);
            ((JButton) button).setIcon(imageIcon);
        }
    }
    
    private void gridLayout(int x, int y)
    {
        JButton button;
        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                button = new JButton();
                buttonPanel.add(button);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
