package nu.mine.mosher.war;



public class Card {
    private final int rank;

    Card(final int rank) {
        this.rank = rank;
    }

    public int rank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return String.format("%s", display());
    }

    public String display() {
        final int v = this.rank+1;
        return switch (v) {
            case 14 -> "A";
            case 13 -> "K";
            case 12 -> "Q";
            case 11 -> "J";
            default -> Integer.toString(v);
        };
    }
}
