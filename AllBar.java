import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color; //(Allows the creation of Colours for custom colors)

/**
 * Allows the user to choose between one of two bars to display.  [1. Health Bar]   [2. Reload Bar]
 * <p>
 * (1.Displays the health of each entity, directly above their image, in the World.)
 * <p>
 * (2.Displays the reload time left when main character decides to reload their weapon)
 * 
 * @author Ken Ng
 * @version 1.0
 */
public class AllBar extends Actor {
    //Health Bar Variables
    private GreenfootImage healthBar;
    private int currHP;
    private int maxHP;
    private float percentHP;
    private int size;
    
    private Color darkRed = new Color (139, 0, 0);
    
    //Reload Bar Variables
    private GreenfootImage reloadBar;
    private int currReload;
    private int maxReload;
    private float percentReload;
    private int size2;
    
    private Color BGGray = new Color (230, 230, 230);
    private Color frontGray = new Color (132, 132, 132);
    
    //Check Bar
    private boolean HPBar = false;
    private boolean RBar = false;
    
    //Other Variables
    private final int BAR_LENGTH = 30;
    private final int BAR_HEIGHT = 3;
    private GreenfootImage blank = new GreenfootImage(BAR_LENGTH,BAR_HEIGHT);
    private Actor target;
    
    //Transparency variables
    private int transCounter;
    private boolean hitOrNot = false;

    /**
     * Creates a HealthBar with a given HP value
     * <p>
     * OR
     * <p>
     * Creates a ReloadBar with the given value
     * 
     * @param value Value of the entity that needs a HealthBar or Value of the Reload Bar
     * @param barType Chooses between one of two types of bars  [1. Health Bar]   [2. Reload Bar]
     */
    public AllBar (int value, int barType) {
        //Chooses between Health or Reload Bar and sets values accordingly
        if (barType == 1) {
            currHP = value;
            maxHP = value;
            HPBar = true;
        } else if (barType == 2) {
            currReload = value;
            maxReload = value;
            RBar = true;
        }
        drawBar();
    }

    /**
     * Creates a HealthBar for an Actor with given HP value
     * <p>
     * OR
     * <p>
     * Creates a ReloadBar for an Actor(main character only) with the given value
     * 
     * @param value Value of the entity that needs a HealthBar or Value of the Reload Bar
     * @param theTarget The Actor's identification (name)
     * @param barType Chooses between one of two types of bars  1. Health Bar    2. Reload Bar
     */
    public AllBar (int value, Actor theTarget, int barType) {
        //Chooses between Health or Reload Bar and sets values accordingly
        if (barType == 1) {
            currHP = value;
            maxHP = value;
            HPBar = true;
        } else if (barType == 2) {
            currReload = value;
            maxReload = value;
            RBar = true;
        }
        this.target = theTarget;
        drawBar();
    }

    /**
     * Creates a HealthBar for an Actor with given HP value (Both current and total health)
     * <p>
     * OR
     * <p>
     * Creates a ReloadBar for an Actor(main character only) with the given current and total value
     * 
     * @param curr  Current Value as opposed to Total value, used mainly for health bars
     * @param value Value of the entity that needs a HealthBar or Value of the Reload Bar
     * @param theTarget The Actor's identification (name)
     * @param barType Chooses between one of two types of bars  1. Health Bar    2. Reload Bar
     */
    public AllBar (int currValue,int value, Actor theTarget, int barType) {
        //Chooses between Health or Reload Bar and sets values accordingly
        if (barType == 1) {
            currHP = currValue;
            maxHP = value;
            HPBar = true;
        } else if (barType == 2) {
            currReload = value;
            maxReload = value;
            RBar = true;
        }
        this.target = theTarget;
        drawBar();
    }

    /**
     * Act - This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * <p>
     * Checks if the current health is within boundaries and acts accordingly
     * Once the owner of the health bar is gone it will disappear as well
     * <p>
     * Also will make the health bar slowly fade away when it is unaltered for awhile
     * by using counters and changing transparency
     */
    public void act() {
        //Limiter to not allow health bar go below or above limits
        if (currHP > maxHP) {
            currHP = maxHP;
        } else if (currHP < 0) {
            currHP = 0;
        }
        
        //Sets location, and if character is null, removes
        if (target.getWorld() != null) {
            if (HPBar) setLocation (target.getX(), target.getY() - 26);
            else if (RBar) setLocation (target.getX(), target.getY() - 22);
        } else {
            getWorld().removeObject(this);
        }
        
        //When character is deemed hit, resets transparency and counter
        if (hitOrNot) {
            transCounter = 350;
            healthBar.setTransparency(245);
            hitOrNot = false;
        }
        
        //continuation of the above if statement, slowly fades the bar away
        if (transCounter >= 0){
            transCounter--;
            if (healthBar.getTransparency() >= 15){
                healthBar.setTransparency (healthBar.getTransparency() - 1);
            } else {
                healthBar.setTransparency (0);
            }
        }
    }

    /**
     * Updates the visuals of HealthBar with the newest health value.
     * 
     * @param newCurrHP     New current HP (health) value, after the entity gets damaged.
     */
    public void updateHP (int newCurrHP) {
        if (newCurrHP != currHP){
            currHP = newCurrHP;
            percentHP = ((float)currHP/(float) maxHP);//calculations
            
            //draws the background of the bar
            healthBar.clear();
            healthBar.setColor(darkRed);
            healthBar.fillRect(0,0,BAR_LENGTH,BAR_HEIGHT);
            
            size = (int) (percentHP * (BAR_LENGTH - 2)); // calculates size of health bar
            
            //based on the amount of health, color of bar changes
            if (percentHP > 0.62) {
                healthBar.setColor(Color.GREEN);
                healthBar.fillRect(1,1,size,BAR_HEIGHT/3);
            } else if (percentHP > 0.29) {
                healthBar.setColor(Color.ORANGE);
                healthBar.fillRect(1,1,size,BAR_HEIGHT/3);
            } else {
                healthBar.setColor(Color.RED);
                healthBar.fillRect(1,1,size,BAR_HEIGHT/3);
            }

            this.setImage (healthBar);
        } else if (newCurrHP == maxHP && healthBar.getTransparency () <= 30) {
            this.setImage (blank);//when full health, fades away
        }
    }
    
    /**
     * Checks if the entity was hit
     */
    public void hitGuy (boolean hit) {
        hitOrNot = hit;
    }
    
    /**
     * Updates the visuals of ReloadBar with the newest reload value.
     * 
     * @param newCurrReload     New current Reload value, after the main character reloads.
     */
    public void updateReload (int newCurrReload) {
        if (newCurrReload != currReload){
            currReload = newCurrReload;
            percentReload = ((float)currReload/(float) maxReload);//calculations
            
            //Draws background behind the bar
            reloadBar.clear();
            reloadBar.setColor(BGGray);
            reloadBar.fillRect(0,0,BAR_LENGTH,BAR_HEIGHT);
            
            //Draws the animated front bar based on the values given for reloading
            size2 = (int) (percentReload * BAR_LENGTH);//calculates size of reload bar
            reloadBar.setColor(frontGray);
            reloadBar.fillRect(0,0,size2,BAR_HEIGHT);
            
            this.setImage (reloadBar);
        } else if (newCurrReload <= 0) {
            this.setImage (blank); // blank when reload is complete
        }
    }

    /**
     * Draws a HealthBar with its given Length and Height
     * <p>
     * OR
     * <p>
     * Draws a ReloadBar with its given Length and Height
     */
    private void drawBar() {
        if (HPBar) {
            healthBar = new GreenfootImage (BAR_LENGTH, BAR_HEIGHT);
            this.setImage(healthBar);
        } else if (RBar) {
            reloadBar = new GreenfootImage (BAR_LENGTH, BAR_HEIGHT);
            this.setImage(reloadBar);
        }
    }
}
