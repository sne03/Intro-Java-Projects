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

public class Longhorn extends Critter{
    public static final int NUM_DIRECTIONS = 4;
    public static final int MAX_INCREMENT = 10;
    Attack fightOption;
    boolean fakeAnt = true;
    int moveCount = 0;
    int chosenDirection;
    Direction currDirection;
    int numIncrement = 3;
    int stepVal = 1;

    public Longhorn(){

    }

    // gets if longhorn eats
    public boolean eat(){
        return true;
    }

    // gets fight method
    public Attack fight(String opponent){

        // checks if opponent is hippo (hippo returns an int)
        int defaultHippo = -1;
        try{
            defaultHippo = Integer.parseInt(opponent);
        }catch(Exception E){

        }

        // if ant
        if(opponent.equals("%")){
            fightOption = Attack.ROAR;
        }

        // if we are fake ant opponent is not a hippo
        else if(fakeAnt && defaultHippo < 0){
            fightOption = Attack.POUNCE;
        }
        //if hippo is hungry
        else if(defaultHippo > 0){
            fightOption = Attack.ROAR;
        }
        else{
            fightOption =  Attack.SCRATCH;
        }

        return fightOption;
    }

    // gets longhorn color
    public Color getColor(){
        return Color.ORANGE;
    }

    // gets longhorn movement
    public Direction getMove(){

        // chooses random direction for move when moveCount reaches 0
        if(moveCount == 0){
            Random chooseDirection = new Random();
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

            // numIncrement is the number of steps the animal is going to move in the next direction
            // when it reaches the max limit or 1, the steps will start counting the opposite way
            // Ex. (1 to 10 then back down from 10 to 1)
            if(numIncrement > MAX_INCREMENT || numIncrement <= 1){
                stepVal *= -1;
            }
            numIncrement += stepVal;
            moveCount = numIncrement;
        }
        moveCount--;
        return currDirection;
    }

    // returns symbol
    public String toString(){

        // if longhorn is a fake ant
        if (fakeAnt){
            return "%";
        }else{
            return "V";
        }
    }
}
