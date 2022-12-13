
import java.util.Scanner;
import java.util.ArrayList; // This is a dynamic array (an array the expands when needed)
// this comment is just so ik i turned in the updated code!

public class FormingTeams {
	private static int n; // The number of nodes
	private static int m; // The number of edges
	public static int[][] team;// double array to store both student and team
	private static int[] dn;
	public static int x = 0;

	// private static int r;
	// this allows code to run in vim :)
	// unsure if we were supposed to keep so i commented it out lol.
	// @SuppressWarnings("unchecked")
	/**
	 * @param args
	 *             main method where team is printed.
	 *             Got immpossible almost working.
	 *             Couldn't figure everything out but I tried ;-;
	 */
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		n = kb.nextInt();
		m = kb.nextInt();
		// hannahs funness went down thruout this. im sorry.
		// An adjacency list is an array of arrays (or array of ArrayList objects)
		ArrayList<Integer>[] adjacency_list = new ArrayList[n + 1];
		// boolean[] v = new boolean[n + 1];
		for (int i = 0; i < n + 1; ++i)
			adjacency_list[i] = new ArrayList<Integer>();

		// Read the edges and build the adjacency list. adjacency_list[x] contains the
		// neighbor of x.
		for (int i = 0; i < m; ++i) {
			int a = kb.nextInt();
			int b = kb.nextInt();
			adjacency_list[a].add(new Integer(b));
			adjacency_list[b].add(new Integer(a));
		}

		kb.close();

		// Given code above
		// adjacency_list[x] contains the neighbors of x. You can iterate through them
		// as...
		// for (Integer y: adjacency_list[x]) {
		// y is a neighbor of x. But it is an Integer object.
		// int z = y.intValue() is the neighbor, which is of type int.
		// ...
		// }

		// TODO: Code to form teams using depth-first search
		// meaning 0 is unassigned team.
		team = new int[n + 1][3];
		// quick n +1 vairbale so i dont have to type it so many times :3
		int b = n + 1;
		// initialize all of team one to zero which will make it unassigned.
		for (int i = 0; i < b; i++) {
			// simple for loops to keep it under 3
			for (int c = 0; c < 3; c++) {
				// sets all of teams to unassigned to reset the list.
				team[i][c] = 0;
			}
		}
		team[1][1] = 1;

		// the for loop given above.
		// had to change up given code to make it work with my code.
		// irrerate thru the adjacency list.
		for (x = 0; x < b; x++) {
			for (Integer y : adjacency_list[x]) {
				// converts it to int val to be used.
				int z = y.intValue();
				// was going to see if z or x was equal neighbors but scraped that just in case
				// it decided to nuke my code :P
				binaryteam(x, z);

			}
		}
		dn = new int[x + 1];
		// int t2[] = new int[x + 1];
		// int t3[] = new int[x + 1];
		int i = 0;
		for (i = 1; i < x; i++) {
			// sets up the array to be printed.
			if (team[i][1] == 1) {
				dn[i] = 1;

			} else if (team[i][2] == 1) {
				dn[i] = 2;
			}
			// will test if immppossible or not.
			// if student is on the different teams return immposible.
			else if (team[i][2] == 1 && team[i][1] == 1) {
				// returns so it stops everything.
				System.out.println("impossible");
				break;
			} else {
				// returns so it stops everything.
				System.out.println("impossible");
				return;
			}
		}
		// else {
		// System.out.println("impossible");
		// return;
		// }

		// quick print function :3
		print(dn);

		/*
		 * old code i scraped but wanted to keep justttt incase.
		 * for (int i = 1; i <= n; i++) {
		 * if (!v[i]) {
		 * dfs(adjacency_list, 0);
		 * System.out.println(c);
		 * if (c != 1 & c % 2 == 1) {
		 * r++;
		 * c = 1;
		 * }
		 * }
		 * }
		 */
		/*
		 * for (int x = 1; x <= n; x++) {
		 * for (Integer y : adjacency_list[x]) {
		 * int z = y.intValue();
		 * if (!v[z]) {
		 * dfs(adjacency_list, 0);
		 * System.out.println(z);
		 * if (z != 1 & z % 2 == 1) {
		 * r++;
		 * z = 1;
		 * }
		 * 
		 * }
		 * 
		 * }
		 * }
		 */
	}

	/**
	 * @param dn
	 *           uses int array to print out formed teams.
	 *           and has a extra line to help format.
	 *           simple print that allows the main to print out.
	 */
	public static void print(int[] dn) {
		for (int a = 1; a < x; a++) {
			System.out.print(dn[a] + " ");
		}
		System.out.println("");
	}

	/**
	 * @param s1
	 * @param s2
	 *           This will check if team member has been placed or not already!!!
	 *           checks thru array to see if wether team has been set or not . If it
	 *           has set to either team 1 or 2 and if still unassigned set to team
	 *           1.
	 *           kinda does a dfs to this but not really im sorry my brain is all
	 *           messed up this week AGAIN
	 */
	public static void binaryteam(int s1, int s2) {
		// place team mates into team 1 if not in a team and not in team 2!
		if (team[s1][2] == 1) {
			// see if teem needs to be set.
			if (team[s2][1] != 1) {
				if (team[s2][2] == 0)
				// sets team.
				{
					team[s2][1] = 1;
				}
			}

		}
		// if student in team one and then places one team 2 if in team 1 already.
		if (team[s1][1] == 1) {
			// assigns team :3
			if ((team[s2][2] != 1)) {
				if ((team[s2][1] == 0)) {
					// sets to team to 2
					team[s2][2] = 1;
				}
			}
			// nothing set so set to team 1.
		} else if ((team[s2][2] == 0)) {
			// just assign to team 1 :3
			if (team[s1][1] == 0) {
				team[s2][1] = 1;
			}

		}
		// ended up breaking code and tests lol
		// else {
		// this really doesnt do anything but its here JUST IN CASE.
		// System.out.println("impossible");
		// returns.
		// return;
		// }

	}
	/*
	 * Unsure of these functions but kept them
	 * public static void dfs_rec(ArrayList<Integer>[] adjacency_list, boolean[] v,
	 * int team) {
	 * v[team] = true;
	 * 
	 * for (int i = 0; i < adjacency_list[team].size(); i++) {
	 * 
	 * if (!v[i]) {
	 * dfs_rec(adjacency_list, v, i);
	 * }
	 * }
	 * }
	 * 
	 * public static void dfs(ArrayList<Integer>[] adjacency_list, int team) {
	 * int n = adjacency_list[team].size();
	 * boolean[] v = new boolean[n];
	 * dfs_rec(adjacency_list, v, team);
	 * }
	 * 
	 * 
	 * public static void teams() {
	 * for (int i = 1; i <= n; i++) {
	 * v[i] = false;
	 * }
	 * }
	 */
}
