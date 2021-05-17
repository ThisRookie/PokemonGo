package main.goods;

import main.character.Pokemon;
import main.pokemonAttribute.Situation;

public enum Drug {

//    BLUE(1,2),
//    RED(1, 2),
    Potion       (1,2),
    Super_Potion (1,2),
    Hyper_Potion (1,2),
    Max_Potion   (1,2),

    Antidote     (1,2),
    Burn_Heal    (1,2),
    Ice_Heal     (1,2),
    Awakening    (1,2),
    Paralyze_Heal(1,2),
    Full_Heal    (1,2),

    Revive       (1,2),
    MAX_Revive   (1,2);


    private int sellingPrice;
    private int purchasingPrice;

    Drug(int sellingPrice, int purchasingPrice) {
        this.sellingPrice = sellingPrice;
        this.purchasingPrice = purchasingPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getPurchasingPrice() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(int purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }

}





