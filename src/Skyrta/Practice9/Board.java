
import java.util.ArrayList;

/**
 * 
 * @author Maria Skyrta. Imolementation of Board API.
 *
 */
public class Board implements Comparable<Board> {

	private int[][] blocks;
	Board previous;
	private int man;
	private int steps;

	public Board(int[][] block, Board previous, int steps) {
		blocks = new int[block.length][block.length];
		for (int i = 0; i < blocks.length; i++) {
			for (int u = 0; u < blocks.length; u++) {
				blocks[i][u] = block[i][u];
			}
		}
		this.steps = steps;
		this.previous = previous;
		man = manhattan();
	}

	public int dimension() {
		return blocks.length;
	}

	public int hamming() {
		int hamm = 0;
		int meaning = 0;
		for (int i = 0; i < blocks.length; i++) {
			for (int u = 0; u < blocks.length; u++) {
				if (meaning + 1 != blocks[i][u] && blocks[i][u] != 0) {
					hamm++;
				}
				meaning++;
			}
		}

		return hamm + this.steps;
	}

	public int manhattan() {
		int places = 0;
		int block;

		for (int i = 0; i < blocks.length; i++) {
			for (int u = 0; u < blocks.length; u++) {

				if (blocks[i][u] != i * dimension() + u + 1 && blocks[i][u] != 0) {
					block = blocks[i][u];
					int n = (block - 1) % blocks.length;
					int p = (block - 1) / blocks.length;
					places += Math.abs(i - p) + Math.abs(u - n);
				}

			}
		}
		places += steps;
		return places;
	}

	public boolean isGoal() {
		for (int i = 0; i < blocks.length; i++) {
			for (int u = 0; u < blocks.length && (i + 1) * (u + 1) < dimension() * dimension(); u++) {
				if (blocks[i][u] != i * dimension() + u + 1) {
					return false;
				}
			}
		}
		return true;

	}

	public boolean equals(Board y) {
		for (int i = 0; i < dimension(); i++) {
			for (int u = 0; u < dimension(); u++) {
				if (blocks[i][u] != y.array()[i][u]) {
					return false;
				}
			}
		}
		return true;
	}

	public int[] getTheEmpty(int[][] blocks) {

		for (int n = 0; n < blocks.length; n++) {
			for (int t = 0; t < blocks.length; t++) {
				if (blocks[n][t] == 0) {
					return new int[] { n, t };
				}
			}
		}
		return null;
	}

	public int getTheEmptyRev(int[][] blocks) {

		for (int n = blocks.length - 1; n >= 0; n--) {
			for (int t = blocks.length - 1; t >= 0; t--) {
				if (blocks[n][t] == 0) {
					return blocks.length - n;
				}
			}
		}
		return -1;
	}

	public int[][] array() {
		return blocks;
	}

	public Iterable<Board> neighbors() {
		ArrayList<Board> br = new ArrayList<>();
		int n = 0;
		int t = 0;
		int temp;
		int[] m = getTheEmpty(blocks);
		n = m[0];
		t = m[1];

		if (n < blocks.length - 1) {
			temp = blocks[n + 1][t];
			blocks[n + 1][t] = 0;
			blocks[n][t] = temp;
			Board toAdd = new Board(blocks, this, steps + 1);

			if (this.previous == null || !this.previous.equals(toAdd)) {
				br.add(toAdd);
			}

			blocks[n + 1][t] = temp;
			blocks[n][t] = 0;
		}
		if (n > 0) {

			temp = blocks[n - 1][t];
			blocks[n - 1][t] = 0;
			blocks[n][t] = temp;
			Board toAdd = new Board(blocks, this, steps + 1);
			if (this.previous == null || !this.previous.equals(toAdd)) {
				br.add(toAdd);
			}
			blocks[n - 1][t] = temp;
			blocks[n][t] = 0;
		}
		if (t < blocks.length - 1) {
			temp = blocks[n][t + 1];
			blocks[n][t + 1] = 0;
			blocks[n][t] = temp;
			Board toAdd = new Board(blocks, this, steps + 1);
			if (this.previous == null || !this.previous.equals(toAdd)) {
				br.add(toAdd);
			}
			blocks[n][t + 1] = temp;
			blocks[n][t] = 0;
		}
		if (t > 0) {

			temp = blocks[n][t - 1];
			blocks[n][t - 1] = 0;
			blocks[n][t] = temp;
			Board toAdd = new Board(blocks, this, steps + 1);
			if (this.previous == null || !this.previous.equals(toAdd)) {
				br.add(toAdd);
			}

			blocks[n][t - 1] = temp;
			blocks[n][t] = 0;
		}

		return br;

	}

	public String toString() {

		String answer = new String();
		for (int i = 0; i < blocks.length; i++) {
			for (int u = 0; u < blocks.length; u++) {
				answer += blocks[i][u] + " ";
			}
			answer += "\n";
		}
		return answer;
	}

	@Override
	public int compareTo(Board o) {
		if (this.manhattan() >= o.manhattan()) {
			return 1;
		} else if (this.manhattan() == o.manhattan()) {
			return 0;
		}
		return -1;
	}

}