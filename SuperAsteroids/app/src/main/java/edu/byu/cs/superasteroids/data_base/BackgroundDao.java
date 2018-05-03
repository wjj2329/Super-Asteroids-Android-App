package edu.byu.cs.superasteroids.data_base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;

import java.util.ArrayList;

import edu.byu.cs.superasteroids.model_classes.Background;

/**
 * Created by williamjones on 2/11/16.
 * Converts from JSON Array to the SQL Database.  Also returns from the Database an Array list
 * of the objects in my Model class
 */
public class BackgroundDao
{
    private  SQLiteDatabase db;
    public BackgroundDao(SQLiteDatabase db)
    {
        this.db=db;
    }
    private final String ObjectsTableSelect="select * from Objects";

    public  void  create(JSONArray Object)
    {
        for(int i=0; i<Object.length(); i++)
        {
            try
            {
                ContentValues values=new ContentValues();
                String insert = Object.getString(i);
                values.put("objects", insert);
                db.insert("Objects", null, values);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

   public ArrayList<Background> returnbackgroundarray()
    {
        ArrayList<Background>toreturn=new ArrayList<>();
        String[] emptyarray = {};
        Cursor mycursor = db.rawQuery(ObjectsTableSelect, emptyarray);
        mycursor.moveToFirst();
        while (!mycursor.isAfterLast())
        {
                String image = mycursor.getString(0);
                Background object = new Background(image);
                toreturn.add(object);
                mycursor.moveToNext();
        }
           return toreturn;
    }

}
