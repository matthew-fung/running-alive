import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Weapons in the RunningAlive game.
 * 
 * All weapons under this calss can be picked up as a drop
 * 
 * @author MACKX
 * @version Final
 */
public class Weapons extends Actor
{
    protected boolean equipped;
    //State of weapon being equipped by player
    //True = equipped, false = not equipped
    protected int turnCounter;
    /**
     * 
     * 
     */
    public void gunAttack(int hp, int damage){
        hp -= damage;
    }
    /**
     *  
     * 
     */
    public void meleeAttack(int hp, int damage){
        hp -=damage;
    }
    /**
     * The main method for drops, they will turn on the spot slowly and are removed from the world
     * if they either intersect a player or reach 50000 acts.
     */
    public void spawn (){
        //Checks to disappear if a player picks it up.
        if(!getObjectsAtOffset(0,0,Humans.class).isEmpty()){
            getWorld().removeObject(this);
        }
        //Removes the object from the world after 50000 acts and turns the object.
        for (int x = 0; x < 50000; x++) {
            if (x % 10000 == 0) {
                turn(1);
            }
         
            if (x == 50000) {
                getWorld().removeObject(this);
            }
        }
  
    }

}
