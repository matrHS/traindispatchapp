package edu.ntnu.stud.logic;

import edu.ntnu.stud.entity.TrainDeparture;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * This class is a collection of all train departures from the station.
 * The following table describes the parameters in the class as well as the reasoning
 * behind the datatypes:
 * <table border="1">
 *   <tr>
 *     <th>Datatype</th>
 *     <th>Variable Name</th>
 *     <th>Comment</th>
 *   </tr>
 *   <tr>
 *     <td>LocalTime</td>
 *     <td>departure</td>
 *     <td>
 *       LocalTime is an easy and clear datatype that already handles exceptions like if a user
 *       tries to set the hours over 23 or minutes under 0, etc.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>String</td>
 *     <td>line</td>
 *     <td>
 *       String is used as train lanes can often be represented as numbers and letters combined
 *       depending on the country, etc.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>String</td>
 *     <td>trainId</td>
 *     <td>
 *       String as the train number (trainId) can be represented as numbers and characters combined.
 *       This parameter represents a unique identifier per day for the trains, which is why I have
 *       chosen to name it trainId.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>String</td>
 *     <td>destination</td>
 *     <td>String as the destination is a combination of characters.</td>
 *   </tr>
 *   <tr>
 *     <td>int</td>
 *     <td>track</td>
 *     <td>
 *       Integer has been selected here as tracks are rarely defined as characters.
 *       If no tracks have been assigned yet to the departure, the track will be assigned to -1.
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>LocalTime</td>
 *     <td>delay</td>
 *     <td>
 *       LocalTime is used as it is an easy-to-use class that handles exceptions like wrong
 *       formatted times or times outside of the range.
 *     </td>
 *   </tr>
 * </table>
 *
 * <p>Almost all the methods in this class are set as public as most of the functionality of this
 * class should be accessible for implementation outside the class.
 * It could be discussed if the {@code deleteDeparture} method should be private or not, but I have
 * decided that it should be possible for a departure manager to delete departures manually if they
 * accidentally add some wrong information to the Departure.
 * In general the {@code deleteDeparture} should only be accessed by the automatic deletion if
 * the time rolls over, but it will be required for the Departure manager as well.
 */
public class TrainDepartureRegister {
  private static final int MAX_TRACK_NUMBER = 10;

  LocalTime currentTime;
  private HashMap<String, TrainDeparture> departureRegister;



  /**
   * Creates a new train register that holds the train departures.
   *
   */
  public TrainDepartureRegister() {
    departureRegister = new HashMap<String, TrainDeparture>();

  }


  /**
   * Adds a new departure to the register.
   * Checks if departure has an unique trainId.
   * returns true if the departure was added, false if not.
   *
   * @param departure Train departure object to be added.
   * @return True if the departure was added, false if not.
   */
  public boolean addDeparture(TrainDeparture departure) {
    if (departure == null || departureRegister.containsKey(departure.getTrainId())) {
      return false;
    }


    departureRegister.put(departure.getTrainId(), departure);
    return true;
  }


  /**
   * Removes departure from register.
   *
   * @param departure Departure to be removed
   * @return true if departure was removed, false if not.
   */
  public boolean deleteDeparture(TrainDeparture departure) {
    boolean state = false;

    if (departure != null) {
      departureRegister.remove(departure.getTrainId());
      state = true;
    }

    return state;
  }

  /**
   * Gets a departure from the register by the trainId.
   *
   * @param trainId Unique identifier for the train departure.
   * @return Train departure object if found, null if not found.
   */
  public TrainDeparture getDepartureFromId(String trainId) {
    TrainDeparture foundDeparture = null;
    if (trainId != null && !trainId.isEmpty()) {
      foundDeparture = departureRegister.get(trainId);
    }

    return foundDeparture;
  }

  /**
   * Returns a collection of train departures that are arriving at specified destination.
   *
   * @param destination Destination to check for.
   * @return Collection of train departures.
   */
  public Collection<TrainDeparture> getDeparturesByDestination(String destination) {
    Collection<TrainDeparture> foundDepartures;

    foundDepartures = departureRegister.values().stream().filter(
        (TrainDeparture d) -> {
          return d.getDestination().equals(destination);
        }
    ).toList();

    return foundDepartures;
  }

  /**
   * Returns a collection of all departures sorted by the departure time.
   * Delay for the departures are not included in this sorting.
   *
   * @return Collection of train departures.
   */
  public List<TrainDeparture> getAllDepartures() {
    List<TrainDeparture> departures = null;

    // Uses streams to sort the departures by departure times.
    // The lambda expression returns a Comparator to sort the data in the stream.
    // Converts the sorted stream into a list to be returned.
    departures = departureRegister.values().stream().sorted(
        (TrainDeparture d1, TrainDeparture d2) -> {
          return d1.getDeparture().compareTo(d2.getDeparture());
        }
    ).toList();



    return departures;
  }

  /**
   * Deletes all departures that are older than the current time.
   *
   * @param currentTime Current time.
   * @return deleteCount Number of deleted Departures.
   */
  public int deleteOldDepartures(LocalTime currentTime) {

    List<TrainDeparture> filteredList = departureRegister.values().stream().filter(
        (TrainDeparture d) -> {
          return d.getDeparture()
              .plusHours(d.getDelay().getHour())
              .plusMinutes(d.getDelay().getMinute())
              .isBefore(currentTime);
        }
    ).toList();

    int deleteCount = 0;
    for (TrainDeparture departure : filteredList) {
      this.deleteDeparture(this.getDepartureFromId(departure.getTrainId()));
      deleteCount += 1;
    }

    return deleteCount;
  }
}
