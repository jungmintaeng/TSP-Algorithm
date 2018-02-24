package jungmin.Algorithm;

import java.util.*;

public class Node_With_Bound implements Comparable<Node_With_Bound>{
	int level;
	int bound;
	List<Integer> path;
	public Node_With_Bound(){
		path = new ArrayList<Integer>();
	}
	@Override
	public int compareTo(Node_With_Bound o) {
		if(this.bound > o.bound)
			return 1;
		if(this.bound < o.bound)
			return -1;
		return 0;
	}
}
