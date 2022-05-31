package mobility;


/**
 * Point - defines (x,y) coordinate on a 2-dim grid.
 * @version : 1
 * @author : Tomer Burman, Oran Bourak
 */
public class Point {
    /**
     * x - value of x coordinate
     * y - value of y coordinate
     * max_x, min_x, max_y, min_y - defines ranges for (x,y) coordinate.
     */
    private int x; // values between 0-800
    private int y; //values between 0-600
    final static int max_x = 800, max_y = 600;
    final static int min_x = 0, min_y = 0;

    /**
     * Point constructor - recieves (x,y) coordinate and creates a Point at the same coordinate.
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public Point(int x,int y){
        this.setX(x);
        this.setY(y);

    }

    /**
     * default ctor that sets the point to (0,0).
     */
    public Point(){
        this(0,0);
    }

    /**
     * setX - recieves int param and assigns it to X coordinate
     * @param x - x coordinate to set
     * @return boolean
     */
    public boolean setX(int x) {
        this.x = x;
        return true;
    }

    /**
     * setY - receives int param and assigns it to Y coordinate.
     * @param y - y coordinate to set
     * @return boolean
     */
    public boolean setY(int y){
            this.y = y;
            return true;
    }

    /**
     * checkBoundaries - checks if a point is valid. (Between 0-800 on X axis and 0-600 on Y axis included.
     * @param pointToCheck - point that is checked.
     * @return true if valid false otherwise.
     */
    public static boolean checkBoundaries(Point pointToCheck) {
        return (pointToCheck.getX() >= min_x && pointToCheck.getX() <= max_x && pointToCheck.getY() >= min_y && pointToCheck.getY() <= max_y);
    }


    /**
     * getX - returns value of X coordinate
     * @return int
     */
    public int getX(){return this.x;}

    /**
     * getY - returns value of Y coordinate
     * @return int
     */
    public int getY(){return this.y;}

    /**
     *
     * @return String e.g - (5,6)
     */
    @Override
    public String toString(){
        return "("+ this.getX() + "," + this.getY() + ")";
    }




}
