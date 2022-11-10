import java.util.LinkedList;

public class QuickSort {
  public static void main(String[] args) {

    LinkedList<Integer> list = new LinkedList<>();
    for (int i = 0; i < 7; i++) {
      list.add(getRandomNumber());
    }
//    [3, 19, 15, 6, 15, 13, 0]
//    list.add(3);
//    list.add(19);
//    list.add(15);
//    list.add(6);
//    list.add(15);
//    list.add(13);
//    list.add(0);
    System.out.println(list);
    sort(list);
    System.out.println(list);
  }
  static LinkedList<Integer> sort(LinkedList<Integer> list) {
    if (list.size() <= 1) {
      return list;
    }
    int n = list.get(list.size() / 2);
    LinkedList<Integer> lower = new LinkedList<>();
    LinkedList<Integer> equal = new LinkedList<>();
    LinkedList<Integer> greater = new LinkedList<>();
    for (int item : list) {
      if (item < n) lower.add(item);
      if (item == n) equal.add(item);
      if (item > n) greater.add(item);
    }
    list.clear();
    list.addAll(sort(lower));
    list.addAll(equal);
    list.addAll(sort(greater));
    return list;
  }
  static int getRandomNumber() {
    return (int) (Math.random() * 30 - 5);
  }
}
