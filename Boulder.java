import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The boulder is the hardest breakable to break.
 * Drop rate: 20%
 * Drop: M60
 * 
 * @author MACKX
 * @version 2.0.0
 */
public class Boulder extends Breakables
{
    public Boulder() {
        hp = 3000;
        delay = 50;
    }
    /**
     * Act - do whatever the Boulder wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        dropBoulder();
    }    
}
