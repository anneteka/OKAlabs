import java.util.ArrayList;
import java.util.List;

public class Board {
	
	private int[][] blks;
	
	
	
	private int manhDist;

	// hamming distance, for cache
	private int hammDist;

	// zero block x coordinate
	private int zx;

	// zero block y coordinate
	private int zy;

	
	public Board(int[][] blocks) {
		int n = blocks.length;
		blks = new int[n][n];
		manhDist = 0;
		hammDist = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				blks[i][j] = blocks[i][j];
				if (blks[i][j] == 0) {
					zx = i;
					zy = j;
				} else {
					int px = (blks[i][j] - 1) / n;
					int py = (blks[i][j] - 1) % n;
					manhDist += Math.abs(i - px) + Math.abs(j - py);

					int pos = i * n + j;
					if ((pos + 1) != blks[i][j]) {
						hammDist++;
					}
				}
			}
		}
	}

	private Board(Board parent, int manhDistChange) {
		int n = parent.blks.length;
		blks = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				blks[i][j] = parent.blks[i][j];
				if (blks[i][j] == 0) {
					zx = i;
					zy = j;
				} else {
					int pos = i * n + j;
					if ((pos + 1) != blks[i][j]) {
						hammDist++;
					}
				}
			}
		}
		manhDist = parent.manhattan() + manhDistChange;
	}

	public int dimension() {
		return blks.length;
	}

	public int hamming() {
		return hammDist;
	}

	public int manhattan() {
		return manhDist;
	}

	public boolean isGoal() {
		return manhattan() == 0;
	}

	public Board twin() {
		int n = dimension();
		int[][] newBlocks = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				newBlocks[i][j] = blks[i][j];
			}
		}

		int t;
		if (newBlocks[0][0] == 0 || newBlocks[0][1] == 0) {
			t = newBlocks[1][0];
			newBlocks[1][0] = newBlocks[1][1];
			newBlocks[1][1] = t;
		} else {
			t = newBlocks[0][0];
			newBlocks[0][0] = newBlocks[0][1];
			newBlocks[0][1] = t;
		}

		return new Board(newBlocks);
	}

	
	@Override
	public boolean equals(Object y) {
		if (!(y instanceof Board)) {
			return false;
		}
		Board that = (Board) y;
		if (zx != that.zx || zy != that.zy || manhDist != that.manhDist || hammDist != that.hammDist) {
			return false;
		}

		int n = dimension();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (blks[i][j] != that.blks[i][j]) {
					return false;
				}
			}
		}

		return true;
	}

	public Iterable<Board> neighbors() {
		List<Board> neis = new ArrayList<Board>();

		int n = dimension();
		if (zx != 0) {
			int changed = 0;
			int pos = blks[zx - 1][zy] - 1;
			int x = pos / n;
			int y = pos % n;
			changed += -(Math.abs(zx - 1 - x) + Math.abs(zy - y));
			blks[zx][zy] = blks[zx - 1][zy];
			blks[zx - 1][zy] = 0;
			changed += Math.abs(zx - x) + Math.abs(zy - y);
			neis.add(new Board(this, changed));
			blks[zx - 1][zy] = blks[zx][zy];
			blks[zx][zy] = 0;
		}
		if (zx != n - 1) {
			int changed = 0;
			int pos = blks[zx + 1][zy] - 1;
			int x = pos / n;
			int y = pos % n;
			changed += -(Math.abs(zx + 1 - x) + Math.abs(zy - y));
			blks[zx][zy] = blks[zx + 1][zy];
			blks[zx + 1][zy] = 0;
			changed += Math.abs(zx - x) + Math.abs(zy - y);
			neis.add(new Board(this, changed));
			blks[zx + 1][zy] = blks[zx][zy];
			blks[zx][zy] = 0;
		}
		if (zy != 0) {
			int changed = 0;
			int pos = blks[zx][zy - 1] - 1;
			int x = pos / n;
			int y = pos % n;
			changed += -(Math.abs(zx - x) + Math.abs(zy - 1 - y));
			blks[zx][zy] = blks[zx][zy - 1];
			blks[zx][zy - 1] = 0;
			changed += Math.abs(zx - x) + Math.abs(zy - y);
			neis.add(new Board(this, changed));
			blks[zx][zy - 1] = blks[zx][zy];
			blks[zx][zy] = 0;
		}
		if (zy != n - 1) {
			int changed = 0;
			int pos = blks[zx][zy + 1] - 1;
			int x = pos / n;
			int y = pos % n;
			changed += -(Math.abs(zx - x) + Math.abs(zy + 1 - y));
			blks[zx][zy] = blks[zx][zy + 1];
			blks[zx][zy + 1] = 0;
			changed += Math.abs(zx - x) + Math.abs(zy - y);
			neis.add(new Board(this, changed));
			blks[zx][zy + 1] = blks[zx][zy];
			blks[zx][zy] = 0;
		}

		return neis;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(dimension());
		sb.append("\n");
		int n = dimension();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(String.format("%2d ", blks[i][j]));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}