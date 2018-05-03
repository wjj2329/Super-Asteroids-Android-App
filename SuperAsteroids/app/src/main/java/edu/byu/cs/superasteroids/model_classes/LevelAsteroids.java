package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by williamjones on 2/17/16.
 */
public class LevelAsteroids
{
    private int number;
    private int asteroidid;
    private int levelid;

    public LevelAsteroids(int number, int asteroidid, int levelid)
    {
        this.number=number;
        this.asteroidid=asteroidid;
        this.levelid=levelid;

    }
    //getters
    public int getLevelid()
    {
        return levelid;
    }

    public int getNumber() {
        return number;
    }

    public int getasteroidid() {
        return asteroidid;
    }

}
