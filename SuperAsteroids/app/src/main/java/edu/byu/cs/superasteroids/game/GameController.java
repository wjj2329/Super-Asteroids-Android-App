package edu.byu.cs.superasteroids.game;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import edu.byu.cs.superasteroids.base.IGameDelegate;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.model_classes.CompleteAsteroids;
import edu.byu.cs.superasteroids.model_classes.CompleteBackgroundObjects;
import edu.byu.cs.superasteroids.model_classes.Projectile;
import edu.byu.cs.superasteroids.model_classes.Ship;
import edu.byu.cs.superasteroids.model_classes.SuperAsteroids;
import edu.byu.cs.superasteroids.model_classes.ViewPort;

/**
 * Created by williamjones on 2/24/16.
 */
public class GameController implements IGameDelegate
{
    //for safe zone state
    private boolean transparencychange=true;
    //for safe zone at the beginning of the level
    private double initialtime=0;
    private  boolean safezone=true;
    //also for safezone at the beginning of the level
    private boolean shipstart=true;
    //lists of all my level objects for the level
    private ArrayList<Integer>asteroidobjectids;
    private ArrayList<Integer>backgroundobjectsids;
    private ArrayList<CompleteAsteroids>asteroidsforlevel;
    private ArrayList<CompleteBackgroundObjects>backgroundsforlevel;
    private ArrayList<Projectile>myshots;
    private int backgroundimageid;
    private ContentManager content;
    //some variables that need to be global to cross between the functions I call. 
    private int background_height=-1;
    private int background_width=-1;
    private int projectileid=-1;
    private boolean firedshot;
    private int sound=-1;
    private int count=0;
    private int collisionsound=-1;
    private boolean gameover=false;

    private void octeroidcreation(CompleteAsteroids deadasteroid)//creates octeroid children when parent octeroid asteroid dies
    {
        for(int i=0; i<8; i++)
        {
            String name = deadasteroid.getName();
            String image = deadasteroid.getImage();
            int imagewidth = deadasteroid.getImagewidth();
            int imageheight = deadasteroid.getImageheight();
            String type = deadasteroid.getType();
            int number = deadasteroid.getNumber();
            int asteroidid = deadasteroid.getAsteroidid();
            CompleteAsteroids mywholeasteroid = new CompleteAsteroids(name, image, imagewidth, imageheight, type, number, asteroidid);
            mywholeasteroid.setParent(false);
            mywholeasteroid.setScale(.25f);
            Random rand= new Random();
            int addw=rand.nextInt(50)-50;
            mywholeasteroid.setWidth(asteroidsforlevel.get(i).getWidth()+addw);
            int addh=rand.nextInt(50)-50;
            mywholeasteroid.setHeight(asteroidsforlevel.get(i).getHeight()+addh);
            float left = mywholeasteroid.getWidth() - mywholeasteroid.getImagewidth()*mywholeasteroid.getScale();
            float top = mywholeasteroid.getHeight() - mywholeasteroid.getImageheight()*mywholeasteroid.getScale();
            float right = mywholeasteroid.getWidth() + mywholeasteroid.getImagewidth()*mywholeasteroid.getScale();
            float bot = mywholeasteroid.getHeight() + mywholeasteroid.getImageheight()*mywholeasteroid.getScale();
            RectF myrect = new RectF(left, top, right, bot);
            mywholeasteroid.setRectangle(myrect);
            mywholeasteroid.generatespeed();
            asteroidsforlevel.add(mywholeasteroid);
            System.out.println(mywholeasteroid.getAsteroidid());
            asteroidobjectids.add(content.loadImage(deadasteroid.getImage()));
        }
    }

    private void growingcreation(CompleteAsteroids deadasteroid)//creates growing asteroid children on death of parent  growing asteroid
    {
        for(int i=0; i<2; i++)
        {
            String name = deadasteroid.getName();
            String image = deadasteroid.getImage();
            int imagewidth = deadasteroid.getImagewidth();
            int imageheight = deadasteroid.getImageheight();
            String type = deadasteroid.getType();
            int number = deadasteroid.getNumber();
            int asteroidid = deadasteroid.getAsteroidid();
            CompleteAsteroids mywholeasteroid = new CompleteAsteroids(name, image, imagewidth, imageheight, type, number, asteroidid);
            mywholeasteroid.setParent(false);
            Random rand= new Random();
            int addw=rand.nextInt(50)-50;
            mywholeasteroid.setWidth(deadasteroid.getWidth()+addw);
            int addh=rand.nextInt(50)-50;
            mywholeasteroid.setHeight(deadasteroid.getHeight()+addh);
            mywholeasteroid.setScale(0.5f);
            mywholeasteroid.generatespeed();
            float left = mywholeasteroid.getWidth() - mywholeasteroid.getImagewidth()*mywholeasteroid.getScale();
            float top = mywholeasteroid.getHeight() - mywholeasteroid.getImageheight()*mywholeasteroid.getScale();
            float right = mywholeasteroid.getWidth() + mywholeasteroid.getImagewidth()*mywholeasteroid.getScale();
            float bot = mywholeasteroid.getHeight() + mywholeasteroid.getImageheight()*mywholeasteroid.getScale();
            RectF myrect = new RectF(left, top, right, bot);
            mywholeasteroid.setRectangle(myrect);
            asteroidsforlevel.add(mywholeasteroid);
            asteroidobjectids.add(content.loadImage(deadasteroid.getImage()));
        }
    }

    private void regularcreation(CompleteAsteroids deadasteroid)//creates regular asteroid children on death of parent  regular asteroid
    {
        for(int i=0; i<2; i++)
        {
            String name = deadasteroid.getName();
            String image = deadasteroid.getImage();
            int imagewidth = deadasteroid.getImagewidth();
            int imageheight = deadasteroid.getImageheight();
            String type = deadasteroid.getType();
            int number = deadasteroid.getNumber();
            int asteroidid = deadasteroid.getAsteroidid();
            CompleteAsteroids mywholeasteroid = new CompleteAsteroids(name, image, imagewidth, imageheight, type, number, asteroidid);
            mywholeasteroid.setParent( false);
            mywholeasteroid.setScale(.5f);
            Random rand= new Random();
            int addw=rand.nextInt(50)-50;
            mywholeasteroid.setWidth(deadasteroid.getWidth() + addw);
            int addh=rand.nextInt(50)-50;
            mywholeasteroid.setHeight(deadasteroid.getHeight() + addh);
            mywholeasteroid.generatespeed();
            float left = mywholeasteroid.getWidth() - mywholeasteroid.getImagewidth()*mywholeasteroid.getScale();
            float top = mywholeasteroid.getHeight() - mywholeasteroid.getImageheight()*mywholeasteroid.getScale();
            float right = mywholeasteroid.getWidth() + mywholeasteroid.getImagewidth()*mywholeasteroid.getScale();
            float bot = mywholeasteroid.getHeight() + mywholeasteroid.getImageheight()*mywholeasteroid.getScale();
            RectF myrect = new RectF(left, top, right, bot);
            mywholeasteroid.setRectangle(myrect);
            asteroidsforlevel.add(mywholeasteroid);
            asteroidobjectids.add(content.loadImage(deadasteroid.getImage()));
        }
    }

    //function to check for asteroid destruction
    private void  asteroiddeath(CompleteAsteroids deadasteroid, int index)
    {
        if(deadasteroid.getHealth()<=0)
        {
            asteroidobjectids.remove(index);//get rid of id of asteroid
            if(deadasteroid.getParent())
            {
                if(deadasteroid.getType().equals("octeroid"))
                {
                    octeroidcreation(deadasteroid);
                }
                if(deadasteroid.getType().equals("growing"))
                {
                   growingcreation(deadasteroid);
                }
                if(deadasteroid.getType().equals("regular"))
                {
                    regularcreation(deadasteroid);
                }
            }
            asteroidsforlevel.remove(index);//get rid of asteroid
        }
    }

   private  void projectileandasteroidcollidecheck(int i)//checks for asteroid and projectile collision
    {
        for (int j = 0; j < myshots.size(); j++)
        {
            if(j==myshots.size())//stops from crash when removing the last shot
            {
                break;
            }
            PointF mypoint = new PointF(myshots.get(j).getx(), myshots.get(j).gety());
            if (asteroidsforlevel.get(i).getRectangle().contains(mypoint.x + ViewPort.Viewportheight, mypoint.y + ViewPort.Viewportwidth))//compare rects
            {
                try
                {
                    collisionsound = content.loadSound("sounds/impact.wav");//play collide noise
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                content.playSound(collisionsound, 1, 1);
                asteroidsforlevel.get(i).setHealth( asteroidsforlevel.get(i).getHealth()- Ship.myship.getCannon().getDamage());
                asteroiddeath(asteroidsforlevel.get(i), i);
                myshots.remove(myshots.get(j));
                if (i == asteroidsforlevel.size())//stops crash when removing asteroid
                {
                    break;
                }
            }
        }
    }

   private void shipandasteriodbehavioreffects(int i, double elapsedTime)//decides how to control the ship when hit with asteroid and safezone calculations
    {
        asteroidsforlevel.get(i).setTimesincehit(asteroidsforlevel.get(i).getTimesincehit()+elapsedTime);
        Ship.myship.generatemyrect();
        RectF test = Ship.myship.getMyrect();
        if (test.intersect(asteroidsforlevel.get(i).getRectangle())&&!safezone)//check for crash with asteroid and ship
        {
            Ship.myship.setHealthpoints(Ship.myship.getHealthpoints()-5);
            initialtime=0;
            asteroidsforlevel.get(i).crash();
            safezone=true;
            asteroidsforlevel.get(i).setAlreadyhit(true);
        }
        if(test.intersect(asteroidsforlevel.get(i).getRectangle())&&!asteroidsforlevel.get(i).getAlreadyhit())//not to keep hurting ship when hit
        {

            asteroidsforlevel.get(i).crash();
            asteroidsforlevel.get(i).setAlreadyhit(true);
        }
        if(asteroidsforlevel.get(i).getTimesincehit()>5)//so it can get hit again and not be invincible.
        {
            asteroidsforlevel.get(i).setAlreadyhit(false);
            asteroidsforlevel.get(i).setTimesincehit(0.0);
        }
    }

   private void updateasteroids(double elapsedTime)
    {
        for (int i = 0; i < asteroidsforlevel.size(); i++)
        {
            //update the asteroids location on screen
            asteroidsforlevel.get(i).setWidth(asteroidsforlevel.get(i).getWidth() + asteroidsforlevel.get(i).getSpeedforwidth());
            asteroidsforlevel.get(i).setHeight(asteroidsforlevel.get(i).getHeight() + asteroidsforlevel.get(i).getSpeedforheight());
            //Specifically update the growing asteroid scale
            if (asteroidsforlevel.get(i).getType().equals("growing"))
            {
                asteroidsforlevel.get(i).updatescale();
            }
            asteroidsforlevel.get(i).outofboundscheck(background_height, background_width); //check for out of bounds
            projectileandasteroidcollidecheck(i);  //check collision
            if(asteroidsforlevel.size()==i)//stop from crash
            {
                break;
            }
            if (asteroidsforlevel.size() != 0)
            {
                shipandasteriodbehavioreffects(i, elapsedTime);
            }
        }
    }

    private void updatedrawingcorrdinates(double elapsedTime)//calculate the new ViewPort coordinates.
    {
        if (InputManager.movePoint != null)
        {
            float radian = (float) Math.atan2(((InputManager.movePoint.y - Ship.myship.getDrawingcordinateheight())), (InputManager.movePoint.x - Ship.myship.getDrawingcordinatelength()));
            radian += GraphicsUtils.HALF_PI;
            float degree = (float) GraphicsUtils.radiansToDegrees(radian);
            Ship.myship.updaterotationdegree(degree);
            PointF mypointf = new PointF(Ship.myship.getDrawingcordinatelength(), Ship.myship.getDrawingcordinateheight());
            RectF myrect = Ship.myship.getMyrect();
            GraphicsUtils.MoveObjectResult object = GraphicsUtils.moveObject(mypointf, myrect, Ship.myship.getEngine().getBaseSpeed() * 2, Math.toRadians(degree - 90), elapsedTime);
            int x = (int) object.getNewObjPosition().x;
            int y = (int) object.getNewObjPosition().y;
            Ship.myship.setMyrect(object.getNewObjBounds());
            ViewPort.updateheightandwidth(x - Ship.myship.getDrawingcordinatelength() + ViewPort.x, y - Ship.myship.getDrawingcordinateheight() + ViewPort.y);
        }
    }

    private void createprojeectilewhenfired()
    {
        firedshot = InputManager.firePressed;
        if (firedshot)
        {
            try
            {
                sound = content.loadSound(Ship.myship.getCannon().getAttacksound());
                if (InputManager.movePoint != null)
                {
                    float x = InputManager.movePoint.x;
                    float y = InputManager.movePoint.y;
                    Projectile shot = new Projectile(projectileid, Ship.myship.getRotation());
                    myshots.add(shot);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            content.playSound(sound, 1, 1);
        }
    }

    @Override
    public void update(double elapsedTime)
    {
        initialtime+=elapsedTime;
        //control the safezonetime
        if(initialtime>5&&safezone)
        {
            safezone=false;
        }
        //end game if ship dies
        if(Ship.myship.getHealthpoints()<=0)
        {
            gameover=true;
        }
        if(!gameover)
        {
            //start the next level
            if (asteroidsforlevel.isEmpty())
            {
                this.loadContent(content);
            }
            //keep moving the shots across the screen
            for (int i = 0; i < myshots.size(); i++)
            {
                myshots.get(i).update();
            }
            //go through all the asteroids
            updateasteroids(elapsedTime);
            //update coordinates of viewport
            updatedrawingcorrdinates(elapsedTime);
            //create projectiles if needed
            createprojeectilewhenfired();

        }
    }

    private void loadasteroidsforlevel()//loads from level asteriods and level info to combine them to create COMPLETE ASTERIODS!
    {
        for (int i = 0; i < SuperAsteroids.singleton.getMylevelasteroids().size(); i++)
        {
            if (SuperAsteroids.singleton.getMylevelasteroids().get(i).getLevelid() == SuperAsteroids.singleton.currentlevel())
            {
                for (int j = 0; j < SuperAsteroids.singleton.getMylevelasteroids().get(i).getNumber(); j++)
                {
                    String name = SuperAsteroids.singleton.getMyasteroids().get(SuperAsteroids.singleton.getMylevelasteroids().get(i).getasteroidid() - 1).getName();
                    String image = SuperAsteroids.singleton.getMyasteroids().get(SuperAsteroids.singleton.getMylevelasteroids().get(i).getasteroidid() - 1).getImagepic();
                    int imagewidth = SuperAsteroids.singleton.getMyasteroids().get(SuperAsteroids.singleton.getMylevelasteroids().get(i).getasteroidid() - 1).getImageWidth();
                    int imageheight = SuperAsteroids.singleton.getMyasteroids().get(SuperAsteroids.singleton.getMylevelasteroids().get(i).getasteroidid() - 1).getImageHeight();
                    String type = SuperAsteroids.singleton.getMyasteroids().get(SuperAsteroids.singleton.getMylevelasteroids().get(i).getasteroidid() - 1).getType();
                    int number = SuperAsteroids.singleton.getMylevelasteroids().get(i).getNumber();
                    int asteroidid = SuperAsteroids.singleton.getMylevelasteroids().get(i).getasteroidid();
                    CompleteAsteroids mywholeasteroid = new CompleteAsteroids(name, image, imagewidth, imageheight, type, number, asteroidid);
                    mywholeasteroid.generateheight();
                    mywholeasteroid.generatewidth();
                    mywholeasteroid.generaterotate();
                    mywholeasteroid.generatespeed();
                    mywholeasteroid.setParent( true);
                    float left = mywholeasteroid.getWidth() - mywholeasteroid.getImagewidth();
                    float top = mywholeasteroid.getHeight() - mywholeasteroid.getImageheight();
                    float right = mywholeasteroid.getWidth() + mywholeasteroid.getImagewidth();
                    float bot = mywholeasteroid.getHeight() + mywholeasteroid.getImageheight();
                    RectF myrect = new RectF(left, top, right, bot);
                    mywholeasteroid.setRectangle(myrect);
                    asteroidsforlevel.add(mywholeasteroid);
                    asteroidobjectids.add(content.loadImage(SuperAsteroids.singleton.getMyasteroids().get(SuperAsteroids.singleton.getMylevelasteroids().get(i).getasteroidid() - 1).getImagepic()));
                }
            }
        }
    }

    private void loadbackgroundobjectsforlevel()//Loads from level and leve backgrounds to create COMPLETE LEVEL BACKGROUNDS
    {
        for (int i = 0; i < SuperAsteroids.singleton.getMylevelobjects().size(); i++)
        {
            if (SuperAsteroids.singleton.getMylevelobjects().get(i).getLevelid() == SuperAsteroids.singleton.currentlevel())
            {
                String image = SuperAsteroids.singleton.getMybackground().get(SuperAsteroids.singleton.getMylevelobjects().get(i).getObjectId() - 1).getImagelink();
                String position = SuperAsteroids.singleton.getMylevelobjects().get(i).getPosition();
                int objectid = SuperAsteroids.singleton.getMylevelobjects().get(i).getObjectId();
                double scale = SuperAsteroids.singleton.getMylevelobjects().get(i).getScale();
                CompleteBackgroundObjects mycompletebackground = new CompleteBackgroundObjects(image, position, objectid, scale);
                backgroundsforlevel.add(mycompletebackground);
                backgroundobjectsids.add(content.loadImage(SuperAsteroids.singleton.getMybackground().get(SuperAsteroids.singleton.getMylevelobjects().get(i).getObjectId() - 1).getImagelink()));
            }
        }
    }

    @Override
    public void loadContent(ContentManager content)
    {
        SuperAsteroids.singleton.incrementlevel();
        if(SuperAsteroids.singleton.currentlevel()>SuperAsteroids.singleton.getMylevel().size())
        {
            gameover=true;
        }
        if(!gameover)
        {
            count = 0;
            myshots = new ArrayList<>();
            int sound = -1;
            //load ship
            this.content = content;
            content.loadImage(Ship.myship.getCannon().getImage());
            content.loadImage(Ship.myship.getMainbody().getImage());
            content.loadImage(Ship.myship.getEngine().getImage());
            content.loadImage(Ship.myship.getExtrapart().getImage());
            backgroundimageid = content.loadImage("images/space.bmp");
            //load the sound
            try
            {
                sound = content.loadLoopSound(SuperAsteroids.singleton.getlevelinfo().getMusic());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            content.playLoop(sound);
            //loading background objects and asteriods.
            backgroundsforlevel = new ArrayList<>();
            asteroidobjectids = new ArrayList<>();
            backgroundobjectsids = new ArrayList<>();
            asteroidsforlevel = new ArrayList<>();
            projectileid = content.loadImage(Ship.myship.getCannon().getAttackimage());
            loadasteroidsforlevel();
            loadbackgroundobjectsforlevel();
        }
    }

    @Override
    public void unloadContent(ContentManager content) {
        
    }

    private void drawbackgrounds()
    {
        for (int i = 0; i < backgroundsforlevel.size(); i++)
        {
            int backgroundidforbackground = backgroundobjectsids.get(i);
            int hlocation = backgroundsforlevel.get(i).getHeightlocation();
            int wlocation = backgroundsforlevel.get(i).getWidthlocation();
            float scale = (float) backgroundsforlevel.get(i).getScale();
            DrawingHelper.drawImage(backgroundidforbackground, wlocation - (ViewPort.Viewportheight), hlocation - (ViewPort.Viewportwidth), 0, scale, scale, 255);
        }
    }

    private void drawasteroids()
    {
        for (int i = 0; i < asteroidsforlevel.size(); i++)
        {
            int backgroundidforasteroid = asteroidobjectids.get(i);
            float hlocation = asteroidsforlevel.get(i).getHeight();
            float wlocation = asteroidsforlevel.get(i).getWidth();
            int rotation = asteroidsforlevel.get(i).getRotateangle();
            float scale = asteroidsforlevel.get(i).getScale();
            float left = asteroidsforlevel.get(i).getWidth() - asteroidsforlevel.get(i).getImagewidth()*scale;
            float top = asteroidsforlevel.get(i).getHeight() - asteroidsforlevel.get(i).getImageheight()*scale;
            float right = asteroidsforlevel.get(i).getWidth() + asteroidsforlevel.get(i).getImagewidth()*scale;
            float bot = asteroidsforlevel.get(i).getHeight() + asteroidsforlevel.get(i).getImageheight()*scale;
            RectF myrect = new RectF(left, top, right, bot);
            asteroidsforlevel.get(i).setRectangle(myrect);
            DrawingHelper.drawImage(backgroundidforasteroid, wlocation - (ViewPort.Viewportheight), hlocation - (ViewPort.Viewportwidth), rotation, scale, scale, 255);
        }
    }

    private void safezonedrawing()//Gives the ship a flashing effect
    {
        if(safezone)
        {
            if(Ship.myship.getTransparency()<1)
            {
                transparencychange=true;
            }
            if (Ship.myship.getTransparency()>254)
            {
                transparencychange=false;
            }
            if(transparencychange)
            {
                Ship.myship.setTransparency(Ship.myship.getTransparency()+4);
            }
            else
            {
                Ship.myship.setTransparency(Ship.myship.getTransparency()-4);
            }
        }
        else
        {
            Ship.myship.setTransparency( 255);
        }
    }

    private void projectiledrawing()
    {
        for (int i = 0; i < myshots.size(); i++)
        {
            myshots.get(i).drawprojectile();
            if (myshots.get(i).getx() > background_width || myshots.get(i).getx() < 0)
            {
                myshots.remove(i);
            } else if (myshots.get(i).gety() > background_height || myshots.get(i).gety() < 0)
            {
                myshots.remove(i);
            }
        }
        firedshot = false;
    }

    private void drawminimap()
    {
        float  calculationw= ((200f)/(SuperAsteroids.singleton.getlevelinfo().getWidth()));
        float calculationh=(100f/SuperAsteroids.singleton.getlevelinfo().getHeight());
        Rect myrect=new Rect(1500 ,900, 1700, 1000);
        DrawingHelper.drawFilledRectangle(myrect, Color.BLUE, 128);
        PointF mypoint=new PointF(1500+(calculationw*ViewPort.Viewportheight), 900+(calculationh*ViewPort.Viewportwidth));
        DrawingHelper.drawFilledCircle(mypoint, 4, Color.RED, 200);
        for(int i=0; i<asteroidsforlevel.size(); i++)
        {
            PointF astpoint=new PointF(((1500+asteroidsforlevel.get(i).getWidth()*calculationw)),(((900+asteroidsforlevel.get(i).getHeight()*calculationh))));
            DrawingHelper.drawFilledCircle(astpoint, 4, Color.GREEN, 200);
        }
    }
    void startingtheship()
    {
        Ship.myship.setdrawingcordinateheightandlength(DrawingHelper.getGameViewWidth() / 2, DrawingHelper.getGameViewHeight() / 2);
        shipstart = false;
        ViewPort.Viewportheight = 0;
        ViewPort.Viewportwidth = 0;
        Ship.myship.generatemyrect();
        ViewPort.Viewportheight = SuperAsteroids.singleton.getlevelinfo().getHeight() / 2;
        ViewPort.Viewportwidth = SuperAsteroids.singleton.getlevelinfo().getWidth() / 2;
    }
    @Override
    public void draw()
    {
       if(!gameover)
       {
         if (shipstart)//only called at the beggining of the game to draw the ship
         {
            startingtheship();
         }
           //draw the background star picture
           background_height = SuperAsteroids.singleton.getlevelinfo().getHeight();
           background_width = SuperAsteroids.singleton.getlevelinfo().getWidth();
           float calculationforlevelsize;
           calculationforlevelsize = (background_height * background_width) / (2048 * 2048);
           DrawingHelper.drawImage(backgroundimageid, 0, 0, 0, calculationforlevelsize, calculationforlevelsize, 255);
           drawbackgrounds();
           drawasteroids();
           safezonedrawing();
           Ship.myship.draw();
           projectiledrawing();
           count++;
           drawminimap();
           //Level Message
           if (count < 200)
           {
               DrawingHelper.drawCenteredText("NOW STARTING LEVEL " + SuperAsteroids.singleton.currentlevel()+" "+SuperAsteroids.singleton.getlevelinfo().getHint(), 40, Color.CYAN);
           }
       }
        else
       {
          if(Ship.myship.getHealthpoints()>0)
          {
              //end of the game
              DrawingHelper.drawCenteredText("YOU WON!!!!!!!!!!!!", 60, Color.CYAN);
          }
          else
          {
              //ship dies
              DrawingHelper.drawCenteredText("YOU LOST!!!!!", 60, Color.CYAN);
          }
       }
    }
}
