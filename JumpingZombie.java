import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * This zombie moves differently from others, it will only move if the player is in range
 * and it only moves by "jumping" in intervals towards the player
 *
 * @author Xuan Kong
 * @version Final
 */
public class JumpingZombie extends Monsters
{
    
    //Variables only available to this zombie, related to how it attacks players.
    private int jumpCooldown;
    private boolean jumpReady;
    private int attack;
    /**
     *Contains all the intial stat values regarding health, movement and score.
     */
    public JumpingZombie(){
        hp = 500;
        moveSpeed = 0;
        damage = 250;
        sightRange = 300;
        value = 300;
        jumpCooldown = 10000;
        jumpReady = true;
        tier = 3;
        bar = new AllBar (hp, this, 1);
    }

    /**
     * Runs the various methods related to movement and health.
     */
    public void act()
    {
        hunt();
        bar.updateHP(hp);
        if (barAdded == false) {
            getWorld().addObject(bar, getX(), getY() - 22);
            barAdded = true;
        }
        checkDeath();
    }

    /**
     *Different from other move methods as it only moves depending on the cooldown of its jump.
     */
    private void hunt() {
        Humans = (ArrayList) getObjectsInRange(sightRange,Humans.class);
        //Consists of the code that causes the Hunterzombie to jump towards the player.
        //Done by increasing move to a large number and quickly diminishing that speed.
        if (!Humans.isEmpty()) {
            //chooses first Humans in range.
            targetHumans = Humans.get(0);
            turnTowards(targetHumans.getX(),targetHumans.getY());
            if (jumpReady) {
                setImage("JumpingZombieOpen.png");
                move (8);
                jumpCooldown -= 500;
            }
            if (isTouching(Humans.class)) {
                move(0);
                targetHumans = Humans.get(0);//chooses and sets first Human in range
                attack++;
                setImage("HunterZombie.png");
                jumpReady = false;
                jumpCooldown = 0;
                if(attack == 20) {
                    targetHumans.attack(getDamaged());//Causes the Human to be damaged
                    attack = 0;
                }
            }
        }

        //jumpReady is required otherwise jumpCooldown will increase as soon as it begins decreasing
        if (jumpCooldown == 0 || !jumpReady) {
            move(0);
            jumpCooldown += 200;
            jumpReady = false;
            setImage("HunterZombie.png");
        }
        if (jumpCooldown == 10000) {
            jumpReady = true;
        }

    }
}
