/**
 * {@code DeckKernel} enhanced with secondary methods.
 *
 * @param <A>
 *            int array of {@code DeckKernal} entries value, suit
 */
public interface Deck<A> extends DeckKernel<A> {

    /**
     * changes position of card at position {@code start} to {@code end}.
     *
     * @param start
     *            the initial position of card in {@code this}
     * @param end
     *            the ending position of card in {@code this}
     * @aliases reference {@code start}
     * @aliases reference {@code end}
     * @updates this
     * @requires <pre>
     * |this| > start and
     * |this| > end and
     * start != end
     * start >= 0
     * end >= 0
     * </pre>
     * @ensures <pre>
     * IF end > start THEN
     * this = #this[0, start-1) * #this[start, end) * #this[start - 1] * [end, |#this|)
     * ELSE
     * this = #this[0, end-1) * #this[start - 1]
     *        * #this[end-1, start-1) * #this[start, |#this|)
     * </pre>
     */
    void moveCard(int start, int end);

    /**
     * Concatenates ("appends") {@code d} to the end of {@code this}.
     *
     * @param d
     *            the {@code Deck} to be appended to the end of {@code this}
     * @updates this
     * @clears d
     * @ensures this = #this * #d
     */
    void addDeck(Deck<A> d);

    /**
     * deals {@code num} cards from {@code this} to each {@code Deck} in {@code hands} .
     *
     * @param num
     *           the number of cards to deal to each hand
     * @param hands
     *             the array of {@code Deck[]}s (hands) to deal to
     * @requires <pre>
     * num x hands.length < |#this| and
     * num > 0 and
     * hands.length > 0
     * </pre>
     * @updates this
     * @ensures this = #this[num * hands.length, |#this|)
     */
    void deal(int num, Deck<A>[] hands);

    /**
     * removes {@code num} cards from the top(front) of {@code this} and returns
     * them as a new deck.
     *
     * @param num
     *           the number of cards to remove from the top of {@code this}
     * @return the deck containing the first {@code num} cards in {@code this}
     * @requires <pre>
     * pos > 0 and
     * pos <= |#this|
     * </pre>
     * @updates this
     * @ensures this = #this[num, |#this|)
     */
    Deck<A> cut(int num);


    /**
     * randomizes the order of Deck.Cards in {@code this}.
     *
     * @updates this
     * @ensures |this| = |#this|
     */
    void shuffle();

    /**
     * returns true if {@code this} equals {@code d}.
     *
     * @param d
     * @return equal
     */
    boolean equals(Deck<A> d);

    /**
     * prints the deck as a readable string of numbers.
     * @return String of cards with v, s, c
     * @ensures #this = this
     */
    String prettyPrint();


}
