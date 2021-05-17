package main.pokemonAttribute;

import main.pokemonAttribute.Types;

/**
 * Construct Type main.pokemonAttribute.AttackType
 *
 * @author Zehao Jin
 */


public class AttackType {

    private int attack;
    private Types attack_type;
    private int round;

    public AttackType(int attack, Types attack_type){
        this.attack = attack;
        this.attack_type = attack_type;
    }
    
    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
    
    public boolean isInCd(Types attack_type, int round){
        return (round-cdRound(attack_type)==0);
    }

    public int cdRound(Types attack_type){
        int cdRound=1;
        switch (attack_type){
            case Poison:case Psychic:case Flying:case Fairy:
                cdRound=3;
                break;
            case Rock:case Bug:case Grass:case Water:
                cdRound=2;
                break;
            case Electric:case Ground:case Fire:case Ghost:
                cdRound=4;
                break;
            case Dark:case Dragon:case Ice:
                cdRound=6;
            }
           return cdRound;
        }

    public Types getAttack_type() {
        return attack_type;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setAttack_type(Types attack_type) {
        this.attack_type = attack_type;
    }

    public String toString(){
        return "Attack:" +attack + " type:" +attack_type.toString();
    }
}
