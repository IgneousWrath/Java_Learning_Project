package games;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

    public static void playTicTacToe() {

        String[] squares = {" ", " ", " ", " ", " ", " ", " ", " ", " "};
        String[] squareAssignment = {"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3"};
        String[] playerNumberAndLetter = {"2", "o"};
        Scanner scanner = new Scanner(System.in);
        String input;

        for (int i = 0; i < 100; i++) {

            if (checkWin(squares) == 0) {
                if (Arrays.stream(squares).anyMatch(" "::contains)) {
                    drawBoard(squares);

                    swapPlayer(playerNumberAndLetter);
                    System.out.println("Player " + playerNumberAndLetter[0] + ", it is your turn. Type your tile: Ex: A1");

                    input = scanner.next();
                    takeTurn(squareAssignment, squares, input, playerNumberAndLetter[1]);

                } else {
                    drawBoard(squares);
                    System.out.println("It's a tie!");
                    break;
                }
            } else {

                drawBoard(squares);
                if (checkWin(squares) != 0) {
                    String winStyle = "";
                    if (checkWin(squares) == 1) {
                        winStyle = "horizontally";
                    } else if (checkWin(squares) == 2) {
                        winStyle = "vertically";
                    } else if (checkWin(squares) == 3) {
                        winStyle = "diagonally";
                    }
                    System.out.println("Player " + playerNumberAndLetter[0] + " won with " + playerNumberAndLetter[1] + " " + winStyle + "!");
                }
                break;
            }

        }
    }


    public static void drawBoard(String[] squares) {

        System.out.println("* | 1 | 2 | 3");
        System.out.println("A | " + squares[0] + " | " + squares[1] + " | " + squares[2]);
        System.out.println("B | " + squares[3] + " | " + squares[4] + " | " + squares[5]);
        System.out.println("C | " + squares[6] + " | " + squares[7] + " | " + squares[8]);

    }

    public static void takeTurn(String[] assignment, String[] square, String input, String player) {
        if (Arrays.stream(assignment).anyMatch(input::matches)) {
            for (int x = 0; x < 9; x++) {
                if (square[x].equals(" ") && assignment[x].equals(input.toUpperCase())) {
                    square[x] = player;
                } else if (!square[x].equals(" ") && assignment[x].equals(input.toUpperCase())) {
                    System.out.println("Someone already played in this square. Turn skipped.");
                }

            }

        } else {
            System.out.println("Input not recognized. Turn skipped.");


        }
    }

    public static int checkWin(String[] squares) {

        if ((!squares[0].equals(" ") && squares[0].equals(squares[1]) && squares[0].equals(squares[2])) ||
                (!squares[3].equals(" ") && squares[3].equals(squares[4]) && squares[4].equals(squares[5])) ||
                (!squares[6].equals(" ") && squares[6].equals(squares[7]) && squares[6].equals(squares[8]))) {
            return 1;
            //Win across
        } else if ((!squares[0].equals(" ") && squares[0].equals(squares[3]) && squares[0].equals(squares[6])) ||
                (!squares[1].equals(" ") && squares[1].equals(squares[4]) && squares[4].equals(squares[7])) ||
                (!squares[2].equals(" ") && squares[2].equals(squares[5]) && squares[5].equals(squares[8]))) {
            return 2;
            //Win down
        } else if ((!squares[0].equals(" ") && squares[0].equals(squares[4]) && squares[0].equals(squares[8])) ||
                (!squares[6].equals(" ") && squares[6].equals(squares[4]) && squares[4].equals(squares[2]))) {
            return 3;
            //Win diagonal
        }
        return 0;
    }

    public static void swapPlayer(String[] count) {
        System.out.println(count[0]);
        if (count[0].equals("1")) {
            count[0] = "2";
            count[1] = "o";
        } else {
            count[0] = "1";
            count[1] = "x";

        }

    }
}