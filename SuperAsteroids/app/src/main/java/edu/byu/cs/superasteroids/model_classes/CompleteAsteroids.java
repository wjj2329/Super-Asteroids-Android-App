package edu.byu.cs.superasteroids.model_classes;

import android.graphics.RectF;

import java.util.Random;

/**
 * Created by williamjones on 2/29/16.
 * I combined the level asteroid and the asteroid class here
 */
public class CompleteAsteroids
{
    private double timesincehit;
    private boolean alreadyhit=false;
    private float scale;
    private int health;
    private String name;
    private String image;
    private int imagewidth;
    private int imageheight;
    private String type;
    private int number;
    private  int asteroidid;
    private  float height;
    private float width;
    private int rotateangle;
    private int speedwidth;
    private int speedheight;
    private RectF myrectangle;
    private boolean parent;

    public CompleteAsteroids(String name, String image, int imagewidth, int imageheight, String type, int number, int asteroidid)
    {
        this.name=name;
        this.image=image;
        this.imagewidth=imagewidth;
        this.imageheight=imageheight;
        this.type=type;
        this.number=number;
        this.asteroidid=asteroidid;
        this.myrectangle=new RectF();
        if(type.equals("octeroid"))
        {
        scale=1;
        this.health=5;
        }
        if(type.equals("growing"))
        {
        scale=1;
        this.health=5;
        }
        if(type.equals("regular"))
        {
        scale=1;
        this.health=5;
        }
    }

    public void crash()
    {
        rotateangle+=180;
        speedwidth-=speedwidth*2;
        speedheight-=speedheight*2;
    }

    public void outofboundscheck(int gameheight,  int gamewidth)
    {
        if(height+imageheight>=gameheight)
        {
            rotateangle+=180;
            speedheight-=(speedheight*2);
        }
        if(width+imagewidth>=gamewidth)
        {
            rotateangle+=180;
            speedwidth-=(speedwidth*2);
        }
        if(width-imagewidth<0)
        {
            rotateangle+=180;
            speedwidth-=(speedwidth*2);
        }
        if(height-imageheight<0)
        {
            rotateangle+=180;
            speedheight-=(speedheight*2);
        }
    }

    //my setters/update functions
    public void setTimesincehit(double timesincehit){this.timesincehit=timesincehit;}

    public void setHealth(int health){this.health=health;}

    public void setHeight(float height){this.height=height;}

    public void setParent(boolean parent) {this.parent=parent;}

    public void setRectangle(RectF myrectangle) {this.myrectangle=myrectangle;}

    public void setScale(float scale){this.scale=scale;}

    public void setWidth(float width){this.width=width;}

    public void setAlreadyhit(Boolean alreadyhit){this.alreadyhit=alreadyhit;}

    public void updatescale() {scale+=0.001f;}

    public void generatespeed()
    {
        Random rand=new Random();
        Random rand2=new Random();
        this.speedwidth=rand.nextInt(20)-rand2.nextInt(20);
        while(speedwidth==0)
        {
            this.speedwidth=rand.nextInt(20)-rand2.nextInt(20);
        }
        this.speedheight=rand.nextInt(20)-rand2.nextInt(20);
        while(speedheight==0)
        {
            this.speedheight=rand.nextInt(20)-rand2.nextInt(20);
        }
    }
    public void   generateheight()
    {
        Random rand=new Random();
        this.height=rand.nextInt(SuperAsteroids.singleton.getlevelinfo().getWidth()-imageheight);
        while(this.height<=0+imageheight)
        {
            rand=new Random();
            this.height=rand.nextInt(SuperAsteroids.singleton.getlevelinfo().getWidth()-imageheight);
        }
    }
    public void  generatewidth()
    {
        Random rand=new Random();
        this.width=rand.nextInt(SuperAsteroids.singleton.getlevelinfo().getHeight()-imagewidth);
        while(this.width<=0+imagewidth)
        {
            rand=new Random();
            this.width=rand.nextInt(SuperAsteroids.singleton.getlevelinfo().getHeight()-imagewidth);
        }
    }
    public void generaterotate()
    {
        Random rand=new Random();
        this.rotateangle=rand.nextInt(361);
    }
    //My getters
    public boolean getAlreadyhit(){return alreadyhit;}

    public double getTimesincehit(){return timesincehit;}

    public float getScale(){return scale;}

    public float getHeight(){return height;}

    public float getWidth(){return  width;}

    public boolean getParent(){return parent;}

    public int getHealth() {return health;}

    public RectF getRectangle() {return this.myrectangle;}

    public String getName() {return name;}

    public String getImage() {
        return image;
    }

    public int getImagewidth() {
        return imagewidth;
    }

    public int getImageheight() {
        return imageheight;
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public int getAsteroidid() {
        return asteroidid;
    }

    public int getSpeedforwidth()
    {
        return speedwidth;
    }

    public int getSpeedforheight()
    {
        return speedheight;
    }

    public int getRotateangle() {return rotateangle;}

}
