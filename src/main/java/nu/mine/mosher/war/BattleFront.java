package nu.mine.mosher.war;

public enum BattleFront {
    FACE_UP('|'),
    FACE_DOWN('-');

    private final char display;

    BattleFront(final char display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return Character.toString(this.display);
    }
}
