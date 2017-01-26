import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * HealthKit heals the Human when it is used.
 * 
 * @author MACKX
 * @version 2.0.1
 */
public class HealthKit extends Actor
{
    private boolean used;
    private int countDown;
    //Variable for keeping track if the healthkit has been touched by the player.
    //True = used
    //False = still in world

    /**
     * Checks if the HealthKit is touching the Human. 
     * If it is, the health kit is removed from the world.
     */

    public void checkUsed(){
        if(!getObjectsAtOffset(0,0,Humans.class).isEmpty()){
            used = true;
            countDown++;
            if (countDown >= 5) {
                getWorld().removeObject(this);
            }
        }
    }

    /**
     * Act - do whatever the HealthKit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkUsed();
    }    
}
