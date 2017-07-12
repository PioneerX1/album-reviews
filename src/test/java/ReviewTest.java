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

}
