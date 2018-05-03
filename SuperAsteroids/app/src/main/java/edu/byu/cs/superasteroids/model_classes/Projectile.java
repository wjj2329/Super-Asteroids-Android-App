package edu.byu.cs.superasteroids.model_classes;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by williamjones on 3/1/16.
 */
public class Projectile
{
    private int id;
    private float size=.5f;
    private float rotation;
    private float resultx;
    private float resulty;
    private boolean temp=true;

    public Projectile(int id, float rotation)
    {
        this.id=id;
        this.rotation=rotation;
    }

    public void drawprojectile()//drawing the shot
    {
        String abstracting=Ship.myship.getCannon().getEmitPoint();
        String [] parts=abstracting.split(",");
        float projectilewidth=Float.parseFloat(parts[0]);
        float projectileheight=Integer.parseInt(parts[1]);
        PointF temp=new PointF(projectilewidth,projectileheight);
        float raidans= (float) GraphicsUtils.degreesToRadians(rotation);
        PointF result= GraphicsUtils.rotate(temp, raidans);
        if(this.temp)
        {
            resultx = result.x*size+Ship.myship.getDrawingcordinatelength();
            resulty = result.y*size+Ship.myship.getDrawingcordinateheight();
        }
        this.temp=false;
        DrawingHelper.drawImage(id, resultx, resulty, rotation, size, size, 255);
    }

    public void update()//update coordinates
    {
        PointF mypoint=new PointF(0, -1);
        PointF newpoint=GraphicsUtils.rotate(mypoint, Math.toRadians(rotation));
        resultx+=newpoint.x*40;
        resulty+=newpoint.y*40;
    }
    public float getx() {return resultx;}

    public float gety(){return resulty;}

    public void updatex(float update) {this.resultx=update;}

    public void updatey(float update){this.resulty=update;}
}
