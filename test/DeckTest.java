import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Customized JUnit test fixture for {@code Deck}.
 */
public class DeckTest {

    /**
     * final variable 3.
     */
    private final int th = 3;
    /**
     * final variable 4.
     */
    private final int four = 4;
    /**
     * final variable 5.
     */
    private final int five = 5;
    /**
     * final variable 6.
     */
    private final int six = 6;
    /**
     * final variable 7.
     */
    private final int sev = 7;
    /**
     * final variable 8.
     */
    private final int e = 8;
    /**
     * final variable 9.
     */
    private final int nine = 9;
    /**
     * final variable 10.
     */
    private final int ten = 10;
    /**
     * final variable 13.
     */
    private final int thirteen = 13;


    /**
     * tests if deck can move a card from a higher position to a lower one.
     */
    @Test
    public void testMoveCard() {
        Deck<int[]> a = new DeckOnSequence<int[]>();
        a.addCard(1, 1, 1);
        a.addCard(2, thirteen, th);
        a.addCard(th, ten, 2);
        Deck<int[]> b = new DeckOnSequence<int[]>();
        b.addCard(1, thirteen, th);
        b.addCard(2, 1, 1);
        b.addCard(th, ten, 2);
        b.moveCard(1, 2);
        int[] ac = a.seeCard(1);
        int[] bc = b.seeCard(1);
        assertEquals(ac[0], bc[0]);
        assertEquals(ac[1], bc[1]);
        assertEquals(ac[2], bc[2]);
    }
    /**
     * tests if deck can move a card from a lower position to a higher one.
     */
    @Test
    public void testMoveCard2() {
        Deck<int[]> a = new DeckOnSequence<int[]>();
        a.addCard(1, 1, 1);
        a.addCard(2, thirteen, th);
        a.addCard(th, ten, 2);
        Deck<int[]> b = new DeckOnSequence<int[]>();
        b.addCard(1, thirteen, th);
        b.addCard(2, 1, 1);
        b.addCard(th, ten, 2);
        b.moveCard(2, 1);
        int[] ac = a.seeCard(1);
        int[] bc = b.seeCard(1);
        assertEquals(ac[0], bc[0]);
        assertEquals(ac[1], bc[1]);
        assertEquals(ac[2], bc[2]);
    }
    /**
     * tests if deck can be added to a deck with 1 card.
     */
    @Test
    public void testAddDeckWithCard() {
        Deck<int[]> a = new DeckOnSequence<int[]>();
        a.addCard(1, 1, 1);
        a.addCard(2, thirteen, th);
        int[] ac = a.seeCard(1);
        Deck<int[]> b = new DeckOnSequence<int[]>();
        b.addCard(1, th, 1);
        b.addDeck(a);
        int[] bc = b.seeCard(2);
        assertEquals(ac[0], bc[0]);
        assertEquals(ac[1], bc[1]);
    }
    /**
     * tests if deck can be added to an empty deck.
     */
    @Test
    public void testAddDeckNoCard() {
        Deck<int[]> a = new DeckOnSequence<int[]>();
        a.addCard(1, 1, 1);
        a.addCard(2, thirteen, th);
        int[] ac = a.seeCard(1);
        Deck<int[]> b = new DeckOnSequence<int[]>();
        b.addDeck(a);
        int[] bc = b.seeCard(1);
        assertEquals(ac[0], bc[0]);
        assertEquals(ac[1], bc[1]);
    }
    /**
     * tests if deck can deal 1 card to 1 hand.
     */
    @Test
    public void dealTest1Card1Deck() {
        Deck<int[]> a = new DeckOnSequence<int[]>();
        a.addCard(1, 1, 1);
        a.addCard(2, thirteen, th);
        int[] ac = a.seeCard(2);
        Deck<int[]> h1 = new DeckOnSequence<int[]>();
        @SuppressWarnings("unchecked")
        Deck<int[]>[] hl = new Deck[]{h1};
        a.deal(1, hl);
        int[] h1c = a.seeCard(1);
        assertEquals(ac[0], h1c[0]);
        assertEquals(1, h1.seeV(1));
        assertEquals(1, a.length());
    }
    /**
     * tests if deck can deal 1 card to 3 different hands.
     */
    @Test
    public void dealTest1Card3Deck() {
        Deck<int[]> a = new DeckOnSequence<int[]>();
        a.addCard(1, 1, 1);
        a.addCard(2, thirteen, th);
        a.addCard(th, thirteen, th);
        int[] ac1 = a.seeCard(1);
        int[] ac2 = a.seeCard(2);
        int[] ac3 = a.seeCard(th);
        Deck<int[]> h1 = new DeckOnSequence<int[]>();
        Deck<int[]> h2 = new DeckOnSequence<int[]>();
        Deck<int[]> h3 = new DeckOnSequence<int[]>();
        @SuppressWarnings("unchecked")
        Deck<int[]>[] hl = new Deck[]{h1, h2, h3};
        a.deal(1, hl);
        int[] h1c = h1.seeCard(1);
        int[] h2c = h2.seeCard(1);
        int[] h3c = h3.seeCard(1);
        assertEquals(ac1[0], h1c[0]);
        assertEquals(ac2[0], h2c[0]);
        assertEquals(ac3[0], h3c[0]);
    }

    /**
     * tests if deck can deal 3 cards to 3 different hands.
     */
    @Test
    public void dealTest3Card3Deck() {
        Deck<int[]> a = new DeckOnSequence<int[]>();
        a.addCard(1, 1, 1);
        a.addCard(2, 2, 2);
        a.addCard(th, th, th);
        a.addCard(four, four, 1);
        a.addCard(five, five, 2);
        a.addCard(six, six, th);
        a.addCard(sev, sev, 1);
        a.addCard(e, e, 2);
        a.addCard(nine, nine, th);
        a.addCard(nine, nine, th);
        int[] ac1 = a.seeCard(1);
        int[] ac2 = a.seeCard(2);
        int[] ac3 = a.seeCard(th);
        int[] ac4 = a.seeCard(four);
        int[] ac5 = a.seeCard(five);
        int[] ac6 = a.seeCard(six);
        int[] ac7 = a.seeCard(sev);
        int[] ac8 = a.seeCard(e);
        int[] ac9 = a.seeCard(nine);
        Deck<int[]> h1 = new DeckOnSequence<int[]>();
        Deck<int[]> h2 = new DeckOnSequence<int[]>();
        Deck<int[]> h3 = new DeckOnSequence<int[]>();
        @SuppressWarnings("unchecked")
        Deck<int[]>[] hl = new Deck[]{h1, h2, h3};
        a.deal(th, hl);
        int[] h1c = h1.seeCard(1);
        int[] h2c = h2.seeCard(1);
        int[] h3c = h3.seeCard(1);
        int[] h1c2 = h1.seeCard(2);
        int[] h2c2 = h2.seeCard(2);
        int[] h3c2 = h3.seeCard(2);
        int[] h1c3 = h1.seeCard(th);
        int[] h2c3 = h2.seeCard(th);
        int[] h3c3 = h3.seeCard(th);
        assertEquals(ac1[0], h1c[0]);
        assertEquals(ac2[0], h2c[0]);
        assertEquals(ac3[0], h3c[0]);
        assertEquals(ac4[0], h1c2[0]);
        assertEquals(ac5[0], h2c2[0]);
        assertEquals(ac6[0], h3c2[0]);
        assertEquals(ac7[0], h1c3[0]);
        assertEquals(ac8[0], h2c3[0]);
        assertEquals(ac9[0], h3c3[0]);
        assertEquals(1, a.length());
        assertEquals(nine, a.seeV(1));
    }
    /**
     * tests if shuffle has potential to both move a card
     * and leave it the same and keeps the length the same.
     */
    @Test
    public void testShuffle() {
        Deck<int[]> a = new DeckOnSequence<int[]>();
        a.addCard(1, 1, 1);
        a.addCard(2, 2, 2);
        a.addCard(th, th, th);
        a.addCard(four, four, 1);
        a.addCard(five, five, 2);
        a.addCard(six, six, 2);
        a.addCard(sev, sev, 1);
        a.addCard(e, e, 2);
        a.addCard(nine, nine, 2);
        //int[] ac1 = a.seeCard(1);
        a.shuffle();
        assertEquals(nine, a.length());
        //assertNotEquals(ac1[0], a.seeV(1));
        //Above line was tested multiple times to check
        //if it was possible to have both pass and fail
        //of test, which is the desired result.
    }

    /**
     * tests if first five are cut from original deck and returned.
     */
    @Test
    public void testCut() {
        Deck<int[]> a = new DeckOnSequence<int[]>();
        a.addCard(1, 1, 1);
        a.addCard(2, 2, 2);
        a.addCard(th, th, 2);
        a.addCard(four, four, 1);
        a.addCard(five, five, 2);
        a.addCard(six, six, 2);
        a.addCard(sev, sev, 1);
        a.addCard(e, e, 2);
        a.addCard(nine, nine, 2);
        int l = a.length();
        int[] ac1 = a.seeCard(1);
        int[] ac2 = a.seeCard(2);
        int[] ac3 = a.seeCard(th);
        int[] ac4 = a.seeCard(four);
        int[] ac5 = a.seeCard(five);
        Deck<int[]> b = a.cut(five);
        int[] bc1 = b.seeCard(1);
        int[] bc2 = b.seeCard(2);
        int[] bc3 = b.seeCard(th);
        int[] bc4 = b.seeCard(four);
        int[] bc5 = b.seeCard(five);
        assertEquals(five, b.length());
        assertEquals(ac1, bc1);
        assertEquals(ac2, bc2);
        assertEquals(ac3, bc3);
        assertEquals(ac4, bc4);
        assertEquals(ac5, bc5);
        assertEquals(l - five, a.length());
    }

    /**
     * tests if two equal decks are equal.
     */
    @Test
    public void testEquals() {
        Deck<int[]> a = new DeckOnSequence<int[]>();
        a.addCard(1, 1, 1);
        a.addCard(2, 2, 2);
        Deck<int[]> b = new DeckOnSequence<int[]>();
        b.addCard(1, 1, 1);
        b.addCard(2, 2, 2);
        assertTrue(a.equals(b));
    }

    /**
     * tests if two decks with different cards are equal.
     */
    @Test
    public void testNotEquals() {
        Deck<int[]> a = new DeckOnSequence<int[]>();
        a.addCard(1, 1, 1);
        a.addCard(2, 2, 2);
        Deck<int[]> b = new DeckOnSequence<int[]>();
        b.addCard(1, 1, 1);
        b.addCard(2, 1, 2);
        assertFalse(a.equals(b));
    }

    /**
     * tests if two decks of different length are equal.
     */
    @Test
    public void testNotEqualsLength() {
        Deck<int[]> a = new DeckOnSequence<int[]>();
        a.addCard(1, 1, 1);
        a.addCard(2, 2, 2);
        a.addCard(th, 2, 1);
        Deck<int[]> b = new DeckOnSequence<int[]>();
        b.addCard(1, 1, 1);
        b.addCard(2, 2, 2);
        assertFalse(a.equals(b));
    }

    /**
     * tests prettyPrint on one card.
     */
    @Test
    public void testPrettyPrint1() {
        Deck<int[]> a = new DeckOnSequence<int[]>();
        a.addCard(1, 1, 1);
        String s = a.prettyPrint();
        assertEquals("A SPADES ", s);
    }

    /**
     * tests prettyPrint on two cards.
     */
    @Test
    public void testPrettyPrint2() {
        Deck<int[]> a = new DeckOnSequence<int[]>();
        a.addCard(1, 1, 1);
        a.addCard(2, 2, 2);
        String s = a.prettyPrint();
        assertEquals("A SPADES, 2 HEARTS ", s);
    }
}






