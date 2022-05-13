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

import java.util.NoSuchElementException;

public class RingBuffer {
    private int size;
    private int firstIndex;
    private int lastIndex;
    private double [] buffer;

    // create an empty ring buffer, with given max capacity
    public RingBuffer(int capacity){
        size = 0;
        firstIndex = 0;
        lastIndex = firstIndex;
        buffer = new double[capacity];
    }

    // return number of items currently in the buffer
    public int size(){
        return size;
    }

    // is the buffer empty (size equals zero)?
    public boolean isEmpty(){
        return size == 0;
    }

    // is the buffer full  (size equals capacity)?
    public boolean isFull(){
        return size == buffer.length;
    }

    // add item x to the end (as long as the buffer is not full)
    public void enqueue(double x){
        if(isFull()){
            throw new IllegalStateException("Cannot call enqueue on a full RingBuffer");
        }else{
            buffer[lastIndex] = x;

            // wraps around
            lastIndex = (lastIndex + 1) % buffer.length;
            size++;
        }
    }

    // delete and return item from the front (as long as the buffer is not empty)
    public double dequeue(){
        if(isEmpty()) {
            throw new NoSuchElementException("Cannot call dequeue on an empty RingBuffer.");
        }else{
            double current = buffer[firstIndex];

            // wraps around
            firstIndex = (firstIndex + 1) % buffer.length;
            size--;
            return current;
        }
    }

    // return (but do not delete) item from the front of the buffer
    double peek(){
        if(!isEmpty()){
            return buffer[firstIndex];
        }
        throw new NoSuchElementException("Cannot call peek on an empty RingBuffer.");
    }

    // override toString. Return a String of the form [front, next, next, last]
    public String toString(){
        String toReturn = "[";
        int index = firstIndex;

        // concatenates buffer to string
        for(int i = 0; i < size; i++){
            toReturn += buffer[index];
            toReturn += ", ";
            index = (index + 1) % buffer.length;
        }

        // gets rid of extra comma and space
        if(size > 0){
            toReturn = toReturn.substring(0, (toReturn.length() - 2));
        }
        toReturn += "]";
        return toReturn;
    }
}
