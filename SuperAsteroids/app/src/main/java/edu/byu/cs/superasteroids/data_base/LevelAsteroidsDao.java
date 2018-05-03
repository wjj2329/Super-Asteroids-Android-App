package edu.byu.cs.superasteroids.data_base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;

import java.util.ArrayList;

import edu.byu.cs.superasteroids.model_classes.LevelAsteroids;
import edu.byu.cs.superasteroids.model_classes.SuperAsteroids;

/**
 * Created by williamjones on 2/11/16.
 * Converts from JSON Array to the SQL Database.  Also returns from the Database an Array list
 * of the objects in my Model class
 */
public class LevelAsteroidsDao extends ArrayList<LevelAsteroids>
{
    private SQLiteDatabase db;
    public LevelAsteroidsDao(SQLiteDatabase db)
    {
        this.db=db;
    }
    private final String levelasteroidsSelect="select * from LevelAsteroids";
    private int levelid=0;
    //keeps track of which level these things are for in the DB
    public void increaselevelid()
    {
        levelid++;
    }

    public void  create(JSONArray object)
    {
        for(int i=0; i<object.length(); i++)
        {
            try
            {
                ContentValues values=new ContentValues();
                int number = object.getJSONObject(i).getInt("number");
                int asteroidId=object.getJSONObject(i).getInt("asteroidId");
                System.out.println(number);
                System.out.println(asteroidId);
                values.put("number", number);
                values.put("asteroidId", asteroidId);
                values.put("levelid", levelid);
                if(object.length()!=0)
                {
                    db.insert("LevelAsteroids", null, values);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<LevelAsteroids> returnasteroidsarray()
    {
        ArrayList<LevelAsteroids>toreturn=new ArrayList<>();
        String[] emptyarray = {};
        Cursor mycursor = db.rawQuery(levelasteroidsSelect, emptyarray);
        mycursor.moveToFirst();
        int size=0;
        while (!mycursor.isAfterLast())
        {
            size++;
            int number=mycursor.getInt(0);
            int asteroidid=mycursor.getInt(1);
            int levelid=mycursor.getInt(2);
            LevelAsteroids lvaobject = new LevelAsteroids(number, asteroidid, levelid);
            toreturn.add(lvaobject);
            mycursor.moveToNext();
        }
        SuperAsteroids.singleton.getNumoflevasteroidstoloadperlevel().add(size);
        return toreturn;
    }

}
