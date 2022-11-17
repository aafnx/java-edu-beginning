public class Product {
  String name;
  int price;
  int count;
  String description;

  public String toString() {
    return String.format(
        "---\nproduct: %s\nprice: %d\ncount: %d\ndescription: %s\n---", this.name, this.price, this.count,
        this.description);
  }

  public void printConsole() {
    System.out.println(this.toString());
  }
}
