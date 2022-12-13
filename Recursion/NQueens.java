
//scanner for input
import java.util.Scanner;

public class NQueens {
    private static int n;
    private static int[][] board;

    /**
     * @param args
     *             getting the amount of queens and how big the board is
     *             n by n for board which is board[n][n]
     *             also tests if a 0 or negative number was inputed
     *             it will ask for a new number instead
     */
    public static void main(String[] args) {
        // scanner to get amount of queens
        Scanner queen = new Scanner(System.in);
        // Question
        System.out.print("Enter the number of queens: ");
        // n is number of queens
        n = queen.nextInt();
        // simple while loop to test if valid input for board and queens.
        while (n < 1) {
            System.out.println("ERROR, Enter valid number that is not negative or 0.");
            System.out.println("Please enter new queen amount.");
            // gets new input
            n = queen.nextInt();
        }
        // sets the board size up nXn is input is valid.
        board = new int[n][n];
        // checking if rows in not 0 or negative.
        if (isArrVal(n)) {
            // checks the rows for valid arrangments.
            int b = check_row(0);
            // output of the number of valid arrangments.
            System.out.println("The number of valid arrangements is " + b);
        }
        // just closes the scanner.
        queen.close();
    }

    /**
     * @param m
     * @return If the arrangment is valid
     */
    public static boolean isArrVal(int m) {
        // if at int m check the amount of rows is greater or equal to 0
        // basically checking to make sure it is not negative.
        if (check_row(m) >= 0) {
            return true;
        }
        return false;
    }

    /**
     * @param r     row valye
     * @param c     collumn value
     * @param value value either 1 or -1
     *              3 values to allow different positons on board and having totals
     *              easily.
     *              value is main value that will set the board and where the board
     *              has
     *              been
     *              taken and used.
     */
    public static void testBoard(int r, int c, int value) {
        // tests board boundaries
        for (int i = r + 1, j = 1; i < n; i++, j++) {
            // will set where the queen is placed.
            board[i][c] += value;
            // tests the left of the board
            if (c - j >= 0) {
                // subtracts the value of j to c and sets equal to += v
                // sets where the queen has been and can go
                board[i][c - j] += value;
            }
            // tests the right of the board.
            if (c + j < n) {
                // adds the value of j to c and sets equal to += v
                // sets where the queen has been and can go
                board[i][c + j] += value;
            }
        }
    }

    /**
     * @param r
     * @return how many valid arrangments does the input of n have?
     *         tests along side the n input of what ever user inputs
     */
    public static int check_row(int r) {
        // checks if row is greater then n which would be out of bounds.
        if (r >= n) {
            // returns 1 if r is greater then the boundary of the board
            return 1;
        }
        // value that will be returns and used to keep in check of how many valid
        // arrangments.
        int q = 0;
        // sets up a for loop with col comparing to n which is the board of the board..
        for (int col = 0; col < n; col++) {
            if (board[r][col] == 0) {
                // this allows code to see whre the queen has been recursivly :)
                // sets testboard with row, col and value = 1
                testBoard(r, col, 1);
                // recurses back thru the method and adds 1 to the row value
                q += check_row(r + 1);
                // sets testboard with row, col and value = -1 which allows the board to shown
                // where the queen has been.
                testBoard(r, col, -1);
            }
        }
        // returns how many valid arrangments there are.
        return q;
    }

}
