package mobility;

/**
 * Mobile - Abstract class that represents movement in (x,y) coordinate and implements Ilocateable interface.
 * @version : 1
 * @author : Oran Bourak, Tomer Burman
 */
public abstract class Mobile implements ILocatable{
    //Attributes
    /**
     * location - Current location
     * totalDistance - Distance the object has traveled(greater than zero).
     */
    private Point location; //Current location
    private double totalDistance;//Distance the object traveled (greater than zero).

    //Methods
    /**
     * Mobile constructor - creates a Point with p coordinates and setting distance traveled to 0.
     * @param p Point object represented in (x,y) coordinate.
     */

    public Mobile(Point p){

        this.setLocation(p);
        this.totalDistance = 0;
    }

    /**
     * Mobile default constructor
     * Point default constructor is invoked and a point is created at (0,0).
     */
    public Mobile(){
        this(new Point());
    }

    /**
     * increases total distance after movement.
     * @param distance double type - distance traveled. >0.
     */
    public void addTotalDistance(double distance){
        this.setTotalDistance(this.getTotalDistance() + distance); // adds given distance to old distance.
    }

    /**
     * setter for location.
     * @param location Point given.
     * @return true once location is set.
     */
    @Override
    public boolean setLocation(Point location){
        if(Point.checkBoundaries(location)) {
            this.location = location;
            return true;
        }
        this.location = this.getDefaultLocation();
        return false;
    }

    /**
     * returns location of the object that invoked method.
     * @return Point - location.
     */
    @Override
    public Point getLocation(){return location;}

    /**
     * adds to current distance distance traveled.
     * @param d - distance traveled.
     * @return false if distance is below 0, true otherwise.
     */

    public boolean setTotalDistance(double d){
        if (d<=0)
            return false;
        this.totalDistance += d;
        return true;

    }

    /**
     *getTotalDistance
     * @return total distance traveled.
     */

    public double getTotalDistance(){return totalDistance;}

    /**
     * calculates distance between two given points in (x,y) coordinates. sqrt ( (x1-x2)^2 + (y1-y2)^2))
     * @param other Point to other object.
     * @return distance between two objects.
     */
    public double calcDistance(Point other){
        /*Return the distance of the obj from received point*/
        return Math.sqrt((Math.pow(this.location.getX() - other.getX(),2) +Math.pow((this.location.getY() - other.getY()),2))); // sqrt((x_1 + x_2)^2 +(y_1 + y_2)^2)
    }

    /**
     * move - checks if Point in boundaries if so
     * Moves from one place to another
     * @param other recieved Point to travel to
     * @return distance traveled.
     */
    public double move(Point other){
        if(Point.checkBoundaries(other)) {
            double distance = this.calcDistance(other); // returns distance between 2 Points.
            this.setLocation(other); //setting location of our obj to other obj location.
            this.addTotalDistance(distance); //updating total distance traveled.
            return distance;
        }
        return 0;

    }

    /**
     *
     */
    public abstract Point getDefaultLocation();

}
