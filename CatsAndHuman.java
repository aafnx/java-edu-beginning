import java.util.Arrays;
import java.util.HashSet;

// БОЛЬШАЯ ЗАДАЧА НА ВЕКА
// Попробовать описать логику взаимодействия человека и котика
// Человек зовёт котика - котик бежит к своему хозяину
// ps если котиков много - бежит только один

public class CatsAndHuman {
  public static void main(String[] args) {
    // создаем котиков
    Cat catBarsik = new Cat(101, "Barsik", "Оранжевый", true);
    Cat catTom = new Cat(102, "Tom", "Серый", false);
    Cat catLisa = new Cat(103, "Lisa", "Белый", true);
    Cat catKotya = new Cat(104, "Kotya", "Черный", true);
    Cat catDarthVeider = new Cat(666, "Darth Veider", "Черный", true);
    // помещаем котиков в приют
    HashSet<Cat> catsShelter = new HashSet<Cat>(Arrays.asList(catBarsik, catTom, catLisa, catKotya, catDarthVeider));
    // показывает какие котики в приюте
    printCatsInShelter(catsShelter);
    // создаем людей
    Human personChuck = new Human(10, "Chuck", "Norris");
    Human personLeia = new Human(11, "Leia", "Organa");
    Human personNeo = new Human(77, "Thomas", "Anderson");
    // узнаем явлюятся ли люди хозяном котиков
    personChuck.hasCat();
    personLeia.hasCat();
    personNeo.hasCat();
    // Chuck Norris кормит котика Darth Veider
    // и Chuck Norris становится хозяином котика Darth Veider
    personChuck.feedCat(catDarthVeider);
    // Leia Organa кормит котика Tom
    // но котик уже сыт, поэтому Leia Organa не становится хозяином котика Tom
    personLeia.feedCat(catTom);

    // котик Dart Veider проголодался
    catDarthVeider.isHungry = true;
    // Thomas Anderson кормит котика Darth Veider
    // Котик кушает, но остается предан хозяину Chuck Norris
    personNeo.feedCat(catDarthVeider);

    // Chuck Norris зовет котика из приюта, на зов откликается Darth Veider
    personChuck.callCat(catsShelter);
    // Leia Organa зовет котика из приюта, но ни один котик не откликнулся :(
    personLeia.callCat(catsShelter);
    // Thomas Anderson зовет котика из приюта,
    // но ни один котик не откликнулся :(
    personNeo.callCat(catsShelter);

    // выводим информацию есть ли у людей котики
    personChuck.hasCat();
    personLeia.hasCat();
    personNeo.hasCat();

  }

  static void printCatsInShelter(HashSet<Cat> catsInShelter) {
    System.out.println("***");
    System.out.println("Котики в приюте:");
    for (Cat cat : catsInShelter) {
      System.out.println(cat);
    }
    System.out.println("---");
  }
}
