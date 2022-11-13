// Сформировать и сделать обход дерева, для которого у каждого узла может быть больше 2-х потомков

// * Сложный пример ПОДУМАТЬ
//          1
//        /   \
//       2      3
//      /     /   \
//     4     5     6
//   / | \        / \
//  7  8  9     11   20

public class BinaryTreeHard {
  public static void main(String[] args) {
    Node root = new Node(1);

    Node n2 = new Node(2);
    Node n3 = new Node(3);

    Node n4 = new Node(4);
    Node n5 = new Node(5);
    Node n6 = new Node(6);

    Node n7 = new Node(7);
    Node n8 = new Node(8);
    Node n9 = new Node(9);
    Node n11 = new Node(11);
    Node n20 = new Node(20);

    root.left = n2;
    root.right = n3;

    n2.left = n4;
    n3.left = n5;
    n3.right = n6;

    n4.left = n7;
    n4.middle = n8;
    n4.right = n9;

    n6.left = n11;
    n6.right = n20;
    // 1 2 4 7 8 9 3 5 6 11 20

    preOrder(root, "");
    searchPreOrder(root, 0);
    searchPreOrder(root, 8);
  }

  static class Node {
    int value;

    public Node(int value) {
      this.value = value;
    }

    Node left;
    Node middle;
    Node right;
  }

  static void searchPreOrder(Node node, int value) {
    if (node == null) {
      return;
    }
    if (node.value == value) {
      System.out.printf("Число - %d есть в дереве", node.value);
      return;
    }
    searchPreOrder(node.left, value);
    searchPreOrder(node.middle, value);
    searchPreOrder(node.right, value);
  }

  static void preOrder(Node node, String space) {
    if (node == null) {
      return;
    }
    System.out.println(space + node.value);
    preOrder(node.left, space + "  ");
    // поставил +, чтобы было понятно какое значение в средней ветке
    preOrder(node.middle, space + "+ ");
    preOrder(node.right, space + "  ");
  }
}