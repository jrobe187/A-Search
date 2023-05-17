package IntroAIProject2;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;




public class path{
	
	public static int startX;
	public static int startY;
	public static int goalX;
	public static int goalY;
	Node[][] board = new Node[15][15];
	List<Node> neighbors = new ArrayList<Node>();
	Node start;
	Node goal;

	
	private PriorityQueue<Node> openList = new PriorityQueue<>((n1, n2) -> Double.compare(n1.getF(), n2.getF()));
	private ArrayList<Node> closedList =  new ArrayList<Node>();
	
	
//Creates and fills array for path	
	public Node[][] buildPath(){
		System.out.println("Please input a number 0-14 for the starting X coordinate");
		Scanner coord = new Scanner(System.in);
		int x = coord.nextInt();
		startX = x;
		
		System.out.println("Please input a number 0-14 for the starting Y coordinate");
		coord = new Scanner(System.in);
		x = coord.nextInt();
		startY = x;
		
		start = new Node(startX, startY, 2, 0);
		
		board[startX][startY] = start;
		System.out.println("Please input a number 0-14 for the goal X coordinate");
		coord = new Scanner(System.in);
		x = coord.nextInt();
		goalX = x;
		
		System.out.println("Please input a number 0-14 for the goal Y coordinate");
		coord = new Scanner(System.in);
		x = coord.nextInt();
		goalY = x;
		goal = new Node(goalX, goalY, 3, 0);
		board[goalX][goalY] = goal;
 		Random random = new Random(); 
		 
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				int rand = random.nextInt(10);
				if(rand == 5) {						//10% of nodes untraversable
					board[i][j]= new Node(i, j, 1, 0);
				}
				else {
					board[i][j] = new Node(i, j, 0, 0);
				}
				
			}
		}
		return board;		
	}
	
	
	
	public void printBoard(Node[][] n) {
		int length = n.length;
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < length; j++) {
				if(n[i][j].getCol() == startX && n[i][j].getRow() == startY){
					System.out.print('S' + "  ");
				}
				else if(n[i][j].getCol() == goalX && n[i][j].getRow() == goalY) {
					System.out.print("E" + "  ");
				}
				else {
					if((n[i][j].getType() == 1)){
						System.out.print('#' + "  ");
					}
						
					else {
						System.out.print('-' + "  ");
					}
				}
			}
				System.out.println("");
		}
	}

	public List<Node> getNeighbors(Node node) {
		neighbors.clear();
	    int x = node.getCol();
	    int y = node.getRow();
	    
	    
	    for (int dx = -1; dx <= 1; dx++) {
	        for (int dy = -1; dy <= 1; dy++) {
	        	boolean isDiag = false;
	            if (dx == 0 && dy == 0) continue;
	            
	            int nx = x + dx;
	            int ny = y + dy;
	            
	            if(Math.abs(nx) + Math.abs(ny) == 2) {
	            	isDiag = true;
	            }
	            
	            if (nx < 0 || ny < 0 || nx > 14 || ny > 14) continue;
	            
	            Node neighbor = board[ny][nx];
	            neighbor.setParent(node);
	            if(isDiag == true) {
	            	neighbor.setDiagonal(1);
	            }
	            if (neighbor.getType() == 1) continue;
	            
	            neighbors.add(neighbor);
	        }
	    }
	    return neighbors;
	}


	public void gScore(Node s) {
			if(s.getDiagonal() == 1) {
				s.setG(s.getParent().getG() + 14);
			}
			else {
				s.setG(s.getParent().getG() + 10);
			}
	}

	public void hScore(Node s) {
		int xDistance = Math.abs(s.getRow() - goalX);
		int yDistance = Math.abs(s.getCol() - goalY);
		int h = xDistance + yDistance;
		h = h * 10;
		s.setH(h);
		
	}
	
	public void giveScores(List<Node> s) {
		for(int i = 0; i < s.size(); i++) {
			gScore(s.get(i));
			hScore(s.get(i));
			fScore(s.get(i));
		}
	}
	
	public void fScore(Node s) {
		s.setF();
	}
	
	public int fillHeap(List<Node> s) {
		
		start.setG(0);
		hScore(start);
		openList.add(start);
		
	
	while(openList.size() != 0) {
		Node head = openList.poll();
		if(head.getRow() == goal.getRow() && head.getCol() == goal.getCol()) {
			closedList.add(goal);
			System.out.println(goal);
			System.out.println("Goal state has been reached!");
			return 0;
		}
		closedList.add(head);
		s = getNeighbors(head);
		for(int i = 0; i < s.size(); i++) {
				giveScores(s);
				s.get(i).setParent(head);
				openList.add(s.get(i));
				
		}
				
		
		System.out.println(closedList.get(closedList.size() - 1));
		
	}
	System.out.println("no path could be found");
	return 0;
		

	}
	public void afterPrint(Node[][] n) {
		int rowCoords[] = new int[closedList.size()];
		int colCoords[] = new int[closedList.size()];
		for(int i = 1; i < closedList.size(); i++) {
			rowCoords[i] = closedList.get(i).getRow();
			colCoords[i] = closedList.get(i).getCol();
		}
		
		
		for(int i = 0; i <= 14; i++) {
			for(int j = 0; j <= 14; j++) {
				if(n[i][j].getCol() == startX && n[i][j].getRow() == startY){
					System.out.print('S' + "  ");
				}
				else if(n[i][j].getCol() == goalX && n[i][j].getRow() == goalY) {
					System.out.print("E" + "  ");
				}
				else if(closedList.contains(n[j][i])){
					System.out.print("X" + "  ");
				}
				
				else {
					if((n[i][j].getType() == 1)){
						System.out.print('#' + "  ");
					}
						
					else {
						System.out.print('-' + "  ");
					}
				}
			}
				System.out.println("");
		}
	}
	
	
}
