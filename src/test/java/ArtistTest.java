import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ArtistTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Artist_instantiatesCorrectly_true() {
    Artist testArtist = new Artist("Radiohead");
    assertEquals(true, testArtist instanceof Artist);
  }

  @Test
  public void getName_retrievesName_Radiohead() {
    Artist testArtist = new Artist("Radiohead");
    assertEquals("Radiohead", testArtist.getName());
  }

  @Test
  public void getId_instantiatesWithAnId_true() {
    Artist testArtist = new Artist("Radiohead");
    testArtist.save();
    assertTrue(testArtist.getId() > 0);
  }

  @Test
  public void all_retrievesAllInstancesOfArtist_true() {
    Artist testArtist = new Artist("Radiohead");
    testArtist.save();
    Artist testArtist2 = new Artist("Pink Floyd");
    testArtist2.save();
    assertEquals(true, Artist.all().get(0).equals(testArtist));
    assertEquals(true, Artist.all().get(1).equals(testArtist2));
  }

  @Test
  public void find_returnArtistWIthSameId_testArtist2() {
    Artist testArtist = new Artist("Radiohead");
    testArtist.save();
    Artist testArtist2 = new Artist("Pink Floyd");
    testArtist2.save();
    assertEquals(Artist.find(testArtist2.getId()), testArtist2);
  }

  @Test
  public void updateName_updatesArtistName_CannibalCorpse() {
    Artist testArtist = new Artist("Radiohead");
    testArtist.save();
    testArtist.updateName("Cannibal Corpse");
    assertEquals("Cannibal Corpse", Artist.find(testArtist.getId()).getName());
  }

  @Test
  public void delete_deletesAnArtist_true() {
    Artist testArtist2 = new Artist("Pink Floyd");
    testArtist2.save();
    int testArtistId = testArtist2.getId();
    testArtist2.delete();
    assertEquals(null, Artist.find(testArtistId));
  }
}
