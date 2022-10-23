// Написать программу, показывающую последовательность действий для игры “Ханойская башня”
public class HanoiTower {
  public static void main(String[] args) {
    start(4, 1, 3);
  }
  static void start(int lengthTower, int fromPin, int toPin) {
    if (lengthTower == 1) {
      System.out.printf("Переместить диск 1 со стержня - %d, на стержень - %d\n", fromPin, toPin);
    } else {
      int tmpPin = 6 - fromPin - toPin;
      start(lengthTower - 1, fromPin, tmpPin);
      System.out.printf("Переместить диск %d со стержня - %d, на стержень - %d\n", lengthTower, fromPin, toPin);
      start(lengthTower - 1, tmpPin, toPin);
    }
  }
}