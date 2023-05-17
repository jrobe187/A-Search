package IntroAIProject2;

public class Node {
	
	private int row, col, f, g, h, type, diagonal;
	private Node parent;
   
	public Node(int r, int c, int t, int d){
		row = r;
		col = c;
		type = t;
		parent = null;
		diagonal = d;
		//type 0 is traverseable, 1 is not
		//type 2 is start node, type 3 is goal node
		//diagonal 1 is diagonal, diagonal 0 is orthogonal
	}
	
	//mutator methods to set values
	public void setF(){
		f = g + h;
	}
	public void setG(int value){
		g = value;
	}
	public void setH(int value){
		h = value;
	}
	public void setParent(Node n){
		parent = n;
	}
	public void setDiagonal(int val) {
		diagonal = val;
	}
	//accessor methods to get values
	public int getF(){
		return f;
	}
	public int getG(){
		return g;
	}
	public int getH(){
		return h;
	}
	public int getType() {
		return type;
	}
	public Node getParent(){
		return parent;
	}
	public int getDiagonal() {
		return diagonal;
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	
	public boolean equals(Object in){
		//typecast to Node
		Node n = (Node) in;
		
		return row == n.getRow() && col == n.getCol();
	}
   
	public String toString(){
		return "Node: " + row + "_" + col;
	}
	
}
