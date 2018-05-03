package edu.byu.cs.superasteroids.model_classes;

import java.util.ArrayList;

/**
 * Created by williamjones on 2/8/16.
 *
 * the super mega boss class that everything inherits from.
 *
 */
public class  SuperAsteroids
{
    public static SuperAsteroids singleton=new SuperAsteroids();
    private ArrayList<Integer>numoflevasteroidstoloadperlevel=new ArrayList<>();
    private ArrayList<Background>mybackground;
    private ArrayList<PowerCore>mypowercore;
    private ArrayList<Cannon>mycannoncore;
    private ArrayList<Engine>myenginecore;
    private ArrayList<Asteroids>myasteroids;
    private ArrayList<MainBody>mymainbody;
    private ArrayList<LevelAsteroids>mylevelasteroids;
    private ArrayList<LevelBackground>mylevelobjects;
    private ArrayList<LevelInfo>mylevel;
    private ArrayList<ExtraParts>myextraparts;
    private int currentlevel=0;
    //setters
    public void givetomybackground(ArrayList<Background>givetoBackground) {mybackground=givetoBackground;}

    public void givetomypowercore(ArrayList<PowerCore>givetomypowercore) {mypowercore=givetomypowercore;}

    public void givetomycannoncore(ArrayList<Cannon>givetomycannoncore) {mycannoncore=givetomycannoncore;}

    public void givetomyenginecore(ArrayList<Engine>givetomyenginecore) {myenginecore=givetomyenginecore;}

    public void givetoasteroidscore(ArrayList<Asteroids> givetomyasteroids) {myasteroids=givetomyasteroids;}

    public void givetomymainbodies(ArrayList<MainBody> givetomymainbody) {mymainbody=givetomymainbody;}

    public void givetomylevelasteroids(ArrayList<LevelAsteroids> givetomylevelasteroids) {mylevelasteroids=givetomylevelasteroids;}

    public void givetomylevelobjects(ArrayList<LevelBackground>givetomylevelobjects) {mylevelobjects=givetomylevelobjects;}

    public void givetomylevel(ArrayList<LevelInfo> givetomylevel)
    {
        mylevel=givetomylevel;
    }

    public void givetomyextraparts(ArrayList<ExtraParts> givetomyextraparts) {myextraparts=givetomyextraparts;}

    public LevelInfo getlevelinfo()
    {
        return mylevel.get(currentlevel-1);
    }

    public void incrementlevel()
    {
        currentlevel++;
    }

    public void setlevel(int level){currentlevel=level;}

      //getters

    public int  currentlevel() {return currentlevel;}

    public ArrayList<Integer> getNumoflevasteroidstoloadperlevel() {return numoflevasteroidstoloadperlevel;}

    public ArrayList<Background> getMybackground() {return mybackground;}

    public ArrayList<PowerCore> getMypowercore() {return mypowercore;}

    public ArrayList<Cannon> getMycannoncore() {return mycannoncore;}

    public ArrayList<Engine> getMyenginecore() {return myenginecore;}

    public ArrayList<Asteroids> getMyasteroids() {return myasteroids;}

    public ArrayList<MainBody> getMymainbody() {return mymainbody;}

    public ArrayList<LevelAsteroids> getMylevelasteroids() {return mylevelasteroids;}

    public ArrayList<LevelBackground> getMylevelobjects() {return mylevelobjects;}

    public ArrayList<LevelInfo> getMylevel() {return mylevel;}

    public ArrayList<ExtraParts> getMyextraparts() {return myextraparts;}

}
