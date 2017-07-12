import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class AlbumTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Album_instantiatesCorrectly_true() {
    Album testAlbum = new Album("OK Computer", 1997, "indie rock", 1);
    assertEquals(true, testAlbum instanceof Album);
  }
  @Test
  public void getName_retrievesAlbumName_OKComputer() {
    Album testAlbum = new Album("OK Computer", 1997, "indie rock", 1);
    assertEquals("OK Computer", testAlbum.getName());
  }
  @Test
  public void getId_instantiatesWithAnId_true() {
    Album testAlbum = new Album("OK Computer", 1997, "indie rock", 1);
    testAlbum.save();
    assertTrue(testAlbum.getId() > 0);
  }

  @Test
  public void all_retrievesAllInstancesOfAlbum_true() {
    Album testAlbum = new Album("King Of Limbs", 1997, "indie rock", 1);
    testAlbum.save();
    Album testAlbum2 = new Album("In Rainbows", 1997, "indie rock", 2);
    testAlbum2.save();
    assertEquals(true, Album.all().get(0).equals(testAlbum));
    assertEquals(true, Album.all().get(1).equals(testAlbum2));

  }

  @Test
  public void find_returnAlbumWIthSameId_testAlbum2(){
    Album testAlbum = new Album("King Of Limbs", 1997, "indie rock", 1);
    testAlbum.save();
    Album testAlbum2 = new Album("In Rainbows", 1997, "indie rock", 2);
    testAlbum2.save();
    assertEquals(Album.find(testAlbum2.getId()), testAlbum2);
  }

}
