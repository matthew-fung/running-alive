import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Crate type breakable two.
 * CrateTwo is harder to break than CrateOne
 * Drop rate: 30%
 * Drop: AA12
 * 
 * @author MACKX 
 * @version 2.0.0
 */
public class CrateTwo extends Breakables
{
       /**
     * Sets health, and delay
     * 
     * @param rota Sets initial Rotation.
     */
    public CrateTwo () {
        hp = 100;
        delay = 200;
    }

    /**
     * Act - do whatever the Crate wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        dropCrateTwo();
    }
}

