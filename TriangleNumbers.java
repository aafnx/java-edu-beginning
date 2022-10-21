// Написать программу вычисления n-ого треугольного числа
public class TriangleNumbers {
    public static void main(String[] args) {
        System.out.println(calculateTriangleNum(2));
        System.out.println(calculateTriangleNum(3));
        System.out.println(calculateTriangleNum(8));
        System.out.println(isTriangleNum(12));
        System.out.println(isTriangleNum(10));
    }
    // вычислить треугольное число
    static int calculateTriangleNum (int n) {
        return n == 1 ? 1 : calculateTriangleNum(n - 1) + n;
    }
    // проверяем, является ли переданное число треугольным
    static boolean isTriangleNum (int x) {
        for (int i = 0; i < x; i++) {
            boolean res = (8 * x + 1) == (8 * ((i * (i + 1)) / 2) + 1);
            if (res) {
                return true;
            }
        }
        return false;
    }
}