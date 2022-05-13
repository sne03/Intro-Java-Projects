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
public class GuitarString {
private static final int SAMPLING_RATE = 44100;
private static final double DECAY_FACTOR = 0.994;

private RingBuffer gsBuffer;
private int stringLength;
private int tics;

    // create a guitar string of the given frequency, using a sampling rate of 44,100
    public GuitarString(double frequency){
        int desiredCapacity = (int) Math.ceil (SAMPLING_RATE / frequency);
        stringLength = desiredCapacity;
        gsBuffer = new RingBuffer(desiredCapacity);

        // initializes string buffer to 0
        for (int i = 0; i < desiredCapacity; i++){
            gsBuffer.enqueue(0);
        }
    }

    // create a guitar string whose size and initial values are given by the array
    public GuitarString(double[] init){
        gsBuffer = new RingBuffer (init.length);
        for(int i = 0; i < init.length; i++){
            gsBuffer.enqueue(init[i]);
        }
    }

    // set the buffer to white noise
    public void pluck(){
        while(!gsBuffer.isEmpty()){
            gsBuffer.dequeue();
        }
        for(int i = 0; i < stringLength; i++){
            double random = Math.random() - 0.5;
            gsBuffer.enqueue((random));
        }
    }

    // advance the simulation one time step
    public void tic(){
        double removeFirst = gsBuffer.dequeue();
        double newFirst = sample();
        gsBuffer.enqueue(((removeFirst + newFirst) / 2) * DECAY_FACTOR);
        tics++;
    }

    // return the current sample
    public double sample(){
        return gsBuffer.peek();
    }

    // return number of tics
    public int time(){
        return tics;
    }
}
