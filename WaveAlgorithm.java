public class WaveAlgorithm {
  static int emptyCell = 0;
//  static int winCell = -8;
  static int block = -1;
  static int start = -2;
  static int exit = -8;
  static int[][] map = {
      { 0, 0, 0, -1, 0, 0, 0, 0 },
      { 0, -1, 0, 0, 0, -1, -1, 0 },
      { 0, 0, 0, 0, -1, 0, 0, 0 },
      { 0, -1, 0, 0, -8, 0, -1, 0 },
      { -2, 0, 0, -1, -1, 0, -1, 0 },
      { 0, 0, -1, 0, 0, -1, 0, -8 },
      { 0, 0, 0, -1, -1, -1, 0, 0 },
      { 0, -1, 0, 0, 0, 0, 0, 0 }
  };
  static int[][] queue = new int[map.length * map[0].length][3];
  static int posQueue = 0;
  static int[] positionExit = { 5, 7, 0, 7 };

  public static void main(String[] args) {

    print();
    int countExits = getCountOfExit();
    int[][] coordinatesExit = getCoordinatesOfExit(countExits);
    move();
    findShortestWay(countExits, coordinatesExit);
    print();
    // TODO 3
    // Сделать функцию, которая будет проверять возможно ли сделать шаг
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
    int idx = 0;
    System.out.println("***");
    ways[idx][0] = y;
    ways[idx][1] = x;
    idx++;
    while (map[y][x] != start) {
      // down
      y = y + 1;
      if (y < map.length && map[y][x] < map[y - 1][x] && map[y][x] != block) {
        ways[idx][0] = y;
        ways[idx][1] = x;
        idx++;
        continue;
      }
      // left
      y = y - 1;
      x = x - 1;
      if (x >= 0 && map[y][x] < map[y][x + 1] && map[y][x] != block) {
        ways[idx][0] = y;
        ways[idx][1] = x;
        idx++;
        continue;
      }
      // right
      x = x + 2;
      if (x < map[0].length && map[y][x] < map[y][x - 1] && map[y][x] != block) {
        ways[idx][0] = y;
        ways[idx][1] = x;
        idx++;
        continue;
      }
      // up
      x = x - 1;
      y = y - 1;
      if (y >= 0 && map[y][x] < map[y + 1][x] && map[y][x] != block) {
        ways[idx][0] = y;
        ways[idx][1] = x;
        idx++;
      }
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
//        } else if (anInt == winCell) {
//          System.out.printf(" %c |", '\u2714');
        } else {
          System.out.printf(" %d |", anInt);
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
        map[upY][x] = s;
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
