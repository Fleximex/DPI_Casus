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
public class Position
{
    private final int xPos; //first member of pair
    private final int yPos; //second member of pair

    public Position(int xPos, int yPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public int getxPos()
    {
        return xPos;
    }

    public int getyPos()
    {
        return yPos;
    }
}
