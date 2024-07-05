package nu.mine.mosher.war;

import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.ansi;

public interface WarPrinter {
    void printHeader();
    void printSizes(int round, Deck x, Deck y);
    void printFlagWar();
    void printFlagY();
    void printFlagX();
    void printDraw();
    void printWarSize(int s);
    void printDeployment(Deck x, Deck y, BattleFront battlefront);
    void printWinner(int w);
    void printNewLine();
    void printSeed(long seed);
    void printDeck(Deck deck);
}
