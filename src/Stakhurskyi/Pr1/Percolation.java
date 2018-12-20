package Stakhurskyi.Pr1;

public class Percolation {

    private WeightedQuickUnionUF conGrid;	//Keeps track of connectivity
    private boolean[] blockedGrid;			//Keeps track of whether a site is blocked or not
    int size, head, foot;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {

        size = N;
        WeightedQuickUnionUF conGrid = new WeightedQuickUnionUF(N*N + 2);
        boolean[] blockedGrid = new boolean[N*N];

        //Sets all the sites to be blocked
        for(int i=0; i<blockedGrid.length; i++)
            blockedGrid[i] = false;

        //Sets the values for the head and foot nodes
        head = N*N + 1;
        foot = N*N + 2;

        //Connects the head node to all the "top" nodes (0 - N-1)
        for(int i=0; i<N; i++)
            conGrid.union(head, i);

        //Connects the foot node to all the "bottom" nodes ((N^2 - N) - N^2-1)
        for(int i=0; i<N; i++)
            conGrid.union(foot, (N*N)-N+i);
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {

        if((i < 1 || i > size) || (j < 1 || j > size)) {
            throw new IndexOutOfBoundsException();
        }

        int connectingNode = size*(i-1) + j-1;

        if(blockedGrid[connectingNode]==false) {	// ensure not already open

            blockedGrid[connectingNode] = true;

            if(connectingNode % size != 0) 	//node not on the left
                conGrid.union(connectingNode, connectingNode - 1);
            if(connectingNode >= size) 		//node not on the top
                conGrid.union(connectingNode, connectingNode - size);
            if(connectingNode % size != size-1) //node not on the right
                conGrid.union(connectingNode, connectingNode + 1);
            if(connectingNode <= size*(size-1)) //node not on the bottom
                conGrid.union(connectingNode, connectingNode + size);
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {

        if((i < 1 || i > size) || (j < 1 || j > size)) {
            throw new IndexOutOfBoundsException();
        }

        return blockedGrid[(i-1)*size + j-1];

    }

    // is site (row i, column j) full (connects to the head node)
    public boolean isFull(int i, int j) {

        if((i < 1 || i > size) || (j < 1 || j > size)) {
            throw new IndexOutOfBoundsException();
        }

        return conGrid.connected((i-1)*size + j-1, head);

    }

    // does the system percolate?
    public boolean percolates() {

        return conGrid.connected(head, foot);

    }
    public static void main(String args[]){

    }
}