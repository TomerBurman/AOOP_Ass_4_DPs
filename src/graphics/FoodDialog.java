package graphics;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import food.Meat;
import plants.*;
import static javax.swing.JOptionPane.showOptionDialog;

/**
 *version: 1
 * author: Tomer Burman, Oran Bourak
 *
 * FoodDialog class -use for add food to animals zooPanel.
 *  open dialog with 3 food options: Meat, Cabbage,Lettuce.
 **/

public class FoodDialog implements ActionListener {
    private JButton cabbage,lettuce,meat;
    public FoodDialog(){
        cabbage = new JButton("Cabbage");
        lettuce = new JButton("Lettuce");
        meat = new JButton("Meat");

        Object[] options = {cabbage,lettuce,meat};
        cabbage.addActionListener(this);
        lettuce.addActionListener(this);
        meat.addActionListener(this);
        showOptionDialog(ZooPanel.getDraw_panel(),"Choose one","Food",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,new ImageIcon(System.getProperty("user.dir") + "\\src\\photos\\" + "food.png"),options,options[2]);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(cabbage))
            ZooPanel.setFoodType(Cabbage.makeInstance());
        else if(e.getSource().equals(lettuce))
            ZooPanel.setFoodType(Lettuce.makeInstance());
        else if(e.getSource().equals(meat))
            ZooPanel.setFoodType(Meat.makeInstance());
    }
}
