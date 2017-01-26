import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Monsters superclass containing methods for use by all monsters.
 * 
 * @author MACKX
 * @version Final
 */
public class Monsters extends Mobs
{
    //Arraylists and variables used in the animation of the monster, value is the "score" of the monster
    protected ArrayList<Humans> Humans;
    protected Humans targetHumans;
    protected int value;
    protected int i;

    /**
     *Contains animations for all monsters and deals damage according to which frame is being played
     */
    protected void animate()
    {
        i++;
        Humans = (ArrayList) getObjectsInRange(150,Humans.class);
        //chooses first Humans in range.
        //tier acts as an ID for each monster class that deals damage.
        if (tier == 1){  
            if(i == 10){
                setImage("ZombieAttack1.png");
            }
            else if(i == 20){
                setImage("ZombieAttack2.png");
                if (isTouching(Humans.class)){
                    targetHumans = Humans.get(0);//chooses and sets first Human in range
                    targetHumans.attack(getDamaged());//Causes the Human to be damaged
                }
            }
            else if(i == 30){
                setImage("ZombieAttack3.png");
            }
            else if(i == 40){
                setImage("ZombieAttack4.png");
                i=0;
            }
            else if(i == 0){
                setImage("StandardZombie.png");
            }
        }
        else if(tier == 2){
            if(i == 10){
                setImage("CrawlerAttack1.png");
            }
            else if(i == 20){
                setImage("CrawlerAttack2.png");
                if (isTouching(Humans.class)){
                    targetHumans = Humans.get(0);//chooses and sets first Human in range
                    targetHumans.attack(getDamaged());//Causes the Human to be damaged
                }
            }
            else if(i == 30){
                setImage("CrawlerAttack3.png");
            }
            else if(i == 40){
                setImage("CrawlerAttack4.png");
                i = 0;
            }
            else if(i == 0){
                setImage("CrawlingZombie.png");
            }
        }
        else if(tier == 4){
            if (i == 10) {
                setImage("Slasher1.png");
            }
            else if(i == 20){
                setImage("Slasher2.png");
            }
            else if(i == 30){
                setImage("Slasher3.png");
                if (isTouching(Humans.class)){
                    targetHumans = Humans.get(0);//chooses and sets first Human in range
                    targetHumans.attack(getDamaged());//Causes the Human to be damaged
                }
            }
            else if(i == 40){
                setImage("Slasher4.png");
            }
            else if(i == 50){
                setImage("Slasher5.png");
                i=0;
            }
            else if(i == 0){
                setImage("BladeZombie.png");
            }
        }
    }

    /**
     * Chases Players in the World.
     */
    protected void chaseHumans() 
    {
        //This list checks for Humans in range of this monster.
        Humans = (ArrayList) getObjectsInRange(sightRange,Humans.class);
        if (!Humans.isEmpty() && !isTouching(Humans.class)) 
        {
            //chooses first Humans in range.
            targetHumans = Humans.get(0);
            turnTowards(targetHumans.getX(),targetHumans.getY());
            if(isTouching(Walls.class)){
                move(1);
            }
            else{
                move(moveSpeed);
            }
        }
        //If it is not touching a Humans, it will move randomly.
        if (!isTouching(Humans.class))
        {
            moveRandomly();
        }
        //If it touches a Humans object, it will run the animate method.
        else if (isTouching(Humans.class) == true)
        {
            animate();
        }
    }

    /**
     *Chases players in the world, includes parameter that involves walls
     */
    protected void chaseHumans(boolean ignoreWalls) 
    {
        //This list checks for Humans in range of this monster.
        Humans = (ArrayList) getObjectsInRange(sightRange,Humans.class);
        if (!Humans.isEmpty() && !isTouching(Humans.class)) 
        {
            //chooses first Humans in range.
            targetHumans = Humans.get(0);
            turnTowards(targetHumans.getX(),targetHumans.getY());
            move(moveSpeed);
        }
        //If it is not touching a Humans or Building object, it will move randomly.
        if (!isTouching(Humans.class))
        {
            moveRandomly(ignoreWalls);
        }
        //         //If it touches a Humans object, it will run the animate method.
        //         else if (isTouching(Humans.class) == true)
        //         {
        //             animate();
        //         }
    }

    /**
     * Moves the Monster in a random direction, at its own speed.
     */
    protected void moveRandomly ()
    {        
        if (Greenfoot.getRandomNumber(100) < 5) {
            move(moveSpeed);
            if(isTouching(Walls.class)){
                move(-moveSpeed);
            }

        }
        else if (Greenfoot.getRandomNumber (300) == 1 && !isTouching(Humans.class))
        {
            turn (Greenfoot.getRandomNumber(360));

        }
    }

    /**
     * Moves the Monster in a random direction, at its own speed.
     * @param ignoreWalls
     */
    protected void moveRandomly (boolean ignoreWalls)
    {

        if (Greenfoot.getRandomNumber(100) < 5) {
            move(moveSpeed);            
            if(getX()<=0 && getX()>=600 && getY()<=0 && getY()>=600){
                move(-moveSpeed);
            }

        }
        else if (Greenfoot.getRandomNumber (300) == 1 && !isTouching(Humans.class))
        {
            turn (Greenfoot.getRandomNumber(360));
            move(0);
        }
    }
     /**
     *Method for adding points based on the monster defeated
     */
    protected void addScore (int points) {
        SurvivalGame world = (SurvivalGame) getWorld();
        Counter counter = world.getCounter();
        counter.bumpCount(points);
    }

    /**
     * Checks if a Monster's hp is less than 0: if it is, then the Mob will be removed from the World.
     */
    protected void checkDeath()
    {
        if(hp <= 0)
        {
            dropBreakable();
            addScore(value);
            getWorld().removeObject(this);
        }
    }
    /**
     *Method for detecting damage and responding accordingly
     */
    protected void attacked(int damage) {
        hp -= damage;
    }
}
