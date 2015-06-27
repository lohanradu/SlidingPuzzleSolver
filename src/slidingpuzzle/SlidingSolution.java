package slidingpuzzle;

public class SlidingSolution {
	public static final SlidingSolution NO_RESPONSE = new SlidingSolution("",
			-123456);
	// Represents a null solution
	public static final SlidingSolution NO_SOLUTION = new SlidingSolution("",
			-789);

	private String myMoves;
	private int myProblemSpaceSize;

	public SlidingSolution(String moves, int spaceSize) {
		myMoves = moves;
		myProblemSpaceSize = spaceSize;
	}

	public String getMoves() {
		return myMoves;
	}

	public int getProblemSpaceSize() {
		return myProblemSpaceSize;
	}

	public String toString() {
		int numberOfMovesPerLine = 0;
		String toStr = "Move the empty space in the following directions:\n\n";
		for (char move : myMoves.toCharArray()) {
			switch (move) {
			case 'U':
				toStr += "UP ";
				break;
			case 'R':
				toStr += "RIGHT ";
				break;
			case 'D':
				toStr += "DOWN ";

				break;
			case 'L':
				toStr += "LEFT ";

				break;
			}
			numberOfMovesPerLine ++;
			if (numberOfMovesPerLine % 6 == 0)
				toStr += "\n";
		}
		return toStr;
	}
}