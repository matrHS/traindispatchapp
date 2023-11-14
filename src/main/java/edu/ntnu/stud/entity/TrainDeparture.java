package edu.ntnu.stud.entity;

import java.time.LocalTime;

/**
 * The role of this class is to represent a train departure.
 * The following table describes the parameters in the class as well as reasoning for datatypes:
 *
 *<table border="1">
 *   <tr>
 *     <th>Datatype</th>
 *     <th>Variable Name</th>
 *     <th>Comment</th>
 *   </tr>
 *   <tr>
 *     <td>LocalTime</td>
 *     <td>departure</td>
 *     <td>
 *        LocalTime is an easy and clear datatype that already handles exceptions like if a user
 *        tries to set the hours over 23 or minutes under 0, etc.
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
 * <br>
 *
 * <p>I have decided to keep the following methods private as no one should be able to change them
 * after the Departure has been created.
 * {@code setDeparture(), setLine(), setDestination(), setTrainId()}
 *
 *
 * @author Matthew Hunt
 * @version 2023-10-18
 */
public class TrainDeparture {
  private LocalTime departure;
  private LocalTime delay;
  private String line;
  private String trainId; // trainId previously trainNumber.
  private String destination;
  private int track;

  /**
   * Constructor for the train departure class.
   * Track and Delay are set to their default values.
   *
   * @param departure departure time of the train
   * @param destination destination the train is arriving at
   * @param trainId train number of the train
   * @param line line the train is assigned for
   */
  public TrainDeparture(LocalTime departure, String destination, String trainId, String line) {
    setDeparture(departure);
    setLine(line);
    setTrainId(trainId);
    setDestination(destination);
    setTrack(-1);
    setDelay(LocalTime.of(0, 0));
  }

  /**
   * The default constructor for the train departure class.
   *
   * @param departure departure time of the train
   * @param destination destination the train is arriving at
   * @param trainId train number of the train
   * @param line line the train is assigned for
   * @param track track the train is assigned for
   * @param delay delay of the train
   */
  public TrainDeparture(LocalTime departure, String destination, String trainId, String line,
                        int track, LocalTime delay) {

    this(departure, destination, trainId, line); // Calls general constructor for looser coupling
    setTrack(track);
    setDelay(delay);

  }

  /**
   * Get the departure time for this train.
   *
   * @return Departure time
   */
  public LocalTime getDeparture() {
    return departure;
  }

  /**
   * Get the delay of the train if there is one.
   *
   * @return Delay for the train.
   */
  public LocalTime getDelay() {
    return delay;
  }

  /**
   * Get the current line the train is assigned.
   *
   * @return Train line
   */
  public String getLine() {
    return line;
  }

  /**
   * Get the Identifier of the train that is assigned.
   *
   * @return Train number
   */
  public String getTrainId() {
    return trainId;
  }

  /**
   * Get the destination the train is arriving at.
   *
   * @return Train destination
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Get the current assigned track for the train.
   *
   * @return Train track
   */
  public int getTrack() {
    return track;
  }

  /**
   * Sets the departure time of the train.
   * Expects a LocalTime object.
   *
   * @param departure LocalTime object that contains the time of the train departure.
   */
  private void setDeparture(LocalTime departure) {
    if (departure != null) {
      this.departure = departure;
    } else {
      this.departure = LocalTime.of(0, 0);
    }

  }

  /**
   * Sets the delay for the train departure.
   * Expects a LocalTime object.
   *
   * @param delay LocalTime object that represents the hh:mm the departure will be delayed
   */
  public void setDelay(LocalTime delay) {
    if (delay != null) {
      this.delay = delay;
    } else {
      this.delay = LocalTime.of(0, 0);
    }
  }

  /**
   * Sets the train line for the train.
   * if Null or "" is passed the line will be set as "INVALID LINE"
   *
   * @param line to assign the train
   */
  private void setLine(String line) {
    if (line != null && !line.isEmpty()) {
      this.line = line;
    } else {
      this.line = "INVALID LINE";
    }
  }

  /**
   * Sets the number for the train.
   * if Null or "" is passed the train number will be sat as "INVALID ID"
   *
   * @param trainId String to act as trains identifier
   */
  private void setTrainId(String trainId) {
    if (trainId != null && !trainId.isEmpty()) {
      this.trainId = trainId;
    } else {
      this.trainId = "INVALID ID";
    }
  }

  /**
   * Sets the destination of the train.
   * if Null or "" is passed the trains destination will be set as "INVALID DESTINATION".
   *
   * @param destination String to set the destination as.
   */
  private void setDestination(String destination) {
    if (destination != null && !destination.isEmpty()) {
      this.destination = destination;
    } else {
      this.destination = "INVALID DESTINATION";
    }
  }

  /**
   * Sets the assigned track for the train where negative values are not accepted.
   * If a negative value is passed the value gets set to -1 to indicate no track is assigned.
   *
   * @param track Integer track to assign the train to.
   */
  public void setTrack(int track) {
    if (track >= 0) {
      this.track = track;
    } else {
      this.track = -1;
    }
  }
}
