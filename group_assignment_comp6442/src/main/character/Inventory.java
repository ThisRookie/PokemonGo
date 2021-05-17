package main.character;
import main.goods.Drug;
import main.pokemonAttribute.Wild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory {

    public List<Pokemon> pokemonFight;
    public List<Pokemon> pokemonNotFight;
    private HashMap<String,Integer> inventoryMap = new HashMap<>();
    private int gold;
    private static final int MaxFightNumber = 6;

    public Inventory() {}

    public Inventory(Integer n) {
        for(Drug d: Drug.values()){
            inventoryMap.put(d.toString(), n);
        }
        gold = 10*(n+1);
    }

    public Inventory(List<Pokemon> pokemonFight, List<Pokemon> pokemonNotFight) {
        this.pokemonFight = pokemonFight;
        this.pokemonNotFight = pokemonNotFight;
    }

    public HashMap<String, Integer> getInventoryMap() {
        return inventoryMap;
    }

    public void setInventoryMap(HashMap<String, Integer> inventoryMap) {
        this.inventoryMap = inventoryMap;
    }





    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    /**
     * Initialize an empty Inventory
     * @return EmptyInventory
     * @author  Zehao Jin
     */
    public static Inventory emptyInventory(){
        Inventory output = new Inventory();
        output.pokemonFight = new ArrayList<>();
        output.pokemonNotFight = new ArrayList<>();
        // TODO:  pokeBall and Drug hashmap
        return output;
    }

    /**
     * Determine whether target pokemon is not fighting or not
     * @param inventory person's inventory
     * @param pokemon target pokemon
     * @return True if Pokemon is not for fight
     * @author  Zehao Jin
     */
    public static Boolean containsNotFightPokemon(Inventory inventory, Pokemon pokemon){
        boolean output = false;
        int index = pokemon.getIndex();
        String name = pokemon.getName();
        if (inventory.pokemonNotFight !=null && pokemon.getWild() == Wild.UnWild){
            for (Pokemon m : inventory.pokemonNotFight){
                if (m.getIndex() == index && m.getName().equals(name)) {
                    output = true;
                    break;
                }
            }
        }
        return output;
    }

    /**
     * Determine whether target pokemon is ready fighting or not
     * @param inventory person's inventory
     * @param pokemon target pokemon
     * @return True if Pokemon is ready for fight
     * @author  Zehao Jin
     */
    public static Boolean containsFightPokemon(Inventory inventory, Pokemon pokemon){
        boolean output = false;
        int index = pokemon.getIndex();
        String name = pokemon.getName();

        if (inventory.pokemonFight != null  && pokemon.getWild() == Wild.UnWild){
            for (Pokemon m : inventory.pokemonFight){
                if (m.getIndex() == index && m.getName().equals(name)) {
                    output = true;
                    break;
                }
            }
        }

        return output;
    }

    /**
     * Determine whether the inventory contains the target pokemon or not
     * @param inventory person's inventory
     * @param pokemon target pokemon
     * @return True if Pokemon is ready for fight
     * @author  Zehao Jin
     */
    public static Boolean containsPokemon(Inventory inventory, Pokemon pokemon){
        return containsFightPokemon(inventory,pokemon) || containsNotFightPokemon(inventory,pokemon);
    }

    /**
     * add target pokemon into inventory
     * if the Fighting pokemon List is not full . Pokemon will be add into Fighting pokemon List.
     * if the Fighting pokemon List is full . Pokemon will be add into Not Fighting pokemon List.
     *
     * @param inventory person's inventory
     * @param pokemon target pokemon
     * @author  Zehao Jin
     */
    public static void addPokemon(Inventory inventory, Pokemon pokemon){
        indexSetter(inventory,pokemon);

        if (fullFightPokemon(inventory)){
            inventory.pokemonNotFight.add(pokemon);
        }else{
            inventory.pokemonFight.add(pokemon);
        }
    }

    /**
     * delete target pokemon from inventory
     *
     * @param inventory person's inventory
     * @param pokemon target pokemon
     * @author  Zehao Jin
     */
    public static void deletePokemon(Inventory inventory, Pokemon pokemon){
        int index = pokemon.getIndex();
        String name = pokemon.getName();

        if (containsFightPokemon(inventory,pokemon)){
            inventory.pokemonFight.removeIf(m -> m.getName().equals(name) && m.getIndex() == index);
        } else if (containsNotFightPokemon(inventory,pokemon)){
            inventory.pokemonNotFight.removeIf(m -> m.getName().equals(name) && m.getIndex() == index);
        }else {
            System.out.println("Inventory does not contain this pokemon.");
        }
    }

    /**
     * Let the pokemon be away from fight
     *
     * @param inventory person's inventory
     * @param pokemon target pokemon
     * @author  Zehao Jin
     */
    public static void pokemonFightToNotFight(Inventory inventory, Pokemon pokemon){
        int index = pokemon.getIndex();
        String name = pokemon.getName();
        if (!containsPokemon(inventory,pokemon)){
            System.out.println("Inventory does not contain " +pokemon.toString());
        } else if (containsNotFightPokemon(inventory,pokemon)) {
            System.out.println(pokemon.toString()+" is not fighting right now.");
        }else {
            inventory.pokemonFight.removeIf(m -> m.getName().equals(name) && m.getIndex() == index);
            inventory.pokemonNotFight.add(pokemon);
        }
    }

    /**
     * Let the pokemon be ready to fight
     *
     * @param inventory person's inventory
     * @param pokemon target pokemon
     * @author  Zehao Jin
     */
    public static void pokemonNotFightToFight(Inventory inventory, Pokemon pokemon){
        int index = pokemon.getIndex();
        String name = pokemon.getName();
        if (!fullFightPokemon(inventory)){
            if (!containsPokemon(inventory,pokemon)){
                System.out.println("Inventory does not contain this pokemon.");
            } else if (containsFightPokemon(inventory,pokemon)) {
                System.out.println("This pokemon is fighting already.");
            }else {
                inventory.pokemonNotFight.removeIf(m -> m.getName().equals(name) && m.getIndex() == index);
                inventory.pokemonFight.add(pokemon);
            }
        }else {
            System.out.println("Full pokemon for fighting");
        }
    }

    /**
     * Switch a fighting Pokemon and a NotFighting Pokemon
     *
     * @param inventory person's inventory
     * @param pokemonFight fighting pokemon
     * @param pokemonNotFight  Not fighting pokemon
     * @author  Zehao Jin
     */
    public static void pokemonSwitchFight(Inventory inventory, Pokemon pokemonFight, Pokemon pokemonNotFight){
        if (!Inventory.containsPokemon(inventory,pokemonFight) || !Inventory.containsPokemon(inventory,pokemonNotFight)){
            System.out.println("There are no "+pokemonFight.getName()+pokemonFight.getIndex()+" or "+pokemonNotFight.getName()+pokemonNotFight.getIndex()+" in this inventory");
        }else if (!Inventory.containsFightPokemon(inventory,pokemonFight)) {
            System.out.println(pokemonFight.getName()+pokemonFight.getIndex()+ " is not fighting");
        }else if (!Inventory.containsNotFightPokemon(inventory,pokemonNotFight)){
            System.out.println(pokemonNotFight.getName()+pokemonNotFight.getIndex()+ " is fighting");
        }else {
            Inventory.pokemonFightToNotFight(inventory,pokemonFight);
            Inventory.pokemonNotFightToFight(inventory,pokemonNotFight);
        }
    }

    /**
     * set target pokemon's index
     * The index of pokemon got by the person
     * @param inventory person's inventory
     * @param pokemon target pokemon
     * @author  Zehao Jin
     */
    public static void indexSetter(Inventory inventory, Pokemon pokemon){
        int MaxIndex = 1;

        if (inventory.pokemonNotFight != null && inventory.pokemonFight != null){
            for(Pokemon m : inventory.pokemonNotFight){
                if (m.getName().equals(pokemon.getName())){
                    if (MaxIndex <= m.getIndex()){
                        MaxIndex = m.getIndex() +1;
                    }
                }
            }
            for(Pokemon m : inventory.pokemonFight){
                if (m.getName().equals(pokemon.getName())){
                    if (MaxIndex <= m.getIndex()){
                        MaxIndex = m.getIndex() +1;
                    }
                }
            }
        }else if (inventory.pokemonNotFight != null){
            for(Pokemon m : inventory.pokemonNotFight){
                if (m.getName().equals(pokemon.getName())){
                    if (MaxIndex <= m.getIndex()){
                        MaxIndex = m.getIndex() +1;
                    }
                }
            }
        }else if (inventory.pokemonFight != null){
            for(Pokemon m : inventory.pokemonFight){
                if (m.getName().equals(pokemon.getName())){
                    if (MaxIndex <= m.getIndex()){
                        MaxIndex = m.getIndex() +1;
                    }
                }
            }
        }
        pokemon.setIndex(MaxIndex);
    }

    /**
     * determine the fighting pokemon list is full or not
     *
     * @param inventory person's inventory
     * @author  Zehao Jin
     */
    public static Boolean fullFightPokemon(Inventory inventory){ return inventory.pokemonFight.size() >= MaxFightNumber; }

}
