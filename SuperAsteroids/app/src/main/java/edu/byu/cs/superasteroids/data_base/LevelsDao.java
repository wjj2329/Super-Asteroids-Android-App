package edu.byu.cs.superasteroids.data_base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONObject;

import java.util.ArrayList;

import edu.byu.cs.superasteroids.model_classes.LevelInfo;

/**
 * Created by williamjones on 2/11/16.
 * Converts from JSON Array to the SQL Database.  Also returns from the Database an Array list
 * of the objects in my Model class
 */
public class LevelsDao {

    private SQLiteDatabase db;
    private final String LevelSelect="select * from Level";
    public LevelsDao(SQLiteDatabase db) {
        this.db = db;
    }
    public void create(JSONObject Level)
    {
        try
        {
            ContentValues values = new ContentValues();
            int number = Level.getInt("number");
            String title = Level.getString("title");
            String hint = Level.getString("hint");
            int width = Level.getInt("width");
            int height = Level.getInt("height");
            String music = Level.getString("music");
            System.out.println(number);
            System.out.println(music);
            values.put("number", number);
            values.put("title", title);
            values.put("hint", hint);
            values.put("width", width);
            values.put("height", height);
            values.put("music", music);
            db.insert("Level", null, values);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<LevelInfo> returnlevelinfoarray()
    {
        ArrayList<LevelInfo>toreturn=new ArrayList<>();
        String[] emptyarray = {};
        Cursor mycursor = db.rawQuery(LevelSelect, emptyarray);
        mycursor.moveToFirst();
        while (!mycursor.isAfterLast())
        {
            int levelnum=mycursor.getInt(0);
            String title=mycursor.getString(1);
            String hint=mycursor.getString(2);
            int width=mycursor.getInt(3);
            int height=mycursor.getInt(4);
            String music=mycursor.getString(5);
            LevelInfo levelstuff = new LevelInfo(levelnum, title, hint, width, height, music);
            toreturn.add(levelstuff);
            mycursor.moveToNext();
        }
        return toreturn;
    }

}
