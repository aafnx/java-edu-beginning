// сделать волновой алгоритм, который будет находить выход из лабиринта
// и найти самый короткий путь от старта до выхода

public class WaveAlgorithm {
  // определяем что будет на карте
  static int emptyCell = 0;
  static int block = -1;
  static int start = -2;
  static int exit = -8;
  // создаем карту
  static int[][] map = {
      { 0, 0, 0, -1, 0, 0, 0, 0 },
      { 0, -1, 0, -8, 0, -1, -1, 0 },
      { 0, 0, -1, 0, -1, 0, 0, 0 },
      { 0, -1, 0, -1, 0, 0, -1, 0 },
      { 0, 0, 0, -1, -1, 0, -1, 0 },
      { 0, 0, 0, -1, 0, -1, 0, 0 },
      { 0, 0, -2, -1, -1, -1, 0, 0 },
      { 0, -1, 0, 0, 0, 0, 0, 0 }
  };
  static int[][] queue = new int[map.length * map[0].length][3];
  static int posQueue = 0;

  public static void main(String[] args) {
    int countExits = getCountOfExit();
    if (countExits == 0) {
      return;
    }
    int[][] coordinatesExit = getCoordinatesOfExit(countExits);
    print();
    move();
    findShortestWay(countExits, coordinatesExit);
    print();
  }

  // i = 0 - Количество шагов до выхода
  // i = 1 - Координата y
  // i = 2 - Координата x
  static int getCountOfExit() {
    int result = 0;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == exit) {
          result++;
        }
      }
    }
    return result;
  }
  static int[][] getCoordinatesOfExit(int countsExit) {
    int[][] result = new int[countsExit][2];
    int idx = 0;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (idx == countsExit) {
          return result;
        }
        if (map[i][j] == exit) {
          result[idx][0] = i;
          result[idx][1] = j;
          idx++;
        }
      }
    }
    return null;
  }
  static int[] getShortWayAndCoordinateToFinish(int countExit, int[][] coordinatesExit) {
    if (countExit <= 0) {
      return null;
    }
    int[] result = new int[3];
    int steps = map[coordinatesExit[0][0]][coordinatesExit[0][1]];
    for (int i = 0; i < coordinatesExit.length; i++) {
      for (int j = 1; j < coordinatesExit[i].length; j++) {
        int y = coordinatesExit[i][j - 1];
        int x = coordinatesExit[i][j];
        if (map[y][x] <= steps) {
          steps = map[y][x];
          result[1] = y;
          result[2] = x;
        }
      }
    }
    result[0] = steps;
    return result;
  }
  static int[] getStartPosition() {
    int[] startPosition = new int[2];
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == start) {
          startPosition[0] = i;
          startPosition[1] = j;
        }
      }
    }
    return startPosition;
  }
  static void findShortestWay(int countsExit, int[][] coordinatesExit) {
    int[] exit = getShortWayAndCoordinateToFinish(countsExit, coordinatesExit);
    assert exit != null;
    int stepsToExit = exit[0];
    int y = exit[1];
    int x = exit[2];
    int[][] ways = new int[stepsToExit + 1][2];
    int sides = 4;
    int idx = 0;
    System.out.println("***");
    ways[idx][0] = y;
    ways[idx][1] = x;
    idx++;
    while (map[y][x] != start && sides > 0) {
      int upY = y - 1;
      int rightX = x + 1;
      int leftX = x - 1;
      int downY = y + 1;
      // down
      if (downY < map.length && map[downY][x] < map[y][x] && map[downY][x] != block) {
        ways[idx][0] = downY;
        ways[idx][1] = x;
        y = downY;
        idx++;
        continue;
      }
      // left
      if (leftX >= 0 && map[y][leftX] < map[y][x] && map[y][leftX] != block) {
        ways[idx][0] = y;
        ways[idx][1] = leftX;
        x = leftX;
        idx++;
        continue;
      }
      // right
      if (rightX < map[0].length && map[y][rightX] < map[y][x] && map[y][rightX] != block) {
        ways[idx][0] = y;
        ways[idx][1] = rightX;
        x = rightX;
        idx++;
        continue;
      }
      // up
      if (upY >= 0 && map[upY][x] < map[y][x] && map[upY][x] != block) {
        ways[idx][0] = upY;
        ways[idx][1] = x;
        y = upY;
        idx++;
        continue;
      }
      // если не можем двигаться, то
      // помечаем ячейку
      // берем предыдущие координаты
      map[y][x] = -1;
      y = ways[idx][0];
      x = ways[idx][0];
      // увеличиваем попытки шагов на 1
      sides++;
      }
      if (map[y][x] == start) {
        System.out.printf("Path find, steps - %d\n", stepsToExit);
        for (int i = ways.length - 1; i >= 0; i--) {
          for (int j = 1; j < ways[i].length; j++) {
            System.out.printf("%d - %d", ways[i][j - 1], ways[i][j]);
          }
          System.out.println();
        }
      }
      if (sides <= 0) {
        System.out.println("Path not found");
        return;
      }
      System.out.println("***");
  }

  static void print() {
    System.out.print("-------------------------------\n");
    for (int[] ints : map) {
      for (int anInt : ints) {
        if (anInt == block) {
          System.out.printf(" %c |", '▓');
        } else if (anInt == exit) {
          System.out.printf(" %c |", '\u2605');
        } else if (anInt == start) {
          System.out.printf(" %c |", '\u263a');
        } else if (anInt == emptyCell) {
          System.out.printf(" %c |", ' ');
        } else {
          System.out.printf(" %d |", anInt);
        }
      }
      System.out.println();
    }
    System.out.printf("-------------------------------\n");
  }

  // в каждой строке очереди
  // queue[0][0] - какой шаг делается
  // queue[0][1] - координата y
  // queue[0][2] - координата x
  static void initQueue() {
    int[] positionStart = getStartPosition();
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
    initQueue();
    int step = 1;
    while (queue[0][0] != 0) {
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
        map[upY][x] = s;
        continue;
      }
      // right
      if (rightX < map.length && map[y][rightX] == emptyCell) {
        map[y][rightX] = s;
        addQueue(s + 1, y, rightX);
      } else if (rightX < map.length && map[y][rightX] == exit) {
        map[y][rightX] = s;
        continue;
      }
      // left
      if (leftX >= 0 && map[y][leftX] == emptyCell) {
        map[y][leftX] = s;
        addQueue(s + 1, y, leftX);
      } else if (leftX >= 0 && map[y][leftX] == exit) {
        map[y][leftX] = s;
        continue;
      }
      // down
      if (downY < map.length && map[downY][x] == emptyCell) {
        map[downY][x] = s;
        addQueue(s + 1, downY, x);
      } else if (downY < map.length && map[downY][x] == exit) {
        map[downY][x] = s;
      }
    }
  }
}
