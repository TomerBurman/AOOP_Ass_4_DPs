package mobility;

/**Ilocateable - interface that represents functionality of objects space.
 * EFoodType - represents types of existing foods.
 * @version: 1
 * @author : Oran Bourak,Tomer Burman.
 */
public interface ILocatable {
    /**
     *
     * @return location of the object represented by (x,y) coordinate.
     */
    Point getLocation();

    /**
     *
     * @return True if Point is set and false otherwise.
     */
    boolean setLocation(Point p);
}
