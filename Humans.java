import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Human is the protagonist of RunningAlive, and fights the Monsters of the world.
 * 
 * @author MACKX 
 * @version Final
 */
public class Humans extends Mobs
{
    //Various variables regarding movement and shooting method.
    protected int speed;
    protected int diag;
    protected int fireRate;
    protected int ammo;
    protected int reload;
    protected int reloadSpeed;
    protected boolean shotReady = true;

    //These variables are to check if the player has found this weapon or not, allowing them to select the weapon.
    protected boolean pistolGet;
    protected boolean spas12Get;
    protected boolean ak47Get;
    protected boolean m60Get;

    //added by ken for the bullet
    private SurvivalGame w;
    private int rotation=90;
    private int timer;
    protected int wid;
    //int rot=getRotation();
    private int PistolDamage = 400;
    private int SPAS12Damage = 300;
    private int AK47Damage = 350;
    private int M60Damage = 500;
    private int BaseballBatDamage = 200;

    protected void addedToWorld(World world){
        this.w = (SurvivalGame) world;
    }

    /**
     * Allows the player to move by pressing down arrow keys.
     */
    protected void movement() 
    {
        //Move in the direction selected.
        if (Greenfoot.isKeyDown ("up")){
            rotation = 270;
        } else if (Greenfoot.isKeyDown ("down")){
            rotation = 90;
        } else if (Greenfoot.isKeyDown ("left")){
            rotation = 180;
        } else if (Greenfoot.isKeyDown ("right")){
            rotation = 0;
        }

        if (Greenfoot.isKeyDown ("up") && !Greenfoot.isKeyDown ("down") && !Greenfoot.isKeyDown ("left") && !Greenfoot.isKeyDown ("right")){
            diag = 0;
        } else if (!Greenfoot.isKeyDown ("up") && Greenfoot.isKeyDown ("down") && !Greenfoot.isKeyDown ("left") && !Greenfoot.isKeyDown ("right")){
            diag = 0;
        } else if (!Greenfoot.isKeyDown ("up") && !Greenfoot.isKeyDown ("down") && Greenfoot.isKeyDown ("left") && !Greenfoot.isKeyDown ("right")){
            diag = 0;
        } else if (!Greenfoot.isKeyDown ("up") && !Greenfoot.isKeyDown ("down") && !Greenfoot.isKeyDown ("left") && Greenfoot.isKeyDown ("right")){
            diag = 0;
        }

        //Timer to delay the speed of bullet shooting
        timer ++;

        //checks for user-input, if pressed, will shoot
        //A timer and various other stats variables create a reloading and firerate function of the gun
        if (Greenfoot.isKeyDown ("space")){
            if (timer >= fireRate && shotReady == true && wid != 3 && wid != 1) {
                if(wid == 2){
                    w.addObject(new Bullet(rotation + diag,PistolDamage),getX(),getY());
                    timer = 0;
                    reload++;
                    Greenfoot.playSound("Pistol.wav");
                }
                if(wid == 4){
                    w.addObject(new Bullet(rotation + diag,AK47Damage),getX(),getY());
                    timer = 0;
                    reload++;
                    Greenfoot.playSound("AK47.wav");
                }
                if(wid == 5){
                    w.addObject(new Bullet(rotation + diag,M60Damage),getX(),getY());
                    timer = 0;
                    reload++;
                    Greenfoot.playSound("M60.wav");
                }
            } else if (timer >= fireRate && wid == 3 && shotReady == true) {
                w.addObject(new Bullet(rotation + diag,SPAS12Damage),getX(),getY());
                timer = 0;
                reload++;
                Greenfoot.playSound("SPAS12.wav");
            } else if (timer >= fireRate && wid == 1) {
                setImage("Human_BaseballBat2.png");
                w.addObject(new BaseballBatBullet(rotation + diag, BaseballBatDamage, 255),getX(),getY());
                Greenfoot.playSound("BaseballBat.wav");
                timer = 0;
            }
        }
        //Reloads if a certain amount of ammo is fired depending on the weapon
        if (reload >= ammo && wid == 2) {
            shotReady = false;
            reloadSpeed++;
            if (reloadSpeed == 80) {
                reload = 0;
                reloadSpeed = 0;
                shotReady = true;
            }
        } else if (reload >= ammo && wid == 3){
            shotReady = false;
            reloadSpeed++;
            if (reloadSpeed == 150) {
                reload = 0;
                reloadSpeed = 0;
                shotReady = true;
            }
        } else if (reload >= ammo && wid == 4){
            shotReady = false;
            reloadSpeed++;
            if (reloadSpeed == 125) {
                reload = 0;
                reloadSpeed = 0;
                shotReady = true;
            }
        }
        else if (reload >= ammo && wid == 5){
            shotReady = false;
            reloadSpeed++;
            if (reloadSpeed == 250) {
                reload = 0;
                reloadSpeed = 0;
                shotReady = true;
            }
        }
        //Movement and interactions with walls
        if(Greenfoot.isKeyDown("up"))
        {   
            diag=0;
            if(Greenfoot.isKeyDown("left"))
            {
                setLocation(getX()-speed,getY());
                if (!getIntersectingObjects(WallL.class).isEmpty() 
                || !getIntersectingObjects(WallR.class).isEmpty()
                || !getIntersectingObjects(CornerTR.class).isEmpty()
                || !getIntersectingObjects(CornerBR.class).isEmpty()
                || !getIntersectingObjects(CornerTL.class).isEmpty()
                || !getIntersectingObjects(CornerBL.class).isEmpty()
                || !getIntersectingObjects(W1E6.class).isEmpty()
                || !getIntersectingObjects(W7E6.class).isEmpty()
                || !getIntersectingObjects(W8E6.class).isEmpty()
                || !getIntersectingObjects(W2E6.class).isEmpty()                

                ){
                    setLocation (getX()+speed,getY());                      
                }
                diag=-45;
            }
            if(Greenfoot.isKeyDown("right"))
            {
                setLocation(getX()+speed,getY());
                if (!getIntersectingObjects(WallL.class).isEmpty() 
                || !getIntersectingObjects(WallR.class).isEmpty()
                || !getIntersectingObjects(CornerTL.class).isEmpty()
                || !getIntersectingObjects(CornerBL.class).isEmpty()
                || !getIntersectingObjects(CornerTR.class).isEmpty()
                || !getIntersectingObjects(CornerBR.class).isEmpty()
                || !getIntersectingObjects(W3E4.class).isEmpty()
                || !getIntersectingObjects(W8E4.class).isEmpty()
                || !getIntersectingObjects(W2E4.class).isEmpty()  
                || !getIntersectingObjects(W9E4.class).isEmpty()
                ){
                    setLocation (getX()-speed,getY());              
                }
                diag=45;
            }

            setLocation (getX(),getY()-speed);
            if (!getIntersectingObjects(WallU.class).isEmpty() 
            ||!getIntersectingObjects(WallD.class).isEmpty() 
            || !getIntersectingObjects(CornerBL.class).isEmpty()
            || !getIntersectingObjects(CornerBR.class).isEmpty()
            || !getIntersectingObjects(W4E2.class).isEmpty()
            || !getIntersectingObjects(W6E2.class).isEmpty()
            ){
                setLocation (getX(),getY()+speed);
            }

            setRotation(180+diag);  
        }
        else if(Greenfoot.isKeyDown("down"))
        {
            diag=0;
            if(Greenfoot.isKeyDown("left"))
            {
                setLocation (getX()-speed,getY());
                if (!getIntersectingObjects(WallL.class).isEmpty()
                || !getIntersectingObjects(WallR.class).isEmpty() 
                || !getIntersectingObjects(CornerTR.class).isEmpty()
                || !getIntersectingObjects(CornerBR.class).isEmpty()
                || !getIntersectingObjects(W7E6.class).isEmpty()
                || !getIntersectingObjects(W1E6.class).isEmpty()
                || !getIntersectingObjects(W2E6.class).isEmpty()                
                || !getIntersectingObjects(W8E6.class).isEmpty()
                ){
                    setLocation (getX()+speed,getY());                      
                }
                diag=45;
            }
            if(Greenfoot.isKeyDown("right"))
            {
                setLocation (getX()+speed,getY());
                if (!getIntersectingObjects(WallL.class).isEmpty() 
                || !getIntersectingObjects(WallR.class).isEmpty()
                || !getIntersectingObjects(WallU.class).isEmpty()
                || !getIntersectingObjects(CornerTL.class).isEmpty()
                || !getIntersectingObjects(CornerBL.class).isEmpty()
                || !getIntersectingObjects(W3E4.class).isEmpty()
                || !getIntersectingObjects(W9E4.class).isEmpty()
                || !getIntersectingObjects(W2E4.class).isEmpty()
                || !getIntersectingObjects(W8E4.class).isEmpty()){
                    setLocation (getX()-speed,getY());                      
                }
                diag=-45;
            }            
            setLocation (getX(),getY()+speed);
            if (!getIntersectingObjects(WallU.class).isEmpty() 
            || !getIntersectingObjects(WallD.class).isEmpty() 
            || !getIntersectingObjects(WallR.class).isEmpty()
            || !getIntersectingObjects(CornerTL.class).isEmpty()
            || !getIntersectingObjects(CornerTR.class).isEmpty()
            || !getIntersectingObjects(W4E8.class).isEmpty()
            || !getIntersectingObjects(W6E8.class).isEmpty()
            ){
                setLocation (getX(),getY()-speed);
            }
            setRotation(0+diag);
        }
        else if(Greenfoot.isKeyDown("left"))
        {
            setLocation (getX()-speed,getY());
            if (!getIntersectingObjects(Walls.class).isEmpty()){
                setLocation (getX()+speed,getY());                      
            }
            setRotation(90);
        }
        else if(Greenfoot.isKeyDown("right"))
        {
            setLocation (getX()+speed,getY());
            if (!getIntersectingObjects(Walls.class).isEmpty()){
                setLocation (getX()-speed,getY());                      
            }
            setRotation(270);
        }

        /**
         * CHANGING WEAPONS
         */
        int rot=getRotation();
        //If a number is pressed, the player with the corresponding weapon will be placed at location, then the previous removed.
        if (Greenfoot.isKeyDown ("1")){
            wid = 1;
            w.addObject(new Human_BaseballBat(rot + diag, hp, pistolGet, ak47Get, spas12Get, m60Get),getX(),getY());
            reload = 0;
            getWorld().removeObject (this);
        }
        else if (Greenfoot.isKeyDown ("2") && pistolGet == true){
            wid = 2;
            w.addObject(new Human_Pistol(rot + diag, hp, pistolGet, ak47Get, spas12Get, m60Get),getX(),getY());
            reload = 0;
            getWorld().removeObject (this);
        }
        else if (Greenfoot.isKeyDown ("3") && spas12Get == true){
            wid = 3;
            w.addObject(new Human_Spas12(rot + diag, hp, pistolGet, ak47Get, spas12Get, m60Get),getX(),getY());
            reload = 0;
            getWorld().removeObject (this);
        }
        else if (Greenfoot.isKeyDown ("4") && ak47Get == true){
            wid = 4;
            w.addObject(new Human_AK47(rot + diag, hp, pistolGet, ak47Get, spas12Get, m60Get),getX(),getY());
            reload = 0;
            getWorld().removeObject (this);
        }
        else if (Greenfoot.isKeyDown ("5") && m60Get == true){
            wid = 5;
            w.addObject(new Human_M60(rot + diag, hp, pistolGet, ak47Get, spas12Get, m60Get),getX(),getY());
            reload = 0;
            getWorld().removeObject (this);
        }

    }    

    /**
     * This methods detects which drop the player has encountered and changes the variable regarding it accordingly.
     * Includes both weapon related and health related drops.
     */
    protected void dropDetection() {
        if (isTouching(Pistol.class)) {
            pistolGet = true;
        }
        if (isTouching(SPAS12.class)) {
            spas12Get = true;
        }
        if (isTouching(AK47.class)) {
            ak47Get = true;
        }
        if (isTouching(M60.class)) {
            m60Get = true;
        }
        if (!getObjectsAtOffset(0,0,HealthKit.class).isEmpty()) {
            hp += 25;
        }
    }
}
