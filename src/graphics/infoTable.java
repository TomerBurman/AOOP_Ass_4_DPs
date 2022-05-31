package graphics;
import animals.Animal;

/**
 * infoTable class - Extracting all animals' information for InfoTAble
 */
public class infoTable{
    private static Object[] colsParams = {"Animal","Name","Color","Weight","Hor. speed","Ver. speed","Eat counter"};
    private Object[][] animalsInfo;

    /**
     * Constructor- initiate 2 dim list with all zoo animals info according to the parameters list
     */
    public infoTable(){
        animalsInfo = new Object[ZooPanel.getAnimalList().size()+1][colsParams.length];
        int i = 0;
        int total = 0;
        for(Animal animal:ZooPanel.getAnimalList()){
           animalsInfo[i][0] = animal.getClass().getSimpleName();
           animalsInfo[i][1] = animal.getAnimalName();
           animalsInfo[i][2] = animal.getColor();
           animalsInfo[i][3] = animal.getWeight();
           animalsInfo[i][4] = animal.getHorSpeed();
           animalsInfo[i][5] = animal.getVerSpeed();
           animalsInfo[i][6] = animal.getEatCount();
           i++;
           total += animal.getEatCount();
        }
        animalsInfo[i][0] = "Total";
        animalsInfo[i][6] = total;

    }

    /**
     * GetCols method
     * @return list of parameters for JTable
     */
    public Object[] getCols(){
        return colsParams;
    }

    /**
     * GetAnimalsInfo method
     * @return 2 dim Object list,each animal have the details according to the list of parameters
     */
    public Object[][] getAnimalsInfo(){
        return animalsInfo;
    }

}
