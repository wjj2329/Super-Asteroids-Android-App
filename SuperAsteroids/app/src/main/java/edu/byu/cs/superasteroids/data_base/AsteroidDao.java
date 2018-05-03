package edu.byu.cs.superasteroids.data_base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;

import java.util.ArrayList;

import edu.byu.cs.superasteroids.model_classes.Asteroids;

/**
 * Created by williamjones on 2/9/16.
 * Converts from JSON Array to the SQL Database.  Also returns from the Database an Array list
 * of the objects in my Model class
 */
public class AsteroidDao
{
    private final String AsteroidsTableSelect="select * from asteroids";
    private SQLiteDatabase db;
    public AsteroidDao(SQLiteDatabase db)
    {
        this.db=db;
    }

    public  void  create(JSONArray insert)
    {
        for(int i=0; i<insert.length(); i++)
        {
            try
            {
                ContentValues values=new ContentValues();
                String name = insert.getJSONObject(i).getString("name");
                String image=insert.getJSONObject(i).getString("image");
                int imagewidth=insert.getJSONObject(i).getInt("imageWidth");
                int imageheight=insert.getJSONObject(i).getInt("imageHeight");
                String type=insert.getJSONObject(i).getString("type");
                System.out.println(name);
                System.out.println(imagewidth);
                values.put("name", name);
                values.put("image", image);
                values.put("imagewidth", imagewidth);
                values.put("imageheight", imageheight);
                values.put("type", type);
                db.insert("asteroids",null, values);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<Asteroids> returnasteroidsarray()
    {
        ArrayList<Asteroids>toreturn=new ArrayList<>();
        String[] emptyarray = {};
        Cursor mycursor = db.rawQuery(AsteroidsTableSelect, emptyarray);
        mycursor.moveToFirst();
        while (!mycursor.isAfterLast())
        {
            String name = mycursor.getString(0);
            String imagepic= mycursor.getString(1);
            int imageWidth=mycursor.getInt(2);
            int imageHeight=mycursor.getInt(3);
            String type=mycursor.getString(4);
            Asteroids object = new Asteroids(name, imagepic, imageWidth, imageHeight, type);
            toreturn.add(object);
            mycursor.moveToNext();
        }
        return toreturn;
    }

}
