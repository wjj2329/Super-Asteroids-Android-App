package edu.byu.cs.superasteroids.model_classes;
/**
 * Created by williamjones on 2/8/16.
 */
public class Asteroids extends SuperAsteroids
{
    private String name;
    /**
     * String. The path for the image file for the asteroid
     */
    private  String imagepic;
    /**
     * Integer. The pixel width of the asteroid’s image.
     */
    private  int imageWidth;
    /**
     * Integer. The pixel height of the asteroid’s image.
     */
    private int imageHeight;
    /**
     * String. The type of the asteroid. This is used to determine the behavior and characteristics
     * of the asteroid.
     */
    private String type;

    public Asteroids(String name, String imagepic, int imageWidth, int imageHeight, String type)
    {
        this.name=name;
        this.imagepic=imagepic;
        this.imageWidth=imageWidth;
        this.imageHeight=imageHeight;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public String getImagepic() {
        return imagepic;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public String getType() {return type;}

}