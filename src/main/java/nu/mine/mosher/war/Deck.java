package nu.mine.mosher.war;



import java.util.Collections;
import java.util.LinkedList;
import java.util.random.RandomGenerator;


public class Deck {
    private final LinkedList<Card> cards = new LinkedList<>();
    private final RandomGenerator rnd;

    Deck(final RandomGenerator rnd) {
        this.rnd = rnd;
    }

    public void addToBottom(final Card card) {
        this.cards.addLast(card);
    }

    public void shuffle() {
        Collections.shuffle(this.cards, this.rnd);
    }

    public Card deal() {
        return this.cards.removeFirst();
    }

    public Card last() {
        return this.cards.peekLast();
    }

    public boolean any() {
        return size() > 0;
    }

    public int size() {
        return this.cards.size();
    }

    public void addDeck(final Deck war) {
        war.shuffle();
        while (war.any()) {
            addToBottom(war.deal());
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final Card card : this.cards) {
            sb.append(card);
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
