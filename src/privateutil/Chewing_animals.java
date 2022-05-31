package privateutil;
import animals.*;
import mobility.Point;

/**Chewing animals - extends from Animal, represents chewing animals.
 * @version :1
 * @author : Oran Bourak, Tomer Burman
 */
public abstract class Chewing_animals extends Animal{

    /**
     * ctor that calls Animal ctor
     * @param name - name of the Animal
     * @param location - location of the animal
     */
    public Chewing_animals(String name, Point location,String col,int size){
        super(name,location,col,size);
    }


    /**
     * abstract method to chew
     */
    public abstract void chew();
    /**
     * makeSound - makes a chew sound depends on animal.
     */
    @Override
    public void makeSound() {
        this.chew();
    }

}
