// Basic BinaryNode class, Feel free to edit this case.

public class BinaryNode {
    private String label; // The label of the node
    private BinaryNode[] childs; // childs[0] points to the left child, and childs[1] points to the right child
    private int[] weights; // weights[0] is the funness of the left ski path from here, and weights[1]
	// is the funness of the right ski path from here.
	private boolean edge;

    // Feel free to add extra data fields and methods to cater to your implementation.
    // However, please do not edit anything that is already provided, as these are needed
	// to build the tree from the input.

	// Constructor
    public BinaryNode(String label, BinaryNode left, BinaryNode right, int lw, int rw) {
        this.label = label;
        this.childs = new BinaryNode[]{left, right};
        this.weights = new int[]{lw, rw};
    }

	// Accessors and Mutators
    public BinaryNode getLeftChild() {
        return childs[0];
    }

    public void setLeftChild(BinaryNode child) {
        this.childs[0] = child;
    }

    public BinaryNode getRightChild() {
        return childs[1];
    }

    public void setRightChild(BinaryNode child) {
        this.childs[1] = child;
    }

    public int getLeftWeight() {
        return weights[0];
    }

    public void setLeftWeight(int child) {
        this.weights[0] = child;
    }

    public int getRightWeight() {
        return weights[1];
    }

    public void setRightWeight(int child) {
        this.weights[1] = child;
    }

    public String getLabel() {
        return this.label;
    }

    public String toString() {
        return "Node: " + this.label;
    }
}
