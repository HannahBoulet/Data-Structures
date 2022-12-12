public class FunSki {
    // this comment is just so ik i turned in the updated code!
    // simple size variable
    public static int s = 0;
    // binary node storage.
    // new root to store root in.
    public static BinaryNode nr;
    // left child
    public static BinaryNode lc;
    // right child
    public static BinaryNode rc;
    // funness and child ints
    // funness varaible.
    public static int f = 0;
    // right varaible.
    public static int ri = 0;
    // left variable.
    public static int lf = 0;

    /**
     * @param args
     *             Main method where tree and root is set and then left and right
     *             wieght is set and funski is called to print everythingand test
     *             funness
     *             ran out of time to perfect this so this has some funks but it
     *             works for the sample!!
     */
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryNode root = tree.getRoot();
        // funski to get funness and nodes.
        funski(root, 0, 0);
        // print(ns[s]);
    }

    public static void funski(BinaryNode root, int right, int left) {
        // binary node old root set equal to root to keep track.
        BinaryNode or = root;
        // string array set to a massive size.
        String[] ns = new String[1050001];

        while (root.getLeftWeight() > 0 || root.getRightWeight() > 0) {
            // tests if left node is greater or less then right
            right = root.getRightWeight();
            left = root.getLeftWeight();
            if (left > right) {
                // add left child to funness int
                f = f + left;
                // new root is set the left child.
                nr = root.getLeftChild();
                // sets root to new root.
                root = nr;
                // sets the new left child
                lc = root.getLeftChild();
                // sets the new right child
                rc = root.getRightChild();
                // gets left weight again.
                left = root.getLeftWeight();
                // gets right weight again.
                right = root.getRightWeight();
                // stores in string array..
                ns[s] = "Node: " + root.getLabel();
                // expands size again.
                s++;
            } // does the other (right is greater then left)
            else if (left < right) {
                // add right child to funness int
                f = f + right;
                // new root set to roots right child
                nr = root.getRightChild();
                // root is set to new root
                root = nr;
                // left child is set again
                lc = root.getLeftChild();
                // sets right child again.
                rc = root.getRightChild();
                // sets left int to the left weight again.
                left = root.getLeftWeight();
                // set right int to right weight again
                right = root.getRightWeight();
                // string array stored node.
                ns[s] = "Node: " + root.getLabel();
                // increase size again.
                s++;

            } else if ((left - right) == 0) {
                // this will print a funny statment
                // making it seem like marty has no fun.
                System.out.println("Marty goes home no funess allowed >:(");
            }

        }
        // prints largest funness
        System.out.print("Largest Funness: " + f + "\n");
        // prints old root first(orignal root that starts the track down.)
        System.out.print(or + "\n");
        // print statment to go thru array and print node
        print(ns);

    }

    /**
     * @param ns
     *           Simple print statement that iterates thru the array and prints.
     */
    public static void print(String[] ns) {
        // simple for loop to go thru the array.
        for (int i = 0; i < s; i++) {
            System.out.println(ns[i]);
        }
    }

}
