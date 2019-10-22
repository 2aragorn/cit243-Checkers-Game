public class Game {
	
	private String[] players;
	private Board board;
	private string currentPlayer;

	public Game() {
		players = new String[]{"Red", "Black"};
	}
	
	
	public void StartGame() {
		
		board = new Board();	// Initialize a new board
		
		
		SetupBoard();
		
		currentPlayer = players[0];
			
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
		// There are 12 red checkers added at the top of the board.
		// REMINDER: counting in arrays starts at 0
		
		for (var y = 0; y < 3; y++) {	// Loop down the first 3 rows
			for (var x = 0; x < 8; x++) {	// Loop across all 8 columns
				
				bool xEven = ((x % 2) == 0);	// Check if x is an even number
				bool yEven = ((y % 2) == 0);	// Check if y is an even number
				
				bool isBlack = (xEven == yEven);	// Black squares have matching x and y coordinates
				
				
				if (isBlack) {
					Checker checker = new Checker("Red");		// Initialize a new RED checker
					board.squares[x][y].SetChecker(checker);	// Place the checker on the board
				}
				
			}		
		}		
	}
	
	private void AddBlackCheckers() {	
		// There are 12 black checkers added at the bottom of the board.
		// REMINDER: counting in arrays starts at 0 and ends at 7
		
		for (var y = 7; y > 4; y--) {	// Loop up the first 3 rows
			for (var x = 0; x < 8; x++) {	// Loop across all 8 columns
				
				bool xEven = ((x % 2) == 0);	// Check if x is an even number
				bool yEven = ((y % 2) == 0);	// Check if y is an even number
				
				bool isBlack = (xEven == yEven);	// Black squares have matching x and y coordinates
				
				
				if (isBlack) {
					Checker checker = new Checker("Black");		// Initialize a new BLACK checker
					board.squares[x][y].SetChecker(checker);	// Place the checker on the board
				}
				
			}		
		}		
	}
	
		
	
	
}
