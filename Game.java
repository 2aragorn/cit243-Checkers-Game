public class Game {
	
	private String[] players;
	private Board board;

	public Game() {
		players = new String[]{"Red", "Black"};
	}
	
	
	public void StartGame() {
		
		board = new Board();	// Initialize a new board
		
		SetupBoard();
		GameLoop();
	}
		
	public void GameLoop() {
		// Incomplete functions
	}
	
	
	public void EndGame() {
		// Incomplete functions
	}
	
	
	
	// --------------------------------------------------------
	// Setup functions	
	// --------------------------------------------------------
		
	private void SetupBoard() {
		AddRedCheckers();
		AddBlackCheckers();
	}
	
	private void AddRedCheckers() {
		// Incomplete functions
		
		// There are 12 red checkers added at the top of the board.
		// REMINDER: counting in arrays starts at 0
		
		for (var y = 0; y < 3; y++) {	// Loop down the first 3 rows
			for (var x = 0; x < 8; x++) {	// Loop across all 8 columns
				
				bool xEven = ((x % 2) == 0);	// Check if x is an even number
				bool yEven = ((y % 2) == 0);	// Check if y is an even number
				
				bool isBlack = (xEven == yEven);	// Black squares have matching x and y coordinates
				
				
				if (isBlack) {
					// 1.) Get this square
					// 2.) Create a new checker for this player
					// 3.) Assign this checker to the square
				}
				
			}		
		}		
	}
	
	private void AddBlackCheckers() {
		// Incomplete functions
	}
	
		
	
	
}
