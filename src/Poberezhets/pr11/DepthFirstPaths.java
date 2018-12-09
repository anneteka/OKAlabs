package meize;

 /** DFS realization
 * Ви потрапили в лабіринт. Десь в лабіринті знаходиться чарівна лампа, що дозволить Вам повернутися додому. Іншого виходу з лабіринту, крім як знайти лампу, не існує.

Ви з'євляєтеся в лабіринті завжди в одній і тій же точці, лабіринт задається з файлу, чарівна лампа з'являється випадково.

Ваша задача знайти шлях до лампи використовуючи метод DFS і BFS, як результат вивести шлях, довжину шляху, та кількість відвіданих вузлів.

Як ви зрозуміли лабіринт задається у вигляді графа. При реалізації DFS і BFS вузли додаються в чергу або стек в порядку зростання їх нумерів.

За бажанням зробити графічний інтерфейс.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class DepthFirstPaths {

	private boolean[] marked;    
    private int[] edgeTo;        
    private final int s;     //шукана точка (лампа)    
    
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.getV()];
        marked = new boolean[G.getV()];
        dfs(G, s);
    }
    
    /**
     * пошук в глибину
     * @param G - граф
     * @param v - dfs з вершини v
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }
    
    /**
     * Чи присутній шлях з v в s, що задана конструктором
     * @param v - вершина до чкої шукаємо шлях
     * @return true якщо є шлях, false якщо немає
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }
    /**
     * повертає шлях між s та v; null якщо шляху немає
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
    
    
    public static void main(String[] args) throws FileNotFoundException {
    	Scanner sc=new Scanner(new File("src/meize/first.txt"));
    	int lamp=sc.nextInt();
    	int v=sc.nextInt();
    	int edge=sc.nextInt();
    	Graph gr=new Graph(v);
    	
    	for(int i=0; i<edge;i++) {
    		int e=sc.nextInt();
    		int e1=sc.nextInt();
    		gr.addEdge(e, e1);
    	}
    	DepthFirstPaths dfs=new DepthFirstPaths(gr, lamp);
    	if(dfs.hasPathTo(0)) {
    		Stack path = (Stack) dfs.pathTo(0);
    	for(int weg:dfs.pathTo(0)) 
    		System.out.print(weg+" - ");
    	System.out.println(" DFS Path");
    	System.out.println("Amount of veges:"+ path.size());
    	System.out.println("Path`s lenght:"+ (path.size()-1));
    	}else {
    		System.err.println("No path found!");
    	}
    	
    }

    
    
}