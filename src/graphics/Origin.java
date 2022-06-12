package graphics;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class Origin {
    private ArrayList<IAnimalInterface> animal_list = ZooPanel.getAnimalList();
    private BlockingQueue<IAnimalInterface> animal_queue = ZooPanel.getAnimalQueue();

    public Memento save(){
        Memento m = new Memento(animal_list,animal_queue);
        return m;
    }

    public void restore(Memento m) {
        ZooPanel.setAnimalList(m.getStateOfAnimal());
        ZooPanel.setWaitingQueue(m.getStateOfWaiting());
        animal_list = ZooPanel.getAnimalList();

    }
}
