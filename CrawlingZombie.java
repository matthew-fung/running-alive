import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A faster and stronger version of the zombie.
 * 
 * @author MACKX
 * @version Final
 */
public class CrawlingZombie extends Monsters
{
    /**
     *Contains all the intial stat values regarding health, movement and score.
     */
    public CrawlingZombie(){
        hp = 2000;
        moveSpeed = 3;
        damage = 100;
        sightRange = 200;
        value = 75;
        tier = 2;
        bar = new AllBar (hp, this, 1);
    }
    
    /**
     * Runs the methods related to health, movement and damage
     */
    public void act() 
    {
        if(!isTouching(Humans.class)) {
            setImage("CrawlingZombie.png");
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
