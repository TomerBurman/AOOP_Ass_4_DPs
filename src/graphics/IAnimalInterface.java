package graphics;

import animals.Animal;
import diet.IDiet;
import food.IEdible;

public interface IAnimalInterface extends IDrawable,IAnimalBehavior, IEdible, Runnable{
    Animal getAnimal();
}
