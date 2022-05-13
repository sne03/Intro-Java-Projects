import java.awt.*;
/**
 * CS312 Assignment 11.
 *
 * On MY honor, Sneha Kamal, this programming assignment is MY own work
 * and I have not provided this code to any other student.
 *
 * Student name: Sneha Kamal
 * UTEID: sk52223
 * email address: snehakamal@utexas.edu
 * Grader name: Emma
 * Number of slip days used on this assignment: 2
 *
 */

public class Ant extends Critter{

    // keeps track of direction
    boolean walkSouth;
    boolean currEast;

    // checks direction
    public Ant (boolean walkSouth){
        this.walkSouth = walkSouth;
        currEast = true;
    }

    // gets whether ant eats
    public boolean eat(){
        return true;
    }

    // sets attack method
    public Attack fight(String opponent){
        return Attack.SCRATCH;
    }

    // gets color
    public Color getColor(){
        return Color.RED;
    }

    // gets movement direction
    public Direction getMove(){
        // alternates direction from east to north or south
        currEast = !currEast;

        // if set to walk south first
        if(walkSouth){
            if(currEast){
                return Direction.EAST;
            }else{
                return Direction.SOUTH;
            }

            // if set to walk north first
        }else{
            if(currEast){
                return Direction.EAST;
            }else{
                return Direction.NORTH;
            }
        }
    }

    // returns string symbol
    public String toString(){
        return "%";
    }
}
