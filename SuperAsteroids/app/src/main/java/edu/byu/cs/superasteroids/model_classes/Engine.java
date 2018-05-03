package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by williamjones on 2/8/16.
 */
public class Engine
{
    /**
     *Integer. The base maximum velocity of the ship in pixels per second.
     */
    private int baseSpeed;
    /**
     *Integer. The base turn rate of the ship in degrees per second.
     */
    private int baseTurnRate;
    /*
    Coordinate String. The point of the engine part image that attaches to the
     */
    /**
     *main body image
     */
    private String attachPoint;
    /**
     *String. The path to engine part image.
     */
    private String image;
    /**
     *Integer. The pixel width of the engine part image.
     */
    private int imageWidth;
    /**
     *Integer. The pixel height of the engine part image.
     */
    private int imageHeight;

    public Engine(int baseSpeed, int baseTurnRate, String attachPoint, String image, int imageWidth, int imageHeight)
    {
        this.baseSpeed=baseSpeed;
        this.baseTurnRate=baseTurnRate;
        this.attachPoint=attachPoint;
        this.image=image;
        this.imageWidth=imageWidth;
        this.imageHeight=imageHeight;

    }
    //getters
    public int getBaseSpeed() {
        return baseSpeed;
    }

    public int getBaseTurnRate() {
        return baseTurnRate;
    }

    public String getAttachPoint() {
        return attachPoint;
    }

    public String getImage() {
        return image;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {return imageHeight;}

}
