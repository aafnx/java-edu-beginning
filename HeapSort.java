// Реализовать алгоритм пирамидальной сортировки (HeapSort)

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[] {5, 10, 2, 4, -10, 99, 8, 1, 0, -6, 3};
        printArray(arr);
        sort(arr);
        printArray(arr);
    }
    static void  sort (int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heap(arr, arr.length, i);
        }
        for (int i = arr.length-1; i>=0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heap(arr, i, 0);
        }
    }
    static void heap (int[] arr, int length, int idx) {
        int max = idx;
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;
        if (left < length && arr[left] > arr[max]) {
            max = left;
        }
        if (right < length && arr[right] > arr[max]) {
            max = right;
        }
        if (max != idx) {
            int temp = arr[idx];
            arr[idx] = arr[max];
            arr[max] = temp;
            heap(arr, length, max);
        }
    }
    static void printArray (int[] arr) {
        for (int item : arr) {
            System.out.printf("%d ", item);
        }
        System.out.println();
    }
}