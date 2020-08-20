package nu.mine.mosher.war;



public class Card {
    private final int rank;

    public Card(final int rank) {
        this.rank = rank;
    }

    public int rank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return String.format("%02d", rank);
    }
}
