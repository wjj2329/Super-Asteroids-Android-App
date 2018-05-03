package edu.byu.cs.superasteroids.data_base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;

import java.util.ArrayList;

import edu.byu.cs.superasteroids.model_classes.LevelBackground;

/**
 * Created by williamjones on 2/11/16.
 * Converts from JSON Array to the SQL Database.  Also returns from the Database an Array list
 * of the objects in my Model class
 */
public class LevelBackgroundDao
{
    private SQLiteDatabase db;
    public LevelBackgroundDao(SQLiteDatabase db)
    {
        this.db=db;
    }
    private final String LevelTableSelect="select * from LevelObject";
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
                String position = object.getJSONObject(i).getString("position");
                int objectId=object.getJSONObject(i).getInt("objectId");
                double scale=object.getJSONObject(i).getDouble("scale");
                System.out.println(position);
                System.out.println(objectId);
                values.put("position", position);
                values.put("objectId", objectId);
                values.put("scale", scale);
                values.put("levelid", levelid);
                if(object.length()!=0)
                {
                    db.insert("LevelObject", null, values);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<LevelBackground> returnlevelobject()
    {
        ArrayList<LevelBackground>toreturn=new ArrayList<LevelBackground>();
        String[] emptyarray = {};
        Cursor mycursor = db.rawQuery(LevelTableSelect, emptyarray);
        mycursor.moveToFirst();
        while (!mycursor.isAfterLast())
        {
            String position=mycursor.getString(0);
            int objectId=mycursor.getInt(1);
            double scale=mycursor.getDouble(2);
            int levelid=mycursor.getInt(3);
            LevelBackground Levelobjectto = new LevelBackground(position, objectId, scale, levelid);
            toreturn.add(Levelobjectto);
            mycursor.moveToNext();
        }
        return toreturn;
    }

}
