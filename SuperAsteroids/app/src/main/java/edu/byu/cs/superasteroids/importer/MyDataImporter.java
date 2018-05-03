package edu.byu.cs.superasteroids.importer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.util.Scanner;

import edu.byu.cs.superasteroids.data_base.AsteroidDao;
import edu.byu.cs.superasteroids.data_base.CannonsDao;
import edu.byu.cs.superasteroids.data_base.EnginesDao;
import edu.byu.cs.superasteroids.data_base.ExtraPartsDao;
import edu.byu.cs.superasteroids.data_base.LevelAsteroidsDao;
import edu.byu.cs.superasteroids.data_base.LevelBackgroundDao;
import edu.byu.cs.superasteroids.data_base.LevelsDao;
import edu.byu.cs.superasteroids.data_base.MainBodiesDao;
import edu.byu.cs.superasteroids.data_base.BackgroundDao;
import edu.byu.cs.superasteroids.data_base.PowerCoresDao;
import edu.byu.cs.superasteroids.data_base.dbOpenHelper;
import edu.byu.cs.superasteroids.model_classes.SuperAsteroids;

/**
 * Created by williamjones on 2/11/16.
 */
public class MyDataImporter implements IGameDataImporter {
    //this is a pointer to an activity

    private Context context;
    //my daos
    private SQLiteDatabase db;
    private BackgroundDao objects;
    private LevelsDao levels;
    private AsteroidDao asteroids;
    private LevelBackgroundDao levelobject;
    private LevelAsteroidsDao levelasteroid;
    private MainBodiesDao mainbodies;
    private CannonsDao cannons;
    private ExtraPartsDao extrapart;
    private EnginesDao engines;
    private PowerCoresDao powercore;
    public MyDataImporter(Context c)
    {

        context=c;
    }

    //functions to give to my dao's
    private void givetomainbodies(JSONArray mainbodyinfo)
    {

        mainbodies.create(mainbodyinfo);
    }
    private void givetocannons(JSONArray cannoninfo)
    {

        cannons.create(cannoninfo);
    }
    private void givetoextrapart(JSONArray extrapartinfo)
    {

        extrapart.create(extrapartinfo);
    }
    private void givetoengines(JSONArray enginesinfo)
    {
        engines.create(enginesinfo);
    }
    private void givetopowercore(JSONArray powercoreinfo) {powercore.create(powercoreinfo);}
    private void givetoobjects(JSONArray objectInfo)
    {
        objects.create(objectInfo);
    }
    private void givetoasteroids(JSONArray asteroidinfo)
    {
        asteroids.create(asteroidinfo);
    }
    private void givetolevels(JSONArray levelinfo)//function for giving to levels dao
    {
        for(int i=0; i<levelinfo.length(); i++)
        {
            try
            {
                JSONObject Level =(JSONObject)levelinfo.get(i);
                levels.create(Level);
                JSONArray LevelObject=Level.getJSONArray("levelObjects");
                levelobject.increaselevelid();
                levelobject.create(LevelObject);
                JSONArray levelAsteroids=Level.getJSONArray("levelAsteroids");
                levelasteroid.increaselevelid();
                levelasteroid.create(levelAsteroids);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void givetoModelClasses()//function to give to Model Classes
    {
        SuperAsteroids.singleton.givetomybackground(objects.returnbackgroundarray());
        SuperAsteroids.singleton.givetoasteroidscore(asteroids.returnasteroidsarray());
        SuperAsteroids.singleton.givetomylevelobjects(levelobject.returnlevelobject());
        SuperAsteroids.singleton.givetomylevel(levels.returnlevelinfoarray());
        SuperAsteroids.singleton.givetomycannoncore(cannons.returncannonsarray());
        SuperAsteroids.singleton.givetomyenginecore(engines.returnaenginesarray());
        SuperAsteroids.singleton.givetomylevelasteroids(levelasteroid.returnasteroidsarray());
        SuperAsteroids.singleton.givetomymainbodies(mainbodies.returnmainbodiesarray());
        SuperAsteroids.singleton.givetomypowercore(powercore.returnpowercoresarray());
        SuperAsteroids.singleton.givetomyextraparts(extrapart.returnextrapartsarray());
    }

    private void setupeverything()
    {
        dbOpenHelper mydatabase=new dbOpenHelper(context);
        db=mydatabase.getWritableDatabase();
        mydatabase.droptable();
        mydatabase.createtablenames();
        //create the DAOS
        objects=new BackgroundDao(db);
        asteroids=new AsteroidDao(db);
        levels=new LevelsDao(db);
        levelobject=new LevelBackgroundDao(db);
        levelasteroid=new LevelAsteroidsDao(db);
        mainbodies=new MainBodiesDao(db);
        cannons=new CannonsDao(db);
        extrapart= new ExtraPartsDao(db);
        engines=new EnginesDao(db);
        powercore=new PowerCoresDao(db);

    }


    
    @Override
    public boolean importData(InputStreamReader dataInputReader)
    {

        setupeverything();


        try
        {
            JSONObject fullfile=new JSONObject(dataInputReader.toString());
            //get the Json file and then get the asteroidsGame object
            JSONObject obj = new JSONObject(fullfile.toString());
            JSONObject  whatIwant= obj.getJSONObject("asteroidsGame");
            //get the objects out of it
            JSONArray objects= whatIwant.getJSONArray("objects");

            //get the asteroids out of it
            JSONArray asteroids=whatIwant.getJSONArray("asteroids");
            //get the levels array out of it with it's sub tables will have to parse to get them
            JSONArray levels=whatIwant.getJSONArray("levels");
            //get Main Bodies
            JSONArray mainbodies=whatIwant.getJSONArray("mainBodies");
            //get the cannons
            JSONArray cannons=whatIwant.getJSONArray("cannons");
            //get the extra parts
            JSONArray extraparts=whatIwant.getJSONArray("extraParts");
            //get the power cores
            JSONArray powercores=whatIwant.getJSONArray("powerCores");
            //get the engines
            JSONArray engines=whatIwant.getJSONArray("engines");
            givetoobjects(objects);
            givetoasteroids(asteroids);
            givetolevels(levels);
            givetomainbodies(mainbodies);
            givetocannons(cannons);
            givetoextrapart(extraparts);
            givetopowercore(powercores);
            givetoengines(engines);
            givetoModelClasses();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
