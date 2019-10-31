public class Game {
	
	private String[] players;
	private Board board;
	
	private string currentPlayer;
	boolean canChangePiece;
	boolean chosePiece;
	int chosenX;
	int chosenY;
	
	

	public Game() {
		players = new String[]{"Red", "Black"};
	}
	
	
	public void StartGame() {		
		board = new Board();	// Initialize a new board
		
		SetupBoard();
		currentPlayer = players[0];
	}
	
	public void EndGame() {
		// Incomplete function
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
		
	
	
	
	
	// --------------------------------------------------------
	// Game Play functions
	// --------------------------------------------------------
	
		// The players can trigger to actions in the code
		
			// Choose piece on square
				// Check if square has piece
				// Check if piece belongs to player
				// Check if piece can move
					// Get valid movement squares
				
				
			// Move piece to square
				// Check if move to destination square is valid
				// Update source and destination squares
				// Remove any captured pieces
				// Change player
					// Check if at least one piece can move
			

		public void ChoosePiece(int x, int y) {
			
			if (canChangePiece) {
				chosePiece = false;
				
				if (board.squares[x][y].IsOccupied()) {
					
					Checker checker = board.squares[x][y].checker;
					
					if (checker.player == currentPlayer) {
						
						boolean canMove = CanMove(x, y);
						
						if (canMove) {
							chosePiece = true;
							chosenX = x;
							chosenY = y;
						}
						else {
							// The checker is boxed in and cannot move
						}
					}
					else {
						// The checker does not belong to this player
					}				
				}
				else {
					// There is no checker on this square
				}
			}
		}
	
		public void MoveToSquare(int x, int y) {
			if (chosePiece) {	// We already have a piece chosen and ready to move
				
				Square[] validSquares = ValidMovements(chosenX, chosenY);
				
				for (int i = 0; i < validSquares.length; i++) {	// Check to see if our X and Y are in the valid squares
					
					if ((validSquares[i].x == x) && (validSquares[i].y == y)) {	// This is a valid square!
					
						// Remove any captured pieces
							RemoveCapturedPieces(chosenX, chosenY, x, int y);
							
						// Move the checker to the new square
							board.squares[x][y].SetChecker(board.squares[chosenX][chosenY].checker);
							board.squares[chosenX][chosenY].checker.RemoveChecker();
							
							if (CanJump(x, y)) {	// This player must continue moving this piece
								canChangePiece = false;
								chosenX = x;
								chosenY = y;
							} 
							else {
								UpdatePlayer();
							}
						
						break;
					}
					
				}
				
			}
		}
	
	
	
		private boolean CanMove(int x, int y) {
			Square[] validSquares = ValidMovements(x, y);
			
			boolean canMove = (validSquares.length > 0);
			
			return (canMove);
		}
	
		private void UpdatePlayer() {
			
			// Check if the game is over
				boolean gameOver = IsGameOver();
			
			if (!gameOver) {
			
				// Reset the chosePiece variable because the new player has not chosen a piece yet
					chosePiece = false;
					canChangePiece = true;
					
				// Update the player's name
					if (currentPlayer == players[0]) {
						currentPlayer = players[1];
					}
					else {
						currentPlayer = players[0];
					}
					
				// Check if the current player can move (they might be boxed in)
					if (!CanPlayerMove(currentPlayer)) {
						UpdatePlayer();	// If the player cannot move then change back to the other player
					}
			}
			else {
				EndGame();
			}
		}
		
		private boolean CanPlayerMove(String player) {
			boolean canMove = false;
			
			
			for (int x = 0; x < 8; x++) {	// Loop over each column
			
				if (!canMove) {	// Only loop if we haven't found a piece that can move yet
					for (int y = 0; y < 8; y++) {	// Loop over each row in the column
						
						if (board.squares[x][y].IsOccupied()) {	// If the square is occupied
							if (board.squares[x][y].checker.GetPlayer() == player) {	// If the piece belongs to this player
								if (CanMove(x, y)) {	// If the piece can move
									canMove = true;
									break;
								}
							}
						}
						
					}
				}
				
			}
			
			return (canMove);
		}
		
		private boolean IsGameOver() {
			
			boolean gameOver = true;
			
			
			boolean player1Alive = false;
			boolean player2Alive = false;
			
			for (int x = 0; x < 8; x++) {	// Loop over each column
				
				if (gameOver) {
					for (int y = 0; y < 8; y++) {	// Loop over each row in the column
						
						if (board.squares[x][y].IsOccupied()) {	// If the square is occupied
							if (board.squares[x][y].checker.GetPlayer() == players[0]) {
								player1Alive = true;
							}
							else {
								player2Alive = true;
							}
						}
						
						if (player1Alive && player2Alive) {
							gameOver = false;
							break;
						}
						
					}
				}
			}
			
			return (gameOver);
		}
		
		
		
		
		
		private boolean CanJump(int x, int y) {
			// Incomplete function
			boolean canJump = false;
			
			return (canJump);
		}
		
		private Square[] ValidMovements(int x, int y) {
			// Incomplete function
			Square[] validSquares;
			
			return (validSquares);
		}
		
		private void RemoveCapturedPieces(int fromX, int fromY, int destX, int destY) {			
			// Incomplete function
			
			// If the a piece was captured then there would have been
			// a diagonal jump. We need to find the square between the diagonal
			// locations and remove any piece from it.
			
			
			// Case 1: The user moved one space and did NOT capture a piece
			// x o x
			// o x x
			
			// Case 2: The user jumped and captured a piece
			// x x o
			// x x x
			// o x x
			
			
			
		}
		
		
		
		public void RedrawBoard() {
			// - Update the player's label
			// - Draw the colored squares on the board
			// - Draw the checkers on the board
			
			// Incomplete function
		}
	
}
