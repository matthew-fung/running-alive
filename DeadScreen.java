import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author Ken Ng
 * @version 1
 */
public class DeadScreen extends Actor
{
    //Variables
    private GreenfootImage title = new GreenfootImage ("deadScreen.jpg");
    private SurvivalGame w;
    
    /**
     * Sets image using constructor
     */
    public DeadScreen (){
        this.setImage(title);
    }
    
    /**
     * Adds to World
     */
    protected void addedToWorld(World world) {
        this.w = (SurvivalGame) world;
    }
    
    /**
     * Act - do whatever the TitleScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
    }    
}
