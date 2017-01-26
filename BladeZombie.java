import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Larger zombie dealing heavy damage and high health, also very fast
 * 
 * @author MACKX 
 * @version Final
 */
public class BladeZombie extends Monsters
{
    /**
     *Contains all the intial stat values regarding health, movement and score.
     */
    public BladeZombie(){
        hp = 3000;
        moveSpeed = 3;
        damage = 300;
        sightRange = 250;
        value = 1250;
        tier = 4;
        bar = new AllBar (hp, this, 1);
    }

    /**
     * Runs the various methods related to movement, health and damage
     */
    public void act() 
    {
        if(!isTouching(Humans.class)) {
            setImage("BladeZombie.png");
        }
        bar.updateHP(hp);
        if (barAdded == false) {
            getWorld().addObject(bar, getX(), getY() - 22);
            barAdded = true;
        }
        chaseHumans();
        checkDeath();
    }       
}
