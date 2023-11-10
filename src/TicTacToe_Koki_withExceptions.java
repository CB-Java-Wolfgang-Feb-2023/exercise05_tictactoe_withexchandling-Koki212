import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe_Koki_withExceptions {
    static String[] board;
    static String turn;

    public static void main(String[] args) {
        board = new String[9];
        turn = "X";

        // Fill numbers in playfield
        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }

        System.out.println("Welcome to Tic - Tac - Toe!");
        System.out.println();
        printGameMode();
        selectedGameMode();
        System.out.println();

    }

    // METHODS

    // Methode for displaying the playfield
    static void printBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("|---|---|---|");
    }

    // Method for printing GameMode
    static void printGameMode() {
        System.out.println("[1] - Player VS Player");
        System.out.println("[2] - Player VS COM");
        System.out.println("[3] - COM VS COM");
        System.out.print("Please choose your GameMode: ");
    }

    // Method for choosing GameMode
    static void selectedGameMode() {
        int selectedMode;
        Scanner playerInput = new Scanner(System.in);
        selectedMode = playerInput.nextInt();
        if (selectedMode == 1) {
            System.out.println("You selected following GameMode: Player VS Player");
            playVSplay();
        } else if (selectedMode == 2) {
            System.out.println("You selected following GameMode: Player VS COM");
            playVSCOM();
        } else if (selectedMode == 3) {
            System.out.println("You selected following GameMode: COM VS COM");
        } else {
            System.out.println("Your input is not valid. Please try again!");
            System.out.print("Please choose your GameMode: ");
            selectedGameMode();
        }
    }

    // Method for Player VS Player
    static void playVSplay() {
        printBoard();
        Scanner userInput = new Scanner(System.in);
        String winner = null;
        System.out.println("X will play first. Enter a slot number to place X in: ");
        while (winner == null) {
            int numInput;

            // Exception handling. → Code wurde online (geekforgeeks) gefunden
            // numInput will take input from user like from 1 to 9.
            // If it is not in range from 1 to 9.
            // then it will show you an error "Invalid input."
            try {
                numInput = userInput.nextInt();
                if (!(numInput > 0 && numInput <= 9)) {
                    System.out.println("Invalid input; re-enter slot number:");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; re-enter slot number:");
                continue;
            }

            // This game has two player x and O.
            // Here is the logic to decide the turn.
            if (board[numInput - 1].equals(String.valueOf(numInput))) {
                board[numInput - 1] = turn;
                if (turn.equals("X")) {
                    turn = "O";
                } else {
                    turn = "X";
                }
                printBoard();
                winner = checkWinner();
            } else {
                System.out.println("Slot already taken; re-enter slot number:");
            }
        }
        // If no one win or lose from both player x and O.
        // then here is the logic to print "draw".
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        }

        // For winner - to display Congratulations! message.
        else {
            System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
        }
        userInput.close();
    }

    // Method for Player VS COM
    static void playVSCOM() {
        printBoard();
        Scanner userInput = new Scanner(System.in);
        String winner = null;
        System.out.println("X will play first. Enter a slot number to place X in: ");
        while (winner == null) {
            int numInput;

            // Exception handling.
            // numInput will take input from user like from 1 to 9.
            // If it is not in range from 1 to 9.
            // then it will show you an error "Invalid input."
            try {
                numInput = userInput.nextInt();
                if (!(numInput > 0 && numInput <= 9)) {
                    System.out.println("Invalid input; re-enter slot number:");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; re-enter slot number:");
                continue;
            }

            // This game has one player x.
            // 0 is COM.
            // Here is the logic to decide the turn.
            if (board[numInput - 1].equals(String.valueOf(numInput))) {
                board[numInput - 1] = turn;
                if (turn.equals("X")) {
                    turn = "O";
                    getCOMTip();
                } else {
                    turn = "X";
                }
                printBoard();
                winner = checkWinner();
            } else {
                System.out.println("Slot already taken; re-enter slot number:");
            }
        }
        // If no one win or lose from both player x and O.
        // then here is the logic to print "draw".
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        }

        // For winner -to display Congratulations! message.
        else {
            System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
        }
        userInput.close();
    }

    // Method to check winner
    static String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }
            // For Player 1 [X] winner
            if (line.equals("XXX")) {
                return "X";
            }

            // For Player 2 [O] winner
            else if (line.equals("OOO")) {
                return "O";
            }
        }

        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(board).contains(String.valueOf(a + 1))) {
                break;
            } else if (a == 8) {
                return "draw";
            }
        }

        // To enter the X Or O at the exact place on board.
        System.out.println(turn + "'s turn; enter a slot number to place " + turn + " in:");
        return null;
    }

    // Method for random COM-Tip   → not functional yet
    static void getCOMTip() {
        int maxNumber = board.length;
        Random rand = new Random();
        int randomTip = (rand.nextInt(maxNumber)) + 1;
        System.out.println(randomTip);
    }

}
