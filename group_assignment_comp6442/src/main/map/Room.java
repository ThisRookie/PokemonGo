package main.map;

import com.google.gson.annotations.Expose;
import main.character.NPC;
import main.character.WildPokemon;

import java.util.ArrayList;

/**
 * @author BoYang
 */
public class Room {

    @Expose
    private String name;
    //pointers point to other adjacent rooms
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    @Expose
    public ArrayList<NPC> NPCs;
    @Expose
    public ArrayList<WildPokemon> wildPokemon;
    @Expose
    public String description;

    public Room(String name)
    {
        this.name = name;
        description = "";
        north = null;
        south = null;
        east = null;
        west = null;
        NPCs = new ArrayList<>();
    }

    /**
     * create a large map that consist current room and its adjacent rooms
     * @return String that representing the map
     */
    public String printMap(){

        //initialize map for individual rooms
        String[][] currentRoom = createSingleMap();
        String[][] north = null;
        String[][] south = null;
        String[][] east = null;
        String[][] west = null;
        String[][] wholeMap = new String[19][62];
        if(this.north!=null){
            north = this.north.createSingleMap();
        }
        if(this.south!=null){
            south = this.south.createSingleMap();
        }
        if(this.east!=null){
            east = this.east.createSingleMap();
        }
        if(this.west!=null){
            west = this.west.createSingleMap();
        }

        // assemble the individual maps to a large map
        for(int i = 0; i< 19; i++){
            for(int j = 0; j < 62 ; j++){
                if((i>=0&&i<=4)&&(j>=22&&j<=39)&&(north!=null)){ //north room
                    wholeMap[i][j] = north[i][j-22];
                } else if((i>=7&&i<=11)&&((j>=0&&j<=17)||(j>=22&&j<=39)||(j>=44&&j<=61))){ //East, current, west room
                    if((west!=null)&&(j>=0&&j<=17)){
                        wholeMap[i][j] = west[i-7][j];
                    } else if(j>=22&&j<=39){
                        wholeMap[i][j] = currentRoom[i-7][j-22];
                    } else if ((east!=null)&&(j>=44&&j<=61)){
                        wholeMap[i][j] = east[i-7][j-44];
                    } else {
                        wholeMap[i][j] = " ";
                    }
                } else if((i>=14&&i<=18)&&(j>=22&&j<=39)&&(south!=null)){ //south room
                    wholeMap[i][j] = south[i-14][j-22];
                } else {
                    wholeMap[i][j] = " ";
                }
            }
        }

        //draw arrows between different room
        if(this.north!=null){
            wholeMap[5][31] = "↑";
            wholeMap[6][31] = "↑";
        }
        if(this.south!=null){
            wholeMap[12][31] = "↓";
            wholeMap[13][31] = "↓";
        }
        if(this.east!=null){
            //wholeMap[9][40] = "‐";
            wholeMap[9][41] = "‐";
            wholeMap[9][42] = "→";
            //wholeMap[9][43] = "→";

        }
        if(this.west!=null){
            //wholeMap[9][18] = "←";
            wholeMap[9][19] = "←";
            wholeMap[9][20] = "‐";
            //wholeMap[9][21] = "‐";
        }

        //add a compass to the map
        wholeMap[2][53] = "↑";
        wholeMap[1][53] = "N";

        wholeMap[4][53] = "↓";
        wholeMap[5][53] = "S";

        wholeMap[3][54] = "‐";
        wholeMap[3][55] = "‐";
        wholeMap[3][56] = "→";
        wholeMap[3][58] = "E";

        wholeMap[3][52] = "‐";
        wholeMap[3][51] = "‐";
        wholeMap[3][50] = "←";
        wholeMap[3][48] = "W";

        //export map
        String result = arrayToString(wholeMap,19,62);
        System.out.println(result);
        return result;
    }

    /**
     * create a 2D array that representing the map of a single room
     * @return String[][]
     */
    public String[][] createSingleMap(){ // 5 * 18
        int row = 5;
        int col = 18;
        String[][] map = new String[row][col];
        String[] name = (this.getName().length()%2==0)? this.getName().split(""):(this.getName() + " ").split("");
        int tmp = 0;

        for(int i = 0; i < row;i++){
            for(int j = 0; j < col;j++){

                if(i==2){
                    if(j==0||j==col-1){
                        map[i][j] = "*";
                    } else if (j>=(col/2)-name.length/2&&j<=(col/2)+name.length/2-1){
                        map[i][j] = name[tmp];
                        tmp++;
                    } else{
                        map[i][j] = " ";
                    }
                } else if(i==0||i==row-1||j==0||j==col-1){
                    map[i][j] = "*";

                } else {
                    map[i][j] = " ";
                }
            }
        }
        return map;
    }

    /**
     * convert the 2-dimensional array into String, for demonstration purposes.
     * @param array 2-dimensional array
     * @param row number of rows
     * @param col number of columns
     * @return a String representing the 2-dementia array
     */
    public String arrayToString(String[][] array, int row, int col) {
        String str = "";
        String tempStr = null;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tempStr = array[i][j];
                str = str + tempStr;
            }
            str = str + "\n";
        }
        return str;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getEast() {
        return east;
    }

    public Room getWest() {
        return west;
    }

    public void setNorth(Room north) {
        this.north = north;
        if(north.getSouth()!=this){
            north.setSouth(this);
        }
    }

    public void setSouth(Room south) {
        this.south = south;
        if(south.getNorth()!=this){
            south.setNorth(this);
        }
    }
    public void setEast(Room east) {
        this.east = east;
        if(east.getWest()!=this){
            east.setWest(this);
        }
    }
    public void setWest(Room west) {
        this.west = west;
        if(west.getEast()!=this){
            west.setEast(this);
        }
    }
    public ArrayList<NPC> getNPCs() {
        return NPCs;
    }

    public void setNPCs(ArrayList<NPC> NPCs) {
        this.NPCs = NPCs;
    }

    public ArrayList<WildPokemon> getWildPokemon() {
        return wildPokemon;
    }

    public void setWildPokemon(ArrayList<WildPokemon> wildPokemon) {
        this.wildPokemon = wildPokemon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.name;
    }
}