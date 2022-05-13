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

public class Bird extends Critter {

    // increment amount for each direction
    public static final int NUM_INCREMENT = 3;

    // number of possible directions
    public static final int NUM_DIRECTIONS = 4;
    int moveCount = -1;
    Direction lastDirection;

    public Bird(){

    }

    // returns if eats
    public boolean eat(){
        return false;
    }

    // returns attack option
    public Attack fight(String opponent){

        // if opponent is ant
        if(opponent.equals("%")){
            return Attack.ROAR;
        }else{
            return Attack.POUNCE;
        }
    }

    // gets color
    public Color getColor(){
        return Color.BLUE;
    }

    // gets move
    public Direction getMove(){
        moveCount++;

        // calculates move direction
        int calcMove = (moveCount / NUM_INCREMENT) % NUM_DIRECTIONS;
        if(calcMove == 0){
            lastDirection = Direction.NORTH;
        }
        else if(calcMove == 1){
            lastDirection = Direction.EAST;
        }
        else if(calcMove == NUM_INCREMENT - 1) {
            lastDirection = Direction.SOUTH;
        }else{
            lastDirection = Direction.WEST;
        }
        return lastDirection;
    }

    // returns symbol based on direction for the last move
    public String toString(){
        if(lastDirection == Direction.NORTH || lastDirection == Direction.CENTER){
            return "^";
        }
        else if(lastDirection == Direction.EAST){
            return ">";
        }
        else if(lastDirection == Direction.SOUTH){
            return "V";
        }else{
            return "<";
        }
    }
}
