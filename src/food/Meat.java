package food;

import graphics.IDrawable;
import graphics.ZooPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Meat - class that represents Food type of meat. implements IDrawable
 * and IEdible interfaces.
 */
public class Meat implements IDrawable, IEdible {
    /**
     * img - represents img of meat.
     */
    private BufferedImage img;

    private static Meat instance = null;

    /**
     * Singleton for Meat. thread-safe. DCL.
     * @return instance.
     */
    public static Meat makeInstance(){
        if(instance == null){
            synchronized (Meat.class){
                if(instance == null)
                    instance = new Meat();
            }
        }
        return instance;
    }

    /**
     * Meat ctor - loads image of meat to img.
     */
    private Meat(){
        this.loadImages("meat");
    }

    /**
     * loadImages - loads a certain image into img variable.
     * @param nm - name of the img without endings. e.g "bear"
     */
    @Override
    public void loadImages(String nm) {
        try {
            img = ImageIO.read(new File(PICTURE_PATH + "\\src\\photos\\" + nm + ".gif"));
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(ZooPanel.getDraw_panel(),"Cannot load image","Error",JOptionPane.ERROR_MESSAGE);
            }
    }

    /**
     * drawObject - uses graphics drawImage to draw meat to the center of the screen.
     * @param g
     */
    @Override
    public void drawObject(Graphics g) {
        Graphics2D gr = (Graphics2D)g;
        JPanel pan = ZooPanel.getDraw_panel();
        gr.drawImage(img,pan.getWidth()/2-5,pan.getHeight()/2-5, 25, 25, pan);
    }

    /**
     * getColor - returns Meat color.
     * @return
     */
    @Override
    public String getColor() {
        return "Red";
    }

    /**
     * returns food type
     * @return EFoodType.
     */
    @Override
    public EFoodType getFoodType() {
        return EFoodType.MEAT;
    }
}
