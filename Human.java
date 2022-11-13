import java.util.HashSet;

public class Human {
  int id;
  String firstName;
  String lastName;
  Cat cat;

  public Human(int id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  // *** - начало действия
  // --- - конец действия
  public void hasCat() {
    System.out.println("***");
    if (this.cat == null) {
      System.out.printf("У %s %s нет котика\n", this.firstName, this.lastName);
    } else {
      System.out.printf("%s %s является хозяином котика - %s, цвет: %s , возраст: %d\n", this.firstName, this.lastName,
          cat.name, cat.color, cat.age);
    }
    System.out.println("---");
  }

  public void feedCat(Cat cat) {
    System.out.println("***");
    System.out.printf("%s %s зовет котика %s чтобы покормить\n", this.firstName, this.lastName, cat.name);
    if (cat.isHungry) {
      cat.isHungry = false;
      if (cat.owner != null) {
        System.out.printf("Котик %s наелся и мурлычит, но хозяином этого котика уже является - %s %s\n", cat.name,
            cat.owner.firstName, cat.owner.lastName);
        return;
      }
      cat.owner = this;
      this.cat = cat;
      System.out.printf("Котик %s наелся и мурлычит, теперь хозяином этого котика стал - %s %s\n", cat.name,
          this.firstName,
          this.lastName);
    } else {
      System.out.printf("Котик %s уже сыт, котик продолжает заниматься своими делами\n", cat.name);
    }
    System.out.println("---");
  }

  public void callCat(HashSet<Cat> catsShelter) {
    System.out.println("***");
    if (catsShelter.contains(this.cat)) {
      System.out.printf("%s %s зовет котика\n", this.firstName, this.lastName);
      System.out.printf("Котик %s бежит к вам\n", this.cat.name);
    } else {
      System.out.printf("%s %s зовет котика\n", this.firstName, this.lastName);
      System.out.println("Ни один котик не откликнулся, здесь нет вашего котика :(");
    }
    System.out.println("---");
  }

  @Override
  public String toString() {
    return String.format("id:%d, fn:%s, ln:%s", id, firstName, lastName);
  }

  @Override
  public boolean equals(Object obj) {
    Human person = (Human) obj;
    if (id == person.id && firstName == person.firstName && lastName == person.lastName) {
      return true;
    }
    return false;
  }

}
