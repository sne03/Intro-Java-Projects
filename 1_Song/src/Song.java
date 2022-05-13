/*
 * CS312 Assignment 1.
 * On my honor,Sneha Kamal, this programming assignment is my own work.
 *
 * A program to print out the lyrics to the
 * children's song, "There was an old woman".
 *
 *  Name: Sneha Kamal
 *  email address: snehakamal@utexas.edu
 *  UTEID: sk52223
 *  Section 5 digit ID: 51095
 *  Grader name: Emma Simon
 */

public class Song {
    public static void main(String[] args) {
        firstStanza();
        secondStanza();
        thirdStanza();
        fourthStanza();
        fifthStanza();
        sixthStanza();
        seventhStanza();
        eighthStanza();
    }

    // print stanza ending lines
    public static void stanzaFinisher() {
        System.out.println("I don't know why she swallowed that fly,");
        System.out.println("Perhaps she'll die.");
        System.out.println();
    }

    // prints spider line and finishes stanza
    public static void spiderLine() {
        System.out.println("She swallowed the spider to catch the fly,");
        stanzaFinisher();
    }

    // prints bird line and finishes stanza
    public static void birdLine() {
        System.out.println("She swallowed the bird to catch the spider,");
        spiderLine();
    }

    // prints cat line and finishes stanza
    public static void catLine() {
        System.out.println("She swallowed the cat to catch the bird,");
        birdLine();
    }

    // prints dog line and finishes stanza
    public static void dogLine() {
        System.out.println("She swallowed the dog to catch the cat,");
        catLine();
    }

    // prints goat line and finishes stanza
    public static void goatLine() {
        System.out.println("She swallowed the goat to catch the dog,");
        dogLine();
    }

    // prints cow line and finishes stanza
    public static void cowLine() {
        System.out.println("She swallowed the cow to catch the goat,");
        goatLine();

    }

    // prints first stanza
    public static void firstStanza() {
        System.out.println("There was an old woman who swallowed a fly.");
        stanzaFinisher();
    }

    // prints second stanza
    public static void secondStanza () {
        System.out.println("There was an old woman who swallowed a spider,");
        System.out.println("That wriggled and iggled and jiggled inside her.");
        spiderLine();
    }

    // prints third stanza
    public static void thirdStanza (){
        System.out.println("There was an old woman who swallowed a bird,");
        System.out.println("How absurd to swallow a bird.");
        birdLine();
    }

    // prints fourth stanza
    public static void fourthStanza () {
        System.out.println("There was an old woman who swallowed a cat,");
        System.out.println("Imagine that to swallow a cat.");
        catLine();
    }

    // prints fifth stanza
    public static void fifthStanza () {
        System.out.println("There was an old woman who swallowed a dog,");
        System.out.println("What a hog to swallow a dog.");
        dogLine();
    }

    // prints sixth stanza
    public static void sixthStanza () {
        System.out.println("There was an old woman who swallowed a goat,");
        System.out.println("She just opened her throat to swallow a goat.");
        goatLine();
    }

    // prints seventh stanza
    public static void seventhStanza (){
        System.out.println("There was an old woman who swallowed a cow,");
        System.out.println("I don't know how she swallowed a cow.");
        cowLine();
    }

    // prints eighth stanza
    public static void eighthStanza () {
        System.out.println("There was an old woman who swallowed a horse,");
        System.out.println("She died of course.");
    }
}
