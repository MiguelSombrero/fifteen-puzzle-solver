
package fifteenpuzzlesolver.utils;


/**
 *
 * @author miika
 */
public class TestUtils {
    public ArrayList<int[]> boardList() {
        ArrayList<int[]> boards = new ArrayList<>();
        
        // solvable boards
        int[] b0 = {
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            13, 14, 15, 0
        };
        int[] b2 = {
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 0,
            13, 14, 15, 12
        };
        int[] b3 = {
            1, 0, 2, 3,
            4, 6, 5, 7,
            8, 9, 10, 11,
            12, 13, 14, 15
        };
        int[] b4 = {
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            0, 13, 14, 15
        };
        int[] b5 = {
            1, 4, 2, 3,
            5, 6, 7, 8,
            9, 10, 11, 12,
            0, 13, 14, 15
        };
        int[] b6 = {
            1, 2, 3, 4,
            5, 6, 0, 8,
            9, 10, 11, 12,
            13, 7, 14, 15
        };
        
        // un-solvable boards
        
        int[] b1 = {
            15, 2, 1, 12,
            8, 5, 6, 11,
            4, 9, 10, 7,
            3, 14, 13, 0
        };
        int[] b7 = {
            1, 4, 2, 3,
            5, 6, 7, 8,
            9, 10, 11, 12,
            0, 14, 13, 15
        };
        int[] b8 = {
            1, 2, 3, 4,
            5, 6, 0, 8,
            9, 10, 11, 12,
            7, 13, 14, 15
        };
        int[] b9 = {
            1, 3, 2, 4,
            5, 6, 7, 8,
            9, 10, 11, 0,
            13, 14, 15, 12
        };
        int[] b10 = {
            15, 14, 13, 12,
            11, 10, 9, 8,
            7, 6, 5, 4,
            3, 2, 1, 0
        };
        
        // Illegal board
        int[] b11 = {
            15, 14, 13, 12,
            11, 10, 9, 8,
            7, 6, 5, 4,
            3, 2, 1, 5
        };
        
        // board for testing column collisions
        int[] b12 = {
            9, 2, 15, 4,
            5, 14, 7, 8,
            1, 10, 11, 12,
            13, 6, 3, 0
        };
        
        boards.add(b0);
        boards.add(b1);
        boards.add(b2);
        boards.add(b3);
        boards.add(b4);
        boards.add(b5);
        boards.add(b6);
        boards.add(b7);
        boards.add(b8);
        boards.add(b9);
        boards.add(b10);
        boards.add(b11);
        boards.add(b12);
        
        return boards;
    }
}
