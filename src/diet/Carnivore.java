package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;

/**
 * Carnivore - Class represents objects that only eat meat
 * @version 1
 * @author Tomer Burman, Oran Bourak
 *
 */
public class Carnivore implements IDiet{

    /**
     *
     * @param food food type
     * @return true if the food is meat, else false.
     */
    @Override
    public boolean canEat(EFoodType food) {
        return (food == EFoodType.MEAT); // if the food is meat returns true, else false.
    }

    /**
     * eat
     * @param animal Animal checked.
     * @param food food type
     * @return returns the weight the animal will gain after a meal.
     * if the animal can't eat the food, returns 0.
     */
    @Override
    public double eat(Animal animal, IEdible food) {
        if (this.canEat(food.getFoodType()))
            return animal.getWeight()*0.1;
        return 0;

    }
}
