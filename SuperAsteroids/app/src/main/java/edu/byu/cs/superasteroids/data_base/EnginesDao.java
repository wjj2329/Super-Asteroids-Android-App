package edu.byu.cs.superasteroids.data_base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;

import java.util.ArrayList;

import edu.byu.cs.superasteroids.model_classes.Engine;

/**
 * Created by williamjones on 2/11/16.
 * Converts from JSON Array to the SQL Database.  Also returns from the Database an Array list
 * of the objects in my Model class
 */
public class EnginesDao
{
    private SQLiteDatabase db;
    public EnginesDao(SQLiteDatabase db)
    {
        this.db=db;
    }
    private final String EnginesTableSelect="select * from engines";

    public void  create(JSONArray insert)
    {
        for (int i = 0; i < insert.length(); i++)
        {
            try
            {
                ContentValues values = new ContentValues();
                int baseSpeed=insert.getJSONObject(i).getInt("baseSpeed");
                int baseTurnRate=insert.getJSONObject(i).getInt("baseTurnRate");
                String attachPoint = insert.getJSONObject(i).getString("attachPoint");
                String image = insert.getJSONObject(i).getString("image");
                int imagewidth = insert.getJSONObject(i).getInt("imageWidth");
                int imageheight = insert.getJSONObject(i).getInt("imageHeight");
                values.put("baseSpeed", baseSpeed);
                values.put("baseTurnRate", baseTurnRate);
                values.put("attachPoint", attachPoint);
                values.put("image", image);
                values.put("imagewidth", imagewidth);
                values.put("imageheight", imageheight);
                db.insert("engines",null, values);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Engine> returnaenginesarray()
    {
        ArrayList<Engine>toreturn=new ArrayList<>();
        String[] emptyarray = {};
        Cursor mycursor = db.rawQuery(EnginesTableSelect, emptyarray);
        mycursor.moveToFirst();
        while (!mycursor.isAfterLast())
        {
            int basespeed=mycursor.getInt(0);
            int baseturn=mycursor.getInt(1);
            String attachpoint=mycursor.getString(2);
            String image=mycursor.getString(3);
            int imagewidth=mycursor.getInt(4);
            int imageheight=mycursor.getInt(5);
            Engine engineobject = new Engine(basespeed, baseturn, attachpoint, image, imagewidth, imageheight);
            toreturn.add(engineobject);
            mycursor.moveToNext();
        }
        return toreturn;
    }

}
