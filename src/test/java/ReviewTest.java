import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ReviewTest{
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void review_instantiatesCorrectly_true(){
    Review testReview1 = new Review("red", "thought it sucked", 1, 3);
    assertEquals(true, testReview1 instanceof Review);
  }

  @Test
  public void getName_retrievesName_red() {
    Review testReview1 = new Review("red", "thought it sucked", 1, 3);
    assertEquals("red", testReview1.getName());
  }

  @Test
  public void getComment_retrievesComment_mindBlowing() {
    Review testReview1 = new Review("blue", "mind blowing", 1, 1);
    assertEquals("mind blowing", testReview1.getComment());
  }

  @Test
  public void getRating_retrievesRating_1() {
    Review testReview1 = new Review("blue", "mind blowing", 1, 1);
    assertEquals(1, testReview1.getRating());
  }

  @Test
  public void getAlbumId_retrievesAlbumId_1() {
    Review testReview1 = new Review("blue", "mind blowing", 1, 1);
    assertEquals(1, testReview1.getAlbumId());
  }

  @Test
  public void save_createsId_true() {
    Review testReview1 = new Review("blue", "mind blowing", 1, 1);
    testReview1.save();
    assertEquals(true, testReview1.getId() > 0);
  }

  @Test
  public void all_retrievesSavedReviews() {
    Review testReview1 = new Review("blue", "mind blowing", 1, 1);
    testReview1.save();
    Review testReview2 = new Review("yellow", "its was kk", 2, 2);
    testReview2.save();
    assertEquals(true, Review.all().get(0).equals(testReview1));
    assertEquals(true, Review.all().get(1).equals(testReview2));
  }

  @Test
  public void find_retrievesReviewWithSameId_testReview2() {
    Review testReview1 = new Review("blue", "mind blowing", 1, 1);
    testReview1.save();
    Review testReview2 = new Review("yellow", "its was kk", 2, 2);
    testReview2.save();
    assertEquals(Review.find(testReview2.getId()), testReview2);
  }

  @Test
  public void update_updatesReviewRating_2() {
    Review testReview1 = new Review("blue", "mind blowing", 5, 1);
    testReview1.save();
    testReview1.update("red", "straight fire", 2);
    assertEquals("red", Review.find(testReview1.getId()).getName());
    assertEquals("straight fire", Review.find(testReview1.getId()).getComment());
    assertEquals(2, Review.find(testReview1.getId()).getRating());
  }

  @Test
  public void delete_deletesReviewEntryFromDB_null() {
    Review testReview1 = new Review("blue", "mind blowing", 5, 1);
    testReview1.save();
    int testReviewId = testReview1.getId();
    testReview1.delete();
    assertEquals(null, Review.find(testReviewId));
  }

}
