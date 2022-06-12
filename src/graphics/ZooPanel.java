package graphics;

import animals.Animal;
import food.IEdible;
import mobility.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * ZooPanel - extends JPanel implements Runnable.
 * built of 2 panels -> Button Panel at SOUTH and draw_panel at CENTER.
 * @author : Oran Bourak, Tomer Burman
 * @version :1
 */
public class ZooPanel extends JPanel implements Runnable {
    /**
     * num_of_Animals - number of animals in the zoo
     * max_animals - max animals allowed.
     * dialog - JDialog incase of AddAnimalDialog or MoveAnimalDialog
     * animal_list - array list of animals.
     * draw_panel - drawing panel at the Center of main panel
     * button_panel - button panel at the Bottom of main panel
     * backGround - incase of Image background.
     * food - food instance.
     *
     */
    private static int num_of_Animals = 0; // number of animals in zoo
    private static final int max_animals = 10;
    private static final int corePool = 10;
    private static final int maximumPoolSize = 10;
    private static final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(5);
    private static final BlockingQueue<IAnimalInterface> waiting_queue = new ArrayBlockingQueue<>(5);


    private static ExecutorService executor;
    private static JDialog dialog;
    private static final ArrayList<IAnimalInterface> animal_list = new ArrayList<>();
    private static drawPanel draw_panel;
    private static ButtonPanel button_panel;
    private static ImageIcon backGround = null;
    private static IEdible food=null;
    private Thread controller;


    private volatile static ZooPanel instance=null;

    /**
     * setFoodType - sets the food to received food.
     * @param f - food received. (Lettuce,Cabbage or Meat)
     */
    public static void setFoodType(IEdible f){ food = f; }


    public static ZooPanel makeInstance(){
        if(instance == null){
            synchronized (ZooPanel.class){
                if(instance == null)
                    instance = new ZooPanel();
            }
        }
        return instance;
    }

    /**
     * ZooPanel ctor - sets layout to BorderLayout, initiates draw_panel and button_panel.
     * adds button panel to south and draw panel to center.
     */
    private ZooPanel(){
        draw_panel = new drawPanel();
        this.setLayout(new BorderLayout());
        button_panel = new ButtonPanel();
        this.add(button_panel,BorderLayout.SOUTH); // button panel
        this.add(draw_panel,BorderLayout.CENTER); // painting panel
        this.controller = new Thread(this);
        controller.setName("ZooPanel");
        executor = new ThreadPoolExecutor(corePool,maximumPoolSize,5,TimeUnit.SECONDS,queue,new RejectionHandler());


    }

    /**
     * manageZoo - keeps track on if there are  any changes in our zoo
     * if there are, handles the changes and calls draw_panel repaint.
     */
    public void manageZoo() {
            if (animal_list.size() != num_of_Animals) { // in cases: 1) Clear animal button is pressed , 2)one of the animals has been eaten
                num_of_Animals = animal_list.size();
                draw_panel.repaint();
            }
            if (isChanged()) { // in case of movement
                draw_panel.repaint();
            }

            int i = 0;
            Animal animal1, animal2;
            while (i < num_of_Animals) {// checks all animals with all animals, if one can eat the other.
                animal1 = animal_list.get(i).getAnimal();
                for (int j = 0; j < num_of_Animals; j++) {
                    animal2 = animal_list.get(j).getAnimal();
                    if (animal1.getWeight() > 2 * animal2.getWeight() && animal1.calcDistance(animal2.getLocation()) <= animal2.getSize() && animal1 != animal2)
                        if (animal1.eat(animal2)) {
                            synchronized (animal2) {
                                animal_list.remove(j);
                                if (waiting_queue.size() != 0)
                                    animal_list.add(waiting_queue.poll());

                                        animal2.setThreadExit(true);

                                        animal2.notifyAll();


                            }
                            draw_panel.repaint();
                            i = 0;
                            break;
                        }
                }
                if (animal_list.size() != num_of_Animals)// in case animal has been eaten.
                    num_of_Animals--;
                else
                    i++;
            }
            if (food != null) { // if food was placed on the screen
                for (IAnimalInterface animal_dec : animal_list) {
                    Animal animal = animal_dec.getAnimal();
                    synchronized (animal) {
                        if (animal.getDiet().canEat(food.getFoodType())) {
                            animal.setExistingFood(true);
                            if (animal.calcDistance(new Point(draw_panel.getWidth() / 2, draw_panel.getHeight() / 2 + 12)) <= animal.getEAT_DISTANCE()) {
                                animal.eat(food);
                                for (IAnimalInterface temp_animal_dec : animal_list) {
                                    Animal temp_animal = temp_animal_dec.getAnimal();
                                    temp_animal.setExistingFood(false);
                                }
                                food = null;
                                break;
                            }
                        }
                    }
                }
            }

            if (food != null)//if there's food
                draw_panel.repaint();
    }


    /**
     * isChanged - checks if any animal in animal list moved, if so returns true else returns false.
     * @return boolean
     */
    public static boolean isChanged() {
        boolean flag = false;
        for (IAnimalInterface animal : animal_list){
            if (animal.getChanges()) {
                synchronized (animal.getAnimal()) {
                    animal.setChanges(false);
                    flag = true;
                    animal.getAnimal().notifyAll();
                }
            }
        }
        return flag;
    }

    /**
     * getDraw_panel - returns draw_panel
     * @return drawPanel
     */
    public static drawPanel getDraw_panel(){
        return draw_panel;
    }

    /**
     * setBack - sets backGround to null, and then sets draw_panel backGround with received color
     * @param c background color wanted.
     */
    public static void setBack(Color c){
        backGround = null;
        draw_panel.setBackground(c);
    }

    /**
     * setBack - in case user wants to upload an image to the background, creating a new ImageIcon and calling draw_panel
     * repaint
     * @param file - file name
     */
    public static void setBack(String file){
        backGround = new ImageIcon(file);
        draw_panel.repaint();
    }

    /**
     * getAnimalList - returns animal_list
     * @return ArrayList<Animal>
     */
   public static ArrayList<IAnimalInterface> getAnimalList(){return animal_list;}

    /**
     * Get Controller thread
     * @return controller thread
     */
    public Thread getController() {
        return controller;
    }

    /**
     * ButtonPanel - extends JPanel, represents a Button panel that has
     * buttons with action listeners. - what the user can do with our system.
     * @author : Tomer Burman , Oran Bourak
     * @version : 1
     */
    private class ButtonPanel extends JPanel{
        /**
         * add_Animal - Add animal button
         * move_Animal - Move animal button
         * clear - Clear button.
         * food - Food button
         * info - Info button
         * exit - Exit button
         */
        private JButton add_Animal = new JButton("Add Animal"); //getcontent
        private JButton sleep = new JButton("Sleep");
        private JButton wake_up = new JButton("Wake up");
        private JButton clear = new JButton("Clear");
        private JButton food = new JButton("Food");
        private JButton animal_color = new JButton("Change Color");
        private JButton info = new JButton("Info");
        private JButton exit = new JButton("Exit");


        /**
         * ButtonPanel ctor - sets Layout with Gridlayout that has 1 row, sets backGround to blue
         * buttons are seperated between themselves.
         */
        ButtonPanel(){
            this.setPreferredSize(new Dimension(800,30));
            this.setBackground(new Color(0,0,255));
            animalButton();
            GridLayout lay = new GridLayout(1,0);
            this.setLayout(lay);
            lay.setHgap(5);
            this.add(add_Animal);
            this.add(sleep);
            this.add(wake_up);
            this.add(clear);
            this.add(food);
            this.add(animal_color);
            this.add(info);
            this.add(exit);


        }

        /**
         * implements ActionListeners to all buttons.
         * add_Animal - opens AddAnimalDialog if number of animals is less than maximum animals allowed.
         * move_Animal - opens MoveAnimalDialog.
         * info - opens JOptionPane message including JTable with all animals info.
         * clear - deletes animals from the list and uses ManageZoo to call repaint.
         * exit - Exits the system.
         *
         * @return boolean
         */
        public boolean animalButton(){
            this.add_Animal.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e){
                        if(num_of_Animals < max_animals + 5) // queue length is 5
                            dialog = AddAnimalDialog.makeInstance();
                        else {
                            JOptionPane.showMessageDialog(button_panel,"Can not add more then 15 animals. ","Error message",JOptionPane.WARNING_MESSAGE
                            );

                        }
                }
            });
            this.sleep.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(num_of_Animals > 0 ){
                        for(IAnimalInterface animal : animal_list)
                            animal.setSuspended();
                    }
                    else
                        JOptionPane.showMessageDialog(dialog,"No animals available","Error message",JOptionPane.WARNING_MESSAGE);

                }
            });
            this.wake_up.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (num_of_Animals > 0) {
                        for (IAnimalInterface animal : animal_list) {
                            synchronized (animal.getAnimal()) {
                                animal.getAnimal().setResumed();
                                animal.getAnimal().notifyAll();
                            }
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(dialog,"No animals available","Error message",JOptionPane.WARNING_MESSAGE);
                }
            });
            this.info.addActionListener(new ActionListener() {
                /**
                 * if info button was pressed and animal list is greater then 1, JOptionPane with JTable will pop.
                 * @param e - info pressed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(animal_list.size() != 0) {
                        infoTable info = new infoTable();
                        JTable infoTable = new JTable(info.getAnimalsInfo(), info.getCols());
                        JOptionPane.showMessageDialog(button_panel, new JScrollPane(infoTable));
                    }
                    else{
                        JOptionPane.showMessageDialog(button_panel,"No animals to show.","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            this.clear.addActionListener(new ActionListener() {
                /**
                 * if clear button was pressed, all animals are deleted. Must have at-least 1 animal present.
                 * if not, shows JOptionPane error message
                 * @param e
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    queue.clear();
                    waiting_queue.clear();
                    if(animal_list.size() > 0) {
                        for(IAnimalInterface animal_dec : animal_list) {
                            Animal animal = animal_dec.getAnimal();
                            synchronized (animal) {
                                animal.setThreadExit(true);
                                try {
                                    animal.wait();
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                        animal_list.clear();
                        num_of_Animals = 0;
                        manageZoo();
                    }
                    else
                        JOptionPane.showMessageDialog(button_panel,"Animals do not exist.","Error",JOptionPane.ERROR_MESSAGE);

                }
            });
            this.food.addActionListener(new ActionListener() {
                /**
                 * if food was pressed, FoodDialog is created and manageZoo is called.
                 * @param e
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    FoodDialog food = new FoodDialog();
                    manageZoo();
                }
            });

            this.animal_color.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(animal_list.size() >0) {
                        new ChangeColorDialog();
                    }
                    else
                        JOptionPane.showMessageDialog(button_panel,"Animals do not exist.","Error",JOptionPane.ERROR_MESSAGE);
                }
            });

            this.exit.addActionListener(new ActionListener() {
                /**
                 * exit button was pressed - JOptionPane message will pop up and exit the system.
                 * @param e
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(new JFrame(), "Goodbye");
                    System.exit(0);
                }
            });
            return true;
        }


    }

    /**
     * drawPanel - extends JPanel. represents drawing panel - all instances in our system are drawn
     * by drawPanel
     * @author : Oran Bourak, Tomer Burman
     * @version : 1
     */
    private static class drawPanel extends JPanel {

        /**
         * sets PreferredSize to 800 on X axis and 600 on Y axis.
         */
        public drawPanel(){
            this.setPreferredSize(new Dimension(800,600));

        }

        /**
         * paintComponent - draws all animals, if food is not null draws current food, if backGround is not null
         * draws back ground chosen.
         * @param g
         */
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            if(backGround != null)
                g.drawImage(backGround.getImage(),0,0,draw_panel.getWidth(),draw_panel.getHeight(),null);
            if(food != null)
                ((IDrawable)food).drawObject(g);
            synchronized (animal_list) {
                for (IAnimalInterface animal : animal_list)
                    animal.drawObject(g);
            }
        }

    }


    @Override
    public void run() {
        while (true) {
            manageZoo();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * addAnimal - adds animal to list if num_of_Animals is smaller then max animals allowed, and calls manageZoo
     * if  num_of_Animals is not smaller JOPtionPane error message will pop up.
     * @param animal
     */
    public static void addAnimal(Animal animal){
        if(num_of_Animals < max_animals) {
                animal_list.add(animal);
                for (IAnimalInterface animal_to_check_dec : animal_list) {
                    Animal animal_to_check = animal_to_check_dec.getAnimal();
                    if (animal_to_check.isThreadSuspended()) {
                        animal.setSuspended();
                        break;
                    }
                }
            }
        else if(queue.size() < 5){
            waiting_queue.add(animal);
            JOptionPane.showMessageDialog(dialog,"Animal added to waiting queue","Notification",JOptionPane.INFORMATION_MESSAGE);
        }
        executor.execute(animal);
        System.out.println(executor);
    }

    /**
     * RejectionHandler - Handles excetions where Thread-pool has no available threads and queue is full.
     * opens JOptionPane with an appropriate message.
     */
    private static class RejectionHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            JOptionPane.showMessageDialog(new JPanel(),r + " Got rejected. Number of animals in the system is 15","Error",JOptionPane.ERROR_MESSAGE);
        }
    }


}
