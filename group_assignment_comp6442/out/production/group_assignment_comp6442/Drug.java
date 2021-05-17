import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.Arrays;

/**
 * Construct Type Drug
 *
 * @author Zehao Jin
 */
public class Drug {
    private Drug_name drug_name; // name of a drug
    private int num;  // total number of a drug

    //construct a drug
    public Drug(Drug_name drug_name, int num){
        this.drug_name = drug_name;
        this.num = num;
    }

    //setter
    public void setDrug_name(Drug_name drug_name) { this.drug_name = drug_name; }
    public void setNum(int num) { this.num = num; }

    //getter
    public Drug_name getDrug_name() { return drug_name; }
    public int getNum() { return num; }

    /**
     * Initialize an empty drug list
     * @return Drug list with all number 0
     * @author  Zehao Jin
     */
    public static Drug[] initialDrugInBag(){
        Drug_name[] names = Drug_name.names();
        Drug[] output = new Drug[Drug_name.number_names()];
        int i = 0;
        for (Drug_name a : names){
            Drug newDrug = new Drug(a,0);
            output[i] = newDrug;
            i++;
        }
        return output;
    }

    public static String toString(Drug drug){
        return "Name: " + drug.getDrug_name() + "    Number: " + drug.getNum() + "";
    }

    public static void main(String[] args) {

        Drug_name[] names = Drug_name.names();
        Drug[] output = new Drug[Drug_name.number_names()];
        int i = 0;
        for (Drug_name a : names){
            Drug newDrug = new Drug(a,0);
            output[i] = newDrug;
            i++;
        }

        System.out.println(Arrays.toString(output));
        System.out.println(Drug.toString(new Drug(names[1],0)));
    }


}


/**
 * Construct enum Drug_name
 *
 * @author Zehao Jin
 */
enum Drug_name{

    Potion,
    Super_Potion,
    Hyper_Potion,
    Max_Potion,

    Antidote,
    Burn_Heal,
    Ice_Heal,
    Awakening,
    Paralyze_Heal,
    Full_Heal,

    Revive,
    MAX_Revive;

    public static Drug_name[] names(){
        return Drug_name.values();
    }

    public static int number_names(){
        return names().length;
    }

}
