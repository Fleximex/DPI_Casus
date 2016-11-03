/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import com.google.gson.Gson;
import game.GameState;

/**
 *
 * @author Maarten
 */
public class GameStateSerializer
{
    private final Gson gson;

    public GameStateSerializer()
    {
        gson = new Gson();
    }
    
    public String gameStateToJson(GameState gameState)
    {
        return gson.toJson(gameState);
    }
    
    public GameState gameStateFromJson(String gameState)
    {
        return gson.fromJson(gameState, GameState.class);
    }
}
