import org.sql2o.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;

public class Room {

  private final int ROOM_WIDTH = 20;
  private final int ROOM_LENGTH = 20;
  private final double ITEM_DENSITY = 0.1;
  private final double NPC_DENSITY = 0.1;
  private ArrayList<int[]> itemLocations;
  private ArrayList<int[]> npcLocations;



  //constructor spawns two ArrayLists of coordinates as int[2] for items and npcs

  public Room () {

    Random xCoord = new Random();
    Random yCoord = new Random();
    itemLocations = new ArrayList<int[]>();
    npcLocations = new ArrayList<int[]>();


    double itemCount =  Math.floor(ROOM_WIDTH * ROOM_LENGTH * ITEM_DENSITY);
    int itemCountInt = (int) itemCount;

    double npcCount =  Math.floor(ROOM_WIDTH * ROOM_LENGTH * NPC_DENSITY);
    int npcCountInt = (int) npcCount;

    //make arraylist of locations and npcs

    for (int i = 0; i < itemCountInt; i++){

      int[] coords = new int[2];
      coords[0] = xCoord.nextInt(ROOM_WIDTH);
      coords[1] = yCoord.nextInt(ROOM_LENGTH);
      itemLocations.add(coords);

    }

    for (int i = 0; i < npcCountInt; i++){

      int[] coords = new int[2];
      coords[0] = xCoord.nextInt(ROOM_WIDTH);
      coords[1] = yCoord.nextInt(ROOM_LENGTH);
      npcLocations.add(coords);

    }





  }

  public ArrayList<int[]> getItemLocations(){
    return itemLocations;
  }

  public ArrayList<int[]> getNpcLocations(){
    return npcLocations;
  }



  //method to check given coords - returns what?
  // public Object checkCoords (int[] coords){
  //
  //   //check if u hit da wall
  //   //check if u found da exit
  //   //check for item
  //   //check for npc
  //
  // }

}
