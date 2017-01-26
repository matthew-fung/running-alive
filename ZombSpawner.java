import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * The location for all types of monsters to spawn from.
 * 
 * @author MACKX
 * @version Final
 */
public class ZombSpawner extends Actor
{
    private ArrayList <Monsters> Monsters;
    private int bigMob;
    private int counter = 0;
    private int spawnDown = 0;

    /**
     * Checks for the total number of monsters and spawns them accordingly.
     */
    public void act() 
    {
        int xCoordOne = getX();
        int yCoordOne = getY();
        int mobSpawner=Greenfoot.getRandomNumber(12000 - spawnDown);
        Monsters = (ArrayList) getObjectsInRange(450,Monsters.class);
        //This detects how many Monsters are in the world and spawns if conditions are met.
        if(Monsters.size()<8){
            if (mobSpawner>=0 && mobSpawner<=100){
                getWorld().addObject(new StandardZombie(),getX(),yCoordOne);
            }    
            if (mobSpawner>=121 && mobSpawner<=128){
                getWorld().addObject(new CrawlingZombie (),getX(),yCoordOne);
            }  
            if (mobSpawner>=129 && mobSpawner<=131){
                bigMob++;
                if (bigMob % 2 == 0) {
                    getWorld().addObject(new JumpingZombie(),getX(),yCoordOne);
                }
            }  
            if (mobSpawner>=132 && mobSpawner<=133){
                bigMob++;
                if(bigMob%3 == 0) {
                    getWorld().addObject(new BladeZombie(),getX(),yCoordOne);
                }
            }  
        }
        counter++;
        if (counter >= 25000) {
            spawnDown = 1200;
        } else if (counter >= 80000) {
            spawnDown = 2800;
        }
    }
}

