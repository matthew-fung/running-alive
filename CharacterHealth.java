import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * 
 * 
 * @author Ken Ng
 * @version 1.0
 */
public class CharacterHealth extends Actor{
    //Image declarations to store pictures
    private GreenfootImage allBar = new GreenfootImage (300, 70);
    private GreenfootImage blinkBar = new GreenfootImage(300, 70);
    private GreenfootImage characterName;
    private GreenfootImage hpStatDisplay;
    
    //Blink variable to determine whether to blink or not
    private boolean blink = false;
    
    //Health variables
    private int currentHP;
    private int totalHP;
    private double percentHP;
    
    //Actor and Character name
    private String name;
    private Actor target;
    
    //Color Customization
    private Color darkRed = new Color (139,0,0);
    
    /**
     * Creates a customized one-of-a-kind health bar for the main character
     * 
     * @param initialHP Current Health of out Maximum Value
     * @param initialMaxHP Maximum set value for health
     * @param charName Name of character
     */
    public CharacterHealth(int initialHP, int initialMaxHP, String charName) {
        currentHP = initialHP;
        totalHP = initialMaxHP;
        name = charName;
        drawNewHP();
    }
    
    /**
     * Creates a customized one-of-a-kind health bar for the main character
     * 
     * @param initialMaxHP Maximum set value for health
     * @param charName Name of character
     */
    public CharacterHealth (int initialMaxHP, String charName){
        this(initialMaxHP, initialMaxHP, charName);
    }
    
    /**
     * Act - This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * <p>
     * Checks whether the health is low enough for the blink effect to start
     */
    public void act(){
        if (((float)currentHP/(float)totalHP) < 0.35){
            blink = true;
        } else {
            blink = false;
        }
        
        drawNewHP();
    }
    
    /**
     * Updates Health Value
     * Checks if the current health is within boundaries and acts accordingly
     * 
     * @param updateHP Health bar points is identical to the entity's health
     */
    public void update(int updateHP){
        currentHP = updateHP;
        
        if (currentHP > totalHP) {
            currentHP = totalHP;
        } else if (currentHP < 0) {
            currentHP = 0;
        }
        
        drawNewHP();
    }
    
    /**
     * Draws and updates the character's health bar art according to the new values
     */
    private void drawNewHP () {
        if (currentHP == totalHP){
            allBar.clear();
            drawBackground();
            
            allBar.setColor(Color.GREEN);
            allBar.fillRect(80,30,210,35);
            
            
            allBar.drawImage (hpStatDisplay, 138, 35);
            
            this.setImage (allBar);
        } else if (currentHP != totalHP){
            allBar.clear();
            drawBackground();
            
            int hpScale = Math.round ( (float)currentHP/(float) totalHP * 340);
            
            allBar.setColor(Color.GREEN);
            allBar.fillRect(80,30,hpScale,35);
            
            if (hpScale < 105) {
                allBar.setColor(Color.ORANGE);
                allBar.fillRect(80,30,hpScale,35);
            }
            
            if (hpScale < 32){
                allBar.setColor(Color.RED);
                allBar.fillRect(80,30,hpScale,35);
            }
            
            allBar.drawImage (hpStatDisplay, 138, 35);
            
            blinkBar.clear();
            if (blink){
                blinkBar.fillRect (80,30,hpScale,35);
                int t = blinkBar.getTransparency();
                t -= 5;
                if (t < 0){
                    t = 255;
                }
                blinkBar.setTransparency(t);
                allBar.drawImage (blinkBar, 0,0);
            }
            
            this.setImage (allBar);
        }
    }
    
    /**
     * Draws the background and texts for the character's health bar
     */
    private void drawBackground(){
        allBar.setColor(Color.BLACK);
        allBar.fillRect(40,25,260,70);
        allBar.fillOval(0,0,70,70);
        allBar.setColor(Color.WHITE);
        allBar.fillOval(3,3,67,67);
        allBar.setColor(darkRed);
        allBar.fillRect(80,30,210,35);
        
        blinkBar.setColor (Color.WHITE);
        
        characterName = new GreenfootImage(name , 28, Color.BLACK, new Color(0,0,0,0));
        hpStatDisplay = new GreenfootImage(currentHP + " / " + totalHP , 26, Color.RED, new Color(0,0,0,0));
       
        allBar.drawImage(characterName, 78, 0);
    }
}
