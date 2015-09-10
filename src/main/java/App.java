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

    Person player = new Person("John");
    Person bad1 = new Person("Bad Guy Mike");
    Person bad2 = new Person("Bad Guy Jake");

    bad1.save();
    bad2.save();
    player.save();

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");

      npcMovement(player, bad1);
      npcMovement(player, bad2);

      String message = checkIfCloseToNPC(player, bad1, bad2);
      model.put("x", player.getXCoordinate());
      model.put("y", player.getYCoordinate());

      model.put("x-bad1", bad1.getXCoordinate());
      model.put("y-bad1", bad1.getYCoordinate());

      model.put("x-bad2", bad2.getXCoordinate());
      model.put("y-bad2", bad2.getYCoordinate());

      model.put("player", player);
      model.put("bad1", bad1);
      model.put("bad2", bad2);

      model.put("combat-status", message);

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


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
  }

  public static void npcMovement(Person player, Person person) {

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
      randomlyMove(person);
    }
  }

  public static void randomlyMove(Person person) {
    Random randomGenerator = new Random();

    int random = randomGenerator.nextInt(5);
    if(random == 1) {
      person.moveUp();
    } else if(random == 2) {
      person.moveDown();
    } else if(random == 3) {
      person.moveLeft();
    } else {
      person.moveRight();
    }
  }
  public static String checkIfCloseToNPC(Person player, Person bad1, Person bad2) {
    String message = "Did not get hit";
    if(Math.abs(player.getXCoordinate() - bad1.getXCoordinate()) <= 20 &&
      (Math.abs(player.getYCoordinate() - bad1.getYCoordinate())) <= 20) {
      bad1.melee(player);
      message = "You got hit by " + bad1.getName() + "!";
    }
    if(Math.abs(player.getXCoordinate() - bad2.getXCoordinate()) <= 20 &&
      (Math.abs(player.getYCoordinate() - bad2.getYCoordinate())) <= 20) {
      bad2.melee(player);
      message = "You got hit by " + bad2.getName() + "!";
    }
    return message;
  }
}
