package edu.grinnell.csc261;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * Uses an Iterative Policy Evaluation Algorithm (IPEA) to calculate state values
 * of a small 4x4 grid-world environment.
 *
 * @author Maral Bat-Erdene
 * @author Sara Jaljaa
 */
public class IPEA {

  // +---------------+------------------------------------------------------
  // | Static Fields |
  // +---------------+

  /* The gamma value. */
  private static final double  GAMMA = 1;

  /* The reinforcement value. */
  private static final int R = -1;

  /* The length of the array. */
  private static final int SNUM = 16;

  /* The theta value. */
  private static final double THETA = 0.05;

  double[] actions = new double[4];

  // +--------+-------------------------------------------------------------
  // | Fields |
  // +--------+

  /* The minimum delta value. */
  private double delta = THETA;

  /* The array that holds the current state-values. */
  double states[] = new double[SNUM];

  // +-------------+--------------------------------------------------------
  // | Main Method |
  // +-------------+

  /**
   * Run the Iterative Policy Evaluation Algorithm (k + 1) times.
   *
   * @pre
   *    The value of k is non-negative.
   *
   * @param args
   *    Command line arguments (currently ignored).
   */
  public static void main(String args[]) {
    IPEA states = new IPEA();
    states.calc(10);
  } // main(String[])

  // +----------------+-----------------------------------------------------
  // | Helper Methods |
  // +----------------+

  /**
   * Initializes the grid with the same value in all cells.
   *
   * @param val
   *    The value to initialize with.
   */
  private void initialize(double val) {
    for (int i = 0; i < SNUM; i++) {
      this.states[i] = val;
    } // for(i)
  } // initialize(double)

  /**
   * Calculate the delta value/new arg max.
   *
   * @param previous
   *    The previous state value, v.
   * @param current
   *    The current state value, V(s).
   * @param delta
   *    The difference, |v - V(s)|.
   * @return
   *    The delta value.
   */
  private static double diff(double previous, double current, double delta) {
    double change = Math.abs(previous - current);
    /* If a state is terminal or the state-value difference is less than the
    current delta value, the delta value is updated. */
    if (change < delta && change != 0) {
      delta = change;
    } // if
    return delta;
  } // diff(double, double, double)

  /**
   * Format the state-value table.
   *
   * @param count
   *    The current iteration, 0-based.
   */
  private void printing(int count) {
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.print("k = " + count);
    /* Iteratively print each line of the state-value table. */
    for (int i = 0, j = 0; (i < SNUM); i++, j++) {
      /* If in column 1 (e.g. A/E/I/M), print the row on a new line. */
      if (j % 4 == 0) {
        j = 0;
        pen.println();
      } // if
      DecimalFormat df = new DecimalFormat("0.00");
      /* Lazy spacing adjustment for terminal states. */
      if (((Double) this.states[i]).equals(0.0)) {
        pen.print(" ");
      } // if
      pen.print("  " + df.format(round(this.states[i])));
    } // for(i, j)
    pen.println('\n');
  } // printing(int)

  private void values(double[] copy, int s) {
    this.actions[0] = 0.25 * (R + (GAMMA * copy[Environment.left(s)]));
    this.actions[1] = 0.25 * (R + (GAMMA * copy[Environment.right(s)]));
    this.actions[2] = 0.25 * (R + (GAMMA * copy[Environment.up(s)]));
    this.actions[3] = 0.25 * (R + (GAMMA * copy[Environment.down(s)]));
    DecimalFormat df = new DecimalFormat("0.0000");
    System.out.println(
        "  " + df.format(this.actions[2])    // up
        + " " + df.format(this.actions[3])   // down
        + " " + df.format(this.actions[1])   // right
        + " " + df.format(this.actions[0])); // left
    } // values(double[], int)

  // +----------------+-----------------------------------------------------
  // | Static Methods |
  // +----------------+

  /**
   * Rounds a decimal to the nearest tens place.
   *
   * @param val
   *    The decimal to round.
   * @return
   *    The rounded decimal.
   */
  private static double round(double val) {
    return Math.round(((int) (val * Math.pow(10, 2)))) / Math.pow(10, 2);
  } // round(double)

  /**
   * Iteratively calculate state values.
   *
   * @param k
   *    The number of iterations.
   */
  public void calc(int k) {
    /* Initialize the state-values to 0. */
    initialize(0.0);
  
    /* For printing purposes. */
    int count = 0;
    /* The previous state-value & recently calculated state-value. */
    double prev, newval;
    
    /* Iterate (k + 1) times or until the difference from each state
    is negligible.*/
    while ((delta >= THETA) && (count < (k + 1))) {
      this.printing(count);
      count++;

      /* Calculate the new state-value from the previous state-values. */
      double[] copy = Arrays.copyOf(this.states, SNUM);

      System.out.println("      UP      DOWN    RIGHT   LEFT");
      for (int s = 0; (s < SNUM); s++) {
        prev = this.states[s];
        /* If the state is a terminal, it has a value of 0. */
        if (s == 0 || s == 15) {
          newval = 0;
        } else {
          /* Formatting table. */
          System.out.print((char) (s + 97) + ": ");

          /* If the state is non-terminal, add all the action-values of the
          current state to find the delta & new state-value. */
          values(copy, s);
          newval = actions[0] + actions[1] + actions[2] + actions[3];
        } // elif
        this.states[s] = newval;
        delta = diff(prev, newval, delta);
      } // for(s)
      System.out.println("");
    } // while
  } // calc(int)
} // class IPEA
