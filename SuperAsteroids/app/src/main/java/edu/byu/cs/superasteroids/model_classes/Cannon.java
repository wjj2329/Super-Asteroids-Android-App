package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by williamjones on 2/8/16.
 */
public class Cannon
{
    /**
     *Coordinate String. The point of the cannon image that attaches to the
     */
    /**
     *main body image.
     */
    private String attachPoint;
    /**
     *Coordinate String. The point of the cannon image the projectile is emitted from.
     */
    private String emitPoint;
    /**
     *String. The path to cannon image.
     */
    private String image;
    /**
     *Integer. The pixel width of the cannon image.
     */
    private int imageWidth;
    /**
     *Integer. The pixel height of the cannon image.
     */
    private int imageHeight;
    /**
     *String. The path to the cannon’s projectile image
     */
    private String attackimage;
    /**
     * Integer. The pixel width of the cannon’s projectile image.
     */
    private int attackimagewidth;
    /**
     * Integer. The pixel height of the cannon’s projectile image.
     */
    private int attackimageheight;
    /**
     *
     String. The path to the cannon’s projectile sound file
     */
    private String attacksound;

    /**
     *constructor would get all these objects to initialize them.
     */
    private int damage;

    public Cannon(String attachPoint, String emitPoint, String image, int imageWidth, int imageHeight, String attackimage, int attackimagewidth, int attackimageheight, String attacksound,int damage)
    {
        this.attachPoint=attachPoint;
        this.emitPoint=emitPoint;
        this.image=image;
        this.imageWidth=imageWidth;
        this.imageHeight=imageHeight;
        this.attackimage=attackimage;
        this.attackimagewidth=attackimagewidth;
        this.attackimageheight=attackimageheight;
        this.attacksound=attacksound;
        this.damage=damage;
    }

    public String getAttachPoint() {
        return attachPoint;
    }

    public String getImage() {
        return image;
    }

    public String getEmitPoint() {
        return emitPoint;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public String getAttackimage() {
        return attackimage;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getAttackimagewidth() {
        return attackimagewidth;
    }

    public String getAttacksound() {
        return attacksound;
    }

    public int getAttackimageheight() {
        return attackimageheight;
    }

    public int getDamage() {return damage;}

}
