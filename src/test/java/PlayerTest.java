import org.junit.*;
import static org.junit.Assert.*;

public class PlayerTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void escaped_saysIfPlayerIsNearDoor_false() {
    Player player = new Player("George");
    player.save();
    assertEquals(false, player.escaped());
  }

  @Test
  public void escaped_saysIfPlayerIsNearDoor_true() {
    Player player = new Player("George");
    player.save();
    while (player.getXCoordinate() < 175) {
      player.moveRight();
    }
    while (player.getYCoordinate() < 390) {
      player.moveDown();
    }
    assertEquals(true, player.escaped());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Player.all().size());
  }

  @Test
  public void getName_returnsName() {
    Player player = new Player("George");
    assertEquals("George", player.getName());
  }

  @Test
  public void getXCoordinate_isInt() {
    Player player = new Player("George");
    assertTrue(player.getXCoordinate() < 401);
  }

  @Test
  public void getYCoordinate_isInt() {
    Player player = new Player("George");
    assertTrue(player.getYCoordinate() < 401);
  }

  @Test
  public void getHealth_returnsHealth() {
    Player player = new Player("George");
    assertEquals(100, player.getHealth());
  }

  @Test
  public void getId_returnsIdAfterSave() {
    Player player = new Player("George");
    player.save();
    assertEquals(Player.all().get(0).getId(), player.getId());
  }

  @Test
  public void equals_returnsTrueWhenParamsMatch() {
    Player firstPlayer = new Player("George");
    Player secondPlayer = new Player("George");
    assertEquals(true, firstPlayer.equals(secondPlayer));
  }

  @Test
  public void equals_returnsFalseWhenParamsDiffer() {
    Player firstPlayer = new Player("George");
    Player secondPlayer = new Player("Frank");
    assertEquals(false, firstPlayer.equals(secondPlayer));
  }

  @Test
  public void save_addsToDatabase() {
    Player player = new Player("George");
    player.save();
    assertEquals(Player.all().get(0), player);
  }

  @Test
  public void moveLeft_keepsPlayerInBounds() {
    Player player = new Player("George");
    player.save();
    int originalX = player.getXCoordinate();
    player.moveLeft();
    assertEquals(0, player.getXCoordinate());
    assertEquals(0, Player.all().get(0).getXCoordinate());
  }

  @Test
  public void moveRight_updatesXCoordinatePlus10() {
    Player player = new Player("George");
    player.save();
    int originalX = player.getXCoordinate();
    player.moveRight();
    assertEquals(10, player.getXCoordinate() - originalX);
    assertEquals(10, Player.all().get(0).getXCoordinate() - originalX);
  }

  @Test
  public void moveUp_keepsPlayerInBounds() {
    Player player = new Player("George");
    player.save();
    int originalY = player.getYCoordinate();
    player.moveUp();
    assertEquals(0, player.getYCoordinate());
    assertEquals(0, Player.all().get(0).getYCoordinate());
  }

  @Test
  public void moveDown_updatesYCoordinatePlus10() {
    Player player = new Player("George");
    player.save();
    int originalY = player.getYCoordinate();
    player.moveDown();
    assertEquals(10, player.getYCoordinate() - originalY);
    assertEquals(10, Player.all().get(0).getYCoordinate() - originalY);
  }

  @Test
  public void melee_affectsHealth(){
    Player attacker = new Player("George");
    attacker.save();
    Player target = new Player("Frank");
    target.save();
    attacker.melee(target);
    assertTrue(Player.find(attacker.getId()).getHealth() < 100);
    assertTrue(Player.find(target.getId()).getHealth() < 100);
    // Will fail if randomGenerator chooses 0.
  }

  @Test
  public void delete_deletesFromDatabase() {
    Player player = new Player("George");
    player.save();
    player.delete();
    assertEquals(0, Player.all().size());
  }

  @Test
  public void all_containsSavedPlayer() {
    Player player = new Player("George");
    player.save();
    assertEquals(true, Player.all().contains(player));
  }

  @Test
  public void find_findsPlayerInDatabase() {
    Player player = new Player("George");
    player.save();
    assertEquals(player, Player.find(player.getId()));
  }

}
