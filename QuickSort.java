import java.util.ArrayList;

public class QuickSort {
  public static void main(String[] args) {
    ArrayList<Integer> list = initList();
    System.out.println(list);
    sort(list);
    System.out.println(list);
  }
  static ArrayList<Integer> sort(ArrayList<Integer> list) {
    if (list.size() <= 1) {
      return list;
    }
    int n = list.get(0);
    ArrayList<Integer> lower = new ArrayList<>();
    ArrayList<Integer> equal = new ArrayList<>();
    ArrayList<Integer> greater = new ArrayList<>();
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
  static ArrayList<Integer> initList() {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      int rnd = (int) (Math.random() * 30 - 5);
      list.add(rnd);
    }
    return list;
  }
}
