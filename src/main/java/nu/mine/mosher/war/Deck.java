package nu.mine.mosher.war;



import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;



public class Deck {
    private final LinkedList<Card> cards = new LinkedList<>();
    private final Random rnd;

    public Deck(final Random rnd) {
        this.rnd = rnd;
    }

    public static Deck create(final Random rnd) {
        final Deck deck = new Deck(rnd);
        for (int s = 0; s < 4; ++s) {
            for (int i = 0; i < 13; ++i) {
                deck.addToBottom(new Card(i + 1));
            }
        }
        return deck;
    }

    public void addToBottom(final Card card) {
        cards.addLast(card);
    }

    public void shuffle() {
        Collections.shuffle(this.cards, rnd);
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
}
