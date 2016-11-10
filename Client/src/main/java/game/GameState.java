/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Maarten
 */
public class GameState
{
    private final int PlayerID;
    private boolean connected = false;
    private int round = 1;
    private String guessWord;
    private String guessLetter = "";
    private boolean winner = false;
    private boolean someoneWinner = false;
    private boolean guess = false;
    private boolean goodGuess = false;
    
    public GameState(int playerID)
    {
        this.PlayerID = playerID;
    }

    public int getPlayerID()
    {
        return PlayerID;
    }

    public boolean isConnected()
    {
        return connected;
    }

    public void setConnected(boolean connected)
    {
        this.connected = connected;
    }

    public int getRound()
    {
        return round;
    }

    public void setRound(int round)
    {
        this.round = round;
    }

    public String getGuessWord()
    {
        return guessWord;
    }

    public void setGuessWord(String guessWord)
    {
        this.guessWord = guessWord;
    }

    public String getGuessLetter()
    {
        return guessLetter;
    }

    public void setGuessLetter(String guessLetter)
    {
        this.guessLetter = guessLetter;
    }

    public boolean isWinner() 
    {
        return winner;
    }

    public void setWinner(boolean winner)
    {
        this.winner = winner;
    }

    public boolean isSomeoneWinner()
    {
        return someoneWinner;
    }

    public void setSomeoneWinner(boolean someoneWinner)
    {
        this.someoneWinner = someoneWinner;
    }

    public boolean isGuess()
    {
        return guess;
    }

    public void setGuess(boolean guess)
    {
        this.guess = guess;
    }

    public boolean isGoodGuess()
    {
        return goodGuess;
    }

    public void setGoodGuess(boolean goodGuess)
    {
        this.goodGuess = goodGuess;
    }
}
