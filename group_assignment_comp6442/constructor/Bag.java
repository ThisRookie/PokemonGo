
import java.util.ArrayList;
import java.util.List;

/**个数
 * Construct Class main.character.Bag
 *
 * @author Zehao Jin
 */

public class Bag {
    public List<Pokemon> pokemonFight;
    public List<Pokemon> pokemonNotFight;
    public Drug[] drugs;
    public PokeBall[] pokeBalls;

    private static final int MaxFightNumber = 6;
    public Bag(){};

    public Bag(List<Pokemon> pokemonFight, List<Pokemon> pokemonNotFight, Drug[] drugs, PokeBall[] pokeBalls){
        this.pokemonFight = pokemonFight;
        this.pokemonNotFight = pokemonNotFight;
        this.drugs = drugs;
        this.pokeBalls = pokeBalls;
    }

    /**
     * Initialize an empty bag
     * @return EmptyBag
     * @author  Zehao Jin
     */
    public static Bag emptyBag(){
        Bag output = new Bag();
        output.pokemonFight = new ArrayList<Pokemon>();
        output.pokemonNotFight = new ArrayList<Pokemon>();
        output.drugs = Drug.initialDrugInBag();
        output.pokeBalls = PokeBall.initialPokeBallsInBag();
        return output;
    }

    /**
     * Determine person do have target drug or not
     *
     * @param bag person's bag
     * @param drug target drug
     * @author  Zehao Jin
     */
    public static Boolean containDrug(Bag bag, Drug drug){
        boolean output = false;
        for(Drug a: bag.drugs){
            if (a.getDrug_name() == drug.getDrug_name() && a.getNum() != 0) {
                output = true;
                break;
            }
        }
        return output;
    }

    /**
     * add target pokeBall into bag
     *
     * @param bag person's bag
     * @param drug target drug
     * @author  Zehao Jin
     */
    public static void addDrug(Bag bag, Drug drug){
        for(Drug a: bag.drugs){
            if (a.getDrug_name() == drug.getDrug_name()) {
                a.setNum(a.getNum() + drug.getNum());
                break;
            }
        }
    }

    /**
     * Determine person do have target pokeBall or not
     *
     * @param bag person's bag
     * @param pokeBall target pokeBall
     * @author  Zehao Jin
     */
    public static Boolean containPokeBall(Bag bag, PokeBall pokeBall){
        boolean output = false;
        for(PokeBall a: bag.pokeBalls){
            if (a.getBallName() == pokeBall.getBallName() && a.getNumber() != 0) {
                output = true;
                break;
            }
        }
        return output;
    }

    /**
     * add target pokeBall into bag
     *
     * @param bag person's bag
     * @param pokeBall target pokeBall
     * @author  Zehao Jin
     */
    public static void addPokeBall(Bag bag, PokeBall pokeBall){
        for (PokeBall a: bag.pokeBalls){
            if (a.getBallName() == pokeBall.getBallName()){
                a.setNumber(a.getNumber() + pokeBall.getNumber());
                break;
            }
        }
    }

    /**
     * add target pokeBall into bag
     *
     * @param bag person's bag
     * @param pokemon target pokemon
     * @author  Zehao Jin
     */
    public static void addPokemon(Bag bag, Pokemon pokemon){
        IndexSetter(bag,pokemon);

        if (fullFightPokemon(bag)){
            bag.pokemonNotFight.add(pokemon);
        }else{
            bag.pokemonFight.add(pokemon);
        }
    }

    /**
     * set target pokemon's index
     *
     * @param bag person's bag
     * @param pokemon target pokemon
     * @author  Zehao Jin
     */
    public static void IndexSetter(Bag bag, Pokemon pokemon){
        int MaxIndex = 1;

        if (bag.pokemonNotFight != null && bag.pokemonFight != null){
            for(Pokemon m : bag.pokemonNotFight){
                if (m.getName().equals(pokemon.getName())){
                    if (MaxIndex <= m.getIndex()){
                        MaxIndex = m.getIndex() +1;
                    }
                }
            }
            for(Pokemon m : bag.pokemonFight){
                if (m.getName().equals(pokemon.getName())){
                    if (MaxIndex <= m.getIndex()){
                        MaxIndex = m.getIndex() +1;
                    }
                }
            }
        }else if (bag.pokemonNotFight != null){
            for(Pokemon m : bag.pokemonNotFight){
                if (m.getName().equals(pokemon.getName())){
                    if (MaxIndex <= m.getIndex()){
                        MaxIndex = m.getIndex() +1;
                    }
                }
            }
        }else if (bag.pokemonFight != null){
            for(Pokemon m : bag.pokemonFight){
                if (m.getName().equals(pokemon.getName())){
                    if (MaxIndex <= m.getIndex()){
                        MaxIndex = m.getIndex() +1;
                    }
                }
            }
        }

        pokemon.setIndex(MaxIndex);
    }

    public static Boolean fullFightPokemon(Bag bag){ return bag.pokemonFight.size() == MaxFightNumber; }

    public static void main(String[] args) {
        List<Pokemon> pokes = new ArrayList<Pokemon>();

        Pokemon a = new Pokemon("a",1,50);
        Pokemon b = new Pokemon("a",1,50);
        Pokemon c = new Pokemon("a",2,50);

        Bag bag = Bag.emptyBag();
        bag.pokemonNotFight.add(a);
        bag.pokemonNotFight.add(c);
        Bag.addPokemon(bag,b);
        for(Pokemon x : bag.pokemonNotFight){
            System.out.println("Name: " + x.getName() + " index: " + x.getIndex() + " hp: " +x.getHp());
        }
        for(Pokemon x : bag.pokemonFight){
            System.out.println("Name: " + x.getName() + " index: " + x.getIndex() + " hp: " +x.getHp());
        }
    }
}
