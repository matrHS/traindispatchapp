package edu.ntnu.stud.entity;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.entity.TrainDeparture;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

/**
 * Tests the train departure class.
 * The following must be tested:
 * <ul>
 *   <li> Positive test: Test creation of object with valid parameters. </li>
 *   <li> Negative test: Test creation of object with invalid parameters. </li>
 *   <li> Positive test: Test Setter methods for the Object with valid parameters.</li>
 *   <li> Negative test: Test Setter methods for the Object with invalid parameters.</li>
 * </ul>
 */
class TrainDepartureTest {

  /**
   * Negative tests that the train departure gets created with default values.
   *
   */
  @Test
  void testCreationOfTrainDeparture(){
    TrainDeparture testDeparture = new TrainDeparture(
        null,
        "",
        "",
        "",
        -9,
        null
    );
    assertEquals(LocalTime.of(0,0), testDeparture.getDeparture());
    assertEquals(LocalTime.of(0,0), testDeparture.getDelay());
    assertEquals("INVALID LINE", testDeparture.getLine());
    assertEquals("INVALID ID", testDeparture.getTrainId());
    assertEquals("INVALID DESTINATION", testDeparture.getDestination());
    assertEquals(-1, testDeparture.getTrack());
  }

  /**
   * Positive test of the creation of a TrainDeparture object with paramteres passed in the creation.
   *
   */
  @Test
  void testCreationOfTrainDepartureWithParams(){
    TrainDeparture testDeparture = new TrainDeparture(
        LocalTime.of(8,23),
        "Trondheim",
        "N002",
        "J8",
        8,
        LocalTime.of(1,14)
    );

    assertEquals(LocalTime.of(8,23), testDeparture.getDeparture());
    assertEquals(LocalTime.of(1,14), testDeparture.getDelay());
    assertEquals("J8", testDeparture.getLine());
    assertEquals("N002", testDeparture.getTrainId());
    assertEquals("Trondheim", testDeparture.getDestination());
    assertEquals(8, testDeparture.getTrack());
  }

  /**
   * Positive test for checking all the set methods on the train departure class
   *
   */
  @Test
  void testSetMethodsOfTrainDeparture() {
    TrainDeparture testDeparture = new TrainDeparture(
        LocalTime.of(5,26),
        "Bodø",
        "V003",
        "E6"
    );

    testDeparture.setDelay(LocalTime.of(1,52));
    testDeparture.setTrack(9);

    assertEquals(LocalTime.of(1,52),testDeparture.getDelay());
    assertEquals(9,testDeparture.getTrack());
    assertEquals(LocalTime.of(5,26),testDeparture.getDeparture());
    assertEquals("Bodø",testDeparture.getDestination());
    assertEquals("V003", testDeparture.getTrainId());
    assertEquals("E6",testDeparture.getLine());


  }

  /**
   * Negative test for checking all the set methods on the train departure class
   *
   */
  @Test
  void testSetMethodsOfTrainDepartureIncorrect() {
    TrainDeparture testDeparture = new TrainDeparture(
        LocalTime.of(0,0),
        "",
        "",
        ""
    );

    testDeparture.setTrack(-9);
    testDeparture.setDelay(null);

    assertEquals("INVALID DESTINATION",testDeparture.getDestination());
    assertEquals("INVALID ID", testDeparture.getTrainId());
    assertEquals("INVALID LINE",testDeparture.getLine());
    assertEquals(-1,testDeparture.getTrack());
    assertEquals(LocalTime.of(0,0),testDeparture.getDelay());
  }
}