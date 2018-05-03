package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by williamjones on 2/8/16.
 */
public class LevelInfo
{
    /**
     * Integer. The level number
     */

    private int levelnum;
    /**
     * String. The level title.
     */
    private String title;
    /**
     * String. The level hint to be displayed with the title.
     */
    private String hint;
    /**
     * Integer. The pixel width of the level.
     */
    private int width;
    /**
     * Integer. The pixel height of the level.
     */
    private int height;
    /**
     * String. The path to the music file to be played with the level.
     */
    private String music;

    public LevelInfo(int levelnum, String title, String hint, int width, int height, String music)
    {
        this.levelnum=levelnum;
        this.title=title;
        this.hint=hint;
        this.width=width;
        this.height=height;
        this.music=music;
    }
    //getters
    public int getLevelnum() {
        return levelnum;
    }

    public String getMusic() {
        return music;
    }

    public int getWidth() {
        return width;
    }

    public String getTitle() {
        return title;
    }

    public String getHint() {
        return hint;
    }

    public int getHeight() {return height;}

}
