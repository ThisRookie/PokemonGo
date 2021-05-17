import main.character.Inventory;
import main.character.Pokemon;
import main.data.PokemonDataJson;
import main.pokemonAttribute.Wild;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.List;

public class InventoryTest {

    Inventory inventoryA = Inventory.emptyInventory();
    Inventory inventoryB = Inventory.emptyInventory();
    Inventory inventoryC = Inventory.emptyInventory();
    Pokemon testPokemonA1 = new Pokemon("a",1,50, Wild.UnWild);
    Pokemon testPokemonA2 = new Pokemon("a",1,50, Wild.UnWild);
    Pokemon testPokemonA3 = new Pokemon("a",1,50, Wild.UnWild);
    Pokemon testPokemonA4 = new Pokemon("a",1,50, Wild.UnWild);
    Pokemon testPokemonB1 = new Pokemon("b",1,50, Wild.UnWild);
    Pokemon testPokemonB2 = new Pokemon("b",1,50, Wild.UnWild);
    Pokemon testPokemonX;
    Pokemon testPokemonY;
    Pokemon testPokemonZ;

    /**
     * Set the time of testing
     */
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    /**
     *
     * @author Zehao Jin
     */
    @Before
    public void beforeTest(){
        List<Pokemon> list = PokemonDataJson.initialUnWildPokemon();
        testPokemonX = (Pokemon)list.get(100).clone();
        testPokemonY = (Pokemon)list.get(101).clone();
        testPokemonZ = (Pokemon)list.get(102).clone();
        // inventory A
        for (int i = 0; i < 6; i++) {
            Inventory.addPokemon(inventoryA,(Pokemon)list.get(1).clone());
        }
        Inventory.addPokemon(inventoryA,testPokemonB1);
        Inventory.addPokemon(inventoryA,testPokemonX);
        Inventory.addPokemon(inventoryA,testPokemonY);
        Inventory.addPokemon(inventoryA,testPokemonZ);

        //inventory B
        Inventory.addPokemon(inventoryB,testPokemonA1);
        Inventory.addPokemon(inventoryB,testPokemonA2);
        Inventory.addPokemon(inventoryB,testPokemonA3);
        Inventory.addPokemon(inventoryB,testPokemonX);
        Inventory.addPokemon(inventoryB,testPokemonY);
        Inventory.addPokemon(inventoryB,testPokemonZ);

        //Inventory C
        for (int i = 0; i < 3; i++) {
            Inventory.addPokemon(inventoryC,(Pokemon)list.get(i).clone());
        }
        Inventory.addPokemon(inventoryC,testPokemonX);
        Inventory.addPokemon(inventoryC,testPokemonY);
        Inventory.addPokemon(inventoryC,testPokemonZ);
        Inventory.addPokemon(inventoryC,testPokemonB1);

        Inventory.pokemonFightToNotFight(inventoryC,testPokemonX);
        Inventory.pokemonFightToNotFight(inventoryC,testPokemonY);
        Inventory.pokemonFightToNotFight(inventoryC,testPokemonZ);

        Inventory.addPokemon(inventoryC,testPokemonA1);
    }

    /**
     * Test the function: containsFightPokemon
     * @author Zehao Jin
     */
    @Test
    public void containsFightPokemonTest(){
        Assert.assertFalse(Inventory.containsFightPokemon(inventoryA,testPokemonA1));
        Assert.assertTrue(Inventory.containsFightPokemon(inventoryB,testPokemonA2));
        Assert.assertFalse(Inventory.containsFightPokemon(inventoryA,testPokemonB1));

    }

    /**
     * Test the function: containsNotFight
     * @author Zehao Jin
     */
    @Test
    public void containsNotFightPokemon(){
        Assert.assertTrue(Inventory.containsNotFightPokemon(inventoryA,testPokemonB1));
        Assert.assertFalse(Inventory.containsNotFightPokemon(inventoryB,testPokemonA1));
        Assert.assertFalse(Inventory.containsNotFightPokemon(inventoryB,testPokemonA2));

    }

    /**
     * Test the function: containsPokemon
     * @author Zehao Jin
     */
    @Test
    public void containsPokemonTest(){
        Assert.assertFalse(Inventory.containsPokemon(inventoryA,testPokemonA1));
        Assert.assertTrue(Inventory.containsPokemon(inventoryB,testPokemonA2));
        Assert.assertFalse(Inventory.containsPokemon(inventoryB,testPokemonB1));
    }

    /**
     * Test the function: deletePokemon
     * @author Zehao Jin
     */
    @Test
    public void deletePokemon(){
        Inventory.deletePokemon(inventoryA,testPokemonZ);
        Assert.assertFalse(Inventory.containsPokemon(inventoryA,testPokemonZ));
        Inventory.deletePokemon(inventoryA,testPokemonY);
        Assert.assertFalse(Inventory.containsPokemon(inventoryA,testPokemonY));
    }

    /**
     * Test the function: pokemonFightToNotFight
     * @author Zehao Jin
     */
    @Test
    public void pokemonFightToNotFightTest(){
        Inventory.pokemonFightToNotFight(inventoryB,testPokemonX);
        Assert.assertFalse(Inventory.containsFightPokemon(inventoryB,testPokemonX));
        Assert.assertTrue(Inventory.containsNotFightPokemon(inventoryB,testPokemonX));

        Inventory.pokemonFightToNotFight(inventoryB,testPokemonY);
        Assert.assertFalse(Inventory.containsFightPokemon(inventoryB,testPokemonY));
        Assert.assertTrue(Inventory.containsNotFightPokemon(inventoryB,testPokemonY));

        Inventory.pokemonFightToNotFight(inventoryB,testPokemonZ);
        Assert.assertFalse(Inventory.containsFightPokemon(inventoryB,testPokemonZ));
        Assert.assertTrue(Inventory.containsNotFightPokemon(inventoryB,testPokemonZ));
    }

    /**
     * Test the function: pokemonNotFightToFight
     * @author Zehao Jin
     */
    @Test
    public void pokemonNotFightToFightTest(){
        Inventory.pokemonNotFightToFight(inventoryC,testPokemonX);
        Assert.assertTrue(Inventory.containsFightPokemon(inventoryC,testPokemonX));
        Assert.assertFalse(Inventory.containsNotFightPokemon(inventoryC,testPokemonX));

        Inventory.pokemonNotFightToFight(inventoryC,testPokemonY);
        Assert.assertTrue(Inventory.containsFightPokemon(inventoryC,testPokemonY));
        Assert.assertFalse(Inventory.containsNotFightPokemon(inventoryC,testPokemonY));
    }

    /**
     * Test the function: pokemonSwitch
     * @author Zehao Jin
     */
    @Test
    public void pokemonSwitchTest() {
        Inventory.pokemonSwitchFight(inventoryC, testPokemonA1, testPokemonB1);
        Assert.assertTrue(Inventory.containsFightPokemon(inventoryC, testPokemonB1));
        Assert.assertTrue(Inventory.containsNotFightPokemon(inventoryC, testPokemonA1));

        Inventory.pokemonSwitchFight(inventoryC, testPokemonB1, testPokemonX);
        Assert.assertTrue(Inventory.containsFightPokemon(inventoryC, testPokemonX));
        Assert.assertTrue(Inventory.containsNotFightPokemon(inventoryC, testPokemonB1));
    }

    /**
     * Test the function: indexSetter
     * @author Zehao Jin
     */
    @Test
    public void indexSetterTest(){
        Assert.assertEquals(2,testPokemonA2.getIndex());
        Assert.assertEquals(3,testPokemonA3.getIndex());

        Inventory.indexSetter(inventoryB,testPokemonA4);
        Assert.assertEquals(4, testPokemonA4.getIndex());

        Inventory.indexSetter(inventoryB,testPokemonB2);
        Assert.assertEquals(1, testPokemonB2.getIndex());
    }

    /**
     * Test the function: fullFightPokemon
     * @author Zehao Jin
     */
    @Test
    public void fullFightPokemonTest(){
        Assert.assertTrue(Inventory.fullFightPokemon(inventoryA));
        Assert.assertTrue(Inventory.fullFightPokemon(inventoryB));
        Assert.assertFalse(Inventory.fullFightPokemon(inventoryC));
    }
}
