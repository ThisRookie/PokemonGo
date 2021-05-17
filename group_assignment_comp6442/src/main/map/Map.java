package main.map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import main.character.NPC;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Map {
    private Room currentRoom;
    ArrayList<Room> rooms = new ArrayList<>();

    public Map() {
        createRooms();
    }

    public static void main(String[] args) {
        Map map = new Map();
        System.out.println(map.getRooms());
        System.out.println(map.loadData());


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

            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try(FileWriter fw = new FileWriter("resources/map.json")){
            gson.toJson(rooms,fw);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Room> loadData(){
        Gson gson = new Gson();
        JsonReader jsonReader = null;

        final Type CUS_LIST_TYPE = new TypeToken<List<Room>>() {}.getType();
        //or TypeToken.getParameterized(ArrayList.class, Room.class).getType();

        try{
            jsonReader = new JsonReader(new FileReader("resources/map.json"));
        }catch (Exception e) {
            e.printStackTrace();
        }

        return gson.fromJson(jsonReader, CUS_LIST_TYPE);
    }

    /**
     * if the name is find in the adjacent room list, then make it the current room
     * @param input direction or the name of a adjacent room
     */
    public void go(String input){
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
        System.out.println("You entered [" + currentRoom.getName() + "], what would you like to do?");
        //currentRoom.printMap();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
}
