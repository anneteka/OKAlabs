import java.util.EmptyStackException;
import java.util.Stack;

public class GrahamStack extends Stack {

    /**
     * Get the element on the top of the stack, like Peek().
     *
     * @return the top element of the stack.
     */
    public Vertex top() {
        int length = size();
        if (length == 0) {
            throw new EmptyStackException();
        }
        return (Vertex) elementAt(length - 1);
    }

    /**
     * Get the second to top element of the stack.
     *
     * @return the second to top element of the stack.
     */
    public Vertex nextToTop() {
        int len = size();
        if (len == 0) {
            throw new EmptyStackException();
        }
        else {
            return (Vertex) elementAt(len - 2);
        }
    }

    @Override
    /**
     * Pop an element from the stack.
     * @return the top element on the stack
     * @postcondition the top element is removed.
     */
    public Vertex pop() {
        return (Vertex) super.pop();
    }
}