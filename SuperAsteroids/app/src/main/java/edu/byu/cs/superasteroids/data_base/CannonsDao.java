package edu.byu.cs.superasteroids.data_base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;

import java.util.ArrayList;

import edu.byu.cs.superasteroids.model_classes.Cannon;

/**
 * Created by williamjones on 2/11/16.
 * Converts from JSON Array to the SQL Database.  Also returns from the Database an Array list
 * of the objects in my Model class
 */
public class CannonsDao
{
    private final String CannonTableSelect="select * from cannons";
    private SQLiteDatabase db;
    public CannonsDao(SQLiteDatabase db)
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
          String attachPoint = insert.getJSONObject(i).getString("attachPoint");
          String emitPoint = insert.getJSONObject(i).getString("emitPoint");
          String image = insert.getJSONObject(i).getString("image");
          int imagewidth = insert.getJSONObject(i).getInt("imageWidth");
          int imageheight = insert.getJSONObject(i).getInt("imageHeight");
          String attackImage=insert.getJSONObject(i).getString("attackImage");
          int  attackImageWidth=insert.getJSONObject(i).getInt("attackImageWidth");
          int attackImageHeight=insert.getJSONObject(i).getInt("attackImageHeight");
          String attackSound=insert.getJSONObject(i).getString("attackSound");
          int damage=insert.getJSONObject(i).getInt("damage");
          values.put("attachPoint", attachPoint);
          values.put("emitPoint", emitPoint);
          values.put("image", image);
          values.put("imagewidth", imagewidth);
          values.put("imageheight", imageheight);
          values.put("attackImage", attackImage);
          values.put("attackImageWidth", attackImageWidth);
          values.put("attackImageHeight",attackImageHeight);
          values.put("attackSound",attackSound);
          values.put("damage",damage);
          db.insert("cannons", null, values);
         }
         catch (Exception e)
         {
          e.printStackTrace();
         }
       }
    }

 public ArrayList<Cannon> returncannonsarray()
    {
    ArrayList<Cannon>toreturn=new ArrayList<>();
    String[] emptyarray = {};
    Cursor mycursor = db.rawQuery(CannonTableSelect, emptyarray);
    mycursor.moveToFirst();
     while (!mycursor.isAfterLast())
        {
            String attachpoint=mycursor.getString(0);
            String emitpoint=mycursor.getString(1);
            String image=mycursor.getString(2);
            int imagewidth=mycursor.getInt(3);
            int imageheight=mycursor.getInt(4);
            String attackimage=mycursor.getString(5);
            int attackimagewidth=mycursor.getInt(6);
            int attackimageheight=mycursor.getInt(7);
            String attacksound=mycursor.getString(8);
            int damage=mycursor.getInt(9);
            Cannon Cannonstuff = new Cannon(attachpoint, emitpoint, image, imagewidth, imageheight, attackimage, attackimagewidth, attackimageheight, attacksound, damage);
            toreturn.add(Cannonstuff);
            mycursor.moveToNext();
        }
    return toreturn;
    }

}
