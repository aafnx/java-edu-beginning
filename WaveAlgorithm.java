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
    static int positionY = 4;
    static int positionX = 0;
    static int[] finishPos = { 5, 7 };

    public static void main(String[] args) {
        initQueue();
        print();
        move();
        findShortestWay();
        print();
    }

    static void findShortestWay() {
        System.out.println("TODO");
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
                } else if (map[i][j] == winCell) {
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
        queue[0][1] = positionY;
        queue[0][2] = positionX;
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

            // if (map[y][x] == end) {
            // map[y][x] = s;
            // continue;
            // }
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
                map[y][rightX] = winCell;
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
