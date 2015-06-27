package slidingpuzzle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSlidingSolver {

	@Test
	public void testSolutions() {
		String puzzle = "123456780";
		SlidingSolution solution = new SlidingSolver(puzzle).solvePuzzleBFS();
		assertEquals(solution.getMoves(), "");

		String puzzle2 = "123406758";
		SlidingSolution solution2 = new SlidingSolver(puzzle2).solvePuzzleBFS();
		assertEquals(solution2.getMoves(), "DR");
		
		String puzzle3 = "123456078";
		SlidingSolution solution3 = new SlidingSolver(puzzle3).solvePuzzleBFS();
		assertEquals(solution3.getMoves(), "RR");

		String puzzle4 = "413726580";
		SlidingSolution solution4 = new SlidingSolver(puzzle4).solvePuzzleBFS();
		assertEquals(solution4.getMoves(), "LLUURDDR");
		
		String puzzle5 = "512630478";
		SlidingSolution solution5 = new SlidingSolver(puzzle5).solvePuzzleBFS();
		assertEquals(solution5.getMoves(), "LLURRDLLDRR");
	}
}
