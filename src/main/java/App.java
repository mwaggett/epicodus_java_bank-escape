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
    Weapon weapon = new Weapon("Metal Pipe", 20);

    player.save();
    bad1.save();
    bad2.save();
    weapon.save();


    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");

      String message;
      String event_message = " ";

      int bad1_radius = 5;
      int bad2_radius = 5;

      int weapon_x1;
      int weapon_y1;
      int weapon_x2;
      int weapon_y2;

      if(!bad1.isDead()) {
        npcMovement(player, bad1);
      }
      if(!bad2.isDead()) {
        npcMovement(player, bad2);
      }

      //If player is within range of a weapon, it picks up the weapon.
      if(player.weaponInRange(weapon)) {
        player.pickUp(weapon);
      }
      if(player.getWeapons().contains(weapon)) {
        weapon_x1 = player.getXCoordinate();
        weapon_y1 = player.getYCoordinate();
        weapon_x2 = player.getXCoordinate() + 10;
        weapon_y2 = player.getYCoordinate() - 10;
      } else {
        weapon_x1 = weapon.getXCoordinate() - 5;
        weapon_y1 = weapon.getYCoordinate() + 5;
        weapon_x2 = weapon.getXCoordinate() + 5;
        weapon_y2 = weapon.getYCoordinate() - 5;
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
      model.put("weapon", weapon);
      model.put("weapon_x1", weapon_x1);
      model.put("weapon_y1", weapon_y1);
      model.put("weapon_x2", weapon_x2);
      model.put("weapon_y2", weapon_y2);
      model.put("event_message", event_message);
      model.put("combat-status", message);

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/movement/attack", (request, response) -> {

      Random randomGenerator = new Random();

      int random = randomGenerator.nextInt(1);

      if(player.getWeapons().contains(weapon)) {
        if(player.inRange(bad1) && player.inRange(bad2)) {
          if(random == 1) {
            player.use(weapon, bad1);
          } else {
            player.use(weapon, bad2);
          }
        } else if(player.inRange(bad1)) {
          player.use(weapon, bad1);
        } else if(player.inRange(bad2)) {
          player.use(weapon, bad2);
        }
      } else {
        if(player.inRange(bad1) && player.inRange(bad2)) {
          if(random == 1) {
            player.melee(bad1);
          } else {
            player.melee(bad2);
          }
        } else if(player.inRange(bad1)) {
          player.melee(bad1);
        } else if(player.inRange(bad2)) {
          player.melee(bad2);
        }
      }

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
