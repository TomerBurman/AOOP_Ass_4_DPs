package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;

/**
 * Herbivore - Class that represents objects that only eat Herbs.
 * @version 1
 * @author Tomer Burman, Oran Bourak
 *
 */
public class Herbivore implements IDiet {


    /**
     * @param food food type
     * @return if food type is vegetable returns true, else false.
     */
    @Override
    public boolean canEat(EFoodType food) {

        return food == EFoodType.VEGETABLE;
    }

    /**
     *
     * @param animal Animal checked.
     * @param food food type
     * @returnthe weight the animal will gain after a meal.
     * if the animal can't eat the food, returns 0.
     */
    @Override
    public double eat(Animal animal, IEdible food) {
        if (this.canEat(food.getFoodType()))
            return animal.getWeight() * 0.07;
        return 0;
    }
}
