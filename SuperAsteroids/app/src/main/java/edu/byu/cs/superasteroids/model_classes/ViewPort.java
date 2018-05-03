package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by williamjones on 2/26/16.
 * The viewport to keep track of corrdiantes
 */
public class ViewPort
{
    public static float Viewportheight=SuperAsteroids.singleton.getlevelinfo().getHeight()/2;
    public static float Viewportwidth= SuperAsteroids.singleton.getlevelinfo().getWidth()/2;
    public static void updateheightandwidth(float viewportheight, float viewportwidth)
    {
        Viewportheight= (int) viewportheight;
        Viewportwidth= (int) viewportwidth;
    }
}
