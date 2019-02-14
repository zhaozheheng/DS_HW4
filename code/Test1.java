//queue class
class Queue{
	private final int SIZE = 20;
	private int[] queArray;
	private int front;
	private int rear;
	
	//queue class constructor
	public Queue() {
		// TODO Auto-generated constructor stub
		queArray = new int[SIZE];
		front = 0;
		rear = -1;
	}
	
	//insert operation
	public void insert(int num) {
		if (rear == SIZE - 1) {
			rear = -1;
		}
		queArray[++rear] = num;
	}
	
	//dequeue operation
	public int remove() {
		int tmp = queArray[front++];
		if (front == SIZE) {
			front = 0;
		}
		return tmp;
	}
	
	//check whether the queue is empty
	public boolean isEmpty() {
		return ( rear + 1 == front || (front + SIZE - 1 == rear));
	}
}

//vertex class
class Vertex{
	public char label;
	public boolean wasVisited;
	
	//vertex class constructor
	public Vertex(char lab) {
		label = lab;
		wasVisited = false;
	}
}

//graph class
class Graph{
	private final int MAX_VERTS = 20;	//set the max degree of the matrix
	private Vertex vertexList[];	//vertex list
	private int adjMat[][];	//adjacent matrix
	private int nVerts;	//vertex number
	private Queue theQueue;
	
	//graph class constructor
	public Graph() {
		// TODO Auto-generated constructor stub
		vertexList = new Vertex[MAX_VERTS];
		
		adjMat = new int [MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for (int i = 0; i < MAX_VERTS; i++) {
			for (int j = 0; j < MAX_VERTS; j++) {
				adjMat[i][j] = 0;
			}
		}
		theQueue = new Queue();
	}
	
	//add vertex function
	public void addVertex(char lab) {
		vertexList[nVerts++] = new Vertex(lab);
	}
	
	//add edge function
	public void addEdge(int start, int end) {
		adjMat[start][end] = 1;
	}
	
	//BFS traverse return the result by String
	public String bfs() {
		String result = "";
		for (int i = 0; i < nVerts; i++) {
			while (vertexList[i].wasVisited == false) {
				vertexList[i].wasVisited = true;
				//displayVertex(i);
				result += String.valueOf(vertexList[i].label);
				theQueue.insert(i);
				int v2;
				
				while (!theQueue.isEmpty()) {
					int v1 = theQueue.remove();
					while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {
						vertexList[v2].wasVisited = true;
						//displayVertex(v2);
						result += String.valueOf(vertexList[v2].label);
						theQueue.insert(v2);
					}
				}
			}
		}
		return result;
		/*reset all the vertex to unvisited
		for (int i = 0; i < nVerts; i++) {
			vertexList[i].wasVisited = false;
		}
		*/
	}

	//get the adjacent unvisited vertex
	public int getAdjUnvisitedVertex(int v) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nVerts; i++) {
			if (adjMat[v][i] == 1 && vertexList[i].wasVisited == false) {
				return i;
			}
		}
		return -1;
	}
}

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph theGraph = new Graph();
		//add vertex
		theGraph.addVertex('A');	//#0
		theGraph.addVertex('B');	//#1
		theGraph.addVertex('C');	//#2
		theGraph.addVertex('D');	//#3
		theGraph.addVertex('E');	//#4
		theGraph.addVertex('F');	//#5
		theGraph.addVertex('G');	//#6
		theGraph.addVertex('H');	//#7
		theGraph.addVertex('I');	//#8
		theGraph.addVertex('J');	//#9
		
		//add edges
		theGraph.addEdge(0, 1);		//#0
		theGraph.addEdge(0, 2);		//#1
		theGraph.addEdge(1, 5);		//#2
		theGraph.addEdge(6, 1);		//#3
		theGraph.addEdge(2, 4);		//#4
		theGraph.addEdge(2, 5);		//#5
		theGraph.addEdge(3, 2);		//#6
		theGraph.addEdge(4, 3);		//#7
		theGraph.addEdge(4, 7);		//#8
		theGraph.addEdge(7, 4);		//#9
		theGraph.addEdge(4, 8);		//#10
		theGraph.addEdge(5, 6);		//#11
		theGraph.addEdge(5, 7);		//#12
		theGraph.addEdge(8, 7);		//#13
		theGraph.addEdge(8, 9);		//#14
		
		System.out.print("BFS traverse: ");
		System.out.println();
		String result = theGraph.bfs();	//activate BFS traverse
		System.out.print(result);
		System.out.println();
	}
}
