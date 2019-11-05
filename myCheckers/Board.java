/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myCheckers;

/**
 *
 * @author Cliff
 */
public class Board {
	
    private int width;
    private int height;
    public Square[][] squares;

    public Board() {

        width = 8;		// A checker board is 8 squares wide
        height = 8;		// A checker board is 8 squares high

        squares = new Square[width][height];	// Initialize a multidimensional array for the board

        for (int x = 0; x < width; x++) {	// Loop over each column
            for (int y = 0; y < width; y++) {	// Loop over each row in the column

                Boolean xEven = ((x % 2) == 0);	// Check if x is an even number
                Boolean yEven = ((y % 2) == 0);	// Check if y is an even number

                Boolean isBlack = (xEven == yEven);	// Black squares have matching x and y coordinates

                squares[x][y] = new Square(isBlack, x, y);	// Initialize a new square at thiis coordinate

            }
        }

    }


    public int GetWidth() {
        return width;	
    }

    public int GetHeight() {
        return height;	
    }

    public Square[][] GetSquares() {
        return squares;	
    }

    public Square GetSquare(int x, int y) {
        // NOTE: this might not be the best way to handle the errors. But it is effective.

        x = (x % width);	// Make sure that we do not try for an index that does not exist
        y = (y % height);	// Make sure that we do not try for an index that does not exist

        return squares[x][y];	
    }

	
}
