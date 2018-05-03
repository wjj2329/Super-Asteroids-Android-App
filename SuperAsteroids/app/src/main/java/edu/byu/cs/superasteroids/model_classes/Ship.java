package edu.byu.cs.superasteroids.model_classes;

import android.graphics.PointF;
import android.graphics.RectF;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by williamjones on 2/22/16.
 */
public class Ship
{
    private int transparency=255;
    private int healthpoints=25;
    private RectF myrect;
    private int drawingcordinateheight;
    private int drawingcordinatelength;
    private float size =.25f;
    public static Ship myship=new Ship();
    private int width;
    private int height;
    private ExtraParts extrapart=null;
    private Engine engine=null;
    private PowerCore powercore=null;
    private Cannon cannon=null;
    private MainBody mainbody=null;
    private int mainbodycannonwidthap;
    private int mainbodycannonheightap;
    private int mainbodyenginewidthap;
    private int mainbodyengineheightap;
    private int mainbodyextraattachwidthap;
    private int mainbodyextraatachheightap;
    private float rotation=0;

   private void drawengine()
    {
        String abstrating=engine.getAttachPoint();
        String[] parts=abstrating.split(",");
        float engionwidth=Integer.parseInt(parts[0]);
        float engineheight=Integer.parseInt(parts[1]);
        float partoffsetwidthengine=(mainbodyenginewidthap*size-width*size)+((engine.getImageWidth()*size/2)-engionwidth*size);
        float partoffsetheightengine=(mainbodyengineheightap*size-height*size)+((engine.getImageHeight()*size/2)-engineheight*size);
        PointF temp=new PointF(partoffsetwidthengine, partoffsetheightengine);
        float raidans= (float) GraphicsUtils.degreesToRadians(rotation);
        PointF result= GraphicsUtils.rotate(temp, raidans);
        float widthlocationengine=result.x+drawingcordinatelength;
        float heightlocationengine=result.y+drawingcordinateheight;
        int idengine=ContentManager.getInstance().getImageId(engine.getImage());
        DrawingHelper.drawImage(idengine, widthlocationengine , heightlocationengine, rotation,size, size, transparency );
    }

    private void drawextrapart()
    {
        String abstrating=extrapart.getAttachpoint();
        String[] parts=abstrating.split(",");
        float extrapartwidth=Integer.parseInt(parts[0]);
        float extrapartheight=Integer.parseInt(parts[1]);
        float partoffsetwidthextrapart=(mainbodyextraattachwidthap*size-width*size)+((extrapart.getImagewidth()*size/2)-extrapartwidth*size);
        float partoffsetheightextrapart=(mainbodyextraatachheightap*size-height*size)+((extrapart.getImageheight()*size/2)-extrapartheight*size);//offset
        PointF temp=new PointF(partoffsetwidthextrapart, partoffsetheightextrapart);//rotate
        float radians=(float)GraphicsUtils.degreesToRadians(rotation);
        PointF result=GraphicsUtils.rotate(temp, radians);


        float widthlocationextrapart=result.x+drawingcordinatelength;
        float heightlocationextrapart=result.y+drawingcordinateheight;
        int id=ContentManager.getInstance().getImageId(extrapart.getImage());
        DrawingHelper.drawImage(id, widthlocationextrapart, heightlocationextrapart, rotation, size, size, transparency);
    }

    private void drawcannon()
    {
        String abstrating=cannon.getAttachPoint();
        String[] parts=abstrating.split(",");
        float cannonapx=Integer.parseInt(parts[0]);
        float cannonapy=Integer.parseInt(parts[1]);


        float partoffsetwidth=(mainbodycannonwidthap*size-width*size)+((cannon.getImageWidth()*size/2)-cannonapx*size);
        float partoffsetheight=(mainbodycannonheightap*size-height*size)+((cannon.getImageHeight()*size/2)-cannonapy*size);//offset
        PointF temp=new PointF(partoffsetwidth, partoffsetheight);//rotate
        float radians=(float)GraphicsUtils.degreesToRadians(rotation);
        PointF result=GraphicsUtils.rotate(temp, radians);
        float widthlocation=result.x+drawingcordinatelength;
        float heightlocation=result.y+drawingcordinateheight;
        int id=ContentManager.getInstance().getImageId(cannon.getImage());
        DrawingHelper.drawImage(id, widthlocation,heightlocation, rotation, size, size, transparency);
    }
   private void drawmainbody()
    {
        this.height=mainbody.getImageHeight()/2;
        this.width=mainbody.getImageWidth()/2;

        String abstrating=mainbody.getCannonAttach();
        String[] parts=abstrating.split(",");
        this.mainbodycannonwidthap=Integer.parseInt(parts[0]);
        this.mainbodycannonheightap=Integer.parseInt(parts[1]);

        String abstractingextrapart=mainbody.getExtraAttach();
        String[] extraparts=abstractingextrapart.split(",");
        this.mainbodyextraattachwidthap=Integer.parseInt(extraparts[0]);
        this.mainbodyextraatachheightap=Integer.parseInt(extraparts[1]);

        String absstractingengine=mainbody.getEngineAttach();
        String[] engineparts=absstractingengine.split(",");
        this.mainbodyenginewidthap=Integer.parseInt(engineparts[0]);
        this.mainbodyengineheightap=Integer.parseInt(engineparts[1]);

        int id=ContentManager.getInstance().getImageId(mainbody.getImage());
        DrawingHelper.drawImage(id, drawingcordinatelength,drawingcordinateheight, rotation, size, size, transparency);
    }
    public void draw()
    {
        if(engine!=null&&mainbody!=null)
        {
            drawengine();
        }
        if(extrapart!=null&&mainbody!=null)
        {
           drawextrapart();
        }
        if(powercore!=null&&mainbody!=null)
        {
            int id=ContentManager.getInstance().getImageId(powercore.getImage());
        }
        if(cannon!=null&&mainbody!=null)
        {
            drawcannon();
        }
        if(mainbody!=null)
        {
           drawmainbody();
        }
    }
    //setters
    public void setdrawingcordinateheightandlength(int length, int height)
    {
        this.drawingcordinatelength=length;
        this.drawingcordinateheight=height;
    }

    public void generatemyrect()
    {
        float top=ViewPort.y+drawingcordinateheight-extrapart.getImageheight()*size;
        float right=ViewPort.x+drawingcordinatelength+cannon.getImageHeight()*size;
        float left=ViewPort.y+drawingcordinatelength-extrapart.getImagewidth()*size;
        float bottom=ViewPort.x+drawingcordinateheight+cannon.getImageWidth()*size;
        myrect=new RectF(left, top, right, bottom);
    }
    public void setHealthpoints(int healthpoints){this.healthpoints=healthpoints;}

    public void  setextrapartforship(ExtraParts extrapart) {this.extrapart=extrapart;}

    public void setMyrect(RectF myrect) {this.myrect=myrect;}

    public  void setengineforship(Engine engine) {this.engine=engine;}

    public void setpowercoreforship(PowerCore powercore) {this.powercore=powercore;}

    public void setcannonforship(Cannon cannon) {this.cannon=cannon;}

    public void setmainbodyforship(MainBody mainbody) {this.mainbody=mainbody;}

    public void updaterotationdegree(float update) {this.rotation=update;}

    public void setTransparency(int Transparency) {this.transparency=Transparency;}
    //getters
    public RectF getMyrect() {return myrect;}

    public float getRotation() {return rotation;}

    public int getTransparency() {return transparency;}

    public int getHealthpoints() {return healthpoints;}

    public int getDrawingcordinateheight() {return drawingcordinateheight;}

    public int getDrawingcordinatelength() {return drawingcordinatelength;}

    public float getSize() {return size;}

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    public ExtraParts getExtrapart() {return extrapart;}

    public Engine getEngine() {return engine;}

    public PowerCore getPowercore() {return powercore;}

    public Cannon getCannon() {return cannon;}

    public MainBody getMainbody() {return mainbody;}

    public int getMainbodycannonwidthap() {return mainbodycannonwidthap;}

    public int getMainbodycannonheightap() {return mainbodycannonheightap;}

    public int getMainbodyenginewidthap() {return mainbodyenginewidthap;}

    public int getMainbodyengineheightap() {return mainbodyengineheightap;}

    public int getMainbodyextraattachwidthap() {return mainbodyextraattachwidthap;}

    public int getMainbodyextraatachheightap() {return mainbodyextraatachheightap;}

}
