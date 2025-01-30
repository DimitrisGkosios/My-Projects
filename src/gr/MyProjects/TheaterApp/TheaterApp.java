package gr.MyProjects.TheaterApp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TheaterApp {
    private static final int ROWS = 30;
    private static final int COLUMNS = 12;
    private boolean[][] seats;

    public TheaterApp() {
        seats = new boolean[ROWS][COLUMNS];
    }

    public void book(char column, int row) {
        int colIndex = column - 'A';
        int rowIndex = row - 1;

        if (rowIndex < 0 || rowIndex >= ROWS || colIndex < 0 || colIndex >= COLUMNS) {
            System.out.println("Λανθασμένη θέση.");
            return;
        }

        if (!seats[rowIndex][colIndex]) {
            seats[rowIndex][colIndex] = true;
            System.out.println("Η θέση " + column + row + " κρατήθηκε.");
        } else {
            System.out.println("Η θέση " + column + row + " είναι ήδη κρατημένη.");
        }
    }


    public void cancel(char column, int row) {
        int colIndex = column - 'A';
        int rowIndex = row - 1;

        if (rowIndex < 0 || rowIndex >= ROWS || colIndex < 0 || colIndex >= COLUMNS) {
            System.out.println("Λανθασμένη θέση.");
            return;
        }

        if (seats[rowIndex][colIndex]) {
            seats[rowIndex][colIndex] = false;
            System.out.println("Η κράτηση της θέσης " + column + row + " ακυρώθηκε.");
        } else {
            System.out.println("Η θέση " + column + row + " δεν είναι κρατημένη.");
        }
    }


    public void printSeats() {
        System.out.print("  ");
        for (char c = 'A'; c < 'A' + COLUMNS; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int i = 0; i < ROWS; i++) {
            System.out.printf("%2d", i + 1);
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(seats[i][j] ? " B" : " O");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TheaterApp theater = new TheaterApp();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            theater.printSeats();

            System.out.println("\nΕπιλέξτε μία επιλογή:");
            System.out.println("1. Κράτηση θέσης");
            System.out.println("2. Ακύρωση κράτησης");
            System.out.println("3. Έξοδος");

            int choice = -1;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ακατάλληλη είσοδος! Παρακαλώ εισάγετε έναν αριθμό.");
                scanner.nextLine();
                continue;
            }

            if (choice == 3) {
                System.out.println("Έξοδος από το πρόγραμμα.");
                break;
            }

            if (choice < 1 || choice > 3) {
                System.out.println("Ακατάλληλη επιλογή. Προσπαθήστε ξανά.");
                continue;
            }

            char column = ' ';
            int row = -1;

            try {
                System.out.print("Εισάγετε τη στήλη (A-L): ");
                column = scanner.next().charAt(0);

                if (column < 'A' || column > 'L') {
                    System.out.println("Η στήλη πρέπει να είναι μεταξύ A και L.");
                    continue;
                }

                System.out.print("Εισάγετε τη σειρά (1-30): ");
                row = scanner.nextInt();

                if (row < 1 || row > 30) {
                    System.out.println("Η σειρά πρέπει να είναι μεταξύ 1 και 30.");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ακατάλληλη είσοδος! Παρακαλώ εισάγετε έναν αριθμό για τη σειρά.");
                scanner.nextLine();
                continue;
            }

            if (choice == 1) {
                theater.book(column, row);
            } else if (choice == 2) {
                theater.cancel(column, row);
            }
        }

        scanner.close();
    }
}
