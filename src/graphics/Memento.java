package graphics;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Memento {
    private ArrayList<IAnimalInterface> animal_list = new ArrayList<>();
    private BlockingQueue<IAnimalInterface> animal_queue = new ArrayBlockingQueue<>(5);





    public Memento(ArrayList<IAnimalInterface> list,BlockingQueue<IAnimalInterface> animal_queue){
        try {
            for(IAnimalInterface animal : list)
                animal_list.add((IAnimalInterface) animal.getAnimal().clone());
            for(IAnimalInterface animal : animal_queue)
                this.animal_queue.add((IAnimalInterface) animal.getAnimal().clone());

        }
        catch(ClassCastException e){
            JOptionPane.showMessageDialog(ZooPanel.getDraw_panel(),"Something went wrong","Error",JOptionPane.ERROR_MESSAGE);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<IAnimalInterface> getStateOfAnimal(){
        return animal_list;
    }

    public BlockingQueue<IAnimalInterface> getStateOfWaiting() {
        return animal_queue;
    }


}
