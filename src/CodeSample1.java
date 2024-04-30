import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CodeSample1 demonstrating a deck being shuffled then split evenly between 2 hands.
 * Hand 1 is displayed in terminal.
 *
 * @author Sophia Rakowsky
 *
 */
public class CodeSample1 {
    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        final int half = 26;
        final int fourteen = 14;
        final int five = 5;
        SimpleWriter out = new SimpleWriter1L();
        @SuppressWarnings({ "rawtypes", "unchecked" })
        Deck<int[]> d = new DeckOnSequence();
        int i = 1;
        for (int v = 1; v < fourteen; v++) {
            for (int s = 1; s < five; s++) {
                d.addCard(i, v, s);
                i++;
            }
        }
        d.shuffle();
        Deck<int[]> h1 = new DeckOnSequence<int[]>();
        Deck<int[]> h2 = new DeckOnSequence<int[]>();
        @SuppressWarnings("unchecked")
        Deck<int[]>[] hl = new Deck[]{h1, h2};
        d.deal(half, hl);
        out.print(h1.prettyPrint());
        out.close();
    }
}
