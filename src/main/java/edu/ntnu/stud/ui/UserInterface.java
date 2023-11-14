package edu.ntnu.stud.ui;

import edu.ntnu.stud.entity.Clock;
import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.logic.TrainDepartureRegister;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


/**
 * Represents the user interface for the train dispatch application.
 * Init method handles the initial setup of the application.
 * Start method handles the main loop of the application.
 */
public class UserInterface {

  TrainDepartureRegister departureRegister;
  Clock systemClock;

  private static final String VERSION = "1.3-SNAPSHOT";

  private static final int ADD_NEW_DEPARTURE = 1;
  private static final int ADD_DELAY_TO_DEPARTURE = 2;
  private static final int ADD_TRACK_TO_DEPARTURE = 3;
  private static final int SHOW_ALL_DEPARTURES = 4;
  private static final int SHOW_ALL_DEPARTURES_BY_DEST = 5;
  private static final int SHOW_DEPARTURE_BY_ID = 6;
  private static final int DELETE_DEPARTURE_BY_ID = 7;
  private static final int UPDATE_CLOCK = 8;
  private static final int EXIT = 9;
  private static final int MAX_MENU_CHOICE = 9;



  /**
   * Method for initializing the application along with data that is needed for it to function
   * correctly.
   *
   */
  public void init() {

    systemClock = new Clock();

    this.departureRegister = new TrainDepartureRegister();

    this.departureRegister.addDeparture(new TrainDeparture(
        LocalTime.of(1, 45),
        "Trondheim",
        "N002",
        "J8",
        2,
        LocalTime.of(0, 0)
    ));

    this.departureRegister.addDeparture(new TrainDeparture(
        LocalTime.of(3, 23),
        "Bodø",
        "N003",
        "J9",
        8,
        LocalTime.of(1, 14)
    ));

    this.departureRegister.addDeparture(new TrainDeparture(
        LocalTime.of(8, 23),
        "Trondheim",
        "N004",
        "J1",
        3,
        LocalTime.of(0, 37)
    ));


  }

  /**
   * Start method and the main program loop.
   */
  public void start() {

    this.printWelcomeMessage();


    boolean finished = false;
    while (!finished) {
      this.displayMenu();
      int menuSelection = getMenuSelection();

      if (!handleMenuChoice(menuSelection)) {
        finished = true;
      }
    }
  }

  private int getMenuSelection() {
    int menuSelection;
    Scanner inputScanner = new Scanner(System.in);

    System.out.println("Please enter a menu choice between 1-" + MAX_MENU_CHOICE + ": ");
    if (inputScanner.hasNextInt()) {
      menuSelection = inputScanner.nextInt();
    } else {
      menuSelection = -1;
    }

    return menuSelection;
  }

  /**
   * Handles user input.
   *  <p>
   *    If the choice is valid, the command is executed and {@code true} is returned.
   *  </p>
   *  <p>
   *    If the choice is to exit, {@code false} is returned.
   *  </p>
   *
   * @param menuSelection User input.
   *
   */
  private boolean handleMenuChoice(int menuSelection) {
    // TODO: Create handling input functionality
    boolean result = true;

    switch (menuSelection) {
      case ADD_NEW_DEPARTURE:
        this.addNewDeparture();
        break;
      case ADD_DELAY_TO_DEPARTURE:
        this.addDelayToDeparture();
        break;
      case ADD_TRACK_TO_DEPARTURE:
        this.addTrackToDeparture();
        break;
      case SHOW_ALL_DEPARTURES:
        this.showAllDepartures();
        break;
      case SHOW_ALL_DEPARTURES_BY_DEST:
        this.showAllDeparturesByDest();
        break;
      case SHOW_DEPARTURE_BY_ID:
        this.showDepartureById();
        break;
      case DELETE_DEPARTURE_BY_ID:
        this.deleteDepartureById();
        break;
      case UPDATE_CLOCK:
        this.updateClockAndDeleteOldDepartures();
        break;
      case EXIT:
        result = false;
        break;
      default:
        System.out.println("Invalid menu choice. Please enter a number between 1-"
            + MAX_MENU_CHOICE + ": ");
        break;
    }

    return result;
  }

  /**
   * Prints the menu options for the user to interact with.
   */
  private void displayMenu() {
    // TODO: Consider changing to Enums instead of menu choices
    System.out.println("Current system time: " + systemClock.getCurrentTime());
    System.out.printf("\n"
        + ADD_NEW_DEPARTURE + ". Add new departure\n"
        + ADD_DELAY_TO_DEPARTURE + ". Add delay to departure\n"
        + ADD_TRACK_TO_DEPARTURE + ". Assign track to departure\n"
        + SHOW_ALL_DEPARTURES + ". Show all departures\n"
        + SHOW_ALL_DEPARTURES_BY_DEST + ". Search for departures going to destination\n"
        + SHOW_DEPARTURE_BY_ID + ". Show specific departure by Id\n"
        + DELETE_DEPARTURE_BY_ID + ". Delete specific departure by Id\n"
        + UPDATE_CLOCK + ". Updates the system clock\n"
        + EXIT + ". Exit application\n"
    );
  }

  private void updateClockAndDeleteOldDepartures() {
    Scanner inputScanner = new Scanner(System.in);

    System.out.println("Please provide the system time with format \"hh:mm\"");
    try {
      LocalTime currentTime = LocalTime.parse(inputScanner.nextLine());
      systemClock.setCurrentTime(currentTime);
      System.out.println("Successfully updated system time. New system time is: "
          + systemClock.getCurrentTime());

      int deleteCount = departureRegister.deleteOldDepartures(systemClock.getCurrentTime());

      System.out.println("Number of departures deleted from register: " + deleteCount);

    } catch (DateTimeParseException e) {
      System.out.println("Provided system time was not formatted correctly.");
    }

    holdProgramForKey();
  }

  private void deleteDepartureById() {
    System.out.println("--------------------Delete departure by id--------------------");

    Scanner inputScanner = new Scanner(System.in);
    TrainDeparture foundDeparture;

    System.out.println("Please provide an Id");
    String trainId = inputScanner.nextLine().toUpperCase();

    // If the departure doesnt exist, exit back to menu.
    if (!departureExists(trainId)) {
      return;
    }


    foundDeparture = departureRegister.getDepartureFromId(trainId);

    departureRegister.deleteDeparture(foundDeparture);
    System.out.println("Departure deleted successfully");

    holdProgramForKey();
  }



  private void showDepartureById() {
    System.out.println("--------------------Show departure by Id--------------------");

    Scanner inputScanner = new Scanner(System.in);
    TrainDeparture foundDeparture;

    System.out.println("Please provide an Id");
    String trainId = inputScanner.nextLine().toUpperCase();

    // If the departure doesnt exist, exit back to menu.
    if (!departureExists(trainId)) {
      return;
    }


    foundDeparture = departureRegister.getDepartureFromId(trainId);

    printDepartureInfo(foundDeparture);

    holdProgramForKey();
  }

  private void showAllDeparturesByDest() {
    System.out.println("--------------------Show departures by destination--------------------");

    Scanner inputScanner = new Scanner(System.in);

    System.out.println("Please provide a destination");
    String destination = inputScanner.nextLine();

    for (TrainDeparture departure : departureRegister.getDeparturesByDestination(destination)) {
      printDepartureInfo(departure);
    }


    holdProgramForKey();
  }

  private void addTrackToDeparture() {
    // TODO: Consider extracting parts of method since it is used in multiple methods
    System.out.println("--------------------Assign track to departure--------------------");

    Scanner inputScanner = new Scanner(System.in);
    TrainDeparture foundDeparture;

    System.out.println("Please provide the ID of the departure you wish to assign a track to");
    String trainId = inputScanner.nextLine().toUpperCase();

    // If the departure doesnt exist, exit back to menu.
    if (!departureExists(trainId)) {
      return;
    }


    foundDeparture = departureRegister.getDepartureFromId(trainId);
    System.out.println("Please provide the track to assign the departure");
    int track = inputScanner.nextInt();
    foundDeparture.setTrack(track);

    holdProgramForKey();
  }

  /**
   * Adds delay to specified train departure.
   */
  private void addDelayToDeparture() {
    System.out.println("--------------------Add delay to departure--------------------");

    Scanner inputScanner = new Scanner(System.in);
    TrainDeparture foundDeparture;

    System.out.println("Please provide the ID of the departure you wish to add a delay to");
    String trainId = inputScanner.nextLine().toUpperCase();

    // If the departure doesnt exist, exit back to menu.
    if (!departureExists(trainId)) {
      return;
    }


    foundDeparture = departureRegister.getDepartureFromId(trainId);
    System.out.println("Please provide the delay you wish to apply with format \"hh:mm\"");


    try {
      LocalTime delay = LocalTime.parse(inputScanner.nextLine());
      foundDeparture.setDelay(delay);
    } catch (DateTimeParseException e) {
      System.out.println("Provided delay was not formatted correctly.");
    }

    holdProgramForKey();
  }


  /**
   * Checks if departure with given Id exists in register.
   *
   * @param trainId Id of the departure.
   * @return {@code true} if departure was found {@code false} if departure was not found.
   */
  private boolean departureExists(String trainId) {
    boolean state = true;
    TrainDeparture foundDeparture = departureRegister.getDepartureFromId(trainId);

    if (foundDeparture == null) {
      System.out.println("No departure with provided ID was found. Please provide a valid ID");
      state = false;
    }

    return state;
  }

  /**
   * Prints all departures.
   * Holds program until input is provided.
   */
  private void showAllDepartures() {
    System.out.println("--------------------All departures--------------------");
    for (TrainDeparture departure : departureRegister.getAllDepartures()) {
      printDepartureInfo(departure);
    }

    holdProgramForKey();
  }

  private static void holdProgramForKey() {
    // As the menu is quite large I wait for a user input before returning to menu
    boolean next = true;
    System.out.println("\nPress enter to return to menu.\n");
    while (next) {
      if (new Scanner(System.in).nextLine() != null) {
        next = false;
      }
    }
  }

  /**
   * Add new train departure to the register from user menu.
   */
  private void addNewDeparture() {
    TrainDeparture createdDeparture = this.createDepartureFromUser(new Scanner(System.in));

    if (departureRegister.addDeparture(createdDeparture)) {
      System.out.println("Departure created succesfully!");
      printDepartureInfo(departureRegister.getDepartureFromId(createdDeparture.getTrainId()));
    } else {
      System.out.println("Departure could not be added to register.");
    }

    holdProgramForKey();
  }

  /**
   * Takes inputs from user to build a train departure.
   *
   * @param inputScanner Input scanner object
   * @return New train departure from input.
   */
  private TrainDeparture createDepartureFromUser(Scanner inputScanner) {
    // Collects input from User

    LocalTime departureTime;

    try {
      System.out.println("Enter departure time with format \"hh:mm\" : ");
      departureTime = LocalTime.parse(inputScanner.nextLine());
    } catch (DateTimeParseException e) {
      System.out.println("Provided departure time was incorrectly formatted. returning to menu");
      return null;
    }

    System.out.println("Enter destination: ");
    String destination = inputScanner.nextLine();

    System.out.println("Enter train number: ");
    String trainNumber = inputScanner.nextLine().toUpperCase();

    System.out.println("Enter line: ");
    String line = inputScanner.nextLine().toUpperCase();

    return new TrainDeparture(departureTime, destination, trainNumber, line);
  }

  /**
   * Prints a welcome message to the console as well as version number of application.
   */
  private void printWelcomeMessage() {
    System.out.println("--------------------Train Dispatch System--------------------");
    System.out.println("Current version: " + VERSION);
    System.out.println("Author: Matthew Hunt");
    System.out.println("-------------------------------------------------------------");
  }



  /**
   * Prints out all the information of a specific departure.
   * Omits delay if delay is 0.
   * Omits Track if id is -1.
   *
   * @param trainDeparture Departure to print info from.
   */
  private void printDepartureInfo(TrainDeparture trainDeparture) {
    String printString = " | Departure time: " + trainDeparture.getDeparture()
        + " | Line: " + trainDeparture.getLine()
        + " | Train Number: " + trainDeparture.getTrainId()
        + " | Destination: " + trainDeparture.getDestination();

    if (trainDeparture.getDelay() != LocalTime.of(0, 0)) {
      printString += " | Delay: " + trainDeparture.getDelay();
    }
    if (trainDeparture.getTrack() != -1) {
      printString += " | Track: " + trainDeparture.getTrack();
    }

    System.out.println(printString);
  }

}
