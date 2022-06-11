/**
 * Lion - class that represent Lion obj.
 * Extends Roaring_animals
 * @version : 1
 * @author : Tomer Burman, Oran Bourak
 */
package animals;
import diet.Carnivore_factory;
import food.*;
import mobility.Point;
import java.util.Random;
import privateutil.Roaring_animals;

public class Lion extends Roaring_animals {
    private final double weightFactor = 0.8;
    private int scarCount ;// how many scar's lion has


    /**
     * constructor that receives name and starting location.
     * @param name animal name
     * @param location animal starting location
     */
    public Lion(String name,Point location,String col,int size){
        super(name,location,col,size);
        super.setWeight(getSize() * weightFactor);
        this.setDiet(new Carnivore_factory()); // meat eater
        this.loadImages("lio_" + color_choice.get(col) + "_");

    }

    /**
     * Set new size for animals will animal movements or eating
     */
    @Override
    public void setNewSize() {
        int new_size=(int)(getWeight()/weightFactor);
        if(new_size < 50)
            new_size= 50;
        else if (new_size > 300)
            new_size = 300;
        setSize(new_size);
    }

    /**
     * GetWeightFactor
     * @return animal weight factor
     */
    @Override
    public double getWeightFactor() {
        return weightFactor;
    }

    /**
     * constructor that recieves only name, setting to default location of (20,0)
     * @param name animal name
     */
    public Lion(String name){
        this(name,new Point(20,0),default_color,default_size);
    }

    /**
     * adds scarCount by 1.
     * @return true.
     */
    public boolean addScarCount(){
        Random rand = new Random();
        int rand_int = rand.nextInt(2); //generating either 0/1
        if(rand_int == 1) {
            scarCount += 1;
            return true;
        }
        return false;
    }

    /**
     * Get Scar's method.
     * @return how many scar's the lion has.
     */
    public int getScarsCount(){
        return scarCount;}

    /**
     * eat - uses Animal class eat, if food is edible, there's a 50% chance to
     * get a scar.
     * @param food food to feed the animal.
     * @return returns true if edible, false otherwise.
     */
    @Override
    public boolean eat(IEdible food){
            if(super.eat(food)){
                addScarCount();
                return true;
             }
        return false;
    }

    /**
     * getScarCount - returns how many scars the lion has
     * @return int
     */
    public int getScarCount(){return scarCount;}


    public void roar(){
        //TODO mat need to be deleted

    }

    /**
     * toString - uses Animal class toString.
     * @return Animal representation.
     */

    public String toString(){
        return super.toString();
    }

    /**
     * getFoodType
     * @return EFoodType
     */
    public EFoodType getFoodType(){
        return EFoodType.NOTFOOD;
    }

    /**
     * getDefaultLocation - returns default location
     * @return point
     */
    public Point getDefaultLocation(){
        return new Point(20,0);
    }
}
