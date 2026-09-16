
import java.util.Random;
import java.util.Scanner;

class Task1 {
    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int steps = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            steps++;

            if (arr[mid] == target) {
                return steps;
            }

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int min = random.nextInt(100);
        int max = min + random.nextInt(100) + 20;

        int[] arr = new int[max - min + 1];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = min + i;
        }

        System.out.println("Введите число в диапозоге от " + min + " до " + max);
        int target = scanner.nextInt();
        scanner.close();

        if (target < min || target > max) {
            System.out.println("Заданное число не находится в диапозоне");
        }

        int steps = binarySearch(arr, target);

        if (steps != -1) {
            System.out.println("Кол-во шагов: " + steps);
        } else {
            System.out.println("Заданное число не найдено");
        }
    }
}