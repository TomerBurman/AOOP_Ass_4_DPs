package diet;
import food.EFoodType;
import food.IEdible;
import animals.Animal;
/**
 * IDiet - interface that represents eating functionality.
 * @version 1
 * @author Tomer Burman, Oran Bourak
 *
 */
public interface IDiet {
    /**
     *
     * @param food food type
     * @return True if it can be eaten false otherwise.
     */
    boolean canEat(EFoodType food);

    /**
     *
     * @param animal Animal checked.
     * @param food food type
     * @return weight of animal given.
     */
    double eat(Animal animal, IEdible food);

}

