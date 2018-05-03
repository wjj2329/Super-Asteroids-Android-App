package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by williamjones on 2/8/16.
 */
public class MainBody
{
    /**
     *Coordinate String. The point on the main body image where the
     */
    /**
     *cannon should be attached.
     */
    private String cannonAttach;
    /**
     *Coordinate String. The point on the main body image where the
     */
    /**
     *engine should be attached.
     */
    private String engineAttach;
    /**
     *Coordinate String. The point on the main body image where the
     */
    /**
     * extra part should be attached.
     */
    private String extraAttach;
    /**
     *String. The path to main body image
     */
    private String image;
    /**
     * Integer. The pixel width of the main body image
     */
    private int imageWidth;
    /**
     *Integer. The pixel height of the main body image
     */
    private int imageHeight;

    public MainBody(String cannonattach, String engineattach, String extraatach, String image, int imagewidth, int imageheight)
    {
        this.cannonAttach=cannonattach;
        this.engineAttach=engineattach;
        this.extraAttach=extraatach;
        this.image=image;
        this.imageWidth=imagewidth;
        this.imageHeight=imageheight;
    }
    //getters
    public String getEngineAttach() {
        return engineAttach;
    }

    public String getExtraAttach() {
        return extraAttach;
    }

    public String getImage() {
        return image;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public String getCannonAttach() {return cannonAttach;}

}
