package main.NLP;

/**
 * Token class to save extracted token from tokenizer.
 * Each token has its surface form saved in {@code token}
 * and type saved in {@code type} which is one of the predefined type in Type enum.
 * VERB: to be updated
 * ARTICLE: a , an, the
 * DIRECTION: north, south, east, west
 * NAME: name of NPCs, may also include Pokemon's name
 * @author
 */
public class Token {
    public enum Type {UNKNOWN, VERB, ARTICLE,DIRECTION,NAME}
    private String token = "";
    private Type type = Type.UNKNOWN;

    public Token(String token, Type type){
        this.token = token;
        this.type = type;
    }

    public String token(){
        return token;
    }
    public Type type(){
        return type;
    }

    @Override
    public String toString() {
        return this.token;
    }
}
