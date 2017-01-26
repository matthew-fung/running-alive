import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bullet Deals damage to objects and monsters and removes itself when out of bound or touching walls
 * 
 * @author Ken Ng, Andrew Cheung
 * @version 2
 */
public class Bullet extends Actor {
    //variables
    private int speed;
    private boolean dead;
    private SurvivalGame w;
    private int fadeTimer;
    private GreenfootImage bullet = new GreenfootImage ("bullet.gif");
    private int damage;
    
    /**
     * Sets damage, rotation and transparency
     * 
     * @param rota Sets initial Rotation.
     */
    public Bullet (int rota) {
        damage=100;
        setRotation (rota);
        speed = 10;
        dead = false;
        this.setImage(bullet);
        fadeTimer = 0;
        bullet.setTransparency(0);
    }

    /**
     * Sets damage, rotation and transparency
     * 
     * @param rota Sets initial Rotation.
     * @param dmg The Damage of the bullet
     */
    public Bullet (int rota,int dmg) {
        damage=dmg;
        setRotation (rota);
        speed = 10;
        dead = false;
        this.setImage(bullet);
        fadeTimer = 0;
        bullet.setTransparency(0);
    }
    
    /**
     * Sets damage, rotation and transparency
     * 
     * @param rota Sets initial Rotation.
     * @param dmg The Damage of the bullet
     * @param transparency The Parency
     */
    public Bullet (int rota,int dmg, int transparency) {
        fadeTimer++;
        damage=dmg;
        setRotation (rota);
        speed = 10;
        dead = false;
        this.setImage(bullet);
        bullet.setTransparency(transparency);
    }

    /**
     * Add to World
     */
    protected void addedToWorld(World world) {
        this.w = (SurvivalGame) world;
    }

    /**
     * Checks if it should be dead or touching anything, and sets the transparency after a while
     */
    public void act() {
        //When the bullet should appear
        if (fadeTimer <= 30){
            fadeTimer++;
        }
        if (fadeTimer >=3) {
            bullet.setTransparency(250);
        }

        //The speed the bullet moves at.
        move (speed);

        //Checks if bullet reaches the sides. If the bullet does, it disappears.
        if (getX() <=2 || getX() >= 595){
            dead = true;
        }
        if (getY() <= 2 || getY() >= 595){
            dead = true;
        }
        //Checks if bullet is touching wall, if so, delete
        if(isTouching(Walls.class)){dead=true;}
        //Checks if bullet is touching monster, if so, deals damage
        if (isTouching(Monsters.class)){
            Monsters m = (Monsters)getOneIntersectingObject(Monsters.class);
            m.attacked(damage);
            dead=true;
        }
        //Checks if bullet is touching breakables, if so, deal damage
        if (isTouching(Breakables.class)){
            Breakables b = (Breakables)getOneIntersectingObject(Breakables.class);
            b.checkDeath(damage);
            dead=true;
        }
        //Remove if dead
        if (dead){
            getWorld().removeObject (this);
        }
    }
}
