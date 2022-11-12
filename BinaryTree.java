// Сформировать и сделать обход дерева

public class BinaryTree {
  static Node root = null;

  public static void main(String[] args) {
    insertNode(10); // root

    insertNode(8); // left
    insertNode(20); // right

    insertNode(6); // left - left
    insertNode(9); // left - right
    insertNode(15); // right - left
    insertNode(26); // right - right

    insertNode(5); // left - left - left
    insertNode(7); // left - left - right
    insertNode(14); // right - left - left
    insertNode(17); // right - left - right
    insertNode(23); // right - right - left
    insertNode(30); // right - right - right

    //                10
    //           /         \
    //         8            20
    //      /    \        /     \
    //     6      9      15      26
    //   /  \         /  \     /   \
    //  5    7       14  17   23   30

    preOrder(root, "");
    System.out.println(isInTree(31));
    System.out.println(isInTree(6));

  }
  static boolean isInTree(int value) {
    return search(root, value);
  }
  private static boolean search(Node node, int value) {
    if (node == null) {
      return false;
    }
    if (node.value == value) {
      return true;
    }
    if (value < node.value) {
      return search(node.left, value);
    } else {
      return search(node.right, value);
    }
  }

  static void insertNode(int item) {
    Node node = new Node(item);
    if (root == null) {
      root = node;
      return;
    }
    builtNode(root, node);
  }

  private static void builtNode(Node currentNode, Node newNode) {
    if (currentNode.value > newNode.value) {
      if (currentNode.left == null) {
        currentNode.left = newNode;
      } else {
        builtNode(currentNode.left, newNode);
      }
    } else {
      if (currentNode.right == null) {
        currentNode.right = newNode;
      } else {
        builtNode(currentNode.right, newNode);
      }
    }
  }

  static class Node {
    int value;
    public Node(int value) {
      this.value = value;
    }
    Node left;
    Node right;
  }

  static void preOrder(Node node, String space) {
    if (node == null) {
      return;
    }
    System.out.println(space + node.value);
    preOrder(node.left, space + "  ");
    preOrder(node.right, space + "  ");
  }
}