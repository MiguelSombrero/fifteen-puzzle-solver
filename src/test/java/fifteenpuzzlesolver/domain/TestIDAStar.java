
package fifteenpuzzlesolver.domain;

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
    
    private FifteenPuzzle puzzle0;
    private FifteenPuzzle puzzle1;
    private FifteenPuzzle puzzle2;
    private FifteenPuzzle puzzle3;
    private FifteenPuzzle puzzle4;
    private FifteenPuzzle puzzle5;
    private FifteenPuzzle puzzle6;
    
    public TestIDAStar() {
        StateComparatorManhattan manhattan = new StateComparatorManhattan();
        StateComparatorPosition position = new StateComparatorPosition();
        StateComparatorLinearCollision linear = new StateComparatorLinearCollision();
        
        this.idaStarManhattan = new IDAStar(manhattan);
        this.idaStarPosition = new IDAStar(position);
        this.idaStarLinearCollision = new IDAStar(linear);
        
        TestUtils utils = new TestUtils();
        this.puzzle0 = new FifteenPuzzle(utils.boardList().get(0));
        this.puzzle1 = new FifteenPuzzle(utils.boardList().get(1));
        this.puzzle2 = new FifteenPuzzle(utils.boardList().get(2));
        this.puzzle3 = new FifteenPuzzle(utils.boardList().get(3));
        this.puzzle4 = new FifteenPuzzle(utils.boardList().get(4));
        this.puzzle5 = new FifteenPuzzle(utils.boardList().get(5));
        this.puzzle6 = new FifteenPuzzle(utils.boardList().get(6));
    }
    
    
}
