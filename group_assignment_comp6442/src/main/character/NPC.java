package main.character;


import main.goods.Drug;
import main.character.Inventory;

public class NPC extends Characters {
    int id;

    public NPC(String name, int id) {
        super(name);
        this.id = id;
        this.inventory = new Inventory(9999);
    }
    public int sell(String item){
        return Drug.valueOf(item).getSellingPrice();
    }

}
