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
public abstract class Unit 
{
    private UnitType unitType;
    private Position position;

    public UnitType getUnitType()
    {
        return unitType;
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }
}
