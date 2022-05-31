package graphics;

/** IAnimalBehavior -
 * @version : 1
 * @author : Oran Bourak, Tomer Burman
 *All animal's behavior methods
 *All animals are impliments this interface
 */
public interface IAnimalBehavior {
    public String getAnimalName();
    public int getSize();
    public void eatInc();
    public int getEatCount();
    public boolean getChanges();
    public void setChanges(boolean state);
    public void setSuspended();
    public void setResumed();

}
