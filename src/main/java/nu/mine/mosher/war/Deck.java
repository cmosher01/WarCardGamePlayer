package nu.mine.mosher.war;



import java.util.Collections;
import java.util.LinkedList;



public class Deck {
    private final LinkedList<Card> cards = new LinkedList<>();

    public Deck() {
    }

    public static Deck create() {
        final Deck deck = new Deck();
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
        Collections.shuffle(this.cards);
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
