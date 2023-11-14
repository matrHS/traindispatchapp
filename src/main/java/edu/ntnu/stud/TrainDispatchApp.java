package edu.ntnu.stud;

import edu.ntnu.stud.ui.UserInterface;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  // TODO: Fill in the main method and any other methods you need.

  /**
   * Main method.
   *
   * @param arg arguments for the main methods.
   */
  public static void main(String[] arg) {
    UserInterface userInterface = new UserInterface();
    userInterface.init();
    userInterface.start();

  }
}


