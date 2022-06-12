package graphics;

import animals.Animal;
import animals.Observable_interface;
import diet.IDiet;
import food.IEdible;

public interface IAnimalInterface extends IDrawable,IAnimalBehavior, IEdible, Runnable, Observable_interface,Cloneable {
    Animal getAnimal();
    void run();
}
