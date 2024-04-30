
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Customized JUnit test fixture for {@code DeckOnSequence}.
 */
public class DeckOnSequenceTest {

    /**
     * final variable 3.
     */
    private final int th = 3;
    /**
     * final variable 10.
     */
    private final int ten = 10;
    /**
     * final variable 13.
     */
    private final int thirteen = 13;

    /**
     * tests length of empty deck.
     */
    @Test
    public void testLengthEmpty() {
        Deck<int[]> d = new DeckOnSequence<int[]>();
        assertEquals(0, d.length());
    }

    /**
     * tests length of deck with one card.
     */
    @Test
    public void testLengthOneSeesOne() {
        Deck<int[]> d = new DeckOnSequence<int[]>();
        d.addCard(1, 1, 1);
        assertEquals(1, d.length());
        assertEquals(1, d.seeV(1));
        assertEquals(1, d.seeS(1));
        assertEquals(1, d.seeC(1));
    }

    /**
     * tests add with int array.
     */
    @Test
    public void testLengthOneSeesOneAddIntArray() {
        int[] a = new int[]{1, 1, 1};
        Deck<int[]> d = new DeckOnSequence<int[]>();
        d.addCard(1, a);
        int[] b = d.seeCard(1);
        assertEquals(a, b);
        assertEquals(1, d.length());
        assertEquals(1, d.seeV(1));
        assertEquals(1, d.seeS(1));
        assertEquals(1, d.seeC(1));
    }

    /**
     * tests add to the middle of deck.
     */
    @Test
    public void testLengthThreeAddToMiddle() {
        Deck<int[]> d = new DeckOnSequence<int[]>();
        d.addCard(1, 1, 1);
        d.addCard(2, thirteen, th);
        d.addCard(2, ten, 2);
        assertEquals(1, d.position(1, 1));
        assertEquals(2, d.position(ten, 2));
        assertEquals(th, d.position(thirteen, th));
        assertEquals(th, d.length());
        assertEquals(ten, d.seeV(2));
        assertEquals(2, d.seeS(2));
        assertEquals(2, d.seeC(2));
    }

    /**
     * tests remove using position.
     */
    @Test
    public void testLengthThreeAddToMiddleRemoveOne() {
        Deck<int[]> d = new DeckOnSequence<int[]>();
        d.addCard(1, 1, 1);
        d.addCard(2, thirteen, th);
        d.addCard(2, ten, 2);
        assertTrue(d.hasCard(1, 1));
        d.removeCard(2);
        assertEquals(2, d.length());
        assertEquals(thirteen, d.seeV(2));
        assertEquals(th, d.seeS(2));
        assertEquals(1, d.seeC(2));
    }

    /**
     * tests remove's return value.
     */
    @Test
    public void testAddOneRemoveOneReturnVals() {
        Deck<int[]> d = new DeckOnSequence<int[]>();
        int v = 1;
        int[] a = new int[]{v, v, v};
        d.addCard(1, 1, 1);
        int[] b = d.removeCard(1);
        assertEquals(a[0], b[0]);
        assertEquals(a[1], b[1]);
        assertEquals(a[2], b[2]);
    }

    /**
     * tests remove with params.
     */
    @Test
    public void testRemoveOneFromParams() {
        Deck<int[]> d = new DeckOnSequence<int[]>();
        d.addCard(1, 1, 1);
        int b = d.removeCard(1, 1);
        assertEquals(1, b);
        assertEquals(0, d.length());
    }

}
