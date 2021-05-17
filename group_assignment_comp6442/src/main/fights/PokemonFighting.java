package main.fights;

import main.character.Inventory;
import main.character.Pokemon;
import main.goods.Drug;
import main.goods.PokeBall;
import main.pokemonAttribute.AttackType;
import main.pokemonAttribute.Situation;
import main.pokemonAttribute.Types;
import main.pokemonAttribute.Wild;

import java.util.List;
import java.util.Random;

import static main.character.Pokemon.gainExp;

/**
 * This class provides the functions for fighting
 *
 * @author Zehao Jin u6969113
 * @author Haiwei Zha
 */


public class PokemonFighting {

    Pokemon pokemonA;
    Pokemon pokemonB;


    public int round(Pokemon pokemonA, Pokemon pokemonB){
        return 0;
    }

    /**
     * Attacker pokemon attack defender pokemon by a kind of attackType
     * attacker will lose some hp if it is poisoned/Scald/Frozen
     *
     * @param attacker attacker pokemon
     * @param defender defender pokemon
     * @param attackType attackType which attacker pokemon use
     * @author Zehao Jin
     */
    public static void attacks(Pokemon attacker, Pokemon defender, AttackType attackType){
        switch (attacker.getSituation()){
            case Poisoned :case Scald:case Frozen:
                attacker.setHp((int)Math.max(attacker.getHp()-attacker.getMax_hp()*0.1,1));
        }
        do_damage(attackType,defender);
        if (defender.getSituation() !=Situation.Dead){
            situationChange(attackType,defender);
        }
    }

    /**
     * defender pokemon's situation may change by the type of the attack when it is attacked
     *
     * @param defender  defender pokemon
     * @param attackType attackType of the attack
     * @author Zehao Jin
     */
    public static void situationChange(AttackType attackType, Pokemon defender){
        if (new Random().nextInt(2) == 1){
            switch (attackType.getAttack_type()){
                case Electric:
                    defender.setSituation(Situation.Palsy);
                    break;
                case Poison:case Bug:
                    defender.setSituation(Situation.Poisoned);
                    break;
                case Ice: case Water:
                    defender.setSituation(Situation.Frozen);
                    break;
                case Fire: case Steel:
                    defender.setSituation(Situation.Scald);
                    break;
                case Fairy:case Dark:case Ghost:
                    defender.setSituation(Situation.Sleep2);
                    break;
                case Grass:
                    defender.setSituation(Situation.Sleep1);
                    break;
            }
        }
    }

    /**
     * This function can return the winner of a fight.
     *
     * @author Haiwei Zha
     */
    public Pokemon fightWinner(Pokemon pokemonA, Pokemon pokemonB){
        if(pokemonA.getHp()==0&&pokemonB.getHp()>0){
            return pokemonB;
        }
        if(pokemonA.getHp()>0&&pokemonB.getHp()==0){
            return pokemonA;
        }
        return null;
    }

    /**
     * This function can return the damage that a kind of attack makes to the defender pokemon.
     *
     * @author Haiwei Zha
     * @author Zehao Jin
     */
    public static int Defense_poke_hp (AttackType attackType,Pokemon defense_poke){
        if (attackType.getAttack_type() == Types.Empty)  {
            return  (int) Math.ceil(attackType.getAttack()- defense_poke.getPhysical_defense());
        } else {
            return  (int) Math.ceil((attackType.getAttack()- defense_poke.getMagical_defense()) * typeEstimation(attackType,defense_poke));
        }

    }
    /**
     * This function can return the Board damage that the attack poke makes to the defender pokemon.
     *
     * @author Haiwei Zha
     */
    public static void do_damage(AttackType attackType,Pokemon defense_poke){
        int currentHp = defense_poke.getHp()- Defense_poke_hp(attackType,defense_poke);
        if (currentHp > 0){
            defense_poke.setHp(currentHp);
        }else {
            defense_poke.setHp(0);
            defense_poke.setSituation(Situation.Dead);
        }
    }

    /**
     * This function gives the coefficient of attack affected by attack types
     * @param attack attack
     * @param defender defender pokemon
     * @return coefficient of attack affected by attack types and defender's type
     * @author Zehao Jin
     */
    public static double typeEstimation(AttackType attack, Pokemon defender) {
        double type1Coefficient = 1;
        double type2Coefficient = 1;
        double m = 0.5;
        double n = 2;
        Types type1 = defender.getType1();
        Types type2 = defender.getType2();
        switch (attack.getAttack_type()) {
            case Normal:
                if ( type1 == Types.Fight){ type1Coefficient = m; }
                if ( type2 == Types.Fight){ type2Coefficient = m; }
                break;

            case Bug:
                if ( type1 == Types.Grass || type1 == Types.Ghost ){ type1Coefficient = n; }
                else if (type1 == Types.Fire || type1 == Types.Rock || type1 == Types.Flying ){ type1Coefficient = m;}
                if ( type2 == Types.Grass || type2 == Types.Ghost ){ type2Coefficient = n; }
                else if (type2 == Types.Fire || type2 == Types.Rock || type2 == Types.Flying ){ type2Coefficient = m;}
                break;

            case Ice:
                if ( type1 == Types.Flying || type1 == Types.Ground || type1 == Types.Grass || type1 == Types.Dragon){ type1Coefficient = n; }
                else if (type1 == Types.Fire || type1 == Types.Rock || type1 == Types.Fight || type1 == Types.Steel) { type1Coefficient = m;}
                if ( type2 == Types.Flying || type2 == Types.Ground || type2 == Types.Grass || type2 == Types.Dragon){ type2Coefficient = n; }
                else if (type2 == Types.Fire || type2 == Types.Rock || type2 == Types.Fight || type2 == Types.Steel) { type2Coefficient = m;}
                break;

            case Dark:
                if ( type1 == Types.Ghost ){ type1Coefficient = n; }
                else if (type1 == Types.Fairy || type1 == Types.Bug || type1 == Types.Fight ) { type1Coefficient = m;}
                if ( type2 == Types.Ghost ){ type2Coefficient = n; }
                else if (type2 == Types.Fairy || type2 == Types.Bug || type2 == Types.Fight ) { type2Coefficient = m;}
                break;

            case Fire:
                if ( type1 == Types.Bug || type1 == Types.Steel || type1 == Types.Grass || type1 == Types.Ice){ type1Coefficient = n; }
                else if (type1 == Types.Ground || type1 == Types.Water || type1 == Types.Rock ) { type1Coefficient = m;}
                if ( type2 == Types.Bug || type2 == Types.Steel || type2 == Types.Grass || type2 == Types.Ice){ type2Coefficient = n; }
                else if ( type2 == Types.Ground || type2 == Types.Water || type2 == Types.Rock) { type2Coefficient = m;}
                break;

            case Rock:
                if ( type1 == Types.Flying || type1 == Types.Bug || type1 == Types.Fire || type1 == Types.Ice){ type1Coefficient = n; }
                else if (type1 == Types.Ground || type1 == Types.Water || type1 == Types.Fight || type1 == Types.Steel ||type1 == Types.Grass) { type1Coefficient = m;}
                if ( type2 == Types.Bug || type2 == Types.Flying || type2 == Types.Fire || type2 == Types.Ice){ type2Coefficient = n; }
                else if (type2 == Types.Ground || type2 == Types.Water || type2 == Types.Fight || type2 == Types.Steel ||type2 == Types.Grass) { type2Coefficient = m;}
                break;

            case Fairy:
                if ( type1 == Types.Dragon || type1 == Types.Fight || type1 == Types.Dark){ type1Coefficient = n; }
                else if ( type1 == Types.Poison || type1 == Types.Steel ) { type1Coefficient = m;}
                if ( type2 == Types.Dragon || type2 == Types.Fight || type2 == Types.Dark){ type2Coefficient = n; }
                else if ( type2 == Types.Poison || type2 == Types.Steel ) { type2Coefficient = m;}
                break;

            case Ghost:
                if ( type1 == Types.Ghost ){ type1Coefficient = n; }
                else if ( type1 == Types.Dark ) { type1Coefficient = m;}
                if ( type2 == Types.Ghost){ type2Coefficient = n; }
                else if ( type2 == Types.Dark ) { type2Coefficient = m;}
                break;

            case Grass:
                if ( type1 == Types.Ground || type1 == Types.Water || type1 == Types.Fire){ type1Coefficient = n; }
                else if ( type1 == Types.Grass || type1 == Types.Electric ) { type1Coefficient = m;}
                if ( type2 == Types.Ground || type2 == Types.Water || type2 == Types.Fire){ type2Coefficient = n; }
                else if ( type2 == Types.Grass || type2 == Types.Electric ) { type2Coefficient = m;}
                break;

            case Steel:
                if ( type1 == Types.Rock || type1 == Types.Ice ){ type1Coefficient = n; }
                else if ( type1 == Types.Fight || type1 == Types.Fire || type1 == Types.Ground) { type1Coefficient = m;}
                if ( type2 == Types.Rock || type2 == Types.Ice ){ type2Coefficient = n; }
                else if ( type2 == Types.Fight || type2 == Types.Fire || type2 == Types.Ground) { type2Coefficient = m;}
                break;

            case Water:
                if ( type1 == Types.Ground || type1 == Types.Fire || type1 == Types.Rock){ type1Coefficient = n; }
                else if ( type1 == Types.Grass || type1 == Types.Electric) { type1Coefficient = m;}
                if ( type2 == Types.Rock || type2 == Types.Fire || type2 == Types.Ground){ type2Coefficient = n; }
                else if ( type2 == Types.Grass || type2 == Types.Electric) { type2Coefficient = m;}
                break;

            case Dragon:
                if ( type1 == Types.Dragon){ type1Coefficient = n; }
                else if ( type1 == Types.Fairy || type1 == Types.Ice) { type1Coefficient = m;}
                if ( type2 == Types.Dragon){ type2Coefficient = n; }
                else if ( type2 == Types.Fairy || type2 == Types.Ice) { type2Coefficient = m;}
                break;

            case Flying:
                if ( type1 == Types.Fight || type1 == Types.Bug || type1 == Types.Grass){ type1Coefficient = n; }
                else if ( type1 == Types.Rock || type1 == Types.Ice || type1 == Types.Electric) { type1Coefficient = m;}
                if ( type2 == Types.Fight || type2 == Types.Bug || type2 == Types.Grass){ type2Coefficient = n; }
                else if ( type2 == Types.Rock || type2 == Types.Ice || type2 == Types.Electric) { type2Coefficient = m;}
                break;

            case Ground:
                if ( type1 == Types.Poison || type1 == Types.Rock || type1 == Types.Fire || type1 == Types.Electric){ type1Coefficient = n; }
                else if ( type1 == Types.Water || type1 == Types.Ice || type1 == Types.Grass) { type1Coefficient = m;}
                if ( type2 == Types.Poison || type2 == Types.Rock || type2 == Types.Fire || type2 == Types.Electric){ type2Coefficient = n; }
                else if ( type2 == Types.Water || type2 == Types.Ice || type2 == Types.Grass) { type2Coefficient = m;}
                break;

            case Poison:
                if ( type1 == Types.Fairy || type1 == Types.Grass){ type1Coefficient = n; }
                else if ( type1 == Types.Ground) { type1Coefficient = m;}
                if ( type2 == Types.Fairy || type2 == Types.Grass){ type2Coefficient = n; }
                else if ( type2 == Types.Ground) { type2Coefficient = m;}
                break;

            case Psychic:
                if ( type1 == Types.Fight || type1 == Types.Poison){ type1Coefficient = n; }
                else if ( type1 == Types.Bug || type1 == Types.Dark || type1 == Types.Ghost) { type1Coefficient = m;}
                if ( type2 == Types.Fight || type2 == Types.Poison){ type2Coefficient = n; }
                else if ( type2 == Types.Bug || type2 == Types.Dark || type2 == Types.Ghost) { type2Coefficient = m;}
                break;

            case Electric:
                if ( type1 == Types.Flying || type1 == Types.Water){ type1Coefficient = n; }
                else if ( type1 == Types.Ground) { type1Coefficient = m;}
                if ( type2 == Types.Flying || type2 == Types.Water){ type2Coefficient = n; }
                else if ( type2 == Types.Ground) { type2Coefficient = m;}

            case Fight:
                if ( type1 == Types.Normal || type1 == Types.Rock || type1 == Types.Ice || type1 == Types.Dark || type1 == Types.Steel){ type1Coefficient = n; }
                else if ( type1 == Types.Flying || type1 == Types.Psychic || type1 == Types.Fairy) { type1Coefficient = m;}
                if ( type2 == Types.Normal || type2 == Types.Rock || type2 == Types.Ice || type2 == Types.Dark || type2 == Types.Steel){ type2Coefficient = n; }
                    else if ( type2 == Types.Flying || type2 == Types.Psychic || type2 == Types.Fairy) { type2Coefficient = m;}
                break;

        }
        return type1Coefficient * type2Coefficient;
    }

    /**
     * Catch a wild pokemon by using a pokeBall
     *
     * @param inventory person's inventory
     * @param wildPokemon wild Pokemon
     * @param pokeBall pokeBall
     * @return True : if the pokemon is caught, else False.
     * @author Zehao Jin
     */
    public static Boolean catchPokemon(Inventory inventory, Pokemon wildPokemon,PokeBall pokeBall){
        boolean output = false;

        if (catchRandomInt(pokeBall,wildPokemon) <100){
            wildPokemon.setWild(Wild.UnWild);
            Inventory.addPokemon(inventory,wildPokemon);
            output = true;
        }
        return output;
    }

    /**
     * Random coefficient for catching a pokemon
     *
     * @param pokemon wild Pokemon
     * @param pokeBall pokeBall
     * @return int
     * @author Zehao Jin
     */
    public static int catchRandomInt(PokeBall pokeBall, Pokemon pokemon){
        int mean = 0;
        for (int i = 0; i < 100; i++) {
            int m = new Random().nextInt(100);
            mean = m+mean;
        }
        int cof = mean/5;
        int out = (int) Math.ceil(cof * ((double)pokemon.getHp() / pokemon.getMax_hp()) );
        return (int) (out * pokeCatchCOEF(pokeBall));
    }

    /**
     * PokeBall coefficient for catching a pokemon
     *
     * @param pokeBall pokeBall
     * @return True : if the pokemon is caught, else False.
     * @author Zehao Jin
     */
    public static double pokeCatchCOEF(PokeBall pokeBall){
        double output = 0;
        switch (pokeBall){
            case Normal:
                output =1;
                break;
            case Great:
                output = 0.5;
                break;
            case Ultra:
                output = 0.35;
                break;
            case Luxury:
                output = 0.25;
                break;
            case Master:
                output = 0;
                break;
        }
        return output;
    }


    /**
     * The effects of the drugs
     * Change pokemon only
     * @param pokemon pokemon which will take the drug
     * @param drug target drug
     * @author Zehao Jin
     */
    public static void drugTaken(Pokemon pokemon, Drug drug){
        Situation situation = pokemon.getSituation();
        switch (drug){
            case Potion:
                pokemon.setHp(Math.min(pokemon.getHp() + 20, pokemon.getMax_hp()));
                break;

            case Super_Potion:
                pokemon.setHp(Math.min(pokemon.getHp() + 60, pokemon.getMax_hp()));
                break;

            case Hyper_Potion:
                pokemon.setHp(Math.min(pokemon.getHp() + 120, pokemon.getMax_hp()));
                break;

            case Max_Potion:
                pokemon.setHp(pokemon.getMax_hp());
                break;

            case Antidote:
                if (situation == Situation.Poisoned){
                    pokemon.setSituation(Situation.Normal);
                }
                break;

            case Ice_Heal:
                if (situation == Situation.Frozen){
                    pokemon.setSituation(Situation.Normal);
                }
                break;

            case Awakening:
                if (situation == Situation.Sleep1 || situation == Situation.Sleep2){
                    pokemon.setSituation(Situation.Normal);
                }
                break;

            case Burn_Heal:
                if (situation == Situation.Scald){
                    pokemon.setSituation(Situation.Normal);
                }
                break;

            case Paralyze_Heal:
                if (situation == Situation.Palsy){
                    pokemon.setSituation(Situation.Normal);
                }
                break;

            case Full_Heal:
                if (situation != Situation.Dead){
                    pokemon.setSituation(Situation.Normal);
                }
                break;

            case Revive:
                if (situation == Situation.Dead){
                    pokemon.setSituation( Situation.Normal);
                    pokemon.setHp(pokemon.getMax_hp()/2);
                }

            case MAX_Revive:
                if (situation == Situation.Dead){
                    pokemon.setSituation(Situation.Normal);
                    pokemon.setHp(pokemon.getMax_hp());
                }
        }
    }

    /**
     * Update the Level-up pokemons
     *
     * @param pokemons pokemons which attended the fight
     * @param experience amount of exp for each pokemon
     * @author  Zehao Jin
     */
    public static void updateAllPokemon(List<Pokemon> pokemons, int experience){
        if (pokemons == null){
            System.out.println("List is null");
        }else {
            for (Pokemon pokemon : pokemons) {
                if (!(pokemon.getSituation() == Situation.Dead)) {
                    gainExp(pokemon, experience);
                }
            }
        }
    }

    //testing
    public static void main(String[] args) {
//        Pokemon poke = new Pokemon();
//        poke.setHp(10);
//        poke.setMax_hp(100);
//        System.out.println(catchRandomInt(PokeBall.Normal,poke));
//
//        Inventory inventory = Inventory.emptyInventory();
//        Boolean m = catchPokemon(inventory,poke,PokeBall.Normal);
//        System.out.println(m);
//
//        System.out.println(new Random().nextInt(2));
//
//
//        Pokemon attacker = new Pokemon();
//        attacker.setSituation(Situation.Frozen);
//        attacker.setHp(50);
//        attacker.setMax_hp(100);
//        switch (attacker.getSituation()){
//            case Poisoned :case Scald:case Frozen:
//                attacker.setHp((int)Math.max(attacker.getHp()-attacker.getMax_hp()*0.1,1));
//        }
//       System.out.println(attacker.getHp());
    }
}
