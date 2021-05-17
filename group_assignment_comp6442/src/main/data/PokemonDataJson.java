package main.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import main.character.Pokemon;
import main.pokemonAttribute.AttackType;
import main.pokemonAttribute.Situation;
import main.pokemonAttribute.Types;
import main.pokemonAttribute.Wild;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Load the main.character.Pokemon data from Json file--poke.json
 *
 * @author Zehao Jin
 */
public class PokemonDataJson {

    public PokemonDataJson() {}

    /**
     * Load the data from Json file
     *
     * @param filename file path
     * @return List of pokemon
     * @author Zehao Jin
     */
    public static List<Pokemon> load(String filename) {
        Gson gson = new Gson();
        JsonReader jsonReader = null;
        try {
            jsonReader = new JsonReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert jsonReader != null;
        return gson.fromJson(jsonReader, new TypeToken<List<Pokemon>>() {}.getType());

    }

    /**
     * Initialize the pokemon data
     *
     * @return List of initialized pokemon
     * @author Zehao Jin
     */
    public static List<Pokemon> initialPokemon() {
        List<Pokemon> output = PokemonDataJson.load("data/poke.json");
        for (Pokemon pokemon : output) {
            pokemon.setMainType(Types.Empty);
            pokemon.setLevel(1);
            pokemon.setIndex(1);
            pokemon.setSituation(Situation.Normal);
            pokemon.setExp(0);
            pokemon.setHp(pokemon.getMax_hp());
            pokemon.attackTypes = Pokemon.attackTypeSetter(pokemon);
        }
        return output;
    }

    /**
     * Initialize wild pokemon data
     *
     * @return List of wild pokemon (level 1)
     * @author Zehao Jin
     */
    public static List<Pokemon> initialIsWildPokemon() {
        List<Pokemon> output = initialPokemon();
        for (Pokemon pokemon : output) {
            pokemon.setWild(Wild.IsWild);
        }
        return output;
    }

    /**
     * Initialize UnWild pokemon data
     *
     * @return List of UnWild pokemon (level 1)
     * @author Zehao Jin
     */
    public static List<Pokemon> initialUnWildPokemon() {
        List<Pokemon> output = initialPokemon();
        for (Pokemon pokemon : output) {
            pokemon.setWild(Wild.UnWild);
        }
        return output;
    }
}
