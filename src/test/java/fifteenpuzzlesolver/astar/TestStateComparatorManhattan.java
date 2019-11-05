
package fifteenpuzzlesolver.astar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miika
 */
public class TestStateComparatorManhattan {
    
    private StateComparatorManhattan comparator;
    
    public TestStateComparatorManhattan() {
        this.comparator = new StateComparatorManhattan();
    }
    
    @Test
    public void countDistance() {
        assertEquals(2, comparator.countDistance(9, 12));
        assertEquals(3, comparator.countDistance(0, 12));
        assertEquals(6, comparator.countDistance(3, 12));
        assertEquals(2, comparator.countDistance(9, 12));
        assertEquals(0, comparator.countDistance(6, 6));
        assertEquals(4, comparator.countDistance(3, 9));
        assertEquals(4, comparator.countDistance(4, 11));
        assertEquals(3, comparator.countDistance(8, 11));
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

}
