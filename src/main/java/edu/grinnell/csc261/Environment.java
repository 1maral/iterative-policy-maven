package edu.grinnell.csc261;

/**
 * Possible actions to take in the 4x4 grid environment.
 *
 * @author Maral Bat-Erdene
 * @author Sara Jaljaa
 */
public class Environment {

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Move up in the grid.
   *
   * @param current
   *    The current location of the state.
   * @return
   *    The next location of the state if the agent moved up.
   */
  static int up(int current) {
    int up = 0;
    if (current > 3) {
      up = current - 4;
    } else {
      up = current;
    } // elif
    return up;
  } // up(int)

  /**
   * Move right in the grid.
   *
   * @param current
   *    The current location of the state.
   * @return
   *    The next location of the state if the agent moved right.
   */
  static int right(int current) {
    int right = 0;
    if (current % 4 != 3) {
      right = current + 1;
    } else {
      right = current;
    } // elif
    return right;
  } // right(int)

  /**
   * Move left in the grid.
   *
   * @param current
   *    The current location of the state.
   * @return
   *    The next location of the state if the agent moved left.
   */
  static int left(int current) {
    int left = 0;
    if (current % 4 != 0) {
      left = current - 1;
    } else {
      left = current;
    } // elif
    return left;
  } // left(int)

  /**
   * Move down in the grid.
   *
   * @param current
   *    The current location of the state.
   * @return
   *    The next location of the state if the agent moved down.
   */
  static int down(int current) {
    int down = 0;
    if (current < 12) {
      down = current + 4;
    } else {
      down = current;
    } // elif
    return down;
  } // down(int)
} // class Environment
