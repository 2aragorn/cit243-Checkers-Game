/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myCheckers;

import java.awt.Color;

/**
 *
 * @author Cliff
 */


                //System.out.println("Movement squares..." + x + " - " + y);
                //System.out.println("Movement squares..." + bottomLeftX + " - " + bottomLeftY);
public class Game {
	
    private String[] players;
    private Board board;

    private boolean gameStarted;
    private boolean gameOver;
    private String currentPlayer;
    boolean canChangePiece;
    boolean chosePiece;
    int chosenX;
    int chosenY;



    public Game() {
        players = new String[]{"Red", "Black"};
        gameStarted = false;
        chosePiece = false;
        chosenX = -1;
        chosenY = -1;
    }


    public void StartGame() {		
        board = new Board();	// Initialize a new board

        SetupBoard();
        currentPlayer = players[0];
        canChangePiece = true;
        gameStarted = true;
        gameOver = false;
    }

    public void EndGame() {
        gameStarted = false;
        gameOver = true;
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

                        Boolean xEven = ((x % 2) == 0);	// Check if x is an even number
                        Boolean yEven = ((y % 2) == 0);	// Check if y is an even number

                        Boolean isBlack = (xEven == yEven);	// Black squares have matching x and y coordinates


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

                        Boolean xEven = ((x % 2) == 0);	// Check if x is an even number
                        Boolean yEven = ((y % 2) == 0);	// Check if y is an even number

                        Boolean isBlack = (xEven == yEven);	// Black squares have matching x and y coordinates


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

                if (gameStarted && canChangePiece) {
                    chosePiece = false;

                    if (board.squares[x][y].IsOccupied()) {

                        Checker checker = board.squares[x][y].GetChecker();

                        if (checker.GetPlayer() == currentPlayer) {

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
                    if (canChangePiece) {
                        chosePiece = false; // Default reset
                    }

                    Square[] validSquares = ValidMovements(chosenX, chosenY);

                    for (int i = 0; i < validSquares.length; i++) {	// Check to see if our X and Y are in the valid squares

                        if ((validSquares[i].GetX() == x) && (validSquares[i].GetY() == y)) {	// This is a valid square!

                            // Remove any captured pieces
                                RemoveCapturedPieces(chosenX, chosenY, x, y);

                            // Move the checker to the new square
                                board.squares[x][y].SetChecker(board.squares[chosenX][chosenY].checker);
                                board.squares[chosenX][chosenY].RemoveChecker();
                                TryCrowningPiece(x, y);

                                boolean didJump = (Math.abs(chosenX - x) > 1);
                                
                                if (didJump && CanJump(x, y)) {	// This player must continue moving this piece
                                    canChangePiece = false;
                                    chosePiece = true;
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
                            canChangePiece = true;
                            chosePiece = false;

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

            private void RemoveCapturedPieces(int fromX, int fromY, int destX, int destY) {

                // If the a piece was captured then there would have been
                // a diagonal jump. We need to find the square between the diagonal
                // locations and remove any piece from it.


                // Case 1: The user moved one space and did NOT capture a piece
                // x x x
                // x o x
                // o x x

                // Case 2: The user jumped and captured a piece
                // x x o
                // x x x
                // o x x

                int xDistance = Math.abs(fromX - destX);	// Get the absolute value in case the number is negative
                int yDistance = Math.abs(fromY - destY);	// Get the absolute value in case the number is negative

                if ((xDistance == 1) && (yDistance == 1)) {	// Case 1: the user only moved one space
                    // Do nothing.
                }
                else {	// Case 2: the user jumped and captured a piece

                        int captureX = 0;
                    int captureY = 0;

                    if (fromX > destX) {
                        captureX = (fromX - 1);
                    }
                    else {
                        captureX = (fromX + 1);
                    }

                    if (fromY > destY) {
                        captureY = (fromY - 1);
                    }
                    else {
                        captureY = (fromY + 1);
                    }

                    board.squares[captureX][captureY].RemoveChecker();
                }		

            }

            private Square[] ValidMovements(int x, int y) {

                    Boolean canMoveDown = false;	// Only crowned or red checkers can move down
                    Boolean canMoveUp = false;		// Only crowned or black checkers can move up

                    Checker checker = board.squares[x][y].GetChecker();
                    
                    // Check if the checker can move down
                            if (checker.IsCrowned() || (checker.GetPlayer() == players[0])) {
                                canMoveDown = true;
                            }

                    // Check if the checker can move up
                            if (checker.IsCrowned() || (checker.GetPlayer() == players[1])) {
                                canMoveUp = true;
                            }




                    // Define variables we might user
                            Square TL = null;	// Top-left
                            Square TR = null;	// Top-right
                            Square BL = null;	// Bottom-left
                            Square BR = null;	// Bottom-right
                            int squareCount = 0;	// Used to initialize the array



                    // Get valid upward movements

                            if (canMoveUp && (x > 0) && (y > 0)) {	// Can move diagonally to the top-left

                                    int topLeftX = (x - 1);
                                    int topLeftY = (y - 1);

                                    if (board.squares[topLeftX][topLeftY].IsOccupied()) {
                                            Checker diagonalChecker = board.squares[topLeftX][topLeftY].GetChecker();

                                            if (diagonalChecker.GetPlayer() != checker.GetPlayer()) {
                                                    // We can try capturing this piece


                                                    if ((topLeftX > 0) && (topLeftY > 0)) {	// Can move diagonally to the top-left

                                                            int top2LeftX = (topLeftX - 1);
                                                            int top2LeftY = (topLeftY - 1);

                                                            if (!board.squares[top2LeftX][top2LeftY].IsOccupied()) {
                                                                    // We can move here
                                                                    squareCount++;	// We can add a square
                                                                    TL = board.squares[top2LeftX][top2LeftY];
                                                            }
                                                            else {
                                                                    // We can NOT move here
                                                            }
                                                    }


                                            }
                                    }
                                    else if (canChangePiece) {	// We can move here. We are not required to jump.
                                            squareCount++;	// We can add a square
                                            TL = board.squares[topLeftX][topLeftY];
                                    }

                            }

                            if (canMoveUp && (x < 7) && (y > 0)) {	// Can move diagonally to the top-right

                                    int topLeftX = (x + 1);
                                    int topLeftY = (y - 1);

                                    if (board.squares[topLeftX][topLeftY].IsOccupied()) {
                                            Checker diagonalChecker = board.squares[topLeftX][topLeftY].GetChecker();

                                            if (diagonalChecker.GetPlayer() != checker.GetPlayer()) {
                                                    // We can try capturing this piece


                                                    if ((topLeftX < 7) && (topLeftY > 0)) {	// Can move diagonally to the top-right

                                                            int top2LeftX = (topLeftX + 1);
                                                            int top2LeftY = (topLeftY - 1);

                                                            if (!board.squares[top2LeftX][top2LeftY].IsOccupied()) {
                                                                    // We can move here
                                                                    squareCount++;	// We can add a square
                                                                    TR = board.squares[top2LeftX][top2LeftY];
                                                            }
                                                            else {
                                                                    // We can NOT move here
                                                            }
                                                    }


                                            }
                                    }
                                    else if (canChangePiece) {	// We can move here. We are not required to jump.
                                        squareCount++;	// We can add a square
                                        TR = board.squares[topLeftX][topLeftY];
                                    }

                            }

                            if (canMoveDown && (x > 0) && (y < 7)) {	// Can move diagonally to the bottom-left

                                    int bottomLeftX = (x - 1);
                                    int bottomLeftY = (y + 1);

                                    if (board.squares[bottomLeftX][bottomLeftY].IsOccupied()) {
                                            Checker diagonalChecker = board.squares[bottomLeftX][bottomLeftY].GetChecker();

                                            if (diagonalChecker.GetPlayer() != checker.GetPlayer()) {
                                                    // We can try capturing this piece


                                                    if ((bottomLeftX > 0) && (bottomLeftY < 7)) {	// Can move diagonally to the bottom-left

                                                            int bottom2LeftX = (bottomLeftX - 1);
                                                            int bottom2LeftY = (bottomLeftY + 1);

                                                            if (!board.squares[bottom2LeftX][bottom2LeftY].IsOccupied()) {
                                                                    // We can move here
                                                                    squareCount++;	// We can add a square
                                                                    BL = board.squares[bottom2LeftX][bottom2LeftY];
                                                            }
                                                            else {
                                                                    // We can NOT move here
                                                            }
                                                    }


                                            }
                                    }
                                    else if (canChangePiece) {	// We can move here. We are not required to jump.
                                            squareCount++;	// We can add a square
                                            BL = board.squares[bottomLeftX][bottomLeftY];
                                    }

                            }

                            if (canMoveDown && (x < 7) && (y < 7)) {	// Can move diagonally to the bottom-right

                                    int bottomLeftX = (x + 1);
                                    int bottomLeftY = (y + 1);
                                    
                                    

                                    if (board.squares[bottomLeftX][bottomLeftY].IsOccupied()) {
                                            Checker diagonalChecker = board.squares[bottomLeftX][bottomLeftY].GetChecker();

                                            if (diagonalChecker.GetPlayer() != checker.GetPlayer()) {
                                                    // We can try capturing this piece


                                                    if ((bottomLeftX < 7) && (bottomLeftY < 7)) {	// Can move diagonally to the bottom-right

                                                            int bottom2LeftX = (bottomLeftX + 1);
                                                            int bottom2LeftY = (bottomLeftY + 1);

                                                            if (!board.squares[bottom2LeftX][bottom2LeftY].IsOccupied()) {
                                                                    // We can move here
                                                                    squareCount++;	// We can add a square
                                                                    BR = board.squares[bottom2LeftX][bottom2LeftY];
                                                            }
                                                            else {
                                                                    // We can NOT move here
                                                            }
                                                    }


                                            }
                                    }
                                    else if (canChangePiece) {	// We can move here. We are not required to jump.
                                            squareCount++;	// We can add a square
                                            BR = board.squares[bottomLeftX][bottomLeftY];
                                    }

                            }







                    Square[] validSquares = new Square[squareCount];

                    int countDown = (squareCount - 1);	// Add a -1 to account for zero based counting

                    if (countDown >= 0 && BR != null) {
                            validSquares[countDown] = BR;
                            countDown--;
                    }
                    if (countDown >= 0 && BL != null) {
                            validSquares[countDown] = BL;
                            countDown--;
                    }
                    if (countDown >= 0 && TR != null) {
                            validSquares[countDown] = TR;
                            countDown--;
                    }
                    if (countDown >= 0 && TL != null) {
                            validSquares[countDown] = TL;
                            countDown--;
                    }




                    return (validSquares);
            }

            private boolean CanJump(int x, int y) {
                    boolean canJump = false;


                    Square[] validSquares = ValidMovements(x, y);

                    for (int i = 0; i < validSquares.length; i++) {

                            int xDifference = Math.abs(x - validSquares[i].GetX());

                            if (xDifference > 1) {	// If we have to jump diagonally then we will move at least 2 x squares away
                                    canJump = true;
                                    break;
                            }
                    }


                    boolean canMove = (validSquares.length > 0);

                    return (canJump);
            }

            public void Click(int x, int y) {
                
                if (chosePiece) {	// We already have a piece chosen and ready to move
                    MoveToSquare(x, y);
                }
                else {
                    ChoosePiece(x, y);
                }
                
            }

            private String GetCurrentPlayer() {
                return currentPlayer;
            }
            
            private void TryCrowningPiece(int x, int y) {
                
                Square thisSquare = board.squares[x][y];
                Checker checker = thisSquare.GetChecker();
                
                if ((currentPlayer == players[0]) && (y == 7)) {
                    checker.SetCrowned();
                }
                else if ((currentPlayer == players[1]) && (y == 0)) {
                    checker.SetCrowned();
                }
                
                board.squares[x][y].SetChecker(checker);
            }
                    
                    
            
            public void RedrawBoard(javax.swing.JLabel playerLabel, javax.swing.JButton[][] btnSquares, javax.swing.JLabel winLabel ) {

                        
                // - Update the win label
                    winLabel.setText("");
                        
                if (gameStarted) {
                
                    // - Update the player's label
                        playerLabel.setText("Current Player: " + GetCurrentPlayer());
                        

                    // - Draw the checkers on the board     


                        Square[] movementSquares = new Square[0];
                        if (chosePiece) {
                            movementSquares = ValidMovements(chosenX, chosenY);
                        }

                        for (int x = 0; x < 8; x++) {
                            for (int y = 0; y < 8; y++) {
                                boolean xEven = ((x % 2) == 0);
                                boolean yEven = ((y % 2) == 0);

                                if (xEven == yEven) {

                                    if (board.squares[x][y] != null) {

                                        Square thisSquare = board.squares[x][y];

                                        btnSquares[x][y].setBackground(new java.awt.Color(51, 0, 0));   // Set the default ackground color


                                        if (thisSquare.IsOccupied()) {
                                            Checker checker = thisSquare.GetChecker();


                                            // Check if this piece is chosen to be moved
                                                if (chosePiece && (chosenX == x) && (chosenY == y)) {
                                                    btnSquares[x][y].setBackground(Color.green);
                                                }
                                                else {
                                                    btnSquares[x][y].setBackground(new java.awt.Color(51, 0, 0));
                                                }

                                            // Set the checker icon
                                                if (checker.GetPlayer() == "Red") { // Red player
                                                    if (checker.IsCrowned()) {                                            
                                                        btnSquares[x][y].setIcon(new javax.swing.ImageIcon(getClass().getResource("images/red-crowned-checker.png")));
                                                    }
                                                    else {
                                                        btnSquares[x][y].setIcon(new javax.swing.ImageIcon(getClass().getResource("images/red-checker.png")));
                                                    }
                                                }
                                                else {  // Black player
                                                    if (checker.IsCrowned()) {                                            
                                                        btnSquares[x][y].setIcon(new javax.swing.ImageIcon(getClass().getResource("images/black-crowned-checker.png")));
                                                    }
                                                    else {
                                                        btnSquares[x][y].setIcon(new javax.swing.ImageIcon(getClass().getResource("images/black-checker.png")));
                                                    }
                                                }



                                        }
                                        else {
                                            btnSquares[x][y].setIcon(null);
                                        }




                                        // Check if this is a square that can be moved to by the chosen piece
                                            for (int i = 0; i < movementSquares.length; i++) {
                                                if ((x == movementSquares[i].GetX()) && (y == movementSquares[i].GetY())) {
                                                    btnSquares[x][y].setBackground(Color.blue);
                                                }
                                            }


                                    }
                                }

                            }
                        }
                }
                if (gameOver) {                    
                    winLabel.setText(currentPlayer + " Wins!");
                }
                
            }
	
}