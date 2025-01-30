package gr.MyProjects.CharacterFrequencyApp;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class CharacterFrequencyApp {
    public static void main(String[] args) {
        String filename = "c:/character.txt";
        int[][] charTable = new int[128][2];

        for (int i = 0; i < 128; i++) {
            charTable[i][0] = i;
            charTable[i][1] = 0;
        }

        try (FileReader reader = new FileReader(filename)) {
            int c;
            while ((c = reader.read()) != -1) {
                if (Character.isWhitespace(c)) {
                    continue;
                }
                if (c < 128) {
                    charTable[c][1]++;
                }
            }
        } catch (IOException e) {
            System.err.println("Σφάλμα κατά την ανάγνωση του αρχείου: " + e.getMessage());
            return;
        }

        int[][] filteredTable = Arrays.stream(charTable)
                .filter(entry -> entry[1] > 0)
                .toArray(int[][]::new);

        Arrays.sort(filteredTable, Comparator.comparingInt(entry -> entry[0]));
        System.out.println("Στατιστικά ανά χαρακτήρα (αλφαβητικά):");
        for (int[] entry : filteredTable) {
            System.out.printf("Χαρακτήρας: %c, Συχνότητα: %d%n", entry[0], entry[1]);
        }

        Arrays.sort(filteredTable, (a, b) -> Integer.compare(b[1], a[1]));
        System.out.println("\nΣτατιστικά ανά συχνότητα εμφάνισης:");
        for (int[] entry : filteredTable) {
            System.out.printf("Χαρακτήρας: %c, Συχνότητα: %d%n", entry[0], entry[1]);
        }
    }
}
