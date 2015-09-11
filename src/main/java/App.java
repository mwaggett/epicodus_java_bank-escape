import java.util.Random;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.Map;
import java.lang.Math.*;

public class App {

  public static void main(String[] args) {

    staticFileLocation("/public"); // Relative path for images, css, etc.
    String layout = "templates/layout.vtl";

    Player player = new Player("John");
    Person bad1 = new Person("Bad Guy Mike");
    Person bad2 = new Person("Bad Guy Jake");
    Weapon weapon1 = new Weapon("Metal Pipe", 20);
    Weapon weapon2 = new Weapon("Glass Shard", 30);
    Weapon weapon3 = new Weapon("Piece of Paper", 5);

    player.save();
    bad1.save();
    bad2.save();
    weapon1.save();
    weapon2.save();
    weapon3.save();


    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");

      String message;
      String event_message = " ";

      int bad1_radius = 5;
      int bad2_radius = 5;

      int weapon1_x1;
      int weapon1_y1;
      int weapon1_x2;
      int weapon1_y2;

      int weapon2_x1;
      int weapon2_y1;
      int weapon2_x2;
      int weapon2_y2;
      int weapon2_x3;
      int weapon2_y3;

      int weapon3_x;
      int weapon3_y;

      if(!bad1.isDead()) {
        npcMovement(player, bad1);
      }
      if(!bad2.isDead()) {
        npcMovement(player, bad2);
      }

      //If player is within range of a weapon, it picks up the weapon.
      for (Weapon weapon : Weapon.all()) {
        if(player.weaponInRange(weapon)) {
          player.pickUp(weapon);
          event_message = "Picked up weapon: " + weapon.getNameOfWeapon() ;
        }
      }
      if(player.getWeapons().contains(weapon1)) {
        weapon1_x1 = player.getXCoordinate();
        weapon1_y1 = player.getYCoordinate();
        weapon1_x2 = player.getXCoordinate() + 10;
        weapon1_y2 = player.getYCoordinate() - 10;
      } else {
        weapon1_x1 = weapon1.getXCoordinate() - 5;
        weapon1_y1 = weapon1.getYCoordinate() + 5;
        weapon1_x2 = weapon1.getXCoordinate() + 5;
        weapon1_y2 = weapon1.getYCoordinate() - 5;
      }
      if(player.getWeapons().contains(weapon2)) {
        weapon2_x1 = player.getXCoordinate();
        weapon2_y1 = player.getYCoordinate();
        weapon2_x2 = player.getXCoordinate() + 4;
        weapon2_y2 = player.getYCoordinate() - 6;
        weapon2_x3 = player.getXCoordinate() + 8;
        weapon2_y3 = player.getYCoordinate();
      } else {
        weapon2_x1 = weapon2.getXCoordinate();
        weapon2_y1 = weapon2.getYCoordinate();
        weapon2_x2 = weapon2.getXCoordinate() + 4;
        weapon2_y2 = weapon2.getYCoordinate() - 6;
        weapon2_x3 = weapon2.getXCoordinate() + 8;
        weapon2_y3 = weapon2.getYCoordinate();
      }
      String weapon2_coords = String.format("%d,%d %d,%d %d,%d", weapon2_x1, weapon2_y1, weapon2_x2, weapon2_y2, weapon2_x3, weapon2_y3);
      if(player.getWeapons().contains(weapon3)) {
        weapon3_x = player.getXCoordinate();
        weapon3_y = player.getYCoordinate() - 20;
      } else {
        weapon3_x = weapon1.getXCoordinate();
        weapon3_y = weapon1.getYCoordinate();
      }

      //If a player is close to an NPC it either has the NPC perform
      //A melee attack, or if both are close by it will choose a random NPC
      message = npcAttack(player, bad1, bad2);

      //If player's health goes below 0 the game is over
      if(player.getHealth() <= 0) {
          response.redirect("/dead" );
          return null;
      }
      //If an NPC is dead, the radius is set to zero to make the character disappear
      if(bad1.getHealth() <= 0) {
        event_message = bad1.getName() + " is dead";
        bad1_radius = 0;
        bad1.delete();
      }
       if(bad2.getHealth() <= 0) {
        event_message = bad2.getName() + " is dead";
        bad2_radius = 0;
        bad2.delete();
      }
      if (player.escaped()) {
        response.redirect("/success" );
        return null;
      }

      model.put("player", player);
      model.put("bad1", bad1);
      model.put("bad2", bad2);
      model.put("bad1_radius", bad1_radius);
      model.put("bad2_radius", bad2_radius);
      model.put("weapon1_x1", weapon1_x1);
      model.put("weapon1_y1", weapon1_y1);
      model.put("weapon1_x2", weapon1_x2);
      model.put("weapon1_y2", weapon1_y2);

      model.put("weapon2_coords", weapon2_coords);

      model.put("weapon3_x", weapon3_x);
      model.put("weapon3_y", weapon3_y);


      model.put("event_message", event_message);
      model.put("combat-status", message);

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/movement/attack", (request, response) -> {

      Random randomGenerator = new Random();

      int random = randomGenerator.nextInt(1);

      // if(player.getWeapons().contains(weapon)) {
      //   if(player.inRange(bad1) && player.inRange(bad2)) {
      //     if(random == 1) {
      //       player.use(weapon, bad1);
      //     } else {
      //       player.use(weapon, bad2);
      //     }
      //   } else if(player.inRange(bad1)) {
      //     player.use(weapon, bad1);
      //   } else if(player.inRange(bad2)) {
      //     player.use(weapon, bad2);
      //   }
      // } else {
      //   if(player.inRange(bad1) && player.inRange(bad2)) {
      //     if(random == 1) {
      //       player.melee(bad1);
      //     } else {
      //       player.melee(bad2);
      //     }
      //   } else if(player.inRange(bad1)) {
      //     player.melee(bad1);
      //   } else if(player.inRange(bad2)) {
      //     player.melee(bad2);
      //   }
      // }

      response.redirect("/" );
      return null;
    });

    get("/movement/left", (request, response) -> {

      player.moveLeft();
      response.redirect("/" );
      return null;
    });

    get("/movement/right", (request, response) -> {

      player.moveRight();
      response.redirect("/" );
      return null;
    });

    get("/movement/up", (request, response) -> {

      player.moveUp();
      response.redirect("/" );
      return null;
    });

    get("/movement/down", (request, response) -> {

      player.moveDown();
      response.redirect("/" );
      return null;
    });

    get("/dead", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/dead.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/success", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/success.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

  public static void npcMovement(Player player, Person person) {

    Random randomGenerator = new Random();

    int random = randomGenerator.nextInt(6);

    if(random == 1 || random == 2 ) {
      if(player.getXCoordinate() > person.getXCoordinate()) {
        person.moveRight();
      } else {
        person.moveLeft();
      }
    } else if(random == 3 || random == 4) {
      if(player.getYCoordinate() < person.getYCoordinate()) {
        person.moveUp();
      } else {
        person.moveDown();
      }
    } else {
      person.moveRandom();
    }
  }

  public static String npcAttack(Player player, Person bad1, Person bad2) {

    boolean proximityBad1 = player.inRange(bad1);
    boolean proximityBad2 = player.inRange(bad2);

    boolean bad1_is_dead = bad1.isDead();
    boolean bad2_is_dead = bad2.isDead();
    String message = "You are not in combat";

    Random randomGenerator = new Random();
    int random = randomGenerator.nextInt(2);

    if(proximityBad2 == true || proximityBad1 == true) {
       message = "You are in combant, fight back!";
        if((proximityBad1 == true && proximityBad2 == true)
          && (bad1_is_dead == false && bad2_is_dead == false) ) {
          if(random == 1) {
            bad1.melee(player);
          } else {
            bad2.melee(player);
          }
      } else if(proximityBad1 == true && bad1_is_dead == false) {
        bad1.melee(player);
      } else if(proximityBad2 == true && bad2_is_dead == false) {
        bad2.melee(player);
      }
    }
    return message;
  }
}
