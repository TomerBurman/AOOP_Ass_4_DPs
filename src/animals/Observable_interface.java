package animals;

import graphics.Observer_interface;

public interface Observable_interface {

    void update();
    void subscribe(Observer_interface observer);
}
