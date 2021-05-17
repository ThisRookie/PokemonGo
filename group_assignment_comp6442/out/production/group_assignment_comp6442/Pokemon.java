import com.google.gson.annotations.SerializedName;
import main.pokemonAttribute.AttackType;
import main.pokemonAttribute.Situation;
import main.pokemonAttribute.Types;
import main.pokemonAttribute.Wild;

/**
 * Construct Type main.character.Pokemon
 *
 * @author Zehao Jin
 */


public class Pokemon {

    @SerializedName("Name") // Json file data 'Name'
    private String name; // pokemon's name

    @SerializedName("Type 1") // Json file data 'Type 1'
    private Types type1; // pokemon's type1

    @SerializedName("Type 2") // Json file data 'Type 2'
    private Types type2; // pokemon's type2

    private Wild wild;   // wild pokemon or not

    @SerializedName("Speed") // Json file data 'Speed'
    private int speed;   // pokemon's speed

    @SerializedName("#") // Json file data 'Index #'
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

    private Types mainType;

    public Types getMainType() {
        return mainType;
    }

    public void setMainType(Types mainType) {
        this.mainType = mainType;
    }


    private int hp;      // current hp
    private int exp;     // current exp
    private int level;   // current level
    private Situation situation;       // current situation
    private AttackType attack_magical_Type1;    // current magical attack with type1
    private AttackType attack_magical_Type2;    // current magical attack with type2
    private AttackType attack_physical_Type3;    // current physical attack
//    private main.pokemonAttribute.AttackType attackType4;    // current Defense  attack?

    public Pokemon(){};

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
        this.attack_magical_Type1 = attack_magical_Type1;
        this.attack_magical_Type2 = attack_magical_Type2;
        this.attack_physical_Type3 = attack_physical_Type3;
    }

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

    public Pokemon(String name, int index, int hp) {
        this.name = name;
        this.index = index;
        this.hp = hp;
    }

    public Pokemon(String name, Types type1, Types type2, int speed, int index, int physical_attack, int magical_attack, int physical_defense, int magical_defense, int max_hp, Wild wild) {
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
    public void setAttack_magical_Type1 (AttackType attack_magical_Type1)  { this.attack_magical_Type1  = attack_magical_Type1;  }
    public void setAttack_magical_Type2 (AttackType attack_magical_Type2)  { this.attack_magical_Type2  = attack_magical_Type2;  }
    public void setAttack_physical_Type3(AttackType attack_physical_Type3) { this.attack_physical_Type3 = attack_physical_Type3; }

    // getters
    public String getName() { return name;  }
    public Types getType1() { return type1; }
    public Types getType2() { return type2; }
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
    public AttackType getAttack_magical_Type1() { return attack_magical_Type1; }
    public AttackType getAttack_magical_Type2() { return attack_magical_Type2; }
    public AttackType getAttack_physical_Type3(){ return attack_physical_Type3;}


    /**
     * The effects of the drugs
     * @param pokemon pokemon which will take the drug
     * @param drug target drug
     * @author Zehao Jin
     */
//    public static void drugTaken(Pokemon pokemon, Drug drug){
//        Situation situation = pokemon.getSituation();
//        switch (drug.getDrug_name()){
//            case Drug_name.Potion:
//                pokemon.setHp(Math.min(pokemon.getHp() + 20, pokemon.getMax_hp()));
//                break;
//
//            case Drug_name.Super_Potion:
//                pokemon.setHp(Math.min(pokemon.getHp() + 60, pokemon.getMax_hp()));
//                break;
//
//            case Drug_name.Hyper_Potion:
//                pokemon.setHp(Math.min(pokemon.getHp() + 120, pokemon.getMax_hp()));
//                break;
//
//            case Drug_name.Max_Potion:
//                pokemon.setHp(pokemon.getMax_hp());
//                break;
//
//            case Drug_name.Antidote:
//                if (situation == Situation.poisoned){
//                    pokemon.setSituation(Situation.Normal);
//                }
//                break;
//
//            case Drug_name.Ice_Heal:
//                if (situation == Situation.Frozen){
//                    pokemon.setSituation(Situation.Normal);
//                }
//                break;
//
//            case Drug_name.Awakening:
//                if (situation == Situation.Sleep1 || situation == Situation.Sleep2){
//                    pokemon.setSituation(Situation.Normal);
//                }
//                break;
//
//            case Drug_name.Burn_Heal:
//                if (situation == Situation.Scald){
//                    pokemon.setSituation(Situation.Normal);
//                }
//                break;
//
//            case Drug_name.Paralyze_Heal:
//                if (situation == Situation.Palsy){
//                    pokemon.setSituation(Situation.Normal);
//                }
//                break;
//
//            case Drug_name.Full_Heal:
//                if (situation != Situation.Dead){
//                    pokemon.setSituation(Situation.Normal);
//                }
//                break;
//
//            case Drug_name.Revive:
//                if (situation == Situation.Dead){
//                    pokemon.setSituation( Situation.Normal);
//                    pokemon.setHp(pokemon.getMax_hp()/2);
//                }
//
//            case Drug_name.MAX_Revive:
//                if (situation == Situation.Dead){
//                    pokemon.setSituation(Situation.Normal);
//                    pokemon.setHp(pokemon.getMax_hp());
//                }
//
//        }
//        drug.setNum(drug.getNum()-1);
//    }

    //constants for level-up
    private static final int levelConstant = 5;
    private static final int ExpConstant = 3;

    /**
     * Ensure the total EXP for each PokemonWinner
     *
     * @param  pokemonWinners  The pokemons which won the whole game
     * @param  pokemonLosers   The pokemons which lost the whole game
     * @return Exp for a single pokemonWinner
     * @author  Zehao JIn
     */
    public static int CalculateExp(Pokemon[] pokemonWinners, Pokemon[] pokemonLosers){
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
    public static boolean isLevelUp(Pokemon pokemon, int experience){ return (pokemon.getExp() + experience) > pokemon.getLevel() * levelConstant; }

    /**
     * main.character.Pokemon will gain exp after fights and when the exp conform to the max. Its properties will grow.
     *
     * @param pokemon  target pokemon which gains enough exp to be level up
     * @param experience amount of  exp that pokemon gained
     * @author Zehao Jin
     */
    public static void levelup(Pokemon pokemon,int experience){
        pokemon.setExp(pokemon.getExp() + experience - pokemon.getLevel()* levelConstant);
        pokemon.setLevel(pokemon.getLevel()+1);
        pokemon.setSituation(Situation.Normal);

        // TODO:
    }

    /**
     * Update the Level-up pokemons
     *
     * @param pokemons pokemons which attended the fight
     * @param experience amount of exp for each pokemon
     * @author  Zehao Jin
     */
    public static void UpdateAllPokemon(Pokemon[] pokemons, int experience){
        for(Pokemon pokemon : pokemons){
            if (pokemon.getSituation() != Situation.Dead){
                if (isLevelUp(pokemon, experience)) { levelup(pokemon,experience); }
                else { pokemon.setExp(pokemon.getExp() + experience);}
            }
        }
    }

    public double power(double x,int y){
        for(int i=0;i<y;i++){
            x*=x;
        }
        return x;
    }
//    /**
//     * Mean of GrowingProperty for pokemon
//     *
//     * @param pokemon pokemon needs level-up
//     * @return int[] of the growing properties.
//     * @author  Zehao Jin
//     */
//    public double[] meanGrowingProperty(Pokemon pokemon, Types mainType){
//            double[] out = new double[5];
//            double[] type1 = growing_property(pokemon.getType1());
//            double[] type2 = growing_property(pokemon.getType2());
//            if (mainType == this.type1) {
//                for (int i = 0; i < 5; i++) {
//                    out[i] = type2[i] / 3. + type1[i] * 2 / 3.;
//                }
//                if (mainType == this.type2) {
//                    for (int i = 0; i < 5; i++) {
//                        out[i] = type2[i] * 2 / 3. + type1[i] / 3.;
//                    }
//                }
//
//                return out;
//            }
//        }
//
//    public double[] growing_property(Types type){
//        double hpCoefficient = 1;
//        double magicalAttackCoefficient = 1;
//        double physicalAttackCoefficient = 1;
//        double magicalDefenseCoefficient = 1;
//        double physicalDefenseCoefficient = 1;
//         switch(type){
//            case Normal: case Steel:
//                {
//                    hpCoefficient = 1.2;
//                    physicalAttackCoefficient = 1.2;
//                }
//                break;
//            case Psychic:
//                {
//                    physicalAttackCoefficient = 3;
//                    magicalAttackCoefficient = 0.1;
//                    hpCoefficient=1.5;
//                }
//                break;
//            case Ice: case Fire: case Water:
//            case Grass: case Electric:
//                {
//                    magicalAttackCoefficient=1.5;
//                    physicalAttackCoefficient=0.9;
//                    hpCoefficient=1.2;
//                }
//                break;
//            case Fairy: case Dark: case Dragon:
//            case Flying: case Poison: case Ghost:
//                {
//                    magicalAttackCoefficient=2;
//                    hpCoefficient=1.5;
//                }
//                break;
//            case Rock: case Bug: case Ground:
//                {
//                    hpCoefficient=3;
//                    magicalDefenseCoefficient=1.5;
//                    physicalAttackCoefficient=2;
//                }
//                break;
//        }
//
//
//
//        return new double[]{hpCoefficient, magicalAttackCoefficient, physicalAttackCoefficient, magicalDefenseCoefficient, physicalDefenseCoefficient};
//    }

}




