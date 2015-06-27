package slidingpuzzle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import dsa.Deque;
import dsa.Deque2;
import dsa.DequeEmptyException;

@SuppressWarnings("all")
public class SlidingSolver {

	// Maximum search depth for BFS
	private static final int MAX_BFS_DISTANCE = 10000;

	private TileBoard Board;

	private Deque<TileBoard> gameStates;

	/* You may add instance and class variables and methods as you see fit */
	/*
	 * Constructs a SlidingSolver with the given input board.
	 */
	public SlidingSolver(String initialBoard) {

		Board = new TileBoard(initialBoard, "", "");

	}

	/*
	 * Solves the puzzle by performing a breadth-first search over the puzzle
	 * space. Returns SlidingSolution.NO_SOLUTION if the maximum BFS search
	 * depth is reached.
	 */
	public SlidingSolution solvePuzzleBFS() {
		gameStates = new Deque<TileBoard>();
		gameStates.addFirst(Board);
		boolean isSolution = false;
		int spaceSize = 0;
		List<String> previusStates = new ArrayList<String>();
		while ((!isSolution) && (spaceSize < MAX_BFS_DISTANCE)) {
			TileBoard board = null;
			try {
				board = gameStates.removeLast();
			} catch (DequeEmptyException e) {
				e.printStackTrace();
			}
			if (board.getMyBoard().compareTo(TileBoard.goalBoard) == 0) {

				isSolution = true;
				return new SlidingSolution(board.getMyMoves(), spaceSize);
			}

			List<TileBoard> possibleMoves = TileBoard.getNextBoards(board);
			int bestSolution = 0;
			int minHeuristic = evaluateHeuristic(possibleMoves.get(0));
			spaceSize++;
			// for (int i = 0; i < possibleMoves.size(); i++) {
			// System.out.println(possibleMoves.get(i).getMyBoard() + " "
			// + evaluateHeuristic(possibleMoves.get(i)));
			// }
			for (int i = 0; i < possibleMoves.size(); i++) {
				if (evaluateHeuristic(possibleMoves.get(i)) < minHeuristic) {

					bestSolution = i;
					minHeuristic = evaluateHeuristic(possibleMoves.get(i));
				}
			}
			// System.out.println("best: "
			// + possibleMoves.get(bestSolution).getMyBoard());
			if (possibleMoves.get(bestSolution).getMyBoard()
					.compareTo(TileBoard.goalBoard) == 0) {

				isSolution = true;
				return new SlidingSolution(possibleMoves.get(bestSolution)
						.getMyMoves(), spaceSize);
			}

			for (int i = 0; i < possibleMoves.size(); i++) {

				if (evaluateHeuristic(possibleMoves.get(i)) == minHeuristic) {
					if (!previusStates.contains(possibleMoves.get(i)
							.getMyBoard())) {
						gameStates.addFirst(possibleMoves.get(i));
						previusStates.add(possibleMoves.get(i).getMyBoard());
					}
				}
			}
		}

		return SlidingSolution.NO_SOLUTION;
	}

	/*
	 * Evaluates the given board.
	 */
	private int evaluateHeuristic(TileBoard b) {
		return TileBoard.getNumMoves(b) + TileBoard.calcManhattanDistance(b);
	}

	class TileBoardComparator implements Comparator<TileBoard> {
		public int compare(TileBoard a, TileBoard b) {
			// Integer A = TileBoard.calcManhattanDistance(a);
			// Integer B = TileBoard.calcManhattanDistance(b);
			Integer A = evaluateHeuristic(a);
			Integer B = evaluateHeuristic(b);

			return B.compareTo(A);

		}
	}

}