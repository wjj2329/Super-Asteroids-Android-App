package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by williamjones on 2/29/16.
 * I combined the level background objects and background objects here
 */
public class CompleteBackgroundObjects
{
    private String image;
    private int objectid;
    private double scale;
    private int widthlocation;
    private int heightlocation;

    public CompleteBackgroundObjects(String image,  String position, int objectid, double scale)
    {
        this.image=image;
        this.objectid=objectid;
        this.scale=scale;
        String[] parts=position.split(",");
        this.heightlocation=Integer.parseInt(parts[0]);
        this.widthlocation=Integer.parseInt(parts[1]);
    }

    //the getters
    public String getImage() {
        return image;
    }

    public int getObjectid() {
        return objectid;
    }

    public double getScale() {
        return scale;
    }

    public int getWidthlocation() {
        return widthlocation;
    }

    public int getHeightlocation() {
        return heightlocation;
    }
}
