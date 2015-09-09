import org.sql2o.*;
import java.util.List;
import java.util.Random;

public class Player extends Person {

  public Player (String name) {
    super(name);
    this.x_coordinate = 0;
    this.y_coordinate = 0;
    this.health = 100;
  }

}
