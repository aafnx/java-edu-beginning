public class WaveAlgorithm {
  static int emptyCell = 0;
  static int winCell = -5;
  static int block = -1;
  static int start = -2;
  static int exit = -8;
  static int[][] map = {
      { 0, 0, 0, -1, 0, 0, 0, -8 },
      { 0, -1, 0, 0, 0, -1, -1, 0 },
      { 0, 0, 0, 0, -1, 0, 0, 0 },
      { 0, -1, 0, 0, 0, 0, -1, 0 },
      { -2, 0, 0, -1, -1, 0, -1, 0 },
      { 0, 0, -1, 0, 0, -1, 0, -8 },
      { 0, 0, 0, -1, -1, -1, 0, 0 },
      { 0, -1, 0, 0, 0, 0, 0, 0 }
  };
  static int[][] queue = new int[map.length * map[0].length][3];
  static int posQueue = 0;
  static int[] positionStart = { 4, 0 };
  static int[] positionExit = { 5, 7, 0, 7 };

  public static void main(String[] args) {
    initQueue();
    print();
    move();
    findShortestWay();
    print();
    // TODO
    // сделать функцию, которая найдет меньшее количество шагов до выхода и вернет
    // количество шагов
    // это функция будет вызываться в функции поиска пути
    // TODO 2
    // сделать функцию, которая будет возвращать стартовую позицию
    // TODO 3
    // Сделать функцию, которая будет проверять возможно ли сделать шаг
  }

  static void findShortestWay() {
    int stepsToExit = 12;
    int[][] ways = new int[stepsToExit + 1][2];
    int steps = 0;
    int y = positionExit[0];
    int x = positionExit[1];
    if (map[y][x] == stepsToExit) {
      System.out.println("***");
      if (ways[0][0] == 0) {
        ways[steps][0] = y;
        ways[steps][1] = x;
        steps++;
      }
      while (map[y][x] != start) {
        // down
        y = y + 1;
        if (y < map.length && map[y][x] < map[y - 1][x] && map[y][x] != block) {
          System.out.printf("down, %d - %d\n", map[y - 1][x], map[y][x]);
          ways[steps][0] = y;
          ways[steps][1] = x;
          steps++;
          continue;
        }
        // left
        y = y - 1;
        x = x - 1;
        if (x >= 0 && map[y][x] < map[y][x + 1] && map[y][x] != block) {
          System.out.printf("left, %d - %d\n", map[y][x + 1], map[y][x]);
          ways[steps][0] = y;
          ways[steps][1] = x;
          steps++;
          continue;
        }
        // right
        x = x + 2;
        if (x < map[0].length && map[y][x] < map[y][x - 1] && map[y][x] != block) {
          System.out.printf("right, %d - %d\n", map[y][x - 1], map[y][x]);
          ways[steps][0] = y;
          ways[steps][1] = x;
          steps++;
          continue;
        }
        // up
        x = x - 1;
        y = y - 1;
        if (y >= 0 && map[y][x] < map[y + 1][x] && map[y][x] != block) {
          System.out.printf("up, %d - %d\n", map[y + 1][x], map[y][x]);
          ways[steps][0] = y;
          ways[steps][1] = x;
          steps++;
          continue;
        }
        // else {
        // System.out.printf("y - %d, x - %d\n", ways[0][0], ways[0][1]);
        // return;
        // }
      }

      if (map[y][x] == start) {
        System.out.printf("Path find, steps - %d\n", steps - 1);
        for (int i = ways.length - 1; i >= 0; i--) {
          for (int j = 1; j < ways[i].length; j++) {
            System.out.printf("%d - %d", ways[i][j - 1], ways[i][j]);
          }
          System.out.println();
        }
      }
      System.out.println("***");
    }
  }

  static void print() {
    System.out.printf("-------------------------------\n");
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        // System.out.printf("%d ", map[i][j]);
        if (map[i][j] == block) {
          System.out.printf(" %c |", '▓');
        } else if (map[i][j] == exit) {
          System.out.printf(" %c |", '\u2605');
        } else if (map[i][j] == start) {
          System.out.printf(" %c |", '\u263a');
        } else if (map[i][j] == emptyCell) {
          System.out.printf(" %c |", ' ');
        } else if (i == positionExit[0] && j == positionExit[1]
            || i == positionExit[2] && j == positionExit[3]) {
          System.out.printf(" %c |", '\u2714');
        } else {
          System.out.printf(" %d |", map[i][j]);
        }
      }
      System.out.println();
    }
    System.out.printf("-------------------------------\n");
  }

  static void printQueue() {
    for (int i = 0; i < queue.length; i++) {
      for (int j = 0; j < queue[i].length; j++) {
        System.out.printf("%d ", queue[i][j]);
      }
      System.out.println();
    }
  }

  // в каждой строке очереди
  // queue[0][0] - какой шаг делается
  // queue[0][1] - координата y
  // queue[0][2] - координата x
  static void initQueue() {
    queue[0][0] = 1;
    queue[0][1] = positionStart[0];
    queue[0][2] = positionStart[1];
    posQueue++;
  }

  static void addQueue(int steps, int y, int x) {
    for (int i = posQueue; i == posQueue++; i++) {
      for (int j = 2; j < queue[i].length; j++) {
        if (queue[i][j - 1] == 0 && queue[i][j] == 0) {
          queue[i][j - 2] = steps;
          queue[i][j - 1] = y;
          queue[i][j] = x;
          return;
        }
      }
    }
  }

  static int[] removeQueue() {
    int steps = queue[0][0];
    int y = queue[0][1];
    int x = queue[0][2];
    int[] coordinates = { steps, y, x };
    for (int i = 1; i <= posQueue; i++) {
      for (int j = 0; j < queue[i].length; j++) {
        queue[i - 1][j] = queue[i][j];
      }
    }
    posQueue--;
    return coordinates;
  }

  static void move() {
    int step = 1;
    while (queue[0][0] != 0) {
      // try {
      // Thread.sleep(500);
      // print();
      // } catch (Exception ignored) {}

      int[] c = removeQueue();
      int s = c[0];
      int y = c[1];
      int x = c[2];
      int upY = y - step;
      int rightX = x + step;
      int leftX = x - step;
      int downY = y + step;
      // up
      if (upY >= 0 && map[upY][x] == emptyCell) {
        map[upY][x] = s;
        addQueue(s + 1, upY, x);
      } else if (upY >= 0 && map[upY][x] == exit) {
        map[upY][x] = winCell;
        continue;
      }
      // right
      if (rightX < map.length && map[y][rightX] == emptyCell) {
        map[y][rightX] = s;
        addQueue(s + 1, y, rightX);
      } else if (rightX < map.length && map[y][rightX] == exit) {
        // map[y][rightX] = winCell;
        map[y][rightX] = s;
        continue;
      }
      // left
      if (leftX >= 0 && map[y][leftX] == emptyCell) {
        map[y][leftX] = s;
        addQueue(s + 1, y, leftX);
      }
      // down
      if (downY < map.length && map[downY][x] == emptyCell) {
        map[downY][x] = s;
        addQueue(s + 1, downY, x);
      }
    }
  }
}
