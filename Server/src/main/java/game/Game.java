/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Maarten
 */
public class Game
{
    private int width;
    private int height;
    private ResourceAmount resourceAmount;
    private ArrayList<GridTile> gridTiles = new ArrayList<>();

    public Game(int width, int height, ResourceAmount resourceAmount)
    {
        this.width = width;
        this.height = height;
        
        init();
    }
    
    private void init()
    {
        generateGrid();
    }
    
    private void generateGrid()
    {
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                Position position = new Position(x, y);
                GridTile gridTile = new GridTile(position, randomGridTile());
                gridTiles.add(gridTile);
            }
        }
    }
    
    private GridTileType randomGridTile()
    {
        Random ran = new Random();
        int x = ran.nextInt(100) + 1;
        
        switch (resourceAmount)
        {
            case LOW:
                if (x <= 80)
                {
                   return GridTileType.NORMAL; 
                }
                else if (x > 80 && x <= 92)
                {
                    return GridTileType.SILVER;
                }
                else if (x > 92 && x <= 98)
                {
                    return GridTileType.GOLD;
                }
                else if (x > 98 && x <= 100)
                {
                    return GridTileType.DIAMOND;
                }
            break;
            case MEDIUM:
                if (x <= 70)
                {
                   return GridTileType.NORMAL; 
                }
                else if (x > 70 && x <= 87)
                {
                    return GridTileType.SILVER;
                }
                else if (x > 87 && x <= 95)
                {
                    return GridTileType.GOLD;
                }
                else if (x > 95 && x <= 100)
                {
                    return GridTileType.DIAMOND;
                }
            break;
            case HIGH:
                if (x <= 40)
                {
                   return GridTileType.NORMAL; 
                }
                else if (x > 40 && x <= 70)
                {
                    return GridTileType.SILVER;
                }
                else if (x > 70 && x <= 90)
                {
                    return GridTileType.GOLD;
                }
                else if (x > 90 && x <= 100)
                {
                    return GridTileType.DIAMOND;
                }
            break;
            case INSANE:
                if (x <= 10)
                {
                   return GridTileType.NORMAL; 
                }
                else if (x > 10 && x <= 50)
                {
                    return GridTileType.SILVER;
                }
                else if (x > 50 && x <= 80)
                {
                    return GridTileType.GOLD;
                }
                else if (x > 80 && x <= 100)
                {
                    return GridTileType.DIAMOND;
                }
            break;
            default:
                return GridTileType.NORMAL;
        }
        return GridTileType.NORMAL;
    }
}
