import java.util.Scanner;

public class Collatz {
    // the max value and the array value.
    public static int max = 105500000;
    public static int[] q;

    /**
     * @param args
     *             main method to take input and output max colltz length within a
     *             range
     *             and the legnth itself.
     */
    public static void main(String[] args) {
        Scanner m = new Scanner(System.in);
        // makes the array able to hold up to 100000000
        q = new int[max];
        q[1] = 1;
        System.out.print("Enter the range: ");
        // the range inputs
        int i = m.nextInt();
        int j = m.nextInt();
        // sets a to first value of range
        int a = i;
        // finds the legnth of i and sets it to e.
        int e = collatzLength(i);

        // if inputs are out of the 1 - 100000000 range. or if j is greater the i
        while (((i > 100000000 || i < 1 || j > 100000000 || j < 1)) || i > j) {
            // range test of the inputs.
            if ((i > 100000000 || i < 1 || j > 100000000 || j < 1)) {
                // new input.
                System.out.println("Error. a and b must be either the numbers 1-100000000");
                // gets new input
                i = m.nextInt();
                j = m.nextInt();
            }
            // if i is greater then j input test.
            if (i > j) {
                System.out.println("Error. a must be less than b please re-enter values.");
                // get new input
                i = m.nextInt();
                j = m.nextInt();
            }

        }
        // for loop to set up the max length and length
        for (int b = i + 1; b <= j; b++) {
            // set int s to the collatz length of b.
            int s = collatzLength(b);
            // if s is greater then e which is the collatz test for the first input
            // set e equal to s and set a which is the value of i equal to the value the for
            // loop b is at.
            if (s > e) {
                e = s;
                a = b;
            }
            // for loop will run until it can no longer change anything with the if loop.
        }
        m.close();
        // prints the max col length and the actual collatz length
        System.out.println("The number with the maximum Collatz length in the range: " + a);
        System.out.println("Its Collatz length: " + e);

    }

    /**
     * @param x
     * @return l which is the collatz length after going through a recursive method
     * 
     */
    public static int collatzLength(long x) {
        // if x is less then 1 return 0( 1 is added bellow in the even odd test so this
        // is basically testing is x is less then or equal to one :) funny lil code
        // moment.
        if (x < 1) {
            return 0;
        }

        int m = (int) x;
        // checks if x is less then max.
        if (x < max) {
            // if array is empty return a array set to m.
            if (q[m] != 0) {
                return q[m];
            }
        }
        // random int value
        int l = 0;
        // see if x is odd.
        if ((x % 2) != 0) {
            // recurses back through the array and sees if its empty or returns value of
            // array m
            l = 1 + collatzLength((3 * x) + 1);
        }
        // see if x is even.
        if ((x % 2) == 0) {
            // recurses back through the array and sees if its empty or returns value of
            // array m
            l = 1 + collatzLength(x / 2);
        }
        // checks if x is less then max again and sets the array equal to l if it is.
        if (x < max) {
            q[m] = l;
        }
        // simply returns collatz length
        return l;

    }
}
