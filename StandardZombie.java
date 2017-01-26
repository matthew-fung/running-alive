import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Basic zombie, standard and common.
 * 
 * @author MACKX
 * @version Final
 */
public class StandardZombie extends Monsters
{
    /**
     *Contains all the intial stat values regarding health, movement and score.
     */
    public StandardZombie(){
        hp = 1000;
        moveSpeed = 1;
        damage = 50;
        sightRange = 275;
        value = 50;
        tier = 1;
        bar = new AllBar (hp, this, 1);
    }

    /**
     * Act - do whatever the StandardZombie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!isTouching(Humans.class)) {
            setImage("StandardZombie.png");
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
