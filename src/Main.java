import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char currentPlayer = 'X'; // X or O
        byte numberOfSquaresPlayed = 0;
        boolean hasAWinner = false;

        while (true) {
            while (numberOfSquaresPlayed < board.length) {
                System.out.println();
                printBoard(board);
                System.out.print("Player " + currentPlayer + ", enter your move (1-9): ");
                byte move = scanner.nextByte();
                move--;

                if (move < 0 || move > 8) {
                    System.out.println("Invalid move, please try again.");
                    continue;
                }

                if (board[move] == 'X' || board[move] == 'O') {
                    System.out.println("That square is already taken, please try again.");
                    continue;
                }

                board[move] = currentPlayer;

                if (numberOfSquaresPlayed >= 5 && checkForWinner(board, currentPlayer)) {
                    hasAWinner = true;
                    printBoard(board);
                    System.out.println("Player " + currentPlayer + " has won!");
                    break;
                }

                currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
                numberOfSquaresPlayed++;
            }

            if (!hasAWinner) {
                printBoard(board);
                System.out.println("Game over! No player has won.");
            }

            System.out.println();
            System.out.print("Do you want to play again? (y/n): ");
            char playAgain = scanner.next().charAt(0);

            if (playAgain == 'n' || playAgain == 'N')
                break;

            // Reset the game
            board = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
            numberOfSquaresPlayed = 0;
            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
            hasAWinner = false;

            System.out.println();
        }

        System.out.print("The game has ended.");
    }

    private static void printBoard(char[] board) {
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---+---+---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---+---+---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8]);
    }

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