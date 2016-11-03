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
public class GridTile
{
    private final Position position;
    private final GridTileType gridTileType;

    public GridTile(Position position, GridTileType gridTileType)
    {
        this.position = position;
        this.gridTileType = gridTileType;
    }

    public Position getPosition() 
    {
        return position;
    }

    public GridTileType getGridTileType()
    {
        return gridTileType;
    }
}
