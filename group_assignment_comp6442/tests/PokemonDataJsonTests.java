import main.data.PokemonDataJson;
import main.character.Pokemon;
import main.pokemonAttribute.Situation;
import main.pokemonAttribute.Types;
import main.pokemonAttribute.Wild;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


/**
 * Test of functions in main.data.PokemonDataJson
 *
 * @author Zehao Jin
 */

public class PokemonDataJsonTests {

    Pokemon testPokemonA;


    /**
     * Set the time of testing
     */
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    /**
     * Set the main.character.Pokemon -testPokemonA for testing
     * @author Zehao Jin
     */
    @Before
    public void beforeTest(){
        testPokemonA = new Pokemon("Bulbasaur",Types.Grass,Types.Poison,45,1,49,65,49,65,45);
        testPokemonA.setHp(testPokemonA.getMax_hp());
        testPokemonA.setLevel(1);
        testPokemonA.setIndex(1);
        testPokemonA.setExp(0);
        testPokemonA.setSituation(Situation.Normal);
        testPokemonA.attackTypes = Pokemon.attackTypeSetter(testPokemonA);
    }


    /**
     * Test the function: load
     * @author Zehao Jin
     */
    @Test
    public void loadTest(){
        Pokemon pokemonA = PokemonDataJson.load("data/poke.json").get(0);
        assertEquals(testPokemonA.getName(),pokemonA.getName());
        assertEquals(testPokemonA.getMagical_attack(),pokemonA.getMagical_attack());
        assertEquals(testPokemonA.getPhysical_attack(),pokemonA.getPhysical_attack());
        assertEquals(testPokemonA.getMagical_defense(),pokemonA.getMagical_defense());
        assertEquals(testPokemonA.getPhysical_defense(),pokemonA.getPhysical_defense());
        assertEquals(testPokemonA.getMax_hp(),pokemonA.getMax_hp());
        assertEquals(testPokemonA.getSpeed(),pokemonA.getSpeed());
        assertSame(testPokemonA.getType1(),pokemonA.getType1());
        assertSame(testPokemonA.getType2(),pokemonA.getType2());
    }

    /**
     * Test the function: initialPokemon
     * @author Zehao Jin
     */
    @Test
    public void initialPokemonTest(){
        Pokemon pokemonA = PokemonDataJson.initialPokemon().get(0);
        assertEquals(testPokemonA.getMax_hp(),pokemonA.getMax_hp());
        assertEquals(testPokemonA.getLevel(),pokemonA.getLevel());
        assertEquals(testPokemonA.getExp(),pokemonA.getExp());
        assertEquals(testPokemonA.getSituation(),pokemonA.getSituation());
        assertEquals(testPokemonA.attackTypes.get(0).toString(),pokemonA.attackTypes.get(0).toString());

    }

    /**
     * Test the function: initialIsWildPokemon
     * @author Zehao Jin
     */
    @Test
    public void initialIsWildPokemonTest(){
        List<Pokemon> list = PokemonDataJson.initialIsWildPokemon();
        for (Pokemon a : list){
            assertSame(Wild.IsWild,a.getWild());
        }
    }

    /**
     * Test the function: initialUnWildPokemon
     * @author Zehao Jin
     */
    @Test
    public void initialUnWildPokemonTest(){
        List<Pokemon> list = PokemonDataJson.initialUnWildPokemon();
        for (Pokemon a : list){
            assertSame(Wild.UnWild,a.getWild());
        }
    }
}
