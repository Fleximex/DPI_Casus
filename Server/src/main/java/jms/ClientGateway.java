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
    private final MessageSenderGateway sender;
    private final MessageReceiverGateway receiver;
    private final GameStateSerializer serializer;

    public ClientGateway(String senderQueue, String receiverQueue) throws JMSException, NamingException
    {
        serializer = new GameStateSerializer();
        
        sender = new MessageSenderGateway(senderQueue);
        sender.openConnection();
        
        receiver = new MessageReceiverGateway(receiverQueue);
        receiver.setReceivedMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message msg)
            {
                //TODO
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
}