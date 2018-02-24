package jungmin.Algorithm;

import java.util.Scanner;

import org.omg.CORBA.SystemException;

/*HW1
- bound 함수가 T.S.P. algorithm의 성능에 미치는 영향을 분석해 보는
	programming 과제입니다.
- 수행속도 등을 평가할 때 입력하는 graph는 모든 노드 쌍에 대하여
	directed edge가 존재하는 fully connected graph를 사용하기 바랍니다.
- source file, executable file과 실행 결과 분석 리포트를 zip하여 제출합니다.
- 프로그래밍언어는 C/C++ 또는 Java 중 하나로 선택하여 프로그래밍하기 바랍니다.

  1. Breath-First
  2. Best-First.TextBookVersion
  3. Estimated(unknown) cost only
  4. Actual(known) current cost only
  */

public class TSPMain {
	private static final int[][] WText = {/*V1->Vx*/{ 0,14, 4,10,20},
			   							  /*V2->Vx*/{14, 0, 7, 8, 7},
			   							  /*V3->Vx*/{ 4, 5, 0, 7,16},
			   							  /*V4->Vx*/{11, 7, 9, 0, 2},
			   							  /*V5->Vx*/{18, 7,17, 4, 0}};
	private static final int[][] W5 = {/*V1->Vx*/{ 0,19, 8,10, 7},
			   						   /*V2->Vx*/{ 6, 0, 4,16,10},
			   						   /*V3->Vx*/{ 8, 1, 0, 9,12},
			   						   /*V4->Vx*/{19,21, 5, 0,11},
			   						   /*V5->Vx*/{ 3, 5,16,13, 0}};

	private static final int[][] W10 = {/*V1->Vx*/{ 0, 5,11,21, 8,10,12, 7, 9,10},
										/*V2->Vx*/{14, 0, 5, 3,11,16, 1,19,12, 9},
										/*V3->Vx*/{ 3, 7, 0,11, 9,18, 6, 2, 2,10},
										/*V4->Vx*/{15,17, 3, 0, 2,13, 5,18,13, 0},
										/*V5->Vx*/{ 7,13, 4,15, 0,19, 6, 3, 9,11},
										/*V6->Vx*/{ 9,14, 8,16,12, 0, 1,14, 4, 3},
										/*V7->Vx*/{11, 6, 3,13,19, 1, 0,12, 7, 2},
										/*V8->Vx*/{13, 8, 7,15,14, 8,22, 0, 2, 1},
										/*V9->Vx*/{ 1, 5, 8,10, 7, 9,17, 8, 0,13},
									   /*V10->Vx*/{ 5,10,15,20, 6,13,10,22, 1, 0}};
	
	
	private static void printInputGraph(int n, int W[][]){
		for(int i = 0; i < n; i++){
			System.out.print("{ ");
			for(int j = 0; j < n; j++)
			{
				if(W[i][j] < 10)
					System.out.print(" ");
				System.out.print(W[i][j] + " ");
			}

			System.out.println("}");
		}
		System.out.println("");
	}
	
	private static void print(Node_With_Bound minNode, int W[][]){
		int minLength;
		for(Integer integer : minNode.path){
			System.out.print(integer + " ");
		}
		System.out.println("");
		minLength = TSPAlgorithm.calculate_Length_Print(minNode.path, W);
		System.out.println("minLength : " + minLength + "\nNode 수 : " + TSPAlgorithm.getNumOfNode() + "\n");
	}
	
	private static void print(Node minNode, int W[][]){
		int minLength;
		for(Integer integer : minNode.path){
			System.out.print(integer + " ");
		}
		System.out.println("");
		minLength = TSPAlgorithm.calculate_Length_Print(minNode.path, W);
		System.out.println("minLength : " + minLength + "\nNode 수 : " + TSPAlgorithm.getNumOfNode() + "\n");
	}
	
	public static void main(String[] args) {
		Node_With_Bound minNode;
		Node breadthNode;
		
		System.out.println("WText : TextBook에 있는 input size 5 graph");
		printInputGraph(5, WText);
		System.out.println("W5 : 임의로 만들어진 input size 5 graph");
		printInputGraph(5, W5);
		System.out.println("W10 : 임의로 만들어진 input size 10 graph");
		printInputGraph(10, W10);
		
		minNode = TSPAlgorithm.TSP_Best_TextBook(5, W5);
		System.out.println("-------------input5 W5---------TextBookBound");
		print(minNode, W5);
		minNode = TSPAlgorithm.TSP_Best_TextBook(10, W10);
		System.out.println("-------------input10 W10---------TextBookBound");
		print(minNode, W10);
		minNode = TSPAlgorithm.TSP_Best_TextBook(5, WText);
		System.out.println("-------------input5 WText---------TextBookBound");
		print(minNode, WText);
		
		minNode = TSPAlgorithm.TSP_Best_Known(5, W5);
		System.out.println("-------------input5 W5---------Known");
		print(minNode, W5);
		minNode = TSPAlgorithm.TSP_Best_Known(10, W10);
		System.out.println("-------------input10 W10---------Known");
		print(minNode, W10);
		minNode = TSPAlgorithm.TSP_Best_Known(5, WText);
		System.out.println("-------------input5 WText---------Known");
		print(minNode, WText);
		
		
		minNode = TSPAlgorithm.TSP_Best_Unknown(5, W5);
		System.out.println("-------------input5 W5---------Unknown");
		print(minNode, W5);
		minNode = TSPAlgorithm.TSP_Best_Unknown(10, W10);
		System.out.println("-------------input10 W10---------Unknown");
		print(minNode, W10);
		minNode = TSPAlgorithm.TSP_Best_Unknown(5, WText);
		System.out.println("-------------input5 WText---------Unknown");
		print(minNode, WText);
		
		breadthNode = TSPAlgorithm.TSP_Breadth(5, W5);
		System.out.println("-------------input5 W5---------Breadth");
		print(breadthNode, W5);
		breadthNode = TSPAlgorithm.TSP_Breadth(10, W10);
		System.out.println("-------------input10 W10---------Breadth");
		print(breadthNode, W10);
		breadthNode= TSPAlgorithm.TSP_Breadth(5, WText);
		System.out.println("-------------input5 WText---------Breadth");
		print(breadthNode, WText);
		
		System.out.println("input vertex 5, depth n-2까지 고려할 때 나올 수 있는 최대 노드 수 : "+
				TSPAlgorithm.maxNumOfNode(5) + "\n");
		System.out.println("input vertex 10, depth n-2까지 고려할 때 나올 수 있는 최대 노드 수 : "+
						TSPAlgorithm.maxNumOfNode(10));
		
		System.out.println("\n종료하려면 엔터키를 눌러주세요...\n");
		try{System.in.read();}catch(Exception e){}
	}
}
