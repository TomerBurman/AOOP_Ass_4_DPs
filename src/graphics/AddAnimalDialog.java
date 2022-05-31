package graphics;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import animals.*;
import mobility.Point;

/**AddAnimalDialog - JDialog window, creates a dialog window to create an Animal
 * num_of_Animals - static indicator field to know how many animals are created. (10 animals maximum.)
 * contains 2 panels : Animal_fields and SelectAnimal.
 * contains JButton for creation.
 *
 * @version : 1
 * @author : Tomer Burman, Oran Bourak
 */
public class AddAnimalDialog extends JDialog {
    /**
     * num_of_Animals - number of animals in the system.
     * SelectAnimal - Select panel for animals, displays animal selection with combo box and displays picture.
     * Animal_fields - Fields panel for animal fields, contains 6 JLabels and 6 JTexts.
     * create_animal - Button that creates an animal
     */
    private volatile static AddAnimalDialog dialog;
    private static Animal_fields field;
    private static SelectAnimal select;
    private String name,col; // common
    private int horSpeed,verSpeed;

    private double changing_field; // giraffe and elephant
    private int age,size; // turtle
    private Point location; // location
    private JButton create_animal = new JButton("Create");

    /**
     * makeInstance - singleton pattern - checks if theres an instance of the same class.
     *  if there is no instance creates a new instance.
     * @return -returns instance
     */
    public static AddAnimalDialog makeInstance(){
        if(dialog == null){
            synchronized (AddAnimalDialog.class){
                if(dialog == null)
                    dialog = new AddAnimalDialog();
            }
        }
        return dialog;
    }
    /**
     * AddAnimalDialog ctor, Initiates a dialog, adds 2 Panels and 1 Button.
     */
    private AddAnimalDialog() {
        this.setTitle("Add animal");
        this.setLayout(new BorderLayout());

        create_animal.addActionListener(new ActionListener() {
            /**
             * actionPerformed - when create_animal button is pressed, extracts data from using buildFields(),
             * and initiates wanted animal with ZooPanel addAnimal static method. name field must be filled. else a JOptionPane
             * is opened with an error message.
             * @param e - event - create_animal was pressed.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                buildFields();
                if(name.equals("")) {
                    JOptionPane.showMessageDialog(create_animal, "Animal must have a name ! ", "Error message", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Animal animalToAdd=null;
                switch (select.animal_choice.getSelectedItem().toString()) {
                    case "Lion" -> animalToAdd = new Lion(name, location, col, size);
                    case "Bear" -> animalToAdd = new Bear(name, location, col, size);
                    case "Giraffe" -> animalToAdd = new Giraffe(name, location, col, size, changing_field);
                    case "Turtle" -> animalToAdd = new Turtle(name, location, col, size, age);
                    case "Elephant" -> animalToAdd = new Elephant(name, location, col, size, changing_field);

                }
                if(animalToAdd != null) {
                    animalToAdd.setSpeed(horSpeed,verSpeed);
                    ZooPanel.addAnimal(animalToAdd);
                }
            }
        });
        field = new Animal_fields();
        select = new SelectAnimal();
        this.add(select, BorderLayout.CENTER); // adding selection panel - contains photo.
        this.add(field, BorderLayout.EAST); // adding field panel
        this.add(create_animal,BorderLayout.SOUTH);// adding Create button.
        field.setVisible(false);
        this.setSize(400, 400);
        this.addWindowListener(new WindowAdapter() {
            @Override
            /**
             * releases resources after AddAnimalDialog was closed.
             */
            public void windowClosed(WindowEvent e) {
                dialog = null;
                field = null;
                select = null;

            }
        });
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);// focuses on Dialog.
        this.setVisible(true);


    }

    /**
     * buildFields - extracts data from animal fields if name is invalid returns doing nothing.
     * initiates animals name,location,size color and changing field if necessary if any of the inputs is invalid default will
     * be applied.
     */
    private void buildFields(){
            name = field.name_f.getText();
            if(name.equals(""))
                return;
        try {//Location
            int x = Integer.parseInt(field.location_fx.getText());
            int y = Integer.parseInt(field.location_fy.getText());
            location = new Point(x,y);
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(field,"Animal location is invalid.\n Location set to default.","Error",JOptionPane.ERROR_MESSAGE);
            location = new Point(-1,-1);
        }
        if(field.changing_f.isVisible()) {
            if (field.changing_f.getText().equals("Age")) {//in case of a Turtle.
                try {
                    changing_field = Integer.parseInt(field.changing_f.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(field,"Animal "+ field.changing_param.getText()+ " is invalid.\n"+field.changing_param.getText() + " Set to default.","Error",JOptionPane.ERROR_MESSAGE);
                    changing_field = -1; // setting age to out of range.
                }
            } else//Giraffe or Elephant.
                try {
                    changing_field = Double.parseDouble(field.changing_f.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(field,"Animal "+ field.changing_param.getText()+ " is invalid.\n"+field.changing_param.getText() + " Set to default.","Error",JOptionPane.ERROR_MESSAGE);
                    changing_field = -1; // setting changing field to out of range
                }
        }
        try {
            size = Integer.parseInt(field.size_f.getText());
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(field,"Animal "+ field.size.getText()+ " is invalid.\n"+field.size.getText() + " Set to default.","Error",JOptionPane.ERROR_MESSAGE);
            size = -1; // setting age to out of range.
        }
        try{
            horSpeed = Integer.parseInt(field.horizontal_f.getText());
            if(!Animal.validSpeed(horSpeed))
                throw new NumberFormatException();

        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(field,"Animal " + field.horizontal_speed.getText() + " is invalid\n" + field.horizontal_speed.getText() + " Set to default ","Error",JOptionPane.ERROR_MESSAGE);
            horSpeed = 1;
        }
        try{
            verSpeed = Integer.parseInt(field.vertical_f.getText());
            if(!Animal.validSpeed(verSpeed)) {
                throw new NumberFormatException();
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(field,"Animal " + field.vertical_speed.getText() + " is invalid\n" + field.vertical_speed.getText() + " Set to default ","Error",JOptionPane.ERROR_MESSAGE);
            verSpeed = 1;

        }
        col = (String)field.color.getSelectedItem();
    }

    /**
     * Animal_fields - extends JPanel.
     * opens a JPanel displaying Select phase. if an animal was selected from the JComboBox, animal fields will be visible.
     *
     * @author : Tomer Burman, Oran Bourak
     * @version : 1
     */
    private static class Animal_fields extends JPanel {
        /**
         * name - Name label
         * name_f - Text field for name
         * location_x - X coordinate label.
         * location_y - y coordinate label
         * location_fx - X coordinate text field
         * location_fy - Y coordinate text field
         * size - size label.
         * size_f - size text field
         * vertical_speed, horizontal_speed - labels for vertical speed and horizontal speed
         * vertical_f, horizontal_f - text fields for vertical speed and horizontal speed.
         * colors - Array of available colors
         * color - Combobox of colors.
         * changing_param - changing field of the animal ( Age,Neck length, Trunk length)
         * changing_f changing text field.
         * fields - array of available changing fields.
         */
        private final  JLabel name = new JLabel("Name : ");
        private final  JTextField name_f = new JTextField();
        private final  JLabel location_x = new JLabel("X-axis location : ");
        private final  JTextField location_fx = new JTextField();
        private final  JLabel location_y = new JLabel("Y-axis location : ");
        private final  JTextField location_fy = new JTextField();
        private final  JLabel size = new JLabel("Size : ");
        private final  JTextField size_f = new JTextField();
        private final  JLabel vertical_speed = new JLabel("Vertical speed : ");
        private final  JTextField vertical_f = new JTextField();
        private final  JLabel horizontal_speed = new JLabel("Horizontal speed : ");
        private final  JTextField horizontal_f = new JTextField();
        private final String[] colors = {"Natural","Blue","Red"};
        private final  JComboBox<String> color = new JComboBox<>(colors);
        private final  JLabel changing_param = new JLabel(); // field that varies from animal to animal
        private final  JTextField changing_f = new JTextField();
        private final  String[] fields = {"", "", "Neck length", "Age", "Trunk length"};

        /**
         * Animal_fields - ctor. sets Layout to GridLayout and adds labels/text fields
         * bottom panel in AddAnimalDialog
         */
        public Animal_fields() {
            this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Parameters:"), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            this.setLayout(new GridLayout(0, 2));
            this.add(name);
            this.add(name_f);
            this.add(location_x);
            this.add(location_fx);
            this.add(location_y);
            this.add(location_fy);
            this.add(size);
            this.add(size_f);
            this.add(vertical_speed);
            this.add(vertical_f);
            this.add(horizontal_speed);
            this.add(horizontal_f);
            this.add(changing_param);
            this.add(changing_f);
            this.add(color);
            color.setBackground(Color.WHITE);
        }
    }

    /**
     * SelectAnimal - Select panel, is the top panel in AddAnimalDialog
     * built of JComboBox to represent animal selection.
     * @author : Oran Bourak, Tomer Burman
     * @version : 1
     */
    private static class SelectAnimal extends JPanel implements ActionListener {
        /**
         * animal_choice - JComboBox of strings that represent animal instances.
         * animals_choices -2d array of strings. each index represents an animal instance in first place
         * and the file name in 2nd place.
         * img - animals Icon image.
         */
        private final JComboBox<String> animal_choice;

        private final String[][] animals_choices = {{"Lion", "Lion.png"}, {"Bear", "Bear.png"}, {"Giraffe", "Giraffe.png"}, {"Turtle", "Turtle.png"}, {"Elephant", "Elephant.png"}};
        private Image img;

        /**
         * SelectAnimal - ctor. sets BorderLayout, adds the animals class name to the JComboBox
         * and adds the JComboBox to the panel.
         */
        SelectAnimal() {
            this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Select animal"), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            this.setLayout(new BorderLayout());
            animal_choice = new JComboBox<String>(); // All enum constants.
            for (String[] name : animals_choices) // taking animal names.
                animal_choice.addItem(name[0]);
            animal_choice.addActionListener(this);
            this.add(animal_choice, BorderLayout.NORTH);
        }

        /**
         * paintComponent - uses Graphics2D to draw the icon Image at selection panel.
         * @param g
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D gr = (Graphics2D) g;
            gr.drawImage(img, 20, 50, this.getWidth() - 30, this.getHeight() - 60, null);

        }

        /**
         * actionPerformed - invoked when an animal was selected out of selection ComboBox
         * sets horizontal speed and vertical speed to 1, sets them to disabled for editing.
         * checks if the animal has another field, if not sets visible to false
         * and finally sets visible of field panel to true.
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            clearFields();
            int index = animal_choice.getSelectedIndex();
            try {
                img = ImageIO.read((new File(System.getProperty("user.dir") +"\\src\\photos\\" + animal_choice.getSelectedItem() + ".png")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            field.changing_f.setVisible(!field.fields[index].equals("")); // if not lion changing field is visible.
            field.changing_param.setText(field.fields[index]);
            field.setVisible(true);
            this.repaint();

        }

        /**
         * clearFields - sets all text fields to empty string.
         */
        private  void  clearFields(){
            field.name_f.setText("");
            field.location_fx.setText("");
            field.location_fy.setText("");
            field.color.setSelectedIndex(0);
            field.size_f.setText("");
            field.horizontal_f.setText("");
            field.vertical_f.setText("");
        }




    }

}