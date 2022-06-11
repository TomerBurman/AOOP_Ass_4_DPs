/**
 * Elephant - class that represent Elephant obj.
 * Extends Chewing_animals
 * @version : 1
 * @author : Tomer Burman, Oran Bourak
 */
package animals;
import diet.Herbivore_factory;
import mobility.Point;
import privateutil.Chewing_animals;




public class Elephant extends Chewing_animals {
    private double trunkLength; // represent the elephant trunk length.
    private final static double min_trungLength = 0.5;
    private final static double maximum_trunkLength = 3;
    private final static double trunkDefaultLength = 1;
    private final double weightFactor = 10;

    /**
     * Elephant Ctor
     * @param name - elephant name.
     * @param location - elephant location.
     */
    public Elephant(String name, Point location,String col,int size, double trunkLength) {
        super(name, location,col,size);
        this.setWeight(getSize() * weightFactor);
        if (!setTrunkLength(trunkLength))
            setTrunkLength(trunkDefaultLength);
        this.setDiet(new Herbivore_factory());
        this.loadImages("elf_" + color_choice.get(col) + "_");

    }

    /**
     * Elephant Ctor that receives name and location , trunk length is set to default.
     * @param name - elephant name.
     * @param location - elephant location.
     */
    public Elephant(String name, Point location){
        this(name,location,default_color,default_size,trunkDefaultLength);
    }


    /**
     * Elephant Ctor that receives only name and set default location
     * @param name - elephant name.
     */
    public Elephant(String name) {
        this(name, new Point(50,90),default_color,default_size,trunkDefaultLength);

    }

    /**
     *Set Trunk Length
     * @param length - represent elephant trunk length
     * @return true if the length is valid , otherwise false.
     */
    public boolean setTrunkLength(double length){
        if(length >= min_trungLength && length <= maximum_trunkLength){
            this.trunkLength = length;
            return true;
        }
        return false;
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
     * uses animal toString.
     * @return
     */
    @Override
    public String toString(){
        return super.toString();
    }

    /**
     * Chew method
     * Using after animal eats
     * Uses messageUtility
     */
    @Override
    public void chew() {
    }

    /**
     * Get trunk length
     * @return animal trunk length
     */
    public double getTrunkLength() {
        return trunkLength;
    }
    /**
     * getDefaultLocation - returns default location
     * @return point
     */
    public Point getDefaultLocation(){
        return new Point(50,90);
    }

}
