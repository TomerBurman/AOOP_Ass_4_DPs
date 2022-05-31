package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;


// Ask about implementation of methods : canEat and eat if they're supposed to be static. 
/**
 * Omnivore - Class that represents objects that eat all ( meat and herbs).
 * @version 1
 * @author Oran Bourak, Tomer Burman.
 */
public class Omnivore implements IDiet {



    //Delegators
    private final IDiet herb_Eater = new Herbivore(); // herb eater methods
    private final IDiet meat_Eater = new Carnivore(); // meat eater methods


    /** canEat - using herb_Eater and meat_Eater methods
     * @param food food type
     * @return True if food type is either MEAT or VEGETABLE, false otherwise.
     */
    @Override
    public boolean canEat(EFoodType food) {
        return (herb_Eater.canEat(food) || meat_Eater.canEat(food));
    }

    /** eat - using delegators herb_Eater and meat_Eater for the methods.
     * @param animal Animal checked.
     * @param food   food type
     * @return the weight the animal will gain after a meal.
     * * if the animal can't eat the food, returns 0.
     */
    @Override
    public double eat(Animal animal, IEdible food) {
        if (this.canEat(food.getFoodType())) {
            if (food.getFoodType() == EFoodType.MEAT)
                return this.meat_Eater.eat(animal, food); // Carnivore
            return this.herb_Eater.eat(animal, food); // Herbivore
        }
        return 0;
    }
}