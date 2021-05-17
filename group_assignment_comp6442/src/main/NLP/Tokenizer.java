package main.NLP;

import main.map.Map;
import main.map.Room;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * ARTICLE(useless words): a , an, the, to, from...
 * DIRECTION: north, south, east, west
 * move: travel, go, move, run, walk  etc
 * VERB: look, see, check, watch, talk, buy, cell, attack
 *
 *
 * @author
 */

public class Tokenizer {
    private Token currentToken;
    String buffer;
    private String[] Articles = new String[] {"a","an","to","the","from","this","that"};
    private String[] verbs = new String[] {"talk","speak","go","move","buy","sell","use"};
    private ArrayList<String> directions = new ArrayList<>();
    private String[] names = new String[] {"alice","boyang","cathy","diana"};

    /**
     *  Tokenizer class constructor
     *  The constructor extracts the first token and save it to currentToken
     *  **** please do not modify this part ****
     */
    public Tokenizer(String text) {
        buffer = text.toLowerCase().trim();

        //remove unnecessary elements from inputString
        ArrayList<String> tmp = new ArrayList<>(Arrays.asList( buffer.split(" ")));
        tmp.removeAll(Arrays.asList(Articles));
        System.out.println(tmp);
        buffer = "";

        for(String str: tmp){
            buffer = buffer + str;
        }

        //extract room names from "resources/map.json" and add these names to direction array
        initializeDirections();

        //next();		// extracts the first token.
    }

    public void initializeDirections(){
        Map map = new Map();
        ArrayList<Room> rooms = map.loadData();

        // add all room names
        for(Room r:rooms){
            String name = r.getName().trim().toLowerCase().replaceAll(" ","");
            directions.add(name);
        }
        //add four direction
        directions.add("north");
        directions.add("south");
        directions.add("east");
        directions.add("west");
    }

    //TODO next() function need to be fixed
    /**
     *  This function will find and extract a next token from {@code _buffer} and
     *  save the token to {@code currentToken}.
     */
    /*public void next() {
        ArrayList<String> verbs = new ArrayList(Arrays.asList(this.verbs));
        ArrayList<String> names = new ArrayList(Arrays.asList(this.names));

        if(verbs.contains(buffer.get(0))){
            currentToken = new Token(buffer.get(0), Token.Type.VERB);
        } else if (directions.contains(buffer.get(0))){
            currentToken = new Token(buffer.get(0), Token.Type.DIRECTION);
        } else if (names.contains(buffer.get(0))){
            currentToken = new Token(buffer.get(0), Token.Type.NAME);
        } else {
            currentToken = new Token(buffer.get(0), Token.Type.UNKNOWN);
        }

        // Remove the extracted token from buffer
        buffer.remove(0);
    }*/

    /**
     *  returned the current token extracted by {@code next()}
     *  **** please do not modify this part ****
     *
     * @return Token
     */
    public Token current() {
        return currentToken;
    }

    /**
     *  check whether there still exists another tokens in the buffer or not
     *  **** please do not modify this part ****
     *
     * @return boolean
     */
    public boolean hasNext() {
        if(buffer.length()>0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * print all tokens can be extracted from the input text
     */
    /*public void print(){

        while(this.hasNext()){
            System.out.println(this.current());
            next();
        }
        System.out.println(this.current());
    }*/

    public static void main(String[] args) {
        Tokenizer tokenizer = new Tokenizer("kill someone");
        System.out.println(tokenizer.buffer);
    }

}
