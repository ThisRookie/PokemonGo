package main.character;

import main.goods.Drug;

public class Player extends Characters {

    public Player(String name) {
        super(name);
        this.inventory = new Inventory(0);
    }
//item must as same as the enum drug name
    public String sell(NPC npc, String item){
        Integer inveNum = this.inventory.getInventoryMap().get(item);
        if(inveNum > 0) {
            this.inventory.getInventoryMap().put(item, inveNum-1);
            int gold = this.inventory.getGold();
            this.inventory.setGold(gold - Drug.valueOf(item).getPurchasingPrice());
            return "Sell successfully!";
        }
        return "sorry, you don't have this item!";
    }

    public String buy(NPC npc, String item) {
        Inventory player_inventory = this.inventory;
        Inventory npc_inventory = npc.inventory;

        if(npc_inventory.getInventoryMap().get(item)<1||npc_inventory.getInventoryMap().get(item)==null){ //npc doesnt have this item
            return npc.name + ": i don't have what you are looking for! :( \n";

        } else if(npc_inventory.getInventoryMap().get(item)>0){ // npc has this item
            int priceOfItem = Drug.valueOf(item).getPurchasingPrice();

            if(player_inventory.getGold()>=priceOfItem){ //player has enough gold
                // update npc' inventory
                int quantityOfItem = npc_inventory.getInventoryMap().get(item);
                npc_inventory.getInventoryMap().put(item,quantityOfItem - 1);

                //update player' inventory
                quantityOfItem = player_inventory.getInventoryMap().get(item);
                player_inventory.getInventoryMap().put(item,quantityOfItem-1);
                player_inventory.setGold(player_inventory.getGold() - priceOfItem);

                return npc.name + ": pleasure doing business with you :) \n";

            } else { // player doesnt have enough gold
                return npc.name + ": come back when you got enough money! :( \n";

            }
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(" 3");
    }
}
