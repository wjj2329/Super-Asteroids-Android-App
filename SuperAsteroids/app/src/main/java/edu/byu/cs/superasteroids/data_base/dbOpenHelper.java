package edu.byu.cs.superasteroids.data_base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by williamjones on 2/9/16.
 */
public class dbOpenHelper extends SQLiteOpenHelper
{
    private SQLiteDatabase db;
    public dbOpenHelper(Context context)
    {
        super(context, "databasefile.db", null, 1);
        //TODO Auto-generated database stuff
    }
     public  void droptable()  //drop the table to get rid of them
     {
         final String dropleveltable="drop table if exists Level";
         final String droplevelAsteroidtable="drop table if exists LevelAsteroids";
         final String dropLevelObject="drop table if exists LevelObject";
         final String dropObjectsTable="drop table if exists Objects;";
         final String dropAsteroidsTable="drop table if exists asteroids";
         final String dropCannonsTable="drop table if exists cannons";
         final String dropEnginesTable="drop table if exists engines";
         final String dropExtraParts="drop table if exists extraParts";
         final String dropMainBodies="drop table if exists mainbodies";
         final String dropPowerCores="drop table if exists powerCores";
         db.execSQL(dropleveltable);
         db.execSQL(droplevelAsteroidtable);
         db.execSQL(dropLevelObject);
         db.execSQL(dropObjectsTable);
         db.execSQL(dropAsteroidsTable);
         db.execSQL(dropCannonsTable);
         db.execSQL(dropEnginesTable);
         db.execSQL(dropExtraParts);
         db.execSQL(dropMainBodies);
         db.execSQL(dropPowerCores);
     }

    public  void createtablenames()  //create the tables for the DB
    {
        final String leveltable="CREATE TABLE Level ('number' INTEGER NOT NULL , 'title' TEXT NOT NULL , 'hint' TEXT NOT NULL , 'width' INTEGER NOT NULL , 'height' INTEGER NOT NULL , 'music' TEXT NOT NULL , PRIMARY KEY ('number', 'title', 'hint', 'width', 'height', 'music'))";
        final String LevelAsteroidtable="CREATE TABLE LevelAsteroids ('number' INTEGER NOT NULL , 'asteroidId' INTEGER NOT NULL , 'levelid' INTEGER NOT NULL)";
        final String LevelObject="CREATE TABLE LevelObject ('position' TEXT NOT NULL , 'objectId' INTEGER NOT NULL , 'scale' DOUBLE NOT NULL, 'levelid' INTEGER NOT NULL) ";
        final String ObjectsTable="CREATE TABLE Objects ('objects' TEXT DEFAULT (null) )";
        final String AsteroidsTable="CREATE TABLE asteroids ('name' TEXT NOT NULL DEFAULT (null) ,'image' TEXT NOT NULL DEFAULT (null) , 'imageWidth' INTEGER NOT NULL DEFAULT (null) ,'imageHeight' INTEGER NOT NULL ,'type' TEXT NOT NULL DEFAULT (null) )";
        final String CannonsTable="CREATE TABLE cannons ('attachPoint' TEXT NOT NULL , 'emitPoint' TEXT NOT NULL , 'image' TEXT NOT NULL , 'imageWidth' INTEGER NOT NULL , 'imageHeight' INTEGER NOT NULL , 'attackImage' TEXT NOT NULL , 'attackImageWidth' INTEGER NOT NULL , 'attackImageHeight' INTEGER NOT NULL , 'attackSound' TEXT NOT NULL , 'damage' INTEGER NOT NULL , PRIMARY KEY ('attachPoint', 'emitPoint', 'image', 'imageWidth', 'imageHeight', 'attackImage', 'attackImageWidth', 'attackImageHeight', 'attackSound', 'damage')) ";
        final String EnginesTable="CREATE TABLE engines ('baseSpeed' INTEGER NOT NULL , 'baseTurnRate' INTEGER NOT NULL , 'attachPoint' TEXT NOT NULL , 'image' TEXT NOT NULL , 'imageWidth' INTEGER NOT NULL , 'imageHeight' INTEGER NOT NULL , PRIMARY KEY ('baseSpeed', 'baseTurnRate', 'attachPoint', 'image', 'imageWidth', 'imageHeight'))";
        final String ExtraParts="CREATE TABLE extraParts ('attachPoint' TEXT NOT NULL , 'image' TEXT NOT NULL , 'imageWidth' INTEGER NOT NULL , 'imageHeight' INTEGER NOT NULL , PRIMARY KEY ('attachPoint', 'image', 'imageWidth', 'imageHeight'))";
        final String MainBodies="CREATE TABLE mainbodies ('cannonAttach' TEXT NOT NULL , 'engineAttach' TEXT NOT NULL , 'extraAttach' TEXT NOT NULL , 'image' TEXT NOT NULL , 'imageWidth' INTEGER NOT NULL , 'imageHeight' INTEGER NOT NULL , PRIMARY KEY ('cannonAttach', 'engineAttach', 'extraAttach', 'image', 'imageWidth', 'imageHeight'))";
        final String PowerCores="CREATE TABLE powerCores ('cannonBoost' INTEGER NOT NULL , 'engineBoost' INTEGER NOT NULL , 'image' TEXT NOT NULL , PRIMARY KEY ('cannonBoost', 'engineBoost', 'image'))";
        db.execSQL(leveltable);
        db.execSQL(LevelAsteroidtable);
        db.execSQL(LevelObject);
        db.execSQL(ObjectsTable);
        db.execSQL(AsteroidsTable);
        db.execSQL(CannonsTable);
        db.execSQL(EnginesTable);
        db.execSQL(ExtraParts);
        db.execSQL(MainBodies);
        db.execSQL(PowerCores);
    }

    @Override
    public void onCreate(SQLiteDatabase db)  //gets called on creation
    {
       this.db=db;
        //create the schemea
        createtablenames();
    }



    @Override
    public void onOpen(SQLiteDatabase db) {this.db=db;}//gets called everytime

    @Override //doesn't do anything
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
           return;
    }
}
