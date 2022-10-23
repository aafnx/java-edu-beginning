// Реализовать алгоритм сортировки вставками

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6, 7, 3, -20, 0, 100, 89, -74, 1};
        printArray(arr);
        sort(arr);
        printArray(arr);
    }
    static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int swap = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = swap;
                }
            }
        }
    }
    static void printArray(int[] arr) {
        for (int item : arr) {
            System.out.printf("%d ", item);
        }
        System.out.println();
    }
}