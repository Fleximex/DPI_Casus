/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import game.GameState;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.NamingException;

/**
 *
 * @author Maarten
 */
public abstract class ClientGateway
{
    private MessageSenderGateway sender = null;
    private MessageReceiverGateway receiver = null;
    private final GameStateSerializer serializer;

    public ClientGateway(String senderQueue, String receiverQueue) throws JMSException, NamingException
    {
        serializer = new GameStateSerializer();
        
        if(senderQueue != "null")
        {
            sender = new MessageSenderGateway(senderQueue);
            sender.openConnection();
        }
        
        receiver = new MessageReceiverGateway(receiverQueue);
        receiver.setReceivedMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message msg)
            {
                TextMessage m = (TextMessage) msg;
                try 
                {                    
                    onGameStateArrived(serializer.gameStateFromJson(m.getText()));
                } 
                catch (JMSException ex)
                {
                    Logger.getLogger(ClientGateway.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        receiver.openConnection();
    }    
        
    public boolean sendGameState(GameState gameState)
    {
        try
        {
            Message m = sender.createTextMessage(serializer.gameStateToJson(gameState));
            return sender.sendMessage((TextMessage) m);
        }
        catch (JMSException ex)
        {
            Logger.getLogger(GameState.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public abstract void onGameStateArrived(GameState gameState);
    
    public void closeConnection()
    {        
        try
        {
            if(sender != null)
            {
                sender.closeConnection();
            }
            receiver.closeConnection();
        }
        catch (JMSException ex)
        {
            Logger.getLogger(ClientGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}