package edu.byu.cs.superasteroids.ship_builder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.base.IView;
import edu.byu.cs.superasteroids.content.ContentManager;
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
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.model_classes.Cannon;
import edu.byu.cs.superasteroids.model_classes.Engine;
import edu.byu.cs.superasteroids.model_classes.ExtraParts;
import edu.byu.cs.superasteroids.model_classes.MainBody;
import edu.byu.cs.superasteroids.model_classes.PowerCore;
import edu.byu.cs.superasteroids.model_classes.Ship;
import edu.byu.cs.superasteroids.model_classes.SuperAsteroids;
/**
 * Created by williamjones on 2/13/16.
 */
public class ShipBuildingController implements IShipBuildingController {
    private String location="main";
    private IView view;
    private boolean mainbodybool=false;
    private boolean enginebool=false;
    private boolean extrapartbool=false;
    private boolean powercorebool=false;
    private boolean cannonbool=false;
    private dbOpenHelper helper;
    private SQLiteDatabase db;

   public  ShipBuildingController(IView view, Context c)
    {
        //get everything set up.  Resets as well incase they played the game and came back here
        setView(view);
        getView();
        this.view= view;
        this.helper=new dbOpenHelper(c);
        this.db=helper.getWritableDatabase();
        Ship.myship.updaterotationdegree(0);
        Ship.myship.setTransparency(255);
        Ship.myship.setdrawingcordinateheightandlength(DrawingHelper.getGameViewWidth() / 2, DrawingHelper.getGameViewHeight() / 2);
        Ship.myship.setcannonforship(null);
        Ship.myship.setextrapartforship(null);
        Ship.myship.setmainbodyforship(null);
        Ship.myship.setengineforship(null);
        Ship.myship.setpowercoreforship(null);
    }

    //setting up arrows correctly in a simple if else chain
    @Override
    public void onViewLoaded(IShipBuildingView.PartSelectionView partView)
    {
        if(enginebool&&powercorebool&&cannonbool&&extrapartbool&&mainbodybool)
        {
            ((IShipBuildingView)view).setStartGameButton(true);
        }

        if(location.equals("left"))
        {
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.EXTRA_PART, IShipBuildingView.ViewDirection.LEFT, false, "");
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.EXTRA_PART, IShipBuildingView.ViewDirection.RIGHT, true, "Main Bodies");
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.EXTRA_PART, IShipBuildingView.ViewDirection.UP, false, "");
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.EXTRA_PART, IShipBuildingView.ViewDirection.DOWN, false, "");
        }
        else if(location.equals("right"))
        {
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.CANNON, IShipBuildingView.ViewDirection.LEFT, true, "Main Bodies");
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.CANNON, IShipBuildingView.ViewDirection.RIGHT, false, "");
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.CANNON, IShipBuildingView.ViewDirection.UP, false, "");
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.CANNON, IShipBuildingView.ViewDirection.DOWN, false, "");
        }
        else if(location.equals("main"))
        {
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.MAIN_BODY, IShipBuildingView.ViewDirection.LEFT, true, "Extra Part");
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.MAIN_BODY, IShipBuildingView.ViewDirection.RIGHT, true, "Cannon");
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.MAIN_BODY, IShipBuildingView.ViewDirection.UP, true, "Power Core");
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.MAIN_BODY, IShipBuildingView.ViewDirection.DOWN, true, "Engine");
        }
        else if(location.equals("down"))
        {
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.ENGINE, IShipBuildingView.ViewDirection.LEFT, false, "");
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.ENGINE, IShipBuildingView.ViewDirection.RIGHT, false, "");
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.ENGINE, IShipBuildingView.ViewDirection.UP, true, "Main Bodies");
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.ENGINE, IShipBuildingView.ViewDirection.DOWN, false, "");
        }
        else if(location.equals("up"))
        {
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, IShipBuildingView.ViewDirection.LEFT, false, "");
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, IShipBuildingView.ViewDirection.RIGHT, false, "");
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, IShipBuildingView.ViewDirection.UP, false, "");
            ((IShipBuildingView) view).setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, IShipBuildingView.ViewDirection.DOWN, true, "Main Bodies");
        }

    }

    @Override
    public void update(double elapsedTime) {}

    void loaddatabase()
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
    public void loadContent(ContentManager content)
    {
        loaddatabase();
        //load objects into lists needed for ship
        List<Integer>engineids=new ArrayList<>();
        ArrayList<Engine> myenginecoredata= SuperAsteroids.singleton.getMyenginecore();
        for(int i=0; i<myenginecoredata.size(); i++)
        {
            engineids.add(content.loadImage(myenginecoredata.get(i).getImage()));
        }
        ((IShipBuildingView)view).setPartViewImageList(IShipBuildingView.PartSelectionView.ENGINE, engineids);
        List<Integer>powercoreids=new ArrayList<>();
        ArrayList<PowerCore> mypowercoredata=SuperAsteroids.singleton.getMypowercore();
        for(int i=0; i<mypowercoredata.size(); i++)
        {
            powercoreids.add(content.loadImage(mypowercoredata.get(i).getImage()));
        }
        ((IShipBuildingView)view).setPartViewImageList(IShipBuildingView.PartSelectionView.POWER_CORE, powercoreids);
        List<Integer>mainbodyids=new ArrayList<>();
        ArrayList<MainBody> mymainbodiesdata=SuperAsteroids.singleton.getMymainbody();
        for(int i=0; i<mymainbodiesdata.size(); i++)
        {
           mainbodyids.add(content.loadImage(mymainbodiesdata.get(i).getImage()));
        }
        ((IShipBuildingView)view).setPartViewImageList(IShipBuildingView.PartSelectionView.MAIN_BODY, mainbodyids);
        ArrayList<Cannon> mycannoncoredata=SuperAsteroids.singleton.getMycannoncore();
        List<Integer> mycannonids=new ArrayList<>();
        for(int i=0; i<mycannoncoredata.size(); i++)
        {
           mycannonids.add(content.loadImage(mycannoncoredata.get(i).getImage()));
        }
        ((IShipBuildingView)view).setPartViewImageList(IShipBuildingView.PartSelectionView.CANNON, mycannonids);
        List<Integer> extrapartimageids=new ArrayList<>();
        ArrayList<ExtraParts>myextrapartsdata=SuperAsteroids.singleton.getMyextraparts();
        for(int i=0; i<myextrapartsdata.size(); i++)
        {
            extrapartimageids.add(content.loadImage(myextrapartsdata.get(i).getImage()));
        }
        ((IShipBuildingView)view).setPartViewImageList(IShipBuildingView.PartSelectionView.EXTRA_PART, extrapartimageids);
    }

    @Override
    public void unloadContent(ContentManager content) {}

    @Override
    public void draw()
    {

        Ship.myship.draw();
    }

    @Override
    public void onSlideView(IShipBuildingView.ViewDirection direction)
    {
        //the eight different combinations of screens
        if(enginebool&&powercorebool&&cannonbool&&extrapartbool&&mainbodybool)
        {
            ((IShipBuildingView)view).setStartGameButton(true);
        }
        if(location.equals("up"))
        {
            if(direction==IShipBuildingView.ViewDirection.DOWN)
            {
                ((IShipBuildingView)view).animateToView(IShipBuildingView.PartSelectionView.MAIN_BODY, IShipBuildingView.ViewDirection.DOWN);
                location="main";
            }
        }
        else if(location.equals("down"))
        {
            if(direction==IShipBuildingView.ViewDirection.UP)
            {
                ((IShipBuildingView)view).animateToView(IShipBuildingView.PartSelectionView.MAIN_BODY, IShipBuildingView.ViewDirection.UP);
                location="main";
            }
        }
        else if(location.equals("right"))
        {
            if(direction==IShipBuildingView.ViewDirection.LEFT)
            {
                ((IShipBuildingView)view).animateToView(IShipBuildingView.PartSelectionView.MAIN_BODY, IShipBuildingView.ViewDirection.LEFT);
                location="main";
            }
        }
        else if(location.equals("left"))
        {
            if(direction==IShipBuildingView.ViewDirection.RIGHT)
            {
                ((IShipBuildingView)view).animateToView(IShipBuildingView.PartSelectionView.MAIN_BODY, IShipBuildingView.ViewDirection.RIGHT);
                location="main";

            }
        }
       else if(direction==IShipBuildingView.ViewDirection.UP&&location.equals("main"))
       {
           System.out.println("I go up");
                   ((IShipBuildingView) view).animateToView(IShipBuildingView.PartSelectionView.POWER_CORE, IShipBuildingView.ViewDirection.UP);
           location="up";
       }
       else if(direction==IShipBuildingView.ViewDirection.DOWN&&location.equals("main"))
       {
           System.out.println("i go down");
           ((IShipBuildingView)view).animateToView(IShipBuildingView.PartSelectionView.ENGINE, IShipBuildingView.ViewDirection.DOWN);
           location="down";
       }
       else  if(direction==IShipBuildingView.ViewDirection.LEFT&&location.equals("main"))
        {
            System.out.println("I go left");
                    ((IShipBuildingView) view).animateToView(IShipBuildingView.PartSelectionView.EXTRA_PART, IShipBuildingView.ViewDirection.LEFT);
            location="left";
        }
       else if(direction==IShipBuildingView.ViewDirection.RIGHT&&location.equals("main"))
        {
            System.out.println("I go right");
            ((IShipBuildingView)view).animateToView(IShipBuildingView.PartSelectionView.CANNON, IShipBuildingView.ViewDirection.RIGHT);
            location="right";
        }
//this to animate the direction to go  call animate to view to help
    }

    @Override
    public void onPartSelected(int index)
    {
        //On the part selected depending on which screen we are on will add to the Ship the piece
        if(location.equals("main"))
        {
            Ship.myship.setdrawingcordinateheightandlength(DrawingHelper.getGameViewWidth()/2, DrawingHelper.getGameViewHeight()/2);
            Ship.myship.setmainbodyforship(SuperAsteroids.singleton.getMymainbody().get(index));
            mainbodybool=true;
        }
        if(location.equals("left"))
        {
            Ship.myship.setextrapartforship(SuperAsteroids.singleton.getMyextraparts().get(index));
            extrapartbool=true;
        }
        if(location.equals("right"))
        {
            Ship.myship.setcannonforship(SuperAsteroids.singleton.getMycannoncore().get(index));
         }
        if(location.equals("up"))
        {
           Ship.myship.setpowercoreforship(SuperAsteroids.singleton.getMypowercore().get(index));
            powercorebool=true;
        }
        if(location.equals("down"))
        {
            Ship.myship.setengineforship(SuperAsteroids.singleton.getMyenginecore().get(index));
            enginebool=true;
        }
        if(enginebool&&powercorebool&&cannonbool&&extrapartbool&&mainbodybool)
        {
            ((IShipBuildingView) view).setStartGameButton(true);
        }
    }

    @Override
    public void onStartGamePressed()//starts the game with Ship give health
    {
        Ship.myship.setHealthpoints(50);
        SuperAsteroids.singleton.setlevel(0);
        ((IShipBuildingView)view).startGame();
    }

    @Override//not used
    public void onResume() {}

    @Override//not used
    public IView getView() {return null;}

    @Override//not used
    public void setView(IView view) {}
}
