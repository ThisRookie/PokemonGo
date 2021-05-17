package main.character;

import com.google.gson.annotations.SerializedName;
import main.pokemonAttribute.AttackType;
import main.pokemonAttribute.Situation;
import main.pokemonAttribute.Types;
import main.pokemonAttribute.Wild;

import java.util.ArrayList;
import java.util.List;
/**
 * Construct Type main.character.Pokemon
 *
 * @author Zehao Jin
 */


public class Pokemon implements Cloneable{

    @SerializedName("Name") // Json file data 'Name'
    private String name; // pokemon's name

    @SerializedName("Type 1") // Json file data 'Type 1'
    private Types type1; // pokemon's type1

    @SerializedName("Type 2") // Json file data 'Type 2'
    private Types type2; // pokemon's type2

    private Types mainType;
    private Wild wild;   // wild pokemon or not

    @SerializedName("Speed") // Json file data 'Speed'
    private int speed;   // pokemon's speed

    @SerializedName("#") //Json file data 'code'
    private int code;    // special code
    private int index;   // pokemon's index  - for data reading

    @SerializedName("Attack")     // Json file data 'physical_attack'
    private int physical_attack;  // Max physical_attack

    @SerializedName("Sp. Atk")    // Json file data 'magical_attack'
    private int magical_attack;   // Max magical_attack

    @SerializedName("Defense")    // Json file data 'physical_defense'
    private int physical_defense; // Max physical_defense

    @SerializedName("Sp. Def")    // Json file data 'magical_defense'
    private int magical_defense;  // Max magical_defense

    @SerializedName("HP")         // Json file data 'Max_hp'
    private int max_hp;           // Max hp

    private int hp;      // current hp
    private int exp;     // current exp
    private int level;   // current level
    private Situation situation;       // current situation
    public List<AttackType> attackTypes; // current attack Type data


    //constants for Pokemon level-up
    private static final int levelConstant = 10;
    private static final int ExpConstant = 4;

    //test pokemon
    public Pokemon(){}

    //test pokemon
    public Pokemon(String name, int index, int hp) {
        this.name = name;
        this.index = index;
        this.hp = hp;
    }

    //test pokemon
    public Pokemon(String name, int index, int hp, Wild wild) {
        this.name = name;
        this.index = index;
        this.hp = hp;
        this.wild = wild;
    }

    //test pokemon
    public Pokemon(String name, Types type1, Types type2, Wild wild, int speed, int index, int physical_attack, int magical_attack, int physical_defense, int magical_defense, int max_hp, int hp, int exp, int level, Situation situation, AttackType attack_magical_Type1, AttackType attack_magical_Type2, AttackType attack_physical_Type3) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.wild = wild;
        this.speed = speed;
        this.index = index;
        this.physical_attack = physical_attack;
        this.magical_attack = magical_attack;
        this.physical_defense = physical_defense;
        this.magical_defense = magical_defense;
        this.max_hp = max_hp;
        this.hp = hp;
        this.exp = exp;
        this.level = level;
        this.situation = situation;
    }

    //test pokemon
    public Pokemon(String name, Types type1, Types type2, int speed, int index, int physical_attack, int magical_attack, int physical_defense, int magical_defense, int max_hp) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.speed = speed;
        this.index = index;
        this.physical_attack = physical_attack;
        this.magical_attack = magical_attack;
        this.physical_defense = physical_defense;
        this.magical_defense = magical_defense;
        this.max_hp = max_hp;
    }



    // setters
    public void setName (String name) { this.name  = name;  }
    public void setType1(Types type1) { this.type1 = type1; }
    public void setType2(Types type2) { this.type2 = type2; }
    public void setMainType(Types mainType) { this.mainType = mainType; }
    public void setWild (Wild  wild)  { this.wild  = wild;  }
    public void setSpeed(int speed)   { this.speed = speed; }
    public void setIndex(int index)   { this.index = index; }

    public void setMax_hp(int max_hp) { this.max_hp = max_hp; }
    public void setPhysical_attack (int physical_attack)  { this.physical_attack  = physical_attack;  }
    public void setMagical_attack  (int magical_attack)   { this.magical_attack   = magical_attack;   }
    public void setPhysical_defense(int physical_defense) { this.physical_defense = physical_defense; }
    public void setMagical_defense (int magical_defense)  { this.magical_defense  = magical_defense;  }

    public void setHp(int hp) { this.hp = hp; }
    public void setExp(int exp) { this.exp = exp; }
    public void setLevel(int level) { this.level = level; }
    public void setSituation(Situation situation) { this.situation = situation; }


    // getters
    public String getName() { return name;  }
    public Types getType1() { return type1; }
    public Types getType2() { return type2; }
    public Types getMainType() {
        return mainType;
    }
    public Wild getWild() { return wild;  }
    public int getSpeed() { return speed; }
    public int getIndex() { return index; }

    public int getMax_hp() { return max_hp; }
    public int getPhysical_attack() { return physical_attack; }
    public int getMagical_attack()  { return magical_attack;  }
    public int getPhysical_defense(){ return physical_defense;}
    public int getMagical_defense() { return magical_defense; }

    public int getHp() { return hp; }
    public int getExp() { return exp; }
    public int getLevel() { return level; }
    public Situation  getSituation() { return situation; }

    public static int getLevelConstant() {
        return levelConstant;
    }

    /**
     * Set a pokemon's attackTypes
     * @param pokemon pokemon which will gain several types of attack
     * @author Zehao Jin
     */
    public static List<AttackType> attackTypeSetter(Pokemon pokemon){
        List<AttackType> output = new ArrayList<>();
        output.add(new AttackType(pokemon.getPhysical_attack(), Types.Empty));
        if (pokemon.getType1() != null) output.add(new AttackType(pokemon.getMagical_attack(), pokemon.getType1()));
        if (pokemon.getType2() != null) output.add(new AttackType(pokemon.getMagical_attack(), pokemon.getType2()));
        return output;
    }


    /**
     * Set a pokemon's mainType
     * Change pokemon only
     * @param pokemon pokemon which will have a main type
     * @param type target main type
     * @author Zehao Jin
     */
    public static void mainTypeSetter(Pokemon pokemon, Types type){
        if (pokemon.getType1() == null){
            pokemon.setMainType(pokemon.getType2());
        }else if (pokemon.getType2() == null){
            pokemon.setMainType(pokemon.getType1());
        }else {
            pokemon.setMainType(type);
        }
    }


    /**
     * Convert Pokemon's properties into string
     * @ String of properties
     * @author Zehao Jin
     */
    public String toString(){
        return "Name: " + name + " index: " + index + " hp: " + hp + " Level: " +level;
    }


    /**
     * Ensure the total EXP for each PokemonWinner
     *
     * @param  pokemonWinners  The pokemons which won the whole game
     * @param  pokemonLosers   The pokemons which lost the whole game
     * @return Exp for a single pokemonWinner
     * @author  Zehao JIn
     */
    public static int calculateExp(List<Pokemon> pokemonWinners, List<Pokemon> pokemonLosers){
        int numWinners = 0;
        int total_Exp = 0;
        for (Pokemon pokemonLoser : pokemonLosers) {
            total_Exp = total_Exp + pokemonLoser.getLevel() * ExpConstant;
        }
        for(Pokemon pokemonWinner : pokemonWinners){
            if (pokemonWinner.getSituation() != Situation.Dead){
                numWinners = numWinners +1;
            }
        }
        return ((numWinners !=0) ? (total_Exp/numWinners) : 0);
    }


    /**
     * Estimate whether the main.character.Pokemon will be level-up after gaining the experience.
     *
     * @param pokemon target pokemon which will gain exp
     * @param experience amount of exp
     * @return True, if pokemon need to be level-up.
     * False, if pokemon do not need to be level-up
     * @author Zehao Jin
     */
    public static boolean isLevelUp(Pokemon pokemon, int experience){ return (pokemon.getExp() + experience) >= pokemon.getLevel() * levelConstant; }


    /**
     * Pokemon will gain exp after fights and when the exp conform to the max. Its properties will grow.
     *
     * @param pokemon  target pokemon which gains enough exp to be level up
     * @param experience amount of  exp that pokemon gained
     * @author Zehao Jin
     */
    public static void doLevelUp(Pokemon pokemon,int experience){
        pokemon.setExp(pokemon.getExp() + experience - pokemon.getLevel()* levelConstant);
        pokemon.setLevel(pokemon.getLevel()+1);
        pokemon.setMax_hp( (int)Math.ceil(meanGrowingProperty(pokemon)[0] * pokemon.getMax_hp()));
        pokemon.setMagical_attack  ( (int)Math.ceil( meanGrowingProperty(pokemon)[1] * pokemon.getMagical_attack()   ));
        pokemon.setPhysical_attack ( (int)Math.ceil( meanGrowingProperty(pokemon)[2] * pokemon.getPhysical_attack()  ));
        pokemon.setMagical_defense ( (int)Math.ceil( meanGrowingProperty(pokemon)[3] * pokemon.getMagical_defense()  ));
        pokemon.setPhysical_defense( (int)Math.ceil( meanGrowingProperty(pokemon)[4] * pokemon.getPhysical_defense() ));
        pokemon.setHp(pokemon.getMax_hp());
        pokemon.setSituation(Situation.Normal);

        if (isLevelUp(pokemon,pokemon.getExp())){ gainExp(pokemon, 0); }
    }

    /**
     * pokemon gains exp. They will level-Up if they gain enough exp.
     *
     * @param pokemon pokemon which attended the fight
     * @param experience amount of exp that the pokemon gains
     * @author  Zehao Jin
     */
    public static void gainExp(Pokemon pokemon,int experience){
        if (isLevelUp(pokemon, experience)) {
            doLevelUp(pokemon, experience);
        } else {
            pokemon.setExp(pokemon.getExp() + experience);
        }
    }


    /**
     * Mean of GrowingProperty for pokemon
     *
     * @param pokemon pokemon needs level-up
     * @return double[] of the growing properties.
     * @author  Zehao Jin
     * @author  HaiWei
     */
    public static double[] meanGrowingProperty(Pokemon pokemon) {
        double[] out = new double[5];

        if (pokemon.getType1() == null && pokemon.getType2() == null){
            out = new double[]{1.05,1.05,1.05,1.05,1.05};
        } else if(pokemon.getType1() == null){
            out = growing_property(pokemon.getType2());

        }else if (pokemon.getType2() == null){
            out = growing_property(pokemon.getType1());

        }else {
            double[] type1 = growing_property(pokemon.getType1());
            double[] type2 = growing_property(pokemon.getType2());
            Types mainType = pokemon.mainType;
            if (mainType == Types.Empty){
                for (int i = 0; i < 5; i++) { out[i] = type2[i] / 2 + type1[i] / 2; }
            }else if (mainType == pokemon.getType1()) {
                for (int i = 0; i < 5; i++) { out[i] = type2[i] / 3. + type1[i] * 2 / 3.; }
            }else if (mainType == pokemon.getType2()) {
                for (int i = 0; i < 5; i++) { out[i] = type2[i] * 2 / 3. + type1[i] / 3.; }
            }
        }

        return out;

    }


    /**
     * GrowingProperty for single type
     *
     * @param type type
     * @return double[] of the growing properties.
     * @author  Zehao Jin
     * @author  HaiWei
     */
    public static double[] growing_property(Types type){
        double hpCoefficient = 1.05;
        double magicalAttackCoefficient = 1.05;
        double physicalAttackCoefficient = 1.05;
        double magicalDefenseCoefficient = 1.05;
        double physicalDefenseCoefficient = 1.05;
        switch(type){
            case Normal: case Steel:
            {
                hpCoefficient = 1.052;
                physicalAttackCoefficient = 1.052;
            }
            break;
            case Psychic:
            {
                physicalAttackCoefficient = 1.058;
                magicalAttackCoefficient = 1.051;
                hpCoefficient=1.055;
            }
            break;
            case Ice: case Fire: case Water:
            case Grass: case Electric:
            {
                magicalAttackCoefficient=1.055;
                physicalAttackCoefficient=1.051;
                hpCoefficient=1.052;
            }
            break;
            case Fairy: case Dark: case Dragon:
            case Flying: case Poison: case Ghost:
            {
                magicalAttackCoefficient=1.057;
                hpCoefficient=1.055;
            }
            break;
            case Rock: case Bug: case Ground:
            {
                hpCoefficient=1.058;
                magicalDefenseCoefficient=1.055;
                physicalAttackCoefficient=1.057;
            }
            break;
        }
        return new double[]{hpCoefficient, magicalAttackCoefficient, physicalAttackCoefficient, magicalDefenseCoefficient, physicalDefenseCoefficient};
    }


    /**
     * Set a pokemon to target level - level Up only
     *
     * @param level target level
     * @param pokemon pokemon
     * @author  Zehao Jin
     */
    public static void levelSetter(Pokemon pokemon, int level){
        int levels = level - pokemon.getLevel();
        if (levels <0) throw new IndexOutOfBoundsException();
        for (int i = 0; i < levels; i++) {
            Pokemon.doLevelUp(pokemon,pokemon.getLevel() *levelConstant);
        }
    }


    /**
     * clone a pokemon to a new pokemon
     * @author  Zehao Jin
     */
    @Override
    public Object clone(){
        Pokemon poke = null;
        try {
            poke = (Pokemon)super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return poke;
    }

}




