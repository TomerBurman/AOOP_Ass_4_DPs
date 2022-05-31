/**
 * Giraffe - class that represent Giraffe obj.
 * Extends Chewing_animals
 * @version : 1
 * @author : Tomer Burman, Oran Bourak
 */
package animals;
import diet.Herbivore;
import mobility.*;
import privateutil.Chewing_animals;

/**
 * Giraffe - extends Chewing animals
 * @version  - 1
 * @author - Tomer Burman, Oran Bourak.
 */
public class Giraffe extends Chewing_animals {
    private double neckLength; // neck length
    private double weightFactor = 2.2;
    private final static double neck_Length_min = 1, neck_Length_max = 2.5, defaultLength = 1.5; // neck length boundries.

    /**
     * Giraffe ctor - initiates base class and sets weight to default(450), if neckLength
     * is
     *
     * @param name     animal name
     * @param location animal starting location
     */
    public Giraffe(String name, Point location, String color, int size, double length) {
        super(name, location, color, size);
        this.setWeight(getSize() * weightFactor);
        if (!this.setNeckLength(length))
            this.setNeckLength(defaultLength);
        this.setDiet(new Herbivore());
        this.loadImages("grf_" + color_choice.get(color) + "_");

    }

    /**
     * Giraffe ctor - uses ctor that recieves name and point with default point of (50,0).
     *
     * @param name animal name
     */
    public Giraffe(String name, Point location) {
        this(name, location, default_color, default_size, defaultLength);

    }

    /**
     * ctor that receives only name
     *
     * @param name animal name
     */
    public Giraffe(String name) {
        this(name, new Point(50, 0), default_color, default_size, defaultLength);
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
     * setNeckLength - sets neckLength to length received if it's in bounds. (1 to 2.5 meters).
     *
     * @param length length recieved
     * @return true if in boundries, false otherwise.
     */
    public boolean setNeckLength(double length) {
        if (length >= neck_Length_min && length <= neck_Length_max) {
            this.neckLength = length;
            return true;
        }
        return false;
    }

    /**
     * Get Neck Length Method
     *
     * @return neckLength of giraffe.
     */
    public double getNeckLength() {
        return neckLength;
    }

    /**
     * uses Animal toString.
     *
     * @return Animal representation
     */
    public String toString() {
        return super.toString();
    }


    /**
     * Giraffe chew .
     * uses messageUtility.
     */
    public void chew() {
        //TODO mat need to be deleted
    }


    /**
     * getDefaultLocation - returns default location
     *
     * @return point
     */
    public Point getDefaultLocation() {
        return new Point(50, 0);
    }
}
