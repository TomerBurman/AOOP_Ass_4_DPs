package animals;

import graphics.Observer_interface;

import java.awt.*;

public class Blue_Animal_Decorator extends Animal_Decorator{

    public Blue_Animal_Decorator(Animal animal){
        super(animal);
        animal.setColor("Blue");
        this.loadImages(animal.getPhoto_name());
    }
    @Override
    public void loadImages(String nm) {
        animal.loadImages(nm + "b_");
    }

    @Override
    public void drawObject(Graphics g) {
        animal.drawObject(g);
    }

    @Override
    public String getColor() {
        return "Blue";
    }

    public String toString(){
        return "Blue" + this.getAnimal().toString();
    }


}
