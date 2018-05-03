package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by williamjones on 2/8/16.
 */
public class Background
{
    //image link
    private String imagelink="";

    public Background(String image)
{
    this.imagelink=image;
}

    public String getImagelink()
    {
        return imagelink;
    }
}
