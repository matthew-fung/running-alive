
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The trashcan is the easiest breakable to break.
 * Drop rate: 75%
 * Drop: HealthKit
 * 
 * @author MACKX
 * @version 2.0.0
 */
public class TrashCan extends Breakables
{
    public TrashCan(){
        hp = 30;
        delay = 200;
    }

    /**
     * Act - do whatever the TrashCan wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        dropTrashCan();
    }    
}
