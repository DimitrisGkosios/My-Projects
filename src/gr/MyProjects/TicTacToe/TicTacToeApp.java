package gr.MyProjects.TicTacToe;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class TicTacToeApp {
    static String[] gameBoard;
    static String winner;
    static String player;

    public static String whoIsWinner(){

        for (int i=0; i<8; i++){

            String check = switch (i) {
                case 0 -> gameBoard[0] + gameBoard[1] + gameBoard[2];
                case 1 -> gameBoard[3] + gameBoard[4] + gameBoard[5];
                case 2 -> gameBoard[6] + gameBoard[7] + gameBoard[8];
                case 3 -> gameBoard[0] + gameBoard[3] + gameBoard[6];
                case 4 -> gameBoard[1] + gameBoard[4] + gameBoard[7];
                case 5 -> gameBoard[2] + gameBoard[5] + gameBoard[8];
                case 6 -> gameBoard[0] + gameBoard[4] + gameBoard[8];
                case 7 -> gameBoard[2] + gameBoard[4] + gameBoard[6];
                default -> null;
            };

            if(check.equals("XXX"))
                return "X";
            else if (check.equals("OOO"))
                return "O";

        }
        for (int i=0; i<9; i++){

            if(Arrays.asList(gameBoard).contains(String.valueOf(i+1)))
                break;
            else if(i == 8)
                return "Ισοπαλία";

        }

        System.out.println("Σειρά του παίκτη " + player );

        return null;

    }

    public static void showBoard(){

        System.out.println("#############");
        System.out.println("| " + gameBoard[0] + " | " + gameBoard[1] + " | " + gameBoard[2] + " |");
        System.out.println("| " + gameBoard[3] + " | " + gameBoard[4] + " | " + gameBoard[5] + " |");
        System.out.println("| " + gameBoard[6] + " | " + gameBoard[7] + " | " + gameBoard[8] + " |");
        System.out.println("#############");

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        winner = null;
        player = "X";
        gameBoard = new String[9];

        for(int i=0; i<9; i++){
            gameBoard[i] = String.valueOf(i+1);
        }

        showBoard();

        System.out.println("Σειρά του παίκτη X: ");


        while (winner == null) {
            try {
                int input = scanner.nextInt();

                if (input > 0 && input <= 9) {
                    if (gameBoard[input - 1].equals(String.valueOf(input))) {
                        gameBoard[input - 1] = player;
                        showBoard();

                        // Εναλλαγή παίκτη
                        if (Objects.equals(player, "X"))
                            player = "O";
                        else
                            player = "X";

                        winner = whoIsWinner();
                    } else {
                        System.out.println("Η θέση είναι ήδη κατειλημμένη. Δοκιμάστε ξανά.");
                    }
                } else {
                    System.out.println("Ο αριθμός πρέπει να είναι μεταξύ 1 και 9. Δοκιμάστε ξανά.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Παρακαλώ εισάγετε αποκλειστικά αριθμούς. Δοκιμάστε ξανά.");
                scanner.nextLine();
            }
        }

        if (winner.equals("Ισοπαλία"))
            System.out.println("Ισοπαλία!");

        else{
            System.out.println("Συγχαρητήρια!" + winner + " Νίκησες!");
        }
    }
}
