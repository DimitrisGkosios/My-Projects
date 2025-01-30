package gr.MyProjects.MaxSumSubArrayApp;

public class MaxSumSubrray {
    public static int maxSubArraySum(int[] nums) {
        // Αρχικοποίηση των μεταβλητών
        int localMaximum = nums[0];
        int globalMaximum = nums[0];

        // Διαβάζουμε τον πίνακα απο το δεύτερο στοιχείο
        for (int i = 1; i < nums.length; i++) {
            // Ενημέρωση της globalMaximum
            globalMaximum = Math.max(nums[i], globalMaximum + nums[i]);
            // Ενημέρωση της localMaximum
            localMaximum = Math.max(localMaximum, globalMaximum);
        }

        return localMaximum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = maxSubArraySum(nums);
        System.out.println("Maximum sum subarray: " + maxSum);
    }
}
