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

public class Vulture extends Bird{
    boolean isHungry = true;
    public Vulture(){

    }

    // gets if vulture is hungry
    public boolean eat(){
        isHungry = false;
        return isHungry;
    }

    // gets vulture attack method
    public Attack fight(String opponent){
       isHungry = true;
       return super.fight(opponent);
    }

    // gets vulture color
    public Color getColor(){
        return Color.BLACK;
    }

}
