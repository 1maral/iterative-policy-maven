package edu.grinnell.csc261;

import java.util.Arrays;
import java.io.PrintWriter;
import java.text.DecimalFormat;

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
  static final int GAMMA = 1;

  /* The reinforcement value. */
  static final int R = -1;

  /* The length of the array. */
  static final int SNUM = 16;

  /* The theta value. */
  static final double THETA = 0.05;

  // +--------+-------------------------------------------------------------
  // | Fields |
  // +--------+

  /* The minimum delta value. */
  double delta = THETA;

  /* The array that holds the current state-values. */
  double states[] = new double[SNUM];

  // +-----------------------+----------------------------------------------
  // | Static Helper Methods |
  // +-----------------------+

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
   * Initializes the grid with the same value in all cells.
   *
   * @param val
   *    The value to initialize with.
   */
  private static void initialize(double[] states, double val) {
    for (int i = 0; i < SNUM; i++) {
      states[i] = val;
    } // for(i)
  } // initialize(double[], double)

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
    if (change < delta && change != 0) {
      delta = change;
    } // if
    return delta;
  } // diff(double, double, double)

  /**
   * Formats state-values.
   *
   * @param count
   *    The current iteration, 0-based.
   */
  private void printing(int count) {
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.print("k = " + count);
    for (int i = 0, j = 0; (i < SNUM); i++, j++) {
      if (j % 4 == 0) {
        j = 0;
        pen.println();
      } // if
      DecimalFormat df = new DecimalFormat("0.0");
      if (((Double) this.states[i]).equals(0.0)) {
        pen.print(" ");
      } // if
      pen.print("  " + df.format(round(this.states[i])));
    } // for(i, j)
    pen.println('\n');
  } // printing(int)

  // +----------------+-----------------------------------------------------
  // | Static Methods |
  // +----------------+

  /**
   * Iteratively calculate state values.
   *
   * @param k
   *    The number of iterations.
   */
  public void calc(int k) {
    initialize(this.states, 0.0);
    int count = 0;
    double prev, newval;
    
    /* Iteratively (k + 1) times or until the difference from each state
    is negligible.*/
    while ((delta >= THETA) && (count < (k + 1))) {
      this.printing(count);
      count++;

      /* Calculate the new state-value from the previous state-values. */
      double[] copy = Arrays.copyOf(this.states, SNUM);

      for (int s = 0; (s < SNUM); s++) {
        prev = this.states[s];
        /* If the state is a terminal, it must have a value of 0. */
        if (s == 0 || s == 15) {
          newval = 0;
        } else {
          /* If the state is non-terminal, add all the action-values of the
          current state to find the delta & new state value. */
          newval = (0.25 * (R + (GAMMA * copy[Environment.left(s)])))
              + (0.25 * (R + (GAMMA * copy[Environment.down(s)])))
              + (0.25 * (R + (GAMMA * copy[Environment.right(s)])))
              + (0.25 * (R + (GAMMA * copy[Environment.up(s)])));
        } // elif
        this.states[s] = newval;
        delta = diff(prev, newval, delta);
      } // for(s)
    } // while
  } // calc(int)

/**
 * Run the Iterative Policy Evaluation Algorithm (k + 1) times.
 *
 * @param args
 *    Command line arguments (currently ignored).
 */
  public static void main(String args[]) {
    IPEA states = new IPEA();
    states.calc(10);
  } // main(String[])
} // class IPEA
