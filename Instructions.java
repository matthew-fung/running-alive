import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *Instructions Screen here.
 * 
 * @author Ken Ng
 * @version 1
 */
public class Instructions extends Actor {
    //variables
    private GreenfootImage instruction = new GreenfootImage ("Instructions.jpg");
    private StartScreen w;
    private boolean remove;
    
    /**
     * Constructor sets image
     */
    public Instructions (){
        this.setImage(instruction);
    }
    
    /**
     * adds tp wpr;d
     */
    protected void addedToWorld(World world) {
        this.w = (StartScreen) world;
    }
    
    /**
     * Removes this screen when enter is pressed
     * and starts the game
     */
    public void act() {
        if (Greenfoot.isKeyDown ("enter")){
            remove = true;
        }
        
        if (remove){
            getWorld().removeObject (this);
            Greenfoot.setWorld(new SurvivalGame());
        }
    }    
}