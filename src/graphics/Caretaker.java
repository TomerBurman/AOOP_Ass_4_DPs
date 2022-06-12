package graphics;

import javax.swing.*;
import java.util.ArrayList;

public class Caretaker {
    private Origin originator = new Origin();
    private ArrayList<Memento> memento_list = new ArrayList<Memento>();


    public void save(){
        Memento m = originator.save();
        if(memento_list.size() < 3) {
            memento_list.add(m);
            JOptionPane.showMessageDialog(ZooPanel.getDraw_panel(),"State saved","Save",JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(ZooPanel.getDraw_panel(),"Can not save more than 3 states","Error",JOptionPane.ERROR_MESSAGE);
    }

    public int getSizeofList(){
        return memento_list.size();
    }


    public void load(){
        if(memento_list.size() > 0){
            Memento m = memento_list.get(0);
            memento_list.remove(0);
            originator.restore(m);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(ZooPanel.getDraw_panel(),"Loaded successfully","Load",JOptionPane.INFORMATION_MESSAGE);

        }
        else
            JOptionPane.showMessageDialog(ZooPanel.getDraw_panel(),"No saved states","Error",JOptionPane.ERROR_MESSAGE);
    }

}
