package jungmin.Algorithm;

import java.util.*;

public class TSPAlgorithm {
	private static final int VERY_BIG_NUMBER = 9999999;
	private static int numOfNode = 0;
	private TSPAlgorithm(){}

	public static Node_With_Bound TSP_Best_TextBook(int n, int W[][]){
		long time = System.currentTimeMillis();
		Node_With_Bound minNode_With_Bound = new Node_With_Bound();
		int minLength = VERY_BIG_NUMBER;
		PriorityQueue<Node_With_Bound> PQ = new PriorityQueue<Node_With_Bound>();
		Node_With_Bound u, v = new Node_With_Bound();
		v.path.add(Integer.valueOf(1));
		v.level = 0;
		v.bound = bound_TextBook(v, W);
		PQ.add(v);
		numOfNode = 1;
		
		while(!PQ.isEmpty()){
			v = PQ.poll();
			if(v.bound < minLength){
				for(int i = 2; i <= n; i++){
					if(v.path.contains(Integer.valueOf(i)))
							continue;
					numOfNode++;
					u = new Node_With_Bound();
					u.level = v.level + 1;
					u.path.addAll(v.path);
					u.path.add(i);
					if(u.level == n-2){
						u.bound = bound_TextBook(u, W);
						for(int j = 2; j <= n; j++)
							if(!u.path.contains(Integer.valueOf(j)))
									u.path.add(j);
						u.path.add(1);
						if(calculate_Length(u.path, W) < minLength){
							minLength = calculate_Length(u.path, W);
							minNode_With_Bound = u;
						}
					}
					else{
						u.bound = bound_TextBook(u, W);
						if(u.bound < minLength)
						{
							PQ.add(u);
						}
					}
				}
			}
		}
		time = System.currentTimeMillis() - time;
		System.out.println("TSP_Best_TextBook 실행 시간 : " + time);
		return minNode_With_Bound;
	}

	public static Node_With_Bound TSP_Best_Known(int n, int W[][]){
		long time = System.currentTimeMillis();
		Node_With_Bound minNode_With_Bound = new Node_With_Bound();
		int minLength = VERY_BIG_NUMBER;
		PriorityQueue<Node_With_Bound> PQ = new PriorityQueue<Node_With_Bound>();
		Node_With_Bound u, v = new Node_With_Bound();
		v.path.add(Integer.valueOf(1));
		v.level = 0;
		v.bound = bound_Known(v, W);
		PQ.add(v);
		numOfNode = 1;
		
		while(!PQ.isEmpty()){
			v = PQ.poll();
			if(v.bound < minLength){
				for(int i = 2; i <= n; i++){
					if(v.path.contains(Integer.valueOf(i)))
							continue;
					numOfNode++;
					u = new Node_With_Bound();
					u.level = v.level + 1;
					u.path.addAll(v.path);
					u.path.add(i);
					if(u.level == n-2){
						u.bound = bound_Known(u, W);
						for(int j = 2; j <= n; j++)
							if(!u.path.contains(Integer.valueOf(j)))
									u.path.add(j);
						u.path.add(1);
						if(calculate_Length(u.path, W) < minLength){
							minLength = calculate_Length(u.path, W);
							minNode_With_Bound = u;
						}
					}
					else{
						u.bound = bound_Known(u, W);
						if(u.bound < minLength)
						{
							PQ.add(u);
						}
					}
				}
			}
		}
		
		time = System.currentTimeMillis() - time;
		System.out.println("TSP_Best_Known 실행 시간 : " + time);
		return minNode_With_Bound;
	}

	public static Node_With_Bound TSP_Best_Unknown(int n, int W[][]){
		long time = System.currentTimeMillis();
		Node_With_Bound minNode_With_Bound = new Node_With_Bound();
		int minLength = VERY_BIG_NUMBER;
		PriorityQueue<Node_With_Bound> PQ = new PriorityQueue<Node_With_Bound>();
		Node_With_Bound u, v = new Node_With_Bound();
		v.path.add(Integer.valueOf(1));
		v.level = 0;
		v.bound = bound_Unknown(v, W);
		PQ.add(v);
		numOfNode = 1;
		while(!PQ.isEmpty()){
			v = PQ.poll();
			if(v.bound < minLength){
				for(int i = 2; i <= n; i++){
					if(v.path.contains(Integer.valueOf(i)))
							continue;
					numOfNode++;
					u = new Node_With_Bound();
					u.level = v.level + 1;
					u.path.addAll(v.path);
					u.path.add(i);
					if(u.level == n-2){
						for(int j = 2; j <= n; j++)
							if(!u.path.contains(Integer.valueOf(j)))
									u.path.add(j);
						u.path.add(1);
						if(calculate_Length(u.path, W) < minLength){
							minLength = calculate_Length(u.path, W);
							minNode_With_Bound = u;
						}
						u.bound = bound_Unknown(u, W);
					}
					else{
						u.bound = bound_Unknown(u, W);
						if(u.bound < minLength - calculate_Length(u.path, W)){
							PQ.add(u);
						}
					}
				}
			}
		}
		
		time = System.currentTimeMillis() - time;
		System.out.println("TSP_Best_Unknown 실행 시간 : " + time);
		return minNode_With_Bound;
	}
	
	public static Node TSP_Breadth(int n, int W[][]){
		long time = System.currentTimeMillis();
		Node_With_Bound temp;
		Node minNode = new Node();
		int minLength = VERY_BIG_NUMBER;
		Queue<Node> Q = new LinkedList<Node>();
		Node u, v = new Node();
		v.path.add(Integer.valueOf(1));
		v.level = 0;
		Q.add(v);
		numOfNode = 1;
		
		while(!Q.isEmpty()){
			v = Q.poll();
			temp = new Node_With_Bound();
			temp.level = v.level;
			temp.path.addAll(v.path);
			if(bound_TextBook(temp, W) < minLength){
				for(int i = 2; i <= n; i++){
					if(v.path.contains(Integer.valueOf(i)))
							continue;
					numOfNode++;
					u = new Node();
					u.level = v.level + 1;
					u.path.addAll(v.path);
					u.path.add(i);
					if(u.level == n-2){
						for(int j = 2; j <= n; j++)
							if(!u.path.contains(Integer.valueOf(j)))
									u.path.add(j);
						u.path.add(1);
						if(calculate_Length(u.path, W) < minLength){
							minLength = calculate_Length(u.path, W);
							minNode = u;
						}
					}
					else{
						temp = new Node_With_Bound();
						temp.level = u.level;
						temp.path.addAll(u.path);
						if(bound_TextBook(temp, W) < minLength)
						{
							Q.add(u);
						}
					}
				}
			}
		}
		
		time = System.currentTimeMillis() - time;
		System.out.println("TSP_Breadth 실행 시간 : " + time);
		return minNode;
	}
	
	private static int bound_TextBook(Node_With_Bound Node_With_Bound, int W[][]){
		int bound = 0;
		bound += calculate_Length(Node_With_Bound.path, W);
		ArrayList<Integer> leftVertex = new ArrayList<Integer>();
		if(Node_With_Bound.path.size() >= 1)
			leftVertex.add(Node_With_Bound.path.get(Node_With_Bound.path.size()-1));
		for(int i = 1 ; i <= W.length; i++){
			if(!Node_With_Bound.path.contains(Integer.valueOf(i)))
				leftVertex.add(Integer.valueOf(i));
		}
		for(Integer integer : leftVertex){
			int min = VERY_BIG_NUMBER;
			for(int i = 0 ; i < W.length; i++){
				if(min > W[integer.intValue() - 1][i] && W[integer.intValue() - 1][i] != 0)
					min = W[integer.intValue() - 1][i];
			}
			bound += min;
		}
		return bound;
	}
	
	private static int bound_Known(Node_With_Bound Node_With_Bound, int W[][]){
		int bound = 0;
		bound += calculate_Length(Node_With_Bound.path, W);
		ArrayList<Integer> leftVertex = new ArrayList<Integer>();
		for(int i = 1 ; i <= W.length; i++){
			if(!Node_With_Bound.path.contains(Integer.valueOf(i)))
				leftVertex.add(Integer.valueOf(i));
		}
		int min = VERY_BIG_NUMBER, startIndex = Node_With_Bound.path.get(Node_With_Bound.path.size()-1)-1;
		for(Integer integer : leftVertex){
			if(W[startIndex][integer - 1] < min)
				min = W[startIndex][integer - 1];
		}
		bound += min;
		return bound;
	}

	private static int bound_Unknown(Node_With_Bound Node_With_Bound, int W[][]){
		int bound = 0;
		ArrayList<Integer> leftVertex = new ArrayList<Integer>();
		if(Node_With_Bound.path.size() >= 1)
			leftVertex.add(Node_With_Bound.path.get(Node_With_Bound.path.size()-1));
		for(int i = 1 ; i <= W.length; i++){
			if(!Node_With_Bound.path.contains(Integer.valueOf(i)))
				leftVertex.add(Integer.valueOf(i));
		}
		for(Integer integer : leftVertex){
			int min = VERY_BIG_NUMBER;
			for(int i = 0 ; i < W.length; i++){
				if(min > W[integer.intValue() - 1][i] && W[integer.intValue() - 1][i] != 0)
					min = W[integer.intValue() - 1][i];
			}
			bound += min;
		}
		return bound;
	}
	
	public static int calculate_Length_Print(List<Integer> path, int W[][]){
		int length = 0;
		if(path.size() < 2)
			return 0;
		for(int i = 0; i< path.size() - 1; i++){
			System.out.println(path.get(i) + "->" +path.get(i+1) + " : " + W[path.get(i) - 1][path.get(i+1) - 1]);
			length += W[path.get(i) - 1][path.get(i+1) - 1];
		}
		return length;
	}
	
	private static int calculate_Length(List<Integer> path, int W[][]){
		int length = 0;
		if(path.size() < 2)
			return 0;
		for(int i = 0; i< path.size() - 1; i++){
			length += W[path.get(i) - 1][path.get(i+1) - 1];
		}
		return length;
	}
	
	public static int getNumOfNode(){
		return numOfNode;
	}
	
	public static int maxNumOfNode(int n){
		int nodenumber = 1;
		int node = 1;
		for(int i = n - 1; i > 1; i--)
		{
			node = 1;
			for(int j = n - 1;j >= i; j--)
				node *= j;
			nodenumber += node;
		}
		
		return nodenumber;
	}
}
