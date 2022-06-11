package graphics;

import animals.Blue_Animal_Decorator;
import animals.Natural_Animal_Decorator;
import animals.Red_Animal_Decorator;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static javax.swing.JOptionPane.showOptionDialog;

public class ChangeColorDialog implements ActionListener {
    private JComboBox<IAnimalInterface> animals_list;
    private JButton natural;
    private JButton red;
    private JButton blue;
    private IAnimalInterface animal_to_change;
    private int animal_Index = 0;


    public ChangeColorDialog(){
        animals_list = new JComboBox<IAnimalInterface>(ZooPanel.getAnimalList().toArray(new IAnimalInterface[0]));
        natural = new JButton("Natural");
        red = new JButton("Red");
        blue = new JButton("Blue");
        animals_list.setSelectedIndex(0);
        animal_to_change = animals_list.getItemAt(0);

        Object[] options = {animals_list,natural,red,blue};
        natural.addActionListener(this);
        red.addActionListener(this);
        blue.addActionListener(this);
        animals_list.addActionListener(this);
        showOptionDialog(ZooPanel.getDraw_panel(),"Choose one","Change Animal Color",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[2]);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(natural)) {
            IAnimalInterface animal = new Natural_Animal_Decorator(animal_to_change.getAnimal());
            ZooPanel.getAnimalList().set(animal_Index, animal);
        }
        else if(e.getSource().equals(red)) {
            IAnimalInterface animal = (new Red_Animal_Decorator(animal_to_change.getAnimal()));
            ZooPanel.getAnimalList().set(animal_Index, animal);
        }
        else if(e.getSource().equals(blue)) {
            IAnimalInterface animal = new Blue_Animal_Decorator(animal_to_change.getAnimal());
            ZooPanel.getAnimalList().set(animal_Index, animal);
        }
        else if(e.getSource().equals(animals_list)){
           animal_to_change = ((IAnimalInterface) Objects.requireNonNull(animals_list.getSelectedItem()));
           animal_Index = animals_list.getSelectedIndex();
        }
    }
}
