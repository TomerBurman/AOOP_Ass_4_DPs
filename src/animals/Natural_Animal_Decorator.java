package animals;

import java.awt.*;

public class Natural_Animal_Decorator extends Animal_Decorator{


    Natural_Animal_Decorator(Animal animal){
        super(animal);
        animal.setColor("Natural");
    }
    @Override
    public void loadImages(String nm) {
        animal.loadImages(animal.getPhoto_name() + "n_");
    }

    @Override
    public void drawObject(Graphics g) {
        animal.drawObject(g);
    }

    @Override
    public String getColor() {
        return "Natural";
    }
}
