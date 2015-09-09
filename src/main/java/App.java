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

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");

      model.put("x", 250);
      model.put("y", 100);

      model.put("x-bad1", 50);
      model.put("y-bad1", 100);

      model.put("x-bad2", 300);
      model.put("y-bad2", 50);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }

}
