import main.character.Inventory;
import main.character.Pokemon;
import main.data.PokemonDataJson;
import main.fights.PokemonFighting;
import main.goods.Drug;
import main.goods.PokeBall;
import main.pokemonAttribute.AttackType;
import main.pokemonAttribute.Situation;
import main.pokemonAttribute.Types;
import main.pokemonAttribute.Wild;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PokemonFightingTest {

    Inventory inventoryA = Inventory.emptyInventory();
    Pokemon testPokemonA1 = new Pokemon("a",1,50);
    Pokemon testPokemonA2 = new Pokemon("a",1,10);
    Pokemon testPokemonA3 = new Pokemon("a",1,100);
    Pokemon testPokemonA4 = new Pokemon("a",1,50);
    Pokemon testPokemonB1 = new Pokemon("b",1,50);
    Pokemon testPokemonB2 = new Pokemon("b",1,50);
    Pokemon testPokemonX;
    Pokemon testPokemonY;
    Pokemon testPokemonZ;

    /**
     * Set the time of testing
     */
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    /**
     * @author Zehao Jin
     */
    @Before
    public void beforeTest(){
        List<Pokemon> list = PokemonDataJson.initialUnWildPokemon();
        testPokemonA2.setMax_hp(100);
        testPokemonA2.setWild(Wild.UnWild);
        testPokemonA3.setMax_hp(100);
        testPokemonA3.setWild(Wild.UnWild);

        testPokemonX = (Pokemon)list.get(100).clone();
        testPokemonX.attackTypes = Pokemon.attackTypeSetter(testPokemonX);

        testPokemonY = (Pokemon)list.get(200).clone();
        testPokemonY.attackTypes = Pokemon.attackTypeSetter(testPokemonY);

        testPokemonZ = (Pokemon)list.get(4).clone();
        testPokemonZ.attackTypes = Pokemon.attackTypeSetter(testPokemonZ);

    }


    /**
     * Test of drugTakenTest
     * @author Zehao Jin
     */
    @Test
    public void drugTakenTest(){
        testPokemonA1.setMax_hp(100);
        PokemonFighting.drugTaken(testPokemonA1, Drug.Potion);
        assertEquals(70,testPokemonA1.getHp());

        PokemonFighting.drugTaken(testPokemonA1,Drug.Super_Potion);
        assertEquals(100,testPokemonA1.getHp());

        testPokemonA1.setSituation(Situation.Frozen);
        PokemonFighting.drugTaken(testPokemonA1, Drug.Ice_Heal);
        assertEquals(Situation.Normal,testPokemonA1.getSituation());
    }

    /**
     * Test of pokeCatchCOEF
     * @author Zehao Jin
     */

    @Test
    public void pokeCatchCOEFTest(){
        Assert.assertEquals(1,PokemonFighting.pokeCatchCOEF(PokeBall.Normal),0);
        Assert.assertEquals(0.35,PokemonFighting.pokeCatchCOEF(PokeBall.Ultra),0);
        Assert.assertEquals(0.25,PokemonFighting.pokeCatchCOEF(PokeBall.Luxury),0);
        Assert.assertEquals(0.5,PokemonFighting.pokeCatchCOEF(PokeBall.Great),0);
        Assert.assertEquals(0,PokemonFighting.pokeCatchCOEF(PokeBall.Master),0);

    }


    /**
     * Test of catchRandomInt
     * @author Zehao Jin
     */
    @Test
    public void catchRandomIntTest(){
        Assert.assertTrue(PokemonFighting.catchRandomInt(PokeBall.Normal,testPokemonA2)<120);

        testPokemonA2.setHp(20);
        Assert.assertTrue(PokemonFighting.catchRandomInt(PokeBall.Great,testPokemonA2)<120);

        testPokemonA2.setHp(30);
        Assert.assertTrue(PokemonFighting.catchRandomInt(PokeBall.Ultra,testPokemonA2)<120);

        testPokemonA2.setHp(40);
        Assert.assertTrue(PokemonFighting.catchRandomInt(PokeBall.Luxury,testPokemonA2)<120);

        testPokemonA2.setHp(100);
        assertEquals(0, PokemonFighting.catchRandomInt(PokeBall.Master, testPokemonA2));
    }

    /**
     * Test of catchPokemon
     * @author Zehao Jin
     */
    @Test
    public void catchPokemonTest(){
        Assert.assertTrue(PokemonFighting.catchPokemon(inventoryA,testPokemonA2,PokeBall.Great));
        Assert.assertTrue(Inventory.containsPokemon(inventoryA,testPokemonA2));

        Assert.assertTrue(PokemonFighting.catchPokemon(inventoryA,testPokemonA3,PokeBall.Master));
        Assert.assertTrue(Inventory.containsPokemon(inventoryA,testPokemonA3));
    }

    /**
     * Test of Defense_poke_hp
     * @author Zehao Jin
     */
    @Test
    public void Defense_poke_hpTest(){
        Assert.assertEquals(10,PokemonFighting.Defense_poke_hp(new AttackType(60, Types.Ghost),testPokemonX));
        Assert.assertEquals(5,PokemonFighting.Defense_poke_hp(new AttackType(60, Types.Steel),testPokemonX));
        Assert.assertEquals(15,PokemonFighting.Defense_poke_hp(new AttackType(60, Types.Empty),testPokemonX));
    }

    /**
     * Test of do_damage
     * @author Zehao Jin
     */
    @Test
    public void do_damageTest(){
        PokemonFighting.do_damage(new AttackType(60, Types.Ghost),testPokemonX);
        Assert.assertEquals(35,testPokemonX.getHp());
        PokemonFighting.do_damage(new AttackType(60, Types.Steel),testPokemonX);
        Assert.assertEquals(30,testPokemonX.getHp());
        PokemonFighting.do_damage(new AttackType(100, Types.Empty),testPokemonX);
        Assert.assertEquals(0,testPokemonX.getHp());
        Assert.assertEquals(testPokemonX.getSituation(),Situation.Dead);
    }

    /**
     * Test of typeEstimation
     * @author Zehao Jin
     */
    @Test
    public void typeEstimationTest(){
        Assert.assertEquals(1,PokemonFighting.typeEstimation(new AttackType(0, Types.Steel),testPokemonX),0);
        Assert.assertEquals(2,PokemonFighting.typeEstimation(new AttackType(0, Types.Steel),testPokemonY),0);
        Assert.assertEquals(2,PokemonFighting.typeEstimation(new AttackType(0, Types.Ghost),testPokemonX),0);
    }

    /**
     * Test of situationChange
     * @author Zehao Jin
     */
    @Test
    public void situationChangeTest(){
        List<Situation> situationX = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            PokemonFighting.situationChange(new AttackType(0, Types.Grass),testPokemonX);
            situationX.add(testPokemonX.getSituation());
        }
        Assert.assertTrue(situationX.contains(Situation.Sleep1));

        List<Situation> situationY = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            PokemonFighting.situationChange(new AttackType(0, Types.Fire),testPokemonY);
            situationY.add(testPokemonY.getSituation());
        }
        Assert.assertTrue(situationY.contains(Situation.Scald));
    }

    /**
     * Test of attacks
     * @author Zehao Jin
     */
    @Test
    public void attacksTest(){
        PokemonFighting.attacks(testPokemonZ,testPokemonX,testPokemonZ.attackTypes.get(0));
        Assert.assertEquals(Situation.Normal,testPokemonX.getSituation());
        Assert.assertEquals(38,testPokemonX.getHp());


        testPokemonZ.attackTypes.get(1).setAttack(55);
        List<Situation> situationX = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            PokemonFighting.attacks(testPokemonZ,testPokemonX,testPokemonZ.attackTypes.get(1));
            situationX.add(testPokemonX.getSituation());
        }
        Assert.assertTrue(situationX.contains(Situation.Scald));
        Assert.assertEquals(38,testPokemonX.getHp());
    }
}

