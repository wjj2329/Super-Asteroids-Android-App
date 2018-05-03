package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by williamjones on 2/8/16.
 */
public class PowerCore
{
    /**

     *base damage.
     */
    private int cannonBoost;
    /**
     * Integer. Adds to the base speed of the engine.
     */
    private int engineBoost;

    /**
     *String. The path to the power core image.
     */
    private String image;

    public PowerCore(int cannonboost, int engineboost, String image)
    {
        this.cannonBoost=cannonboost;
        this.engineBoost=engineboost;
        this.image=image;
    }

    public int getCannonBoost()
    {
        return cannonBoost;
    }

    public String getImage()
    {
        return image;
    }

    public int getEngineBoost()
    {
        return engineBoost;
    }

}
