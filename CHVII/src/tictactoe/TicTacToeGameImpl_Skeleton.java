package tictactoe;

import sequence.Sequence;

public class TicTacToeGameImpl_Skeleton implements TicTacToeGame {
	//Symbolics:
	protected static final int NO_MOVE = -1;
	protected static final int NO_MATCH = -1;

	private int[] movesArray;

	public TicTacToeGameImpl_Skeleton(Sequence<Integer> gridPositionSequence) {
		assert gridPositionSequence != null : "gridPositionSequence is null!";
		final int CELL_COUNT = ROW_COUNT * COLUMN_COUNT;
		assert gridPositionSequence.size() <= CELL_COUNT :
				String.format("gridPositionSequence.size() = %s > %s = CELL_COUNT!", gridPositionSequence.size(),
						CELL_COUNT);

		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	public Mark getMark(int row, int column) {
		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	//rv == TIE <==> it is neither player's turn (i.e. game is over)
	public Player getTurn() {
		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	//part of post: rv == null <==> game ended in a tie
	public Player getWinner() {
		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	public boolean isGameOver() {
		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	public void setMark(int row, int column) {
		throw new RuntimeException("NOT IMPLEMENTED!");
	}


	private String gridString() {
		StringBuilder sb = new StringBuilder();
		final String HORIZONTAL_DIVIDER = "----" + "-" + "----" + "-" + "----";
		for (int i = 0; i < ROW_COUNT; i++) {
			for (int j = 0; j < COLUMN_COUNT; j++) {
				Mark mark_ij = getMark(i, j);
				String squareString = (mark_ij == null ? "    " : " " + mark_ij + " ");
				sb.append(squareString);

				boolean isLastColumn = (j == COLUMN_COUNT - 1);
                if (!isLastColumn) {
                    sb.append("|");
                }
			}
			sb.append("\n");

			boolean isLastRow = (i == ROW_COUNT - 1);
			if (!isLastRow) {
				sb.append(HORIZONTAL_DIVIDER);
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	public String toString() {
		return gridString();
	}
}
