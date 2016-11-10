/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import game.GameState;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.naming.NamingException;
import jms.ClientGateway;

/**
 *
 * @author Maarten
 */
public class GameUI extends javax.swing.JFrame
{    
    private ArrayList<GameState> gameStates = new ArrayList<>();
    private ArrayList<ClientGateway> clientGateways = new ArrayList<>();
    private ClientGateway clientGateway;
    private int round = 1;
    private boolean nextRound = false;
    private boolean someoneWinner = false;
    private String theWord;
    
    public GameUI()
    {
        init();
    }
    
    private void init()
    {
        initComponents();
        try
        {
            clientGateway  = new ClientGateway("null", "ConnectingQueue")
            {
                @Override
                public void onGameStateArrived(GameState gameState)
                {
                    jTextArea1.setText("");
                    if (!gameState.isConnected())
                    {
                        try
                        {
                            ClientGateway gameGateway = new ClientGateway("ConnectedQueue" + Integer.toString(gameState.getPlayerID()), "ToGameServerQueue")
                            {
                                @Override
                                public void onGameStateArrived(GameState gameState)
                                {
                                    nextRound = true;
                                    gameState.setRound(round + 1);
                                    for (int i = 0; i < gameStates.size(); i++)
                                    {
                                        if (gameStates.get(i).getPlayerID() == gameState.getPlayerID())
                                        {
                                            GameState gameStateCurrent = gameState;
                                            if (theWord.contains(gameStateCurrent.getGuessLetter()))
                                            {
                                                gameStateCurrent.setGoodGuess(true);
                                                StringBuilder guessWord = new StringBuilder(gameStateCurrent.getGuessWord());
                                                int index = theWord.indexOf(gameStateCurrent.getGuessLetter());
                                                while(index >= 0)
                                                {
                                                    char theChar = gameStateCurrent.getGuessLetter().charAt(0);
                                                    System.out.println(theChar);
                                                    guessWord.setCharAt(index, theChar);
                                                    index = theWord.indexOf(gameStateCurrent.getGuessLetter(), index + 1);
                                                }
                                                gameStateCurrent.setGuessWord(guessWord.toString());
                                            }
                                            gameStates.set(i, gameStateCurrent);
                                            if(gameState.getGuessWord().equals(theWord))
                                            {
                                                someoneWinner = true;
                                                GameState gameStateWinner = gameStates.get(i);
                                                gameStateWinner.setWinner(true);
                                                gameStates.set(i, gameStateWinner);
                                            }
                                        }
                                        if (gameStates.get(i).getRound() != round + 1)
                                        {
                                            nextRound = false;
                                        }
                                    }
                                    if (nextRound)
                                    {
                                        round++;
                                        nextRound = false;
                                        jTextArea1.setText("");
                                        for (int i = 0; i < gameStates.size(); i++)
                                        {
                                            if(someoneWinner)
                                            {
                                                GameState gameStateLoser = gameStates.get(i);
                                                gameStateLoser.setSomeoneWinner(true);
                                                gameStates.set(i, gameStateLoser);
                                            }
                                            
                                            if (!clientGateways.get(i).sendGameState(gameStates.get(i)))
                                            {
                                                System.out.println("ERROR: game out of sync!");
                                            }
                                            
                                            if(gameStates.get(i).isWinner())
                                            {
                                                jTextArea1.append("WINNER - Player: " + gameStates.get(i).getPlayerID() + " | Current Guess: '" + gameStates.get(i).getGuessWord() + "' - WINNER" + "\n");
                                            }
                                            else
                                            {
                                                jTextArea1.append("Player: " + gameStates.get(i).getPlayerID() + " | Current Guess: '" + gameStates.get(i).getGuessWord() + "'" + "\n");
                                            }
                                        }
                                    }
                                }
                            };
                            if(!gameGateway.sendGameState(gameState))
                            {
                                System.out.println("ERROR: game out of sync!");
                            }
                            gameStates.add(gameState);
                            clientGateways.add(gameGateway);
                        }
                        catch (JMSException | NamingException ex)
                        {
                            Logger.getLogger(GameUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    for (GameState gs : gameStates)
                    {
                        jTextArea1.append("Player: " + gs.getPlayerID() + "\n");
                    }
                    
                }
            };
        }
        catch (JMSException | NamingException ex)
        {
            Logger.getLogger(GameUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent)
            {
                clientGateway.closeConnection();
                for (int i = 0; i < clientGateways.size(); i++)
                {
                    clientGateways.get(i).closeConnection();
                }
            }
        });
        this.setVisible(true);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jButton1.setText("Start Game");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("The Word:");

        jLabel2.setText("Players:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButton1.setEnabled(false);
        theWord = jTextField1.getText();
        for (int i = 0; i < gameStates.size(); i++)
        {
            GameState gameStateNew = gameStates.get(i);
            String guessWord = "";
            for (int j = 0; j < theWord.length(); j++)
            {
                guessWord = guessWord + ".";
            }
            gameStateNew.setGuessWord(guessWord);
            gameStateNew.setConnected(true);
            gameStates.set(i, gameStateNew);
            if (!clientGateways.get(i).sendGameState(gameStates.get(i)))
            {
                System.out.println("ERROR: game out of sync!");
            }
        }
        this.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        if (jTextField1.getText().length() > 20)
        {
            jTextField1.setText(jTextField1.getText().substring(0, jTextField1.getText().length() -1));
        }
    }//GEN-LAST:event_jTextField1KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
