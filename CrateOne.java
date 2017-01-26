import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Crate type breakable one.
 * CrateOne is easier to break than CrateTwo
 * Drop rate: 30%
 * Drop: AK47
 * 
 * @author MACKX
 * @version 2.0.0
 */
public class CrateOne extends Breakables
{
    public CrateOne(){
        hp = 60;
        delay = 200;
    }

    /**
     * Crate type one, runs the dropCrate method that allows it to be destroyed/drop a weapon.
     */
    public void act() 
    {
        dropCrateOne();
    }
}

