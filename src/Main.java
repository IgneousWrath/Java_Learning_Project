import bank.Account;
import bank.BankingTerminal;
import games.TicTacToe;
import games.Trivia;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {


        Scanner scanner = new Scanner(System.in);

        System.out.println("What would you like to do? Type in the number below:");
        System.out.println("1. Play Tic Tac Toe.");
        System.out.println("2. Play Trivia.");
        System.out.println("3. Digital Banking.");
        System.out.println("Type anything else to exit.");
        String input = scanner.next();

        switch (input){
            case "1", "1.":
                System.out.println("Starting Tic Tac Toe.");
                TicTacToe.playTicTacToe();
                break;
            case "2", "2.":
                System.out.println("Starting Trivia.");
                Trivia.playTrivia();
                break;
            case "3", "3.":
                System.out.println("Starting bank terminal.");
                BankingTerminal.useBankingTerminal();
                break;
            default: System.out.println("Closing terminal."); break;
        }









    }

}