import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Contains all the methods that each breakable will use.
 * 
 * @author MACKX
 * @version 2.0.0
 */
public class Breakables extends Actor
{
    protected int hp;
    //Breakables' HP

    protected boolean destroyed;
    //Destroyed toggle

    private int spawnRate = Greenfoot.getRandomNumber(99);
    //Random drop rate generator

    protected long delay;
    //delay for the breakable to be removed from World

    /**
     * When ever a breakable item gets him this method runs to lower damage.
     * 
     */ 
    protected void checkDeath(int damage) {
        hp -= damage;
    }

    /**
     * Checks if the Boulder is destroyed. 
     * If it is destroyed, there is a 20% chance for it to drop a M60, and is removed from World.
     */ 
    public void dropBoulder(){
        if(hp <=0){
            setImage("BoulderBroken.png");
            if(delay>0){
                delay--;

                destroyed = true;
            }
            else{

                getWorld().addObject(new M60(), getX(),getY()-7);
                getWorld().removeObject(this);
            }
        }
    }

    /**
     * Checks if the TrashCan is destroyed. 
     * If it is destroyed:
     * There is a 75% chance for it to drop a Pistol,and TrashCan is removed from World.
     * There is a slight chance for a HealthKit to drop, and TrashCan is removed from world.
     */
    public void dropTrashCan(){
        if(hp <=0){

            setImage("TrashcanBroken.png");
            if(delay>0){
                delay--;
                destroyed = true;
            }
            else{
                if(spawnRate < 75){
                    getWorld().addObject(new Pistol(), getX(),getY());
                }
                if(spawnRate >= 75 && spawnRate <= 99 ) {
                    getWorld().addObject(new HealthKit(),getX(),getY());
                }
                getWorld().removeObject(this);
            }
        }
    }

    /**
     * Checks if the CrateOne is destroyed. 
     * If it is destroyed, there is a 30% chance for it to drop an AK47, and is removed from World.
     * There is also a slight chance for a HealthKit to drop, and  is removed from world.
     */
    public void dropCrateOne(){
        if(hp<=0){

            setImage("CrateBroken.png");
            if(delay>0){
                delay--;
                destroyed = true;
            }
            else{
                if(spawnRate < 30){
                    getWorld().addObject(new AK47(),getX(), getY());
                }
                if(spawnRate >= 30 && spawnRate <= 70 ) {
                    getWorld().addObject(new HealthKit(),getX(),getY());
                }
                getWorld().removeObject(this);
            }
        }
    }

    /**
     * Checks if the CrateTwo is destroyed. 
     * If it is destroyed, there is a 30% chance for it to drop an AA12, and is removed from World.
     * There is also a slight chance for a HealthKit to drop, and is removed from world.
     */
    public void dropCrateTwo(){
        if(hp<=0){

            setImage("CrateBroken.png");
            if(delay>0){
                delay--;
                destroyed = true;
            }
            else{
                if(spawnRate < 30){
                    getWorld().addObject(new SPAS12(),getX(), getY());
                }
                if(spawnRate >= 30 && spawnRate <= 70 ) {
                    getWorld().addObject(new HealthKit(),getX(),getY());
                }
                getWorld().removeObject(this);
            }

        }
    }
}
