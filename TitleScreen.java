import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title Screen
 * 
 * @author Ken Ng, Andrew Cheung
 * @version 2
 */
public class TitleScreen extends Actor {
    //variables
    private GreenfootImage title = new GreenfootImage ("RATitle.jpg");
    private StartScreen w;
    private boolean remove;
    private boolean instructions = false;
    
    /**
     * Constructor sets image
     */
    public TitleScreen (){
        this.setImage(title);
    }
    
    /**
     * Adds to world
     */
    protected void addedToWorld(World world) {
        this.w = (StartScreen) world;
    }
    
    /**
     * Removes screen if space is pressed
     * and adds an instructions page
     */
    public void act() 
    {
        if (Greenfoot.isKeyDown ("space")){
            remove = true;
        }
        
        if (remove == true && instructions == false){
            getWorld().addObject (new Instructions(), 300, 300);
            getWorld().removeObject (this);
        }
    }    
}