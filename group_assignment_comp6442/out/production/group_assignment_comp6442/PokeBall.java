/**
 * Construct Type main.goods.PokeBall
 *
 * @author Zehao Jin
 */


public class PokeBall {
    private BallName ballName;  // Name of a main.goods.PokeBall
    public int number; // total number of a main.goods.PokeBall


    // construct a main.goods.PokeBall
    public PokeBall(BallName ballName, int number){
        this.ballName = ballName;
        this.number = number;
    }

    //getter
    public BallName getBallName() {
        return ballName;
    }
    public int getNumber() { return number; }

    //setter
    public void setBallName(BallName ballName) {
        this.ballName = ballName;
    }
    public void setNumber(int number) { this.number = number; }


    /**
     * Initialize an empty main.goods.PokeBall list
     * @return main.goods.PokeBall list with all number 0
     * @author  Zehao Jin
     */

    public static PokeBall[] initialPokeBallsInBag(){
        BallName[] names = BallName.names();
        int nameNumber = BallName.number();
        PokeBall[] output = new PokeBall[nameNumber];
        int i = 0;
        for (BallName a : names){
            PokeBall x = new PokeBall(a,0);
            output[i] = x;
            i++;
        }
        return output;
    }
}

/**
 * Construct enum main.pokemonAttribute.BallName
 *
 * @author Zehao Jin
 */
enum BallName{
    Empty,
    Normal,
    Great,
    Ultra,
    Luxury,
    Master;

    public static BallName[] names(){
        return BallName.values();
    }

    public static int number(){
        return names().length;
    }
}