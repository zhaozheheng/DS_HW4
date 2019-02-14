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
	
	public int getnVerts() {
		return nVerts;
	}

	//BFS traverse return the result by String
	public String bfs(int num) {
		String result = "";
		vertexList[num].wasVisited = true;
		//displayVertex(i);
		result += String.valueOf(vertexList[num].label);
		theQueue.insert(num);
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
	
		//reset all the vertex to unvisited
		for (int i = 0; i < nVerts; i++) {
			vertexList[i].wasVisited = false;
		}
		return result;
		
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

/*my method is as follows:
 *if each node can BFS traverse to other nodes
 *this graph is strongly connected
 *otherwise, it's not.
 *implemented by Zheheng Zhao
*/
public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph theGraph1 = new Graph();	//This is not a strongly connected graph.
		boolean isSC = true;
		int numOfVertex1;
		//add vertex
		theGraph1.addVertex('A');	//#0
		theGraph1.addVertex('B');	//#1
		theGraph1.addVertex('C');	//#2
		theGraph1.addVertex('D');	//#3
		theGraph1.addVertex('E');	//#4
		theGraph1.addVertex('F');	//#5
		theGraph1.addVertex('G');	//#6
		theGraph1.addVertex('H');	//#7
		theGraph1.addVertex('I');	//#8
		theGraph1.addVertex('J');	//#9
		
		//add edges
		theGraph1.addEdge(0, 1);	//#0
		theGraph1.addEdge(0, 2);	//#1
		theGraph1.addEdge(1, 5);	//#2
		theGraph1.addEdge(6, 1);	//#3
		theGraph1.addEdge(2, 4);	//#4
		theGraph1.addEdge(2, 5);	//#5
		theGraph1.addEdge(3, 2);	//#6
		theGraph1.addEdge(4, 3);	//#7
		theGraph1.addEdge(4, 7);	//#8
		theGraph1.addEdge(7, 4);	//#9
		theGraph1.addEdge(4, 8);	//#10
		theGraph1.addEdge(5, 6);	//#11
		theGraph1.addEdge(5, 7);	//#12
		theGraph1.addEdge(8, 7);	//#13
		theGraph1.addEdge(8, 9);	//#14
		
		numOfVertex1 = theGraph1.getnVerts();
		for (int i = 0; i < numOfVertex1; i++) {
			String result = theGraph1.bfs(i);	//activate BFS traverse
			System.out.print("Visited: ");
			System.out.print(result);
			System.out.println();
			if (result.length() == numOfVertex1) {	//check if the current node can reach the remain nodes in this graph
				isSC = isSC && true;
			}
			else {	//if one node cannot reach other ones, it's not strongly connected
				isSC = false;
			}
		}
		System.out.println(isSC);
		
		Graph theGraph2 = new Graph();	//This is a strongly connected graph.
		isSC = true;
		int numOfVertex2;
		//add vertex
		theGraph2.addVertex('A');	//#0
		theGraph2.addVertex('B');	//#1
		theGraph2.addVertex('C');	//#2
		theGraph2.addVertex('D');	//#3
		theGraph2.addVertex('E');	//#4
		theGraph2.addVertex('F');	//#5
		theGraph2.addVertex('G');	//#6
		theGraph2.addVertex('H');	//#7
		theGraph2.addVertex('I');	//#8
		theGraph2.addVertex('J');	//#9
		
		//add edges
		theGraph2.addEdge(0, 1);	//#0
		theGraph2.addEdge(0, 2);	//#1
		theGraph2.addEdge(1, 5);	//#2
		theGraph2.addEdge(6, 1);	//#3
		theGraph2.addEdge(2, 4);	//#4
		theGraph2.addEdge(2, 5);	//#5
		theGraph2.addEdge(3, 2);	//#6
		theGraph2.addEdge(4, 3);	//#7
		theGraph2.addEdge(4, 7);	//#8
		theGraph2.addEdge(7, 4);	//#9
		theGraph2.addEdge(4, 8);	//#10
		theGraph2.addEdge(5, 6);	//#11
		theGraph2.addEdge(5, 7);	//#12
		theGraph2.addEdge(8, 7);	//#13
		theGraph2.addEdge(8, 9);	//#14
		theGraph2.addEdge(1, 0);	//#15
		theGraph2.addEdge(9, 7);	//#16
		
		numOfVertex2 = theGraph2.getnVerts();
		for (int i = 0; i < numOfVertex2; i++) {
			String result = theGraph2.bfs(i);	//activate BFS traverse
			System.out.print("Visited: ");
			System.out.print(result);
			System.out.println();
			if (result.length() == numOfVertex2) {	//check if the current node can reach the remain nodes in this graph
				isSC = isSC && true;
			}
			else {	//if one node cannot reach other ones, it's not strongly connected
				isSC = false;
			}
		}
		System.out.println(isSC);
	}
}
