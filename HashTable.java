import java.util.Random;

/*
 * This class builds a hash table that is capable of storing
 * integer keys with the hash returning return (int) 
 * (((2971L * k + 101923L) % 128189L) % (long) size)  and returns random for hash2this is doing 
 * hash3's  and hash4's major job and much more effienctly.
 * 
 */
public class HashTable {
	private Node [] table;			
	private int size;						
	private Random rand; 
	public HashTable (int _size) {
		size = _size;
		table = new Node[size];
	    rand = new Random();
	}
	public void insert1(int key) {
		int i = hash1(key);
		table[i] = new Node(key, table[i]);
	}
	
	public void insert2(int key) {
        int i = hash2(key);
        table[i] = new Node(key, table[i]);
    }

	
	public void print(int i) {
		for (Node curr = table[i]; curr != null; curr = curr.next) 
		{	
		    System.out.println(curr.key);
		}
	}
	public boolean find(int key) {
		int i = hash1(key);
		for (Node curr = table[i]; curr != null; curr = curr.next)
			if (curr.key == key) return true;
		return false;
	}
	//will return k inserted equation this returns an effiecent version of hash3
	//for prog3.
	private int hash1(int k) {
	    return (int) (((2971L * k + 101923L) % 128189L)  % (long) size);
	}
	//will return random int for k for an effient version of hash4 for prog4
	private int hash2(int k) {
        rand.setSeed(k);
        return rand.nextInt(size);
    }

}
