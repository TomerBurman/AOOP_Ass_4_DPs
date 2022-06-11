package animals;

import food.EFoodType;
import food.IEdible;
import graphics.IAnimalInterface;

import java.awt.*;

public abstract class Animal_Decorator implements IAnimalInterface {
    protected Animal animal;


    Animal_Decorator(Animal animal){
        this.animal = animal;
    }


    public Animal getAnimal(){
        return this.animal;
    }

    @Override
    public EFoodType getFoodType() {
        return animal.getFoodType();
    }

    @Override
    public String getAnimalName() {
        return animal.getAnimalName();
    }

    @Override
    public int getSize() {
        return animal.getSize();
    }

    @Override
    public void eatInc() {
        animal.eatInc();
    }

    @Override
    public int getEatCount() {
       return animal.getEatCount();
    }

    @Override
    public boolean getChanges() {
        return animal.getChanges();
    }

    @Override
    public void setChanges(boolean state) {
        animal.setChanges(state);
    }

    @Override
    public void setSuspended() {
        animal.setSuspended();

    }

    @Override
    public void setResumed() {
        animal.setResumed();
    }

    @Override
    public void drawObject(Graphics g) {
        animal.drawObject(g);
    }

    @Override
    public void run() {
        animal.run();
    }


}
