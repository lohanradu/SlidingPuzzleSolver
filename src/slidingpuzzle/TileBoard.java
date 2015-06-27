package slidingpuzzle;

import java.util.ArrayList;
import java.util.List;

public class TileBoard {
	// String representation of the solution board
	public static final String goalBoard = "123456780";

	// String representation of a puzzle board
	private String myBoard;
	// String representation of the list of moves that generated this board
	private String myMoves;
	
	private String previousState;



	private int[][] myTileBoard;

	public TileBoard(String board, String myMoves, String previous) {
		myBoard = new String(board);
		this.myMoves = myMoves;
		this.previousState = previous;
		myTileBoard = new int[3][3];
		int position = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				myTileBoard[i][j] = myBoard.charAt(position) - '0';
				position++;
			}
		}

	}

	/*
	 * Returns a list of boards that are one move away. This list *DOES NOT*
	 * contain the previous board, as this would undo a moving we just made
	 */
	
	public TileBoard() {
		// TODO Auto-generated constructor stub
	}

	public static List<TileBoard> getNextBoards(TileBoard b) {
		List<TileBoard> nextBoards = new ArrayList<TileBoard>();
		int[][] board = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = b.myTileBoard[i][j];
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				if (board[i][j] == 0) {
					if (i > 0) {
						int[][] aux = deepCopyMatrix(board);
						int intaux = aux[i][j];
						aux[i][j] = aux[i - 1][j];
						aux[i - 1][j] = intaux;
						String nextBoard = "";
						for (int k = 0; k < 3; k++) {
							for (int l = 0; l < 3; l++) {
								nextBoard += aux[k][l];
							}
						}
						TileBoard newTileBoard = new TileBoard(nextBoard, b.getMyMoves()+"U",b.getMyBoard());
						nextBoards.add(newTileBoard);
					}
					if (i < 2) {
						int[][] aux = deepCopyMatrix(board);
						swapElements(i, j, aux);
						String nextBoard = "";
						for (int k = 0; k < 3; k++) {
							for (int l = 0; l < 3; l++) {
								nextBoard += aux[k][l];
							}
						}

						TileBoard newTileBoard = new TileBoard(nextBoard, b.getMyMoves() + "D",b.getMyBoard());
						nextBoards.add(newTileBoard);
					}
					if (j < 2) {
						int[][] aux = deepCopyMatrix(board);
						int intaux = aux[i][j];
						aux[i][j] = aux[i][j + 1];
						aux[i][j + 1] = intaux;
						String nextBoard = "";
						for (int k = 0; k < 3; k++) {
							for (int l = 0; l < 3; l++) {
								nextBoard += aux[k][l];
							}
						}
						TileBoard newTileBoard = new TileBoard(nextBoard, b.getMyMoves() +"R",b.getMyBoard());
						nextBoards.add(newTileBoard);
					}
					if (j > 0) {
						int[][] aux = deepCopyMatrix(board);
						int intaux = aux[i][j];
						aux[i][j] = aux[i][j - 1];
						aux[i][j - 1] = intaux;
						String nextBoard = "";
						for (int k = 0; k < 3; k++) {
							for (int l = 0; l < 3; l++) {
								nextBoard += aux[k][l];
							}
						}
						TileBoard newTileBoard = new TileBoard(nextBoard,b.getMyMoves() +"L",b.getMyBoard());
						nextBoards.add(newTileBoard);
					}
				}
			}

		}

		return nextBoards;
	}

	private static void swapElements(int i , int j, int[][] aux) {
		int intaux = aux[i][j];
		aux[i][j] = aux[i +1][j];
		aux[i + 1][j] = intaux;
	}

	private static int[][] deepCopyMatrix(int[][] board) {
		int aux[][] = new int[3][3];
		for (int m = 0; m < 3; m++) {
			for (int n = 0; n < 3; n++) {
				aux[m][n] = board[m][n];
			}
		}
		return aux;
	}

	/*
	 * Returns the number of moves from the initial b.oard
	 */
	public static int getNumMoves(TileBoard b) {
		return b.myMoves.length();
	}

	/*
	 * Evaluates the given board using the Manhattan distance heuristic.
	 */
	public static int calcManhattanDistance(TileBoard b) {

		int manhattanDistanceSum = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				int value = b.myTileBoard[i][j];
				if (value != 0) {
					int targetX = (value - 1) / 3;
					int targetY = (value - 1) % 3;
					int dx = i - targetX; // x-distance to expected coordinate
					int dy = j - targetY; // y-distance to expected coordinate
					manhattanDistanceSum += Math.abs(dx) + Math.abs(dy);
				}
			}
		return manhattanDistanceSum;
	}

	public static boolean isSolvable(TileBoard b){
		int permutations = 0;
		for (int i = 0; i < b.getMyBoard().length(); i++) {
		for (int j = i+1; j < b.getMyBoard().length(); j++) {
		if(b.getMyBoard().charAt(i) != '0' && b.getMyBoard().charAt(j) !='0')	{
			if(b.getMyBoard().charAt(i) < b.getMyBoard().charAt(j))
					permutations ++;
		}
		
		}
		}
		if(permutations%2 == 1)
			return false;
		return true;
	}
	public String getMyBoard() {
		return myBoard;
	}

	public String getMyMoves() {
		return myMoves;
	}

	public void setMyMoves(String myMoves) {
		this.myMoves = myMoves;
	}
	public String getPreviousState() {
		return previousState;
	}

	public void setPreviousState(String previousState) {
		this.previousState = previousState;
	}


}