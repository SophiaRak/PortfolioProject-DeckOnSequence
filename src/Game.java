/**
 * @author Sophia rakowsky
 */
public final class Game {

    /**
     * private constructor for main deck.
     */
    private DeckOnSequence<int[]> d;
    /**
     * private constructor for hand 1.
     */
    private DeckOnSequence<int[]> h1;
    /**
     * private constructor for hand 2.
     */
    private DeckOnSequence<int[]> h2;
    /**
     * private constructor for total number of cards.
     */
    private final int numCards = 52;


    /**
     * creates initial rep of full deck and deals cd cards to each hand.
     * @param d
     *          deck to be dealt from
     * @param h1
     *          hand 1 to be dealt to
     * @param h2
     *          hand 2 to be dealt to
     * @param cd
     *          cards to be dealt
     */
    private Game(DeckOnSequence<int[]> d, DeckOnSequence<int[]>
    h1, DeckOnSequence<int[]> h2, int cd) {
        assert cd < (numCards / 2) : "Violation of: enough cards in deck to deal";
        //final ints
        final int fourteen = 14;
        final int five = 5;
        //creates and shuffles new deck
        this.d = d;
        int i = 1;
        for (int v = 1; v < fourteen; v++) {
            for (int s = 1; s < five; s++) {
                this.d.addCard(i, v, s);
                i++;
            }
        }
        d.shuffle();
        //creates hands
        this.h1 = h1;
        this.h2 = h2;
        Deck<int[]>[] hl = new Deck[]{h1, h2};
        //dealing cards
        d.deal(cd, hl);
    }

    /**
     * ensures deck and hands combined make 1 normal playing deck of cards.
     * @return whether all cards are accounted for either in deck or hands.
     */
    private boolean conventionHolds() {
        boolean b = true;
        if ((this.d.length() + this.h1.length() + this.h2.length()) != numCards) {
            b = false;
        } else {
            //could be while statement, would save runtime
            for (int i = 1; i < d.length() + 1; i++) {
                if (h1.hasCard(d.seeV(i), d.seeS(i))
                || h2.hasCard(d.seeV(i), d.seeS(i))) {
                    b = false;
                }
            }
            for (int j = 1; j < h1.length() + 1; j++) {
                if (h2.hasCard(h1.seeV(j), h1.seeS(j))) {
                    b = false;
                }
            }
        }
        return b;
    }

}
