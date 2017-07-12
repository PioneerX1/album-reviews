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
  public void find_returnAlbumWIthSameId_testAlbum2() {
    Album testAlbum = new Album("King Of Limbs", 1997, "indie rock", 1);
    testAlbum.save();
    Album testAlbum2 = new Album("In Rainbows", 1997, "indie rock", 2);
    testAlbum2.save();
    assertEquals(Album.find(testAlbum2.getId()), testAlbum2);
  }

  @Test
  public void updateName_updatesAlbumName_PabloHoney() {
    Album testAlbum = new Album("King Of Limbs", 1997, "indie rock", 1);
    testAlbum.save();
    testAlbum.updateName("Pablo Honey");
    assertEquals("Pablo Honey", Album.find(testAlbum.getId()).getName());
  }

  @Test
  public void updateReleaseYear_updatesAlbumReleaseYear_1993() {
    Album testAlbum = new Album("King Of Limbs", 1997, "indie rock", 1);
    testAlbum.save();
    testAlbum.updateReleaseYear(1993);
    assertEquals(1993, Album.find(testAlbum.getId()).getReleaseYear());
  }

  @Test
  public void delete_deletesAnAlbum_true() {
    Album testAlbum2 = new Album("In Rainbows", 1997, "indie rock", 2);
    testAlbum2.save();
    int testAlbumId = testAlbum2.getId();
    testAlbum2.delete();
    assertEquals(null, Album.find(testAlbumId));
  }
}
