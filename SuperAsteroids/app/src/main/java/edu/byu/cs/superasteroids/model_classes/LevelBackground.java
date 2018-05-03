package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by williamjones on 2/17/16.
 */
public class LevelBackground
{
    private String position;
    private int objectId;
    private double scale;
    private int levelid;

    public LevelBackground(String position, int objectId, double scale, int levelid)
     {
        this.position=position;
        this.objectId=objectId;
        this.scale=scale;
        this.levelid=levelid;
     }
    //getters
    public int getLevelid() {return  levelid;}

    public String getPosition() {
        return position;
    }

    public double getScale() {
        return scale;
    }

    public int getObjectId() {
        return objectId;
    }
}
