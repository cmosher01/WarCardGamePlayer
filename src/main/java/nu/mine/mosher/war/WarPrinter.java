package nu.mine.mosher.war;

import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.ansi;

public interface WarPrinter {
    void printHeader();
    void printSizes(final int round, final Deck x, final Deck y);
    void printFlagWar();
    void printFlagY();
    void printFlagX();
    void printDraw();
    void printWarSize(int s);
    void printDeployment(final Deck bootyX, final Deck bootyY, final BattleFront battlefront);
    void printWinner(final int w);
    void printNewLine();
    void printSeed(final long seed);
}
