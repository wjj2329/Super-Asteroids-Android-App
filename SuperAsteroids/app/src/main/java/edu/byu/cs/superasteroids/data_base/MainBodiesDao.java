package edu.byu.cs.superasteroids.data_base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;

import java.util.ArrayList;

import edu.byu.cs.superasteroids.model_classes.MainBody;

/**
 * Created by williamjones on 2/11/16.
 * Converts from JSON Array to the SQL Database.  Also returns from the Database an Array list
 * of the objects in my Model class
 */
public class MainBodiesDao
{
    private SQLiteDatabase db;
    public MainBodiesDao(SQLiteDatabase db)
    {
        this.db=db;
    }
    private final String MainbodiesSelect="select * from mainbodies";
    public void  create(JSONArray insert)
    {
        for(int i=0; i<insert.length(); i++)
        {
            try
            {
                ContentValues values=new ContentValues();
                String cannonAttach = insert.getJSONObject(i).getString("cannonAttach");
                String engineAttach=insert.getJSONObject(i).getString("engineAttach");
                String extraAttach=insert.getJSONObject(i).getString("extraAttach");
                String image=insert.getJSONObject(i).getString("image");
                int imagewidth=insert.getJSONObject(i).getInt("imageWidth");
                int imageheight=insert.getJSONObject(i).getInt("imageHeight");
                System.out.println(cannonAttach+imagewidth);
                values.put("cannonAttach", cannonAttach);
                values.put("engineAttach", engineAttach);
                values.put("extraAttach", extraAttach);
                values.put("image", image);
                values.put("imagewidth", imagewidth);
                values.put("imageheight", imageheight);
                db.insert("mainBodies", null, values);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<MainBody> returnmainbodiesarray()
    {
        ArrayList<MainBody>toreturn=new ArrayList<>();
        String[] emptyarray = {};
        Cursor mycursor = db.rawQuery(MainbodiesSelect, emptyarray);
        mycursor.moveToFirst();
        while (!mycursor.isAfterLast())
        {
            String cannonattach=mycursor.getString(0);
            String engineattach=mycursor.getString(1);
            String extraatach=mycursor.getString(2);
            String image=mycursor.getString(3);
            int imagewidth=mycursor.getInt(4);
            int imageheight=mycursor.getInt(5);
            MainBody mbobject = new MainBody(cannonattach, engineattach,extraatach, image, imagewidth, imageheight);
            toreturn.add(mbobject);
            mycursor.moveToNext();
        }
        return toreturn;
    }
}
