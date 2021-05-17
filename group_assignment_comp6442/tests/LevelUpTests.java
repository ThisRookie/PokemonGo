import main.character.Pokemon;
import main.data.PokemonDataJson;
import main.fights.PokemonFighting;
import main.pokemonAttribute.Situation;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Test of functions about level up system
 *
 * @author Zehao Jin
 */
public class LevelUpTests {

    List<Pokemon> testInitialUnWildPokemonList = PokemonDataJson.initialUnWildPokemon();
    List<Pokemon> testWinners = new ArrayList<>();
    List<Pokemon> testLosers = new ArrayList<>();
    List<Pokemon> testA = new ArrayList<>();
    Pokemon testLevelOnePokemonA;
    Pokemon testLevelTwoPokemonA;
    Pokemon testLevelTwoPokemonB;
    Pokemon testLevelThreePokemonA;
    Pokemon testLevelThreePokemonB;
    Pokemon testLevelFivePokemonA;

    /**
     * Set the time of testing
     */
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    /**
     * Set the testing Pokemon and List<Pokemon>
     * @author Zehao Jin
     */
    @Before
    public void beforeTest(){
        testLevelOnePokemonA = (Pokemon)testInitialUnWildPokemonList.get(1).clone();

        testLevelTwoPokemonA = (Pokemon)testLevelOnePokemonA.clone();
        testLevelTwoPokemonA.setLevel(2);
        testLevelTwoPokemonA.setMax_hp( (int)Math.ceil(Pokemon.meanGrowingProperty(testLevelTwoPokemonA)[0] * testLevelTwoPokemonA.getMax_hp()));

        testLevelTwoPokemonB = (Pokemon)testLevelOnePokemonA.clone();
        Pokemon.doLevelUp(testLevelTwoPokemonB,0);

        testLevelThreePokemonA = (Pokemon)testLevelOnePokemonA.clone();
        Pokemon.levelSetter(testLevelThreePokemonA,3);

        testLevelThreePokemonB =(Pokemon)testLevelOnePokemonA.clone();
        Pokemon.gainExp(testLevelThreePokemonB,31);

        testLevelFivePokemonA = (Pokemon)testLevelOnePokemonA.clone();
        Pokemon.gainExp(testLevelFivePokemonA,100);

        //Losers
        testLosers.add(testLevelTwoPokemonA);
        testLosers.add(testLevelFivePokemonA);
        testLosers.add(testLevelThreePokemonA);

        //Winners
        testWinners.add(testLevelOnePokemonA);
        testWinners.add((Pokemon)testInitialUnWildPokemonList.get(2).clone());


        for (int i = 0; i < 6; i++) {
            testA.add((Pokemon)testInitialUnWildPokemonList.get(i).clone());
        }
    }

    /**
     * Test of levelSetter
     * @author Zehao Jin
     */
    @Test
    public void levelSetterTest(){
        Pokemon a= (Pokemon)testLevelOnePokemonA.clone();
        Pokemon.levelSetter(a,5);

        Pokemon b= (Pokemon)testLevelOnePokemonA.clone();
        Pokemon.levelSetter(b,2);

        assertEquals(testLevelFivePokemonA.toString(), a.toString());
        assertEquals(testLevelTwoPokemonA.getMax_hp(), b.getMax_hp());
        assertEquals(testLevelTwoPokemonB.getMax_hp(), b.getMax_hp());
    }

    /**
     * Test of calculateExp
     * @author Zehao Jin
     */
    @Test
    public void calculateExpTest(){
        Pokemon dead = testInitialUnWildPokemonList.get(4);
        dead.setSituation(Situation.Dead);
        testWinners.add(dead);
        assertEquals(20,Pokemon.calculateExp(testWinners,testLosers));
    }

    /**
     * Test of doLevelUp
     * @author Zehao Jins
     */
    @Test
    public void doLevelUpTest(){
        Pokemon b= (Pokemon)testLevelOnePokemonA.clone();
        Pokemon.doLevelUp(b,10);
        assertEquals(testLevelTwoPokemonA.getMax_hp(), b.getMax_hp());

        Pokemon c= (Pokemon)b.clone();
        Pokemon.doLevelUp(c,21);
        assertEquals(testLevelThreePokemonB.toString(),c.toString());
        assertEquals(testLevelThreePokemonB.getExp(),c.getExp());

    }

    /**
     * Test of meanGrowingProperty
     * @author Zehao Jin
     */
    @Test
    public void meanGrowingPropertyTest(){
        double[] k = Pokemon.meanGrowingProperty(testLevelOnePokemonA);
        double[] testDoubles = {1.0535,1.056,1.0505,1.05,1.05};

        for (int i = 0; i < k.length; i++) {
            assertSame(true,k[i] == testDoubles[i]);
        }
    }

    /**
     * Test of UpdateAllPokemon
     * @author Zehao Jin
     */
    @Test
    public void UpdateAllPokemonTest(){
        PokemonFighting.updateAllPokemon(testA,10);
        for(Pokemon a : testA){
            assertEquals(2,a.getLevel());
            System.out.println(a.toString());
        }
    }

    /**
     * Test of isLevelUp
     * @author Zehao Jin
     */
    @Test
    public void isLevelUpTest(){
        assertSame(true, Pokemon.isLevelUp((Pokemon)testLevelOnePokemonA.clone(),100));
        assertSame(true, Pokemon.isLevelUp((Pokemon)testLevelOnePokemonA.clone(),10));
        assertSame(true, Pokemon.isLevelUp((Pokemon)testLevelThreePokemonA.clone(),30));
        assertSame(false, Pokemon.isLevelUp((Pokemon)testLevelThreePokemonA.clone(),29));
        assertSame(false, Pokemon.isLevelUp((Pokemon)testLevelOnePokemonA.clone(),1));
    }

    /**
     * Test of gainExp
     * @author Zehao Jin
     */
    @Test
    public void gainExpTest(){
        Pokemon a = (Pokemon)testLevelOnePokemonA.clone();
        Pokemon.gainExp(a,10);

        assertEquals(a.toString(),testLevelTwoPokemonB.toString());
    }
}
