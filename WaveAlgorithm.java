public class WaveAlgorithm {
    static int block = -1;
    static int start = -2;
    static int end = -8;
  static int[][] map = {
    {0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, -1, 0, 0, 0, 0},
    {0, 0, 0, -1, 0, 0, 0, 0},
    {0, 0, -1, -1, -1, 0, 0, 0},
    {-2, -1, -1, 0, 0, 0, 0, 0},
    {0, 0, -1, 0, 0, 0, 0, -8},
    {0, -1, -1, -1, -1, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0}
  };
  static int[][] queue = new int[map.length * map[0].length][3];
  static int posQueue = 0;
  static int positionY = 4;
  static int positionX = 0;
  static int[] finishPos = {5, 7};


  public static void main(String[] args) {
//      System.out.printf("%d, %d \n", positionY, positionX);
    queue[0][0] = 1;
    queue[0][1] = positionY;
    queue[0][2] = positionX;
    posQueue++;
    move();
//    printQueue();
    print();

  }
  static void print() {
      System.out.printf("-------------------------------\n");
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
       // System.out.printf("%d ", map[i][j]);
       if (map[i][j] == block) {
         System.out.printf("%c", '█');
       } else if (map[i][j] == end) {
         System.out.printf("%c", '▕');
       }
       else if (map[i][j] == start) {
         System.out.printf("%c", '╳');
       } else if (map[i][j] == 0) {
           System.out.printf("%c", '░');
       } else {
         System.out.printf("%d", map[i][j]);
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
    static void addQueue(int direction, int y, int x) {
        for (int i = posQueue; i == posQueue++; i++) {
            for (int j = 2; j < queue[i].length; j++) {
                if (queue[i][j - 1] == 0 && queue[i][j] == 0) {
                    queue[i][j - 2] = direction;
                    queue[i][j - 1] = y;
                    queue[i][j] = x;
                    return;
                }
            }
        }
    }
    static int[] removeQueue() {
      int[] coordinates = {queue[0][1], queue[0][2]};
        for (int i = 1; i <= posQueue; i++) {
            for (int j = 0; j < queue[i].length; j++) {
                    queue[i - 1][j] = queue[i][j];
                }
            }
        posQueue--;
        return coordinates;
    }

    static void move() {
        int up = 1, left = 2, right = 3, down = 4;
        int step = 1;
//        positionX = 0;
//        for (int i = 0; i <= posQueue; i++)
        while (queue[0][0] != 0 ){
            int[] c = removeQueue();
            int y = c[0];
            int x = c[1];
            // up
            if (y - step >= 0 && map[y - step][x] == 0) {
                
                map[y - step][x] = up;
                addQueue(up, y - step, x);
            }
            // right
            if (x + step < map.length && map[y][x + step] == 0) {
                map[y][x + step] = right;
                addQueue(right, y, x + step);
            }
            // left
            if (x - step >= 0 && map[y][x - step] == 0) {
                map[y][x - step] = left;
                addQueue(left, y, x - step);
            }
            // down
            if (y + step < map.length && map[y + step][x] == 0) {
                map[y + step][x] = down;
                addQueue(down, y + step, x);
            }
        }
    }
}

