import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Proof of concept of Deck.
 *
 * @author Sophia Rakowsky
 *
 */
public class POConcept {

    /**
     * representation of {@code this}.
     */
    private Sequence<int[]> cards;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.cards = new Sequence1L<int[]>();
    }

    /**
     * No-argument constructor.
     */
    public POConcept() {
        this.createNewRep();
    }

    /**
     * Adds the card ({@code val}, {@code suit}, {@code color}) to the
     * position {@code pos} of {@code this}.
     *
     * @param val
     *           the value to be added
     * @param suit
     *            the associated suit to be added
     * @param pos
     *             the position at which the entry is added
     * @clears {@code card},
     * @updates this
     * @requires <pre>
     * pos >= 0
     * pos <= |#this|
     * </pre>
     * @ensures this = #this[0, pos) * <card> * #this[pos, |#this|)
     */
    public void addCard(int pos, int val, int suit) {
        final int ft = 14;
        final int f = 5;
        assert val > 0 : "Violation of: val is greater than 0";
        assert val < ft : "Violation of: val is less than 14";
        assert suit > 0 : "Violation of: suit is greater than 0";
        assert suit < f : "Violation of: val is less than 4";
        int color = 1;
        if ((suit == 0) || (suit == 2)) {
            color = 2;
        }
        int[] a = new int[] { val, suit, color};
        this.cards.add(pos - 1, a);
    }

    /**
     * Removes and returns the entry at {@code pos} of {@code this}.
     *
     * @param pos
     *             the position from which the card is removed
     * @return the card removed
     * @updates this
     * @requires |this| >= pos
     * pos >= 0
     * @ensures #this = this[0, pos - 1) * <removeCard> * this[pos - 1, |this|)
     */
    int[] removeCard(int pos) {
        return this.cards.remove(pos - 1);
    }

    /**
     * Reports length of {@code this}.
     *
     * @return the length of {@code this}
     * @ensures length = |this|
     */
    int length() {
        return this.cards.length();
    }

    /**
     * returns the value of entry at position {@code pos} of {@code this}.
     *
     * @param pos
     *             the position at which the entry to be returned is found
     * @return the value of entry at {@code pos} of {@code this}
     * @requires |this| > pos
     * pos >= 0
     * @ensures this = #this
     */
    int seeV(int pos) {
        return this.cards.entry(pos - 1)[0];
    }

    /**
     * returns the suit of entry at position {@code pos} of {@code this}.
     *
     * @param pos
     *             the position at which the entry to be returned is found
     * @return the suit of entry at {@code pos} of {@code this}
     * @requires |this| > pos
     * pos >= 0
     * @ensures this = #this
     */
    int seeS(int pos) {
        return this.cards.entry(pos - 1)[1];
    }

    /**
     * prints the deck as a readable string of numbers.
     * @return String of cards with v, s, c
     * @ensures #this = this
     */
    public final String prettyPrint() {
        final int three = 3;
        final int eleven = 11;
        final int twelve = 12;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < this.length() + 1; i++) {
            int num = this.seeV(i);
            if (num == 1) {
                sb.append("A");
            } else if (num < eleven) {
                sb.append(num);
            } else if (num == eleven) {
                sb.append("J");
            } else if (num == twelve) {
                sb.append("Q");
            } else {
                sb.append("K");
            }
            sb.append(" ");
            num = this.seeS(i);
            if (num == 1) {
                sb.append("SPADES");
            } else if (num == 2) {
                sb.append("HEARTS");
            } else if (num == three) {
                sb.append("CLUBS");
            } else {
                sb.append("DIAMONDS");
            }
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length() - 1);
        return sb.toString();
    }

/**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        final int fourteen = 14;
        final int five = 5;
        SimpleWriter out = new SimpleWriter1L();
        POConcept d = new POConcept();
        int i = 1;
        for (int v = 1; v < fourteen; v++) {
            for (int s = 1; s < five; s++) {
                d.addCard(i, v, s);
                i++;
            }
        }
        out.print(d.prettyPrint());
        out.close();
    }

}
