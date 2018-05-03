package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by williamjones on 2/19/16.
 */
public class ExtraParts
{
    private String attachpoint;
    private String image;
    private int imagewidth;
    private int imageheight;

    public ExtraParts(String attachpoint, String image, int imagewidth, int imageheight)
    {
        this.attachpoint=attachpoint;
        this.image=image;
        this.imagewidth=imagewidth;
        this.imageheight=imageheight;
    }
    //getters
    public String getAttachpoint() {
        return attachpoint;
    }

    public String getImage() {
        return image;
    }

    public int getImagewidth() {
        return imagewidth;
    }

    public int getImageheight() {
        return imageheight;
    }

}
