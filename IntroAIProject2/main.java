package IntroAIProject2;

import java.util.List;

public class main{
	
public static void main(String[] args){
	
	path path = new path();
	
	
	Node[][] board = path.buildPath();
	path.printBoard(board);
	System.out.println(IntroAIProject2.path.startX);
	List<Node> neighbors = path.getNeighbors(board[IntroAIProject2.path.startX][IntroAIProject2.path.startY]);
	path.fillHeap(neighbors);
	path.afterPrint(board);
	
	
	}
}