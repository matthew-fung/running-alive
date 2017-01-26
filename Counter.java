import greenfoot.*;
import java.awt.Color;
/**
 * The Score counter object for the game, records score.
 * 
 * @author MACKX
 * @version 1.0
 */
public class Counter extends Actor {
    private int totalCount;
    public Counter() {
        setImage(new GreenfootImage("Score: 0", 26, Color.BLACK, Color.GREEN));
    }
    public void bumpCount(int amount){
        totalCount += amount;
        setImage(new GreenfootImage("Score: " + totalCount, 26, Color.BLACK, Color.GREEN));
    }
}