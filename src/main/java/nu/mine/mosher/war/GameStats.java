package nu.mine.mosher.war;

import java.util.*;

import static nu.mine.mosher.war.War.WAR;

public final class GameStats {
    private final long seed;
    private int winner; // 0=x, 1=y
    private int battles;
    private final int[] wars = new int[Math.ceilDiv((4*13)/2, WAR+1)];

    public GameStats(final long seed) {
        this.seed = seed;
    }

    public long seed() {
        return this.seed;
    }

    public void winner(final int w) { // 0=x, 1=y
        this.winner = w;
    }

    public int winner() {
        return this.winner;
    }

    public void incBattles() {
        ++this.battles;
    }

    public int battles() {
        return this.battles;
    }

    public void incWar(final int size) {
        ++this.wars[size];
    }

    public int[] wars() {
        return this.wars.clone();
    }
}
