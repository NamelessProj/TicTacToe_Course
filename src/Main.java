import java.util.Scanner;

public class Main {
    // Variables
    public static char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static char currentPlayer = 'X'; // X or O
    public static byte numberOfSquaresPlayed = 0;
    public static boolean hasAWinner = false;

    public static void main(String[] args) {
        // Get the user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            while (numberOfSquaresPlayed < board.length) { // Making sure the game continues until all squares are played or a player wins
                System.out.println();
                printBoard(board);
                System.out.print("Player " + currentPlayer + ", enter your move (1-9): ");
                byte move = scanner.nextByte();
                move--;

                // Check if the move is valid
                if (move < 0 || move > 8) {
                    System.out.println("Invalid move, please try again.");
                    continue;
                }

                // Check if the square is already taken
                if (board[move] == 'X' || board[move] == 'O') {
                    System.out.println("That square is already taken, please try again.");
                    continue;
                }

                // Saving the move to the board
                board[move] = currentPlayer;

                // Check for a winner after the first player has played 3 squares (so we can check for a winner)
                if (numberOfSquaresPlayed >= 5 && checkForWinner(board, currentPlayer)) {
                    hasAWinner = true;
                    printBoard(board);
                    System.out.println("Player " + currentPlayer + " has won!");
                    break;
                }

                currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
                numberOfSquaresPlayed++;
            }

            // If the game ends without a winner, print the board and notify the players
            if (!hasAWinner) {
                printBoard(board);
                System.out.println("Game over! No player has won.");
            }

            System.out.println();
            System.out.print("Do you want to play again? (y/n): ");
            char playAgain = scanner.next().charAt(0);

            // Check if the user wants to play again, if no, exit the loop
            if (playAgain == 'n' || playAgain == 'N')
                break;

            // Reset the game
            board = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
            numberOfSquaresPlayed = 0;
            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
            hasAWinner = false;

            System.out.println();
        }

        System.out.println();
        System.out.print("The game has ended.");
    }

    /**
     * Prints the current state of the board.
     *
     * @param board The current state of the board.
     */
    private static void printBoard(char[] board) {
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---+---+---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---+---+---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8]);
    }

    /**
     * Checks if the current player has won the game.
     *
     * @param board The current state of the board.
     * @param player The current player ('X' or 'O').
     * */
    private static boolean checkForWinner(char[] board, char player) {
        return (board[0] == player && board[1] == player && board[2] == player) ||
                (board[3] == player && board[4] == player && board[5] == player) ||
                (board[6] == player && board[7] == player && board[8] == player) ||
                (board[0] == player && board[3] == player && board[6] == player) ||
                (board[1] == player && board[4] == player && board[7] == player) ||
                (board[2] == player && board[5] == player && board[8] == player) ||
                (board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player);
    }
}