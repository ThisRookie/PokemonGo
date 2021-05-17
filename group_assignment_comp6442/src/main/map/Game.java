package main.map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.media.jfxmediaimpl.platform.Platform;
import main.GameDisplay.GameInterface;
import main.GameDisplay.PlayerInterface;
import main.character.NPC;
import main.character.Player;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class is for the test purpose
 * @author
 */
public class Game {
    //ChoiceHandler choiceHandler = new ChoiceHandler();
//    GameMainSceneController gameMainSceneController = new GameMainSceneController();
    PlayerInterface playerInterface = new PlayerInterface();
    private Room currentRoom;
    ArrayList<Room> rooms = new ArrayList<>();

    public Game()
    {
        createRooms();

        PlayerInterface playerInterface= new PlayerInterface();
        playerInterface.changeOutputText("[" + currentRoom.getName() + "]");
    }

    private void createRooms()
    {
        Room livingRoom, bedroom, kitchen, yard, walk, pokemonCenter;

        //	create room
        bedroom = new Room("Bed Room");
        livingRoom = new Room("Living Room");
        kitchen = new Room("Kitchen");
        yard = new Room("Yard");
        walk = new Room("Walk");
        pokemonCenter = new Room("Pokemon Center");

        rooms.add(bedroom);
        rooms.add(livingRoom);
        rooms.add(kitchen);
        rooms.add(yard);
        rooms.add(walk);
        rooms.add(pokemonCenter);

        //set npc for rooms
        NPC mother = new NPC("Mother",1);
        bedroom.getNPCs().add(mother);

        // set description of room

        //create connection between rooms
        bedroom.setSouth(livingRoom);
        livingRoom.setEast(kitchen);
        livingRoom.setSouth(yard);
        yard.setSouth(walk);
        walk.setWest(pokemonCenter);

        //set current room
        currentRoom = bedroom;

        //store map info to json file
        saveMapData("resources/map.json");

    }

    public void saveMapData(String filePath){
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

        File mapFile = new File(filePath);
        if(!mapFile.exists()){
            mapFile.getParentFile().mkdirs();
            try {
                mapFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try(FileWriter fw = new FileWriter(filePath)){
            gson.toJson(rooms,fw);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * print welcome message
     */
    private String printWelcome() {

        String wel = "Welcome to the POKEMON World! First, what is your name?";
        System.out.println(wel + "\r\n");
        playerInterface.changeOutputText(wel);

        Scanner in = new Scanner(System.in);
        String player = "";

        while(true){
            player = in.nextLine();
            System.out.println("So, " + player + "? Is that correct?");
            playerInterface.changeOutputText("So, " + player + "? Is that correct?");
            String answer = in.nextLine().trim().toLowerCase();

            if(answer.equalsIgnoreCase("yes")){
                break;
            } else if (answer.equalsIgnoreCase("no")){
                System.out.println("Then, what is your name?");
                playerInterface.changeOutputText("Then, what is your name?");
            }
        }

        System.out.println("You are now at [" + currentRoom.getName() + "], what would you like to do?");
        playerInterface.changeOutputText("You are now at [" + currentRoom.getName() + "], what would you like to do?");
        //currentRoom.printMap();
        return player;
    }

    /**
     * print help message
     */
    private void printHelp()
    {
        //TODO need to write a meaningful help message
        playerInterface.changeOutputText("Figure something out yourself idiot!");
        System.out.print("figure something out yourself idiot!");
    }

    /**
     * if the name is find in the adjacent room list, then make it the current room
     * @param input direction or the name of a adjacent room
     */
    public void go(String input){
        Room current = currentRoom;
        System.out.println(input.toLowerCase());
        //TODO implement method to change current room to the input room
        String str = input.toLowerCase();
        if(str.equals("north") && currentRoom.getNorth()!=null){
            currentRoom = currentRoom.getNorth();
        } else if(str.equals("south") && currentRoom.getSouth()!=null){
            currentRoom = currentRoom.getSouth();
        } else if(str.equals("east") && currentRoom.getEast()!=null){
            currentRoom = currentRoom.getEast();
        } else if(str.equals("west") && currentRoom.getWest()!=null){
            currentRoom = currentRoom.getWest();
        } else {
            ArrayList<Room> adjacentRooms = new ArrayList<>();
            adjacentRooms.add(currentRoom.getNorth());
            adjacentRooms.add(currentRoom.getSouth());
            adjacentRooms.add(currentRoom.getEast());
            adjacentRooms.add(currentRoom.getWest());

            for(Room room: adjacentRooms){
                if(room!=null && input.equals(room.getName().toLowerCase().trim())){
                    System.out.println(room.getName().toLowerCase().trim());
                    currentRoom = room;
                }
            }
        }

        if(current==currentRoom){
            playerInterface.changeOutputText("Sorry, that is a dead end!");
        } else {
            playerInterface.changeOutputText("[" + currentRoom.getName() + "]");
            playerInterface.changeOutputText("You entered [" + currentRoom.getName() + "], what would you like to do?");
            System.out.println("You entered [" + currentRoom.getName() + "], what would you like to do?");
        }


        //currentRoom.printMap();
    }

    //this is for the test purposes only
    public static void main(String[] args) {

        Game game = new Game();
        PlayerInterface gif = game.getPlayerInterface();
        Scanner in = new Scanner(System.in);



        game.printWelcome();

        Player player = new Player("player1");

        // this part if for language processing, will be updated later when parser is completed
        while ( true ) {


            String line = in.nextLine();

            String[] words = line.split(" ");
            if ( words[0].equals("help") ) {
                //TODO implement help function
                game.printHelp();
            } else if (words[0].equals("go") ) {
                //TODO implement move function
                game.go(words[1]);
            } else if(words[0].equals("talk")){
                gif.changeOutputText("Need to implement talk function!");
                //TODO implement talk function
            } else if(words[0].equals("buy")||words[0].equals("sell")){
//                gif
//                gif.changeMainText("Need to implement shopping function!");
                //TODO implement sell and buy function
            } else if ( words[0].equals("map") ) {

                System.out.println("fond size changede");
//                gif.changeMainText(game.currentRoom.printMap());
                //gif.getTextOutputArea().setFont(new Font("Times New Roman",Font.PLAIN,25));

                //TODO implement exist game function
            } else if ( words[0].equals("bye") ) {
                System.out.println("Need to implement exit function!");
                //TODO implement exist game function
                break;
            }
        }

        System.out.println("Thank you for playing, bye bye！");
        in.close();
    }

    public PlayerInterface getPlayerInterface() {
        return playerInterface;
    }
}