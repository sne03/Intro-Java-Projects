/*
 * CS312 Assignment 12.
 * On my honor, Sneha Kamal, this programming assignment is my own work.
 *
 * A program to print out the lyrics to the
 * children's song, "There was an old woman".
 *
 *  Name: Sneha Kamal
 *  email address: snehakamal@utexas.edu
 *  UTEID: sk52223
 *  Section 5 digit ID: 51095
 *  Grader name: Emma
 */

public class GuitarHero {
   private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
   private static final double EQUATION_CONSTANT = 440.0;
   private static final double POW_CONSTANT = 1.05956;
   private static final double POW_DIFF = 24.0;



    public static void main(String[] args) {
        Keyboard keyboard = new Keyboard();
        GuitarString guitarStrings [] = new GuitarString[KEYBOARD.length()];


        // converts ith character with frequency equation
        for(int i = 0; i < KEYBOARD.length(); i++){
            double frequency = EQUATION_CONSTANT * Math.pow(POW_CONSTANT, (i-POW_DIFF));
            guitarStrings[i] = new GuitarString(frequency);
        }


        // the main input loop
        while (true) {

            // check if the user has played a key; if so, process it
            if (keyboard.hasNextKeyPlayed()) {

                // the key the user played
                char key = keyboard.nextKeyPlayed();

                // if key is valid
                if(KEYBOARD.indexOf(key) != -1){
                    guitarStrings[KEYBOARD.indexOf(key)].pluck();
                }
            }

            // compute the superposition of the samples
            double sample = 0;
            for(int i = 0; i < KEYBOARD.length(); i++){
                sample += guitarStrings[i].sample();
                guitarStrings[i].tic();
            }

            // play the sample on standard audio
            StdAudio.play(sample);
        }
    }

}
