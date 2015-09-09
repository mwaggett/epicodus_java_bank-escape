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

public class App {

  public static void main(String[] args) {

    staticFileLocation("/public"); // Relative path for images, css, etc.
    String layout = "templates/layout.vtl";

    Person player = new Person("John");
    player.save();

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");

      model.put("x", player.getXCoordinate());
      model.put("y", player.getYCoordinate());

      model.put("x-bad1", 50);
      model.put("y-bad1", 100);

      model.put("x-bad2", 300);
      model.put("y-bad2", 50);

      model.put("player", player);
      model.put("player-text", "Hello");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/movement", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      String left = request.queryParams("left");
      String right = request.queryParams("right");
      String up = request.queryParams("up");
      String down = request.queryParams("down");

      // Person player = Person.find(Integer.parseInt(request.params("id")));

      if(!(left.equals(null)) && left.length()!= 0) {
        player.moveLeft();
      }
      if(!(right.equals(null)) && right.length()!= 0) {
        player.moveRight();
      }
      if(!(up.equals(null)) && up.length()!= 0) {
        player.moveUp();
      }
      if(!(down.equals(null)) && down.length()!= 0) {
        player.moveDown();
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
  }
}
