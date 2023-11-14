package edu.ntnu.stud.entity;

import java.time.LocalTime;

/**
 * Represents a clock and handles all system time functions.
 */
public class Clock {
  private LocalTime currentTime;

  /**
   * Default constructor for the clock, sets time to 00:00.
   */
  public Clock() {
    setCurrentTime(LocalTime.of(0, 0));
  }

  /**
   * Sets the current time to provided time.
   *
   * @param currentTime Time to update the system with.
   */
  public void setCurrentTime(LocalTime currentTime) {
    // TODO: Implement validation that data cannot be sooner than current time
    if (currentTime == null) {
      return;
    }

    this.currentTime = currentTime;
  }

  /**
   * Get the current time of the clock.
   *
   * @return Current Time.
   */
  public LocalTime getCurrentTime() {
    return this.currentTime;
  }
}
