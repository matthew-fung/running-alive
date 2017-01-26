import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.List;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
//popup box
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;

import java.io.IOException;
import java.util.Scanner;
/**
 * Write a description of class SurvivalGame world it populates the world with objects and reads from files to determine where they go.
 * http://stackoverflow.com/questions/19195700/how-to-read-strings-from-textfile-into-2d-array-in-java
 *
 * @author Andrew Cheung & modified solution of Maxim Shoustin at StackOverflow
 * @version (a version number or a date)
 */
public class SurvivalGame extends World
{
    private Counter scoreCounter;
    Scanner scan = new Scanner(System.in);
    /**
     * Constructor for objects of class SurvivalGame.
     *
     */
    public SurvivalGame()
    {  

        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600/** size of background*/, 600/** size of background */, 1/**movement per pixel in the grid*/);
        int object0=0;
        int gSize=40;
        int worldOffset=40;
        int worldX=25, worldY=25;
        int worldSizeX=worldOffset+worldX*gSize;//actual grid size of objects
        int worldSizeY=worldOffset+worldY*gSize;
        String [][]worldT;
        Object [][]worldO;
        List<String> lines;
        //this is the Java SE 7+
        scoreCounter = new Counter();
        addObject(scoreCounter, 500, 25);
        boolean mapSuccess=false;
        String mapFile="world.txt";
        while (!mapSuccess){
            try
            {
                lines = Files.readAllLines(Paths.get(mapFile), StandardCharsets.UTF_8);
                worldT = new String[lines.size()][];
                lines.removeAll(Arrays.asList("", null)); // <- remove empty lines 
                for(int i =0; i<lines.size(); i++)
                { 
                    worldT[i] = lines.get(i).split("[ ]+");
                }
                for (int i = 0; i < worldT.length; i++)  
                {
                    for (int j = 0; j < worldT[i].length; j++)  
                    {
                        //insert objects

                        //Mobs
                        if(worldT[i][j].equals("00ME")){addObject(new Human_BaseballBat(0,1000, false, false, false, false),j*gSize+worldOffset,i*gSize+worldOffset);}
                        if(worldT[i][j].equals("00MS")){addObject(new ZombSpawner(),j*gSize+worldOffset,i*gSize+worldOffset);}
                        //INANIMATE OBJECTS
                        if(worldT[i][j].equals("ROCK")){addObject(new Boulder(),j*gSize+worldOffset,i*gSize+worldOffset);}

                        //Walls & Corners
                        if(worldT[i][j].equals("00W9")){addObject(new CornerTR(),j*gSize+worldOffset-7,i*gSize+worldOffset+7);}
                        if(worldT[i][j].equals("W9E2")){addObject(new W9E2(),j*gSize+worldOffset-7,i*gSize+worldOffset+5);}
                        if(worldT[i][j].equals("W9E4")){addObject(new W9E4(),j*gSize+worldOffset-5,i*gSize+worldOffset+7);}

                        if(worldT[i][j].equals("00W8")){addObject(new WallU(),j*gSize+worldOffset,i*gSize+worldOffset);}
                        if(worldT[i][j].equals("W8E4")){addObject(new W8E4(),j*gSize+worldOffset+2,i*gSize+worldOffset);}
                        if(worldT[i][j].equals("W8E6")){addObject(new W8E6(),j*gSize+worldOffset-2,i*gSize+worldOffset);}

                        if(worldT[i][j].equals("00W7")){addObject(new CornerTL(),j*gSize+worldOffset+7,i*gSize+worldOffset+7);}
                        if(worldT[i][j].equals("W7E2")){addObject(new W7E2(),j*gSize+worldOffset+7,i*gSize+worldOffset+5);}
                        if(worldT[i][j].equals("W7E6")){addObject(new W7E6(),j*gSize+worldOffset+5,i*gSize+worldOffset+7);}

                        if(worldT[i][j].equals("00W6")){addObject(new WallR(),j*gSize+worldOffset,i*gSize+worldOffset);}
                        if(worldT[i][j].equals("W6E2")){addObject(new W6E2(),j*gSize+worldOffset,i*gSize+worldOffset-2);}
                        if(worldT[i][j].equals("W6E8")){addObject(new W6E8(),j*gSize+worldOffset,i*gSize+worldOffset+2);}

                        if(worldT[i][j].equals("00W4")){addObject(new WallL(),j*gSize+worldOffset,i*gSize+worldOffset);}
                        if(worldT[i][j].equals("W4E2")){addObject(new W4E2(),j*gSize+worldOffset,i*gSize+worldOffset-2);}
                        if(worldT[i][j].equals("W4E8")){addObject(new W4E8(),j*gSize+worldOffset,i*gSize+worldOffset+2);}

                        if(worldT[i][j].equals("00W2")){addObject(new WallD(),j*gSize+worldOffset,i*gSize+worldOffset);}
                        if(worldT[i][j].equals("W2E4")){addObject(new W2E4(),j*gSize+worldOffset+2,i*gSize+worldOffset);}
                        if(worldT[i][j].equals("W2E6")){addObject(new W2E6(),j*gSize+worldOffset-2,i*gSize+worldOffset);}

                        if(worldT[i][j].equals("00W3")){addObject(new CornerBR(),j*gSize+worldOffset-7,i*gSize+worldOffset-7);}
                        if(worldT[i][j].equals("W3E8")){addObject(new W3E8(),j*gSize+worldOffset-7,i*gSize+worldOffset-5);}
                        if(worldT[i][j].equals("W3E4")){addObject(new W3E4(),j*gSize+worldOffset-5,i*gSize+worldOffset-7);}

                        if(worldT[i][j].equals("00W1")){addObject(new CornerBL(),j*gSize+worldOffset+7,i*gSize+worldOffset-7);}
                        if(worldT[i][j].equals("W1E8")){addObject(new W1E8(),j*gSize+worldOffset+7,i*gSize+worldOffset-5);}
                        if(worldT[i][j].equals("W1E6")){addObject(new W1E6(),j*gSize+worldOffset+5,i*gSize+worldOffset-7);}
                        mapSuccess=true;
                    }

                }
            }
            catch (IOException e)
            {           
                mapSuccess=false;
                String previousFile=mapFile;
                //makes a popup telling the user to give a alternate file to look for and if it is canceled the program restarts.
                mapFile = JOptionPane.showInputDialog("Map Error: Failed to read map: "+previousFile+", Maybe you have a better map and you can provide a name."+
                    "\nIf not, try to restart with a suitable map in the root folder of the game.",".txt");
            }
        }

        //this is the Java Pre 7
        setPaintOrder(TitleScreen.class, Instructions.class, DeadScreen.class, Counter.class, AllBar.class,Monsters.class, Humans.class, Weapons.class, HealthKit.class,Breakables.class, Walls.class,Bullet.class, ZombSpawner.class);

        prepare();
    }

    //keeping track of score counts
    public Counter getCounter() {
        return scoreCounter;
    }

    public void act ()
    {
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
    }
}
