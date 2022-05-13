import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;

    /**
     * CS312 Assignment 3.
     *
     * Replace <NAME> with your name, stating on your honor you completed this
     * assignment on your own and that you didn't share your code with other
     * students.
     *
     * On my honor, Sneha Kamal, this programming assignment is my own work and I have
     * not shared my solution with any other student in the class.
     *
     * A program to print out various scintillation grids and a student designed drawing.
     *
     *  email address: snehakamal@utexas.edu
     *  UTEID: sk52223
     *  Number of slip days I am using on this assignment: 0
     */

    public class ScintillationGrid {

        // Main method that creates the DrawingPanel with scintillation grids.
        // Restricted to chapters 1 - 3 of Building Java Programs
        public static void main(String[] args) {
        /* In the final version of the program DO NOT call method drawingOne
           from main or anywhere else in the program */
            final int WIDTH = 950;
            final int HEIGHT = 650;
            DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
            Graphics g = dp.getGraphics();
            // CS312 Students add you four methods calls to draw the four
            // required scintillation grids here.
            g.setColor(Color.CYAN);
            g.fillRect(0,0,WIDTH,HEIGHT);
            createScintillationGrid(g, 0, 0, 348, 75, 3, 16);
            createScintillationGrid(g, 400, 50, 422, 50, 6, 12);
            createScintillationGrid(g, 50, 400, 220, 100, 1 , 20);
            createScintillationGrid(g, 500, 500, 148, 15, 7, 4);
            // CS312 students, do not alter the following line of code.
            saveDrawingPanel(dp, "grid.png");
        }

        // method for the student designed drawing
        // NOT restricted to chapters 1 - 3 of Building Java Programs
        // DO NOT ADD ANY PARAMETERS TO THIS METHOD!!!
        public static void drawingOne() {
            // DO NOT ADD ANY PARAMETERS TO THIS METHOD!!!

            // CS312 Students, you may increase the size of the drawing panel if
            // doing a non standard drawing.
            final int WIDTH = 400;
            final int HEIGHT = 200;
            DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
            // CS312 Students, add your code, including method calls here
            Graphics g = dp.getGraphics();
            int halfWidth = WIDTH / 2;
            int halfHeight = HEIGHT / 2;
            int quadrantWidth = WIDTH / 4;
            int quadrantHeight = HEIGHT / 4;
            int lowerQuadrantWidth = (WIDTH * 3) / 4;
            int lowerQuadrantHeight = (HEIGHT * 3) / 4;
            //set background to green
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            //divide into four quadrants
            g.setColor(Color.BLACK);
            g.drawLine(halfWidth, 0, halfWidth, HEIGHT);
            g.drawLine(0, halfHeight, WIDTH, halfHeight);
            //draw ovals
            g.setColor(Color.RED);
            g.fillOval(0, 0, halfWidth, halfHeight);
            g.fillOval(halfWidth, halfHeight, halfWidth, halfHeight);
            //draw oval lines
            g.setColor(Color.BLACK);
            g.drawLine(quadrantWidth, 0, quadrantWidth, halfHeight);
            g.drawLine(0, quadrantHeight, halfWidth, quadrantHeight);
            g.drawLine(lowerQuadrantWidth, halfHeight, lowerQuadrantWidth, HEIGHT);
            g.drawLine(halfWidth, lowerQuadrantHeight, WIDTH, lowerQuadrantHeight);
            // Do not alter this line of code. It saves the panel to a file for later viewing
            saveDrawingPanel(dp, "drawing_one.png");
        }

        // Save the current drawing panel to the given file.
        // CS312 Students, do not alter this method.
        public static void saveDrawingPanel(DrawingPanel dp, String fileName) {
            try {
                dp.save(fileName);
            } catch (IOException e) {
                System.out.println("Unable to save DrawingPanel");
            }
        }

        //draws scintillation grids
        public static void createScintillationGrid(Graphics g, int x, int y, int largeSquareSize,
                                                   int smallSquareSize, int numLines, int lineThickness) {
            createLargeSquare(g, x, y,largeSquareSize);
            createLines(g, x, y, numLines, lineThickness, smallSquareSize, largeSquareSize);
            createCircles(g, x, y, numLines, lineThickness, smallSquareSize, largeSquareSize);
        }

        //draws large square
        public static void createLargeSquare (Graphics g, int xUpperLeftLargeSquare, int yUpperLeftLargeSquare,
                                              int largeSquareSize) {
            g.setColor(Color.BLACK);
            g.fillRect(xUpperLeftLargeSquare, yUpperLeftLargeSquare, largeSquareSize, largeSquareSize);
        }

        //draws horizontal and vertical lines
        public static void createLines(Graphics g, int xUpperLeftLargeSquare, int yUpperLeftLargeSquare,
                                       int numLines, int lineThickness, int smallSquareSize, int largeSquareSize){
            int xLinePosition = xUpperLeftLargeSquare +smallSquareSize;
            int yLinePosition = yUpperLeftLargeSquare;
            int lineSpacing = lineThickness + smallSquareSize;
            int resetLine = xUpperLeftLargeSquare + smallSquareSize + lineSpacing;
            int lineWidth = lineThickness;
            int lineHeight = largeSquareSize;
            int swapLine;
            g.setColor(Color.GRAY);
            for(int linesDrawn = 0; linesDrawn < numLines; linesDrawn++){
                //draws horizontal line
                g.fillRect(xLinePosition, yLinePosition, lineWidth, lineHeight);
                //swap position values to make conjugate vertical line
                xLinePosition = xUpperLeftLargeSquare;
                yLinePosition = yUpperLeftLargeSquare + smallSquareSize + linesDrawn * lineSpacing;
                //swap variable values for width and height of vertical line
                swapLine = lineThickness;
                lineWidth = lineHeight;
                lineHeight = swapLine;
                //draws vertical line
                g.fillRect(xLinePosition, yLinePosition, lineWidth, lineHeight);
                //reset line position
                xLinePosition = resetLine + lineSpacing * linesDrawn;
                yLinePosition = yUpperLeftLargeSquare;
                //swap values back to draw horizontal lines
                swapLine = lineWidth;
                lineWidth = lineHeight;
                lineHeight = swapLine;
            }
        }

        //draws intersecting circles
        public static void createCircles(Graphics g, int xUpperLeftLargeSquare, int yUpperLeftLargeSquare,
                                         int numLines, int lineThickness, int smallSquareSize, int largeSquareSize){
            int xEndOfLine = xUpperLeftLargeSquare + smallSquareSize;
            int yEndOfLine = yUpperLeftLargeSquare + smallSquareSize;
            int circleDisplacement = lineThickness / 4;
            int xCircle = xEndOfLine - circleDisplacement;
            int yCircle = yEndOfLine - circleDisplacement;
            int circleDiameter = lineThickness + lineThickness / 2;
            int circleSpacing = lineThickness + smallSquareSize;
            int resetCircle = xCircle;
            int maxColumns = numLines - 1;
            int maxRows = numLines;
            g.setColor(Color.WHITE);
            //draws amount of circles per row
            for(int row = 0; row < maxRows; row++){
                g.fillOval(xCircle, yCircle, circleDiameter, circleDiameter);
                for(int column = 0; column < maxColumns; column++){
                    xCircle += circleSpacing;
                    g.fillOval(xCircle, yCircle, circleDiameter, circleDiameter);
                }
                //resets circle position to starting point and moves to next row
                xCircle = resetCircle;
                yCircle  += circleSpacing;
            }
        }
    }


