import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This Superclass contains many code both monsters and humans classes use, mostly regarding its health and methods related to it in some way.
 *
 * @author MACKX
 * @version Final
 */
public class Mobs extends Actor
{
    //Common protected variables for each mob class.
    protected int hp;
    protected int damage;
    protected int moveSpeed;
    protected int sightRange;
    protected int tier;
    protected boolean barAdded = false;
    protected AllBar bar;

    /**
     * Gets the damage value of the respective Object.
     * @returns the damage the object deals
     */
    protected int getDamaged()
    {
        return damage;
    }

    /**
     * An object can use it to damage others or recieve damage from another source.
     * 
     * @param damage incurred from one objects attack.
     */
    protected void attack(int damage) {
        hp -= damage;
    }

    /**
     * Checks if a Mob's hp is less than 0: if it is, then the Mob will be removed from the World.
     */
    protected void checkDeath()
    {
        if(hp <= 0)
        {
            getWorld().removeObject(this);
        }
    }

    /**
     * Randomly drops a breakable
     */
    protected void dropBreakable () {
        int dropChance = Greenfoot.getRandomNumber(100);
        if (dropChance <= 25) {
            getWorld().addObject(new TrashCan(),getX(), getY());
        } else if (dropChance > 25 && dropChance <= 35) {
            getWorld().addObject(new CrateOne(),getX(), getY());
        } else if (dropChance > 36 && dropChance <= 46) {
            getWorld().addObject(new CrateTwo(),getX(), getY());
        }
    }

}
