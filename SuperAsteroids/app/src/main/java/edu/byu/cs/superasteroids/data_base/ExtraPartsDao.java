package edu.byu.cs.superasteroids.data_base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;

import java.util.ArrayList;

import edu.byu.cs.superasteroids.model_classes.ExtraParts;

/**
 * Created by williamjones on 2/11/16.
 *  Converts from JSON Array to the SQL Database.  Also returns from the Database an Array list
 * of the objects in my Model class
 */
public class ExtraPartsDao
{
    private final String ExtrapartsSelect="select * from extraParts";
    private SQLiteDatabase db;
    public ExtraPartsDao(SQLiteDatabase db)
    {
        this.db=db;
    }

    public void  create(JSONArray insert)
    {
        ContentValues values = new ContentValues();
        for (int i = 0; i < insert.length(); i++)
        {
            try
            {
                String attachPoint = insert.getJSONObject(i).getString("attachPoint");
                String image = insert.getJSONObject(i).getString("image");
                int imagewidth = insert.getJSONObject(i).getInt("imageWidth");
                int imageheight = insert.getJSONObject(i).getInt("imageHeight");
                values.put("attachPoint", attachPoint);
                values.put("image", image);
                values.put("imagewidth", imagewidth);
                values.put("imageheight", imageheight);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            db.insert("extraParts",null, values);
        }
    }

    public ArrayList<ExtraParts> returnextrapartsarray()
    {
        ArrayList<ExtraParts>toreturn=new ArrayList<>();
        String[] emptyarray = {};
        Cursor mycursor = db.rawQuery(ExtrapartsSelect, emptyarray);
        mycursor.moveToFirst();
        while (!mycursor.isAfterLast())
        {
            String attachpoint=mycursor.getString(0);
            String image=mycursor.getString(1);
            int imagewidth=mycursor.getInt(2);
            int imageheight=mycursor.getInt(3);
            ExtraParts extrapart = new ExtraParts(attachpoint, image, imagewidth, imageheight);
            toreturn.add(extrapart);
            mycursor.moveToNext();
        }
        return toreturn;
    }

}
