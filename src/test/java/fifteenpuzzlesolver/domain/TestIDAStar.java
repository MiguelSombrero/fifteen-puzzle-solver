
package fifteenpuzzlesolver.domain;

import fifteenpuzzlesolver.utils.ArrayList;
import fifteenpuzzlesolver.utils.TestUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miika
 */
public class TestIDAStar {
    
    
    // KESKEN KESKEN KESKEN
    
    private IDAStar idaStarManhattan;
    private IDAStar idaStarPosition;
    private IDAStar idaStarLinearCollision;
    
    private ArrayList<int[]> boards;
    
    private FifteenPuzzle puzzle0;
    private FifteenPuzzle puzzle1;
    private FifteenPuzzle puzzle2;
    private FifteenPuzzle puzzle3;
    private FifteenPuzzle puzzle4;
    private FifteenPuzzle puzzle5;
    private FifteenPuzzle puzzle6;
    private FifteenPuzzle puzzle12;
    
    public TestIDAStar() {
        StateComparatorManhattan manhattan = new StateComparatorManhattan();
        StateComparatorPosition position = new StateComparatorPosition();
        StateComparatorLinearCollision linear = new StateComparatorLinearCollision();
        
        this.idaStarManhattan = new IDAStar(manhattan);
        this.idaStarPosition = new IDAStar(position);
        this.idaStarLinearCollision = new IDAStar(linear);
        
        TestUtils utils = new TestUtils();
        this.boards = utils.boardList();
        this.puzzle0 = new FifteenPuzzle(utils.boardList().get(0));
        this.puzzle1 = new FifteenPuzzle(utils.boardList().get(1));
        this.puzzle2 = new FifteenPuzzle(utils.boardList().get(2));
        this.puzzle3 = new FifteenPuzzle(utils.boardList().get(3));
        this.puzzle4 = new FifteenPuzzle(utils.boardList().get(4));
        this.puzzle5 = new FifteenPuzzle(utils.boardList().get(5));
        this.puzzle6 = new FifteenPuzzle(utils.boardList().get(6));
        this.puzzle12 = new FifteenPuzzle(utils.boardList().get(12));
    }
    
    @Test
    public void costFunctionCalculatesCostRight() {
        assertEquals(0, this.idaStarLinearCollision.cost(puzzle0));
        assertEquals(40, this.idaStarLinearCollision.cost(puzzle1));
        assertEquals(1, this.idaStarLinearCollision.cost(puzzle2));
        assertEquals(25, this.idaStarLinearCollision.cost(puzzle3));
        assertEquals(3, this.idaStarLinearCollision.cost(puzzle4));
        assertEquals(11, this.idaStarLinearCollision.cost(puzzle5));
        assertEquals(5, this.idaStarLinearCollision.cost(puzzle6));
        assertEquals(36, this.idaStarLinearCollision.cost(puzzle12));
        
        assertEquals(3, this.idaStarLinearCollision.cost(new FifteenPuzzle(this.boards.get(0), 3)));
        assertEquals(47, this.idaStarLinearCollision.cost(new FifteenPuzzle(this.boards.get(1), 7)));
        assertEquals(37, this.idaStarLinearCollision.cost(new FifteenPuzzle(this.boards.get(4), 34)));
        assertEquals(37, this.idaStarLinearCollision.cost(new FifteenPuzzle(this.boards.get(12), 1)));
    }
    
}
