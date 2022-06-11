package animals;

import java.awt.*;

public class Red extends ColorfulAnimal_Decorator{

    Red(Animal animal){
        super(animal);
        animal.setColor("Red");
    }
    @Override
    public void loadImages(String nm) {
        animal.loadImages(animal.getPhoto_name() + "b");
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
