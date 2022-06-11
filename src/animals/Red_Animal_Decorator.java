package animals;

import java.awt.*;

public class Red_Animal_Decorator extends Animal_Decorator {

    Red_Animal_Decorator(Animal animal){
        super(animal);
        animal.setColor("Red");
    }
    @Override
    public void loadImages(String nm) {
        animal.loadImages(animal.getPhoto_name() + "r_");
    }

    @Override
    public void drawObject(Graphics g) {
        animal.drawObject(g);
    }

    @Override
    public String getColor() {
        return "Red";
    }
}
