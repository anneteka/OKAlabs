import princeton.lib.*;
import java.util.Stack;
public class ReturnCycle {

    public static void main(String[] args) {
        String fileName = "pathOrCycle";
        Digraph g = new Digraph(new In(fileName));
        DepthFirstOrder dfp = new DepthFirstOrder(g);

        Stack<Integer> q = (Stack<Integer>) dfp.reversePost();
        for (int i = 0; i < q.size() - 1; i++) {
            DepthFirstPaths dpp = new DepthFirstPaths(g, q.get(i + 1));
            if (!g.oneWay(q.get(i+1), q.get(i))) {
                System.out.println("false");
                return;
            }
        }
        DepthFirstPaths dpp = new DepthFirstPaths(g, q.get(0));
        if (!dpp.hasPathTo(q.get(q.size() - 1))) {
            System.out.println("false");
            return;
        }
        System.out.println("True");

        for (int i : q) {
            System.out.print(i + " ");
        }
    }

}