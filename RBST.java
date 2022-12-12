import java.util.Random;

public class RBST {

	private Node root; // Head node of the tree.
	private Random rand; // A random object - required to randomly insert nodes into the tree.

	// Constructors
	public RBST() {
		root = null;
		rand = new Random();
	}

	public RBST(Node _root) {
		root = _root;
		rand = new Random();
	}

	/**
	 * Wraper print method to print the contents of the tree. Calls the private
	 * print method.
	 */
	public void print() {
		print(root);
		System.out.println();
	}

	/**
	 * Print method to print the contents of the tree.
	 */
	private void print(Node T) {
		// TODO: An inorder traversal to print the sequence.
		if (T == null) {
			return;
		}
		print(T.getLeft());
		System.out.print(T.getTeam() + " ");
		print(T.getRight());
	}

	/**
	 * Wrapper for insertNormal method.
	 */
	public void insertNormal(int team, int rank) {
		root = insertNormal(root, team, rank);
	}

	/**
	 * Insert the data team at position rank into node T. This is the normal insert
	 * routine without any balancing.
	 */
	private Node insertNormal(Node T, int team, int rank) {
		// TODO: Base cases here. Inserting into a null tree.
		// testing if null tree to set a new tree.
		if (T == null) {
			return new Node(team);
		}
		assert (rank >= 1 && rank <= T.getSize() + 1)
				: "rank should be between 1 and size of the tree <" + (T.getSize() + 1) + ">";

		// TODO:Recursive case. Recursively insert into left tree if rank <= rank of
		// root. Otherwise insert into right tree.
		// rank of root
		int rOr = 0;
		// testing the rOr value against
		if (T.getLeft() != null) {
			rOr = T.getLeft().getSize() + 1;
		} else if (T.getLeft() == null) {
			rOr = 1;
		}
		if (rank <= rOr) {
			T.setLeft(insertNormal(T.getLeft(), team, rank));

		} else {
			T.setRight(insertNormal(T.getRight(), team, rank - rOr));
		}
		T.incSize();
		return T; // Need to return the actual tree.
	}

	/**
	 * Split the tree at psition rank. It returns RET, a RBST array of length two.
	 * RET[0] is the left side of the split,
	 * and RET[1] is the right side of the split. This is a wrapper method that
	 * calls the private split method.
	 */
	public RBST[] split(int rank) {
		Node[] ret = split(root, rank);
		RBST[] RET = { null, null };
		RET[0] = new RBST(ret[0]);
		RET[1] = new RBST(ret[1]);
		return RET;
	}

	/**
	 * The private split method that splits tree T at position rank.
	 * It returns an array ret, of two nodes -- ret[0] is the root of the left tree,
	 * and
	 * ret[1] is the root of the right tree of the split.
	 */
	private Node[] split(Node T, int rank) {
		Node[] ret = { null, null }; // ret[0] is the root node to the left side of the split, ret[1] is the right
		// side.
		// this will return ret if T is null.
		if (T == null) {
			return ret;
		}

		// TODO: Fill your code here for the split method. It is easy to implement this
		// recursively.
		// Your base case will be an empty tree. Your recursive case will have three
		// cases -- think
		// what happens if the rank of the root == rank, or if rank is smaller or larger
		// than the rank
		// of the root.
		// rank of root
		int rOr;
		// setting rOr depending on if T.getlift is null or not.
		if (T.getLeft() == null) {
			rOr = 1;
		} else {
			rOr = (T.getLeft().getSize() + 1);
		}
		// camparing rank to root. will allow to see where to split depending on rOr
		// rank value.
		if (rank < rOr) {
			// will split the left side and connect ret[1] back to T
			Node[] w = split(T.getLeft(), rank);
			// allow for w[1] to connect to T.
			T.setLeft(w[1]);
			ret[1] = T;
			ret[0] = w[0];

		} else if (rank == rOr) {
			// will set T to right and and set the left to the T.
			ret[1] = T.getRight();
			// set the right to null.
			T.setRight(null);
			ret[0] = T;

		} else if (rank > rOr) {
			// split at the right rank and connect ret[0] back to
			Node[] w = split(T.getRight(), rank - rOr);
			T.setRight(w[0]);
			ret[0] = T;
			ret[1] = w[1];
		}
		T.updateSize();

		return ret;
	}

	/**
	 * Insert the data team at position rank in the tree. This is a wrapper method
	 * that calls the private insert method.
	 */
	public void insert(int team, int rank) {
		root = insert(root, team, rank);

	}

	/**
	 * The private insert method, that inserts the data team at position rank in the
	 * tree rooted at node T.
	 * team is inserted at the root with probability 1/(T.getSize()+1). This is done
	 * by splitting the tree T
	 * at position rank-1, creating a new node for team, and attaching the left and
	 * right sides of the split as
	 * the two subtrees of the new node. Otherwise, with probability 1 -
	 * 1/(T.getSize()+1), insert recursively
	 * at either the left tree (rank <= rank of root) or at the right tree (rank >
	 * rank of root).
	 */
	private Node insert(Node T, int team, int rank) {
		// TODO: Base case here. Inserting into a null tree.
		if (T == null) {
			return new Node(team);
		}
		assert (rank >= 1 && rank <= T.getSize() + 1)
				: "rank should be between 1 and size of the tree <" + (T.getSize() + 1) + ">";

		// TODO:Recursive case. With probability 1 / (T.getSize() + 1), the new node
		// becomes the root. Otherwise
		// recursively insert into left or right subtrees depending upon the rank.
		int s = rand.nextInt();
		int rOr = 0;

		// seetting rank of root.
		if (T.getLeft() == null) {
			rOr = 1;
		}
		if (T.getLeft() != null) {
			rOr = T.getLeft().getSize() + 1;
		}

		// propibility test with 1/ror for 1/n testing for when it is greater or equal
		// to
		// random value. If equal it will split and print the left and right balanced.
		if (s <= (1 / rOr)) {
			// this will set rank equal to the left node.
			Node[] a = split(T, rank - 1);
			T = new Node(team, a[0], a[1]);
			return T;
			// will recursivly insert into subtree left or right depinging on roots ranks.
		} else if (rank <= rOr) {
			T.setLeft(insert(T.getLeft(), team, rank));
		} else {
			T.setRight(insert(T.getRight(), team, rank - rOr));
		}
		T.incSize();
		return T;

		// Need to return the actual tree.
	}

	/**
	 * Return the node at position rank in the tree. This is a wrapper method that
	 * calls the private select method.
	 */
	public Node select(int rank) {
		return select(root, rank);
	}

	/**
	 * The select method that returns the node in the tree at position rank.
	 */
	private Node select(Node T, int rank) {
		// TODO: Base case. Return null if the tree is empty.

		assert (rank >= 1 && rank <= T.getSize())
				: "rank should be between 1 and size of the tree <" + T.getSize() + "> ";
		// TODO: Recursive case. Return T if rank is equal to the rank of the root. Else
		// revursively select in either the left tree (rank < rank of root) or the right
		// tree (rank > rank of the root).
		int r;
		// testing if Node t is null and setting r to 0;
		if (T == null) {
			r = 0;
		}
		// if left is null set r to 1
		else if (T.getLeft() == null) {
			r = 1;
		} else { // else if left is not null and add 1 to the left.
			r = T.getLeft().getSize() + 1;
		}
		if (rank == r) {
			// return T if rank equals rank of root
			return T;
		} else if (rank > r) { // if rank is greater then rank of root return right and rank - rank of root.
			return select(T.getRight(), rank - r);
		} else if (rank < r) { // if rank is less then rank of root just get left of T and rank.
			return select(T.getLeft(), rank);
		} // return T if this statement is reached even tho it wont but it has to return
			// something.
		return T;
	}

	/**
	 * Returns the size of the tree.
	 */
	public int getSize() {
		if (root == null)
			return 0;
		return root.getSize();
	}
}
