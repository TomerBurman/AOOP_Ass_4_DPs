package animals;

import graphics.IDrawable;

public abstract class ColorfulAnimal_Decorator implements IDrawable {
    protected Animal animal;

    ColorfulAnimal_Decorator(Animal animal){
        this.animal = animal;
    }
}
