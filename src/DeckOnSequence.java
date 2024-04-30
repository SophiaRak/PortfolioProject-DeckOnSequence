import java.util.Iterator;
import java.util.NoSuchElementException;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code Deck} represented as a {@Sequence} with implementations of primary
 * methods.
 *
 * @param <A>
 *            int array of {@code DeckKernal} entries value, suit int[] A = [int
 *            value, int suit, int color] value range [1 - 13], 1 is Ace, 2-10,
 *            11-13 is jack, queen, king suit range [1-4], 1 is spade, 2 is
 *            heart, 3 is clover, 4 is diamond color ranges [1, 2] 2 is red, 1
 *            is black spade and club must have color = 1 heart and diamond
 *            must have color = 2
 */
public final class DeckOnSequence<A> extends DeckSecondary<A> {
    /*
     * Private members --------------------------------------------------------
     */

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

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public DeckOnSequence() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public Deck<A> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public void transferFrom(Deck<A> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof DeckOnSequence<?> : ""
                + "Violation of: source is of dynamic type Map1L<?,?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Map2<?,?>, and
         * the ?,? must be K,V or the call would not have compiled.
         */
        DeckOnSequence<A> localSource = (DeckOnSequence<A>) source;
        this.cards = localSource.cards;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
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

    @Override
    public void addCard(int pos, int[] card) {
        this.cards.add(pos - 1, card);
    }

    @Override
    public int[] removeCard(int pos) {
        return this.cards.remove(pos - 1);
    }

    @Override
    public int removeCard(int val, int suit) {
        final int ft = 14;
        final int f = 5;
        assert val > 0 : "Violation of: val is greater than 0";
        assert val < ft : "Violation of: val is less than 14";
        assert suit > 0 : "Violation of: suit is greater than 0";
        assert suit < f : "Violation of: val is less than 4";
        int index = -1;
        Boolean hasCard = false;
        for (int i = 0; i < this.cards.length() && !hasCard; i++) {
            int[] tempCard = this.cards.entry(i);
            if (tempCard[0] == val) {
                if (tempCard[1] == suit) {
                    hasCard = true;
                    index = i + 1;
                    this.cards.remove(i);
                }
            }
        }
        return index;
    }

    @Override
    public int length() {
        return this.cards.length();
    }

    @Override
    public int[] seeCard(int pos) {
        return this.cards.entry(pos - 1);
    }

    @Override
    public int position(int val, int suit) {
        final int ft = 14;
        final int f = 5;
        assert val > 0 : "Violation of: val is greater than 0";
        assert val < ft : "Violation of: val is less than 14";
        assert suit > 0 : "Violation of: suit is greater than 0";
        assert suit < f : "Violation of: val is less than 4";
        int index = -1;
        Boolean hasCard = false;
        for (int i = 0; i < this.cards.length() && !hasCard; i++) {
            int[] tempCard = this.cards.entry(i);
            if (tempCard[0] == val) {
                if (tempCard[1] == suit) {
                    hasCard = true;
                    index = i + 1;
                }
            }
        }
        return index;
    }

    @Override
    public int seeV(int pos) {
        return this.cards.entry(pos - 1)[0];
    }

    @Override
    public int seeS(int pos) {
        return this.cards.entry(pos - 1)[1];
    }

    @Override
    public int seeC(int pos) {
        return this.cards.entry(pos - 1)[2];
    }

    @Override
    public Boolean hasCard(int val, int suit) {
        final int ft = 14;
        final int f = 5;
        assert val > 0 : "Violation of: val is greater than 0";
        assert val < ft : "Violation of: val is less than 14";
        assert suit > 0 : "Violation of: suit is greater than 0";
        assert suit < f : "Violation of: val is less than 4";
        Boolean hasCard = false;
        for (int i = 0; i < this.cards.length() && !hasCard; i++) {
            int[] tempCard = this.cards.entry(i);
            if (tempCard[0] == val) {
                if (tempCard[1] == suit) {
                    hasCard = true;
                }
            }
        }
        return hasCard;
    }

    /**
     * Implementation of {@code Iterator} interface for {@code Map1L}.
     */
    @SuppressWarnings("unused")
    private final class DeckOnSequenceIterator implements Iterator<int[]> {

        /**
         * Representation iterator.
         */
        private final Iterator<int[]> iterator;

        /**
         * No-argument constructor.
         */
        private DeckOnSequenceIterator() {
            this.iterator = DeckOnSequence.this.cards.iterator();
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public int[] next() {
            assert this.hasNext() : "Violation of: ~this.unseen /= <>";
            if (!this.hasNext()) {
                /*
                 * Exception is supposed to be thrown in this case, but with
                 * assertion-checking enabled it cannot happen because of assert
                 * above.
                 */
                throw new NoSuchElementException();
            }
            int[] entry = this.iterator.next();
            return new int[] { (entry[0]), (entry[1]), (entry[2]) };
        }
    }

    @Override
    public Iterator<int[]> iterator() {
        throw new UnsupportedOperationException("int[] iterator not supported");
    }



}
