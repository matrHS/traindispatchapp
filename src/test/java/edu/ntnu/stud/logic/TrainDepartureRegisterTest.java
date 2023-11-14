package edu.ntnu.stud.logic;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.entity.TrainDeparture;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the train departure register class.
 * The following must be tested:
 * <ul>
 *   <li>
 *     Positive test:
 *     Test adding a new departure to the register.
 *     Check that total number of departures gets updated.
 *   </li>
 *   <li>
 *     Negative test:
 *     Test adding a departure with same ID as existing departure.
 *     Check that total number of departures stays the same.
 *     Test adding a departure assigned as null.
 *     Check that total number of departures stays the same.
 *   </li>
 *   <li>
 *     Positive test:
 *     Test that getAllDepartures sorts correctly
 *   </li>
 *   <li>
 *     Positive test:
 *     Test that getDeparturesByDestination returns all destinations with existing destination.
 *   </li>
 *   <li>
 *     Negative test:
 *     Test that getDeparturesByDestinations doesnt crash the program if destination doesnt exist.
 *   </li>
 * </ul>
 */
class TrainDepartureRegisterTest {

  TrainDepartureRegister testRegister;

  /**
   *
   */
  @BeforeEach
  void setupTestEnviroment() {
    this.testRegister = new TrainDepartureRegister();

    this.testRegister.addDeparture(new TrainDeparture(
        LocalTime.of(1,45),
        "Trondheim",
        "N002",
        "J8",
        2,
        LocalTime.of(0,0)
    ));

    this.testRegister.addDeparture(new TrainDeparture(
        LocalTime.of(3,23),
        "Bodø",
        "N003",
        "J9",
        8,
        LocalTime.of(1,14)
    ));

    this.testRegister.addDeparture(new TrainDeparture(
        LocalTime.of(8,23),
        "Trondheim",
        "N004",
        "J1",
        3,
        LocalTime.of(0,37)
    ));
  }

  /**
   * Positive test:
   * Test adding a departure with valid input.
   * Test that total number of departures updates.
   */
  @Test
  void testCreationOfRegisterAndAddingDeparturesWithUniqueId() {
    TrainDeparture departure = new TrainDeparture(
        LocalTime.of(8, 23),
        "Oslo",
        "N009",
        "J6",
        7,
        LocalTime.of(0, 37)
    );

    this.testRegister.addDeparture(departure);

    assertEquals(departure, testRegister.getDepartureFromId(departure.getTrainId()));
    assertEquals(4, testRegister.getAllDepartures().size());
  }

  /**
   * Negative test:
   * Test adding a departure to the register with existing ID.
   * Test that total number of departures stays the same.
   * Test adding a departure assigned to null.
   * Test that total number of departures stays the same.
   */
  @Test
  void testCreationOfRegisterAndAddingDeparturesWithSameId() {
    TrainDeparture departure = new TrainDeparture(
        LocalTime.of(3,23),
        "Bodø",
        "N003",
        "J9",
        8,
        LocalTime.of(1,14)
    );

    this.testRegister.addDeparture(departure);
    this.testRegister.addDeparture(null);

    assertEquals(3,testRegister.getAllDepartures().size());
    assertFalse(testRegister.addDeparture(departure));
    assertFalse(testRegister.addDeparture(null));
    assertEquals(3,testRegister.getAllDepartures().size());
  }

  /**
   * Positive test:
   * Test that departures get sorted correctly by departure time independent of Delay.
   * Test that adding new departure re-sorts the register.
   */
  @Test
  void testGetAllDeparturesSorted() {
    List<TrainDeparture> sortedRegister;

    TrainDeparture departure0 = new TrainDeparture(
        LocalTime.of(3,6),
        "Bodø",
        "N005",
        "J9",
        8,
        LocalTime.of(0, 0)
    );
    TrainDeparture departure1 = new TrainDeparture(
        LocalTime.of(3,4),
        "Bodø",
        "N006",
        "J9",
        8,
        LocalTime.of(1, 0)
    );

    this.testRegister.addDeparture(departure0);
    // Takes all departures from the testRegister to verify specific positions after the sort
    sortedRegister = testRegister.getAllDepartures();

    assertEquals(sortedRegister.get(1),departure0);


    this.testRegister.addDeparture(departure1);
    // Takes all departures from the testRegister to verify specific positions after the sort
    sortedRegister = testRegister.getAllDepartures();

    assertEquals(sortedRegister.get(1),departure1);
  }

  /**
   * Positive test:
   * Test adding new departure with same destination.
   * Test that all departures with shared destination gets returned.
   */
  @Test
  void testGetAllDeparturesByDestination() {
    TrainDeparture departure = new TrainDeparture(
        LocalTime.of(3,6),
        "Bodø",
        "N005",
        "J9",
        8,
        LocalTime.of(0, 0)
    );
    
    testRegister.addDeparture(departure);

    for (TrainDeparture departureDest : testRegister.getDeparturesByDestination("Bodø")) {
      assertEquals(departureDest.getDestination(), "Bodø");
    }
    
  }

  /**
   * Negative test:
   * Test searching for departures by destination which is invalid or non-existent.
   * Verify that the returned collection is an empty collection instead of crashing the program.
   */
  @Test
  void testGetAllDeparturesByInvalidDestination() {
    TrainDeparture departure = new TrainDeparture(
        LocalTime.of(3,6),
        "Bodø",
        "N005",
        "J9",
        8,
        LocalTime.of(0, 0)
    );
    Collection<TrainDeparture> foundRegister;

    testRegister.addDeparture(departure);
    foundRegister = testRegister.getDeparturesByDestination(null);

    assertEquals(Collections.emptyList(),foundRegister);
  }

}