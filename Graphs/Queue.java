/**
 * This class implements a circular array.
 * It expands if the queue becomes full.
 **/
public class Queue {
	private static QNode[] queue; // Array that stores the queue elements.
	private static int front; // Index pointing to the front of the queue.
	private static int end; // Index pointing to the element that is one less than the end of the queue.
	private static int numElements; // The number of elements currently stored in the queue.
	private static int size; // The capacity of the allocated array. If the number of elements reaches this
	// capacity, we need to expand.

	/**
	 * Constructor: Allocates a queue with inital size of 1000.
	 **/
	public Queue() {
		numElements = 0;
		size = 1000;
		front = size - 1;
		end = size - 1;
		queue = new QNode[size];
	}

	/**
	 * This function enqueues a new element p into the queue.
	 * This also expands the array if it is full.
	 **/
	public void enqueue(QNode p) {
		if (numElements == size) {
			// TODO: Expand the array, by first creating another one with twice the size and
			// copying the contents of the old array.
			// increase size by 2
			size = size * 2;
			// sets queue to size
			queue = new QNode[size];
			// sets front to new queue which is numel -1 + size and find Remainder
			front = (numElements - 1 + size) % size;
			// same as the above but takes the end and redoes it.
			end = (end - 1 + size) % size;
		}
		// TODO: Code for normal enqueue.
		// if numel doesnt equal
		queue[end] = new QNode(p);
		// sets end
		end = (end - 1 + size) % size;
		// increases numelements
		numElements++;
	}

	/**
	 * This funciton removes and returns the end front element in the queue.
	 **/
	public QNode dequeue() {
		if (numElements == 0) {
			return null;
		}
		// TODO: Code to remove and return the front element.

		// Qnode dq to set to queue front
		QNode dq = queue[front];
		// removes front from the queue
		front = (front - 1 + size) % size;
		// decreases numelements
		numElements--;
		// returns the dequede node
		return dq; // remove this line once the funciton is completed.
	}

	/**
	 * This funciton returns true if the queue is empty, otherwise returns false.
	 **/
	public boolean isEmpty() {
		if (numElements == 0)
			return true;
		return false;
	}

	/**
	 * This function prints the contents of the queue.
	 **/
	public static void print() {
		// TODO: print the contents of the queue from front to end. Please print each
		// element on its own line. You may use the toString() method of QNode to print
		// it on a line.
		// simple print method :)
		for (int i = 0; i < numElements; i++) {
			// simple int to print front - i + size and finds the remiander
			int content = (front - i + size) % size;
			// if queue if not null
			if (queue[content] != null) {
				// prints the queue
				System.out.println(queue[content]);
			}
		}
	}

	/*
	 * My quick driver to test :)
	 * public static void main(String[] args) {
	 * int n = 10;
	 * 
	 * Queue q = new Queue();
	 * q.enqueue(new QNode(1, "past"));
	 * q.enqueue(new QNode(2, "pest"));
	 * q.enqueue(new QNode(3, "cast"));
	 * q.enqueue(new QNode(4, "dies"));
	 * q.enqueue(new QNode(5, "cras"));
	 * q.enqueue(new QNode(6, "best"));
	 * q.enqueue(new QNode(7, "bast"));
	 * q.enqueue(new QNode(8, "cash"));
	 * System.out.println(" enqueue end");
	 * print();
	 * 
	 * q.dequeue(new QNode(8, "boot"));
	 * 
	 * }
	 */
}
