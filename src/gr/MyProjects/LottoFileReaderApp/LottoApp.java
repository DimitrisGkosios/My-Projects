package gr.MyProjects.LottoFileReaderApp;

import java.io.*;
import java.util.Arrays;

public class LottoApp {
    public static void main(String[] args) {
        String inputFilePath = "c:/lotto.txt";
        String outputFilePath = "combination.txt";

        try {
            int[] numbers = readNumbersFromFile(inputFilePath);
            if (numbers.length < 7 || numbers.length > 49) {
                System.out.println("The file must contain between 7 and 49 numbers.");
                return;
            }

            Arrays.sort(numbers);

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

            generateCombinations(numbers, new int[6], 0, 0, writer);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] readNumbersFromFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        int[] numbers = new int[49];
        int count = 0;
        String line;
        while ((line = br.readLine()) != null) {
            int num = Integer.parseInt(line.trim());
            if (num >= 1 && num <= 49) {
                numbers[count++] = num;
            }
        }
        br.close();
        return Arrays.copyOf(numbers, count);
    }

    private static void generateCombinations(int[] numbers, int[] current, int start, int depth, BufferedWriter writer) throws IOException {
        if (depth == 6) {
            if (isValidCombination(current)) {
                writeCombinationToFile(current, writer);
            }
            return;
        }

        for (int i = start; i < numbers.length; i++) {
            current[depth] = numbers[i];
            generateCombinations(numbers, current, i + 1, depth + 1, writer);
        }
    }

    private static boolean isValidCombination(int[] arr) {
        return isEven(arr) && isOdd(arr) && isContiguous(arr)
                && isSameEnding(arr) && isSameTen(arr);
    }

    private static boolean isEven(int[] arr) {
        int evenCount = 0;
        for (int num : arr) {
            if (num % 2 == 0) {
                evenCount++;
            }
        }
        return evenCount <= 4;
    }

    private static boolean isOdd(int[] arr) {
        int oddCount = 0;
        for (int num : arr) {
            if (num % 2 != 0) {
                oddCount++;
            }
        }
        return oddCount <= 4;
    }

    private static boolean isContiguous(int[] arr) {
        int contiguousCount = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == 1) {
                contiguousCount++;
                if (contiguousCount > 2) {
                    return false;
                }
            } else {
                contiguousCount = 0;
            }
        }
        return true;
    }

    private static boolean isSameEnding(int[] arr) {
        int[] endings = new int[10];
        for (int num : arr) {
            endings[num % 10]++;
        }
        for (int ending : endings) {
            if (ending > 3) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSameTen(int[] arr) {
        int[] tens = new int[5];
        for (int num : arr) {
            tens[num / 10]++;
        }
        for (int ten : tens) {
            if (ten > 3) {
                return false;
            }
        }
        return true;
    }

    private static void writeCombinationToFile(int[] combination, BufferedWriter writer) throws IOException {
        writer.write(Arrays.toString(combination));
        writer.newLine();
    }
}
