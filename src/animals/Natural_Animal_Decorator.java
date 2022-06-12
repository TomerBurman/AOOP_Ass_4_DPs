package animals;

import java.awt.*;

public class Natural_Animal_Decorator extends Animal_Decorator{


    public Natural_Animal_Decorator(Animal animal){
        super(animal);
        animal.setColor("Natural");
        this.loadImages(animal.getPhoto_name());
    }
    @Override
    public void loadImages(String nm) {
        animal.loadImages(nm + "n_");
    }

    @Override
    public void drawObject(Graphics g) {
        animal.drawObject(g);
    }

    @Override
    public String getColor() {
        return "Natural";
    }

    public String toString(){
        return "Natural " +super.toString() ;
    }
}
