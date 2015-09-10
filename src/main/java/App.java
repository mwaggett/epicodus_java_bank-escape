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

    bad1.save();
    bad2.save();
    player.save();

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");

      int bad1_radius = 5;
      int bad2_radius = 5;

      npcMovement(player, bad1);
      npcMovement(player, bad2);

      String message = "You are not in combat";

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
        message = bad1.getName() + " is dead!";
        bad1_radius = 0;
      } else if(bad2.getHealth() <= 0) {
        message = bad2.getName() + " is dead!";
        bad2_radius = 0;
      } else {
        message = "You are not in combat";
      }
      model.put("x", player.getXCoordinate());
      model.put("y", player.getYCoordinate());

      model.put("x-bad1", bad1.getXCoordinate());
      model.put("y-bad1", bad1.getYCoordinate());

      model.put("x-bad2", bad2.getXCoordinate());
      model.put("y-bad2", bad2.getYCoordinate());

      model.put("player", player);
      model.put("bad1", bad1);
      model.put("bad2", bad2);
      model.put("bad1_radius", bad1_radius);
      model.put("bad2_radius", bad2_radius);

       model.put("combat-status", message);

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/movement/attack", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");

      Random randomGenerator = new Random();

      int random = randomGenerator.nextInt(2);

      if(player.inRange(bad1) == true && player.inRange(bad2) == true) {
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

      response.redirect("/" );
      return null;
    });

    get("/movement/left", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");

      player.moveLeft();
      response.redirect("/" );
      return null;
    });
    get("/movement/right", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");

      player.moveRight();
      response.redirect("/" );
      return null;
    });
    get("/movement/up", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");

      player.moveUp();
      response.redirect("/" );
      return null;
    });
    get("/movement/down", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");

      player.moveDown();
      response.redirect("/" );
      return null;
    });
    get("/dead", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/dead.vtl");

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

    String message = "You are not in combat";

    Random randomGenerator = new Random();
    int random = randomGenerator.nextInt(2);

    if(proximityBad2 == true || proximityBad1 == true) {
       message = "You are in combant, fight back!";
      if(proximityBad1 == true && proximityBad2 == true) {
        if(random == 1) {
          bad1.melee(player);
        } else {
          bad2.melee(player);
        }
      } else if(proximityBad1 == true) {
        bad1.melee(player);
      } else if(proximityBad2 == true) {
        bad2.melee(player);
      }
    }
    return message;
  }


}
