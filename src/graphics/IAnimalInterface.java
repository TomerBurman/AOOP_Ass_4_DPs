package graphics;

import animals.Animal;
import food.IEdible;

public interface IAnimalInterface extends IDrawable,IAnimalBehavior, IEdible,Runnable {
    public Animal getAnimal();
}
