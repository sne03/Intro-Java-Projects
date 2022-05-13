import java.awt.*;
import java.util.Random;
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

public class Hippo extends Critter{
    public static final int NUM_DIRECTIONS = 4;
    public static final int NUM_INCREMENT = 5;
    int hungerCounter;
    int moveCount = 0;
    int chosenDirection;
    boolean isHungry = true;
    String foodToEat = "";

    Direction currDirection;

    // sets hunger counter variable equal to hunger variable passed as parameter
    public Hippo(int hunger){
        hungerCounter = hunger;
    }

    // returns if hippo eats
    public boolean eat(){

        // if hippo is hungry
        if(hungerCounter > 0){
            isHungry = true;
            hungerCounter--;

            //if hippo is not hungry
        }else{
            isHungry = false;
        }
        return isHungry;
    }

    // gets attack choice
    public Attack fight(String opponent){

        // if hippo is hungry
        if(isHungry){
            return Attack.SCRATCH;

            // if hippo is not hungry
        }else{
            return Attack.POUNCE;
        }
    }

    // returns color of hippo
    public Color getColor(){

        // if hippo is hungry
        if(isHungry){
            return Color.GRAY;

            // if hippo is not hungry
        }else{
            return Color.WHITE;
        }
    }

    // gets direction for move
    public Direction getMove(){

        // chooses new direction after 5 moves in previous direction
        if(moveCount == 0){
            Random chooseDirection = new Random();

            // gets direction based on random number value and the equivalent enum value of direction
            chosenDirection = chooseDirection.nextInt(NUM_DIRECTIONS);
            if(chosenDirection == 0){
                currDirection = Direction.NORTH;
            }
            else if(chosenDirection == 1){
                currDirection = Direction.SOUTH;
            }
            else if(chosenDirection == 2){
                currDirection = Direction.EAST;
            }else{
                currDirection = Direction.WEST;
            }

            // resets move count back to 5 after choosing new direction
            moveCount = NUM_INCREMENT;
        }
        // decrements move count
        moveCount--;
        return currDirection;
    }

    // returns string based on how much food needed to eat
    public String toString(){
        foodToEat = "" + hungerCounter;
        return foodToEat;
    }
}
