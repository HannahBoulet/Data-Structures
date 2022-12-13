import java.util.Scanner;

//JKust making sure it updated when saved comment
public class Jugs {
    // 3d arrays for visted, previous
    private static boolean[][] v;
    // format string array
    private static String[][] curentString;
    // int arrays for prev A and B
    private static int[][] pA;
    private static int[][] pB;
    // ints for goals, and num
    private static int gA;
    private static int gB;
    // just ints for A,B,C
    private static int A;
    private static int B;
    private static int C;
    // number limit
    private static int num = 10004;

    /**
     * @param args
     *             Main method to get variables and calculate wheither is possible
     *             to add them together to get C
     * 
     */
    public static void main(String[] args) {
        // scanner obj
        Scanner s = new Scanner(System.in);
        // A input
        System.out.print("Enter A: ");
        A = s.nextInt();
        // B input
        System.out.print("Enter B: ");
        B = s.nextInt();
        // C input
        System.out.print("Enter C: ");
        C = s.nextInt();
        // closes scanner
        s.close();
        // sets array boundarys (1000's)
        v = new boolean[num][num];
        // simple for loops i and j against array boundary
        // sets v boolean arrary to false for the boundary
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                // sets v[i][j] to false for future testing :)
                v[i][j] = false;
            }
        }
        // also the string array
        curentString = new String[num][num];
        // makes previous A same booundary as boolean
        pA = new int[num][num];
        // B have the same boundary
        pB = new int[num][num];
        // sets the boolean p to if it is true or not.
        boolean possible = visited(0, 0, -1, -1, "");
        // if true it will print saying it found a solution
        if (possible == true) {
            System.out.println("Yay! Found a solution.");
            // prints the format for the solution showing how it got said solution.
            print(gA, gB);
        } else {
            // if A+B can not equal C it is a impossible calculation.
            System.out.println("Impossible!");

        }
    }

    /**
     * @param x  just different form of A
     * @param y  just different form of B
     * @param px just different fourm of previous A
     * @param py just different fourm of previous B
     *           Just changed them from a,b to x,y type bc formating and easier to
     *           tell apart for me :)
     * @param p  String variable
     * @return to return what path was taken to completethe formula and return true
     *         if possible. :)
     */
    public static boolean visited(int x, int y, int px, int py, String prevString) {
        // simple quick tests to test inputs again themselves and make sure user didnt
        // put 0
        if (x < 0 || x > A || y < 0 || y > B) {
            return false;
        }
        // true this will return false
        if (v[x][y]) {
            return false;
        }
        // if all tests are passed set true
        v[x][y] = true;
        // sets previous A Array to the prev X
        pA[x][y] = px;
        // sets previous B Array to the prev Y
        pB[x][y] = py;
        // Sets prev String F to prev P String
        curentString[x][y] = prevString;
        // tsts to make sure X+Y can even equal C and if so sets goal A to x and goal b
        // to y
        if ((x + y) == C) {
            // sets goal A to x
            gA = x;
            // sets goal B to y
            gB = y;
            return true;
        }
        // returns r if recurise test true
        // makes sure to keep if A or B and fills/empty proper jug
        // fill jug one
        // base booelan as well
        boolean r = false;
        r = visited(A, y, x, y, "Fill Jug 1");
        // fill jug two
        r = r | visited(x, B, x, y, "Fill Jug 2");
        // empty jug 1
        r = r | visited(0, y, x, y, "Empty Jug 1");
        // empty jug 2
        r = r | visited(x, 0, x, y, "Empty Jug 2");
        // random int varibale to keep varibale
        int rm = 0;
        // if x is less then B-y set rm equal x
        rm = (x < (B - y)) ? x : (B - y);
        // ^shorted the if state to a short one :)
        /*
         * if (x < (B - y)) {
         * rm = x;
         * } // if x is greater or equal B-y
         * else {
         * rm = (B - y);
         * }
         */
        // r is set to continue formula
        r = r | visited((x - rm), (y + rm), x, y, "Pour Jug 1 -> Jug 2");
        // If y is less then A - x
        rm = (y < (A - x)) ? y : (A - x);
        /*
         * if (y < (A - x)) {
         * rm = y;
         * }
         * 
         * else {
         * rm = (A - x);
         * }
         */
        // r is set to continue formula
        r = r | visited((x + rm), (y - rm), x, y, "Pour Jug 2 -> Jug 1");
        // return if true or false
        return r;
    }

    /**
     * @param x
     * @param y
     *          print function that prints the fill ,pour
     *          if x or y is 0 return
     */
    public static void print(int x, int y) {
        // if x is 0 or y is 0 just return
        if (x == 0 && y == 0) {
            return;
        }
        // prints prev A and prev B
        print(pA[x][y], pB[x][y]);
        // prints a and b format
        System.out.println(curentString[x][y] + " [a = " + x + ", b = " + y + "]");
    }

}
