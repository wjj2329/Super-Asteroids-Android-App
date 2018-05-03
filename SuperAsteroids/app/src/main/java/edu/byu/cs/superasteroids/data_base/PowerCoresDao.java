package edu.byu.cs.superasteroids.data_base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;

import java.util.ArrayList;

import edu.byu.cs.superasteroids.model_classes.PowerCore;

/**
 * Created by williamjones on 2/11/16.
 * Converts from JSON Array to the SQL Database.  Also returns from the Database an Array list
 * of the objects in my Model class
 */
public class PowerCoresDao
{
    private final String powercoresSelect="select * from powerCores";
    private SQLiteDatabase db;
    public PowerCoresDao(SQLiteDatabase db)
    {
        this.db=db;
    }

    public void  create(JSONArray insert)
    {
        for (int i = 0; i < insert.length(); i++)
        {
            try
            {
                ContentValues values = new ContentValues();
                int cannonBoost = insert.getJSONObject(i).getInt("cannonBoost");
                int engineBoost = insert.getJSONObject(i).getInt("engineBoost");
                String image = insert.getJSONObject(i).getString("image");
                System.out.println(cannonBoost + engineBoost);
                values.put("cannonBoost", cannonBoost);
                values.put("engineBoost", engineBoost);
                values.put("image", image);
                db.insert("powerCores", null, values);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<PowerCore> returnpowercoresarray()
    {
        ArrayList<PowerCore>toreturn=new ArrayList<>();
        String[] emptyarray = {};
        Cursor mycursor = db.rawQuery(powercoresSelect, emptyarray);
        mycursor.moveToFirst();
        while (!mycursor.isAfterLast())
        {
            int cannonboost=mycursor.getInt(0);
            int engineboost=mycursor.getInt(1);
            String image=mycursor.getString(2);
            PowerCore mbobject = new PowerCore(cannonboost, engineboost, image);
            toreturn.add(mbobject);
            mycursor.moveToNext();
        }
        return toreturn;
    }

}
