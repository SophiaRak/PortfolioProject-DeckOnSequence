import components.standard.Standard;

/**
 * Sequential deck kernel component with primary methods. (Note:
 * by package-wide convention, all references are non-null.)
 * @param <A>
 *          int array of {@code DeckKernal} entries value, suit
 *
 * @mathmodel type DeckKernel is modeled by sequence(int[]) of cards
 * @initially <pre>
 * default:
 *  ensures
 *   this = <>
 * </pre>
 * @iterator ~this.seen * ~this.unseen = this
 */
public interface DeckKernel<A> extends Standard<Deck<A>>,
Iterable<int[]> {

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
    void addCard(int pos, int val, int suit);

    /**
     * Adds the card ({@code val}, {@code suit}, {@code color}) to
     * the position {@code pos} of {@code this}.
     * @param pos
     *             the position to add the card to
     * @param card
     *              the card to be added
     * @clears {@code card},
     * @updates this
     * @requires <pre>
     * pos >= 0
     * pos <= |#this|
     * </pre>
     * @ensures this = #this[0, pos) * <card> * #this[pos, |#this|)
     */
    void addCard(int pos, int[] card);

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
    int[] removeCard(int pos);

    /**
     * Removes card with {@code val}, {@code suit}, {@code color} from
     * {@code this} and returns its position in {@code this}.
     * @param val
     *           the value to be removed
     * @param suit
     *            the associated suit to be removed
     * @return the position the removed card was in
     * @updates this
     * @requires <pre>
     * |this| >= position and
     * removeCard is in #this
     * </pre>
     * @ensures #this = this[0, <removeCard> - 1) * card * this[<removeCard> - 1, |this|)
     *
     */
    int removeCard(int val, int suit);

    /**
     * Reports length of {@code this}.
     *
     * @return the length of {@code this}
     * @ensures length = |this|
     */
    int length();

    /**
     * returns the entry at position {@code pos} of {@code this}.
     *
     * @param pos
     *             the position at which the entry to be returned is found
     * @return the entry at {@code pos} of {@code this}
     * @requires |this| > pos
     * pos >= 0
     * @ensures this = #this
     */
    int[] seeCard(int pos);

        /**
     * Reports the position of {@code card} in {@code this}.
     *
     * @param val
     *           the value to look for
     * @param suit
     *            the associated suit to check for after value is found
     * @return the position of card({@code val}, {@code suit}, {@code color})
     *         in {@code this}
     * @requires this.hasCard(card)
     * @ensures #this = this[0, position) * card * this(position, |this|)
     */
    int position(int val, int suit);

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
    int seeV(int pos);

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
    int seeS(int pos);

    /**
     * returns the color of entry at position {@code pos} of {@code this}.
     *
     * @param pos
     *             the position at which the entry to be returned is found
     * @return the value of entry at {@code pos} of {@code this}
     * @requires |this| > pos
     * pos >= 0
     * @ensures this = #this
     */
    int seeC(int pos);

    /**
     * searches for and returns the existence of a card of {@code val}, {@code
     * suit}, {@code color} in {@code this}.
     *
     * @param val
     *           the value to find
     * @param suit
     *            the associated suit to find
     * @return true if {@code card} is in {@code this}
     * @requires this /= <>
     * @ensures this = #this
     */
    Boolean hasCard(int val, int suit);
}

