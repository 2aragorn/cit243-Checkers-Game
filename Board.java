
public class Board {
	
	private width;
	private height;
	private Square[][] squares;
	
	public Board() {
		
		width = 8;		// A checker board is 8 squares wide
		height = 8;		// A checker board is 8 squares high
		
		squares = new Square[width][height];	// Initialize a multidimensional array for the board
		
		for (int x = 0; x < width; x++) {	// Loop over each column
			for (int y = 0; y < width; y++) {	// Loop over each row in the column
				
				bool xEven = ((x % 2) == 0);	// Check if x is an even number
				bool yEven = ((y % 2) == 0);	// Check if y is an even number
				
				bool isBlack = (xEven && yEven);	// Black squares must have even x and y coordinates
				
				squares[x][y] = new Square(isBlack);	// Initialize a new square at thiis coordinate
				
			}
		}
		
	}
	
}
