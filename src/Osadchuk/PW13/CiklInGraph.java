package PW13;

import java.io.IOException;
import java.util.Stack;

public class CiklInGraph {
    Graph graph;

    public CiklInGraph(Graph g) {
	this.graph = g;
    }

    private boolean isEilerCiklExist() {
	int OddVertex = 0;
	for (int i = 0; i < graph.V; ++i)
	    if (graph.degree(i) % 2 == 1) {
		++OddVertex;
	    }
	if (OddVertex > 2) {
	    return false;
	}
	boolean[] visited = new boolean[graph.V];
	for (int i = 0; i < graph.V; ++i)
	    if (graph.degree(i) > 0) {
		visited[i] = true;
		break;
	    }
	for (int i = 0; i < graph.V; ++i)
	    if (graph.degree(i) > 0 && !visited[i]) {
		return false;
	    }
	return true;
    }

    public void getEilerCikl() { // Построение цикла

	Stack<Integer> s = new Stack<Integer>();
	int v = 0;
	int u;

	s.push(v); // сохраняем ее в стек
	while (!s.isEmpty()) { // пока стек не пуст
	    v = s.peek(); // текущая вершина
	    if (graph.adj[v].isEmpty()) { // если нет инцидентных ребер
		v = s.pop();
		System.out.println(v + 1);
	    } else {
		u = v+1;
		s.push(u); // проходим в следующую вершину
	    }
	}
    }
    
    
    
    
    public static void main(String[] args) throws IOException {
	Graph g = new Graph(6);
	g.addEdge(0, 1);
	g.addEdge(1, 2);
	g.addEdge(2, 3);
	g.addEdge(3, 4);
	g.addEdge(4, 0);
	g.addEdge(1, 5);
	g.addEdge(2, 5);
	g.addEdge(3, 5);
	g.addEdge(4, 5);
	g.addEdge(1, 3);
	g.addEdge(2, 4);
	
	CiklInGraph cc = new CiklInGraph(g);
	cc.getEilerCikl();
    }

}