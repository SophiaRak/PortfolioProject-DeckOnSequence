import java.util.Random;

/**
 * Layered implementations of secondary methods {@code moveCard} for
 * {@code Deck}.
 *
 * @param <A>
 *            int array of {@code DeckKernal} entries value, suit
 */
public abstract class DeckSecondary<A> implements Deck<A> {

    @Override
    public final void moveCard(int start, int end) {
        assert start > 0 : "Violation of: start is a positive integer";
        assert start < this
                .length() : "Violation of: start less than this.length";
        assert end < this
                .length() : "Violation of: end is less than this.length";
        assert end > 0 : "Violation of: end is a positive integer";
        assert start != end : "Violation of: start is same as end";

        int v = this.seeV(start);
        int s = this.seeS(start);
        this.removeCard(start);
        this.addCard(end, v, s);
    }

    @Override
    public final void addDeck(Deck<A> d) {
        int i;
        while (d.length() >= 1) {
            i = this.length() + 1;
            this.addCard(i, d.removeCard(1));
        }
    }

    @Override
    public final void deal(int num, Deck<A>[] hands) {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < hands.length; j++) {
                hands[j].addCard(i + 1, this.removeCard(1));
            }
        }
    }

    @Override
    public final void shuffle() {
        Random r = new Random();
        int bound = this.length();
        for (int i = 0; i < this.length(); i++) {
            int b = r.nextInt(bound);
            if (b == 0) {
                b++;
            }
            int v = this.seeV(b);
            int s = this.seeS(b);
            this.removeCard(b);
            this.addCard(this.length() + 1, v, s);
            bound--;
        }
    }

    @Override
    public final Deck<A> cut(int num) {
        Deck<A> d = new DeckOnSequence<A>();
        for (int i = 1; i < num + 1; i++) {
            d.addCard(i, (this.removeCard(1)));
        }
        return d;
    }

    /**
     *
     * @param d
     * @return equal
     */
    @Override
    public boolean equals(Deck<A> d) {
        if (d == null) {
            return false;
        }
        if (d == this) {
            return true;
        }
        if (!(d instanceof Deck<A>)) {
            return false;
        }
        if (d.length() != this.length()) {
            return false;
        }
        for (int i = 1; i < this.length() + 1; i++) {
            if (this.seeCard(i)[0] != d.seeCard(i)[0]) {
                return false;
            } else if (this.seeCard(i)[1] != d.seeCard(i)[1]) {
                return false;
            } else if (this.seeCard(i)[2] != d.seeCard(i)[2]) {
                return false;
            }
        }
        return true;

    }

    @Override
    public final String toString() {
        String s = "";
        int num;
        for (int i = 1; i < this.length() - 1; i++) {
            s = s + "{";
            for (int j = 0; i < this.seeCard(i).length; i++) {
                num = this.seeCard(i)[j];
                s = s + String.valueOf(num) + ", ";
            }
            s = s + this.seeCard(i).toString() + "}, ";
        }
        s = s + this.seeCard(this.length()).toString();
        return s;
    }

    @Override
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

}
