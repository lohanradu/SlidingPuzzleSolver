package slidingpuzzle;

public class SlidingSolverDriver {

	public static void main(String[] args) {
		String puzzle = "876543210";
		if(TileBoard.isSolvable(new TileBoard(puzzle, "","" ))){
		SlidingSolution solution = new SlidingSolver(puzzle).solvePuzzleBFS();
		if (solution == SlidingSolution.NO_SOLUTION)
			System.out.println("No possible solution");
		else
			System.out.println(solution.toString());
	}
		else
		System.out.println("Invalid Board");

	}
	
}
