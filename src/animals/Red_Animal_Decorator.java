package animals;

import java.awt.*;

public class Red_Animal_Decorator extends Animal_Decorator {

    public Red_Animal_Decorator(Animal animal){
        super(animal);
        animal.setColor("Red");
        this.loadImages(animal.getPhoto_name());
    }
    @Override
    public void loadImages(String nm) {
        animal.loadImages(nm + "r_");
    }

    @Override
    public void drawObject(Graphics g) {
        animal.drawObject(g);
    }

    @Override
    public String getColor() {
        return "Red";
    }

    public String toString(){
        return "Red" + this.getAnimal().toString();
    }
}
