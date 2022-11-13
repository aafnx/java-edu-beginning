public class Cat {
  int id;
  String name;
  String color;
  int age;
  Human owner;
  Boolean isHungry;
  Boolean isNearOwner = false;

  public Cat(int id, String name, String color, boolean isHungry) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.age = generateAge();
    this.isHungry = isHungry;
  }

  private int generateAge() {
    return (int) (Math.random() * 16 + 1);
  }

  @Override
  public String toString() {
    return String.format("id:%d, name:%s, color:%s, age:%d", id, name, color, age);
  }

  @Override
  public boolean equals(Object obj) {
    Cat cat = (Cat) obj;
    if (id == cat.id && name == cat.name) {
      return true;
    }
    return false;
  }
}
