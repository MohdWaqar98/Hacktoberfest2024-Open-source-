// Java implementation of the above approach.
import java.util.*;

class GFG 
{

	// function to find the minimum cost
	static int findMinCost(char[] s, int i, int j) 
	{
		// graph
		Vector<Integer>[] gr = new Vector[26];
		for (int iN = 0; iN < 26; iN++)
			gr[iN] = new Vector<Integer>();
			
		// adjacency matrix
		boolean[][] edge = new boolean[26][26];

		// initialising adjacency matrix
		for (int k = 0; k < 26; k++)
			for (int l = 0; l < 26; l++)
				edge[k][l] = false;

		// creating adjacency list
		for (int k = 0; k < s.length; k++) 
		{
			// pushing left adjacent element for index 'k'
			if (k - 1 >= 0 && !edge[s[k] - 97][s[k - 1] - 97]) 
			{
				gr[s[k] - 97].add(s[k - 1] - 97);
				edge[s[k] - 97][s[k - 1] - 97] = true;
			}
			// pushing right adjacent element for index 'k'
			if (k + 1 <= s.length - 1 && !edge[s[k] - 97][s[k + 1] - 97]) 
			{
				gr[s[k] - 97].add(s[k + 1] - 97);
				edge[s[k] - 97][s[k + 1] - 97] = true;
			}
		}

		// queue to perform BFS
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s[i] - 97);

		// visited array
		boolean[] v = new boolean[26];

		// variable to store depth of BFS
		int d = 0;

		// BFS
		while (q.size() > 0) 
		{

			// number of elements in the current level
			int cnt = q.size();

			// inner loop
			while (cnt-- > 0) 
			{

				// current element
				int curr = q.peek();

				// popping queue
				q.remove();

				// base case
				if (v[curr])
					continue;
				v[curr] = true;

				// checking if the current node is required node
				if (curr == s[j] - 97)
					return d;

				// iterating through the current node
				for (Integer it : gr[curr])
					q.add(it);
			}

			// updating depth
			d++;
		}

		return -1;
	}

	// Driver Code
	public static void main(String[] args)
	{
		// input variables
		String s = "abcde";
		int i = 0;
		int j = 4;

		// function to find the minimum cost
		System.out.print(findMinCost(s.toCharArray(), i, j));
	}
}

// This code is contributed by 29AjayKumar
