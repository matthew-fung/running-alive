import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player type that uses the Spas 12 weapon.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Human_Spas12 extends Humans
{
    /**
     * Consists of all the stats that the player contains.
     * 
     * @param direction Used in the direction which the player faces after being spawned into the world
     * @param newHP The hp given upon the intial spawn of the player into the world
     * @param pistol The intial state of the player in terms of the pistol upon the intial spawn of the player into the world, true for useable, false for not
     * @param ak47 The intial state of the player in terms of the ak47 upon the intial spawn of the player into the world, true for useable, false for not
     * @param spas12 The intial state of the player in terms of the spas12 upon the intial spawn of the player into the world, true for useable, false for not
     * @param m60 The intial state of the player in terms of the m60 upon the intial spawn of the player into the world, true for useable, false for not
     */
    public Human_Spas12(int direction,int newHP,boolean pistol, boolean ak47, boolean spas12, boolean m60) {
        setRotation(direction);
        speed = 3;
        fireRate = 30;
        wid = 3;
        ammo = 8;
        hp = newHP;
        pistolGet = pistol;
        ak47Get = ak47;
        spas12Get = spas12;
        m60Get = m60;
        bar = new AllBar (hp, this, 1);
    }

    /**
     * Runs the various methods from the Mobs and Humans superclass
     * Includes methods for updating hp and hp related methods
     */
    public void act() 
    {
        if (hp <= 0) {
            getWorld().addObject(new DeadScreen(),300,300);
        }
        bar.updateHP(hp);
        if (barAdded == false) {
            getWorld().addObject(bar, getX(), getY() - 22);
            barAdded = true;
        }
        dropDetection();
        movement();
        checkDeath();
    }    
}