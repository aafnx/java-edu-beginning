// Описать построителя товаров

public class App {
  public static void main(String[] args) {
    Product Milk = new CreateProduct()
        .setName("Milk")
        .setPrice(56)
        .setCount(1000)
        .setDescription("Best Milk ever")
        .Create();

    Product Burger = new CreateProduct()
        .setName("Burger")
        .setPrice(245)
        .setCount(14)
        .Create();

    Milk.printConsole();
    Burger.printConsole();
  }
}
