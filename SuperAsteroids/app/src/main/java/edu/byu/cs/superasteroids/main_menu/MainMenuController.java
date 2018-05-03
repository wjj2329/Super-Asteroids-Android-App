package edu.byu.cs.superasteroids.main_menu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Random;

import edu.byu.cs.superasteroids.base.IView;
import edu.byu.cs.superasteroids.data_base.AsteroidDao;
import edu.byu.cs.superasteroids.data_base.CannonsDao;
import edu.byu.cs.superasteroids.data_base.EnginesDao;
import edu.byu.cs.superasteroids.data_base.ExtraPartsDao;
import edu.byu.cs.superasteroids.data_base.LevelAsteroidsDao;
import edu.byu.cs.superasteroids.data_base.LevelBackgroundDao;
import edu.byu.cs.superasteroids.data_base.LevelsDao;
import edu.byu.cs.superasteroids.data_base.MainBodiesDao;
import edu.byu.cs.superasteroids.data_base.BackgroundDao;
import edu.byu.cs.superasteroids.data_base.PowerCoresDao;
import edu.byu.cs.superasteroids.data_base.dbOpenHelper;
import edu.byu.cs.superasteroids.model_classes.Ship;
import edu.byu.cs.superasteroids.model_classes.SuperAsteroids;

/**
 * Created by williamjones on 2/23/16.
 * Pretty Simple enough creates the ship for me for my game
 */
public class MainMenuController implements IMainMenuController {
    private MainActivity mainactivity;
    private dbOpenHelper helper;
    private SQLiteDatabase db;

    MainMenuController(MainActivity mainactivity, Context c)
    {
        this.mainactivity=mainactivity;
        this.helper=new dbOpenHelper(c);
        this.db=helper.getWritableDatabase();
    }

    private void loadinfo_from_db()
    {
        BackgroundDao objects=new BackgroundDao(db);
        LevelsDao levels=new LevelsDao(db);
        AsteroidDao asteroids=new AsteroidDao(db);
        LevelBackgroundDao levelobject=new LevelBackgroundDao(db);
        LevelAsteroidsDao levelasteroid=new LevelAsteroidsDao(db);
        MainBodiesDao mainbodies=new MainBodiesDao(db);
        CannonsDao cannons=new CannonsDao(db);
        ExtraPartsDao extrapart= new ExtraPartsDao(db);
        EnginesDao engines=new EnginesDao(db);
        PowerCoresDao powercore=new PowerCoresDao(db);
        SuperAsteroids.singleton.givetomybackground(objects.returnbackgroundarray());
        SuperAsteroids.singleton.givetoasteroidscore(asteroids.returnasteroidsarray());
        SuperAsteroids.singleton.givetomylevelobjects(levelobject.returnlevelobject());
        SuperAsteroids.singleton.givetomylevel(levels.returnlevelinfoarray());
        SuperAsteroids.singleton.givetomycannoncore(cannons.returncannonsarray());
        SuperAsteroids.singleton.givetomyenginecore(engines.returnaenginesarray());
        SuperAsteroids.singleton.givetomylevelasteroids(levelasteroid.returnasteroidsarray());
        SuperAsteroids.singleton.givetomymainbodies(mainbodies.returnmainbodiesarray());
        SuperAsteroids.singleton.givetomypowercore(powercore.returnpowercoresarray());
        SuperAsteroids.singleton.givetomyextraparts(extrapart.returnextrapartsarray());
    }
    @Override
    public void onQuickPlayPressed()
    {
        loadinfo_from_db();
        //generates random ship for game
        Random randomGenerator = new Random();
        int selection=randomGenerator.nextInt(2);
        Ship.myship.setmainbodyforship(SuperAsteroids.singleton.getMymainbody().get(selection));
        selection=randomGenerator.nextInt(2);
        Ship.myship.setengineforship(SuperAsteroids.singleton.getMyenginecore().get(selection));
        selection=randomGenerator.nextInt(2);
        Ship.myship.setextrapartforship(SuperAsteroids.singleton.getMyextraparts().get(selection));
        selection=randomGenerator.nextInt(2);
        Ship.myship.setpowercoreforship(SuperAsteroids.singleton.getMypowercore().get(selection));
        selection=randomGenerator.nextInt(2);
        Ship.myship.setcannonforship(SuperAsteroids.singleton.getMycannoncore().get(selection));
        Ship.myship.setHealthpoints(50);
        SuperAsteroids.singleton.setlevel(0);
        //stars the game
        mainactivity.startGame();
    }

    @Override
    public IView getView() {
        return null;
    }

    @Override
    public void setView(IView view) {}
}
