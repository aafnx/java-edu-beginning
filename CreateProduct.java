public class CreateProduct {
  Product temp;

  public CreateProduct() {
    temp = new Product();
    temp.name = CONST.NONE_STRING;
    temp.price = CONST.NONE_INT;
    temp.count = CONST.NONE_INT;
    temp.description = CONST.NONE_STRING;

  }

  public CreateProduct setName(String name) {
    temp.name = name;
    return this;
  }

  public CreateProduct setPrice(int price) {
    temp.price = price;
    return this;
  }

  public CreateProduct setCount(int count) {
    temp.count = count;
    return this;
  }

  public CreateProduct setDescription(String description) {
    temp.description = description;
    return this;
  }

  public Product Create() {
    return temp;
  }
}