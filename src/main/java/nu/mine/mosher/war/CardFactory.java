package nu.mine.mosher.war;

import java.util.Random;
import java.util.random.RandomGenerator;

public class CardFactory {
    private final RandomGenerator rnd;

    public CardFactory(final long seed) {
        this.rnd = new Random(seed);
    }

    public Deck create() {
        final Deck deck = empty();
        for (int s = 0; s < 4; ++s) {
            for (int i = 0; i < 13; ++i) {
                deck.addToBottom(new Card(i + 1));
            }
        }
        return deck;
    }

    public Deck empty() {
        return new Deck(this.rnd);
    }
}
