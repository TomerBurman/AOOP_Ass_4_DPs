package animals;

import java.awt.*;

public class Blue_Animal_Decorator extends Animal_Decorator{

    Blue_Animal_Decorator(Animal animal){
        super(animal);
        animal.setColor("Blue");
    }
    @Override
    public void loadImages(String nm) {
        animal.loadImages(animal.getPhoto_name() + "b_");
    }

    @Override
    public void drawObject(Graphics g) {
        animal.drawObject(g);
    }

    @Override
    public String getColor() {
        return "Blue";
    }
}
